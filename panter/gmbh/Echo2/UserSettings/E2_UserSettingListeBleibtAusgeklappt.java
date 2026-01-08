package panter.gmbh.Echo2.UserSettings;

import panter.gmbh.indep.exceptions.myException;

public class E2_UserSettingListeBleibtAusgeklappt extends XXX_UserSetting {

	
	public E2_UserSettingListeBleibtAusgeklappt() {
		super();
	}

	@Override
	public String get_SessionHash() 
	{
		return "__LIST_EXPANDER_OPEN_CLOSE_SAVE";
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{
		return (String)oSetting;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
	{
		return cDatabaseSetting;
	}

}
