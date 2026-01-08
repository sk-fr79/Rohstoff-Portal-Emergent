package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.ActionAgent_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;

public class SF_bt_save_and_start_selection extends MyE2_Button implements IF_components_4_decision{

	private SF__Filter  ownFilter = null;

	private Vector<XX_ActionAgent>  v_storage_vector_4_action_agents = new Vector<XX_ActionAgent>();   //zur zwischenspeicherung
	private Vector<XX_ActionAgent>  v_storage_vector_4_status_at_start = new Vector<XX_ActionAgent>();   //zur zwischenspeicherung

	
	public SF_bt_save_and_start_selection(SF__Filter p_ownFilter, MyE2_String label4Button) {
		super(label4Button);
		this.ownFilter = p_ownFilter;
		this.add_oActionAgent(new ownAction4decision(this));
		this.setToolTipText(new MyE2_String("Die aktuelle Einstellung für den Filter anwenden und das Fenster schließen").CTrans());
	}
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
		return this.v_storage_vector_4_action_agents;
	}
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
		return this.v_storage_vector_4_status_at_start;
	}


	/**
	 * decision warnt vor nicht geschlossenen zeilen
	 * @author martin
	 *
	 */
	private class ownAction4decision extends ActionAgent_4_decision {
		
		public ownAction4decision(IF_components_4_decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_true_4_special__false_4_normal(Vector<XX_ActionAgent> v_addon_actions_when_false) throws myException {
			SF__Filter  own_Filter = SF_bt_save_and_start_selection.this.ownFilter;
			
			int i_count_error_at_save = 0;
			
			for (int i=0;i<own_Filter.get_ands_4_popup().size();i++) {
				SF_andBlock _and = own_Filter.get_ands_4_popup().get(i);
				for (int k=0;k<_and.get_orLines().size();k++) {
					SF_orLine _or = _and.get_orLines().get(k);
					if (_or.is_forgotten_to_save()) {
						MyE2_MessageVector mv = _or.check_and_save_line();
						if (mv.get_bHasAlarms()) {
							i_count_error_at_save++;
						}
					}
				}
			}
			
			if (i_count_error_at_save>0) {
				return true;
			} else {
				return false;
			}
		}
 
		@Override
		public void generate_fill_and_show_popup(IF_components_4_decision activeComponent,	Vector<XX_ActionAgent> v_standardAgents) throws myException {
			ownBasicModuleContainer container = new ownBasicModuleContainer();
			
			container.set_bVisible_Row_For_Messages(false);
			container.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
			
			MyE2_Button bt_trotzdem_weiter = new MyE2_Button(new MyE2_String("Weiter (Fehler ignorieren)"));
			MyE2_Button bt_abbruch_erst_schliessen = new MyE2_Button(new MyE2_String("Zurück (Fehler beheben)"));
			
			E2_Grid4MaskSimple grid = new E2_Grid4MaskSimple();
			grid.def_(E2_INSETS.I(5,10,5,10)).def_(2, 1).add_(new MyE2_Label(new MyE2_String("Mindestens eine Bedingung ist fehlerhaft/unvollständig!")))
				.def_(E2_INSETS.I(5,20,5,10)).def_(1, 1).add_(bt_trotzdem_weiter).add_(bt_abbruch_erst_schliessen).setSize_(2);
			
			bt_trotzdem_weiter.add_oActionAgent(v_standardAgents, false);
			
			bt_trotzdem_weiter.add_oActionAgent(this.generate_close_window_action(container, true));
			bt_abbruch_erst_schliessen.add_oActionAgent(this.generate_close_window_action(container, false));
			
			container.add(grid, E2_INSETS.I(2,2,2,2));
			container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(150), new MyE2_String("Bitte überprüfen ..."));
		}
		
		private class ownBasicModuleContainer extends E2_BasicModuleContainer {
		}
		

	}




}
