package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.VERTRAGSMENGEN;

import java.math.BigDecimal;

public class ATOM_Lager_KontraktmengenDaten {
	private String     m_ID_Lager = null;
	private String 	   m_ID_Artikel = null;
	
	private BigDecimal m_bdEKLagermenge = null;
	private BigDecimal m_bdEKRestmenge = null;
	private BigDecimal m_bdEKRestmengeMitPlanung = null;
	
	private BigDecimal m_bdVKLagermenge = null;
	private BigDecimal m_bdVKRestmenge = null;
	private BigDecimal m_bdVKRestmengeMitPlanung = null;
	
	private BigDecimal m_bdGesamtRestmenge = null;
	private BigDecimal m_bdGesamtRestmengeMitPlanung = null;

	private boolean    m_bIsEmpty = true;
	
	public ATOM_Lager_KontraktmengenDaten(String idLager, String idArtikel) {
		set_ID_Lager(idLager);
		set_ID_Artikel(idArtikel);
		
		m_bdEKLagermenge = BigDecimal.ZERO;
		m_bdEKRestmenge = BigDecimal.ZERO;
		m_bdEKRestmengeMitPlanung = BigDecimal.ZERO;
		m_bdVKLagermenge = BigDecimal.ZERO;
		m_bdVKRestmenge = BigDecimal.ZERO;
		m_bdVKRestmengeMitPlanung = BigDecimal.ZERO;
		m_bdGesamtRestmenge = BigDecimal.ZERO;
		m_bdGesamtRestmengeMitPlanung = BigDecimal.ZERO;
		m_bIsEmpty = true;
	}


	public ATOM_Lager_KontraktmengenDaten(String idLager, String idArtikel,
			BigDecimal mBdEKLagermenge,
			BigDecimal mBdEKRestmenge, BigDecimal mBdEKRestmengeMitPlanung,
			BigDecimal mBdVKLagermenge, BigDecimal mBdVKRestmenge,
			BigDecimal mBdVKRestmengeMitPlanung, BigDecimal mBdGesamtRestmenge,
			BigDecimal mBdGesamtRestmengeMitPlanung) {
		super();
		set_ID_Lager(idLager);
		set_ID_Artikel(idArtikel);
		m_bdEKLagermenge = mBdEKLagermenge;
		m_bdEKRestmenge = mBdEKRestmenge;
		m_bdEKRestmengeMitPlanung = mBdEKRestmengeMitPlanung;
		m_bdVKLagermenge = mBdVKLagermenge;
		m_bdVKRestmenge = mBdVKRestmenge;
		m_bdVKRestmengeMitPlanung = mBdVKRestmengeMitPlanung;
		m_bdGesamtRestmenge = mBdGesamtRestmenge;
		m_bdGesamtRestmengeMitPlanung = mBdGesamtRestmengeMitPlanung;
		m_bIsEmpty = false;
	}
	

	public void setEKLagermenge(BigDecimal mBdEKLagermenge) {
		m_bdEKLagermenge = mBdEKLagermenge;
	}

	public void setEKRestmenge(BigDecimal mBdEKRestmenge) {
		m_bdEKRestmenge = mBdEKRestmenge;
		m_bIsEmpty = false;
	}

	public void setEKRestmengeMitPlanung(BigDecimal mBdEKRestmengeMitPlanung) {
		m_bdEKRestmengeMitPlanung = mBdEKRestmengeMitPlanung;
		m_bIsEmpty = false;
	}

	public void setVKLagermenge(BigDecimal mBdVKLagermenge) {
		m_bdVKLagermenge = mBdVKLagermenge;
		m_bIsEmpty = false;
	}

	public void setVKRestmenge(BigDecimal mBdVKRestmenge) {
		m_bdVKRestmenge = mBdVKRestmenge;
		m_bIsEmpty = false;
	}

	public void setVKRestmengeMitPlanung(BigDecimal mBdVKRestmengeMitPlanung) {
		m_bdVKRestmengeMitPlanung = mBdVKRestmengeMitPlanung;
		m_bIsEmpty = false;
	}

	public void setGesamtRestmenge(BigDecimal mBdGesamtRestmenge) {
		m_bdGesamtRestmenge = mBdGesamtRestmenge;
		m_bIsEmpty = false;
	}

	public void setGesamtRestmengeMitPlanung(
			BigDecimal mBdGesamtRestmengeMitPlanung) {
		m_bdGesamtRestmengeMitPlanung = mBdGesamtRestmengeMitPlanung;
		m_bIsEmpty = false;
	}


	
	public boolean getIsEmpty(){
		return m_bIsEmpty;
	}
	
	public BigDecimal getEKLagermenge() {
		return m_bdEKLagermenge;
	}

	public BigDecimal getEKRestmenge() {
		return m_bdEKRestmenge;
	}

	public BigDecimal getEKRestmengeMitPlanung() {
		return m_bdEKRestmengeMitPlanung;
	}

	public BigDecimal getVKLagermenge() {
		return m_bdVKLagermenge;
	}

	public BigDecimal getVKRestmenge() {
		return m_bdVKRestmenge;
	}

	public BigDecimal getVKRestmengeMitPlanung() {
		return m_bdVKRestmengeMitPlanung;
	}

	public BigDecimal getGesamtRestmenge() {
		return m_bdGesamtRestmenge;
	}

	public BigDecimal getGesamtRestmengeMitPlanung() {
		return m_bdGesamtRestmengeMitPlanung;
	}

	public void set_ID_Lager(String m_ID_Lager) {
		this.m_ID_Lager = m_ID_Lager;
	}

	public String get_ID_Lager() {
		return m_ID_Lager;
	}

	public void set_ID_Artikel(String m_ID_Artikel) {
		this.m_ID_Artikel = m_ID_Artikel;
	}

	public String get_ID_Artikel() {
		return m_ID_Artikel;
	}

}
