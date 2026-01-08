package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_FAHRZEUGE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum ermitteln und interpretieren von Kennzeichen
 * @author manfred
 *
 */
public class WK_Kennzeichen {

	private String m_Kennzeichen_ORI = null;
	private String m_Kennzeichen	 = null;
	
	
	public WK_Kennzeichen(String Kennzeichen) {
		m_Kennzeichen_ORI = Kennzeichen;
		
		// kennzeichen noramalisieren
		normalisiereKennzeichen();
	}
	
	
	/**
	 * nimmt das Kennzeichen und versucht es zu normalisieren, 
	 * d.h. 
	 * - entfernen der unnötigen Leerstellen, 
	 * - große Schrift
	 * 
	 * @return
	 */
	private String normalisiereKennzeichen(){
		m_Kennzeichen = m_Kennzeichen_ORI;

		if (m_Kennzeichen != null){
			
			// bindestrich bearbeiten
			if (m_Kennzeichen.contains("-")){
				int idx = m_Kennzeichen.indexOf("-");
				String s1 = m_Kennzeichen.substring(0, idx );
				String s2 = m_Kennzeichen.substring(idx+1);
				m_Kennzeichen = s1 + " " + s2;
			}

			// gross-Schrift
			m_Kennzeichen = m_Kennzeichen.toUpperCase();
			
			// alle mehrfachen Leerzeichen rauslöschen
			m_Kennzeichen = m_Kennzeichen.replaceAll("\\s{2,}", " ");
		} 

		return m_Kennzeichen;
			
	}
	
	
	/**
	 * gibt das "normalisierte" Kennzeichen zurück
	 * @return
	 */
	public String getKennzeichen() {
		return m_Kennzeichen;
	}
	
	


	/**
	 * Lesen des Kennzeichens aus der Kennzeichen-DB und ermitteln des Records
	 * @param sKennzeichen
	 * @return
	 */
	public RECORD_ADRESSE_FAHRZEUGE getRecordAdresseFahrzeuge(){
		RECORD_ADRESSE_FAHRZEUGE oRecKFZ = null;
		try {
			oRecKFZ = new RECORD_ADRESSE_FAHRZEUGE("KENNZEICHEN = '" + m_Kennzeichen + "' AND AKTIV = 'Y'");
		} catch (myException e) {
			// TODO Auto-generated catch block
			oRecKFZ = null;
		}
		return oRecKFZ;
	}
	
	/**
	 * Erzeugen eines neuen Kennzeichen-Eintrags
	 * @param Kennzeichen
	 * @param sIDKunde
	 * @param sBeschreibung
	 * @return
	 */
	public boolean saveKennzeichen(String sKennzeichen, String sIDKunde, String sBeschreibung){
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		Vector<String> SqlStatements = new Vector<String>();
		
		try {
			oSql.addSQL_Paar("ID_ADRESSE_FAHRZEUGE", "SEQ_ADRESSE_FAHRZEUGE.NEXTVAL", false);
			oSql.addSQL_Paar("ID_ADRESSE", sIDKunde, false);
			oSql.addSQL_Paar("KENNZEICHEN", sKennzeichen, true);
			oSql.addSQL_Paar("BESCHREIBUNG", sBeschreibung, true);
			oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
			oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    String sSql = oSql.get_CompleteInsertString("JT_ADRESSE_FAHRZEUGE", bibE2.cTO());
	    SqlStatements.add(sSql);
	    
		boolean bRet = true;
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(SqlStatements, true);
		bRet &= mv.get_bIsOK();
		if (bRet){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
			
		return bRet;
	    
	}
	
}
