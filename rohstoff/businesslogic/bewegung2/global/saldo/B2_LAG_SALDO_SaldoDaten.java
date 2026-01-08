package rohstoff.businesslogic.bewegung2.global.saldo;

import java.math.BigDecimal;
import java.util.Date;

public class B2_LAG_SALDO_SaldoDaten {

	private String 	   m_ID_Lager = null;
	private String 	   m_ID_Sorte = null;
	private String    m_ID_Einheit = null;
	private BigDecimal m_Saldo = null;
	private BigDecimal m_SaldoLager = null;
	private BigDecimal m_Inventurmenge = null;
	private Date       m_InventurDatum = null;
	private boolean    m_bIsEmpty = true;




	public B2_LAG_SALDO_SaldoDaten() {
		super();
		this.m_ID_Lager = null;
		this.m_ID_Sorte = null;
		this.set_ID_Einheit(null);
		this.m_Saldo = BigDecimal.ZERO;
		this.m_SaldoLager = BigDecimal.ZERO;
		this.m_Inventurmenge = null;
		this.m_InventurDatum = null;

		this.m_bIsEmpty = true;
	}


	public B2_LAG_SALDO_SaldoDaten(String m_ID_Lager, String m_ID_Sorte, String m_ID_Einheit,
			BigDecimal m_SaldoLager, BigDecimal m_Inventurmenge,
			Date m_InventurDatum) {
		super();
		this.m_ID_Lager = m_ID_Lager;
		this.m_ID_Sorte = m_ID_Sorte;
		this.set_ID_Einheit(m_ID_Einheit);
		this.m_SaldoLager = m_SaldoLager;
		this.m_Inventurmenge = m_Inventurmenge;
		this.m_InventurDatum = m_InventurDatum;


		if (m_SaldoLager != null ){
			if (m_Inventurmenge != null){
				m_Saldo =  m_SaldoLager.add(m_Inventurmenge);
			} else {
				m_Saldo = m_SaldoLager;
			}
		}
		this.m_bIsEmpty = false;
	}

	/**
	 * @param m_SaldoLager the m_SaldoLager to set
	 */
	public void set_SaldoLager(BigDecimal m_SaldoLager) {
		this.m_SaldoLager = m_SaldoLager;
	}

	/**
	 * Saldo OHNE Korrektur durch Inventurmenge
	 * @return the m_SaldoLager
	 */
	public BigDecimal get_SaldoLager() {
		return m_SaldoLager;
	}

	/**
	 * @param m_Saldo the m_Saldo to set
	 */
	public void set_Saldo(BigDecimal m_Saldo) {
		this.m_Saldo = m_Saldo;
	}

	/**
	 * Saldo Korrigiert mit Inventurmenge
	 * @return the m_Saldo
	 */
	public BigDecimal get_Saldo() {
		return m_Saldo;
	}

	/**
	 * @param m_Inventurmenge the m_Inventurmenge to set
	 */
	public void set_Inventurmenge(BigDecimal m_Inventurmenge) {
		this.m_Inventurmenge = m_Inventurmenge;
	}

	/**
	 * @return the m_Inventurmenge
	 */
	public BigDecimal get_Inventurmenge() {
		return m_Inventurmenge;
	}

	/**
	 * @param m_InventurDatum the m_InventurDatum to set
	 */
	public void set_InventurDatum(Date m_InventurDatum) {
		this.m_InventurDatum = m_InventurDatum;
	}

	/**
	 * @return the m_InventurDatum
	 */
	public Date get_InventurDatum() {
		return m_InventurDatum;
	}

	/**
	 * @param m_bIsEmpty the m_bIsEmpty to set
	 */
	public void set_bIsEmpty(boolean m_bIsEmpty) {
		this.m_bIsEmpty = m_bIsEmpty;
	}

	/**
	 * @return the m_bIsEmpty
	 */
	public boolean is_bIsEmpty() {
		return m_bIsEmpty;
	}

	/**
	 * @param m_ID_Lager the m_ID_Lager to set
	 */
	public void set_ID_Lager(String m_ID_Lager) {
		this.m_ID_Lager = m_ID_Lager;
	}

	/**
	 * @return the m_ID_Lager
	 */
	public String get_ID_Lager() {
		return m_ID_Lager;
	}

	/**
	 * @param m_ID_Sorte the m_ID_Sorte to set
	 */
	public void set_ID_Sorte(String m_ID_Sorte) {
		this.m_ID_Sorte = m_ID_Sorte;
	}

	/**
	 * @return the m_ID_Sorte
	 */
	public String get_ID_Sorte() {
		return m_ID_Sorte;
	}



	public String get_ID_Einheit() {
		return m_ID_Einheit;
	}


	public void set_ID_Einheit(String m_ID_Einheit) {
		this.m_ID_Einheit = m_ID_Einheit;
	}
}
