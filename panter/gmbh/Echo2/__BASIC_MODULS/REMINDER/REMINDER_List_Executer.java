/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * Executer checkt alle offenen Reminder des Mandanten und verschickt die Nachrichten 
 * @author manfred
 * @date 24.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 24.03.2016
 *
 */
public class REMINDER_List_Executer implements ICallableTask{

	private Vector<REMINDER_List_Base> m_vReminder;
	
	/**
	 * @author manfred
	 * @date 24.03.2016
	 *
	 */
	public REMINDER_List_Executer() {
		m_vReminder = new Vector<>();

		// alle reminder die geprüft werden sollen müssen angemeldet werden
		m_vReminder.add(new REMINDER_List_Collector_Daily());
		m_vReminder.add(new REMINDER_List_Collector_Weekly());
		m_vReminder.add(new REMINDER_List_Collector_Month());
		m_vReminder.add(new REMINDER_List_Collector_Month_NEG());
		m_vReminder.add(new REMINDER_List_Collector_Year());
		
		
	}
	
	
	/** 
	 * Ermittelt alle offenen Reminder und schickt die Nachrichten
	 * @author manfred
	 * @date 24.03.2016
	 *
	 * @return
	 * @throws myException 
	 */
	public boolean doRemindAllOpenReminders() throws myException{
		boolean bRet = true;
		for (REMINDER_List_Base rem : m_vReminder){
			bRet = rem.doRemindOpenReminders();
		}
		return bRet;
	}
	
	
	
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#runTask()
	 */
	@Override
	public boolean runTask() {
		boolean bRet = false;
		try {
			bRet =  doRemindAllOpenReminders();
			m_TaskMessages.add(new MyString("Erinnerungsmeldungen aktualisiert.").CTrans() + (bRet ? " * OK" : " * Fehler"));
			return bRet;
		} catch (myException e) {
			e.printStackTrace();
			m_TaskMessages.add(new MyString("Fehler beim erzeugen der Erinnerungsmeldungen.").CTrans() + e.getMessage());
		}
		return bRet;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#setTaskParameters(java.util.HashMap)
	 */
	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters = hmParameters;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#getTaskMessages()
	 */
	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}
	
	

}
