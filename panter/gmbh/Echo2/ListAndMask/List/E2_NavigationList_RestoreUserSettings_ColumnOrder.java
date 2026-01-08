package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_UserSetting_naviList_column_order;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

/**
 * ausgelagerte klasse zum restaurieren der Spaltenbreiten
 * @author martin
 *
 */
public class E2_NavigationList_RestoreUserSettings_ColumnOrder {
	 
	private E2_NavigationList  navilist_to_restore = null;
	
	public E2_NavigationList_RestoreUserSettings_ColumnOrder(E2_NavigationList p_navilist_to_restore) throws myException {
		super();
		this.navilist_to_restore = p_navilist_to_restore;
		
		E2_ComponentMAP map_ref = this.navilist_to_restore.get_oComponentMAP__REF();
		Vector<String>  v_keys = map_ref.get_vComponentHashKeys();
		
		HashMap<String, String> hmOrder= new E2_UserSetting_naviList_column_order(this.navilist_to_restore).READ_STORED_COL_ORDER();
		if (hmOrder != null) {
			Collections.sort(v_keys, new ownComparator(hmOrder));
		}
		
		//DEBUG.System_print(v_keys,true);
	}

	
	private class ownComparator implements Comparator<String> {

		private HashMap<String, String> sort_map = null;

		public ownComparator(HashMap<String, String> p_sort_map) {
			super();
			this.sort_map = p_sort_map;
		}

		@Override
		public int compare(String s1, String s2) {
			int i1 = 0;
			int i2 = 0;
			
			MyLong l1 = new MyLong(this.sort_map.get(s1));
			MyLong l2 = new MyLong(this.sort_map.get(s2));
			
			if (l1.get_bOK()) { i1 = l1.get_iValue(); }
			if (l2.get_bOK()) { i2 = l2.get_iValue(); }
			
			if (i1<i2) {
				return -1;
			} else if (i1==i2) {
				return 0;
			} else {
				return 1;
			}
		}
		
	}
	
	
}
