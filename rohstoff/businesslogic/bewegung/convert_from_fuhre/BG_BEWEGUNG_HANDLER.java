package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_VERBUCHT;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_VERBUCHT_K;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_SETZKASTEN;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_SETZKASTEN_K;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_Lager_Konto;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_TPA_FUHRE;
import rohstoff.utils.bibROHSTOFF;

@Deprecated
public class BG_BEWEGUNG_HANDLER implements ICallableTask{

	/**
	 *  der Vector mit allen SQL-Statements um die Bewegung im System zu schreiben, mit allen Vectoren und Atomen
	 */

	// die eigenen Lieferadressen
	Vector<String>        				m_vEigeneLieferadressen = new Vector<String>();
	
	// Cache für die Artikelzuordnung. Wird langsam gefüllt und verhindert mehrfachaufrufe des Records für den gleichen Artikel
	// Sollte DB-Zugriffe deutlich verringern
	HashMap<String, String> 			m_hmArtikeBezZuordnung = new HashMap<String, String>();
	HashMap<String, RECORD_ARTIKEL_BEZ> m_hmArtikeBezZuordnungObjekte = new HashMap<String, RECORD_ARTIKEL_BEZ>();
	RecList20 							m_rlArtikel = null;
	
	
		
	// Cache für die Lieferbedingungen
	HashMap<String,String> 				m_hmLieferbedingungen = new HashMap<String, String>();
	
	// Puffer zur Umrechnung der Größen
	GROESSEN_Umrechnung     			m_UmrechnungDerGroessen = null;
	
	//Cache für den Ländercode
	HashMap<String, Long>               m_hmLaenderCode = new HashMap<>();
	
	// MIXED-FAELLE
	HashMap<Integer, Integer> 			faelle = new HashMap<Integer, Integer>();
	boolean 							bFuhreOnly = false;
	boolean 							bLagerOnly = false;
	
	
	// Vektor, der alle ID_LAGER_KONTO hält, die im aktuellen Lauf konvertiert wurden, damit keine doppelten Korrekturen vorkommen 
	Vector<String>   					m_vHandbuchungenKonvertiert = new Vector<String>();
	
	Vector<String>   					m_vLeergut_Artikel = new Vector<String>();
	
	BL_Kostenberechnung 				m_BLKostenberechnung = null;
	
	MyDBToolBox         				m_MyDBToolbox = null;
	
	// Kalkuliere die Kosten mit der generierung der Atome
	private boolean 					m_omitKostenCalc = true; 
	
	private jt_bg_vektor 				m_Vektor = null;
	
	
	
	
	/**
	 * Per Default werden die Kosten-Sätze bei der Generierung der Atome generiert.
	 * Falls man das nicht möchte, muss man dies ausdrücklich verhindern mit omitKostenCalculation(true);
	 * 
	 * @author manfred
	 * @date   27.07.2012
	 */
	public BG_BEWEGUNG_HANDLER() throws myException {
		m_MyDBToolbox = bibALL.get_myDBToolBox();

		// eigene Lieferadressen puffern, da diese immer frisch gelesen werden.
		try {
			m_vEigeneLieferadressen.addAll( bibROHSTOFF.get_vEigeneLieferadressen() );
		} catch (myException e) {
			throw new myException("BG_BEWEGUNG_HANDLER::eigene Lieferadressen können nicht ermittelt werden.");
		}
		
		// Objekt zur Umrechnung der Größen initialisieren
		try {
			m_UmrechnungDerGroessen = new GROESSEN_Umrechnung();
		} catch (myException e) {
			throw new myException("BG_BEWEGUNG_HANDLER::Umrechnungsfaktoren können nicht ermittelt werden.");
		}
		
		// Leergutartikel ermitteln und in den Vektor schreiben
		try {
			SEL sel = new SEL("*").FROM(_TAB.artikel).WHERE(new vgl(ARTIKEL.ist_leergut,COMP.EQ, "Y"));
			RecList20 rlArtikel = new RecList20(_TAB.artikel)._fill(sel.s());
			for (Rec20 r: rlArtikel){
				m_vLeergut_Artikel.add(r.get_ufs_dbVal(ARTIKEL.id_artikel));
			}
		} catch (myException e) {
			throw new myException("BG_BEWEGUNG_HANDLER::Leergutartikel können nicht ermittelt werden.",e);
		}

		
		//
		// Alle Artikel
		//
		try {
			SEL sel = new SEL("*").FROM(_TAB.artikel);
			m_rlArtikel = new RecList20(_TAB.artikel)._fill(sel.s());
		} catch (Exception e) {
			throw new myException("BG_BEWEGUNG_HANDLER::Artikel können nicht geladen werden.");
		}
		
		// Hashmap für die Lieferbedingungen erstellen und füllen
		// alle Lieferbedingungen in UpperCase
		// Key: Bezeichnung, Value: ID_LIEFERBEDINGUNGEN
		try {
			SEL sel = new SEL("*").FROM(_TAB.lieferbedingungen);
			RecList20 rl = new RecList20(_TAB.lieferbedingungen)._fill(sel.s());
			for (Rec20 r: rl){
			
				m_hmLieferbedingungen.put(r.get_ufs_dbVal(LIEFERBEDINGUNGEN.bezeichnung, "").trim().toUpperCase(), r.get_ufs_dbVal(LIEFERBEDINGUNGEN.id_lieferbedingungen,"-1"));
			}
		} catch (Exception e) {
			throw new myException("BG_BEWEGUNG_HANDLER::Lieferbedingungen können nicht ermittelt werden.");
		}
		
		
		// Ländercode cachen
		try {
			RecList20 rl = new RecList20(_TAB.land)._fill("","");
			for (Rec20 r: rl){
				m_hmLaenderCode.put(r.get_ufs_dbVal(LAND.laendercode), r.get_raw_resultValue_Long(LAND.id_land, -1L)  );
			}
		} catch (Exception e) {
			throw new myException("BG_BEWEGUNG_HANDLER::Ländercodes können nicht ermittelt werden.");
		}
		
		
		
//		// Kostneberechnungs-Klasse puffern
//		m_BLKostenberechnung = new  BL_Kostenberechnung();
	
	}

	
	/**
	 * verhindert die erzeugung des Kosten-Satzes
	 * @author manfred
	 * @date 07.10.2016
	 *
	 * @param omitKostenCalc true: verhindert die Kostenkalkulation bei der Erzeugung des Datensatzes
	 * @return
	 */
	public BG_BEWEGUNG_HANDLER omitKostenCalculation ( boolean omitKostenCalc ){
		m_omitKostenCalc = omitKostenCalc;
		return this;
	}

	

	
	/**
	 * Testroutine zum konvertieren einzelner Fuhren oder Fuhrenmengen 
	 * @author manfred
	 *
	 * @param IDFuhre
	 * @param IDFuhreVOn
	 * @param IDFuhreBis
	 * @param DateStart
	 * @param DateEnd
	 * @param bWE
	 * @param bWA
	 * @param bS
	 * @param bMixed
	 * @param bLL
	 * @param bHand
	 */
	public void test(String IDFuhre, String IDLager, String IDFuhreVOn, String IDFuhreBis, String DateStart, String DateEnd, boolean bWE, boolean bWA, boolean bS, boolean bMixed, boolean bLL, boolean bHand,boolean bUsePreparedStatements){
		
		
		GregorianCalendar calBegin = new GregorianCalendar();
		calBegin = new GregorianCalendar();
		String [][] asIds = new String[0][0];
		
		
		
		String sWhere = " nvl(ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' ";
		String sWhereLager = " BUCHUNG_HAND IS NOT NULL ";
		if (!bibALL.isEmpty(IDFuhre) ) {
			
			sWhere += " AND ID_VPOS_TPA_FUHRE = " + IDFuhre;
			bFuhreOnly = true;
			
		} else if (!bibALL.isEmpty(IDLager)){
			sWhereLager += " AND ID_LAGER_KONTO = " + IDLager;
			bLagerOnly = true;
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
		
		String ssqlIDs = "";
		
		
		GregorianCalendar calEnd;
		long diff_in_sec = 0L;
		
		//
		//  Fuhrenbuchungen
		//
		if (!bLagerOnly){
			// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
			ssqlIDs = "SELECT ID_VPOS_TPA_FUHRE FROM "+ bibE2.cTO() +".JT_VPOS_TPA_FUHRE WHERE " + sWhere + " ORDER BY ID_VPOS_TPA_FUHRE ";
			
			
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					Rec20_VPOS_TPA_FUHRE_ext recFuhre = new Rec20_VPOS_TPA_FUHRE_ext();
					recFuhre._fill_id(asIds[iRun][0]);
					generateBGVektorFromFuhre_Prepared(recFuhre, true);
				} catch (myException e1) {
					e1.printStackTrace();
				} 
			}

			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			DEBUG.System_println("Es wurden " + asIds.length + " Fuhren konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS2);
			
		}

		
		
		
		//
		// Handbuchungen 
		//
		calBegin = new GregorianCalendar();
		m_vHandbuchungenKonvertiert.clear();
		if (( bHand && !bFuhreOnly) || bLagerOnly ){
			// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
			ssqlIDs = "SELECT ID_LAGER_KONTO FROM "+ bibE2.cTO() +".JT_LAGER_KONTO WHERE "+  sWhereLager + " ORDER BY ID_LAGER_KONTO ";
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					Rec20_Lager_Konto oKonto = new Rec20_Lager_Konto();
					oKonto._fill_id(asIds[iRun][0]);
					
					generateBGVektorFromHandbuchung(oKonto,true);
				} catch (myException e1) {
					e1.printStackTrace();
				} 
			}
			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			DEBUG.System_println("Es wurden " + asIds.length + " Handbuchungen konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS2);
		}

		// Anzahl der Fälle ausdrucken
		for (Integer oKey: faelle.keySet()){
			DEBUG.System_println("Fall " + oKey.toString() + " : " + faelle.get(oKey) + " mal!" , DEBUG.DEBUG_FLAG_DIVERS2);
		}
		
		
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
	 * 
	 * @author manfred
	 * @param oRecFuhre
	 * @throws myException 
	 * @throws SQLException 
	 */
	public void generateBGVektorFromFuhre_Prepared(Rec20_VPOS_TPA_FUHRE_ext oRecFuhre, boolean bDeleteOldEntry) throws myException{
		if (oRecFuhre == null) return;
		Long l = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
		String idFuhre = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).toString();
		
		if (idFuhre == null) return;
		
		
		int iFuhrentyp = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.typ_strecke_lager_mixed).intValue();
		
		switch (iFuhrentyp) {
		case 1: // WE
			BG_FUHREN_Transformation_WE oTransWE = new BG_FUHREN_Transformation_WE(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen, m_hmLaenderCode);
			m_Vektor = oTransWE.transformiereFuhre(oRecFuhre);
			break;
			
		case 2: // WA
			BG_FUHREN_Transformation_WA oTransWA = new BG_FUHREN_Transformation_WA(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen, m_hmLaenderCode);
			m_Vektor = oTransWA.transformiereFuhre(oRecFuhre);
			break;
			
		case 0: // STRECKE
			BG_FUHREN_Transformation_STRECKE oTransSTRECKE = new BG_FUHREN_Transformation_STRECKE(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen, m_hmLaenderCode);
			m_Vektor = oTransSTRECKE.transformiereFuhre(oRecFuhre);
			break;
		case 3: // MIXED
			BG_FUHREN_Transformation_MIXED oTransMIXED = new BG_FUHREN_Transformation_MIXED(m_vEigeneLieferadressen, m_UmrechnungDerGroessen, m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen,m_hmLaenderCode);
		    m_Vektor = oTransMIXED.transformiereFuhre(oRecFuhre);

		    Integer iRet = new Integer(oTransMIXED.Fallunterscheidung());
		    if (faelle.containsKey(iRet)){
		    	Integer o = faelle.get(iRet);
		    	o++;
		    	faelle.put(iRet, o);
		    } else {
		    	faelle.put(iRet, 1);
		    }
			
			break;
			
		case 4: // LAGER-LAGER
			BG_FUHREN_Transformation_LL oTransLL = new BG_FUHREN_Transformation_LL(m_vEigeneLieferadressen,m_UmrechnungDerGroessen,m_hmArtikeBezZuordnung,m_vLeergut_Artikel,m_hmLieferbedingungen, m_hmLaenderCode);
			m_Vektor = oTransLL.transformiereFuhre(oRecFuhre);
			break;

		default:
			DEBUG.System_println("Default: Nich implementiert: FuhrenID = " + idFuhre + " Fuhrentyp: " + iFuhrentyp , DEBUG.DEBUG_FLAG_DIVERS1);
			break;
		}

		
		Vector<MyDBStatementPrepared> m_vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp ;
		
		
		// 
		// alte Einträge löschen
		//
//		if (bDeleteOldEntry){
//			String sID = null;
//
//			SqlStringExtended s = new SqlStringExtended("select distinct id_bg_vektor from " + _TAB.bg_atom.fullTableName() + " WHERE "	+ BG_ATOM.id_vpos_tpa_fuhre.fn() + " = ? " );
//			s.getValuesList().add(new Param_Long(Long.parseLong(idFuhre)) );
//			
//			String [][] ids = m_MyDBToolbox.EinzelAbfrageInArray(s);
//			for(int i=0; i<ids.length; i++){
//				sID = ids[i][0];
//				Long lID = Long.parseLong(sID);
//				m_vPrepSTMT.addAll(generateDeleteStmts_BGVektorPrepared(lID));
//			}
//			
//		}
	

		//
		// Erzeugung der neuen Einträge
		//
		m_vPrepSTMT.addAll(this.createSTMTForInsert(m_Vektor));
		
		
		
		// jetzt die Daten per Prepared-Statement einfügen...
		boolean bOK = true;
		
		for (MyDBStatementPrepared stmt: m_vPrepSTMT ){
			
			bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(stmt, false);
		}
		
		
		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
			DEBUG.System_println("ROLLBACK:  " + m_Vektor.get_jt_bg_atoms().firstElement().getID_VPOS_TPA_FUHRE().Value() , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
		
	}
	

	

	/**
	 * Handbuchungen erzeugen für BG-Struktur
	 * @author manfred
	 * @date 14.02.2018
	 *
	 * @param kto
	 * @throws myException
	 */
	public void generateBGVektorFromHandbuchung(Rec20_Lager_Konto kto,boolean bDeleteOldEntries) throws myException {
		if (kto == null) return;
		String id_lager_konto = "";
				
		try {	
			id_lager_konto = kto.get_ufs_dbVal(LAGER_KONTO.id_lager_konto,"-1");
		} catch (myException e1) {
			throw new myException(S.ms("LagerID kann nicht gefunden werden").CTrans(), e1);
		}
		
		
		// falls der Lagereintrag schon verarbeitet wurde, dann wieder zurück. (Umbuchungen verarbeiten beide Einträge auf einmal) 
		if (m_vHandbuchungenKonvertiert.contains(id_lager_konto) ) return;
		
		
		// löschen der Statements...
		String sID = null;
		Long lID = null; 
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp ;
		


		
		
		String sSql = "";
		jt_bg_vektor vek = null;
		
		boolean bOK = true;
		try {
			BG_HAND_Transformation oHandTranform = new BG_HAND_Transformation(m_vEigeneLieferadressen, m_UmrechnungDerGroessen, m_hmArtikeBezZuordnungObjekte, m_rlArtikel) ;
			
			// löachen der alten einträge
			if (bDeleteOldEntries){
				// alle Sätze dieses Lagerkontos löschen
				String [][] ids = bibDB.EinzelAbfrageInArray("select id_bg_vektor from " + bibE2.cTO()+ ".jt_bg_ladung where ID_LAGER_KONTO = " + id_lager_konto);
				
				for(int i=0; i<ids.length; i++){
					sID = ids[i][0];
					lID = Long.parseLong(sID);
					_vPrepSTMT.addAll( generateDeleteStmts_BGVektorPrepared(lID) );
				}
			}		
			
			
			
			if (kto.get_ufs_dbVal(LAGER_KONTO.buchung_hand, "-").equalsIgnoreCase("U") ){
				// Umbuchung

				RecList20 listKorr;
				Rec20_Lager_Konto rec1 = null ;
				Rec20_Lager_Konto rec2 = null ;

				listKorr = new RecList20(_TAB.lager_konto)._fill("BUCHUNG_HAND IS NOT NULL AND ERZEUGT_AM = (SELECT ERZEUGT_AM FROM " + bibE2.cTO() +  ".JT_LAGER_KONTO K2 WHERE K2.ID_LAGER_KONTO = " + id_lager_konto + ") ", 
													" ID_LAGER_KONTO");
				
				// listKorr darf nur max 2 Einträge haben!
				if (listKorr.values().size() > 0){
					rec1 = new Rec20_Lager_Konto(listKorr.get(0));
					if (listKorr.values().size() >1) {
						rec2 = new Rec20_Lager_Konto(listKorr.get(1));
					} else rec2 = null;
				}
				
				vek = oHandTranform.generiere_Vektor_Umbuchung(rec1, rec2);

				if ( rec1 != null ) m_vHandbuchungenKonvertiert.add(rec1.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
				if ( rec2 != null ) m_vHandbuchungenKonvertiert.add(rec2.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
				
			} else {
				// Korrekturbuchung
				vek = oHandTranform.generiere_Vektor_Korrekturbuchung(kto);

				m_vHandbuchungenKonvertiert.add(kto.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
			}
			
			// die SQL-Kommandos generieren und in die Queue einbauen
			_vPrepSTMT.addAll(this.createSTMTForInsert(vek));
			
			
			for (MyDBStatementPrepared stmt: _vPrepSTMT ){
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(stmt, false);
			}
			
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bOK = false;
		}

		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
			DEBUG.System_println("ROLLBACK:  " + m_Vektor.getSQLInsert()  , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
	}

	
	
	/**
	 * Erzeugt die sql-Statements für den Insert des Vektors
	 * @author manfred
	 * @date 15.02.2018
	 *
	 * @param vek
	 * @param bUsePrefix
	 * @return
	 */
	public Vector<String> createSQLForInsert(jt_bg_vektor vek, boolean bUsePrefix){
		String sSql = "";

		String sSQLPrefix = "";
		if (bUsePrefix) {
			sSQLPrefix = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION;
		}
		
		Vector<String> vSQL = new Vector<String>();
		
		// die SQL-Kommandos generieren und in die Queue einbauen
		if (vek != null){

			sSql = vek.getSQLInsert();
			vSQL.add(sSQLPrefix + sSql);
			
			// jetzt die Vektoren der Bewegung
			for (jt_bg_atom atom: vek.get_jt_bg_atoms()){
				sSql = atom.getSQLInsert();
				vSQL.add(sSQLPrefix + sSql);
				
				// jtzt die station und ladung...
				for (jt_bg_ladung ladung: atom.get_jt_bg_ladungs()){

					jt_bg_station station = ladung.get_jt_bg_station();
					if (station != null){
						sSql = station.getSQLInsert(); 
						vSQL.add(sSQLPrefix + sSql);
					}
					
					sSql = ladung.getSQLInsert();
					vSQL.add(sSQLPrefix + sSql);
				}
			}
		}
		return vSQL;
	}

	
	

	/**
	 * gibt ein Vektor von prepared-Statements zurück um die Objekte zu erzeugen
	 * @author manfred
	 * @date 19.02.2018
	 *
	 * @param vek
	 * @return
	 */
	public Vector<MyDBStatementPrepared> createSTMTForInsert(jt_bg_vektor vek ){
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>(); 
		
		String sSql = "";
		
		
		// die SQL-Kommandos generieren und in die Queue einbauen
		if (vek != null){
			if (vek.get_jt_bg_storno_info()!= null){
				_vPrepSTMT.addElement(vek.get_jt_bg_storno_info().getPrepInsertStatement());
			}
			if (vek.get_jt_bg_del_info()!= null){
				_vPrepSTMT.addElement(vek.get_jt_bg_del_info().getPrepInsertStatement());
			}
			_vPrepSTMT.addElement(vek.getPrepInsertStatement());
			
			// jetzt die Vektoren der Bewegung
			for (jt_bg_atom atom: vek.get_jt_bg_atoms()){
				if (atom.get_jt_bg_storno_info()!= null){
					_vPrepSTMT.addElement(atom.get_jt_bg_storno_info().getPrepInsertStatement());
				}
				if (atom.get_jt_bg_del_info()!= null){
					_vPrepSTMT.addElement(atom.get_jt_bg_del_info().getPrepInsertStatement());
				}
				
				_vPrepSTMT.addElement(atom.getPrepInsertStatement());
				
				// jtzt die station und ladung...
				for (jt_bg_ladung ladung: atom.get_jt_bg_ladungs()){

					jt_bg_station station = ladung.get_jt_bg_station();
					if (station != null){
						if (station.get_jt_bg_storno_info()!= null){
							_vPrepSTMT.addElement(station.get_jt_bg_storno_info().getPrepInsertStatement());
						}
						if (station.get_jt_bg_del_info()!= null){
							_vPrepSTMT.addElement(station.get_jt_bg_del_info().getPrepInsertStatement());
						}
						_vPrepSTMT.addElement(station.getPrepInsertStatement());
					}
					
					if (ladung.get_jt_bg_storno_info()!= null){
						_vPrepSTMT.addElement(ladung.get_jt_bg_storno_info().getPrepInsertStatement());
					}
					if (ladung.get_jt_bg_del_info()!= null){
						_vPrepSTMT.addElement(ladung.get_jt_bg_del_info().getPrepInsertStatement());
					}
					_vPrepSTMT.addElement(ladung.getPrepInsertStatement());
				}
			}
		}
		return _vPrepSTMT;
	}
	

	
	
	
	/**
	 * löscht alle Korrektursätze mit der vorgegebenen Kennung aus VEKTOR und ATOMEN
	 * @author manfred
	 * @date   27.09.2012
	 * @param Kennung  
	 * @throws myException 
	 */
	private Vector<MyDBStatementPrepared>  loescheKorrektursaetzePrepared(RECORD_LAGER_KONTO kto) throws myException{
		
		String sID = null;
		Long lID = null; 
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp ;
		
		// alle Sätze dieses Lagerkontos löschen
		String [][] ids = bibDB.EinzelAbfrageInArray("select id_bg_vektor from " + bibE2.cTO()+ ".jt_bg_ladung where ID_LAGER_KONTO = " + kto.get_ID_LAGER_KONTO_cUF_NN("-1"));
		
		for(int i=0; i<ids.length; i++){
			sID = ids[i][0];
			lID = Long.parseLong(sID);
			_vPrepSTMT.addAll( generateDeleteStmts_BGVektorPrepared(lID) );
		}
		
		return _vPrepSTMT;
	}
	
	
	

	/**
	 * baut die prepared-Statements für das löschen des Vektors und der abhängigen Datensätze
	 * @author manfred
	 * @date 20.02.2018
	 *
	 * @param lID
	 * @return
	 * @throws myException
	 */
	private Vector<MyDBStatementPrepared>  generateDeleteStmts_BGVektorPrepared(Long lID) throws myException{
		
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp;
		if (lID != null){
			try {
				sp = new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),"DELETE FROM  " + bibE2.cTO()+ ".JT_BG_LADUNG where ID_BG_VEKTOR = ? ");
				sp.STMT.setLong(1, lID);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),"DELETE FROM  " + bibE2.cTO()+ ".JT_BG_STATION where ID_BG_VEKTOR = ?");
				sp.STMT.setLong(1, lID);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),"DELETE FROM  " + bibE2.cTO()+ ".JT_BG_ATOM where ID_BG_VEKTOR  =  ?" );
				sp.STMT.setLong(1, lID);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),"DELETE FROM  " + bibE2.cTO()+ ".JT_BG_VEKTOR where ID_BG_VEKTOR =  ?");
				sp.STMT.setLong(1, lID);
				_vPrepSTMT.addElement(sp);
				
			} catch (SQLException e) {
				throw new myException("loescheBGVektorPrepared(::Fehler beim erzeugen der Lösch-Statements", e);
			}
		}
		return _vPrepSTMT;
	}
		
	
	
	/**
	 * löscht alle Korrektursätze mit der vorgegebenen Kennung aus VEKTOR und ATOMEN
	 * @author manfred
	 * @date   27.09.2012
	 * @param Kennung  
	 * @throws myException 
	 */
	private Vector<String>  loescheKorrektursaetze(RECORD_LAGER_KONTO kto, boolean bUsePrefix) throws myException{
		
		String sSQLPrefix = "";
		if (bUsePrefix){
			sSQLPrefix = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION;
		}
		String sID = null;
		Vector<String> vsDeleteOld = new Vector<String>();
		
		
		// alle Sätze dieses Lagerkontos löschen
		String [][] ids = bibDB.EinzelAbfrageInArray("select id_bg_vektor from " + bibE2.cTO()+ ".jt_bg_ladung where ID_LAGER_KONTO = " + kto.get_ID_LAGER_KONTO_cUF_NN("-1"));
		for(int i=0; i<ids.length; i++){
			sID = ids[i][0];
			if (!bibALL.isEmpty(sID)){
				vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BG_LADUNG where ID_BG_VEKTOR =  " + sID + " " );
				vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BG_STATION where ID_BG_VEKTOR = " + sID + " " );
				vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BG_ATOM where ID_BG_VEKTOR  =  " + sID  + " " );
				vsDeleteOld.add(sSQLPrefix + "DELETE FROM  " + bibE2.cTO()+ ".JT_BG_VEKTOR where ID_BG_VEKTOR =  " + sID  + " " );
			}
		}
		
		return vsDeleteOld;

		
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
		
//		Vector<sT> vCleanFuhren = new Vector<>();
//		
//		if (bCleanFuhren){
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_ATOM.fullTabName(),	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG.fullTabName(),		"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null " ) );
//		}
//		 
//		if (bCleanHandbuchungLager){
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_ATOM.fullTabName() ,	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG.fullTabName(),			"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y' " ) );
//		}
//		 
//		 
//		for (sT tupel: vCleanFuhren){
//			 boolean bOK = bibDB.ExecSQL(tupel.value, true);
//			 mv.add( new MyE2_Info_Message(tupel.key + (bOK ? "OK" : "Error!!!") + "\n" ) );
//		}
//		
//		 
//		// die Sequencer neu aufbauen
//		Vector<String> vNewSequencer = new Vector<>();
//		vNewSequencer.add(BEWEGUNG.fullTabName());
//		vNewSequencer.add(BEWEGUNG_VEKTOR.fullTabName());
//		vNewSequencer.add(BEWEGUNG_VEKTOR_POS.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM.fullTabName());
//		vNewSequencer.add(BEWEGUNG_STATION.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM_ABZUG.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM_KOSTEN.fullTabName());
//		 
//		for (String sTable: vNewSequencer) {
//				Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(sTable, null);
//				mv._add(oSeq.Build_New_SequenceBased_on_DatabaseQuery() ) ;
//		}
//
				 
		 
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
	 *  curl "http://localhost:8080/rohstoff_app/batch?user=batchuser&pw=xxxx&task=convert2bgatom"
	 *  Eintrag in die Tabelle jd_batch_task:
	 *  
	 * insert into jd_batch_task (id_batch_task, id_mandant, taskname, classname, description,geaendert_von,letzte_aenderung,erzeugt_von,erzeugt_am)
	 * values(seq_batch_task.nextval,1,'convert2bgatom','rohstoff.businesslogic.bewegung.convert_from_fuhre.bg_bewegung_handler','Konvertiert die Handbuchungen und Fuhren in die BG-Atom-Strutkur','SYSTEM',sysdate,'SYSTEM',sysdate);
	 * 
	 */
	@Override
	public boolean runTask() {

		
		GregorianCalendar calBegin ;
		GregorianCalendar calEnd;
		long diff_in_sec ;
		
		String sSql1 = "";
		String sSql2 = "";
		String sSql3 = "";
		String sSql4 = "";
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

			Vector<String> vSql = new Vector<>();
			
			if (bConvertHandbuchungen){
				//		-- alle HANDBUCHUNGEN ,die möglicherweise im aktuellen Tag geändert wurden
				//		-- muss gemacht werden, da per Programm keine Zeitangaben beim Timestamp übernommen wird :-(
				vSql.add( 	" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') >  trunc(sysdate  - 1 ,'DD') AND L.BUCHUNG_HAND IS NOT NULL ") ;
				
				//     -- alle HANDBUCHUNGEN, die geändert wurden, und ein Gap des Änderungsdatums > 1 ist
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM  " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						"					INNER JOIN  " + bibE2.cTO() +".JT_BEWEGUNG_ATOM A " +
						" 					ON A.ID_LAGER_KONTO = L.ID_LAGER_KONTO AND A.ID_MANDANT = L.ID_MANDANT " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') - trunc(A.LETZTE_AENDERUNG,'DD') >= 1 "	+ 
						" AND L.BUCHUNG_HAND IS NOT NULL " );
				
				// 		--  alle HANDBUCHUNGEN, die noch nicht verarbeitet wurden
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" 				LEFT OUTER JOIN " + bibE2.cTO() +".JT_BG_LADUNG LA ON L.ID_LAGER_KONTO = LA.ID_LAGER_KONTO AND L.ID_MANDANT = LA.ID_MANDANT  " +
						" 				WHERE LA.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL ") ;
			
			} 
			
			if (bConvertLagerbestandserfassungen){
			
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" 				LEFT OUTER JOIN " + bibE2.cTO() +".JT_BG_LADUNG A ON L.ID_LAGER_KONTO = A.ID_LAGER_KONTO AND L.ID_MANDANT = A.ID_MANDANT " +
						" 				WHERE A.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL AND  " +
						"				UPPER( L.BEMERKUNG ) = UPPER('LAGERBESTANDSERFASSUNG') ") ;
			}

			
			if (vSql.size()>0){
				try {
					ssqlIDs = bibALL.Concatenate(vSql, " UNION ", "") + " order by 1 ";
				} catch (myException e) {
					ssqlIDs = "";	
				}
			}
			
			
			asIds = new String[0][0];
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			Rec20_Lager_Konto oLag = null;
			sID = "";
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					oLag = new Rec20_Lager_Konto ();
					oLag._fill_id(asIds[iRun][0]);
					
					sID = oLag.get_ufs_dbVal(LAGER_KONTO.id_lager_konto, "N/A");
					generateBGVektorFromHandbuchung(oLag,true);
					
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
						" inner join "+ bibE2.cTO() +".jt_bg_atom B " +
						"      on F.ID_MANDANT = B.ID_MANDANT and F.ID_VPOS_TPA_FUHRE = B.ID_VPOS_TPA_FUHRE " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') - trunc(B.LETZTE_AENDERUNG,'DD') >= 1 " ;
		
		// 		--  alle fuhren, die noch nicht verarbeitet wurden
		
		// Nutzen des Outer-Joins mit null-IDs geht deutlich schneller als das "not in" statement
		sSql3 =  " SELECT F.id_vpos_tpa_fuhre FROM  "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
				" left outer join  "+ bibE2.cTO() +".JT_BG_ATOM B on F.id_vpos_tpa_fuhre = B.ID_VPOS_TPA_FUHRE and F.id_mandant = B.ID_MANDANT " +
		" WHERE B.ID_VPOS_TPA_FUHRE is null " +
		" and NVL(F.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " 
		;
		
		

		ssqlIDs = sSql1 + " UNION " + sSql2 + " UNION " + sSql3 + " order by 1 ";
		
		asIds = new String[0][0];
		asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
		Rec20_VPOS_TPA_FUHRE_ext oFuhre = null;
		sID = "";

		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				sID = asIds[iRun][0];
				oFuhre = new Rec20_VPOS_TPA_FUHRE_ext();
				oFuhre._fill_id(sID);
				
				generateBGVektorFromFuhre_Prepared(oFuhre,true);
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
