package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.E2_MODULNAMES;
import echopointng.KeyStrokeListener;

public class FU_SONDER_LIST_BT_NEW extends MyE2_ButtonWithKey
{

	public FU_SONDER_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F8);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_LISTE_FUHREN_SONDERFAELLE,"NEUEINGABE_FU_SONDER"));

	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe Sonderfall"), onavigationList, omaskContainer, oownButton, null, null);
		}
	}
	
}
