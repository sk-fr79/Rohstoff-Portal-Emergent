package panter.gmbh.Echo2.__CONTAINER_ADDONS.MESSAGE_STARTER;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Editor;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BUTTON_MESSAGE_Start extends MyE2_Button  implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;

	public BUTTON_MESSAGE_Start(E2_BasicModuleContainer  ocontainer)
	{
		super(E2_ResourceIcon.get_RI("message.png"));
		this.oContainerCalling = ocontainer;
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		
		MyE2_String oString = new MyE2_String("Neue Meldung verschicken...");
		this.setToolTipText(oString.CTrans());

		this.add_oActionAgent(new ownActionAgentStartTabMessage());
		
	}

	
	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartTabMessage extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				MESSAGE_Editor oMessage = new MESSAGE_Editor();
				oMessage.set_bIsStartContainer_4_DBTimeStamps(true);
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing Messages !",false)));
			}
		}
	}



	@Override
	public void add_cZusatzMODULKENNER(String modulKenner)
	{
		
	}



	/**
	 * @throws myException 
	 * 
	 */
	public boolean get_bIsShown() throws myException
	{
		return ((!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE))
				&& (!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE))
				&& bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_MESSAGES_YES());
	}

	
	public void set_cMODULKENNER(String cModulKenner)
	{
		this.cMODULKENNER = cModulKenner;
	}

	
	public String get_cMODULKENNER()
	{
		return this.cMODULKENNER;
	}
	

	
}
