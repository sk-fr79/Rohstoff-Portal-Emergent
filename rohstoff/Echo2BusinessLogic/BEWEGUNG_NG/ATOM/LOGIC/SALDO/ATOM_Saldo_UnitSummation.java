package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Zeile für die Summation von Mengen die zu einer Einheit gehören
 * @author manfred
 *
 */
public class ATOM_Saldo_UnitSummation {

	private HashMap<String,ATOM_Saldo_UnitSummationEntry> m_hmEntries;
	
	
	public ATOM_Saldo_UnitSummation() {
		m_hmEntries = new HashMap<String, ATOM_Saldo_UnitSummationEntry>();
	}
	
	
	/**
	 * Addiert eine Menge zu der bestehenden Summe der definierten Einheit 
	 * @param bdMenge
	 */
	public void add (String idEinheit, BigDecimal bdMenge){
		ATOM_Saldo_UnitSummationEntry o = null;
		if (m_hmEntries.containsKey(idEinheit)){
			o = m_hmEntries.get(idEinheit);
		} else {
			o = new ATOM_Saldo_UnitSummationEntry(idEinheit);
		}
		o.addMenge(bdMenge);
		m_hmEntries.put(idEinheit, o);
	}
	

	
	public boolean setEinheitName(String idEinheit, String sEinheitname){
		boolean bRet = false;
		if (m_hmEntries.containsKey(idEinheit)){
			ATOM_Saldo_UnitSummationEntry o = m_hmEntries.get(idEinheit);
			o.set_sIDEinheitName(sEinheitname);
			bRet = true;
		}
		return bRet;
	}
	
	

	/**
	 * gibt die Hashmap der gefüllten Zeilen zurück
	 * @return
	 */
	public HashMap<String, ATOM_Saldo_UnitSummationEntry> getEntries(){
		return m_hmEntries;
	}
	
	
	/**
	 * gibt die Ergebniszeile einer bestimmten Einheit zurück
	 * @param idEinheit
	 * @return
	 */
	public ATOM_Saldo_UnitSummationEntry getEntry(String idEinheit){
		ATOM_Saldo_UnitSummationEntry oRet = null;
		if (m_hmEntries.containsKey(idEinheit)){
			oRet = m_hmEntries.get(idEinheit);
		}
		return oRet;
	}
	
	/**
	 * löschen der Einträge
	 */
	public void clear(){
		m_hmEntries.clear();
	}
	
	
}
