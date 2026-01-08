package rohstoff.Echo2BusinessLogic.SPIELWIESE.list;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.SPIELWIESE.mask.SPIELWIESE_MASK_HashMap;


public class SPIELWIESE_LIST_BT_EDIT_NEW extends MyE2_Button
{

	public SPIELWIESE_LIST_BT_EDIT_NEW(E2_NavigationList nl, RB_ModuleContainerMASK mc)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(nl, mc));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_SPIELWIESE"));
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		private E2_NavigationList nl;
		private RB_ModuleContainerMASK mc;
		
		public ownActionAgent(E2_NavigationList nl, RB_ModuleContainerMASK mc)
		{
			this.nl = nl;
			this.mc = mc;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> v = nl.get_vSelectedIDs_Unformated();
			if (v.size() != 1) {
				
			} else {
/*				SPIELWIESE_MASK_HashMap m = (SPIELWIESE_MASK_HashMap)mc.get_RB_MASK_HM();
				m.set_Records_2_All_Masks(v.get(0), RB__CONST.MASK_STATUS.EDIT);
				//TODO: Function name has changed
				//m.RM_maskContent_COMPLETE_FILL_CYCLE();
				 
				 */
				this.mc.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(500), new MyE2_String("Test"));
			}
		}
	}
	

}
