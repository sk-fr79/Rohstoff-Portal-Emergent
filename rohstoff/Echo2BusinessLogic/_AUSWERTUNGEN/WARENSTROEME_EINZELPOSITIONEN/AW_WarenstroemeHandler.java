package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME_EINZELPOSITIONEN;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FUHREN_RECHNUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FUHREN_RECHNUNGEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.bibROHSTOFF;


/**
 * Klasse zum korrigieren von Fuhren mit Fuhrenorten für die Auswertung Warenstroeme-Einzelpositionen
 * Spezialversion MVG
 * @author manfred
 * @date   29.02.2012
 */
public class AW_WarenstroemeHandler {

	// Reclist für alle Fuhren mit Fuhrenorten
	RECLIST_FUHREN_RECHNUNGEN  reclist_fuhrenrechnungen = null;
	RECORD_FUHREN_RECHNUNGEN rec = null;
	
	/* eine Hashmap für alle zusammengehörenden Fuhren.
	 * Innerhalb der Map werden die zusammengehörenden Fuhren-records in einem Vector abgelegt.
	 */
	java.util.HashMap<String, Vector<RECORD_FUHREN_RECHNUNGEN>> m_mapFuhren = null;
	
	// vector mit eigenen Lägern
	Vector<String> m_vEigeneLager = null;
	
	public AW_WarenstroemeHandler()  {
		
		try {
			m_vEigeneLager = new Vector<String>();
			m_vEigeneLager.addAll(bibROHSTOFF.get_vEigeneLieferadressen());
		} catch (myException e) {
			// keine eigenen???
		}
		
		m_mapFuhren = new HashMap<String, Vector<RECORD_FUHREN_RECHNUNGEN>>();
	}
	
	
	/**
	 * Hauptroutine korrigiert die Einträge aller
	 * Fuhren-positionen auf der temporären Tabelle JT_FUHREN_RECHNUNGEN
	 * für die Fuhren, die Lagerfuhren sind und nur einseitige Fuhrenorte haben.  
	 * @author manfred
	 * @throws myException 
	 * @date   29.02.2012
	 */
	public void korrigiereEintraege() throws myException{
		
		// alle relevanten Fuhreneintraege ermitteln
		String sWhere = 

				" ID_VPOS_TPA_FUHRE in ( " +
				"							select distinct F.ID_VPOS_TPA_FUHRE from " + bibE2.cTO() + ".JT_FUHREN_RECHNUNGEN F where F.ID_VPOS_TPA_FUHRE_ORT is not null" +
				"						)" ;
		
		reclist_fuhrenrechnungen = new RECLIST_FUHREN_RECHNUNGEN(sWhere, "");

		
		// aufteilen der Records in eigene Fuhren (quasi die Gruppierung per Programm)
		for (RECORD_FUHREN_RECHNUNGEN rec : reclist_fuhrenrechnungen.values()){
			String sID = rec.get_ID_VPOS_TPA_FUHRE_cUF();
			
			if(m_mapFuhren.containsKey(sID)){
				Vector<RECORD_FUHREN_RECHNUNGEN> v = m_mapFuhren.get(sID);
				v.add(rec);
			} else {
				Vector<RECORD_FUHREN_RECHNUNGEN> v = new Vector<RECORD_FUHREN_RECHNUNGEN>();
				v.add(rec);
				m_mapFuhren.put(sID, v);
			}
		}
		
		
		// jetzt für jede Fuhre die Verarbeitung durchführen
		for (Vector<RECORD_FUHREN_RECHNUNGEN> v : m_mapFuhren.values()){
			korrigiereFuhrenEintraege(v);
		}
		
		
		
	}
	
	
	
	
	
	
	private void korrigiereFuhrenEintraege (Vector<RECORD_FUHREN_RECHNUNGEN> v) throws myException{
		int nRet = -1;
		
		// lagereingang == 1 , lagerausgang == 2, strecke == 0, lager_lager == 4
		int fuhre_lager_eingang_ausgang = -1;
		
		int fuhre = 0;
		
		int fuhre_lager_links = 0;
		int fuhre_lager_rechts = 0;
		int fuhre_kunde_links = 0;
		int fuhre_kunde_rechts = 0;
		
		int lager_links = 0;
		int lager_rechts = 0;
		int kunde_links = 0;
		int kunde_rechts = 0;
		
		String sID_Fuhre = "?";
		
		// linke und rechte bestandteile der Fuhre ermitteln und zählen
		for (RECORD_FUHREN_RECHNUNGEN rec : v){
			
			sID_Fuhre = rec.get_ID_VPOS_TPA_FUHRE_cUF_NN("--");
			
			// hauptfuhre
			if (rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF() == null){

				BigDecimal bdSonderlager = rec.get_ID_SONDERLAGER_bdValue(null); 
				fuhre++;

				if ( m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF()) ) {
					fuhre_lager_links=1;
					lager_links++;
				} else if (rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0 ){
					fuhre_kunde_links=1;
					if (rec.get_ID_LAGER_cUF() != null){
						fuhre_lager_rechts = 1;
						lager_rechts ++;
					}
					kunde_links++;
				} 

				
				if ( m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF()) ) {
					fuhre_lager_rechts=1;
					lager_rechts++;
				} else if (rec.get_ID_ADRESSE_ZIEL_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0 ){
					fuhre_kunde_rechts = 1;
					if (rec.get_ID_LAGER_cUF() != null){
						fuhre_lager_links = 1;
						lager_links++;
					}
					
					kunde_rechts++;
				} 

			} else {
				// fuhrenorte
				if ( m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF()) ) {
					lager_links++;
				} else if (rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0 ){
					kunde_links++;
				} 

				
				if ( m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF()) ) {
					lager_rechts++;
				} else if (rec.get_ID_ADRESSE_ZIEL_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) > 0 ){
					kunde_rechts++;
				} 
				
			}
		}
		
		
		String sIDFuhre = null;
		String sIDFuhreOrt = null;
		
		Vector<String> vSql = new Vector<String>();
		Vector<String> vFieldsNotInUpdate = new Vector<String>();
		vFieldsNotInUpdate.add("");
		
		//Hauptfuhre und Preise
		RECORD_FUHREN_RECHNUNGEN oRecHauptfuhre = null;
		BigDecimal bdEinzelpreis 			= new BigDecimal(0);
		BigDecimal bdGesamtmenge 			= new BigDecimal(1);
		BigDecimal bdGesamtpreis 			= new BigDecimal(0);
		
		
		// finden der Hauptfuhre und der Preisumrechnungen
		for (RECORD_FUHREN_RECHNUNGEN rec : v){
			
			sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
			sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
			
			if (sIDFuhreOrt == null){
				
				oRecHauptfuhre = rec;
				
				bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
				bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
				if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
					bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
				}
				
				break;
			}
		}
		
		// fuhrenort wieder löschen
		sIDFuhreOrt = null;
		
		
		
		/************************************************************
		 * FALL 1  
		 * K -> L
		 * K ->
		 * K ->
		 * L -> * erfordert erweiterung 
		 */
		if (    fuhre_kunde_links == 1 &&	fuhre_lager_rechts == 1 &&
				kunde_links  >= 1 && 
				lager_links >= 0 &&
				kunde_rechts == 0 &&
				lager_rechts == 1  
				)
		{

			BigDecimal bdKorrekturmengeFuhre = new BigDecimal(0);
			BigDecimal bdKorrekturmengeKunde = new BigDecimal(0);
			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){

				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();

				if (sIDFuhreOrt != null){
				
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF());
					if(bOrtIstLager){
						// Der Fuhrenort bildet einen Warenausgang aus dem im Fuhrenort angegebenen Lager ab,
						// d.h. man muss die Wareneingans-Seite des Abladeorts nachbilden.
						
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, rec.get_ID_LAGER_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, rec.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, rec.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, rec.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate().toPlainString(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER, rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).negate().toPlainString(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));						

						// KORREKTURMENGE KUNDENSEITE
						bdKorrekturmengeKunde = bdKorrekturmengeKunde.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));

						
						// FUHRENORT KORRIGIEREN
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						
					} else {
						// Fuhrenort links ist ein Kunde
						rec.set_NEW_VALUE_MENGE_LAGER(rec.get_MENGE_NACH_ABZUG_cF());
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				// Lagerseitige Menge korrigieren
				oRecHauptfuhre.set_NEW_VALUE_MENGE_LAGER(bibALL.convertBigDecimalToString(oRecHauptfuhre.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre) , 3));
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");

				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}
		}

		
		/**
		 * FALL 2
		 *  K -> L
		 *    -> K
		 *    -> K
		 *    -> L
		 *  - Umgekehrte UMLEITUNGSFUHRE - 
		 *  
		 *  für die K<->K-Verbindungen müssen virtuelle Sätze erzeugt werden!!
		 *  
		 */  
		else if (fuhre_kunde_links == 1 && 
				 fuhre_lager_rechts == 1 && 
				 kunde_links == 1 && 
				 lager_links == 0 &&  
				 kunde_rechts >= 0  && 
				 lager_rechts >= 1 )
		{
			/**
			 *  
			 *  
			 *  
			 */
			
			BigDecimal bdKorrekturmengeFuhre = new BigDecimal(0);
			BigDecimal bdKorrekturmengeFuhreLager = new BigDecimal(0);

			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt == null){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
					
					break;
				}
			}

			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt != null){

					// Fuhrenort
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF());
					
					// wenn Fuhrenort ein Kunde ist
					if(!bOrtIstLager){
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, "null", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, "null", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, oRecHauptfuhre.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, oRecHauptfuhre.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, "0", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, "0", false);
						
						// artikel aus der Haupfuhre
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ARTIKEL, oRecHauptfuhre.get_ID_ARTIKEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ANR1, oRecHauptfuhre.get_ANR1_cUF(), true);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_LAGER_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_R_LAGER_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						// Einkaufspreis des Fuhrenortes ist der Preis der  Hauptfuhre 
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_EINZEL_RESULT, oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_bdValue(BigDecimal.ZERO).toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_NACH_ABZUG, rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED).toPlainString(), false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
					} else {
						// ort ist Lager
						rec.set_NEW_VALUE_MENGE_NACH_ABZUG(rec.get_MENGE_LAGER_cF());
						rec.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
						rec.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				// Lagermenge Korrigieren 
				BigDecimal bdNeueGesamtMenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre);
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(bdNeueGesamtMenge, 3) );
				oRecHauptfuhre.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( bdNeueGesamtMenge.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );

				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}

		}

		
		/**
		 * FALL 3
		 *  L -> K
		 *    -> L
		 *    -> L
		 *    -> K
		 *  - UMLEITUNGSFUHRE - 
		 *  
		 *  für die L<->L-Verbindungen müssen virtuelle Sätze erzeugt werden!!
		 *  
		 */  
		else if ( fuhre_lager_links == 1 && fuhre_kunde_rechts == 1 && 
				kunde_links == 0 
				&& 
				lager_links == 1 
				&&  
				kunde_rechts  >= 1  
				&& 
				lager_rechts >= 0 
				)
		{
			/**
			 *  lager-links == Hauptfuhre:
			 *  Korrektur: Kundenfuhren-Orte die Lagermengen korrigieren
			 *  Hauptfuhre: Lagermenge korrigieren: Lagemenge = Lagermenge - Sum(Fuhrenorte-Mengen)
			 */
			
			BigDecimal bdKorrekturmengeFuhre = new BigDecimal(0);
			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt == null){
					oRecHauptfuhre = rec;
				} else {
					
					// Fuhrenort
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF());
					
					if(bOrtIstLager){
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_MENGE_LAGER(rec.get_MENGE_NACH_ABZUG_cF());
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate());
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						// ist Warenausgang aus dem Lager raus
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, rec.get_ID_LAGER_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, rec.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, rec.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, rec.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate().toPlainString(), false);
						
						// negative menge
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER, rec.get_MENGE_LAGER_bdValue(BigDecimal.ONE).abs().negate().toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER_ORI, rec.get_MENGE_LAGER_ORI_bdValue(BigDecimal.ONE).abs().negate().toPlainString(), false);

						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
					} else {
						// ort ist Kunde
						rec.set_NEW_VALUE_MENGE_LAGER(bibALL.convertBigDecimalToString(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate(),3) );
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate());
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
					
				}
			}
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				// Lagermenge Korrigieren Lagermenge ist in diesem Fall negativ zur Menge
				oRecHauptfuhre.set_NEW_VALUE_MENGE_LAGER(
						bibALL.convertBigDecimalToString(
								oRecHauptfuhre.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre)
						,3)	);
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}

		}

		/**
		 * FALL 4  
		 * L -> K
		 * L ->
		 * L ->
		 * K ->
		 */
		else if ( fuhre_lager_links == 1 && 
				fuhre_kunde_rechts == 1 &&  
				kunde_links >= 0 		&& 
				lager_links  >= 1  		&&  
				kunde_rechts == 1 		&& 
				lager_rechts == 0 
				)
		{
			/**
			 *  kunde-rechts == Hauptfuhre:
			 *  Korrektur: 
			 *  	Kundenfuhren-Orte:
			 *  		Menge-nach-Abzug = Menge-Lager
			 *  		Preis-Einzel = Preis-Einzel Hauptfuhre
			 *  		Preis-Gesamt = ??
			 *  		 
			 *  	Hauptfuhre: 
			 *  		Menge-nach-Abzug = Menge-nach-Abzug - Sum(Orte Menge-Lager)
			 *  		Korrektur Gesamtpreis ??
			 */
			
			BigDecimal bdKorrekturmengeFuhre 	= new BigDecimal(0);
			BigDecimal bdNeueMengeFuhre 		= new BigDecimal(0);
			
			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt == null){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
					
					break;
				}
			}
			
			
			/** 
			 * Fuhrenorte ermitteln und korrigieren
			 */
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				// Fuhrenort
				if (sIDFuhreOrt != null){
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF());
					
					if (bOrtIstLager){
						// korrigiere Kundenmenge
						rec.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).negate(),3));
						rec.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
						rec.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( 
								rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis).multiply(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate())
								,2) );
						
						bdKorrekturmengeFuhre =  bdKorrekturmengeFuhre.add(
								rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate()));
						
					} else {

						// Strecken-ergänzung für die K-K-Fuhre
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, "null", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, "null", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, "0", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, "0", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, oRecHauptfuhre.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, oRecHauptfuhre.get_ID_ADRESSE_STANDORT_ZIEL_cUF(), false);
						
						// artikel aus der Haupfuhre
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ARTIKEL, oRecHauptfuhre.get_ID_ARTIKEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ANR1, oRecHauptfuhre.get_ANR1_cUF(), true);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_LAGER_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_R_LAGER_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						// Einkaufspreis des Fuhrenortes ist der Preis der  Hauptfuhre 
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_EINZEL_RESULT, oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_bdValue(BigDecimal.ZERO).toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_NACH_ABZUG, rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED).toPlainString(), false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
						
					}
					
					rec.set_NEW_VALUE_KORREKTUR("1");
					
					vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				BigDecimal bdNeueGesamtMenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre);
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(bdNeueGesamtMenge, 3) );
				oRecHauptfuhre.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( bdNeueGesamtMenge.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
				
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}
			
		}

		
		
		
		/**
		 * FALL 5
		 *  K -> K
		 *    -> L
		 *    -> K

		 *  
		 *  
		 *  für die K<->K-Verbindungen müssen virtuelle Sätze erzeugt werden!!
		 *  in den Kunden-Lager-Fuhre muss des berechnete Gewicht korrigiert werden.
		 *  
		 */  
		else if (fuhre_kunde_links == 1 && 
				 fuhre_kunde_rechts == 1 && 
				 kunde_links == 1 && 
				 lager_links == 0 &&  
				 kunde_rechts >= 1  && 
				 lager_rechts >= 0 )
		{
			/**
			 *  
			 *  
			 *  
			 */
			
			BigDecimal bdKorrekturmengeFuhre = new BigDecimal(0);
			BigDecimal bdKorrekturmengeFuhreLager = new BigDecimal(0);
			BigDecimal sonderlager_start 		=      null;
			
			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				// der Preis ist der linke Preis, der gilt beim ergänzen der Strecken-Fuhre
				sonderlager_start = rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO);
				
				if ( sIDFuhreOrt == null && 
					sonderlager_start.compareTo(BigDecimal.ONE) > 0 ){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
					
					break;
				}
			}


			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt != null){

					// Fuhrenort
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF());
					
					// wenn Fuhrenort ein Kunde ist
					if(!bOrtIstLager){
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, "null", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, "null", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, oRecHauptfuhre.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, oRecHauptfuhre.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, "0", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, "0", false);
						
						// artikel aus der Haupfuhre
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ARTIKEL, oRecHauptfuhre.get_ID_ARTIKEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ANR1, oRecHauptfuhre.get_ANR1_cUF(), true);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_LAGER_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_R_LAGER_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						// Einkaufspreis des Fuhrenortes ist der Preis der  Hauptfuhre 
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_EINZEL_RESULT, oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_bdValue(BigDecimal.ZERO).toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_NACH_ABZUG, rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED).toPlainString(), false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
					} else {
						// ort ist Lager
						rec.set_NEW_VALUE_MENGE_NACH_ABZUG(rec.get_MENGE_LAGER_cF());
						rec.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
						rec.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				// Lagermenge Korrigieren 
				BigDecimal bdNeueGesamtMenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre);
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(bdNeueGesamtMenge, 3) );
				oRecHauptfuhre.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( bdNeueGesamtMenge.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );

				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}

		}
		
		
		
		/**
		 * FALL 6  
		 * K -> K
		 * L ->
		 * L ->
		 * K ->
		 */
		else if ( fuhre_kunde_links == 1 && 
				fuhre_kunde_rechts == 1 &&  
				kunde_links >= 1 		&& 
				lager_links  >= 0  		&&  
				kunde_rechts == 1 		&& 
				lager_rechts == 0 
				)
		{
			/**
			 *  kunde-rechts == Hauptfuhre:
			 *  Korrektur: 
			 *  	Kundenfuhren-Orte:
			 *  		Menge-nach-Abzug = Menge-Lager
			 *  		Preis-Einzel = Preis-Einzel Hauptfuhre
			 *  		Preis-Gesamt = ??
			 *  		 
			 *  	Hauptfuhre: 
			 *  		Menge-nach-Abzug = Menge-nach-Abzug - Sum(Orte Menge-Lager)
			 *  		Korrektur Gesamtpreis ??
			 */
			
			BigDecimal bdKorrekturmengeFuhre 	= new BigDecimal(0);
			BigDecimal bdNeueMengeFuhre 		= new BigDecimal(0);
			BigDecimal sonderlager_start 		=      null;
			
			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){

				// der Preis ist der rechte Preis, der gilt beim ergänzen der Strecken-Fuhre
				sonderlager_start = rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO);
				
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt == null && 
						sonderlager_start.compareTo(BigDecimal.ONE) < 0 ){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
					
					break;
				}
			}
			
			
			/** 
			 * Fuhrenorte ermitteln und korrigieren
			 */
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				// Fuhrenort
				if (sIDFuhreOrt != null){
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF());
					
					if (bOrtIstLager){
						// korrigiere Kundenmenge
						rec.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).negate(),3));
						rec.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
						rec.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( 
								rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis).multiply(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate())
								,2) );
						
						bdKorrekturmengeFuhre =  bdKorrekturmengeFuhre.add(
								rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).multiply(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate()));
						
					} else {

						// Strecken-ergänzung für die K-K-Fuhre
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, "null", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, "null", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, "0", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, "0", false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, oRecHauptfuhre.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, oRecHauptfuhre.get_ID_ADRESSE_STANDORT_ZIEL_cUF(), false);
						
						// artikel aus der Haupfuhre
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ARTIKEL, oRecHauptfuhre.get_ID_ARTIKEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ANR1, oRecHauptfuhre.get_ANR1_cUF(), true);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_LAGER_VORZEICHEN, bibALL.convertBigDecimalToString(rec.get_R_LAGER_VORZEICHEN_bdValue(BigDecimal.ONE).negate(),0 ), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						// Einkaufspreis des Fuhrenortes ist der Preis der  Hauptfuhre 
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_EINZEL_RESULT, oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_bdValue(BigDecimal.ZERO).toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_NACH_ABZUG, rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).multiply(bdEinzelpreis,MathContext.UNLIMITED).toPlainString(), false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
						
					}
					
					rec.set_NEW_VALUE_KORREKTUR("1");
					
					vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				BigDecimal bdNeueGesamtMenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre);
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(bdNeueGesamtMenge, 3) );
				oRecHauptfuhre.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( bdNeueGesamtMenge.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
				
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}
			
		}




		/************************************************************
		 * FALL 7  
		 * L -> L
		 * K ->
		 * L -> * erfordert erweiterung 
		 */
		else if (
				fuhre_lager_links == 1 &&	fuhre_lager_rechts == 1 &&
				kunde_links  >= 0 && 
				lager_links  >= 1 &&
				kunde_rechts == 0 &&
				lager_rechts == 1  
				)
		{

			BigDecimal bdKorrekturmengeFuhre = new BigDecimal(0);
			BigDecimal bdKorrekturmengeKunde = new BigDecimal(0);
			BigDecimal sonderlager_start 		=      null;
			
			
			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){

				// der Preis ist der linke Preis, der gilt beim ergänzen der Strecken-Fuhre
				sonderlager_start = rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO);
				
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt == null && 
						sonderlager_start.compareTo(BigDecimal.ONE) <= 0 ){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
					
					break;
				}
			}
			
			
			
			
			
			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt != null){

					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_START_cUF());
					if(bOrtIstLager){
						// Der Fuhrenort bildet einen Warenausgang aus dem im Fuhrenort angegebenen Lager ab,
						// d.h. man muss die Wareneingans-Seite des Abladeorts nachbilden.
						
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, rec.get_ID_LAGER_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, rec.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, rec.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, rec.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate().toPlainString(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER, rec.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).negate().toPlainString(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));						

						// KORREKTURMENGE KUNDENSEITE
						bdKorrekturmengeKunde = bdKorrekturmengeKunde.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						
						// FUHRENORT KORRIGIEREN
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
					} else {
						// Fuhrenort links ist ein Kunde
						rec.set_NEW_VALUE_MENGE_LAGER(rec.get_MENGE_NACH_ABZUG_cF());
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO));
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
				}
			}
			
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				
				// Kundenseitige Menge korrigieren
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre) , 3));
				
				// Lagerseitige Menge korrigieren
				oRecHauptfuhre.set_NEW_VALUE_MENGE_LAGER(bibALL.convertBigDecimalToString(oRecHauptfuhre.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre) , 3));
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");

				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
			}
		}

		
		
		
		/**
		 * FALL 8
		 *  L -> L
		 *    -> K
		 *    -> L
		 *  
		 *  für die L<->L-Verbindungen müssen virtuelle Sätze erzeugt werden!!
		 *  
		 */  
		else if ( fuhre_lager_links == 1 && 
				  fuhre_lager_rechts == 1 && 
				  kunde_links == 0 	&& 
				  lager_links == 1 	&&  
				  kunde_rechts  >= 0  && 
				  lager_rechts >= 1 
				)
		{
			/**
			 *  lager-links == Hauptfuhre:
			 *  Korrektur: Kundenfuhren-Orte die Lagermengen korrigieren
			 *  Hauptfuhre: Lagermenge korrigieren: Lagemenge = Lagermenge - Sum(Fuhrenorte-Mengen)
			 */
			BigDecimal bdKorrekturmengeFuhre  = new BigDecimal(0);
			BigDecimal bdKorrekturmengeKunde  = new BigDecimal(0);
			BigDecimal sonderlager_start 	  = null;
			
			RECORD_FUHREN_RECHNUNGEN oRecHauptfuhre_gegenseite = null;
			
			//finden der Hauptfuhre
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				// die zu korrigierende Hauptfuhre ist die linke Seite
				sonderlager_start = rec.get_ID_ADRESSE_START_bdValue(BigDecimal.ZERO);
				
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if ( sIDFuhreOrt == null && 
					 sonderlager_start.compareTo(BigDecimal.ONE) > 0 ){
					
					oRecHauptfuhre = rec;
					
					bdGesamtpreis = oRecHauptfuhre.get_R_PREIS_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					bdGesamtmenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO);
					if(bdGesamtmenge.compareTo(BigDecimal.ZERO) != 0 ){
						bdEinzelpreis = bdGesamtpreis.divide(bdGesamtmenge,5, BigDecimal.ROUND_HALF_UP);
					}
				} else if (		sIDFuhreOrt == null && 
					sonderlager_start.compareTo(BigDecimal.ONE) <= 0 ){
					oRecHauptfuhre_gegenseite = rec;
					
				}
				
			}


			
			
			
			for (RECORD_FUHREN_RECHNUNGEN rec : v){
				sIDFuhre 	= rec.get_ID_VPOS_TPA_FUHRE_cUF();
				sIDFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				
				if (sIDFuhreOrt != null){
					// Fuhrenort
					boolean bOrtIstLager = m_vEigeneLager.contains(rec.get_ID_ADRESSE_ZIEL_cUF());
					
					if(bOrtIstLager){
						// vorhanden: fuhrenort rechte seite, d.h. der Preis der linken seite ist gültig
						
						// alten Record clonen in einen Statementbuilder
						MySqlStatementBuilder stmt = rec.get_StatementBuilderFilledWithActualValues();
						
						// jetzt die Werte des Records ändern
						rec.set_NEW_VALUE_MENGE_LAGER(rec.get_MENGE_NACH_ABZUG_cF());
						rec.set_NEW_VALUE_KORREKTUR("1");

						rec.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
						rec.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( 
								rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).abs().multiply( bdEinzelpreis,MathContext.UNLIMITED ),2) );
						
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate());
						
						
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
						
						
						// Gegenbuchungs-Eintrag generieren!! Ladestelle Fuhrenort
						// ist Warenausgang aus dem Lager raus
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER_GEGENSEITE, rec.get_ID_LAGER_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_LAGER, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_START, rec.get_ID_ADRESSE_ZIEL_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_START, rec.get_ID_LAGER_GEGENSEITE_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_ZIEL, rec.get_ID_ADRESSE_START_cUF(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_ADRESSE_STANDORT_ZIEL, rec.get_ID_ADRESSE_STANDORT_START_cUF(), false);
						
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGEN_VORZEICHEN, rec.get_MENGEN_VORZEICHEN_bdValue(BigDecimal.ONE).negate().toPlainString(), false);
						
						// Einzelpreis und Gesamtpreis des neuen Fuhrenortes
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_EINZEL_RESULT, oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cUF());
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__R_PREIS_NACH_ABZUG, (rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).abs()
																									.multiply( bdEinzelpreis,MathContext.UNLIMITED)).toPlainString());
						// negative menge
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER, rec.get_MENGE_LAGER_bdValue(BigDecimal.ONE).abs().negate().toPlainString(), false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__MENGE_LAGER_ORI, rec.get_MENGE_LAGER_ORI_bdValue(BigDecimal.ONE).abs().negate().toPlainString(), false);

						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__ID_FUHREN_RECHNUNGEN, "seq_fuhren_rechnungen.nextval", false);
						stmt.addSQL_Paar(RECORD_FUHREN_RECHNUNGEN.FIELD__KORREKTUR, "2",false);
						
						
						
						vSql.add(stmt.get_CompleteInsertString("JT_FUHREN_RECHNUNGEN"));
					} else {
						// ort ist Kunde
						rec.set_NEW_VALUE_MENGE_LAGER(bibALL.convertBigDecimalToString(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate(),3) );
						rec.set_NEW_VALUE_KORREKTUR("1");
						bdKorrekturmengeFuhre = bdKorrekturmengeFuhre.add(rec.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).negate());
						vSql.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
					}
				}
			}
			// jetzt noch die Hauptfuhre korrigieren
			if (oRecHauptfuhre != null){
				// Lagermenge Korrigieren und 
				// berechnete Menge korrigieren, da es eine Lager-Lager-Fuhre ist (berechnete Menge ist als lager/lager-Fuhre negativ auf der Ladeseite)
				
				
				oRecHauptfuhre.set_NEW_VALUE_MENGE_LAGER(bibALL.convertBigDecimalToString(oRecHauptfuhre.get_MENGE_LAGER_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre),3)	);
				oRecHauptfuhre.set_NEW_VALUE_MENGE_NACH_ABZUG(bibALL.convertBigDecimalToString(oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre.negate()),3)	);
				
				// preis neu setzen
				BigDecimal bdNeueGesamtMenge = oRecHauptfuhre.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO).subtract(bdKorrekturmengeFuhre.negate());
				oRecHauptfuhre.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( bdNeueGesamtMenge.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
				
				oRecHauptfuhre.set_NEW_VALUE_KORREKTUR("1");
				vSql.add(oRecHauptfuhre.get_SQL_UPDATE_STATEMENT(null, true));
				
				
				
				// Gegenseite der Hautpfuhre: Preis korrigieren
				oRecHauptfuhre_gegenseite.set_NEW_VALUE_KORREKTUR("1");
				oRecHauptfuhre_gegenseite.set_NEW_VALUE_R_PREIS_EINZEL_RESULT(oRecHauptfuhre.get_R_PREIS_EINZEL_RESULT_cF());
				oRecHauptfuhre_gegenseite.set_NEW_VALUE_R_PREIS_NACH_ABZUG(bibALL.convertBigDecimalToString( oRecHauptfuhre_gegenseite.get_MENGE_NACH_ABZUG_bdValue(BigDecimal.ZERO)
														.multiply(bdEinzelpreis,MathContext.UNLIMITED),2) );
				
				vSql.add(oRecHauptfuhre_gegenseite.get_SQL_UPDATE_STATEMENT(null, true));
				
				
			}
		}
		
		
		
		/**
		 * irgendeinanderer Fall
		 */
		else {
			String sDebug = "Fuhre " + sID_Fuhre + ": " +
					" Links / Rechts :   " + Integer.toString(lager_links+kunde_links) +" / " + Integer.toString(lager_rechts+kunde_rechts)   + 
					"   Links : FL=" + Integer.toString(fuhre_lager_links) + " FK=" + Integer.toString(fuhre_kunde_links) + " L=" + Integer.toString(lager_links) + " K=" + Integer.toString(kunde_links) + 
					"   Rechts: FL=" + Integer.toString(fuhre_lager_rechts) +" FK=" + Integer.toString(fuhre_kunde_rechts) + " L=" + Integer.toString(lager_rechts) + " K=" + Integer.toString(kunde_rechts) 
					;
			
			DEBUG.System_println(sDebug, DEBUG.DEBUG_FLAG_DIVERS1);
			vSql.clear();
		}
		
		
		
		// jetzt gleich die Daten festschreiben
		if (vSql != null && vSql.size() > 0){
			if (  bibDB.ExecSQL(vSql, true))
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("OK:   " + sID_Fuhre));
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("ERR:   "+ sID_Fuhre));
			}
		}
		
		

	}
	
	
	
	
}
