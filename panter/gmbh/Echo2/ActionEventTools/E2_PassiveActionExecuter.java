package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public class E2_PassiveActionExecuter
{

	public E2_PassiveActionExecuter(E2_IF_Handles_ActionAgents  oActionObjectWithIsUse_from_other_agent, MyActionEvent oActionEventFremd) throws myException
	{
		super();
		
		for (int i=0;i<oActionObjectWithIsUse_from_other_agent.get_vActionAgents().size();i++)
		{
			XX_ActionAgent oAgent =	oActionObjectWithIsUse_from_other_agent.get_vActionAgents().get(i);
			Vector<XX_ActionAgent> vRestActionAgents = new Vector<XX_ActionAgent>();
			/*
			 *  alle agenten im Vector ab der aktuellen position der ExecINFO mitgeben, falls ein popup-Container diese
			 *  Action durchfuehrt (
			 */
			for (int k=i+1;k<oActionObjectWithIsUse_from_other_agent.get_vActionAgents().size();k++)                
			{
				vRestActionAgents.add(oActionObjectWithIsUse_from_other_agent.get_vActionAgents().get(k));
			}
			oAgent.ExecuteAgentCode(new ExecINFO_OnlyCode(oActionEventFremd));
			
			/*
			  * !!!!!! WICHTIG !!! TODO !  wenn popups angezeigt werden und es gibt mehr als einen actionagenten, dann werden die folgenden
			  *                            actionAgents hier ausgefuehrt und auch in dem finalen speicher-button der popups !!!!!
			  * !!!!!! DIESER BUG MUSS NOCH KORRIGIERT WERDEN !!!!                           
			 */
			
			
			//falls ein fehler aufgetreten ist, dann bei diesem actionAgent raus und ende
			if (bibMSG.get_bHasAlarms())
			{
				break;
			}
		}

	}

}
