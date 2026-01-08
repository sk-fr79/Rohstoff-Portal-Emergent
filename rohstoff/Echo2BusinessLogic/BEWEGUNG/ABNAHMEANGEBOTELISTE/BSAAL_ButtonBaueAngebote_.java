package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class BSAAL_ButtonBaueAngebote_ extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = null;

	public BSAAL_ButtonBaueAngebote_(BSAAL__ModulContainerLIST	oModulContainer)
	{
		super(new MyE2_String("Erzeuge Preislistensätze/Abnahmeangebote"));
		this.setLineWrap(true);
		
		this.oModulContainerList = oModulContainer;
		
		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Erzeuge Abnahmeangebote, die im obigen Monat noch nicht vorhanden sind ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_BUILD_ANGEBOTE));
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL__ModulContainerLIST	ooModulContainerList = BSAAL_ButtonBaueAngebote_.this.oModulContainerList;
			
			try
			{
				new BSAAL_ButtonBaueAngebote_Container(	ooModulContainerList,
														ooModulContainerList.get_oSelector().get_oSelMonate().get_ActualWert(), 
														ooModulContainerList.get_oSelector().get_oSelJahre().get_ActualWert());

			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("AAL_ButtonBaueAngebote:ownActionAgent:Error building selectorWindow: ",false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	
	
}
