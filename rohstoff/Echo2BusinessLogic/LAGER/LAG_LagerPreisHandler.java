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
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_STATUS_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_STATUS_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_keys4save;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_Mengenermittlung;
import rohstoff.utils.bibROHSTOFF;

/**
 * Klasse zum Verarbeiten der LagerKonto-Preise der Lager-Tabelle, 
 * d.h. die Findung der Preise der Lager-Eintragungen
 * 
 * @author manfred
 * 26.05.09 
 *
 */
public class LAG_LagerPreisHandler {

	// Vector, der die abzuarbeitenden SQL-Statements beinhaltet
	Vector<String>  m_vSQLStatements = null;
	Vector<String>  m_vEigeneLagerID = null;
	
	private int    			iCount = 0;
	private MyE2_Column   	colLoopInfo = null;
	
	private boolean  		m_bWA_PREIS_KORREKTUR = false;
	private boolean  		m_bWA_GEWICHT_NETTO = false;
	
	
	
	/**
	 * @param IdMandant
	 * @throws myException 
	 */
	public LAG_LagerPreisHandler(MyE2_Column  ColLoopInfo) throws myException {
		m_vSQLStatements = new Vector<String>();
		m_vEigeneLagerID = new Vector<String>();
		
		// Mandanten-Parameter lesen
		String s_PreiskorrekturWA = __RECORD_MANDANT_ZUSATZ.get___Value("LAGER_WA_PREIS_KORREKTUR", "N");
		String s_LagerWAGewichtNetto = __RECORD_MANDANT_ZUSATZ.get___Value("LAGER_WA_GEWICHT_NETTO", "N");
		m_bWA_PREIS_KORREKTUR 	= s_PreiskorrekturWA.equalsIgnoreCase("Y") ? true : false;
		m_bWA_GEWICHT_NETTO  	= s_LagerWAGewichtNetto.equalsIgnoreCase("Y") ? true : false;
		
		// wenn das gewicht angepasst wird, dann darf der preis nicht auch noch angepasst werden.
		if (m_bWA_GEWICHT_NETTO ){
			m_bWA_PREIS_KORREKTUR = false;
		}
		
		
		initEigeneLagerIDs();
		
		this.colLoopInfo = ColLoopInfo;
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
			lMandant = null;
		}
		
		// jetzt für alle Mandanten die Lager organisieren
		String idMandant = "";
		for(RECORD_MANDANT oMandant: lMandant.values()){
			try {
				idMandant = oMandant.get_ID_MANDANT_cUF();
				ReorganisePriceEntries(idMandant);
			} catch (myException e) {
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
		"WHERE nvl(JT_LAGER_KONTO.STORNO,'N' ) = 'N'  " +
		"AND  JT_LAGER_KONTO.ID_MANDANT = ? " ;

		SqlStringExtended sqlExt = new SqlStringExtended(sSql);
		sqlExt.getValuesList().add(new Param_Long(Integer.parseInt(idMandant)));
		
		asIds = bibDB.EinzelAbfrageInArray(sqlExt);

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
		// es werden auch immer alle Preise geprüft die den Wert 0 haben
		RECLIST_LAGER_KONTO listLager 	= null;
		try {
			// ermittlung der Fuhren-preis-Updates  NUR FÜR WEs!!!
			listLager = new RECLIST_LAGER_KONTO
			("SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  " + 
			" WHERE K.ID_VPOS_TPA_FUHRE is not null  " +
			" 	 AND K.BUCHUNGSTYP = 'WE' " + 
			"    AND K.ID_ADRESSE_LAGER = " + idAdresseLager + 
			"    AND K.ID_MANDANT = " + idMandant + 
			"    AND NVL( K.STORNO ,'N') = 'N'   " + 
			"    AND (NVL( K.IST_KOMPLETT ,'N') = 'N' OR K.PREIS IS NULL OR K.PREIS = 0) "	); 
			
			
		} catch (myException e) {
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
		// es werden auch immer alle Preise geprüft die den Wert 0 haben
		listLager = null;
		try {
			// ermittlung der Fuhren-preis-Updates  NUR FÜR WAs!!!
			listLager = new RECLIST_LAGER_KONTO
			("SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  " + 
			" WHERE K.ID_VPOS_TPA_FUHRE is not null  " +
			" 	 AND K.BUCHUNGSTYP = 'WA' " + 
			"    AND K.ID_ADRESSE_LAGER = " + idAdresseLager + 
			"    AND K.ID_MANDANT = " + idMandant + 
			"    AND NVL( K.STORNO ,'N') = 'N'   " + 
			"    AND (NVL( K.IST_KOMPLETT ,'N') = 'N' OR K.PREIS IS NULL OR K.PREIS = 0 ) "   
			); 

		} catch (myException e) {
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

		String sDeleted = null;
		CLagerPreisResult oPreis = null;
		
		try {
			rg = new RECORD_VPOS_RG(IdVPosRG);
			// nur relevant, wenn kein Vorgänger und kein Nachfolger
			boolean bRelevant  =  ( 	rg.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF() == null 
									&& 	rg.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF() == null);
			
			// wenn ein Rechnungssatz gefunden wurde, dann den Eintrag der Werte in den Lägern und Lagerbewegungen löschen
			if (rg != null && bRelevant){
					idFuhre = rg.get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF();
					idFuhrenOrt = rg.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF();
					sDeleted = rg.get_DELETED_cUF();

					if (idFuhre != null ){
						//lesen des Kontoeintrags für die Fuhre  /können theoretisch bei einer Hauptfuhre auch 2 Einträge sein!! (links/rechts)
						String where = " K.ID_VPOS_TPA_FUHRE = " +  idFuhre;
						
						if (!bibALL.isEmpty(idFuhrenOrt)){
							where += " AND K.ID_VPOS_TPA_FUHRE_ORT = " + idFuhrenOrt;
						}else{
							where += " AND K.ID_VPOS_TPA_FUHRE_ORT is null ";
						}
						where += " AND NVL(K.STORNO,'N') = 'N' ";
						
						RECLIST_LAGER_KONTO rlKonto = new RECLIST_LAGER_KONTO (
								"SELECT * FROM "+ bibE2.cTO()+".JT_LAGER_KONTO K  WHERE " + where );
						
						// die Preise neu zuordnen
						for(int i=0; i<rlKonto.size(); i++){
							RECORD_LAGER_KONTO kto = rlKonto.get(i);
							
							if (	(kto.get_BUCHUNGSTYP_cUF().equals("WE") && rg.get_LAGER_VORZEICHEN_cUF().equals("1"))
									|| 
									(kto.get_BUCHUNGSTYP_cUF().equals("WA") && rg.get_LAGER_VORZEICHEN_cUF().equals("-1"))
							){
								
								// Lagerpreis ermitteln
								BigDecimal bdPreisLager = null;
								bdPreisLager = kto.get_PREIS_bdValue(null);
								
									// Rechnungspreis ermitteln
								BigDecimal bdPreis = null;
								String     sStatus = null;
								if (sDeleted.equals("Y")){
									// wenn der Rechnungssatz gelöscht wurde, dann den Preis neu ermitteln
									oPreis =  getPreisForLagerKontoPos(kto);
									bdPreis = oPreis.get_Preis();
									sStatus = oPreis.get_Status();
								}else {
									// sonst den Preis aus der Rechnung nehmen
									bdPreis = rg.get_EPREIS_RESULT_NETTO_MGE_bdValue(null);
									sStatus = LAG_Const.STATUS_PREIS_LAGER_RECHNUNG;
								}

								
								if ( bdPreisLager == null || bdPreis == null || (bdPreisLager.compareTo(bdPreis) != 0) ){
									
									String sPreisRG = null;
									if (bdPreis != null){
										sPreisRG = MyResultValue.formatDez(bdPreis.doubleValue(), 3, true) ;
										
									}
									
									kto.set_NEW_VALUE_PREIS(sPreisRG);
									kto.set_NEW_VALUE_STATUS_PREIS(sStatus);
									
									kto.set_NEW_VALUE_LETZTE_AENDERUNG("SYSDATE");
									
									// das Konto updaten
									m_vSQLStatements.add(kto.get_SQL_UPDATE_STATEMENT(null, true));
									
									// die Lagerbewegungssätze updaten
									String sPreisUpdate = getUpdateLagerBewegungsSatzPreise(kto, bdPreis);
									if (sPreisUpdate != null){
										m_vSQLStatements.add(sPreisUpdate);
									}
								}
							}
						}
					}
			}
			
			
		} catch (myException e) {
			e.printStackTrace();
			rg = null;
		}
		
	}
	
		
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht;
	 * der Vector der Statements wird nach der ausführung gelöscht!
	 * @author manfred
	 * @date 08.04.2009
	 * @param UseOwnTransaction
	 * @return
	 */
	private boolean executeSqlStatements(boolean UseOwnTransaction){
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
		// löschen der Statements
		m_vSQLStatements.clear();
		
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
	 * Initialisiert den Vector für die Lieferadressen des mandanten
	 * @throws myException 
	 */
	private void initEigeneLagerIDs() throws myException{
	
		m_vEigeneLagerID.clear();
		m_vEigeneLagerID = bibSES.get__EigeneLieferadressenOhneSonderlager(false);
//		m_vEigeneLagerID = bibROHSTOFF.get_vEigeneLieferadressen();
	}
	
	
	
		
	/**
	 * Umgebende Methode zum ermitteln der Preise der in der Liste übergebenene Lager-Objekte
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

				if (this.colLoopInfo != null)
				{
					this.colLoopInfo.removeAll();
					this.colLoopInfo.add(new MyE2_Label(new MyE2_String("Preise prüfen .... "+(this.iCount++))));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
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
	 * Liest den Preis einer Sorte einer Fuhre 
	 * Author: manfred
	 * 26.05.2009
	 *
	 * @param kto
	 *
	 * @throws myException 
	 */

	private void readPriceAndGenerateSQLStatements(RECORD_LAGER_KONTO kto) throws myException {
		

		CLagerPreisResult oPreis = getPreisForLagerKontoPos(kto); 
		BigDecimal dPreis = oPreis.get_Preis();
		
		BigDecimal dPreisOld = kto.get_PREIS_bdValue(new BigDecimal(-999999));
		
		boolean bPriceChange = (dPreis != null && kto.get_PREIS_cUF() == null) ||
							   (dPreis == null && kto.get_PREIS_cUF() != null) ||
							   (dPreis != null && kto.get_PREIS_cUF() != null && (dPreis.compareTo(dPreisOld) != 0) ) 
							    ;
		
		// jetzt das Sql Statement für die Änderung am Lagerkonto generieren...
		// wenn der neue Preis unterschiedlich zum alten Preis, dann ändern des Preises und löschen der Bewegungssätze
		if (bPriceChange){
			if (dPreis != null){
				kto.set_NEW_VALUE_PREIS(MyResultValue.formatDez(dPreis.doubleValue(), 3, true) );
				kto.set_NEW_VALUE_STATUS_PREIS(oPreis.get_Status());
			} else {
				kto.set_NEW_VALUE_PREIS(null);
				kto.set_NEW_VALUE_STATUS_PREIS(oPreis.get_Status());
			}
			kto.set_NEW_VALUE_LETZTE_AENDERUNG("SYSDATE");
			
			m_vSQLStatements.add( kto.get_SQL_UPDATE_STATEMENT(null, true) );
			
			// jetzt noch die Setzkästen zurücksetzen, analog zum LagerHandler...
			String sPreisUpdate = getUpdateLagerBewegungsSatzPreise(kto,dPreis);
			if (sPreisUpdate != null){
				m_vSQLStatements.add(sPreisUpdate);
			}
			
			// hier gleich die Daten in die DB schreiben und als Transaktion abschliessen!
			this.executeSqlStatements(true);
			
		}
	}
	
	
	
	/**
	 * Ermitteln eines Preises für eine Lagerposition
	 * Author: manfred
	 * 10.06.2009
	 *
	 * @param oLagerKonto   / das LagerKonto, für das die Daten gesammelt werden sollen.
	 * @return
	 * @throws myException 
	 */
	
	private CLagerPreisResult getPreisForLagerKontoPos(RECORD_LAGER_KONTO oLagerKonto) throws myException{
		
		// Prüfung des Eingangsparameters
		if (oLagerKonto == null ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Lagerposition angegeben.")));
			return null;
		}	

		
		String idFuhre = oLagerKonto.get_ID_VPOS_TPA_FUHRE_cUF();
		String idFuhrenOrt = oLagerKonto.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		
		// Daten aus dem Lager ermitteln
		String idSorteLager = oLagerKonto.get_ID_ARTIKEL_SORTE_cUF_NN("");
		String idAdresseLager = oLagerKonto.get_ID_ADRESSE_LAGER_cUF_NN("");
		
		String sBuchungsTyp = oLagerKonto.get_BUCHUNGSTYP_cUF_NN("");

		String idLagerKonto = oLagerKonto.get_ID_LAGER_KONTO_cUF();
		return getPreisFor_Lagerbuchung(idFuhre, null, idFuhrenOrt, idAdresseLager, idSorteLager, sBuchungsTyp);

	}
	
	
	/**
	 * ermitteln den Preis für eine Lagerbuchung definiert über die gegebenen Parameter
	 * @param idFuhre
	 * @param idFuhrenOrt
	 * @param idAdresseLager
	 * @param idSorteLager
	 * @param sBuchungsTyp
	 * @return
	 * @throws myException
	 */
//	public BigDecimal getPreisFor_Lagerbuchung(String idFuhre, String idFuhrenOrt, String idAdresseLager, String idSorteLager, String sBuchungsTyp) throws myException{
//		CLagerPreisResult oResult = getPreisFor_Lagerbuchung(idFuhre, idFuhrenOrt, idAdresseLager, idSorteLager, sBuchungsTyp );
//		return oResult.get_Preis();
//	}
//	
	
	//private BigDecimal getPreisForLagerKontoPos(RECORD_LAGER_KONTO oLagerKonto) throws myException
	public /*BigDecimal*/ CLagerPreisResult getPreisFor_Lagerbuchung(String idFuhre, 
											RECORD_VPOS_TPA_FUHRE pFuhre,
											String idFuhrenOrt, 
											String idAdresseLager, 
											String idSorteLager, 
											String sBuchungsTyp 
											) throws myException{
		
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
		if (idFuhre != null){
			if (pFuhre != null){
				oFuhre = pFuhre;
			} else {
				oFuhre = new RECORD_VPOS_TPA_FUHRE(idFuhre);
			}
			
			// alle Fuhrenorte noch gleich mit lesen...
			oListeFuhrenOrte = oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
			
			// falls es sich bei der Preisermittlung um einen Fuhrenort handelt, dann müssen wir den Fuhrenort hier merken.
			if ( idFuhrenOrt != null){
				for (int i = 0;i < oListeFuhrenOrte.get_vKeyValues().size(); i++ ){
					RECORD_VPOS_TPA_FUHRE_ORT o = oListeFuhrenOrte.get(i);
					if (o.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("").equals(idFuhrenOrt)){
						oFuhrenOrt = o;
						break;
					}
				}
				// wenn kein Fuhrenort gefunden wurde, der die gleiche ID hat, dann ist was schief...
				if (oFuhrenOrt == null){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Fuhren-Ort "+ idFuhrenOrt + " für die Ermittlung des Lagerpreises gefunden.")));
					return null;
				}
			}
		}
		
	
		
		// bei einem Warenzugang ins Lager betrachtet man die Preise der "linke" Seite der Fuhre, d.h. den "EK"
		// bei einem Warenausgang aus dem Lager betrachtet man die Preise der "rechten" Seite, d.h. die Lieferorte ("VK").
		String sQuelleZiel = "";
		if (sBuchungsTyp.equalsIgnoreCase("WE")){
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
// es werden keine Rechnungspositionen mehr herangezogen, da die Preise in der Fuhre stehen.
//		RECLIST_VPOS_RG oListeRG = oFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord();
		
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
		// 
		
		// TODO: Sonderfall Lagerausgangsfuhrenort
		
		// 1a. Der WA-Fuhrenort ist ein Lager und die Gegenseite der Fuhre ist auch ein Lager -> nur der Lagerpreis des Fuhrenortes ist gültig, da Lager-Lager-Fuhre
		//     Gilt nur, wenn man des Preises des WA-Fuhrenortes ermitteln muss.
		//
		
		
		// 2. Es gibt auf der Lieferseite nur die Fuhre, auf der Abnehmer mehrere -> Der Preis der Fuhre gilt für alle Abnehmer
		// 3. Es gibt nur die Fuhre bei den Abhnehmern, aber Fuhrenorte bei den Lieferanten -> der Durchschnittspreis aller Lieferanten zählt!!
		
		// 3a. Es gibt die Fuhre bei dem Lieferanten, aber Fuhrenorte beim Abnehmer -> Der Durchschnittspreis aller Abnehmer zählt!!
		
		// 4. Es gibt auf der Liefer sowei Abnehmerseite mehrere Orte -> Material  ist relevant und der Durchschnittspreis der gegenüberliegenden 
		// 	  Materialien ergibt den Preis 
		

		// den Lagerpreis für die Fuhre brauchen wir immer!! (Punkt 1 bis 4)
		
		// damit ist Punkt 1 und 2 abgedeckt!
		oLagerPreis = getLagerPreisForFuhre(oFuhre, sBuchungsTyp);
		listRet.add(oLagerPreis);
		
		
		
		// Punkt 3:
		if (bHasFuhrenOrteAbn == false && bHasFuhreOrteLief == true){
			// alle Lieferanten-Fuhreorte-Preise sind wichtig!!
			for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteLief){
				oLagerPreis = getLagerPreisForFuhrenOrt(oFuhre, oFuhreOrt, sBuchungsTyp);
				listRet.add(oLagerPreis);
			}
		} else
			
		// Punkt 3a:
		if (bHasFuhrenOrteAbn == true && bHasFuhreOrteLief == false){
			// alle Lieferanten-Fuhreorte-Preise sind wichtig!!
			for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteAbn){
				oLagerPreis = getLagerPreisForFuhrenOrt(oFuhre, oFuhreOrt, sBuchungsTyp);
				listRet.add(oLagerPreis);
			}
		} else
				
		// Punkt 4: es gibt Lieferanten und Abnehmer
		if (bHasFuhrenOrteAbn && bHasFuhreOrteLief){
			// erst mal alle fuhrenortpreise der Gegenseite ermitteln
			if (sQuelleZiel.equals("VK")){
				// Ort ist auf EK-Seite
				for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteAbn){
					oLagerPreis = getLagerPreisForFuhrenOrt(oFuhre, oFuhreOrt, sBuchungsTyp);
					listRet.add(oLagerPreis);
				}
			} else {
				// Ort ist auf VK-Seite
				for(RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt : oListeFuhrenOrteLief){
					oLagerPreis = getLagerPreisForFuhrenOrt(oFuhre, oFuhreOrt, sBuchungsTyp);
					listRet.add(oLagerPreis);
				}
			}
			
			// prüfen, ob für die ursprungsseite alle Materialien gleich sind:
			String idSorte = idSorteLager;
			boolean bSorteAbnehmerSindGleich = idSorte.equals(idArtikelFuhre);
			for(RECORD_VPOS_TPA_FUHRE_ORT oOrt:oListeFuhrenOrteAbn){
				bSorteAbnehmerSindGleich &= idSorte.equals(oOrt.get_ID_ARTIKEL_cUF_NN("x"));				
			}
			
			if (!bSorteAbnehmerSindGleich){
				// es dürfen nur die Lieferanten-Positionen genommen werden, die auch das gleiche Material haben
				for (int i = listRet.size() -1; i >= 0 ; i--){
					cLagerPreis o = listRet.get(i);
					if (!o.getM_Id_Sorte().equals(idSorteLager)){
						listRet.remove(i);
					}
				}
			}
		}
		
		
		// jetzt schauen, ob ein Herkunftslager ein eigenes Lager ist, wenn ja, dann muss der 
		// letzte Durchschnittspreis der Lagerware genommen werden

		BigDecimal dSumMengePreis   = new BigDecimal(0);
		BigDecimal dSumMenge = new BigDecimal(0);
		
		CLagerPreisResult oPreis = new CLagerPreisResult();
		
		// der Preisstatus bei mehereren Positionen kann unterschiedlich sein. 
		// dann darf man nur noch Preis_OK zurückgeben, da man nicht weiss, ob es Rechnung oder Fuhre oder Kontrakt ist.
		Vector<String> vPreisStatus = new Vector<String>();
		
		// jetzt den Durchschnittspreis berechnen
		for (cLagerPreis o : listRet)
		{
			
			BigDecimal dPreis = o.getM_Preis();
			BigDecimal dMenge = o.getM_Menge();
			String sStatus = o.get_PreisStatus();
					
			// es muss für jede Position immer einen Preis geben, sonst kann der resultierende Preis nicht berechnet werden
			if (dPreis == null){
				
				// sonst ist die ermittlung ungültig! Es kann kein Preis ermittelt werden
				oPreis.m_Preis = null;
				oPreis.set_Status(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
				return oPreis;
			}else{

				dSumMengePreis = dSumMengePreis.add(dPreis.multiply(dMenge));
				dSumMenge = dSumMenge.add(dMenge);
				// einfach die Preisstatus-Einträge distinct sammeln....
				if (!vPreisStatus.contains(o.get_PreisStatus())){
					vPreisStatus.add(o.get_PreisStatus());
				}
			}
		}
		
		// wenn genau ein Preisstatus ermittelt wurde, dann kann man den eintragen, sonst einfach nur OK.
		if (vPreisStatus.size() == 1){
			oPreis.set_Status(vPreisStatus.firstElement());
		} else {
			oPreis.set_Status(LAG_Const.STATUS_PREIS_LAGER_OK);
		}
		
		
		BigDecimal dRet = null;
		
		try
		{
			if (!dSumMenge.equals(BigDecimal.ZERO)){
				dRet = dSumMengePreis.divide(dSumMenge,2,BigDecimal.ROUND_HALF_UP);
			} else {
				// es kann vorkommen, dass in der Fuhre zwar ein Preis eingetragen ist, aber auf der gegenseite keine Menge.
				// deshalb ist der Preis aber doch bindend und man muss keinen Durchschnitt berechnen!
				// das gilt nur, wenn genau ein Wert vorhanden ist (hauptfuhre)
				if (listRet.size() == 1){
					cLagerPreis lp = listRet.get(0);
					if (lp!= null && lp.getM_Preis() != null ){
						dRet = lp.getM_Preis();
					}
				}
			}
			
			oPreis.set_Preis(dRet);

		} catch (ArithmeticException e)
		{
			e.printStackTrace();
			oPreis.set_Preis( null );
			oPreis.set_Status(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
		}
		
		
		return oPreis;
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
	private cLagerPreis getLagerPreisForFuhrenOrt(RECORD_VPOS_TPA_FUHRE		oFuhre,
												  RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt, 
												  String 					sBuchungsTyp ) throws myException {
		
		String idFuhreOrt = oFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		String idLagerAdresseFuhreOrt = oFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN("");
		String idSorteFuhre = oFuhreOrt.get_ID_ARTIKEL_cUF();

		// Ist der Fuhrenort ein Lager? 
		boolean bAdresseIstLager = m_vEigeneLagerID.contains(idLagerAdresseFuhreOrt);
		boolean bErmittlePreisAusLager = false;
		
		
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
		
		BigDecimal dAbzug = new BigDecimal(oFuhreOrt.get_ABZUG_MENGE_cUF_NN("0"));
		
		// Linke oder Rechte Seite des Fuhrenortes
		if (oFuhreOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equalsIgnoreCase("VK")){
			// Wareneingang: es wird die Ablademenge präferiert
			if (dAblademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dAblademenge;
			} else {
				dMenge = dLademenge;
			}
			
		}else{
			// wenn die Adresse des Fuhrenortes ein Lager ist und es ist ein Fuhrenort auf der linken Seite,
			// dann muss der Durchschnittspreis aus dem Lager als Preis ermittelt werden. 
			bErmittlePreisAusLager = true && bAdresseIstLager;
			// Warenausgang: es wird die Lademenge präferiert
			
			if (dLademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dLademenge;
			} else {
				dMenge = dAblademenge;
			}
		}
		
//		if (sBuchungsTyp.equalsIgnoreCase("WE")){
//			// Wareneingang: es wird die Ablademenge präferiert
//			if (dAblademenge.compareTo(BigDecimal.ZERO) > 0){
//				dMenge = dAblademenge;
//			} else {
//				dMenge = dLademenge;
//			}
//			
//		}else{
//			// Warenausgang: es wird die Lademenge präferiert
//			if (dLademenge.compareTo(BigDecimal.ZERO) > 0){
//				dMenge = dLademenge;
//			} else {
//				dMenge = dAblademenge;
//			}
//		}
		
		dMenge = dMenge.subtract(dAbzug);
		
		// neuen Lagerpreis erzeugen
		cLagerPreis oLagerPreis = new cLagerPreis(idLagerAdresseFuhreOrt,idSorteFuhre,null,dMenge);
		
	
		//
		// 1. Preise aus der Rechnung, wenn vorhanden...
		//
		sPreis = getPreisAusRechnung(oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), oFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), sBuchungsTyp);
		
		if (sPreis != null){
			oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_RECHNUNG);
		} else {

			//
			// den Preis aus der Fuhre holen...
			//
			if (bAdresseIstLager){
				// versuche den Preis aus der Hautpfuhre gegenüber zu lesen
				if (oFuhreOrt.get_DEF_QUELLE_ZIEL_cUF_NN("-").equalsIgnoreCase("EK")){
					// d.h. der Fuhrenort ist ein Ladeort -> Preis der Fuhre des Abladeorts lesen...
					sPreis = oFuhre.get_EPREIS_RESULT_NETTO_MGE_VK_cUF();
				} else {
					// d.h. der Fuhrenort ist ein Abladeort -> Preis der Fuhre des Ladeorts lesen...
					sPreis = oFuhre.get_EPREIS_RESULT_NETTO_MGE_EK_cUF();
				}
				
			} else {
				// wenn der Fuhrenort kein Lager ist, schauen, ob der Preis im Fuhrenort eingetragen ist
				sPreis = oFuhreOrt.get_EPREIS_RESULT_NETTO_MGE_cUF();
			}
			
			if (sPreis != null){
				oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FUHRE);
			} else {
			    //
				// schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
				//
				RECORD_VPOS_KON oRec = oFuhreOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon(); 
				if (oRec != null){
					sPreis = oRec.get_EINZELPREIS_cUF();
				}
				
				if (sPreis != null){
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_KONTRAKT);
				} else {
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
				}
			}
		}
		
		
		// Preis berechnen
		BigDecimal bdPreis = null;
		try {
			if (sPreis != null){
				bdPreis = new BigDecimal(sPreis);
			}
		} catch (Exception e) {
			bdPreis = null;
			oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
		}
		
		
		// Prüfen des Preises 
		// wenn man erkennt, dass es eine "Lager-Lager-Fuhre" sein kann, dann den Preis aus dem Lager lesen.
		if (bdPreis == null || bdPreis.compareTo(BigDecimal.ZERO) == 0){

			// fuhrenort ist ein Lager
			if (bAdresseIstLager){

				String idAusgangsLagerFuhre = oFuhre.get_ID_ADRESSE_LAGER_START_cUF();
				
				// lesen des Preises vom Ausgangslager
				// beim Fuhrenort ist es abhängig davon, ob Ladeseite oder Abladeseite.
				// bei der Ladeseite muss man prüfen, ob der Ladeort (idKunde) ein Lager ist, wenn ja, den nehmen,
				// bei der Abladeseite muss man prüfen, ob die Ladeseite der Fuhre ein Lager ist, wenn ja, den nehmen.
				
				String sIDLager = null;
				if (bErmittlePreisAusLager){
					sIDLager = idLagerAdresseFuhreOrt;
				} else {
					sIDLager = idAusgangsLagerFuhre;
				}

				bdPreis = ermittleDurchschnittspreisVonLager( sIDLager, 
						oLagerPreis.getM_Id_Sorte(), 
						oLagerPreis.getM_Menge() ,
						oFuhre,
						true
				);
				
				
				if (bdPreis == null){
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
				} else {
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_LAGER);
				}
			}
		}
		
		// Setzen des Preises
		oLagerPreis.setM_Preis(bdPreis);

		return oLagerPreis;
	}


	
	
	/**
	 * Ermitteln eines Lagerpreis-Objektes für eine Fuhrenposition
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
													String sBuchungsTyp	 ) throws myException {
		
		cLagerPreis oLagerPreis = null;
		
		String sMenge = "0";
		String sPreis = null;
		
		// Mengen bei der Fuhre ermitteln
		BigDecimal dAblademengeAbn = null;
		BigDecimal dLademengeAbn = null;
		BigDecimal dMengeAbn = null;
		BigDecimal dAbzugAbn = null;
		
		BigDecimal dAblademengeLief = null;
		BigDecimal dLademengeLief = null;
		BigDecimal dMengeLief = null;
		BigDecimal dAbzugLief = null;
		
		// WE ermitteln: hier die Ablademenge bevorzugen
		dAbzugAbn = new BigDecimal(oFuhre.get_ABZUG_ABLADEMENGE_ABN_cUF_NN("0"));
		dAblademengeAbn = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_ABN_cUF_NN("0"));
		dLademengeAbn = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_ABN_cUF_NN("0"));
		if (dAblademengeAbn.compareTo(BigDecimal.ZERO) > 0){
			dMengeAbn = dAblademengeAbn;
		} else {
			dMengeAbn = dLademengeAbn;
		}
		
		// WA ermitteln: hier die Lademenge bevorzugen
		dAbzugLief = new BigDecimal(oFuhre.get_ABZUG_LADEMENGE_LIEF_cUF_NN("0"));
		dAblademengeLief = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN("0"));
		dLademengeLief = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_LIEF_cUF_NN("0"));
		if (dLademengeLief.compareTo(BigDecimal.ZERO) > 0){
			dMengeLief = dLademengeLief;
		} else {
			dMengeLief = dAblademengeLief;
		}		
		
		
		// wenn es ein Wareneingang ist, dann wird die Ablademenge genommen.
		// die Abzüge müssen für die Preisberechnung drin sein, damit die Verhältnisse stimmen.
		if (sBuchungsTyp.equalsIgnoreCase("WE")){
			dMengeLief = dMengeLief.subtract(dAbzugLief);
			sMenge = dMengeLief.toPlainString();
		} else {
			dMengeAbn = dMengeAbn.subtract(dAbzugAbn);
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
		

		//
		//
		//
		// 1. Preise aus der Rechnung, wenn vorhanden...
		//
		sPreis = getPreisAusRechnung(oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), null, sBuchungsTyp);
		
		if (sPreis != null){
			oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_RECHNUNG);
		} else {
			
			//
			// 2. preise und Mengen aus der Fuhre lesen
			//
			if (sBuchungsTyp.equalsIgnoreCase("WE")){
				// WE
				sPreis = oFuhre.get_EPREIS_RESULT_NETTO_MGE_EK_cUF();
			} else {
				// WA
				sPreis = oFuhre.get_EPREIS_RESULT_NETTO_MGE_VK_cUF();
			}

			if (sPreis != null){
				oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FUHRE);
			} else {
				
				//
				// 3. wenn nicht, schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
				//
				RECORD_VPOS_KON oKon = null;
				if (sBuchungsTyp.equalsIgnoreCase("WA")){
					oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk();
				} else {
					oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek();
				}
				
				if (oKon != null){
					sPreis = oKon.get_EINZELPREIS_cUF();	
				} 
				
				if (sPreis != null){
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_KONTRAKT);
				} else {
					// kein Preis gefunden
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
				}
			}
		} 
		

		
		// jetzt prüfen, ob der Preis leer ist, oder ein 0-Euro-Preis ist UND vom eigenen Lager kommt.
		// Wenn die Fuhre eine Lagerfuhre ist, dann wird der Preis immer der Durchschnittspreis des Ausgangslagers sein,
		// wie die Preis-Mengen ermittlung beim Setzkasten, d.h. immer der Durchschnittspreis der Mengen, sortiert und kummuliert nach dem Preis aufsteigend
		// bis die Menge erreicht ist.
		BigDecimal bdPreis = null;
		try {
			if (sPreis != null){
				bdPreis = new BigDecimal(sPreis);
			}
		} catch (Exception e) {
			bdPreis = null;
			oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
		}
		
			if (bdPreis == null || bdPreis.compareTo(BigDecimal.ZERO) == 0){
			String idKunde = null;
			
			if (sBuchungsTyp.equalsIgnoreCase("WE")){
				idKunde = oFuhre.get_ID_ADRESSE_LAGER_START_cUF();
			} else {
				idKunde = oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF();
			}
			
			if (m_vEigeneLagerID.contains(idKunde)){
				
				// ermitteln des Preises vom Ausgangslager nach dem Durchschnittspreis der letzten x Einträge (Setzkasten in der Vergangenheit ist nicht
				// aussagefähig. Da ist der Durchschnitt der letzten 10 WE-Buchungen des Ausgangslageres viel genauer.
				String idArtikelAusgangslager = oLagerPreis.getM_Id_Sorte();
				
				// als preis wird der durchschnitt der Ausgangs-Sorte genommen (kann bei Sortenwechsel probleme machen)
				RECORD_ARTIKEL_BEZ recBezStart = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
				if (recBezStart != null) {
					idArtikelAusgangslager = recBezStart.get_ID_ARTIKEL_cUF_NN("");
				}
				
				
				String sDatum = null;
				if (oFuhre.get_DATUM_ABLADEN_cF() != null){
					sDatum = myDateHelper.ChangeNormalString2DBFormatString(oFuhre.get_DATUM_ABLADEN_cF());
				}
				bdPreis = ermittleDurchschnittspreisVonLager_Ext(oFuhre.get_ID_ADRESSE_LAGER_START_cUF(), 
																 idArtikelAusgangslager,
//																 oLagerPreis.getM_Id_Sorte(), 
																 +1, 
																 10, 
																 sDatum );
				
				
				if (bdPreis == null){
					// wenn kein Preis gefunden werden konnte, dann doch noch 
					// lesen des Preises vom Ausgangslager, nach aktuellem Setzkasten-Stand
					bdPreis = ermittleDurchschnittspreisVonLager( oFuhre.get_ID_ADRESSE_LAGER_START_cUF(),
																  idArtikelAusgangslager,
//																  oLagerPreis.getM_Id_Sorte(), 
																  oLagerPreis.getM_Menge() ,
																  oFuhre, true
																  );
				}
				
				if (bdPreis == null){
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_FEHLER);
				} else {
					oLagerPreis.set_PreisStatus(LAG_Const.STATUS_PREIS_LAGER_LAGER);
				}
			}
		}
		
		
		// 
		if ( bdPreis != null ){
			oLagerPreis.setM_Preis(bdPreis);
		}

		return oLagerPreis;
	}
	
	
	/**
	 * Ermittelt den Preis aus der zur Fuhre gehörenden Rechnungsposition
	 * @param idFuhre
	 * @param idFuhrenOrt
	 * @param Buchungstyp  WE / WA
	 * @return
	 */
	private String getPreisAusRechnung (String idFuhre, String idFuhrenOrt, String sBuchungsTyp){
		String sPreis = null;

		int lagervorzeichen = 1;
		
		// die Fuhre muss mindestens da sein.
		if (idFuhre == null) return null;
		
		
		
		// Lagervorzeichen
		String sWhere = " AND P.ID_VPOS_TPA_FUHRE_ZUGEORD =  ?" ; //+  idFuhre  ;

//		sWhere 		 += " AND P.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = ?" ;
		
		// Fuhrenort
		if (idFuhrenOrt != null) {
			sWhere += " AND P.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = ?" ; // + idFuhrenOrt ;
		} else {
			sWhere += " AND P.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " ;
		}
		
		
		sWhere += " AND P.LAGER_VORZEICHEN  =  ?";
		
		// den Abzugsbereinigten Betrag für die ursprüngliche Menge beim Warenausgang anpassen
		// da beim Warenausgang der Abzug nicht im Lager erfasst wird, bei Wareneigang jedoch schon
		String sSelect = "";
		sSelect = "  P.EPREIS_RESULT_NETTO_MGE * A.MENGENDIVISOR / P.MENGENDIVISOR ";

		
		if (sBuchungsTyp.equalsIgnoreCase("WE")){
			// WE
//			sWhere += " 1 ";
			lagervorzeichen = 1;
		} else {
			// WA
//			sWhere += " -1 "; 
			lagervorzeichen = -1;
			
			if (m_bWA_PREIS_KORREKTUR){
				sSelect = "  nvl(EINZELPREIS_RESULT,P.EPREIS_RESULT_NETTO_MGE) * A.MENGENDIVISOR / P.MENGENDIVISOR " ;
			} 
		}
		
		
		
		
		// Fuhre
		
		// Umrechnung des Mengendivisors der Rechnung auf den Mengendivisor des Artikels
		String sSql = 
//			    " SELECT P.EPREIS_RESULT_NETTO_MGE  " +
//    		    " SELECT P.EPREIS_RESULT_NETTO_MGE * A.MENGENDIVISOR / P.MENGENDIVISOR " +
			
			    " SELECT " + sSelect +  
				" FROM " + bibE2.cTO() + ".JT_VPOS_RG P " +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG K " +
				"     ON  P.ID_MANDANT  = K.ID_MANDANT " +
				"     AND P.ID_VKOPF_RG = K.ID_VKOPF_RG " +
				" INNER JOIN  " + bibE2.cTO() + ".JT_ARTIKEL A " +
			    "     ON A.ID_MANDANT = P.ID_MANDANT " +
			    "     AND P.ID_ARTIKEL = A.ID_ARTIKEL " +
				" WHERE P.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
				" AND P.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
				" AND P.ID_VPOS_RG_STORNO_NACHFOLGER IS NULL " +
				" AND nvl(P.DELETED ,'N') = 'N' " +
				" AND P.ID_VPOS_TPA_FUHRE_ZUGEORD IS NOT NULL " +
				" and P.ANZAHL > 0 " +
				" AND K.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL AND K.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
				sWhere + 
				" ";
		
		SqlStringExtended sqlExt = new SqlStringExtended(sSql);
		
		sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idFuhre)));
		
		if (S.isFull(idFuhrenOrt)){
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idFuhrenOrt)));
		} 
		
		sqlExt.getValuesList().add(new Param_Long(lagervorzeichen));
		
		
		String[][] sValues = bibDB.EinzelAbfrageInArray(sqlExt);
		
		// es dürfte genau ein Preis vorhanden sein, wenn die Abfrage stimmt
		if (sValues.length == 1 ){
			sPreis = sValues[0][0];
		}
		
		// wenn ein Wert gefunden wurde wird ein Preis geliefert, sonst "null"
		return sPreis;
		
	}
	
	
	/**
	 * Anpassen der Lagerbewegungs-Sätze, die evtl. schon mal geschrieben wurden
	 * Es wird der Preis neu gesetzt.
	 * Author: manfred
	 * 22.05.2009
	 *
	 * @param oLager
	 * @return 
	 */
	public static String getUpdateLagerBewegungsSatzPreise(RECORD_LAGER_KONTO oLager, BigDecimal neuerPreis) {
		// jetzt die Einträge der Lagerbewegungen erneuern bzw. schreiben
		String sSqlBewegung = null;

		String sPreis = "null";
		
		if (neuerPreis != null){
			sPreis = neuerPreis.toPlainString();
		}
		
		
		try
		{
			// falls ein WA storniert wird: Alle zugeordneten Wareneingänge dürfen dann nicht mehr komplett sein.
			if (oLager.get_BUCHUNGSTYP_cUF().equalsIgnoreCase("WA"))
			{
				sSqlBewegung = " UPDATE " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" SET PREIS_AUSGANG = " + sPreis +
						" WHERE ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF_NN("0") ;
			}
			else 
			{
				sSqlBewegung = " UPDATE " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" SET PREIS_EINGANG = " + sPreis +
						" WHERE ID_LAGER_KONTO_EINGANG = " + oLager.get_ID_LAGER_KONTO_cUF_NN("0");
			}
		} catch (myException e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim anpassen des Lagerkonto-  und Bewegungssatzes!")));
			sSqlBewegung = null;
		}

		return sSqlBewegung;

	}

	/**
	 * Ermittelt den Durchschnittspreis eines Lagers abhängig von der Menge, nach dem gleichen Verfahren, wie die 
	 * Verteilung der Haufen in der Lagerbewegung (-Bewertung).
	 * D.h. es werden nur die Durchschnittspreise ermittelt von den Produkten, die noch auf dem Lager liegen!!
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param Menge
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager(String idAdresseLager, String idArtikelSorte, BigDecimal Menge){
		return ermittleDurchschnittspreisVonLager(idAdresseLager, idArtikelSorte, Menge, null,false);
	}

	/**
	 * Ermittelt den Durchschnittspreis eines Lagers abhängig von der Menge, nach dem gleichen Verfahren, wie die 
	 * Verteilung der Haufen in der Lagerbewegung (-Bewertung).
	 * D.h. es werden nur die Durchschnittspreise ermittelt von den Produkten, die noch auf dem Lager liegen!!
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param Menge
	 * @param bKeineNullPreise - true, es werden keine Einträge mit Preis 0 Euro berücksichtigt
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager(String idAdresseLager, String idArtikelSorte, BigDecimal Menge, boolean bKeineNullPreise){
		return ermittleDurchschnittspreisVonLager(idAdresseLager, idArtikelSorte, Menge, null,bKeineNullPreise);
	}
	
	/**
	 * Ermittelt den Durchschnittspreis eines Lagers abhängig von der Menge, nach dem gleichen Verfahren, wie die 
	 * Verteilung der Haufen in der Lagerbewegung (-Bewertung).
	 * D.h. es werden nur die Durchschnittspreise ermittelt von den Produkten, die noch auf dem Lager liegen!!
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param Menge
	 * @param IDLagerKonto : wenn man den Durchschnittspreis haben will, ohne dass ein bestimmter Lager-Konto-Satz betroffen ist, dann 
	 * kann man den hier angeben.
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager(String idAdresseLager, String idArtikelSorte, BigDecimal Menge, RECORD_VPOS_TPA_FUHRE oFuhre,boolean bKeineNullPreise){
		BigDecimal bdRet = null;
		String idFuhre = null;
		if (oFuhre != null){
			try {
				idFuhre = oFuhre.get_ID_VPOS_TPA_FUHRE_cUF();
			} catch (myException e) {
				idFuhre = null;
			}
		}
		
		
		
		LAG_Mengenermittlung oMengenermittlung = new LAG_Mengenermittlung();
		oMengenermittlung.ermittleDurchschnittspreisVonMenge(idAdresseLager, "", idArtikelSorte, Menge, idFuhre, bKeineNullPreise);
		bdRet = oMengenermittlung.get_ErmittelterPreis();
		return bdRet;
	}
	
	
	/**
	 * Ermittelt den Durchschnittspreis der Lagereinträge. Es werden *ALLE* Einträge berücksichtigt, auch wenn sie schon verbucht sind.
	 * Es kann auch WE und WA gemischt betrachtet werden, wenn man als LagerVorzeichen == 0 setzt. (-1 == WA, +1 == WE)
	 * Mit der Anzahl der Lagerbuchungen kann man festlegen, ob man nur die letzten X Einträge zur Preisermittlung betrachten will ( X<=0 == alle Einträge)  
	 * Mit der Angabe des Datums kann man in der Vergangenheit noch die Durchschnittspreise der einzelnen WA/WEs ermitteln, um Näherungsweise richtige Werte zu 
	 * bekommen. (Für die Lagerpreis-Ermittlung bei Lager/Lager-Buchungen wichtig. 
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param LagerVorzeichen
	 * @param AnzahlBuchungen
	 * @param DatumBuchung  Datum im ISO-Format (yyyy-MM-dd)
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager_Ext(String idAdresseLager, 
			String idArtikelSorte, 
			int LagerVorzeichen, 
			int AnzahlBuchungen, 
			String DatumBuchung ){
	
			return ermittleDurchschnittspreisVonLager_Ext(idAdresseLager, idArtikelSorte, LagerVorzeichen, AnzahlBuchungen, DatumBuchung,null);
	}
	
	
	/**
	 * Ermittelt den Durchschnittspreis der Lagereinträge. Es werden *ALLE* Einträge berücksichtigt, auch wenn sie schon verbucht sind.
	 * Es kann auch WE und WA gemischt betrachtet werden, wenn man als LagerVorzeichen == 0 setzt. (-1 == WA, +1 == WE)
	 * Mit der Anzahl der Lagerbuchungen kann man festlegen, ob man nur die letzten X Einträge zur Preisermittlung betrachten will ( X<=0 == alle Einträge)  
	 * Mit der Angabe des Datums kann man in der Vergangenheit noch die Durchschnittspreise der einzelnen WA/WEs ermitteln, um Näherungsweise richtige Werte zu 
	 * bekommen. (Für die Lagerpreis-Ermittlung bei Lager/Lager-Buchungen wichtig. 
	 * @param idAdresseLager
	 * @param idArtikelSorte
	 * @param LagerVorzeichen
	 * @param AnzahlBuchungen
	 * @param DatumBuchung  Datum im ISO-Format (yyyy-MM-dd)
	 * @param idLagerKonto  eine Kontobuchung, die in der Preisermittlung nicht berücksichtigt werden soll.
	 * @return
	 */
	public static BigDecimal ermittleDurchschnittspreisVonLager_Ext(String idAdresseLager, 
																	String idArtikelSorte, 
																	int LagerVorzeichen, 
																	int AnzahlBuchungen, 
																	String DatumBuchung, 
																	String idLagerKonto){
		BigDecimal bdRet = null;
		
		// zuerst versuchen den Preis aus dem Lagerstatus zu lesen
		try {
			bdRet = ermittlePreisAusLagerstatus(idAdresseLager, idArtikelSorte, DatumBuchung);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		// falls kein Wert aus dem Lagerstatus kommt, nochmal die Werte aus dem Eingang wie vorgegeben ermitteln
		// 2013-02-05 Es müssen auch Preise mit 0 Euro berücksichtigt werden 
//		if (bdRet == null || bdRet.equals(BigDecimal.ZERO))
		if (bdRet == null )
		{
			
			// sonst den Preis suchen
			String sAnzahlLetzerBuchungen = Integer.toString(AnzahlBuchungen);
			String sBuchungstyp = LagerVorzeichen > 0 ? "WE" : "WA";
			
			
			String sWhere = "";
			if (LagerVorzeichen != 0){
				sWhere += " AND BUCHUNGSTYP = ?" ;
			}
			
			
			if (DatumBuchung != null){
				sWhere += " AND BUCHUNGSDATUM <= to_date( ? ,'yyyy-MM-dd') ";
			}
			
			if (!bibALL.isEmpty(idLagerKonto )){
				sWhere += " AND ID_LAGER_KONTO != ? " ;
			}
			
			// 2013-02-05 es müssen alle Preise, auch die 0Euro-Preise betrachtet werden 
			String sSelect = "SELECT  max(ID_ADRESSE_LAGER),  max(ID_ARTIKEL_SORTE),  " +
					" round( sum(PREIS * MENGE) / sum(MENGE) ,2) " +
					" FROM   " +
					" ( " +
					"    SELECT  *  FROM " + bibE2.cTO()+".JT_LAGER_KONTO " +
					"    WHERE  ID_ADRESSE_LAGER   = ? " + //+ idAdresseLager + 
					"        AND ID_ARTIKEL_SORTE  = ?" +  //idArtikelSorte +
					"        AND PREIS is not null " +
					"		 AND NVL(STORNO,'N') = 'N' " +		
					sWhere +
					"    ORDER BY BUCHUNGSDATUM desc " +
					"    ) " +
					" WHERE rownum <= ? " ; //+ sAnzahlLetzerBuchungen;
			
			String [][] sArrDaten = new String[0][0];
			
			SqlStringExtended sqlExt = new SqlStringExtended(sSelect);
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idAdresseLager)));
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idArtikelSorte)));
			
			if (LagerVorzeichen != 0){
				sqlExt.getValuesList().add(new Param_String("",sBuchungstyp) );
			}
			
			
			if (DatumBuchung != null){
				sqlExt.getValuesList().add(new Param_String("",DatumBuchung) );
			}
			
			if (!bibALL.isEmpty(idLagerKonto )){
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idLagerKonto)));
			}
			
			sqlExt.getValuesList().add(new Param_Long(AnzahlBuchungen) );
			
			
			sArrDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
	
			if (sArrDaten != null && sArrDaten.length == 1){
				String sValue = sArrDaten[0][2];
				bdRet = (sValue != null ? new BigDecimal(sValue) : null);
			}
		}
		return bdRet;
	}
	
	
	/**
	 * Für die Durchschnitts-Preisberechnung in der Vergangenheit kann man den Preis aus dem 
	 * Lagerstatus ziehen. Dort ist der Preis immer der Durchschnitt der Restbestände des Lagers 
	 * aus dem Setzkasten. (kommt vielleicht dem eigentlichen Preis am nächsten)
	 * @author manfred
	 * @date   14.02.2012
	 * @param idLager
	 * @param idSorte
	 * @param DatumBuchung
	 * @return
	 * @throws myException 
	 * 
	 * 2013-02-05: Um auch wirklich mögliche Preise zu ermitteln, muss der Bestand im Lagerstatus > 0 sein
	 */
	private static BigDecimal ermittlePreisAusLagerstatus_old(String idLager, String idSorte, String DatumBuchung) throws myException{
		BigDecimal bdRet = null;

		/*
		 * 2012-02-27: Fix Martin
		 * aenderung wegen uebergabe von Datumbuchung=null,
		 * der Teilstring 'null' in der Query gibt einen Fehler
		 */
//		RECLIST_STATUS_LAGER reclist = new RECLIST_STATUS_LAGER(
//							" ID_ADRESSE = " + idLager + 
//							" AND ID_SORTE = " + idSorte +
//							" AND  to_date('" + DatumBuchung + "','yyyy-mm-dd') >= BUCHUNGSDATUM ", "BUCHUNGSDATUM desc");

		String cSQL_Datum = DatumBuchung==null?"null":"'"+DatumBuchung+"'";
		
		RECLIST_STATUS_LAGER reclist = new RECLIST_STATUS_LAGER(
					" ID_ADRESSE = " + idLager + 
					" AND ID_SORTE = " + idSorte +
					" AND  to_date(" + cSQL_Datum + ",'yyyy-mm-dd') >= BUCHUNGSDATUM ", "BUCHUNGSDATUM desc");

		
		if (reclist.size() > 0){
			RECORD_STATUS_LAGER rec = reclist.get(0);
			// nur wenn auch der Bestand des Eintrags zum gefundenen Zeitpunkt > 0 ist, kann ein Preis ermittelt werden, sonst "null"
			if (rec.get_MENGE_GESAMT_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0){
				bdRet = rec.get_AVG_WERT_NICHT_NULL_bdValue(null);
			}
		}
		
		return bdRet;
	}
	

	
	private static BigDecimal ermittlePreisAusLagerstatus(String idLager, String idSorte, String DatumBuchung) throws myException{
		BigDecimal bdRet = null;

		String sql = "SELECT ID_STATUS_LAGER, MENGE_GESAMT, AVG_WERT_NICHT_NULL "
				+ " FROM "
				+ " ( "
				+ "		SELECT * FROM JT_STATUS_LAGER WHERE "
				+ "			ID_ADRESSE = ? "
				+ "			AND ID_SORTE = ? "
				+ "			AND TO_DATE( ?,'YYYY-MM-DD') >= BUCHUNGSDATUM"
				+ "		ORDER BY BUCHUNGSDATUM DESC "
				+ " ) WHERE ROWNUM <= 1 "
				+ "";
		
		SqlStringExtended sqlExt = new SqlStringExtended(sql);
		
		sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idLager)));
		sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idSorte)));
		sqlExt.getValuesList().add(new Param_String("",DatumBuchung));
		
		
		String[][] retValues = bibDB.EinzelAbfrageInArray(sqlExt);
		
		if (retValues.length > 0){
			String sIDLager = retValues[0][0];
			String sMenge   = retValues[0][1];
			String sPreis   = retValues[0][2];
			
			BigDecimal bdMenge = bibALL.convertDBTextToBigDecimal(sMenge);
			BigDecimal bdPreis = bibALL.convertDBTextToBigDecimal(sPreis);
			
			if (bdMenge != null && bdMenge.compareTo(BigDecimal.ZERO)> 0){
				bdRet = bdPreis;
			}
		}
		
		return bdRet;
	}

	
	
	/**
	 * Beinhaltet einen Preis und einen Preis-Status
	 * der im Lager-Konto mit abgelegt wird.
	 * @author manfred
	 *
	 */
	public class CLagerPreisResult{
		private BigDecimal m_Preis = null;
		private String m_Status = null;
		
		/**
		 * @param m_Preis the m_Preis to set
		 */
		public void set_Preis(BigDecimal m_Preis) {
			this.m_Preis = m_Preis;
		}
		/**
		 * @return the m_Preis
		 */
		public BigDecimal get_Preis() {
			return m_Preis;
		}
		/**
		 * @param m_Status the m_Status to set
		 */
		public void set_Status(String m_Status) {
			this.m_Status = m_Status;
		}
		/**
		 * @return the m_Status
		 */
		public String get_Status() {
			return m_Status;
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
		private String 		m_PreisStatus ;
		
		/**
		 * Standard-Konstruktor, nullt alle Daten
		 */
		public cLagerPreis() {
			m_Id_Lager = null;
			m_Id_Sorte = null;
			m_Preis = null;
			m_Menge = null;
			m_PreisStatus = null;
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



		/**
		 * @param m_ErrorStatus the m_ErrorStatus to set
		 */
		public void set_PreisStatus(String m_ErrorStatus) {
			this.m_PreisStatus = m_ErrorStatus;
		}



		/**
		 * @return the m_ErrorStatus
		 */
		public String get_PreisStatus() {
			return m_PreisStatus;
		}
		
		
	}
	
	
}
