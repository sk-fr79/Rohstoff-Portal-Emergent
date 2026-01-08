/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.exceptions.myException;

public class BT_MASK_RESET_PRINTCOUNTER extends MyE2_Button
{
	private BAMF_MASK_ModulContainer oContainerMask = null;
	
	public BT_MASK_RESET_PRINTCOUNTER(BAMF_MASK_ModulContainer oContainerMASK)
	{
		super(new MyE2_String("Reset Zähler"));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oContainerMASK.get_MODUL_IDENTIFIER(),"RESET_ZAEHLER"));
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Den Wiederholungszähler der Weigermeldungen zurücksetzen ...").CTrans());
		this.oContainerMask = oContainerMASK;
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		public ownActionAgent()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				E2_ComponentMAP oMaskMAP = BT_MASK_RESET_PRINTCOUNTER.this.oContainerMask.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
				((MyE2IF__DB_Component)oMaskMAP.get_Comp("WM_ZAEHLER_ENTSPERRUNG")).set_cActualMaskValue("");
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Jetzt speichern, dann ist der Wert wieder null"));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Resetten !"));
			}
		}
		
	}
}