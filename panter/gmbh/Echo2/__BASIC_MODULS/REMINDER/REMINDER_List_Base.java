package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import java.util.Vector;

import javax.print.DocFlavor.STRING;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class REMINDER_List_Base {

	protected boolean						m_isActive 			= false;
	
	// Liste aller reminder die aktuell verschickt werden müssen
	protected Vector<REMINDER_List_Entry> 		m_vReminderEntries	= null;
	
	
	public REMINDER_List_Base () {
		initReminder();
	}
	
	
	private void initReminder() {
		m_vReminderEntries = new Vector<REMINDER_List_Entry>();
	}

	
	
	/**
	 * ermitteln der Reminder die aktuell gesetzt werden müssen.
	 * Die Reminder werden in die m_vReminderEntries geschrieben,
	 * und dann mit doRemind() erzeugt.
	 * 
	 * @author manfred
	 * @date 21.03.2016
	 *
	 */
	protected abstract void collectReminder();
	
	

	/**
	 * Reminder in das Log eintragen und Messages generieren
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date 21.03.2016
	 *
	 */
	public boolean doRemindOpenReminders() throws myException  {
		boolean bRet = true;
		
		// alle Reminder des Typs ermitteln...
		collectReminder();

		if (m_vReminderEntries!= null){
			
			for (int i = 0; i < m_vReminderEntries.size(); i++){
				REMINDER_List_Entry reminderEntry = m_vReminderEntries.get(i);
				reminderEntry.doRemind();
			}
		}
		return bRet;
	}

}
