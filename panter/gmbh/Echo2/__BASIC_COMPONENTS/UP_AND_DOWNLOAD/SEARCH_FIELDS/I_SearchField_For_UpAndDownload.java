package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;




/**
 * Interface, welches alle GUI-Objekte implementieren müssen, wenn sie im Archiv-Up/Download-verwendet werden sollen
 * @author manfred
 * @date   25.03.2013
 */
public interface I_SearchField_For_UpAndDownload {

	
	public String get_FoundObjectID();
	public String get_DBTableName();
		

	
	/**
	 * Setzen eines Parameterwertes von Außen.
	 * 
	 * Der Parameter ist nur Relevant wenn er auch in dem Suchobjekt verarbeitet wird.
	 * Falls er nicht verarbeitet wird, wird der gesetzte Wert einfach ignoriert und
	 * es wird KEIN Fehler geworfen
	 * 
	 * @author manfred
	 * @date   25.03.2013
	 * @param parameter
	 * @param paramValue
	 */
	public void set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS condition, String conditionValue);
	
	
	/**
	 * Gibt die Beschreibung aller Conditions als String zurück.
	 * @author manfred
	 * @date   26.03.2013
	 * @return
	 */
	public String get_ConditionDescriptions();	
	
}
