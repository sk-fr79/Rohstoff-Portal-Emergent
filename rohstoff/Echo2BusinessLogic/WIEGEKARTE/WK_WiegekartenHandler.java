package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_ImageCapture_Handler;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WAEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerHandler;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

/**
 * Beinhaltet die Logik für die Wiegekarten-Verwaltung
 * @author manfred
 *
 */
public class WK_WiegekartenHandler {

	// ID
	String 					m_ID_Wiegekarte = null;
	
	// Mehrfachwiegung == eine Sorte/Kennzeichen/Kunde-Kombination kann mehrfach am gleichen Tag vorkommen
	private boolean 				m_ErlaubeMehrfachwiegungen = false;
	
	// Objekt der aktuellen Wiegekarte
	RECORD_WIEGEKARTE		m_REC_Wiegekarte = null;
	
	// Liste aller Waegungen der Wiegekarte, auch der stornierten
	RECLIST_WAEGUNG			m_RECLIST_Waegung = null;
	
	// objekte der aktuellen Wägungen
	RECORD_WAEGUNG			m_REC_Waegung1 = null;
	RECORD_WAEGUNG			m_REC_Waegung2 = null;
	
	
	// aktuelle Buchungssätze, parallel zu den RECORDS
	WK_Wiegekarte_Buchungssatz 	m_BuchungWiegekarte = null;
	WK_Waegung_Buchungssatz  	m_BuchungWaegung1 = null;
	WK_Waegung_Buchungssatz  	m_BuchungWaegung2 = null;
	
	
	
	// Vector, der die abzuarbeitenden SQL-Statements beinhaltet
	Vector<String>  m_vSQLStatements = null;
	
	
	/** 
	 * Standardkonstruktur
	 */
	public WK_WiegekartenHandler(){
		// Standardkonstruktor zum anlegen von Wiegekarten.
		m_vSQLStatements = new Vector<String>();
		
	}
	
	/**
	 * Konstruktor zum lesen / initialisieren einer bestehenden Wiegekarte
	 * @param ID_Wiegekarte
	 * @throws myException 
	 */
	public WK_WiegekartenHandler(String ID_Wiegekarte) throws myException {
		
		this();
		
		InitWK(ID_Wiegekarte);
	}

	
	
	/**
	 * Es werden alle Instanzvariablen zur Wiegekarte ermittelt:
	 * Die Buchungssätze für Wiegekarten und Wägungen
	 * Die Record-Objekte
	 * @param idWiegekarte
	 * @throws myException 
	 */
	public void InitWK(String idWiegekarte) throws myException{
		// Parameter übernehmen
		m_ID_Wiegekarte = idWiegekarte;

		// Instanzvariablen initialisiern
		m_REC_Wiegekarte = null;
		m_RECLIST_Waegung = null;
		m_REC_Waegung1 = null;
		m_REC_Waegung2 = null;
		
		m_BuchungWiegekarte = null;
		m_BuchungWaegung1 = null;
		m_BuchungWaegung2 = null;
		
		m_vSQLStatements.clear();
		m_vSQLStatements = new Vector<String>();
		
				
		if (m_ID_Wiegekarte == null) return;
		
		// Wiegekarte aus der DB lesen
		m_REC_Wiegekarte = new RECORD_WIEGEKARTE(m_ID_Wiegekarte);
		
		
		if (m_REC_Wiegekarte != null){
		
			m_BuchungWiegekarte = new WK_Wiegekarte_Buchungssatz(m_REC_Wiegekarte);
			
			// ermitteln aller Waegungen der Wiegekarte
			m_RECLIST_Waegung = m_REC_Wiegekarte.get_DOWN_RECORD_LIST_WAEGUNG_id_wiegekarte(null,"WAEGUNG_POS",true);
			
			if (m_RECLIST_Waegung != null && m_RECLIST_Waegung.values().size() > 0){
				for (int i= 0; i < m_RECLIST_Waegung.values().size(); i++ ){
					RECORD_WAEGUNG r = m_RECLIST_Waegung.get(i);
					if ( (r.get_STORNO_cUF() == null || r.get_STORNO_cUF().equals("N"))){
						if (r.get_WAEGUNG_POS_cUF_NN("").equals("1")){
							// waegung 1
							if (m_REC_Waegung1 == null){
								m_REC_Waegung1 = r;
								
								m_BuchungWaegung1 = new WK_Waegung_Buchungssatz(m_REC_Waegung1);
								
							} else {
								// fehler! Es dürfen keine 2 aktive Wägungen existieren
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es existiert mehr als eine aktive Messung für die Wiegekarte!")));
							}
						} else if (r.get_WAEGUNG_POS_cUF_NN("").equals("2")){
							// waegung 2
							if (m_REC_Waegung2 == null){
								m_REC_Waegung2 = r;
								
								m_BuchungWaegung2 = new WK_Waegung_Buchungssatz(m_REC_Waegung2);
							} else  {
								// fehler! Es dürfen keine 2 aktive Wägungen existieren
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es existiert mehr als eine aktive Messung für die Wiegekarte!")));
							}
						} else {
							// fehler
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehlerhafte Wägungsposition (nur 1 und 2 erlaubt)!")));
						}
					}
				}
			}
			
		} else {
			// es wurde keine Wiegekarte gefunden mit der ID
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es existiert kein Eintrag für die Wiegekarte mit der ID " + m_ID_Wiegekarte)));
		}		
	}
	
	
	/**
	 * Speichern der Wiegekarte und den Wägungen
	 * Abzüge und Nettogewichte werden berechnet und eingetragen
	 * 
	 * @return
	 * @throws myException
	 */
	public boolean saveWiegekarte() throws myException
	{
		return this.saveWiegekarte(null, null);
	}
	
		
	/** Speichern einer gesamten Wiegekarte
	 * Insert oder Update 
	 * @param vSqlStatementsBefore
	 * @param vSqlStatementsAfter
	 * @throws myException 
	 */
	public boolean saveWiegekarte(Vector<String> vSqlStatementsBefore, Vector<String> vSqlStatementsAfter) throws myException{
		
		boolean bRet = true;
		BigDecimal bdGewicht1 = null;
		BigDecimal bdGewicht2 = null;
		BigDecimal bdGewichtNetto = null;
		

		
		if (m_BuchungWaegung1 != null){
			bdGewicht1 = m_BuchungWaegung1.getGEWICHT();
		}
		if (m_BuchungWaegung2 != null){
			bdGewicht2 = m_BuchungWaegung2.getGEWICHT();
		}
		
		String sIDWK_Parent_for_update = null;
		String sTYP_WIEGEKARTE = null;
		
		String sSqlAbzugsgewicht = 	null;
		String sSqlAbzugsgewicht_Fuhre = null;
		
		
		String idArtikelbez = !bibALL.isEmpty(m_BuchungWiegekarte.getID_ARTIKEL_BEZ()) ? m_BuchungWiegekarte.getID_ARTIKEL_BEZ() : "0" ;
		String kennzeichen = !bibALL.isEmpty(m_BuchungWiegekarte.getKENNZEICHEN()) ? m_BuchungWiegekarte.getKENNZEICHEN() : "-" ;
		String idWiegekarte = m_BuchungWiegekarte.getID_WIEGEKARTE();
		String sWhereID = "";
		if ( idWiegekarte != null && bibALL.isInteger(idWiegekarte)){
			sWhereID = !bibALL.isEmpty(m_BuchungWiegekarte.getID_WIEGEKARTE()) ? " AND ID_WIEGEKARTE != " + m_BuchungWiegekarte.getID_WIEGEKARTE() + " " : "" ;
		}
		
		
		// prüfen, ob schon einmal die Kombination Kennzeichen und Sorte an diesem Tag erzeugt wurde. Nur bei Neuanlage einer Wiegekarte.
		// Beim Speichern ist die Prüfung schon durchgeführt worden.
		String sDate = "";
//		boolean bWK_hat_KennzUndSorte = false;
		boolean bMehrfachwiegungErlaubnisErforderlich = true;
		
		if (m_REC_Wiegekarte == null){
			sDate = "SYSDATE";
			bMehrfachwiegungErlaubnisErforderlich = true;
		} else {
			sDate = "to_date('" + m_BuchungWiegekarte.getERZEUGT_AM() +  "')" ;
//			bWK_hat_KennzUndSorte = !bibALL.isEmpty(m_REC_Wiegekarte.get_KENNZEICHEN_cUF()) && !bibALL.isEmpty(m_REC_Wiegekarte.get_ID_ARTIKEL_BEZ_cUF() );
			
			// wenn es eine schon gespeicherte WK ist aber sich kennzeichen oder sorte geändert hat, dann ist es erforderlich, dass Mehrfachverwiegung genehmigt wird
			if (	bibALL.null2leer(m_REC_Wiegekarte.get_KENNZEICHEN_cUF()).compareTo(bibALL.null2leer(m_BuchungWiegekarte.getKENNZEICHEN()) ) != 0
				|| 	bibALL.null2leer(m_REC_Wiegekarte.get_ID_ARTIKEL_BEZ_cUF()).compareTo(bibALL.null2leer(m_BuchungWiegekarte.getID_ARTIKEL_BEZ()) ) != 0)
			{
				bMehrfachwiegungErlaubnisErforderlich = true;
			} else {
				bMehrfachwiegungErlaubnisErforderlich = false;
			}
		}
		
		RECLIST_WIEGEKARTE reclist_alt = new RECLIST_WIEGEKARTE(
				" KENNZEICHEN = '" + kennzeichen + "' AND trunc(ERZEUGT_AM,'DD') = trunc(" + sDate + ",'DD') " +
				" AND ID_ARTIKEL_BEZ = " + idArtikelbez + sWhereID , "");

		
		// 
		// Prüfen, ob die Kennzeichen/Sorte/Datums-Kombination schon mal gesetzt war
		// Nur erlauben, wenn der Schalter gesetzt ist
		//
		if ( m_ErlaubeMehrfachwiegungen == false ){
			if (reclist_alt.size() > 0   ){
				if (bMehrfachwiegungErlaubnisErforderlich){
					// wenn bei der aktuellen WK die Sorte oder das Kennzeichen verändert wurde, darf nicht abgespeichert werden.
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Kennzeichen/Sorten-Kombination wurde heute schon verwendet. Bitte ändern Sie das Kennzeichen, oder Erlauben Sie die Mehrfachverwiegung.")));
					return false;
				}
			}
		} 
		

		//
		// Prüfen, ob die Fuhre schon einmal verwendet wurde.
		//
		if (m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE() != null){
			
			reclist_alt = new RECLIST_WIEGEKARTE(
					" nvl(ID_VPOS_TPA_FUHRE,'0') || '-' || nvl(ID_VPOS_TPA_FUHRE_ORT,'')= '" + m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE() + "-" + m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE_ORT() + "' " +
					" AND nvl(STORNO,'N') = 'N' " +  sWhereID , "");
			
			if (reclist_alt.size() > 0 && m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() == null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Fuhre wurde schon einmal verarbeitet. Bitte ändern Sie die Fuhre oder löschen Sie die Fuhre in der anderen Wiegekarte!")));
				return false;
			}
		}
		
		
		// eine Folgewiegung kann keine Gesamtverwiegung sein
		if (m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() != null && bibALL.null2leer(m_BuchungWiegekarte.getIST_GESAMTVERWIEGUNG()).equals("Y") ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Eine Folgewiegung kann keine Gesamtverwiegung sein.")));
			return false;
		}
		
		// die Wiegekarte vorbereiten
		if (m_REC_Wiegekarte == null){

			
			// insert
			if (m_BuchungWiegekarte != null){
				m_vSQLStatements.add(m_BuchungWiegekarte.getSQLForInsert());

				sIDWK_Parent_for_update = m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() ;
				sTYP_WIEGEKARTE = m_BuchungWiegekarte.getTYP_WIEGEKARTE();
			}
		} else {
			
			// Gewicht übernehmen	
			if (bdGewicht1 != null && bdGewicht2 != null){
				bdGewichtNetto = (bdGewicht1.subtract(bdGewicht2)).abs();

				// das Gewicht auch in der Wiegekarte setzen
				m_BuchungWiegekarte.setNETTOGEWICHT(bdGewichtNetto);

			}
			
			// update
			if (m_BuchungWiegekarte != null && m_REC_Wiegekarte != null){
				
				m_BuchungWiegekarte.setUpdateValues(m_REC_Wiegekarte);
				
				sIDWK_Parent_for_update = m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() ;
				if (sIDWK_Parent_for_update == null){
					sIDWK_Parent_for_update = m_BuchungWiegekarte.getID_WIEGEKARTE() ;
				}
				sTYP_WIEGEKARTE = m_BuchungWiegekarte.getTYP_WIEGEKARTE();
				
				// das Gewicht speichern
				m_REC_Wiegekarte.set_NEW_VALUE_NETTOGEWICHT(bibALL.convertBigDecimalToString(bdGewichtNetto,3));

				// Statement dazufügen
				m_vSQLStatements.add(m_REC_Wiegekarte.get_SQL_UPDATE_STATEMENT(null, true));
				
				// Abzüge und gesamtgewicht berechnen und eintragen
				sSqlAbzugsgewicht = 	" UPDATE  " + bibE2.cTO() + ".jt_wiegekarte W  " +
										" SET W.GEWICHT_ABZUG = ( SELECT sum(A.GEWICHT_EINZEL * A.MENGE)    FROM  " + bibE2.cTO() + ".JT_WIEGEKARTE_ABZUG_GEB A    WHERE A.ID_WIEGEKARTE = W.ID_WIEGEKARTE ) ," +
										" W.GEWICHT_NACH_ABZUG = (CASE WHEN W.NETTOGEWICHT is not null " +
										" THEN W.NETTOGEWICHT - NVL( ( SELECT sum(A.GEWICHT_EINZEL * A.MENGE)    FROM  " + bibE2.cTO() + ".JT_WIEGEKARTE_ABZUG_GEB A    WHERE A.ID_WIEGEKARTE = W.ID_WIEGEKARTE),0) " +
										" ELSE null END)" +
										" where W.ID_WIEGEKARTE = " +  m_BuchungWiegekarte.getID_WIEGEKARTE();
			
				sSqlAbzugsgewicht_Fuhre = " UPDATE  " + bibE2.cTO() + ".JT_WIEGEKARTE W  " +
						" SET W.GEWICHT_ABZUG_FUHRE = ( SELECT SUM( CASE  WHEN A.ABZUG_MENGE IS NOT NULL  THEN A.ABZUG_MENGE   ELSE nvl(W.GEWICHT_NACH_ABZUG,0) * A.ABZUG_PROZENT / 100   END ) " +
						" 	 FROM   " + bibE2.cTO() + ".JT_WIEGEKARTE_MGE_ABZ A " +
						"    WHERE   A.ID_WIEGEKARTE = W.ID_WIEGEKARTE  )" +
						"   " +
						", W.GEWICHT_NACH_ABZUG_FUHRE =  (CASE WHEN W.GEWICHT_NACH_ABZUG is not null " +
						"  THEN( W.GEWICHT_NACH_ABZUG - NVL(( SELECT SUM( CASE  WHEN A.ABZUG_MENGE IS NOT NULL  THEN A.ABZUG_MENGE   ELSE nvl(W.GEWICHT_NACH_ABZUG,0) * A.ABZUG_PROZENT / 100   END ) " +
						" 	 FROM   " + bibE2.cTO() + ".JT_WIEGEKARTE_MGE_ABZ A " +
						"    WHERE   A.ID_WIEGEKARTE = W.ID_WIEGEKARTE  ),0) ) ELSE null END)" +
						" WHERE W.ID_WIEGEKARTE = " +  m_BuchungWiegekarte.getID_WIEGEKARTE();
			}
		}
		
		// anpassen des Wiegekarten-Typs für alle Wiegekarten einer Folgewägung...
		// Manfred: 19.11.2015:bei Dokumentarischen Verwiegungen darf der Typ nicht automatisch angepasst werden. 
		if (sIDWK_Parent_for_update != null ){
			
			// der Wiegekarten-Typ darf auch nur angepasst werden, wenn der aktuelle Typ keine Dokumentarische Wiegekarte ist.
			if (!sTYP_WIEGEKARTE.equals(WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH)){
				String sUpdateTyp = "UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE SET TYP_WIEGEKARTE = '" + sTYP_WIEGEKARTE + "' "
						+ " WHERE (ID_WIEGEKARTE = " + sIDWK_Parent_for_update + " OR ID_WIEGEKARTE_PARENT = " + sIDWK_Parent_for_update +" ) "
						+ " AND TYP_WIEGEKARTE != '" + WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH +"'";
				
				m_vSQLStatements.add(sUpdateTyp);
			}
		}

		
		
		// waegung 1
		if (m_BuchungWaegung1 != null && m_REC_Waegung1 == null){
			m_vSQLStatements.add(m_BuchungWaegung1.getSQLForInsert());
		}
		// waegung 2
		if (m_BuchungWaegung2 != null && m_REC_Waegung2 == null){
			m_vSQLStatements.add(m_BuchungWaegung2.getSQLForInsert());
		}
		
		//
		// zusätzliche Statements einfügen
		if (vSqlStatementsBefore != null){
			m_vSQLStatements.addAll(0, vSqlStatementsBefore);
		}
		
		if (vSqlStatementsAfter != null){
			m_vSQLStatements.addAll(vSqlStatementsAfter);
		}
		
		// am Ende noch das Nettogewicht berechnen
		if (sSqlAbzugsgewicht != null){
			m_vSQLStatements.add(sSqlAbzugsgewicht);
		}
		
		// dann noch das Gewicht nach den Fuhrenabzügen berechnen
		if (sSqlAbzugsgewicht_Fuhre != null){
			m_vSQLStatements.add(sSqlAbzugsgewicht_Fuhre);
		}
		
		
		// die Wiegekarte gleich speichern
		if (!bibMSG.get_bHasAlarms()){

			MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSQLStatements, false);
			bRet &= mv.get_bIsOK();
			bibMSG.add_MESSAGE(mv);
			
			if (bRet){
				// jetzt noch die Wiegekarte-ID ermitteln falls es ein Insert war:
				String sIDWiegekarte = null;
				if (m_REC_Wiegekarte == null){
					String sSQLID_Wiegekarte = "SELECT  " + bibE2.cTO() + ".SEQ_WIEGEKARTE.CURRVAL FROM DUAL";
					sIDWiegekarte = bibDB.EinzelAbfrage(sSQLID_Wiegekarte);
					m_BuchungWiegekarte.setID_WIEGEKARTE(sIDWiegekarte);
				}

				
				bibDB.Commit();

				// falls es eine neue Wiegekarte war, dann gleich den Wiegekarte-Buchungssatzu initialisieren
				if (m_REC_Wiegekarte == null){
					this.InitWK(sIDWiegekarte);
				}
		 				    
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Wiegekarte wurde gespeichert.")));

				
			} else {
				bibDB.Rollback();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler: Die Wiegekarte wurde nicht gespeichert.")));
				bRet = false;
			}

		}
		
		m_vSQLStatements.clear();
		
		return bRet;
		
	}
	
	
	
	
	/**
	 * gibt einen bereinigten Buchungssatz zurück
	 * @param bFolgewiegekarte
	 * @return
	 */
	private WK_Wiegekarte_Buchungssatz getCopyOfCurrent( boolean bFolgewiegekarte ){
		// kopie der alten Wiegekarte
		WK_Wiegekarte_Buchungssatz oCopy = null;
		try {
			oCopy = (WK_Wiegekarte_Buchungssatz)m_BuchungWiegekarte.clone();
			oCopy.setBEFUND(null);
			oCopy.setBEMERKUNG1(null);
			oCopy.setBEMERKUNG2(null);
			oCopy.setBEMERKUNG_INTERN(null);
			oCopy.setEINGANGSSCHEIN_NR(null);
			oCopy.setERZEUGT_AM(null);
			oCopy.setERZEUGT_VON(null);
			oCopy.setGEWICHT_ABZUG(null);
			oCopy.setGEWICHT_NACH_ABZUG(null);
			oCopy.setGRUND_ABZUG(null);
			oCopy.setID_ARTIKEL_BEZ(null);
			oCopy.setID_ARTIKEL_SORTE(null);
			oCopy.setID_WIEGEKARTE(null);
			oCopy.setNETTOGEWICHT(null);
			oCopy.setSORTE_HAND(null);
			oCopy.setSTORNO(null);
			oCopy.setiD_WIEGEKARTE_PARENT(null);
			oCopy.setIST_GESAMTVERWIEGUNG(null);
			oCopy.setLFD_NR(null);
			// korrektur
			oCopy.setCONTAINER_NR(null);
			oCopy.setSIEGEL_NR(null);

			// wenn es eine Folgewiegekarte ist, dann die Fuhre beibehalten
			if (!bFolgewiegekarte) {
				oCopy.setID_VPOS_TPA_FUHRE(null);
				oCopy.setID_VPOS_TPA_FUHRE_ORT(null);
			}	
			
			
			// Falls die ursprungswiegekarte eine Dokumentarische Verwiegung ist, d.h. Ohne Prüfung auf Mindestlast
			// dann die Kopie auf Wiegekarte setzen
			if (oCopy.getTYP_WIEGEKARTE() == null || oCopy.getTYP_WIEGEKARTE().equals(WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH)){
				oCopy.setTYP_WIEGEKARTE(WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN);
			}
			
			if (bFolgewiegekarte){
				if (m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() == null){
					oCopy.setiD_WIEGEKARTE_PARENT(	m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() != null ? 
													m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT() : 
													m_BuchungWiegekarte.getID_WIEGEKARTE());
				} else {
					oCopy.setiD_WIEGEKARTE_PARENT (m_BuchungWiegekarte.getiD_WIEGEKARTE_PARENT());
				}
			}
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Wiegekarten-Kopie fehlgeschlagen. (CloneNotSupported)"));
		}

		return oCopy;
	}
		
	/**
	 * Erzeugt aus der aktuellen Wiegekarte eine Kopie 
	 * und initialisiert diese gleich wieder, sodass der Handler die neue Wiegekarte beinhaltet
	 * 
	 * @param bSaveOld			true: die aktuelle Wiegekarte wird zuerst gespeichert
	 * @param bFolgewiegekarte  true: parentid wird gesetzt
	 * @return
	 * @throws myException 
	 */
	public boolean saveWiegekarteAsCopy(boolean bSaveOld,  boolean bFolgewiegekarte) throws myException{
		boolean bRet = false;
		if (m_BuchungWiegekarte == null) {
			return bRet;
		}
		
		
		// kopie der alten Wiegekarte
		try{
			WK_Wiegekarte_Buchungssatz oCopy = this.getCopyOfCurrent(bFolgewiegekarte);
			
			// jetzt alle records nullen
			m_REC_Wiegekarte = null;
			m_RECLIST_Waegung = null;
			m_REC_Waegung1 = null;
			m_REC_Waegung2 = null;
		
			m_BuchungWaegung1 = null;
			m_BuchungWaegung2 = null;
			
			// die kopierte Wiegekarte übernehmen..
			m_BuchungWiegekarte = oCopy;
			
			// .. und speichern
			bRet = this.saveWiegekarte();
			
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Wiegekarten-Kopie fehlgeschlagen."));
		}
		
		return bRet;
	}
	
	
	/**
	 * Fügt das Nettogewicht der Wiegekarte in die Fuhre ein
	 * @throws myException 
	 */
	public boolean setWiegekartenInFuhre () throws myException{
		boolean bRet = false;
		m_vSQLStatements.clear();
		
		if (m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE() == null){
			return true;
		}
		
		// das Gewicht noch in der Fuhre ablegen, wenn nötig
		if (m_BuchungWaegung1 == null || m_BuchungWaegung2 == null ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Wiegekarte ist nicht vollständig. Das Gewicht kann nicht in die Fuhre übertragen werden.")));
			return false;
		}

		// setze die WiegekartenID in der Fuhre, wenn die Wiegekarte geschrieben wurde.
		// setWiegekarteIDInFuhre(m_BuchungWiegekarte);
		bRet = setGewichtInFuhre(m_BuchungWiegekarte);

		if (!bibMSG.get_bHasAlarms() && m_vSQLStatements.size() > 0 ){
			bRet = bibDB.ExecSQL(m_vSQLStatements, true);
			if (!bRet){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Achtung: Der Gewichtswert konnte nicht in der Fuhre gespeichert werden!")));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Der Gewichtswert wurde in der Fuhre gespeichert!")));
			}
		}
		
		
		
		// 2017-06-26 Manfred
		// Erzeugung des Lager-Konto-Eintrags für die Fuhre: Muss gemacht werden, nachdem das Gewicht in der Fuhre eingetragen wurde,
		// sonst kann das Gewicht nicht ermittelt werden
		m_vSQLStatements.clear();

		LAG_LagerHandler handler = new LAG_LagerHandler(); 
		handler.LagerBuchung(m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE()  );
		m_vSQLStatements.addAll(handler.getSqlStatements() );

		if (!bibMSG.get_bHasAlarms() && m_vSQLStatements.size() > 0 ){
			bRet = bibDB.ExecSQL(m_vSQLStatements, true);
			if (bRet){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Der Lager-Datensatz wurde erzeugt")));
			}
		}
		
		
		
		
		//2012-05-07/Martin: die kontrollmengen aufbauen und in die fuhre reinschreiben (wenn moeglich)
		this._schreibe_kontroll_mengen(m_BuchungWiegekarte);
		//2012-05-07//
		

		m_vSQLStatements.clear();
		return bRet;
		
	}
	
	
	/*
	 * 2012-05-07/Martin: Die gewichtsfelder fuer die kontrolle muessen hier manuell korrigiert werden, da der fuhrenspeicherdaemon nicht aufgerufen wird
	 *             konservativ abgesichert, da noch von manfred zu pruefen
	 */
	private void _schreibe_kontroll_mengen(WK_Wiegekarte_Buchungssatz oWK_Buchungssatz)
	{
		try
		{
			PRUEF_RECORD_VPOS_TPA_FUHRE  oRecFuhreKorr = null;
			
			if (S.isFull(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT()))
			{
				RECORD_VPOS_TPA_FUHRE_ORT  recOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT());
				oRecFuhreKorr = new PRUEF_RECORD_VPOS_TPA_FUHRE(recOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre(),false);
			}
			else if (S.isFull(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE()))
			{
				oRecFuhreKorr = new PRUEF_RECORD_VPOS_TPA_FUHRE(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE(), false);
			}
			
			if (oRecFuhreKorr != null)
			{
				oRecFuhreKorr.__writeSQLStatemtents_MengenSituation_undFuhrenStatus(true);
			}
			
		}
		catch(Exception ex)
		{
			
		}
		
	}
	
	
	

	/**
	 * schreibt das die Wiegekarten-ID bei die Fuhre rein.
	 * @param oWK_Buchungssatz
	 * @throws myException 
	 */
	private void setWiegekarteIDInFuhre(WK_Wiegekarte_Buchungssatz oWK_Buchungssatz) throws myException{
		boolean bIstAblademenge = false;
		Vector<String>  m_vSQLFuhre = new Vector<String>();
		
		if (oWK_Buchungssatz == null) return;
		
		if (oWK_Buchungssatz.getIST_LIEFERANT()!= null && oWK_Buchungssatz.getIST_LIEFERANT().equalsIgnoreCase("Y")){
			bIstAblademenge = true;
		}
		
		RECORD_VPOS_TPA_FUHRE oRecFuhre = null;
		RECORD_VPOS_TPA_FUHRE_ORT oRecFuhreOrt = null;
		
		// Fuhre lesen, die dazu gehört
		if (oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT() != null && !oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT().trim().equals("") ){
			//
			// ****** FUHRENORT *******
			//
			oRecFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT());
			
			// Wiegekarte-ID
			oRecFuhreOrt.set_NEW_VALUE_ID_WIEGEKARTE(oWK_Buchungssatz.getID_WIEGEKARTE());
			oRecFuhreOrt.set_NEW_VALUE_DATUM_LADE_ABLADE(oWK_Buchungssatz.getERZEUGT_AM());
			
			m_vSQLFuhre.add( "UPDATE JT_VPOS_TPA_FUHRE_ORT SET ID_WIEGEKARTE = null WHERE ID_WIEGEKARTE = " + oWK_Buchungssatz.getID_WIEGEKARTE());
			m_vSQLFuhre.add(oRecFuhreOrt.get_SQL_UPDATE_STATEMENT(null,true));
			
			
		} else if (oWK_Buchungssatz.getID_VPOS_TPA_FUHRE() != null && !oWK_Buchungssatz.getID_VPOS_TPA_FUHRE().trim().equals("")){
			//
			// ****** FUHRE *******
			//
			oRecFuhre = new RECORD_VPOS_TPA_FUHRE(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE());
						
			if (bIstAblademenge){
				oRecFuhre.set_NEW_VALUE_ID_WIEGEKARTE_ABN(oWK_Buchungssatz.getID_WIEGEKARTE());
				oRecFuhre.set_NEW_VALUE_WIEGEKARTENKENNER_ABLADEN(oWK_Buchungssatz.getID_WIEGEKARTE());
				oRecFuhre.set_NEW_VALUE_DATUM_ABLADEN(oWK_Buchungssatz.getERZEUGT_AM());
			} else {
				oRecFuhre.set_NEW_VALUE_ID_WIEGEKARTE_LIEF(oWK_Buchungssatz.getID_WIEGEKARTE());
				oRecFuhre.set_NEW_VALUE_WIEGEKARTENKENNER_LADEN(oWK_Buchungssatz.getID_WIEGEKARTE());
				oRecFuhre.set_NEW_VALUE_DATUM_AUFLADEN(oWK_Buchungssatz.getERZEUGT_AM());
			}
			
			m_vSQLFuhre.add("UPDATE JT_VPOS_TPA_FUHRE SET ID_WIEGEKARTE_ABN = null WHERE ID_WIEGEKARTE_ABN = " + oWK_Buchungssatz.getID_WIEGEKARTE());
			m_vSQLFuhre.add("UPDATE JT_VPOS_TPA_FUHRE SET ID_WIEGEKARTE_LIEF = null WHERE ID_WIEGEKARTE_LIEF = " + oWK_Buchungssatz.getID_WIEGEKARTE());
			m_vSQLFuhre.add(oRecFuhre.get_SQL_UPDATE_STATEMENT(null,true));
		}
		
		// alle Statements in die Instanz verlegen
		m_vSQLStatements.addAll(m_vSQLFuhre);

	}


	
	/**
	 * schreibt das nettogewicht und die Wiegekarten-ID bei Fertigstellung der Wiegekarte in die Fuhre rein.
	 *  
	 * @param oWK_Buchungssatz
	 * @param bdGewichtNetto
	 * @throws myException 
	 */
	private boolean setGewichtInFuhre(WK_Wiegekarte_Buchungssatz oWK_Buchungssatz) throws myException{
		boolean bIstAblademenge = false;
		
		boolean bWiegegewichtBeidseitigeintragen = bIstWiegegewichteBeidseitigEintragen();
		
		boolean bFuelleLadegewicht = false;
		boolean bFuelleAbladegewicht = false;
		
		
		if (oWK_Buchungssatz.getIST_LIEFERANT()!= null && oWK_Buchungssatz.getIST_LIEFERANT().equalsIgnoreCase("Y")){
			bIstAblademenge = true;
		}
		
		
		// Gewicht ermitteln
		BigDecimal bdGewichtNetto = oWK_Buchungssatz.getGEWICHT_NACH_ABZUG();
		if (bdGewichtNetto == null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Die Wiegekarte hat kein Nettogewicht. Es wurde kein Eintrag in der Fuhre geschrieben.").CTrans()));
			return false;
		}
		
		String sGewicht = bibALL.convertBigDecimalToString(bdGewichtNetto,3);
		
		RECORD_VPOS_TPA_FUHRE oRecFuhre = null;
		RECORD_VPOS_TPA_FUHRE_ORT oRecFuhreOrt = null;

		/**
		 * 
		 * FUHRENORTE
		 * 
		 */
		if (oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT() != null && !oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT().trim().equals("") ){

			oRecFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE_ORT());
			
			// Festlegen, welche Felder beschrieben werden sollen (Abhängig von Ladeseite/Abladeseite)
			if (bIstAblademenge){
				bFuelleAbladegewicht = true;
				bFuelleLadegewicht = oRecFuhreOrt.get_ABLADEMENGE_RECHNUNG_cUF_NN("N").equals("N");
			} else {
				bFuelleAbladegewicht = oRecFuhreOrt.get_LADEMENGE_GUTSCHRIFT_cUF_NN("N").equals("N");
				bFuelleLadegewicht = true;
			}
			
			
			
			
			if (bFuelleAbladegewicht){
				// prüfen, ob da schon der gleiche wert drin steht...
				if (oRecFuhreOrt.get_ANTEIL_ABLADEMENGE_cUF() != null){
					if (oRecFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0){
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
					}
				} else {
					// Gewicht setzen
					oRecFuhreOrt.set_NEW_VALUE_ANTEIL_ABLADEMENGE(sGewicht);
				}
			}
			
			
			if (bFuelleLadegewicht) {
				// prüfen, ob da schon der gleiche wert drin steht...
				if (oRecFuhreOrt.get_ANTEIL_LADEMENGE_cUF() != null){
					if (oRecFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0 ){
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
					}
				} else {
					// Gewicht setzen
					oRecFuhreOrt.set_NEW_VALUE_ANTEIL_LADEMENGE(sGewicht);
				}
			}			

			
			// Datum setzen, wenn es noch nicht gesetzt war
			if (oRecFuhreOrt.get_DATUM_LADE_ABLADE_cUF() == null) {
				oRecFuhreOrt.set_NEW_VALUE_DATUM_LADE_ABLADE(myDateHelper.ChangeDBFormatStringToNormalString(oWK_Buchungssatz.getERZEUGT_AM()));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Lade-/Abladedatum.").CTrans()));
			}
			
			
			String sSql = null;
			try {
				// versuchen, das Sqlstatement zu lesen. 
				// wenn keine Änderungen im Statement sind, wird eine Exception geschmissen

				sSql = oRecFuhreOrt.get_SQL_UPDATE_STATEMENT(null,true);
				if(!bibALL.isEmpty(sSql)){
					m_vSQLStatements.add(sSql);
				}
			} catch (myException e) {
				//  kein Sql-Statement!!
			}
			
			
		} else if (oWK_Buchungssatz.getID_VPOS_TPA_FUHRE() != null && !oWK_Buchungssatz.getID_VPOS_TPA_FUHRE().trim().equals("")){
			//
			// ****** FUHRE *******
			//
			oRecFuhre = new RECORD_VPOS_TPA_FUHRE(oWK_Buchungssatz.getID_VPOS_TPA_FUHRE());
		

			/**
			 * Abladeseite
			 */
			if (bIstAblademenge || bWiegegewichtBeidseitigeintragen){
				
				bFuelleAbladegewicht = true;
				bFuelleLadegewicht = oRecFuhre.get_ABLADEMENGE_RECHNUNG_cUF_NN("N").equals("N");
				
				if (oRecFuhre.get_PRUEFUNG_ABLADEMENGE_cUF_NN("N").equals("Y")){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Das Gewicht wurde schon geprüft und kann nicht mehr gesetzt werden.").CTrans()));
					return false;
				}

				//
				// Gewichte setzen
				//
				if (bFuelleAbladegewicht) {

					// prüfen, ob da schon irgend ein Wert drin steht...
					if (oRecFuhre.get_ANTEIL_ABLADEMENGE_ABN_cUF() != null){
						// prüfen, ob da schon der gleiche wert drin steht...
						if (oRecFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0 ){
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Ablademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						oRecFuhre.set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(sGewicht);
					}
				}
				
	
				if (bFuelleLadegewicht){
					// prüfen, ob da schon irgend ein Wert drin steht...
					if (oRecFuhre.get_ANTEIL_LADEMENGE_ABN_cUF() != null){
						if (oRecFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0 ){
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Lademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						oRecFuhre.set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(sGewicht);
					}
				}

				
				if (oRecFuhre.get_DATUM_ABLADEN_cUF() == null){
					oRecFuhre.set_NEW_VALUE_DATUM_ABLADEN(myDateHelper.ChangeDBFormatStringToNormalString(oWK_Buchungssatz.getERZEUGT_AM()));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Abladedatum.").CTrans()));
				}

			} 
			

			
			/**
			 * Ladeseite
			 */
			if (!bIstAblademenge || bWiegegewichtBeidseitigeintragen){

				bFuelleAbladegewicht = oRecFuhre.get_LADEMENGE_GUTSCHRIFT_cUF_NN("N").equals("N");
				bFuelleLadegewicht = true;
				
				
				if (oRecFuhre.get_PRUEFUNG_LADEMENGE_cUF_NN("N").equals("Y")){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Das Gewicht wurde schon geprüft und kann nicht mehr gesetzt werden.").CTrans()));
					return false;
				}
								
				//
				// Gewichte setzen
				//
				if (bFuelleAbladegewicht) {

					// prüfen, ob da schon irgend ein Wert drin steht...
					if (oRecFuhre.get_ANTEIL_ABLADEMENGE_LIEF_cUF() != null){
						// prüfen, ob da schon der gleiche wert drin steht...
						if (oRecFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0 ){
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Ablademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						oRecFuhre.set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(sGewicht);
					}
				}
				
				if (bFuelleLadegewicht){
					// prüfen, ob da schon irgend ein Wert drin steht...
					if (oRecFuhre.get_ANTEIL_LADEMENGE_LIEF_cUF() != null){
						if (oRecFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO).compareTo(oWK_Buchungssatz.getGEWICHT_NACH_ABZUG()) != 0 ){
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Lademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						oRecFuhre.set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(sGewicht);
					}
				}
				
				// LADEDATUM
				if (oRecFuhre.get_DATUM_AUFLADEN_cUF() == null){
					oRecFuhre.set_NEW_VALUE_DATUM_AUFLADEN(myDateHelper.ChangeDBFormatStringToNormalString(oWK_Buchungssatz.getERZEUGT_AM()));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Ladedatum.").CTrans()));
				}
			}

			
			String sSql = null;
			try {
				// versuchen, das Sqlstatement zu lesen. 
				// wenn keine Änderungen im Statement sind, wird eine Exception geschmissen

				sSql = oRecFuhre.get_SQL_UPDATE_STATEMENT(null,true);
				if(!bibALL.isEmpty(sSql)){
					m_vSQLStatements.add(sSql);
				}
			} catch (myException e) {
				//  kein Sql-Statement!!
			}
			
		}
		return true;
	}

	
	
	
	/**
	 * Gibt true zurück, wenn die Gewichte in den Fuhren als Lade- und Abladegewicht Eingetragen werden soll,
	 * je nach dem wie der Schalter in der Fuhre steht
	 * Folgende Bedingungen müssen erfüllt sein:
	 *  -* LAGER_UMBUCHUNGEN_ERLAUBEN muss im Mandanten-Zusatz gesetzt sein
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIstWiegegewichteBeidseitigEintragen(){
		
		// nur wenn der Schalter im Mandantenzusatz gesetzt ist
		boolean bErlaubt = __RECORD_MANDANT_ZUSATZ.IS__Value("WIEGEGEWICHTE_BEIDSEITIG_EINTRAGEN", "N", "N");
		
		return bErlaubt;
	}
	
	/**
	 * storniert die aktuelle Wiegekarte
	 * @throws myException 
	 */
	public void storniereWiegekarte(boolean bStorno) throws myException{
		if (m_REC_Wiegekarte != null){
			String sStorno = bStorno == true ? "Y" : null;
			m_REC_Wiegekarte.set_NEW_VALUE_STORNO(sStorno);
			
			String sSql = m_REC_Wiegekarte.get_SQL_UPDATE_STATEMENT(null, true);
			// Statement dazufügen
			m_vSQLStatements.add(sSql);
		}
	}
	
	
	/**
	 * Storniert die Wägung mit der Positionsnummer pos
	 * @param pos
	 * @throws myException 
	 */
	public void storniereWaegung(int pos) throws myException{
		String sSql = null;
		switch (pos) {
		case 1:
			if (m_REC_Waegung1 != null){
				m_REC_Waegung1.set_NEW_VALUE_STORNO("Y");
				sSql = m_REC_Waegung1.get_SQL_UPDATE_STATEMENT(null, true);
			} else {
				// fehler
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wägung 1 kann nicht storniert werden. Es wurde keine Wägung gefunden!")));
			}
			break;
		case 2:
			if (m_REC_Waegung2 != null){
				m_REC_Waegung2.set_NEW_VALUE_STORNO("Y");
				sSql = m_REC_Waegung2.get_SQL_UPDATE_STATEMENT(null, true);
			} else {
				// fehler
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wägung 2 kann nicht storniert werden. Es wurde keine Wägung gefunden!")));
			}
			
			break;

		default:
			break;
		}
		
		if (sSql != null){
			m_vSQLStatements.add(sSql);
		}
	}
	
	
	/**
	 * gibt einen bereinigten Kopierten Wiegekartensatz zurück und 
	 * löscht den originären Wiegekartensatz, damit eine Kopie dargestellt wird,
	 * aber noch nicht abgespeichert wurde.
	 * @return
	 */
	public WK_Wiegekarte_Buchungssatz get_BuchungWiegekarteAsCopyAndClearOriginal( boolean bFolgewiegekarte ) {
		
		WK_Wiegekarte_Buchungssatz oCopy = this.getCopyOfCurrent(bFolgewiegekarte);
		
		return oCopy;
	}
	
	
	
	/**
	 * Getter / Setter
	 */
	
	public String get_ID_Wiegekarte() {
		return m_ID_Wiegekarte;
	}

	public RECORD_WIEGEKARTE get_REC_Wiegekarte() {
		return m_REC_Wiegekarte;
	}

	public RECLIST_WAEGUNG get_RECLIST_Waegung() {
		return m_RECLIST_Waegung;
	}

	public RECORD_WAEGUNG get_REC_Waegung1() {
		return m_REC_Waegung1;
	}

	public RECORD_WAEGUNG get_REC_Waegung2() {
		return m_REC_Waegung2;
	}

	
	public WK_Wiegekarte_Buchungssatz get_BuchungWiegekarte() {
		return m_BuchungWiegekarte;
	}
	public void set_BuchungWiegekarte(WK_Wiegekarte_Buchungssatz oBuchungWiegekarte){
		m_BuchungWiegekarte = oBuchungWiegekarte;
	}
	
	
	public WK_Waegung_Buchungssatz get_BuchungWaegung1() {
		return m_BuchungWaegung1;
	}
	public void set_BuchungWaegung1(WK_Waegung_Buchungssatz oBuchungWaegung){
		m_BuchungWaegung1 = oBuchungWaegung;
	}
	

	public WK_Waegung_Buchungssatz get_BuchungWaegung2() {
		return m_BuchungWaegung2;
	}
	public void set_BuchungWaegung2(WK_Waegung_Buchungssatz oBuchungWaegung){
		m_BuchungWaegung2 = oBuchungWaegung;
	}

	/**
	 * @param m_ErlaubeMehrfachwiegungen the m_ErlaubeMehrfachwiegungen to set
	 */
	public void set_ErlaubeMehrfachwiegungen(boolean m_ErlaubeMehrfachwiegungen) {
		this.m_ErlaubeMehrfachwiegungen = m_ErlaubeMehrfachwiegungen;
	}

	/**
	 * @return the m_ErlaubeMehrfachwiegungen
	 */
	public boolean is_ErlaubeMehrfachwiegungen() {
		return m_ErlaubeMehrfachwiegungen;
	}

	
	
	/**
	 * Methode zum externen Zuordnen von Fuhren
	 * @author manfred
	 * @date 04.10.2017
	 *
	 * @param IDFuhre
	 * @param IDWiegekarte
	 * @return
	 * @throws myException 
	 */
	public boolean setFuhreInWiegekarte(String IDFuhre, String IDFuhreOrt) throws myException{
		boolean bRet = false;

		if (m_BuchungWiegekarte == null) return bRet;
		
		// die ID Fuhre muss immer da sein, auch beim Fuhrenort...
		if (bibALL.isEmpty(IDFuhre)) return bRet;
				
		// FuhrenortID normalisieren
		IDFuhreOrt = IDFuhreOrt == null ? "" : IDFuhreOrt ;  

		// prüfen, ob die Wiegekarte schon eine Fuhre zugewiesen bekommen hat und ob es die gleiche ist...
		String IDFuhreWK = m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE() ;
		String IDFuhreOrtWK = m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE_ORT() ;
		
		IDFuhreWK 		= (IDFuhreWK == null ? "" : IDFuhreWK);
		IDFuhreOrtWK 	= (IDFuhreOrtWK == null ? "" : IDFuhreOrtWK);
		
		
		// nicht die eigene Wiegekaret berücksichtigen...
		String sWhereID = !bibALL.isEmpty(m_BuchungWiegekarte.getID_WIEGEKARTE()) ? " AND ID_WIEGEKARTE != " + m_BuchungWiegekarte.getID_WIEGEKARTE() + " " : "" ;
		
		
		

		if (IDFuhreWK.equals(IDFuhre) && IDFuhreOrtWK.equals(IDFuhreOrt) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Fuhre wurde schon mit der Wiegekarte verknüpft")));
			return false;
		}
		
		if ( (  ! bibALL.isEmpty(IDFuhreWK) && 	
				! (IDFuhreWK+"-"+IDFuhreOrtWK).equals(IDFuhre+"-"+IDFuhreOrt))) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die WK wurde schon einmal mit einer Fuhre verknüpft.!")));
			return false;
		}
		
		//
		// Prüfen, ob die Fuhre schon einmal verwendet wurde.
		//
		if (IDFuhre != null){
			
			RECLIST_WIEGEKARTE reclist_alt = new RECLIST_WIEGEKARTE(
					" nvl(ID_VPOS_TPA_FUHRE,'0') || '-' || nvl(ID_VPOS_TPA_FUHRE_ORT,'')= '" + IDFuhre + "-" + IDFuhreOrt + "' " +
					" AND nvl(STORNO,'N') = 'N' " +  sWhereID , "");
			
			if (reclist_alt.size() > 0){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Fuhre wurde schon einmal mit einer Wiegekarte verknüpft. Bitte ändern Sie die Fuhre oder löschen Sie die Fuhre in der anderen Wiegekarte!")));
				return false;
			}
		} 
		
		if (m_REC_Wiegekarte != null){
			BigDecimal bdIDFuhreOrt = null;
			BigDecimal bdIDFuhre = null;
			
			if (!bibALL.isEmpty(IDFuhre)){
				bdIDFuhre = new BigDecimal(IDFuhre);
				m_REC_Wiegekarte.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(bdIDFuhre);

				// nur wenn Fuhre vorhanden ist, dann auch Fuhreort zulassen
				if (!bibALL.isEmpty(IDFuhreOrt)){
					bdIDFuhreOrt = new BigDecimal(IDFuhreOrt);
					m_REC_Wiegekarte.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(bdIDFuhreOrt);
				}
			}

			
			String sSql = m_REC_Wiegekarte.get_SQL_UPDATE_STATEMENT(null, true);
			
			Vector<String> vStatements = new Vector<>();
			vStatements.add(sSql);
			
			// die Wiegekarte gleich speichern
			if (!bibMSG.get_bHasAlarms()){
				bRet = true;
				MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vStatements, true);
				bRet &= mv.get_bIsOK();
				bibMSG.add_MESSAGE(mv);
				
				if (bRet){
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Wiegekarte wurde gespeichert.")));
				} else {
					bibDB.Rollback();
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler: Die Wiegekarte wurde nicht gespeichert.")));
					bRet = false;
				}
			}
		}
		
		return bRet;
	}
	
	
	
	
	
	/**
	 * Aufnehmen der Camera-Bilder der Capture-Group, welche dem Modul "CAPTURE_WAAGE_AUTOMATIK" zugeordnet sind.
	 * Es wird noch abgefragt, welche Waage zuletzt genutzt wurde. 
	 * @author manfred
	 * @date 11.05.2018
	 *
	 * @return
	 * @throws myException
	 */
	public boolean captureImages(WK_Waegung_Buchungssatz waegung){
		boolean bRet = false;
		
		// Falls der Schalter nicht gesetzt ist, wird abgebrochen
		boolean bCapture = false;
		try {
			bCapture = ENUM_MANDANT_DECISION.WAAGE_AUTO_CAPTURE.is_YES() ;
		} catch (myException e1) {
			bCapture = false;
		}

		if (bCapture){

			// aktuelle Waage-Settings ermitteln
			String id_waage_settings = waegung.getID_WAAGE_SETTINGS();
			if (id_waage_settings == null && waegung.getHANDEINGABE().equalsIgnoreCase("Y")){
				id_waage_settings = "HAND";
			}
			
			// Capture-Gruppe ermitteln 
			IMG_ImageCapture_Handler oIMGCapture = new IMG_ImageCapture_Handler("CAPTURE_WAAGE_AUTOMATIK",id_waage_settings);
			try {
				oIMGCapture.captureSnapshots(m_ID_Wiegekarte);
				bRet = true;
			} catch (myException e) {
				bRet = false;
			}
		}
		
		return bRet;
	}
	
	
	
}
