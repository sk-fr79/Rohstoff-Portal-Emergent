package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class E2_JasperHASH_STD extends E2_JasperHASH
{
	
	private String 				cMODULENAME = 				null;
	private MailSecurityPolicy  secPolicy4MailBlock = 		null;
	private MyString 			cLabelString4MailBlock = 	null;
	private MyString 			cLabelString4MailAdress = 	null;
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
	public E2_JasperHASH_STD(		String 				nameOfReport, 
									JasperFileDef 		jasperFileDef,
									String 				cmodulename, 
									String 				cbelegname,
									MailSecurityPolicy 	secPolicy4MailBlock,
									MyString 			labelString4MailBlock, 
									MyString 			labelString4MailAdress,
									boolean 			isDesigned4Mail) throws myException
	{
		super(nameOfReport, jasperFileDef);
		cMODULENAME = cmodulename;
		cBELEGNAME = cbelegname;
		this.secPolicy4MailBlock = secPolicy4MailBlock;
		cLabelString4MailBlock = labelString4MailBlock;
		cLabelString4MailAdress = labelString4MailAdress;
		bIsDesigned4Mail = isDesigned4Mail;
	}


	public E2_JasperHASH_STD(String nameOfReport, JasperFileDef jasperFileDef)	throws myException
	{
		super(nameOfReport, jasperFileDef);
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
			super(E2_JasperHASH_STD.this.secPolicy4MailBlock);
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,this,new MyE2_String("Basisadresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Freie Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Belegschaftsadresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Firmenadresse"));
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(E2_JasperHASH_STD.this.cLabelString4MailBlock, MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return E2_JasperHASH_STD.this.cLabelString4MailBlock;
		}

		@Override
		public String get_cBelegTyp()
		{
			return E2_JasperHASH_STD.this.cBELEGNAME;
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String(E2_JasperHASH_STD.this.cBELEGNAME);
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("<Kein Kommentar>");
		}

		@Override
		public String get_cModulInfo()
		{
			return E2_JasperHASH_STD.this.cMODULENAME;
		}
	}
	
	
	
	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyString cAddOn = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo, MyString cAddon) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, null);
			this.cAddOn = cAddon;
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
			// MyString cHelp = E2_JasperHASH_STD.this.cLabelString4MailAdress;   //2014-01-15: fehler: string wurde mit jeder anfuegung groesser
			MyString cHelp = new MyE2_String(E2_JasperHASH_STD.this.cLabelString4MailAdress.CTrans(),false);
			
			cHelp.addString(this.cAddOn);
			
			return cHelp;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}
		
	}
	
	
}
