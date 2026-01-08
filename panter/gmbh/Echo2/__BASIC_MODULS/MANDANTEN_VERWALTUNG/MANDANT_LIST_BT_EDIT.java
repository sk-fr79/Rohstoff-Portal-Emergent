package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MANDANT_LIST_BT_EDIT extends MyE2_Button
{

	public MANDANT_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_MANDANT"));
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			
			this.get_oButtonMaskSave().add_oActionAgent(new ownActionLeseWerteInSession());
		}
		
		private class ownActionLeseWerteInSession extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (ownActionAgent.this.get_cActualID_Unformated().equals(bibALL.get_ID_MANDANT()))
				{
					BASIC_RECORD_MANDANT  recMandant = new BASIC_RECORD_MANDANT(bibALL.get_ID_MANDANT());
					bibALL.setSessionValue("RECORD_MANDANT", 	recMandant);
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Die neuen Werte wurden in die Session geschrieben ..."));
				}
			}
			
		}
		
	}

	
	
	

}
