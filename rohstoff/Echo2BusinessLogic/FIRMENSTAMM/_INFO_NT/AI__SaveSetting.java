package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI__Const.BLOCK_TO_SHOW;

public class AI__SaveSetting extends E2_UserSetting_ListOfSaveables {

	public AI__SaveSetting(TreeMap<BLOCK_TO_SHOW, IF_Saveable> tm_CB) {
		super();
		ArrayList<IF_Saveable> vCB = new ArrayList<IF_Saveable>();
		Iterator<Entry<BLOCK_TO_SHOW, IF_Saveable>> it = tm_CB.entrySet().iterator();
		while (it.hasNext()) {
			 Map.Entry<BLOCK_TO_SHOW, IF_Saveable> e = it.next();
			 vCB.add(e.getValue());
		}
		this._init(vCB, ENUM_USER_SAVEKEY.MODULKENNER_SAVE_USERSETTINGS_IN_ADRESSINFO);
	}
	
}
