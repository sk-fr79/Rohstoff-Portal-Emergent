package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse kapselt eine zusammenfassung von Bewegung_Vektor, den beteiligten Haupt-Stationen und dem
 * Artikel
 * 
 * @author manfred
 * @date 26.11.2013
 */
public class REC_VektorInfo {
 
	Vector<REC_AtomInfo> 	_list_AtomInfo = new Vector<REC_AtomInfo>();

	private String 			_idAdresseLade = null;
	private String 			_idAdresseAblade = null;
	
	private String 			_idLieferbedingungWE = "";
	private BigDecimal     _bdFaktorLBWE = BigDecimal.ZERO;

	private String			_idLieferbedingungWA = "";
	private BigDecimal		_bdFaktorLBWA = BigDecimal.ZERO;
	
	Vector<String>			_vIDLagerToIgnore = null;
	Vector<String>			_vIDSonderlagerRelevant = null;
	Vector<String>			_vEigeneLagerorte = null;
	
	private String			_idBewegungVektor = null;
	
	
	RECLIST_KOSTEN_LIEFERBED_DEF _oReclistLieferbedDef = null;
	
	
	


	public String get_idBewegungVektor() {
		return _idBewegungVektor;
	}


//	public void set_idBewegungVektor(String _idBewegungVektor) {
//		this._idBewegungVektor = _idBewegungVektor;
//	}


	public BigDecimal get_bdFaktorWA() {
		return _bdFaktorLBWA;
	}


	public void set_bdFaktorWA(BigDecimal _bdFaktorLBWA) {
		this._bdFaktorLBWA = _bdFaktorLBWA;
	}


	public BigDecimal get_bdFaktorWE() {
		return _bdFaktorLBWE;
	}


	public void set_bdFaktorWE(BigDecimal _bdFaktorLBWE) {
		this._bdFaktorLBWE = _bdFaktorLBWE;
	}


	public String get_idLieferbedingungWA() {
		return _idLieferbedingungWA;
	}


	public void set_idLieferbedingungWA(String _idLieferbedingungWA) {
		this._idLieferbedingungWA = _idLieferbedingungWA;
	}

	
	public String get_idLieferbedingungWE() {
		return _idLieferbedingungWE;
	}

	public void set_idLieferbedingungWE(String _idLieferbedingungWE) {
		this._idLieferbedingungWE = _idLieferbedingungWE;
	}


	public String get_idAdresseLade() {
		return _idAdresseLade;
	}


	public void set_idAdresseLade(String _idAdresseLade) {
		this._idAdresseLade = _idAdresseLade;
	}


	public String get_idAdresseAblade() {
		return _idAdresseAblade;
	}


	public void set_idAdresseAblade(String _idAdresseAblade) {
		this._idAdresseAblade = _idAdresseAblade;
	}


	/**
	 * 
	 * @param sIDVektor
	 * @param vIDLagerToIgnore  Alle Sonderläger die nicht als Start und Ende relevant sind. D.h. Alle Sonderläger incl. Strecke und LL
	 */
	public  REC_VektorInfo(String sIDVektor, Vector<String> vIDLagerToIgnore, Vector<String> vIDSonderlagerRelevant, Vector<String> vEigeneLagerorte,  RECLIST_KOSTEN_LIEFERBED_DEF listLieferbed){
		_vIDLagerToIgnore 		= vIDLagerToIgnore;
		_vIDSonderlagerRelevant = vIDSonderlagerRelevant;
		_oReclistLieferbedDef   = listLieferbed;
		_vEigeneLagerorte 		= vEigeneLagerorte;
		_idBewegungVektor 		= sIDVektor;
		
		ReadAtomInfos(sIDVektor);
		GetAdressIds();
	}
	
	
	/**
	 * da die Kosten Ort- und Richtungsgebunden sind müssen Anfangs- und Endeadressen ermittelt werden
	 * Es werden nur die Atome genommen, die Relevant sind. 
	 * Bei einer Strecke ist dann das Streckenlager zu ignorieren und das "gegenüberliegende" Kundenlager zu ermitteln
	 * Bei einem Lager-Lager-Transport ist dann LL zu ignorieren
	 * mit * gekennzeichnet sind relevante Adressen
	 * 1. WE
	 * *K-*L / L-SW / SW-L
	 * 2. WA
	 * L-SW / SW-L / *L-*K
	 * 3. S
	 * *K-S / S-SW / SW-S / S-*K
	 * 4. LL
	 * L-SW / SW-L / *L-LL / LL-L*
	 * 
	 * Lager2Ignore: SW,UH,KH,MK,MI
	 * SonderlagerRelevant: S,LL
	 */
	
	private void GetAdressIds(){
		_idAdresseLade = "-1";
		_idAdresseAblade = "-1";		

		Iterator<REC_AtomInfo> it = _list_AtomInfo.iterator();
		
		while (it.hasNext()){
			REC_AtomInfo o = it.next();
				String sIDLade 		= o.get_IDAdresse_Lade();
				String sIDAblade 	= o.get_IDAdresse_Ablade();
				
				// ein atom, das 
				if (_vIDLagerToIgnore.contains(sIDLade) ||_vIDLagerToIgnore.contains(sIDAblade)){
					continue;
				}
			
				if (!_vIDSonderlagerRelevant.contains(o.get_IDAdresse_Lade())  ){
					_idAdresseLade 		= o.get_IDAdresse_Lade();
					// jetzt die Lieferbedingungen setzen, abhängig ob WE oder WA
					if (!_vEigeneLagerorte.contains(_idAdresseLade)){
						_idLieferbedingungWE = o.get_IDLieferbedingungen() != null ? o.get_IDLieferbedingungen() : "" ;
					}
				} 
				if (!_vIDSonderlagerRelevant.contains(o.get_IDAdresse_Ablade())){
					_idAdresseAblade	= o.get_IDAdresse_Ablade();
					// jetzt die Lieferbedingungen setzen, abhängig ob WE oder WA
					if (!_vEigeneLagerorte.contains(_idAdresseAblade)){
						_idLieferbedingungWA = o.get_IDLieferbedingungen() != null ? o.get_IDLieferbedingungen() : "" ;
					}
				}
				
				
				// jetzt die Kostenfaktoren zuweisen
				for (RECORD_KOSTEN_LIEFERBED_DEF rec: _oReclistLieferbedDef.values()){
					
					try {
						if (	
								rec.get_ID_LIEFERBEDINGUNG_WA_cUF_NN("").equals(_idLieferbedingungWA) 	&& 
								rec.get_ID_LIEFERBEDINGUNG_WE_cUF_NN("").equals(_idLieferbedingungWE)
							){
							set_bdFaktorWE(rec.get_FAKTOR_WE_bdValue(BigDecimal.ZERO));
							set_bdFaktorWA(rec.get_FAKTOR_WA_bdValue(BigDecimal.ZERO));
							break;
						}
					} catch (myException e) {
						e.printStackTrace();
					}
				}
				
		}

	}

	
	
	
	/**
	 * die einzelnen zeilen in die Records überführen 
	 * @param IdBewegungVektor
	 */
	private void ReadAtomInfos(String IdBewegungVektor){
		String sWhere = " WHERE V.ID_BEWEGUNG_VEKTOR = " + IdBewegungVektor;
		
		String sSql = REC_AtomInfo.getSelectAtominfo() + sWhere ;
		
		String [][] cResult = new String[0][0];
		cResult =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cResult == null || cResult.length == 0){
			return;
		}


		for (int iCount = 0; iCount < cResult.length; iCount++) {
			int i = 0;
			REC_AtomInfo o = new REC_AtomInfo();
			
			String _IDBewegungVektor			= cResult[iCount][i++];
			o.set_IDBewegungVektor(_IDBewegungVektor);
			
			String _IDBewegungAtom 				= cResult[iCount][i++];
			o.set_IDBewegungAtom(_IDBewegungAtom);
			
			
			String _IDBewegung 					= cResult[iCount][i++];
			o.set_IDBewegung(_IDBewegung);
			
			String _IDAdresse_Lade 				= cResult[iCount][i++];
			o.set_IDAdresse_Lade(_IDAdresse_Lade);
			
			String _IDBesitzer_Lade 			= cResult[iCount][i++];
			o.set_IDBesitzer_Lade(_IDBesitzer_Lade);
			
			String _IDAdresse_Ablade 			= cResult[iCount][i++];
			o.set_IDAdresse_Ablade(_IDAdresse_Ablade);
			
			String _IDBesitzer_Ablade 			= cResult[iCount][i++];
			o.set_IDBesitzer_Ablade(_IDBesitzer_Ablade);
			
			String _IDArtikel 					= cResult[iCount][i++];
			o.set_IDArtikel(_IDArtikel);
			
			String _IDArtikelBez 				= cResult[iCount][i++];
			o.set_IDArtikelBez(_IDArtikelBez);
			
			BigDecimal _MengeBrutto 			= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeBrutto(_MengeBrutto );
			
			BigDecimal _MengeAbrechnungseinheit = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeAbrechnungseinheit(_MengeAbrechnungseinheit != null ? _MengeAbrechnungseinheit.setScale(3, BigDecimal.ROUND_HALF_UP) : null );
			
			BigDecimal _MengeNetto  			= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeNetto(_MengeNetto );
			
			String _IDLieferbedingungen 		= cResult[iCount][i++];
			o.set_IDLieferbedingungen(_IDLieferbedingungen);
			
			String _IDEinheit_Menge 			= cResult[iCount][i++];
			o.set_IDEinheit_Menge(_IDEinheit_Menge);
			
			String _IDEinheit_Preis 			= cResult[iCount][i++];
			o.set_IDEinheit_Preis(_IDEinheit_Preis);
			
			BigDecimal _MengenDivisor   		= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengenDivisor(_MengenDivisor);
			
			String _Variante 			= cResult[iCount][i++];
			o.set_Variante(_Variante);
			
//			BigDecimal _FaktorWE  = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
//			o.set_FaktorWE(_FaktorWE);
//			
//			BigDecimal _FaktorWA = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
//			o.set_FaktorWA(_FaktorWA);
//
//			BigDecimal _FaktorStreckeWE = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
//			o.set_FaktorStreckeWE(_FaktorStreckeWE);
//
//			BigDecimal _FaktorStreckeWA = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
//			o.set_FaktorStreckeWA(_FaktorStreckeWA);
			
			String _Abrechenbar 			= cResult[iCount][i++];
			o.set_Abrechenbar(_Abrechenbar);
			
			
			// record zur Liste hinzufügen
			_list_AtomInfo.add(o);
		}
	}
	
	
	
	
	
	public void Add(REC_AtomInfo oAtomInfo){
		_list_AtomInfo.add(oAtomInfo);
	}
	
	public Vector<REC_AtomInfo> getList(){
		return _list_AtomInfo;
	}
	
	

}
