package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_STATUS_LAGER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerBewegungHandler;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerPreisHandler;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSaldoDaten;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSaldoErmittlung;

public class LAG_BEW_StatusErmittlung implements ICallableTask{

	private String m_IDAdresseLager = null;
	private String m_IDHauptSorte = null;
	private String m_IDSorte = null;

	/**
	 * Das Buchungsdatum nur verwenden, wenn man die Lagerbewegungsdaten historisch aufbaut.
	 * Funktioniert nur zusammen mit der Verbuchung auf einen Bestimmten Zeitpunkt hin...
	 */
	private String m_BuchungsdatumBis = null;
	
	
	
	private Vector<LAG_BEW_DataRowStatus>  m_vRowStatus = null;
	private Vector<String> m_vSqlStatements ;
	
	
	
	public LAG_BEW_StatusErmittlung() {
		super();
		m_vRowStatus = new Vector<LAG_BEW_DataRowStatus>();
		m_vSqlStatements = new Vector<String>();
	}

	
	


	/**
	 * Ermitteln vom Lagerstatus tzn aktuellen Tag
	 * die Daten werden in die DB-Tabelle geschrieben
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public void ErmittleLagerstatus(boolean overrideOldEntries) throws myException{

		// aktuelles Buchungsdatum generieren
		String sBuchungsdatum = myDateHelper.ChangeNormalString2DBFormatString(bibALL.get_cDateNOW()) ;
		
		this.ErmittleLagerstatus(overrideOldEntries, sBuchungsdatum);
	}
	
	
	/**
	 * Ermitteln des Lagerstatus zum vorgegebenen Tag
	 * @param overrideOldEntries
	 * @param sBuchungsdatum
	 * @throws myException
	 */
	public void ErmittleLagerstatus(boolean overrideOldEntries, String sBuchungsdatum) throws myException{
		m_IDAdresseLager = null;
		m_IDHauptSorte = null;
		m_IDSorte = null;

		m_BuchungsdatumBis = sBuchungsdatum;
		
		readLagerstatus(true);
		
		this.writeDataToDB(overrideOldEntries, sBuchungsdatum);
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Lagerstatus von " + sBuchungsdatum + " wurde ermittelt."));
	}
	
	
	/**
	 * Ermittelt den Lagerstatus zum vorgegebenen Tag und schreibt den Wert in die Tabelle
	 * @param sIDAdresse
	 * @param sHauptSorte
	 * @param sIDSorte
	 * @param sBuchungsdatum  yyyy-MM-dd 
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public void ErmittleLagerstatusBeiReorgLagerSorte(String sIDAdresse, String sHauptSorte, String sIDSorte, String sBuchungsdatum,boolean overrideOldEntries) throws myException{
		m_IDAdresseLager = sIDAdresse;
		m_IDHauptSorte = sHauptSorte;
		m_IDSorte = sIDSorte;

		m_BuchungsdatumBis = sBuchungsdatum;
		
		readLagerstatus(false);

		this.writeDataToDB(overrideOldEntries, sBuchungsdatum);
	}
	

	
	/**
	 * Ermitteln vom Kundenstatus eines bestimmten Kunden des Mandanten
	 * Die Daten werden nicht in die DB übernommen, sonder in einer STATK_DataRowStatus zurückgegeben 
	 * @param spIDAdresse
	 * @param overrideOldEntries
	 * @throws myException
	 */
	public LAG_BEW_DataRowStatus ErmittleLagerstatus(String sIDAdresse, String sHauptSorte, String sIDSorte) throws myException{
		LAG_BEW_DataRowStatus  oRet = null;
		
		m_IDAdresseLager = sIDAdresse;
		m_IDHauptSorte = sHauptSorte;
		m_IDSorte = sIDSorte;

		readLagerstatus(false);
		
		int rows =m_vRowStatus.size(); 
		if ( rows == 1 ){
			oRet = m_vRowStatus.firstElement();
		} else {
			//bibMSG.add_MESSAGE(new MyE2_Info_Message("es sind " + Integer.toString(rows) + " Zeilen zurückgegeben worden. Eine Zeile wird erwartet."));
			//throw new myException("LAG_BEW_StatusErmittlung::ErmittleLagerstatus: es sind " + Integer.toString(rows) + " Zeilen zurückgegeben worden. Eine Zeile wird erwartet.");
		}
		
		return oRet;
	}
	
	
	
	private void readLagerstatus(boolean bGroupAll){

		
		String sCols = "";
		String sWhere = "";
		String sGroupBy = "";
		
		String sWhereAdditionalBeiReorgLagerSorte = "";
		String sWhereAdditionalBeiReorgLagerSorte_Inventur = "";
		
		String sSelect = "";
		if (bGroupAll){
			
			// wird für den Aufbau der Statusliste benötigt
			sGroupBy += " JT_LAGER_KONTO.ID_ADRESSE_LAGER, JT_LAGER_KONTO.ID_ARTIKEL_SORTE ";
			sCols += ", JT_LAGER_KONTO.ID_ADRESSE_LAGER ,JT_LAGER_KONTO.ID_ARTIKEL_SORTE ";
			
		} else {
			// wird für die AdHoc-Abfrage benötigt. Dort ist immer zumindest eine Sorte oder Hauptsorte nötig
			if ( bibALL.isEmpty(m_IDHauptSorte) && bibALL.isEmpty(m_IDSorte) ){
				return;
			}

			// zusätzliche Spalten und Gruppierungen einblenden, wenn man einzelne Abfragen durchführt
			if (!bibALL.isEmpty(m_IDSorte) ){
				sWhere += " AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = " + m_IDSorte + " ";
				sGroupBy += ", JT_LAGER_KONTO.ID_ARTIKEL_SORTE ";
				sCols += ", JT_LAGER_KONTO.ID_ARTIKEL_SORTE ";
			} else if (!bibALL.isEmpty(m_IDHauptSorte) ){
					sWhere += " AND SUBSTR(A.ANR1,0,2) = '" + m_IDHauptSorte + "' ";
					sGroupBy += ", SUBSTR(A.ANR1,0,2) ";
					sCols += ", SUBSTR(A.ANR1,0,2) ";
			} 

			if (! bibALL.isEmpty(m_IDAdresseLager) ){
				sWhere += " AND ID_ADRESSE_LAGER = " + m_IDAdresseLager + " ";
				sCols += ", JT_LAGER_KONTO.ID_ADRESSE_LAGER ";
				sGroupBy += ", JT_LAGER_KONTO.ID_ADRESSE_LAGER ";
			}
			
			
			if (sGroupBy.length() > 0) {
				// 1. Zeichen (Komma) wegnehmen!
				sGroupBy = sGroupBy.substring(1);
			}
		}
		
		
		if (!bibALL.isEmpty(m_BuchungsdatumBis)){
			m_BuchungsdatumBis += " 23:59";
//			String sDatumBis_plus1 = m_BuchungsdatumBis;

			// aus Geschwindigkeitsgründen wird kein "Datum <= 29.01.68 23:59" gemacht sondern ein "Datum < 30.01.68 00:00" 
//			try {
//				Calendar cal = new GregorianCalendar();
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				Date dtBis = df.parse(m_BuchungsdatumBis);
//				cal.setTime(dtBis);
//				
//				// einen Tag drauf
//				cal.add(Calendar.DATE, 1);
//				sDatumBis_plus1 = df.format(cal.getTime()) + " 00:00";

				sWhereAdditionalBeiReorgLagerSorte_Inventur = " AND  to_char(LI.Buchungsdatum,'yyyy-MM-dd') || ' ' || LI.BUCHUNGSZEIT  <=  '" +  m_BuchungsdatumBis + "' " ;
				sWhereAdditionalBeiReorgLagerSorte 			= " AND  to_char(JT_LAGER_KONTO.Buchungsdatum,'yyyy-MM-dd') || ' ' || JT_LAGER_KONTO.BUCHUNGSZEIT <=  '"+ m_BuchungsdatumBis + "' " ;

				
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("LAG_BEW_StatusErmittlung::readLagerstatus(): Fehler bei der Ermittlung des Bis-Datums")));
//				
//			}
			
//			sWhereAdditionalBeiReorgLagerSorte_Inventur = "     AND  to_date( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' || LI.Buchungszeit, 'yyyy-mm-dd HH24:mi')  <=  to_date('"+ m_BuchungsdatumBis + "','yyyy-mm-dd HH24:mi') " ;
//			sWhereAdditionalBeiReorgLagerSorte = "     AND  to_date( to_char(JT_LAGER_KONTO.Buchungsdatum,'yyyy-mm-dd') || ' ' || JT_LAGER_KONTO.Buchungszeit, 'yyyy-mm-dd HH24:mi') <=  to_date('"+ m_BuchungsdatumBis + "','yyyy-mm-dd HH24:mi') " ;
			
			
		}
		

		
		// FIBU
		sSelect = "SELECT " +
				" SUM( JT_LAGER_KONTO.MENGE - nvl  ( ( " +
				" SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				" FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				" WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  ) as menge_gesamt, " +
				"" +
				"  sum(  CASE WHEN (JT_LAGER_KONTO.PREIS != 0  and JT_LAGER_KONTO.PREIS is not null )" +
				"           THEN " +
				"                   JT_LAGER_KONTO.MENGE - nvl  (        ( " +
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  " +
				"            else  0  end  " +
				") as menge_mit_preisen_ungleich_0, " +
				" " +
				" sum( CASE WHEN (JT_LAGER_KONTO.PREIS = 0 and JT_LAGER_KONTO.PREIS is not null)" +
				"            THEN " +
				"                    JT_LAGER_KONTO.MENGE - nvl  (        ( " +
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  " +
				"            ELSE 0 END ) as menge_nur_0_preise, " +
				" " +
				"sum( case WHEN JT_LAGER_KONTO.PREIS is null" +
				"            THEN " +
				"                    JT_LAGER_KONTO.MENGE - nvl  (        ( " +
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  " +
				"            else 0 end ) as menge_ohne_preise ," +
				"" +
				" sum((nvl(JT_LAGER_KONTO.MENGE,0) - nvl (( " +
				"        SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"        FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"        WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )) / A.MENGENDIVISOR * nvl(JT_LAGER_KONTO.PREIS,0)" +
				" ) as sum_restwert," +
				"" +
				"round(sum(" +
				"        (nvl(JT_LAGER_KONTO.MENGE,0) - nvl (( " +
				"        SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"        FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"        WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  ))  * nvl( JT_LAGER_KONTO.PREIS,0)" +
				") / sum( " +
				"        JT_LAGER_KONTO.MENGE - nvl  (        ( " +
				"        SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " +
				"        FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG   " +
				"        WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  ) " +
				"        ,2) as avg_restwert_gesamt," +
				" " +
				"   round( sum( case WHEN (JT_LAGER_KONTO.PREIS != 0  and JT_LAGER_KONTO.PREIS is not null )  " +  
				"            THEN  " + 
				"                   ( JT_LAGER_KONTO.MENGE - nvl  (        ( " + 
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG " + 
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  ) )   * JT_LAGER_KONTO.PREIS " + 
				"            else  " + 
				"                    0 " + 
				"            end " + 
				"       ) /  " + 
				"       sum( case WHEN (JT_LAGER_KONTO.PREIS != 0  and JT_LAGER_KONTO.PREIS is not null )  " + 
				"            THEN  " + 
				"                    nvl(JT_LAGER_KONTO.MENGE,0) - nvl  (        ( " + 
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG " + 
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  " + 
				"             else " + 
				"                   1  " + 
				"            end " + 
				"        ) ,2) as avg_wert_ohne_0_preise,  " + 
				"  " + 
				"   round( sum( case WHEN ( JT_LAGER_KONTO.PREIS is not null ) " + 
				"            THEN  " + 
				"                   ( JT_LAGER_KONTO.MENGE - nvl  (        ( " + 
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG " + 
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  ) )   * JT_LAGER_KONTO.PREIS " + 
				"            else  " + 
				"                    0 " + 
				"            end " + 
				"       ) /  " + 
				"       sum( case WHEN ( JT_LAGER_KONTO.PREIS is not null )  " + 
				"            THEN  " + 
				"                    JT_LAGER_KONTO.MENGE - nvl  (        (  " + 
				"                    SELECT SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
				"                    FROM   " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG " + 
				"                    WHERE  JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO  ) ,0  )  " + 
				"             else " + 
				"                   1  " + 
				"             end  " + 
				"         ) ,2) as avg_wert_mit_0_preise " + 
				sCols +
				" FROM " + 
				"      " + bibE2.cTO() + ".JT_LAGER_KONTO " + 
				"     INNER JOIN JT_ARTIKEL A ON JT_LAGER_KONTO.ID_MANDANT = A.ID_MANDANT AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = A.ID_ARTIKEL " + 
				"  " + 
				"  " + 
				" WHERE  " + 
				"    nvl( JT_LAGER_KONTO.STORNO ,'N') = 'N'  " + 
				"     AND JT_LAGER_KONTO.BUCHUNGSTYP = 'WE'  " + 
				"     AND nvl ( JT_LAGER_KONTO.IST_KOMPLETT,'N') = 'N' " + 
				"     and  " + 
				"     (  " + 
				"             JT_LAGER_KONTO.MENGE - nvl ( " + 
				"                 (  " + 
				"                 SELECT " + 
				"                  sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0))  " + 
				"              FROM  " + 
				"                    " + bibE2.cTO() + ".JT_LAGER_BEWEGUNG  " + 
				"               WHERE  " + 
				"                   JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO " + 
				"                  )  ,0  )  " + 
				"     )  > 0 " + 
				"  " +
				
//				"     AND  to_char(JT_LAGER_KONTO.Buchungsdatum,'yyyy-mm-dd') || ' ' || JT_LAGER_KONTO.Buchungszeit  >  " + 
//				"             (  " + 
//				"             SELECT " + 
//				"                 nvl(max( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' || LI.Buchungszeit ) ,'1900-01-01 00:00' )  " + 
//				"             FROM " + 
//				"                  " + bibE2.cTO() + ".JT_LAGER_INVENTUR LI " + 
//				"             WHERE  " + 
//				"                 LI.ID_ADRESSE_LAGER     = JT_LAGER_KONTO.ID_ADRESSE_LAGER  " + 
//				"                 AND LI.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE  " + 
//								  sWhereAdditionalBeiReorgLagerSorte_Inventur +
//				"  " + 
//				"             )  " + 

				sWhere +
				sWhereAdditionalBeiReorgLagerSorte +
				" GROUP BY " + sGroupBy ;  

				  
				
		
		String [][] sArrDaten = new String[0][0];
		sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect,(String)null);
		//fillDataInRow(sArrDaten, ENUM_Values.FIBU_FORDERUNG );
		m_vRowStatus = new Vector<LAG_BEW_DataRowStatus>();
		
		//Zeile(n) in die DB einfügen
		fillDataInRow(sArrDaten, bGroupAll);
		
	}
	
	
	
	/**
	 * Füllen der Daten in das Datenobjekt
	 * @param sArrDaten
	 * @param bGroupAll - wird verwendet, wenn alle Status-Daten aller Lager/Sorten auf einmal gelesen werden
	 * 					- wenn true, werden auch gleichzeitig negative 
	 */
	private void fillDataInRow(String[][] sArrDaten, boolean bGroupAll) {

		// wenn alle Lagerstati ermittelt werden, wird geprüft, welche Läger zum Zeitpunkt des ermittelns des Lagerstatus keinen Lagerbestand haben, dann wird der Lagersaldo in die Zeile eingetragen.
		Vector<String> vKeysFound = new Vector<String>();
		LAG_BEW_DataRowStatus oRow ;
		
		int j = 0;
		if (sArrDaten != null){
			// sonst den Vektor füllen
			
			for (int i = 0; i < sArrDaten.length; i++){
				
				oRow = new LAG_BEW_DataRowStatus();
				
				
				j = 0;
				oRow.setMenge_Gesamt (sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setMenge_Preise_nicht_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setMenge_Preise_nur_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setMenge_Preise_leer (sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setSumme_Restwert(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setAvg_Restwert_gesamt (sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setAvg_Restwert_Menge_Preise_nicht_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;
				oRow.setAvg_Restwert_Menge_Preise_auch_Null(sArrDaten[i][j] != null ? new BigDecimal(sArrDaten[i][j]) : BigDecimal.ZERO); j++;

				if (bGroupAll){
					// nur dann werden Adresse und Sorte in die Spalten aufgenommen
					oRow.setIdAdresseLager( (String) sArrDaten[i][j]); j++;
					oRow.setIdArtikelSorte((String) sArrDaten[i][j]); j++;
					
					// es wird festgehalten, welche Lager/Sorten-Kombi einen aktuellen Status erhalten hat
					vKeysFound.add(oRow.getIdAdresseLager() + "#" + oRow.getIdArtikelSorte());
					
				} else { 
					
					// zusätzliche Spalten und Gruppierungen einblenden, wenn man einzelne Abfragen durchführt
					if (!bibALL.isEmpty(m_IDSorte) ){
						oRow.setIdArtikelSorte((String) sArrDaten[i][j]); j++;
					} else if (!bibALL.isEmpty(m_IDHauptSorte) ){
						oRow.setIdArtikelHauptsorte( sArrDaten[i][j]); j++;
					} 
					if (!bibALL.isEmpty(m_IDAdresseLager)){
						oRow.setIdAdresseLager( sArrDaten[i][j]); j++;
					}
					
					
				}
				
				// Zeile dazufügen
				this.m_vRowStatus.add(oRow);
			}
		}
		
		// falls bGroupAll angegeben ist, dann prüfen, welche Daten keinen Lagerbestand mehr haben, um dann die negative Menge einzubuchen
		LAG_LagerSaldoErmittlung oLagersaldo = new LAG_LagerSaldoErmittlung();
		if(bGroupAll){
			try {
				// lesen der Saldodaten zum Zeitpunkt der Statusermittlung
				oLagersaldo.readLagerSaldoDaten(null, null, m_BuchungsdatumBis);
				HashMap<String, LAG_LagerSaldoDaten> hmSaldo = oLagersaldo.getFoundLagerSaldoDaten();
				
				for (String sKey :  hmSaldo.keySet()){
					if(!vKeysFound.contains(sKey)){
						// jetzt einen neuen Datensatz aufbauen mit dem Lager, der Sorte, und dem aktuellen Saldo (muss eigentlich negativ sein)
						LAG_LagerSaldoDaten o = hmSaldo.get(sKey);
						if (o != null){
							oRow = new LAG_BEW_DataRowStatus();
							oRow.setIdAdresseLager(o.get_ID_Lager());
							oRow.setIdArtikelSorte(o.get_ID_Sorte());
							oRow.setMenge_Gesamt(o.get_Saldo());
							oRow.setMenge_Preise_nicht_Null(BigDecimal.ZERO); 
							oRow.setMenge_Preise_nur_Null(BigDecimal.ZERO); 
							oRow.setMenge_Preise_leer (BigDecimal.ZERO); 
							oRow.setSumme_Restwert(BigDecimal.ZERO);
							oRow.setAvg_Restwert_gesamt (BigDecimal.ZERO);
							oRow.setAvg_Restwert_Menge_Preise_nicht_Null(BigDecimal.ZERO);
							oRow.setAvg_Restwert_Menge_Preise_auch_Null(BigDecimal.ZERO);
							
							this.m_vRowStatus.add(oRow);
						}
					}
				}
				
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (m_vRowStatus.size() == 0 &&  !bibALL.isEmpty(m_IDAdresseLager) && !bibALL.isEmpty(m_IDSorte) ){
			// 
			try {
				oLagersaldo.readLagerSaldoDaten(m_IDAdresseLager, m_IDSorte, m_BuchungsdatumBis);
				LAG_LagerSaldoDaten o = oLagersaldo.getData(m_IDAdresseLager, m_IDSorte);
				if (o != null ){
					oRow = new LAG_BEW_DataRowStatus();
					oRow.setIdAdresseLager(m_IDAdresseLager);
					oRow.setIdArtikelSorte(m_IDSorte);
					// falls kein Saldo gefunden wurde, schreiben wir 0 rein
					if (o.is_bIsEmpty()){
						oRow.setMenge_Gesamt(BigDecimal.ZERO);
					} else {
						oRow.setMenge_Gesamt(o.get_Saldo());
					}
					oRow.setMenge_Preise_nicht_Null(BigDecimal.ZERO); 
					oRow.setMenge_Preise_nur_Null(BigDecimal.ZERO); 
					oRow.setMenge_Preise_leer (BigDecimal.ZERO); 
					oRow.setSumme_Restwert(BigDecimal.ZERO);
					oRow.setAvg_Restwert_gesamt (BigDecimal.ZERO);
					oRow.setAvg_Restwert_Menge_Preise_nicht_Null(BigDecimal.ZERO);
					oRow.setAvg_Restwert_Menge_Preise_auch_Null(BigDecimal.ZERO);;
					this.m_vRowStatus.add(oRow);
				}
				
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	
	/**
	 * Schreiben der Daten in die DB
	 * @param bOverrideOldEntries  
	 * @param Buchungsdatum  / ISO-FORMAT yyyy-mm-dd
	 * @throws myException
	 */
	private void writeDataToDB(boolean bOverrideOldEntries, String Buchungsdatum) throws myException{

		// prüfen, ob in dem Tag schon einträge gemacht sind. Wenn ja, dann müssen diese Überschrieben werden,
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
		
		java.util.Iterator<LAG_BEW_DataRowStatus> vIter = m_vRowStatus.iterator();
		
		
		// jetzt die neuen Sätze schreiben
		while (vIter.hasNext()){
			LAG_BEW_DataRowStatus row = vIter.next();
			
			MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

			oSql.addSQL_Paar("ID_STATUS_LAGER", "SEQ_STATUS_LAGER.NEXTVAL", false);
			oSql.addSQL_Paar("ID_ADRESSE", row.getIdAdresseLager(),false);
			oSql.addSQL_Paar("ID_SORTE", row.getIdArtikelSorte(),false);
			oSql.addSQL_Paar("MENGE_GESAMT", row.getMenge_Gesamt().toPlainString(),false);
			oSql.addSQL_Paar("MENGE_PREISE_NICHT_NULL", row.getMenge_Preise_nicht_Null().toPlainString(),false);
			oSql.addSQL_Paar("MENGE_PREISE_NULL", row.getMenge_Preise_nur_Null().toPlainString(),false);
			oSql.addSQL_Paar("MENGE_PREISE_LEER", row.getMenge_Preise_leer().toPlainString(),false);
			oSql.addSQL_Paar("SUM_RESTWERT", row.getSumme_Restwert().toPlainString(),false);
			oSql.addSQL_Paar("AVG_WERT_GESAMT", row.getAvg_Restwert_gesamt().toPlainString(),false);
			oSql.addSQL_Paar("AVG_WERT_NICHT_NULL", row.getAvg_Restwert_Menge_Preise_nicht_Null().toPlainString(),false);
			oSql.addSQL_Paar("AVG_WERT_MIT_NULL", row.getAvg_Restwert_Menge_Preise_auch_Null().toPlainString(),false);
			
			oSql.addSQL_Paar("BUCHUNGSDATUM", "to_date( '" + Buchungsdatum +"','yyyy-mm-dd')" , false);
			oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		    
		    sSql = oSql.get_CompleteInsertString("JT_STATUS_LAGER", bibE2.cTO());

		    m_vSqlStatements.add( sSql);
		}
		
		this.executeSqlStatements(true);
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
		
		String sSql = "DELETE FROM " + bibE2.cTO()+".JT_STATUS_LAGER" +
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
		String idMandant = bibALL.get_ID_MANDANT();
		
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
	public boolean verbucheLagerKontoUndErstelleStatus(String idLager, String idSorte, String sBuchungsdatumISO){
		boolean bRet = false;
		String idMandant = bibALL.get_ID_MANDANT();
		
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
		
		return vTage;
	}
	
	/**
	 * Ermittelt die Tage, die neu aufgebaut werden müssen
	 * @param sDatumAb
	 * @return
	 */
	public Vector<String> getTageFuerReorg_NUR_BELEGTE_TAGE(String sDatumAb){
		Vector<String> vTage = new Vector<String>();
		
		String sSql = "SELECT DISTINCT  to_char(K.BUCHUNGSDATUM,'yyyy-MM-dd')  " +
					" FROM " + bibE2.cTO() + ".JT_LAGER_KONTO K " +
					" WHERE TRUNC(K.BUCHUNGSDATUM,'DD') >= to_date('" + sDatumAb + "','yyyy-MM-dd')" +
					" ORDER BY 1";
		
		String[][] datum = bibDB.EinzelAbfrageInArray(sSql);
		
		for (int i = 0; i< datum.length; i++){
			vTage.add(datum[i][0]);
		}
		
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
		
		
		
		/**
		 * Wenn id_lager und id_sorte nicht angegeben sind, werden alle Buchungen der Läger und Sorten bis zum 
		 * angegebenen Datum gelöscht!
		 */
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
		
		return bRet;
	}
	
	
	
	
	/**
	 * Aktualisiert den Lagerstatus eines Mandanten anhand der IST_GEAENDERT-Flags in den Lägern
	 * Es wird der Setztkasten neu aufgebaut und der Status neu geschrieben.
	 * @return  true bei Erfolg
	 */
	public boolean rebuildLagerstatus_AUTO(){
		boolean bRet = false;
		
		// finden der Läger/Sorten die neu aufgebaut werden müssen:
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
		
		// prüfen, ob alle Parameter vorhanden sind:
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
			
			
//			LAG_LagerBewegungHandler oBewHandler = new LAG_LagerBewegungHandler(null);
//			try {
//				oBewHandler.ReorganiseLagerEntries(idMandant);
//				m_TaskMessages.add(new MyString("Bewertungslauf des Lagers...OK").CTrans());
//			} catch (Exception e1) {
//				m_TaskMessages.add(new MyString("Fehler beim Bewertungslauf des Lagers.").CTrans());
//			}
//		
		
		// zum schluss noch den aktuellen Lagerstatus aufbauen, für alle möglichen Lager/Sorten-Kombinationen
		try {
			this.ErmittleLagerstatus(true);
			m_TaskMessages.add(new MyString("Aktuellen Lagerstatus ermitteln...OK.").CTrans());
			bRet = true;
		} catch (myException e) {
			m_TaskMessages.add(e.ErrorMessage);
		}
			
		
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
