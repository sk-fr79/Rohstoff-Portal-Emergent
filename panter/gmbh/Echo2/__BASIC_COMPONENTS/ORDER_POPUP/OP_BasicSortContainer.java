package panter.gmbh.Echo2.__BASIC_COMPONENTS.ORDER_POPUP;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class OP_BasicSortContainer  {
	
	
	private Vector<OP_IF_SortObject>   	v_sort_objects = new Vector<OP_IF_SortObject>();
	private MyE2_Grid  					grid4Sorting = new MyE2_Grid(this.get_columns_4_sortlist(), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	private E2_BasicModuleContainer 	container = null; 
	
	
	public void add_(OP_IF_SortObject object) {
		this.v_sort_objects.add(object);
	}
	
	
	public void start_sort_dialog() throws myException {
		 this.container = this.generateContainer_4_popup();
		
		for (OP_IF_SortObject sort_ob: this.v_sort_objects) {
			sort_ob.connect_with_sort_container(this);
		}
		
		
		container.add(this.grid4Sorting,E2_INSETS.I(5,5,5,5));
		container.CREATE_AND_SHOW_POPUPWINDOW(this.get_width4popup(), this.get_height4popup(), this.get_titel4popup());
		
		this.fill_grid_4_sorting();
		
	}
	
	
	public void fill_grid_4_sorting() throws myException {
		this.grid4Sorting.removeAll();
		for (OP_IF_SortObject sort_ob: this.v_sort_objects) {
			this.fill_sort_list_line(grid4Sorting, sort_ob.generate_SortUpButton(), sort_ob.generate_SortDownButton(), sort_ob.get_SortRepresenationInList());
		}
		
		MyE2_Button  bt_save = this.get_button4save();
		bt_save.add_oActionAgent(new ownActionSave());
		
		Vector<XX_ActionAgent>  vActionsAfterSave = this.get_actionsAfterSave();
		if (vActionsAfterSave!=null) {
			for (XX_ActionAgent agent: vActionsAfterSave) {
				bt_save.add_oActionAgent(agent);
			}
		}
		
		this.add_save_button_to_grid(grid4Sorting, bt_save);

	}
	
	
	public void unmark_all() throws myException {
		for (OP_IF_SortObject so: this.v_sort_objects) {
			so.unmark_object();
		}
	}
	
	
	/*
	 * einen container fuer die sortieroperationen erzeugen
	 */
	public abstract E2_BasicModuleContainer generateContainer_4_popup() throws myException;
	
	
	/*
	 * 3  
	 */
	public abstract int[] get_columns_4_sortlist();
	
	
	public abstract void  fill_sort_list_line(MyE2_Grid grid4Sorting, MyE2_Button  buttonUp, MyE2_Button  buttonDown, Component sortComponent) throws myException;
	public abstract void  add_save_button_to_grid(MyE2_Grid grid4Sorting, MyE2_Button bt_save) throws myException;
	
	
	public abstract MyE2_String 				get_titel4popup() throws myException;
	public abstract Extent    					get_width4popup() throws myException;
	public abstract Extent  					get_height4popup() throws myException;
	public abstract MyE2_Button  				get_button4save() throws myException;

	public abstract Vector<XX_ActionAgent>   	get_actionsAfterSave() throws myException;
	
	
	private class ownActionSave extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			for (OP_IF_SortObject sort_ob: OP_BasicSortContainer.this.v_sort_objects) {
				MyE2_MessageVector mv = sort_ob.store_Sort_position();
				if (mv != null && mv.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(mv);
					break;
				}
			}
			
			if (bibMSG.get_bIsOK()) {
				OP_BasicSortContainer.this.container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}


	public Vector<OP_IF_SortObject> get_v_sort_objects() {
		return v_sort_objects;
	}


	public MyE2_Grid get_grid4Sorting() {
		return grid4Sorting;
	}
	
	
}
