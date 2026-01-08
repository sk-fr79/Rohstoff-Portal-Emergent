package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.ORDER_POPUP.OP_BasicSortContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;

public class AW2_SORT_Container extends OP_BasicSortContainer {

	private RECORD_REPORT_REITER  					recRReiter = null;
	private AW2__Tab_Reiter 	callingTabGrid =null;

	
	public AW2_SORT_Container(RECORD_REPORT_REITER p_recRReiter, AW2__Tab_Reiter p_callingTabGrid ) {
		super();
		this.recRReiter = p_recRReiter;
		
		this.callingTabGrid = p_callingTabGrid;
	}

	@Override
	public E2_BasicModuleContainer generateContainer_4_popup() 	throws myException {
		return new ownBasicContainer();
	}

	@Override
	public int[] get_columns_4_sortlist() {
		int[] i_rueck = {200,20,20};
		return i_rueck;
	}

	@Override
	public void fill_sort_list_line(MyE2_Grid grid4Sorting, MyE2_Button buttonUp, MyE2_Button buttonDown, Component sortComponent) throws myException {
		if (sortComponent.getLayoutData()!=null) {
			grid4Sorting.add_raw(sortComponent);
		} else {
			grid4Sorting.add(sortComponent,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),null,1,1));
		}
		grid4Sorting.add(buttonUp,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),null,1,1));
		grid4Sorting.add(buttonDown,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),null,1,1));
	}

	@Override
	public void add_save_button_to_grid(MyE2_Grid grid4Sorting, MyE2_Button bt_save) throws myException {
		grid4Sorting.add(bt_save,E2_INSETS.I(2,2,2,2));
	}

	@Override
	public MyE2_String get_titel4popup() throws myException {
		return new MyE2_String("Reihenfolge festlegen, Reportgruppe: ",true,this.recRReiter.get_REITERNAME_cF_NN(""),false);
	}

	@Override
	public Extent get_width4popup() {
		return new Extent(500);
	}

	@Override
	public Extent get_height4popup() {
		return new Extent(500);
	}

	@Override
	public MyE2_Button get_button4save() {
		return new MyE2_Button(new MyE2_String("Speichern"));
	}

	@Override
	public Vector<XX_ActionAgent> get_actionsAfterSave() {
		Vector<XX_ActionAgent> v_addon_actions = new Vector<XX_ActionAgent>();
		v_addon_actions.add(new ownActionRebuiltTabGridAfterSort());
		return v_addon_actions;
	}


	private class ownActionRebuiltTabGridAfterSort extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AW2_SORT_Container.this.callingTabGrid._rebuild_ButtonListe();
			AW2_SORT_Container.this.callingTabGrid._refresh_ButtonAnzeige();;
		}
	}
	
	
	/*
	 * proforma zum speichern der groesse
	 */
	private class ownBasicContainer extends E2_BasicModuleContainer {
		public ownBasicContainer() {
			super();
		}
	}
	
}
