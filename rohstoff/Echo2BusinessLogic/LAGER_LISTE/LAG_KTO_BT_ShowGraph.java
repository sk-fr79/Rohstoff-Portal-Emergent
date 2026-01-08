package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_BT_ShowGraph extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3992061515807555980L;
	private E2_NavigationList m_navigationList = null;
	
	
	public LAG_KTO_BT_ShowGraph(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("graph.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_navigationList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_KTO_SHOW_GRAPH"));
	}

	
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			new LAG_KTO_DisplayGraph(m_navigationList);
		}

		
		
	}
	
}
