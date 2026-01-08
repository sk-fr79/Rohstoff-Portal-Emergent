package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.EN_ATOM_TYP;


/**
 * 
 * @author manfred
 * @date 16.04.2018
 *
 */
public class REC_BG_AtomInfo {

	private String 		_ID_BG_Vektor = null;
	private String 		_ID_BG_Atom = null;
	
	private String 		_ID_BG_Ladung_Lade = null;
	private String 		_IDAdresse_Lade = null;
	private String 		_IDAdresse_Basis_Lade = null;
	private String 		_IDAdresse_Besitzer_Lade = null;
	private String 		_IDArtikel_Lade = null;
	private String 		_IDArtikelBez_Lade = null;
	private BigDecimal 	_MengenDivisor_Lade = null;
	private BigDecimal 	_Menge_Lade = null;
	private BigDecimal 	_MengeAbrechnungseinheit_Lade = null;
	private BigDecimal 	_MengeNetto_Lade  = null;
	private String 		_IDEinheit_Menge_Lade = null;
	private String 		_IDEinheit_Preis_Lade = null;
	private String		_ID_BG_Station_lade = null;
	
	private String 		_ID_BG_Ladung_Ablade = null;
	private String 		_IDAdresse_Ablade = null;
	private String 		_IDAdresse_Basis_Ablade = null;
	private String 		_IDAdresse_Besitzer_Ablade = null;
	private String 		_IDArtikel_Ablade = null;
	private String 		_IDArtikelBez_Ablade = null;
	private BigDecimal 	_MengenDivisor_Ablade = null;
	private BigDecimal 	_Menge_Ablade = null;
	private BigDecimal 	_MengeAbrechnungseinheit_Ablade = null;
	private BigDecimal 	_MengeNetto_Ablade  = null;
	private String 		_IDEinheit_Menge_Ablade = null;
	private String 		_IDEinheit_Preis_Ablade = null;
	private String 		_ID_BG_Station_ablade = null;
	
	private String 		_IDLieferbedingung = null;
	private String 		_EN_VektorTyp = null;

	
	private BigDecimal  _bdFaktorLBWE = BigDecimal.ZERO;
	private BigDecimal	_bdFaktorLBWA = BigDecimal.ZERO;

	
	static final String sSelectBGATOMInfo  = " SELECT" +
			"  A.ID_BG_VEKTOR " +
			" ,A.ID_BG_ATOM " +
			
			" ,L1.ID_BG_LADUNG as id_bg_ladung_laden " +
			" ,S1.ID_ADRESSE AS ID_ADRESSE_LADEN   " +
			" ,S1.ID_ADRESSE_BASIS AS ID_ADRESSE_BASIS_LADEN   " +
			" ,L1.ID_ADRESSE_BESITZER AS ID_ADRESSE_BESITZER_LADEN  " +
			" ,L1.ID_ARTIKEL as ID_ARTIKEL_LADEN " +
			" ,L1.ID_ARTIKEL_BEZ as ID_ARTIKEL_BEZ_LADEN " +
			" ,ART1.MENGENDIVISOR as MENGENDIVISOR_LADEN " + 
		    " ,nvl(L1.MENGE,0) as menge_laden" +
		    " ,nvl(L1.MENGE,0) / art1.MENGENDIVISOR as menge_div_laden " +
		    " ,NVL(L1.MENGE_NETTO, nvl(L1.MENGE,0)) as menge_netto_laden " + 
		    " ,ART1.ID_EINHEIT as id_einheit_laden " +
		    " ,ART1.ID_EINHEIT_PREIS as id_einheit_preis_laden " +
		    " ,S1.ID_BG_STATION " +
		    
		    " ,L2.ID_BG_LADUNG as id_bg_ladung_abladen " +
		    " ,S2.ID_ADRESSE AS ID_ADRESSE_ABADEN   " +
			" ,S2.ID_ADRESSE_BASIS AS ID_ADRESSE_BASIS_ABLADEN   " +
			" ,L2.ID_ADRESSE_BESITZER AS ID_ADRESSE_BESITZER_ABLADEN  " +
			" ,L2.ID_ARTIKEL as ID_ARTIKEL_ABLADEN " +
			" ,L2.ID_ARTIKEL_BEZ as ID_ARTIKEL_BEZ_ABLADEN " +
			" ,ART2.MENGENDIVISOR as MENGENDIVISOR_ABLADEN " + 
		    " ,nvl(L2.MENGE,0) as menge_abladen" +
		    " ,nvl(L2.MENGE,0) / art2.MENGENDIVISOR as menge_div_abladen " +
		    " ,NVL(L2.MENGE_NETTO, nvl(L1.MENGE,0)) as menge_netto_abladen " + 
		    " ,ART2.ID_EINHEIT as id_einheit_abladen " +
		    " ,ART2.ID_EINHEIT_PREIS as id_einheit_preis_abladen " +
			" ,S2.ID_BG_STATION " +
			
			
//	        ", nvl(L4.ID_BG_LADUNG,L2.id_BG_LADUNG) as id_bg_ladung_abladen " +
//	        ", nvl(S4.ID_ADRESSE,S2.ID_ADRESSE) as id_adresse_abladen " +
//	        ", nvl(S4.ID_ADRESSE_BASIS,S2.ID_ADRESSE_BASIS) as id_adresse_basis_abladen " +         
//	        ", nvl(L4.ID_ADRESSE_BESITZER,L2.ID_ADRESSE_BESITZER) as id_adresse_besitzer_abladen " +
//	        ", nvl(L4.ID_ARTIKEL,L2.ID_ARTIKEL) as id_artikel_abladen " +
//	        ", nvl(L4.ID_ARTIKEL_BEZ, L2.ID_ARTIKEL_BEZ) as id_artikel_bez_laden " +
//	        ", nvl(ART4.MENGENDIVISOR,ART2.MENGENDIVISOR) as Mengendivisor_abladen " +
//	        ", nvl(L4.MENGE,L2.MENGE) as menge_abladen " +
//	        ", nvl2(L4.MENGE, (L4.MENGE / art4.MENGENDIVISOR),(L2.MENGE / art2.MENGENDIVISOR)) as menge_div_abladen " +
//	        ", nvl(L4.MENGE_NETTO, L2.MENGE_netto) as menge_netto_abladen " +
//	        ", nvl(ART4.ID_EINHEIT,ART2.ID_EINHEIT) as id_einheit_abladen " +
//	        ", nvl(ART4.ID_EINHEIT_PREIS,art2.id_einheit_preis) as id_einheit_preis_abladen " +
//	        ", nvl(A2.ID_LIEFERBEDINGUNGEN,A.ID_LIEFERBEDINGUNGEN) as id_lieferbedingungen_ablade " +
//			
			" ,A.ID_LIEFERBEDINGUNGEN " +
			" ,V.EN_VEKTOR_TYP " +
			
		    
			" FROM " + bibE2.cTO() + ".JT_BG_VEKTOR V " + 
			" INNER JOIN " + bibE2.cTO() + ".JT_BG_ATOM A   	ON  V.ID_BG_VEKTOR = A.ID_BG_VEKTOR" +
			
			" INNER JOIN " + bibE2.cTO() + ".JT_BG_LADUNG L1 	ON  A.ID_BG_ATOM = L1.ID_BG_ATOM AND L1.MENGENVORZEICHEN = -1 " + 
			" INNER JOIN " + bibE2.cTO() + ".JT_BG_STATION S1   ON  L1.ID_BG_STATION = S1.ID_BG_STATION  " +
			" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART1    ON  L1.ID_ARTIKEL = ART1.ID_ARTIKEL  " +
			
			" INNER JOIN " + bibE2.cTO() + ".JT_BG_LADUNG L2    ON  A.ID_BG_ATOM = L2.ID_BG_ATOM AND L2.MENGENVORZEICHEN = +1   " +
			" INNER JOIN " + bibE2.cTO() + ".JT_BG_STATION S2   ON  L2.ID_BG_STATION = S2.ID_BG_STATION  " +
			" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART2    ON  L2.ID_ARTIKEL = ART2.ID_ARTIKEL  "  +
			
//			" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BG_LADUNG L3 ON S2.ID_BG_STATION = L3.ID_BG_STATION and L3.MENGENVORZEICHEN = -1 "  +
//			" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BG_ATOM  A2  ON A2.ID_BG_ATOM = L3.ID_BG_ATOM  "  +
//			" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BG_LADUNG L4 ON A2.ID_BG_ATOM = L4.ID_BG_ATOM AND L4.MENGENVORZEICHEN = +1 "  + 
//			" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BG_STATION S4        ON  L4.ID_BG_STATION = S4.ID_BG_STATION "  +
//			" LEFT OUTER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART4         ON  L4.ID_ARTIKEL = ART4.ID_ARTIKEL "  +
			
			" WHERE A.EN_ATOM_TYP = '" + EN_ATOM_TYP.HAUPTATOM.name() + "' "  ;	

	
	/**
	 * Konstruktor mit allen Feldern
	 * @author manfred
	 * @date   03.12.2013
	 * @param ID_BG_Atom
	 * @param IDAdresse_Lade
	 * @param IDAdresse_Ablade
	 * @param IDBesitzer_Lade
	 * @param IDBesitzer_Ablade
	 * @param IDArtikel
	 * @param IDArtikelBez
	 * @param IDLieferbedingungen
	 * @param IDEinheit_Preis
	 * @param MengeBrutto
	 * @param MengeAbrechnungseinheit
	 * @param MengeNetto
	 * @param IDEinheit_Menge
	 * @param _IDBewegung
	 * @param MengenDivisor
	 * 	
	 */
//	public REC_BG_AtomInfo(String ID_BG_Vektor,String ID_BG_Atom, 
//			String IDAdresse_Lade,	String IDAdresse_Ablade, String IDBesitzer_Lade,
//			String IDBesitzer_Ablade, String IDArtikel, String IDArtikelBez,
//			String IDLieferbedingungen, String IDEinheit_Preis,
//			BigDecimal MengeBrutto, BigDecimal MengeAbrechnungseinheit, 
//			BigDecimal MengeNetto, String IDEinheit_Menge, 
//			BigDecimal MengenDivisor,
//			String Variante
//			) {
//		super();
//		
//	
//		
//		this._ID_BG_Vektor = ID_BG_Vektor;
//		this._ID_BG_Atom = ID_BG_Atom;
//		this._IDAdresse_Lade = IDAdresse_Lade;
//		this._IDAdresse_Ablade = IDAdresse_Ablade;
//		
//		
//		this._IDAdresse_Besitzer_Lade = IDBesitzer_Lade;
//		this._IDAdresse_Besitzer_Ablade = IDBesitzer_Ablade;
//		this._IDArtikel_Lade = IDArtikel;
//		this._IDArtikelBez_Lade = IDArtikelBez;
//		this._IDLieferbedingungen = IDLieferbedingungen;
//		this._IDEinheit_Preis_Lade = IDEinheit_Preis;
//		this._Menge_Lade = MengeBrutto;
//		this._MengeAbrechnungseinheit_Lade = MengeAbrechnungseinheit;
//		this._MengeNetto_Lade = MengeNetto;
//		this._IDEinheit_Menge_Lade = IDEinheit_Menge;
//		this._MengenDivisor_Lade = MengenDivisor;
//		
//
//	}

	/**
	 * Default-Konstruktor mit leeren Feldern
	 * @author manfred
	 * @date   03.12.2013
	 */
	public REC_BG_AtomInfo() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * Konstruktor
	 * Füllt die Felder aktiv mit den Inhalten der Daten des Atoms sIDBewegungsAtom
	 * @author manfred
	 * @date   03.12.2013
	 * @param sIDBewegungAtom
	 * @throws myException
	 */
	public REC_BG_AtomInfo(String sIDBewegungAtom) throws myException {
		InitBGAtom(sIDBewegungAtom);
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 18.04.2018
	 *
	 * @param cResult
	 */
	public REC_BG_AtomInfo(String[] cResult){
		initData(cResult);
	}
		
	
	/**
	 * Initialisiert die Klasse ausgehend von der IDAtom
	 * 
	 * @author manfred
	 * @date 02.12.2013
	 * @param idBewegungAtom
	*/
	private void InitBGAtom(String sIDBAtom) throws myException {

		SqlStringExtended query = new SqlStringExtended(REC_BG_AtomInfo.sSelectBGATOMInfo + " AND ID_BG_Atom = ? ");
		query.getValuesList().add(new Param_Long(Integer.parseInt(sIDBAtom)) );

		String [][] cResult = new String[0][0];
		cResult =  bibDB.EinzelAbfrageInArray(query);
		
		// wenn nichts gefunden wird, myException
		if (cResult == null || cResult.length == 0){
			throw new myException("Fehler beim Erzeugen von REC_AtomInfo");
		}

		initData(cResult[0]);
		
	}

	
	
	/**1
	 * 
	 * @author manfred
	 * @date 19.04.2018
	 *
	 * @param cResult
	 */
	private void initData(String[] cResult){
		int i = 0;
		
		_ID_BG_Vektor					= cResult[i++];
		_ID_BG_Atom 					= cResult[i++];
		
		_ID_BG_Ladung_Lade     			= cResult[i++];
		_IDAdresse_Lade 				= cResult[i++];
		_IDAdresse_Basis_Lade 			= cResult[i++];
		_IDAdresse_Besitzer_Lade 		= cResult[i++];
		_IDArtikel_Lade					= cResult[i++];
		_IDArtikelBez_Lade				= cResult[i++];
		_MengenDivisor_Lade				= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_Menge_Lade						= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_MengeAbrechnungseinheit_Lade 	= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_MengeNetto_Lade				= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_IDEinheit_Menge_Lade			= cResult[i++];
		_IDEinheit_Preis_Lade			= cResult[i++];
		_ID_BG_Station_lade				= cResult[i++];
		
		_ID_BG_Ladung_Ablade    		= cResult[i++];
		_IDAdresse_Ablade 				= cResult[i++];
		_IDAdresse_Basis_Ablade 		= cResult[i++];
		_IDAdresse_Besitzer_Ablade		= cResult[i++];
		_IDArtikel_Ablade				= cResult[i++];
		_IDArtikelBez_Ablade			= cResult[i++];
		_MengenDivisor_Ablade			= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_Menge_Ablade					= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_MengeAbrechnungseinheit_Ablade = bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_MengeNetto_Ablade				= bibALL.convertDBTextToBigDecimal(cResult[i++]);
		_IDEinheit_Menge_Ablade			= cResult[i++];
		_IDEinheit_Preis_Ablade			= cResult[i++];
		_ID_BG_Station_ablade			= cResult[i++];
		
		_IDLieferbedingung 			   	= cResult[i++];
		_EN_VektorTyp 					= cResult[i++];
	}
	
	
	

	
	

	/**
	 * @return the _IDBewegungAtom
	 */
	public String get_IDBewegungAtom() {
		return _ID_BG_Atom;
	}



	/**
	 * @return the sselectatominfo
	 */
	public static String getSelectAtomInfo() {
		return sSelectBGATOMInfo;
	}

	public String get_ID_BG_Vektor() {
		return _ID_BG_Vektor;
	}

	public REC_BG_AtomInfo set_ID_BG_Vektor(String _ID_BG_Vektor) {
		this._ID_BG_Vektor = _ID_BG_Vektor;
		return this;
	}

	public String get_ID_BG_Atom() {
		return _ID_BG_Atom;
	}

	public REC_BG_AtomInfo set_ID_BG_Atom(String _ID_BG_Atom) {
		this._ID_BG_Atom = _ID_BG_Atom;
		return this;
	}

	public String get_ID_BG_Ladung_Lade() {
		return _ID_BG_Ladung_Lade;
	}

	public REC_BG_AtomInfo set_ID_BG_Ladung_Lade(String _ID_BG_Ladung_Lade) {
		this._ID_BG_Ladung_Lade = _ID_BG_Ladung_Lade;
		return this;
	}

	public String get_IDAdresse_Lade() {
		return _IDAdresse_Lade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Lade(String _IDAdresse_Lade) {
		this._IDAdresse_Lade = _IDAdresse_Lade;
		return this;
	}

	public String get_IDAdresse_Basis_Lade() {
		return _IDAdresse_Basis_Lade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Basis_Lade(String _IDAdresse_Basis_Lade) {
		this._IDAdresse_Basis_Lade = _IDAdresse_Basis_Lade;
		return this;
	}

	public String get_IDAdresse_Besitzer_Lade() {
		return _IDAdresse_Besitzer_Lade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Besitzer_Lade(String _IDAdresse_Besitzer_Lade) {
		this._IDAdresse_Besitzer_Lade = _IDAdresse_Besitzer_Lade;
		return this;
	}

	public String get_IDArtikel_Lade() {
		return _IDArtikel_Lade;
	}

	public REC_BG_AtomInfo set_IDArtikel_Lade(String _IDArtikel_Lade) {
		this._IDArtikel_Lade = _IDArtikel_Lade;
		return this;
	}

	public String get_IDArtikelBez_Lade() {
		return _IDArtikelBez_Lade;
	}

	public REC_BG_AtomInfo set_IDArtikelBez_Lade(String _IDArtikelBez_Lade) {
		this._IDArtikelBez_Lade = _IDArtikelBez_Lade;
		return this;
	}

	public BigDecimal get_MengenDivisor_Lade() {
		return _MengenDivisor_Lade;
	}

	public REC_BG_AtomInfo set_MengenDivisor_Lade(BigDecimal _MengenDivisor_Lade) {
		this._MengenDivisor_Lade = _MengenDivisor_Lade;
		return this;
	}

	public BigDecimal get_Menge_Lade() {
		return _Menge_Lade;
	}

	public REC_BG_AtomInfo set_Menge_Lade(BigDecimal _Menge_Lade) {
		this._Menge_Lade = _Menge_Lade;
		return this;
	}

	public BigDecimal get_MengeAbrechnungseinheit_Lade() {
		return _MengeAbrechnungseinheit_Lade;
	}

	public REC_BG_AtomInfo set_MengeAbrechnungseinheit_Lade(BigDecimal _MengeAbrechnungseinheit_Lade) {
		this._MengeAbrechnungseinheit_Lade = _MengeAbrechnungseinheit_Lade;
		return this;
	}

	public BigDecimal get_MengeNetto_Lade() {
		return _MengeNetto_Lade;
	}

	public REC_BG_AtomInfo set_MengeNetto_Lade(BigDecimal _MengeNetto_Lade) {
		this._MengeNetto_Lade = _MengeNetto_Lade;
		return this;
	}

	public String get_IDEinheit_Menge_Lade() {
		return _IDEinheit_Menge_Lade;
	}

	public REC_BG_AtomInfo set_IDEinheit_Menge_Lade(String _IDEinheit_Menge_Lade) {
		this._IDEinheit_Menge_Lade = _IDEinheit_Menge_Lade;
		return this;
	}

	public String get_IDEinheit_Preis_Lade() {
		return _IDEinheit_Preis_Lade;
	}

	public REC_BG_AtomInfo set_IDEinheit_Preis_Lade(String _IDEinheit_Preis_Lade) {
		this._IDEinheit_Preis_Lade = _IDEinheit_Preis_Lade;
		return this;
	}

	public String get_ID_BG_Ladung_Ablade() {
		return _ID_BG_Ladung_Ablade;
	}

	public REC_BG_AtomInfo set_ID_BG_Ladung_Ablade(String _ID_BG_Ladung_Ablade) {
		this._ID_BG_Ladung_Ablade = _ID_BG_Ladung_Ablade;
		return this;
	}

	public String get_IDAdresse_Ablade() {
		return _IDAdresse_Ablade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Ablade(String _IDAdresse_Ablade) {
		this._IDAdresse_Ablade = _IDAdresse_Ablade;
		return this;
	}

	public String get_IDAdresse_Basis_Ablade() {
		return _IDAdresse_Basis_Ablade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Basis_Ablade(String _IDAdresse_Basis_Ablade) {
		this._IDAdresse_Basis_Ablade = _IDAdresse_Basis_Ablade;
		return this;
	}

	public String get_IDAdresse_Besitzer_Ablade() {
		return _IDAdresse_Besitzer_Ablade;
	}

	public REC_BG_AtomInfo set_IDAdresse_Besitzer_Ablade(String _IDAdresse_Besitzer_Ablade) {
		this._IDAdresse_Besitzer_Ablade = _IDAdresse_Besitzer_Ablade;
		return this;
	}

	public String get_IDArtikel_Ablade() {
		return _IDArtikel_Ablade;
	}

	public REC_BG_AtomInfo set_IDArtikel_Ablade(String _IDArtikel_Ablade) {
		this._IDArtikel_Ablade = _IDArtikel_Ablade;
		return this;
	}

	public String get_IDArtikelBez_Ablade() {
		return _IDArtikelBez_Ablade;
	}

	public REC_BG_AtomInfo set_IDArtikelBez_Ablade(String _IDArtikelBez_Ablade) {
		this._IDArtikelBez_Ablade = _IDArtikelBez_Ablade;
		return this;
	}

	public BigDecimal get_MengenDivisor_Ablade() {
		return _MengenDivisor_Ablade;
	}

	public REC_BG_AtomInfo set_MengenDivisor_Ablade(BigDecimal _MengenDivisor_Ablade) {
		this._MengenDivisor_Ablade = _MengenDivisor_Ablade;
		return this;
	}

	public BigDecimal get_Menge_Ablade() {
		return _Menge_Ablade;
	}

	public REC_BG_AtomInfo set_Menge_Ablade(BigDecimal _Menge_Ablade) {
		this._Menge_Ablade = _Menge_Ablade;
		return this;
	}

	public BigDecimal get_MengeAbrechnungseinheit_Ablade() {
		return _MengeAbrechnungseinheit_Ablade;
	}

	public REC_BG_AtomInfo set_MengeAbrechnungseinheit_Ablade(BigDecimal _MengeAbrechnungseinheit_Ablade) {
		this._MengeAbrechnungseinheit_Ablade = _MengeAbrechnungseinheit_Ablade;
		return this;
	}

	public BigDecimal get_MengeNetto_Ablade() {
		return _MengeNetto_Ablade;
	}

	public REC_BG_AtomInfo set_MengeNetto_Ablade(BigDecimal _MengeNetto_Ablade) {
		this._MengeNetto_Ablade = _MengeNetto_Ablade;
		return this;
	}

	public String get_IDEinheit_Menge_Ablade() {
		return _IDEinheit_Menge_Ablade;
	}

	public REC_BG_AtomInfo set_IDEinheit_Menge_Ablade(String _IDEinheit_Menge_Ablade) {
		this._IDEinheit_Menge_Ablade = _IDEinheit_Menge_Ablade;
		return this;
	}

	public String get_IDEinheit_Preis_Ablade() {
		return _IDEinheit_Preis_Ablade;
	}

	public REC_BG_AtomInfo set_IDEinheit_Preis_Ablade(String _IDEinheit_Preis_Ablade) {
		this._IDEinheit_Preis_Ablade = _IDEinheit_Preis_Ablade;
		return this;
	}



	public String get_EN_VektorTyp() {
		return _EN_VektorTyp;
	}

	public REC_BG_AtomInfo set_EN_VektorTyp(String _EN_VektorTyp) {
		this._EN_VektorTyp = _EN_VektorTyp;
		return this;
	}



	public String get_IDLieferbedingung() {
		return _IDLieferbedingung;
	}



	public REC_BG_AtomInfo set_IDLieferbedingunge(String _IDLieferbedingung) {
		this._IDLieferbedingung = _IDLieferbedingung;
		return this;
	}





	public BigDecimal get_bdFaktorLBWE() {
		return _bdFaktorLBWE;
	}



	public REC_BG_AtomInfo set_bdFaktorLBWE(BigDecimal _bdFaktorLBWE) {
		this._bdFaktorLBWE = _bdFaktorLBWE;
		return this;
	}



	public BigDecimal get_bdFaktorLBWA() {
		return _bdFaktorLBWA;
	}



	public REC_BG_AtomInfo set_bdFaktorLBWA(BigDecimal _bdFaktorLBWA) {
		this._bdFaktorLBWA = _bdFaktorLBWA;
		return this;
	}



	public String get_ID_BG_Station_ablade() {
		return _ID_BG_Station_ablade;
	}



	public REC_BG_AtomInfo set_ID_BG_Station_ablade(String _ID_BG_Station_ablade) {
		this._ID_BG_Station_ablade = _ID_BG_Station_ablade;
		return this;
	}



	public String get_ID_BG_Station_lade() {
		return _ID_BG_Station_lade;
	}



	public REC_BG_AtomInfo set_ID_BG_Station_lade(String _ID_BG_Station_lade) {
		this._ID_BG_Station_lade = _ID_BG_Station_lade;
		return this;
	}


	

}
