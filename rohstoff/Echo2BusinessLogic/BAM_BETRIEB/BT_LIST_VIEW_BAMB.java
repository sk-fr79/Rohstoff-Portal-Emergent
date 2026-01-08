/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;


import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BT_LIST_VIEW_BAMB extends MyE2_Button
{
	public BT_LIST_VIEW_BAMB(	E2_NavigationList 			oList,
								BAMB_MASK_ModulContainer 	oMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png"), true);
		this.add_oActionAgent(new ActionAgentVIEW(oList,oMaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_BBAM_LIST,"ANZEIGE_BBAM"));

	}
	
	class ActionAgentVIEW extends ButtonActionAgentVIEW
	{
		public ActionAgentVIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Anzeigen Betriebs-BAM"), onavigationList, omaskContainer, oownButton, null);
			
			try
			{
				this.get_vZusatzkomponenten().add(new BT_MASK_Mail_Print_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),onavigationList,true));
			} 
			catch (myException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
}