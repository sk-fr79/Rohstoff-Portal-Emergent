package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_SALDO_LIST_BT_VIEW extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5282159825770782374L;

	public ATOM_SALDO_LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super("Preise ermitteln!");
		//super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ANZEIGE_LAG"));

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Preise ermitteln"), onavigationList, omaskContainer, oownButton,null);
		}
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			// TODO Auto-generated method stub
			//super.executeAgentCode(execInfo);
//			ATOM_SALDO_LagerPreisHandler oPreise = new ATOM_SALDO_LagerPreisHandler(null);
//			oPreise.ReorganisePriceEntries();
			//oPreise.executeSqlStatements(true);
			
		}
	}
	
}
