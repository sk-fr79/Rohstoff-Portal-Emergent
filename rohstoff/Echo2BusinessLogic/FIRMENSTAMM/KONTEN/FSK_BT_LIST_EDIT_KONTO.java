package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.E2_MODULNAMES;
import echopointng.KeyStrokeListener;

public class FSK_BT_LIST_EDIT_KONTO extends MyE2_ButtonWithKey
{

	public FSK_BT_LIST_EDIT_KONTO(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"), KeyStrokeListener.VK_F4);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"BEARBEITE_KONTO"));

	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten eine Kontos"), onavigationList, omaskContainer, oownButton, null, null);
		}
	}
	
}
