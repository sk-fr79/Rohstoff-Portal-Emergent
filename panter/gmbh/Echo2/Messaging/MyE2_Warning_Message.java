package panter.gmbh.Echo2.Messaging;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;


public class MyE2_Warning_Message extends MyE2_Message 
{

	public MyE2_Warning_Message(MyString cmessage) 
	{
		super(cmessage, MyE2_Message.TYP_WARNING, false);
	}
	public MyE2_Warning_Message(MyString cmessage,boolean MustBeShownInPopup) 
	{
		super(cmessage, MyE2_Message.TYP_WARNING, MustBeShownInPopup);
	}
	
	/**
	 * 
	 * @param cmessage (wird uebersetzt)
	 */
	public MyE2_Warning_Message(String cmessage) 
	{
		super(new MyE2_String(cmessage), MyE2_Message.TYP_WARNING, false);
	}

	public MyE2_Warning_Message(String cmessage,boolean bTranslation,boolean MustBeShownInPopup) 
	{
		super(new MyE2_String(cmessage,bTranslation), MyE2_Message.TYP_WARNING, MustBeShownInPopup);
	}

	
}
