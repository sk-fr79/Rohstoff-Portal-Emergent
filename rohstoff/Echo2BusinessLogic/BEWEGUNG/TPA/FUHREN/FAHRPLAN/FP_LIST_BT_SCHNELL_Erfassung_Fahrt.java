package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG.FPSE_Container_SCHNELL_Erfassung_Fahrt;

public class FP_LIST_BT_SCHNELL_Erfassung_Fahrt extends MyE2_Button
{
	
	public FP_LIST_BT_SCHNELL_Erfassung_Fahrt()
	{
		super(E2_ResourceIcon.get_RI("lkw_button.png") , true);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ERFASSUNG_FAHRT"));
		this.setToolTipText(new MyE2_String("Schnellerfassung einer neuen Fahrt ").CTrans());
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			new FPSE_Container_SCHNELL_Erfassung_Fahrt();
		}
	}
	
}
