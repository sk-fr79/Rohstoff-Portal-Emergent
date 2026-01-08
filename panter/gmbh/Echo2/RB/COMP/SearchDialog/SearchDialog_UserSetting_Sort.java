package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.util.ArrayList;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ListOfSaveables;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.indep.exceptions.myException;

public class SearchDialog_UserSetting_Sort extends E2_UserSetting_ListOfSaveables {
	
	private SearchDialog_Base  searchdialog_this_belongs_to = null;

	public SearchDialog_UserSetting_Sort(SearchDialog_Base  p_searchdialog_this_belongs_to) throws myException {
		super();
		this.searchdialog_this_belongs_to = p_searchdialog_this_belongs_to;
		
		ArrayList<IF_Saveable>  als = new ArrayList<IF_Saveable>();
		als.add(this.searchdialog_this_belongs_to.get_rb_vecResultButtons());
		
		if (this.searchdialog_this_belongs_to.get_key_4_save_sorting()==null) {
			throw new myException(this, "Error: no savekey is present !!");
		}
		
		this._init(als, this.searchdialog_this_belongs_to.get_key_4_save_sorting());
	}

	
	
	
	
	
}
