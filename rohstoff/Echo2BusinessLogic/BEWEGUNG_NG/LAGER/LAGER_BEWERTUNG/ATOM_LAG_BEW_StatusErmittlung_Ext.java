package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_SaldoDaten;

public class ATOM_LAG_BEW_StatusErmittlung_Ext implements ICallableTask{

	private String m_IDAdresseLager = null;
	private Vector<String> m_vIDAdresseLager = null;
	private String m_IDHauptSorte = null;
	private Vector<String> m_vIDHauptSorte = null;
	private String m_IDSorte = null;
	private Vector<String> m_vIDSorte = null;
	
	private ENUM_ATOM_Statusermittlung_Typ m_Status  	= null;

	private String m_sqlTablenameAtomSetzkasten			= " JT_BEWEGUNG_SETZKASTEN ";
	private String m_sqlPrimaryKeyColAtomSetzkasten 	= " ID_BEWEGUNG_SETZKASTEN ";
	private String m_sqlColumPreis 						= " A.E_PREIS_RESULT_NETTO_MGE ";
	private String m_sqlColumnPreisOhneKosten			= " A.E_PREIS_RESULT_NETTO_MGE ";
	
	public enum   ENUM_ATOM_Statusermittlung_Typ       { einzelzeilen,statuszeilen,historie }; 
	
	/**
	 * Das Buchungsdatum nur verwenden, wenn man die Lagerbewegungsdaten historisch aufbaut.
	 * Funktioniert nur zusammen mit der Verbuchung auf einen Bestimmten Zeitpunkt hin...
	 */
	private String m_BuchungsdatumBis = null;
	
	
	
	private Vector<ATOM_LAG_BEW_DataRowStatus>  m_vRowStatus = null;
	private Vector<String> m_vSqlStatements ;
	
	private boolean m_bKostenberuecksichtigung = false;
	
	
	public ATOM_LAG_BEW_StatusErmittlung_Ext(boolean bKostenberuecksichtigung) {
		super();
		m_bKostenberuecksichtigung 	= bKostenberuecksichtigung;
		m_vRowStatus 				= new Vector<ATOM_LAG_BEW_DataRowStatus>();
		m_vSqlStatements 			= new Vector<String>();
		
		
			
	}

	
	

	/**
	 * Ermittelt den Lagerstatus für genau einen Artikel in einem Lager, ggf. zu einem Buchungsdatum
	 * und gibt die Liste der EINZELPOSITIONEN zurück, damit die Mengenermittlung 
	 * mit den zum Zeitpunkt gültigen ATOM_LAG_BEW_DataRowStatus arbeiten kann.
	 * 
	 * @param sIDAdresse
	 * @param sHauptSorte
	 * @param sIDSorte
	 * @param sBuchungsdatum
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 07.05.2014
	 *
	 */
	public Vector<ATOM_LAG_BEW_DataRowStatus> ErmittleLagerstatusFuerMengenermittlung(
			String sIDAdresse,
			String sHauptSorte,
			String sIDSorte, 
			String sBuchungsdatum){
		return ErmittleLagerstatusFuerMengenermittlung(sIDAdresse,null,sHauptSorte,null, sIDSorte,null, sBuchungsdatum);
	}
	
	
	/**
	 * Ermittelt den Lagerstatus für genau einen Artikel in einem Lager, ggf. zu einem Buchungsdatum
	 * und gibt die Liste der EINZELPOSITIONEN zurück, damit die Mengenermittlung 
	 * mit den zum Zeitpunkt gültigen ATOM_LAG_BEW_DataRowStatus arbeiten kann.
	 * 
	 * @param sIDAdresse
	 * @param vIDAdresse
	 * @param sHauptSorte
	 * @param vIDHauptSorte
	 * @param sIDSorte
	 * @param vIDSorte
	 * @param sBuchungsdatum
	 * @return
	 */
	public Vector<ATOM_LAG_BEW_DataRowStatus> ErmittleLagerstatusFuerMengenermittlung(
			String sIDAdresse,
			Vector<String> vIDAdresse,
			String sHauptSorte,
			Vector<String> vIDHauptSorte,
			String sIDSorte, 
			Vector<String> vIDSorte,
			String sBuchungsdatum){
		m_Status			= ENUM_ATOM_Statusermittlung_Typ.einzelzeilen;
		m_IDAdresseLager 	= sIDAdresse;
		m_IDHauptSorte 		= sHauptSorte;
		m_IDSorte 			= sIDSorte;
		m_BuchungsdatumBis 	= sBuchungsdatum;
		m_vIDAdresseLager	= vIDAdresse;
		m_vIDHauptSorte		= vIDHauptSorte;
		m_vIDSorte			= vIDSorte;

		// Lagerdaten für die Mengenermittlung 
		this.initLagerstatusDaten();
		// danach sind die Daten selektiert und in der m_vRowStatus abgelegt
		return m_vRowStatus;
	}
	
	/**
	 * gibt den Vektor mit den gefundenen Stautusinformationen zurück
	 * @return
	 */
	public Vector<ATOM_LAG_BEW_DataRowStatus> getResultList(){
		return m_vRowStatus;
	}
	
	
	
	
	/**
	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
	 * Die Daten werden nicht in die DB übernommen, sonder in einer ATOM_LAG_BEW_DataRowStatus zurückgegeben
	 * Es werden nur die positiven, nicht verbuchten WE-Positionen berücksichtigt
	 *  
	 * @param sIDAdresse
	 * @param sHauptSorte
	 * @param sIDSorte
	 * @param sDatum
	 * @return
	 * @throws myException
	 */
	public ATOM_LAG_BEW_DataRowStatus ErmittleLagerstatus(
			String sIDAdresse,
			String sHauptSorte,
			String sIDSorte, 
			String sDatum) throws myException{
		
		return ErmittleLagerstatus(sIDAdresse,null,sHauptSorte,null, sIDSorte,null, sDatum);
	}
	
	
	
	/**
	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
	 * Die Daten werden nicht in die DB übernommen, sonder in einer ATOM_LAG_BEW_DataRowStatus zurückgegeben
	 * Es werden nur die positiven, nicht verbuchten WE-Positionen berücksichtigt
	 * 
	 * @param sIDAdresse
	 * @param vIDAdresse
	 * @param sHauptSorte
	 * @param vIDHauptSorte
	 * @param sIDSorte
	 * @param vIDSorte
	 * @param sDatum
	 * @return
	 * @throws myException
	 */
	public ATOM_LAG_BEW_DataRowStatus ErmittleLagerstatus(
			String sIDAdresse,
			Vector<String> vIDAdresse,
			String sHauptSorte,
			Vector<String> vIDHauptSorte,
			String sIDSorte, 
			Vector<String> vIDSorte,
			String sDatum) throws myException{
		ATOM_LAG_BEW_DataRowStatus  oRet = null;
		m_Status         	= ENUM_ATOM_Statusermittlung_Typ.statuszeilen;
		m_IDAdresseLager 	= sIDAdresse;
		m_IDHauptSorte 		= sHauptSorte;
		m_IDSorte 			= sIDSorte;
		m_BuchungsdatumBis 	= sDatum;
		
		m_vIDAdresseLager	= vIDAdresse;
		m_vIDHauptSorte		= vIDHauptSorte;
		m_vIDSorte			= vIDSorte;
		
		// gruppierte ermittlung der Mengen
		this.initLagerstatusDaten();
		int rows =m_vRowStatus.size(); 
		if ( rows == 1 ){
			oRet = m_vRowStatus.firstElement();
		} else if (rows > 1){
			// falls es mehr als eine Zeile gibt (d.h. mehrere Firmen
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Es sind " + Integer.toString(rows) + " Statuszeilen zurückgegeben worden. Eine Zeile wird erwartet."));
		} 
		
		return oRet;
	}
	
	
	
	
//
//
//	/**
//	 * Ermitteln vom Lagerstatus tzn aktuellen Tag
//	 * die Daten werden in die DB-Tabelle geschrieben
//	 * @param overrideOldEntries
//	 * @throws myException
//	 */
//	public void ErmittleLagerstatus(boolean overrideOldEntries) throws myException{
//
//		// aktuelles Buchungsdatum generieren
//		String sBuchungsdatum = myDateHelper.ChangeNormalString2DBFormatString(bibALL.get_cDateNOW()) ;
//		
//		this.ErmittleLagerstatus(overrideOldEntries, sBuchungsdatum);
//	}
//	
//	
//	/**
//	 * Ermitteln des Lagerstatus zum vorgegebenen Tag
//	 * @param overrideOldEntries
//	 * @param sBuchungsdatum
//	 * @throws myException
//	 */
//	public void ErmittleLagerstatus(boolean overrideOldEntries, String sBuchungsdatum) throws myException{
//		m_IDAdresseLager = null;
//		m_IDHauptSorte = null;
//		m_IDSorte = null;
//
//		m_BuchungsdatumBis = sBuchungsdatum;
//		
//		readLagerstatus(m_bKostenberuecksichtigung);
//		
//		this.writeDataToDB(overrideOldEntries, sBuchungsdatum);
//		
//		bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Lagerstatus von " + sBuchungsdatum + " wurde ermittelt."));
//	}
//	
//	
//	/**
//	 * Ermittelt den Lagerstatus zum vorgegebenen Tag und schreibt den Wert in die Tabelle
//	 * @param sIDAdresse
//	 * @param sHauptSorte
//	 * @param sIDSorte
//	 * @param sBuchungsdatum  yyyy-MM-dd 
//	 * @param overrideOldEntries
//	 * @throws myException
//	 */
//	public void ErmittleLagerstatusBeiReorgLagerSorte(String sIDAdresse, String sHauptSorte, String sIDSorte, String sBuchungsdatum,boolean overrideOldEntries) throws myException{
//		m_IDAdresseLager = sIDAdresse;
//		m_IDHauptSorte = sHauptSorte;
//		m_IDSorte = sIDSorte;
//
//		m_BuchungsdatumBis = sBuchungsdatum;
//		
//		readLagerstatus(m_bKostenberuecksichtigung);
//
//		this.writeDataToDB(overrideOldEntries, sBuchungsdatum);
//	}
//	
//
//	/**
//	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
//	 * Die Daten werden nicht in die DB übernommen, sonder in einer ATOM_LAG_BEW_DataRowStatus zurückgegeben 
//	 * 
//	 * @param sIDAdresse
//	 * @param sHauptSorte
//	 * @param sIDSorte
//	 * @return
//	 * @throws myException
//	 */
//	public ATOM_LAG_BEW_DataRowStatus ErmittleLagerstatus(String sIDAdresse, String sHauptSorte, String sIDSorte) throws myException{
//		return ErmittleLagerstatus(sIDAdresse, sHauptSorte, sIDSorte,null);
//	}
//	
//
//
//	/**
//	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
//	 * Die Daten werden nicht in die DB übernommen, sonder in einer ATOM_LAG_BEW_DataRowStatus zurückgegeben
//	 *  
//	 * @param sIDAdresse
//	 * @param sHauptSorte
//	 * @param sIDSorte
//	 * @param sDatum
//	 * @return
//	 * @throws myException
//	 */
//	public ATOM_LAG_BEW_DataRowStatus ErmittleLagerstatus(String sIDAdresse, String sHauptSorte, String sIDSorte, String sDatum) throws myException{
//		ATOM_LAG_BEW_DataRowStatus  oRet = null;
//		
//		m_IDAdresseLager = sIDAdresse;
//		m_IDHauptSorte = sHauptSorte;
//		m_IDSorte = sIDSorte;
//		m_BuchungsdatumBis = sDatum;
//
//		readLagerstatus(m_bKostenberuecksichtigung);
//		
//		int rows =m_vRowStatus.size(); 
//		if ( rows == 1 ){
//			oRet = m_vRowStatus.firstElement();
//		} else {
//			//bibMSG.add_MESSAGE(new MyE2_Info_Message("es sind " + Integer.toString(rows) + " Zeilen zurückgegeben worden. Eine Zeile wird erwartet."));
//			//throw new myException("LAG_BEW_StatusErmittlung::ErmittleLagerstatus: es sind " + Integer.toString(rows) + " Zeilen zurückgegeben worden. Eine Zeile wird erwartet.");
//		}
//		
//		return oRet;
//	}
	
	
	
	/**
	 * 
	 * @param bKostenberuecksichtigung
	 * @param bSelectAllAndGroup
	 * @return
	 */
	private String getSqlStatement( /*boolean bSelectForMengenermittlung*/ ){
		
		String sColArtikel = "";
		String sColAdresse = "";
		String sWhereArtikel = "";
		String sWhereAdresse = "";
		String sWhereDatumSetzkasten = "";
		String sWhereDatumAll = "";
		
		String sGroupBy = "";
		String sSum = "";
		String sColumnsForMengenermittlung	= " 0 ";

		
		
		
		
		// Zeitraumeinschraenkung 
		if (!bibALL.isEmpty(m_BuchungsdatumBis)) {
			sWhereDatumSetzkasten = 
							" AND A_AUS.LEISTUNGSDATUM  < ( TO_DATE('" + m_BuchungsdatumBis + "','YYYY-MM-DD') + 1 ) " + 
							" AND A_EIN.LEISTUNGSDATUM  < ( TO_DATE('" + m_BuchungsdatumBis + "','YYYY-MM-DD') + 1 ) " ;
			sWhereDatumAll = 
							" AND A.LEISTUNGSDATUM      < ( TO_DATE('" + m_BuchungsdatumBis + "','YYYY-MM-DD') + 1 ) " ;
		}
		
		// Setzkastentabelle und ID-Spalte
		m_sqlTablenameAtomSetzkasten		= m_bKostenberuecksichtigung ? "JT_BEWEGUNG_SETZKASTEN_K" : "JT_BEWEGUNG_SETZKASTEN";
		m_sqlPrimaryKeyColAtomSetzkasten 	= m_bKostenberuecksichtigung ? "ID_BEWEGUNG_SETZKASTEN_K" : "ID_BEWEGUNG_SETZKASTEN";
		
		
		if (m_bKostenberuecksichtigung){
			m_sqlColumPreis = " ((A.E_PREIS_RESULT_NETTO_MGE * (A.MENGE_NETTO/NVL(ART.MENGENDIVISOR,1)) ) + NVL(V.KOSTEN_TRANSPORT_WE,0) + NVL(V.KOSTEN_PRODUKT_WE,0) ) / NVL(A.MENGE_NETTO / NVL(ART.MENGENDIVISOR,1),1)  ";
		} else {
			m_sqlColumPreis = " A.E_PREIS_RESULT_NETTO_MGE ";
		}
		

		
		
		sGroupBy 	  = "" ;

		if (m_Status.equals(ENUM_ATOM_Statusermittlung_Typ.einzelzeilen)){
			
			// keine Gruppierung, keine summierung, zusätzliche Spalten für die Mengenermittlung
			sColAdresse 				= " S.ID_ADRESSE, ";
			sColArtikel    				= " A.ID_ARTIKEL, ";
			sSum 						= "";
			sColumnsForMengenermittlung = " A.ID_BEWEGUNG_ATOM, ART.ID_EINHEIT, EINH.EINHEITKURZ , A.LEISTUNGSDATUM ";
			sGroupBy 					= "";
			
		} else if (m_Status.equals(ENUM_ATOM_Statusermittlung_Typ.statuszeilen)){
			// Gruppierung nur über Gewichtseinheit, der Rest geht über die Selektion allgemein
			sColAdresse 				= " 0, ";
			sColArtikel    				= " 0, ";
			sSum 						= " SUM ";
			sColumnsForMengenermittlung = " 0 ,0, '-', '0' ";
			sGroupBy 					= "GROUP BY ART.ID_EINHEIT ";
			
		} else if (m_Status.equals(ENUM_ATOM_Statusermittlung_Typ.historie)){
			
			sColAdresse 				= " S.ID_ADRESSE, ";
			sColArtikel    				= " A.ID_ARTIKEL, ";
			sSum 						= " SUM ";
			sColumnsForMengenermittlung = " 0 ,0, '-', '0' ";
			sGroupBy 					= "GROUP BY S.ID_ADRESSE, A.ID_ARTIKEL ";
			
		} else {
			// darf nicht passieren
		}
		
		
		// SORTE: Vorrangbehandlung:
		// 1. ID alleine
		// 2. ID-Vektor
		// 3. HAUPTSORTE alleine
		// 4. HAUPTSORTE Vektor
		if (m_IDSorte != null && m_IDSorte.trim().length() > 0) {
			sWhereArtikel 			= " AND A.ID_ARTIKEL =  " + m_IDSorte;
			
		} else if (m_vIDSorte != null && m_vIDSorte.size() > 0){
			if (m_vIDSorte.size()==1){
				sWhereArtikel 			= " AND A.ID_ARTIKEL =  " + m_vIDSorte.get(0);
			
			} else {
				
				try {
					sWhereArtikel 			= " AND A.ID_ARTIKEL IN (" + bibALL.Concatenate(m_vIDSorte, ",", "-1" )  + ") " ;
				} catch (myException e) {
					
					e.printStackTrace();
				}
			}

		} else if (m_IDHauptSorte != null && m_IDHauptSorte.trim().length() > 0) {
			sWhereArtikel 		= " AND substr(ART.ANR1,0,2) = substr('" + "00" + m_IDHauptSorte + "',-2) ";
			
		} else if (m_vIDHauptSorte != null && m_vIDHauptSorte.size()> 0){
			if (m_vIDHauptSorte.size()==1){
				sWhereArtikel 		= " AND SUBSTR(ART.ANR1,0,2) = substr('" + "00" + m_vIDHauptSorte.get(0) + "',-2) ";
			
			} else {
				Vector<String> vTemp = new Vector<String>();
				for (String s: m_vIDHauptSorte){
					vTemp.add("substr('" + "00" + s + "',-2) ");
				}
				try {
					sWhereArtikel 		= " AND SUBSTR(ART.ANR1,0,2) IN (" + bibALL.Concatenate(vTemp, ",", "'--'" )  + ") ";
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		// LAGER
		if (m_IDAdresseLager != null && m_IDAdresseLager.trim().length() > 0) {
			sWhereAdresse 				= " AND S.ID_ADRESSE = " + m_IDAdresseLager +" ";
		}  else if (m_vIDAdresseLager != null && m_vIDAdresseLager.size() > 0){
			if (m_vIDAdresseLager.size() == 1){
				sWhereAdresse 				= " AND S.ID_ADRESSE = " + m_vIDAdresseLager.get(0) +" ";
			} else {
				try {
					sWhereAdresse 				= " AND S.ID_ADRESSE in ( " + bibALL.Concatenate(m_vIDAdresseLager, ",","-1") + " ) ";
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}

		
		
		
		// zusätzliche Spalten und Gruppierungen einblenden, wenn man einzelne Abfragen durchführt
//		if (!bibALL.isEmpty(m_IDSorte) ){
//			sWhereArtikel  = " AND A.ID_ARTIKEL = " + m_IDSorte + " ";
//			
//		} else if (!bibALL.isEmpty(m_IDHauptSorte) ){
//			sWhereArtikel  = " AND substr(ART.ANR1,0,2) = '" + m_IDHauptSorte + "' ";
//			
//		} else {
//		}
		
		
		// Adresse
//		if (! bibALL.isEmpty(m_IDAdresseLager) ){
//			sWhereAdresse += " AND S.ID_ADRESSE = " + m_IDAdresseLager + " ";
//		}
		
		String 	sSelect = 
				  " SELECT "
				+   sColAdresse
				+ 	sColArtikel 
				+ "   " + sSum + "( nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0) ) as  menge_gesamt "
				+ " , " + sSum + " ( case when A.E_PREIS_RESULT_NETTO_MGE is null then 0 else nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0) end ) as menge_bepreist  "
				+ " , " + sSum + " ( case when A.E_PREIS_RESULT_NETTO_MGE is not null then 0 else  nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0) end ) as menge_nullpreis "
				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumPreis + " ),2) as wert_gesamt "
				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumPreis + " ) / nvl(" + sSum + "( (nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) ,1),2) as epreis_avg_all "
				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumPreis + ") / nvl(" + sSum + " ( (case when A.E_PREIS_RESULT_NETTO_MGE is null then 0 else  (nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0))  end)  / ART.MENGENDIVISOR)  ,1),2) as epreis_avg_bepreist "

				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumnPreisOhneKosten + "),2) as wert_gesamt_ohne "
				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumnPreisOhneKosten + ") / nvl(" + sSum + "( (nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) ,1),2) as epreis_avg_all_ohne "
				+ " , round ( " + sSum + " ( ((nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0)) / ART.MENGENDIVISOR) * " + m_sqlColumnPreisOhneKosten + ") / nvl(" + sSum + " ( (case when A.E_PREIS_RESULT_NETTO_MGE is null then 0 else  (nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0))  end)  / ART.MENGENDIVISOR)  ,1),2) as epreis_avg_bepreist_ohne "
				+ " , " + sColumnsForMengenermittlung 
				
				+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM  A "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S  ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM    AND S.MENGENVORZEICHEN = 1 "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL ART         ON A.ID_ARTIKEL = ART.ID_ARTIKEL "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V   ON A.ID_BEWEGUNG_VEKTOR = V.ID_BEWEGUNG_VEKTOR "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT EINH        ON ART.ID_EINHEIT = EINH.ID_EINHEIT  " 				
				+ " LEFT OUTER JOIN  (  "
				+ "                  SELECT "
				+ "                    A_EIN.ID_ARTIKEL ID_ARTIKEL_VERSETZT "
				+ "                  , S_EIN.ID_ADRESSE ID_ADRESSE_VERSETZT "
				+ "                  , A_EIN.ID_BEWEGUNG_ATOM ID_ATOM_VERSETZT "
				+ "                  , sum(SK.menge) as WE_VERSETZT "
				+ "                  FROM " + bibE2.cTO() + "." + m_sqlTablenameAtomSetzkasten + " SK "
				+ "                  INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A_AUS ON SK.ID_ATOM_AUSGANG = A_AUS.ID_BEWEGUNG_ATOM "
				+ "                  INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A_EIN ON SK.ID_ATOM_EINGANG = A_EIN.ID_BEWEGUNG_ATOM "
				+ "                  INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S_EIN ON A_EIN.ID_BEWEGUNG_ATOM = S_EIN.ID_BEWEGUNG_ATOM AND S_EIN.MENGENVORZEICHEN = 1 "
				+ "                  WHERE 1=1 "
				+ 					 sWhereDatumSetzkasten
				+ "                  GROUP BY  "
				+ "                            S_EIN.ID_ADRESSE "
				+ "                           ,A_EIN.ID_ARTIKEL "
				+ "                           ,A_EIN.ID_BEWEGUNG_ATOM "
				+ "   	) T1 ON S.ID_ADRESSE = T1.ID_ADRESSE_VERSETZT and A.ID_ARTIKEL = T1.ID_ARTIKEL_VERSETZT AND A.ID_BEWEGUNG_ATOM = T1.ID_ATOM_VERSETZT "
				+ " WHERE "
//				+ "   nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0) != 0  "
				+ "   nvl(A.MENGE_NETTO,0) - nvl(T1.WE_VERSETZT,0) > 0  "
				+ "   AND s.ID_ADRESSE IN ( SELECT id_adresse from V_FIRMENADRESSEN_FLACH where ID_ADRESSE_BASIS = 5525 and V_FIRMENADRESSEN_FLACH.SONDERLAGER is null) "
				+ "   AND A.LEISTUNGSDATUM is not null "
				+ "   AND nvl(A.DELETED,'N') = 'N' "
				+ "   AND nvl(A.STORNIERT,'N') = 'N'  "
				+ 	  sWhereDatumAll
				+ 	  sWhereArtikel
				+     sWhereAdresse
				+ "  "
				+     sGroupBy
				+ " ORDER BY 8,4,15 "
				+ "  ";
				  
				return sSelect;
	}
	
	
	
	/**
	 * Ermittelt den Lagerstatus 
	 * für die vorgegebenen Einstellungen für Lager/Sorte/Datum
	 * 
	 * @param bKostenberuecksichtigung
	 */
	private void initLagerstatusDaten(){
		
		String [][] sArrDaten = new String[0][0];
		
		// Bei mengenermittlung werden die Preiszeilen einzeln zurückgegeben, sonst summiert nach Adresse/Artikel bzw. Hauptartikel
		String sSelect = this.getSqlStatement( );
		
		sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect,(String)null);
		
		//fillDataInRow(sArrDaten, ENUM_Values.FIBU_FORDERUNG );
		m_vRowStatus = new Vector<ATOM_LAG_BEW_DataRowStatus>();
		
		//Zeile(n) in die DB einfügen
		fillDataInRow(sArrDaten /*, bSelectForMengenermittlung, bBeruecksichtigeAuchArtikelMitNegativSaldo*/ );
		
	}
	
	
	
	
	
	
	/**
	 * Füllen der Daten in das Datenobjekt
	 * @param sArrDaten
	 * @param bBeruecksichtigeAuchArtikelMitNegativSaldo - wird verwendet, wenn alle Status-Daten aller Lager/Sorten auf einmal gelesen werden
	 * 					- wenn true, werden auch gleichzeitig negative Lagerbestände ermittelt und die fehlenden Einträge ersetzt
	 */
	private void fillDataInRow(String[][] sArrDaten/*, boolean bSelectForMengenermittlung, boolean bBeruecksichtigeAuchArtikelMitNegativSaldo*/) {

		// wenn alle Lagerstati ermittelt werden, wird geprüft, welche Läger zum Zeitpunkt des ermittelns des Lagerstatus keinen Lagerbestand haben, dann wird der Lagersaldo in die Zeile eingetragen.
		Vector<String> vKeysFound = new Vector<String>();
		ATOM_LAG_BEW_DataRowStatus oRow ;
		
		
		boolean bSelectForMengenermittlung = m_Status.equals(ENUM_ATOM_Statusermittlung_Typ.einzelzeilen);
		boolean bBeruecksichtigeAuchArtikelMitNegativSaldo = m_Status.equals(ENUM_ATOM_Statusermittlung_Typ.historie);
		
		int j = 0;
		if (sArrDaten != null){
			// sonst den Vektor füllen
			
			for (int i = 0; i < sArrDaten.length; i++){
				
				oRow = new ATOM_LAG_BEW_DataRowStatus();

				/**
				 *  Ergebnisspalten:
				 *  1. ID_ADRESSE
				 *  2. ID_ARTIKEL/ATRIKELGRUPPE
				 *  3. Menge Gesamt
				 *  4. Menge Bepreist
				 *  5. Menge Nullpreis
				 *  6. Wert Gesamt      oder Wert Einzelposition falls Einzelliste
				 *  7. AVG Alles		oder Einzelpreis falls Einzelliste
				 *  8. AVG Bepreist		oder Einzelpreis falls Einzelliste
				 *  9. Wert Gesamt Ohne Kosten		oder Wert Einzelposition falls Einzelliste
				 *  10. AVG Alles Ohne Kosten		oder Einzelpreis falls Einzelliste
				 *  11. AVG Bepreist Ohne Kosten	oder Einzelpreis falls Einzelliste
				 *  12. ID_BEWEGUNG_ATOM oder 0 wenn gruppiert
				 *  13. ID_EINHEIT       oder 0 wenn gruppiert
				 *  14. EINHEIT_KURZ     oder - wenn gruppiert
				 */
				
				j = 0;
				// 1. Spalte Adresse
				oRow.setIdAdresseLager(sArrDaten[i][j] ); j++;
				
				// 2. Spalte Artikel/Hauptartikel
				if (bibALL.isEmpty( m_IDSorte ) && !bibALL.isEmpty(m_IDHauptSorte) ){
					oRow.setIdArtikelHauptsorte(sArrDaten[i][j] ); j++;
				} else {
					oRow.setIdArtikelSorte(sArrDaten[i][j] ); j++;
				}
				
				// 3. Spalte
				oRow.setMenge_Gesamt (sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				// 4. Spalte
				oRow.setMenge_Preise_nicht_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				// 5. Spalte
				oRow.setMenge_Preise_nur_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				// 6. Spalte
				oRow.setSumme_Restwert(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;
				// 7. Spalte
				oRow.setAvg_Restwert_gesamt (sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;
				// 8. Spalte
				oRow.setAvg_Restwert_Menge_Preise_nicht_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;

				// 9. Spalte
				oRow.setSumme_Restwert_Ohne_Kosten(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;
				// 10. Spalte
				oRow.setAvg_Restwert_gesamt_Ohne_Kosten(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;
				// 11. Spalte
				oRow.setAvg_Restwert_Menge_Preise_nicht_Null_Ohne_Kosten(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : null); j++;
				// 12. Spalte
				oRow.setIdBewegungAtom(sArrDaten[i][j]); j++;
				// 13. Spalte
				oRow.setIdEinheit(sArrDaten[i][j]); j++;
				// 14. Spalte
				oRow.setEinheitKurz(sArrDaten[i][j]); j++;
				
				// falls alle Artikel gesucht werden, eine Liste füllen, um negative Bestände zu laden.
				if (bBeruecksichtigeAuchArtikelMitNegativSaldo){
					// es wird festgehalten, welche Lager/Sorten-Kombi einen aktuellen Status erhalten hat
					vKeysFound.add(oRow.getIdAdresseLager() + "#" + oRow.getIdArtikelSorte());
				}
				
				// Zeile dazufügen
				this.m_vRowStatus.add(oRow);
			}
		}
		
		
		// falls bGroupAll angegeben ist, dann prüfen, welche Daten keinen Lagerbestand mehr haben, um dann die negative Menge einzubuchen
		ATOM_LagerSaldoErmittlung oLagersaldo = new ATOM_LagerSaldoErmittlung();
		if(bBeruecksichtigeAuchArtikelMitNegativSaldo){
			try {
				// lesen der Saldodaten zum Zeitpunkt der Statusermittlung
				oLagersaldo.readSaldoDaten(m_IDAdresseLager, m_IDHauptSorte, m_IDSorte, m_BuchungsdatumBis);
				HashMap<String, ATOM_SaldoDaten> hmSaldo = oLagersaldo.getListOfSaldoDaten();
				
				for (String sKey :  hmSaldo.keySet()){
					if(!vKeysFound.contains(sKey)){
						// jetzt einen neuen Datensatz aufbauen mit dem Lager, der Sorte, und dem aktuellen Saldo (muss eigentlich negativ sein)
						ATOM_SaldoDaten o = hmSaldo.get(sKey);
						if (o != null){
							oRow = new ATOM_LAG_BEW_DataRowStatus();
							oRow.setIdAdresseLager(o.get_ID_Lager());
							oRow.setIdArtikelSorte(o.get_ID_Sorte());
							oRow.setMenge_Gesamt(o.get_Saldo());
							oRow.setMenge_Preise_nicht_Null(BigDecimal.ZERO); 
							oRow.setMenge_Preise_nur_Null(BigDecimal.ZERO); 
							oRow.setSumme_Restwert(BigDecimal.ZERO);
							oRow.setAvg_Restwert_gesamt (BigDecimal.ZERO);
							oRow.setAvg_Restwert_Menge_Preise_nicht_Null(BigDecimal.ZERO);
							this.m_vRowStatus.add(oRow);
						}
					}
				}
				
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
//		else if (bSelectForMengenermittlung == false && m_vRowStatus.size() == 0 &&  !bibALL.isEmpty(m_IDAdresseLager) && !bibALL.isEmpty(m_IDSorte) ){
//			// 
//			try {
//				oLagersaldo.readSaldoDaten(m_IDAdresseLager, m_IDSorte, m_BuchungsdatumBis);
//				ATOM_SaldoDaten o = oLagersaldo.getData(m_IDAdresseLager, m_IDSorte);
//				if (o != null ){
//					oRow = new ATOM_LAG_BEW_DataRowStatus();
//					oRow.setIdAdresseLager(m_IDAdresseLager);
//					oRow.setIdArtikelSorte(m_IDSorte);
//					// falls kein Saldo gefunden wurde, schreiben wir 0 rein
//					if (o.is_bIsEmpty()){
//						oRow.setMenge_Gesamt(BigDecimal.ZERO);
//					} else {
//						oRow.setMenge_Gesamt(o.get_Saldo());
//					}
//					oRow.setMenge_Preise_nicht_Null(BigDecimal.ZERO); 
//					oRow.setMenge_Preise_nur_Null(BigDecimal.ZERO); 
//					oRow.setSumme_Restwert(BigDecimal.ZERO);
//					oRow.setAvg_Restwert_gesamt (BigDecimal.ZERO);
//					oRow.setAvg_Restwert_Menge_Preise_nicht_Null(BigDecimal.ZERO);
//					this.m_vRowStatus.add(oRow);
//				}
//			} catch (myException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Schreiben der Daten in die DB
	 * @param bOverrideOldEntries  
	 * @param Buchungsdatum  / ISO-FORMAT yyyy-mm-dd
	 * @throws myException
	 */
	private void writeDataToDB(boolean bOverrideOldEntries, String Buchungsdatum) throws myException{

/*		// prüfen, ob in dem Tag schon einträge gemacht sind. Wenn ja, dann müssen diese Überschrieben werden,
		// oder der Vorgang abgebrochen.
		if (!bOverrideOldEntries){
			RECLIST_STATUS_LAGER rlStatus = new RECLIST_STATUS_LAGER("SELECT * from  " + bibE2.cTO() + ".JT_STATUS_LAGER WHERE BUCHUNGSDATUM = to_date( '" + Buchungsdatum +"','yyyy-mm-dd')" );
			
			if (rlStatus.size() > 0 && ! bOverrideOldEntries){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es sind schon Einträge für diesen Tag vorhanden. Bitte setzten Sie die Option 'Einträge überschreiben'")));
				return;
			} 
		} else {
			// wenn eh die alten überschrieben werden sollen, dann löschen der alten Einträge
			cleanStatusRecords(Buchungsdatum);
		}
		
		
		String sSql = "";
		
		java.util.Iterator<ATOM_LAG_BEW_DataRowStatus> vIter = m_vRowStatus.iterator();
		
		
		// jetzt die neuen Sätze schreiben
		while (vIter.hasNext()){
			ATOM_LAG_BEW_DataRowStatus row = vIter.next();
			
			MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

			oSql.addSQL_Paar("ID_STATUS_LAGER", "SEQ_STATUS_LAGER.NEXTVAL", false);
			oSql.addSQL_Paar("ID_ADRESSE", row.getIdAdresseLager(),false);
			oSql.addSQL_Paar("ID_SORTE", row.getIdArtikelSorte(),false);
			oSql.addSQL_Paar("MENGE_GESAMT", row.getMenge_Gesamt().toPlainString(),false);
			oSql.addSQL_Paar("MENGE_PREISE_NICHT_NULL", row.getMenge_Preise_nicht_Null().toPlainString(),false);
			oSql.addSQL_Paar("MENGE_PREISE_NULL", row.getMenge_Preise_nur_Null().toPlainString(),false);
//			oSql.addSQL_Paar("MENGE_PREISE_LEER", row.getMenge_Preise_leer().toPlainString(),false);
			oSql.addSQL_Paar("SUM_RESTWERT", row.getSumme_Restwert().toPlainString(),false);
			oSql.addSQL_Paar("AVG_WERT_GESAMT", row.getAvg_Restwert_gesamt().toPlainString(),false);
			oSql.addSQL_Paar("AVG_WERT_NICHT_NULL", row.getAvg_Restwert_Menge_Preise_nicht_Null().toPlainString(),false);
//			oSql.addSQL_Paar("AVG_WERT_MIT_NULL", row.getAvg_Restwert_Menge_Preise_auch_Null().toPlainString(),false);
			
			oSql.addSQL_Paar("BUCHUNGSDATUM", "to_date( '" + Buchungsdatum +"','yyyy-mm-dd')" , false);
			oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		    
		    sSql = oSql.get_CompleteInsertString("JT_STATUS_LAGER", bibE2.cTO());

		    m_vSqlStatements.add( sSql);
		}
		
		this.executeSqlStatements(true);*/
	}
		
	
	
	
	
		
	/**
	 * löscht die Daten aus der JT_STATUS_LAGER
	 * @param sDate  im ISO-Format yyyy-mm-dd   
	 * 
	 */
	private boolean cleanStatusRecords(String sDate){
		return cleanStatusRecords(sDate, m_IDAdresseLager, m_IDSorte);
	}
	
	
	
	/**
	 * löscht die Daten aus der JT_STATUS_LAGER
	 * @param sDate im ISO-Format yyyy-mm-dd  
	 * @param idLager
	 * @param idSorte
	 * @return
	 */
	private boolean cleanStatusRecords(String sDate, String idLager, String idSorte){
		boolean bRet = false;
		
/*		String sSql = "DELETE FROM " + bibE2.cTO()+".JT_STATUS_LAGER" +
		" WHERE BUCHUNGSDATUM  >= to_date( '" + sDate +"','yyyy-mm-dd')" +
		" AND ID_MANDANT = " + bibALL.get_ID_MANDANT(); 
		
		if (!bibALL.isEmpty(idLager)){
			sSql += " AND ID_ADRESSE = " + idLager ; 
		}
		
		if (!bibALL.isEmpty(idSorte)){
			sSql += " AND ID_SORTE = " + idSorte ;
		}

		if (!bibALL.isEmpty(sDate)){
			m_vSqlStatements.add(sSql);
			bRet =executeSqlStatements(true);
		}
*/		
		return bRet;
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
//		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSqlStatements, UseOwnTransaction);
		boolean bRetSQL = bibDB.ExecSQL(m_vSqlStatements, UseOwnTransaction);
		bRet &= bRetSQL;
		
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


	
	/**
	 * verbucht alle LagerKonto-Einträge in der JT_LAGER_BEWEGUNG
	 * schreibt anschliessend den Lagerstatus in die JT_STATUS_LAGER-Tabelle
	 * gültig zum aktuellen Datum der Ausführung 
	 * 
	 * NB: Es werden *KEINE* Preisermittlungen im vorfeld der Lagerverbuchung durchgeführt 
	 * (im Gegensatz zum task-Lauf der automatischen Verarbeitung durch die crontab.
	 * 
	 * @return
	 */
	public boolean verbucheLagerKontoUndErstelleStatus(String sBuchungsdatumISO){
		boolean bRet = false;
/*		String idMandant = bibALL.get_ID_MANDANT();
		
		LAG_LagerBewegungHandler oBewHandler = new LAG_LagerBewegungHandler(null);
		try {
			oBewHandler.ReorganiseLagerEntries(idMandant,sBuchungsdatumISO);
		} catch (Exception e1) {
		}
		
		try {
			this.ErmittleLagerstatus(true, sBuchungsdatumISO);
			bRet = true;
		} catch (myException e) {
		}
		
*/		return bRet;
	}
	
	
	/**
	 * verbucht alle LagerKonto-Einträge in der JT_LAGER_BEWEGUNG
	 * schreibt anschliessend den Lagerstatus in die JT_STATUS_LAGER-Tabelle
	 * gültig zum aktuellen Datum der Ausführung 
	 * 
	 * NB: Es werden *KEINE* Preisermittlungen im vorfeld der Lagerverbuchung durchgeführt 
	 * (im Gegensatz zum task-Lauf der automatischen Verarbeitung durch die crontab.
	 * 
	 * @return
	 */
	public boolean verbucheLagerKontoUndErstelleStatus(String idLager, String idSorte, String sBuchungsdatumISO){
		boolean bRet = false;
/*		String idMandant = bibALL.get_ID_MANDANT();
		
		LAG_LagerBewegungHandler oBewHandler = new LAG_LagerBewegungHandler(null);
		try {
			oBewHandler.ReorganiseLagerEntries(idMandant,idLager,idSorte,sBuchungsdatumISO);
		} catch (Exception e1) {
		}
		
		try {
			this.ErmittleLagerstatusBeiReorgLagerSorte(idLager,null, idSorte,  sBuchungsdatumISO, true);
			bRet = true;
		} catch (myException e) {
		}
*/		
		return bRet;
	}

	
	
	
	/**
	 * Ermittelt die Tage, die neu aufgebaut werden müssen
	 * 2012-04-03: Es werden alle Tage vom Ausgangszeitpunkt bis Heute berechnet
	 * @param sDatumAb
	 * @return
	 */
	public Vector<String> getTageFuerReorg(String sDatumAb){
		Vector<String> vTage = new Vector<String>();
		
/*
		// den kleinsten Tag bestimmen, der als Anfang des Neuaufbaus genutzt werden soll 
		String sSql =   " SELECT to_char(MIN( K.BUCHUNGSDATUM ),'yyyy-MM-dd') as datum " +
						" FROM " + bibE2.cTO() + ".JT_LAGER_KONTO K " +
						" WHERE TRUNC(K.BUCHUNGSDATUM,'DD') >= to_date('" + sDatumAb + "','yyyy-MM-dd') " ;
						
        String sSql2 =  " SELECT to_char(MAX( K.BUCHUNGSDATUM ),'yyyy-MM-dd')  " +
						" FROM " + bibE2.cTO() + ".JT_LAGER_KONTO K " +
					    " WHERE TRUNC(K.BUCHUNGSDATUM,'DD') <= to_date('" + sDatumAb + "','yyyy-MM-dd')" ;
					    
		
		String datumMin = bibDB.EinzelAbfrage(sSql,null,null,null);
		String datumMax = bibDB.EinzelAbfrage(sSql2,null,null,null);
		
		String sDatumMin = "";
		
		// beginn-Datum prüfen.
		if (datumMax != null ){
			sDatumMin = datumMax;
		} else {
			sDatumMin = datumMin;
		}
		
		if (sDatumMin == null){
			bibMSG.add_MESSAGE( new MyE2_Alarm_Message(new MyString("LAG_BEW_StatusErmittlung::getTageFuerReorg() : Kann den Tag nicht bestimmen.")));
			return vTage;
		}
		
		Calendar calAb = new GregorianCalendar();
		Calendar calHeute = new GregorianCalendar();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dtAb;
		try {
			dtAb = df.parse(sDatumMin);
			calAb.setTime(dtAb);
			
			long time = calHeute.getTime().getTime() - calAb.getTime().getTime(); // differenz in Millisekunden
			long tage = Math.round((double)time / (24. * 60. * 60. * 1000.) );
			
			for (int i = 0; i<= tage; i++){
				vTage.add(df.format(calAb.getTime()) );
				
				// 1 Tag weiter...
				calAb.add(Calendar.DATE, 1);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bibMSG.add_MESSAGE( new MyE2_Alarm_Message(new MyString("LAG_BEW_StatusErmittlung::getTageFuerReorg() : Kann den Tag nicht bestimmen.")));
		}
*/		
		return vTage;
	}
	
	/**
	 * Ermittelt die Tage, die neu aufgebaut werden müssen
	 * @param sDatumAb
	 * @return
	 */
	public Vector<String> getTageFuerReorg_NUR_BELEGTE_TAGE(String sDatumAb){
		Vector<String> vTage = new Vector<String>();
		
/*		String sSql = "SELECT DISTINCT  to_char(K.BUCHUNGSDATUM,'yyyy-MM-dd')  " +
					" FROM " + bibE2.cTO() + ".JT_LAGER_KONTO K " +
					" WHERE TRUNC(K.BUCHUNGSDATUM,'DD') >= to_date('" + sDatumAb + "','yyyy-MM-dd')" +
					" ORDER BY 1";
		
		String[][] datum = bibDB.EinzelAbfrageInArray(sSql);
		
		for (int i = 0; i< datum.length; i++){
			vTage.add(datum[i][0]);
		}*/
		
		return vTage;
	}
	/**
	 * Löschen aller beteiligten Lagerbewegungen und zurücksetzen des IST_KOMPLETT-Schalters im LagerKonto
	 * wenn idLager oder idSorte leer sind, dann werden alle Läger/Sorten gelöscht
	 * 
	 * @param sDatumAb
	 * @param idLager
	 * @param idSorte
	 * @return
	 */
	public boolean cleanLagerKontoUndBewegungFuerReorg(String m_datum_ab, String m_id_lager, String m_id_sorte){
		boolean bRet = false;
/*		
		// das Datum muss angegeben sein!
		if (m_datum_ab == null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("DatumAb muss bei der Lagerneubewertung angegeben werden."));
			return bRet;
		}
		
		// es dürfen entweder Lager und Sorte definiert sein, oder nicht!
		// falls es gemischt ist, ist es ein Fehler!!
		if ( (m_id_lager == null && m_id_sorte != null) ||
			 (m_id_lager != null && m_id_sorte == null)
			){
			
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es müssen Lager und Sorte bei der Lagerneubewertung angegeben werden."));
			return bRet;
		}
		
		
		
		*//**
		 * Wenn id_lager und id_sorte nicht angegeben sind, werden alle Buchungen der Läger und Sorten bis zum 
		 * angegebenen Datum gelöscht!
		 *//*
		String sWhere = "";
		if (m_id_lager != null || m_id_sorte != null){
			sWhere = " AND K.ID_ADRESSE_LAGER =  " + m_id_lager + 
				     " AND K.ID_ARTIKEL_SORTE = " + m_id_sorte ; 
		}
		
		String sSql_Kontosaetze_BASIS =	
			" SELECT K.ID_LAGER_KONTO FROM " + bibE2.cTO() +  ".JT_LAGER_KONTO K " + 
			" WHERE K.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
			" AND NVL(K.STORNO ,'N')  = 'N' " 
			+ sWhere;
		
		
		// Finden aller Kontoeintragungen, die auf IST_KOMPLETT = 'N' zurückgesetzt werden sollen
		String sSql_Kontosaetze =	 
			sSql_Kontosaetze_BASIS +  
			" AND K.BUCHUNGSDATUM >=  to_date('" + m_datum_ab  + "','yyyy-MM-dd')";		
		
		
		
		// finden der dazugehörigen Kontosätze, die auf IST_KOMPLETT = 'N' gesetzt werden sollen
		String sSql_SetComplete_No_Eingang =	
			" UPDATE " + bibE2.cTO() +  ".JT_LAGER_KONTO K SET K.IST_KOMPLETT = null WHERE K.ID_LAGER_KONTO in ( " + 
			" SELECT DISTINCT B.ID_LAGER_KONTO_AUSGANG " +
			" FROM " + bibE2.cTO() +  ".JT_LAGER_BEWEGUNG B " + 
			" WHERE B.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
			" AND B.ID_LAGER_KONTO_EINGANG IN (" + sSql_Kontosaetze + 	") " +
			" ) " ;
	
		// finden der dazugehörigen Kontosätze, die auf IST_KOMPLETT = 'N' gesetzt werden sollen
		String sSql_SetComplete_No_Ausgang = 	
			" UPDATE " + bibE2.cTO() +  ".JT_LAGER_KONTO K SET K.IST_KOMPLETT = null WHERE K.ID_LAGER_KONTO in ( " +
			" SELECT DISTINCT B.ID_LAGER_KONTO_EINGANG " +
			" FROM " + bibE2.cTO() +  ".JT_LAGER_BEWEGUNG B " + 
			" WHERE B.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
			" AND B.ID_LAGER_KONTO_AUSGANG IN (" + sSql_Kontosaetze + 	") " +
			" ) " ;
		
		
		// finden der dazugehörigen Kontosätze, die auf IST_KOMPLETT = 'N' gesetzt werden sollen
		String sSql_SetComplete_No = 	
			" UPDATE " + bibE2.cTO() +  ".JT_LAGER_KONTO B SET B.IST_KOMPLETT = null WHERE B.ID_LAGER_KONTO in ( " 
			+ sSql_Kontosaetze + " ) " ;
		
		
		
		// alle Bewegungssätze, die gelöscht werden müssen
		String sSql_DeleteBewegungssatz = 	
			" DELETE FROM " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG B " +
			" WHERE B.ID_MANDANT = " + bibALL.get_ID_MANDANT() + 
			" AND ( " +
			"	B.ID_LAGER_KONTO_AUSGANG IN ( " + sSql_Kontosaetze + " ) " +
			" 	OR " +
			"	B.ID_LAGER_KONTO_EINGANG IN ( " + sSql_Kontosaetze + " ) " +
			" ) ";

		
		
		Vector<String> vSql = new Vector<String>();
		vSql.add(sSql_SetComplete_No);
		vSql.add(sSql_SetComplete_No_Ausgang);
		vSql.add(sSql_SetComplete_No_Eingang);
		
		bibDB.ExecMultiSQLVector(vSql, false);
		
		if (bibMSG.get_bIsOK()){
			bRet = bibDB.ExecSQL(sSql_DeleteBewegungssatz, true);
		}
		vSql.clear();
		
*/		return bRet;
	}
	
	
	
	
	/**
	 * Aktualisiert den Lagerstatus eines Mandanten anhand der IST_GEAENDERT-Flags in den Lägern
	 * Es wird der Setztkasten neu aufgebaut und der Status neu geschrieben.
	 * @return  true bei Erfolg
	 */
	public boolean rebuildLagerstatus_AUTO(){
		boolean bRet = false;
		
/*		// finden der Läger/Sorten die neu aufgebaut werden müssen:
		String sSqlLagerSorte = "" +
				" SELECT 	K.ID_ADRESSE_LAGER, K.ID_ARTIKEL_SORTE, min(to_char(K.BUCHUNGSDATUM,'yyyy-MM-dd') ) " +
				" FROM 		" + bibE2.cTO() + ".JT_LAGER_KONTO K " +
				" WHERE 	NVL(K.IST_GEAENDERT,'N') = 'Y' " +
				" GROUP BY 	K.ID_ADRESSE_LAGER, K.ID_ARTIKEL_SORTE " +
				" ";
		String[][] aLagerSorte = bibDB.EinzelAbfrageInArray(sSqlLagerSorte);
		
		String sIDLager = null;
		String sIDSorte = null;
		String sDatumAb = null;
		for (int i = 0; i< aLagerSorte.length; i++){
			sIDLager = aLagerSorte[i][0];
			sIDSorte = aLagerSorte[i][1];
			sDatumAb = aLagerSorte[i][2];
			
			// finden der Tage die neu aufgebaut werden müssen
			Vector<String> vTage = getTageFuerReorg(sDatumAb);
			
	
			// löschen der Lagerkonto-Bewegungen und säubern der Konto-Verbuchungs-Stati
			cleanLagerKontoUndBewegungFuerReorg(sDatumAb, sIDLager, sIDSorte);
			
			// Löschen der Lager-Status-Werte
			cleanStatusRecords(sDatumAb, sIDLager, sIDSorte);
			
			// jetzt für jeden Tag das Konto neu aufbauen 
			for (String sDatum : vTage){
					this.verbucheLagerKontoUndErstelleStatus(sIDLager, sIDSorte,sDatum);
			}
			
			// danach die KENNUNG IST_GEAENDERT in den Lagerkonto-Sätzen entfernen
			Vector<String> vSql = new Vector<String>();
			vSql.add("UPDATE " + bibE2.cTO() + ".JT_LAGER_KONTO SET IST_GEAENDERT = null WHERE ID_ARTIKEL_SORTE = " + sIDSorte + " AND ID_ADRESSE_LAGER =  " + sIDLager + " ");
			bibDB.ExecMultiSQLVector(vSql, true);
			bRet = bibMSG.get_bIsOK();
			
		}
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Die automatische Lagerverbuchung und Erzeugung der Lagerwert-Historie wurde beendet."));
*/		
		return bRet;
	}
	
	
	
	
	
	
	
	
	
	
	
	// Automator-
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();

	
	@Override
	/**
	 * Parameter die von oben kommen
	 * readprices == 1 korrigieren der Preise sonst nicht
	 * reorganize == 1 lagerverbuchung der Kontostände sonst nicht
	 */
	public boolean runTask() {
		boolean bRet = false;
		
		
/*		// prüfen, ob alle Parameter vorhanden sind:
		// Bei dieser Methode gibt es gar keine Parameter, die gesetzt sein müssen, nur welche die gesetzt sein können.
		String idMandant = bibALL.get_ID_MANDANT();
		
		LAG_LagerPreisHandler oPreisHandler;
		try {
			oPreisHandler = new LAG_LagerPreisHandler(null);
		
			try {
				oPreisHandler.ReorganisePriceEntries(idMandant);
				m_TaskMessages.add(new MyString("Ermittlung der Preiseinträge...OK").CTrans());
				
			} catch (Exception e1) {
				m_TaskMessages.add(new MyString("Fehler bei der Ermittlung der Preiseinträge.").CTrans());
			}
			
		} catch (myException e2) {
			m_TaskMessages.add("LAG_LagerPreisHandler konnte nicht erzeugt werden." + e2.ErrorMessage);
		}

		
		// Alle geänderten Lagerbewegungen aktualisieren an hand des IST_GEAENDERT-Flags
		try{
			this.rebuildLagerstatus_AUTO();
			m_TaskMessages.add(new MyString("Lagerstatus neu Aufbauen...OK.").CTrans());
		} catch (Exception e2) {
			m_TaskMessages.add(new MyString("Fehler beim Neuaufbau des Lagerstatus.").CTrans() + e2.getMessage());
		}

		// zum schluss noch den aktuellen Lagerstatus aufbauen, für alle möglichen Lager/Sorten-Kombinationen
		try {
			this.ErmittleLagerstatus(true);
			m_TaskMessages.add(new MyString("Aktuellen Lagerstatus ermitteln...OK.").CTrans());
			bRet = true;
		} catch (myException e) {
			m_TaskMessages.add(e.ErrorMessage);
		}
		*/	
		
		return bRet;
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
