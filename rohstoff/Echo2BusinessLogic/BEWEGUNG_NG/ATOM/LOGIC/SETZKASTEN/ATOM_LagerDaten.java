package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN;

import java.math.BigDecimal;

/**
 * Private Klasse: Wird hier genutzt, um die Verbuchungsberechnungen 
 * Zwischenzuspeichern 
 * 
 * @author manfred
 */
public class ATOM_LagerDaten{
	

	
	String id_bewegung_atom = null;
	private String id_bewegung_vektor = null;
	private String id_bewegung = null;
	
	BigDecimal preis = null;
	BigDecimal menge = null;
	String buchungsdatum = null;
	BigDecimal verbuchteMenge = null;
	BigDecimal offeneMenge = null;
	
	
	
	public ATOM_LagerDaten(String id_atom, 
			String id_vektor, 
			String id_bewegung,
			BigDecimal preis,
			BigDecimal menge, String buchungsdatum,
			BigDecimal verbuchteMenge, BigDecimal offeneMenge) {
		super();
		this.id_bewegung_atom = id_atom;
		this.id_bewegung_vektor = id_vektor;
		this.id_bewegung = id_bewegung;
		this.preis = preis;
		this.menge = menge;
		this.buchungsdatum = buchungsdatum;
		this.verbuchteMenge = verbuchteMenge;
		this.offeneMenge = offeneMenge;
	}
	/**
	 * @return the id_lager_konto
	 */
	public String getId_bewegungsatom() {
		return id_bewegung_atom;
	}
	/**
	 * @param id_atom the id_lager_konto to set
	 */
	public void setId_bewegungsatom(String id_atom) {
		this.id_bewegung_atom = id_atom;
	}
	
	/**
	 * @param id_bewegungsvektor
	 */
	public void setId_bewegungsvektor(String id_bewegungsvektor) {
		this.id_bewegung_vektor = id_bewegungsvektor;
	}

	/**
	 * @return 
	 */
	public String getId_bewegungsvektor() {
		return id_bewegung_vektor;
	}
	
	/**
	 * @param id_bewegung the id_bewegung to set
	 */
	public void setId_bewegung(String id_bewegung) {
		this.id_bewegung = id_bewegung;
	}
	/**
	 * @return the id_bewegung
	 */
	public String getId_bewegung() {
		return id_bewegung;
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
