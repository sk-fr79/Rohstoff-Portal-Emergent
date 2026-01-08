package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.bibROHSTOFF;

/**
 *	Klasse für die Kostenberechnung der kalkulatorischen Kosten  
 * @author manfred
 * @date   21.11.2013
 */
public class BL_Kostenberechnung {

	// eigene Lagerorte ohne Sonderlager
	Vector<String> 								_vEigeneLagerOrte 		= new Vector<String>();
	// Sonderlager die man ignorieren muss (alle ausser Streckenlager)
	Vector<String>								_vSonderlagerToIgnore 	= new Vector<String>();
	Vector<String>								_vSonderlagerRelevant 	= new Vector<String>();
	String 										_sIDStreckenlager 		= null;

	
	RECLIST_KOSTEN_LIEFERBED_DEF 				_oLieferbedKostenDef = null;
	String 										_sIDAdresseMandant		= null;
	Vector<String>  							m_vSQLStatements 		= null;

	MyDBToolBox         						m_MyDBToolbox = null;
	
	Hashtable<String, REC_Kosten_Lieferbed> 	m_htKostenLieferbedCache = null;
	

	/**
	 * Konstruktor der Kostenberechnung
	 */
	public BL_Kostenberechnung() {
		
		m_MyDBToolbox = bibALL.get_myDBToolBox();
		
		// Eigene Lagerorte definieren
		try {
			_vEigeneLagerOrte 					= bibROHSTOFF.get_vEigeneLieferadressen();
			_vSonderlagerToIgnore				= bibROHSTOFF.get_vSonderlagerAdressen(false, false, true, true, true, true, true);
			_vSonderlagerRelevant				= bibROHSTOFF.get_vSonderlagerAdressen(true, true, false, false, false, false, false);
			
			_oLieferbedKostenDef				= new RECLIST_KOSTEN_LIEFERBED_DEF("", "");
			
			_sIDStreckenlager					= bibSES.get_ID_ADRESSE_LAGER_STRECKE();
			_sIDAdresseMandant					= bibALL.get_ID_ADRESS_MANDANT();
			
			m_vSQLStatements = new Vector<String>();
			
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
	 */
	public void ErzeugeSQL_Kostensaetze_Fuer_Bewegung(String sIDBewegung){
		clearStatements();
		
		// erzeuge einer Liste von Atom_info-Objekten die zur Bewegung gehören
		String sSql = " SELECT distinct ID_BEWEGUNG_VEKTOR FROM JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG = " + sIDBewegung;
		String[][] aIDs = bibDB.EinzelAbfrageInArray(sSql);
		
		for(int iRun = 0; iRun < aIDs.length; iRun++){
			ErzeugeSQL_Kostensaetze_Fuer_Vektor(aIDs[iRun][0]);
		}
	}

	
	
	/**
	 * Erzeugt die Kostensätze für alle Vektoren die zu dieser Adresse gehören.
	 * Die Statements werden gleich ausgeführt, da sonst zuviele...
	 * AdressID muss eine Kunden-ID sein, und darf nicht die ID des Mandanten sein.
	 * 
	 * @param sIDAdresseBasis   - Die Basis(Haupt)-Adresse des Kunden.
	 */
	public void ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(String sIDAdresseBasis){
		
		GregorianCalendar calBegin = new GregorianCalendar();
		
		clearStatements();
		String sID = null;
		
		// ermitteln der Hauptadresse
		String sAdr = bibDB.EinzelAbfrage("SELECT V.ID_ADRESSE_BASIS FROM V_FIRMENADRESSEN_FLACH V WHERE ID_MANDANT = 1  AND V.ID_ADRESSE = " + sIDAdresseBasis);
		if (sAdr != null){
			sID = sAdr;
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Haupt-Adresse konnte nicht ermittelt werden.")));
			return;
		}
		
		if (sIDAdresseBasis.equals(bibALL.get_ID_ADRESS_MANDANT())){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Kostenermittlung darf nicht für die Adresse des Mandanten durchgeführt werden.")));
			return;
		}
		
		// finden aller Einträge, zu dieser Adresse gehören
		String sSqlVektoren = 
				" SELECT DISTINCT A.ID_BEWEGUNG_VEKTOR " +
				" FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A " +
				" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S ON A.ID_MANDANT = S.ID_MANDANT AND A.ID_BEWEGUNG_ATOM = S.ID_BEWEGUNG_ATOM " +
				" WHERE S.ID_ADRESSE IN (SELECT DISTINCT ID_ADRESSE FROM " + bibE2.cTO() + ".V_FIRMENADRESSEN_FLACH WHERE ID_ADRESSE_BASIS = " + sID + " ) " ;
			
		String[][] aIDs = bibDB.EinzelAbfrageInArray(sSqlVektoren);
		
		for(int iRun = 0; iRun < aIDs.length; iRun++){
			ErzeugeSQL_Kostensaetze_Fuer_Vektor(aIDs[iRun][0]);
		}
		
		
		GregorianCalendar calEnd = new  GregorianCalendar();
		long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		
		DEBUG.System_println("Es wurden " + aIDs.length + " Vektoren konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
		calBegin = new GregorianCalendar();
		
		// Schreiben der Statements...
		this.executeSqlStatements(true);

		
		calEnd = new  GregorianCalendar();
		diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		DEBUG.System_println("Es wurden " + m_vSQLStatements.size() + " Statements geschrieben in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
			
	}
	
	
	
	
	/**
	 * liest alle Atome aus der Bewegung und ermittelt die Kostensätze für die Atome
	 * @author manfred
	 * @date   02.12.2013
	 * @param sIDBewegung
	 * @return
	 */
	public void ErzeugeSQL_Kostensaetze_Fuer_Vektor(String sIDVektor){
		// erzeuge einer Liste von Atom_info-Objekten die zur Bewegung gehören
		REC_VektorInfo oVecInfo = new REC_VektorInfo(sIDVektor,_vSonderlagerToIgnore,_vSonderlagerRelevant,_vEigeneLagerOrte, _oLieferbedKostenDef);
		Iterator<REC_AtomInfo> it = oVecInfo._list_AtomInfo.iterator();

		while (it.hasNext()){
			REC_AtomInfo oRec = it.next();
			Create_ATOM_Kosten_SQLStatements(oVecInfo,oRec);
		}
	}
	
	
	
	/**
	 * gibt die erzeugten SQL-Statements zurück
	 * @author manfred
	 * @date   02.12.2013
	 * @return
	 */
	public Vector<String> getStatements(){
		return m_vSQLStatements;
	}

	
	
	/**
	 * löscht die generierten SQL-Statements
	 * @author manfred
	 * @date   03.12.2013
	 */
	public void clearStatements(){
		m_vSQLStatements = new Vector<String>();
	}
	
	
	
	/**
	 * Ermittelt die Kosten für die Incoterms und die Artikel-Handlings und erzeugt die SQL-Statements
	 * für die Tochtertabelle und das Update des Atom-Eintrags
	 *  
	 * @author manfred
	 * @date   29.11.2013
	 * @param IDBewegungAtom
	 * @throws myException
	 */
	private void Create_ATOM_Kosten_SQLStatements(REC_VektorInfo oVecInfo, REC_AtomInfo oAtomInfo){

		if (oAtomInfo == null || oAtomInfo.get_IDBewegungAtom() == null){
			return;
		}
		
		Vector<String>  vSqlTemp					= new Vector<String>();
		
		// besitzer der Atom-Stationen
		String sIDBesitzerLade 						= oAtomInfo.get_IDBesitzer_Lade();
		String sIDBesitzerAblade 					= oAtomInfo.get_IDBesitzer_Ablade();

		// die Lade und Ablade-Adresse kommt aber aus dem Vektor
		String sIDAdresseAblade  					= oVecInfo.get_idAdresseAblade();
		String sIDAdresseLade  						= oVecInfo.get_idAdresseLade();
		
		BigDecimal bdFaktorWE						= oVecInfo.get_bdFaktorWE();
		BigDecimal bdFaktorWA						= oVecInfo.get_bdFaktorWA();
		
		String sIDAdresse 							= null;

		String sArtbezLiefWEWA 						= null;
		
		RECLIST_BEWEGUNG_ATOM_KOSTEN recListKosten 	= null;
		int 	 nCountNeueKostensaetze				= 0;
		boolean bRecreateKosten						= false;
		boolean bFound 								= false;
		
		// die für das Atom bestehenden Kostensätze ermitteln, um zu prüfen, ob man die Kostensätze neu schreiben muss
		try {
			recListKosten = new RECLIST_BEWEGUNG_ATOM_KOSTEN(RECORD_BEWEGUNG_ATOM_KOSTEN.FIELD__ID_BEWEGUNG_ATOM + " = " + oAtomInfo.get_IDBewegungAtom(), "");
		} catch (myException e) {
			e.printStackTrace();
		}
		
		//
		// Löschen aller Kosteneinträge zum Bewegungsatom
		//
		vSqlTemp.addAll(createStatement_DeleteKostensaetze(oAtomInfo.get_IDBewegungAtom(), oAtomInfo.get_IDBewegungVektor()));
		
		
		//
		// ermitteln der neuen Datensätze
		// 
		boolean bIstStrecke  = oAtomInfo.get_IDAdresse_Lade().equals(_sIDStreckenlager) || oAtomInfo.get_IDAdresse_Ablade().equals(_sIDStreckenlager);
		
		// nur bei Besitzerübergang relevant
		if ( !sIDBesitzerAblade.equals(sIDBesitzerLade)  ){
			if (sIDBesitzerAblade.equals(_sIDAdresseMandant)){
				// Kunde --> Mandant : WE
				sIDAdresse = oAtomInfo.get_IDAdresse_Lade();
				sArtbezLiefWEWA = "EK";
				
			} else if (sIDBesitzerLade.equals(_sIDAdresseMandant)){
				// Mandant -> Kunde : WA
				sIDAdresse = oAtomInfo.get_IDAdresse_Ablade();
				sArtbezLiefWEWA = "VK";
			} else {
				// Fremdwarenübergang direkt von Kunde zu Kunde 
			}
		}  else {
			// Kein Besitzer-Übergang, prüfen, ob es ein eigener Ladeort ist
			if (_vEigeneLagerOrte.contains(sIDAdresseLade) ){
				sIDAdresse = oAtomInfo.get_IDAdresse_Ablade();
				sArtbezLiefWEWA = "VK";
				
			} else if (_vEigeneLagerOrte.contains(sIDAdresseAblade)){
				sIDAdresse = oAtomInfo.get_IDAdresse_Lade();
				sArtbezLiefWEWA = "EK";
			} else {
				// kann eigentlich nur Strecke für Fremdware sein, bei dem es keinen Besitzerübergang gibt
			}
			
		}
		
		//
		// Ermitteln der Transportkosten
		//
		REC_Kosten_Lieferbed oLieferkosten = getTransportkostenSatz(sIDAdresseLade, oAtomInfo.get_IDArtikel(), sIDAdresseAblade);

		
		if (oLieferkosten != null && sArtbezLiefWEWA != null){
			// jetzt die Kosten umwandeln in einen Satz für die Atomkosten
			REC_Bewegung_Atom_Kosten oRec = new REC_Bewegung_Atom_Kosten(oAtomInfo.get_IDBewegungVektor(), oAtomInfo.get_IDBewegungAtom());
			
			
			oRec.set_ID_Kosten_Lieferbed_Adr(oLieferkosten.get_ID_Kosten_Lieferbed_Adr().lValue);
			oRec.set_Bemerkung("");
			oRec.set_Kostentyp("INCOTERM");
			oRec.set_Pauschal("N");
			oRec.set_erzeugtam("sysdate");
			oRec.set_letzteAenderung("sysdate");
			
			
			
			if (sArtbezLiefWEWA.equals("EK")){
				oRec.set_KostenEinzel( oLieferkosten.get_Betrag_Kosten().bdValue.multiply(bdFaktorWE) );
			} else {
				oRec.set_KostenEinzel( oLieferkosten.get_Betrag_Kosten().bdValue.multiply(bdFaktorWA) );
			}
			
			oRec.set_IstWareneingang(sArtbezLiefWEWA.equals("EK") ? "Y" : "N");
			
			
			// Gesamtkosten
			if (oAtomInfo.get_MengeAbrechnungseinheit() != null && oRec.get_KostenEinzel().bdValue != null ){
				oRec.set_KostenGesamt(oAtomInfo.get_MengeAbrechnungseinheit().multiply(oRec.get_KostenEinzel().bdValue));
			}
			
			
			// den Datensatz nur eintragen, wenn die Kosten auch <> 0 Euro sind
			if (oRec.get_KostenGesamt().bdValue != null && oRec.get_KostenGesamt().bdValue.compareTo(BigDecimal.ZERO) != 0 ){
				vSqlTemp.add(oRec.getSQLInsert());
				nCountNeueKostensaetze++;
			}
			
			// prüfen, ob der Satz schon vorhanden ist
			bFound = false;
			
			if (recListKosten != null && recListKosten.size() > 0){
				for (RECORD_BEWEGUNG_ATOM_KOSTEN r: recListKosten.values()){
					try {
						
						BigDecimal bdEinzel 	= r.get_KOSTEN_EINZEL_bdValue(BigDecimal.ZERO);
						BigDecimal bdEinzelNeu	= oRec.get_KostenEinzel().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
						BigDecimal bdGesamt 	= r.get_KOSTEN_GESAMT_bdValue(BigDecimal.ZERO);
						BigDecimal bdGesamtNeu	= oRec.get_KostenGesamt().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
						String     sWE			= r.get_IST_WARENEINGANG_cUF_NN("-");
						String	   sWENeu 		= oRec.get_IstWareneingang().sValue;
						
						if (r.get_ID_BEWEGUNG_ATOM_cUF_NN("-").equals(oRec.get_ID_Bewegung_Atom().ValuePlain()) && 
							r.get_ID_KOSTEN_LIEFERBED_ADR_cUF_NN("-").equals(oRec.get_ID_Kosten_Lieferbed_Adr().ValuePlain()) &&
							bdEinzel.compareTo(bdEinzelNeu) == 0 &&
							bdGesamt.compareTo(bdGesamtNeu) == 0 &&
							sWE.equals(sWENeu) &&
							r.get_KOSTENTYP_cUF_NN("-").equals(oRec.get_Kostentyp().sValue) &&
							r.get_PAUSCHAL_cUF_NN("-").equals(oRec.get_Pauschal().sValue))
						{
							// der Datensatz ist schon vorhanden und muss nicht noch mal erzeugt werden
							bFound = true;
							break;
						}
					} catch (myException e) {
						e.printStackTrace();
						int i = e.ErrorCode;
					}
				} 
				bRecreateKosten |= (!bFound);
			}
			
		}
		
		
		//
		// Ermitteln der Handlingskosten
		//
		String idArtikelbez = oAtomInfo.get_IDArtikelBez();
		
		Vector<REC_Kosten_Artikelbez> vHandlingskosten = calculateHandlingskosten(sIDAdresse, idArtikelbez, sArtbezLiefWEWA);

		if (vHandlingskosten != null){
			for (REC_Kosten_Artikelbez oKA : vHandlingskosten){
				// jetzt die Kosten umwandeln in einen Satz für die Atomkosten
				REC_Bewegung_Atom_Kosten oRec = new REC_Bewegung_Atom_Kosten(oAtomInfo.get_IDBewegungVektor(), oAtomInfo.get_IDBewegungAtom());
				
				oRec.set_ID_Kosten_Artbez_Lief(oKA.get_ID_Kosten_Artbez_Lief().lValue) ;
				oRec.set_Kostentyp(oKA.get_Bezeichnung().ValuePlain());
				oRec.set_Bemerkung(oKA.get_Bemerkung().ValuePlain());
				oRec.set_Pauschal(oKA.get_Pauschal().ValuePlain());
				
				BigDecimal bdEinzel 		= ( oKA.get_Betrag().bdValue != null ? oKA.get_Betrag().bdValue : BigDecimal.ZERO);
				BigDecimal bdEinzelStrecke 	= ( oKA.get_BetragStrecke().bdValue != null ? oKA.get_BetragStrecke().bdValue : BigDecimal.ZERO);
				BigDecimal bdMenge 			= ( oAtomInfo.get_MengeAbrechnungseinheit() != null ? oAtomInfo.get_MengeAbrechnungseinheit() : BigDecimal.ZERO);
				
				// Einzelkosten
				if (!bIstStrecke){
					oRec.set_KostenEinzel( bdEinzel );
				} else {
					oRec.set_KostenEinzel( bdEinzelStrecke  );
				}
				oRec.set_IstWareneingang(sArtbezLiefWEWA.equals("EK") ? "Y" : "N");
				

				// Gesamtkosten
				if (oKA.get_Pauschal().ValuePlain().equals("Y")){
					oRec.set_KostenGesamt(oRec.get_KostenEinzel().bdValue);
				} else {
					oRec.set_KostenGesamt( bdMenge.multiply(oRec.get_KostenEinzel().bdValue) ) ;
				}
				
				vSqlTemp.add(oRec.getSQLInsert());
				nCountNeueKostensaetze++;
				
				
				// prüfen, ob der Satz schon vorhanden ist
				bFound = false;
				
				if (recListKosten != null && recListKosten.size() > 0){
					for (RECORD_BEWEGUNG_ATOM_KOSTEN r: recListKosten.values()){
						try {
							
							BigDecimal bdEinzelAlt 	= r.get_KOSTEN_EINZEL_bdValue(BigDecimal.ZERO);
							BigDecimal bdEinzelNeu	= oRec.get_KostenEinzel().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
							BigDecimal bdGesamt 	= r.get_KOSTEN_GESAMT_bdValue(BigDecimal.ZERO);
							BigDecimal bdGesamtNeu	= oRec.get_KostenGesamt().bdValue.setScale(2, BigDecimal.ROUND_HALF_UP) ;
							String     sWE			= r.get_IST_WARENEINGANG_cUF_NN("-");
							String	   sWENeu 		= oRec.get_IstWareneingang().sValue;
							
							if (r.get_ID_BEWEGUNG_ATOM_cUF_NN("-").equals(oRec.get_ID_Bewegung_Atom().ValuePlain()) && 
								r.get_ID_KOSTEN_ARTBEZ_LIEF_cUF_NN("-").equals(oRec.get_ID_Kosten_Artbez_Lief().ValuePlain()) &&
								bdEinzelAlt.compareTo(bdEinzelNeu) == 0 &&
								bdGesamt.compareTo(bdGesamtNeu) == 0 &&
								sWE.equals(sWENeu) &&
								r.get_KOSTENTYP_cUF_NN("-").equals(oRec.get_Kostentyp().Value()) &&
								r.get_PAUSCHAL_cUF_NN("-").equals(oRec.get_Pauschal().Value()))
							{
								// der Datensatz ist schon vorhanden und muss nicht noch mal erzeugt werden
								bFound = true;
								break;
							}
						} catch (myException e) {
							e.printStackTrace();
						}
					}
					
					bRecreateKosten |= (!bFound);
				}
			}
		}
		

		// Update des Bewegungsatoms mit den Kostendaten
		vSqlTemp.add( getStatement_SetzeKostenInVektor(oAtomInfo.get_IDBewegungVektor()) );
	
		// die Statements nur ausführen, wenn festgestellt wurde, dass Kosten neu erzeugt werden müssen, oder 
		// dass die Anzahl der neuen Kostensätze ungleich der alten ist.
		if (bRecreateKosten || nCountNeueKostensaetze != recListKosten.size() ){
			m_vSQLStatements.addAll(vSqlTemp);
		}
		
	}
	
	
	
	/**
	 * Generiert das SqlStatement um die bestehenden Kostensätze zu löschen und den Atom-Datensatz zu aktualisieren
	 * @author manfred
	 * @date   29.11.2013
	 * @param idBewegungAtom
	 * @return
	 */
	private Vector<String> createStatement_DeleteKostensaetze(String idBewegungAtom, String idBewegungVektor){
		Vector<String> vSql = new Vector<String>();
		
		String sSql = "";
		sSql = " DELETE FROM " 
						+ bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME  +  
						" WHERE " + RECORD_BEWEGUNG_ATOM_KOSTEN.FIELD__ID_BEWEGUNG_ATOM + " = " + idBewegungAtom;
		vSql.add(sSql);
		
//		sSql = " UPDATE "  + bibE2.cTO() + "." + RECORD_BEWEGUNG_VEKTOR.TABLENAME  + " SET " +
//			   " KOSTEN_TRANSPORT_WA = null, KOSTEN_PRODUKT_WA = null, KOSTEN_TRANSPORT_WE = null, KOSTEN_PRODUKT_WE = null " + 
//						" WHERE " + RECORD_BEWEGUNG_VEKTOR.FIELD__ID_BEWEGUNG_VEKTOR + " = " + idBewegungVektor;
//		
//		vSql.add(sSql);
		
		return vSql;
	}
	
	
	
	/**
	 * Generiert das Update-Statement für die Gesamtkosten-Berechnung der Transport und Handlingskosten
	 * @author manfred
	 * @date   29.11.2013
	 * @param idBewegungVektor
	 */
	public String getStatement_SetzeKostenInVektor(String idBewegungVektor){
		String sSql = "";
		sSql = 	" UPDATE  " + bibE2.cTO() + "." + RECORD_BEWEGUNG_VEKTOR.TABLENAME + " v SET " +
				" 		 v.KOSTEN_TRANSPORT_WE 	= nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and k.ID_KOSTEN_LIEFERBED_ADR is not null and upper(k.IST_WARENEINGANG) = 'Y' ),0) " +
				" 		,v.KOSTEN_PRODUKT_WE 	= nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and k.ID_KOSTEN_ARTBEZ_LIEF is not null and upper(k.IST_WARENEINGANG) = 'Y' ),0)  " +
				" 		,v.KOSTEN_TRANSPORT_WA  = nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and k.ID_KOSTEN_LIEFERBED_ADR is not null and upper(k.IST_WARENEINGANG) = 'N' ),0)  " +
				" 		,v.KOSTEN_PRODUKT_WA 	= nvl((select sum(k.kosten_gesamt) from " + bibE2.cTO() + "." + RECORD_BEWEGUNG_ATOM_KOSTEN.TABLENAME + " k WHERE k.ID_BEWEGUNG_VEKTOR = v.ID_BEWEGUNG_VEKTOR and k.ID_KOSTEN_ARTBEZ_LIEF is not null and upper(k.IST_WARENEINGANG) = 'N' ),0)  " +
				" WHERE v.ID_BEWEGUNG_VEKTOR  = " + idBewegungVektor;
		
		return sSql;
	}
	
	
	
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht;
	 * der Vector der Statements wird nach der ausführung gelöscht!
	 * @author manfred
	 * @date 08.04.2009
	 * @param bCommit / True führt Commit() aus
	 * @return
	 */
	public boolean executeSqlStatements(boolean bCommit){
		// ausführen der Statements
		boolean bOK = true;
		
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vSQLStatements, bCommit);
	
		return bOK;
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
		// Sql-Statement sucht die Lieferbedingungen zuerst bei der Adresse, dann bei der Hauptadresse (wenn vorhanden) , dann bei den allgemeinen Lieferbedingungen
		String sSql = 
				" SELECT a.ID_KOSTEN_LIEFERBED_ADR, a.ID_ADRESSE, a.ID_ARTIKEL, a.ID_ADRESSE_ZIEL, nvl(a.BETRAG_KOSTEN,0)  " +
			 	" FROM  " + bibE2.cTO() +".JT_KOSTEN_LIEFERBED_ADR a " +
				" WHERE nvl(a.BETRAG_KOSTEN,0) != 0 ";			
			
				
				
		try {
			String [][] cKostenDaten = new String[0][0];
			cKostenDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cKostenDaten == null || cKostenDaten.length == 0){
				return ;
			}
					
			REC_Kosten_Lieferbed oResult = null;
			String sKey = "";
			for (int i=0; i< cKostenDaten.length; i++){
				// wenn ein/oder mehrere Satz/e gefunden wurden, wird der 1. Satz genommen und die Werte eingetragen.
				oResult = new REC_Kosten_Lieferbed();
				
				// den Record füllen (1.Spalte ist die Sortierung...)
				int iCol = 0;
				oResult.set_ID_Kosten_Lieferbed_Adr(cKostenDaten[i][iCol++]) ;
				oResult.set_ID_Adresse( cKostenDaten[i][iCol++]);
				oResult.set_ID_Artikel( cKostenDaten[i][iCol++]);
				oResult.set_ID_Adresse_Ziel( cKostenDaten[i][iCol++]);
				oResult.set_Betrag_Kosten(bibALL.convertDBTextToBigDecimal(cKostenDaten[i][iCol++]).setScale(3, BigDecimal.ROUND_HALF_UP));
				
				sKey = oResult.get_ID_Adresse().ValuePlain() + "#" + oResult.get_ID_Artikel().ValuePlain() + "#" + oResult.get_ID_Adresse_Ziel().ValuePlain();
				m_htKostenLieferbedCache.put(sKey, oResult);
			}
					
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Cachen der Transportkosten.")));
			m_htKostenLieferbedCache = null;
		}
				
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
		
		
		// Sql-Statement sucht die Lieferbedingungen zuerst bei der Adresse, dann bei der Hauptadresse (wenn vorhanden) , dann bei den allgemeinen Lieferbedingungen
		String sSql = 
				" SELECT a.ID_KOSTEN_LIEFERBED_ADR, a.ID_ADRESSE, a.ID_ADRESSE_ZIEL, nvl(a.BETRAG_KOSTEN,0)  "
			+ 	" FROM  " + bibE2.cTO() +".JT_KOSTEN_LIEFERBED_ADR a " +
				" WHERE  a.ID_ADRESSE = " + sIDAdr + " and a.ID_ADRESSE_ZIEL = " + sIDAdrZiel + " and A.ID_ARTIKEL = " + sIDArtikel +
				" AND nvl(a.BETRAG_KOSTEN,0) != 0 ";			
		
		
		
		try {
			
			String [][] cKostenDaten = new String[0][0];
			cKostenDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cKostenDaten == null || cKostenDaten.length == 0){
				return oResult;
			}
			
		
			// wenn ein/oder mehrere Satz/e gefunden wurden, wird der 1. Satz genommen und die Werte eingetragen.
			oResult = new REC_Kosten_Lieferbed();
			
			// den Record füllen (1.Spalte ist die Sortierung...)
			int iCol = 0;
			oResult.set_ID_Kosten_Lieferbed_Adr(cKostenDaten[0][iCol++]) ;
			oResult.set_ID_Adresse( cKostenDaten[0][iCol++]);
			oResult.set_ID_Adresse_Ziel( cKostenDaten[0][iCol++]);
			oResult.set_Betrag_Kosten(bibALL.convertDBTextToBigDecimal(cKostenDaten[0][iCol++]).setScale(3, BigDecimal.ROUND_HALF_UP));
			
			
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der Transportkosten.")));
			oResult = null;
		}
		
		return oResult;
	}
	

	
	

	/**
	 * Liefert einen Vektor von Einträgen der Artikelbezogenen Kosten zurück
	 * @author manfred
	 * @date   25.11.2013
	 * @param idLieferAdresse
	 * @param idArtikelBez
	 * @param ArtbezTypWEWA
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
			"     l.ID_ADRESSE = nvl( (select la.ID_ADRESSE_BASIS from JT_LIEFERADRESSE la where la.ID_ADRESSE_LIEFER = " + idLieferAdresse + " ),  " + idLieferAdresse + " ) " +
			" AND l.ID_ARTIKEL_BEZ = " + idArtikelBez + " " +
			" AND l.ARTBEZ_TYP = '" + ArtbezTypWEWA + "' ";
			
		
		try {
			
			String [][] cKostenDaten = new String[0][0];
			cKostenDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
			
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
