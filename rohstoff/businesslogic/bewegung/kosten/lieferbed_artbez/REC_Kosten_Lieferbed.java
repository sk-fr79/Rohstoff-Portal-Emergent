package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;

import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DOBigDecimal;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DOLong;

public class REC_Kosten_Lieferbed {

	private DOLong   	_ID_Kosten_Lieferbed_Adr 	= new DOLong("ID_KOSTEN_LIEFERBED_ADR");
	private DOLong 		_ID_Adresse 				= new DOLong("ID_ADRESSE");
	private DOLong     _ID_Adresse_Ziel 			= new DOLong("ID_ADRESSE_ZIEL");
	private DOLong     _ID_Artikel 					= new DOLong("ID_ARTIKEL");
	private DOBigDecimal 	_Betrag_Kosten 			=  new DOBigDecimal("BETRAG_KOSTEN");
	
	
	
	/**
	 * @return the _ID_Kosten_Lieferbed_Adr
	 */
	public DOLong get_ID_Kosten_Lieferbed_Adr() {
		return _ID_Kosten_Lieferbed_Adr;
	}
	/**
	 * @param _ID_Kosten_Lieferbed_Adr the _ID_Kosten_Lieferbed_Adr to set
	 */
	public void set_ID_Kosten_Lieferbed_Adr(String _ID_Kosten_Lieferbed_Adr) {
		this._ID_Kosten_Lieferbed_Adr.setValue( _ID_Kosten_Lieferbed_Adr );
	}
	/**
	 * @return the _ID_ADresse
	 */
	public DOLong get_ID_Adresse() {
		return _ID_Adresse;
	}
	/**
	 * @param _ID_ADresse the _ID_ADresse to set
	 */
	public void set_ID_Adresse(String _ID_ADresse) {
		this._ID_Adresse.setValue(_ID_ADresse);
	}
	/**
	 * @return the _ID_ADresse_Lager
	 */
	public DOLong get_ID_Adresse_Ziel() {
		return _ID_Adresse_Ziel;
	}
	/**
	 * @param _ID_ADresse the _ID_ADresse to set
	 */
	public void set_ID_Adresse_Ziel(String _ID_ADresse_Ziel) {
		this._ID_Adresse_Ziel.setValue(_ID_ADresse_Ziel);
	}
	/**
	 * @return the _ID_Artikel
	 */
	public DOLong get_ID_Artikel() {
		return _ID_Artikel;
	}
	/**
	 * @param _ID_Artikel the _ID_Artikel to set
	 */
	public void set_ID_Artikel(String _ID_Artikel) {
		this._ID_Artikel.setValue(_ID_Artikel);
	}
	
	
	/**
	 * @return the _BetragWE
	 */
	public DOBigDecimal get_Betrag_Kosten() {
		return _Betrag_Kosten;
	}
	/**
	 * @param _BetragWE the _BetragWE to set
	 */
	public void set_Betrag_Kosten(BigDecimal _Betrag_Kosten) {
		this._Betrag_Kosten.setValue(_Betrag_Kosten);
	}

	
	
	
	
}
