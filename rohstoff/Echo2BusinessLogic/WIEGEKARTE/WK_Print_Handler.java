package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.omg.CORBA.TCKind;

import java.util.Set;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BAM_IMPORT.BAMImporter.ENUM_TABLES_TO_CONNECT_TO;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingReminder;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class WK_Print_Handler {
	
	
	public enum ENUM_ADDITIONAL_VALUES {
		ID_WAAGE_STANDORT("ID_WAAGE_STANDORT"), 
		
		ID_ADRESSE_KUNDE("ID_ADRESSE_KUNDE"),
		ID_ADRESSE_LAGER("ID_ADRESSE_LAGER"),
		ID_ADRESSE_SPEDITION("ID_ADRESSE_SPEDITION"),
		ID_ADRESSE_ABN_STRECKE("ID_ADRESSE_ABN_STRECKE"),
	
		
		// Parameter für die neue Wiegekarte, die hier auch gesetzt werden müssen, da eventuell in 
		// Groovy abgefragt
		ID_WIEGEKARTE_PARENT("ID_WIEGEKARTE_PARENT"),
		WE_WA("WE_WA"),	// WE,WA redundant zu EXTRA...
		ID_ARTIKEL("ID_ARTIKEL"),
		ID_ARTIKEL_BEZ("ID_ARTIKEL_BEZ"),
		IST_GESAMTVERWIEGUNG("IST_GESAMTVERWIEGUNG"),
		TYP_WIEGEKARTE("TYP_WIEGEKARTE"),
		GUETERKATEGORIE("GUETERKATEGORIE"),
		KENNZEICHEN("KENNZEICHEN"),
		TRAILER("TRAILER"), 
		ID_EINGANGSSCHEIN("ID_EINGANGSSCHEIN"), 
		ID_ABZUG_GEBINDE("ID_ABZUG_GEBINDE"), 
		NUM_COPY_GEBINDE("NUM_COPY_GEBINDE")		, 
		NUM_COPY_GEBINDE_GESAMT("NUM_COPY_GEBINDE_GESAMT"),
		STORNO("STORNO"),
		ID_WIEGEKARTE_STORNO("ID_WIEGEKARTE_STORNO"),
		ID_CONTAINER_EIGEN("ID_CONTAINER_EIGEN")
		;
	
		
		
		
		
		
		
		;
	    private String     _Name = null;

	 	ENUM_ADDITIONAL_VALUES(String name) {
	       this._Name = name;
	    }
		
		public String Name(){
			return this._Name;
		}
	}
	
	
	//
	// Parameter von Aussen
	//
	private boolean 		m_bDruckeWiegekarte;
	private boolean 		m_bDruckeEingangsschein;
	private boolean 		m_bErzwingeDruckEingangsschein;
	private boolean			m_bPrintBuero;
	private boolean 		m_bDruckeEtikett;
	private boolean       	m_bDruckeHofschein;
	private int				m_nCopyEtikett = 1;
 
	
	//
	// Lieferschein wird nur gedruckt, wenn WL angekreuzt wurde und auch ein WA vorhanden ist.
	private boolean 		m_bDruckeLieferschein = false;

	
	// Vector mit allen zu druckenden IDs
	private String 			m_sIDWiegekarte = null;
	private String			m_sIDEingangsscheinLieferschein = null;
	private boolean		m_bIstLieferant = false;
	
	private String 			m_sIDAdresse = null;
	private String 			m_sIDAdresseLager = null;

	// Kennzeichnung, ob WE oder WA Waegung
//	private String       	m_sZusatzFeldJasperHash = "";
	cZusatzParameter  		m_Zusatzparameter = null;

	// Record der aktuell gedruckten WK 
	private RECORD_WIEGEKARTE  m_recWK = null;
	
	// Der Jasper-Hash als Instanzvariable anlegen, damit die Temporäre Datei nicht gelöscht wird, bevor runtergeladen...
	E2_JasperHASH_STD oJasperHash = null;
	
	// spezielle Jasper-Hash für die Archivierung der Wiegekarte
//	WK_Print_JasperHash_Archiv oJasperHashArchiv = null;
//	E2_JasperHASH_STD oJasperHashArchiv = null;
	
	
	
	public WK_Print_Handler() {
		String s = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()).toString();
		m_bDruckeWiegekarte = false;
		m_bDruckeEtikett = false;
		m_bDruckeEingangsschein = false;
		m_bErzwingeDruckEingangsschein = false;
		m_bPrintBuero = false;
		m_bDruckeHofschein = false;
		m_nCopyEtikett = 1;
		
		m_Zusatzparameter = new cZusatzParameter();
		
		// default-Modulname für die "alte" wiegekarte, zur unterscheidung mit der neuen  "WK_RB"
		addAdditionalParameter("MODULNAME", "WK");
		
		// alle default-Parameter eintragen, die in der neuen WK_RB_* gebraucht werden, und hier benötigt sind,
		// dass die alten Reports mit den neuen Parametern laufen
		// Ablaufkontrolle
		addAdditionalParameter("WE_WA","");
		addAdditionalParameter("ID_ARTIKEL","");
		addAdditionalParameter("ID_ARTIKEL_BEZ","");
		addAdditionalParameter("IST_GESAMTVERWIEGUNG","");
		addAdditionalParameter("TYP_WIEGEKARTE","");
		addAdditionalParameter("GUETERKATEGORIE","");
		addAdditionalParameter("KENNZEICHEN","");
		addAdditionalParameter("TRAILER",""); 
		addAdditionalParameter("ID_EINGANGSSCHEIN",""); 
		addAdditionalParameter("ID_ABZUG_GEBINDE",""); 
		addAdditionalParameter("NUM_COPY_GEBINDE","");		 
		addAdditionalParameter("NUM_COPY_GEBINDE_GESAMT","");
		addAdditionalParameter("STORNO","");
		addAdditionalParameter("ID_WIEGEKARTE_STORNO","");
		addAdditionalParameter("ID_CONTAINER_EIGEN","");
		
	}
	


	
	/**
	 * Initialisiert den Druckerhandler mit genau einer ID
	 * @param sWiegekartenID
	 */
	public WK_Print_Handler(String sWiegekartenID){
		this();
		
		
		if (sWiegekartenID != null){
			m_sIDWiegekarte = sWiegekartenID;
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es wurde keine Wiegekarte zum Drucken angegeben!")));
		}
	}
	
	
	
	/**
	 * Initialisiert den Druckerhandler mit allen selektierten IDs
	 * @param onavigationList
	 */
	public WK_Print_Handler(E2_NavigationList onavigationList){
		this();
		
		if (onavigationList.get_vSelectedIDs_Unformated().size() == 1){
			m_sIDWiegekarte = onavigationList.get_vSelectedIDs_Unformated().firstElement();
			
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss genau eine Wiegekarte ausgewählt sein!")));
		}
	}
	
	
	
	/**
	 * Setzt beliebigen Zusatzwert der in die JasperHash übergeben wird
	 * 
	 * @author manfred
	 * @date   29.10.2015
	 *
	 * @param ParameterName
	 * @param Value
	 */
	public void addAdditionalParameter(String ParameterName, String Value){
		String sValue = "";
		if (bibALL.isEmpty(ParameterName)) {
			return;
		}
		
		if (!bibALL.isEmpty(Value) ){
			sValue = Value;
		}
		
		m_Zusatzparameter.addParameter(ParameterName, sValue);
		
	}
	
	
	/**
	 * Setzt den Kenner ob WE oder WA 
	 * @param sExtra
	 */
	public void setExtraHashValue(String sExtra){
//		if (bibALL.isEmpty(sExtra)){
//			m_sZusatzFeldJasperHash = "";
//		} else {
//			m_sZusatzFeldJasperHash = sExtra;
//		}
		
		addAdditionalParameter("EXTRA", sExtra);
	}
	
	
	/**
	 * Führt den Druck aus und setzt das Druckdatum wenn es noch nicht gesetzt war
	 * 
	 * @throws myException
	 */
	public void Print() throws myException{
		
		if (m_sIDWiegekarte == null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss genau eine Wiegekarte ausgewählt sein!")));
			return;
		}

		// Die Wiegekarte aktualisieren
		UpdateWiegekarte();

		// die Wiegekarte und die dazugehörigen Blätter
		String sFilename = "";
		
		
		if (( m_bDruckeWiegekarte || m_bDruckeEingangsschein ) && !m_bDruckeEtikett && !m_bDruckeHofschein){
			
			// drucken der Wiegekarte(n)
//			oJasperHash = new E2_JasperHASH_STD("wiegekarte",new JasperFileDef_PDF());
//			oJasperHashArchiv = new WK_Print_JasperHash_Archiv("wiegekarte", m_sIDWiegekarte);
			oJasperHash = new E2_JasperHASH_STD("wiegekarte",new JasperFileDef_PDF());
			oJasperHash.set_bWarningOnEmptyPipelineResult(true);
			
			oJasperHash.put("ID_WIEGEKARTE",m_sIDWiegekarte);
			oJasperHash.put("ID_EINGANGSSCHEIN", m_sIDEingangsscheinLieferschein);
			
			// Alle Zusatzparameter zur Hashmap zufügen
			m_Zusatzparameter.AddParameterToJasperHash(oJasperHash);
			
			// Adressid des Kunden übergeben, damit man adressabhängige Verarbeitungen durchführen kann
			oJasperHash.put("ID_ADRESSE", get_IDAdresse() != null ? get_IDAdresse() : "" );
			oJasperHash.put("ID_ADRESSE_LAGER", get_IDAdresseLager()!= null ? get_IDAdresseLager() : "" );

			if (!m_bPrintBuero){
				oJasperHash.put("DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN",( ( (m_bDruckeEingangsschein &&  m_bIstLieferant) || (m_bDruckeEingangsschein && !m_bIstLieferant && m_bDruckeLieferschein ) ) ? "Y":"N") );
				oJasperHash.put("DRUCKE_WIEGEKARTE", ( m_bDruckeWiegekarte ? "Y":"N") );
				oJasperHash.put("DRUCKE_AUSGANG_BUERO", "N" );
				
				if (m_bDruckeWiegekarte){
					sFilename += "WK_" ;
				}
				if (m_bDruckeEingangsschein){
					sFilename += m_bIstLieferant ? "ES_" : "LS_";
				}
				sFilename += m_sIDWiegekarte;

			} else {
				oJasperHash.put("DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN","N" );
				oJasperHash.put("DRUCKE_WIEGEKARTE", "N" );
				oJasperHash.put("DRUCKE_AUSGANG_BUERO", "Y" );
			}
			
			oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
			try {
				oJasperHash.Build_tempFile(false);
				FileWithSendName oFile = oJasperHash.get_oTempFileWithSendeName();
				if (oFile != null){
					oFile.Download_File();
				}
				
			} catch (Exception e) {
				// möglich, dass ein Fehler auftritt da kein Tempfile vorhanden ist.
				e.printStackTrace();
			}
			
			// Nach dem Erzeugen des Reports wird geschaut, ob ein Archiveintrag der Wiegekarte vorhanden ist.
			// Falls ja, wird der verlinkt zu den Adressen und Fuhren.
			ConnectWKArchivToAdditionalRecords();
			
			// Druckzähler hochsetzen, wenn die Wiegekarte gedruckt wird.
			this.UpdateWiegekarte_SetDruckzaehler();
			
			// BORDERCROSSING-MELDUNGEN prüfen
			try {
				bibMSG.add_MESSAGE(new BorderCrossingReminder().check_and_generate_necessary_reminders_from_wiegekarte(m_sIDWiegekarte));
			} catch (Exception e) {
				// sollte nie passieren
			}
			
		}

		
		
		// das Etikett
		if (m_bDruckeEtikett){
			
			int nCopies = m_nCopyEtikett;
			V_JasperHASH v_jasper = new V_JasperHASH();
			
			for (int nCount = 1; nCount <= nCopies; nCount ++){
				// drucken der Wiegekarte(n)
				E2_JasperHASH_STD jasperHash = new E2_JasperHASH_STD("wiegekarte_etikett",new JasperFileDef_PDF());
				jasperHash.set_bWarningOnEmptyPipelineResult(true);

				// Alle Zusatzparameter zur Hashmap zufügen
				m_Zusatzparameter.AddParameterToJasperHash(jasperHash);
				
				// WE beim Wareneingang, WA beim Warenausgang
//				jasperHash.put("EXTRA", m_sZusatzFeldJasperHash);

				jasperHash.put("ID_WIEGEKARTE",m_sIDWiegekarte);
				jasperHash.put("NUM_COPY", Integer.toString(nCount));
				jasperHash.put("NUM_COPY_GESAMT", Integer.toString(nCopies));
				
				jasperHash.put("ID_ADRESSE", get_IDAdresse() != null ? get_IDAdresse() : "" );
				jasperHash.put("ID_ADRESSE_LAGER", get_IDAdresseLager()!= null ? get_IDAdresseLager() : "" );

				v_jasper.add(jasperHash);
				
			}

			v_jasper.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false, true);
			v_jasper.DOWNLOAD_FILES(null);
		}

		
		// der Hofschein
		if (m_bDruckeHofschein){
			
			String sReportName = "waage_hofschein" + (m_bIstLieferant ? "_WE" : "_WA");
			
			
			// drucken des Hofscheins
			oJasperHash = new E2_JasperHASH_STD(sReportName,new JasperFileDef_PDF());
			oJasperHash.set_bWarningOnEmptyPipelineResult(true);
			
			// Alle Zusatzparameter zur Hashmap zufügen
			m_Zusatzparameter.AddParameterToJasperHash(oJasperHash);
			
			// WE beim Wareneingang, WA beim Warenausgang
//			oJasperHash.put("EXTRA", m_sZusatzFeldJasperHash);

			oJasperHash.put("ID_WIEGEKARTE",m_sIDWiegekarte);
			sFilename = "Hofschein_";
			sFilename += m_bIstLieferant ? "WE_" : "WA_";
			sFilename += m_sIDWiegekarte;
			
			oJasperHash.set_cDownloadAndSendeNameStaticPart(sFilename);
			
			try {
				oJasperHash.Build_tempFile(false);
				oJasperHash.get_oTempFileWithSendeName().Download_File();
			} catch (Exception e) {
				// möglich, dass ein Fehler auftritt da kein Tempfile vorhanden ist.
			}
		}
	}

	
	
	
	
	
	/**
	 * Werte in den Wiegekarten aktualisieren:
	 * 1. Druckdatum
	 * 2. Eingangsschein-Nr / Lieferschein-Nr
	 * @throws myException
	 */
	private void UpdateWiegekarte() throws myException{
		boolean bFehler = false;

			Vector<String> vSql = new Vector<String>();
			Date dt = new Date();

			// erste Wiegekarte lesen, um die sequenzer zu initialisieren
			
			String sIDNew = null;
			String sSQLSeqence = null;
			
			
			String sSQLUpdate = null;
			String sSQLWhere = null;
			

			// nur die einzelne WK drucken
			m_recWK= new RECORD_WIEGEKARTE(m_sIDWiegekarte);
			
			m_bIstLieferant = m_recWK.get_IST_LIEFERANT_cUF_NN("N").equals("Y");
			
			// Lieferschein drucken, wenn explizit von aussen angefordert, oder wenn W+L als Typ definiert wird
			m_bDruckeLieferschein = m_recWK.get_TYP_WIEGEKARTE_cUF_NN("-").equals("WL") && !m_bIstLieferant;
			// oder der Druck wird explizit angefordert
			m_bDruckeLieferschein |= m_bErzwingeDruckEingangsschein;
			
			
			// Update der Wiegekarte
			if (m_bDruckeWiegekarte){
				sSQLWhere = " WHERE  WK.ID_WIEGEKARTE = " + m_sIDWiegekarte  ;
				
				sSQLUpdate = 	" UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE WK SET WK.GEDRUCKT_AM = SYSDATE " + sSQLWhere 
								+ " AND GEDRUCKT_AM IS NULL AND NETTOGEWICHT IS NOT NULL ";
				vSql.add(sSQLUpdate);
			}
		
			
			// Update der Liefer/Eingangsschein-Nr. 
			if (m_bDruckeEingangsschein){
				// wenn noch nichts gesetzt war im Eingangsschein/Lieferschein-Nr.
				// wenn es ein Wareneingang ist, dann auch die Eingangsschein-Nr. setzen
				// Das gilt immer für den aktuellen und allen zur "Folgewiegungen" gehörigen Wiegescheine
				if (m_recWK.get_ES_NR_cUF() == null) {
					if ( m_bIstLieferant ) {
						sSQLSeqence = " SELECT SEQ_" + bibALL.get_ID_MANDANT() + "_WK_EINGANGSSCHEIN.NEXTVAL  FROM DUAL "  ;
						sIDNew = bibDB.EinzelAbfrage(sSQLSeqence);
					} else {
						// Lieferschein nur drucken, wenn der Lieferschein auch gedruckt wurde
						if (m_bDruckeLieferschein){
							sSQLSeqence = " SELECT SEQ_" + bibALL.get_ID_MANDANT() + "_WK_LIEFERSCHEIN.NEXTVAL  FROM DUAL "  ;
							sIDNew = bibDB.EinzelAbfrage(sSQLSeqence);
						}
					}
					m_sIDEingangsscheinLieferschein = sIDNew;
				} else {
					m_sIDEingangsscheinLieferschein = m_recWK.get_ES_NR_cUF();
				}
				
				
				// jetzt die Eingangsschein-Nummern setzen, wenn in der Wiegekarte noch kein ES gesetzt war
				if (m_bDruckeEingangsschein && sIDNew != null){
					
					String sIDLieferant = m_recWK.get_ID_ADRESSE_LIEFERANT_cUF() ;
					String sKennzeichen = m_recWK.get_KENNZEICHEN_cUF();
					
					// immer die eigene WK-ID wenn noch nicht gesetzt...
					String sWhereES = " WHERE ES_NR IS NULL AND ID_WIEGEKARTE = " + m_sIDWiegekarte ;
					String sWhereLieferant = "";
					
					if (sIDLieferant != null){
						sWhereLieferant = " AND ID_ADRESSE_LIEFERANT = " + sIDLieferant ;
					}
					
					// und die des dazugehörigen Eingangsscheins 
					if (sKennzeichen != null){
						// oder alle noch nicht zu einem ES gehörenden WK eines Kunden, die schon eine Wiegekarte haben.
						 sWhereES += " OR ( "
						 + " (SELECT typ_wiegekarte FROM jt_wiegekarte  WHERE id_wiegekarte = " + m_sIDWiegekarte + " ) != 'S' "
						 + " AND TYP_WIEGEKARTE != 'S' "
						 + " AND KENNZEICHEN = '" + sKennzeichen + "' " 
						 + sWhereLieferant 
						 + " AND trunc(ERZEUGT_AM,'DD') = " + m_recWK.get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT()
						 + " AND ES_NR IS NULL AND NVL(DRUCKZAEHLER,0) > 0 " 
						 ;
//						 + " AND TYP_WIEGEKARTE = (Select typ_wiegekarte from jt_wiegekarte where id_wiegekarte = " + m_sIDWiegekarte + ") ";
						 if (m_bIstLieferant){
							 sWhereES += " AND NVL(IST_LIEFERANT,'N') = 'Y' )"; 
						 } else {
							 sWhereES += " AND NVL(IST_LIEFERANT,'N') = 'N' )";
						 } 
						 
						 // für den Streckenschein kann es auch sein, dass 2 Einträge ohne Wiegekarte auf einen Streckenschein gedruckt werden sollen. 
						 sWhereES += " OR ( "
						 + " TYP_WIEGEKARTE = 'S' "
						 + " AND TYP_WIEGEKARTE = (Select typ_wiegekarte from jt_wiegekarte where id_wiegekarte = " + m_sIDWiegekarte + ") " 
						 + " AND KENNZEICHEN = '" + sKennzeichen + "' " 
						 + sWhereLieferant 
						 + " AND trunc(ERZEUGT_AM,'DD') = " + m_recWK.get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT()
						 + " AND ES_NR IS NULL AND NVL(DRUCKZAEHLER,0) = 0  "
						 ;
						 if (m_bIstLieferant){
							 sWhereES += " AND NVL(IST_LIEFERANT,'N') = 'Y' )"; 
						 } else {
							 sWhereES += " AND NVL(IST_LIEFERANT,'N') = 'N' )";
						 }
						 
					}

					sSQLUpdate = " UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE WK SET WK.ES_NR = '" + sIDNew + "' " + sWhereES;
					vSql.add(sSQLUpdate);
					
				}
			}
			
			
			if (!bibDB.ExecSQL(vSql, true)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Schreiben der Reportliste")));
				bFehler = true;
			}			

			if (!bFehler) {
				bibDB.Commit();
			} else {
				bibDB.Rollback();
			}

	}

	
	
	/**
	 * Werte in den Wiegekarten aktualisieren:
	 * 1. Druckzähler
	 * @throws myException
	 */
	private void UpdateWiegekarte_SetDruckzaehler() throws myException{
		String sSql = "";
		Vector<String> vSql = new Vector<String>();
		if(m_bDruckeWiegekarte){
			sSql=  " UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE WK SET WK.DRUCKZAEHLER = nvl(WK.DRUCKZAEHLER,0) + 1 " +
				" WHERE WK.ID_WIEGEKARTE = " + m_sIDWiegekarte ;
			vSql.add(sSql);
		}
		
		if (m_bDruckeEingangsschein){
			sSql=  " UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE WK SET DRUCKZAEHLER_EINGANGSSCHEIN = nvl(WK.DRUCKZAEHLER_EINGANGSSCHEIN,0) + 1 " +
			" WHERE WK.ES_NR = '" + m_sIDEingangsscheinLieferschein + "'" ;
			vSql.add(sSql);
		}
		
		
		if (!bibDB.ExecSQL(vSql, true) ) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim setzen des Druckzählers")));
		}

	}

	
	
	
	private void ConnectWKArchivToAdditionalRecords() {
		//suchen, ob bei der Wiegekarte schon eine Archivdatei besteht.
		RECLIST_ARCHIVMEDIEN_ext  	rlMedien = null;
		RECORD_ARCHIVMEDIEN_ext  	oMedien  = null;
		Vector<String> vSql = new Vector<String>();
		String 	sIDMsg = "";
	
		boolean bConnectToAddress = bib_Settigs_Mandant.get_Wiegekarte_ArchivAnAdresseAnbinden();
		boolean bConnectToFuhre   = bib_Settigs_Mandant.get_Wiegekarte_ArchivAnFuhreAnbinden();

		if (!bConnectToAddress && !bConnectToFuhre) return;
		
		
		try {
			String	sIDAdresse 	= m_recWK.get_ID_ADRESSE_LIEFERANT_cUF();
			String  sIDFuhre	= m_recWK.get_ID_VPOS_TPA_FUHRE_cUF();
			
			// prüfen, ob dem Wiegekaten-DS schon ein Archiv-Dolument zugeordent ist (Medienkenner == PRINT)
			rlMedien = new RECLIST_ARCHIVMEDIEN_ext(_DB.WIEGEKARTE, m_sIDWiegekarte, Archiver_CONST.MEDIENKENNER.PRINT);
			
			if (rlMedien.size() > 0){
				// 1. Mediensatz nehmen
				oMedien = new RECORD_ARCHIVMEDIEN_ext(rlMedien.get(0));;
				
				// mit dem Ziel-Records verknüpfen
				String sTables = "";
				if (!bibALL.isEmpty(sIDAdresse) && bConnectToAddress){
					vSql.add(oMedien.connectToAdditionalEntry(_DB.ADRESSE.substring(3), sIDAdresse, Archiver_CONST.MEDIENKENNER.WIEGEKARTE) );
					sTables += "Adresse";
				}
				if (!bibALL.isEmpty(sIDFuhre) && bConnectToFuhre){
					vSql.add(oMedien.connectToAdditionalEntry(_DB.VPOS_TPA_FUHRE.substring(3), sIDFuhre, Archiver_CONST.MEDIENKENNER.WIEGEKARTE ) );
					sTables += (sTables.length() >0 ? " und " : "") + "Fuhre";
				}
				
				
				if (vSql.size() > 0) {
					// die Einträge in der DB eintragen
					bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSql, true) );
					
					if (bibMSG.get_bIsOK()){
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Wiegekarte wurde an " + sTables + " gekoppelt." ));
					}
				}
			}
			
		} catch (myException e) {
			rlMedien = null;
		}
	}
	

	
	
	

	/**
	 * @param m_bPrintWiegekarte the m_bPrintWiegekarte to set
	 */
	public void set_PrintWiegekarte(boolean bPrintWiegekarteEinzeln) {
		this.m_bDruckeWiegekarte = bPrintWiegekarteEinzeln;
	}

	/**
	 * @return the m_bPrintWiegekarte
	 */
	public boolean is_PrintWiegekarte() {
		return m_bDruckeWiegekarte;
	}

	/**
	 * @param m_bPrintEingangsschein the m_bPrintEingangsschein to set
	 */
	public void set_PrintEingangsschein(boolean m_bPrintEingangsschein) {
		this.m_bDruckeEingangsschein = m_bPrintEingangsschein;
	}
	
	
	/**
	 * @return the m_bPrintEingangsschein
	 */
	public boolean is_PrintEingangsschein() {
		return m_bDruckeEingangsschein;
	}
	
	/**
	 * @param bPrintEtikett the m_bPrintEtikett to set
	 */
	public void set_PrintEtikett(boolean bPrintEtikett) {
		this.m_bDruckeEtikett = bPrintEtikett;
	}

	/**
	 * @return the m_bPrintEtikett
	 */
	public boolean is_PrintEtikett() {
		return m_bDruckeEtikett;
	}

	/**
	 * @param m_nCopyEtikett the m_nCopyEtikett to set
	 */
	public void set_nCopyEtikett(int m_nCopyEtikett) {
		this.m_nCopyEtikett = m_nCopyEtikett;
	}

	/**
	 * @return the m_nCopyEtikett
	 */
	public int get_nCopyEtikett() {
		return m_nCopyEtikett;
	}

	/**
	 * @param m_bPrintBuero the m_bPrintBuero to set
	 */
	public void set_PrintBuero(boolean m_bPrintBuero) {
		this.m_bPrintBuero = m_bPrintBuero;
	}

	/**
	 * @return the m_bPrintBuero
	 */
	public boolean is_PrintBuero() {
		return m_bPrintBuero;
	}

	/**
	 * @param m_bPrintBuero the m_bPrintBuero to set
	 */
	public void set_PrintHofschein(boolean m_bPrintHofschein) {
		this.m_bDruckeHofschein = m_bPrintHofschein;
	}

	/**
	 * @return the m_bPrintBuero
	 */
	public boolean is_PrintHofschein() {
		return this.m_bDruckeHofschein;
	}



	/**
	 * @param m_bErzwingeDruckEingangsschein the m_bErzwingeDruckEingangsschein to set
	 */
	public void set_ErzwingeDruckEingangsschein(
			boolean m_bErzwingeDruckEingangsschein) {
		this.m_bErzwingeDruckEingangsschein = m_bErzwingeDruckEingangsschein;
	}




	/**
	 * @return the m_bErzwingeDruckEingangsschein
	 */
	public boolean is_ErzwingeDruckEingangsschein() {
		return m_bErzwingeDruckEingangsschein;
	}




	/**
	 * @param m_sIDAdresse the m_sIDAdresse to set
	 */
	public void set_IDAdresse(String m_sIDAdresse) {
		this.m_sIDAdresse = m_sIDAdresse;
	}




	/**
	 * @return the m_sIDAdresse
	 */
	public String get_IDAdresse() {
		return m_sIDAdresse;
	}




	/**
	 * @param m_sIDAdresseLager the m_sIDAdresseLager to set
	 */
	public void set_IDAdresseLager(String m_sIDAdresseLager) {
		this.m_sIDAdresseLager = m_sIDAdresseLager;
	}




	/**
	 * @return the m_sIDAdresseLager
	 */
	public String get_IDAdresseLager() {
		return m_sIDAdresseLager;
	}
	
	

	/**
	 * Speichert eine Liste von Key/Value-Paaren die als Parameter an den Jasper-Hash übergeben werden
	 * @author manfred
	 * @date 29.10.2015
	 *
	 */
	private class cZusatzParameter{
		private HashMap<String, String> m_Param = new HashMap<String, String>();

		public HashMap<String, String> get_Param() {
			return m_Param;
		}
		
		/**
		 * fügt der Parameterliste einen neuen Wert zu
		 * 
		 * @author manfred
		 * @date   29.10.2015
		 *
		 * @param sParameter
		 * @param sValue
		 */
		public void addParameter (String sParameter, String sValue){
			m_Param.put(sParameter, sValue);
			
		}
		
		/**
		 * Löscht alle Parameter aus der Liste
		 * 
		 * @author manfred
		 * @date   29.10.2015
		 *
		 */
		public void clear(){
			m_Param.clear();
		}
		
		
	
		/**
		 * Anpassung wg WK_RB_*: Nur die Parameter setzen, die noch nicht explizit gesetzt waren
		 * @author manfred
		 * @date 26.05.2021
		 *
		 * @param oJasperHash
		 */
		public void AddParameterToJasperHash(E2_JasperHASH oJasperHash){
//			oJasperHash.putAll(m_Param);
			
			Set<Entry<String, String>> entries = m_Param.entrySet();
			Iterator<Map.Entry<String, String>> it = entries.iterator();
			while (it.hasNext()){
				Map.Entry<String, String>entry = (Map.Entry<String,String>) it.next();
				if (!oJasperHash.containsKey(entry.getKey())) {
					oJasperHash.put(entry.getKey(), entry.getValue());
				}
				
			}
		}
		
		
		
	}

}
