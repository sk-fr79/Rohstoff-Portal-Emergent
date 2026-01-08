package panter.gmbh.Echo2.ActionEventTools.Decision;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;

public abstract class ActionAgent_4_decision extends XX_ActionAgent {

	private IF_components_4_decision  actionComponent = null;
	
	
	public abstract Boolean 	make_decision_true_4_special__false_4_normal(Vector<XX_ActionAgent> v_addon_actions_when_false) throws myException;
	
	/**
	 * falls ein popup zur entscheidung noetig ist (sind sie sicher z.b.) dann wird er vektor mit den standard-aktionen an den container uebergeben 
	 */
	public abstract void 	   	generate_fill_and_show_popup(	IF_components_4_decision 	activeComponent, 
																Vector<XX_ActionAgent> 		v_standardAgents
																) throws myException;
	
	
	public ActionAgent_4_decision(IF_components_4_decision p_actionComponent) {
		super();
		this.actionComponent = p_actionComponent;
	}


		
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		//falls der executer zum ersten mal aufgerufen wird, dann wird der start-actionagents-vektor gesichert, ansonsten geladen
		if (this.actionComponent.get_storage_vector_4_status_at_start().size()==0) {
			this.actionComponent.get_storage_vector_4_status_at_start().addAll(this.actionComponent.get_vActionAgents());
		} else {
			//umgekehrt, sichert ab, das jeder wiederholte aufruf des buttons unter den gleichen voraussetzungen startet
			this.actionComponent.get_vActionAgents().clear();
			this.actionComponent.get_vActionAgents().addAll(this.actionComponent.get_storage_vector_4_status_at_start());
		}
		
		//analyse und sicherung der basis-actionagents ohne den ActionAgent4Decision
		Vector<XX_ActionAgent>  v_standardAgents = new Vector<XX_ActionAgent>();
		
		//jetzt sucht sich der Agent in der liste selbst und stellt fest, ob er an erster stelle steht, weiterhin werden die "normalen Agents" in sicherheit gebracht
		for (int i=0;i<this.actionComponent.get_vActionAgents().size();i++) {
			if (this.actionComponent.get_vActionAgents().get(i)==this) {
				if (i!=0) {
					throw new myException("Messagevector-Error! ActionAgent_4_decision must be the first member!");
				}
			} else {
				// standard-agents werden gesammelt
				v_standardAgents.add(this.actionComponent.get_vActionAgents().get(i));
			}
		}
		//jetzt parken der "normalen" actionagenten im speicher-vektor
		this.actionComponent.get_storage_vector_4_action_agents().clear();
		for (XX_ActionAgent  agent: v_standardAgents) {
			if (!this.actionComponent.get_storage_vector_4_action_agents().contains(agent)) {
				this.actionComponent.get_storage_vector_4_action_agents().add(agent);
			}
		}

		DEBUG.System_println("Anzahl Standard-Agents :" +this.actionComponent.get_vActionAgents().size());
		
		//jetzt die action-Queue der komponente leer machen (bis auf den ersten, der gerade ausgefuehrt wird
		this.actionComponent.get_vActionAgents().removeAllElements();
		this.actionComponent.get_vActionAgents().add(this);
		//jetzt besteht der Vector nur noch aus diesem agent
		
		Vector<XX_ActionAgent>  v_addon_actions_when_false = new Vector<XX_ActionAgent>();
		
		Boolean b_popup_needed = this.make_decision_true_4_special__false_4_normal(v_addon_actions_when_false);    //im check-popup koennen noch weitere aktionen folgen
		
		if (b_popup_needed != null && b_popup_needed.booleanValue()==true) {
			
			this.generate_fill_and_show_popup(this.actionComponent,	this.actionComponent.get_storage_vector_4_action_agents());
		} else {
			//im laufenden ablauf die schleife resetten und  erweitern ....	
			//hier werden die vorher entfernten wieder hinzugefuegt
			this.actionComponent.get_vActionAgents().addAll(this.actionComponent.get_storage_vector_4_action_agents());
			this.actionComponent.get_vActionAgents().addAll(v_addon_actions_when_false);
			
			//DEBUG.System_println("Anzahl benutze Agents :" +this.actionComponent.get_vActionAgents());

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
	
	

}
