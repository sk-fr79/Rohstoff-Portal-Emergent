package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap_XMLSTYLE;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_naviList_column_width extends E2_UserSetting_HashMap_XMLSTYLE {

	private E2_NavigationList  f_naviList = null;
	
	public E2_UserSetting_naviList_column_width(E2_NavigationList naviList) {
		super(ENUM_USER_SAVEKEY.SESSIONHASH_SAVE_COLUMN_WIDTH_OF_NAVILIST.name(), new Vector<String>(naviList.get_oComponentMAP__REF().get_vComponentHashKeysSaveOrig()),true);
		this.f_naviList = naviList;
	}
	
	public void SAVE_COL_WIDTHS(HashMap<String, String> hm) throws myException {
		String cMODUL_IDENTIFIER = this.f_naviList.get_AUTOMATIC_GENERATED_KENNUNG();
		if (S.isFull(cMODUL_IDENTIFIER)) {
			this.STORE(cMODUL_IDENTIFIER, hm);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Spaltenbreiten wurden für den nächsten Aufruf gespeichert !")));
		}
	}
	
	
	/**
	 * einlesen der gespeicherten spaltenbreiten in die navilist-Map
	 * @throws myException
	 */
	public HashMap<String, String> READ_STORED_COL_WIDTHS()  throws myException 	{
		HashMap<String, String>  hm = this.READ(this.f_naviList.get_AUTOMATIC_GENERATED_KENNUNG());
		if (hm != null) {
			return hm;
		} else {
			return new HashMap<>();
		}
	}

}
