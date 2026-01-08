package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MailAdress4MailBlock_STD extends MailAdress4MailBlock
{

	private MyString cInfo = null;

	public MailAdress4MailBlock_STD(String mailAdress,MailBlock MailBlockThisBelongsTo,MyString Info) throws myException
	{
		super(mailAdress, MailBlockThisBelongsTo, null);
		this.cInfo = Info;
	}

	@Override
	public Component get_ComponentForMailerList()
	{
		return new MyE2_Label(this.cInfo,MyE2_Label.STYLE_SMALL_ITALIC());
	}
	
	@Override
	public String get_cAdressInfo()
	{
		return new MyE2_String("<Keine Adresse>").CTrans();
	}

	@Override
	public MyString get_cComponentText()
	{
		return this.cInfo;
	}

	@Override
	public String get_cID_ADRESSE_EMPFAENGER()
	{
		return "";
	}

}
