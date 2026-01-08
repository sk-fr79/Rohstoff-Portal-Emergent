package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW_FROM_COPY;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;

public class RUL_LIST_BT_NEW_COPY extends MyE2_Button
{

	public RUL_LIST_BT_NEW_COPY(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png") ,true);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("COPY_RUL"));

	}
	
	private class ownActionAgent extends ButtonActionAgentNEW_FROM_COPY
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Kopiere ... "), onavigationList, omaskContainer, oownButton, null,null);
		}
	}
	
}
