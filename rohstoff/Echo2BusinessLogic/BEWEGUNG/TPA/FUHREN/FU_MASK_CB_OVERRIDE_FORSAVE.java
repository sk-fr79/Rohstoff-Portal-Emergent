package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.components.MyE2_CheckBox;

public class FU_MASK_CB_OVERRIDE_FORSAVE extends MyE2_CheckBox 
{

	//NEU31
	public FU_MASK_CB_OVERRIDE_FORSAVE() 
	{
		super();
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("SAVE_FALSE_AVV_DEFINITION"));
		this.setToolTipText(new MyE2_String("Speichere AVV-Einstellung trotz Inkonsistenz !").CTrans());
		
		// damit der validator gezogen wird, muss ein actionAgent vorhanden sein
		this.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
			}
		});
	}
}
