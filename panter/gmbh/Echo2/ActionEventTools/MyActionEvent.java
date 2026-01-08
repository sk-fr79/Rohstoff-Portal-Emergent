package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public class MyActionEvent extends ActionEvent {

	

	/*
	 * dieser Vector wird im actioAgent abgefragt und ebenfalls in die popup-orgie der execution miteingefuehrt
	 */
	private V_Single_BasicModuleContainer_PopUp_BeforeExecute  		vE2_ContainerBeforeExecute_Dynamic = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
	
	
	
	
	/*
	 * messageVector, kann in den event-handlern benutzt werden
	 */
	private Vector_SQL_STACK_DAEMON 	vSQL_STACK_DAEMON = new Vector_SQL_STACK_DAEMON();
	
	public MyActionEvent(ActionEvent oEvent) 
	{
		super(oEvent.getSource(), oEvent.getActionCommand());
	}

	
	public Vector_SQL_STACK_DAEMON get_vSQL_STACK_DAEMON() 
	{
		return vSQL_STACK_DAEMON;
	}

	public void make_ID_Validation_ADD_TO_Global_MV(Vector<String> vIDsToHandle)
	{
		try 
		{
			bibMSG.add_MESSAGE(((E2_IF_Handles_ActionAgents)this.getSource()).valid_IDValidation(vIDsToHandle));
		} 
		catch (myException e) 
		{
			bibMSG.add_MESSAGE(e.get_ErrorMessage(), false);
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(ex.getLocalizedMessage(),false), false);
			ex.printStackTrace();
		}
	}
	
	public MyE2_MessageVector make_ID_Validation(Vector<String> vIDsToHandle)
	{
		MyE2_MessageVector ooMV = new MyE2_MessageVector();
		
		try 
		{
			ooMV.add_MESSAGE(((E2_IF_Handles_ActionAgents)this.getSource()).valid_IDValidation(vIDsToHandle));
		} 
		catch (myException e) 
		{
			ooMV.add_MESSAGE(e.get_ErrorMessage(), false);
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ooMV.add_MESSAGE(new MyE2_Alarm_Message(ex.getLocalizedMessage(),false), false);
			ex.printStackTrace();
		}
		return ooMV;
	}


	/*
	 * Container traegt vom Ausfuehrenden element uebergebene (evtl. vorhandene) Popup-container zur ausfuehrung
	 */
	public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_ContainerBeforeExecute_Dynamic()
	{
		return vE2_ContainerBeforeExecute_Dynamic;
	}



	
	
}
