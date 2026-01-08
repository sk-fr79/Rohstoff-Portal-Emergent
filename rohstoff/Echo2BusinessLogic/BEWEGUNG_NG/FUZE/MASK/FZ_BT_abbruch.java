package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.Std_action_popup_frage_yes_no;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class FZ_BT_abbruch extends MyE2_Button implements IF_components_4_decision{
	
	private Vector<XX_ActionAgent> v_storage_vector_4_action_agents 	= new Vector<>();
	private Vector<XX_ActionAgent> v_storage_vector_4_status_at_start 	= new Vector<>();
	private RB_ComponentMapCollector component_map_collector;
	
	public FZ_BT_abbruch(RB_ComponentMapCollector p_component_map_collector, MASK_STATUS mask_STATUS) throws myException {
		super(E2_ResourceIcon.get_RI("cancel.png"), E2_ResourceIcon.get_RI("cancel__.png"));
		this.component_map_collector = p_component_map_collector;
		this
		._ttt(new MyE2_String("Die Warenbewegung aus der Maske entfernen.."));
		
		if(mask_STATUS==MASK_STATUS.EDIT){
			this._aaa(new ownAction_start_yes_no(this));
		}
		
		this._aaa(new ownCloseActionAgent(p_component_map_collector));
		
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
		return this.v_storage_vector_4_action_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
			return this.v_storage_vector_4_status_at_start;
	}
	
	private class ownAction_start_yes_no extends Std_action_popup_frage_yes_no {
		public ownAction_start_yes_no(IF_components_4_decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		protected MyE2_String get_popup_titel_string() throws myException {
			return new MyE2_String("Sind Sie sicher ?");
		}

		@Override
		protected void define_buttons_and_fill_grid_in_info_popup(MyE2_Button bt_ok, MyE2_Button bt_cancel,	MyE2_Grid grid_4_info) throws myException {
			bt_ok.setText(new MyE2_String("Ja").CTrans());
			bt_cancel.setText(new MyE2_String("Nein").CTrans());
			
			int[] i_breite = {200,200};
			grid_4_info.set_Spalten(i_breite);
			grid_4_info.add(new E2_Grid4MaskSimple()
								.def_(new Alignment(Alignment.CENTER, Alignment.CENTER))
								.def_(E2_INSETS.I(2,5,2,5))
								.def_(2,1)
								.add_(new RB_lab("Abbrechen?"))
								.def_(E2_INSETS.I(2,15,2,5))
								.def_(1, 1)
								.add_(bt_ok)
								.add_(bt_cancel)
								.setSize_(i_breite)
					);
					
		}

		@Override
		protected Extent get_width_of_popup() { return new Extent(370); }

		@Override
		protected Extent get_height_of_popup() { return new Extent(180);}
		
	}
	
	private class ownCloseActionAgent extends XX_ActionAgent{

		private RB_ComponentMapCollector compMapCollector = null;
		
		public ownCloseActionAgent(RB_ComponentMapCollector component_map_collector) {
			this.compMapCollector = component_map_collector;		
			}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_ComponentMapCollector oThis = this.compMapCollector;
	
			FZ_MASK_MaskModulContainer cont = (FZ_MASK_MaskModulContainer)oThis.rb_get_belongs_to();

			if (cont.rb_hm_component_map_collector().size()==1) {
				cont.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}  else {
				RB_K keyToRemove = null;
				for (Entry<RB_K, RB_ComponentMapCollector> e: cont.rb_hm_component_map_collector().entrySet()) {
					if (e.getValue()==oThis) {
						keyToRemove=e.getKey();
						break;
					}
				}
				//cont.rb_hm_component_map_collector().remove(keyToRemove);
				cont.rb_remove(keyToRemove);
			}
			cont.rebuild_container_grid();
		}
		
	}
}
