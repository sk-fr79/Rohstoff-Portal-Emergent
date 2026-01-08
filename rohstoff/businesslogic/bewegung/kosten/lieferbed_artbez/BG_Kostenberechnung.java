package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS.KOSTEN_LIEFERBED_ADR;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList2;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.ENUM_KOSTENTYP;
import rohstoff.utils.bibROHSTOFF;


/**
 * kalkulatorische Kosten für die BG-Struktur
 * @author manfred
 * @date 09.04.2018
 *
 */
public class BG_Kostenberechnung {

	// eigene Lagerorte ohne Sonderlager
	Vector<String> 								_vEigeneLagerOrte 		= new Vector<String>();
	// Sonderlager die man ignorieren muss (alle ausser Streckenlager)
	Vector<String>								_vSonderlagerToIgnore 	= new Vector<String>();
	Vector<String>								_vSonderlagerRelevant 	= new Vector<String>();
	String 										_sIDStreckenlager 		= null;

	
	RECLIST_KOSTEN_LIEFERBED_DEF 				_oLieferbedKostenDef = null;
	String 										_sIDAdresseMandant		= null;
//	Vector<String>  							m_vSQLStatements 		= null;

	MyDBToolBox         						m_MyDBToolbox = null;
	
	Hashtable<String, REC_Kosten_Lieferbed> 	m_htKostenLieferbedCache = null;

	
	// Liste der kosten, die für einen Vektor schon bestehen 
	RecList21 									_recListKostenExisting = null;
	
	// Vektor für die Statements
	Vector<MyDBStatementPrepared> 				_vPrepSTMT = new Vector<>();
	
	int 										_nCountVektor 		= 0;
	int											_nCountNew 			= 0;
	int 										_nCountExisiting	=0;
	
	

	/**
	 * Konstruktor der Kostenberechnung
	 */
	public BG_Kostenberechnung() {
		
		m_MyDBToolbox = bibALL.get_myDBToolBox();
		
		// Eigene Lagerorte definieren
		try {
			_vEigeneLagerOrte 					= bibROHSTOFF.get_vEigeneLieferadressen();
			_vSonderlagerToIgnore				= bibROHSTOFF.get_vSonderlagerAdressen(false, false, true, true, true, true, true);
			_vSonderlagerRelevant				= bibROHSTOFF.get_vSonderlagerAdressen(true, true, false, false, false, false, false);
			
			_oLieferbedKostenDef				= new RECLIST_KOSTEN_LIEFERBED_DEF("", "");
			
			_sIDStreckenlager					= bibSES.get_ID_ADRESSE_LAGER_STRECKE();
			_sIDAdresseMandant					= bibALL.get_ID_ADRESS_MANDANT();
			
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * erzeugt die Kostensätze für alle Vektoren die in der Bewegung vorhanden sind.
	 * es werden nur die Sql-Statements generiert, welche dann mit der Methode
	 * 		executeSqlStatements(bool) 
	 * ausgeführt werden müssen.
	 * @param sIDBewegung
	 * @throws myException 
	 */
	public void Erzeuge_Kostensaetze_Fuer_BGVektor(String sIDBGVektor) throws myException{
		initCounters();
		ErzeugeSQL_Kostensaetze_Fuer_BG_Vektor(sIDBGVektor);
	}

	
	
	/**
	 * Erzeugt die Kostensätze für alle Vektoren die zu dieser Adresse gehören.
	 * Die Statements werden gleich ausgeführt, da sonst zuviele...
	 * AdressID muss eine Kunden-ID sein, und darf nicht die ID des Mandanten sein.
	 * 
	 * @param sIDAdresseBasis   - Die Basis(Haupt)-Adresse des Kunden.
	 * @throws myException 
	 */
	public void ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(String sIDAdresseBasis) throws myException{
		
		GregorianCalendar calBegin = new GregorianCalendar();
		
		initCounters();
		String sID = null;
		
		// ermitteln der Hauptadresse
		SqlStringExtended query = new SqlStringExtended("SELECT V.ID_ADRESSE_BASIS FROM V_FIRMENADRESSEN_FLACH V WHERE ID_MANDANT = ?  AND V.ID_ADRESSE = ? ");
		query.getValuesList().add(new Param_Long(Integer.parseInt(bibALL.get_ID_MANDANT())) );
		query.getValuesList().add(new Param_Long(Integer.parseInt(sIDAdresseBasis)) );

		
		String [][] ids = bibDB.EinzelAbfrageInArray(query);
		if (ids.length == 1){
			sID = ids[0][0];
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Haupt-Adresse konnte nicht ermittelt werden.")));
			return;
		}
		
		if (sIDAdresseBasis.equals(bibALL.get_ID_ADRESS_MANDANT())){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Kostenermittlung darf nicht für die Adresse des Mandanten durchgeführt werden.")));
			return;
		}
		
		// finden aller Einträge, zu dieser Adresse gehören
		String sSqlAtome = 
				" SELECT DISTINCT L.ID_BG_VEKTOR " +
				" FROM " + bibE2.cTO() + ".JT_BG_LADUNG L " +
				" INNER JOIN " + bibE2.cTO() + ".JT_BG_STATION S ON L.ID_BG_STATION = S.ID_BG_STATION " +
				" WHERE S.ID_ADRESSE in (SELECT DISTINCT ID_ADRESSE FROM V_FIRMENADRESSEN_FLACH WHERE ID_ADRESSE_BASIS = ? ) " ;

		SqlStringExtended queryAtome = new SqlStringExtended(sSqlAtome);
		queryAtome.getValuesList().add(new Param_Long(Integer.parseInt(sID)) );
		String[][] aIDs = bibDB.EinzelAbfrageInArray(queryAtome);
		
		
		for(int iRun = 0; iRun < aIDs.length; iRun++){
			ErzeugeSQL_Kostensaetze_Fuer_BG_Vektor(aIDs[iRun][0]);
		}
		
		GregorianCalendar calEnd = new  GregorianCalendar();
		long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;

		DEBUG.System_println(String.format("Es wurden %d Vektoren untersucht, %d neue Kostendatensätze geschrieben und %d Kostendatensätze beibehalten. Dauer: %d Sek. -> %d DS/s",_nCountVektor,_nCountNew, _nCountExisiting, diff_in_sec, _nCountVektor / diff_in_sec) );
	}
	
	
	
	
	/**
	 * liest alle Atome aus der Bewegung und ermittelt die Kostensätze für die Atome
	 * @author manfred
	 * @date   02.12.2013
	 * @param sIDBewegung
	 * @return
	 * @throws myException 
	 */
	public void ErzeugeSQL_Kostensaetze_Fuer_BG_Vektor(String sIDBGVektor) throws myException{

		_nCountVektor ++;
		
		// erzeuge einer Liste von Atom_info-Objekten die zur Bewegung gehören
		REC_Transport_Liste oBGVektorListe = new REC_Transport_Liste(sIDBGVektor, _vSonderlagerToIgnore,  _vEigeneLagerOrte, _oLieferbedKostenDef);


		//
		// alle bestehenden Kostensätze für den Vektor ermitteln...
		//
		try {
			
			SqlStringExtended s = new SqlStringExtended(" SELECT * FROM " + _TAB.bg_vektor_kosten.fullTableName() + " WHERE " + BG_VEKTOR_KOSTEN.id_bg_vektor.tnfn() + " = ?" 	);
			s.getValuesList().add(new Param_Long(Integer.parseInt(sIDBGVektor)) );
			_recListKostenExisting = new RecList21(_TAB.bg_vektor_kosten)._fill(s);
		} catch (myException e) {
			throw new myException("Create_ATOM_Kosten_SQLStatements::Vektordaten konnten nicht ermittelt werden", e);
		}

		
		//
		// Alle neuen Kostensätze für den Vektor erzeugen...
		// die bestehenden Kostensätze werden ausgefiltert und nicht neu geschrieben, alle anderen werden gelöscht
		//
		Iterator<REC_Transport_Info> it = oBGVektorListe.get_list_Transport_Info().iterator();
		while (it.hasNext()){
			REC_Transport_Info oRec = it.next();
			CreateAtomKosten(oRec);
		}
		
		
		//
		// Rest der alten Kostensätze löschen
		// 
		for (Rec21 r: _recListKostenExisting.values()){
			try {
				Long ID 	= r.getActualID();
				MyDBStatementPrepared sp = new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),"DELETE FROM  " + BG_VEKTOR_KOSTEN.fullTabName() + " where " + BG_VEKTOR_KOSTEN.id_bg_vektor_kosten.tnfn() + " = ? ");
				sp.STMT.setLong(1, ID);
				_vPrepSTMT.insertElementAt(sp,0);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		// jetzt alle schreiben...
		// jetzt die Daten per Prepared-Statement einfügen...
		boolean bOK = true;
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for (MyDBStatementPrepared stmt: _vPrepSTMT ){
			bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(stmt, false);
			_nCountNew++;
		}
		
		
		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
			DEBUG.System_println("ROLLBACK:  ???" , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
		
		// löschen aller prepared statements, damit der Cursor freigeben wird
		_vPrepSTMT.clear();
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 27.04.2018
	 *
	 * @param oTransport
	 * @throws myException
	 */
	private void CreateAtomKosten(REC_Transport_Info oTransport) throws myException{
		
		String sIDBGVektor = oTransport.get_idBGVektor();
		
		
		REC_BG_Vektor_Kosten Kostensatz_Neu= null;
		
		//
		// Ermitteln der Transportkosten
		//
		REC_Kosten_Lieferbed oLieferkosten;
		BigDecimal bdFaktor ;
		BigDecimal bdKostenEinzel;
		
		// WE-Seite (entweder WE, oder ST
		if (! this._vEigeneLagerOrte.equals( oTransport.get_idAdresseLade() ) ){
			// Sorte beim Kunden relevant
			oLieferkosten = getTransportkostenSatz(oTransport.get_idAdresseLade(), oTransport.get_idSorteLade(), oTransport.get_idAdresseAblade());
			if (oLieferkosten != null){
				
				bdFaktor = oTransport.get_bdFaktorLBLade();
				bdKostenEinzel = (oLieferkosten.get_Betrag_Kosten().bdValue.multiply(bdFaktor));
				
				
				// Gesamtkosten
				if (oTransport.get_AtomInfo1().get_MengeAbrechnungseinheit_Lade() != null 
						&& bdKostenEinzel != null 
						&& bdKostenEinzel.compareTo(BigDecimal.ZERO) != 0 ){
					
					Kostensatz_Neu = new REC_BG_Vektor_Kosten(oTransport,oTransport.get_AtomInfo1(),true);
					Kostensatz_Neu.set_Kostentyp("INCOTERM");
					Kostensatz_Neu.set_Pauschal("N");
					Kostensatz_Neu.set_Lagervorzeichen(1L);
					Kostensatz_Neu.set_KostenEinzel(bdKostenEinzel);
					Kostensatz_Neu.set_ID_Kosten_Lieferbed_Adr(oLieferkosten.get_ID_Kosten_Lieferbed_Adr().lValue);
					Kostensatz_Neu.set_KostenGesamt(oTransport.get_AtomInfo1().get_MengeAbrechnungseinheit_Lade().multiply(bdKostenEinzel));
					
					
					DEBUG.System_println("KOSTENSATZ ERZEUGT FÜR: " + oTransport.info(), DEBUG_FLAGS.DIVERS3.name());
					
					// prüfen, ob der Kostensatz schon mal geschrieben wurde...
					if (!KostensatzExists(Kostensatz_Neu,_recListKostenExisting) 
							&& Kostensatz_Neu.get_KostenGesamt().bdValue.compareTo(BigDecimal.ZERO)>0){
						// Kostensatz schreiben
						_vPrepSTMT.add(Kostensatz_Neu.getPrepInsertStatement() );
						
					} else {
						_nCountExisiting ++;
					}
				}
			}

		} 
		

		
		
		// die WA Seite prüfen  / Pro Transport nur einen Kostensatz ermitteln. Bei einer Strecke könnten das 2 sein,
		// was hiermit verhindert wird.
		if (Kostensatz_Neu == null){
			if (! this._vEigeneLagerOrte.equals( oTransport.get_idAdresseAblade() )){
				// Sorte beim Kunden relevant
				oLieferkosten = getTransportkostenSatz(oTransport.get_idAdresseLade(), oTransport.get_idSorteAblade(), oTransport.get_idAdresseAblade());
				if (oLieferkosten != null){
					bdFaktor = oTransport.get_bdFaktorLBAblade();
					
					bdKostenEinzel = (oLieferkosten.get_Betrag_Kosten().bdValue.multiply(bdFaktor));
					
					
					// Gesamtkosten
					REC_BG_AtomInfo atomInfo = oTransport.get_AtomInfo2() != null ?oTransport.get_AtomInfo2() :oTransport.get_AtomInfo1() ;
					
					if (atomInfo.get_MengeAbrechnungseinheit_Ablade() != null 
							&& bdKostenEinzel != null
							&& bdKostenEinzel.compareTo(BigDecimal.ZERO) != 0 ){
						
						Kostensatz_Neu = new REC_BG_Vektor_Kosten(oTransport,atomInfo,false);
						Kostensatz_Neu.set_Kostentyp("INCOTERM");
						Kostensatz_Neu.set_Pauschal("N");
						Kostensatz_Neu.set_Lagervorzeichen(-1L);
						
						Kostensatz_Neu.set_KostenEinzel(bdKostenEinzel);
						Kostensatz_Neu.set_ID_Kosten_Lieferbed_Adr(oLieferkosten.get_ID_Kosten_Lieferbed_Adr().lValue);
						
						Kostensatz_Neu.set_KostenGesamt(atomInfo.get_MengeAbrechnungseinheit_Lade().multiply(bdKostenEinzel));
						
						DEBUG.System_println("KOSTENSATZ ERZEUGT FÜR: " + oTransport.info(), DEBUG_FLAGS.DIVERS3.name());
						// prüfen, ob der Kostensatz schon mal geschrieben wurde...
						if (!KostensatzExists(Kostensatz_Neu,_recListKostenExisting) 
								&& Kostensatz_Neu.get_KostenGesamt().bdValue.compareTo(BigDecimal.ZERO)>0){
							// Kostensatz schreiben
							_vPrepSTMT.add(Kostensatz_Neu.getPrepInsertStatement() );
						} else {
							_nCountExisiting++;
						}
					}
					
				}
			}
		}
			

		
		/////
		//
		// Handlingskosten ermitteln....können mehrere sein pro Fuhre
		//
		
		// WE-Seite (entweder WE, oder ST
		if (! this._vEigeneLagerOrte.equals( oTransport.get_idAdresseLade() ) ){
			
			Vector<REC_Kosten_Artikelbez> handlingskosten = calculateHandlingskosten(oTransport.get_idAdresseLade(), oTransport.get_idArtikelBezLade(), "EK");
			for (REC_Kosten_Artikelbez k: handlingskosten){
				
				BigDecimal bdMenge = oTransport.get_AtomInfo1().get_MengeAbrechnungseinheit_Lade();
				bdMenge = bdMenge != null ? bdMenge : BigDecimal.ZERO;
				
				BigDecimal bdKosten = k.get_Betrag().bdValue;
				bdKosten = bdKosten != null ? bdKosten : BigDecimal.ZERO;
				
				BigDecimal bdGesamt = BigDecimal.ZERO; 
				if(k.is_Pauschal()) {
					bdGesamt = (bdKosten != null ? bdKosten : BigDecimal.ZERO);
				} else {
					bdGesamt = bdKosten.multiply(bdMenge);
				}
				
				if (bdGesamt.compareTo(BigDecimal.ZERO) >0 ){
					REC_BG_Vektor_Kosten kst = new REC_BG_Vektor_Kosten(oTransport, oTransport.get_AtomInfo1(), true);
					kst.set_Kostentyp(k.get_Bezeichnung().ValuePlain());
					kst.set_Pauschal(k.get_Pauschal().ValuePlain());
					kst.set_Lagervorzeichen(+1);
					kst.set_KostenEinzel(bdKosten);
					kst.set_ID_Kosten_Artbez_Lief(k.get_ID_Kosten_Artbez_Lief().lValue);
					kst.set_KostenGesamt(bdGesamt);
					
					if (!KostensatzExists(kst ,_recListKostenExisting) ){
						_vPrepSTMT.add(kst.getPrepInsertStatement() );
					} else {
						_nCountExisiting ++;
					}
				}
			}
		}
		
		
		// WA-Seite (entweder WA, oder ST)
		if (! this._vEigeneLagerOrte.equals( oTransport.get_idAdresseAblade() ) ){
			
			Vector<REC_Kosten_Artikelbez> handlingskosten = calculateHandlingskosten(oTransport.get_idAdresseAblade(), oTransport.get_idArtikelBezLade(), "VK");
			for (REC_Kosten_Artikelbez k: handlingskosten){
				
				BigDecimal bdMenge = oTransport.get_AtomInfo1().get_MengeAbrechnungseinheit_Ablade();
				bdMenge = bdMenge != null ? bdMenge : BigDecimal.ZERO;
				
				BigDecimal bdKosten = k.get_Betrag().bdValue;
				bdKosten = bdKosten != null ? bdKosten : BigDecimal.ZERO;
				
				BigDecimal bdGesamt = BigDecimal.ZERO; 
				if(k.is_Pauschal()) {
					bdGesamt = (bdKosten != null ? bdKosten : BigDecimal.ZERO);
				} else {
					bdGesamt = bdKosten.multiply(bdMenge);
				}
				
				// die WA-Atominfo ermitteln
				REC_BG_AtomInfo atomInfo = oTransport.get_AtomInfo2() != null ?oTransport.get_AtomInfo2() :oTransport.get_AtomInfo1() ;
				
				if (bdGesamt.compareTo(BigDecimal.ZERO) >0 ){
					REC_BG_Vektor_Kosten kst = new REC_BG_Vektor_Kosten(oTransport, atomInfo, false);
					kst.set_Kostentyp(k.get_Bezeichnung().Value());
					kst.set_Pauschal(k.get_Pauschal().Value());
					kst.set_Lagervorzeichen(-1);
					kst.set_KostenEinzel(bdKosten);
					kst.set_ID_Kosten_Artbez_Lief(k.get_ID_Kosten_Artbez_Lief().lValue);
					kst.set_KostenGesamt(bdGesamt);
					
					if (!KostensatzExists(kst ,_recListKostenExisting) ){
						_vPrepSTMT.add(kst.getPrepInsertStatement() );
					} else {
						_nCountExisiting ++;
					}
				}
			}
		}

		
		
	}
	
	
	
	
	
	/**
	 * @author manfred
	 * @date 27.04.2018
	 *
	 * @param kostensatz_Neu
	 * @param recListKosten
	 * @return
	 */
	private boolean KostensatzExists(REC_BG_Vektor_Kosten kostensatz_Neu, RecList21 recListKosten) {
		boolean bFound = false;
		
		if (recListKosten != null && recListKosten.size() > 0){
			for (Rec21 r: recListKosten.values()){
				try {
					
					BigDecimal 	bdEinzel 	= r.get_raw_resultValue_bigDecimal(BG_VEKTOR_KOSTEN.kosten_einzel,BigDecimal.ZERO);
					BigDecimal 	bdEinzelNeu	= kostensatz_Neu.get_KostenEinzel().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
					
					BigDecimal 	bdGesamt 	= r.get_raw_resultValue_bigDecimal(BG_VEKTOR_KOSTEN.kosten_gesamt,BigDecimal.ZERO);
					BigDecimal 	bdGesamtNeu	= kostensatz_Neu.get_KostenGesamt().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
					
					Long 	  	LagerVZ		= r.get_raw_resultValue_Long(BG_VEKTOR_KOSTEN.lagervorzeichen,0L);
					Long 		LagerVZNeu	= kostensatz_Neu.get_Lagervorzeichen().lValue;
					
					String      idBGAtom 	= r.get_ufs_dbVal(BG_VEKTOR_KOSTEN.id_bg_atom,"-");
					String 		idKostenLief= r.get_ufs_dbVal(BG_VEKTOR_KOSTEN.id_kosten_lieferbed_adr,"-");
					
					String 		idKostenArtbezLief = r.get_ufs_dbVal(BG_VEKTOR_KOSTEN.id_kosten_artbez_lief, "-");
					
					String		kostentyp   = r.get_ufs_dbVal(BG_VEKTOR_KOSTEN.kostentyp, "-");
					String 		pauschal 	= r.get_ufs_dbVal(BG_VEKTOR_KOSTEN.pauschal,"-");
					
					
					
					if (idBGAtom.equals(kostensatz_Neu.get_ID_BG_Atom().ValuePlain()) && 
						idKostenLief.equals(kostensatz_Neu.get_ID_Kosten_Lieferbed_Adr().ValuePlain("-")) &&
						idKostenArtbezLief.equals(kostensatz_Neu.get_ID_Kosten_Artbez_Lief().ValuePlain("-")) &&
						bdEinzel.compareTo(bdEinzelNeu) == 0 &&
						bdGesamt.compareTo(bdGesamtNeu) == 0 &&
						LagerVZ.equals(LagerVZNeu) &&
						kostentyp.equals(kostensatz_Neu.get_Kostentyp().ValuePlain("-")) &&
						pauschal.equals(kostensatz_Neu.get_Pauschal().ValuePlain("-")))
					{
						// der Datensatz ist schon vorhanden und muss nicht noch mal erzeugt werden
						bFound = true;
						// d.h. den Datensatz aus der Liste derer rausnehmen, die gelöscht werden müssen..Zum Schluss müssen alle Records gelöscht werden, die noch in der gefundenen Liste drin sind
						recListKosten.remove(r.get_key_value());
						break;
					}
				} catch (myException e) {
					e.printStackTrace();
					int i = e.ErrorCode;
				}
			} 
		}
		return bFound;
	}







	
	/**
	 * Setzt alle Zähler zurück (für die Statistik)
	 * @author manfred
	 * @date 02.05.2018
	 *
	 */
	public void initCounters(){
		_nCountExisiting = 0;
		_nCountNew = 0;
		_nCountVektor = 0;
		
	}
	
	
	

	


	
	/**
	 * Ermittelt den Transportkostensatz, der optimalerweise vorher mit dem aufruf von cacheTransportkosten() 
	 * gecached wurde.
	 * 
	 * @param idAdresse
	 * @param idArtikel
	 * @param idAdresseZiel
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 09.07.2014
	 *
	 */
	protected REC_Kosten_Lieferbed getTransportkostenSatz(String idAdresse,String idArtikel, String idAdresseZiel) {
		REC_Kosten_Lieferbed oResult = null;
		if (m_htKostenLieferbedCache != null){
			String sKey = idAdresse + "#" +idArtikel  + "#" + idAdresseZiel;
			if (m_htKostenLieferbedCache.containsKey(sKey) ){
				oResult = m_htKostenLieferbedCache.get(sKey);
			}
		} else {
			oResult = getTransportkostenSatzDirect(idAdresse, idArtikel, idAdresseZiel);
		}
		return oResult;
	}
	
	
	
	/**
	 * Ermitteln des Transportkostensatzes direkt als Statement aus der DB, falls kein Cache gefüllt werden konnte
	 * @param idAdresse
	 * @param idArtikel
	 * @param idAdresseZiel
	 * @return
	 */
	protected REC_Kosten_Lieferbed getTransportkostenSatzDirect(String idAdresse,String idArtikel, String idAdresseZiel) {
		REC_Kosten_Lieferbed oResult = null;
		
		String sIDAdr = idAdresse;
		if (bibALL.isEmpty(idAdresse)) {
			sIDAdr = "-1";
		}

		String sIDAdrZiel = idAdresseZiel;
		if (bibALL.isEmpty(idAdresseZiel)) {
			sIDAdrZiel = "-1";
		}

		String sIDArtikel = idArtikel;
		if (bibALL.isEmpty(idArtikel)) {
			sIDArtikel = "-1";
		}
		
		String sSql = 
				" SELECT *  "
			+ 	" FROM  " + bibE2.cTO() +".JT_KOSTEN_LIEFERBED_ADR a " +
				" WHERE  a.ID_ADRESSE = ? and a.ID_ADRESSE_ZIEL = ? and A.ID_ARTIKEL = ?" + 
				" AND nvl(a.BETRAG_KOSTEN,0) != 0 ";			
		
		SqlStringExtended s = new SqlStringExtended(sSql);
		s.getValuesList().add(new Param_Long(Integer.parseInt(sIDAdr)) );
		s.getValuesList().add(new Param_Long(Integer.parseInt(sIDAdrZiel)) );
		s.getValuesList().add(new Param_Long(Integer.parseInt(sIDArtikel)) );
		
		try {
			RecList21 _recListKostenExisting = new RecList21(_TAB.kosten_lieferbed_adr)._fill(s);
			
			// wenn nichts gefunden wird, einen leeren Satz zurückgeben
			if (_recListKostenExisting == null || _recListKostenExisting.size() == 0){
				return oResult;
			}
			
			// den ersten Satz  (es darf eh nur einer da sein...
			Rec21 o = _recListKostenExisting.get(0);
			
			
			// wenn ein/oder mehrere Satz/e gefunden wurden, wird der 1. Satz genommen und die Werte eingetragen.
			oResult = new REC_Kosten_Lieferbed();
			
			// den Record füllen (1.Spalte ist die Sortierung...)
			int iCol = 0;
			oResult.set_ID_Kosten_Lieferbed_Adr(o.getUfs(KOSTEN_LIEFERBED_ADR.id_kosten_lieferbed_adr)) ;
			oResult.set_ID_Adresse( o.getUfs(KOSTEN_LIEFERBED_ADR.id_adresse));
			oResult.set_ID_Adresse_Ziel( o.getUfs(KOSTEN_LIEFERBED_ADR.id_adresse_ziel));
			oResult.set_Betrag_Kosten(o.get_raw_resultValue_bigDecimal(KOSTEN_LIEFERBED_ADR.betrag_kosten, BigDecimal.ZERO).setScale(3, BigDecimal.ROUND_HALF_UP));
			oResult.set_ID_Artikel( o.getUfs(KOSTEN_LIEFERBED_ADR.id_artikel));
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der Transportkosten.")));
			oResult = null;
		}
		
		return oResult;
	}
	

	
	
	
	/**
	 * Transportkosten cachen, da sie sonst jedes mal aufgerufen werden
	 *
	 * Autor:	 manfred
	 * Erstellt: 09.07.2014
	 *
	 */
	public void cacheTransportkosten(){
		m_htKostenLieferbedCache = new Hashtable<String, REC_Kosten_Lieferbed>();
		
		
		String sSql = 
				" SELECT *  " +
				" FROM  " + bibE2.cTO() +".JT_KOSTEN_LIEFERBED_ADR a " +
				" WHERE nvl(a.BETRAG_KOSTEN,0) != 0 ";			
		
		SqlStringExtended s = new SqlStringExtended(sSql);
		String sKey;
		
		try {
			RecList21 recListKostenExisting = new RecList21(_TAB.kosten_lieferbed_adr)._fill(s);

			for (Rec21 o : recListKostenExisting) {

				// wenn ein/oder mehrere Satz/e gefunden wurden, wird der 1. Satz genommen und die Werte eingetragen.
				REC_Kosten_Lieferbed oResult = new REC_Kosten_Lieferbed();
				
				// den Record füllen (1.Spalte ist die Sortierung...)
				oResult.set_ID_Kosten_Lieferbed_Adr(o.getUfs(KOSTEN_LIEFERBED_ADR.id_kosten_lieferbed_adr)) ;
				oResult.set_ID_Adresse( o.getUfs(KOSTEN_LIEFERBED_ADR.id_adresse));
				oResult.set_ID_Adresse_Ziel( o.getUfs(KOSTEN_LIEFERBED_ADR.id_adresse_ziel));
				oResult.set_Betrag_Kosten(o.get_raw_resultValue_bigDecimal(KOSTEN_LIEFERBED_ADR.betrag_kosten, BigDecimal.ZERO).setScale(3, BigDecimal.ROUND_HALF_UP));
				oResult.set_ID_Artikel( o.getUfs(KOSTEN_LIEFERBED_ADR.id_artikel));
				
				sKey = oResult.get_ID_Adresse().ValuePlain() + "#" + oResult.get_ID_Artikel().ValuePlain() + "#" + oResult.get_ID_Adresse_Ziel().ValuePlain();
				m_htKostenLieferbedCache.put(sKey, oResult);

			}
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim cachen der Transportkosten.")));
		}
	}

		
	

	/**
	 * Liefert einen Vektor von Einträgen der Artikelbezogenen Kosten zurück
	 * @author manfred
	 * @date   25.11.2013
	 * @param idLieferAdresse
	 * @param idArtikelBez
	 * @param ArtbezTypWEWA (EK / VK)
	 * @return
	 * @throws myException 
	 */
	protected java.util.Vector<REC_Kosten_Artikelbez> calculateHandlingskosten(String idLieferAdresse, String idArtikelBez, String ArtbezTypWEWA) {
		Vector<REC_Kosten_Artikelbez> oVec = new Vector<REC_Kosten_Artikelbez>();
		
		if (bibALL.isEmpty(idLieferAdresse) || bibALL.isEmpty(idArtikelBez)|| bibALL.isEmpty(ArtbezTypWEWA) ){
			return oVec;
		}
		
		

		
		// Sql-Statement sucht die Lieferbedingungen zuerst bei der Adresse, dann bei der Hauptadresse (wenn vorhanden) , dann bei den allgemeinen Lieferbedingungen
		String sSql = 
			" SELECT k.ID_KOSTEN_ARTBEZ_LIEF, l.ID_ADRESSE, k.BETRAG, k.BETRAG_STRECKE, t.PAUSCHAL, t.BEZ_KURZ, k.BEMERKUNG " +
			" FROM " + bibE2.cTO() +".JT_ARTIKELBEZ_LIEF l " + 
			" INNER JOIN  " + bibE2.cTO() +".JT_KOSTEN_ARTBEZ_LIEF k ON l.ID_ARTIKELBEZ_LIEF = k.ID_ARTIKELBEZ_LIEF " +
			" INNER JOIN  " + bibE2.cTO() +".JT_KOSTENTYP t ON k.ID_KOSTENTYP = t.ID_KOSTENTYP  " + 
			" WHERE " +
			"     l.ID_ADRESSE = nvl(  (select la.ID_ADRESSE_BASIS from JT_LIEFERADRESSE la where la.ID_ADRESSE_LIEFER = ? ),?) " +
			" AND l.ID_ARTIKEL_BEZ = ?"  +
			" AND l.ARTBEZ_TYP = ? " ;
		
			
		SqlStringExtended s = new SqlStringExtended(sSql);
		s.getValuesList().add(new Param_Long(Integer.parseInt(idLieferAdresse)) );
		s.getValuesList().add(new Param_Long(Integer.parseInt(idLieferAdresse)) );
		s.getValuesList().add(new Param_Long(Integer.parseInt(idArtikelBez)) );
		s.getValuesList().add(new Param_String("",ArtbezTypWEWA,"'") );
		
		try {
			
			String [][] cKostenDaten = new String[0][0];
			cKostenDaten =  bibDB.EinzelAbfrageInArray(s);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cKostenDaten == null || cKostenDaten.length == 0){
				return oVec;
			}
			
		
			for(int i=0; i < cKostenDaten.length; i++){
				// wenn ein/oder mehrere Satz/e gefunden wurden, wird der 1. Satz genommen und die Werte eingetragen.
				REC_Kosten_Artikelbez oResult = new REC_Kosten_Artikelbez();
				
				// den Record füllen (1.Spalte ist die Sortierung...)
				int iCol = 0;
				oResult.set_ID_Kosten_Artbez_Lief(cKostenDaten[i][iCol++]) ;
				oResult.set_ID_Adresse(cKostenDaten[i][iCol++]);
				oResult.set_Betrag(bibALL.convertDBTextToBigDecimal(cKostenDaten[i][iCol++]));
				oResult.set_BetragStrecke(bibALL.convertDBTextToBigDecimal(cKostenDaten[i][iCol++]));
				oResult.set_Pauschal(cKostenDaten[i][iCol++]);
				oResult.set_Bezeichnung(cKostenDaten[i][iCol++]);
				oResult.set_Bemerkung(cKostenDaten[i][iCol++]);
				
				oVec.add(oResult);
			}
			
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der Handlingskosten.")));
		}
		
		return oVec;
		
	}
	
	
	
	
	
	
}
