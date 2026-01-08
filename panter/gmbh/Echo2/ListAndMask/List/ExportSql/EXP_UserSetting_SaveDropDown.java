package panter.gmbh.Echo2.ListAndMask.List.ExportSql;

import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;


public class EXP_UserSetting_SaveDropDown extends XXX_UserSetting {

	private IF_Field f = null;
	
	public EXP_UserSetting_SaveDropDown(IF_Field field) {
		super();
		this.f = field;
	}

	private String genKey() {
		return this.f.tnfn()+"#TYP";
	}
	
	@Override
	public String get_SessionHash() {
		return ENUM_USER_SAVEKEY.KEY_SAVE_SQLFIELDMASK_4_EXPORT.name();
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
	 * @param string2save
	 * @throws myException
	 */
	public void SAVE(String string2save) throws myException {
		this.STORE(this.genKey(), string2save);
	}
	
	/**
	 * liest den wert wieder ein 
	 * @return
	 * @throws myException
	 */
	public String LOAD() throws myException {
		return (String)this.get_Settings(this.genKey());
	}
	
}
