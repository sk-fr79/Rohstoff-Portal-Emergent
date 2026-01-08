package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;

/**
 * Private Klasse: Wird hier genutzt, um die Verbuchungsberechnungen 
 * Zwischenzuspeichern 
 * 
 * @author manfred
 */
public class LagerDaten{
	

	
	String id_lager_konto;
	BigDecimal preis;
	BigDecimal menge;
	String buchungsdatum;
	BigDecimal verbuchteMenge;
	BigDecimal offeneMenge;
	
	
	
	public LagerDaten(String id_lager_konto, BigDecimal preis,
			BigDecimal menge, String buchungsdatum,
			BigDecimal verbuchteMenge, BigDecimal offeneMenge) {
		super();
		this.id_lager_konto = id_lager_konto;
		this.preis = preis;
		this.menge = menge;
		this.buchungsdatum = buchungsdatum;
		this.verbuchteMenge = verbuchteMenge;
		this.offeneMenge = offeneMenge;
	}
	/**
	 * @return the id_lager_konto
	 */
	public String getId_lager_konto() {
		return id_lager_konto;
	}
	/**
	 * @param id_lager_konto the id_lager_konto to set
	 */
	public void setId_lager_konto(String id_lager_konto) {
		this.id_lager_konto = id_lager_konto;
	}
	/**
	 * @return the preis
	 */
	public BigDecimal getPreis() {
		return preis;
	}
	/**
	 * @param preis the preis to set
	 */
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	/**
	 * @return the menge
	 */
	public BigDecimal getMenge() {
		return menge;
	}
	/**
	 * @param menge the menge to set
	 */
	public void setMenge(BigDecimal menge) {
		this.menge = menge;
	}
	/**
	 * @return the buchungsdatum
	 */
	public String getBuchungsdatum() {
		return buchungsdatum;
	}
	/**
	 * @param buchungsdatum the buchungsdatum to set
	 */
	public void setBuchungsdatum(String buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}
	/**
	 * @return the verbuchteMenge
	 */
	public BigDecimal getVerbuchteMenge() {
		return verbuchteMenge;
	}
	/**
	 * @param verbuchteMenge the verbuchteMenge to set
	 */
	public void setVerbuchteMenge(BigDecimal verbuchteMenge) {
		this.verbuchteMenge = verbuchteMenge;
	}
	/**
	 * @return the offeneMenge
	 */
	public BigDecimal getOffeneMenge() {
		return offeneMenge;
	}
	/**
	 * @param offeneMenge the offeneMenge to set
	 */
	public void setOffeneMenge(BigDecimal offeneMenge) {
		this.offeneMenge = offeneMenge;
	}

	
}
