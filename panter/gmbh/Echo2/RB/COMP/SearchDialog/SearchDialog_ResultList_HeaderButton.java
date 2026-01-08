package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base.SORTSTATUS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.exceptions.myException;

public class SearchDialog_ResultList_HeaderButton extends MyE2_Grid {
	
	private SearchDialog_Base  calling_searchDialog = 		null;
	private int    				i_colum_of_this_button = 	-1;
	private MyE2_String         c_text = 					null;
	private MyE2_Button         button_to_sort = 			null;
	
	private SORTSTATUS   		sortstatus_actual =         SORTSTATUS.NEUTRAL;
	
	/**
	 * 
	 * @param p_calling_searchDialog
	 * @param p_i_colum_of_this_button
	 * @param p_text
	 * @param style_button
	 */
	public SearchDialog_ResultList_HeaderButton(SearchDialog_Base p_calling_searchDialog, int p_i_colum_of_this_button, MyE2_String p_text, E2_MutableStyle  style_button) {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.calling_searchDialog = p_calling_searchDialog;
		this.i_colum_of_this_button = p_i_colum_of_this_button;
		this.c_text = p_text;
		
		this.button_to_sort = new MyE2_Button(this.c_text,
											 style_button==null?MyE2_Button.StyleTextButton_LOOK_like_LABEL():style_button);
		
		this.button_to_sort.add_oActionAgent(new ownActionSort());
		this.button_to_sort.add_oActionAgent(new ownActionSaveSortstatus());
		
		this.button_to_sort.setFocusTraversalParticipant(false);
		
		this.setSize(2);
		this._init();
	}
	
	private void _init() {
		this.removeAll();
		this.add(this.button_to_sort,E2_INSETS.I(0,0,0,0));
		this.add(this.sortstatus_actual.icon(),E2_INSETS.I(2,0,0,0));
	}
	
	private class ownActionSort extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SearchDialog_ResultList_HeaderButton oThis = SearchDialog_ResultList_HeaderButton.this;
			oThis.calling_searchDialog.get_rb_vecResultButtons().set_actual_sort_col(oThis.i_colum_of_this_button);
			oThis.calling_searchDialog.get_rb_vecResultButtons().set_actual_sort_status(oThis.sortstatus_actual.get_next());
			oThis.calling_searchDialog.get_rb_vecResultButtons().sort();
			oThis.calling_searchDialog.fillResultWindow();
//			oThis.calling_searchDialog.fill_grid_4_popup(	oThis.calling_searchDialog.get_grid_container_4_searchResult(), 
//															oThis.calling_searchDialog.get_rb_ResultButtonArray());
		}
	}


	public void set_sortstatus_actual(SORTSTATUS p_sortstatus_actual) {
		this.sortstatus_actual = p_sortstatus_actual;
		this._init();
	}
	
	
	public class ownActionSaveSortstatus extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SearchDialog_ResultList_HeaderButton oThis = SearchDialog_ResultList_HeaderButton.this;
			if (oThis.calling_searchDialog!=null && oThis.calling_searchDialog.get_key_4_save_sorting()!=null) {
				new SearchDialog_UserSetting_Sort(oThis.calling_searchDialog).SAVE();
				
			}
		}
	}
	
	
	
}
