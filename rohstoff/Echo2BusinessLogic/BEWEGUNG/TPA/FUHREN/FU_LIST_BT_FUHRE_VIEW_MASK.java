package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_BT_FUHRE_VIEW_MASK extends MyE2_Button
{

	public FU_LIST_BT_FUHRE_VIEW_MASK(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , true);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"ANZEIGE_FUHREN"));

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeige von Transport-Positionen"), onavigationList, omaskContainer, oownButton, null);
		}
		
		public Component build_ComponentWithMaskButtons() throws myException
		{
			/*
			 * zuerst die buttons der basis-klasse einbauen
			 */
			E2_ComponentGroupHorizontal oCompGroup= (E2_ComponentGroupHorizontal)super.build_ComponentWithMaskButtons();
			
			MyE2_Button oButtonPrintLieferschein = new FU_MASK_BT_PRINT_MAIL_BELEG((FU_MASK_ModulContainer)this.get_oMaskContainer(),true);
			oCompGroup.add(oButtonPrintLieferschein);
			return oCompGroup;
		}

	}
	
}
