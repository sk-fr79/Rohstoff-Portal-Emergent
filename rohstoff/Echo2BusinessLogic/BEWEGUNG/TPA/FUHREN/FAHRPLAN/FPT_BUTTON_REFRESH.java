package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class FPT_BUTTON_REFRESH extends MyE2_Button
{
	private FU_LIST_ModulContainer ModulContainer = null;
	
	public FPT_BUTTON_REFRESH(FU_LIST_ModulContainer Modulcontainer)
	{
		super(E2_ResourceIcon.get_RI("reload.png"),true);
		this.ModulContainer = Modulcontainer;
		this.add_oActionAgent(new actionAgentOpenFahrplan());
		this.setToolTipText(new MyE2_String("Fahrplan neu einlesen ...").CTrans());
	}

	
	/*
	 * action nach oeffnen des fahrplans
	 */
	private class actionAgentOpenFahrplan extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_ModulContainer oThis = FPT_BUTTON_REFRESH.this.ModulContainer;
			oThis.get_oNavList()._REBUILD_COMPLETE_LIST("");
		}
		
	}

	
}
