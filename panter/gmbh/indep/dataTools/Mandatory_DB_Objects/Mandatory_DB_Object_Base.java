package panter.gmbh.indep.dataTools.Mandatory_DB_Objects;

import panter.gmbh.indep.exceptions.myException;


public abstract class Mandatory_DB_Object_Base {

	/**
	 * Identifizierender Objektstring 
	 */
	private String m_Object_Name = null;
	

	/**
	 * Konstruktor, legt den Objektnamen fest
	 * @author manfred
	 * @date   21.01.2013
	 * @param ObjectName
	 */
	public Mandatory_DB_Object_Base(String ObjectName) {
		super();
		m_Object_Name = ObjectName;
	}

	
	/**
	 * gibt den Namen des Datenbank-Objektes zurück
	 * @author manfred
	 * @date   21.01.2013
	 * @return
	 */
	public String getObjectName(){
		return m_Object_Name;
	}
	
	

	/**
	 * Abstrakte Mehthode für die Prüfung der Existenz des Datenbankobjekts
	 * @author manfred
	 * @date   21.01.2013
	 * @return
	 */
	public abstract boolean ObjectExists();
		
	
	
	/**
	 * Abstrakte Methhode für die Prüfung der Gueltigkeit des Datenbankobjekts
	 * @author martin
	 * @date   11.09.2013
	 * @return
	 */
	public abstract boolean ObjectIsValid() throws myException;

	
	
	/**
	 * Abstrakte Methode zur (Neu)-Erzeugung des Datenbank-Objekts 
	 * @author manfred
	 * @date   21.01.2013
	 * @return
	 */
	public abstract boolean CreateObject();
	
	
	
}
