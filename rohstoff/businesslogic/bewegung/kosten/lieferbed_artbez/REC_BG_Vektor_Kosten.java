package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR_KOSTEN;
import panter.gmbh.indep.bibALL;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base;

public class REC_BG_Vektor_Kosten extends REC_Base {

	
	
	
	private DOString       _ID_BG_Vektor_Kosten 		= new DOString(BG_VEKTOR_KOSTEN.id_bg_vektor_kosten.fn(),null,"");
	
	private DOLong			_ID_Mandant 				= new DOLong(BG_VEKTOR_KOSTEN.id_mandant.fn());
	private DODate			_letzteAenderung 			= new DODate(BG_VEKTOR_KOSTEN.letzte_aenderung.fn());
	private DOString 		_geaendertVon 				= new DOString(BG_VEKTOR_KOSTEN.geaendert_von.fn());
	private DOString 		_erzeugtvon 				= new DOString(BG_VEKTOR_KOSTEN.erzeugt_von.fn());
	private DODate			_erzeugtam 					= new DODate(BG_VEKTOR_KOSTEN.erzeugt_am.fn());
	
	private DOString		_ID_BG_Vektor				= new DOString(BG_VEKTOR_KOSTEN.id_bg_vektor.fn(),null,"");
	private DOString		_ID_BG_Atom					= new DOString(BG_VEKTOR_KOSTEN.id_bg_atom.fn(),null,"");
	private DOString		_ID_BG_Ladung				= new DOString(BG_VEKTOR_KOSTEN.id_bg_ladung.fn(),null,"");
	
	private DOLong 			_ID_Kosten_Artbez_Lief 		= new DOLong(BG_VEKTOR_KOSTEN.id_kosten_artbez_lief.fn());
	private DOLong 			_ID_Kosten_Lieferbed_Adr 	= new DOLong(BG_VEKTOR_KOSTEN.id_kosten_lieferbed_adr.fn());
	
	private DOString    	_Pauschal 					= new DOString(BG_VEKTOR_KOSTEN.pauschal.fn());
	private DOBigDecimal 	_KostenEinzel 				= new DOBigDecimal(BG_VEKTOR_KOSTEN.kosten_einzel.fn());
	private DOBigDecimal 	_KostenGesamt 				= new DOBigDecimal(BG_VEKTOR_KOSTEN.kosten_gesamt.fn());
	
	private DOString    	_Kostentyp 					= new DOString(BG_VEKTOR_KOSTEN.kostentyp.fn());
	private DOString    	_Bemerkung 					= new DOString(BG_VEKTOR_KOSTEN.bemerkung.fn());
	
	private DOLong			_Lagervorzeichen			= new DOLong(BG_VEKTOR_KOSTEN.lagervorzeichen.fn());

	

	public REC_BG_Vektor_Kosten(){
		super("JT_BG_VEKTOR_KOSTEN");
		this.initFieldList();
	}
	
	public REC_BG_Vektor_Kosten(REC_Transport_Info oTransport,REC_BG_AtomInfo oAtom ,boolean bLadeseite ){
		this();
		
		_ID_BG_Vektor_Kosten.setValue("SEQ_BG_VEKTOR_KOSTEN.NEXTVAL");

		_ID_Mandant.setValue( bibALL.get_ID_MANDANT() );
		_erzeugtvon.setValue(bibALL.get_ID_USER());
		_erzeugtam.setValue(new java.util.Date());
		_letzteAenderung.setValue(new java.util.Date());
		_geaendertVon.setValue(bibALL.get_ID_USER());

		_ID_BG_Vektor.setValue(oTransport.get_idBGVektor());
		_ID_BG_Atom.setValue(  oAtom.get_ID_BG_Atom());
		_ID_BG_Ladung.setValue(bLadeseite ? oAtom.get_ID_BG_Ladung_Lade() : oAtom.get_ID_BG_Ladung_Ablade());
		
		
	}

	
	
	/**
	 * @return the _ID_BG_Atom_Kosten
	 */
	public DOString get_ID_BG_Vektor_Kosten() {
		return _ID_BG_Vektor_Kosten;
	}
	/**
	 * @param _ID_BG_Atom_Kosten the _ID_BG_Atom_Kosten to set
	 */
	public void set_ID_BG_Vektor_Kosten(String _ID_BG_Atom_Kosten) {
		this._ID_BG_Vektor_Kosten.setValue(_ID_BG_Atom_Kosten);
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
	 * falls das datum als Stringliteral "sysdate" übergeben wird
	 * @author manfred
	 * @date 10.10.2016
	 *
	 * @param letzteAenderung
	 */
	public void set_letzteAenderung( Date letzteAenderung)  {
		this._letzteAenderung.setValue( letzteAenderung );
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
	public DODate get_erzeugtam() {
		return _erzeugtam;
	}
//	/**
//	 * @param _erzeugtam the _erzeugtam to set
//	 */
//	public void set_erzeugtam( Date _erzeugtam) {
//		this._erzeugtam.setValue(_erzeugtam );
//	}
	
	/**
	 * Übergabe per String wie z.B. 'sysdate'
	 * @author manfred
	 * @date 10.10.2016
	 *
	 * @param _erzeugtam
	 */
	public void set_erzeugtam(Date _erzeugtam){
		this._erzeugtam.setValue(_erzeugtam);
	}

	/**
	 * @return the _ID_Bewegung_Vektor
	 */
	public DOString get_ID_Bewegung_Vektor() {
		return _ID_BG_Vektor;
	}
	/**
	 * @param _ID_BG_Vektor the _ID_Bewegung_Vektor to set
	 */
	public void set_ID_Bewegung_Vektor(String ID_Bewegung_Vektor) {
		this._ID_BG_Vektor.setValue(ID_Bewegung_Vektor );
	}
	
	/**
	 * @return the _ID_BG_Atom
	 */
	public DOString get_ID_BG_Atom() {
		return _ID_BG_Atom;
	}
	/**
	 * @param _ID_BG_Atom the _ID_BG_Atom to set
	 */
	public void set_ID_BG_Atom( String ID_BG_Atom) {
		this._ID_BG_Atom.setValue(ID_BG_Atom);
	}

	/**
	 * @return the _ID_BG_Atom
	 */
	public DOString get_ID_BG_Ladung() {
		return _ID_BG_Ladung;
	}
	/**
	 * @param _ID_BG_Atom the _ID_BG_Atom to set
	 */
	public void set_ID_BG_Ladung( String ID_BG_Ladung) {
		this._ID_BG_Ladung.setValue(ID_BG_Ladung);
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
	public void set_ID_Kosten_Artbez_Lief( Long ID_Kosten_Artbez_Lief) {
		this._ID_Kosten_Artbez_Lief.setValue( ID_Kosten_Artbez_Lief);
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
	public void set_ID_Kosten_Lieferbed_Adr( Long ID_Kosten_Lieferbed_Adr) {
		this._ID_Kosten_Lieferbed_Adr.setValue( ID_Kosten_Lieferbed_Adr);
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
	public void set_Pauschal( String Pauschal) {
		this._Pauschal.setValue( Pauschal );
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
	public void set_KostenEinzel( BigDecimal KostenEinzel) {
		this._KostenEinzel.setValue( KostenEinzel );
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
	public void set_KostenGesamt( BigDecimal KostenGesamt) {
		this._KostenGesamt.setValue( KostenGesamt );
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
	public void set_Kostentyp( String Kostentyp) {
		this._Kostentyp.setValue( Kostentyp );
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
	public void set_Bemerkung( String Bemerkung) {
		this._Bemerkung.setValue( Bemerkung );
	}
	

	public DOLong get_Lagervorzeichen() {
		return _Lagervorzeichen;
	}

	public void set_Lagervorzeichen(long _Lagervorzeichen) {
		this._Lagervorzeichen.setValue(_Lagervorzeichen);
	}
	
	

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {
		// TODO Auto-generated method stub
		m_vDataObjects.addElement ( _ID_BG_Vektor_Kosten );
		m_vDataObjects.addElement ( _ID_Mandant );
		m_vDataObjects.addElement ( _letzteAenderung );
		m_vDataObjects.addElement ( _geaendertVon );
		m_vDataObjects.addElement ( _erzeugtvon );
		m_vDataObjects.addElement ( _erzeugtam );
		m_vDataObjects.addElement ( _ID_BG_Vektor );
		m_vDataObjects.addElement ( _ID_BG_Atom  );
		m_vDataObjects.addElement ( _ID_BG_Ladung  );
		m_vDataObjects.addElement ( _ID_Kosten_Artbez_Lief );
		m_vDataObjects.addElement ( _ID_Kosten_Lieferbed_Adr  );
		m_vDataObjects.addElement ( _Pauschal );
		m_vDataObjects.addElement ( _KostenEinzel );
		m_vDataObjects.addElement ( _KostenGesamt );
		m_vDataObjects.addElement ( _Kostentyp );
		m_vDataObjects.addElement ( _Bemerkung );
		m_vDataObjects.addElement ( _Lagervorzeichen );
	}

	


}
