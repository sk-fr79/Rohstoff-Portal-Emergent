package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DOBigDecimal;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DODate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DOLong;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DOString;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.REC_Base;

public class REC_Bewegung_Atom_Kosten extends REC_Base {

	
	
	
	private DOString       _ID_Bewegung_Atom_Kosten 	= new DOString("ID_BEWEGUNG_ATOM_KOSTEN",null,"");
	
	private DOLong			_ID_Mandant 				= new DOLong("ID_MANDANT");
	private DODate 			_letzteAenderung 			= new DODate("LETZTE_AENDERUNG");
	private DOString 		_geaendertVon 				= new DOString("GEAENDERT_VON");
	private DOString 		_erzeugtvon 				= new DOString("ERZEUGT_VON");
	private DOString		_erzeugtam 					= new DOString("ERZEUGT_AM",null,"");
	
	private DOString		_ID_Bewegung_Atom			= new DOString("ID_BEWEGUNG_ATOM",null,"");
	private DOString		_ID_Bewegung_Vektor			= new DOString("ID_BEWEGUNG_VEKTOR",null,"");
	
	private DOLong 			_ID_Kosten_Artbez_Lief 		= new DOLong("ID_KOSTEN_ARTBEZ_LIEF");
	private DOLong 			_ID_Kosten_Lieferbed_Adr 	= new DOLong("ID_KOSTEN_LIEFERBED_ADR");
	
	private DOString    	_Pauschal 					= new DOString("PAUSCHAL");
	private DOBigDecimal 	_KostenEinzel 				= new DOBigDecimal("KOSTEN_EINZEL");
	private DOBigDecimal 	_KostenGesamt 				= new DOBigDecimal("KOSTEN_GESAMT");
	
	private DOString    	_Kostentyp 					= new DOString("KOSTENTYP");
	private DOString    	_Bemerkung 					= new DOString("BEMERKUNG");
	
	private DOString    	_IstWareneingang 			= new DOString("IST_WARENEINGANG");
	

	

	public REC_Bewegung_Atom_Kosten(){
		super("JT_BEWEGUNG_ATOM_KOSTEN");
	}
	
	public REC_Bewegung_Atom_Kosten(String sIDBewegungVektor, String sIDBewegungAtom){
		this();
		
		_erzeugtvon.setValue(bibALL.get_ID_USER());
		_erzeugtam.setValue("SYSDATE");
		_letzteAenderung.setValue(new Date());
		_geaendertVon.setValue(bibALL.get_ID_USER());

		_ID_Mandant.setValue( bibALL.get_ID_MANDANT() );
		_ID_Bewegung_Atom.setValue(sIDBewegungAtom);
		_ID_Bewegung_Vektor.setValue(sIDBewegungVektor);
		
		_ID_Bewegung_Atom_Kosten.setValue("SEQ_BEWEGUNG_ATOM_KOSTEN.NEXTVAL");
		
	}

	
	/**
	 * @return the _ID_Bewegung_Atom_Kosten
	 */
	public DOString get_ID_Bewegung_Atom_Kosten() {
		return _ID_Bewegung_Atom_Kosten;
	}
	/**
	 * @param _ID_Bewegung_Atom_Kosten the _ID_Bewegung_Atom_Kosten to set
	 */
	public void set_ID_Bewegung_Atom_Kosten(String _ID_Bewegung_Atom_Kosten) {
		this._ID_Bewegung_Atom_Kosten.setValue(_ID_Bewegung_Atom_Kosten);
	}
	/**
	 * @return the _ID_Mandant
	 */
	public DOLong get_ID_Mandant() {
		return _ID_Mandant;
	}
	/**
	 * @param _ID_Mandant the _ID_Mandant to set
	 */
	public void set_ID_Mandant(Long _ID_Mandant) {
		this._ID_Mandant.setValue(_ID_Mandant);
	}
	/**
	 * @return the _letzteAenderung
	 */
	public DODate get_letzteAenderung() {
		return _letzteAenderung;
	}
	/**
	 * @param _letzteAenderung the _letzteAenderung to set
	 */
	public void set_letzteAenderung( Date _letzteAenderung) {
		this._letzteAenderung.setValue( _letzteAenderung );
	}
	
	/**
	 * falls das datum als Stringliteral "sysdate" übergeben wird
	 * @author manfred
	 * @date 10.10.2016
	 *
	 * @param letzteAenderung
	 */
	public void set_letzteAenderung( String letzteAenderung)  {
		this._letzteAenderung.setValue( letzteAenderung,true );
	}
	
	/**
	 * @return the _geaendertVon
	 */
	public DOString get_geaendertVon() {
		return _geaendertVon;
	}
	/**
	 * @param _geaendertVon the _geaendertVon to set
	 */
	public void set_geaendertVon( String _geaendertVon) {
		this._geaendertVon.setValue( _geaendertVon);
	}
	/**
	 * @return the _erzeugtvon
	 */
	public DOString get_erzeugtvon() {
		return _erzeugtvon;
	}
	/**
	 * @param _erzeugtvon the _erzeugtvon to set
	 */
	public void set_erzeugtvon( String _erzeugtvon) {
		this._erzeugtvon.setValue( _erzeugtvon);
	}
	/**
	 * @return the _erzeugtam
	 */
	public DOString get_erzeugtam() {
		return _erzeugtam;
	}
	/**
	 * @param _erzeugtam the _erzeugtam to set
	 */
	public void set_erzeugtam( Date _erzeugtam) {
		this._erzeugtam.setValue( bibALL.makeSQL(_erzeugtam, true) );
	}
	
	/**
	 * Übergabe per String wie z.B. 'sysdate'
	 * @author manfred
	 * @date 10.10.2016
	 *
	 * @param _erzeugtam
	 */
	public void set_erzeugtam(String _erzeugtam){
		this._erzeugtam.setValue(_erzeugtam);
	}
	
	/**
	 * @return the _ID_Bewegung_Atom
	 */
	public DOString get_ID_Bewegung_Atom() {
		return _ID_Bewegung_Atom;
	}
	/**
	 * @param _ID_Bewegung_Atom the _ID_Bewegung_Atom to set
	 */
	public void set_ID_Bewegung_Atom( String _ID_Bewegung_Atom) {
		this._ID_Bewegung_Atom.setValue(_ID_Bewegung_Atom);
	}
	/**
	 * @return the _ID_Bewegung_Vektor
	 */
	public DOString get_ID_Bewegung_Vektor() {
		return _ID_Bewegung_Vektor;
	}
	/**
	 * @param _ID_Bewegung_Vektor the _ID_Bewegung_Vektor to set
	 */
	public void set_ID_Bewegung_Vektor(String _ID_Bewegung_Vektor) {
		this._ID_Bewegung_Vektor.setValue(_ID_Bewegung_Vektor );
	}
	/**
	 * @return the _ID_Kosten_Artbez_Lief
	 */
	public DOLong get_ID_Kosten_Artbez_Lief() {
		return _ID_Kosten_Artbez_Lief;
	}
	/**
	 * @param _ID_Kosten_Artbez_Lief the _ID_Kosten_Artbez_Lief to set
	 */
	public void set_ID_Kosten_Artbez_Lief( Long _ID_Kosten_Artbez_Lief) {
		this._ID_Kosten_Artbez_Lief.setValue( _ID_Kosten_Artbez_Lief);
	}
	/**
	 * @return the _ID_Kosten_Lieferbed_Adr
	 */
	public DOLong get_ID_Kosten_Lieferbed_Adr() {
		return _ID_Kosten_Lieferbed_Adr;
	}
	/**
	 * @param _ID_Kosten_Lieferbed_Adr the _ID_Kosten_Lieferbed_Adr to set
	 */
	public void set_ID_Kosten_Lieferbed_Adr( Long _ID_Kosten_Lieferbed_Adr) {
		this._ID_Kosten_Lieferbed_Adr.setValue( _ID_Kosten_Lieferbed_Adr);
	}
	/**
	 * @return the _Pauschal
	 */
	public DOString get_Pauschal() {
		return _Pauschal;
	}
	/**
	 * @param _Pauschal the _Pauschal to set
	 */
	public void set_Pauschal( String _Pauschal) {
		this._Pauschal.setValue( _Pauschal );
	}
	/**
	 * @return the _KostenEinzel
	 */
	public DOBigDecimal get_KostenEinzel() {
		return _KostenEinzel;
	}
	/**
	 * @param _KostenEinzel the _KostenEinzel to set
	 */
	public void set_KostenEinzel( BigDecimal _KostenEinzel) {
		this._KostenEinzel.setValue( _KostenEinzel );
	}
	/**
	 * @return the _KostenGesamt
	 */
	public DOBigDecimal get_KostenGesamt() {
		return _KostenGesamt;
	}
	/**
	 * @param _KostenGesamt the _KostenGesamt to set
	 */
	public void set_KostenGesamt( BigDecimal _KostenGesamt) {
		this._KostenGesamt.setValue( _KostenGesamt );
	}
	/**
	 * @return the _Kostentyp
	 */
	public DOString get_Kostentyp() {
		return _Kostentyp;
	}
	/**
	 * @param _Kostentyp the _Kostentyp to set
	 */
	public void set_Kostentyp( String _Kostentyp) {
		this._Kostentyp.setValue( _Kostentyp );
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
	public void set_Bemerkung( String _Bemerkung) {
		this._Bemerkung.setValue( _Bemerkung );
	}
	
	public DOString get_IstWareneingang() {
		return _IstWareneingang;
	}

	public void set_IstWareneingang(String _IstWareneingang) {
		this._IstWareneingang.setValue(_IstWareneingang);
	}
	
	
	@Override
	public void AddStatements() {
		AddStatement(_ID_Bewegung_Atom_Kosten);
		AddStatement(_Bemerkung);
		AddStatement(_ID_Bewegung_Atom);
		AddStatement(_ID_Bewegung_Vektor);
		
		AddStatement(_ID_Kosten_Artbez_Lief);
		AddStatement(_ID_Kosten_Lieferbed_Adr);
		AddStatement(_KostenEinzel);
		AddStatement(_KostenGesamt);
		AddStatement(_Kostentyp);
		AddStatement(_Pauschal);

		AddStatement(_geaendertVon);
		AddStatement(_letzteAenderung);
		AddStatement(_erzeugtam );
		AddStatement(_erzeugtvon);
		AddStatement(_ID_Mandant);
		
		AddStatement(_IstWareneingang);
		
	}
	
	
	
	


}
