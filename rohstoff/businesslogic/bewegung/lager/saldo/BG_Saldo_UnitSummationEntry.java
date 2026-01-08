package rohstoff.businesslogic.bewegung.lager.saldo;

import java.math.BigDecimal;

/**
 * Zeile für die Summation von Mengen die zu einer Einheit gehören
 * @author manfred
 *
 */
public class BG_Saldo_UnitSummationEntry {

	private String m_sIDEinheit = null;
	private String m_sIDEinheitName = null;
	
	private BigDecimal m_bdEinheitSumme = BigDecimal.ZERO;
	
	public BG_Saldo_UnitSummationEntry(String sIDEinheit) {
		this.m_sIDEinheit = sIDEinheit;
	}

	public String get_sIDEinheitName() {
		return m_sIDEinheitName; 
	}

	public void set_sIDEinheitName(String m_sIDEinheitName) {
		this.m_sIDEinheitName = m_sIDEinheitName;
	}

	public BigDecimal get_bdEinheitSumme() {
		return m_bdEinheitSumme;
	}

	public void set_bdEinheitSumme(BigDecimal m_bdEinheitSumme) {
		this.m_bdEinheitSumme = m_bdEinheitSumme;
	}

	/**
	 * Addiert eine Menge zu der bestehenden Summe 
	 * @param bdMenge
	 */
	public void addMenge (BigDecimal bdMenge){
		m_bdEinheitSumme = m_bdEinheitSumme.add(bdMenge != null ? bdMenge : BigDecimal.ZERO);
	}

	/**
	 * gibt die IDEinheit der Summe zurück
	 * @return
	 */
	public String get_sIDEinheit() {
		return m_sIDEinheit;
	}

	
	
	
}
