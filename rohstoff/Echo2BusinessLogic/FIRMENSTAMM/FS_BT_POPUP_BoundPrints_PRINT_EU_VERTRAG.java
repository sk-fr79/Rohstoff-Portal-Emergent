package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BUTTON_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcherNG;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG extends BUTTON_MAIL_AND_REPORT {

	public static String KENNER_KLEMMBRETT_MAIL_BETREFF_EU_VERTRAG = 	"MAIL_BETREFF_EU_VERTRAG";
	public static String KENNER_KLEMMBRETT_MAIL_TEXT_EU_VERTRAG = 		"MAIL_TEXT_EU_VERTRAG";
	
	
	private E2_NavigationList    o_NaviList = null;
	
	
	public FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG(E2_NavigationList    oNaviList) {
		super(new MyE2_String("Drucke EU-Vertrag"), new MyE2_String("EU-Vertrag-Drucken"), "DRUCKE_EU_VERTRAG", "DRUCKE_EU_VERTRAG", null);
		
		this.o_NaviList=oNaviList;
		this.add_GlobalValidator(new ownValidatorValidNoEmptySelection());
		this.add_GlobalValidator(new ownValidatorValidEveryAdressHasReport());
		this.add_GlobalAUTHValidator_AUTO("EU_VERTRAEGE_ERZEUGEN");
		
		
	}

	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException {
		V_JasperHASH  vRueck = new V_JasperHASH();
		Vector<String> vIDs = this.o_NaviList.get_vSelectedIDs_Unformated();
		for (String cID_ADRESSE: vIDs) {
			vRueck.add(new ownJASPER_HASH(new RECORD_ADRESSE(cID_ADRESSE), new JasperFileDef_PDF()));
		}
		return vRueck;
	}

	
	@Override
	public E2_MassMailer get_MassMailer() throws myException {
		return new ownMassMailer();
	}

	
	
	
	private class ownValidatorValidNoEmptySelection extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG.this.o_NaviList.get_vSelectedIDs_Unformated().size()==0) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie mindestens eine Adresse ")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}
	
	
	
	private class ownValidatorValidEveryAdressHasReport extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG oThis = FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG.this;
			
			Vector<String> vID_Adress = oThis.o_NaviList.get_vSelectedIDs_Unformated();
			
			for (String cID_ADRESS: vID_Adress) {
				RECORD_ADRESSE  recADRESS = new RECORD_ADRESSE(cID_ADRESS);
				
				if (S.isEmpty(recADRESS.get_ID_ADRESSE_EU_VERTR_FORM_cUF_NN(""))) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Adresse ohne definiertes Vertragsformular: ",true,
									recADRESS.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$ORT)),false)));
				}
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}
	
	

	
	
	
	private class ownJASPER_HASH extends E2_JasperHASH {

		private RECORD_ADRESSE  rec_ADRESSE = null;
		
		public ownJASPER_HASH(RECORD_ADRESSE recADRESSE, JasperFileDef jasperFileDef) throws myException {
			super(recADRESSE.get_UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form().get_JASPERFORMULAR_cUF_NN(""), jasperFileDef);
			
			if (S.isEmpty(recADRESSE.get_UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form().get_JASPERFORMULAR_cUF_NN(""))) {
				//sollte durch die validierung verhindert werden
				throw new myException(this,"Error: Adress: "+recADRESSE.get_ID_ADRESSE_cUF_NN("<id>")+" has no Contract-Definition !");
			}
			
			this.put("id_adresse", recADRESSE.get_ID_ADRESSE_cUF_NN(""));
			this.rec_ADRESSE = recADRESSE;
		}
		
		
		@Override
		protected MailBlock create_MailBlock() throws myException {
			return new ownMailBlock();
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {
			return true;
		}

		@Override
		public void doActionAfterCreatedFile() throws myException {
		}
		
		
		private class ownMailBlock extends MailBlock
		{
			public ownMailBlock() throws myException
			{
				super(new MailSecurityPolicyAllowAll());
				MailAdressSearcherNG  oMailSearcher = new MailAdressSearcherNG(ownJASPER_HASH.this.rec_ADRESSE, true, true, true, null, null, true, true, true);
				this.add_VMailAdress4MailBlock(oMailSearcher);
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock(mailAdresse,OWN_MailBlock,new MyE2_String("Frei eingegebene MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock("",OWN_MailBlock,new MyE2_String("Frei eingegebene MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarbeiter-MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-MailAdresse"));
			}

			@Override
			public Component get_ComponentForMailerList() throws myException
			{
				return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
			}

			@Override
			public MyString get_ComponentTextForProtokoll() throws myException
			{
				return new MyE2_String("EU-Vertrag-Anschreiben");
			}

			@Override
			public String get_cBelegTyp() throws myException
			{
				return "EU-VERTRAG";
			}

			@Override
			public MyString get_cBelegTyp4User() throws myException
			{
				return new MyE2_String("EU-VERTRAG");
			}

			@Override
			public MyString get_cKommentar() throws myException
			{
				return new MyE2_String("Mail mit EU-VERTRAG");
			}

			@Override
			public String get_cModulInfo() throws myException
			{
				return E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST;
			}
		}
		
		
		private class ownMailAdress4Mailblock extends MailAdress4MailBlock_STD {
			public ownMailAdress4Mailblock(String mailAdress, MailBlock MailBlockThisBelongsTo, MyString Info) throws myException {
				super(mailAdress, MailBlockThisBelongsTo, Info);
			}
		}
		
	}
	
	
	
	private class ownMassMailer extends E2_MassMailer {

		public ownMassMailer() throws myException {
			super(	FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG.KENNER_KLEMMBRETT_MAIL_BETREFF_EU_VERTRAG, 
					FS_BT_POPUP_BoundPrints_PRINT_EU_VERTRAG.KENNER_KLEMMBRETT_MAIL_TEXT_EU_VERTRAG, "EU_VERTRAG_MAIL", new MailSecurityPolicyAllowNothing());
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException {
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails() throws myException {
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException {
			return null;
		}
	}
	
	
}
