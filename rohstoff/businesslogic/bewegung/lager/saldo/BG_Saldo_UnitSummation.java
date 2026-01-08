package rohstoff.businesslogic.bewegung.lager.saldo;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Zeile für die Summation von Mengen die zu einer Einheit gehören
 * @author manfred
 *
 */
public class BG_Saldo_UnitSummation {

	private HashMap<String,BG_Saldo_UnitSummationEntry> m_hmEntries;
	
	
	public BG_Saldo_UnitSummation() {
		m_hmEntries = new HashMap<String, BG_Saldo_UnitSummationEntry>();
	}
	
	
	/**
	 * Addiert eine Menge zu der bestehenden Summe der definierten Einheit 
	 * @param bdMenge
	 */
	public void add (String idEinheit, BigDecimal bdMenge){
		BG_Saldo_UnitSummationEntry o = null;
		if (m_hmEntries.containsKey(idEinheit)){
			o = m_hmEntries.get(idEinheit);
		} else {
			o = new BG_Saldo_UnitSummationEntry(idEinheit);
		}
		o.addMenge(bdMenge);
		m_hmEntries.put(idEinheit, o);
	}
	

	
	public boolean setEinheitName(String idEinheit, String sEinheitname){
		boolean bRet = false;
		if (m_hmEntries.containsKey(idEinheit)){
			BG_Saldo_UnitSummationEntry o = m_hmEntries.get(idEinheit);
			o.set_sIDEinheitName(sEinheitname);
			bRet = true;
		}
		return bRet;
	}
	
	

	/**
	 * gibt die Hashmap der gefüllten Zeilen zurück
	 * @return
	 */
	public HashMap<String, BG_Saldo_UnitSummationEntry> getEntries(){
		return m_hmEntries;
	}
	
	
	/**
	 * gibt die Ergebniszeile einer bestimmten Einheit zurück
	 * @param idEinheit
	 * @return
	 */
	public BG_Saldo_UnitSummationEntry getEntry(String idEinheit){
		BG_Saldo_UnitSummationEntry oRet = null;
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
