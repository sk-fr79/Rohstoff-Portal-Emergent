/**
 * panter.gmbh.Echo2.__BASIC_COMPONENTS
 * @author manfred
 * @date 29.06.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_CONST;

/**
 * @author manfred
 * @date 29.06.2016
 *
 */
public class BT_UserDropdownExtension_Self_Setter extends MyE2_Button {

	private MyE2_SelectField 	m_selField = null;
	private XX_ActionAgent  	m_ActionAgent = null;
	
	/**
	 * @author manfred
	 * @date 29.06.2016
	 *
	 */
	public BT_UserDropdownExtension_Self_Setter(MyE2_SelectField oSelFieldToConnectTo) {
		super(E2_ResourceIcon.get_RI("user-mini.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.m_selField = oSelFieldToConnectTo;
		m_ActionAgent = new cActionSelectMe();
		this.add_oActionAgent(m_ActionAgent);
		
	}
	
	/**
	 * Actionagent für die Selektion des angemeldeten Benutzers oder leerem Feld
	 * @author manfred
	 * @date 29.06.2016
	 *
	 */
	private class cActionSelectMe extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (S.isEmpty(BT_UserDropdownExtension_Self_Setter.this.m_selField.get_ActualWert())) {
				BT_UserDropdownExtension_Self_Setter.this.m_selField.set_ActiveValue_OR_FirstValue(bibALL.get_ID_USER_FORMATTED());
			} else {
				BT_UserDropdownExtension_Self_Setter.this.m_selField.set_ActiveValue_OR_FirstValue("");
			}
		}
	}
	
	
}
