package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse kapselt eine zusammenfassung von einem einzelnen Transport-Vorgang, d.h. den beteiligten Haupt-Stationen und dem Artikel
 * 
 * @author manfred
 * @date 26.11.2013
 */
public class REC_Transport_Info {
 
	// Bei einem einfachen Transport wird nur _Atom1 berücksichtigt,
	// Bei Streckenfuhren wird auch _Atom2 benötigt.
	//
	private REC_BG_AtomInfo	_Atom1				= null;
	private REC_BG_AtomInfo	_Atom2				= null;
	

	private String 			_idAdresseLade 			= null;
	private String			_idAdresseBesitzerLade  = null;
	private String			_idSorteLade			= null;
	private String			_idArtikelBezLade		= null;
	private String 			_idBGAtomLade			= null;
	private String			_idBGLadungLade			= null;

	
	
	
	private String 			_idAdresseAblade 		= null;
	private String			_idAdresseBesitzerAblade= null;
	private String			_idSorteAblade			= null;
	private String			_idArtikelBezAblade		= null;
	private String 			_idBGAtomAblade			= null;
	private String			_idBGLadungAblade		= null;
	
	private String 			_idLieferbedingungLade 	= "";
	private BigDecimal      _bdFaktorLBWE 			= BigDecimal.ZERO;

	private String			_idLieferbedingungAblade 	= "";
	private BigDecimal		_bdFaktorLBWA 			= BigDecimal.ZERO;
	
	Vector<String>			_vIDLagerToIgnore 		= null;
	Vector<String>			_vEigeneLagerorte 		= null;
	
	private String			_idBGVektor		 		= null;
	
	RECLIST_KOSTEN_LIEFERBED_DEF _oReclistLieferbedDef = null;
	
	
	/**
	 * @author manfred
	 * @date 24.04.2018
	 *
	 */
	public REC_Transport_Info(	Vector<String> vIDLagerToIgnore,	
								Vector<String> vEigeneLagerorte,
								RECLIST_KOSTEN_LIEFERBED_DEF lieferkosten) {
		this._vEigeneLagerorte = vEigeneLagerorte;
		this._vIDLagerToIgnore = vIDLagerToIgnore;
		this._oReclistLieferbedDef = lieferkosten;
	}


	
	
	/**
	 * Vervollständigt die Daten bzgl. Adressen, Artikel, Lieferbedingungen und Faktoren  
	 * @author manfred
	 * @date 27.04.2018
	 */
	public void Initialize(){
		_idAdresseLade = "-1";
		_idAdresseAblade = "-1";		
		
		if (_Atom1 == null){
			return;
		}
		
		
		_idBGVektor = _Atom1.get_ID_BG_Vektor();
		
		// setzen der AdressIDs und der Sorten
		_idAdresseLade = _Atom1.get_IDAdresse_Lade();
		_idAdresseBesitzerLade = _Atom1.get_IDAdresse_Besitzer_Lade();
		_idSorteLade = _Atom1.get_IDArtikel_Lade();
		_idArtikelBezLade	= _Atom1.get_IDArtikelBez_Lade();
		_idLieferbedingungLade = _Atom1.get_IDLieferbedingung()!= null ? _Atom1.get_IDLieferbedingung(): "" ;;
		_idBGAtomLade = _Atom1.get_ID_BG_Atom();
		_idBGLadungLade = _Atom1.get_ID_BG_Ladung_Lade();
		
		
		if (_Atom2 != null){
			_idAdresseAblade = _Atom2.get_IDAdresse_Ablade();
			_idAdresseBesitzerAblade = _Atom2.get_IDAdresse_Besitzer_Ablade();
			_idSorteAblade = _Atom2.get_IDArtikel_Ablade();
			_idArtikelBezAblade = _Atom2.get_IDArtikelBez_Ablade();
			_idLieferbedingungAblade = _Atom2.get_IDLieferbedingung()!= null ? _Atom2.get_IDLieferbedingung(): "" ;;
			_idBGAtomAblade = _Atom2.get_ID_BG_Atom();
			_idBGLadungAblade = _Atom2.get_ID_BG_Ladung_Ablade();
			
		} else {
			_idAdresseAblade = _Atom1.get_IDAdresse_Ablade();
			_idAdresseBesitzerAblade = _Atom1.get_IDAdresse_Besitzer_Ablade();
			_idSorteAblade = _Atom1.get_IDArtikel_Ablade();
			_idArtikelBezAblade = _Atom1.get_IDArtikelBez_Ablade();
			_idLieferbedingungAblade = _Atom1.get_IDLieferbedingung()!= null ? _Atom1.get_IDLieferbedingung(): "" ;;
			_idBGAtomAblade = _Atom1.get_ID_BG_Atom();
			_idBGLadungAblade = _Atom1.get_ID_BG_Ladung_Ablade();
		}
		
		
		// falls eine Lageradresse dabei ist die nicht berücksichtigt werden soll, abbrechen
		if (_vIDLagerToIgnore.contains(_idAdresseLade) ||_vIDLagerToIgnore.contains(_idAdresseAblade)){
			return;
		}
		


		// jetzt die Kostenfaktoren zuweisen
		for (RECORD_KOSTEN_LIEFERBED_DEF rec: _oReclistLieferbedDef.values()){
			
			try {
				if (	rec.get_ID_LIEFERBEDINGUNG_WA_cUF_NN("").equals(_idLieferbedingungAblade)	){
					_bdFaktorLBWA = rec.get_FAKTOR_WA_bdValue(BigDecimal.ZERO);
					break;
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}

		// jetzt die Kostenfaktoren zuweisen
		for (RECORD_KOSTEN_LIEFERBED_DEF rec: _oReclistLieferbedDef.values()){
			
			try {
				if (rec.get_ID_LIEFERBEDINGUNG_WE_cUF_NN("").equals(_idLieferbedingungLade)	){
					_bdFaktorLBWE = rec.get_FAKTOR_WE_bdValue(BigDecimal.ZERO);
					break;
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		 

	}

	public String get_idAdresseLade() {
		return _idAdresseLade;
	}

	public REC_Transport_Info set_idAdresseLade(String _idAdresseLade) {
		this._idAdresseLade = _idAdresseLade;
		return this;
	}

	public String get_idAdresseAblade() {
		return _idAdresseAblade;
	}

	public REC_Transport_Info set_idAdresseAblade(String _idAdresseAblade) {
		this._idAdresseAblade = _idAdresseAblade;
		return this;
	}

	public String get_idLieferbedingungLade() {
		return _idLieferbedingungLade;
	}

	public REC_Transport_Info set_idLieferbedingungLade(String _idLieferbedingungWE) {
		this._idLieferbedingungLade = _idLieferbedingungWE;
		return this;
	}

	public BigDecimal get_bdFaktorLBLade() {
		return _bdFaktorLBWE;
	}

	public REC_Transport_Info set_bdFaktorLBLade(BigDecimal _bdFaktorLBWE) {
		this._bdFaktorLBWE = _bdFaktorLBWE;
		return this;
	}

	public String get_idLieferbedingungAblade() {
		return _idLieferbedingungAblade;
	}

	public REC_Transport_Info set_idLieferbedingungAblade(String _idLieferbedingungWA) {
		this._idLieferbedingungAblade = _idLieferbedingungWA;
		return this;
	}

	public BigDecimal get_bdFaktorLBAblade() {
		return _bdFaktorLBWA;
	}

	public REC_Transport_Info set_bdFaktorLBAblade(BigDecimal _bdFaktorLBWA) {
		this._bdFaktorLBWA = _bdFaktorLBWA;
		return this;
	}

	public Vector<String> get_vIDLagerToIgnore() {
		return _vIDLagerToIgnore;
	}

	public REC_Transport_Info set_vIDLagerToIgnore(Vector<String> _vIDLagerToIgnore) {
		this._vIDLagerToIgnore = _vIDLagerToIgnore;
		return this;
	}


	public Vector<String> get_vEigeneLagerorte() {
		return _vEigeneLagerorte;
	}

	public REC_Transport_Info set_vEigeneLagerorte(Vector<String> _vEigeneLagerorte) {
		this._vEigeneLagerorte = _vEigeneLagerorte;
		return this;
	}

	public String get_idBGVektor() {
		return _idBGVektor;
	}

	public REC_Transport_Info set_idBGVektor(String _idBewegungVektor) {
		this._idBGVektor = _idBewegungVektor;
		return this;
	}


	public RECLIST_KOSTEN_LIEFERBED_DEF get_oReclistLieferbedDef() {
		return _oReclistLieferbedDef;
	}

	public REC_Transport_Info set_oReclistLieferbedDef(RECLIST_KOSTEN_LIEFERBED_DEF _oReclistLieferbedDef) {
		this._oReclistLieferbedDef = _oReclistLieferbedDef;
		return this;
	}



	public String get_idAdresseBesitzerLade() {
		return _idAdresseBesitzerLade;
	}



	public REC_Transport_Info set_idAdresseBesitzerLade(String _idAdresseBesitzerLade) {
		this._idAdresseBesitzerLade = _idAdresseBesitzerLade;
		return this;
	}



	public String get_idAdresseBesitzerAblade() {
		return _idAdresseBesitzerAblade;
	}



	public REC_Transport_Info set_idAdresseBesitzerAblade(String _idAdresseBesitzerAblade) {
		this._idAdresseBesitzerAblade = _idAdresseBesitzerAblade;
		return this;
	}



	public REC_BG_AtomInfo get_AtomInfo1() {
		return _Atom1;
	}



	public REC_Transport_Info set_AtomInfo1(REC_BG_AtomInfo _AtomLade) {
		this._Atom1 = _AtomLade;
		return this;
	}



	public REC_BG_AtomInfo get_AtomInfo2() {
		return _Atom2;
	}



	public REC_Transport_Info set_AtomInfo2(REC_BG_AtomInfo _AtomAblade) {
		this._Atom2 = _AtomAblade;
		return this;
	}



	public String get_idSorteLade() {
		return _idSorteLade;
	}



	public REC_Transport_Info set_idSorteLade(String _idSorteLade) {
		this._idSorteLade = _idSorteLade;
		return this;
	}



	public String get_idSorteAblade() {
		return _idSorteAblade;
	}



	public REC_Transport_Info set_idSorteAblade(String _idSorteAblade) {
		this._idSorteAblade = _idSorteAblade;
		return this;
	}

	public String info(){
		return String.format("Vektortyp=%s, ID_BG_VEKTOR=%s, ID_BG_ATOM=%s, ID_ADRESSE_LADEN=%S, ID_ARTIKEL_LADEN=%s, ID_ADRESSE_ABLADEN=%s, ID_ARTIKEL_ABLADEN=%s", 
				"?"
				,_idBGVektor 
				,_Atom1.get_ID_BG_Atom()
				, _idAdresseLade
				,_idSorteLade
				,_idAdresseAblade
				,_idSorteAblade
				);
	}





	public String get_idBGAtomLade() {
		return _idBGAtomLade;
	}



	public REC_Transport_Info set_idBGAtomLade(String _idBGAtomLade) {
		this._idBGAtomLade = _idBGAtomLade;
		return this;
	}



	public String get_idBGLadungLade() {
		return _idBGLadungLade;
	}



	public REC_Transport_Info set_idBGLadungLade(String _idBGLadungLade) {
		this._idBGLadungLade = _idBGLadungLade;
		return this;
	}



	public String get_idBGAtomAblade() {
		return _idBGAtomAblade;
	}



	public REC_Transport_Info set_idBGAtomAblade(String _idBGAtomAblade) {
		this._idBGAtomAblade = _idBGAtomAblade;
		return this;
	}



	public String get_idBGLadungAblade() {
		return _idBGLadungAblade;
	}



	public REC_Transport_Info set_idBGLadungAblade(String _idBGLadungAblade) {
		this._idBGLadungAblade = _idBGLadungAblade;
		return this;
	}




	public String get_idArtikelBezLade() {
		return _idArtikelBezLade;
	}

	public REC_Transport_Info set_idArtikelBezLade(String _idArtikelBezLade) {
		this._idArtikelBezLade = _idArtikelBezLade;
		return this;
	}

	public String get_idArtikelBezAblade() {
		return _idArtikelBezAblade;
	}

	public REC_Transport_Info set_idArtikelBezAblade(String _idArtikelBezAblade) {
		this._idArtikelBezAblade = _idArtikelBezAblade;
		return this;
	}
	
	
//	/**
//	 * die einzelnen zeilen in die Records überführen 
//	 * @param IdBewegungVektor
//	 */
//	private void ReadBGAtomInfos(String IdBGVektor){
//		
//		//  
//		SqlStringExtended query = new SqlStringExtended(REC_BG_AtomInfo.sSelectBGATOMInfo + " AND V.ID_BG_VEKTOR = ? ");
//		query.getValuesList().add(new Param_Long(Integer.parseInt(IdBGVektor)) );
//		
//		String [][] cResult = new String[0][0];
//		cResult =  bibDB.EinzelAbfrageInArray(query);
//		
//		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
//		if (cResult == null || cResult.length == 0){
//			return;
//		}
//
//		// Atominfos generieren und an die Liste hängen
//		for (int iCount = 0; iCount < cResult.length; iCount++) {
//			int i = 0;
//			
//			String[] sRow = cResult[iCount];
//			REC_BG_AtomInfo o = new REC_BG_AtomInfo(sRow);
//			
//			_list_BG_AtomInfo.add(o);
//		}
//
//	}

	
	
	
	
	
//	public void Add(REC_BG_AtomInfo oAtomInfo){
//		_list_BG_AtomInfo.add(oAtomInfo);
//	}
//	
//	public Vector<REC_BG_AtomInfo> getList(){
//		return _list_BG_AtomInfo;
//	}
	
	

}
