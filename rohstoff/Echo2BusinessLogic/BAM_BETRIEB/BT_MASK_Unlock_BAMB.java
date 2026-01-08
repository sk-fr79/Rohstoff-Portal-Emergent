/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class BT_MASK_Unlock_BAMB extends MyE2_Button
{

	public BT_MASK_Unlock_BAMB(BAMB_MASK_ModulContainer oContainerMASK)
	{
		super(E2_ResourceIcon.get_RI("unlocked.png"));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oContainerMASK.get_MODUL_IDENTIFIER(),"UNLOCK_BAM"));
		this.add_oActionAgent(new ownActionAgent(oContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN()));
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP oComponentMAP = null;
		public ownActionAgent(E2_ComponentMAP componentMAP)
		{
			super();
			this.oComponentMAP = componentMAP;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			this.oComponentMAP.set_DisabledFromInteractive(BAMB_MASK_ModulContainer.FBAM_LOCKLIST_BAM,":",false);
			try
			{
				this.oComponentMAP.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_UNDEFINED);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Felder sind wieder offen !"));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Entsperren !"));
			}
		}
		
	}
	
}