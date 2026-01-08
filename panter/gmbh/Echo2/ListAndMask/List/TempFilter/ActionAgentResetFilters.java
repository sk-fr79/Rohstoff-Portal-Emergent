package panter.gmbh.Echo2.ListAndMask.List.TempFilter;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

public class ActionAgentResetFilters extends XX_ActionAgent {

	private E2_NavigationList  naviList = null;
	
	
	
	/**
	 * @param naviList
	 */
	public ActionAgentResetFilters(E2_NavigationList naviList) {
		super();
		this.naviList = naviList;
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		for (MyE2IF__Component comp: this.naviList.get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				((IfFilterExtForListComponents)comp).clearFilter(false);
				((IfFilterExtForListComponents)comp).getvCopyOfVector4Segmentation().clear();
			}
		}
		
	}

}
