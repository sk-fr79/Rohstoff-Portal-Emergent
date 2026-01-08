package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField.SORTSTATUS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.exceptions.myException;

public class RB_SearchFieldListSortButton extends MyE2_Grid {
	
	private RB_SearchField  calling_searchField = 		null;
	private int    				i_colum_of_this_button = 	-1;
	private MyE2_String         c_text = 					null;
	private MyE2_Button         button_to_sort = 			null;
	
	private SORTSTATUS   		sortstatus_actual =         SORTSTATUS.NEUTRAL;
	
	/**
	 * 
	 * @param p_calling_searchField
	 * @param p_i_colum_of_this_button
	 * @param p_text
	 * @param style_button
	 */
	public RB_SearchFieldListSortButton(RB_SearchField p_calling_searchField, int p_i_colum_of_this_button, MyE2_String p_text, E2_MutableStyle  style_button) {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.calling_searchField = p_calling_searchField;
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
			RB_SearchFieldListSortButton oThis = RB_SearchFieldListSortButton.this;
			
			oThis.calling_searchField.get_rb_ResultButtonArray().set_actual_sort_col(oThis.i_colum_of_this_button);
			oThis.calling_searchField.get_rb_ResultButtonArray().set_actual_sort_status(oThis.sortstatus_actual.get_next());
			oThis.calling_searchField.get_rb_ResultButtonArray().sort();
			oThis.calling_searchField.fill_grid_4_popup(	oThis.calling_searchField.get_grid_container_4_popupWindow(), 
															oThis.calling_searchField.get_rb_ResultButtonArray(), oExecInfo);
		}
	}


	public void set_sortstatus_actual(SORTSTATUS p_sortstatus_actual) {
		this.sortstatus_actual = p_sortstatus_actual;
		this._init();
	}
	
	
	public class ownActionSaveSortstatus extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_SearchFieldListSortButton oThis = RB_SearchFieldListSortButton.this;
			if (oThis.calling_searchField!=null && oThis.calling_searchField.get_key_4_save_sorting()!=null) {
				new RB_SaveSortOfPopup(oThis.calling_searchField).SAVE();
				
			}
		}
	}


	public MyE2_Button getButton_to_sort() {
		return button_to_sort;
	}
	
	
}
