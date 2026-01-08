package panter.gmbh.Echo2.components.LeftRightSelect;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_Refreshable;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class LR_CB<T> extends MyE2_CheckBox {
	
	private boolean  			is_right = false; 
	private IF_Refreshable      grid_left = null;
	private IF_Refreshable      grid_right = null;
	
	private LR_Container<T>     lr_container = null;
	
	//jede checkbox bekommt ein object aus einer liste zugeteilt
	private T 					my_object = null;
	
	private String   			save_key = null;
	
	private String   			cb_text = null;		
	
	public LR_CB(	T   				p_object,
					String              p_text,
					LR_Container<T>     p_container,
					String    			p_save_key,
					boolean 			isRight) {
		super(p_text);
		this.my_object = p_object;
		this.cb_text = p_text;
		this.lr_container = p_container;
		this.is_right = isRight;
		this.grid_left = this.lr_container.get_grid4selection_left();
		this.grid_right = this.lr_container.get_grid4selection_right();
		this.save_key = p_save_key;
		
		if (this.is_right) {
			this.setSelected(true);
		}
		
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LR_CB<T> oThis = LR_CB.this;
			
			boolean left_to_refresh = false;
			boolean right_to_refresh = false;
			
			if (oThis.is_right) {
				oThis.lr_container.get_v_cb_right().remove(oThis);
				
				//die entsprechende cb links finden und unchecked machen
				for (LR_CB<T> cb:  oThis.lr_container.get_v_cb_left()) {
					if (cb.my_object==oThis.my_object) {
						cb.setSelected(false);
					}
				}
				right_to_refresh=true;
			} 
			
			// klick auf linke checkbox
			if (! oThis.is_right) {
				if (oThis.isSelected()) {
					//rechts kopie dazu
					LR_CB<T> neu = oThis.get_copy(true);
					neu.set_right_side(true);
					oThis.lr_container.get_v_cb_right().add(neu);
				} else {
					//die entsprechende cb rechts finden und entfernen
					LR_CB<T> found = null;
					for (LR_CB<T> cb:  oThis.lr_container.get_v_cb_right()) {
						if (cb.my_object==oThis.my_object) {
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


	
	public String get_save_key() {
		return this.save_key;
	}
	

	public LR_CB<T> get_copy(boolean  isRight) throws myException {
		LR_CB<T> newCB = new LR_CB<T>(this.my_object, this.cb_text,  this.lr_container,this.save_key,isRight);
		return newCB;
	}


	public T get_my_object() {
		return (T)this.my_object;
	}


	
	public boolean button_is_right_side() {
		return is_right;
	}


	
	public void set_right_side(boolean is_right) {
		this.is_right = is_right;
	}
	
	
	
	
}
