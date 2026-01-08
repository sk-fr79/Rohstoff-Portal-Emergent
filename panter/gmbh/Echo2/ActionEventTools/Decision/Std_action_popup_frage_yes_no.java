package panter.gmbh.Echo2.ActionEventTools.Decision;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;

public abstract class Std_action_popup_frage_yes_no extends ActionAgent_4_decision {
	
	public Std_action_popup_frage_yes_no(IF_components_4_decision p_actionComponent) {
		super(p_actionComponent);
	}

	@Override
	public Boolean make_decision_true_4_special__false_4_normal(Vector<XX_ActionAgent> v_addon_actions_when_false) 	throws myException {
		return true;
	}

	@Override
	public void generate_fill_and_show_popup(IF_components_4_decision activeComponent, Vector<XX_ActionAgent> v_standardAgents) throws myException {
		ownPopupContainer  cont = new ownPopupContainer();
		MyE2_Button  bt_ok = new MyE2_Button(new MyE2_String("OK"));
		MyE2_Button  bt_cancel = new MyE2_Button(new MyE2_String("Abbruch"));
		
		MyE2_Grid grid = new MyE2_Grid();
		
		cont.add(grid,MyE2_Column.LAYOUT_CENTER(E2_INSETS.I(2,2,2,2)));
		
		bt_ok.add_oActionAgent(v_standardAgents,false);
		bt_ok.add_oActionAgent(this.generate_close_window_action(cont, true));
		bt_cancel.add_oActionAgent(this.generate_close_window_action(cont, false));
		
		this.define_buttons_and_fill_grid_in_info_popup(bt_ok,bt_cancel,grid);
		
		cont.CREATE_AND_SHOW_POPUPWINDOW(this.get_width_of_popup(), this.get_height_of_popup(),this.get_popup_titel_string() );
		
	}
	
	

	private class ownPopupContainer extends E2_BasicModuleContainer {
		public ownPopupContainer() {
			super();
			this.set_bVisible_Row_For_Messages(false);
			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
		}
	}
	
	protected abstract void define_buttons_and_fill_grid_in_info_popup(MyE2_Button bt_ok,MyE2_Button bt_cancel, MyE2_Grid grid_4_info) throws myException;
	protected abstract MyE2_String 	get_popup_titel_string() throws myException;
	protected abstract Extent   	get_width_of_popup();
	protected abstract Extent   	get_height_of_popup();
	
	
	
}
