package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MMA_JasperHASH extends E2_JasperHASH
{

	private String 				cMODULENAME = 				null;
	private MailSecurityPolicy  secPolicy4MailBlock = 		null;
	private MyString 			cLabelString4MailBlock = 	null;
	private boolean 			bIsDesigned4Mail = 			false; 
	private String   			cBELEGNAME = 				null;
	
	
	/**
	 * 
	 * @param nameOfReport
	 * @param jasperFileDef
	 * @param cmodulename
	 * @param cbelegname
	 * @param secPolicy4MailBlock
	 * @param labelString4MailBlock
	 * @param labelString4MailAdress
	 * @param isDesigned4Mail
	 * @throws myException
	 */
	public MMA_JasperHASH(		String 				nameOfReport, 
									JasperFileDef 		jasperFileDef,
									String 				cmodulename, 
									String 				cbelegname,
									MailSecurityPolicy 	secPolicy4MailBlock,
									MyString 			labelString4MailBlock, 
									boolean 			isDesigned4Mail) throws myException
	{
		super(nameOfReport, jasperFileDef);
		cMODULENAME = cmodulename;
		cBELEGNAME = cbelegname;
		this.secPolicy4MailBlock = secPolicy4MailBlock;
		cLabelString4MailBlock = labelString4MailBlock;
		bIsDesigned4Mail = isDesigned4Mail;
	}



	
	@Override
	protected MailBlock create_MailBlock() throws myException
	{
		return new ownMailBlock();
	}

	@Override
	public boolean get_bIsDesignedForMail()
	{
		return this.bIsDesigned4Mail;
	}

	@Override
	public void doActionAfterCreatedFile() throws myException
	{
	}
	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() 	throws myException
		{
			super(MMA_JasperHASH.this.secPolicy4MailBlock);
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,this,new MyE2_String("Basisadresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Manuell zugefügt: Freie Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Manuell zugefügt: Belegschaftsadresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Manuell zugefügt: Firmenadresse"));
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(MMA_JasperHASH.this.cLabelString4MailBlock, MyE2_Label.STYLE_SMALL_BOLD(),true);
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return MMA_JasperHASH.this.cLabelString4MailBlock;
		}

		@Override
		public String get_cBelegTyp()
		{
			return MMA_JasperHASH.this.cBELEGNAME;
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String(MMA_JasperHASH.this.cBELEGNAME);
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("<Kein Kommentar>");
		}

		@Override
		public String get_cModulInfo()
		{
			return MMA_JasperHASH.this.cMODULENAME;
		}
	}
	
	
	
	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyString cBeschreibung = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo, MyString Beschreibung) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, null);
			this.cBeschreibung = Beschreibung;
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_cComponentText(), MyE2_Label.STYLE_SMALL_ITALIC());
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return "<ADRESSINFO>";
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cBeschreibung;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}
		
	}

}
