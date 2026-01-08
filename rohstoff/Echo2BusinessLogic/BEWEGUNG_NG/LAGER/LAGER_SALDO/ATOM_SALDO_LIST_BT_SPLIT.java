package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.UMBUCHUNG.ATOM_LAG_Umbuchung_Erfassung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.UMBUCHUNG.EnumLAGUmbuchungDisplayOption;

public class ATOM_SALDO_LIST_BT_SPLIT extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3992061515807555980L;
	private E2_NavigationList m_navigationList = null;
	
	
	public ATOM_SALDO_LIST_BT_SPLIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("split.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_navigationList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_LAG"));
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
			new ATOM_LAG_Umbuchung_Erfassung(m_navigationList,EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_SPLIT);
		}

		
		
	}
	
}
