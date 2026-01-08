package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.indep.exceptions.myException;

/**
 * es wird ein text unter einem Sessionhash gespeichert, ohne modulkenner
 * @author martin
 *
 */
public class E2_UserSetting_TextSaver extends E2_UserSetting_SIMPLE {
	
	private static String NOMOD = "NOMOD";
	
	public E2_UserSetting_TextSaver(ENUM_USER_SAVEKEY hash) {
		super(hash.name());
	}
	
	public void save_values(String cValues) throws myException {
		super.STORE(E2_UserSetting_TextSaver.NOMOD, cValues);
	}
	
	public String get_values() throws myException {
		return (String)super.get_Settings(E2_UserSetting_TextSaver.NOMOD);
	}

}
