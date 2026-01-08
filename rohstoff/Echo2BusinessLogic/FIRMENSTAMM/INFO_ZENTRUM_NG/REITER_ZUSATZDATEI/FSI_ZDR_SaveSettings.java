package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_ZUSATZDATEI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;

public class FSI_ZDR_SaveSettings extends E2_UserSetting_ListOfSaveables {

	public FSI_ZDR_SaveSettings(TreeMap<String, IF_Saveable> hm_Steuerpult) {
		super();
		ArrayList<IF_Saveable> vCB = new ArrayList<IF_Saveable>();
		Iterator<Entry<String, IF_Saveable>> it = hm_Steuerpult.entrySet().iterator();
		while (it.hasNext()) {
			 Map.Entry<String, IF_Saveable> e = it.next();
			 vCB.add(e.getValue());
		}
		this._init(vCB, ENUM_USER_SAVEKEY.KEY_SAVE_USERSETTINGS_ZUSATZDATEI_IN_ADRESSINFO);
	}
	
}
