package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

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

public class BST_K_LIST_BT_NEW_VORGANG extends MyE2_ButtonWithKey
{

	public BST_K_LIST_BT_NEW_VORGANG(E2_NavigationList onavigationList, BST_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"), KeyStrokeListener.VK_F8);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_TPA_LIST,"NEUEINGABE_TPA"));
		this.setToolTipText(new MyE2_String("Neuen Transportauftrag eingeben").CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe eines Transportauftrag"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartingNew(new MyE2_String("Neueingabe eines Transportauftrages ..."));
			this.set_cMessageNewRowIsSaved(new MyE2_String("Neuer Transportautrag wurde gespeichert ..."));
		}
	}
	
}
