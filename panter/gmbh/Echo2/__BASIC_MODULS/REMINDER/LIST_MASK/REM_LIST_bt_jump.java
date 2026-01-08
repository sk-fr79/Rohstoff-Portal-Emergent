package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class REM_LIST_bt_jump extends MyE2_Button
{
	
	private Vector<String> 	vIDs = new Vector<String>();
	private E2_ComponentMAP m_compMap  = null;
	private MODUL 			m_modul = null;
	
	
	public REM_LIST_bt_jump(MODUL modul, String pID) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass.png"));
		this.setToolTipText(new MyE2_String("Springe zum selektierten Listen-Modul").CTrans());
		
		m_modul = modul;
		vIDs = bibALL.get_Vector(pID);
		
		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(m_modul.get_callKey(), vIDs, m_modul.get_userInfo().CTrans()) );
	}
	
		
}

