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

public class BSAAL_ButtonBauePDFs_ extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = null;

	public BSAAL_ButtonBauePDFs_(BSAAL__ModulContainerLIST	oModulContainer)
	{

		super(new MyE2_String("Erzeuge Preisinfo-Belege/Abnahmeangebote (Versandvorbereitung) "));
		this.setLineWrap(true);

		this.oModulContainerList = oModulContainer;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Bauen ALLER (komplett ausgefüllten) Abnahmeangebote des Monats und versenden der Dokumente ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_CREATE_AND_SEND_PAPERS));
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_ButtonBauePDFs_ oThis = BSAAL_ButtonBauePDFs_.this;
			
			try
			{
				new BSAAL_ButtonBauePDFs_Container(	oThis.oModulContainerList,
													oThis.oModulContainerList.get_oSelector().get_oSelMonate().get_ActualWert(), 
													oThis.oModulContainerList.get_oSelector().get_oSelJahre().get_ActualWert()); 
				
			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufruf des Aufbau-Dialogs !!"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
}
