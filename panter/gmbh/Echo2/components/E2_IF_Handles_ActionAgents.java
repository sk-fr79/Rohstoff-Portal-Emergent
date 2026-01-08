package panter.gmbh.Echo2.components;

import java.util.Vector;

import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.IF_simpleValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public interface E2_IF_Handles_ActionAgents 
{
	public Vector<XX_ActionAgent> 	get_vActionAgents();
	public void 					add_oActionAgent(XX_ActionAgent actionAgent);	
	public void 					add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront);
	
	public void 					add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront);
	
	public void 					remove_oActionAgent(XX_ActionAgent actionAgent);
	public void 					remove_AllActionAgents();
	public MyE2_MessageVector 		valid_GlobalValidation() throws myException;
	public MyE2_MessageVector 		valid_IDValidation(Vector<String> vID_Unformated) throws myException;
	
	public boolean  				get_bMustSet_MILLISECONDSTAMP_TO_Session();
	public void		  			set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP);
	
	public void 					add_GlobalValidator(XX_ActionValidator oValid);
	public void 					add_IDValidator(XX_ActionValidator oValid);
	public void 					add_GlobalValidator(Vector<XX_ActionValidator> vValid);
	public void 					add_IDValidator(Vector<XX_ActionValidator> vValid);
	
	/*
	 * methoden, die benutzt werden muessen, wenn ein z.b. Button aus dem code heraus ausgeloest wird.
	 * dann kann im E2_InnerActionListenerForActionAgents der MyActionEvent nicht der SESSION uebergeben werden
	 * da sonst unvorhergesehene Dinge passieren (z.B. eine aktion auf einem noch nicht gerenderten element),
	 * d.h. der eigentliche button-event, der betaetigt wurde, wuerde verdeckt werden!!
	 */
	public void 					doActionPassiv() throws myException;
	public boolean   				get_bIsPassivAction();
	public void 	   				set_bPassivAction(boolean bPassiv);
	
	
	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException; 

	//2013-01-04: weiterer actionAgentVector fuer interne Reaktionen, die unabhaengig von anderen Programmaktionen immer ausgefuehrt werden muessen,
	//            BSP: den Inhalt eines breiten SelectFeldes, der nicht ganz sichbar ist als tooltip anzeigen. Dies hat mit dem restlichen ActionAgent-Gedoens nix zu tun
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents();   //werden nach den eigentlichen ActionAgents ausgefuehrt                 
	public void 					add_oInternalActionAgent(XX_ActionAgent actionAgent);	
	public void 					add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront);
	public void 					remove_oInternalActionAgent(XX_ActionAgent actionAgent);
	public void 					remove_AllInternalActionAgents();


	public XX_MessageManipulator   get_MessageManipulator();
	public void 					set_MessageManipulator(XX_MessageManipulator oManipulator);
	
	
	
	public default E2_IF_Handles_ActionAgents  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}
	
	public default E2_IF_Handles_ActionAgents  _aaa(XX_ActionAgent agent, boolean bInFront) {
		this.add_oActionAgent(agent, bInFront);
		return this;
	}

	
	public default E2_IF_Handles_ActionAgents  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

	
	public default E2_IF_Handles_ActionAgents  _aaaV(Vector<XX_ActionAgent> v_agents, boolean inFront) {
		//umsortieren
		VEK<XX_ActionAgent> vAgentsReverseOrder = new VEK<XX_ActionAgent>();
		if (inFront) {
			for (XX_ActionAgent agent: v_agents) {
				vAgentsReverseOrder.add(0, agent);
			}
		} else {
			vAgentsReverseOrder.addAll(v_agents);
		}
		for (XX_ActionAgent agent: vAgentsReverseOrder) {
			this.add_oActionAgent(agent,inFront);
		}
		return this;
	}

	
	//20170906: erweiterung des actionHandlings um unterbrechnungen mit benutzerinteraktion
	public E2_Break4PopupController  	getBreak4PopupController();
	
	//20180116: setter 
	public void setBreak4PopupController(E2_Break4PopupController controller);

	
	
//	public default E2_IF_Handles_ActionAgents _ttt(MyE2_String tooltips) throws myException{
//		if (this instanceof AbstractButton) {
//			((AbstractButton)this).setToolTipText(tooltips.CTrans());
//		}  else {
//			throw new myException("methode _ttt() can only be used at class of type AbstractButton");
//		}
//		return this;
//	}
	
	//20180229: moeglichkeit, simple actions zu erzeugen und zu uebergebben
	public default E2_IF_Handles_ActionAgents  _aaa(IF_agentSimple agent) {
		this.add_oActionAgent(agent.genActionAgent());
		return this;
	}
	
	//20180229: moeglichkeit, simple actions zu erzeugen und zu uebergebben
	public default E2_IF_Handles_ActionAgents  _aaaInFront(IF_agentSimple agent, boolean inFront) {
		this.add_oActionAgent(agent.genActionAgent(), inFront);
		return this;
	}

	
	//20180406: validierer als lambda zulassen
	public default E2_IF_Handles_ActionAgents _addValidator(IF_simpleValidator validator) {
		this.add_GlobalValidator(validator.getValidator());
		return this;
	}
	
	
}
