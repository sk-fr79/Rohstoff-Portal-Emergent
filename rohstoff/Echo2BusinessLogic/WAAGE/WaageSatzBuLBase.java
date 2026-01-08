package rohstoff.Echo2BusinessLogic.WAAGE;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;



/**
 * Systec-Waage Offenburg / Kehl
 * @author manfred
 *
 */
public class WaageSatzBuLBase extends WaageSatzBase {
	
	// Waage-Satz-Elemente speziell für B+L-Waagen
//	public static final String WF_BUL_ERR = "WF_BUL_ERR";
	public static final String WF_BUL_E_CODE = "WF_BUL_E_CODE";
	public static final String WF_BUL_STAT = "WF_BUL_STAT";
	

	
	/**
	 * Der Wagesatz-Aufbau der Systec-Waage
	 * Reihenfolge der Felder entspricht der Reihenfolge im Satzaufbau
	 */
	public WaageSatzBuLBase() {
		super();

		this.setBeginTag(Character.toString((char)2 ));
		this.setEndeTag(Character.toString((char)3 ));
		this.setDatumsFormat("dd-MM-yyyy");
	}

	
	@Override
	public String getErrorDescription() {
		String fehlercode = this.getData(WF_Fehlercode);
		String message = null;
		if (fehlercode != null){
			if (fehlercode.equalsIgnoreCase("0")){
				message = "OK"; // kein Fehler;
			} else if (fehlercode.equalsIgnoreCase("1")){
				message = "Kommando-Schnittstelle nicht initialisiert";
			} else if (fehlercode.equalsIgnoreCase("2")){
				message = "Kommandobearbeitung nicht abgeschlossen";
			} else if (fehlercode.equalsIgnoreCase("3")){
				message = "Kommando kann nicht ausgeführt werden";
			} else if (fehlercode.equalsIgnoreCase("4")){
				message = "Ungültige oder falsche Kommandoparameter";
			} else if (fehlercode.equalsIgnoreCase("7")){
				message = "Unbekanntes Kommando";
			} else {
				message = "Unbekannter Fehlercode." ;
			}
			message += " (" + fehlercode + ")";
		}
		
		// TODO Auto-generated method stub
		return message;
	}
	
	
	/**
	 * Parst die Übergebenen Waage-String und trägt die Ergebnisse in 
	 * die Ergebnisliste ein.
	 * bei B+L wird immer als erstes Byte ein Errorwert angegeben, der den Status der Rückgabe definiert.
	 * 
	 */
	public boolean parseWaageData(String waage_daten){
		boolean bRet = false;
		if (waage_daten == null) {
			return bRet;
		}
		
		// status zuerst mal leer!
		m_StatusWaageResult = ENUM_WaageResultStatus.EMPTY;
		
		
		int size = m_FieldsDefinition.size();


		if (m_BeginTag != null &&  waage_daten != null && waage_daten.length() > m_BeginTag.length()){
			waage_daten = waage_daten.substring(m_BeginTag.length());
		} else {
			return false;
		}
		
		if (m_EndeTag != null &&  waage_daten != null && waage_daten.length() > m_EndeTag.length()){
			waage_daten = waage_daten.substring(0,waage_daten.length() - m_EndeTag.length());
		}else {
			return false;
		}

		
		m_WaageSatzData = new String[size];

		bRet = true;
		
		
		// Errorcode-Feld auslesen. Ist bei B+L eigentlich immer das 1. Feld
		String sErr = "";
		try {
			sErr = getFieldDataFromResultString(waage_daten, WF_Fehlercode);
			m_WaageSatzData[m_FieldOrder.get(WF_Fehlercode)] = sErr;
			
		} catch (myException e1) {
			sErr = null;
		}
		
		// falls kein Fehler vorhanden ist, den rest auslesen
		if (sErr != null && sErr.equals("0")){
			m_StatusWaageResult = ENUM_WaageResultStatus.OK;
		   
			// jetzt alle definierten Felder auslesen...
			for (int i=0; i < m_FieldsDefinition.size(); i++){
				WaageSatzFeld feld = m_FieldsDefinition.get(i);
				int pos = m_FieldOrder.get(feld.getName());
				try {
					String sValue = getFieldDataFromResultString(waage_daten, feld.getName());
					m_WaageSatzData[pos] = sValue;
				} 
				catch (myException e)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(e.getMessage()));
					bRet = false;
				}
			}
			
		} else {
			m_StatusWaageResult = ENUM_WaageResultStatus.ERROR;
		}


		return bRet;
	}
	
	
	@Override
	public String getDemoData() {

		return "";
	}
	
	
}
