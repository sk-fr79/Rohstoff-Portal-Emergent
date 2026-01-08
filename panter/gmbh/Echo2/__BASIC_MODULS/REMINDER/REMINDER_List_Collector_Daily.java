/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 21.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.RECLIST_REMINDER_DEF_Ext.ENUM_REMINDER_WIEDERHOLUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 21.03.2016
 *
 */
public class REMINDER_List_Collector_Daily extends REMINDER_List_Base {

	public REMINDER_List_Collector_Daily()  {
		super();
	}

	
	@Override
	protected void collectReminder() {
		try {
			RECLIST_REMINDER_DEF_Ext rlRemindersDay = new RECLIST_REMINDER_DEF_Ext(ENUM_REMINDER_WIEDERHOLUNG.Day);
			for (RECORD_REMINDER_DEF rec: rlRemindersDay){
				REMINDER_List_Entry entry = new REMINDER_List_Entry(rec);
				
				m_vReminderEntries.add(entry);
				
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
	}


	
	
	
	
}
