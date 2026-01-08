package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;




/**
 * Interface, welches SearchBlock-Objekte die im GUI-Suchfeld verwendet wreden implementieren müssen, 
 * wenn sie im Archiv-Up/Download-verwendet werden sollen
 * 
 * @author manfred
 * @date   25.03.2013
 */
public interface I_SearchBlock_For_UpAndDownload {

	
	/**
	 * fügt eine zusätzliche Bedingung zum Searchblock dazu, ohne den Wert der Bedingung festzulegen
	 * @author manfred
	 * @date   25.03.2013
	 * @param oCond
	 */
	public void add_SearchConditionForARCHIVE(DBSearchCondition oCond);
	
	
	/**
	 * Setzt den Wert einer Suchbedingung 
	 * @author manfred
	 * @date   25.03.2013
	 * @param sSearchCondition
	 * @param sValue
	 */
	public void set_SearchConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS  enumCondition, String sValue);
	
	
	/**
	 * Gibt die Beschreibung für die zusätzlichen Bedingungen zurück, die aktuell im Searchblock gesetzt sind.
	 * @author manfred
	 * @date   26.03.2013
	 * @return
	 */
	public String get_DescriptionOfAdditionalSearchConditions();


}
