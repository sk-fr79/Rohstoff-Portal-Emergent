/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK
 * @author manfred
 * @date 30.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_List_Executer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * Testbutton in der 
 * @author manfred
 * @date 30.03.2016
 *
 */
public class REM_ButtonTest extends MyE2_Button {

	/**
	 * @author manfred
	 * @date 30.03.2016
	 *
	 */
	public REM_ButtonTest() {
		
		super("Reminder verschicken");
		this.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				REMINDER_List_Executer remider_executer = new REMINDER_List_Executer();
				remider_executer.doRemindAllOpenReminders();
			}
		});
	}
	
}



