package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;


public class MESSAGE_LIST_BT_EDIT extends MyE2_Button
{

	public MESSAGE_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_MESSAGE"));
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		E2_NavigationList	m_navlist = null;
		E2_BasicModuleContainer_MASK m_MaskContainer = null;
		MyE2_Button m_Button = null;
		
		
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
			this.m_navlist = onavigationList;
			this.m_MaskContainer = omaskContainer;
			this.m_Button = oownButton;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> vSelectedIDs = this.m_navlist.get_vSelectedIDs_Unformated();
			String sID = "= -1";
			if (vSelectedIDs.size()<= 0)	{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss mindestens eine Meldung ausgewählt sein.")));
				return;
			} else if (vSelectedIDs.size() == 1){
				// Unterscheidung der Vergleichsoption für bessere Performance bei einzelselektion
				sID = " = " + vSelectedIDs.get(0);
			}else {
				sID = " in ("+ bibALL.Concatenate(vSelectedIDs, ",", "-1") + ")";
			}
			RECLIST_NACHRICHT rl = new RECLIST_NACHRICHT ("SELECT * FROM JT_NACHRICHT WHERE ID_NACHRICHT " + sID ) ;
			MESSAGE_Editor ed = new MESSAGE_Editor(rl,m_navlist,false);
		}
	}

	
}
	


