package panter.gmbh.Echo2.RB.COMP.SearchField;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public interface RB_ResultButton_IF {

	public String 					get_sort_string() throws myException;
	public MyRECORD_IF_RECORDS 		get_result_record();
	public RB_SearchField 		get_calling_searchField();
//	public void 					set_result_record(MyRECORD_IF_RECORDS result_record) ;
	public void 					set_calling_searchField(RB_SearchField calling_searchField);
	public Component    			me() throws myException;
	
}
