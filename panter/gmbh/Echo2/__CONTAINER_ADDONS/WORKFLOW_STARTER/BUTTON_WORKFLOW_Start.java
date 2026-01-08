package panter.gmbh.Echo2.__CONTAINER_ADDONS.WORKFLOW_STARTER;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.BaseJumper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_BasicModuleContainer;

public class BUTTON_WORKFLOW_Start extends MyE2_Button  implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;

	public BUTTON_WORKFLOW_Start(E2_BasicModuleContainer  ocontainer)
	{
		super(E2_ResourceIcon.get_RI("workflow.png"));
		this.oContainerCalling = ocontainer;
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		
		MyE2_String oString = new MyE2_String("Zu bearbeitende Einträge / eigene Einträge / eigene Workflows");
		this.setToolTipText(oString.CTrans());

		boolean bJumpToSimple = false;
		
		try {
			bJumpToSimple = ENUM_MANDANT_DECISION.WORKFLOW_JUMP_TO_SIMPLE_WORKFLOW_LIST.is_YES();
		} catch (myException e) {		
		}
		if (bJumpToSimple) {
			this.add_oActionAgent(new actionGotoModulWFSimple());
		} else {
			this.add_oActionAgent(new ownActionAgentStartTabWorkflow());
		}
		
		
	}

	
	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartTabWorkflow extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				WF_HEAD_LIST_BasicModuleContainer oWorkflow = new WF_HEAD_LIST_BasicModuleContainer();
				oWorkflow.set_bIsStartContainer_4_DBTimeStamps(true);
				oWorkflow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String("Workflows bearbeiten"));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error showing Workflows !",false)));
			}
		}
	}

	
	
	private class actionGotoModulWFSimple extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				Vector<String> v = new Vector<String>();
				new BaseJumper(E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_LIST.get_callKey(), E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_LIST.get_callKey(), v, false, null);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error jumping to Workflow List !",false)));
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
		return ((!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE))
				&& (!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_MASKE))
				&& (!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE))
				&& (!this.cMODULKENNER.equals(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE))
				&& bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_WORKFLOW_YES());
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
