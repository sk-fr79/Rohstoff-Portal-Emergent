package panter.gmbh.Echo2.components.LeftRightSelect2;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_Refreshable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class LR_CB2 extends MyE2_CheckBox {
	
	private boolean  			is_right = false; 
	private IF_Refreshable      grid_left = null;
	private IF_Refreshable      grid_right = null;

	private LR_Container2     	lr_container = null;

	private LR_ObjectExtender   place_4_everything = null;
	
	public  LR_CB2(		LR_Container2     	p_container) {
		super();
		this.lr_container = p_container;
		this.grid_left = this.lr_container.get_grid4selection_left();
		this.grid_right = this.lr_container.get_grid4selection_right();
		this.add_oActionAgent(new ownActionAgent());
	}
	

	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LR_CB2 oThis = LR_CB2.this;
			
			boolean left_to_refresh = false;
			boolean right_to_refresh = false;
			
			if (oThis.is_right) {
				oThis.lr_container.get_v_cb_right().remove(oThis);
				
				//die entsprechende cb links finden und unchecked machen
				for (LR_CB2 cb:  oThis.lr_container.get_v_cb_left()) {
					if (cb.get_place_4_everything()==oThis.get_place_4_everything()) {
						cb.setSelected(false);
					}
				}
				right_to_refresh=true;
			} else {
				// klick auf linke checkbox
				if (oThis.isSelected()) {
					//rechts kopie dazu
					LR_CB2 neu = new LR_CB2(oThis.lr_container);
					neu.set_place_4_everything(oThis.get_place_4_everything());
					neu.set_right_side(true);
					neu.setSelected(true);
					oThis.lr_container.get_v_cb_right().add(neu);
				} else {
					//die entsprechende cb rechts finden und entfernen
					LR_CB2 found = null;
					for (LR_CB2 cb:  oThis.lr_container.get_v_cb_right()) {
						if (cb.get_place_4_everything()==oThis.get_place_4_everything()) {
							found=cb;
							break;
						}
					}
					if (found!=null) {
						oThis.lr_container.get_v_cb_right().remove(found);
					}
				}
				right_to_refresh=true;
			} 
			
			if (left_to_refresh) {oThis.grid_left.refresh();}
			if (right_to_refresh) {oThis.grid_right.refresh();}
		}
	}


	
	public boolean button_is_right_side() {
		return is_right;
	}


	
	public void set_right_side(boolean is_right) {
		this.is_right = is_right;
	}

	
	public LR_ObjectExtender get_place_4_everything() {
		return place_4_everything;
	}

	
	public void set_place_4_everything(LR_ObjectExtender place_4_everything) throws myException {
		this.place_4_everything = place_4_everything;
		this.__setText(place_4_everything.get_text4_checkBox());
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
		this.place_4_everything.set_enableExtenderComponents(enabled);
	}
	
}
