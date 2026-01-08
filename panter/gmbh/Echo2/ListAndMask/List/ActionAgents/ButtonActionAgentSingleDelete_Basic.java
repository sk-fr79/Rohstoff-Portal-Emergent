package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


public class ButtonActionAgentSingleDelete_Basic extends ButtonActionAgentSingleDELETE
{

	
	public ButtonActionAgentSingleDelete_Basic(	MyE2_String 		actionName,
												E2_NavigationList 	onavigationList)
	{
		super(actionName,onavigationList);
	}

	public MyE2_MessageVector CheckIdToDelete(String cID_UnformatedToDelete) {	return null;}
	public void Execute_After_DELETE(String cID_toDeleteUnformated) throws myException {}
	public void Execute_Before_DELETE(String cID_toDeleteUnformated) throws myException {}
	public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) {return  new Vector<String>();}
	public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated) {return  new Vector<String>();}
}
