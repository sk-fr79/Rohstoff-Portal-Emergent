package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ButtonReloadList extends MyE2_Button 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	
	
	public BSAAL_ButtonReloadList(BSAAL__ModulContainerLIST	oModulContainer) 
	{
		super(E2_ResourceIcon.get_RI("reload.png"), true);
		
		this.oModulContainerList = oModulContainer;

		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Neuladen der Listenansicht ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_RELOAD_LISTE));
		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_ButtonReloadList oThis = BSAAL_ButtonReloadList.this;
			try
			{
				oThis.oModulContainerList.get_oNaviList()._REBUILD_COMPLETE_LIST("");
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neuaufbau der Liste !"));
			}
			
		}
	}
	
	
	
}
