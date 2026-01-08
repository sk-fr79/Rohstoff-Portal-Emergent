package panter.gmbh.Echo2.ActionEventTools.DecisionSimple;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision.DECISION;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public abstract class DS_ActionAgent extends XX_ActionAgent {

	private DS_IF_components4decision  actionComponent = null;
	
	
	public abstract Boolean 	make_decision_when_true_then_popup() throws myException;
	
	/**
	 * falls ein popup zur entscheidung noetig ist ("Sind sie sicher ?" z.b.) dann wird er vektor mit den standard-aktionen an den container uebergeben 
	 */
	public abstract DS_PopupContainer4Decision 	generate_popupContainer(	DS_IF_components4decision 	activeComponent) throws myException;
	public abstract void 						fill_popupContainer(	DS_PopupContainer4Decision 	container) throws myException;
	public abstract MyE2_MessageVector          is_something_to_do_before_ok_is_possible() throws myException;
	
	
	public DS_ActionAgent(DS_IF_components4decision p_actionComponent) {
		super();
		this.actionComponent = p_actionComponent;
	}


		
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		//falls der executer zum ersten mal aufgerufen wird, dann wird der start-actionagents-vektor gesichert, ansonsten geladen
		if (this.actionComponent.get_storage_vector_4_all_agents().size()==0) {
			
			//der erste aufruf MUSS vom ersten decision-agent erfolgen
			if (this.actionComponent.get_vActionAgents().get(0)!=this) {
				throw new myException(this,"Decision-Agents MUST be the first ActionAgent !!");
			}
			
			this.actionComponent.get_storage_vector_4_all_agents().addAll(this.actionComponent.get_vActionAgents());
			this.separate_normal_and_special_agents(	this.actionComponent.get_vActionAgents(), 
														this.actionComponent.get_storage_vector_4_standard_agents(), 
														this.actionComponent.get_storage_vector_4_decision_agents());
			//jetzt die actionschleife auf die entscheidungen reduzieren
			this.actionComponent.get_vActionAgents().removeAllElements();
			this.actionComponent.get_vActionAgents().addAll(this.actionComponent.get_storage_vector_4_decision_agents());
		
		}
		
		
		DS_PopupContainer4Decision  pop = this.generate_popupContainer(this.actionComponent);
		
		//2016-04-07: hier wird dem popup-container das DS_ActionAgent-Objekt uebergeben
		pop.set_myDecisionActionAgent(this);
		
		if (this.make_decision_when_true_then_popup()) {
			this.fill_popupContainer(pop);
			pop.CREATE_AND_SHOW_POPUPWINDOW(	new Extent(pop.get_width_in_pixel()), 
												new Extent(pop.get_height_in_pixel()), 
												pop.get_titleText4PopUp());
			pop.set_my_descision(null);
		} else  {
			pop.set_my_descision(DECISION.OK);
		}
		
		//und speichert ihn weg, naechster
		this.actionComponent.get_hm_descision_containers().put(this, pop);
		
		//hier jetzt nachsehen, ob es der letzte decicion-agent ist, dann muss geprueft werden, ob alle auf ok stehen
		if (this.actionComponent.get_storage_vector_4_decision_agents().get(this.actionComponent.get_storage_vector_4_decision_agents().size()-1)==this) {
			if (this.check_all_decisions_ok()) {
				//dann muss hier wieder alles restliche in den vector gehaengt werden
				this.actionComponent.get_vActionAgents().addAll(this.actionComponent.get_storage_vector_4_standard_agents());
				//danach laueft alles ab hier durch
				this.reset_component_status1();   //alles wieder auf anfang
			}
		}
	}
	
	
	public XX_ActionAgent  generate_close_window_action(E2_BasicModuleContainer p_container2Close,	boolean p_close_true_cancel_false) {
		return new ownActionCloseWindow(p_container2Close,	p_close_true_cancel_false);
	}
	
	//allgemeine closeWindowAction
	private class ownActionCloseWindow extends XX_ActionAgent {

		private E2_BasicModuleContainer  	container2Close = null;
		private boolean    					close_true_cancel_false = true;
		
		public ownActionCloseWindow(E2_BasicModuleContainer p_container2Close,	boolean p_close_true_cancel_false) {
			super();
			this.container2Close = p_container2Close;
			this.close_true_cancel_false = p_close_true_cancel_false;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			this.container2Close.CLOSE_AND_DESTROY_POPUPWINDOW(this.close_true_cancel_false);
		}
		
	}
	
	
	private void separate_normal_and_special_agents(	Vector<XX_ActionAgent> v_agents_all, 
														Vector<XX_ActionAgent> v_agents_normal, 
														Vector<DS_ActionAgent> v_decisions) {
		for (XX_ActionAgent agent: v_agents_all) {
			if (agent instanceof DS_ActionAgent) {
				v_decisions.add((DS_ActionAgent)agent);
			} else {
				v_agents_normal.addElement(agent);
			}
		}
	}
	

	/**
	 * prueft, ob alle popup-container die decision true haben
	 * @return
	 * @throws myException
	 */
	public boolean check_all_decisions_ok() throws myException {
		boolean b_rueck = true;
		for (DS_PopupContainer4Decision pop: this.actionComponent.get_hm_descision_containers().values()) {
			if (!pop.is_decision_ok()) {
				b_rueck = false;
			}
		}
		return b_rueck;
	}
	
	
	/**
	 * falls alle im ersten zyklus ohne popup durchgelaufen sind, dann ist hier wieder alles glatt
	 * @throws myException 
	 */
	private void reset_component_status1() throws myException {
		this.actionComponent.get_storage_vector_4_all_agents().clear();
		this.actionComponent.get_storage_vector_4_decision_agents().clear();
		this.actionComponent.get_storage_vector_4_standard_agents().clear();
		this.actionComponent.get_hm_descision_containers().clear();
	}
	
	
	/**
	 * beim letzten mit ok abeschlossenen ActionAgent_4_decisions2 wird die komponente wieder in den ausgangszustand versetzt
	 * @throws myException 
	 */
	public void reset_component_status2() throws myException {
		this.actionComponent.get_vActionAgents().removeAllElements();
		this.actionComponent.get_vActionAgents().addAll(this.actionComponent.get_storage_vector_4_all_agents());
		this.actionComponent.get_storage_vector_4_all_agents().removeAllElements();
		this.actionComponent.get_storage_vector_4_decision_agents().removeAllElements();
		this.actionComponent.get_storage_vector_4_standard_agents().removeAllElements();
		this.actionComponent.get_hm_descision_containers().clear();
	}

	public DS_IF_components4decision get_actionComponent() {
		return actionComponent;
	}

}
