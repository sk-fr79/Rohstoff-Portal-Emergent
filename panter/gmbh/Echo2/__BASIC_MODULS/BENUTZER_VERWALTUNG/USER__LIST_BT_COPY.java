package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW_FROM_COPY;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;

public class USER__LIST_BT_COPY extends MyE2_Button
{

	public USER__LIST_BT_COPY(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());  //2014-02-17: neu: Benutzung fuer alle USER moeglich, aber nur fuer den eigenen Account

	}
	
	private class ownActionAgent extends ButtonActionAgentNEW_FROM_COPY
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null, null);
		}
	}
	
}
