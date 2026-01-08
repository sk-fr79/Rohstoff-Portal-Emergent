package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;

public class LAG_LagerMengenDaten {
	private BigDecimal m_bdEKLagermenge = null;
	private BigDecimal m_bdEKRestmenge = null;
	private BigDecimal m_bdEKRestmengeMitPlanung = null;
	
	private BigDecimal m_bdVKLagermenge = null;
	private BigDecimal m_bdVKRestmenge = null;
	private BigDecimal m_bdVKRestmengeMitPlanung = null;
	
	private BigDecimal m_bdGesamtRestmenge = null;
	private BigDecimal m_bdGesamtRestmengeMitPlanung = null;

	private boolean    m_bIsEmpty = true;
	
	public LAG_LagerMengenDaten() {
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


	public LAG_LagerMengenDaten(BigDecimal mBdEKLagermenge,
			BigDecimal mBdEKRestmenge, BigDecimal mBdEKRestmengeMitPlanung,
			BigDecimal mBdVKLagermenge, BigDecimal mBdVKRestmenge,
			BigDecimal mBdVKRestmengeMitPlanung, BigDecimal mBdGesamtRestmenge,
			BigDecimal mBdGesamtRestmengeMitPlanung) {
		super();
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


	
	protected boolean getIsEmpty(){
		return m_bIsEmpty;
	}
	
	protected BigDecimal getEKLagermenge() {
		return m_bdEKLagermenge;
	}

	protected BigDecimal getEKRestmenge() {
		return m_bdEKRestmenge;
	}

	protected BigDecimal getEKRestmengeMitPlanung() {
		return m_bdEKRestmengeMitPlanung;
	}

	protected BigDecimal getVKLagermenge() {
		return m_bdVKLagermenge;
	}

	protected BigDecimal getVKRestmenge() {
		return m_bdVKRestmenge;
	}

	protected BigDecimal getVKRestmengeMitPlanung() {
		return m_bdVKRestmengeMitPlanung;
	}

	protected BigDecimal getGesamtRestmenge() {
		return m_bdGesamtRestmenge;
	}

	protected BigDecimal getGesamtRestmengeMitPlanung() {
		return m_bdGesamtRestmengeMitPlanung;
	}


}
