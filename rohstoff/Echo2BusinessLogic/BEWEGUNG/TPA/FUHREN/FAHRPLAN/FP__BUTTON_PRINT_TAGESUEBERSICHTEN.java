package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BUTTON_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public abstract class FP__BUTTON_PRINT_TAGESUEBERSICHTEN extends BUTTON_MAIL_AND_REPORT
{
	public FP__BUTTON_PRINT_TAGESUEBERSICHTEN(MyE2_String fensterTitel,String valid_tag_print, String valid_tag_mail,XX_ActionAgent actionAfterPrintOrMail)
	{
		super("print_fahrplanuebersicht.png", fensterTitel, valid_tag_print, valid_tag_mail, actionAfterPrintOrMail);
	}


	@Override
	public E2_MassMailer get_MassMailer() throws myException
	{
		return new ownMassMailer();
	}
	
	
	protected V_JasperHASH build_JasperHash(String cDateString_YYYY_MM_DD, String cSonderbedingung) throws myException
	{
			
		V_JasperHASH  VJasperHash = new V_JasperHASH();
		VJasperHash.add(new ownJasperHash(cDateString_YYYY_MM_DD, cSonderbedingung));
		
		return VJasperHash;

	}
	
	
	
	private class ownJasperHash extends E2_JasperHASH
	{

		public ownJasperHash(String cDateString_YYYY_MM_DD, String cSonderbedingung) throws myException
		{
			super("fahrplan_tagesuebersicht", new JasperFileDef_PDF());
			this.put("dat_fahrplan",cDateString_YYYY_MM_DD);
			if (S.isFull(cSonderbedingung))
			{
				this.put("zusatzbedingung", cSonderbedingung);
			}
			this.set_cDownloadAndSendeNameStaticPart("FAHRPLAN_UEBERSICHT_");
			this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART(null);
		}

		@Override
		protected MailBlock create_MailBlock() throws myException
		{
			return new ownMailBlock();
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
	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
			this.ADD_NewTargetAdress_interactivEmptyAdress();
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Freie eMail"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Mitarbeiter-eMail"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return new MyE2_String("Fahrplanuebersicht");
		}

		@Override
		public String get_cBelegTyp()
		{
			return "FAHRPLANUEBERSICHT";
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("Fahrplanuebersicht");
		}

		@Override
		public MyString get_cKommentar()
		{
			return null;
		}

		@Override
		public String get_cModulInfo()
		{
			return null;
		}
	}

	
	private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String cStringForComponent = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, null);
			this.cStringForComponent = StringForComponent; 
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.cStringForComponent);
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

	
	private class ownMassMailer extends E2_MassMailer
	{

		public ownMassMailer() throws myException
		{
			super("MAIL_BETREFF_FAHRPLAN_MAIL", "MAIL_BLOCK_FAHRPLAN_MAIL",	"FAHRPLAN", new MailSecurityPolicyAllowNothing());
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails()	throws myException
		{
			return null;
		}
		
	}
	


	
}
