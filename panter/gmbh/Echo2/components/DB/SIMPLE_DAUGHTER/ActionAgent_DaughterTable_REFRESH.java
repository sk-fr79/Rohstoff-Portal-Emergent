package panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.exceptions.myException;

public class ActionAgent_DaughterTable_REFRESH extends XX_ActionAgent {
	private MyE2_DBC_DaughterTable 	oDaughterTable = null;
	
	public ActionAgent_DaughterTable_REFRESH(MyE2_DBC_DaughterTable odaughtertable)
	{
		super();
		oDaughterTable = odaughtertable;
	}

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		ActionAgent_DaughterTable_REFRESH oThis = ActionAgent_DaughterTable_REFRESH.this; 
		if (!oThis.oDaughterTable.EXT().get_oComponentMAP().get_bIs_Neueingabe() ) {
			oThis.oDaughterTable.get_oNavigationList().Fill_NavigationList("");
		}
	}

}
