package rohstoff.Echo2BusinessLogic.INTRASTAT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_INTRASTAT_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_INTRASTAT_MELDUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_INTRASTAT_NEGATIVLISTE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTRASTAT_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTRASTAT_LAND_FINANZAMT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTRASTAT_MELDUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTRASTAT_NEGATIVLISTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_PLZ_BUNDESLAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerPreisHandler;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.utils.bibROHSTOFF;

public class INSTAT_Handler {

	/**  
	 * Enums für die Intrastat-Typen einfuhr/ausfuhr
	 * @author manfred
	 *
	 */
	public static enum ENUM_IntrastatType {
		ENUM_Import,
		ENUM_Export
	} ;
	
	
	
	// Kennzeichner für die Herkunft des Preises
	private static final String Typ_Fuhre = "F";
	private static final String Typ_Rechnung = "R";
	private static final String Typ_Kontrakt = "K";
	private static final String Typ_Angebot = "A";
	
	
	private Vector<String> m_vSqlStatements ;
	// alle zum Zeitraum gefundenen Records die eine Intrastat-Meldung bekommen könnten
	private Vector<INSTAT_Record> m_InstatRecords;
	
	// alle zum Zeitraum schon gemeldeten Intrastat-Records
	private Vector<INSTAT_Record> m_InstatRecordsExisting;
	
	
	
	private String 	m_sIDLand = "";
	private String 	m_sUnterscheidungsnr = "";
	private String  m_sPLZMandant = "";
	private String 	m_sSteuernr = "";
	private String [][] m_sArrDaten = null;
	private int    	m_Paginiernr = 1;
	private int		m_Month = 0;
	private int 	m_Year = 0;
	private String  m_sMonat = null;
	private String  m_sJahr = null;
	private String  m_sBundeslandFA = null;
	
	
	
	private String m_VFuhren = null;
	private String m_IdMandant = null;
	private String m_TableOwner = null;
	
	// (negativ-)Liste der Intrastat-Artikel die nicht gemeldet werden sollen
	// Sql-Where-Bedingung
	private String m_Where_Artikel_Negativliste = "";
	// der Vektor der die ANRS hält
	private Vector<String> m_vArtikel_Negativliste =  new Vector<String>();
	
	// liste der Transportpauschalen für die Abrechnungseinheiten
	private HashMap<String, BigDecimal> m_hmTransportpauschalen = new HashMap<String, BigDecimal>();
	
	Vector<String> vEigenwarenLager = null;

	
	
	public INSTAT_Handler() {
		// TODO Auto-generated constructor stub
		m_vSqlStatements = new Vector<String>();
		m_InstatRecords = new Vector<INSTAT_Record>();
		m_InstatRecordsExisting = new Vector<INSTAT_Record>();
		
		
		m_IdMandant = bibALL.get_ID_MANDANT();
		m_VFuhren =  bibALL.get_TABLEOWNER() + ".V" + m_IdMandant + "_REALGEWICHTE";
		m_TableOwner = bibALL.get_TABLEOWNER();

		// Eigenwarenläger puffern
		try {
			vEigenwarenLager = bibROHSTOFF.get_vLieferadressenEigenwarenlager();
		} catch (myException e) {
			vEigenwarenLager = new Vector<String>();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Löscht die noch nicht exportierten Intrastat-Einträge aus der DB
	 * @param year
	 * @param month
	 */
	public boolean cleanIntrastatRecords(int year, int month, ENUM_IntrastatType type, boolean bDeleteExported){
		boolean bRet = false;
		setYearMonth(year, month);
		
		String sMeldeart = getMeldeartFromIntrastatType(type);
		String sDeleteExported = (bDeleteExported ? "" : " AND " + RECORD_INTRASTAT_MELDUNG.FIELD__EXPORTIERT_AM + " IS NULL ");
		
		String sSql = "DELETE FROM " + bibE2.cTO()+".JT_INTRASTAT_MELDUNG " +
		" WHERE ANMELDEJAHR  = '" + m_sJahr + "'" +
		" AND ANMELDEMONAT =  '" + m_sMonat + "'" +
		" AND NVL(NICHT_MELDEN,'N') = 'N' " + 
		" AND MELDEART = '" + sMeldeart + "'" + sDeleteExported ;	;
		
		m_vSqlStatements.add(sSql);
		
		bRet = executeSqlStatements(true);
		
		return bRet;
	}

	
	/**
	 * Ermittelt die Meldeart aus dem Intrastat-Typ
	 * @param type
	 * @return
	 */
	protected String getMeldeartFromIntrastatType(ENUM_IntrastatType type) {
		// Meldeart aus dem Record-Typ ermitteln
		// dazu ein Record-Objekt öffnen, da diese die Meldeart gesetzt haben.
		String sMeldeart = "";
		if (type == ENUM_IntrastatType.ENUM_Import){
			INSTAT_Record_Einfuhr oEin = new INSTAT_Record_Einfuhr();
			sMeldeart = oEin.get_Meldeart();
			oEin = null;
		} else {
			INSTAT_Record_Ausfuhr oAus = new INSTAT_Record_Ausfuhr();
			sMeldeart = oAus.get_Meldeart();
			oAus = null;
		}
		return sMeldeart;
	}
	
	
	
	/**
	 * Prüft, ob die Intrastat-Liste schon generiert wurde für
	 * Jahr
	 * Monat
	 * Meldetyp,
	 * die noch nicht exportiert wurde
	 * @param year
	 * @param month
	 * @param type
	 * @throws myException 
	 */
	public boolean ListExists(int year, int month, ENUM_IntrastatType type ) throws myException{
		boolean bRet = false;
		setYearMonth(year, month);
		
		String sMeldeart = getMeldeartFromIntrastatType(type);

		RECLIST_INTRASTAT_MELDUNG oList = new RECLIST_INTRASTAT_MELDUNG(
				"SELECT * FROM  " + m_TableOwner + ".JT_INTRASTAT_MELDUNG WHERE ANMELDEMONAT = '" + m_sMonat + "' " +
				" AND ANMELDEJAHR = '" + m_sJahr + "'" +
				" AND MELDEART = " + sMeldeart + 
				" AND EXPORTIERT_AM IS NULL " +
				" AND NVL(NICHT_MELDEN,'N') = 'N' ");
		
		bRet = oList.get_vKeyValues().size() > 0;
			
		return bRet;
	}

	

	/**
	 * Erzeugen der Intrastat-Records in die Tabelle Intrastat-Meldungen.
	 * @param year
	 * @param month
	 * @return anzahl der erzeugten Meldungen
	 * @throws myException
	 */
	public int createIntrastatRecords(int year, int month, ENUM_IntrastatType type) throws myException{
		
		setYearMonth(year, month);
		
		RECORD_MANDANT recMandant = new RECORD_MANDANT(bibALL.get_ID_MANDANT());
		
		m_sIDLand = recMandant.get_ID_LAND_cUF();
		m_sUnterscheidungsnr = recMandant.get_UNTERSCHEIDUNGSNR_INTRASTAT_cUF();
		
		String sAdressId = recMandant.get_EIGENE_ADRESS_ID_cUF();
		RECORD_ADRESSE recAdr = new RECORD_ADRESSE(sAdressId);
		RECLIST_FIRMENINFO reclistInfo = recAdr.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse();
		RECORD_FIRMENINFO recInfo = reclistInfo.get(0);

	
		// nur Zahlen in der Steuernummner, d.h. alles ausser Zahlen aus dem String entfernen 
		try {
			m_sSteuernr = recInfo.get_STEUERNUMMER_cUF();
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(m_sSteuernr);
			m_sSteuernr = m.replaceAll("");
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es ist keine Steuernummer im System vorhanden. Bitte korrigieren."));
			return 0;
		}

		
		
		
		// das Bundesland des zuständigen Finanzamtes lesen
		String sPLZFA = recAdr.get_PLZ_cUF();
		RECORD_PLZ_BUNDESLAND recPLZ = new RECORD_PLZ_BUNDESLAND("PLZ_VON <= '" + sPLZFA + "' AND PLZ_BIS >= '" + sPLZFA + "'");
		String sBL = recPLZ.get_ID_BUNDESLAND_cUF();
		RECORD_INTRASTAT_LAND_FINANZAMT recFA = new RECORD_INTRASTAT_LAND_FINANZAMT("ID_BUNDESLAND = " + sBL);
		m_sBundeslandFA = recFA.get_INTRASTAT_SCHLUESSEL_cUF();
		
		// die PLZ des Mandaten
		m_sPLZMandant = recAdr.get_PLZ_cUF();
		
		if (type == ENUM_IntrastatType.ENUM_Import){
			// die Importe
			m_Paginiernr = 1;
			this.create_list_of_new_INSTAT_RECORDS(ENUM_IntrastatType.ENUM_Import);
		}
		
		if (type == ENUM_IntrastatType.ENUM_Export){
			// die Exporte
			m_Paginiernr = 1;
			this.create_list_of_new_INSTAT_RECORDS(ENUM_IntrastatType.ENUM_Export);
		}
		
		
		// jetzt die Preise für die Records generieren
		this.getPriceForRecords();
		
		// jetzt alle Statements generieren
		for(INSTAT_Record rec : m_InstatRecords){
			m_vSqlStatements.add(rec.GetSqlForInsert());
		}
		
		executeSqlStatements(true);
		
		return m_InstatRecords.size();
	}


	
	
	
	
	/**
	 * Exportieren der intrastat-Liste für den gewünschten Meldetyp
	 * @param year
	 * @param month
	 * @param type
	 * @throws myException
	 */
	public void exportIntrastatRecords(int year, int month, int zaehler, ENUM_IntrastatType type) throws myException
	{
		setYearMonth(year, month);
		String sSQLMain  = 	" SELECT * FROM  " + m_TableOwner + ".JT_INTRASTAT_MELDUNG WHERE ANMELDEMONAT = '" + m_sMonat + "' " +
							" AND ANMELDEJAHR = '" + m_sJahr + "'" +
							" AND NVL(NICHT_MELDEN,'N') = 'N' ";
		

		// Dateinamen lesen aus dem Mandanten:
		String sFileName = "";
		RECORD_MANDANT recM = new RECORD_MANDANT( bibALL.get_ID_MANDANT());
		sFileName =  recM.get_FILENAME_INTRASTAT_IN_cUF_NN("IMPORT_Dateiname muss_angepasst_werden.asc");
		
		// Meldeart ...
		String sWhereType = " AND MELDEART = '1'";  // Einfuhr als Default
		if (type.equals(ENUM_IntrastatType.ENUM_Export)){
			sWhereType = " AND MELDEART = '2'";
			sFileName = recM.get_FILENAME_INTRASTAT_OUT_cUF_NN("EXPORT_dateiname_muss_angepasst_werden.asc");
		}
		sWhereType += " AND NVL(NICHT_MELDEN,'N') = 'N' ";
		
		
		// export in abhängigkeit vom ausgewählten Zählerstand
		String sZaehlerWhere = "";
		if (zaehler > 0){
			sZaehlerWhere += " AND ZAEHLER_MELDUNG = " + String.valueOf(zaehler);
		} else {
			sZaehlerWhere += " AND ZAEHLER_MELDUNG IS NULL ";
		}
		
		
		RECLIST_INTRASTAT_MELDUNG oList = new RECLIST_INTRASTAT_MELDUNG( sSQLMain + 
				sWhereType + sZaehlerWhere + 
				" ORDER BY PAGINIERNUMMER ");
		
		
		// Prüfung, ob noch Intrastat-Meldunge mit Menge oder Preis == 0 vorhanden sind. Diese müssen Korrigiert werden (Schätzwert)
		for (RECORD_INTRASTAT_MELDUNG o: oList.values()){
			if (o.get_EIGENMASSE_cUF_NN("0").equals("0") ||
				(o.get_RECHBETRAG_cUF_NN("0").equals("0") && o.get_EXPORT_NO_PRICE_cUF_NN("N").equals("N") )
				){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt noch Intrastat-Sätze, die einen 0-Betrag oder eine 0-Menge haben. Bitte Schätzwert eintragen, 0-Preis bestätigen oder löschen! "));
			}
		}
		
		
		// Prüfung, ob noch Intrastat-Meldunge vorhanden sind, die keine Warennummer haben
		for (RECORD_INTRASTAT_MELDUNG o: oList.values()){
			if (o.get_WARENNR_cUF()== null || o.get_WARENNR_cUF().trim().equals(""))
				{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt noch Intrastat-Sätze, die keine Warennummer haben. Bitte prüfen Sie die Einträge und korrigieren Sie den Wert in der Fuhre oder dem Artikelstamm."));
			}
		}

		// Wenn aus der Prüfung ein Alarm kommt, beenden!
		if (bibMSG.get_bHasAlarms())
		{
			return;
		}
		
		
		RECORD_INTRASTAT_MELDUNG oRec;
		StringBuilder sb = new StringBuilder();
		
		// jetzt über alle Lagereinträge laufen und den Preis ermitteln
		for (int k=0;k<oList.get_vKeyValues().size();k++)
		{
			try {
				oRec = oList.get(k);
				String sRow = createInstatRecord(oRec).GetRowForExportfile();
				sb.append(sRow); 
				sb.append("\r\n");
				
			} catch (Exception e) {
				// fehler bei der Generierung
				// TODO: Korrektur
			}
		}
		
		
		// der Meldezähler
		String sZaehler = "";
		sZaehler = bibDB.EinzelAbfrage(sSQLMain.replace("*", "max(nvl(ZAEHLER_MELDUNG,0)) + 1 ") + sWhereType );
		if (sZaehler == null || sZaehler.equals("")){
			sZaehler = "1";
		}
		
		
		myTempFile oFile = new myTempFile(sFileName + "_" + m_sJahr + "_" + m_sMonat, ".tmp", false);
		oFile.WriteTextBlock(sb.toString());
		oFile.close();

		E2_Download oDown = new E2_Download();
		oDown.starteFileDownload(oFile.getFileName(),sFileName ,"text/plain");

		// dann noch das Exportdatum setzen, für die die noch nicht exportiert wurden.:
		String sUpdate = "UPDATE  " + m_TableOwner + ".JT_INTRASTAT_MELDUNG " +
				" SET EXPORTIERT_AM = trunc(SYSDATE,'DD') ," +
				" ZAEHLER_MELDUNG = " + sZaehler +
				" WHERE ANMELDEMONAT = '" + m_sMonat + "' " +
				" AND ANMELDEJAHR = '" + m_sJahr + "'" + sWhereType +
				" AND EXPORTIERT_AM IS NULL ";
		
		m_vSqlStatements.add(sUpdate);
		this.executeSqlStatements(true);
	
	}

	

	/**
	 * lesen der Preise für alle erzeugten Intrastat Zeilen
	 * @throws myException 
	 */
	private void getPriceForRecords() throws myException {
		// lesen der Transportkosten-Pauschalen
		initTransportkostenpauschale();
		
		// berechnen der Preise
		for(INSTAT_Record rec : m_InstatRecords){
			getPriceForRecord(rec);
		}
	}

	/**
	 * Ermitteln des Preises für diesen Intrastat-Record 
	 * aus 
	 * 1. Rechnung
	 * 2. Kontrakt
	 * 
	 * @param rec
	 * @throws myException
	 */
	private void getPriceForRecord(INSTAT_Record rec) throws myException {
		
		String sIDFuhre = rec.get_IdVposTpaFuhre();
		String sIDFuhreOrt = rec.get_IdVposTpaFuhreOrt();
		
		// import (einkauf) oder export (verkauf)
		ENUM_IntrastatType type = ENUM_IntrastatType.ENUM_Import;;
		if(rec instanceof INSTAT_Record_Ausfuhr){
			type = ENUM_IntrastatType.ENUM_Export;
		} ;

		
		if (sIDFuhreOrt == null){
			getPreisForFuhre(sIDFuhre, rec, type);
		} else {
			getPreisForFuhrenOrt(sIDFuhre, sIDFuhreOrt, rec, type);
		}

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
	private void getPreisForFuhre( String IDFuhre, INSTAT_Record rec, ENUM_IntrastatType type ) throws myException{	
		
		// ermitteln der Fuhre
		RECORD_VPOS_TPA_FUHRE oFuhre = new RECORD_VPOS_TPA_FUHRE(IDFuhre);

		// die Gesamtmenge
		BigDecimal bdMenge = new BigDecimal(rec.get_Eigenmasse());
		BigDecimal bdDivisor = BigDecimal.ONE;		
		BigDecimal bdEinzelpreis = null;
		BigDecimal bdGesamtpreis = null;
		
		// prüfen, ob die Fuhre eine LL-Fuhre ist, dann spezielle Behandlung:
		// Preis aus Lager
		// Geschäftsart = 12 (Laut Auskunft DESTATIS, telefonisch am 22.1.2014)
		if (vEigenwarenLager.contains(oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) && vEigenwarenLager.contains(oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-")) ){
			int nLagervorzeichen = +1;
			String sDatum = oFuhre.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT().replace("'", "");
			if (type.equals(ENUM_IntrastatType.ENUM_Export)){
				nLagervorzeichen = -1;
				sDatum = oFuhre.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT().replace("'", "");
			}
			
			
			// es gilt immer der Preis des Ausgangslagers
			String idLager = oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-1");

			
			RECORD_ARTIKEL recArtikel = null;
			try {
				recArtikel = new RECORD_ARTIKEL(oFuhre.get_ID_ARTIKEL_cUF_NN("-1"));
				bdDivisor = recArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
			} catch (Exception e) {
				recArtikel = null;
			} 
			
			
			
			
			BigDecimal bdPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(
						idLager, 
						oFuhre.get_ID_ARTIKEL_cUF_NN("-1"), 
						nLagervorzeichen, 
						10, 
						sDatum);
			
			// Gesamtpreis = Preis + Transportkosten
			if (bdPreis == null){
				bdPreis = BigDecimal.ZERO;
			}
			bdGesamtpreis = bdPreis.multiply(bdMenge).divide(bdDivisor);
			bdGesamtpreis = bdGesamtpreis.setScale(0, BigDecimal.ROUND_HALF_UP);
			
			rec.set_Rechbetrag(bdGesamtpreis.toPlainString());
			rec.set_StatistischerBetrag(bdGesamtpreis.toPlainString());
			rec.set_Kostenpauschale("0");
			
			rec.set_Preistyp(Typ_Fuhre); 
			rec.set_Geschaeftsart("12");
			
			return;
		}
		
		
		// ermitteln des Artikels
		RECORD_ARTIKEL oArtikel = oFuhre.get_UP_RECORD_ARTIKEL_id_artikel();
		
		// 1. versuchen, den Preis von der Rechnung zu ermitteln
		RECLIST_VPOS_RG oListeRG = oFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord();

		
		// jetzt den preis für die Lagerposition ermitteln
		RECORD_VPOS_RG oRGCurrent = null;
		RECORD_VPOS_RG oRG;
		
		int countRGPos;
		String sLagerVorzeichen;
		if (type.equals(ENUM_IntrastatType.ENUM_Import)){
			sLagerVorzeichen = "1";
		} else {
			sLagerVorzeichen = "-1";
		}

		

	
		String sPreistyp = "";
		
		countRGPos = 0;
		if (oListeRG != null && oListeRG.get_vKeyValues().size() > 0 ){
			for (int k = 0; k < oListeRG.get_vKeyValues().size(); k++){
				oRG = oListeRG.get(k);
				
				// wenn der Eintrag nicht gelöscht ist 
				// und kein Fuhrenort hat, 
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
				bdEinzelpreis = oRGCurrent.get_EINZELPREIS_RESULT_bdValue(BigDecimal.ZERO, 0);

				//bdGesamtpreis = oRGCurrent.get_GESAMTPREIS_bdValue(BigDecimal.ZERO, 0);
				bdDivisor = oRGCurrent.get_MENGENDIVISOR_bdValue(BigDecimal.ONE, 2);
				bdGesamtpreis = bdEinzelpreis.multiply(bdMenge).divide(bdDivisor);
				
				sPreistyp = Typ_Rechnung;
			}
		}
			
		
		//
		//  wenn nicht, schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
		//
		if (bdGesamtpreis == null){
			oRGCurrent = null;
							
			RECORD_VPOS_KON oKon = null;
			if (type.equals(ENUM_IntrastatType.ENUM_Export)){
				oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk();
			} else {
				oKon = oFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek();
			}

			if (oKon != null){
				bdEinzelpreis = oKon.get_EINZELPREIS_bdValue(BigDecimal.ZERO, 2);
				bdDivisor = oKon.get_MENGENDIVISOR_bdValue(BigDecimal.ONE, 2);
				bdGesamtpreis = bdEinzelpreis.multiply(bdMenge).divide(bdDivisor);
				sPreistyp = Typ_Kontrakt;
			}
		}
		
		
		//
		// sonst versuchen, den Preis von der Fuhre zu ermitteln
		//
		if (bdGesamtpreis == null){
			oRGCurrent = null;
			
			
			bdDivisor = oArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE,2);
			
			BigDecimal bdPreisVK = oFuhre.get_EINZELPREIS_VK_bdValue(BigDecimal.ZERO,2);;
			BigDecimal bdPreisEK = oFuhre.get_EINZELPREIS_EK_bdValue(BigDecimal.ZERO,2);;
			
			
			if (type.equals(ENUM_IntrastatType.ENUM_Export)){
				// zuerst prüfen, ob der VK-Preis eingetragen ist. Sonst nimm den Preis der gegenüberliegenden Seite
				if (bdPreisVK != null && bdPreisVK.compareTo(BigDecimal.ZERO)> 0){
					bdEinzelpreis = bdPreisVK;
				} else {
					bdEinzelpreis = bdPreisEK;
				}
				//bdEinzelpreis = oFuhre.get_EINZELPREIS_VK_bdValue(BigDecimal.ZERO,2);
				
			} else {
				// Import
				if (bdPreisEK != null && bdPreisEK.compareTo(BigDecimal.ZERO) > 0){
					bdEinzelpreis = bdPreisEK;
				} else {
					bdEinzelpreis = bdPreisVK;
				}
				//bdEinzelpreis = oFuhre.get_EINZELPREIS_EK_bdValue(BigDecimal.ZERO,2);
			}
			
			bdGesamtpreis = bdEinzelpreis.multiply(bdMenge).divide(bdDivisor);
			sPreistyp = Typ_Fuhre;
		}
		
		// Transportkostenpauschale berechnen
		BigDecimal bdTransportkosten = null;
		if (bdMenge.compareTo(BigDecimal.ZERO) != 0){
			bdDivisor = oArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
			String sIdEinheit = oArtikel.get_ID_EINHEIT_PREIS_cUF_NN("");
			BigDecimal menge_fuer_transportkosten = bdMenge.divide(bdDivisor,3,BigDecimal.ROUND_HALF_UP);
			
			if (m_hmTransportpauschalen.containsKey(sIdEinheit)){
				BigDecimal bdKosten = m_hmTransportpauschalen.get(sIdEinheit);
				bdTransportkosten = bdKosten.multiply(menge_fuer_transportkosten).abs();
				bdTransportkosten = bdTransportkosten.setScale(0, BigDecimal.ROUND_HALF_UP);
			}else {
				bdTransportkosten = null;
			}
		} 
		

		// Gesamtpreis setzen
		if (bdGesamtpreis != null){
			
			// bei negativem Preis wird die Geschäftsart = Sonstige (99) gesetzt
			// aber der Preis muss positiv ausgewiesen sein laut DATEV (Telefonat mit AMalsam v.29.3.2012)
			if (bdGesamtpreis.compareTo(BigDecimal.ZERO) < 0){
				rec.set_Geschaeftsart("99");
				bdGesamtpreis = bdGesamtpreis.abs();
			}
			
		} else {
			bdGesamtpreis = bdTransportkosten;
		}
		

		// wenn der Gesamtpreis jetzt vorhanden ist, dann setzen der Daten im Record
		if (bdGesamtpreis != null || bdTransportkosten != null){
			
			BigDecimal bdTKtoAdd = BigDecimal.ZERO;
			if (bdTransportkosten != null){
				bdTKtoAdd = bdTransportkosten;
			}
			
			bdGesamtpreis = bdGesamtpreis.setScale(0, BigDecimal.ROUND_HALF_UP);
			
			// Gesamtpreis = Preis + Transportkosten
			rec.set_Rechbetrag(bdGesamtpreis.add(bdTKtoAdd).toPlainString());
			rec.set_StatistischerBetrag(bdGesamtpreis.add(bdTKtoAdd).toPlainString());
			
			// Transport-Kostenpauschale setzen
			rec.set_Kostenpauschale(bdTKtoAdd.toPlainString());
			
			rec.set_Preistyp(sPreistyp); 
		}


		
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
	private void getPreisForFuhrenOrt(String IDFuhre, String IDFuhrenOrt, INSTAT_Record rec, ENUM_IntrastatType type) throws myException{
			
		// ermitteln der Fuhre
		RECORD_VPOS_TPA_FUHRE_ORT oFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(IDFuhrenOrt);

		// Artikel 
		RECORD_ARTIKEL oArtikel = oFuhreOrt.get_UP_RECORD_ARTIKEL_id_artikel();
		
		// ermitteln der Rechnunspositionen der FUhre
		RECLIST_VPOS_RG oListeRG = oFuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord();
		
		BigDecimal bdMenge = new BigDecimal(rec.get_Eigenmasse());
		
		// jetzt den preis für die Lagerposition ermitteln
		RECORD_VPOS_RG oRGCurrent = null;
		RECORD_VPOS_RG oRG;
		
		BigDecimal bdDivisor = BigDecimal.ONE;
		BigDecimal bdEinzelpreis = null;
		BigDecimal bdGesamtpreis = null;
		
		// prüfen, ob die Fuhre eine LL-Fuhre ist, dann spezielle Behandlung:
		// Preis aus Lager
		// Geschäftsart = 12 (Laut Auskunft DESTATIS, telefonisch am 22.1.2014)
		
		// ermitteln der Fuhre
		RECORD_VPOS_TPA_FUHRE oFuhre = new RECORD_VPOS_TPA_FUHRE(IDFuhre);
		String idLagerZiel = null;
		String idLagerStart = null;
		
		if (type.equals(ENUM_IntrastatType.ENUM_Export)){
			idLagerStart 	= oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-");
			idLagerZiel  	= oFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN("*");
		} else {
			idLagerStart  	= oFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN("*");
			idLagerZiel 	= oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-");
		}
		
		

		// prüfen, ob die Fuhre eine LL-Fuhre ist, dann spezielle Behandlung:
		// Preis aus Lager
		// Geschäftsart = 12 (Laut Auskunft DESTATIS, telefonisch am 22.1.2014)
		if (vEigenwarenLager.contains(idLagerStart) && vEigenwarenLager.contains(idLagerZiel) ){
			String sDatum = oFuhreOrt.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT().replace("'", "");
			
			
			
			RECORD_ARTIKEL recArtikel = null;
			try {
				recArtikel = new RECORD_ARTIKEL(oFuhreOrt.get_ID_ARTIKEL_cUF_NN("-1"));
				bdDivisor = recArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
			} catch (Exception e) {
				recArtikel = null;
			} 
			
			int nLagervorzeichen = type.equals(ENUM_IntrastatType.ENUM_Export) ? -1 : +1;
			
			BigDecimal bdPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(
						idLagerStart, 
						oFuhreOrt.get_ID_ARTIKEL_cUF_NN("-1"), 
						nLagervorzeichen, 
						10, 
						sDatum);
			

			bdGesamtpreis = bdPreis.multiply(bdMenge).divide(bdDivisor);
			bdGesamtpreis = bdGesamtpreis.setScale(0, BigDecimal.ROUND_HALF_UP);
			rec.set_Rechbetrag(bdGesamtpreis.toPlainString());
			rec.set_StatistischerBetrag(bdGesamtpreis.toPlainString());
			rec.set_Kostenpauschale("0");
			
			rec.set_Preistyp(Typ_Fuhre); 
			rec.set_Geschaeftsart("12");
			
			return;
		}

		
		
		
		int countRGPos;
		String sLagerVorzeichen;
		if (type.equals(ENUM_IntrastatType.ENUM_Import)){
			sLagerVorzeichen = "1";
		} else {
			sLagerVorzeichen = "-1";
		}


		


		

		
		String idFuhreOrt = oFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		String idLagerAdresseFuhreOrt = oFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN("");
		String idSorteFuhre = oFuhreOrt.get_ID_ARTIKEL_cUF();
		String sPreistyp = "";
		
		
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
				bdEinzelpreis = oRGCurrent.get_EINZELPREIS_RESULT_bdValue(BigDecimal.ZERO, 0);
				bdGesamtpreis = oRGCurrent.get_GESAMTPREIS_bdValue(BigDecimal.ZERO, 0);
				sPreistyp = Typ_Rechnung;

			  }
			}
		}
		
		
		//
		// 2. wenn nicht, schauen, ob Kontraktpreis vorhanden, abhängig davon, ob WA oder WE
		//
		if (bdGesamtpreis == null){
			oRGCurrent = null;
			
			RECORD_VPOS_KON oKon = oFuhreOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon(); 
			if (oKon != null){
				
				if (oKon != null){
					bdEinzelpreis = oKon.get_EINZELPREIS_bdValue(BigDecimal.ZERO, 2);
					bdDivisor = oKon.get_MENGENDIVISOR_bdValue(BigDecimal.ONE, 2);
					bdGesamtpreis = bdEinzelpreis.multiply(bdMenge).divide(bdDivisor);
					sPreistyp = Typ_Kontrakt;
				}
			}
		}

		
		//
		// sonst versuchen, den Preis von der Fuhre zu ermitteln
		//
		if (bdGesamtpreis == null){
			oRGCurrent = null;
			

			bdDivisor = oArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE,2);
			
			bdEinzelpreis = oFuhreOrt.get_EINZELPREIS_bdValue(BigDecimal.ZERO,2);
			
			bdGesamtpreis = bdEinzelpreis.multiply(bdMenge).divide(bdDivisor);
			sPreistyp = Typ_Fuhre;
		}

		
//		if (bdGesamtpreis != null){
//			bdGesamtpreis = bdGesamtpreis.setScale(0, BigDecimal.ROUND_HALF_UP);
//			
//			// bei negativem Preis wird die Geschäftsart = Sonstige (99) gesetzt
//			if (bdGesamtpreis.compareTo(BigDecimal.ZERO) < 0){
//				rec.set_Geschaeftsart("99");
//			}
//			rec.set_Rechbetrag(bdGesamtpreis.toPlainString());
//			rec.set_StatistischerBetrag(bdGesamtpreis.toPlainString());
//			rec.set_Preistyp(sPreistyp);
//		}

		
		
		
		// Transportkostenpauschale berechnen
		BigDecimal bdTransportkosten = null;
		if (bdMenge.compareTo(BigDecimal.ZERO) != 0){
			
			bdDivisor = oArtikel.get_MENGENDIVISOR_bdValue(BigDecimal.ONE);
			String sIdEinheit = oArtikel.get_ID_EINHEIT_PREIS_cUF_NN("");
			BigDecimal menge_fuer_transportkosten = bdMenge.divide(bdDivisor,3,BigDecimal.ROUND_HALF_UP);
			
			if (m_hmTransportpauschalen.containsKey(sIdEinheit)){
				BigDecimal bdKosten = m_hmTransportpauschalen.get(sIdEinheit);
				bdTransportkosten = bdKosten.multiply(menge_fuer_transportkosten).abs();
				bdTransportkosten = bdTransportkosten.setScale(0, BigDecimal.ROUND_HALF_UP);
				
			} else {
				bdTransportkosten = null;
			}
		}
		

		// Gesamtpreis setzen
		if (bdGesamtpreis != null){
			
			// bei negativem Preis wird die Geschäftsart = Sonstige (99) gesetzt
			// aber der Preis muss positiv ausgewiesen sein laut DATEV (Telefonat mit AMalsam v.29.3.2012)
			if (bdGesamtpreis.compareTo(BigDecimal.ZERO) < 0){
				rec.set_Geschaeftsart("99");
				bdGesamtpreis = bdGesamtpreis.abs();
			}
			
		} else {
			bdGesamtpreis = bdTransportkosten;
		}
		
		
		// wenn der Gesamtpreis jetzt vorhanden ist, dann setzen der Daten im Record
		if (bdGesamtpreis != null || bdTransportkosten != null){
			
			BigDecimal bdTKtoAdd = BigDecimal.ZERO;
			if (bdTransportkosten != null){
				bdTKtoAdd = bdTransportkosten;
			}
			
			bdGesamtpreis = bdGesamtpreis.setScale(0, BigDecimal.ROUND_HALF_UP);
			
			// Gesamtpreis = Preis + Transportkosten
			rec.set_Rechbetrag(bdGesamtpreis.add(bdTKtoAdd).toPlainString());
			rec.set_StatistischerBetrag(bdGesamtpreis.add(bdTKtoAdd).toPlainString());
			
			// Transport-Kostenpauschale setzen
			rec.set_Kostenpauschale(bdTKtoAdd.toPlainString());
			
			rec.set_Preistyp(sPreistyp); 
		}

		
		
	}


	
	
	/**
	 * Jahr und Monat in der Instanz setzen.
	 * @param year
	 * @param month
	 */
	private void setYearMonth(int year, int month) {
		m_Month = month;
		m_Year = year;
		
		m_sMonat = String.format("%02d", m_Month);
		m_sJahr = String.format("%4d",m_Year);
	}
	
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht
	 * @author manfred
	 * @date 08.04.2009
	 * @param bCommit
	 * @return
	 */
	private boolean executeSqlStatements(boolean bCommit){
		boolean bRet = true;
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSqlStatements, bCommit);
		bRet &= mv.get_bIsOK();
		
		if ( bCommit){
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
	 * Ermittelt alle möglichen Eintragungen in Intrastat und generiert eine Liste mit 
	 * INSTAT_Record - Objekte
	 * Unabhängig ob diese schon mal generiert und exportiert wurden.
	 * 
	 * Monat und Jahr sind in Membervariablen abgelegt.
	 * 
	 * @param type type
	 * @throws myException 
	 */
	private void create_list_of_all_INSTAT_Records(ENUM_IntrastatType type) throws myException{
		
		String sWhere = "";
		String sREGION = "" ;
		
		if (type == ENUM_IntrastatType.ENUM_Export){
			// beim Export ist das Ursprungsland immer das eigene
			sWhere = " AND ( " +
					 "     V.INTRASTAT_MELD_OUT = '" + TAX_CONST.INTRASTAT_MELDEN_YES +"' " +
					 "   OR ( " +
					 "      V.INTRASTAT_MELD_OUT IS NULL " +
 					 " 		AND NVL(V.OHNE_ABRECHNUNG,'N') = 'N' " + 
			 		 " 		AND NVL(V.ID_ADRESSE_FREMDAUFTRAG,0) = 0 " +
			         " 		AND ADR_LIEF.ID_LAND != ADR_ABN.ID_LAND " + 
			         " 		AND ( ADR_LIEF.ID_LAND = " + m_sIDLand  + " AND (V.ID_VPOS_TPA_FUHRE_ORT IS NULL OR V.DEF_QUELLE_ZIEL = 'VK') )" +
					 " 		AND NVL( LAND_ABN.INTRASTAT_JN,'N' )   = 'Y' " +
					 " 		AND NVL( LAND_LIEF.INTRASTAT_JN,'N') = 'Y' " +
					 " 		)" +
					 " )" ;
			
			sREGION = 	", CASE ADR_LIEF.ID_LAND WHEN " + m_sIDLand + 
						"     then   (  SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_BESTIMM_REGION WHERE JT_INTRASTAT_BESTIMM_REGION.ID_MANDANT = V.ID_MANDANT and ID_BUNDESLAND = ( SELECT DISTINCT ID_BUNDESLAND FROM JT_PLZ_BUNDESLAND WHERE JT_PLZ_BUNDESLAND.ID_MANDANT = V.ID_MANDANT and PLZ_VON <= ADR_LIEF.PLZ AND PLZ_BIS >= ADR_LIEF.PLZ  ) ) " +  
						"     else   ( SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_BESTIMM_REGION WHERE JT_INTRASTAT_BESTIMM_REGION.ID_MANDANT = V.ID_MANDANT and ID_BUNDESLAND = ( SELECT DISTINCT ID_BUNDESLAND FROM JT_PLZ_BUNDESLAND WHERE  JT_PLZ_BUNDESLAND.ID_MANDANT = V.ID_MANDANT and PLZ_VON <= " + m_sPLZMandant + " AND PLZ_BIS >= " + m_sPLZMandant + " )) " +   
						"  END AS  BESTIMM_URSPRUNGS_REGION " ;
			
		} else {
			// Beim Import ist das Land immer das eigene
			sWhere = " AND ( " +
			 		 "     V.INTRASTAT_MELD_IN = '" + TAX_CONST.INTRASTAT_MELDEN_YES +"' " +
			 		 "   OR ( " +
					 "  	V.INTRASTAT_MELD_IN IS NULL " +
					 " 		AND NVL(V.OHNE_ABRECHNUNG,'N') = 'N' " + 
					 " 		AND NVL(V.ID_ADRESSE_FREMDAUFTRAG,0) = 0 " +
					 " 		AND ADR_LIEF.ID_LAND != ADR_ABN.ID_LAND " + 
					 " 		AND ( ADR_ABN.ID_LAND =  " + m_sIDLand + " AND (V.ID_VPOS_TPA_FUHRE_ORT IS NULL OR V.DEF_QUELLE_ZIEL = 'EK') )" +
					 " 		AND NVL(LAND_LIEF.INTRASTAT_JN,'N') = 'Y' " +
					 " 		AND NVL(LAND_ABN.INTRASTAT_JN,'N')   = 'Y' " +
					 "		) " +
					 "	)" ;
			
			sREGION = 	", CASE ADR_ABN.ID_LAND WHEN " + m_sIDLand + 
						"     then   (  SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_BESTIMM_REGION WHERE JT_INTRASTAT_BESTIMM_REGION.ID_MANDANT = V.ID_MANDANT and ID_BUNDESLAND = ( SELECT DISTINCT ID_BUNDESLAND FROM JT_PLZ_BUNDESLAND WHERE JT_PLZ_BUNDESLAND.ID_MANDANT = V.ID_MANDANT and PLZ_VON <= ADR_ABN.PLZ AND PLZ_BIS >= ADR_ABN.PLZ  ) ) " +  
						"     else   ( SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_BESTIMM_REGION WHERE JT_INTRASTAT_BESTIMM_REGION.ID_MANDANT = V.ID_MANDANT and ID_BUNDESLAND = ( SELECT DISTINCT ID_BUNDESLAND FROM JT_PLZ_BUNDESLAND WHERE  JT_PLZ_BUNDESLAND.ID_MANDANT = V.ID_MANDANT and PLZ_VON <= " + m_sPLZMandant + " AND PLZ_BIS >= " + m_sPLZMandant + " )) " +   
						"  END AS  BESTIMM_URSPRUNGS_REGION " ;

		}
		
		String sSelect = 
		"SELECT " +
		"V.ID_VPOS_TPA_FUHRE, " +
	    "V.ID_VPOS_TPA_FUHRE_ORT,  " +
	    "V.DEF_QUELLE_ZIEL, " +
	    "V.L_LAENDERCODE,  " +
	    "V.A_LAENDERCODE,  " +
	    "V.ID_ADRESSE_LAGER_START, " +
	    "V.ID_ADRESSE_START, " +
	    "V.ID_ADRESSE_LAGER_ZIEL, " +
	    "V.ID_ADRESSE_ZIEL, " +
	    "V.DATUM_ABLADEN,  " +
	    "V.DATUM_AUFLADEN,  " +
	    "V.DATUM_ABHOLUNG,  " +
	    "V.DATUM_ANLIEFERUNG,  " +
	    "V.ID_ARTIKEL,  " +
	    "V.TRANSPORTMITTEL, " + 
	    "nvl(V.ZOLLTARIFNR , JT_ARTIKEL.ZOLLTARIFNR), " +
	    "V.EINHEIT_MENGEN,  " +
	    "V.ANTEIL_LADEMENGE_LIEF, " + 
	    "V.ANTEIL_ABLADEMENGE_LIEF,  " +
	    "V.ANTEIL_LADEMENGE_ABN  " +
	    ",V.ANTEIL_ABLADEMENGE_ABN " +
	    ",ADR_LIEF.ID_LAND ID_LAND_LIEF " +
	    ",INTRA_LIEF.INTRASTAT_SCHLUESSEL " + // as INTRASTAT_LAND_LIEF 
	    ",ADR_LIEF.PLZ PLZ_LIEF " +
	    ",ADR_ABN.ID_LAND ID_LAND_ABN " +
	    ",INTRA_ABN.INTRASTAT_SCHLUESSEL " + // as INTRASTAT_LAND_ABN 
	    ",ADR_ABN.PLZ PLZ_ABN " +
	    
	    ",null" +
//	    ",case ADR_ABN.ID_LAND   when " + m_sIDLand +
//	    " then  ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_ABN.PLZ  AND PLZ_BIS >= ADR_ABN.PLZ) " +  
//	    "else  ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_LIEF.PLZ  AND PLZ_BIS >= ADR_LIEF.PLZ)   " +
//	    " end as BUNDESLAND " +

	    ",null " +
//	   " ,case ADR_LIEF.ID_LAND   when " + m_sIDLand +  
//	   "     then  ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_LIEF.PLZ  AND PLZ_BIS >= ADR_LIEF.PLZ) " +  
//	   "     else   ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_ABN.PLZ  AND PLZ_BIS >= ADR_ABN.PLZ)  " +  
//	   " end as LIEF_ABN_BUNDESLAND " +

	    sREGION +
//	   " ,case ADR_LIEF.ID_LAND   when " + m_sIDLand + 
//	   "     then  (SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_URSPRUNG_REG WHERE ID_BUNDESLAND  = ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_LIEF.PLZ  AND PLZ_BIS >= ADR_LIEF.PLZ)) " +  
//	   "     else   (SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_BESTIMM_REGION WHERE ID_BUNDESLAND  = ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_ABN.PLZ  AND PLZ_BIS >= ADR_ABN.PLZ)) " +   
//	   " end as BESTIMM_URSPRUNGS_REGION " +

	   
	   ",null " +
//	    ",case ADR_LIEF.ID_LAND   when  " + m_sIDLand + 
//	    "    then  (SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_LAND_FINANZAMT WHERE ID_BUNDESLAND  = ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_LIEF.PLZ  AND PLZ_BIS >= ADR_LIEF.PLZ)) " +  
//	    "    else   (SELECT INTRASTAT_SCHLUESSEL FROM JT_INTRASTAT_LAND_FINANZAMT WHERE ID_BUNDESLAND  = ( SELECT distinct ID_BUNDESLAND from JT_PLZ_BUNDESLAND WHERE PLZ_VON <=ADR_ABN.PLZ  AND PLZ_BIS >= ADR_ABN.PLZ))    " +
//	    "end as SCHLUESSEL_FA " +
	   
	    ", V.ABZUG_ABLADEMENGE_ABN  " +
	    ", V.ABZUG_LADEMENGE_LIEF " +
	    ", JT_ARTIKEL.ID_EINHEIT_PREIS as Abrechnungseinheit " +

		" FROM  " +
		 		m_VFuhren   + "  V " + 
		"		inner join " + m_TableOwner + ".JT_ARTIKEL 							ON JT_ARTIKEL.ID_ARTIKEL = V.ID_ARTIKEL	" +
		"	    inner join " + m_TableOwner + ".JT_ADRESSE  			ADR_LIEF 	ON V.ID_ADRESSE_LAGER_START = ADR_LIEF.ID_ADRESSE " +
		"	    inner join " + m_TableOwner + ".JD_LAND     			LAND_LIEF 	ON ADR_LIEF.ID_LAND = LAND_LIEF.ID_LAND " +
		"	    left outer join " + m_TableOwner + ".JT_INTRASTAT_LAND 	INTRA_LIEF 	ON ADR_LIEF.ID_LAND = INTRA_LIEF.ID_LAND " +
		
		"	    inner join " + m_TableOwner + ".JT_ADRESSE   			ADR_ABN 	ON 	V.ID_ADRESSE_LAGER_ZIEL = ADR_ABN.ID_ADRESSE " +
		"	    inner join " + m_TableOwner + ".JD_LAND      			LAND_ABN 	ON ADR_ABN.ID_LAND = LAND_ABN.ID_LAND " +
		"	    left outer join " + m_TableOwner + ".JT_INTRASTAT_LAND 	INTRA_ABN 	ON ADR_ABN.ID_LAND = INTRA_ABN.ID_LAND " +
		
		"	WHERE  " +
		"	    nvl(V.IST_STORNIERT,'N') = 'N' " + 
		"	    AND nvl(V.DELETED,'N')   = 'N'  " +
		"	    AND  " +
		"	    (  " +
		"	        to_number(to_char( V.DATUM_ABLADEN,'mm'))     =  " + m_sMonat +
		"	    )  " +
		"	    AND  " +
		"	    (  " +
		"	        to_number(to_char( V.DATUM_ABLADEN,'yyyy'))     = " + m_sJahr + 
		"	    )  " +
		"    AND (V.STATUS_BUCHUNG >= 1  ) " +
		sWhere + 
		" ORDER BY V.ID_VPOS_TPA_FUHRE, V.ID_VPOS_TPA_FUHRE_ORT "
		;

		
		m_sArrDaten = new String[0][0];
		m_sArrDaten =  bibDB.EinzelAbfrageInArray(sSelect,(String)null);
		
		if (m_sArrDaten != null){
			// sonst den Vektor füllen
			for (int i = 0; i < m_sArrDaten.length; i++){
				INSTAT_Record oRec = createInstatRecord(m_sArrDaten[i],type);
				if (oRec != null){
					m_InstatRecords.add(oRec);
				}
			}
		}
	} 

	
	
	/**
	 * Ermittelt eine Liste aller schon geschriebener Intrastat-Meldungen
	 * @param type
	 * @throws myException
	 */
	private void create_list_of_existing_RECORD_INTRASTAT_MELDUNG(ENUM_IntrastatType type) throws myException
	{
		
		String sWhereType = " AND MELDEART = '1'";  // Einfuhr als Default
		if (type.equals(ENUM_IntrastatType.ENUM_Export)){
			sWhereType = " AND MELDEART = '2'";
		}

		RECLIST_INTRASTAT_MELDUNG oList = new RECLIST_INTRASTAT_MELDUNG(
				"SELECT * FROM  " + m_TableOwner + ".JT_INTRASTAT_MELDUNG WHERE ANMELDEMONAT = '" + m_sMonat + "' " +
				" AND ANMELDEJAHR = '" + m_sJahr + "'" + 
				sWhereType +
				" ORDER BY PAGINIERNUMMER ");
		
		RECORD_INTRASTAT_MELDUNG oRec;
		
		// jetzt über alle Lagereinträge laufen und den Preis ermitteln
		for (int k=0;k<oList.get_vKeyValues().size();k++)
		{
			try {
				oRec = oList.get(k);
				m_InstatRecordsExisting.add(createInstatRecord(oRec));
			} catch (Exception e) {
				// fehler bei der Generierung
			}
		}
	}

	
	
	
	
	
	/**
	 * Erzeugt den Whereblock für die Auschlusskriterien der Artikel
	 * @author manfred
	 * @date   14.11.2012
	 * @param sTableAlias
	 */
	private void create_statement_artikel_negativliste(String sTableAlias){
		// die ganze liste lesen
		StringBuilder sbWhere = new StringBuilder();
		
		if (sTableAlias != null && sTableAlias.trim() != ""){
			sTableAlias = "." + sTableAlias.trim();
		} else {
			sTableAlias = "";
		}
		
		try {
			RECLIST_INTRASTAT_NEGATIVLISTE oListNegativliste  = new RECLIST_INTRASTAT_NEGATIVLISTE("","");
			// für jeden Eintrag prüfen, ob es ein einzeleintrag ist oder eine 
			for (RECORD_INTRASTAT_NEGATIVLISTE oRec : oListNegativliste.values()){
				String anr1 = oRec.get_ANR1_cUF();
				String anr1_von = oRec.get_ANR1_VON_cUF();
				String anr1_bis = oRec.get_ANR1_BIS_cUF();
				
				
				// wenn die einzelangabe gesetzt ist, dann diese nehmen...
				if (anr1 != null){
					sbWhere.append(" OR ");
					sbWhere.append( " " + sTableAlias + "anr1 = '" + anr1.trim() + "' ");
				// sonst die von-bis regel anwenden	
				} else if (anr1_von != null || anr1_bis != null){
					//evtl. Nullwerte behandeln
					if (anr1_von == null){
						anr1_von = anr1_bis;
					}
					if (anr1_bis == null){
						anr1_bis = anr1_von;
					}
					sbWhere.append(" OR ");
					sbWhere.append(" " + sTableAlias + "ANR1 between '" + anr1_von.trim() + "' AND '" + anr1_bis.trim() +  "'" ); 
				} else {
					// Fehler, ignorieren!!
				}
			}

			
			if (sbWhere.length() > 0){
				
				// erstes OR entfernen
				sbWhere.delete(0, 3);
				// und klammern
				sbWhere.insert(0,"(");
				sbWhere.append(")");
			}
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m_Where_Artikel_Negativliste = sbWhere.toString();
		
	}
	
	
	/**
	 * Baut eine Liste mit Artikel-IDs auf, die nicht in der Intrastat gemeldet werden sollen
	 * @author manfred
	 * @date   14.11.2012
	 */
	private void initArtikelNegativListe(){
		String [][] asIds = new String[0][0];
		m_vArtikel_Negativliste.clear();
		
		create_statement_artikel_negativliste(null);
		
		// wenn keine Negativ-Liste vorhanden ist, dann abbruch
		if (m_Where_Artikel_Negativliste == null || m_Where_Artikel_Negativliste.length() == 0 ){
			return ;
		}
		
		String sSql = "SELECT DISTINCT ID_ARTIKEL FROM JT_ARTIKEL WHERE " + m_Where_Artikel_Negativliste;
        asIds = bibDB.EinzelAbfrageInArray(sSql);
        
        for (int i=0;i<asIds.length;i++)
		{
			m_vArtikel_Negativliste.add(asIds[i][0]);
		}
	}

	
	/**
	 * Liest die Transportkostenpauschalen pro Abrechnungseinheit in den Puffer
	 * @author manfred
	 * @date   15.11.2012
	 */
	private void initTransportkostenpauschale(){
		
		try {
			RECLIST_INTRASTAT_KOSTEN rl = new RECLIST_INTRASTAT_KOSTEN("","");
			m_hmTransportpauschalen.clear();
			
			// alle Kosten in Hashmap eintragen
			for (RECORD_INTRASTAT_KOSTEN rec : rl.values()){
				String ID_EINHEIT = rec.get_ID_EINHEIT_cUF();
				BigDecimal Kosten = rec.get_KOSTENPAUSCHALE_bdValue(BigDecimal.ZERO);
				if (ID_EINHEIT != null){
					m_hmTransportpauschalen.put(ID_EINHEIT, Kosten);
				}
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * Erzeugt eine Liste von allen möglichen INTRASTAT-Einträgen
	 * ABZÜGLICH der schon erledigten und schon verarbeiteten Einträgen
	 * und legt sie in m_Instat-Records ab.
	 * 
	 * @param type
	 * @throws myException
	 */
	private void create_list_of_new_INSTAT_RECORDS(ENUM_IntrastatType type) throws myException{
		
		// beide Listen erzeugen, die die alle möglichen enthält aus den Fuhren
		this.create_list_of_all_INSTAT_Records(type);
		
		// .. und die die schon existieren.
		this.create_list_of_existing_RECORD_INTRASTAT_MELDUNG(type);
		
		// jetzt die Schnittmenge ermitteln:
		boolean bOK = m_InstatRecords.removeAll(m_InstatRecordsExisting);
		
		
		initArtikelNegativListe();
		
		// jetzt die Paginiernummer neu setzen
		// jetzt alle Statements generieren
		int num = 1;
		for(INSTAT_Record rec : m_InstatRecords){
			if (m_vArtikel_Negativliste.contains(rec.get_IdArtikel()) ){
				// den Artikel nicht zum export freigeben und die paginiernummer == null setzen
				rec.m_Paginiernummer = null;
				rec.m_NichtMelden = "Y";
			} else {
				// ganz normal paginieren
				rec.m_Paginiernummer = String.format("%05d",num++);
				rec.m_NichtMelden = null;
			}
		}
		
	}
	

	/**
	 * Erzeugt ein Objekt vom Typ INSTAT_Record mit Daten die aus den Fuhren ermittelt wurde.
	 * Wird benutzt um die Daten in die Tabelle INTRASTAT_MELDUNGEN zu schreiben
	 * 
	 * @param row
	 * @param type
	 * @return
	 */
	private INSTAT_Record createInstatRecord(String[] row,ENUM_IntrastatType type){
//		String DEF_QUELLE_ZIEL = row[2];
//		String L_LAENDERCODE = row[3];
//		String A_LAENDERCODE = row[4];
//		String ID_ADRESSE_LAGER_START = row[5];
//	    String ID_ADRESSE_START = row[6];
//	    String ID_ADRESSE_LAGER_ZIEL = row[7];
//	    String ID_ADRESSE_ZIEL = row[8];
//	    String DATUM_ABLADEN = row[9];
//	    String DATUM_AUFLADEN = row[10];
//	    String DATUM_ABHOLUNG = row[11];
//	    String DATUM_ANLIEFERUNG = row[12];
//	    String ID_ARTIKEL = row[13];
//	    String TRANSPORTMITTEL = row[14]; 
//	    String EINHEIT_MENGEN = row[16];
//	    String ID_LAND_LAG_LIEF = row[21];
//	    String PLZ_LIEF = row[23];
//	    String ID_LAND_LAGER_ABN = row[24];
//	    String PLZ_ABN = row[26];
//	    String BUNDESLAND = row[27];
//	    String LIEF_ABN_BUNDESLAND = row[28];
//	    String SCHLUESSEL_FA = row[30];

		String ID_VPOS_TPA_FUHRE = row[0];
		String ID_VPOS_TPA_FUHRE_ORT= row[1];
		String ID_ADRESSE_LAGER_START = row[5];
		String ID_ADRESSE_LAGER_ZIEL = row[7];
		String ZOLLTARIFNR = row[15];
		String ANTEIL_LADEMENGE_LIEF = row[17]; 
		String ANTEIL_ABLADEMENGE_LIEF = row[18];
		String ANTEIL_LADEMENGE_ABN  = row[19];
		String ANTEIL_ABLADEMENGE_ABN = row[20];
		String INTRASTAT_LAND_LAG_LIEF = row[22];
		String INTRASTAT_LAND_LAGER_ABN = row[25];
	    String BESTIMM_URSPRUNGS_REGION = row[29];
	    String ABZUG_ABLADEMENGE_ABN = row[31];
	    String ABZUG_LADEMENGE_LIEF = row[32];
	    String ID_ARTIKEL = row[13];
	    String ID_EINHEIT_PREIS = row[33];

	    
	    
	    String sMenge = null;
	    BigDecimal bdMenge = null;
	    String sAbzug = null;
	    BigDecimal bdAbzug = null;
	    
	    
	    INSTAT_Record oRec;
		if (type.equals(ENUM_IntrastatType.ENUM_Import)){
			
			oRec = new INSTAT_Record_Einfuhr();
			
			sMenge = ANTEIL_LADEMENGE_LIEF != null ? ANTEIL_LADEMENGE_LIEF : "0";
			bdMenge = new BigDecimal(sMenge);
			sAbzug = ABZUG_LADEMENGE_LIEF != null ? ABZUG_LADEMENGE_LIEF: "0";
			bdAbzug = new BigDecimal(sAbzug);
			bdMenge = bdMenge.subtract(bdAbzug).setScale(0, BigDecimal.ROUND_HALF_UP);
			
			oRec.m_Eigenmasse = bdMenge.toPlainString();
			if (oRec.m_Eigenmasse.equals("0")) return null;
			
			oRec.m_Bestimmungsland = INTRASTAT_LAND_LAG_LIEF;
			oRec.m_Bestimmungsregion = BESTIMM_URSPRUNGS_REGION;
			oRec.m_IdVposTpaFuhre = ID_VPOS_TPA_FUHRE;
			oRec.m_IdVposTpaFuhreOrt = ID_VPOS_TPA_FUHRE_ORT;
			oRec.m_Land_Ursprung = INTRASTAT_LAND_LAG_LIEF;
			oRec.m_Warennr = ZOLLTARIFNR;
			oRec.m_IdArtikel = ID_ARTIKEL;
			
			oRec.m_Bundesland_FA = m_sBundeslandFA;
			oRec.m_Bezugsjahr = m_sJahr.substring(2);
			oRec.m_Bezugsmonat = m_sMonat;
			oRec.m_Unterscheidungsnr = m_sUnterscheidungsnr;
			
			oRec.m_Geschaeftsart = "11";
			oRec.m_Jahr = m_sJahr;
			oRec.m_Masseinheit = "           ";
			oRec.m_Monat = m_sMonat;
			oRec.m_Paginiernummer = String.format("%05d",m_Paginiernr++);
			oRec.m_Rechbetrag = "";
			oRec.m_StatistischerBetrag ="";
			oRec.m_Steuernr = m_sSteuernr;
			oRec.m_Verkehrszweig = "3";
			oRec.m_IdAbrechnungseinheit = ID_EINHEIT_PREIS;
			
			
			Rec21_adresse recAdr;
			try {
				recAdr = new Rec21_adresse()._fill_id(ID_ADRESSE_LAGER_START);
				Pair<String> pUST = recAdr.getUstLkzUstId();
				if (pUST != null) {
					oRec.m_UmsatzsteuerLKZ = pUST.getVal1();
					oRec.m_UmsatzsteuerID = pUST.getVal2();
				}
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}else{
			
			oRec = new INSTAT_Record_Ausfuhr();
			
			sMenge = ANTEIL_ABLADEMENGE_ABN != null ? ANTEIL_ABLADEMENGE_ABN : "0";
			bdMenge = new BigDecimal(sMenge);
			sAbzug = ABZUG_ABLADEMENGE_ABN != null ? ABZUG_ABLADEMENGE_ABN : "0";
			bdAbzug = new BigDecimal(sAbzug);
			bdMenge = bdMenge.subtract(bdAbzug).setScale(0, BigDecimal.ROUND_HALF_UP);

			oRec.m_Eigenmasse = bdMenge.toPlainString();
			if (oRec.m_Eigenmasse.equals("0")) return null;
			
			oRec.m_Bestimmungsland = INTRASTAT_LAND_LAGER_ABN;
			oRec.m_Bestimmungsregion = BESTIMM_URSPRUNGS_REGION;
			oRec.m_IdVposTpaFuhre = ID_VPOS_TPA_FUHRE;
			oRec.m_IdVposTpaFuhreOrt = ID_VPOS_TPA_FUHRE_ORT;
			oRec.m_Land_Ursprung = "";
			oRec.m_Warennr = ZOLLTARIFNR;
			oRec.m_Bezugsjahr = m_sJahr.substring(2);
			oRec.m_IdArtikel = ID_ARTIKEL;
						
			oRec.m_Bundesland_FA = m_sBundeslandFA;
			oRec.m_Bezugsmonat = m_sMonat;
			oRec.m_Unterscheidungsnr = m_sUnterscheidungsnr;

			oRec.m_Geschaeftsart = "11";
			oRec.m_Jahr = m_sJahr;
			oRec.m_Masseinheit = "           ";
			oRec.m_Monat = m_sMonat;
			oRec.m_Paginiernummer = String.format("%05d",m_Paginiernr++);
			oRec.m_Rechbetrag = "";
			oRec.m_StatistischerBetrag ="";
			oRec.m_Steuernr = m_sSteuernr;
			oRec.m_Verkehrszweig = "3";
			oRec.m_IdAbrechnungseinheit = ID_EINHEIT_PREIS;
			
			Rec21_adresse recAdr;
			try {
				recAdr = new Rec21_adresse()._fill_id(ID_ADRESSE_LAGER_ZIEL);
				Pair<String> pUST = recAdr.getUstLkzUstId();

				if (pUST != null) {
					oRec.m_UmsatzsteuerLKZ = pUST.getVal1();
					oRec.m_UmsatzsteuerID = pUST.getVal2();
				}
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		
		return oRec;
	}
	
	
	
	
	
	
	/**
	 * Erzeugt und initialisiert ein Objekt vom Typ INSTAT_Record aus dem der Tabell
	 * INTRASTAT_MELDUNG
	 * Wird benutzt, um den export zu schreiben.
	 * @param rec
	 * @return
	 * @throws myException 
	 */
	private INSTAT_Record createInstatRecord(RECORD_INTRASTAT_MELDUNG rec) throws myException{
	
	    INSTAT_Record oRec;
		if (rec.get_ANMELDEFORM_cUF().equals("1")){
			//Einfuhr
			
			oRec = new INSTAT_Record_Einfuhr();
		}else{
			
			oRec = new INSTAT_Record_Ausfuhr();
		}			
			
		oRec.m_Bestimmungsland = rec.get_BESTIMM_LAND_cUF_NN("");
		oRec.m_Bestimmungsregion = rec.get_BESTIMM_REGION_cUF_NN("");
		oRec.m_Eigenmasse = rec.get_EIGENMASSE_cUF_NN("");
		oRec.m_IdVposTpaFuhre = rec.get_ID_VPOS_TPA_FUHRE_cUF_NN("");
		oRec.m_IdVposTpaFuhreOrt = rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");
		oRec.m_Land_Ursprung = rec.get_LAND_URSPRUNG_cUF_NN("");
		oRec.m_Warennr = rec.get_WARENNR_cUF_NN("");
		oRec.m_Unterscheidungsnr = rec.get_UNTERSCHEIDUNGSNR_cUF_NN("000");
		oRec.m_Bundesland_FA = rec.get_BUNDESLAND_FA_cUF_NN("");
		oRec.m_Bezugsjahr = rec.get_BEZUGSJAHR_cUF_NN("");
		oRec.m_Bezugsmonat = rec.get_BEZUGSMONAT_cUF_NN("");
		oRec.m_Geschaeftsart = rec.get_GESCHAEFTSART_cUF_NN("");
		oRec.m_Jahr = rec.get_ANMELDEJAHR_cUF_NN("");
		oRec.m_Masseinheit = rec.get_MASSEINHEIT_cUF_NN("");
		oRec.m_Monat = rec.get_ANMELDEMONAT_cUF_NN("");
		oRec.m_Paginiernummer = rec.get_PAGINIERNUMMER_cUF_NN("");
		oRec.m_Rechbetrag = rec.get_RECHBETRAG_cUF_NN("");
		oRec.m_StatistischerBetrag =rec.get_STATISTISCHER_WERT_cUF_NN("");
		oRec.m_Steuernr = rec.get_STEUERNR_cUF_NN("");
		oRec.m_Verkehrszweig = rec.get_VERKEHRSZWEIG_cUF_NN("");
		
		oRec.m_IdArtikel = rec.get_ID_ARTIKEL_cUF_NN("");
		oRec.m_Kostenpauschale = rec.get_FRACHTKOSTEN_cUF_NN("");
		oRec.m_NichtMelden = rec.get_NICHT_MELDEN_cUF_NN("");
		oRec.m_UmsatzsteuerID = rec.get_UMSATZSTEUERID_cUF_NN("");
		oRec.m_UmsatzsteuerLKZ = rec.get_UMSATZSTEUER_LKZ_cUF_NN("");
		return oRec;

	}
	
	
	
	
	/**
	 * löschen der in der Liste angegebenen Intrastat-Meldungen.
	 * Es können nur Meldungen gelöscht werden, die noch nicht exportiert wurden!
	 * Sonst wird eine Exception geworfen
	 * @param IDIntrastat
	 */
	public void deleteIntrastatRecords(int year, int month, Vector<String> IDIntrastat) throws myException {
		
		this.setYearMonth(year, month);
		
		String sIN = bibALL.Concatenate(IDIntrastat, ",", "0");
		sIN = "(" + sIN + ")";
		
		// welche Listen sind betroffen?
		boolean bImport = false;
		boolean bExport = false;
		
		// reclist definieren und aufbauen
		RECLIST_INTRASTAT_MELDUNG list = new RECLIST_INTRASTAT_MELDUNG("ID_INTRASTAT_MELDUNG IN " + sIN, (String)null);
		
		// prüfen, ob alle übergebenen Einträge auch löschbar sind, d.h. noch nicht exportiert waren
		RECORD_INTRASTAT_MELDUNG oRec = null;
		for (int k=0;k<list.get_vKeyValues().size();k++)
		{
			oRec = list.get(k);
			if (oRec.get_MELDEART_cUF_NN("0").equals("1")){
				bImport |= true;
			}
			if (oRec.get_MELDEART_cUF_NN("0").equals("2")){
				bExport |= true;
			}
			
			if (oRec.get_EXPORTIERT_AM_cUF() != null){
				throw new myException("Es können nur Intrastatmeldungen gelöscht werden, die noch nicht exportiert wurden.");
			}
		}

		
		String sSqlDelete = "DELETE FROM " +bibE2.cTO() + ".JT_INTRASTAT_MELDUNG WHERE " + RECORD_INTRASTAT_MELDUNG.FIELD__ID_INTRASTAT_MELDUNG + " in " + sIN; 
		m_vSqlStatements.add(sSqlDelete);
		
		// erst mal dieses Statement durchführen
		executeSqlStatements(false);
		
		
		// Reclist der verbliebenen neu aufbauen
		String sSqlWhere = " NVL(NICHT_MELDEN,'N') = 'N' AND ANMELDEMONAT = '" + m_sMonat + "' "+ " AND ANMELDEJAHR = '" + m_sJahr + "' " + " AND EXPORTIERT_AM IS NULL " ;
		String sOrder = " PAGINIERNUMMER ";
		
		String sWhereType = null;  
		
		//
		// IMPORT
		//
		if (bImport){
			sWhereType = " AND MELDEART = '1'";  
		
			// Liste neu aufbauen mit den zu ändernden Einträgen
			list = new RECLIST_INTRASTAT_MELDUNG(sSqlWhere + sWhereType, sOrder);
			
		
			StringBuilder sb = new StringBuilder();
			int i = 1;
			String sPagNr = "";
			// jetzt über alle Lagereinträge laufen und den Preis ermitteln
			for (int k=0;k<list.get_vKeyValues().size();k++)
			{
				try {
					oRec = list.get(k);
					sPagNr = Integer.toString(i);
					oRec.set_NEW_VALUE_PAGINIERNUMMER(INSTAT_Record.getStringForColumnRight(sPagNr,5,"0"));
					m_vSqlStatements.add(oRec.get_SQL_UPDATE_STATEMENT(null, true));
					i++;
					
				} catch (Exception e) {
					// fehler bei der Generierung
					// TODO: Korrektur
				}
			}
		}
		
		//
		// EXPORT
		//
		if (bExport){
			sWhereType = " AND MELDEART = '2'";  
			
			// Liste neu aufbauen mit den zu ändernden Einträgen
			list = new RECLIST_INTRASTAT_MELDUNG(sSqlWhere + sWhereType, sOrder);
			
		
			StringBuilder sb = new StringBuilder();
			int i = 1;
			String sPagNr = "";
			// jetzt über alle Lagereinträge laufen und den Preis ermitteln
			for (int k=0;k<list.get_vKeyValues().size();k++)
			{
				try {
					oRec = list.get(k);
					sPagNr = Integer.toString(i);
					oRec.set_NEW_VALUE_PAGINIERNUMMER(INSTAT_Record.getStringForColumnRight(sPagNr,5,"0"));
					m_vSqlStatements.add(oRec.get_SQL_UPDATE_STATEMENT(null, true));
					i++;
					
				} catch (Exception e) {
					// fehler bei der Generierung
					// TODO: Korrektur
				}
			}
		}
		
		
		// speichern aller records
		// und committen
		executeSqlStatements(true);
		
	}
	
	
	/**
	 * Invertiert den zustand des Nicht-Melden-Schalters (Y->N, N->Y)
	 * Es können nur Meldungen geändert werden, die noch nicht exportiert wurden!
	 * Sonst wird eine Exception geworfen
	 * @param IDIntrastat
	 */
	public void setIntrastatRecords_NichtMelden_Invertiert(int year, int month, Vector<String> IDIntrastat) throws myException {
		
		this.setYearMonth(year, month);
		
		String sIN = bibALL.Concatenate(IDIntrastat, ",", "0");
		sIN = "(" + sIN + ")";
		
		// welche Listen sind betroffen?
		boolean bImport = false;
		boolean bExport = false;
		
		// reclist definieren und aufbauen
		RECLIST_INTRASTAT_MELDUNG list = new RECLIST_INTRASTAT_MELDUNG("ID_INTRASTAT_MELDUNG IN " + sIN, (String)null);
		
		// prüfen, ob alle übergebenen Einträge auch löschbar sind, d.h. noch nicht exportiert waren
		RECORD_INTRASTAT_MELDUNG oRec = null;
		for (int k=0;k<list.get_vKeyValues().size();k++)
		{
			oRec = list.get(k);
			if (oRec.get_MELDEART_cUF_NN("0").equals("1")){
				bImport |= true;
			}
			if (oRec.get_MELDEART_cUF_NN("0").equals("2")){
				bExport |= true;
			}
			
			if (oRec.get_EXPORTIERT_AM_cUF() != null){
				throw new myException("Es können nur Intrastatmeldungen auf 'Nicht melden' gesetzt werden, die noch nicht exportiert wurden.");
			}
		}

		String sNichtMelden = null;
		
		for (int k=0;k<list.get_vKeyValues().size();k++)
		{
			oRec = list.get(k);
			if (oRec.get_NICHT_MELDEN_cUF_NN("N").equalsIgnoreCase("N")){
				sNichtMelden = "Y";
			} else {
				sNichtMelden = "N";
			}
				
			oRec.set_NEW_VALUE_NICHT_MELDEN(sNichtMelden);
			oRec.set_NEW_VALUE_PAGINIERNUMMER(null);
			
			String sSqlUpdate = oRec.get_SQL_UPDATE_STATEMENT(new Vector<String>(), true); 
			m_vSqlStatements.add(sSqlUpdate);
		}
		
		
		// erst mal dieses Statement durchführen
		executeSqlStatements(false);
		
		
		// Reclist der verbliebenen neu aufbauen
		String sSqlWhere = "NVL(NICHT_MELDEN,'N') = 'N' AND ANMELDEMONAT = '" + m_sMonat + "' "+ " AND ANMELDEJAHR = '" + m_sJahr + "' " + " AND EXPORTIERT_AM IS NULL " ;
		String sOrder = " PAGINIERNUMMER ";
		
		String sWhereType = null;  
		
		//
		// IMPORT
		//
		if (bImport){
			sWhereType = " AND MELDEART = '1'";  
		
			// Liste neu aufbauen mit den zu ändernden Einträgen
			list = new RECLIST_INTRASTAT_MELDUNG(sSqlWhere + sWhereType, sOrder);
			
		
			StringBuilder sb = new StringBuilder();
			int i = 1;
			String sPagNr = "";
			// jetzt über alle Lagereinträge laufen und den Preis ermitteln
			for (int k=0;k<list.get_vKeyValues().size();k++)
			{
				try {
					oRec = list.get(k);
					sPagNr = Integer.toString(i);
					oRec.set_NEW_VALUE_PAGINIERNUMMER(INSTAT_Record.getStringForColumnRight(sPagNr,5,"0"));
					m_vSqlStatements.add(oRec.get_SQL_UPDATE_STATEMENT(null, true));
					i++;
					
				} catch (Exception e) {
					// fehler bei der Generierung
					// TODO: Korrektur
				}
			}
		}
		
		//
		// EXPORT
		//
		if (bExport){
			sWhereType = " AND MELDEART = '2'";  
			
			// Liste neu aufbauen mit den zu ändernden Einträgen
			list = new RECLIST_INTRASTAT_MELDUNG(sSqlWhere + sWhereType, sOrder);
			
		
			StringBuilder sb = new StringBuilder();
			int i = 1;
			String sPagNr = "";
			// jetzt über alle Lagereinträge laufen und den Preis ermitteln
			for (int k=0;k<list.get_vKeyValues().size();k++)
			{
				try {
					oRec = list.get(k);
					sPagNr = Integer.toString(i);
					oRec.set_NEW_VALUE_PAGINIERNUMMER(INSTAT_Record.getStringForColumnRight(sPagNr,5,"0"));
					m_vSqlStatements.add(oRec.get_SQL_UPDATE_STATEMENT(null, true));
					i++;
					
				} catch (Exception e) {
					// fehler bei der Generierung
					// TODO: Korrektur
				}
			}
		}
		
		
		// speichern aller records
		// und committen
		executeSqlStatements(true);
		
	}
	
	
}