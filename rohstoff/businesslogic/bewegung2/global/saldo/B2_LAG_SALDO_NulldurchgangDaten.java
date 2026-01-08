package rohstoff.businesslogic.bewegung2.global.saldo;

import java.math.BigDecimal;
import java.util.Date;

public class B2_LAG_SALDO_NulldurchgangDaten {
	
	private String 	   m_ID_Lager = null;
	private String 	   m_ID_Sorte = null;
	private BigDecimal m_Inventurmenge = null;
	private Date       m_InventurDatum = null;
	
	
	public B2_LAG_SALDO_NulldurchgangDaten(String ID_Lager, String ID_Sorte,
			BigDecimal Inventurmenge, Date InventurDatum) {
		super();
		this.m_ID_Lager = ID_Lager;
		this.m_ID_Sorte = ID_Sorte;
		this.m_Inventurmenge = Inventurmenge;
		this.m_InventurDatum = InventurDatum;
	}


	public String getKey(){
		return m_ID_Lager + "#" + m_ID_Sorte;
	}
	
	
	public String get_ID_Lager() {
		return m_ID_Lager;
	}
	public void set_ID_Lager(String m_ID_Lager) {
		this.m_ID_Lager = m_ID_Lager;
	}
	public String get_ID_Sorte() {
		return m_ID_Sorte;
	}
	public void set_ID_Sorte(String m_ID_Sorte) {
		this.m_ID_Sorte = m_ID_Sorte;
	}
	public BigDecimal get_Inventurmenge() {
		return m_Inventurmenge;
	}
	public void set_Inventurmenge(BigDecimal m_Inventurmenge) {
		this.m_Inventurmenge = m_Inventurmenge;
	}
	public Date get_InventurDatum() {
		return m_InventurDatum;
	}
	public void set_InventurDatum(Date m_InventurDatum) {
		this.m_InventurDatum = m_InventurDatum;
	}
	
	
	
	
}
