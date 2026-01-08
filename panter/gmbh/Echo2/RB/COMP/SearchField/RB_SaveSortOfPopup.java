package panter.gmbh.Echo2.RB.COMP.SearchField;

import java.util.ArrayList;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.indep.exceptions.myException;

public class RB_SaveSortOfPopup extends E2_UserSetting_ListOfSaveables {
	
	private RB_SearchField  searchfield_this_belongs_to = null;

	public RB_SaveSortOfPopup(RB_SearchField  p_searchfield_this_belongs_to) throws myException {
		super();
		this.searchfield_this_belongs_to = p_searchfield_this_belongs_to;
		
		ArrayList<IF_Saveable>  als = new ArrayList<IF_Saveable>();
		als.add(this.searchfield_this_belongs_to.get_rb_ResultButtonArray());
		
		if (this.searchfield_this_belongs_to.get_key_4_save_sorting()==null) {
			throw new myException(this, "Error: no savekey is present !!");
		}
		
		this._init(als, this.searchfield_this_belongs_to.get_key_4_save_sorting());
	}

	
	
	
	
	
}
