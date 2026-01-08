package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_Saldo_UnitSummation;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_Saldo_UnitSummationEntry;

public class LAG_LagerSaldoErmittlung {


	
	private HashMap<String, LAG_LagerSaldoDaten> 	m_Lagerdaten = null;
	private ATOM_Saldo_UnitSummation				m_UnitSummation = null;
	
	public LAG_LagerSaldoErmittlung() {
		
	}


	/**
	 * Wandelt ein String in ein Date und zurück. Dann kann man sicher sein, dass der Timestamp richtig formatiert wurde
	 * Foramt: yyyy-MM-dd hh:mm
	 * @param sDateTimeIso
	 * @return
	 * @throws ParseException
	 */
	private String checkDateTime(String sDateTimeIso) throws ParseException{
		String sRet = "";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt = sdformat.parse(sDateTimeIso);
		
		sRet = sdformat.format(dt);
		return sRet;
	}
	
	
	/**
	 * Wandelt ein String in ein Date und zurück. Dann kann man sicher sein, dass der Timestamp richtig formatiert wurde
	 * Foramt: yyyy-MM-dd
	 * @param sDateTimeIso
	 * @return
	 * @throws ParseException
	 */
	private Date getDateTime(String sDateTimeIso) throws ParseException{
		Date dRet = null;
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		dRet = sdformat.parse(sDateTimeIso);
		return dRet;
	}
	
	
	
	public void readLagerSaldoDaten() throws myException{
		this.readLagerSaldoDaten(null, null, null, null, null, null);
	}
	
	
	public void readLagerSaldoDaten(String sIDLager, String sIDSorte, String sDatum) throws myException{
		this.readLagerSaldoDaten(sIDLager, null ,sIDSorte, null, sDatum, null);
	}
	
	/**
	 * Ermittelt für 
	 * @param sIDLager
	 * @param sIDSorte
	 * @param sDatum
	 * @param sZeit
	 * @throws myException
	 */
	public void readLagerSaldoDaten(String sIDLager,  String sIDSorte, String sDatum, String sZeit ) throws myException{
		this.readLagerSaldoDaten(sIDLager, null ,sIDSorte, null, sDatum, sZeit);
	}

	
	/**
	 * Standard-Methode bis zur Einführung der Möglichkeit den Sortenbereich zu selektieren 
	 * @author manfred
	 * @date 10.06.2021
	 *
	 * @param sIDLager
	 * @param sIDHauptsorte
	 * @param sIDSorte
	 * @param sIDEinheit
	 * @param sDatum
	 * @param sZeit
	 * @throws myException
	 */
	public void readLagerSaldoDaten(String sIDLager, String sIDHauptsorte, String sIDSorte, String sIDEinheit, String sDatum, String sZeit ) throws myException{
		this.readLagerSaldoDaten(sIDLager, sIDHauptsorte ,sIDSorte, sIDEinheit, sDatum, sZeit, null);
	}

	
	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Saldi eines bestimmten Datums,
	 * und berücksichtigt dabei die Inventurdaten der Tabelle JT_LAGER_INVENTUR.
	 * D.h. Falls es einen Inventureintrag gibt, dann wird der Saldo von dem Zeitpunkt der letzten Inventur 
	 * bis zum angegegebenen Zeitpunkt ermittelt
	 * 
	 *  sIDLager, sIDSorte und sDatum sind kann-Felder und dienen der Einschränkung der Ergebnisliste.
	 *  Sind alle null, werden alle Läger und alle Sorten ermittelt, ohne Datumseinschränkung
	 * 
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager, idSorte) gelesen werden 
	 * @param sIDLager  - das Lager
	 * @param sIDHauptsorte - die Hauptsorte (ersten 2 Stellen der ANR1)
	 * @param sIDSorte  - die Sorte
	 * @param sDatum	- Datum im Format yyyy-mm-dd (iso)
	 * @param sZeit		- Zeit im Format  HH24:mi (von 00:00 bis 23:59) 23:58 sind alle Handbuchungen, 23:59 sind die Lagerinventur-Einträge
	 * @param sANR1_VonBis - String Array [2] mit 2 ANR1-Werten (von / bis) inclusive
	 * @throws myException
	 */
	public void readLagerSaldoDaten(String sIDLager, String sIDHauptsorte, String sIDSorte, String sIDEinheit, String sDatum, String sZeit, ArrayList<String[]> sANR1_VonBis ) throws myException{

		String [][] cLagerDaten = null;	
		
		String sSqlWhere = "";
		String sSqlWhereSubselect = "";
		String sSqlWhereDate = "";
		
		if (sIDLager != null && sIDLager.trim().length() > 0) {
			sSqlWhere += " AND K.ID_ADRESSE_LAGER = " + sIDLager;
		}
		
		
		//
		// Sortenselektion:
		//
		// Eeinzelsorte zuerst
		if (sIDSorte != null && sIDSorte.trim().length() > 0) {
			sSqlWhere += " AND K.ID_ARTIKEL_SORTE = " + sIDSorte ;
		}
		// dann von-bis-Sorte
		else if (sANR1_VonBis != null && sANR1_VonBis.size() > 0 ) {
			// ANR1 von / bis
			String sAdd = " ";
			for(String[] werte : sANR1_VonBis) {
				
				String anr1_von = werte[0];
				String anr1_bis = werte[1];
				
				if(S.isAllFull(anr1_von, anr1_bis) ){
					sAdd += " OR ( ART.ANR1 >= '" + anr1_von.trim() + "' AND ART.ANR1 <= '" + anr1_bis.trim() + "' )"  ;
				}
			}
			sAdd = "AND ( 1 = 2 " +  sAdd + ") ";
			
			sSqlWhere += sAdd;
		}
		// dann Hauptsorte
		else if (sIDHauptsorte != null && sIDHauptsorte.trim().length() > 0) {
			sSqlWhere += " AND SUBSTR(ART.ANR1,0,2) = substr('" + "00" + sIDHauptsorte+ "',-2) " ;
		} 
		
		

		if (sIDEinheit != null && sIDEinheit.trim().length() > 0){
			sSqlWhere += " AND ART.ID_EINHEIT = " + sIDEinheit + "  ";
		}
		
		
		// Datum/Zeit gehört immer zusammen
		String sDT = "";
		if (sDatum != null && sDatum.trim().length() > 0) {
			if (sZeit != null && sZeit.trim().length() > 0 ){
				sDT = sDatum + " " + sZeit;
			} else {
				sDT = sDatum + " 23:59";
			}
			sSqlWhereSubselect = 	" AND to_char(LI.BUCHUNGSDATUM,'yyyy-mm-dd') || ' ' || LI.BUCHUNGSZEIT <= '" + sDT +  "'";
			sSqlWhereDate = 		" AND to_char(K1.BUCHUNGSDATUM,'yyyy-mm-dd') || ' ' || K1.BUCHUNGSZEIT <= '" + sDT +  "'";
		}

		
		String 
		sSqlMenge =
				"  NVL((  SELECT sum(K1.menge)   " +	
				"     FROM  "+bibE2.cTO()+".JT_LAGER_KONTO K1  " +	
				"     WHERE K1.ID_ADRESSE_LAGER = K.ID_ADRESSE_LAGER  " +	
				"          AND K1.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE " +	
				"          AND NVL(K1.STORNO,'N') = 'N' " +				
				 sSqlWhereDate + 
				" ),0) " ;	

				
		if (1==2) {
			sSqlMenge =
				"  NVL((  SELECT sum(K1.menge)   " +	
				"     FROM  "+bibE2.cTO()+".JT_LAGER_KONTO K1  " +	
				"     WHERE K1.ID_ADRESSE_LAGER = K.ID_ADRESSE_LAGER  " +	
				"          AND K1.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE " +	
				"          AND NVL(K1.STORNO,'N') = 'N' " +				
				"          AND to_char(K1.BUCHUNGSDATUM,'yyyy-MM-dd')  || ' ' || K1.BUCHUNGSZEIT  > " +
				"														( SELECT nvl(max( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' || LI.Buchungszeit ), '1900-01-01 00:00') " +	
				"                                                         FROM     "+bibE2.cTO()+".JT_LAGER_INVENTUR LI " +	
				"                                                         where LI.ID_ADRESSE_LAGER = K1.ID_ADRESSE_LAGER AND " +	
				"                                                               LI.ID_ARTIKEL_SORTE = K1.ID_ARTIKEL_SORTE " +	
																				sSqlWhereSubselect + 
				"                                   ) " +
					sSqlWhereDate + 
				" ),0) " ;	
		}
		
		
		
		
		String sSql = 
		" SELECT K.ID_ADRESSE_LAGER  , K.ID_ARTIKEL_SORTE , ART.ID_EINHEIT, " + 
		"     (   SELECT MENGE FROM  "+bibE2.cTO()+".JT_LAGER_INVENTUR INV  " +
		"     	WHERE INV.ID_ADRESSE_LAGER = K.ID_ADRESSE_LAGER  " +
		"         AND INV.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE " +
		"         and to_char(INV.Buchungsdatum,'yyyy-mm-dd') || ' ' || INV.Buchungszeit  =  " +
		"             (SELECT max( to_char(Buchungsdatum,'yyyy-mm-dd') || ' ' || Buchungszeit ) " +
		" 		    FROM   "+bibE2.cTO()+".JT_LAGER_INVENTUR LI " +
		" 		    WHERE LI.ID_ADRESSE_LAGER = INV.ID_ADRESSE_LAGER AND  LI.ID_ARTIKEL_SORTE = INV.ID_ARTIKEL_SORTE " +
					  sSqlWhereSubselect + 
		"             ) " +
		"       ) as INVENTURMENGE, " +
		"       to_char( ( SELECT to_date( to_char(INV.BUCHUNGSDATUM,'yyyy-mm-dd') || ' ' || INV.BUCHUNGSZEIT, 'yyyy-mm-dd HH24:mi') " +
		"         FROM  "+bibE2.cTO()+".JT_LAGER_INVENTUR INV  " +
		"         WHERE INV.ID_ADRESSE_LAGER     = K.ID_ADRESSE_LAGER AND " +
		"         	INV.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE " +
		"         	AND to_char(INV.Buchungsdatum,'yyyy-mm-dd') || ' ' || INV.Buchungszeit  =  " +
		"         	( " +
		" 		    SELECT max( to_char(Buchungsdatum,'yyyy-mm-dd') || ' ' || Buchungszeit ) " +
		" 		    FROM   "+bibE2.cTO()+".JT_LAGER_INVENTUR LI " +
		" 		    where LI.ID_ADRESSE_LAGER = INV.ID_ADRESSE_LAGER and LI.ID_ARTIKEL_SORTE = INV.ID_ARTIKEL_SORTE " +
					sSqlWhereSubselect + 
		" 		 ) " +
		" 	) ,'yyyy-mm-dd HH24:mi') as INVENTURDATUM,  " +
		sSqlMenge +
//		"  NVL((  SELECT sum(K1.menge)   " +	
//		"     FROM  "+bibE2.cTO()+".JT_LAGER_KONTO K1  " +	
//		"     WHERE K1.ID_ADRESSE_LAGER = K.ID_ADRESSE_LAGER  " +	
//		"          AND K1.ID_ARTIKEL_SORTE = K.ID_ARTIKEL_SORTE " +	
//		"          AND NVL(K1.STORNO,'N') = 'N' " +				
//		"          AND to_char(K1.BUCHUNGSDATUM,'yyyy-MM-dd')  || ' ' || K1.BUCHUNGSZEIT  > " +
//		"														( SELECT nvl(max( to_char(LI.Buchungsdatum,'yyyy-mm-dd') || ' ' || LI.Buchungszeit ), '1900-01-01 00:00') " +	
//		"                                                         FROM     "+bibE2.cTO()+".JT_LAGER_INVENTUR LI " +	
//		"                                                         where LI.ID_ADRESSE_LAGER = K1.ID_ADRESSE_LAGER AND " +	
//		"                                                               LI.ID_ARTIKEL_SORTE = K1.ID_ARTIKEL_SORTE " +	
//																		sSqlWhereSubselect + 
//		"                                   ) " +
//			sSqlWhereDate + 
//		" ),0) " +	
		" FROM  "+bibE2.cTO()+".JT_LAGER_KONTO K INNER JOIN JT_ARTIKEL ART ON K.ID_ARTIKEL_SORTE = ART.ID_ARTIKEL AND K.ID_MANDANT = ART.ID_MANDANT " +
		" WHERE NVL(K.STORNO,'N') = 'N' " +
			sSqlWhere + 
		" GROUP BY K.ID_ADRESSE_LAGER, K.ID_ARTIKEL_SORTE, ART.ID_EINHEIT " +
		"  " ;
		

		
		m_Lagerdaten = new HashMap<String, LAG_LagerSaldoDaten>();
		m_UnitSummation = new ATOM_Saldo_UnitSummation();
		ATOM_Saldo_UnitSummationEntry oUnitSum = null;
		
		cLagerDaten = new String[0][0];
		
		// daten lesen
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// Hashmap füllen
		for (int i = 0; i < cLagerDaten.length; i++){
			
			String 		Lager = 			cLagerDaten[i][0];
			String		Sorte =				cLagerDaten[i][1];
			String      IDEinheit = 		cLagerDaten[i][2];
			String 		Inventurmenge = 	cLagerDaten[i][3];
			String 		Inventurdatum = 	cLagerDaten[i][4];
			String 		Lagermenge = 		cLagerDaten[i][5];
			
			
			BigDecimal	dLagermenge = 		Lagermenge != null ? new BigDecimal(Lagermenge) : BigDecimal.ZERO ;
			BigDecimal  dInventurmenge = 	Inventurmenge != null ? new BigDecimal(Inventurmenge) : BigDecimal.ZERO ;
			
			Date dInventurDate = null;
			try {
				dInventurDate = Inventurdatum != null ? getDateTime(Inventurdatum) : null;
			} catch (ParseException e) {
				DEBUG.System_println("Datum kann nicht ermittelt werden: " + Inventurdatum, DEBUG.DEBUG_FLAG_DIVERS1);
			} 
				
			String 		sKey = Lager + "#" + Sorte;
			LAG_LagerSaldoDaten oValue = new LAG_LagerSaldoDaten(Lager, Sorte, IDEinheit, dLagermenge, dInventurmenge, dInventurDate);
			m_Lagerdaten.put(sKey, oValue);
			
			// die Einheitensummation gleich mit machen
			m_UnitSummation.add(IDEinheit,dLagermenge);
			
		}
		
		int i = 0;
	
	}


	/**
	 * Die Ergebnismenge wird gelöscht.
	 */
	public void ClearData(){
		m_Lagerdaten.clear();
		m_UnitSummation.clear();
	}
	
	
	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet wurden, kann 
	 * durch getData(..) die Information für eine Lager/Sorten-Kombination ermittelt werden. 
	 * @param idLager
	 * @param idSorte
	 * @return
	 */
	public LAG_LagerSaldoDaten getData(String idLager, String idSorte) {
		
		String sKey = idLager + "#" + idSorte;
		
		// auf jeden Fall ein Leeres Objekt zurückliefern
		LAG_LagerSaldoDaten oDaten = new LAG_LagerSaldoDaten();
		
		if (m_Lagerdaten != null && m_Lagerdaten.containsKey(sKey)){
			oDaten =  m_Lagerdaten.get(sKey);
		}
		
		return oDaten;
	}
	

	/**
	 * gibt alle gefundenen LAG_LagersaldoDaten-Objekte in einer HashMap zurück.
	 * Der Key wird definiert durch <idlager>#<idsorte>
	 * @return
	 */
	public HashMap<String, LAG_LagerSaldoDaten> getFoundLagerSaldoDaten(){
		return m_Lagerdaten;
	}
	
	
	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet wurden, kann 
	 * durch getSumOfAllSaldoValuesFound(..) die Gesamtsaldo-Menge aller gefundenen Einzelsaldi für
	 * die Gewählten Lager/Sorten-Kombinationen ermittelt werden.
	 * @return
	 */
	public BigDecimal getSumOfAllSaldoValuesFound(){
		BigDecimal bdSum = BigDecimal.ZERO;
		
		for (LAG_LagerSaldoDaten oSaldo : m_Lagerdaten.values()){
			bdSum = bdSum.add(oSaldo.get_Saldo());
		}
		
		return bdSum;
	}
	
	
	/**
	 * gibt die Objekte der Summationswerte zurück als hashmap unsortiert
	 * @return
	 */
	public HashMap<String, ATOM_Saldo_UnitSummationEntry> getSummationRowsOfAllSaldoValuesFound() {
		return m_UnitSummation.getEntries();
	}
	
}
