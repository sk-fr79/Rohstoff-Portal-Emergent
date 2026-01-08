/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

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

public class BT_LIST_EDIT_BAMB extends MyE2_Button
{
	private E2_NavigationList oNaviList = null;
	
	public BT_LIST_EDIT_BAMB(	E2_NavigationList 			oList,
								BAMB_MASK_ModulContainer 	oMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png"), true);
		this.oNaviList = oList;
		
		this.add_oActionAgent(new ActionAgentEDIT(oList,oMaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_BBAM_LIST,"EDIT_BBAM"));

	}
	
	private class ActionAgentEDIT extends ButtonActionAgentEDIT
	{
		public ActionAgentEDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten Betriebs-BAM"), onavigationList, omaskContainer, oownButton, null, null);
		}



		public Component build_ComponentWithMaskButtons() throws myException
		{
			/*
			 * zuerst die buttons der basis-klasse einbauen
			 */
			E2_ComponentGroupHorizontal oCompGroup= (E2_ComponentGroupHorizontal)super.build_ComponentWithMaskButtons();
			
			MyE2_Button oButtonPrintBAM = new BT_MASK_Mail_Print_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),BT_LIST_EDIT_BAMB.this.oNaviList,false);
			//MyE2_Button oButtonPrintBAM = new BT_MASK_Print_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),BT_LIST_EDIT_BAMB.this.oNaviList);
			MyE2_Button oButtonUnlockBAM = new  BT_MASK_Unlock_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer());
			//MyE2_Button oButtonMailBAM = new BT_MASK_Mail_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),BT_LIST_EDIT_BAMB.this.oNaviList);
			
			oCompGroup.add(oButtonPrintBAM);
			oCompGroup.add(oButtonUnlockBAM);
			//oCompGroup.add(oButtonMailBAM);
			
			return oCompGroup;
		}
		
	}
	
	
}