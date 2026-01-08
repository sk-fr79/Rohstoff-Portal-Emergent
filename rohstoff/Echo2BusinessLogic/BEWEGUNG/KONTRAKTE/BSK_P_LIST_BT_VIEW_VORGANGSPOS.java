package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import echopointng.KeyStrokeListener;

public class BSK_P_LIST_BT_VIEW_VORGANGSPOS extends MyE2_ButtonWithKey
{

	public BSK_P_LIST_BT_VIEW_VORGANGSPOS(E2_NavigationList onavigationList,BSK_P_MASK__ModulContainer oPositionModulContainerMASK, BSK_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , true,KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_F4);
		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"ANZEIGE_POSITION_IN_MASKE"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Positionen").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSK_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige einer Position"), onavigationList, omaskContainer, oownButton, null);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige einer Position ..."));
		}
	}
	
}
