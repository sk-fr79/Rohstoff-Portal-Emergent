package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD_MitEditPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;
import rohstoff.utils.MAILCOLLECTORS.MAILFillerTPA;

public class BST___JasperHashLIEFERSCHEIN_multi extends E2_JasperHASH
{
	private String cModulName = null;
	private Vector<String> vIDs_Fuhren = new Vector<String>();

	/**
	 * beim Sammellieferschein werden alle noetigen EU-Blaetter hinterangehaengt
	 * Dazu wird ein  Jasper_Exe_ - object verwendet
	 */
	private Vector<BST___JasperHash_NUR_EU_BLAETTER>  vJasperHashAnhang7 = new Vector<BST___JasperHash_NUR_EU_BLAETTER>();
	
	
	public BST___JasperHashLIEFERSCHEIN_multi(String ModulName, Vector<String> vIDsFuhren, boolean bMail_Is_Yes_In_PrintProtokoll) 	throws myException
	{
		super("lieferschein_multi", new JasperFileDef_PDF());
		
		this.vIDs_Fuhren.addAll(vIDsFuhren);
		
		if (vIDs_Fuhren.size()==0) {
			throw new myException(this,"Bitte eine Fuhre wählen !");  //sollte vorher schon abgefangen werden
		}
		
		
		//hier das jasperExe-Object einfuegen, das die anhang-7 -verarbeitung macht
		this.get_vExecuters().add(new __JASPER_EXEC_HaengeZusatzFilesAnHauptPDF());
		
		this.cModulName = ModulName;
		
		//validierung der adressen und anhang7 - dokus und einhaengen der noetigen "nur_eu_blatt"-dokus
		// 
		
		for (String cID_VPOS_TPA_FUHRE: vIDs_Fuhren) {

			BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE(
					cID_VPOS_TPA_FUHRE, myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI,bMail_Is_Yes_In_PrintProtokoll,this);

			
			__SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE  recFuhre = new __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE,null);
			RECLIST_VPOS_TPA_FUHRE_ORT  rlFuhrenORT = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'",null, true);

			//in zusatzvector die zusaetzlichen jasperhashs fuer die anhang7 - blaetter
			if (recFuhre.is_PRINT_EU_AMTSBLATT_YES()) {
				this.vJasperHashAnhang7.add(new BST___JasperHash_NUR_EU_BLAETTER(this.cModulName,recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
			}
			
			for (RECORD_VPOS_TPA_FUHRE_ORT  oRT: rlFuhrenORT.values()) {
				if (oRT.get_DEF_QUELLE_ZIEL_cUF().equals("VK")) {
					new __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE,oRT.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
					
					//in zusatzvector die zusaetzlichen jasperhashs fuer die anhang7 - blaetter
					if (oRT.is_PRINT_EU_AMTSBLATT_YES()) {
						this.vJasperHashAnhang7.add(new BST___JasperHash_NUR_EU_BLAETTER(this.cModulName,recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),oRT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
					}
					
					BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE_ORT(
							cID_VPOS_TPA_FUHRE,oRT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI, bMail_Is_Yes_In_PrintProtokoll,this);
				}
			}
		}
		
		
		//jetzt absichern, ob ein fehler aufgetreten ist, wenn ja, dann wird das jasperhash-objekt unbrauchbar gemacht (sicherheitshalber)
		if (bibMSG.get_bHasAlarms())
		{
			this.set_Basics("dummy");   //kein definerter reportname
			return;
		}
		
		
		//zur suche der Mailadressen wird die adresse der ersten fuhre benutzt
		RECORD_VPOS_TPA_FUHRE  recFuhreStellvertretend = new RECORD_VPOS_TPA_FUHRE(vIDs_Fuhren.get(0));
		
		//nachsehen, ob die fuhre zu einem TPA gehoert, wegen der adresse der spedition (sonst ist die Mandanten-Adresse die zieladresse
		String cID_ADRESSE_for_Mails = bibALL.get_ID_ADRESS_MANDANT();
		
		if (recFuhreStellvertretend.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
		{
			cID_ADRESSE_for_Mails = recFuhreStellvertretend.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_UP_RECORD_ADRESSE_id_adresse().get_ID_ADRESSE_cUF();
		}
		else
		{
			if (recFuhreStellvertretend.get_UP_RECORD_ADRESSE_id_adresse_spedition()!=null)
			{
				cID_ADRESSE_for_Mails = recFuhreStellvertretend.get_UP_RECORD_ADRESSE_id_adresse_spedition().get_ID_ADRESSE_cUF();
			}
		}
			
		this.get_vID_ADRESSE_FOR_MailLoad().removeAllElements();
		this.get_vID_ADRESSE_FOR_MailLoad().add(cID_ADRESSE_for_Mails);

		String cVectorInBlock ="("+bibALL.Concatenate(vIDs_Fuhren, ",", "-1")+")";
		
		this.put("id_vpos_tpa_fuhre_in_block",cVectorInBlock);

		this.set_cDownloadAndSendeNameStaticPart("lieferschein_sammel_ab_");
		this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART(
				"SELECT MIN(NVL(BUCHUNGSNR_FUHRE,'-')) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN "+cVectorInBlock);
		
		//einen executer fuer das schreiben der mailadressen zu den printlog-dateien
		this.get_vExecuters().add(new Jasper_Exe_WriteEMailToPrintProtokoll(_DB.VPOS_TPA_FUHRE_DRUCK,
																			_DB.VPOS_TPA_FUHRE_DRUCK_EM,
																			_DB.VPOS_TPA_FUHRE_DRUCK_EM$ID_VPOS_TPA_FUHRE_DRUCK,
																			_DB.VPOS_TPA_FUHRE_DRUCK_EM$EMAIL_SEND,
																			_DB.VPOS_TPA_FUHRE_DRUCK_EM$EMAIL_RECEIVE));

	}

	
	

	@Override
	public MailBlock create_MailBlock() throws myException
	{
		return new ownMailBlock();
	}
	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			//super(new MailSecurityPolicyAllowNothing_but_EditAdress());
			// 2013-06-24: neue policy: freie adresseingabe
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
			new MAILFillerTPA().fill_MAILAdresses_to_MailBLOCK(this, BST___JasperHashLIEFERSCHEIN_multi.this, true);
			
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new MailAdress4MailBlock_STD_MitEditPolicy("",OWN_MailBlock,new MyE2_String("Freie eMail"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new MailAdress4MailBlock_STD_MitEditPolicy(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"));
		}


		
		@Override
		public MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}


		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return new MyE2_String("Sammel-Lieferschein");
		}

		@Override
		public String get_cBelegTyp()
		{
			return myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI;
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("Sammel-Lieferschein");
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("Mailversendung eines Sammel-Lieferschein");
		}

		@Override
		public String get_cModulInfo()
		{
			return BST___JasperHashLIEFERSCHEIN_multi.this.cModulName;
		}
		
	}
	
	



	@Override
	public boolean get_bIsDesignedForMail()
	{
		return true;
	}
	
	@Override
	public void doActionAfterCreatedFile() throws myException
	{
	}




	public Vector<BST___JasperHash_NUR_EU_BLAETTER> get_vJasperHashAnhang7() {
		return vJasperHashAnhang7;
	}
	

}
