package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public interface SearchDialog_ResultButton_IF {

	public String 					get_sort_string() throws myException;
	
	public String					get_result_row_id();
	public void 					set_result_row_id(String rowid);
	
	public SearchDialog_Base 		get_calling_searchDialog();
	
	public void 					set_calling_searchDialog(SearchDialog_Base calling_searchDialog);
	public Component    			me() throws myException;
	
	
}
