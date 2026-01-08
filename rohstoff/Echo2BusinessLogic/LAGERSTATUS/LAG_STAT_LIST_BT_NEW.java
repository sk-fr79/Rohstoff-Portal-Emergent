package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_BEW_StatusErmittlung;

public class LAG_STAT_LIST_BT_NEW extends MyE2_Button
{

	E2_NavigationList m_naviList = null;
	
	public LAG_STAT_LIST_BT_NEW(E2_NavigationList onavigationList)
	{
		super("Lagerstatus aktualisieren", E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.m_naviList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_LAG_STAT"));

	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		LAG_STAT_LIST_BT_NEW oThis =LAG_STAT_LIST_BT_NEW.this;
		
		public ownActionAgent() {
			super();
		}
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			// TODO
			LAG_BEW_StatusErmittlung oStat = new LAG_BEW_StatusErmittlung();
			// automatisch alle Äderungen verbuchen
			oStat.rebuildLagerstatus_AUTO();
			// dann noch den Tagesaktuellen Eintrag durchführen
			oStat.ErmittleLagerstatus(true);
			
			oThis.m_naviList.RefreshList();
			
		}

	}
	
}
