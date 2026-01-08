package panter.gmbh.Echo2.UserSettings;

import panter.gmbh.indep.exceptions.myException;


public class E2_UserSetting_SaveOneString extends XXX_UserSetting {

	private ENUM_USER_SAVEKEY    key = null;
	
	
	public E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY  p_key) {
		super();
		this.key = p_key;
	}

	@Override
	public String get_SessionHash() {
		return this.key.name();
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException {
		return (String)oSetting;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException {
		return cDatabaseSetting;
	}

	
	/**
	 * speichert den WERT string2save fuer den user ab
	 * @param string2save
	 * @throws myException
	 */
	public void SAVE(String string2save) throws myException {
		this.STORE(this.key.name(), string2save);
	}
	
	/**
	 * liest den wert wieder ein 
	 * @return
	 * @throws myException
	 */
	public String LOAD() throws myException {
		return (String)this.get_Settings(this.key.name());
	}
	
}
