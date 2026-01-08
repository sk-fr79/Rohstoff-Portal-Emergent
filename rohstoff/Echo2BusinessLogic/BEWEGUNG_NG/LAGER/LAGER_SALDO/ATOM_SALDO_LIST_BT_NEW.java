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

public class ATOM_SALDO_LIST_BT_NEW extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1563265202576531967L;
	private E2_NavigationList m_navigationList = null;
	
	
	public ATOM_SALDO_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_navigationList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_LAG"));
	}

	
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			//	super(new MyE2_String("Lagerbuchung... "), onavigationList, omaskContainer, oownButton, null);
			
			super();
			
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			// TODO Auto-generated method stub
			//	super.executeAgentCode(execInfo);
			
			new ATOM_LAG_Umbuchung_Erfassung(m_navigationList,EnumLAGUmbuchungDisplayOption.SHOW_BUCHUNG_EINFACH);
			
			
				
			
		}

		
		
	}
	
}
