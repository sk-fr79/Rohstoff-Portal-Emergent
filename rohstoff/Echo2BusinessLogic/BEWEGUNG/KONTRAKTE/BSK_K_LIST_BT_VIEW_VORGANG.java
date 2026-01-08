package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import echopointng.KeyStrokeListener;

public class BSK_K_LIST_BT_VIEW_VORGANG extends MyE2_ButtonWithKey
{

	public BSK_K_LIST_BT_VIEW_VORGANG(E2_NavigationList onavigationList, BSK_K_MASK__ModulContainer omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_F4);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"ANZEIGE_KONTRAKT"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Kontrakte").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSK_K_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige eines Kontraktes"), onavigationList, omaskContainer, oownButton, null);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige eines Kontraktes ..."));
		}
	}
	
}
