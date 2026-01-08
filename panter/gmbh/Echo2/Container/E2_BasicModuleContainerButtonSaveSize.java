package panter.gmbh.Echo2.Container;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingWindowSize;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

/*
 * button, der jedem BasicmodulContainer zugeordnet wird und der beim popup-erzeugen
 * angezeigt wird, wenn der container einer eigenen (nachfolgenden ) klasse von E2_BasicModuleContainer
 * entstammt und wenn der messagebereich angezeigt wird
 */
public class E2_BasicModuleContainerButtonSaveSize extends MyE2_Button 
{

	private E2_BasicModuleContainer oMotherContainer = null;
	
	public E2_BasicModuleContainerButtonSaveSize(E2_BasicModuleContainer oMother) 
	{
		//super(E2_ResourceIcon.get_RI("save_status.png"));
		super(new MyE2_String("Fenstergroesse speichern"));
		this.oMotherContainer = oMother;
		this.setToolTipText(new MyE2_String("Fenstergrösse und Position sichern !").CTrans());
		this.add_oActionAgent(new ownActionAgent());
	}
	

	
	private class ownActionAgent  extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainerButtonSaveSize oThis = E2_BasicModuleContainerButtonSaveSize.this; 

			if (oThis.oMotherContainer.get_oWindowPane() != null)
			{
				int iSavedWindows = new E2_UserSettingWindowSize().storeContainer(oThis.oMotherContainer);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(	"Fenstergröße und -Position wurde gesichert: Anzahl Fenster: ",true,
																			""+iSavedWindows,false,
																			"  Breite:",true,
																			""+oThis.oMotherContainer.get_oExtWidth(),false,
																			"  Höhe:",true,
																			""+oThis.oMotherContainer.get_oExtHeight(),false
																			)));
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Status kann nicht gesichert werden !"));
			}
		}
	}
	
	
	
	

}
