package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class FU_PRO_JasperHash extends E2_JasperHASH {


	private FU_PRO_PassendeFuhrenInfos 		oFU_PRO_PassendeFuhrenInfos = null;
	private MyE2_CheckBox                  oCB_DieseRechnungWirdGedruckt = new MyE2_CheckBox(true, false);


	private FU_PRO_SettingsGrid    		    oSettingsGrid = null;
	
	public FU_PRO_JasperHash(FU_PRO_PassendeFuhrenInfos fu_PRO_Field4Jasper)	throws myException
	{
		super(fu_PRO_Field4Jasper.get_NameOfReport(),new JasperFileDef_PDF());
		
		this.oCB_DieseRechnungWirdGedruckt.add_oActionAgent(new ownActionAgent());
		
		this.oFU_PRO_PassendeFuhrenInfos = fu_PRO_Field4Jasper;
		
		//jetzt die info- und steuerparameter uebergeben
		this.put("id_adresse", 					fu_PRO_Field4Jasper.get_cID_ADRESSE_ZielFuhre_UF());
		this.put("id_adresse_lager", 			fu_PRO_Field4Jasper.get_cID_ADRESSE_LAGER_ZielFuhre_UF());
		this.put("id_vpos_tpa_fuhre", 			fu_PRO_Field4Jasper.get_cID_VPOS_TPA_FUHRE_UF());
		this.put("id_vpos_tpa_fuhre_ort",		fu_PRO_Field4Jasper.get_cID_VPOS_TPA_FUHRE_ORT_UF());
		this.put("id_proforma_rechnung",		fu_PRO_Field4Jasper.get_RecProforma().get_ID_PROFORMA_RECHNUNG_cUF());
		
		this.set_cDownloadAndSendeNameStaticPart("PROFROMA_RECHNUNG_");
		
	}

	public FU_PRO_PassendeFuhrenInfos get_oFU_PRO_PassendeFuhrenInfos() {
		return oFU_PRO_PassendeFuhrenInfos;
	}
	
	@Override
	protected MailBlock create_MailBlock() throws myException
	{
		ownMailBlock oMailBlock = new ownMailBlock();
		
		String cID_fuer_MailRelevante_Adresse = this.oFU_PRO_PassendeFuhrenInfos.get_bSonderFall() ?
												this.oFU_PRO_PassendeFuhrenInfos.get_RecProforma().get_ID_ADRESSE_KAEUFER_cUF_NN("0")
												:
												this.oFU_PRO_PassendeFuhrenInfos.get_cID_ADRESSE_ZielFuhre_UF();	
		
		oMailBlock.add_VMailAdress4MailBlock(
				new MailAdressSearcher(	
						cID_fuer_MailRelevante_Adresse,
											true,
											true,
											true,
											myCONST.EMAIL_TYPE_VALUE_RECHNUNG,
											new MyE2_String(myCONST.EMAIL_TYPE_VALUE_RECHNUNG),
											null, 
											true, 
											new Boolean(false), 
											new Boolean(true))
					);
		
		return oMailBlock;
	}

	@Override
	public boolean get_bIsDesignedForMail() throws myException
	{
		return true;           //wenn preview, dann keine mail
	}
	
	
	@Override
	public void doActionAfterCreatedFile() throws myException
	{
	}
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Vorhandene eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Freie eMail"),new Boolean(true));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-eMail"),new Boolean(false));
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String("PROFORMA_RECHNUNG");
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return "PROFORMA_RECHNUNG";
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String("PROFORMA_RECHNUNG");
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("Mail aus PROFORMA_RECHNUNG");
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER;
		}
	}

	private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String cStringForComponent = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent, Boolean bAllowEdit) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, bAllowEdit);
			this.cStringForComponent = StringForComponent; 
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.cStringForComponent,MyE2_Label.STYLE_SMALL_ITALIC());
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return "<keine Adressinfo>";
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cStringForComponent;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}
	}

	public FU_PRO_SettingsGrid get_oSettingsGrid() {
		return oSettingsGrid;
	}

	public void set_oSettingsGrid(FU_PRO_SettingsGrid oSettingsGrid) {
		this.oSettingsGrid = oSettingsGrid;
	}


	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (FU_PRO_JasperHash.this.oCB_DieseRechnungWirdGedruckt.isSelected()) {
				FU_PRO_JasperHash.this.set_bActiv();
			} else	{
				FU_PRO_JasperHash.this.set_bInactiv();
			}
		}
		
	}

	public MyE2_CheckBox get_oCB_DieseRechnungWirdGedruckt() {
		return oCB_DieseRechnungWirdGedruckt;
	}


	
}



