package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;


/*
 * 2013-01-04 neuer zweiter actionAgent fuer simple aktionen, die ausserhalb der sonstigen systematik laufen muessen
 */
public class E2_InnerActionListenerForInternalActionAgents implements ActionListener 
{
	
	public E2_InnerActionListenerForInternalActionAgents() 
	{
		super();
	}


	public void actionPerformed(ActionEvent eEvent)
	{
		// hier wird ein eigene Event untergeschoben, der traeger weiterer infos sein kann
		MyActionEvent oMyActionEvent = new MyActionEvent(eEvent);
		
		//rufendes element muss 2 interfaces erfuellen
		E2_IF_Handles_ActionAgents 	oComponentWithAction = 	(E2_IF_Handles_ActionAgents)oMyActionEvent.getSource();

		try
		{
			Vector<XX_ActionAgent> vAgents = oComponentWithAction.get_vInternalActionAgents();
			for (int i=0;i<vAgents.size();i++)
			{
				vAgents.get(i).ExecuteAgentCode(new ExecINFO(oMyActionEvent,false));
			}
		}
		
		catch (myExceptionForUser ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		
	}
	
	
}
