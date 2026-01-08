package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_SALDO_LIST_BT_EDIT extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 92685089518534147L;

	public ATOM_SALDO_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super("Setzkasten berechnen");
		//		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_LAG"));
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Setzkasten berechnen"), onavigationList, omaskContainer, oownButton, null, null);
		}
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			// TODO Auto-generated method stub
			//super.executeAgentCode(execInfo);
//			ATOM_SALDO_LagerBewegungHandler oBewegung = new ATOM_SALDO_LagerBewegungHandler(null);
//			oBewegung.ReorganiseLagerEntries(bibALL.get_ID_MANDANT());
			
		}

	}
	

}
