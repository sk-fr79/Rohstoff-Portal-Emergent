package panter.gmbh.Echo2.UserSettings;

import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_CheckBoxAnAus extends XXX_UserSetting {

	private String cSessionHash = null;
	
	public E2_UserSetting_CheckBoxAnAus(String SessionHash) 
	{
		super();
		this.cSessionHash = SessionHash;
	}

	@Override
	public String get_SessionHash() 
	{
		return this.cSessionHash;
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
