package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


public class WF_HEAD_LIST_BT_CopyWF extends MyE2_Button
{

	/**
	 * 
	 * @author manfred
	 * @date 17.10.2017
	 *
	 * @param onavigationList
	 * @param omaskContainer
	 */
		
	public WF_HEAD_LIST_BT_CopyWF(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png") , E2_ResourceIcon.get_RI("copy.png"));
		this.add_oActionAgent(new actionAgentCopyWF(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("WF_HEAD_LIST_CopyWF"));
		this.setToolTipText(new MyString("Kopie eines Laufzettels anlegen").CTrans());
	}
	
	
	
	private class actionAgentCopyWF extends XX_ActionAgent{

		E2_NavigationList m_navList = null;
		public actionAgentCopyWF(E2_NavigationList onavigationList) {
			m_navList = onavigationList;
		}
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			
			if (m_navList == null || m_navList.get_vSelectedIDs_Unformated().size() != 1){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur genau ein Laufzettel kopiert werden")));
				return;
			}

			String id = m_navList.get_vSelectedIDs_Unformated().firstElement();
			new WF_Dialog_Copy_Laufzettel(id ,m_navList);
			
		}
		
	}
	
}
