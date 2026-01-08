package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing_but_EditAdress;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


public class MMC_MailBlock extends MailBlock {
	private MyE2_String 	cStringForLabel = null;
	private MyE2_String 	cStringForSubLabel = null;
	
	public MMC_MailBlock(MyE2_String  StringForLabel, MyE2_String StringForSubLabel)throws myException
	{
		super( new MailSecurityPolicyAllowNothing_but_EditAdress());
		this.cStringForLabel = StringForLabel;
		this.cStringForSubLabel = StringForSubLabel;
	}

	
	public MMC_MailBlock(MyE2_String  StringForLabel, MyE2_String StringForSubLabel, MailSecurityPolicy oSecPoliciy)throws myException
	{
		super( oSecPoliciy);
		this.cStringForLabel = StringForLabel;
		this.cStringForSubLabel = StringForSubLabel;
	}

	
	@Override
	protected MailAdress4MailBlock build_MailAdress4MailBlock(	String mailAdresse, MailBlock OWN_MailBlock) throws myException
	{
		return new MailAdress4MailBlock_STD(mailAdresse,this,this.cStringForSubLabel);
	}

	@Override
	public Component get_ComponentForMailerList()
	{
		return new MyE2_Label(this.cStringForLabel,MyE2_Label.STYLE_SMALL_PLAIN());
	}

	@Override
	public MyString get_ComponentTextForProtokoll()
	{
		return this.cStringForLabel;
	}

	@Override
	public String get_cBelegTyp()
	{
		return new MyE2_String("<kein Beleg>").CTrans();
	}

	@Override
	public MyString get_cBelegTyp4User()
	{
		return new MyE2_String("<kein Beleg>");
	}

	@Override
	public MyString get_cKommentar()
	{
		return new MyE2_String("<Kein Kommentar>");
	}

	@Override
	public String get_cModulInfo()
	{
		return "MASSMAILER";
	}

	@Override
	protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
	{
		return new MailAdress4MailBlock_STD("",this,new MyE2_String("<freie Eingabe>"));
	}

	@Override
	protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
	{
		return new MailAdress4MailBlock_STD(mailAdress,this,new MyE2_String("<Mitarbeiteradresse>"));
	}

	@Override
	protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
	{
		return new MailAdress4MailBlock_STD(mailAdress,this,new MyE2_String("<Adresse aus Firmenstamm>"));
	}


	public void set_cStringForLabel(MyE2_String c_StringForLabel) {
		this.cStringForLabel = c_StringForLabel;
	}


	public void set_cStringForSubLabel(MyE2_String c_StringForSubLabel) {
		this.cStringForSubLabel = c_StringForSubLabel;
	}



}
