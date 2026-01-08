package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;

public class FUO_LIST_BT_VIEW extends MyE2_Button
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, FU_DAUGHTER_ORTE FUO_DaugherComponent)
	{
		super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"));

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ANZEIGE_FUHRENZUSATZORT"));

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige des zusätzlichen Fuhrenortes"), onavigationList, omaskContainer, oownButton, null);
		}
	}
	
	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

}
