package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;

import panter.gmbh.indep.bibALL;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOString;


public class REC_Kosten_Artikelbez   {

	
	public REC_Kosten_Artikelbez() {
	}
	
	private DOLong 		_ID_Kosten_Artbez_Lief = new DOLong("");
	private DOLong    	_ID_Adresse = new DOLong("ID_Adresse");
	private DOString    	_Pauschal = new DOString("Pauschal");
	
	private DOString 		_Bezeichnung = new DOString("Bezeichnung");
	private DOString  		_Bemerkung = new DOString("Bemerkung");
	
	private DOBigDecimal 	_Betrag = new DOBigDecimal("Betrag");
	private DOBigDecimal 	_BetragStrecke = new DOBigDecimal("BetragStrecke");
	
	
	/**
	 * @return the _ID_Kosten_Artbez_Lief
	 */
	public DOLong get_ID_Kosten_Artbez_Lief() {
		return _ID_Kosten_Artbez_Lief;
	}
	/**
	 * @param _ID_Kosten_Artbez_Lief the _ID_Kosten_Artbez_Lief to set
	 */
	public void set_ID_Kosten_Artbez_Lief(String _ID_Kosten_Artbez_Lief) {
		this._ID_Kosten_Artbez_Lief.setValue(_ID_Kosten_Artbez_Lief);
	}
	/**
	 * @return the _ID_Adresse
	 */
	public DOLong get_ID_Adresse() {
		return _ID_Adresse;
	}
	/**
	 * @param _ID_Adresse the _ID_Adresse to set
	 */
	public void set_ID_Adresse(String _ID_Adresse) {
		this._ID_Adresse.setValue(_ID_Adresse);
	}
	/**
	 * @return the _Pauschal
	 */
	public DOString get_Pauschal() {
		return _Pauschal;
	}

	/**
	 * Setzt den Schalter von Y/N auf true/false
	 * @author manfred
	 * @date   22.11.2013
	 * @return
	 */
	public boolean is_Pauschal() {
		if (!bibALL.isEmpty(_Pauschal.Value())){
			return _Pauschal.ValuePlain().equalsIgnoreCase("Y");
		} else{
			return false;
		}
	}
	
	/**
	 * @param _Pauschal the _Pauschal to set
	 */
	public void set_Pauschal(String _Pauschal) {
		this._Pauschal.setValue( _Pauschal );
	}
	/**
	 * @return the _Bezeichnung
	 */
	public DOString get_Bezeichnung() {
		return _Bezeichnung;
	}
	/**
	 * @param _Bezeichnung the _Bezeichnung to set
	 */
	public void set_Bezeichnung(String _Bezeichnung) {
		this._Bezeichnung.setValue(_Bezeichnung);
	}
	/**
	 * @return the _Bemerkung
	 */
	public DOString get_Bemerkung() {
		return _Bemerkung;
	}
	/**
	 * @param _Bemerkung the _Bemerkung to set
	 */
	public void set_Bemerkung(String _Bemerkung) {
		this._Bemerkung.setValue(_Bemerkung);
	}
	/**
	 * @return the _Betrag
	 */
	public DOBigDecimal get_Betrag() {
		return _Betrag;
	}
	/**
	 * @param _Betrag the _Betrag to set
	 */
	public void set_Betrag(BigDecimal _Betrag) {
		this._Betrag.setValue(_Betrag);
	}
	/**
	 * @return the _BetragStrecke
	 */
	public DOBigDecimal get_BetragStrecke() {
		return _BetragStrecke;
	}
	/**
	 * @param _BetragStrecke the _BetragStrecke to set
	 */
	public void set_BetragStrecke(BigDecimal _BetragStrecke) {
		this._BetragStrecke.setValue(_BetragStrecke);
	}
	

	

	
	
}
