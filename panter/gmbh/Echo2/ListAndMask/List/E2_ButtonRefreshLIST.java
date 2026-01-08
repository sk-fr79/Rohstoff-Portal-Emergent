package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonRefreshLIST extends MyE2_Button
{

	private E2_NavigationList oNaviList = null;
	
	public E2_ButtonRefreshLIST(E2_NavigationList NavigationList)
	{
		super(E2_ResourceIcon.get_RI("reload.png"), true);
		this.oNaviList = NavigationList;
		this.add_oActionAgent(new ownActionAgentRefresh());
	}
	
	private class ownActionAgentRefresh extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_NavigationList oNavList = E2_ButtonRefreshLIST.this.oNaviList;
			
			String cID_Marked = oNavList.get_cID_Unformated_Of_LastActive_Row();
			
			int iActualPage = oNavList.get_iActualPage();
			oNaviList._REBUILD_COMPLETE_LIST("");
			oNaviList.goToPage(null,iActualPage);
			/*
			 * falls die aktuelle seite leer ist, dann auf die erste seite gehen
			 */
			if (oNaviList.get_vActualID_Segment().size()==0)
				oNaviList.goToPage(null,0);
			
			if (cID_Marked != null)
				oNavList.Mark_ID_IF_IN_Page(cID_Marked);
				
		}
		
	}

	
}
