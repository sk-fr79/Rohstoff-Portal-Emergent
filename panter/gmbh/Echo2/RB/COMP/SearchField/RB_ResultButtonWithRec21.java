package panter.gmbh.Echo2.RB.COMP.SearchField;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_ResultButtonWithRec21 extends E2_Button implements RB_ResultButton_IF {

	private Rec21    			 result_record  = 		null;
	private RB_SearchField      calling_searchField = 	null;
	
	@Override
	public abstract String get_sort_string() throws myException;

	 
	public RB_ResultButtonWithRec21(RB_SearchField p_calling_searchField, Rec21 p_result_record, String cText, MutableStyle oStyle, MyE2_String cToolTips, XX_ActionAgent oAgent) throws myException {
		super();
		this._t(cText)._style(oStyle)._ttt(cToolTips)._aaa(oAgent) ;//, oStyle, cToolTips, oAgent);
		this.result_record = p_result_record;
		this.calling_searchField = p_calling_searchField;
	}

	public RB_ResultButtonWithRec21(RB_SearchField calling_searchField, Rec21 p_result_record, String cText, MutableStyle oStyle) throws myException {
		this(calling_searchField, p_result_record, cText, oStyle,null,null);
	}

	@Override
	public MyRECORD_IF_RECORDS get_result_record() {
		return this.result_record;
	}

	@Override
	public RB_SearchField get_calling_searchField() {
		return calling_searchField;
	}


//	@Override
//	public void set_result_record(MyRECORD_IF_RECORDS result_record) {
//		this.result_record = result_record;
//	}

	@Override
	public void set_calling_searchField(RB_SearchField calling_searchField) {
		this.calling_searchField = calling_searchField;
	}
	
	
	
	
}
