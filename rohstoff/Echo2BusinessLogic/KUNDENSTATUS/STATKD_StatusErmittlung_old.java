/**
 * 
 */
package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_STATUS_KUNDE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * Klasse zum ermitteln des aktuellen Kundenstatus bzgl. der Forderungen
 * 
 */
public class STATKD_StatusErmittlung_old implements ICallableTask{

	private HashMap<String , STATKD_DataRowStatus> m_htDataRows = null;
	private Vector<String> m_vSqlStatements ;
	
	
	// Preis-Cache für die Ermittlung der Durchschnittspreise 
	private HashMap<String, BigDecimal> m_htPreiseVerkauf = null;
	private HashMap<String, BigDecimal> m_htPreiseEinkauf = null;
	

	public STATKD_StatusErmittlung_old() {
		m_htDataRows = new HashMap<String, STATKD_DataRowStatus>();
		
		m_htPreiseEinkauf = new HashMap<String, BigDecimal>();
		m_htPreiseVerkauf = new HashMap<String, BigDecimal>();
		
		m_vSqlStatements = new Vector<String>();
		
	}

	
	/**
	 * Ermitteln vom Kundenstatus aller Kunden des Mandanten,
	 * die Daten werden in die DB-Tabelle geschrieben
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public void ErmittleKundenStatus(boolean overrideOldEntries) throws myException{
		readKundenStatus(null);
		this.writeDataToDB(overrideOldEntries);
	}
	
	
	
	/**
	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
	 * Die Daten werden nicht in die DB übernommen, sonder in einer STATK_DataRowStatus zurückgegeben 
	 * @param spIDAdresse
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public STATKD_DataRowStatus ErmittleKundenStatus(String spIDAdresse) throws myException{
		STATKD_DataRowStatus oRet = null;
		if (spIDAdresse == null) {
			throw new myException("ErmittleKundenStatus(idAdresse): die Adresse wurde nicht angegeben!");
		}
		readKundenStatus(spIDAdresse);
		
		if(m_htDataRows.values().size() == 1){
			STATKD_DataRowStatus[] rows = m_htDataRows.values().toArray(new STATKD_DataRowStatus[0]);
			oRet = rows[0];
		}
		
		return oRet;
	}
	
	/**
	 * Forderungen Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
	 * Die Daten werden nicht in die DB übernommen, sonder in einer STATK_DataRowStatus zurückgegeben
	 *  
	 * @param spIDAdresse
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public STATKD_DataRowStatus ErmittleKundenStatus_Forderungen(String spIDAdresse) throws myException{
		STATKD_DataRowStatus oRet = null;
		if (spIDAdresse == null) {
			throw new myException("ErmittleKundenStatus(idAdresse): die Adresse wurde nicht angegeben!");
		}
		readKundenStatus(spIDAdresse,false,true);
		
		if(m_htDataRows.values().size() == 1){
			STATKD_DataRowStatus[] rows = m_htDataRows.values().toArray(new STATKD_DataRowStatus[0]);
			oRet = rows[0];
		}
		
		return oRet;
	}
	
	
	
	
	private String getSQLForFibu(boolean bForderung, String sWhereAdresse ){
		
//		String sSelect_FIBU_Where = (bForderung ? " >= 0 " : " < 0 ");
		String sSelect_FIBU_where = "";
		// Buchungsbloecke mit Rechnungen haben mindestens eine ID_VPOS_RG
		// oder der Buchungsblock ist ein Geldeingang/Ausgang ohne Zuordnung zu einer Rechnung / Gutschrift
		if (bForderung){
			sSelect_FIBU_where = " HAVING ( " +
								 " 			( SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) >= 0 AND ( MIN(F2.ID_VKOPF_RG) is not null OR MAX(F2.ID_VKOPF_RG) is not null) )" +
								 "    		OR" +
								 "   		(SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) < 0 AND MIN(F2.ID_VKOPF_RG) is null AND MAX(F2.ID_VKOPF_RG) is null )" +
								 "   	 )" ;
		} else {
			sSelect_FIBU_where = " HAVING ( " +
			 " 			( SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) < 0 AND ( MIN(F2.ID_VKOPF_RG) is not null OR MAX(F2.ID_VKOPF_RG) is not null) )" +
			 "    		OR" +
			 "   		(SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) > 0 AND MIN(F2.ID_VKOPF_RG) is null AND MAX(F2.ID_VKOPF_RG) is null )" +
			 "   	  )" ;
			
		}
		
		
		String sSelect_FIBU = 
		"" +
		" SELECT F.ID_ADRESSE , F.BUCHUNGSBLOCK_NR , (CASE WHEN F.ID_VKOPF_RG IS NULL THEN F.BUCHUNGSDATUM ELSE K.DRUCKDATUM END) , RP.STEUERSATZ " +
		" ,CASE WHEN (RP.ID_VPOS_RG IS NOT NULL) " +
		"  		THEN  -1 * RP.LAGER_VORZEICHEN * (NVL(RP.GESAMTPREIS_FW,0) - nvl(RP.GESAMTPREIS_ABZUG_FW,0) )" +
		"   	ELSE F.FAKTOR_BUCHUNG_PLUS_MINUS * F.ZAHLUNGSBETRAG_FREMD_WAEHRUNG" +
		"  END AS POS_PREIS_NETTO " +
		" " +
		" ,ROUND(CASE WHEN (RP.ID_VPOS_RG IS NOT NULL) " +
		"  		THEN  -1 * RP.LAGER_VORZEICHEN * (NVL(RP.GESAMTPREIS_FW,0) - nvl(RP.GESAMTPREIS_ABZUG_FW,0) ) * (1 + nvl(RP.STEUERSATZ,0) / 100)" +
		"  		ELSE F.FAKTOR_BUCHUNG_PLUS_MINUS * F.ZAHLUNGSBETRAG_FREMD_WAEHRUNG" +
		"  END,2) AS POS_PREIS_BRUTTO " +
		" " +
		" FROM  " + bibE2.cTO() + ".JT_FIBU F " +
		"  " +
		" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG K ON   " +
		"		F.ID_MANDANT = K.ID_MANDANT AND " +
		"		F.ID_VKOPF_RG = K.ID_VKOPF_RG " +
		"  " +
		" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_RG RP ON " +
		"		K.ID_MANDANT = RP.ID_MANDANT AND " +
		"		K.ID_VKOPF_RG= RP.ID_VKOPF_RG	AND " +
		"		NVL(RP.DELETED,'N') = 'N' " +
		"  " +
		// SUBSELECT zum ermitteln der gesuchten Buchungsblöcke (Einteilung in Forderungen / Verbindlichkeiten)
		"  INNER JOIN ( " +
		"				SELECT F2.ID_ADRESSE ADR, " +
		"				F2.BUCHUNGSBLOCK_NR BUCHUNGSBLOCK, " +
		"				SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) as SUMME_OFFEN " +
		" 				FROM  " + bibE2.cTO() + ".JT_FIBU F2 " +
		"				LEFT OUTER JOIN   " + bibE2.cTO() + ".JT_VKOPF_RG K2 ON   F2.ID_VKOPF_RG = K2.ID_VKOPF_RG " +
		" 				WHERE NVL(F2.VORLAEUFIG,'N')   = 'N' " +
		"				AND NVL(F2.STORNIERT,'N')= 'N' " +
		"				AND NVL(F2.BUCHUNG_GESCHLOSSEN,'N') = 'N' " +
		"				AND F2.ID_MANDANT = " + bibALL.get_ID_MANDANT() + " " + 
		"				GROUP BY F2.ID_ADRESSE, F2.BUCHUNGSBLOCK_NR " +
						sSelect_FIBU_where + 
		" 			  ) " +
		" 	ON  BUCHUNGSBLOCK = F.BUCHUNGSBLOCK_NR 	AND ADR = F.ID_ADRESSE " +
		// END SUBSELECT
		" WHERE   F.ID_MANDANT = " + bibALL.get_ID_MANDANT() + " " + 
		sWhereAdresse + 
		" ORDER BY F.ID_ADRESSE, F.BUCHUNGSBLOCK_NR, 3" +
		"  " ;
		
		return sSelect_FIBU;
		
		
	}
	

	/**
	 * List den Kundenstatus der Forderungen und Verbindlichkeiten aus und schreibt ihn in die tabelle JT_STATUS_KUNDE
	 */
	private void readKundenStatus(String spIDAdresse){
		readKundenStatus(spIDAdresse, true, true);
	}
	
	
	/**
	 * List den Kundenstatus der Forderungen aus und schreibt ihn in die tabelle JT_STATUS_KUNDE
	 */
	private void readKundenStatus(String spIDAdresse, boolean bVerbindlichkeiten, boolean bForderungen){
	
		String sWhereAdresse = "";

		String sIDAdresseMandant = bibALL.get_ID_ADRESS_MANDANT();
		
		String sSelect_RECH_FORDERUNG = "";
		String sSelect_FREIEPOS_FORDERUNG = "";
		String sSelect_FUHRE_FORDERUNG = "";
		String sSelect_FUHRENORT_FORDERUNG = "";
		
		String sSelect_RECH_VERB = "";
		String sSelect_FREIEPOS_VERB = "";
		String sSelect_FUHRE_VERB = "";
		String sSelect_FUHRENORT_VERB = "";
		
		String sSelect_FIBU = "";
		
		
		if (spIDAdresse != null){
			sWhereAdresse = " AND F.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		

		
		// FIBU
		String [][] sArrDaten = new String[0][0];
		
		
		//
		// FIBU-FORDERUNGEN NETTO
		//
		if (bForderungen){
			sSelect_FIBU = getSQLForFibu(true, sWhereAdresse);
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FIBU,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FIBU_FORDERUNG );
		}
        
		//
		// FIBU-VERBINDLICHKEITEN NETTO
		//
		if(bVerbindlichkeiten){
			sSelect_FIBU = getSQLForFibu(false, sWhereAdresse);
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FIBU,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FIBU_VERBINDLICHKEIT );
		}
		
		
		
		//
		//  nicht abgeschlossene Rechnungen
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND  JT_VKOPF_RG.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}

		if(bForderungen){
			sSelect_RECH_FORDERUNG = "" +
					" SELECT JT_VKOPF_RG.ID_ADRESSE," +
					" SUM( ( NVL(JT_VPOS_RG.GESAMTPREIS,0) - NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG,0)) * -1 * JT_VPOS_RG.LAGER_VORZEICHEN )  " +
					" FROM " + bibE2.cTO() + ".JT_VPOS_RG INNER  " +
					" JOIN " + bibE2.cTO() + ".JT_VKOPF_RG ON (JT_VPOS_RG.ID_VKOPF_RG=JT_VKOPF_RG.ID_VKOPF_RG) " +
					" WHERE NVL(JT_VKOPF_RG.ABGESCHLOSSEN,'N')='N' " +
					" AND NVL(JT_VKOPF_RG.DELETED,'N')='N' " +
					" AND NVL(JT_VPOS_RG.DELETED,'N')='N' " +
					" AND JT_VKOPF_RG.VORGANG_TYP = 'RECHNUNG' " +
					sWhereAdresse +
					" GROUP BY  JT_VKOPF_RG.ID_ADRESSE " ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_RECH_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.RECHNUNG_FORDERUNG );
		}
		
		
		if (bVerbindlichkeiten){
			sSelect_RECH_VERB = "" +
					" SELECT JT_VKOPF_RG.ID_ADRESSE," +
					" SUM( ( NVL(JT_VPOS_RG.GESAMTPREIS,0) - NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG,0)) * -1 * JT_VPOS_RG.LAGER_VORZEICHEN )  " +
					" FROM " + bibE2.cTO() + ".JT_VPOS_RG INNER  " +
					" JOIN " + bibE2.cTO() + ".JT_VKOPF_RG ON (JT_VPOS_RG.ID_VKOPF_RG=JT_VKOPF_RG.ID_VKOPF_RG) " +
					" WHERE NVL(JT_VKOPF_RG.ABGESCHLOSSEN,'N')='N' " +
					" AND NVL(JT_VKOPF_RG.DELETED,'N')='N' " +
					" AND NVL(JT_VPOS_RG.DELETED,'N')='N' " +
					" AND JT_VKOPF_RG.VORGANG_TYP = 'GUTSCHRIFT' " +
					sWhereAdresse +
					" GROUP BY  JT_VKOPF_RG.ID_ADRESSE " ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_RECH_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.RECHNUNG_VERBINDLICHKEIT );
		}
	
		
		
		
		//
		// nicht zugeordnete Rechnungspositionen
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND  JT_VPOS_RG.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}

		if(bForderungen){
			sSelect_FREIEPOS_FORDERUNG = "" +
					" SELECT JT_VPOS_RG.ID_ADRESSE," +
					" SUM( ( NVL(JT_VPOS_RG.GESAMTPREIS,0) - NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG,0) ) * -1 * JT_VPOS_RG.LAGER_VORZEICHEN) " + 
					" FROM " + bibE2.cTO() + ".JT_VPOS_RG  " +
					" WHERE JT_VPOS_RG.ID_VKOPF_RG IS NULL " +
					" AND NVL(JT_VPOS_RG.DELETED,'N')='N' " +
					" AND JT_VPOS_RG.LAGER_VORZEICHEN = -1 " +
					sWhereAdresse + 
					" GROUP BY JT_VPOS_RG.ID_ADRESSE ";
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FREIEPOS_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FREIE_POSITIONEN_FORDERUNG );
		}

		

		if (bVerbindlichkeiten){
			sSelect_FREIEPOS_VERB = "" +
					" SELECT JT_VPOS_RG.ID_ADRESSE," +
					" SUM( ( NVL(JT_VPOS_RG.GESAMTPREIS,0) - NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG,0) ) * -1 * JT_VPOS_RG.LAGER_VORZEICHEN) " + 
					" FROM " + bibE2.cTO() + ".JT_VPOS_RG  " +
					" WHERE JT_VPOS_RG.ID_VKOPF_RG IS NULL " +
					" AND NVL(JT_VPOS_RG.DELETED,'N')='N' " +
					" AND JT_VPOS_RG.LAGER_VORZEICHEN = 1 " +
					sWhereAdresse + 
					" GROUP BY JT_VPOS_RG.ID_ADRESSE ";
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FREIEPOS_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FREIE_POSITIONEN_VERBINDLICHKEIT );
		}


		
		
		
		
		//
		//  FUHREN FORDERUNGEN, vermutlich schon geliefert
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND ID_ADRESSE_ZIEL = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}

		if (bForderungen){
			sSelect_FUHRE_FORDERUNG = "" +
					"SELECT ID_ADRESSE_ZIEL, " +
					"F.ID_ARTIKEL, " + 
					"( NVL(NVL(NVL(F.ANTEIL_ABLADEMENGE_ABN , F.ANTEIL_LADEMENGE_ABN), ANTEIL_PLANMENGE_ABN),0) / A.MENGENDIVISOR ) AS MENGE," +
					" ( case when nvl(F.EPREIS_RESULT_NETTO_MGE_VK,0) = 0 then (" + 
					"           SELECT avg(EPREIS_RESULT_NETTO_MGE)  " + 	
					"           FROM   ( " + 	
					"                    SELECT   R.ID_ARTIKEL as ARTIKEL, R.EPREIS_RESULT_NETTO_MGE  FROM " + bibE2.cTO() + ".JT_VPOS_RG R " + 	
					"                    WHERE  R.LAGER_VORZEICHEN =  -1  AND nvl(R.DELETED,'N') = 'N' " + 	
					"                    ORDER BY R.ID_VPOS_RG desc " + 	
					"                    ) " + 	
					"           WHERE rownum <= 10 and F.ID_ARTIKEL = artikel " + 	
					"        ) " + 	
					"        else " + 	
					"         F.EPREIS_RESULT_NETTO_MGE_VK  " + 	
					"       end  " + 	
					" ) AS EINZELPREIS  " +
					" ,F.ID_VPOS_TPA_FUHRE " +
					"  FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K ON (F.ID_VPOS_KON_VK=K.ID_VPOS_KON)" +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A  ON  F.ID_ARTIKEL = A.ID_ARTIKEL " +
					" LEFT OUTER JOIN ( " +
					"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) ID_FUHRE_BERECHNET " +  
					"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
					"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
					"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					"         AND  NVL(R.DELETED,'N')='N' " +
					"         AND  NVL(RK.DELETED,'N')='N' " +
					" ) ON ID_FUHRE_BERECHNET = F.ID_VPOS_TPA_FUHRE " +
					" WHERE " +
					" ID_FUHRE_BERECHNET IS NULL " +
					" AND ( F.STATUS_BUCHUNG>=1 ) " +
					" AND ( F.BUCHUNGSNR_FUHRE IS NOT NULL " +  
					"              OR NVL(F.PRUEFUNG_ABLADEMENGE,'N')='Y' " +
					"              OR F.DATUM_ABLADEN IS NOT NULL " +
					"              OR NVL(F.ANTEIL_ABLADEMENGE_ABN,0)>0 " +
					"             ) " +
//					" AND (ID_VPOS_TPA_FUHRE NOT IN ( " +
//					"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) " +  
//					"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
//					"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
//					"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
//					"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
//					"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
//					"         AND  NVL(R.DELETED,'N')='N' " +
//					"         AND  NVL(RK.DELETED,'N')='N' " +
//					"     )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND NVL(NVL(NVL(F.ANTEIL_ABLADEMENGE_ABN , F.ANTEIL_LADEMENGE_ABN), ANTEIL_PLANMENGE_ABN),0) <> 0 " +
					" AND   (ID_ADRESSE_ZIEL <> " + sIDAdresseMandant + " ) " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRE_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_FORDERUNG );
		}
				
		
		
		//
		//  FUHREN-ORT FORDERUNGEN, vermutlich schon geliefert
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND O.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}

		if(bForderungen){
			
			sSelect_FUHRENORT_FORDERUNG = "" +
					" SELECT  O.ID_ADRESSE, " +
					" O.ID_ARTIKEL, " +
					" ( NVL(NVL(NVL(O.ANTEIL_ABLADEMENGE,O.ANTEIL_LADEMENGE),O.ANTEIL_PLANMENGE),0) / A.MENGENDIVISOR ) AS MENGE, " +
					" ( case when nvl(O.EPREIS_RESULT_NETTO_MGE,0) = 0 then (" + 
					"           SELECT avg(EPREIS_RESULT_NETTO_MGE)  " + 	
					"           FROM   ( " + 	
					"                    SELECT   R.ID_ARTIKEL as ARTIKEL, R.EPREIS_RESULT_NETTO_MGE  FROM " + bibE2.cTO() + ".JT_VPOS_RG R " + 	
					"                    WHERE  R.LAGER_VORZEICHEN =  -1  AND nvl(R.DELETED,'N') = 'N' " + 	
					"                    ORDER BY R.ID_VPOS_RG desc " + 	
					"                    ) " + 	
					"           WHERE rownum <= 10 and F.ID_ARTIKEL = artikel " + 	
					"        ) " + 	
					"        else " + 	
					"         O.EPREIS_RESULT_NETTO_MGE  " + 	
					"       end  " + 	
					" ) AS EINZELPREIS  " +
					" , O.ID_VPOS_TPA_FUHRE " +
					" FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE_ORT O " +
					" INNER JOIN  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" ON ( O.ID_VPOS_TPA_FUHRE = F.ID_VPOS_TPA_FUHRE ) " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
					" ON (O.ID_VPOS_KON=K.ID_VPOS_KON) " +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A ON A.ID_ARTIKEL = O.ID_ARTIKEL " +
					" WHERE (F.STATUS_BUCHUNG>=1) AND     " +
					" (    F.BUCHUNGSNR_FUHRE IS NOT NULL OR " +
					"     NVL( O.PRUEFUNG_MENGE,'N')='Y' " +
					"     OR O.DATUM_LADE_ABLADE IS NOT NULL " +
					"     OR NVL(O.ANTEIL_ABLADEMENGE,0)>0 " +
					"    ) " +
					" AND (ID_VPOS_TPA_FUHRE_ORT NOT IN ( " +
					" SELECT NVL(R.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0) " +  
					" FROM  " + bibE2.cTO() + ".JT_VPOS_RG R " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK " +
					" ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					" WHERE  R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
					"  AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					" AND  NVL(R.DELETED,'N')='N' " +
					" AND  NVL(RK.DELETED,'N')='N' " +
					" )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(O.DELETED,'N') = 'N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND (NVL(O.OHNE_ABRECHNUNG,'N')='N') " +
					" AND O.DEF_QUELLE_ZIEL = 'VK' " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRENORT_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_ORT_FORDERUNG );
		}


		
		//
		//  FUHREN VERBINDLICHKEITEN, vermutlich schon geliefert
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND F.ID_ADRESSE_START = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		
		if (bVerbindlichkeiten){
			
			sSelect_FUHRE_VERB = "" +
					" SELECT F.ID_ADRESSE_START, " +
					" F.ID_ARTIKEL, " + 
					" ( -1 * NVL(NVL(NVL(F.ANTEIL_LADEMENGE_LIEF , F.ANTEIL_ABLADEMENGE_LIEF), ANTEIL_PLANMENGE_LIEF),0) / A.MENGENDIVISOR )  AS MENGE, " +
					" ( case when nvl(F.EPREIS_RESULT_NETTO_MGE_EK,0) = 0 then (" + 
					"           SELECT avg(EPREIS_RESULT_NETTO_MGE)  " + 	
					"           FROM   ( " + 	
					"                    SELECT   R.ID_ARTIKEL as ARTIKEL, R.EPREIS_RESULT_NETTO_MGE  FROM " + bibE2.cTO() + ".JT_VPOS_RG R " + 	
					"                    WHERE  R.LAGER_VORZEICHEN =  +1  AND nvl(R.DELETED,'N') = 'N' " + 	
					"                    ORDER BY R.ID_VPOS_RG desc " + 	
					"                    ) " + 	
					"           WHERE rownum <= 10 and F.ID_ARTIKEL = artikel " + 	
					"        ) " + 	
					"        else " + 	
					"         F.EPREIS_RESULT_NETTO_MGE_EK  " + 	
					"       end  " + 	
					" ) AS EINZELPREIS  " +
					" , F.ID_VPOS_TPA_FUHRE " +
				"  FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
				"	ON (F.ID_VPOS_KON_VK=K.ID_VPOS_KON) " +
				" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A  ON  F.ID_ARTIKEL = A.ID_ARTIKEL " +

				" LEFT OUTER JOIN ( " +
				"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) ID_FUHRE_BERECHNET " +  
				"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
				"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
				"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
				"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
				"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
				"         AND  NVL(R.DELETED,'N')='N' " +
				"         AND  NVL(RK.DELETED,'N')='N' " +
				" ) ON ID_FUHRE_BERECHNET = F.ID_VPOS_TPA_FUHRE " +
				" WHERE " +
				" ID_FUHRE_BERECHNET IS NULL " +
				
				" AND ( F.STATUS_BUCHUNG>=1 ) " +
				" AND      (   F.BUCHUNGSNR_FUHRE IS NOT NULL " +  
				"              OR NVL(F.PRUEFUNG_LADEMENGE,'N')='Y' " +
				"              OR F.DATUM_AUFLADEN IS NOT NULL " +
				"              OR NVL(F.ANTEIL_LADEMENGE_LIEF,0)>0 " +
				"             ) " +
//				" AND (ID_VPOS_TPA_FUHRE NOT IN ( " +
//				"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) " +  
//				"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
//				"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
//				"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
//				"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
//				"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
//				"         AND  NVL(R.DELETED,'N')='N' " +
//				"         AND  NVL(RK.DELETED,'N')='N' " +
//				"     )) " +
				" AND (NVL(F.DELETED,'N')='N') " +
				" AND (NVL(F.IST_STORNIERT,'N')='N') " +
				" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
				" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
				" AND NVL(NVL(NVL(F.ANTEIL_LADEMENGE_LIEF , F.ANTEIL_ABLADEMENGE_LIEF), ANTEIL_PLANMENGE_LIEF),0)  <> 0 " +
				" AND   (ID_ADRESSE_START<> " + sIDAdresseMandant + " ) " +
				sWhereAdresse ;
			
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRE_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_VERBINDLICHKEIT );
		}

		//
		//  FUHREN-ORT VERBINDLICHKEITEN, vermutlich schon geliefert
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND O.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		
		if (bVerbindlichkeiten){
			
			sSelect_FUHRENORT_VERB = "" +
					" SELECT  O.ID_ADRESSE," +
					" O.ID_ARTIKEL, " +
					" ( -1 * NVL(NVL(NVL(O.ANTEIL_LADEMENGE,O.ANTEIL_ABLADEMENGE),O.ANTEIL_PLANMENGE),0) / A.MENGENDIVISOR ) AS MENGE,   " +
					" ( NVL(O.EINZELPREIS, NVL(K.EINZELPREIS,0)) ) AS PREIS" +
					" , O.ID_VPOS_TPA_FUHRE " +
					" FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE_ORT O " +
					" INNER JOIN  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" ON ( O.ID_VPOS_TPA_FUHRE = F.ID_VPOS_TPA_FUHRE ) " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
					" ON (O.ID_VPOS_KON=K.ID_VPOS_KON) " +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A ON A.ID_ARTIKEL = O.ID_ARTIKEL " +
					" WHERE (F.STATUS_BUCHUNG>=1) AND     " +
					" (    F.BUCHUNGSNR_FUHRE IS NOT NULL OR " +
					"     NVL( O.PRUEFUNG_MENGE,'N')='Y' " +
					"     OR O.DATUM_LADE_ABLADE IS NOT NULL " +
					"     OR NVL(O.ANTEIL_LADEMENGE,0)>0 " +
					"    ) " +
					" AND (ID_VPOS_TPA_FUHRE_ORT NOT IN ( " +
					" SELECT NVL(R.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0) " +  
					" FROM  " + bibE2.cTO() + ".JT_VPOS_RG R " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK " +
					" ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					" WHERE  R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
					"  AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					" AND  NVL(R.DELETED,'N')='N' " +
					" AND  NVL(RK.DELETED,'N')='N' " +
					" )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(O.DELETED,'N') = 'N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND (NVL(O.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND O.DEF_QUELLE_ZIEL = 'EK' " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRENORT_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_ORT_VERBINDLICHKEIT );
		}
		
		
		
		
		
		//	GEPLANTE
		//  FUHREN FORDERUNGEN, 
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND F.ID_ADRESSE_ZIEL = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		
		if (bForderungen){
			
			sSelect_FUHRE_FORDERUNG = "" +
					"SELECT F.ID_ADRESSE_ZIEL, " +
					"F.ID_ARTIKEL, " + 
					"( NVL(NVL(NVL(F.ANTEIL_ABLADEMENGE_ABN , F.ANTEIL_LADEMENGE_ABN), ANTEIL_PLANMENGE_ABN),0) / A.MENGENDIVISOR ) AS MENGE," +
					" ( "+ 
					" 	CASE "	+ 
					" 	WHEN F.EPREIS_RESULT_NETTO_MGE_VK is not null then F.EPREIS_RESULT_NETTO_MGE_VK  "	+ 
					" 	WHEN F.EINZELPREIS_VK is not null then F.EINZELPREIS_VK  " + 
					" 	WHEN K.EINZELPREIS is not null then K.EINZELPREIS " + 
					" 	ELSE  ( " + 
					"           SELECT avg(EPREIS_RESULT_NETTO_MGE)  " + 	
					"           FROM   ( " + 	
					"                    SELECT   R.ID_ARTIKEL as ARTIKEL, R.EPREIS_RESULT_NETTO_MGE  FROM " + bibE2.cTO() + ".JT_VPOS_RG R " + 	
					"                    WHERE  R.LAGER_VORZEICHEN =  -1  AND nvl(R.DELETED,'N') = 'N' " + 	
					"                    ORDER BY R.ID_VPOS_RG desc " + 	
					"                    ) " + 	
					"           WHERE rownum <= 10 and F.ID_ARTIKEL = artikel " + 	
					"        ) " + 	
					"       end  " + 	
					" ) AS EINZELPREIS  " +
					" , F.ID_VPOS_TPA_FUHRE " +
					"  FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K ON (F.ID_VPOS_KON_VK=K.ID_VPOS_KON)" +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A  ON  F.ID_ARTIKEL = A.ID_ARTIKEL " +
					" LEFT OUTER JOIN ( " +
					"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) ID_FUHRE_BERECHNET " +  
					"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
					"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
					"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					"         AND  NVL(R.DELETED,'N')='N' " +
					"         AND  NVL(RK.DELETED,'N')='N' " +
					" ) ON ID_FUHRE_BERECHNET = F.ID_VPOS_TPA_FUHRE " +
					" WHERE " +
					" ID_FUHRE_BERECHNET IS NULL " +

					" AND  ( F.STATUS_BUCHUNG>=1 ) " +
					" AND      (   F.BUCHUNGSNR_FUHRE IS NULL " +  
					"              AND NVL(F.PRUEFUNG_ABLADEMENGE,'N') != 'Y' " +
					"              AND F.DATUM_ABLADEN IS NULL " +
					"              AND NVL(F.ANTEIL_ABLADEMENGE_ABN,0) = 0 " +
					"             ) " +
//					" AND (ID_VPOS_TPA_FUHRE NOT IN ( " +
//					"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) " +  
//					"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
//					"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
//					"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
//					"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
//					"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
//					"         AND  NVL(R.DELETED,'N')='N' " +
//					"         AND  NVL(RK.DELETED,'N')='N' " +
//					"     )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND NVL(NVL(NVL(F.ANTEIL_ABLADEMENGE_ABN , F.ANTEIL_LADEMENGE_ABN), ANTEIL_PLANMENGE_ABN),0) <> 0 " +
					" AND   (ID_ADRESSE_ZIEL <> " + sIDAdresseMandant + " ) " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRE_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_FORDERUNG_GEPLANT );
		}
				
		
		//  GEPLANTE
		//  FUHREN-ORT FORDERUNGEN, 
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND O.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		if (bForderungen){
			sSelect_FUHRENORT_FORDERUNG = "" +
					" SELECT  O.ID_ADRESSE, " +
					" O.ID_ARTIKEL, " +
					" ( NVL(NVL(NVL(O.ANTEIL_ABLADEMENGE,O.ANTEIL_LADEMENGE),O.ANTEIL_PLANMENGE),0) / A.MENGENDIVISOR ) AS MENGE, " +
					"  ( CASE  " + 
					"            WHEN O.EPREIS_RESULT_NETTO_MGE is not null then O.EPREIS_RESULT_NETTO_MGE    " + 
					"            WHEN O.EINZELPREIS is not null then O.EINZELPREIS    " + 
					"            WHEN K.EINZELPREIS is not null then K.EINZELPREIS    " + 
					"            else    " + 
					"                (    " + 
					"                    SELECT    " + 
					"                        AVG(EPREIS_RESULT_NETTO_MGE)    " + 
					"                    FROM    " + 
					"                        (    " + 
					"                            SELECT    " + 
					"                                R.ID_ARTIKEL AS ARTIKEL,    " + 
					"                                R.EPREIS_RESULT_NETTO_MGE    " + 
					"                            FROM    " + 
					"                                " + bibE2.cTO() + ".JT_VPOS_RG R    " + 
					"                            WHERE    " + 
					"                                R.LAGER_VORZEICHEN = -1   AND nvl(R.DELETED,'N') = 'N'  " + 
					"                            ORDER BY R.ID_VPOS_RG DESC ) 	" + 
					"                    WHERE     " + 
					"                        rownum <= 10     " + 
					"                    AND O.ID_ARTIKEL = artikel )     " + 
					"       END )  AS EINZELPREIS   " + 
					" , O.ID_VPOS_TPA_FUHRE " +
					" FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE_ORT O " +
					" INNER JOIN  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" ON ( O.ID_VPOS_TPA_FUHRE = F.ID_VPOS_TPA_FUHRE ) " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
					" ON (O.ID_VPOS_KON=K.ID_VPOS_KON) " +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A ON A.ID_ARTIKEL = O.ID_ARTIKEL " +
					" WHERE (F.STATUS_BUCHUNG>=1) AND     " +
					" (    F.BUCHUNGSNR_FUHRE IS  NULL " +
					"     AND NVL( O.PRUEFUNG_MENGE,'N')!='Y' " +
					"     AND O.DATUM_LADE_ABLADE IS NULL " +
					"     AND NVL(O.ANTEIL_ABLADEMENGE,0) = 0 " +
					"    ) " +
					" AND (ID_VPOS_TPA_FUHRE_ORT NOT IN ( " +
					" SELECT NVL(R.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0) " +  
					" FROM  " + bibE2.cTO() + ".JT_VPOS_RG R " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK " +
					" ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					" WHERE  R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
					"  AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					" AND  NVL(R.DELETED,'N')='N' " +
					" AND  NVL(RK.DELETED,'N')='N' " +
					" )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(O.DELETED,'N') = 'N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND (NVL(O.OHNE_ABRECHNUNG,'N')='N') " +
					" AND O.DEF_QUELLE_ZIEL = 'VK' " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRENORT_FORDERUNG,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_ORT_FORDERUNG_GEPLANT );
			
		}


		
		//  GEPLANTE
		//  FUHREN VERBINDLICHKEITEN, 
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND F.ID_ADRESSE_START = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		if (bVerbindlichkeiten){
			sSelect_FUHRE_VERB = "" +
					" SELECT F.ID_ADRESSE_START, " +
					" F.ID_ARTIKEL, " + 
					" ( -1 * NVL(NVL(NVL(F.ANTEIL_LADEMENGE_LIEF , F.ANTEIL_ABLADEMENGE_LIEF), ANTEIL_PLANMENGE_LIEF),0) / A.MENGENDIVISOR )  AS MENGE, " +
					" ( NVL(F.EINZELPREIS_EK, NVL( K.EINZELPREIS,0)) ) AS PREIS  " + 
					" , F.ID_VPOS_TPA_FUHRE " +
				"  FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
				"	ON (F.ID_VPOS_KON_VK=K.ID_VPOS_KON) " +
				" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A  ON  F.ID_ARTIKEL = A.ID_ARTIKEL " +
				" LEFT OUTER JOIN ( " +
				"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) ID_FUHRE_BERECHNET " +  
				"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
				"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
				"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
				"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
				"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
				"         AND  NVL(R.DELETED,'N')='N' " +
				"         AND  NVL(RK.DELETED,'N')='N' " +
				" ) ON ID_FUHRE_BERECHNET = F.ID_VPOS_TPA_FUHRE " +
				" WHERE " +
				" 		ID_FUHRE_BERECHNET IS NULL " +
				" AND ( F.STATUS_BUCHUNG>=1 ) " +
				" AND      (   F.BUCHUNGSNR_FUHRE IS NULL " +  
				"              AND NVL(F.PRUEFUNG_LADEMENGE,'N') != 'Y' " +
				"              AND F.DATUM_AUFLADEN IS NULL " +
				"              AND NVL(F.ANTEIL_LADEMENGE_LIEF,0) = 0 " +
				"             ) " +
//				" AND (ID_VPOS_TPA_FUHRE NOT IN ( " +
//				"   SELECT NVL(R.ID_VPOS_TPA_FUHRE_ZUGEORD,0) " +  
//				"   FROM  " + bibE2.cTO() + ".JT_VPOS_RG R  LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
//				"   WHERE   R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL  " +
//				"         AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
//				"         AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL  " +
//				"         AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
//				"         AND  NVL(R.DELETED,'N')='N' " +
//				"         AND  NVL(RK.DELETED,'N')='N' " +
//				"     )) " +
				" AND (NVL(F.DELETED,'N')='N') " +
				" AND (NVL(F.IST_STORNIERT,'N')='N') " +
				" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
				" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
				" AND NVL(NVL(NVL(F.ANTEIL_LADEMENGE_LIEF , F.ANTEIL_ABLADEMENGE_LIEF), ANTEIL_PLANMENGE_LIEF),0)  <> 0 " +
				" AND   (ID_ADRESSE_START<> " + sIDAdresseMandant + " ) " +
				sWhereAdresse ;
			
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRE_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_VERBINDLICHKEIT_GEPLANT );
			
		}

		//  GEPLANTE
		//  FUHREN-ORT VERBINDLICHKEITEN, 
		// 
		//  SPALTEN: ID_ADRESSE, ID_ARTIKEL, MENGE, EINZELPREIS
		//
		if (spIDAdresse != null){
			sWhereAdresse = " AND O.ID_ADRESSE = " + spIDAdresse + " ";
		} else {
			sWhereAdresse = "";
		}
		
		if (bVerbindlichkeiten){
			
			sSelect_FUHRENORT_VERB = "" +
					" SELECT  O.ID_ADRESSE," +
					" O.ID_ARTIKEL, " +
					" ( -1 * NVL(NVL(NVL(O.ANTEIL_LADEMENGE,O.ANTEIL_ABLADEMENGE),O.ANTEIL_PLANMENGE),0) / A.MENGENDIVISOR ) AS MENGE,   " +
					" ( NVL(O.EINZELPREIS, NVL(K.EINZELPREIS,0)) ) AS PREIS" +
					" , O.ID_VPOS_TPA_FUHRE " +
					" FROM  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE_ORT O " +
					" INNER JOIN  " + bibE2.cTO() + ".JT_VPOS_TPA_FUHRE  F " +
					" ON ( O.ID_VPOS_TPA_FUHRE = F.ID_VPOS_TPA_FUHRE ) " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VPOS_KON K " +
					" ON (O.ID_VPOS_KON=K.ID_VPOS_KON) " +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL A ON A.ID_ARTIKEL = O.ID_ARTIKEL " +
					" WHERE (F.STATUS_BUCHUNG>=1) AND     " +
					" (    F.BUCHUNGSNR_FUHRE IS NULL " +
					"     AND NVL( O.PRUEFUNG_MENGE,'N') != 'Y' " +
					"     AND O.DATUM_LADE_ABLADE IS NULL " +
					"     AND NVL(O.ANTEIL_LADEMENGE,0) = 0 " +
					"    ) " +
					" AND (ID_VPOS_TPA_FUHRE_ORT NOT IN ( " +
					" SELECT NVL(R.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0) " +  
					" FROM  " + bibE2.cTO() + ".JT_VPOS_RG R " +
					" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG RK " +
					" ON (RK.ID_VKOPF_RG=R.ID_VKOPF_RG) " +
					" WHERE  R.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
					"  AND  R.ID_VPOS_RG_STORNO_NACHFOLGER  IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_VORGAENGER IS NULL " +
					" AND  RK.ID_VKOPF_RG_STORNO_NACHFOLGER IS NULL " +
					" AND  NVL(R.DELETED,'N')='N' " +
					" AND  NVL(RK.DELETED,'N')='N' " +
					" )) " +
					" AND (NVL(F.DELETED,'N')='N') " +
					" AND (NVL(O.DELETED,'N') = 'N') " +
					" AND (NVL(F.IST_STORNIERT,'N')='N') " +
					" AND (NVL(F.OHNE_ABRECHNUNG,'N')='N') " +
					" AND F.ID_ADRESSE_FREMDAUFTRAG is null " + 
					" AND (NVL(O.OHNE_ABRECHNUNG,'N')='N') " +
					" AND O.DEF_QUELLE_ZIEL = 'EK' " +
					sWhereAdresse ;
			
			sArrDaten = new String[0][0];
			sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect_FUHRENORT_VERB,(String)null);
			fillDataInRow(sArrDaten, ENUM_Values.FUHRE_ORT_VERBINDLICHKEIT_GEPLANT );
		}
		

		// Fuhrendaten jetzt konsolidieren
		Iterator<STATKD_DataRowStatus> itr = m_htDataRows.values().iterator();
		while (itr.hasNext()){
			itr.next().calculateDaten();
		}
		
		
	}


	
	
	/**
	 * Füllen der Daten in das Datenobjekt
	 * @param sArrDaten
	 * @param value_type
	 */
	private void fillDataInRow(String[][] sArrDaten, ENUM_Values value_type) {
		String sIDAdresse;
		String sValue;
		BigDecimal bdSum;
		
		String 	   sIDArtikel;
		BigDecimal bdMenge;
		BigDecimal bdEPreis;
		String 	   sIDRow; // Fuhren-ID oder sonstige ID, relevant für die jeweilige ROW, abhängig von der Abfrage bzw. Information
		
		STATKD_DataRowFuhreEntry oEntry;
		STATKD_DataRowStatus row;
		
		if (sArrDaten != null){
			// sonst den Vektor füllen
			for (int i = 0; i < sArrDaten.length; i++){
				
				row = null;
				bdSum = null;
				sIDAdresse = null;
				oEntry = null;

				
				sIDAdresse = (String) sArrDaten[i][0];
				if (m_htDataRows.containsKey(sIDAdresse)){
					row = m_htDataRows.get(sIDAdresse);
				} else {
					row = new STATKD_DataRowStatus(sIDAdresse);
					m_htDataRows.put(sIDAdresse, row);
				}
				
				
				// bei Fuhren werden die Daten in die Vektoren geschrieben, um ggf. fehlende Preise zu ermitteln
				if (value_type.equals(ENUM_Values.FUHRE_FORDERUNG) ||
						value_type.equals(ENUM_Values.FUHRE_ORT_FORDERUNG) ||
						value_type.equals(ENUM_Values.FUHRE_FORDERUNG_GEPLANT) ||
						value_type.equals(ENUM_Values.FUHRE_ORT_FORDERUNG_GEPLANT) ||
						value_type.equals(ENUM_Values.FUHRE_VERBINDLICHKEIT) ||
						value_type.equals(ENUM_Values.FUHRE_VERBINDLICHKEIT_GEPLANT) ){
						
						sIDArtikel = sArrDaten[i][1];
						
						sValue = sArrDaten[i][2];
						bdMenge = (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);

						sValue = sArrDaten[i][3];
						bdEPreis = (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);
						
						sIDRow = sArrDaten[i][4];
						
						// hier gleich den Wert ermitteln, wenn der Preis 0 ist
						if (bdEPreis.equals(BigDecimal.ZERO)){
							int vorzeichen = 1;
							if (value_type.equals(ENUM_Values.FUHRE_FORDERUNG) || value_type.equals(ENUM_Values.FUHRE_FORDERUNG_GEPLANT)){
								vorzeichen = -1;
							}
							bdEPreis = this.getAvgPriceForArticle(sIDArtikel, vorzeichen, 10);
						}
						
						oEntry = new STATKD_DataRowFuhreEntry(sIDArtikel, bdMenge, bdEPreis,sIDRow);
						
				}
				// bei FIBU-Einträgen werden die NETTO-Werte über die RG-Positionen ermittelt
				else if (value_type.equals(ENUM_Values.FIBU_FORDERUNG) ||
						   value_type.equals(ENUM_Values.FIBU_VERBINDLICHKEIT)){
					// Feldbeschreibung des Statements
					// 0 ID_ADRESSE
					// 1 BUCHUNGSBLOCK_NR
					// 2 BUCHUNGSDATUM
					// 3 STEUERSATZ
					// 4 POS_PREIS_NETTO
					// 5 POS_PREIS_BRUTTO
					String sBuchungsblock = sArrDaten[i][1];
					sValue = sArrDaten[i][3];
					BigDecimal bdSteuersatz =  (sValue != null ? new BigDecimal(sValue) : null);
					sValue = sArrDaten[i][4];
					BigDecimal bdNetto =  (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);
					sValue = sArrDaten[i][5];
					BigDecimal bdBrutto =  (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);
					
					if (value_type.equals(ENUM_Values.FIBU_FORDERUNG)){
						row.add_FibuEntry_FORDERUNG(sBuchungsblock, bdSteuersatz, bdNetto, bdBrutto);
					} else {
						row.add_FibuEntry_VERBINDLICHKEIT(sBuchungsblock, bdSteuersatz, bdNetto, bdBrutto);
					}
					
				} else {
					sValue = sArrDaten[i][1];
					bdSum = (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);
				}
				
				
				switch (value_type) {
				case FIBU_FORDERUNG:
//					row.set_FIBU_FORDERUNG(bdSum);
					break;
				case FREIE_POSITIONEN_FORDERUNG:
					row.set_FREIEPOS_FORDERUNG(bdSum);
					break;
				case RECHNUNG_FORDERUNG:
					row.set_RECH_FORDERUNG( bdSum );
					break;
					
				case FUHRE_FORDERUNG:
				case FUHRE_ORT_FORDERUNG:
					row.add_FuhreGefahrenForderung(oEntry);
					break;

				case FUHRE_FORDERUNG_GEPLANT:
				case FUHRE_ORT_FORDERUNG_GEPLANT:
					row.add_FuhreGeplantForderung(oEntry);
					break;
					
					
				case FIBU_VERBINDLICHKEIT:
//					row.set_FIBU_VERBINDLICHKEIT(bdSum);
					break;
				case FREIE_POSITIONEN_VERBINDLICHKEIT:
					row.set_FREIEPOS_VERBINDLICHKEIT(bdSum);
					break;
				case RECHNUNG_VERBINDLICHKEIT:
					row.set_RECH_VERBINDLICHKEIT( bdSum );
					break;					

				case FUHRE_VERBINDLICHKEIT:
				case FUHRE_ORT_VERBINDLICHKEIT:
					row.add_FuhreGefahrenVerbindlichkeit(oEntry);
					break;

				case FUHRE_VERBINDLICHKEIT_GEPLANT:
				case FUHRE_ORT_VERBINDLICHKEIT_GEPLANT:
					row.add_FuhreGeplantVerbindlichkeit(oEntry);
					break;
				
				default:
					break;
				}
			}
		}
	}
	
		
	/**
	 * Schreiben der Daten in die DB	
	 * @throws myException 
	 */
	private void writeDataToDB(boolean bOverrideOldEntries) throws myException{
		// prüfen, ob in dem Tag schon einträge gemacht sind. Wenn ja, dann müssen diese Überschrieben werden,
		// oder der Vorgang abgebrochen.
		// ES wird das aktuelle Datum genommen
		String sDate = bibALL.get_cDateNOW();
		
		RECLIST_STATUS_KUNDE rlStatus = new RECLIST_STATUS_KUNDE("SELECT * from  " + bibE2.cTO() + ".JT_STATUS_KUNDE WHERE BUCHUNGSDATUM = to_date( '" + sDate +"','dd.mm.yyyy')" );
	
		
		if (rlStatus.size() > 0 && ! bOverrideOldEntries){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es sind schon Einträge für diesen Tag vorhanden. Bitte setzten Sie die Option 'Einträge überschreiben'")));
			return;
		} 
		
		// sonst weiter im Programm...
		if (bOverrideOldEntries){
			cleanStatusRecords(sDate);
		}
		
		String sSql = "";
		
		// jetzt die neuen Sätze schreiben
		for (STATKD_DataRowStatus row : m_htDataRows.values()){
			

			
			MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
			
			BigDecimal dSaldo = BigDecimal.ZERO;
			
			oSql.addSQL_Paar("ID_STATUS_KUNDE", "SEQ_STATUS_KUNDE.NEXTVAL", false);
			oSql.addSQL_Paar("ID_ADRESSE", row.get_AdressID() , false);
			oSql.addSQL_Paar("BUCHUNGSDATUM", "to_date( '" + sDate +"','dd.mm.yyyy')" , false);
			oSql.addSQL_Paar("SUM_FIBU_FORDERUNG",row.get_FIBU_FORDERUNG().toPlainString() , false);
			oSql.addSQL_Paar("SUM_RECHNUNG_FORDERUNG", row.get_RECH_FORDERUNG().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FREIE_POS_FORDERUNG", row.get_FREIEPOS_FORDERUNG().toPlainString(), false);
			oSql.addSQL_Paar("SUM_FUHREN_FORDERUNG", row.get_FUHRE_FORDERUNG().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FUHREN_FORDERUNG_GEPLANT", row.get_FUHRE_FORDERUNG_GEPLANT().toPlainString(),false);
			
			oSql.addSQL_Paar("SUM_GESAMT_FORDERUNG", row.get_Gesamt_FORDERUNG().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FIBU_VERB",row.get_FIBU_VERBINDLICHKEIT().toPlainString() , false);
			oSql.addSQL_Paar("SUM_RECHNUNG_VERB", row.get_RECH_VERBINDLICHKEIT().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FREIE_POS_VERB", row.get_FREIEPOS_VERBINDLICHKEIT().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FUHREN_VERB",row.get_FUHRE_VERBINDLICHKEIT().toPlainString() , false);
			oSql.addSQL_Paar("SUM_FUHREN_VERB_GEPLANT",row.get_FUHRE_VERBINDLICHKEIT_GEPLANT().toPlainString() , false);
			
			oSql.addSQL_Paar("SUM_GESAMT_VERB", row.get_Gesamt_VERBINDLICHKEIT().toPlainString() , false);
			oSql.addSQL_Paar("SUM_GESAMT", row.get_Gesamt().toPlainString() , false);

			
			oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		    
		    sSql = oSql.get_CompleteInsertString("JT_STATUS_KUNDE", bibE2.cTO());

		    m_vSqlStatements.add( sSql);
		}
		
		this.executeSqlStatements(true);
	}
		
	
	
	
	
		
	/**
	 * Löscht die noch nicht exportierten Intrastat-Einträge aus der DB
	 * @param year
	 * @param month
	 */
	public boolean cleanStatusRecords(String sDate){
		boolean bRet = false;
		
		String sSql = "DELETE FROM " + bibE2.cTO()+".JT_STATUS_KUNDE" +
		" WHERE BUCHUNGSDATUM  = to_date( '" + sDate +"','dd.mm.yyyy')" +
		" AND ID_MANDANT = " + bibALL.get_ID_MANDANT();
		
		m_vSqlStatements.add(sSql);
		
		executeSqlStatements(true);
		
		return bRet;
	}
	
	
	
	/**
	 * Ermitteln des Durchschnittspreises auf Rechnungen / Gutschriften  für die letzten n Belege 
	 * @param IDArtikel
	 * @param LagerVorzeichen
	 * @param AnzahlLetzerRechnungen
	 * @return
	 */
	private BigDecimal getAvgPriceForArticle(String IDArtikel,int LagerVorzeichen, int AnzahlLetzerRechnungen){
		BigDecimal bdRet = null;
		// schauen,ob man den Artikel schon im Cache hat
		if (LagerVorzeichen > 0){
			if (m_htPreiseEinkauf.containsKey(IDArtikel)){
				bdRet = m_htPreiseEinkauf.get(IDArtikel);
			}
		} else {
			if (m_htPreiseVerkauf.containsKey(IDArtikel)){
				bdRet = m_htPreiseVerkauf.get(IDArtikel);
			}
		}
		if (bdRet != null) {
			return bdRet;
		}
		
		
		// sonst den Preis suchen
		String sAnzahlLetzerRechnungen = Integer.toString(AnzahlLetzerRechnungen);
		String sLagerVZ = Integer.toString(LagerVorzeichen);
		
		
		String sSelect = "SELECT  max(ID_MANDANT),  max(ID_ARTIKEL), "	+ 
//				" round(avg(EINZELPREIS_FW),4) " +
				" round(avg(EPREIS_RESULT_NETTO_MGE),4) " + 
				" FROM   ( " +
				"    SELECT  *  FROM  " + bibE2.cTO()+".JT_VPOS_RG " +
				"    WHERE  JT_VPOS_RG.LAGER_VORZEICHEN =  " + sLagerVZ + 
				"        and JT_VPOS_RG.ID_ARTIKEL   = " + IDArtikel +
				"    ORDER BY JT_VPOS_RG.ID_VPOS_RG desc " +
				"    ) " +
				" WHERE rownum <= " + sAnzahlLetzerRechnungen;
		
		String [][] sArrDaten = new String[0][0];
		sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect,(String)null);

		if (sArrDaten != null && sArrDaten.length == 1){
			
			String sValue = sArrDaten[0][2];
			bdRet = (sValue != null ? new BigDecimal(sValue) : BigDecimal.ZERO);
			if (LagerVorzeichen > 0){
				m_htPreiseEinkauf.put(IDArtikel, bdRet);
			} else {
				m_htPreiseVerkauf.put(IDArtikel, bdRet);
			}
		}
		
		return bdRet;
	}
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht
	 * @author manfred
	 * @date 08.04.2009
	 * @param UseOwnTransaction
	 * @return
	 */
	private boolean executeSqlStatements(boolean UseOwnTransaction){
		boolean bRet = true;
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSqlStatements, UseOwnTransaction);
		bRet &= mv.get_bIsOK();
		
		if ( UseOwnTransaction){
			if (bRet){
				bibDB.Commit();
			} else {
				bibDB.Rollback();
			}
		}
		
		m_vSqlStatements.clear();
		
		return bRet;
	}


	
	
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();
	
	@Override
	public boolean runTask() {
		boolean bRet = false;
		// prüfen, ob alle Parameter vorhanden sind:
		// Bei dieser Methode gibt es gar keine Parameter, die gesetzt sein müssen, nur welche die gesetzt sein können.
		
		try {
			this.ErmittleKundenStatus(true);
			bRet = true;
		} catch (myException e) {
			m_TaskMessages.add(e.ErrorMessage);
		}
		
		return bRet;
	}

	

	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters = hmParameters;
	}


	@Override
	public Vector<String> getTaskMessages() {
//		// test: rückgabe der übergebenen Parameter
//		for ( Map.Entry<String, String> e : m_hmParameters.entrySet() ){
//			m_TaskMessages.add( e.getKey() + "=" + e.getValue() + ";") ;
//		}
		return m_TaskMessages;
		
	}
	
}
