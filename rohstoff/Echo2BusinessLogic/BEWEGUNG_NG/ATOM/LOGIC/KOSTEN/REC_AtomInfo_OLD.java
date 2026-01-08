package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.indep.exceptions.myException;


/**
 * Klasse kapselt eine zusammenfassung von Bewegung_atom, den Stationen und dem Artikel
 * 
 * @author manfred
 * @date   26.11.2013
 */
public class REC_AtomInfo_OLD {

	
	
	private RECORD_BEWEGUNG_ATOM 		_oAtom 		= null;
	private RECORD_BEWEGUNG_STATION 	_oStationAblade = null;
	private RECORD_BEWEGUNG_STATION 	_oStationLade = null;
	private RECORD_ARTIKEL				_oArtikel   = null;
	private boolean 					_bInit      = false;
	
	
	
	public REC_AtomInfo_OLD(String sIDBewegungAtom ) {
		InitAtom(sIDBewegungAtom);
	}
	
	
	public REC_AtomInfo_OLD(RECORD_BEWEGUNG_ATOM oAtom){
		InitAtomExt(oAtom);
	}
	

	
	/**
	 * Initialisiert die Klasse ausgehend von der IDAtom
	 * @author manfred
	 * @date   02.12.2013
	 * @param idBewegungAtom
	 */
	private void InitAtom(String sIDBewegungAtom){
	
		// Atom lesen
		try {
			_oAtom = new RECORD_BEWEGUNG_ATOM(Long.parseLong(sIDBewegungAtom));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (myException e) {
			e.printStackTrace();
		}
		_bInit = true && _oAtom != null;
		
		// weitere Infos sammeln
		InitAtomExt(_oAtom);
	}
	

	/**
	 * Initialisiert die Klasse ausgehende vom RECORD-Objekt
	 * @author manfred
	 * @date   02.12.2013
	 * @param oAtom
	 */
	private void InitAtomExt (RECORD_BEWEGUNG_ATOM oAtom){
		// Atom zuordnen
		_oAtom = oAtom;
		_bInit = true && _oAtom != null;
		if (!_bInit) return;
		
		
		// die Stationen lesen und zuordnen ob WE oder WA
		try {
			RECLIST_BEWEGUNG_STATION rlStationen = _oAtom.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom();
			for (RECORD_BEWEGUNG_STATION s : rlStationen.values()){
				if (s.get_MENGENVORZEICHEN_lValue(0L).compareTo(0L) > 0) {
					_oStationAblade = s;
				} else  {
					_oStationLade = s;
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		_bInit &= (_oStationLade != null) && (_oStationAblade != null);
		
		
		// den Artikel ermitteln
		try {
			_oArtikel  = _oAtom.get_UP_RECORD_ARTIKEL_id_artikel();
		} catch (myException e) {
			e.printStackTrace();
		}
		_bInit &= (_oArtikel != null) ;

	}
	
	
	/**
	 * true, wenn die Klasse korrekt initialisiert wurde
	 * @author manfred
	 * @date   26.11.2013
	 * @return
	 */
	public boolean bInit(){
		return _bInit;
	}
	
	
	public String getIDBewegungAtom(){
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oAtom.get_ID_BEWEGUNG_ATOM_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	public String getIDAdresseAblade() {
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oStationAblade.get_ID_ADRESSE_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	public String getIDAdresseLade() {
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oStationLade.get_ID_ADRESSE_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	public String getIDBesitzerLade() {
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oStationLade.get_ID_ADRESSE_BESITZER_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	public String getIDBesitzerAblade() {
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oStationAblade.get_ID_ADRESSE_BESITZER_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	public String getIDEinheitPreis(){
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oArtikel.get_ID_EINHEIT_PREIS_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	

	public String getIDLieferbedingung(){
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oAtom.get_ID_LIEFERBEDINGUNGEN_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}

	public String getIDArtikel(){
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oAtom.get_ID_ARTIKEL_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}

	public String getIDArtikelBez(){
		String sRet = null;
		
		if (!_bInit) return sRet;
		
		try {
			sRet = _oAtom.get_ID_ARTIKEL_BEZ_cUF();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	/**
	 * Gibt die Bruttomenge des Atoms zurück
	 * @author manfred
	 * @date   26.11.2013
	 * @return
	 */
	public BigDecimal getMengeBrutto(){
		BigDecimal bdRet = null;
		
		if (!_bInit) return bdRet;
		
		try {
			bdRet = _oAtom.get_MENGE_bdValue(null);
		} catch (myException e) {
			e.printStackTrace();
		}
		return bdRet;
	}
	

	/**
	 * Gibt die Brutto-Menge umgerechnet in die Abrechnungseinheit zurück
	 * @author manfred
	 * @date   26.11.2013
	 * @return
	 */
	public BigDecimal getMengeBruttoAbrechnungseinheit(){
		BigDecimal bdRet = null;
		
		if (!_bInit) return bdRet;
		
		try {
			BigDecimal bdMenge = _oAtom.get_MENGE_bdValue(null);
			if (bdMenge != null ){
				BigDecimal bdDivisor = _oArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
				bdRet = bdMenge.divide(bdDivisor,3,BigDecimal.ROUND_HALF_UP);
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return bdRet;
	}

	
	
}
