package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_BT_FUHRE_EDIT_MASK extends MyE2_Button
{

	public FU_LIST_BT_FUHRE_EDIT_MASK(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit.png") , true);
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"BEARBEITE_FUHRE"));
		this.add_IDValidator(new FU__Validator_Fuhre_ist_gloescht_ODER_ist_storniert());
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Bearbeiten einer Transport-Position"), onavigationList, omaskContainer, oownButton, null, null);
		}
		
		public Component build_ComponentWithMaskButtons() throws myException
		{
			/*
			 * zuerst die buttons der basis-klasse einbauen
			 */
			E2_ComponentGroupHorizontal oCompGroup= (E2_ComponentGroupHorizontal)super.build_ComponentWithMaskButtons();
			
			MyE2_Button oButton_Print_und_Mail = new FU_MASK_BT_PRINT_MAIL_BELEG((FU_MASK_ModulContainer)this.get_oMaskContainer(),false);
			
			FU__MASK_Button_SaveFromMask_And_FollowAction  oButtonSaveAndMore = new FU__MASK_Button_SaveFromMask_And_FollowAction((FU_MASK_ModulContainer)this.get_oMaskContainer(), 
																																	this.get_oNavigationList());

			
			oCompGroup.add(oButton_Print_und_Mail);
			oCompGroup.add(oButtonSaveAndMore);
			
			return oCompGroup;
		}

	}
	
}
