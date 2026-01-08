/**
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;

import panter.gmbh.indep.myDateHelper;

/**
 * @author manfred
 * Ein Lagerbuchungs-Satz enthält alle Informationen um eine Lagerbuchung durchzuführen.
 * Ein oder mehrere Buchungssatz werden an den LagerHandler übergeben
 * 
 * eine ID die bei der Umbuchung geschrieben wird, damit man die zusammengehoerigen Saetze besser ermitteln kann.
 * 
 */

public class LAG_LagerBuchungsSatz {

	private String _id_lager_konto = "";
	private String _id_adresse_lager = "";
	private String _id_artikel_sorte = "";
	private BigDecimal _preis = BigDecimal.ZERO;
	private BigDecimal _menge =  BigDecimal.ZERO;
	private String _buchungsdatum = "";
	private String _buchungszeit = "";
	private String _buchungstyp = "";
	private String _id_vpos_tpa_fuhre = "";
	private String _id_vpos_tpa_fuhre_ort = "";
	private String _storno = "";
	private String _id_lager_konto_parent = "";
	private String _ist_komplett = "";
	private String _bemerkung = "";
	private String _buchung_hand = "";
	private String _status_preis = "";
	private String _id_umbuchung = "";
	
	private BigDecimal _menge_buchhalterisch = null;
	
	

	/**
	 * Konstruktor 
	 * @param _id_adresse_lager
	 * @param _id_artikel_sorte
	 * @param _preis
	 * @param _menge
	 * @param _buchungsdatum
	 * @param _buchungstyp
	 * @param _id_vpos_tpa_fuhre
	 * @param _id_vpos_tpa_fuhre_ort
	 * @param _bemerkung TODO
	 */
	public LAG_LagerBuchungsSatz(String _id_adresse_lager, String _id_artikel_sorte,
			BigDecimal _preis, BigDecimal _menge, String _buchungsdatum,
			String _buchungszeit,
			String _buchungstyp, String _id_vpos_tpa_fuhre,
			String _id_vpos_tpa_fuhre_ort, String _bemerkung, String _buchung_hand, String _status_preis) {
		super();
	
		this._id_adresse_lager = _id_adresse_lager;
		this._id_artikel_sorte = _id_artikel_sorte;
		this._preis = _preis;
		this._menge = _menge;
		this._buchungsdatum = _buchungsdatum;
		this._buchungszeit = _buchungszeit;
		this._buchungstyp = _buchungstyp;
		this._id_vpos_tpa_fuhre = _id_vpos_tpa_fuhre;
		this._id_vpos_tpa_fuhre_ort = _id_vpos_tpa_fuhre_ort;
		this._bemerkung = _bemerkung;
		this._buchung_hand = _buchung_hand;
		this._status_preis = _status_preis;
	}
	
	
	/**
	 * Konstruktor 
	 * @param _id_adresse_lager
	 * @param _id_artikel_sorte
	 * @param _preis
	 * @param _menge
	 * @param _buchungsdatum
	 * @param _buchungstyp
	 * @param _id_vpos_tpa_fuhre
	 * @param _id_vpos_tpa_fuhre_ort
	 * @param _bemerkung TODO
	 * 
	 */
	public LAG_LagerBuchungsSatz(String _id_adresse_lager, String _id_artikel_sorte,
			BigDecimal _preis, BigDecimal _menge, String _buchungsdatum,
			String _buchungszeit,
			String _buchungstyp, String _id_vpos_tpa_fuhre,
			String _id_vpos_tpa_fuhre_ort, String _bemerkung, String _buchung_hand, String _status_preis, String _id_umbuchung) {
		super();
	
		this._id_adresse_lager = _id_adresse_lager;
		this._id_artikel_sorte = _id_artikel_sorte;
		this._preis = _preis;
		this._menge = _menge;
		this._buchungsdatum = _buchungsdatum;
		this._buchungszeit = _buchungszeit;
		this._buchungstyp = _buchungstyp;
		this._id_vpos_tpa_fuhre = _id_vpos_tpa_fuhre;
		this._id_vpos_tpa_fuhre_ort = _id_vpos_tpa_fuhre_ort;
		this._bemerkung = _bemerkung;
		this._buchung_hand = _buchung_hand;
		this._status_preis = _status_preis;
		this._id_umbuchung = _id_umbuchung;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return new LAG_LagerBuchungsSatz(
//				this._id_adresse_lager,
//				this._id_artikel_sorte,
//				this._preis,
//				this._menge,
//				this._buchungsdatum,
//				this._buchungszeit,
//				this._buchungstyp,
//				this._id_vpos_tpa_fuhre, 
//				this._id_vpos_tpa_fuhre_ort, 
//				this._bemerkung,
//				this._buchung_hand,
//				this._status_preis,
//				this._id_umbuchung);
//	}




	public String get_id_lager_konto() {
		return _id_lager_konto;
	}
	public void set_id_lager_konto(String _id_lager_konto) {
		this._id_lager_konto = _id_lager_konto;
	}
	
	public String get_id_adresse_lager() {
		return _id_adresse_lager;
	}
	public void set_id_adresse_lager(String _id_adresse_lager) {
		this._id_adresse_lager = _id_adresse_lager;
	}

	public String get_id_artikel_sorte() {
		return _id_artikel_sorte;
	}
	public void set_id_artikel_sorte(String _id_artikel_sorte) {
		this._id_artikel_sorte = _id_artikel_sorte;
	}
	
	public BigDecimal get_preis() {
		return _preis;
	}
	public void set_preis(BigDecimal _preis) {
		this._preis = _preis;
	}
	
	public BigDecimal get_menge() {
		return _menge;
	}
	public void set_menge(BigDecimal _menge) {
		this._menge = _menge;
	}
	
	public String get_buchungsdatum_formatted(){
		String sRet = null;
		if (_buchungsdatum != null){
			sRet = myDateHelper.ChangeDBFormatStringToNormalString(_buchungsdatum);
		}
		return sRet;
	}
	
	public String get_buchungsdatum() {
		return _buchungsdatum;
	}
	public void set_buchungsdatum(String _buchungsdatum) {
		this._buchungsdatum = _buchungsdatum;
	}
	
	public String get_buchungstyp() {
		return _buchungstyp;
	}
	public void set_buchungstyp(String _buchungstyp) {
		this._buchungstyp = _buchungstyp;
	}
	
	public String get_id_vpos_tpa_fuhre() {
		return _id_vpos_tpa_fuhre;
	}
	public void set_id_vpos_tpa_fuhre(String _id_vpos_tpa_fuhre) {
		this._id_vpos_tpa_fuhre = _id_vpos_tpa_fuhre;
	}
	
	public String get_id_vpos_tpa_fuhre_ort() {
		return _id_vpos_tpa_fuhre_ort;
	}
	public void set_id_vpos_tpa_fuhre_ort(String _id_vpos_tpa_fuhre_ort) {
		this._id_vpos_tpa_fuhre_ort = _id_vpos_tpa_fuhre_ort;
	}
	
	public String get_storno() {
		return _storno;
	}
	public void set_storno(String _storno) {
		this._storno = _storno;
	}
	
	public String get_id_lager_konto_parent() {
		return _id_lager_konto_parent;
	}
	public void set_id_lager_konto_parent(String _id_lager_konto_parent) {
		this._id_lager_konto_parent = _id_lager_konto_parent;
	}
	
	public String get_ist_komplett() {
		return _ist_komplett;
	}
	public void set_ist_komplett(String _ist_komplett) {
		this._ist_komplett = _ist_komplett;
	}

	public void set_bemerkung(String _bemerkung) {
		this._bemerkung = _bemerkung;
	}

	public String get_bemerkung() {
		return _bemerkung;
	}

	
	public void set_buchungszeit(String _buchungszeit) {
		this._buchungszeit = _buchungszeit;
	}

	public String get_buchungszeit() {
		return _buchungszeit;
	}
	
	public String get_buchung_hand() {
		return _buchung_hand;
	}
	
	public void set_buchung_hand(String _buchung_hand) {
		this._buchung_hand = _buchung_hand;
	}


	/**
	 * @param _status_preis the _status_preis to set
	 */
	public void set_status_preis(String _status_preis) {
		this._status_preis = _status_preis;
	}


	/**
	 * @return the _status_preis
	 */
	public String get_status_preis() {
		return _status_preis;
	}


	/**
	 * @param _id_umbuchung the _id_umbuchung to set
	 */
	public void set_id_umbuchung(String _id_umbuchung) {
		this._id_umbuchung = _id_umbuchung;
	}


	/**
	 * @return the _id_umbuchung
	 */
	public String get_id_umbuchung() {
		return _id_umbuchung;
	}


	/**
	 *  @param _menge_buchhalterisch
	 */
	public void set_menge_buchhalterisch(BigDecimal _menge_buchhalterisch) {
		this._menge_buchhalterisch = _menge_buchhalterisch;
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal get_menge_buchhalterisch() {
		return _menge_buchhalterisch;
	}

}
