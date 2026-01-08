package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse kapselt eine zusammenfassung von Bewegung_atom, den Stationen und dem
 * Artikel
 * 
 * @author manfred
 * @date 26.11.2013
 */
public class REC_AtomInfo {

	
	private String 		_IDBewegungVektor = null;
	private String 		_IDBewegungAtom = null;
	private String 		_IDAdresse_Lade = null;
	private String 		_IDAdresse_Ablade = null;
	private String 		_IDBesitzer_Lade = null;
	private String 		_IDBesitzer_Ablade = null;
	private String 		_IDArtikel = null;
	private String 		_IDArtikelBez = null;
	private String 		_IDLieferbedingungen = null;
	private String 		_IDEinheit_Preis = null;
	private BigDecimal _MengeBrutto = null;
	private BigDecimal _MengeAbrechnungseinheit = null;
	private BigDecimal _MengeNetto  = null;
	private String 		_IDEinheit_Menge = null;
	private String 		_IDBewegung = null;
	private BigDecimal _MengenDivisor = null;
	private String		_Variante = null;

	private String		_Abrechenbar = null;
	
	
//	private String _IDVektor_Gruppe = null;
	

	
	
	/**
	 * Basis-Select für das Atom-Info
	 */
//	static final String sSelectAtomInfo = " "
//			+ "	SELECT 							"
//			+ "			a.ID_BEWEGUNG_VEKTOR, "			
//			+ "			a.ID_BEWEGUNG_ATOM, "
//			+ "			a.ID_BEWEGUNG, "
//			+ "			s1.ID_ADRESSE, "
//			+ "			s1.ID_ADRESSE_BESITZER, "
//			+ " 		s2.ID_ADRESSE, "
//			+ "			s2.ID_ADRESSE_BESITZER, "
//			+ " 		a.ID_ARTIKEL, "
//			+ "			a.ID_ARTIKEL_BEZ, "
//			+ "			a.MENGE, "
//			+ "			a.MENGE / art.MENGENDIVISOR,"
//			+ "			a.MENGE_NETTO,"
//			+ "			a.ID_LIEFERBEDINGUNGEN, "
//			+ " 		art.ID_EINHEIT, "
//			+ "			art.ID_EINHEIT_PREIS, "
//			+ "			art.MENGENDIVISOR, "
//			+ "			v.VARIANTE, "
//			+ " 		nvl(a.Abrechenbar,'N')	"
//			+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM a "
//			+ " INNER JOIN " + bibE2.cTO() + ".jt_BEWEGUNG_STATION s1 on a.ID_BEWEGUNG_ATOM = s1.ID_BEWEGUNG_ATOM and s1.MENGENVORZEICHEN = -1 "
//			+ " INNER JOIN " + bibE2.cTO() + ".jt_BEWEGUNG_STATION s2 on a.ID_BEWEGUNG_ATOM = s2.ID_BEWEGUNG_ATOM and s2.MENGENVORZEICHEN = 1 "
//			+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL art on a.ID_ARTIKEL = art.id_artikel "
//			+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR v on a.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR ";

	
	static final String sSelectAtomInfo  = " SELECT" +
			"  A1.ID_BEWEGUNG_VEKTOR " +
			" ,A1.ID_BEWEGUNG_ATOM " +
			" ,A1.ID_BEWEGUNG  " +
			" ,S1.ID_ADRESSE AS ID_ADRESSE_LADEN   " +
			" ,S1.ID_ADRESSE_BESITZER AS ID_ADRESSE_BESITZER_LADEN  " +
			" ,CASE WHEN A2.ID_BEWEGUNG_ATOM IS NOT NULL THEN S2.ID_ADRESSE  ELSE S1_2.ID_ADRESSE  END AS ID_ADRESSE_ABLADEN  " +
			" ,CASE WHEN A2.ID_BEWEGUNG_ATOM IS NOT NULL THEN S2.ID_ADRESSE_BESITZER ELSE S1_2.ID_ADRESSE_BESITZER END AS ID_ADRESSE_BESITZER_ABLADEN  " +
			" ,NVL(A1.ID_ARTIKEL, A2.ID_ARTIKEL) AS ID_ARTIKEL" +
			" ,NVL(A1.ID_ARTIKEL_BEZ, A2.ID_ARTIKEL_BEZ) AS ID_ARTIKEL_BEZ" +
			" ,A1.MENGE " +
			" ,A1.MENGE / ART.MENGENDIVISOR " +
			" ,NVL(A1.MENGE_NETTO, A1.MENGE) " +
			" ,A1.ID_LIEFERBEDINGUNGEN " +
			" ,ART.ID_EINHEIT "  +
			" ,ART.ID_EINHEIT_PREIS "  +
			" ,ART.MENGENDIVISOR " + 
			" ,V.VARIANTE"  +
			" ,NVL(A1.ABRECHENBAR,'N') "  +
			" FROM " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR_POS P   " +
			" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON P.ID_BEWEGUNG_VEKTOR = V.ID_BEWEGUNG_VEKTOR " + 
			" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A1 on P.ID_BEWEGUNG_VEKTOR_POS = A1.ID_BEWEGUNG_VEKTOR_POS and A1.POSNR = 1  " +
			" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1 on A1.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM and S1.MENGENVORZEICHEN = -1   " +
			" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S1_2 on A1.ID_BEWEGUNG_ATOM = S1_2.ID_BEWEGUNG_ATOM and S1_2.MENGENVORZEICHEN = +1   " +
			" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM a2 on P.ID_BEWEGUNG_VEKTOR_POS = A2.ID_BEWEGUNG_VEKTOR_POS and A2.POSNR = 2   " +
			" LEFT OUTER  JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S2 on A2.ID_BEWEGUNG_ATOM = S2.ID_BEWEGUNG_ATOM and S2.MENGENVORZEICHEN = +1   " +
			" INNER JOIN  	   " + bibE2.cTO() + ".JT_ARTIKEL art on art.id_artikel = a1.id_artikel "+ 
			" ";
	
	
	
	
	
	
	/**
	 * Konstruktor mit allen Feldern
	 * @author manfred
	 * @date   03.12.2013
	 * @param _IDBewegungAtom
	 * @param _IDAdresse_Lade
	 * @param _IDAdresse_Ablade
	 * @param _IDBesitzer_Lade
	 * @param _IDBesitzer_Ablade
	 * @param _IDArtikel
	 * @param _IDArtikelBez
	 * @param _IDLieferbedingungen
	 * @param _IDEinheit_Preis
	 * @param _MengeBrutto
	 * @param _MengeAbrechnungseinheit
	 * @param _MengeNetto
	 * @param _IDEinheit_Menge
	 * @param _IDBewegung
	 * @param _MengenDivisor
	 * 	
	 */
	public REC_AtomInfo(String _IDBewegungVektor,String _IDBewegungAtom, String _IDAdresse_Lade,
			String _IDAdresse_Ablade, String _IDBesitzer_Lade,
			String _IDBesitzer_Ablade, String _IDArtikel, String _IDArtikelBez,
			String _IDLieferbedingungen, String _IDEinheit_Preis,
			BigDecimal _MengeBrutto, BigDecimal _MengeAbrechnungseinheit, 
			BigDecimal _MengeNetto, String _IDEinheit_Menge, 
			String _IDBewegung, BigDecimal _MengenDivisor,
			String _Variante, 
			String _Abrechenbar
			) {
		super();
		this._IDBewegungVektor = _IDBewegungVektor;
		this._IDBewegungAtom = _IDBewegungAtom;
		this._IDAdresse_Lade = _IDAdresse_Lade;
		this._IDAdresse_Ablade = _IDAdresse_Ablade;
		this._IDBesitzer_Lade = _IDBesitzer_Lade;
		this._IDBesitzer_Ablade = _IDBesitzer_Ablade;
		this._IDArtikel = _IDArtikel;
		this._IDArtikelBez = _IDArtikelBez;
		this._IDLieferbedingungen = _IDLieferbedingungen;
		this._IDEinheit_Preis = _IDEinheit_Preis;
		this._MengeBrutto = _MengeBrutto;
		this._MengeAbrechnungseinheit = _MengeAbrechnungseinheit;
		this._MengeNetto = _MengeNetto;
		this._IDEinheit_Menge = _IDEinheit_Menge;
		this._IDBewegung = _IDBewegung;
		this._MengenDivisor = _MengenDivisor;
		this._Variante =_Variante;
		this._Abrechenbar = _Abrechenbar;

	}

	/**
	 * Default-Konstruktor mit leeren Feldern
	 * @author manfred
	 * @date   03.12.2013
	 */
	public REC_AtomInfo() {
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
	public REC_AtomInfo(String sIDBewegungAtom) throws myException {
		InitAtom(sIDBewegungAtom);
	}

	/**
	 * Initialisiert die Klasse ausgehend von der IDAtom
	 * 
	 * @author manfred
	 * @date 02.12.2013
	 * @param idBewegungAtom
	 */
	private void InitAtom(String sIDBewegungAtom) throws myException {

		String sWhere = " WHERE a.ID_Bewegung_atom = " + sIDBewegungAtom;
		
		String sSql = sSelectAtomInfo + sWhere;
		
		
		String [][] cResult = new String[0][0];
		cResult =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// wenn nichts gefunden wird, myException
		if (cResult == null || cResult.length == 0){
			throw new myException("Fehler beim Erzeugen von REC_AtomInfo");
		}
		
/*		
		+ "			a.ID_BEWEGUNG_ATOM, "
		+ "			a.ID_BEWEGUNG, "
		+ "			s1.ID_ADRESSE, "
		+ "			s1.ID_ADRESSE_BESITZER, "
		+ " 		s2.ID_ADRESSE, "
		+ "			s2.ID_ADRESSE_BESITZER, "
		+ " 		a.ID_ARTIKEL, "
		+ "			a.ID_ARTIKEL_BEZ, "
		+ "			a.MENGE, "
		+ "			a.MENGE / art.MENGENDIVISOR,"
		+ "			a.MENGE_NETTO,"
		+ "			a.ID_LIEFERBEDINGUNGEN, "
		+ " 		art.ID_EINHEIT, "
		+ "			art.ID_EINHEIT_PREIS, "
		+ "			art.MENGENDIVISOR "
*/
	
		int i = 0;
		_IDBewegungVektor			= cResult[0][i++];
		_IDBewegungAtom 			= cResult[0][i++];
		_IDBewegung 				= cResult[0][i++];
		_IDAdresse_Lade 			= cResult[0][i++];
		_IDBesitzer_Lade 			= cResult[0][i++];
		_IDAdresse_Ablade 			= cResult[0][i++];
		_IDBesitzer_Ablade 			= cResult[0][i++];
		_IDArtikel 					= cResult[0][i++];
		_IDArtikelBez 				= cResult[0][i++];
		_MengeBrutto 				= bibALL.convertDBTextToBigDecimal(cResult[0][i++]);
		
		_MengeAbrechnungseinheit 	= bibALL.convertDBTextToBigDecimal(cResult[0][i++]);
		_MengeAbrechnungseinheit.setScale(3, BigDecimal.ROUND_HALF_UP);
		
		_MengeNetto  				= bibALL.convertDBTextToBigDecimal(cResult[0][i++]);
		_IDLieferbedingungen 		= cResult[0][i++];
		_IDEinheit_Menge 			= cResult[0][i++];
		_IDEinheit_Preis 			= cResult[0][i++];
		_MengenDivisor   			= bibALL.convertDBTextToBigDecimal(cResult[0][i++]);
		
		_Variante 					= cResult[0][i++];
		_Abrechenbar				= cResult[0][i++]; 
	}

	/**
	 * @return the _IDBewegungAtom
	 */
	public String get_IDBewegungAtom() {
		return _IDBewegungAtom;
	}

	/**
	 * @param _IDBewegungAtom the _IDBewegungAtom to set
	 */
	public void set_IDBewegungAtom(String _IDBewegungAtom) {
		this._IDBewegungAtom = _IDBewegungAtom;
	}

	/**
	 * @return the _IDBewegungVektor
	 */
	public String get_IDBewegungVektor() {
		return _IDBewegungVektor;
	}

	/**
	 * @param _IDBewegungVektor the _IDBewegungVektor to set
	 */
	public void set_IDBewegungVektor(String _IDBewegungVektor) {
		this._IDBewegungVektor = _IDBewegungVektor;
	}

	/**
	 * @return the _IDAdresse_Lade
	 */
	public String get_IDAdresse_Lade() {
		return _IDAdresse_Lade;
	}

	/**
	 * @param _IDAdresse_Lade the _IDAdresse_Lade to set
	 */
	public void set_IDAdresse_Lade(String _IDAdresse_Lade) {
		this._IDAdresse_Lade = _IDAdresse_Lade;
	}

	/**
	 * @return the _IDAdresse_Ablade
	 */
	public String get_IDAdresse_Ablade() {
		return _IDAdresse_Ablade;
	}

	/**
	 * @param _IDAdresse_Ablade the _IDAdresse_Ablade to set
	 */
	public void set_IDAdresse_Ablade(String _IDAdresse_Ablade) {
		this._IDAdresse_Ablade = _IDAdresse_Ablade;
	}

	/**
	 * @return the _IDBesitzer_Lade
	 */
	public String get_IDBesitzer_Lade() {
		return _IDBesitzer_Lade;
	}

	/**
	 * @param _IDBesitzer_Lade the _IDBesitzer_Lade to set
	 */
	public void set_IDBesitzer_Lade(String _IDBesitzer_Lade) {
		this._IDBesitzer_Lade = _IDBesitzer_Lade;
	}

	/**
	 * @return the _IDBesitzer_Ablade
	 */
	public String get_IDBesitzer_Ablade() {
		return _IDBesitzer_Ablade;
	}

	/**
	 * @param _IDBesitzer_Ablade the _IDBesitzer_Ablade to set
	 */
	public void set_IDBesitzer_Ablade(String _IDBesitzer_Ablade) {
		this._IDBesitzer_Ablade = _IDBesitzer_Ablade;
	}

	/**
	 * @return the _IDArtikel
	 */
	public String get_IDArtikel() {
		return _IDArtikel;
	}

	/**
	 * @param _IDArtikel the _IDArtikel to set
	 */
	public void set_IDArtikel(String _IDArtikel) {
		this._IDArtikel = _IDArtikel;
	}

	/**
	 * @return the _IDArtikelBez
	 */
	public String get_IDArtikelBez() {
		return _IDArtikelBez;
	}

	/**
	 * @param _IDArtikelBez the _IDArtikelBez to set
	 */
	public void set_IDArtikelBez(String _IDArtikelBez) {
		this._IDArtikelBez = _IDArtikelBez;
	}

	/**
	 * @return the _IDLieferbedingungen
	 */
	public String get_IDLieferbedingungen() {
		return _IDLieferbedingungen;
	}

	/**
	 * @param _IDLieferbedingungen the _IDLieferbedingungen to set
	 */
	public void set_IDLieferbedingungen(String _IDLieferbedingungen) {
		this._IDLieferbedingungen = _IDLieferbedingungen;
	}

	/**
	 * @return the _IDEinheit_Preis
	 */
	public String get_IDEinheit_Preis() {
		return _IDEinheit_Preis;
	}

	/**
	 * @param _IDEinheit_Preis the _IDEinheit_Preis to set
	 */
	public void set_IDEinheit_Preis(String _IDEinheit_Preis) {
		this._IDEinheit_Preis = _IDEinheit_Preis;
	}

	/**
	 * @return the _MengeBrutto
	 */
	public BigDecimal get_MengeBrutto() {
		return _MengeBrutto;
	}

	/**
	 * @param _MengeBrutto the _MengeBrutto to set
	 */
	public void set_MengeBrutto(BigDecimal _MengeBrutto) {
		this._MengeBrutto = _MengeBrutto;
	}

	/**
	 * @return the _MengeAbrechnungseinheit
	 */
	public BigDecimal get_MengeAbrechnungseinheit() {
		return _MengeAbrechnungseinheit;
	}

	/**
	 * @param _MengeAbrechnungseinheit the _MengeAbrechnungseinheit to set
	 */
	public void set_MengeAbrechnungseinheit(BigDecimal _MengeAbrechnungseinheit) {
		this._MengeAbrechnungseinheit = _MengeAbrechnungseinheit ;
	}

	/**
	 * @return the _MengeNetto
	 */
	public BigDecimal get_MengeNetto() {
		return _MengeNetto;
	}

	/**
	 * @param _MengeNetto the _MengeNetto to set
	 */
	public void set_MengeNetto(BigDecimal _MengeNetto) {
		this._MengeNetto = _MengeNetto;
	}

	/**
	 * @return the _IDEinheit_Menge
	 */
	public String get_IDEinheit_Menge() {
		return _IDEinheit_Menge;
	}

	/**
	 * @param _IDEinheit_Menge the _IDEinheit_Menge to set
	 */
	public void set_IDEinheit_Menge(String _IDEinheit_Menge) {
		this._IDEinheit_Menge = _IDEinheit_Menge;
	}

	/**
	 * @return the _IDBewegung
	 */
	public String get_IDBewegung() {
		return _IDBewegung;
	}

	/**
	 * @param _IDBewegung the _IDBewegung to set
	 */
	public void set_IDBewegung(String _IDBewegung) {
		this._IDBewegung = _IDBewegung;
	}

	/**
	 * @return the _MengenDivisor
	 */
	public BigDecimal get_MengenDivisor() {
		return _MengenDivisor;
	}

	/**
	 * @param _MengenDivisor the _MengenDivisor to set
	 */
	public void set_MengenDivisor(BigDecimal _MengenDivisor) {
		this._MengenDivisor = _MengenDivisor;
	}

	/**
	 * @return the sselectatominfo
	 */
	public static String getSelectAtominfo() {
		return sSelectAtomInfo;
	}

	
	public String get_Variante() {
		return _Variante;
	}

	public void set_Variante(String _Variante) {
		this._Variante = _Variante;
	}


	public String get_Abrechenbar() {
		return _Abrechenbar;
	}

	public void set_Abrechenbar(String _Abrechenbar) {
		this._Abrechenbar = _Abrechenbar;
	}

}
