package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class SearchDialog_ResultButton extends E2_Button implements SearchDialog_ResultButton_IF {
	
	private String 					 result_rowid = null;
	private SearchDialog_Base      calling_searchDialog = 	null;
	private String					 sortString 		  = "";
	

	 
	public SearchDialog_ResultButton(
			SearchDialog_Base p_calling_searchDialog, 
			String rowID,
			String cText, 
			MutableStyle oStyle, 
			MyE2_String cToolTips, 
			XX_ActionAgent oAgent,
			String sort) throws myException {
		super();
		this._t(cText)._style(oStyle)._ttt(cToolTips)._aaa(oAgent) ;//, oStyle, cToolTips, oAgent);
		this.result_rowid = rowID;
		this.calling_searchDialog = p_calling_searchDialog;
		// sortstring darf nicht null werden
		this.sortString = (sort==null ? "" : sort);
	}

	
	
	public SearchDialog_ResultButton(
			SearchDialog_Base calling_searchDialog, 
			String rowID,
			String cText, 
			MutableStyle oStyle) throws myException {
		this(calling_searchDialog, rowID, cText, oStyle, null,null,null);
	}


	@Override
	public SearchDialog_Base get_calling_searchDialog() {
		return calling_searchDialog;
	}



	@Override
	public void set_calling_searchDialog(SearchDialog_Base calling_searchField) {
		this.calling_searchDialog = calling_searchField;
	}
	
	@Override
	public String get_sort_string() throws myException{
		return this.sortString;
	}

	
	@Override
	public Component me() throws myException {
		return this;
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_ResultButton_IF#get_result_row_id()
	 */
	@Override
	public String get_result_row_id() {
		return result_rowid;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_ResultButton_IF#set_result_row_id()
	 */
	@Override
	public void set_result_row_id(String rowid) {
		result_rowid = rowid;
	}
	
	
}
