package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;

public class BSA_P_LIST_BT_VIEW_VORGANGSPOS extends MyE2_Button
{

	public BSA_P_LIST_BT_VIEW_VORGANGSPOS(E2_NavigationList onavigationList,BSA_P_MASK__ModulContainer oPositionModulContainerMASK, BSA_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , true);
		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"ANZEIGE_POSITION_IN_MASKE"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Positionen").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSA_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige einer Position"), onavigationList, omaskContainer, oownButton, null);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige einer Position ..."));
		}
	}
	
}
