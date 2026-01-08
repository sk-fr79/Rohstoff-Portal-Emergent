package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.E2_MODULNAMES;
import echopointng.KeyStrokeListener;

public class BST_K_LIST_BT_VIEW_VORGANG extends MyE2_ButtonWithKey
{

	public BST_K_LIST_BT_VIEW_VORGANG(E2_NavigationList onavigationList, BST_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_F4);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_TPA_LIST,"ANZEIGE_TPA"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Transportaufträge").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BST_K_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige eines Transportauftrages"), onavigationList, omaskContainer, oownButton, null);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige eines Transportauftrages ..."));
		}
	}
	
}
