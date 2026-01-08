package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_ABZUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_VERBUCHT;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_VERBUCHT_K;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_SETZKASTEN;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_SETZKASTEN_K;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.REC_Bewegung_Atom_Kosten;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.utils.bibROHSTOFF;

public class BL_BEWEGUNG_HANDLER implements ICallableTask{

	/**
	 *  der Vector mit allen SQL-Statements um die Bewegung im System zu schreiben, mit allen Vectoren und Atomen
	 */
	private Vector<String> m_vSQLCreate = new Vector<String>();
	private JtBewegung    m_Bewegung = null;

	// die eigenen Lieferadressen
	Vector<String>        m_vEigeneLieferadressen = new Vector<String>();
	
	// Cache für die Artikelzuordnung. Wird langsam gefüllt und verhindert mehrfachaufrufe des Records für den gleichen Artikel
	// Sollte DB-Zugriffe deutlich verringern
	HashMap<String, String> m_hmArtikeBezZuordnung = new HashMap<String, String>();
	HashMap<String, RECORD_ARTIKEL_BEZ> m_hmArtikeBezZuordnungObjekte = new HashMap<String, RECORD_ARTIKEL_BEZ>();
	
	// Cache für die Lieferbedingungen
	HashMap<String,String> m_hmLieferbedingungen = new HashMap<String, String>();
	
	// Puffer zur Umrechnung der Größen
	GROESSEN_Umrechnung     m_UmrechnungDerGroessen = null;
	
	// MIXED-FAELLE
	HashMap<Integer, Integer> faelle = new HashMap<Integer, Integer>();
	boolean bFuhreOnly = false;
	
	// Vektor, der alle ID_LAGER_KONTO hält, die im aktuellen Lauf konvertiert wurden, damit keine doppelten Korrekturen vorkommen 
	Vector<String>   	m_vHandbuchungenKonvertiert = new Vector<String>();
	
	Vector<String>   	m_vLeergut_Artikel = new Vector<String>();
	
	BL_Kostenberechnung m_BLKostenberechnung = null;
	
	MyDBToolBox         m_MyDBToolbox = null;
	
	// Defaultverhalten: Kalkuliere die Kosten mit der generierung der Atome
	private boolean 	_omitKostenCalc = false; 
	
	
	
	
	
	
	/**
	 * Per Default werden die Kosten-Sätze bei der Generierung der Atome generiert.
	 * Falls man das nicht möchte, muss man dies ausdrücklich verhindern mit omitKostenCalculation(true);
	 * 
	 * @author manfred
	 * @date   27.07.2012
	 */
	public BL_BEWEGUNG_HANDLER() {
		// DB-Toolbox holen und initialisieren
		m_MyDBToolbox = bibALL.get_myDBToolBox();
		
		m_Bewegung = new JtBewegung();
	    bibALL.get_ID_ADRESS_MANDANT();
		// eigene Lieferadressen puffern, da diese immer frisch gelesen werden.
		try {
			m_vEigeneLieferadressen.addAll( bibROHSTOFF.get_vEigeneLieferadressen() );
		} catch (myException e) {
			e.printStackTrace();
		}
		
		// Objekt zur Umrechnung der Größen initialisieren
		try {
			m_UmrechnungDerGroessen = new GROESSEN_Umrechnung();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Leergutartikel ermitteln und in den Vektor schreiben
		try {
			RECLIST_ARTIKEL listArtikel = new RECLIST_ARTIKEL(_DB.ARTIKEL$IST_LEERGUT + " = UPPER('Y') ", "");
			for (RECORD_ARTIKEL oArt : listArtikel.values()){
				m_vLeergut_Artikel.add(oArt.get_ID_ARTIKEL_cUF());
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Kostneberechnungs-Klasse puffern
		m_BLKostenberechnung = new  BL_Kostenberechnung();
		
		
		// Hashmap für die Lieferbedingungen erstellen und füllen
		// alle Lieferbedingungen in UpperCase
		// Key: Bezeichnung, Value: ID_LIEFERBEDINGUNGEN
		RECLIST_LIEFERBEDINGUNGEN lLiefBed;
		try {
			lLiefBed = new RECLIST_LIEFERBEDINGUNGEN("", "");
			for (RECORD_LIEFERBEDINGUNGEN rLief : lLiefBed.values()){
				m_hmLieferbedingungen.put(rLief.get_BEZEICHNUNG_cUF_NN("").trim().toUpperCase(), rLief.get_ID_LIEFERBEDINGUNGEN_cUF_NN("-1"));
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
	/**
	 * verhindert die erzeugung des Kosten-Satzes
	 * @author manfred
	 * @date 07.10.2016
	 *
	 * @param omitKostenCalc true: verhindert die Kostenkalkulation bei der Erzeugung des Datensatzes
	 * @return
	 */
	public BL_BEWEGUNG_HANDLER omitKostenCalculation ( boolean omitKostenCalc ){
		_omitKostenCalc = omitKostenCalc;
		return this;
	}

	

	
	
	public void test(String IDFuhre, String IDFuhreVOn, String IDFuhreBis, String DateStart, String DateEnd, boolean bWE, boolean bWA, boolean bS, boolean bMixed, boolean bLL, boolean bHand){
		
		
		GregorianCalendar calBegin = new GregorianCalendar();
		calBegin = new GregorianCalendar();
		String [][] asIds = new String[0][0];
				
		String sWhere = " nvl(ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' ";
		if (!bibALL.isEmpty(IDFuhre) ) {
			sWhere += " AND ID_VPOS_TPA_FUHRE = " + IDFuhre;
			bFuhreOnly = true;
			
		} else {
			String sTyp = "";
			sTyp += bWE ? "1," : "";
			sTyp += bWA ? "2," : "";
			sTyp += bMixed ? "3," : "";
			sTyp += bLL ? "4," : "";
			sTyp += bS ? "0," : "";
			
			if (sTyp.length() > 0){
				sTyp = sTyp.substring(0, sTyp.length() - 1 );
				sTyp = " AND TYP_STRECKE_LAGER_MIXED in (" + sTyp + ") ";
			}
			
			if (!bibALL.isEmpty(IDFuhreVOn) ){
				sWhere += " AND ID_VPOS_TPA_FUHRE >= " + IDFuhreVOn;
			}
			
			if (!bibALL.isEmpty(IDFuhreBis) ){
				sWhere += " AND ID_VPOS_TPA_FUHRE <= " + IDFuhreBis;
			}

			if (!bibALL.isEmpty(DateStart) ){
				sWhere += " AND to_char(DATUM_AUFLADEN,'yyyy-mm-dd') >= '" + DateStart + "'"; 
			}
			
			if (!bibALL.isEmpty(DateEnd) ){
				sWhere += " AND to_char(DATUM_AUFLADEN,'yyyy-mm-dd') <= '" + DateEnd + "'"; 
			}
			
			
			sWhere += sTyp;
		}
		
		
		// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
		String ssqlIDs = "SELECT ID_VPOS_TPA_FUHRE FROM "+ bibE2.cTO() +".JT_VPOS_TPA_FUHRE WHERE " + sWhere + " ORDER BY ID_VPOS_TPA_FUHRE ";
		

		asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
		
		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				RECORD_VPOS_TPA_FUHRE oFuhre = new RECORD_VPOS_TPA_FUHRE(asIds[iRun][0]);
				generateBewegungFromFuhre(oFuhre,true);
			} catch (myException e1) {
				e1.printStackTrace();
			} 
		}
		
		
		GregorianCalendar calEnd = new  GregorianCalendar();
		long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		DEBUG.System_println("Es wurden " + asIds.length + " Fuhren konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
		calBegin = new GregorianCalendar();
		
		//
		// ALLE handbuchungen updaten
		//
		m_vHandbuchungenKonvertiert.clear();
		if (bHand && !bFuhreOnly){
			// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
			ssqlIDs = "SELECT ID_LAGER_KONTO FROM "+ bibE2.cTO() +".JT_LAGER_KONTO WHERE BUCHUNG_HAND IS NOT NULL ORDER BY ID_LAGER_KONTO ";
			
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					RECORD_LAGER_KONTO oKonto = new RECORD_LAGER_KONTO(asIds[iRun][0]);
					generateVektorFromHandbuchung(oKonto);
				} catch (myException e1) {
					e1.printStackTrace();
				} 
			}
		}

		
		
		calEnd = new  GregorianCalendar();
		diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		
		// Anzahl der Fälle ausdrucken
		for (Integer oKey: faelle.keySet()){
			DEBUG.System_println("Fall " + oKey.toString() + " : " + faelle.get(oKey) + " mal!" , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
		DEBUG.System_println("Es wurden " + asIds.length + " Handbuchungen konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
		
	}

	
	/**
	 * schneidet eine Tranche aus dem Array raus und erzeugt eine IN-Clause
	 * @author manfred
	 * @date   01.10.2012
	 * @param asIds
	 * @param iBlock
	 * @param iBlockSize
	 * @return
	 */
	private String getInClause(String [][] asIds, int iBlock, int iBlockSize){
		String sRet = "";
		
		int iStart = iBlock*iBlockSize;
		int iSize = asIds.length;
		
		if (iSize <= 0 || iStart  > iSize) return null;
		
		StringBuilder sIDs = new StringBuilder();
		sIDs.append("(");
		
		for (int iCount = iStart; iCount < iSize; iCount++){
			sIDs.append(asIds[iCount][0]);
			sIDs.append(",");
		}
		
		sIDs.deleteCharAt(sIDs.lastIndexOf(","));
		
		sIDs.append(")");
		
		return sIDs.toString();
	}
	
	/**
	 * Hautproutine, generiert einen Satz aus BEWEGUNG-VEKTOREN-ATOMEN
	 * Es werden alle Fuhrenorte berücksichtigt 
	 * aus einer Fuhre heraus
	 * @author manfred
	 * @param idVposTpaFuhre
	 * @throws myException 
	 */
	public void generateBewegungFromFuhre(String idVposTpaFuhre, boolean bDeleteOldEntry) throws myException{
		RECORD_VPOS_TPA_FUHRE oRec = null;
		try {
			oRec = new RECORD_VPOS_TPA_FUHRE(idVposTpaFuhre);
		} catch (myException e) {
			e.printStackTrace();
		}

		
		// TODO: Fehlerbehandlung
		if (oRec == null){
			return;
		}
		
		generateBewegungFromFuhre(oRec,bDeleteOldEntry);
		
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @param oRecFuhre
	 * @throws myException 
	 */
	public void generateBewegungFromFuhre(RECORD_VPOS_TPA_FUHRE oRecFuhre, boolean bDeleteOldEntry) throws myException{
		// TODO: Fehlerbehandlung
		if (oRecFuhre == null) return;
		
		String sSQLPrefix = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION;
		sSQLPrefix = "";
		
		// Sqlstatements löschen
		m_Bewegung = null;
		m_vSQLCreate.clear();
		
		// generiere die Bewegung, abhängig von dem Fuhrentyp (WE/WA/STRECKE/LL/MIXED)
		int iFuhrentyp = oRecFuhre.get_TYP_STRECKE_LAGER_MIXED_lValue(Long.valueOf("-1")).intValue();
			
		
		switch (iFuhrentyp) {
		case 1: // WE
			BL_FUHREN_Transformation_WE oTransWE = new BL_FUHREN_Transformation_WE(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen);
			m_Bewegung = oTransWE.transformiereFuhre(oRecFuhre);
			break;
			
		case 2: // WA
			BL_FUHREN_Transformation_WA oTransWA = new BL_FUHREN_Transformation_WA(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen);
			m_Bewegung = oTransWA.transformiereFuhre(oRecFuhre);
			break;
			
		case 0: // STRECKE
			BL_FUHREN_Transformation_STRECKE oTransSTRECKE = new BL_FUHREN_Transformation_STRECKE(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen);
			m_Bewegung = oTransSTRECKE.transformiereFuhre(oRecFuhre);
			break;
			
		case 3: // MIXED
			BL_FUHREN_Transformation_MIXED oTransMIXED = new BL_FUHREN_Transformation_MIXED(m_vEigeneLieferadressen, m_UmrechnungDerGroessen, m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen);
			oTransMIXED.initFuhre(oRecFuhre);
			
			Integer iRet = new Integer(oTransMIXED.Fallunterscheidung());
			if (faelle.containsKey(iRet)){
				Integer o = faelle.get(iRet);
				o++;
				faelle.put(iRet, o);
			} else {
				faelle.put(iRet, 1);
			}
			
		    m_Bewegung = oTransMIXED.transformiereFuhre(oRecFuhre);
			
			break;
			
		case 4: // LAGER-LAGER
			BL_FUHREN_Transformation_LL oTransLL = new BL_FUHREN_Transformation_LL(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen);
			m_Bewegung = oTransLL.transformiereFuhre(oRecFuhre);
			break;

		default:
			DEBUG.System_println("Default: Nich implementiert: FuhrenID = " + oRecFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("?") + " Fuhrentyp: " + iFuhrentyp , DEBUG.DEBUG_FLAG_DIVERS1);
			break;
		}
		
		Vector<String> vsDeleteOld = new Vector<String>();
		
		
	
		if (bDeleteOldEntry){
			String sID = bibDB.EinzelAbfrage("select id_bewegung from " + bibE2.cTO()+ ".jt_bewegung where ID_VPOS_TPA_FUHRE = " + oRecFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("-1"),null,null,null );
			if (!bibALL.isEmpty(sID)){
				vsDeleteOld.add("DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG_ATOM 	WHERE ID_BEWEGUNG = " + sID + " " );
				vsDeleteOld.add("DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG = " + sID + " " );
				vsDeleteOld.add("DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG WHERE ID_BEWEGUNG = " + sID  + " " );
			}
		}

		
		String sSql = "";
		
		// Löschbefehle vorne weg
		m_vSQLCreate.addAll(vsDeleteOld);

		// "Gelöscht-Bedinungen anschauen:
		// Wenn eine Bewegung gelöscht ist, dann auch alle Vektoren und darin enthaltene Atome lösch-markieren
		// Wenn ein Vektor gelöscht ist, dann alle darin enthaltenen Atome lösch-markieren
		if (m_Bewegung != null){
			
			boolean bDelBewegung = ( m_Bewegung.getDeleted().sValue == null ? false : m_Bewegung.getDeleted().sValue.equalsIgnoreCase("Y") );
			
			// jetzt die Vektoren der Bewegung
			for (JtBewegung_Vektor vec: m_Bewegung.getJtBewegungsvektors()){
				boolean bDelVektor = (vec.getDeleted().sValue == null ? false : vec.getDeleted().sValue.equalsIgnoreCase("Y") );
				if (bDelBewegung){
					// unabhängig vom eigenen Zustand, falls die Bewegung gelöscht ist, dann auch den Vektor
					vec.setDeleted("Y");
				}
				for (JtBewegung_Vektor_Pos pos: vec.getJtBewegungVektorPoss()){
					
					// jetzt die Atome im VektorPos
					for (JtBewegung_Atom atom: pos.getJtBewegungsatoms()){
						// unabhängig vom eigenen Zustand, falls Bewegung oder Vektor gelöscht ist, dann auch das Atom
						if (bDelBewegung || bDelVektor){
							atom.setDeleted("Y");
						}
					}
				}
			}
		}
		

		 
		
		// die SQL-Kommandos generieren und in die Queue einbauen
		if (m_Bewegung != null){

			sSql = m_Bewegung.getSQLInsert();
			m_vSQLCreate.add(sSQLPrefix + sSql);
			
			// jetzt die Vektoren der Bewegung
			for (JtBewegung_Vektor vec: m_Bewegung.getJtBewegungsvektors()){
				sSql = vec.getSQLInsert();
				m_vSQLCreate.add(sSQLPrefix + sSql);
				
				// jetzt die Vektorpositionen im Vektor
				for (JtBewegung_Vektor_Pos pos: vec.getJtBewegungVektorPoss()){
					sSql = pos.getSQLInsert();
					m_vSQLCreate.add(sSQLPrefix + sSql);
					
					// jetzt die Atome in den Vektorpositionen
					for (JtBewegung_Atom atom: pos.getJtBewegungsatoms()){
						// Atom
						sSql = atom.getSQLInsert();
						m_vSQLCreate.add(sSQLPrefix + sSql);
						
						// Stationen
						for (JtBewegung_Station station : atom.getJtBewegungstations()){
							// Station Start
							sSql = station.getSQLInsert();
							m_vSQLCreate.add(sSQLPrefix + sSql);
							
						}
						
					}
				}
				
			}
		}

		
		// prüfen
		m_vSQLCreate.size();
		 
		 
		 
		// ausführen der Statements
		boolean bOK = true;
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vSQLCreate, false);
		
		
		// Kostenkalkulation verhindern?
		if (!_omitKostenCalc){
			//
			// Ermitteln der aktuellen ID_BEWEGUNG
			//
			Vector<String> m_vSQLKosten = new Vector<String>();
			String sIDBewegung = getCurrentBewegungID();
			
			// Kostenberechnung
			m_BLKostenberechnung.clearStatements();
			m_BLKostenberechnung.ErzeugeSQL_Kostensaetze_Fuer_Bewegung(sIDBewegung);
			m_vSQLKosten = m_BLKostenberechnung.getStatements();
			
			if (m_vSQLKosten.size() > 0){
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vSQLKosten, false);
			}
		}
		
		
		
		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
			DEBUG.System_println("ROLLBACK:  " + m_Bewegung.getIdVposTpaFuhre() , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
	}
	
	
	
	private String getCurrentBewegungID(){
		// Kostenberechnung: ermittlungd des aktuellen Atoms
		String sSqlCurrentATOMID = "SELECT SEQ_BEWEGUNG.currval FROM DUAL";
		String sRet = bibDB.EinzelAbfrage(sSqlCurrentATOMID);
		return sRet;
	}
	
	
	
	
	

	/**
	 * Konviertiert eine Handbuchung die auf dem Lager-Konto gemacht wurde in ein BewegungsVEKTOR+ATOM
	 * Es werden Umbuchungen immer komplett generiert 
	 * @author manfred
	 * @date   13.09.2013
	 * @param kto
	 */
	public void generateVektorFromHandbuchung(RECORD_LAGER_KONTO kto) {
		if (kto == null) return;
		String id_lager_konto = "";
		
		try {	id_lager_konto = kto.get_ID_LAGER_KONTO_cUF();
		} catch (myException e1) {	
			// TODO Auto-generated catch block
		}
		
		

		
		// falls der Lagereintrag schon verarbeitet wurde, dann wieder zurück. (Umbuchungen verarbeiten beide Einträge auf einmal) 
		if (m_vHandbuchungenKonvertiert.contains(id_lager_konto) ) return;
		
		// löschen der Statements...
		m_vSQLCreate.clear();
		
		String sSql = "";
		JtBewegung bew = null;

		try {
			BL_HAND_Transformation oHandTranform = new BL_HAND_Transformation(m_vEigeneLieferadressen, m_UmrechnungDerGroessen, m_hmArtikeBezZuordnungObjekte) ;
			
			// löschen der alten Daten
			loescheKorrektursaetze(kto);
			
			if (kto.get_BUCHUNG_HAND_cUF_NN("-").equalsIgnoreCase("U") ){
				// Umbuchung

				RECLIST_LAGER_KONTO listKorr;
				RECORD_LAGER_KONTO rec1 = null ;
				RECORD_LAGER_KONTO rec2 = null ;

				listKorr = new RECLIST_LAGER_KONTO("BUCHUNG_HAND IS NOT NULL AND ERZEUGT_AM = (SELECT ERZEUGT_AM FROM " + bibE2.cTO() +  ".JT_LAGER_KONTO K2 WHERE K2.ID_LAGER_KONTO = " + kto.get_ID_LAGER_KONTO_cUF_NN("-1") + ") ", 
													" ID_LAGER_KONTO");
				
				// listKorr darf nur max 2 Einträge haben!
				if (listKorr.values().size() > 0){
					rec1 = listKorr.get(0);
					if (listKorr.values().size() >1) {
						rec2 = listKorr.get(1);
					} else rec2 = null;
				}
				
				bew = oHandTranform.generiere_Bewegung_Umbuchung(rec1, rec2);
				schreibeBewegung(bew);
				
				if ( rec1 != null ) m_vHandbuchungenKonvertiert.add(rec1.get_ID_LAGER_KONTO_cUF());
				if ( rec2 != null ) m_vHandbuchungenKonvertiert.add(rec2.get_ID_LAGER_KONTO_cUF());
				
			} else {
				// Korrekturbuchung
				bew = oHandTranform.generiere_Bewegung_Korrketurbuchung(kto);
				schreibeBewegung(bew);

				m_vHandbuchungenKonvertiert.add(kto.get_ID_LAGER_KONTO_cUF());
			}
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	

	/**
	 * löscht alle Korrektursätze mit der vorgegebenen Kennung aus VEKTOR und ATOMEN
	 * @author manfred
	 * @date   27.09.2012
	 * @param Kennung  
	 * @throws myException 
	 */
	private void loescheKorrektursaetze(RECORD_LAGER_KONTO kto) throws myException{
		
		String sSQLPrefix = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION;
		sSQLPrefix = "";
		
		// ermitteln der bewegungsatome und -vektoren die gelöscht werden sollen, d.h. ermitteln des Vektors in dem der Korrektursatz liegt und dann löschen 
		// aller Atome des Vektors und den Vektor selber
		RECLIST_BEWEGUNG_ATOM list;
		list = new RECLIST_BEWEGUNG_ATOM("ID_LAGER_KONTO = " + kto.get_ID_LAGER_KONTO_cUF(),"");
		
		// alle Sätze dieses Lagerkontos löschen
		Vector<String> vsDeleteOld = new Vector<String>();
		for (RECORD_BEWEGUNG_ATOM a : list.values()){
			String sIDBewegung= a.get_ID_BEWEGUNG_cUF();
			vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG_ATOM 	 WHERE ID_BEWEGUNG = " + sIDBewegung );
			vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG = " + sIDBewegung  );
			vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BEWEGUNG 		 WHERE ID_BEWEGUNG = " + sIDBewegung  );
		}
		
		m_vSQLCreate.addAll(vsDeleteOld);
		
	}

	
	
	
	/**
	 * schreibt den Vektor in die DB
	 * @author manfred
	 * @date   27.09.2012
	 * @param vec
	 */
	public boolean schreibeBewegung(JtBewegung bew){
		
		// die Statements erzeugen
		m_vSQLCreate.addAll( getSQLForBewegung(bew) ) ;
		
		// prüfen
		m_vSQLCreate.size();
			 
		// ausführen der Statements
		boolean bOK = true;
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vSQLCreate, true);
		
		return bOK;
	
	}

	

	
	/**
	 * Gibt ein Vektor aus SQL-Statements zurück, die die Bewegung anlegen
	 * @param vec
	 * @return
	 */
	public Vector<String> getSQLForBewegung(JtBewegung bew){
		String sSQLPrefix = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION;
		sSQLPrefix = "";
		
		Vector<String> vSql = new Vector<String>();
		
		String sSql = null;
		
		// SQL für die Bewegung
		sSql = bew.getSQLInsert();
		vSql.add(sSQLPrefix + sSql);
		
		for (JtBewegung_Vektor vec: bew.getJtBewegungsvektors()){
			// SQL für den Vektor 
			sSql = vec.getSQLInsert();
			vSql.add(sSQLPrefix + sSql);
			
			for (JtBewegung_Vektor_Pos pos: vec.getJtBewegungVektorPoss()){
				sSql = pos.getSQLInsert();
				vSql.add(sSQLPrefix + sSql);
				
				// jetzt die Vektoren der Atome
				for (JtBewegung_Atom atom: pos.getJtBewegungsatoms()){
					// Atom
					sSql = atom.getSQLInsert();
					vSql.add(sSQLPrefix + sSql);
					
					// Stationen
					for (JtBewegung_Station station : atom.getJtBewegungstations()){
						// Station Start
						sSql = station.getSQLInsert();
						vSql.add(sSQLPrefix + sSql);
					}
					
					// Kosten ins atom eintragen
					for (REC_Bewegung_Atom_Kosten o: atom.getRecKosten()){
						sSql = o.getSQLInsert();
						vSql.add(sSQLPrefix + sSql);
					}
				}
			}
		}
	
		return vSql;
	
	}

	
	

	
	
	/**
	 * Hilfsklasse für das Aufräumen der Tabellen
	 * @author manfred
	 * @date 05.10.2016
	 *
	 */
	class sT {
		public String key;
		public String value;
		
		sT(String s_key, String s_value){
			key = s_key;
			value = s_value;
		}
	}

	
	/**
	 * Löscht Bewegungssätze, die aus der alten Welt kommen, abhängig von den Schaltern:
	 * @author manfred
	 * @date 05.10.2016
	 *
	 * @param bCleanFuhren 	Löscht alle Bewegungen die aus Fuhren resultieren
	 * @param bCleanHandbuchungLager	löscht alle Bewegungen, die aus Lagerbuchungen Hand/Umbuchung resultieren
	 */
	public MyE2_MessageVector cleanBewegungssaetze(boolean bCleanFuhren, boolean bCleanHandbuchungLager){
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		Vector<sT> vCleanFuhren = new Vector<>();
		
		if (bCleanFuhren){
			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_ATOM.fullTabName(),	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG.fullTabName(),		"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null " ) );
		}
		 
		if (bCleanHandbuchungLager){
			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_ATOM.fullTabName() ,	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG.fullTabName(),			"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y' " ) );
		}
		 
		 
		for (sT tupel: vCleanFuhren){
			 boolean bOK = bibDB.ExecSQL(tupel.value, true);
			 mv.add( new MyE2_Info_Message(tupel.key + (bOK ? "OK" : "Error!!!") + "\n" ) );
		}
		
		 
		// die Sequencer neu aufbauen
		Vector<String> vNewSequencer = new Vector<>();
		vNewSequencer.add(BEWEGUNG.fullTabName());
		vNewSequencer.add(BEWEGUNG_VEKTOR.fullTabName());
		vNewSequencer.add(BEWEGUNG_VEKTOR_POS.fullTabName());
		vNewSequencer.add(BEWEGUNG_ATOM.fullTabName());
		vNewSequencer.add(BEWEGUNG_STATION.fullTabName());
		vNewSequencer.add(BEWEGUNG_ATOM_ABZUG.fullTabName());
		vNewSequencer.add(BEWEGUNG_ATOM_KOSTEN.fullTabName());
		 
		for (String sTable: vNewSequencer) {
				Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(sTable, null);
				mv._add(oSeq.Build_New_SequenceBased_on_DatabaseQuery() ) ;
		}

				 
		 
		return mv;
		 
	}
	
	
	
	
	/**
	 * löscht die Setzkaesten   
	 * @author manfred
	 * @date 05.10.2016
	 *
	 */
	public MyE2_MessageVector cleanSetzkasten(){
		MyE2_MessageVector mv = new MyE2_MessageVector();

		Vector<sT> vCleanFuhren = new Vector<>();

		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_ATOM_VERBUCHT.fullTabName(),			"DELETE FROM " + BEWEGUNG_ATOM_VERBUCHT.fullTabName()));
		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_SETZKASTEN.fullTabName(),				"DELETE FROM " + BEWEGUNG_SETZKASTEN.fullTabName()));
		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_ATOM_VERBUCHT_K.fullTabName(),			"DELETE FROM " + BEWEGUNG_ATOM_VERBUCHT_K.fullTabName()));
		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_SETZKASTEN_K.fullTabName(),			"DELETE FROM " + BEWEGUNG_SETZKASTEN_K.fullTabName()));

		for (sT tupel : vCleanFuhren) {
			boolean bOK = bibDB.ExecSQL(tupel.value, true);
			mv.add(new MyE2_Info_Message(tupel.key + (bOK ? "OK" : "Error!!!") + "\n"));
		}

		// die Sequencer neu aufbauen
		Vector<String> vNewSequencer = new Vector<>();
		vNewSequencer.add(BEWEGUNG_ATOM_VERBUCHT.fullTabName());
		vNewSequencer.add(BEWEGUNG_ATOM_VERBUCHT_K.fullTabName());
		vNewSequencer.add(BEWEGUNG_SETZKASTEN.fullTabName());
		vNewSequencer.add(BEWEGUNG_SETZKASTEN_K.fullTabName());

		for (String sTable : vNewSequencer) {
			Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(sTable, null);
			mv._add(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
		}

		return mv;
	}

	
	
	
	
	
	
	/***********
	 * 
	 *
	 * 
	 * Bereich für die Automatisierte Umsetzung der Fuhren in den neuen Bewegungssatz
	 * 
	 * 
	 * 
	 *********/

	// Automator-
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();

	

	/**
	 * 
	 * Hauptroutine zum automatischen ausführen des Aufbaus der neuen Datensätze
	 * - es werden immer alle Fuhren generiert, die noch nicht in den Atomen vorhanden sind
	 * zusätzlich: 
	 * 		- wenn parameter days=x angegeben ist, werden alle Fuhren neu generiert, die in den letzten x-Tagen geändert wurden
	 *        damit werden auch geänderte Fuhren erfasst. -> wenn die Batch jeden tag läuft, sollten immer alle Fuhren aktuell sein
	 *     
	 *  curl "http://localhost:8080/rohstoff_app/batch?user=batchuser&pw=xxxx&task=convert2atom"
	 * 
	 */
	@Override
	public boolean runTask() {

		
		GregorianCalendar calBegin ;
		GregorianCalendar calEnd;
		long diff_in_sec ;
		
		String sSql1;
		String sSql2;
		String sSql3;
		String ssqlIDs = "";
		String [][] asIds;
		String sID = "";
		
		boolean bConvertHandbuchungen = false;
		try {
			bConvertHandbuchungen = ENUM_MANDANT_DECISION.CONVERT_HANDBUCHUNG_LAGER_TO_ATOM.is_YES();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		boolean bConvertLagerbestandserfassungen = false;
		try {
			bConvertLagerbestandserfassungen = ENUM_MANDANT_DECISION.CONVERT_LAGERBESTANDSERFASSUNG_TO_ATOM.is_YES();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		// zuerst alle Handbuchungen
		if (bConvertHandbuchungen || bConvertLagerbestandserfassungen){
			//
			// Handbuchungen korrigieren
			//
			calBegin = new GregorianCalendar();

			
			if (bConvertHandbuchungen){
				//		-- alle HANDBUCHUNGEN ,die möglicherweise im aktuellen Tag geändert wurden
				//		-- muss gemacht werden, da per Programm keine Zeitangaben beim Timestamp übernommen wird :-(
				sSql1 = 	" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') >  trunc(sysdate  - 1 ,'DD') AND L.BUCHUNG_HAND IS NOT NULL " ;
				
				//     -- alle HANDBUCHUNGEN, die geändert wurden, und ein Gap des Änderungsdatums > 1 ist
				sSql2 = 	" SELECT L.ID_LAGER_KONTO FROM  " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						"					INNER JOIN  " + bibE2.cTO() +".JT_BEWEGUNG_ATOM A " +
						" 					ON A.ID_LAGER_KONTO = L.ID_LAGER_KONTO AND A.ID_MANDANT = L.ID_MANDANT " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') - trunc(A.LETZTE_AENDERUNG,'DD') >= 1 "	+ 
						" AND L.BUCHUNG_HAND IS NOT NULL " ;
				
				// 		--  alle HANDBUCHUNGEN, die noch nicht verarbeitet wurden
				sSql3 =  " SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" 				LEFT OUTER JOIN " + bibE2.cTO() +".JT_BEWEGUNG_ATOM A ON L.ID_LAGER_KONTO = A.ID_LAGER_KONTO AND L.ID_MANDANT = A.ID_MANDANT " +
						" WHERE A.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL " ;
				
				ssqlIDs = sSql1 + " UNION " + sSql2 + " UNION " + sSql3 + " order by 1 ";
			
			} else if (bConvertLagerbestandserfassungen){
			
				ssqlIDs =  " SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" 				LEFT OUTER JOIN " + bibE2.cTO() +".JT_BEWEGUNG_ATOM A ON L.ID_LAGER_KONTO = A.ID_LAGER_KONTO AND L.ID_MANDANT = A.ID_MANDANT " +
						" 				WHERE A.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL " +
						"				AND upper( L.BEMERKUNG ) = UPPER('LAGERBESTANDSERFASSUNG') " ;
			}

			
			
			asIds = new String[0][0];
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			RECORD_LAGER_KONTO oLag = null;
			sID = "";
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					oLag = new RECORD_LAGER_KONTO(asIds[iRun][0]);
					sID = oLag.get_ID_LAGER_KONTO_cUF_NN("N/A");
					generateVektorFromHandbuchung(oLag);
					
				} catch (myException e1) {
					e1.printStackTrace();
					
					m_TaskMessages.add("Fehler beim Konvertieren der Handbuchung " + sID);
				} catch (Exception e_default){
					e_default.printStackTrace();
				}
			}
			
			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			
			m_TaskMessages.add("Es wurden " + asIds.length + " Handbuchungen konvertiert in " + diff_in_sec + " sec.");

		}
		
		
		

		// Fuhrenbuchungen Konvertieren
		calBegin = new GregorianCalendar();
		
		//		-- alle fuhren ,die möglicherweise im aktuellen Tag geändert wurden
		//		-- muss gemacht werden, da per Programm keine Zeitangaben beim Timestamp übernommen wird :-(
		sSql1 = 	" SELECT F.id_vpos_tpa_fuhre FROM "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') >  trunc(sysdate  - 1 ,'DD') " ;
		//     -- alle Fuhren, die geändert wurden, und ein Gap des Änderungsdatums > 1 ist
		sSql2 = 	" SELECT F.id_vpos_tpa_fuhre FROM "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
						" inner join "+ bibE2.cTO() +".jt_bewegung B " +
						"      on F.ID_MANDANT = B.ID_MANDANT and F.ID_VPOS_TPA_FUHRE = B.ID_VPOS_TPA_FUHRE " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') - trunc(B.LETZTE_AENDERUNG,'DD') >= 1 " ;
		
		// 		--  alle fuhren, die noch nicht verarbeitet wurden
		
		// Nutzen des Outer-Joins mit null-IDs geht deutlich schneller als das "not in" statement
		sSql3 =  " SELECT F.id_vpos_tpa_fuhre FROM  "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
				" left outer join  "+ bibE2.cTO() +".JT_BEWEGUNG B on F.id_vpos_tpa_fuhre = B.ID_VPOS_TPA_FUHRE and F.id_mandant = B.ID_MANDANT " +
		" WHERE B.ID_VPOS_TPA_FUHRE is null " +
		" and NVL(F.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " 
		;
		
		

		ssqlIDs = sSql1 + " UNION " + sSql2 + " UNION " + sSql3 + " order by 1 ";
		
		asIds = new String[0][0];
		asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
		RECORD_VPOS_TPA_FUHRE oFuhre = null;
		sID = "";

		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				oFuhre = new RECORD_VPOS_TPA_FUHRE(asIds[iRun][0]);
				sID = oFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("N/A");
				generateBewegungFromFuhre(oFuhre,true);
			} catch (myException e1) {
				e1.printStackTrace();
				
				m_TaskMessages.add("Fehler beim Konvertieren der Fuhre " + sID);
			} catch (Exception e_default){
				e_default.printStackTrace();
			}
		}
		
		
		
		calEnd = new  GregorianCalendar();
		diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		
		// anzahl der Faelle ausdrucken
		for (Integer oKey: faelle.keySet()){
			m_TaskMessages.add("Fall " + oKey.toString() + " : " + faelle.get(oKey) + " mal!");
		}
		
		m_TaskMessages.add("Es wurden " + asIds.length + " Fuhren konvertiert in " + diff_in_sec + " sec.");
		


		
		return true;
	}




	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters  = hmParameters;		
	}




	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}
	
}
