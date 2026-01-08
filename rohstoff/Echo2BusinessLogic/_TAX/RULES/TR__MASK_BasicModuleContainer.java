package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

/**
 * der basiccontainer fuer die standard-edits
 * @author martin
 *
 */
public class TR__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK {

	private E2_NavigationList  naviList = null;
	
	public TR__MASK_BasicModuleContainer(E2_NavigationList  p_naviList) throws myException {
		super(E2_MODULNAME_ENUM.MODUL.MODUL_TAXRULES_MASK.get_callKey());
		
		this.naviList = p_naviList;
				
		this.set_bVisible_Row_For_Messages(true);
		
		TR__MASK_ComponentMAP oTR__MASK_ComponentMAP = new TR__MASK_ComponentMAP();
		
		this.add_CloseActions(new ownActionAfterCloseMarkAktiv(this));
		
		this.INIT(oTR__MASK_ComponentMAP, new TR__MASK(oTR__MASK_ComponentMAP), new Extent(950), new Extent(650));
	}
	
	
	private class ownActionAfterCloseMarkAktiv extends XX_ActionAgentWhenCloseWindow {

		public ownActionAfterCloseMarkAktiv(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList  nList = TR__MASK_BasicModuleContainer.this.naviList;
		
			if (nList != null) {
				nList.fireActionAgentsAfterListCompleted();
				nList._applyMarker();
			}
		}
		
	}


	
	public E2_NavigationList getNaviList() {
	
		return naviList;
	}
	
}
