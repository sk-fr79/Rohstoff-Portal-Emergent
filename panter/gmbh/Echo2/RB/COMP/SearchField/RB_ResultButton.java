package panter.gmbh.Echo2.RB.COMP.SearchField;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_ResultButton extends E2_Button implements RB_ResultButton_IF {

	private MyRECORD_IF_RECORDS result_record  = 		null;
	private RB_SearchField      calling_searchField = 	null;
	
	@Override
	public abstract String get_sort_string() throws myException;


	/**
	 * 
	 * @author martin
	 * @date 23.11.2018
	 *
	 * @param p_calling_searchField
	 * @param p_result_record
	 * @param cText
	 * @param oStyle (can be null)
	 * @param cToolTips
	 * @param oAgent
	 * @throws myException
	 */
	public RB_ResultButton(RB_SearchField p_calling_searchField, MyRECORD_IF_RECORDS p_result_record, String cText, MutableStyle oStyle, MyE2_String cToolTips, XX_ActionAgent oAgent) throws myException {
		super();
		this._t(cText)._ttt(cToolTips)._aaa(oAgent) ;//, oStyle, cToolTips, oAgent);
		if (oStyle!=null) {
			this._style(oStyle);
		}
		this.result_record = p_result_record;
		this.calling_searchField = p_calling_searchField;
	}

	/**
	 * 
	 * @author martin
	 * @date 23.11.2018
	 *
	 * @param calling_searchField
	 * @param p_result_record
	 * @param cText
	 * @param oStyle  (nullable)
	 * @throws myException
	 */
	public RB_ResultButton(RB_SearchField calling_searchField, MyRECORD_IF_RECORDS p_result_record, String cText, MutableStyle oStyle) throws myException {
		this(calling_searchField, p_result_record, cText, oStyle,null,null);
	}

	@Override
	public MyRECORD_IF_RECORDS get_result_record() {
		return result_record;
	}

	@Override
	public RB_SearchField get_calling_searchField() {
		return calling_searchField;
	}


//	@Override
//	public void set_result_record(MyRECORD_IF_RECORDS result_record) {
//		this.result_record = result_record;
//	}
//
	@Override
	public void set_calling_searchField(RB_SearchField calling_searchField) {
		this.calling_searchField = calling_searchField;
	}
	
	
	
	
}
