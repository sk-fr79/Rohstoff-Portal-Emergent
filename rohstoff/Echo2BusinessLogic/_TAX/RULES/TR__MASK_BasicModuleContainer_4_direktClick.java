package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;

/**
 * der basiccontainer fuer die edit-buttons in der liste (breitere ansicht zu speichern)
 * @author martin
 *
 */
public class TR__MASK_BasicModuleContainer_4_direktClick extends TR__MASK_BasicModuleContainer { 
	
	public TR__MASK_BasicModuleContainer_4_direktClick(E2_NavigationList  naviList) throws myException  {
		super(naviList);
		
		this.add_CloseActions(new ownActionAfterCloseRefreshActualId(this));
		this.add_CloseActions(new ownActionAfterCloseMarkAktiv(this));

	}
	
	
	
	private class ownActionAfterCloseMarkAktiv extends XX_ActionAgentWhenCloseWindow {

		public ownActionAfterCloseMarkAktiv(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList  nList = TR__MASK_BasicModuleContainer_4_direktClick.this.getNaviList();
		
			if (nList != null) {
				nList.fireActionAgentsAfterListCompleted();
			}
			
		}
		
	}

	private class ownActionAfterCloseRefreshActualId extends XX_ActionAgentWhenCloseWindow {

		public ownActionAfterCloseRefreshActualId(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList  nList = TR__MASK_BasicModuleContainer_4_direktClick.this.getNaviList();
		
			if (nList != null) {
				nList._REBUILD_ACTUAL_SITE(null);
			}
			
		}
		
	}

	
}
