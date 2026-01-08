package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.ArrayList;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;

public class HAD__UserSetting extends E2_UserSetting_ListOfSaveables {

	public HAD__UserSetting(ArrayList<IF_Saveable> v_selects) {
		super();
		this._init(v_selects, ENUM_USER_SAVEKEY.MODULEKENNER__SAVE_SELECTION_HELP_AND_DOCUMENTATION);
	}

}
