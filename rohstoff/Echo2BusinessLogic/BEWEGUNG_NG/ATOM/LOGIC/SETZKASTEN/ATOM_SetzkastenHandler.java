/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_INVENTUR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum Verarbeiten der LagerKonto-Daten in den "Setzkasten", 
 * d.h. die Zuordnung von Lager-Ausgang zu Lager-Eingangs-Mengen und Preisen
 * 
 * @author manfred
 *
 */
public class ATOM_SetzkastenHandler {

	
	private int    			iCount = 0;
	private MyE2_Column   	colLoopInfo = null;
	
	private int				m_iIntervalOfDays = 7;
	
	protected String m_sqlVorzeichen = "";
	protected String m_sqlOrder = "";
	protected String m_sqlMenge = "";
	protected String m_sqlPreis = "";
	protected String m_sqlJoinSetzkasten = "";
	protected String m_sqlTableNameAtomVerbucht = "";
	protected String m_sqlTablenameAtomSetzkasten = "";
	protected String m_sqlPrimaryKeyColAtomSetzkasten = "";
	
	
	
	protected enum EnumBuchungstyp { WE,	WA }; 
	
	
	
	
	/**
	 * @param IdMandant
	 */
	public ATOM_SetzkastenHandler(MyE2_Column   	ColLoopInfo ) {
		
		this.colLoopInfo = ColLoopInfo;
		
		// Tabellennamen setzen
		setQuerysettingsBase();
		
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date   28.01.2014
	 */
	protected void setQuerysettingsBase(){
		m_sqlTablenameAtomSetzkasten	= "JT_BEWEGUNG_SETZKASTEN";
		m_sqlPrimaryKeyColAtomSetzkasten = "ID_BEWEGUNG_SETZKASTEN";
		m_sqlTableNameAtomVerbucht 		= "JT_BEWEGUNG_ATOM_VERBUCHT";
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   27.01.2014
	 * @param bt
	 */
	protected void setQuerysettingsForLagerdaten(EnumBuchungstyp bt) {
		if (bt == EnumBuchungstyp.WA){
			m_sqlVorzeichen = " -1 ";
			m_sqlOrder = " ORDER BY 4,2 ";
			m_sqlMenge = "nvl(A.MENGE,0)";
			m_sqlPreis = "(A.E_PREIS_RESULT_BRUTTO_MGE)";
			m_sqlJoinSetzkasten = "SK.ID_ATOM_AUSGANG";
		} else {
			m_sqlVorzeichen = " +1 ";
			m_sqlOrder = " ORDER BY 2,4 ";
			m_sqlMenge = "nvl(A.MENGE_NETTO,0)";
			m_sqlPreis = "(A.E_PREIS_RESULT_NETTO_MGE)";
			m_sqlJoinSetzkasten = "SK.ID_ATOM_EINGANG";
		}
	}

	
	
	/**
	 * Alle Lagereinträge im Lager werden automatisch reorganisiert
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @return
	 */
//	public void ReorganiseLagerEntries()	{
//		
//		RECLIST_MANDANT lMandant = null;
//		try {
//			 lMandant = new RECLIST_MANDANT (
//				       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
//		} catch (myException e) {
//			// TODO Auto-generated catch block
//			lMandant = null;
//		}
//		
//		// jetzt für alle Mandanten die Lager organisieren
//		String idMandant = "";
//		for(RECORD_MANDANT oMandant: lMandant.values()){
//			try {
//				idMandant = oMandant.get_ID_MANDANT_cUF();
//				ReorganiseLagerEntries(idMandant);
//			} catch (myException e) {
//				// TODO Auto-generated catch block
//				continue;
//			}
//		}
//	}
//	
//		
	/**
	 * Nur die Lager des Mandanten werden automatisch reorganisiert zum aktuellen Datum
	 * Die Setzkasteneinträge werden gereinigt
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @return
	 */
	public void ReorganiseLagerEntries(){
		ReorganiseLagerEntries(true);
	}
	
	
	/**
	 * Nur die Lager des Mandanten werden automatisch reorganisiert zum aktuellen Datum
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @return
	 */
	public void ReorganiseLagerEntries(boolean bClearSK){
		// wenn alles reorganisiert werden soll, dann immer den Setzkastenlog aufräumen
		if (bClearSK){
			cleanSetzkasten(null, null);
		}
		
		
		String sDatum = myDateHelper.ChangeNormalString2DBFormatString(bibALL.get_cDateNOW()) ;
		try {
			ReorganiseLagerEntries(/*idMandant,*/null,sDatum,false);
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	/**
	 * Ermittelt die Lager-Artikel-Kombinationen, die für den Mandanten zu dem Zeitpunkt
	 * verarbeitet werden müssen
	 * @author manfred
	 * @date   17.01.2014
	 * @param idMandant
	 * @param sDateISO
	 * @throws myException 
	 */
	public void ReorganiseLagerEntries(/*String idMandant, */ String sDateISO, boolean bClearSK) throws myException{
		this.ReorganiseLagerEntries(/*idMandant,*/ null, sDateISO, bClearSK);
	}

	

	/**
	 * Ermitteltdie Artikel die für ein Lager zum dem Zeitpunkt verarbeitet werden müssen
	 * @author manfred
	 * @date   12.12.2012
	 * @param idMandant
	 * @param sDateISO
	 * @throws myException
	 */
	public void ReorganiseLagerEntries(/*String idMandant ,*/String IdAdresseLager, String sDateISO, boolean bClearSK) throws myException{
		
		if (bClearSK){
			cleanSetzkasten(IdAdresseLager, null);
		}
		
		String [][] asIds = new String[0][0];
		String sSqlAdresse = "";
		if (!bibALL.isEmpty(IdAdresseLager)){
			sSqlAdresse = " AND  S.ID_ADRESSE = " + IdAdresseLager + " ";
		}
		
		
		// nur die Sätze raussuchen, bei denen in Lager/Sorte mindestens 1 Eingung und mindestens 1 Ausgang vorhanden ist, sonst macht es keinen Sinn zu versetzen
		// und die Mengen müssen auch noch vorhanden sein
		String sSql = 
		" SELECT   " +
		" S.ID_ADRESSE, " +
		" A.ID_ARTIKEL,  " +
		" to_char( min (A.LEISTUNGSDATUM),'yyyy-mm-dd' ) " +
		" FROM  "+ bibE2.cTO()+".JT_BEWEGUNG_ATOM A " +
		" INNER JOIN "+ bibE2.cTO()+".JT_BEWEGUNG_STATION S ON A.ID_BEWEGUNG_ATOM = S.ID_BEWEGUNG_ATOM " +
		" INNER JOIN "+ bibE2.cTO()+".JT_ARTIKEL ART ON ART.ID_ARTIKEL = A.ID_ARTIKEL " +
		" INNER JOIN  " +
		" 	( " +
		" 		SELECT LA.ID_ADRESSE_LIEFER as ID_ADRESSE_LAGER,  A.SONDERLAGER as ADR_SONDERLAGER, LA.ID_ADRESSE_FREMDWARE as ADR_ID_ADRESSE_FREMDWARE " +
		"		FROM  "+ bibE2.cTO()+".JT_LIEFERADRESSE LA " +
		" 		LEFT OUTER JOIN  "+ bibE2.cTO()+".JT_ADRESSE A ON A.ID_ADRESSE = LA.ID_ADRESSE_LIEFER " +
		" 		WHERE LA.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + 
		" 		UNION " +
		"  	 	SELECT " + bibALL.get_ID_ADRESS_MANDANT() + ",null,null FROM DUAL " +
		"  	) ADR " +
		"   ON S.ID_ADRESSE = ADR.ID_ADRESSE_LAGER AND ADR_SONDERLAGER IS NULL	" +
		" LEFT OUTER JOIN  "+ bibE2.cTO()+"." + m_sqlTableNameAtomVerbucht + " AV ON AV.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM  " +
		" WHERE nvl(A.STORNIERT,'N') 	= 'N' " +
		" AND   nvl(A.DELETED,'N') 		= 'N' " +
		// ein Atom braucht eine Menge, sonst nicht versetzen...
		" AND 	NVL(A.MENGE,0) != 0			  " +
		" AND   AV.ID_BEWEGUNG_ATOM  IS NULL  " +
		" AND   A.LEISTUNGSDATUM <= to_date(' " +sDateISO + "','yyyy-MM-dd')" +
		sSqlAdresse + 
		" GROUP BY " +
		" S.ID_ADRESSE, " +
		" A.ID_ARTIKEL  " +
		// Datensätze zählen, dass mindestens 1 WE und mindestens 1 WA Satz vorhanden sind
		" HAVING " +
		" ( sum( case when S.MENGENVORZEICHEN = 1 then 1 else 0 end) > 0) " +  
        " 	AND " +
        " ( sum( case when S.MENGENVORZEICHEN = -1 then 1 else 0 end) > 0) " +
		"" +
		" ORDER BY 1,2 ";

		
		asIds = bibDB.EinzelAbfrageInArray(sSql); 
		
		for (int i=0;i<asIds.length;i++)
		{
			ReorganiseLagerEntries(/*idMandant,*/ asIds[i][0],asIds[i][1],sDateISO, asIds[i][2],false );
		}
		
	}

	
	
	/**
	 * Es wird ein bestimmter Artikel in einem Lager Reorganisiert
	 * Author: manfred
	 * 26.03.2009

	 * @param idMandant
	 * @param IdAdresseLager
	 * @param IdArtikel
	 * @param sDatumBisISO
	 * @param sDatum_BeginnZeitscheiben
	 * @throws myException
	 */
	public void ReorganiseLagerEntries(/*String idMandant,*/ String IdAdresseLager, String IdArtikel, String sDatumBisISO, String sDatum_BeginnZeitscheiben) throws myException{
		ReorganiseLagerEntries(/*idMandant,*/ IdAdresseLager, IdArtikel, sDatumBisISO, sDatum_BeginnZeitscheiben, true);
	}

	
	/**
	 * Es wird ein bestimmter Artikel in einem Lager Reorganisiert
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @param IdAdresseLager
	 * @param IdArtikel
	 * @param DatumBis  - Datum bis zu dem die Verbuchung stattfinden soll im ISO-Format yyyy-MM-dd
	 * @param sDatum_BeginnZeitscheiben - Datum ab welchem die Zeitscheiben für die Verarbeitung beginnen sollen. Wird benötigt, um eine bessere granulierung der Verbuchung zu bekommen 
	 * 		   im ISO-Format yyyy-MM-dd
	 * @param bClearSK true: der setzkasten wird gelöscht
	 * @return
	 * @throws myException 
	 */
	public void ReorganiseLagerEntries(/*String idMandant,*/ String IdAdresseLager, String IdArtikel, String sDatumBisISO, String sDatum_BeginnZeitscheiben, boolean bClearSK) throws myException{
		
		// falls das log gelöscht werden soll, dann löschen
		if (bClearSK){
			this.cleanSetzkasten(IdAdresseLager, IdArtikel);
		}
		
		
		ArrayList<String> alDatumsgrenzen = new ArrayList<String>();
		
		Vector<ATOM_LagerDaten> vLagerEingangAll = new Vector<ATOM_LagerDaten>();
		Vector<ATOM_LagerDaten> vLagerAusgangAll = new Vector<ATOM_LagerDaten>();
		
		String sDatum = null;
		sDatum = sDatumBisISO + " 23:59";
		
		// alle einzelnen Lagerinventur-Abschnitte ermitteln
		RECLIST_LAGER_INVENTUR listLagerInventur = new RECLIST_LAGER_INVENTUR("SELECT * FROM " + bibE2.cTO()  + ".JT_LAGER_INVENTUR WHERE " + /*ID_MANDANT = " + idMandant +*/
		" ID_ADRESSE_LAGER = " + IdAdresseLager + " AND ID_ARTIKEL_SORTE = " + IdArtikel + 
				" AND  to_date( to_char(BUCHUNGSDATUM,'yyyy-mm-dd') || ' ' || BUCHUNGSZEIT, 'yyyy-mm-dd HH24:mi')  <= to_date('" + sDatumBisISO + "', 'yyyy-MM-dd HH24:mi') " +
				" ORDER BY BUCHUNGSDATUM " );
		
		// für jeden Abschnitt die Verbuchung durchführen
		for (int i=0; i <listLagerInventur.size(); i++){
			sDatum = listLagerInventur.get(i).get_BUCHUNGSDATUM_cUF() + " " + listLagerInventur.get(i).get_BUCHUNGSZEIT_cUF_NN("23:59");
			alDatumsgrenzen.add(sDatum);
		}
		
		
		// jetzt noch, falls ein Beginndatum für die Zeitscheiben gegeben ist, die Zeitscheiben aufteilen, indem man diese einfach in die alDatumsgrenzen einfügt.
		if (!bibALL.isEmpty(sDatum_BeginnZeitscheiben ) ){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calBegin = new GregorianCalendar();
			Calendar calEnd   = new GregorianCalendar();
			
			try {
				calBegin.setTime(df.parse(sDatum_BeginnZeitscheiben));
				calEnd.setTime(df.parse(sDatumBisISO));
				
				while(calBegin.before(calEnd)){
					calBegin.add(Calendar.DATE, m_iIntervalOfDays);
					sDatum = df.format(calBegin.getTime()) + " 23:59";
					alDatumsgrenzen.add(sDatum);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		// letzten Eintrag noch dazufügen
		sDatum = sDatumBisISO + " 23:59";
		alDatumsgrenzen.add(sDatum);
		
		// die Liste sortieren
		Collections.sort(alDatumsgrenzen);
		
		// alle Lagerdaten einmal lesen 
		vLagerAusgangAll = FillLagerDaten(/*idMandant,*/ IdAdresseLager, IdArtikel, EnumBuchungstyp.WA );
		vLagerEingangAll = FillLagerDaten(/*idMandant,*/ IdAdresseLager, IdArtikel, EnumBuchungstyp.WE );
		
		// falls eine der beiden gesamtliste leer ist, dann gleich beenden
		if (vLagerAusgangAll.size() == 0 || vLagerEingangAll.size() == 0){
			return;
		}
		
		// Alle Datumsbereiche verbuchen
		for (int j = 0; j < alDatumsgrenzen.size(); j ++){

			Vector<ATOM_LagerDaten> vLagerAusgang = GetLagerdatenForPeriod(vLagerAusgangAll, EnumBuchungstyp.WA, alDatumsgrenzen.get(j));
			Vector<ATOM_LagerDaten> vLagerEingang = GetLagerdatenForPeriod(vLagerEingangAll, EnumBuchungstyp.WE, alDatumsgrenzen.get(j));
			
			String sSqlBewegung = "";
			String sSqlVerbuchtWE = null;
			String sSqlVerbuchtWA = null;
			
			BigDecimal dNewWEMenge = BigDecimal.ZERO;
			BigDecimal dNewWAMenge = BigDecimal.ZERO;
			
			if (vLagerAusgang.size() == 0 || vLagerEingang.size()== 0){
				continue;
			}
			
			// jetzt alle Warenausgänge durchlaufen, und versuchen Setzkasten-Einträge zu schreiben
			for(ATOM_LagerDaten oAusgang: vLagerAusgang){
				
				BigDecimal dAusgangOffen = oAusgang.getOffeneMenge();
				dNewWAMenge = dAusgangOffen;
				
				// wenn nichts offen ist, weiter
				if (dAusgangOffen.compareTo(BigDecimal.ZERO) == 0){
					continue;
				}
				
				if (vLagerEingang.size() == 0){
					break;
				}
				
				// jetzt über alle gelesenen Lagereingänge gehen und den Lagerausgang mit Einträgen "füllen"
				for (ATOM_LagerDaten oEingang: vLagerEingang){
					// die verbleibende offene Menge lesen
					BigDecimal dEingangOffen = oEingang.getOffeneMenge();
					dNewWEMenge = dEingangOffen;
					
					// die Ausgangsmenge anpassen, da sie unten ja geändert werden kann
					dAusgangOffen = oAusgang.getOffeneMenge();
					dNewWAMenge = dAusgangOffen;
					
					// wenn nichts offen ist, weiter
					if (dEingangOffen.compareTo(BigDecimal.ZERO) == 0){
						continue;
					}
					
					// prüfen, wer in wen passt 
					int comp = dEingangOffen.compareTo(dAusgangOffen) ;
					if (comp < 0 ){
						// WE < WA
						sSqlBewegung = SQLInsertNewSetzkastenEintrag(	oAusgang.getId_bewegungsatom(), 
								oEingang.getId_bewegungsatom(), 
								dEingangOffen, 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
								null);
						
						// Insert: WE ist komplett verbucht
						sSqlVerbuchtWE = SQLInsertAtomVerbucht(
								oEingang.getId_bewegungsatom(), 
								oEingang.getId_bewegungsvektor(), 
								oEingang.getId_bewegung()); 
						
						dNewWEMenge = BigDecimal.ZERO;
						dNewWAMenge = dAusgangOffen.subtract(dEingangOffen);
						
					} else if (comp > 0){
						
						// WE > WA
						sSqlBewegung = SQLInsertNewSetzkastenEintrag(	oAusgang.getId_bewegungsatom(), 
								oEingang.getId_bewegungsatom(), 
								dAusgangOffen.abs(), 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
						"Y");
						
						sSqlVerbuchtWA = SQLInsertAtomVerbucht(
								oAusgang.getId_bewegungsatom(), 
								oAusgang.getId_bewegungsvektor(), 
								oAusgang.getId_bewegung()); 
						
						
						dNewWEMenge = dEingangOffen.subtract(dAusgangOffen);
						dNewWAMenge = BigDecimal.ZERO;
						
						
					} else {
						// WE == WA 
						sSqlBewegung = SQLInsertNewSetzkastenEintrag(	oAusgang.getId_bewegungsatom(), 
								oEingang.getId_bewegungsatom(), 
								dAusgangOffen, 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
						"Y");

						sSqlVerbuchtWA = SQLInsertAtomVerbucht(
								oAusgang.getId_bewegungsatom(), 
								oAusgang.getId_bewegungsvektor(), 
								oAusgang.getId_bewegung()); 
						
						sSqlVerbuchtWE = SQLInsertAtomVerbucht(
								oEingang.getId_bewegungsatom(), 
								oEingang.getId_bewegungsvektor(), 
								oEingang.getId_bewegung()); 
						
						dNewWAMenge = BigDecimal.ZERO;
						dNewWEMenge = BigDecimal.ZERO;
					}
					
					
					
					Vector<String> sSQLVector = new Vector<String>();
					sSQLVector.add(sSqlBewegung);

					if (sSqlVerbuchtWA != null) {
						sSQLVector.add(sSqlVerbuchtWA);
					}
					if (sSqlVerbuchtWE != null){
						sSQLVector.add(sSqlVerbuchtWE);
					}
					
					
					boolean bRet =  bibDB.ExecSQL_in_Batch(sSQLVector, true);
					if (bRet){
						oEingang.setOffeneMenge(dNewWEMenge);
						oAusgang.setOffeneMenge(dNewWAMenge);
					} else {
						DEBUG.System_println("Fehler in SQL:" + sSQLVector, DEBUG.DEBUG_FLAG_SQL_ERROR);
					}

					// löschen der Statements
					sSqlVerbuchtWE = null;
					sSqlVerbuchtWA = null;
					
					// wenn der Warenausgang auf die Eingänge verteilt ist..
					if (oAusgang.getOffeneMenge().compareTo(BigDecimal.ZERO)==0){
						break;  // ..weitermachen beim nächste Warenausgang!!
					}
										
					//loop-kontrolle von aussen
					if (this.colLoopInfo != null)
					{
						this.colLoopInfo.removeAll();
						this.colLoopInfo.add(new MyE2_Label(new MyE2_String("Lager verbuchen .... "+(this.iCount++))));
					}
					
					
				}
				
				
				// jetzt die verbuchten Eingangsposten aus dem Vector rausschmeissen
				int size = vLagerEingang.size();
				for (int i = size -1; i >= 0; i--){
					ATOM_LagerDaten oEin = vLagerEingang.get(i);
					// wenn nichts offen ist, weiter
					if (oEin.getOffeneMenge().compareTo(BigDecimal.ZERO) == 0){
						vLagerEingang.remove(i);
					}
				}
				
				
				
			}
		}
		
		return ;
		
		
	}
	

	/**
	 * Gibt alle Lagerdaten zurück, die in der Zeitscheibe 
	 * @param vLagerdaten
	 * @param bt
	 * @param sDatumBis
	 * @return
	 */
	private Vector<ATOM_LagerDaten> GetLagerdatenForPeriod(Vector<ATOM_LagerDaten> vLagerdaten, EnumBuchungstyp bt, String sDatumBis){
		// alle Lagerdaten durchgehen
		Vector<ATOM_LagerDaten> vTemp = new Vector<ATOM_LagerDaten>();

		for (ATOM_LagerDaten a : vLagerdaten){
			if (a.buchungsdatum.compareTo(sDatumBis.substring(0, 11)) <= 0  && a.offeneMenge.compareTo(BigDecimal.ZERO) != 0 ){
				vTemp.add(a);
			}
		}
		return vTemp;
	}
	
	
	/**
	 * ermitteln des kleinsten Datums in beiden Vektoren
	 * @param vLagerdatenWE
	 * @param vLagerdatenWA
	 * @return
	 */
	private String getMinDateFromArrays(Vector<ATOM_LagerDaten> vLagerdaten ){
		String sDate = "";
		
		
		Collections.sort(vLagerdaten, new Comparator<ATOM_LagerDaten>() {
			   public int compare(ATOM_LagerDaten o1, ATOM_LagerDaten o2){
			      return o1.getBuchungsdatum().compareTo(o2.getBuchungsdatum());
			   }
			});
		// jetzt 1. Buchungsdatum finden
		return vLagerdaten.firstElement().getBuchungsdatum();		
	}
	
	
	
	/**
	 * Alle Lagerdaten unabhängig von der Zeit die noch nicht versetzt sind
	 * @param idMandant
	 * @param idLager
	 * @param idArtikel
	 * @param bt
	 * @return
	 */
	private Vector<ATOM_LagerDaten> FillLagerDaten(	/*String idMandant, */
														String idLager, 
														String idArtikel,
														EnumBuchungstyp bt){
		
		
		return FillLagerDaten( /*idMandant,*/ idLager, idArtikel, bt, "9999-12-31 23:59");
	}
 
	
	
	/**
	 * Ermitteln der Lagerbestände, die noch nicht verteilt sind!
	 * Author: manfred
	 * 01.04.2009
	 *
	 * @param idMandant
	 * @param idLager
	 * @param idArtikel
	 * @param bt EnumBuchungstyp.WA/WE
	 * @return
	 */
	private Vector<ATOM_LagerDaten> FillLagerDaten(	//String idMandant, 
												String idLager, 
												String idArtikel,
												EnumBuchungstyp bt,
												String sDatumBis){
		
		Vector<ATOM_LagerDaten> vReturn = new Vector<ATOM_LagerDaten>();
		
		String [][] cLagerDaten = new String[0][0];
		
		
		String sSql = "";
		

		// die Settings für die Abfrage definieren, damit man die "echten" und die "kalkulatorischen" Daten unterscheiden kann
		setQuerysettingsForLagerdaten(bt);
		
		
//		sSql =  	" SELECT " +
//					" A.ID_BEWEGUNG_ATOM, " +
//					m_sqlPreis + " , " + 
//					" max(S.MENGENVORZEICHEN) * max(" + m_sqlMenge + "), " +
//					" max(A.LEISTUNGSDATUM),   " +
//					" nvl( sum(nvl(SK.MENGE,0) ),0.0) as verbucht  " +
//					" , A.ID_BEWEGUNG_VEKTOR " +
//					" , A.ID_BEWEGUNG " +
//					
//					" FROM JT_BEWEGUNG_ATOM A " +
//					" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM and S.MENGENVORZEICHEN =   " + m_sqlVorzeichen + 
//					" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON V.ID_BEWEGUNG_VEKTOR = A.ID_BEWEGUNG_VEKTOR " +
//					" INNER JOIN " + bibE2.cTO()+".JT_ARTIKEL ART ON ART.ID_ARTIKEL = A.ID_ARTIKEL " +
//					" LEFT OUTER JOIN   " + bibE2.cTO() + "."+ m_sqlTablenameAtomSetzkasten + " SK  ON S.ID_BEWEGUNG_ATOM = " + m_sqlJoinSetzkasten +
//					" LEFT OUTER JOIN   " + bibE2.cTO() + "." + m_sqlTableNameAtomVerbucht + " AV  ON AV.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM  " +
//					
//					" WHERE " +
//					" 	  nvl(A.DELETED,'N') 	= 'N' " +
//					" AND nvl(A.STORNIERT,'N') 	= 'N' " +
//					" AND AV.ID_BEWEGUNG_ATOM  IS NULL " +
//					
//					" AND A.LEISTUNGSDATUM <= to_date('" + sDatumBis + "','yyyy-mm-dd HH24:mi') "  +
//					" AND A.ID_ARTIKEL = " + idArtikel + 
//					" AND S.ID_ADRESSE = " + idLager +
//					
//					" GROUP BY S.ID_ADRESSE, A.ID_ARTIKEL, A.ID_BEWEGUNG_ATOM  ,A.ID_BEWEGUNG_VEKTOR, A.ID_BEWEGUNG " +
//					" HAVING  max(S.MENGENVORZEICHEN) * max(" + m_sqlMenge + ")  != 0 " +
//					m_sqlOrder
//			;
		
		
		sSql =  	" SELECT " +
				" A.ID_BEWEGUNG_ATOM, " +
				m_sqlPreis + " , " + 
				" (S.MENGENVORZEICHEN) * (" + m_sqlMenge + "), " +
				" (A.LEISTUNGSDATUM),   " +
				" nvl( (SELECT sum(menge) FROM " + bibE2.cTO() + "."+ m_sqlTablenameAtomSetzkasten + " SK WHERE " +  m_sqlJoinSetzkasten + " = A.ID_BEWEGUNG_ATOM )  ,0.0) as verbucht  " +
				" , A.ID_BEWEGUNG_VEKTOR " +
				" , A.ID_BEWEGUNG " +
				
				" FROM JT_BEWEGUNG_ATOM A " +
				" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S ON S.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM and S.MENGENVORZEICHEN =   " + m_sqlVorzeichen + 
				" INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON V.ID_BEWEGUNG_VEKTOR = A.ID_BEWEGUNG_VEKTOR " +
				" INNER JOIN " + bibE2.cTO()+".JT_ARTIKEL ART ON ART.ID_ARTIKEL = A.ID_ARTIKEL " +
//				" LEFT OUTER JOIN   " + bibE2.cTO() + "." + m_sqlTableNameAtomVerbucht + " AV  ON AV.ID_BEWEGUNG_ATOM = A.ID_BEWEGUNG_ATOM  " +
				
				" WHERE " +
				" 	  nvl(A.DELETED,'N') 	= 'N' " +
				" AND nvl(A.STORNIERT,'N') 	= 'N' " +
//				" AND AV.ID_BEWEGUNG_ATOM  IS NULL " +
				
				" AND A.LEISTUNGSDATUM <= to_date('" + sDatumBis + "','yyyy-mm-dd HH24:mi') "  +
				" AND A.ID_ARTIKEL = " + idArtikel + 
				" AND S.ID_ADRESSE = " + idLager +
				" AND nvl("+m_sqlMenge+",0) != 0 " + 
				" AND nvl("+m_sqlMenge+",0) - nvl( (SELECT sum(menge) FROM " + bibE2.cTO() + "."+ m_sqlTablenameAtomSetzkasten + " SK WHERE " +  m_sqlJoinSetzkasten + " = A.ID_BEWEGUNG_ATOM )  ,0.0) != 0" + 
				m_sqlOrder
		;
	
		
		
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cLagerDaten == null || cLagerDaten.length == 0){
			return vReturn;
		}
		
		// sonst den Vektor füllen
		for (int i = 0; i < cLagerDaten.length; i++){
			String 		Lager = 			cLagerDaten[i][0];
			String      Vektor =            cLagerDaten[i][5];
			String      Bewegung =          cLagerDaten[i][6];
			
			String		sPreis = cLagerDaten[i][1];
			BigDecimal 	dPreis = 			sPreis != null ? new BigDecimal(cLagerDaten[i][1]) : null ;
			BigDecimal 	dMenge = 			new BigDecimal(cLagerDaten[i][2]) ;
			String 		Buchungsdatum = 	cLagerDaten[i][3];
			BigDecimal 	dVerbuchteMenge = 	new BigDecimal(cLagerDaten[i][4]) ;
			
			
			BigDecimal 	dOffeneMenge  = 	new BigDecimal(0);
			
			// Warenausgänge sind im Konto negativ, aber die Bewegungsliste hat immer positive Werte
			if (bt == EnumBuchungstyp.WA){
				dOffeneMenge =  dMenge.add(dVerbuchteMenge).abs();
			}else{
				dOffeneMenge = dMenge.subtract(dVerbuchteMenge).abs();
			}
				
			ATOM_LagerDaten l = new ATOM_LagerDaten(	Lager,
											Vektor, 
											Bewegung,
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
	 * SQLString zur erzeugung eines neuen Lager-Bewegungssatz
	 * @author manfred
	 * @date 12.03.2009
	 * 
	 * @param id_lager_kto_ausgang
	 * @param id_lager_kto_eingang
	 * @param menge
	 * @param preis_eingang
	 * @param preis_ausgang
	 * @param komplett
	 * @return
	 * @throws myException 
	 */
	protected String SQLInsertNewSetzkastenEintrag(	String id_lager_kto_ausgang, 
												String id_lager_kto_eingang,
												BigDecimal menge,
												BigDecimal preis_eingang,
												BigDecimal preis_ausgang,
												String komplett) throws myException{
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		oSql.addSQL_Paar("ID_BEWEGUNG_SETZKASTEN", "SEQ_BEWEGUNG_SETZKASTEN.NEXTVAL", false);

	    oSql.addSQL_Paar("ID_ATOM_EINGANG", id_lager_kto_eingang, false);
	    oSql.addSQL_Paar("ID_ATOM_AUSGANG", id_lager_kto_ausgang, false);
	    oSql.addSQL_Paar("MENGE", menge == null ? null : menge.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_EINGANG", preis_eingang == null ? null : preis_eingang.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_AUSGANG",preis_ausgang == null ? null : preis_ausgang.toPlainString() , false);
	    
	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    
	    String sSql = oSql.get_CompleteInsertString("JT_BEWEGUNG_SETZKASTEN", bibE2.cTO());

		return sSql;
	}

	
	/**
	 * Insert-Statement für die Tabelle JT_BEWEGUNG_ATOM_VERBUCHT
	 * @author manfred
	 * @date   13.12.2012
	 * @param id_atom
	 * @param id_vektor
	 * @param id_bewegung
	 * @return
	 * @throws myException
	 */
	protected String SQLInsertAtomVerbucht(
			String id_atom, 
			String id_vektor,
			String id_bewegung
			) throws myException{
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
		oSql.addSQL_Paar("ID_BEWEGUNG_ATOM_VERBUCHT", "SEQ_BEWEGUNG_ATOM_VERBUCHT.NEXTVAL", false);
		
		oSql.addSQL_Paar("ID_BEWEGUNG_ATOM", id_atom, false);
		oSql.addSQL_Paar("ID_BEWEGUNG_VEKTOR", id_vektor, false);
		oSql.addSQL_Paar("ID_BEWEGUNG", id_bewegung , false);
		
		oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		
		String sSql = oSql.get_CompleteInsertString("JT_BEWEGUNG_ATOM_VERBUCHT", bibE2.cTO());
		
		return sSql;
	}
	
	
	
	
	
	
	
	/**
	 * Ermitteln aller möglichen Kombinationen,die gelöscht werden sollen
	 * 
	 * @param sIDAdresse
	 * @param sIDArtikel
	 */
	protected boolean cleanSetzkasten ( String sIDAdresse, String sIDArtikel ){
		boolean bRet = false;
		Vector<String> vRet = new Vector<String>();
		
		StringBuilder sb = new StringBuilder();
		
		// Abfrage prüft ATOME, die noch nicht versetzt wurden und ermittelt das früheste Datum der Atome
		sb.append( "  SELECT MIN(A.LEISTUNGSDATUM), S.ID_ADRESSE, A.ID_ARTIKEL  " );
		sb.append( "  FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A  " );
		sb.append( "  INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S ON A.ID_BEWEGUNG_ATOM = S.ID_BEWEGUNG_ATOM  " );
//		sb.append( "  LEFT OUTER JOIN " + bibE2.cTO() + "." + m_sqlTableNameAtomVerbucht + " V ON A.ID_BEWEGUNG_ATOM = V.ID_BEWEGUNG_ATOM  " );
		sb.append( "  WHERE   " );
		sb.append( "  S.ID_ADRESSE IN (SELECT ID_ADRESSE FROM " + bibE2.cTO() + ".V_FIRMENADRESSEN_FLACH WHERE ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + " AND SONDERLAGER IS NULL)  " );
//		sb.append( "  AND V.ID_BEWEGUNG_ATOM IS NULL  " );
		sb.append( "  AND A.LEISTUNGSDATUM IS NOT NULL  " );
		sb.append( "  AND NVL(A.DELETED,'N')= 'N'  " );
		sb.append( "  AND NVL(A.STORNIERT,'N')= 'N'  " );
		sb.append( "  AND NVL(A.MENGE,0) != 0  " );
		sb.append( "  AND nvl(A.MENGE,0) - nvl((SELECT sum(K.menge) from " + m_sqlTablenameAtomSetzkasten + " K  WHERE K.ID_ATOM_AUSGANG = A.ID_BEWEGUNG_ATOM),0) - nvl((SELECT sum(K.menge) from " + m_sqlTablenameAtomSetzkasten + " K  WHERE K.ID_ATOM_EINGANG = A.ID_BEWEGUNG_ATOM),0)!= 0" );

		if (org.apache.commons.lang.StringUtils.isNotEmpty(sIDAdresse)){
			sb.append(" AND S.ID_ADRESSE = ");
			sb.append( sIDAdresse);
		}
		if (org.apache.commons.lang.StringUtils.isNotEmpty(sIDArtikel)){
			sb.append(" AND A.ID_ARTIKEL = ");
			sb.append( sIDArtikel);
		}		
		sb.append( "  GROUP BY S.ID_ADRESSE, A.ID_ARTIKEL  " );
		
		
		// Statement ausführen
		String[][] cResult = bibDB.EinzelAbfrageInArray(sb.toString(),(String)null);
				
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cResult == null || cResult.length == 0){
			return true;
		}
		
		// sonst den Vektor füllen
		for (int i = 0; i < cResult.length; i++){
			String		Datum 		= cResult[i][0];
			String 		IDAdresse 	= cResult[i][1];
			String 		IDArtikel 	= cResult[i][2];
			vRet.addAll(cleanSetzkasten(IDAdresse, IDArtikel, Datum) ) ;
		}
	

		bRet =  bibDB.ExecSQL_in_Batch(vRet, true);
		if (!bRet){
			DEBUG.System_println("Fehler beim löschen des Setzkastens in SQL:" + vRet, DEBUG.DEBUG_FLAG_SQL_ERROR);
		}
		return bRet;

	}
	
	
	/**
	 * Erzeugt die Delete-Statements alle Setzkasten und Verbucht-Einträge die zu einer Auswahl gehören 
	 * @param sIDAdresse
	 * @param sIDArtikel
	 */
	private Vector<String> cleanSetzkasten( String sIDAdresse, String sIDArtikel, String sDatum){
		Vector<String> vRet = new Vector<String>();
		String sAtoms = " IN ( " + getSQLSelectAtoms(sIDAdresse, sIDArtikel, sDatum) + ")";
		
		
	
		String sSqlDelSetzkasten = " DELETE FROM " + m_sqlTablenameAtomSetzkasten + 
				" WHERE ID_ATOM_AUSGANG " + sAtoms +
				" OR ID_ATOM_EINGANG " + sAtoms;
		
		// wird im Trigger gemacht
//		String sSqlDelVerbucht =   " DELETE FROM " + m_sqlTableNameAtomVerbucht + 
//				" WHERE ID_BEWEGUNG_ATOM " + sAtoms;
		
		vRet.add(sSqlDelSetzkasten);
//		vRet.add(sSqlDelVerbucht);
	
		
		return vRet;
	}
	

	
	/**
	 * Erzeugt das subselect für die Lösch-Query
	 * @param sIDADresse
	 * @param sIDArtikel
	 * @param sDatum
	 * @return
	 */
	private String getSQLSelectAtoms(String sIDADresse, String sIDArtikel, String sDatum){
		String sSql = " SELECT A.ID_BEWEGUNG_ATOM "
					+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A "
					+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_STATION S ON A.ID_BEWEGUNG_ATOM = S.ID_BEWEGUNG_ATOM "
					+ " WHERE S.ID_ADRESSE = " + sIDADresse 
					+ " AND A.ID_ARTIKEL = " + sIDArtikel 
					+ " AND A.LEISTUNGSDATUM >= to_date('"+ sDatum + "','YYYY-MM-DD') ";
		return sSql;
	}

	
	
	

}
