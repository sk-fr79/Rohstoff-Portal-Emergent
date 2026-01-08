package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class WF_HEAD_LIST_BT_VIEW extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6498472592961207584L;

	public WF_HEAD_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"ANZEIGE_WF_HEAD"));

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige von von Laufzetteln"), onavigationList, omaskContainer, oownButton,null);
		}
	}
	
}
