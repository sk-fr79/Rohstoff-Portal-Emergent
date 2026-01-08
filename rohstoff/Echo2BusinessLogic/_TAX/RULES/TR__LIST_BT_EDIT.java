package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;


public class TR__LIST_BT_EDIT extends MyE2_Button
{

	public TR__LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		//2018-07-12: neue validierung: this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_TR_"));
		this.add_GlobalValidator(ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT.getValidatorWithoutSupervisorPersilschein());
		
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			this.get_oButtonMaskSave().set_MessageManipulator(new TR__ManipuliereMeldungWegenDubletten());
		}
	}
	

}
