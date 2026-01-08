package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import panter.gmbh.Echo2.components.DB.MaskSearchField.DBSearchBlockStandard;
import panter.gmbh.indep.bibALL;


/**
 * Klasse beschreibt eine zusätzliche Bedingung, die man an einen Selektor im DBSearchBlock(Standard) dynamisch einfügen kann
 * 
 * @author manfred
 * @date   25.03.2013
 */
public class DBSearchCondition {

	/**
	 * Die Bedinung zu der die definition gehört
	 */
	private UP_AND_DOWNLOAD_ENUM_CONDITIONS m_enumCondition = null;
	
	
	/**
	 * Der Name der Bedinung, wird zur Identifikation benutzt
	 */
	private String m_sConditionName = null;
	
	/**
	 * Der Bezeichner innerhalb der Condition
	 */
	private String m_sConditionParameterName = null;
	
	/**
	 * Das Sql-Statement, das man als Bedingung an das Sql-Statement anhängt
	 */
	private String m_sConditionSelectPart = null;

	/**
	 * true, wenn die Werte mit Hochkomma umgeben werden sollen, false sonst
	 */
	private boolean m_bConditionIsTypeOfString = false;
	
	/**
	 * Beschreibungs-String, der auch mit dem Paramter-Wert definiert werden kann damit eine Beschreibung der 
	 * Bedingung ausgegeben werden kann:
	 * z.B. "Es werden alle Fuhren die zur Adresse mit der ID #ID_ADR# in Verbindung stehen durchsucht"
	 */
	private String  m_sDescription = null;
	
	
	/**
	 * Standard-Konstruktor der eine Suchbedingung zu einem DBSearchBlock definiert.
	 * Die Suchbedinung kann an den Searchblock angebunden werden und dynamisch angehängt werden
	 * 
	 * Erzeugt mit: DBSearchCondition("ID_ADRESSE","ID_ADR","ID_ADRESSE_ZUGEORDNET = #ID_ADR# ") {
	 * 
	 * 
	 * {@link DBSearchBlockStandard}
	 * @author manfred
	 * @date   25.03.2013
	 * @param enum_cond
	 * @param sConditionName
	 * @param sConditionParameterName
	 * @param sConditionSelectPart
	 * @param sDescription
	 * @param bConditionValueIsTypeofString
	 */
	public DBSearchCondition(	UP_AND_DOWNLOAD_ENUM_CONDITIONS enum_cond,
								String sConditionName,
								String sConditionParameterName, 
								String sConditionSelectPart,
								String sDescription,
								boolean bCondtionValueIsTypeOfString) {
		super();
		this.set_enumCondition(enum_cond);
		this.m_sConditionName = sConditionName;
		this.m_sConditionParameterName = sConditionParameterName;
		this.m_sConditionSelectPart = sConditionSelectPart;
		this.m_bConditionIsTypeOfString = bCondtionValueIsTypeOfString;
		this.set_Description(sDescription);
	}
	
	
	/**
	 * Gibt den Bedingunsstring mit vorausgestelltem ' AND ' und dem korrekt formatierten Wert zurück
	 * Wird nur angehängt, wenn auch ein Wert vorhanden ist.
	 * @author manfred
	 * @date   25.03.2013
	 * @param sConditionName
	 * @param sValue
	 * @return
	 */
//	public String getConditionWithValue(String sConditionName, String sConditionValue){
//		String sRet = "";
//		
//		if( !bibALL.isEmpty(sConditionName) &&  !bibALL.isEmpty(sConditionValue)) {
//
//			if (sConditionName.equalsIgnoreCase(this.m_sConditionName)){
//				String sDelimiter = m_bConditionIsTypeOfString ? "'" : "";
//				String s_Value = sDelimiter + sConditionValue + sDelimiter;
//				
//				sRet = " AND " + bibALL.ReplaceTeilString(m_sConditionSelectPart, "#" + m_sConditionParameterName + "#", s_Value);
//				
//			}
//		}
//
//		return sRet;
//	}



	/**
	 * Gibt den Bedingunsstring mit vorausgestelltem ' AND ' und dem korrekt formatierten Wert zurück
	 * Wird nur angehängt, wenn auch ein Wert vorhanden ist.
	 * @author manfred
	 * @date   25.03.2013
	 * @param condition Enum-Wert
	 * @param sValue
	 * @return
	 */
	public String getConditionWithValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS enum_cond, String sConditionValue){
		String sRet = "";

		if ( enum_cond.equals(this.get_enumCondition()) ){
			String sDelimiter = m_bConditionIsTypeOfString ? "'" : "";
			String s_Value = sDelimiter + sConditionValue + sDelimiter;
			
			sRet = " AND " + bibALL.ReplaceTeilString(m_sConditionSelectPart, "#" + m_sConditionParameterName + "#", s_Value);
			
		}

		return sRet;
	}
	
	/**
	 * Gibt den Bedingunsstring mit vorausgestelltem ' AND ' und dem korrekt formatierten Wert zurück
	 * Wird nur angehängt, wenn auch ein Wert vorhanden ist.
	 * @author manfred
	 * @date   25.03.2013
	 * @param condition Enum-Wert
	 * @param sValue
	 * @return
	 */
	public String getDescriptionWithValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS enum_cond, String sConditionValue){
		String sRet = "";
		
		if ( enum_cond.equals(this.get_enumCondition()) && !bibALL.isEmpty(m_sDescription) ){
			sRet =  bibALL.ReplaceTeilString(m_sDescription, "#" + m_sConditionParameterName + "#", sConditionValue);
		}
		
		return sRet;
	}
	
	
	/**
	 * @return the sConditionName
	 */
	public String getConditionName() {
		return m_sConditionName;
	}

	/**
	 * @param sConditionName the sConditionName to set
	 */
	public void setConditionName(String sConditionName) {
		this.m_sConditionName = sConditionName;
	}

	/**
	 * @param m_enumCondition the m_enumCondition to set
	 */
	public void set_enumCondition(UP_AND_DOWNLOAD_ENUM_CONDITIONS m_enumCondition) {
		this.m_enumCondition = m_enumCondition;
	}


	/**
	 * @return the m_enumCondition
	 */
	public UP_AND_DOWNLOAD_ENUM_CONDITIONS get_enumCondition() {
		return m_enumCondition;
	}


	/**
	 * @return the sConditionParameterName
	 */
	public String getConditionParameterName() {
		return m_sConditionParameterName;
	}

	/**
	 * @param sConditionParameterName the sConditionParameterName to set
	 */
	public void setConditionParameterName(String sConditionParameterName) {
		this.m_sConditionParameterName = sConditionParameterName;
	}

	/**
	 * @return the sConditionSelectPart
	 */
	public String getConditionSelectPart() {
		return m_sConditionSelectPart;
	}

	/**
	 * @param sConditionSelectPart the sConditionSelectPart to set
	 */
	public void setConditionSelectPart(String sConditionSelectPart) {
		this.m_sConditionSelectPart = sConditionSelectPart;
	}


	/**
	 * @param m_sDescription the m_sDescription to set
	 */
	public void set_Description(String m_sDescription) {
		this.m_sDescription = m_sDescription;
	}


	/**
	 * @return the m_sDescription
	 */
	public String get_Description() {
		return m_sDescription;
	}
	
	
	
}
