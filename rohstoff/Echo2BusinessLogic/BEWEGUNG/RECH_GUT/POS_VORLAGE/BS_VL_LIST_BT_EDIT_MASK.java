package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class BS_VL_LIST_BT_EDIT_MASK extends MyE2_Button
{

	private ownActionAgent oAgentEdit = null;
	
	public BS_VL_LIST_BT_EDIT_MASK(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		this.oAgentEdit = new ownActionAgent(onavigationList,omaskContainer,this);
		this.add_oActionAgent(this.oAgentEdit);
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_POSITION_VORLAGEN,"BEARBEITE_POSITION"));
	}
	
	public MyE2_Button get_oSaveButtonInMask()
	{
		return oAgentEdit.get_oButtonMaskSave();
	}

	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten einer Position"), onavigationList, omaskContainer, oownButton, null, null);
		}
	}

	
}
