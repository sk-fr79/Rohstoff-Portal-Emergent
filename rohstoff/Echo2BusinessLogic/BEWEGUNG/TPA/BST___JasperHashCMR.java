package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD_MitEditPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;
import rohstoff.utils.MAILCOLLECTORS.MAILFillerTPA;

public class BST___JasperHashCMR extends E2_JasperHASH
{
	private String cModulName = null;
	

	public BST___JasperHashCMR(String ModulName, String cID_VPOS_TPA_FUHRE, String cID_VPOS_TPA_FUHRE_ORT, boolean bMail_Is_Yes_In_PrintProtokoll) 	throws myException
	{
		super("frachtbrief_cmr", new JasperFileDef_PDF());
		
		this.cModulName = ModulName;
		
		if ( (S.isEmpty(cID_VPOS_TPA_FUHRE)))
			throw new myException(this,"Programm error. Allowed: FUHRE + NULL or FUHRE+FUHRE_ORT !");
		
	
		String cSQL_Query = "SELECT * FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN " +
									" WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE+" AND ID_VPOS_TPA_FUHRE_ORT IS NULL";

		if (S.isFull(cID_VPOS_TPA_FUHRE_ORT))
		{
			cSQL_Query = "SELECT * FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN " +
				" WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE+" AND ID_VPOS_TPA_FUHRE_ORT="+cID_VPOS_TPA_FUHRE_ORT;
		}
		
		RECORD_VPOS_TPA_FUHRE recVPOS_TPA_FUHRE = new RECORD_VPOS_TPA_FUHRE(cSQL_Query);
		

		
		
		//nachsehen, ob die fuhre zu einem TPA gehoert, wegen der adresse der spedition
		String cID_ADRESSE_for_Mails =  bibALL.get_ID_ADRESS_MANDANT();
		
		if (recVPOS_TPA_FUHRE.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
		{
			cID_ADRESSE_for_Mails = recVPOS_TPA_FUHRE.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_UP_RECORD_ADRESSE_id_adresse().get_ID_ADRESSE_cUF();
		}
		else
		{
			if (recVPOS_TPA_FUHRE.get_UP_RECORD_ADRESSE_id_adresse_spedition()!=null)
			{
				cID_ADRESSE_for_Mails = recVPOS_TPA_FUHRE.get_UP_RECORD_ADRESSE_id_adresse_spedition().get_ID_ADRESSE_cUF();
			}
		}
		
		this.get_vID_ADRESSE_FOR_MailLoad().removeAllElements();
		this.get_vID_ADRESSE_FOR_MailLoad().add(cID_ADRESSE_for_Mails);
		
		if (S.isFull(cID_VPOS_TPA_FUHRE_ORT))
		{
			BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE_ORT(
					cID_VPOS_TPA_FUHRE,cID_VPOS_TPA_FUHRE_ORT, myCONST.FUHRE_BELEG_CMR,bMail_Is_Yes_In_PrintProtokoll,this);
		}
		else if (S.isFull(cID_VPOS_TPA_FUHRE))
		{
			BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE(
					cID_VPOS_TPA_FUHRE, myCONST.FUHRE_BELEG_CMR,bMail_Is_Yes_In_PrintProtokoll,this);
		}
		

		
		this.put("id_vpos_tpa_fuhre",cID_VPOS_TPA_FUHRE);
		this.put("id_vpos_tpa_fuhre_ort",S.isFull(cID_VPOS_TPA_FUHRE_ORT)?cID_VPOS_TPA_FUHRE_ORT:"-1");
		this.put("id_vpos_tpa","-1");

		this.set_cDownloadAndSendeNameStaticPart("cmr_frachtbrief");
		this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART("SELECT NVL(BUCHUNGSNR_FUHRE,'-') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE);
		
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
			new MAILFillerTPA().fill_MAILAdresses_to_MailBLOCK(this, BST___JasperHashCMR.this, true);
			
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

//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
//		{
//			return null;
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//		{
//			return null;
//		}

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
			return new MyE2_String("CMR Frachtbrief");
		}

		@Override
		public String get_cBelegTyp()
		{
			return "CMRFRACHTBRIEF";
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("CMR Frachtbrief");
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("Mailversendung eines CMR Frachtbrief");
		}

		@Override
		public String get_cModulInfo()
		{
			return BST___JasperHashCMR.this.cModulName;
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
	
	
	
	
	
}
