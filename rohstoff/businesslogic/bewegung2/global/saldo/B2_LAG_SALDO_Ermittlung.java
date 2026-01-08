package rohstoff.businesslogic.bewegung2.global.saldo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_LAG_SALDO_Ermittlung {

	private HashMap<String, B2_LAG_SALDO_SaldoDaten> 			m_Lagerdaten = null;
	private HashMap<String, B2_LAG_SALDO_NulldurchgangDaten> 	m_Inventurdaten = null;

	private B2_LAG_SALDO_UnitSummation  						m_UnitSummation = null;

	// Datum an dem das Saldo ermittelt wurde, wenn angegeben, sonst null
	private String										m_DatumSaldo = null;


	public enum ENUM_Lagertyp {EIGENWAREN,FREMDWAREN};

	/**
	 * Wandelt ein String in ein Date und zurück. Dann kann man sicher sein,
	 * dass der Timestamp richtig formatiert wurde Foramt: yyyy-MM-dd hh:mm
	 * 
	 * @param sDateTimeIso
	 * @return
	 * @throws ParseException
	 */
	/*private String checkDateTime(String sDateTimeIso) throws ParseException {
		String sRet = "";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt = sdformat.parse(sDateTimeIso);

		sRet = sdformat.format(dt);
		return sRet;
	}*/

	/**
	 * Wandelt ein String in ein Date und zurück. Dann kann man sicher sein,
	 * dass der Timestamp richtig formatiert wurde Foramt: yyyy-MM-dd
	 * 
	 * @param sDateTimeIso
	 * @return
	 * @throws ParseException
	 */
	private Date getDateTime(String sDateTimeIso) throws ParseException {
		Date dRet = null;

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdformat_time = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		// zuerst iso-Format des DAtums probieren
		try {
			dRet = sdformat.parse(sDateTimeIso);
		} catch (Exception e) {
			dRet = null;
		}
		// wenn das nicht geht das Iso-Format mit Zeit probieren. Wenn das nicht geht dann exception schmeissen 
		if (dRet == null){
			dRet = sdformat_time.parse(sDateTimeIso);
		}
		return dRet;
	}


	/**
	 * Gibt das Datum der Saldoermittlung zurück. null, falls kein Datum angegeben.
	 * 
	 * @author manfred
	 * @date 20.10.2016
	 *
	 * @return
	 */
	public String getSaldoDatum(){
		return m_DatumSaldo;
	}



	/**
	 * Ermittelt alle Lagerbestände ohne Datumsgrenze am Ende des Tages incl. Strecke, eigenwaren, fremdwaren
	 * 
	 * @throws myException
	 */
	public void readSaldoDaten() throws myException {
		this.readSaldoDaten(null,null, null,null,null, null,null, null, false,true,true,true);
	}


	/**
	 * Ermittelt alle Lagerbestände des Datums am Ende des Tages als reine Summe WE-WA
	 * @param sIDLager
	 * @param sIDSorte
	 * @param sDatum
	 * @throws myException
	 */
	public void readSaldoDaten(String sIDLager, String sIDSorte, String sDatum)
			throws myException {
		this.readSaldoDaten(sIDLager,null, null,null, sIDSorte,null,null, sDatum, false ,true,true,false);
	}


	/**
	 * Ermittlet alle Lagerbestände des Datums am Ende des Tages als reine Summe WE-WA
	 * Ohne Strecke
	 * @param sIDLager
	 * @param sHauptsorte
	 * @param sIDSorte
	 * @param sDatum
	 * @throws myException
	 *
	 * Autor:	 manfred
	 * Erstellt: 27.05.2014
	 *
	 */
	public void readSaldoDaten(String sIDLager, String sHauptsorte, String sIDSorte, String sDatum)
			throws myException {
		this.readSaldoDaten(sIDLager,null, sHauptsorte,null, sIDSorte,null,null, sDatum, false ,true,true,false);
	}


	/**
	 *  incl. Strecke
	 * 
	 * @param sIDLager
	 * @param sIDSorte
	 * @param sDatum
	 * @param sZeit
	 * @throws myException
	 */
	public void readSaldoDaten(String sIDLager, String sIDSorte, String sDatum,	Boolean bTagesbeginn , ENUM_Lagertyp enumLagertyp) throws myException {
			this.readSaldoDaten(sIDLager,null, null,null, sIDSorte,null, null, sDatum, bTagesbeginn,  enumLagertyp.equals(ENUM_Lagertyp.EIGENWAREN),enumLagertyp.equals(ENUM_Lagertyp.FREMDWAREN) ,true);
	}

	
	/**
	 * Ermittelt für ein Lager, eine Sorte an einem Datum den Lagerbestand, incl. der Nullstellen-Definitionen
	 * 
	 * @param sIDLager
	 * @param sIDSorte
	 * @param sDatum
	 * @param sZeit
	 * @throws myException
	 */
	public void readSaldoDaten(String sIDLager, String sIDSorte,String sIDEinheit, String sDatum, Boolean bTagesbeginn) throws myException {
		this.readSaldoDaten(sIDLager,null, null,null, sIDSorte,null, sIDEinheit, sDatum, bTagesbeginn, true, true,true);
	}

	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Saldi
	 * eines bestimmten Datums, und berücksichtigt dabei die Inventurdaten der
	 * Tabelle JT_LAGER_INVENTUR. D.h. Falls es einen Inventureintrag gibt, dann
	 * wird der Saldo von dem Zeitpunkt der letzten Inventur bis zum
	 * angegegebenen Zeitpunkt ermittelt
	 * 
	 * sIDLager, sIDSorte und sDatum sind kann-Felder und dienen der
	 * Einschränkung der Ergebnisliste. Sind alle null, werden alle Läger und
	 * alle Sorten ermittelt, ohne Datumseinschränkung
	 * 
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager,
	 * idSorte) gelesen werden
	 * 
	 * @param sIDLager
	 *            - das Lager
	 * @param sIDHauptsorte
	 *            - die Hauptsorte (ersten 2 Stellen der ANR1)
	 * @param sIDSorte
	 *            - die Sorte
	 * @param sDatum
	 *            - Datum im Format yyyy-mm-dd (iso)
	 * @param bTagesbeginn
	 *            - true, der Saldo wird für den Beginn des Tages ermittelt (Noch keine warenbewegungen an diesem Tag)
	 *            - false, der Saldo wird für das Ende des Tages ermittelt (Alle Warenbewegungen des Tages sind berücksichtigt)
	 *             
	 * @throws myException
	 */
	public void readSaldoDaten(
			String sIDLager, 
			String sIDHauptsorte,
			String sIDSorte, 
			String sIDEinheit,
			String sDatum, 
			Boolean bTagesbeginn, 
			Boolean bEigenwarenlager,
			Boolean bFremdwarenlager,
			Boolean bIncludeStrecke) throws myException {
		this.readSaldoDaten(sIDLager,null,sIDHauptsorte,null,sIDSorte,null,sIDEinheit, sDatum,bTagesbeginn,bEigenwarenlager,bFremdwarenlager,bIncludeStrecke);
	}

	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Saldi
	 * eines bestimmten Datums, und berücksichtigt dabei die Inventurdaten der
	 * Tabelle JT_LAGER_INVENTUR. D.h. Falls es einen Inventureintrag gibt, dann
	 * wird der Saldo von dem Zeitpunkt der letzten Inventur bis zum
	 * angegegebenen Zeitpunkt ermittelt
	 * 
	 * sIDLager, sIDSorte und sDatum sind kann-Felder und dienen der
	 * Einschränkung der Ergebnisliste. Sind alle null, werden alle Läger und
	 * alle Sorten ermittelt, ohne Datumseinschränkung
	 * 
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager,
	 * idSorte) gelesen werden
	 * 
	 * NB: Es werden die Inventurdaten mit gelesen.
	 * 
	 * @param sIDLager
	 *            - das Lager
	 * @param vIDLager
	 * 			  - Vektor von Lagern
	 * @param sIDHauptsorte
	 *            - die Hauptsorte (ersten 2 Stellen der ANR1)
	 * @param vIDHaupssorte
	 * 			  - Vektor von Hauptsorten-IDS
	 * @param sIDSorte
	 *            - die Sorte
	 * @param vIDSorte
	 * 			  -Vektor von SortenIDs           
	 * @param sDatum
	 *            - Datum im Format yyyy-mm-dd (iso)
	 * @param bTagesbeginn
	 *            - true, der Saldo wird für den Beginn des Tages ermittelt (Noch keine warenbewegungen an diesem Tag)
	 *            - false, der Saldo wird für das Ende des Tages ermittelt (Alle Warenbewegungen des Tages sind berücksichtigt)
	 *             
	 * @throws myException
	 */
	public void readSaldoDaten(
			String sIDLager, 
			Vector<String> vIDLager,
			String sIDHauptsorte,
			Vector<String> vIDHauptsorte,
			String sIDSorte, 
			Vector<String> vIDSorte,
			String sIDEinheit,
			String sDatum, 
			Boolean bTagesbeginn, 
			Boolean bEigenwarenlager,
			Boolean bFremdwarenlager,
			Boolean bIncludeStrecke) throws myException 
	{
		// Saldodaten mit Inventurdaten lesen
		readSaldoDaten(sIDLager,vIDLager,sIDHauptsorte,vIDHauptsorte,sIDSorte,vIDSorte,sIDEinheit,sDatum,bTagesbeginn,bEigenwarenlager,bFremdwarenlager,bIncludeStrecke,true);
	}



	/**
	 * Ermittelt für alle Läger und alle Sorten in einem Statement alle Saldi
	 * eines bestimmten Datums, und berücksichtigt dabei die Inventurdaten der
	 * Tabelle JT_LAGER_INVENTUR. D.h. Falls es einen Inventureintrag gibt, dann
	 * wird der Saldo von dem Zeitpunkt der letzten Inventur bis zum
	 * angegegebenen Zeitpunkt ermittelt
	 * 
	 * sIDLager, sIDSorte und sDatum sind kann-Felder und dienen der
	 * Einschränkung der Ergebnisliste. Sind alle null, werden alle Läger und
	 * alle Sorten ermittelt, ohne Datumseinschränkung
	 * 
	 * Die einzelnen Werte müssen dann durch die Methode GetData(idLager,
	 * idSorte) gelesen werden
	 * 
	 * @param sIDLager
	 *            - das Lager
	 * @param vIDLager
	 * 			  - Vektor von Lagern
	 * @param sIDHauptsorte
	 *            - die Hauptsorte (ersten 2 Stellen der ANR1)
	 * @param vIDHaupssorte
	 * 			  - Vektor von Hauptsorten-IDS
	 * @param sIDSorte
	 *            - die Sorte
	 * @param vIDSorte
	 * 			  -Vektor von SortenIDs           
	 * @param sDatum
	 *            - Datum im Format yyyy-mm-dd (iso)
	 * @param bTagesbeginn
	 *            - true, der Saldo wird für den Beginn des Tages ermittelt (Noch keine warenbewegungen an diesem Tag)
	 *            - false, der Saldo wird für das Ende des Tages ermittelt (Alle Warenbewegungen des Tages sind berücksichtigt)
	 * @apram bEigenwaren
	 * 			  - Besitzer der Ware ist der Mandant
	 * @param bFremdwaren
	 * 			  - Besitzer der Ware ist NICHT der Mandant
	 *@param bReadInventurdaten
	 *			  - true, die Inventurdaten werden ermittelt für die einzelnen Lager/Sorten-Kombinationen
	 *			  - false, die Inventurdaten werden genullt             
	 *             
	 * @throws myException
	 */
	public void readSaldoDaten(
			String sIDLager, 
			Vector<String> vIDLager,
			String sIDHauptsorte,
			Vector<String> vIDHauptsorte,
			String sIDSorte, 
			Vector<String> vIDSorte,
			String sIDEinheit,
			String sDatum, 
			Boolean bTagesbeginn, 
			Boolean bEigenwaren,
			Boolean bFremdwaren,
			Boolean bIncludeStrecke,
			Boolean bReadInventurdaten) throws myException {

		// übernehmen des Saldo-Datums		
		m_DatumSaldo = sDatum;

		String[][] cLagerDaten = null;

		String sSqlWhereLager = "";
		String sSqlWhereArtikel = "";
		String sSqlWhereArtikelHAUPT = "";

		String sSqlWhereDatum = "";

		String sSqlWhereEinheit = "";
		
		
		
		
		
		String sSqlBase = "SELECT " 
				+ "  V.ID_ADRESSE " 
				+ ", V.ID_ARTIKEL "
				+ ", ART.ID_EINHEIT "
				+ ", SUM(  CASE WHEN V.MENGENVORZEICHEN = 1  "
				+ "							THEN MENGENVORZEICHEN * NVL(V.MENGE_NETTO,nvl(V.MENGE,0)) "
				+ "                         ELSE MENGENVORZEICHEN * nvl(V.MENGE,0)  "
				+ "	       END ) AS MENGE"
				+ ", ART.ANR1 "
				+ " FROM  JT_BG_AUSWERT V "
				+"	INNER JOIN JT_ARTIKEL ART ON ART.ID_ARTIKEL = V.ID_ARTIKEL "
				+"	INNER JOIN JT_ADRESSE ADR ON ADR.ID_ADRESSE = V.ID_ADRESSE "
				+ " LEFT OUTER JOIN JT_LIEFERADRESSE LA ON LA.ID_ADRESSE_LIEFER  = ADR.ID_ADRESSE "
				
				+"  WHERE 1 = 1 "
				+"  AND V.ID_BG_DEL_INFO IS NULL "
				+"  AND V.ID_BG_STORNO_INFO IS NULL"
				+"  AND V.ID_ARTIKEL IS NOT NULL " 
				+"  AND  V.DATUM_AUSFUEHRUNG IS NOT NULL "
				+"  AND V.ID_ADRESSE IN " +
		 		"            ( " +
		 		"               SELECT VF.ID_ADRESSE FROM V_FIRMENADRESSEN_FLACH VF " +
		 		"                WHERE VF.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + " AND NVL( VF.SONDERLAGER,'STRECKE') = 'STRECKE' AND VF.ID_MANDANT =  " + bibALL.get_ID_MANDANT() +
		 		" 	         )											"
		 		;
				

		;
		
		
		
		String sSqlWhereLagerTyp = "  ";


		
		sSqlWhereLagerTyp = " 1 = 2 ";
		if (bEigenwaren) {
			sSqlWhereLagerTyp += " OR LA.ID_ADRESSE_FREMDWARE IS NULL ";
		}
		if (bFremdwaren) {
			sSqlWhereLagerTyp += " OR LA.ID_ADRESSE_FREMDWARE IS NOT NULL ";
		} 
		if (bIncludeStrecke) {
			sSqlWhereLagerTyp += " OR V.ID_ADRESSE = " + bibSES.get_ID_ADRESSE_LAGER_STRECKE() + "  ";
		}
		sSqlWhereLagerTyp = "AND ( " + sSqlWhereLagerTyp + " ) ";
		
		

		
		
		String sSqlStrecke = "";
		if (!bIncludeStrecke ) {
			sSqlStrecke = " AND V.ID_ADRESSE !=  " + bibSES.get_ID_ADRESSE_LAGER_STRECKE() ;
		}
		
		
		

		// LAGER
		if (sIDLager != null && sIDLager.trim().length() > 0) {
			sSqlWhereLager 				= " AND V.ID_ADRESSE = " + sIDLager +" ";
		}  else if (vIDLager != null && vIDLager.size() > 0){
			if (vIDLager.size() == 1){
				sSqlWhereLager 				= " AND V.ID_ADRESSE = " + vIDLager.get(0) +" ";
			} else {
				sSqlWhereLager 				= " AND V.ID_ADRESSE in ( " + bibALL.Concatenate(vIDLager, ",","-1") + " ) ";
			}
		}


		// SORTE: Vorrangbehandlung:
		// 1. ID alleine
		// 2. ID-Vektor
		// 3. HAUPTSORTE alleine
		// 4. HAUPTSORTE Vektor
		if (sIDSorte != null && sIDSorte.trim().length() > 0) {
			sSqlWhereArtikel 			= " AND V.ID_ARTIKEL = " + sIDSorte.trim();

		} else if (vIDSorte != null && vIDSorte.size() > 0){
			if (vIDSorte.size()==1){
				sSqlWhereArtikel 			= " AND V.ID_ARTIKEL =  " + vIDSorte.get(0).trim();

			} else {
				sSqlWhereArtikel 			= " AND V.ID_ARTIKEL in (" + bibALL.Concatenate(vIDSorte, ",", "-1" )  + ")";
			}

		} else if (sIDHauptsorte != null && sIDHauptsorte.trim().length() > 0) {
			sSqlWhereArtikelHAUPT = " AND V.ID_ARTIKEL IN(  SELECT ID_ARTIKEL FROM JT_ARTIKEL WHERE SUBSTR(JT_ARTIKEL.ANR1 ,0 ,2) IN( SUBSTR(LPAD("+ sIDHauptsorte.trim() + ",2,'0') ,-2) ) )";
			
		} else if (vIDHauptsorte != null && vIDHauptsorte.size()> 0){
			if (vIDHauptsorte.size()==1){
				sSqlWhereArtikelHAUPT = " AND V.ID_ARTIKEL IN(  SELECT ID_ARTIKEL FROM JT_ARTIKEL WHERE SUBSTR(JT_ARTIKEL.ANR1 ,0 ,2) IN( SUBSTR(LPAD("+ vIDHauptsorte.get(0).trim() + ",2,'0') ,-2) ) )";
			} else {
				Vector<String> vTemp = new Vector<String>();
				for (String s: vIDHauptsorte){
					
					vTemp.add(" SUBSTR(LPAD(" + s.trim() + ",2,'0') ,-2) ");
				}
				sSqlWhereArtikelHAUPT =  "AND V.ID_ARTIKEL IN(  SELECT ID_ARTIKEL FROM JT_ARTIKEL WHERE SUBSTR(JT_ARTIKEL.ANR1 ,0 ,2) IN( " + bibALL.Concatenate(vTemp, ",", "'--'" ) + ") ) " ;
				
			}
		}



		// Datum/Zeit gehört immer zusammen
		String sDT = "";
		if (sDatum != null && sDatum.trim().length() > 0) {

			sDT = " trunc(to_date('" + sDatum + "','yyyy-mm-dd'),'DD') ";
			if (bTagesbeginn){
				// Saldo Morgens
				sSqlWhereDatum =  " and trunc(V.DATUM_AUSFUEHRUNG,'DD') < " +  sDT + " ";
			} else {
				sSqlWhereDatum =  " and trunc(V.DATUM_AUSFUEHRUNG,'DD') <= " +  sDT + " ";
			}
		}

		if (!bibALL.isEmpty(sIDEinheit )){
			sSqlWhereEinheit = " AND ART.ID_EINHEIT = " + sIDEinheit;
		}

		// Inventurdatenfüllen
		readInventurdaten(sDatum, bTagesbeginn);
		
		
		String sSql2 =  sSqlBase  
				+sSqlWhereArtikel
				+sSqlWhereLager
				+sSqlWhereArtikelHAUPT
				+sSqlWhereEinheit
				+sSqlWhereDatum
				+sSqlWhereLagerTyp
				+sSqlStrecke
				+" GROUP BY V.ID_ADRESSE, V.ID_ARTIKEL ,ART.ID_EINHEIT, ART.ANR1"
				;
		

		m_Lagerdaten = new HashMap<String, B2_LAG_SALDO_SaldoDaten>();
		m_UnitSummation = new B2_LAG_SALDO_UnitSummation();

		cLagerDaten = new String[0][0];

		// daten lesen
		cLagerDaten = bibDB.EinzelAbfrageInArray(sSql2, (String) null);

		int iCol = 0;
		// Hashmap füllen
		for (int i = 0; i < cLagerDaten.length; i++) {
			iCol = 0;
			String Lager = cLagerDaten[i][iCol++];
			String Sorte = cLagerDaten[i][iCol++];
			String IDEinheit = cLagerDaten[i][iCol++];
			String Lagermenge = cLagerDaten[i][iCol++];

			String sKey = Lager + "#" + Sorte;

			
			BigDecimal dLagermenge ;
			try {
				dLagermenge	= Lagermenge != null ? new BigDecimal(Lagermenge) : BigDecimal.ZERO;
			} catch (Exception e) {
				// TODO: handle exception
				dLagermenge = BigDecimal.ZERO;
			}

			BigDecimal dInventurmenge = null;

			Date dInventurDate = null;

			if (m_Inventurdaten.containsKey(sKey)){
				B2_LAG_SALDO_NulldurchgangDaten oInventurdaten = m_Inventurdaten.get(sKey);
				dInventurmenge = oInventurdaten.get_Inventurmenge();
				dInventurDate  = oInventurdaten.get_InventurDatum();
			}

			B2_LAG_SALDO_SaldoDaten oValue = new B2_LAG_SALDO_SaldoDaten(Lager, Sorte, IDEinheit, dLagermenge, dInventurmenge, dInventurDate);
			m_Lagerdaten.put(sKey, oValue);

			// Einheitenbezogene Summation
			m_UnitSummation.add(IDEinheit, dLagermenge);

		}
		m_Lagerdaten.size();
	}





	/**
	 * Gibt zurück, ob die Liste schon aktualisiert wurde
	 * @author manfred
	 * @date   25.06.2013
	 * @return
	 */
	public boolean IsInitialized(){
		return m_Lagerdaten != null && m_Lagerdaten.size() > 0;
	}


	/**
	 * Die Ergebnismenge wird gelöscht.
	 */
	public void ClearData() {
		if (m_Lagerdaten != null) {
			m_Lagerdaten.clear();
		}
		if (m_UnitSummation != null){
			m_UnitSummation.clear();
		}
		if (m_Inventurdaten != null ) {
			m_Inventurdaten.clear();
		}
		m_DatumSaldo = null;
	}


	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet
	 * wurden, kann durch getData(..) die Information für eine
	 * Lager/Sorten-Kombination ermittelt werden.
	 * 
	 * @param idLager
	 * @param idSorte
	 * @return
	 */
	public B2_LAG_SALDO_SaldoDaten getData(String idLager, String idSorte) {

		String sKey = idLager + "#" + idSorte;

		// auf jeden Fall ein Leeres Objekt zurückliefern
		B2_LAG_SALDO_SaldoDaten oDaten = new B2_LAG_SALDO_SaldoDaten();

		if (m_Lagerdaten != null && m_Lagerdaten.containsKey(sKey)) {
			oDaten = m_Lagerdaten.get(sKey);
		}

		return oDaten;
	}

	/**
	 * gibt alle gefundenen LAG_LagersaldoDaten-Objekte in einer HashMap zurück.
	 * Der Key wird definiert durch <idlager>#<idsorte>
	 * 
	 * @return
	 */
	public HashMap<String, B2_LAG_SALDO_SaldoDaten> getListOfSaldoDaten() {
		return m_Lagerdaten;
	}

	/**
	 * Nachdem die Daten durch die Methode ReadVertragsLagerDaten() vorbereitet
	 * wurden, kann durch getSumOfAllSaldoValuesFound(..) die Gesamtsaldo-Menge
	 * aller gefundenen Einzelsaldi für die Gewählten Lager/Sorten-Kombinationen
	 * ermittelt werden.
	 * 
	 * @return
	 */
	public BigDecimal getSumOfAllSaldoValuesFound() {
		BigDecimal bdSum = BigDecimal.ZERO;

		for (B2_LAG_SALDO_SaldoDaten oSaldo : m_Lagerdaten.values()) {
			bdSum = bdSum.add(oSaldo.get_Saldo());
		}

		return bdSum;
	}



	/**
	 * gibt die Objekte der Summationswerte zurück als hashmap unsortiert
	 * @return
	 */
	public HashMap<String, B2_LAG_SALDO_UnitSummationEntry> getSummationRowsOfAllSaldoValuesFound() {
		return m_UnitSummation.getEntries();
	}



	/**
	 * liest die Nulldurchgänge der Lager des Mandanten 
	 * 
	 * Ausgelagert, das das einzeln-Lesen deutlich schneller geht als das Lesen im SQL-Statement des Bestands
	 * 
	 * @author manfred
	 * @date   14.09.2015
	 *
	 * @param sDatum   Datumsgrenze des Inveturdatums. Es wird die Inventur vor dem angegebenen Datum ermittelt
	 */
	private void readInventurdaten(String sDatum, boolean bTagesbeginn ){
		String sSqlWhereDatumLI = "";

		if (sDatum != null && sDatum.trim().length() > 0) {

			String sDT = " to_date('" + sDatum + "','yyyy-mm-dd')";
			if (bTagesbeginn){
				// Saldo Morgens
				sSqlWhereDatumLI = " AND LI2.BUCHUNGSDATUM < " +  sDT + " ";
			} else {
				// Saldo Abends
				sSqlWhereDatumLI = " AND LI2.BUCHUNGSDATUM < (" +  sDT + " + 1 ) ";
			}
		}
		String	sSqlSelectInventurdaten = ""	 
				+  "                SELECT LI4.ID_ADRESSE_LAGER as LI4_ID_ADRESSE_LAGER, "
				+  "                                LI4.ID_ARTIKEL_SORTE as LI4_ID_ARTIKEL_SORTE, "
				+  "                                ( TO_DATE( TO_CHAR(LI4.BUCHUNGSDATUM,'YYYY-MM-DD') || ' ' || LI4.BUCHUNGSZEIT, 'YYYY-MM-DD HH24:MI') ) as LI_DATUM ,"
				+  "                                LI4.MENGE as LI_MENGE "
				+  "                                FROM   " + bibE2.cTO()+  ".JT_LAGER_INVENTUR LI4 "
				+  "                                WHERE LI4.ID_MANDANT = " +  bibALL.get_ID_MANDANT() + " AND LI4.ID_LAGER_INVENTUR = "               
				+  "                                        ( "
				+  "                                        SELECT LI1.ID_LAGER_INVENTUR "
				+  "                                                from   " + bibE2.cTO()+  ".JT_LAGER_INVENTUR LI1 " 
				+  "                                                WHERE  LI1.ID_MANDANT = " + bibALL.get_ID_MANDANT()
				+  " 												AND LI1.ID_ADRESSE_LAGER =  LI4.ID_ADRESSE_LAGER " 
				+  "                                                AND LI1.ID_ARTIKEL_SORTE =  LI4.ID_ARTIKEL_SORTE " 
				+  "                                                AND (TO_DATE( TO_CHAR(LI1.BUCHUNGSDATUM,'YYYY-MM-DD') || ' ' || LI1.BUCHUNGSZEIT, 'YYYY-MM-DD HH24:MI' )) = " 
				+  "                                                ( " 
				+  "                                                        select max(TO_DATE( TO_CHAR(LI2.BUCHUNGSDATUM,'YYYY-MM-DD') || ' ' || LI2.BUCHUNGSZEIT, 'YYYY-MM-DD HH24:MI' )) "
				+  "                                                        FROM   " + bibE2.cTO()+  ".JT_LAGER_INVENTUR LI2 " 
				+  "                                                        WHERE  LI2.ID_ADRESSE_LAGER =  LI1.ID_ADRESSE_LAGER AND LI2.ID_MANDANT = " + bibALL.get_ID_MANDANT()  
				+  "                                                        AND LI2.ID_ARTIKEL_SORTE =  LI1.ID_ARTIKEL_SORTE "
				+ 															sSqlWhereDatumLI
				+  "                                                ) " 
				+  " "                                                
				+  "                                        ) " 
				;


		m_Inventurdaten = new HashMap<String, B2_LAG_SALDO_NulldurchgangDaten>();

		String [][] cInventurDaten = new String[0][0];

		// daten lesen
		cInventurDaten = bibDB.EinzelAbfrageInArray(sSqlSelectInventurdaten, (String) null);

		int iCol = 0;
		// Hashmap füllen
		for (int i = 0; i < cInventurDaten.length; i++) {
			iCol = 0;
			String Lager = cInventurDaten[i][iCol++];
			String Sorte = cInventurDaten[i][iCol++];
			String Inventurdatum = cInventurDaten[i][iCol++];
//			String Inventurmenge = cInventurDaten[i][iCol++];


//			BigDecimal dInventurmenge = Inventurmenge != null ? new BigDecimal(	Inventurmenge) : BigDecimal.ZERO;

			Date dInventurDate = null;
			try {
				dInventurDate = (Inventurdatum != null) ? getDateTime(Inventurdatum) 	: null;
			} catch (ParseException e) {
				DEBUG.System_println("Datum kann nicht ermittelt werden: "
						+ Inventurdatum, DEBUG.DEBUG_FLAG_DIVERS1);
			}


			//TODO: test 
			B2_LAG_SALDO_NulldurchgangDaten oValue = new B2_LAG_SALDO_NulldurchgangDaten(Lager, Sorte, BigDecimal.ZERO, dInventurDate);
			m_Inventurdaten.put(oValue.getKey(), oValue);

		}

	}


}
