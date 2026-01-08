package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.indep.exceptions.myException;

public abstract class REMINDER_Handler_Base {

	String m_cTO 										= null;
	Vector<String> m_vIDUser 							= null;
	Vector<MESSAGE_Entry> m_vReminderEntries			= null;
	
	String m_Modulname 									= null;
	String m_ModulZusatz 								= null;
	String m_KategorieKennung 							= null;
	
	
	public REMINDER_Handler_Base( ) {
		m_cTO = bibE2.cTO();
		m_vIDUser = new Vector<String>();
		m_vReminderEntries = new Vector<MESSAGE_Entry>();
	}
	
	
	
	public void updateReminders(){
		initReminder();
		insertReminders();
	}
	
	
	/**
	 * Ermitteln der Reminder-Einträge und dann 
	 * Einfügen der Werte mit insertReminders
	 * @param ModulNameTarget
	 */
	protected abstract void initReminder();
	
	
	
	/**
	 * Füllen der Daten in das Datenobjekt
	 * @param sArrDaten
	 * @param value_type
	 */
	protected void insertReminders() {
		MESSAGE_Handler  oHandler = new MESSAGE_Handler();
		
		if (m_vReminderEntries!= null){
			// sonst den Vektor füllen
			for (int i = 0; i < m_vReminderEntries.size(); i++){
				MESSAGE_Entry oEntry = m_vReminderEntries.get(i);
				try {
					oHandler.saveMessage(oEntry);
				} catch (myException e) {
					// todo: was tun, wenn ein Reminder nicht eingetragen wurde????
				}
			}
		}
	}


	
}
