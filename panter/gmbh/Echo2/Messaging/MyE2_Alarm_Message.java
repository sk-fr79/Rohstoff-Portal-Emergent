package panter.gmbh.Echo2.Messaging;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;


public class MyE2_Alarm_Message extends MyE2_Message 
{

	public MyE2_Alarm_Message(MyString cmessage) 
	{
		super(cmessage, MyE2_Message.TYP_ALARM, false);
	}

	
	public MyE2_Alarm_Message(MyString cmessage, boolean bShowInPopup) 
	{
		super(cmessage, MyE2_Message.TYP_ALARM, bShowInPopup);
	}

	
	/**
	 * 
	 * @param cmessage  (wird uebersetzt)
	 */
	public MyE2_Alarm_Message(String cmessage) 
	{
		super(new MyE2_String(cmessage), MyE2_Message.TYP_ALARM, false);
	}

	public MyE2_Alarm_Message(String cmessage,boolean bTranslation) 
	{
		super(new MyE2_String(cmessage,bTranslation), MyE2_Message.TYP_ALARM, false);
	}



	
}
