/**
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_Mengenermittlung;

/**
 * Klasse zum Verarbeiten der LagerKonto-Preise der Lager-Tabelle, 
 * d.h. die Findung der Preise der Lager-Eintragungen
 * 
 * @author manfred
 * 26.05.09 
 *
 */
public class LAG_LagerPreisHandler_old {

	// Vector, der die abzuarbeitenden SQL-Statements beinhaltet
	Vector<String>  m_vSQLStatements = null;
	
	/**
	 * @param IdMandant
	 */
	public LAG_LagerPreisHandler_old() {
		// TODO: ...
		m_vSQLStatements = new Vector<String>();
	}
	
	
	/**
	 * Preise aller Mandaten im Lager werden automatisch erneuert/ermittelt
	 * Author: manfred
	 * 26.05.09
	 *
	 * @return
	 */
	public void ReorganisePriceEntries()	{
		
		RECLIST_MANDANT lMandant = null;
		try {
			 lMandant = new RECLIST_MANDANT (
				       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		} catch (myException e) {
			// TODO Auto-generated catch block
			lMandant = null;
		}
		
		// jetzt für alle Mandanten die Lager organisieren
		String idMandant = "";
		for(RECORD_MANDANT oMandant: lMandant.values()){
			try {
				idMandant = oMandant.get_ID_MANDANT_cUF();
				ReorganisePriceEntries(idMandant);
			} catch (myException e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
	}

	
	/**
	 * Nur die Lager-Preise des Mandanten werden automatisch reorganisiert
	 * Author: manfred
	 * 26.05.2009
	 *
	 * @param idMandant
	 * @return
	 */
	public void ReorganisePriceEntries(String idMandant){

		// ermitteln aller Lageradressen eines Mandanten:
		//ermitteln aller Läger eines Mandanten, die verwaltet werden
		String [][] asIds = new String[0][0];
		String sSql = "SELECT DISTINCT  JT_LAGER_KONTO.ID_ADRESSE_LAGER " +
		"FROM "+ bibE2.cTO()+".JT_LAGER_KONTO " +
		"WHERE (JT_LAGER_KONTO.STORNO is null OR JT_LAGER_KONTO.STORNO ='N') " +
		"AND (JT_LAGER_KONTO.IST_KOMPLETT is null OR JT_LAGER_KONTO.IST_KOMPLETT = 'N') " +
		"AND  JT_LAGER_KONTO.ID_MANDANT = " + idMandant  ;

		asIds = bibDB.EinzelAbfrageInArray(sSql);

		for (int i=0;i<asIds.length;i++)
		{
			ReorganisePriceEntries(idMandant, asIds[i][0]);
		}

	}
	
	
	/**
	 * Es werden die Preise eines bestimmtes Lager eines Mandanten reorganisiert
	 * Author: manfred
	 * 26.05.2009
	 *
	 * @param idMandant
	 * @param idAdresseLager
	 * @return
	 * @throws myException 
	 */
	public void ReorganisePriceEntries(String idMandant, String idAdresseLager) {
		//
		//  WE
		//
		// lesen aller neuen und betroffenen Lagereinträge, die neue Preise brauchen!!
		RECLIST_LAGER_KONTO listLager 	= null;
		try {
			// ermittlung der Fuhren-preis-Updates  NUR FÜR WEs!!!
			listLager = new RECLIST_LAGER_KONTO
			("SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  " + 
			" WHERE K.ID_VPOS_TPA_FUHRE is not null  " +
			" 	 AND K.BUCHUNGSTYP = 'WE' " + 
//			"    AND K.ID_VPOS_TPA_FUHRE_ORT is null  " + 
			"    AND K.ID_ADRESSE_LAGER = " + idAdresseLager + 
			"    AND K.ID_MANDANT = " + idMandant + 
			"    AND NVL( K.STORNO ,'N') = 'N'   " + 
			"    AND NVL( K.IST_KOMPLETT ,'N') = 'N' "   			); 
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim suchen der Lagerpositionen.") ));
			listLager = null;
		}
		
		// jetzt die Preise für die gefundenen Läger suchen und die SQL-Statements erzeugen
		findPriceInfosOfLagerRecords(listLager);

		//
		//  WA
		//
		// lesen aller neuen und betroffenen Lagereinträge, die neue Preise brauchen!!
		listLager = null;
		try {
			// ermittlung der Fuhren-preis-Updates  NUR FÜR WAs!!!
			listLager = new RECLIST_LAGER_KONTO
			("SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  " + 
			" WHERE K.ID_VPOS_TPA_FUHRE is not null  " +
			" 	 AND K.BUCHUNGSTYP = 'WA' " + 
//			"    AND K.ID_VPOS_TPA_FUHRE_ORT is null  " + 
			"    AND K.ID_ADRESSE_LAGER = " + idAdresseLager + 
			"    AND K.ID_MANDANT = " + idMandant + 
			"    AND NVL( K.STORNO ,'N') = 'N'   " + 
			"    AND NVL( K.IST_KOMPLETT ,'N') = 'N' "   
			); 

		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim suchen der Lagerpositionen.") ));
			listLager = null;
		}

		// jetzt die Preise für die gefundenen Läger suchen und die SQL-Statements erzeugen
		findPriceInfosOfLagerRecords(listLager);

	}


	/**
	 * Prüft, ob sich für ein Lagereintrag der Preis geändert hat, durch einen Eintrag/Storno/Delete
	 * bei einer Rechnungsposition
	 * @param IdVPosRG
	 */
	public void pruefePreisaenderungLagerRechnung(String IdVPosRG){
		// den aktuellen Rechnungssatz lesen...
		String idFuhre = null;
		String idFuhrenOrt = null;
		RECORD_VPOS_RG rg = null;
		
		try {
			rg = new RECORD_VPOS_RG(IdVPosRG);
		
			// wenn ein Rechnungssatz gefunden wurde, dann den Eintrag der Werte in den Lägern und Lagerbewegungen löschen
			if (rg != null){
				idFuhre = rg.get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF();
				idFuhrenOrt = rg.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF();
			}
			
			if (idFuhre != null ){
				//lesen des Kontoeintrags für die Fuhre  /können theoretisch bei einer Hauptfuhre auch 2 Einträge sein!! (links/rechts)
				String where = " K.ID_VPOS_TPA_FUHRE = " +  idFuhre;
//				if (!bibALL.isEmpty(idFuhrenOrt)){
//					where += " AND K.ID_VPOS_TPA_FUHRE_ORT = " + idFuhrenOrt;
//				}else{
//					where += " AND K.ID_VPOS_TPA_FUHRE_ORT is null ";
//				}
				where += " AND NVL(K.STORNO,'N') = 'N' ";
				
				RECLIST_LAGER_KONTO rlKonto = new RECLIST_LAGER_KONTO (
						"SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  WHERE " + where );

				// einfach mal alle Preise löschen, und die Zuordnungen auch.
				for(int i=0; i<rlKonto.size(); i++){
					RECORD_LAGER_KONTO kto = rlKonto.get(i);
					kto.set_NEW_VALUE_PREIS(null);
					kto.set_NEW_VALUE_LETZTE_AENDERUNG("SYSDATE");
					
					// das Konto updaten
					m_vSQLStatements.add(kto.get_SQL_UPDATE_STATEMENT(null, true));
					
					// die Lagerbewegungssätze updaten
					updateLagerBewegungsSatz(kto);
				} 
 			}
			
		
		} catch (myException e) {
			rg = null;
		}
		
	}
	
		
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht
	 * @author manfred
	 * @date 08.04.2009
	 * @param UseOwnTransaction
	 * @return
	 */
	public boolean executeSqlStatements(boolean UseOwnTransaction){
		boolean bRet = true;
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSQLStatements, UseOwnTransaction);
		bRet &= mv.get_bIsOK();
		
		if ( UseOwnTransaction){
			if (bRet){
				bibDB.Commit();
			} else {
				bibDB.Rollback();
			}
		}
			
		return bRet;
	}
	
	
	/**
	 * Gibt einen Vector mit allen erzeugten SQL-Statements zurück
	 * 
	 * @author manfred
	 * @date 19.11.2009
	 * @return
	 */
	public Vector<String> getSqlStatements(){
		return m_vSQLStatements;
	}
	
		
	/**
	 * 
	 * Author: manfred
	 * 02.06.2009
	 *
	 * @param listLager
	 */
	private void findPriceInfosOfLagerRecords(RECLIST_LAGER_KONTO listLager) {
		if (listLager == null){
			return;
		}
		
		
		RECORD_LAGER_KONTO kto = null;
		
		String id_LagerKto = "";

		// jetzt über alle Lagereinträge laufen und den Preis ermitteln
		for (int k=0;k<listLager.get_vKeyValues().size();k++)
		{
			id_LagerKto = "?";
			try {
				kto = listLager.get(k);
				id_LagerKto =  kto.get_ID_LAGER_KONTO_cUF_NN("N/A");
				
				// jetzt den Preis ermitteln und SqlStatements generieren
				readPriceAndGenerateSQLStatements(kto);

			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Preis für den Lagersatz " + id_LagerKto + " konnte nicht ermittelt werden!")));
			}
		}
	}

	
	
//	// DEBUG
//	
//	  //variante fuhre 
//    //Preis 1 (gut):
//    new RECORD_VPOS_TPA_FUHRE("1").get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_EINZELPREIS_dValue(new Double(0));
//    new RECORD_VPOS_TPA_FUHRE("1").get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_EINZELPREIS_dValue(new Double(0));
//
//    //Preis 2 (besser):   !!! wichtig! den RECORD_VPOS_RG suchen, der keine ID_VPOS_TPA_FUHRE_ORT hat und nicht gelöscht 
//    // ungerade anzahl von Datensätzen ->letzter Satz ist der gültige Preis
//    // gerade Anzahl von Datensätzen -> kein gültiger Satz
//    // isdeleted no
//    new RECORD_VPOS_TPA_FUHRE("1").get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(0).get_EINZELPREIS_dValue(new Double(0)); 
//    
//    



	/**
	 * Liest den Preis einer Sorte einer Fuhre und gibt es als formatierten String zurück;
	 * Author: manfred
	 * 26.05.2009
	 *
	 * @param kto
	 * @return Preis formatiert als String || null
	 * @throws myException 
	 */

	private void readPriceAndGenerateSQLStatements(RECORD_LAGER_KONTO kto) throws myException {
		

		BigDecimal dPreis = getPreisForLagerKontoPos(kto);
		
		
		// jetzt das Sql Statement für die Änderung am Lagerkonto generieren...
		// wenn der neue Preis unterschiedlich zum alten Preis, dann ändern des Preises und löschen der Bewegungssätze
		if ( dPreis != null &&  !dPreis.equals(new BigDecimal(kto.get_PREIS_cUF_NN("0")))){
			kto.set_NEW_VALUE_PREIS(MyResultValue.formatDez(dPreis.doubleValue(), 3, true) );
			kto.set_NEW_VALUE_LETZTE_AENDERUNG("SYSDATE");
			
			m_vSQLStatements.add( kto.get_SQL_UPDATE_STATEMENT(null, true) );
			// jetzt noch die Setzkästen zurücksetzen, analog zum LagerHandler...
			updateLagerBewegungsSatz(kto);
		}
		
	}
	
	
	
	/**
	 * Ermitteln eines Preises für eine Lagerposition, anhand von Kontrakt- bzw. Rechnungsdaten. 
	 * Author: manfred
	 * 10.06.2009
	 *
	 * @param oLagerKonto   / das LagerKonto, für das die Daten gesammelt werden sollen.
	 * @return
	 * @throws myException 
	 */
	private BigDecimal getPreisForLagerKontoPos(RECORD_LAGER_KONTO oLagerKonto) throws myException
	{
		// Prüfung des Eingangsparameters
		if (oLagerKonto == null ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Lagerposition angegeben.")));
			return null;
		}
		
		RECORD_VPOS_TPA_FUHRE oFuhre = null;				 	// die betroffene Fuhre 
		RECORD_VPOS_TPA_FUHRE_ORT oFuhrenOrt = null;			// der betroffene Fuhrenort

		RECLIST_VPOS_TPA_FUHRE_ORT oListeFuhrenOrte = null;   	// alle Fuhrenorte der Fuhre
		

		// nur die relevanten Fuhrenorte, ohne stornierte und andere unwichtige...
		ArrayList<RECORD_VPOS_TPA_FUHRE_ORT> oListeFuhrenOrteValid = new ArrayList<RECORD_VPOS_TPA_FUHRE_ORT>();
		
		
		ArrayList<RECORD_VPOS_TPA_FUHRE_ORT> oListeFuhrenOrteLief = new ArrayList<RECORD_VPOS_TPA_FUHRE_ORT>();
		boolean bHasFuhreOrteLief = false;
		ArrayList<RECORD_VPOS_TPA_FUHRE_ORT> oListeFuhrenOrteAbn = new ArrayList<RECORD_VPOS_TPA_FUHRE_ORT>();
		boolean bHasFuhrenOrteAbn = false;
		

		
		// Fuhre lesen, es können nur Preise für Lagerpositionen aus Fuhren ermittelt werden.
		if (oLagerKonto.get_ID_VPOS_TPA_FUHRE_cUF() != null){
			oFuhre = new RECORD_VPOS_TPA_FUHRE(oLagerKonto.get_ID_VPOS_TPA_FUHRE_cUF());
			if (oFuhre == null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Fuhre zur Lagerposition "+ oLagerKonto.get_ID_VPOS_TPA_FUHRE_cUF() + " gefunden.")));
				return null;
			}else{ 
				// alle Fuhrenorte noch gleich mit lesen...
				oListeFuhrenOrte = oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
				// falls es sich bei der Preisermittlung um einen Fuhrenort handelt, dann den hier gleich noch zuweisen
				if (oLagerKonto.get_ID_VPOS_TPA_FUHRE_ORT_cUF() != null){
					for (int i = 0;i < oListeFuhrenOrte.get_vKeyValues().size(); i++ ){
						RECORD_VPOS_TPA_FUHRE_ORT o = oListeFuhrenOrte.get(i);
						if (o.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("").equals(oLagerKonto.get_ID_VPOS_TPA_FUHRE_ORT_cUF())){
							oFuhrenOrt = o;
							break;
						}
					}
					// wenn kein Fuhrenort gefunden wurde, der die gleiche ID hat, dann ist was schief...
					if (oFuhrenOrt == null){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Fuhren-Ort zur Lagerposition "+ oLagerKonto.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("N/A") + " gefunden.")));
						return null;
					}
				}
			}
		}
		
		// Daten aus dem Lager ermitteln
		String idSorteLager = oLagerKonto.get_ID_ARTIKEL_SORTE_cUF_NN("");
		String idAdresseLager = oLagerKonto.get_ID_ADRESSE_LAGER_cUF_NN("");
		String sBuchungsTyp = oLagerKonto.get_BUCHUNGSTYP_cUF_NN("");
		
		
		// bei einem Warenzugang ins Lager betrachtet man die Preise der "linke" Seite der Fuhre, d.h. den "EK"
		// bei einem Warenausgang aus dem Lager betrachtet man die Preise der "rechten" Seite, d.h. die Lieferorte ("VK").
		String sQuelleZiel = "";
		if (sBuchungsTyp.equals("WE")){
			sQuelleZiel = "EK";
		}else{
			sQuelleZiel = "VK";
		}
		
		
		// alle Relevanten FuhrenOrte extrahieren, die die gleiche Sorte haben.
		if (oListeFuhrenOrte != null && !oListeFuhrenOrte.get_vKeyValues().isEmpty() ) {
			for (int i = 0; i < oListeFuhrenOrte.get_vKeyValues().size(); i++ ) {
				RECORD_VPOS_TPA_FUHRE_ORT oOrt = oListeFuhrenOrte.get(i);
				// gelöschte Fuhrenorte ausblenden
				if (oOrt.is_DELETED_NO()){
					if (oOrt.get_ID_ARTIKEL_cUF_NN("").equals(idSorteLager) && oOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals(sQuelleZiel)){
						oListeFuhrenOrteValid.add(oOrt);
					}
					// die linke und rechte Seite der Fuhrenorte noch aufteilen
					if (oOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK")){
						oListeFuhrenOrteLief.add(oOrt);
						bHasFuhreOrteLief = true;
					}else{
						oListeFuhrenOrteAbn.add(oOrt);
						bHasFuhrenOrteAbn = true;
					}
				}
			}
		}

		
		
		
		// alle Rechnungspositionen die zur Fuhre gehören
		RECLIST_VPOS_RG oListeRG = oFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord();
		
		// Daten aus der Fuhre
		String idArtikelFuhre = "";
		
		
		
		// Lageradresse und Artikel der Fuhre
		if (sQuelleZiel.equals("EK"))	{
			RECORD_ARTIKEL_BEZ recBezEK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
			if (recBezEK != null) {
				idArtikelFuhre  = recBezEK.get_ID_ARTIKEL_cUF_NN("");
			}
		} else {
			RECORD_ARTIKEL_BEZ recBezVK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
			if (recBezVK != null) {
				idArtikelFuhre = recBezVK.get_ID_ARTIKEL_cUF_NN("");
			}
		}
		
		// jetzt alle möglichen preise Sammeln in einem Array...
		ArrayList<cLagerPreis>  listRet = new ArrayList<cLagerPreis>();
		cLagerPreis oLagerPreis = null;

		
		// 4 Möglichkeiten:
		// 1. Es gibt nur die Fuhre -> Der Preis der gegenüberliegenden Fuhren-Position gilt, unabhängig vom Material
		// 2. Es gibt auf der Lieferseite nur die Fuhre, auf der Abnehmer mehrere -> Der Preis der Fuhre gilt für alle Abnehmer
		// 3. Es gibt nur die Fuhre bei den Abhnehmern, aber Fuhrenorte bei den Lieferanten -> der Durchschnittspreis aller Lieferanten zählt!!
		// 4. Es gibt auf der Liefer sowei Abnehmerseite mehrere Orte -> Material  ist relevant und der Durchschnittspreis der gegenüberliegenden 
		// 	  Materialien ergibt den Preis 
		

		// den Lagerpreis für die Fuhre brauchen wir immer!! (Punkt 1 bis 4)
		// damit ist Punkt 1 und 2 abgedeckt!
		oLagerPreis = getLagerPreisForFuhre(oFuhre, sBuchungsTyp, oListeRG);
		listRet.add(oLagerPreis);
		
		
		
		// Punkt 3:
		if (bHasFuhrenOrteAbn == false && bHasFuhreOrteLief == true){
			// alle Lieferanten-Fuhreorte-Preise sind wichtig!!
			for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteLief){
				oLagerPreis = getLagerPreisForFuhrenOrt(oFuhreOrt, sBuchungsTyp, oListeRG);
				listRet.add(oLagerPreis);
			}
		} else
		// Punkt 4: es gibt Lieferanten und Abnehmer
		if (bHasFuhrenOrteAbn && bHasFuhreOrteLief){
			// erst mal alle fuhrenortpreise ermitteln
			for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteLief){
				oLagerPreis = getLagerPreisForFuhrenOrt(oFuhreOrt, sBuchungsTyp, oListeRG);
				listRet.add(oLagerPreis);
			}
			
			// prüfen, ob für die ursprungsseite alle Materialien gleich sind:
			String idSorte = idSorteLager;
			boolean bSorteAbnehmerSindGleich = idSorte.equals(idArtikelFuhre);
			for(RECORD_VPOS_TPA_FUHRE_ORT oOrt:oListeFuhrenOrteAbn){
				bSorteAbnehmerSindGleich |= idSorte.equals(oOrt.get_ID_ARTIKEL_cUF_NN("x"));				
			}
			
			if (!bSorteAbnehmerSindGleich){
				// es dürfen nur die Lieferanten-Positionen genommen werden, die auch das gleiche Material haben
				for (int i = listRet.size() -1; i > 0 ; i--){
					cLagerPreis o = listRet.get(i);
					if (!o.getM_Id_Sorte().equals(idSorteLager)){
						listRet.remove(i);
					}
				}
			}
		}
		
		
		// jetzt schauen, ob ein Herkunftslager ein eigenes Lager ist, wenn ja, dann muss der 
		// letzte Durchschnittspreis der Lagerware genommen werden
		Integer iZeitraum = new Integer(30);  // Zeitraum in Tagen...
		
		BigDecimal dSumMengePreis   = new BigDecimal(0);
		BigDecimal dSumMenge = new BigDecimal(0);
		
		// jetzt den Durchschnittspreis berechnen
		for (cLagerPreis o : listRet)
		{
			BigDecimal dPreis = new BigDecimal(0);
			BigDecimal dMenge = o.getM_Menge();
			
			if (idAdresseLager.equals(o.getM_Id_Lager())){
				//wenn der Fuhrenort von dem es kommt ein eigener Lagerort ist, dann muss man den Durchschnittspreis der Lagerware berechnen
				dPreis = ermittleDurchschnittspreisVonLager( o.getM_Id_Lager(), o.getM_Id_Sorte(), o.getM_Menge() );
				
			}else{
				dPreis = o.getM_Preis();
			}
			
			// es muss immer einen Preis geben !
			if (dPreis == null){
				// sonst ist die ermittlung ungültig! Es kann kein Preis ermittelt werden
				return null;
			}else{
				dSumMengePreis = dSumMengePreis.add(dPreis.multiply(dMenge));
				dSumMenge = dSumMenge.add(dMenge);
			}
		}
		
		
		BigDecimal dRet = null;
		
		try
		{
			if (!dSumMenge.equals(BigDecimal.ZERO)){
				dRet = dSumMengePreis.divide(dSumMenge,2,BigDecimal.ROUND_HALF_UP);
			}
		} catch (ArithmeticException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			dRet = null;
		}
		return dRet;
	}


	
	/**
	 * Ermitteln des aktuellen Preises für eine Fuhrenort-Position
	 * Author: manfred
	 * 24.06.2009
	 *
	 * @param oFuhreOrt
	 * @param sBuchungsTyp
	 * @param oListeRG
	 * @return
	 * @throws myException
	 */
	private cLagerPreis getLagerPreisForFuhrenOrt(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt, 
														String sBuchungsTyp, 
														RECLIST_VPOS_RG oListeRG ) throws myException {
		
		String idFuhreOrt = oFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		String idLagerAdresseFuhreOrt = oFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN("");
		String idSorteFuhre = oFuhreOrt.get_ID_ARTIKEL_cUF();


		String sPreis = null;
		String sMenge = null;
		RECORD_VPOS_RG oRG;
		int countRGPos;
		sMenge = "0";
		sPreis = null;
		
		// Menge ermitteln
		BigDecimal dAblademenge = new BigDecimal (oFuhreOrt.get_ANTEIL_ABLADEMENGE_cUF_NN("0"));
		BigDecimal dLademenge = new BigDecimal(oFuhreOrt.get_ANTEIL_LADEMENGE_cUF_NN("0"));
		BigDecimal dMenge = BigDecimal.ZERO;
		

		if (sBuchungsTyp.equalsIgnoreCase("WE")){
			// Wareneingang: es wird die Ablademenge präferiert
			if (dAblademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dAblademenge;
			} else {
				dMenge = dLademenge;
			}
			
		}else{
			// Warenausgang: es wird die Lademenge präferiert
			if (dLademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dLademenge;
			} else {
				dMenge = dAblademenge;
			}
		}
		
		// neuen Lagerpreis erzeugen
		cLagerPreis oLagerPreis = new cLagerPreis(idLagerAdresseFuhreOrt,idSorteFuhre,null,dMenge);
		
		
		// nach der Rechnung suchen
		RECORD_VPOS_RG oRGCurrent = null;
		
		if (oListeRG != null && oListeRG.get_vKeyValues().size() > 0){
			countRGPos = 0;
			for (int k = 0; k < oListeRG.get_vKeyValues().size(); k++){
				oRG = oListeRG.get(k);
				// wenn der Eintrag nicht gelöscht ist und kein Fuhrenort hat, dann ist es ein gültiger Eintrag!
				if (oRG.is_DELETED_NO() && oRG.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF_NN("").equals(idFuhreOrt)){
					oRGCurrent = oRG;
					countRGPos ++;
				}
			}
			
			
			//jetzt prüfen, ob es eine ungerade Anzahl von RGPos sind, -> Gültig 
			if ( countRGPos % 2 == 1 ){
			  if (oRGCurrent != null){
				sMenge = oRGCurrent.get_ANZAHL_cUF_NN("0");
				sPreis = oRGCurrent.get_EINZELPREIS_cUF();
				if (sPreis != null)	oLagerPreis.setM_Preis(new BigDecimal(sPreis));
				if (sMenge != null)	oLagerPreis.setM_Menge(new BigDecimal(sMenge));
			  }
			}
		}
		
		
		//
		// 2. wenn nicht, schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
		//
		if (sPreis == null){
			oRGCurrent = null;
			
			RECORD_VPOS_KON oRec = oFuhreOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon(); 
			if (oRec != null){
		
				// neuen Preis schreiben	
				sPreis = oRec.get_EINZELPREIS_cUF();
				
				if (sPreis != null){
					oLagerPreis.setM_Preis(new BigDecimal(sPreis));
				}
			}
		}

		return oLagerPreis;
	}


	
	
	/**
	 * Ermitteln eines Preises für eine Fuhrenpositione
	 * Author: manfred
	 * 24.06.2009
	 *
	 * @param oFuhre
	 * @param sBuchungsTyp
	 * @param oListeRG
	 * @return
	 * @throws myException
	 */
	private cLagerPreis getLagerPreisForFuhre(	RECORD_VPOS_TPA_FUHRE oFuhre,	
													String sBuchungsTyp, 
													RECLIST_VPOS_RG oListeRG			
													 ) throws myException {
		
		cLagerPreis oLagerPreis = null;
		
		String sMenge = "0";
		String sPreis = null;
		
		// Mengen bei der Fuhre ermitteln
		BigDecimal dAblademengeAbn = null;
		BigDecimal dLademengeAbn = null;
		BigDecimal dMengeAbn = null;
		
		BigDecimal dAblademengeLief = null;
		BigDecimal dLademengeLief = null;
		BigDecimal dMengeLief = null;
		
		// WE ermitteln: hier die Ablademenge bevorzugen
		dAblademengeAbn = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_ABN_cUF_NN("0"));
		dLademengeAbn = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_ABN_cUF_NN("0"));
		if (dAblademengeAbn.compareTo(BigDecimal.ZERO) > 0){
			dMengeAbn = dAblademengeAbn;
		} else {
			dMengeAbn = dLademengeAbn;
		}
		
		// WA ermitteln: hier die Lademenge bevorzugen
		dAblademengeLief = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN("0"));
		dLademengeLief = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_LIEF_cUF_NN("0"));
		if (dLademengeLief.compareTo(BigDecimal.ZERO) > 0){
			dMengeLief = dLademengeLief;
		} else {
			dMengeLief = dAblademengeLief;
		}		
		
		if (sBuchungsTyp.equalsIgnoreCase("WE")){
			sMenge = dMengeLief.toPlainString();
		} else {
			sMenge = dMengeAbn.toPlainString();
		}
		
		
		//
		// Lageradresse und Artikel der Fuhre
		//
		String idAdresseFuhre = null;
		String idArtikelFuhre = null;
		
		if (sBuchungsTyp.equalsIgnoreCase("WE"))	{
			idAdresseFuhre = oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("");
			RECORD_ARTIKEL_BEZ recBezEK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
			if (recBezEK != null) {
				idArtikelFuhre  = recBezEK.get_ID_ARTIKEL_cUF_NN("");
			}
		} else {
			idAdresseFuhre = oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("");
			RECORD_ARTIKEL_BEZ recBezVK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
			if (recBezVK != null) {
				idArtikelFuhre = recBezVK.get_ID_ARTIKEL_cUF_NN("");
			}
		}

		
		// neuen Lagerpreis erzeugen
		oLagerPreis = new cLagerPreis(idAdresseFuhre,idArtikelFuhre,null,new BigDecimal (sMenge) );
		
		
		// jetzt den preis für die Lagerposition ermitteln
		RECORD_VPOS_RG oRGCurrent = null;
		RECORD_VPOS_RG oRG;
		
		int countRGPos;
		String sLagerVorzeichen;
		if (sBuchungsTyp.equalsIgnoreCase("WE")){
			// es werden die Warenausgänge für die Preisfindung berücksichtigt
			sLagerVorzeichen = "1";
		} else {
			sLagerVorzeichen = "-1";
		}
		
		
		countRGPos = 0;
		if (oListeRG != null && oListeRG.get_vKeyValues().size() > 0 ){
			for (int k = 0; k < oListeRG.get_vKeyValues().size(); k++){
				oRG = oListeRG.get(k);
				
				// wenn der Eintrag nicht gelöscht ist 
				// und kein Fuhrenort hat, 
				// und es sind die gleichen Läger, dann ist es ein gültiger Eintrag!
				if ( oRG.is_DELETED_NO() 
						&& oRG.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF()==null
						&& oRG.get_LAGER_VORZEICHEN_cUF_NN("").equals(sLagerVorzeichen)){
					oRGCurrent = oRG;
					countRGPos ++;
				}
			}
			
			
			//jetzt prüfen, ob es eine ungerade Anzahl von RGPos sind, -> Gültig 
			if ( countRGPos % 2 == 1 ){
				// die Menge Korrigieren, da die Menge auf der Rechnung die korrekte sein muss...
				BigDecimal dMengeRG = new BigDecimal(oRGCurrent.get_ANZAHL_cUF_NN("0"));
				if ( dMengeRG.compareTo(new BigDecimal(sMenge)) != 0 ){
					sMenge = dMengeRG.toPlainString();
				}
				
				// den Preis  und die Menge korrigieren
				sPreis = oRGCurrent.get_EINZELPREIS_cUF();
				if (sPreis != null){
					oLagerPreis.setM_Preis(new BigDecimal(sPreis));
					oLagerPreis.setM_Menge(new BigDecimal(sMenge));
				}
			}
		}
			
		
		//
		// 2. wenn nicht, schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
		//
		if (sPreis == null){
			oRGCurrent = null;
							
			RECORD_VPOS_KON oKon = null;
			if (sBuchungsTyp.equalsIgnoreCase("WA")){
				oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk();
			} else {
				oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek();
			}

			if (oKon != null){
				sPreis = oKon.get_EINZELPREIS_cUF();	
			}
			
			//jetzt prüfen, ob es eine ungerade Anzahl von RGPos sind, -> Gültig 
			if ( sPreis != null ){
				oLagerPreis.setM_Preis(new BigDecimal(sPreis));
			}
		}
		
		return oLagerPreis;
	}
	
	
	
	
	/**
	 * Anpassen der Lagerbewegungs-Sätze, die evtl. schon mal geschrieben wurden
	 * Author: manfred
	 * 22.05.2009
	 *
	 * @param oLager
	 */
	@Deprecated
	private void updateLagerBewegungsSatz1(RECORD_LAGER_KONTO oLager) {
		// jetzt die Einträge der Lagerbewegungen erneuern bzw. schreiben
		String sSqlKto = "";
		String sSqlBewegung = "";
		
		try
		{
			
			// falls ein WA storniert wird: Alle zugeordneten Wareneingänge dürfen dann nicht mehr komplett sein.
			if (oLager.get_BUCHUNGSTYP_cUF().equalsIgnoreCase("WA"))
			{
				sSqlKto = " UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO K" +
						" SET K.IST_KOMPLETT = null " +
						" WHERE K.ID_LAGER_KONTO IN ( " +
						" SELECT B.ID_LAGER_KONTO_EINGANG FROM  " + bibE2.cTO()+".JT_LAGER_BEWEGUNG B " +
						" WHERE B.ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF() + 
						")" +
						" OR K.ID_LAGER_KONTO = " + oLager.get_ID_LAGER_KONTO_cUF();
				sSqlBewegung = "DELETE FROM " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" WHERE ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF();
				

			}
			else // ?????WA-Konto-Sätze auf jeden fall das IstKomplett gelöscht werden
			{
 			}
		} catch (myException e)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim anpassen des Lagerkonto-  und Bewegungssatzes!")));
		}

		
		if (!bibALL.isEmpty(sSqlKto)){
			m_vSQLStatements.add( sSqlKto);
		}
		if (!bibALL.isEmpty(sSqlBewegung)){
			m_vSQLStatements.add( sSqlBewegung);
		}
	}
	
	
	
	
	

	
	


	/**
	 * Anpassen der Lagerbewegungs-Sätze, die evtl. schon mal geschrieben wurden
	 * Author: manfred
	 * 22.05.2009
	 *
	 * @param oLager
	 * @param bDeletePrice TODO
	 */
	private void updateLagerBewegungsSatz(RECORD_LAGER_KONTO oLager) {
		// jetzt die Einträge der Lagerbewegungen erneuern bzw. schreiben
		String sSqlKto = "";
		String sSqlBewegung = "";
		
		try
		{
			// falls ein WA storniert wird: Alle zugeordneten Wareneingänge dürfen dann nicht mehr komplett sein.
			if (oLager.get_BUCHUNGSTYP_cUF().equalsIgnoreCase("WA"))
			{
				sSqlKto = " UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" SET JT_LAGER_KONTO.IST_KOMPLETT = null " + 
						" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO IN ( " +
						" SELECT JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG FROM  " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF() + 
						")";
				sSqlBewegung = "DELETE FROM " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" WHERE ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF();
			}
			else // falls ein WE storniert wird muss für die WA-Konto-Sätze auf jeden fall das IstKomplett gelöscht werden
			{
				sSqlKto = "UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
				" SET JT_LAGER_KONTO.IST_KOMPLETT = null "+ 
				" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO IN ( " +
				" SELECT JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_AUSGANG FROM  " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
				" WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = " + oLager.get_ID_LAGER_KONTO_cUF() + 
				")";

				// und dann der Lagerbewegungs-Satz geputzt werden
				sSqlBewegung = "DELETE FROM " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
				" WHERE ID_LAGER_KONTO_EINGANG = " + oLager.get_ID_LAGER_KONTO_cUF();
			}
		} catch (myException e)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim anpassen des Lagerkonto-  und Bewegungssatzes!")));
		}

		
		if (!bibALL.isEmpty(sSqlKto)){
			m_vSQLStatements.add( sSqlKto);
		}
		if (!bibALL.isEmpty(sSqlBewegung)){
			m_vSQLStatements.add( sSqlBewegung);
		}

	}


	
	
	
	
	
	
	/**
	 * Ermittelt den aktuellen Durchschnittspreis von Lagerpreisen eines 
	 * Lagers
	 * Materials
	 * Buchungstyp
	 * und dem Zeitraum in Tagen von Heute ab.
	 * @author manfred
	 * @date 16.06.2009
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param zeitraum
	 * @return
	 * 
	 */
	@Deprecated
	public static BigDecimal ermittleDurchschnittspreisVonLager(String idAdresseLager, String idArtikelSorte, Integer zeitraum)
	{
		BigDecimal dRet = null;
		
		String sPreis = null;
		String sSql = "select sum(nvl(menge,0) * nvl(preis,0)) / sum (nvl(menge,0)) as durchschnittspreis " + 
						" from  " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" where ( JT_LAGER_KONTO.STORNO is null OR JT_LAGER_KONTO.STORNO = 'N') " +
						" and JT_LAGER_KONTO.PREIS > 0 "+
						" and JT_LAGER_KONTO.ID_ADRESSE_LAGER = " + idAdresseLager + 
						" and JT_LAGER_KONTO.ID_ARTIKEL_SORTE = "+ idArtikelSorte +
						" and JT_LAGER_KONTO.BUCHUNGSTYP = 'WE' " + 
						" and buchungsdatum >= sysdate - " + zeitraum.toString();
						

		sPreis = bibDB.EinzelAbfrage(sSql);

		if (sPreis != null && !sPreis.equals("null")){
			dRet = new BigDecimal(sPreis);
		}
		
		return dRet;
	}


	/**
	 * Ermittelt den Durchschnittspreis eines Lagers abhängig von der Menge, nach dem gleichen Verfahren, wie die 
	 * Verteilung der Haufen in der Lagerbewegung (-Bewertung).
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param Menge
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager(String idAdresseLager, String idArtikelSorte, BigDecimal Menge){
		BigDecimal bdRet = null;
		LAG_Mengenermittlung oMengenermittlung = new LAG_Mengenermittlung();
		oMengenermittlung.ermittleDurchschnittspreisVonMenge(idAdresseLager, "", idArtikelSorte, Menge );
		bdRet = oMengenermittlung.get_ErmittelterPreis();
		return bdRet;
	}
	
	
	/**
	 * Ermitteln von Daten, die für die Ermittlung durchschnittlicher Lagerpreise dienen
	 * Author: manfred
	 * 01.04.2009
	 *
	 * @param vLager
	 * @param vArtikel
	 * @return
	 * @throws myException 
	 */
	private Vector<LagerDaten> FillLagerDaten(	Vector<String> vLager, 
												Vector<String> vArtikel) throws myException{
		
		// Parameterprüfung
		if ( (vLager == null || vLager.size() == 0) && (vArtikel == null || vArtikel.size() == 0)){
			throw new myException(new MyE2_String("Es muss mindestens ein Lager oder ein Artikel angegeben sein").CTrans());
		}

		Vector<LagerDaten> vReturn = new Vector<LagerDaten>();
		
		// Sql-Statement für die Artikelauswahl
		String sArtikel = null;
		if (vArtikel != null && vArtikel.size()> 0){
			sArtikel = bibALL.ConcatenateWithoutException(vArtikel, ",", "-1") ;
			
			if (vArtikel.size() > 1){
				sArtikel = " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE IN ( " + sArtikel + ") ";
			} else {
				sArtikel = " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE  = " + sArtikel;
			}
			
		}
		// Sql-Statement für die Läger
		String sLager = null;
		if (vLager != null && vLager.size() > 0){
			sLager = bibALL.ConcatenateWithoutException(vLager, ",", "-1");
			if (vLager.size() > 1){
				sLager = " and JT_LAGER_KONTO.ID_ADRESSE_LAGER  IN ( " + sLager + ") ";
			} else {
				sLager = " and JT_LAGER_KONTO.ID_ADRESSE_LAGER  = " + sLager;
			}
		}
		

		String [][] cLagerDaten = new String[0][0];
		String sSql = "";
			sSql = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO, " +
				" max(JT_LAGER_KONTO.PREIS) ," + 
				" max(JT_LAGER_KONTO.MENGE), " +
				" max(JT_LAGER_KONTO.BUCHUNGSDATUM), " +
				" nvl( SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0  )),0.0) as verbucht, " +
				" max(JT_LAGER_KONTO.MENGE) - nvl( SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0 )),0.0) as offen " + 
				" FROM "+ bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
				" RIGHT OUTER JOIN "+ bibE2.cTO()+".JT_LAGER_KONTO on JT_LAGER_BEWEGUNG.ID_MANDANT = JT_LAGER_KONTO.ID_MANDANT " +
				" and JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO " +
				" WHERE JT_LAGER_KONTO.BUCHUNGSTYP = 'WE' " +
				 sArtikel + 
				 sLager +
				" AND (JT_LAGER_KONTO.STORNO is null or JT_LAGER_KONTO.STORNO = 'N') " +
				" AND ( JT_LAGER_KONTO.IST_KOMPLETT is null or JT_LAGER_KONTO.IST_KOMPLETT = 'N') " +
				" GROUP BY JT_LAGER_KONTO.ID_LAGER_KONTO " +
				" ORDER BY 2,4 " 
				;

			
		
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cLagerDaten == null || cLagerDaten.length == 0){
			return vReturn;
		}
		
		// sonst den Vektor füllen
		for (int i = 0; i < cLagerDaten.length; i++){
			String 		Lager = 			cLagerDaten[i][0];
			String		sPreis = cLagerDaten[i][1];
			BigDecimal 	dPreis = 			sPreis != null ? new BigDecimal(cLagerDaten[i][1]) : null ;
			BigDecimal 	dMenge = 			new BigDecimal(cLagerDaten[i][2]) ;
			String 		Buchungsdatum = 	cLagerDaten[i][3];
			BigDecimal 	dVerbuchteMenge = 	new BigDecimal(cLagerDaten[i][4]) ;
			BigDecimal 	dOffeneMenge  = 	new BigDecimal(cLagerDaten[i][5]) ;
			
			// Warenausgänge sind im Konto negativ, aber die Bewegungsliste hat immer positive Werte
			//dOffeneMenge = dMenge.subtract(dVerbuchteMenge).abs();
				
			LagerDaten l = new LagerDaten(	Lager,
											dPreis,
											dMenge,
											Buchungsdatum,
											dVerbuchteMenge,
											dOffeneMenge);
			// an den Vector dranhängen!
			vReturn.add(l);
			
		}
		return vReturn;
		
	}

	
	
	
	/**
	 * PreisInfo. Wird als Rückgabe-Wert genutzt für die Methode zum finden des Preises für einen Lagersatz
	 * 
	 * @author manfred
	 * @date 16.06.2009
	 */
	@SuppressWarnings("unused")
	private class cPreisInfo{
		BigDecimal m_bPreis = null;
		String     m_idRG = null;
		/**
		 * @return the m_bPreis
		 */
		public BigDecimal getM_bPreis()
		{
			return m_bPreis;
		}
		/**
		 * @param preis the m_bPreis to set
		 */
		public void setM_bPreis(BigDecimal preis)
		{
			m_bPreis = preis;
		}
		/**
		 * @return the m_idRG
		 */
		public String getM_idRG()
		{
			return m_idRG;
		}
		/**
		 * @param m_idrg the m_idRG to set
		 */
		public void setM_idRG(String m_idrg)
		{
			m_idRG = m_idrg;
		}
		
		
	}
	
	
	/***
	 * Lokale Klasse zum ablegen  von Preisen
	 * @author manfred
	 *
	 */
	private class cLagerPreis{
		String m_Id_Lager;
		String m_Id_Sorte;
		BigDecimal m_Preis;
		BigDecimal m_Menge;
		
		/**
		 * Standard-Konstruktor, nullt alle Daten
		 */
		public cLagerPreis() {
			// TODO Auto-generated constructor stub
			m_Id_Lager = null;
			m_Id_Sorte = null;
			m_Preis = null;
			m_Menge = null;
		}
		
		

		/**
		 * Konstruktor zum anlegen der Lager-Preis-Information
		 * @param id_Lager
		 * @param id_Sorte
		 * @param preis
		 * @param menge
		 */
		public cLagerPreis(String id_Lager, String id_Sorte, BigDecimal preis,
				BigDecimal menge) {
			super();
			m_Id_Lager = id_Lager;
			m_Id_Sorte = id_Sorte;
			m_Preis = preis;
			m_Menge = menge;
		}

		public String getKey(){
			if (m_Id_Lager != null && m_Id_Sorte != null)
			{
				return m_Id_Lager + m_Id_Sorte;
			} else return "";
		}

		/**
		 * @return the m_Id_Lager
		 */
		public String getM_Id_Lager() {
			return m_Id_Lager;
		}

		/**
		 * @param id_Lager the m_Id_Lager to set
		 */
		public void setM_Id_Lager(String id_Lager) {
			m_Id_Lager = id_Lager;
		}

		/**
		 * @return the m_Id_Sorte
		 */
		public String getM_Id_Sorte() {
			return m_Id_Sorte;
		}

		/**
		 * @param id_Sorte the m_Id_Sorte to set
		 */
		public void setM_Id_Sorte(String id_Sorte) {
			m_Id_Sorte = id_Sorte;
		}

		/**
		 * @return the m_Preis
		 */
		public BigDecimal getM_Preis() {
			return m_Preis;
		}

		/**
		 * @param preis the m_Preis to set
		 */
		public void setM_Preis(BigDecimal preis) {
			m_Preis = preis;
		}

		/**
		 * @return the m_Menge
		 */
		public BigDecimal getM_Menge() {
			return m_Menge;
		}

		/**
		 * @param menge the m_Menge to set
		 */
		public void setM_Menge(BigDecimal menge) {
			m_Menge = menge;
		}
		
		
	}
	
	
}
