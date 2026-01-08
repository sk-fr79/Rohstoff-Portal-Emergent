package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import echopointng.KeyStrokeListener;

public class BSA_K_LIST_BT_NEW_VORGANG extends MyE2_ButtonWithKey
{

	public BSA_K_LIST_BT_NEW_VORGANG(E2_NavigationList onavigationList, BSA_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"), KeyStrokeListener.VK_F8);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"NEUEINGABE_ANGEBOT"));
		this.setToolTipText(new MyE2_String("Neues Angebot eingeben").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe eines Angebotes"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartingNew(new MyE2_String("Neueingabe eines Angebotes ..."));
			this.set_cMessageNewRowIsSaved(new MyE2_String("Neues Angebot wurde gespeichert ..."));
		}
	}
	
}
