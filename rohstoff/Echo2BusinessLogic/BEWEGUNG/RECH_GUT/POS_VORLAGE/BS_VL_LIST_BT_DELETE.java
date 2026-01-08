package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class BS_VL_LIST_BT_DELETE extends MyE2_Button
{

	public BS_VL_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , true);
		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_POSITION_VORLAGEN,"LOESCHE_POSITION"));
	}
	
	
	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Lösche Positionsschablen"), onavigationList);
		}
		
		public MyE2_MessageVector CheckIdToDelete(Vector<String> unformatedToDelete) throws myException
		{
			return null;
		}
		public void Execute_After_DELETE(Vector<String> ds_toDeleteUnformated)	throws myException
		{
		}
		public void Execute_Before_DELETE(Vector<String> ds_toDeleteUnformated)	throws myException
		{
		}
		public Vector<String> get_vSQL_After_DELETE(String deleteUnformated) throws myException
		{
			return null;
		}
		public Vector<String> get_vSQL_Before_DELETE(String deleteUnformated)	throws myException
		{
			return null;
		}
	}

}
