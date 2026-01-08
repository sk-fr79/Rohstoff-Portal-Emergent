package panter.gmbh.Echo2.RB.CONTROLLERS;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_bt_delete extends E2_Button implements DS_IF_components4decision {

	public abstract Vector<String>   		get_ids_to_delete() 						throws  myException;
	public abstract Vector<String> 			get_delete_sql_statements(String id_to_delete) throws myException;
	public abstract Vector<XX_ActionAgent>  get_action_after_delete() throws myException;
	
	/**
	 * @return den namen des datensatzes für meldungen z.b. Es werden 3 <....> gelöscht, einmal einzahl, einmal mehrzahl
	 */
	public abstract String get_message_text_mindestens_eine_irgendwas_markieren();     
	public abstract String get_warnung_achtung_es_wird_ein_irgendwas_geloescht();
	public abstract String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl();

	
	private Vector<String>   v_ids_to_delete = new Vector<>();
	

	
	/**
	 * @throws myException 
	 * 
	 */
	public RB_bt_delete() throws myException {
		super();
        this._image(E2_ResourceIcon.get_RI("delete_mini.png"))
        	._v(new ownValidator_must_select_even_one_line())
			._aaa(new ownDecisionAgent_Frage_sind_Sie_sicher())
			._aaa(new action_execute_delete())
			._aaaV(this.get_action_after_delete())
			;
	}

	
	
	
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

	
	
	private class ownValidator_must_select_even_one_line extends XX_ActionValidator_NG {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			RB_bt_delete.this.v_ids_to_delete.clear();
			RB_bt_delete.this.v_ids_to_delete.addAll(RB_bt_delete.this.get_ids_to_delete());
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (RB_bt_delete.this.v_ids_to_delete.size()==0) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt(RB_bt_delete.this.get_message_text_mindestens_eine_irgendwas_markieren())));
			}
			return mv;
		}
	}
	

	
	
	
	private class ownDecisionAgent_Frage_sind_Sie_sicher extends DS_ActionAgent {

		public ownDecisionAgent_Frage_sind_Sie_sicher() {
			super(RB_bt_delete.this);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)	throws myException {
			return new ownContainer();
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			String s_warning = RB_bt_delete.this.get_warnung_achtung_es_wird_ein_irgendwas_geloescht();
			if (RB_bt_delete.this.v_ids_to_delete.size()==1) {
				s_warning = bibALL.ReplaceTeilString(RB_bt_delete.this.get_warnung_achtung_es_wird_ein_irgendwas_geloescht(), "#WERT#", ""+RB_bt_delete.this.v_ids_to_delete.size());
			} else if (RB_bt_delete.this.v_ids_to_delete.size()>1) {
				s_warning = bibALL.ReplaceTeilString(RB_bt_delete.this.get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl(), "#WERT#", ""+RB_bt_delete.this.v_ids_to_delete.size());
			}
			
			E2_Grid  g_innen = new E2_Grid()._a(new RB_lab()._tr(s_warning),new RB_gld()._ins(2)._left_mid()._span(2))
											._a(container.get_bt_OK(), 		new RB_gld()._ins(2,40,2,2)._left_mid()._span(1))
											._a(container.get_bt_NO(), 		new RB_gld()._ins(2,40,2,2)._left_mid()._span(1))
											._setSize(150,150)
											;
			container.RESET_Content();
			container.add(g_innen, E2_INSETS.I(2,2,2,2));
			
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}
	}
	

	
	private class action_execute_delete extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String>  v_ids = new Vector<>();
			Vector<String>  v_sql = new Vector<>();
			
			v_ids.addAll(RB_bt_delete.this.get_ids_to_delete());
			
			
			for (String id: v_ids) {
				v_sql.addAll(RB_bt_delete.this.get_delete_sql_statements(id));
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(v_sql, true));
		}
	}
	
	
	
	private class ownContainer extends DS_PopupContainer4Decision {
		public ownContainer() {
			super(RB_bt_delete.this);
		}

		@Override
		public int get_width_in_pixel() {
			return 320;
		}

		@Override
		public int get_height_in_pixel() {
			return 160;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return S.mt("Bitte bestätigen Sie den Löschvorgang:");
		}
		
	}
	
}
