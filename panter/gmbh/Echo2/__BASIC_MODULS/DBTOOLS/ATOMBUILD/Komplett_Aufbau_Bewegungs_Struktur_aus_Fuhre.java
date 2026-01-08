package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class Komplett_Aufbau_Bewegungs_Struktur_aus_Fuhre extends E2_Grid {

	Basic_PluginColumn _container = null;
	
	/**
	 * grid, das kompeltt in den korre-tab-reiter eingeblendet wird
	 */
	public Komplett_Aufbau_Bewegungs_Struktur_aus_Fuhre(Basic_PluginColumn oContainer) {
		super();
		
		_container = oContainer;
		
		this._setSize(500,400,20)
		 	._a(new RB_lab()._tr("Neuaufbau der Atomstrukturen aus dem Fuhrenarchiv"), new RB_gld()._ins(2))
		 	._a(new bt_loesche_alte_atome(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_loesche_alte_atome(), new RB_gld()._ins(2))

		 	._a(new RB_lab()._tr(""), new RB_gld()._ins(2))
		 	._a(new bt_loesche_setzkasten(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_loesche_setzkasten(), new RB_gld()._ins(2))
		 	
		 	
		 	._a(new RB_lab()._tr(""), new RB_gld()._ins(2))
		 	._a(new bt_baue_atom_struktur_auf(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_baue_atom_struktur_auf(), new RB_gld()._ins(2))
		 	
		 	._a(new RB_lab()._tr(""), new RB_gld()._ins(2))
		 	._a(new bt_ermittle_kosten(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_ermittle_kosten(), new RB_gld()._ins(2))
		 	
		 	._a(new RB_lab()._tr(""), new RB_gld()._ins(2))
		 	._a(new bt_korrigiere_fehlbuchungen(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_korrigiere_fehlbuchungen(), new RB_gld()._ins(2))

		 	._a(new RB_lab()._tr(""), new RB_gld()._ins(2))
		 	._a(new bt_aufbau_setzkasten(_container), new RB_gld()._ins(2))
		 	._a(new BT_info_aufbau_setzkasten(), new RB_gld()._ins(2))
;
		
	}
	
	
	private class bt_loesche_setzkasten extends ownStartButton {
		
		
		/**
		 * @author manfred
		 * @date 05.10.2016
		 *
		 */
		public bt_loesche_setzkasten(Basic_PluginColumn container) {
			super(container);
			this._tr("Setzkastentabelle löschen")._s_BorderText()
			._aaa(new AA_loesche_setzkasten(container));
		}
	}
	
	
	
	private class bt_loesche_alte_atome extends ownStartButton {

		/**
		 * 
		 */
		public bt_loesche_alte_atome(Basic_PluginColumn container) {
			super(container);
			this._tr("Bewegungsätze aus dem Fuhrenarchiv löschen")._s_BorderText()
				._aaa(new AA_loesche_alte_atome(container));
			
		}
		
		
		
	}
	
	private class bt_baue_atom_struktur_auf extends ownStartButton {

		/**
		 * 
		 */
		public bt_baue_atom_struktur_auf(Basic_PluginColumn container) {
			super(container);
			this._tr("Atom-basierte Daten aufbauen")._s_BorderText()
			._aaa(new AA_baue_atom_struktur_auf(container));
			;
		}
		
		
	}
	
	
	private class bt_ermittle_kosten extends ownStartButton {

		/**
		 * 
		 */
		public bt_ermittle_kosten(Basic_PluginColumn container) {
			super(container);
			this._tr("Kostensätze in den Atomen ermitteln")._s_BorderText()
				._aaa(new AA_ermittle_kosten(container));
			
		}
		
	}

	
	
	private class bt_korrigiere_fehlbuchungen extends ownStartButton {

		/**
		 * 
		 */
		public bt_korrigiere_fehlbuchungen(Basic_PluginColumn container) {
			super(container);
			this._tr("Korrekturen alter Falsch-Abzüge durchführen")._s_BorderText()
			._aaa(new AA_korrigiere_fehlbuchungen(container));
			;
		}
		
	}


	private class bt_aufbau_setzkasten extends ownStartButton {

		/**
		 * 
		 */
		public bt_aufbau_setzkasten(Basic_PluginColumn container) {
			super(container);
			this._tr("Setzkasten aufbauen (normal und kostenbehaftet)")._s_BorderText()
			._aaa(new AA_aufbau_setzkasten(container));
			;
		}
		
	}


	
	
	
	
	
	private class ownStartButton extends E2_Button implements DS_IF_components4decision {

		
		/**
		 * 
		 */
		public ownStartButton( Basic_PluginColumn oContainer ) {
			super();
			_container = oContainer;
			this.add_oActionAgent(new actionAgentFrage(this));
			
		}
		
		private Basic_PluginColumn _container;
		
		private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
		private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
		private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
		private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
		
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
			return this.storage_vector_4_all_agents;
		}
	
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
			return this.storage_vector_4_standard_agents;
		}
	
		@Override
		public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
			return this.storage_vector_4_decision_agents;
		}
	
		@Override
		public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
			return this.hm_descision_containers;
		}

	}
	
	
	private class actionAgentFrage extends DS_ActionAgent {

		/**
		 * @param p_actionComponent
		 * @param mother_bt
		 */
		public actionAgentFrage(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)	throws myException {
			return new ownQuestionContainer(this.get_actionComponent());
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			
			E2_Grid g = new E2_Grid()	._setSize(100,100)
										._a(new RB_lab()._t("Soll ich starten (es kann lange dauern) ?")._b()._fsa(2), 	new RB_gld()._ins(5, 5, 5, 5)._center_mid()._span(2))
										._a(container.get_bt_OK(), 														new RB_gld()._ins(5, 5, 5, 5)._center_mid())
										._a(container.get_bt_NO(), 														new RB_gld()._ins(5, 5, 5, 5)._center_mid());
			container.add(g,  E2_INSETS.I(4, 4, 4, 4));
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}
		
		
		private class ownQuestionContainer extends DS_PopupContainer4Decision {

			public ownQuestionContainer(DS_IF_components4decision p_motherComponent) {
				super(p_motherComponent);
			}

			@Override
			public int get_width_in_pixel() { 	return 300; }

			@Override
			public int get_height_in_pixel() { 	return 200;	}

			@Override
			public MyE2_String get_titleText4PopUp() {	return new MyE2_String("Sind Sie sicher ?"); }
			
		}
	}
	
	
	// hier erfolgen werden die aktionen eingetragen

	

	
	
	
	
	
	
	
}
