package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_LIST_BT_NEW extends MyE2_Button
{

	E2_NavigationList m_navList = null;
	
	public MESSAGE_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_MESSAGE"));
		m_navList = onavigationList;

	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MESSAGE_LIST_BT_NEW oThis = MESSAGE_LIST_BT_NEW.this;
			MESSAGE_Editor ed = new MESSAGE_Editor(oThis.m_navList);
			
		}

	}

}
