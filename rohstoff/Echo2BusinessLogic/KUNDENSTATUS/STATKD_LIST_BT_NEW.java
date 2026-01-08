package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_LIST_BT_NEW extends MyE2_Button
{

	E2_NavigationList m_naviList = null;
	public STATKD_LIST_BT_NEW(E2_NavigationList onavigationList)
	{
		
		super("Status für den heutigen Tag generieren",E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_STATKD"));
		this.setToolTipText(new MyE2_String("Kundenstatus für den heutigen Tag aufbauen.").CTrans());
		this.m_naviList = onavigationList;

		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		STATKD_LIST_BT_NEW oThis = null;
		public ownActionAgent() {
			super();
			oThis = STATKD_LIST_BT_NEW.this;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			
			STATKD_StatusErmittlung oStatusErmittlung = new STATKD_StatusErmittlung();
			oStatusErmittlung.ErmittleKundenStatus(true);
			oThis.m_naviList.RefreshList();
			
		}
	}
	
}
