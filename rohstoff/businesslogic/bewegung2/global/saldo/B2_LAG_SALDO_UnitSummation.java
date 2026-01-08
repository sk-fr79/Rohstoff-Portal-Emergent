package rohstoff.businesslogic.bewegung2.global.saldo;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Zeile für die Summation von Mengen die zu einer Einheit gehören
 * @author manfred
 *
 */
public class B2_LAG_SALDO_UnitSummation {

	private HashMap<String,B2_LAG_SALDO_UnitSummationEntry> m_hmEntries;
	
	
	public B2_LAG_SALDO_UnitSummation() {
		m_hmEntries = new HashMap<String, B2_LAG_SALDO_UnitSummationEntry>();
	}
	
	
	/**
	 * Addiert eine Menge zu der bestehenden Summe der definierten Einheit 
	 * @param bdMenge
	 */
	public void add (String idEinheit, BigDecimal bdMenge){
		B2_LAG_SALDO_UnitSummationEntry o = null;
		if (m_hmEntries.containsKey(idEinheit)){
			o = m_hmEntries.get(idEinheit);
		} else {
			o = new B2_LAG_SALDO_UnitSummationEntry(idEinheit);
		}
		o.addMenge(bdMenge);
		m_hmEntries.put(idEinheit, o);
	}
	

	
	public boolean setEinheitName(String idEinheit, String sEinheitname){
		boolean bRet = false;
		if (m_hmEntries.containsKey(idEinheit)){
			B2_LAG_SALDO_UnitSummationEntry o = m_hmEntries.get(idEinheit);
			o.set_sIDEinheitName(sEinheitname);
			bRet = true;
		}
		return bRet;
	}
	
	

	/**
	 * gibt die Hashmap der gefüllten Zeilen zurück
	 * @return
	 */
	public HashMap<String, B2_LAG_SALDO_UnitSummationEntry> getEntries(){
		return m_hmEntries;
	}
	
	
	/**
	 * gibt die Ergebniszeile einer bestimmten Einheit zurück
	 * @param idEinheit
	 * @return
	 */
	public B2_LAG_SALDO_UnitSummationEntry getEntry(String idEinheit){
		B2_LAG_SALDO_UnitSummationEntry oRet = null;
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
