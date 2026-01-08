package panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import org.apache.commons.io.IOUtils;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver.ENUM_ARCHIV_AUFTEILUNG;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;


public class IMG_ImageCapture_Handler {

	private HashSet<String> hsMedienTypen = new HashSet<String>();
	
	String m_sSnapshotModul = "";
	String m_Param1 		= null;
	
	String m_sTableName		= "";
	String m_sSnapshotName  = "";
	
	
	
	// beinhaltet die Infos für den aktuellen Snapshot
	String[][] 	m_sSnapshotDaten = null;
	
	// true wenn Snapshot-Daten vorhanden sind, sonst false
	boolean 	m_bInitialized = false;
	
	
	/**
	 * Konstruktor des Capture-Handlers 
	 * @param sTableName
	 * @param sSnapshotName
	 */
	public IMG_ImageCapture_Handler( String sModulname) {
		this(sModulname,(String)null);
	}
	
	
	/**
	 * Konstruktor mit Parameter...
	 * @author manfred
	 * @date 08.05.2018
	 *
	 * @param sModulname
	 * @param param
	 */
	public IMG_ImageCapture_Handler( String sModulname, String param ) {
		if (initSnapshot(sModulname, param)){
			initMedienTypen();
		}
	}
	
	
	
	/**
	 * initialisiert die Snapshot-Daten und puffert die Infos in einem Array solange das Objekt exisitert
	 * @return
	 */
	private boolean initSnapshot(String sModulname, String param){
		m_sSnapshotModul = sModulname;
		m_Param1 = param;
		
		// null setzen falls Leerzeichen kommt
		if (bibALL.isEmpty(m_Param1) ){
			m_Param1 = null;
		}
		
		
		
		// daten initialiseren
		m_sSnapshotDaten = null;
		m_bInitialized = false;
		
//	 	// lesen der Infos für die Aufnahmen
//		String sSQL = "SELECT "
//		 		+ " KS.SNAPSHOT_URL, "
//		 		+ " KS.STANDORT, "
//		 		+ " SG.BESCHREIBUNG, "
//		 		+ " KS.KAMERA_NAME, "
//		 		+ " SG.MODUL_NAME, "
//		 		+ " SG.TABLE_NAME_TO_BIND_TO ,"
//		 		+ " SG.SNAPSHOT_NAME "
//		 		+ " FROM " + bibE2.cTO() + ".JT_KAMERA_SETTINGS KS "
//		 		+ " INNER JOIN " + bibE2.cTO() + ".JT_KAMERA_SNAPSHOT_ENTRY SE 	ON SE.ID_KAMERA_SETTINGS = KS.ID_KAMERA_SETTINGS "
//		 		+ " INNER JOIN " + bibE2.cTO() + ".JT_KAMERA_SNAPSHOT_GRP SG 	ON SG.ID_KAMERA_SNAPSHOT_GRP = SE.ID_KAMERA_SNAPSHOT_GRP "
//		 		+ " WHERE upper(SG.MODUL_NAME) =  upper('" + m_sSnapshotModul + "')";
//		
//		 
//		m_sSnapshotDaten = bibDB.EinzelAbfrageInArray(sSQL,(String)null);

	 	// lesen der Infos für die Aufnahmen 
		// param1 = modulname
		// parem2 = param1 (z.B. waagesettings_id)
		String sSelect = "SELECT "
		 		+ " KS.SNAPSHOT_URL, "
		 		+ " KS.STANDORT, "
		 		+ " SG.BESCHREIBUNG, "
		 		+ " KS.KAMERA_NAME, "
		 		+ " SG.MODUL_NAME, "
		 		+ " SG.TABLE_NAME_TO_BIND_TO ,"
		 		+ " SG.SNAPSHOT_NAME "
		 		+ " FROM " + bibE2.cTO() + ".JT_KAMERA_SETTINGS KS "
		 		+ " INNER JOIN " + bibE2.cTO() + ".JT_KAMERA_SNAPSHOT_ENTRY SE 	ON SE.ID_KAMERA_SETTINGS = KS.ID_KAMERA_SETTINGS "
		 		+ " INNER JOIN " + bibE2.cTO() + ".JT_KAMERA_SNAPSHOT_GRP SG 	ON SG.ID_KAMERA_SNAPSHOT_GRP = SE.ID_KAMERA_SNAPSHOT_GRP "
		 		+ " WHERE UPPER(SG.MODUL_NAME) =  upper(?) "
		 		+ "       AND UPPER(nvl(SG.PARAM1,'*')) = UPPER(nvl(?,'*'))";
		
		SqlStringExtended sSql = new SqlStringExtended(sSelect);
		sSql.getValuesList().add(new Param_String("Modul",	m_sSnapshotModul));
		sSql.getValuesList().add(new Param_String("Param1", m_Param1)) ;
		
		m_sSnapshotDaten = bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		if (m_sSnapshotDaten != null && m_sSnapshotDaten.length > 0){
			
			// Tabellenname aus der 1. Zeile des Arrays lesen (er ist in allen Zeilen identisch da aus Kopftabelle)
			cSnapshotData oRow = new cSnapshotData(m_sSnapshotDaten[0]);
			m_sTableName 	= oRow.get_Tablename();
			m_sSnapshotName = oRow.get_SnapshotName();
			
			m_bInitialized = true;
		}
		
		return m_bInitialized;
	}
	

	/**
	 * gibt ein Row-Objekt zurück welches einen Button für den Snapshot und einen für die Bild-Maske beinhaltet
	 * Falls es für das Modul keine Snapshot-Gruppe gibt, wird eine leere Row zurückgegeben.
	 * @param oNavList
	 * @return
	 * @throws myException 
	 */
	public MyE2_Row		get_ButtonRow(E2_NavigationList oNavList) throws myException{
		MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		MyE2_Button oBtnSnap = get_ButtonForSnapshot(oNavList);
		MyE2_Button oBtnDisp = get_ButtonForImageDisplay(oNavList);
		
		if (oBtnSnap != null){
			oRow.add(oBtnSnap);
		}
		if (oBtnDisp != null){
			oRow.add(oBtnDisp);
		}
		return oRow;
	}
	
	/**
	 * gibt ein Row-Objekt zurück welches einen Button für den Snapshot und einen für die Bild-Maske beinhaltet
	 * Falls es für das Modul keine Snapshot-Gruppe gibt, wird eine leere Row zurückgegeben.
	 * @param oNavList
	 * @return
	 * @throws myException 
	 */
	public MyE2_Row		get_ButtonRow(String sID) throws myException{
		MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		MyE2_Button oBtnSnap = get_ButtonForSnapshot(sID);
		MyE2_Button oBtnDisp = get_ButtonForImageDisplay(sID);
		
		if (oBtnSnap != null){
			oRow.add(oBtnSnap);
		}
		if (oBtnDisp != null){
			oRow.add(oBtnDisp);
		}
		return oRow;
	}
	
	
	
	/**
	 * Gibt den Button für die Snapshot-Erzeugung des Moduls zurück
	 * null falls keine Snapshot-Definition vorhanden ist
	 * @param oNavList
	 * @return
	 */
	public MyE2_Button get_ButtonForSnapshot(E2_NavigationList oNavList){
		MyE2_Button oButton = null;
	
		if (m_bInitialized){
			oButton = new MyE2_Button(E2_ResourceIcon.get_RI("camera.png") , E2_ResourceIcon.get_RI("camera__.png"));
			oButton.add_oActionAgent(new oAgentCaptureSnapshots(oNavList));
		}
		return oButton;
	}
	
	/**
	 * Gibt den Button für die Snapshot-Erzeugung des Moduls zurück
	 * null falls keine Snapshot-Definition vorhanden ist
	 * @param sID
	 * @return
	 */
	public MyE2_Button get_ButtonForSnapshot(String sID){
		MyE2_Button oButton = null;
	
		if (m_bInitialized){
			oButton = new MyE2_Button(E2_ResourceIcon.get_RI("camera.png") , E2_ResourceIcon.get_RI("camera__.png"));
			oButton.add_oActionAgent(new oAgentCaptureSnapshots(sID));
		}
		return oButton;
	}
	
	
	
	/**
	 * Gibt den Button für die Bilder-Anzeige von Snapshots des Moduls zurück
	 * null falls keine Snapshot-Definition vorhanden ist
	 * @param oNavList
	 * @return
	 */
	public MyE2_Button get_ButtonForImageDisplay(E2_NavigationList oNavList) throws myException{
		MyE2_Button oButtonDia = null;
		if(m_bInitialized){
			oButtonDia = new IMG_Button_Popup_Image_Viewer(oNavList);
		}
		return oButtonDia;
	}
	
	/**
	 * Gibt den Button für die Bilder-Anzeige von Snapshots des Moduls zurück
	 * null falls keine Snapshot-Definition vorhanden ist
	 * @param sID
	 * @return 
	 */
	public MyE2_Button get_ButtonForImageDisplay( String sID){
		MyE2_Button oButtonDia = null;
		if(m_bInitialized){
			oButtonDia = new IMG_Button_Popup_Image_Viewer(m_sTableName, sID);
		}
		return oButtonDia;
	}
	
	
	/**
	 * gibt einen allgemeingültigen ActionAgent zurück, wenn eine NavigatinList zugrunde liegt.
	 * @param oNavList
	 * @return
	 */
	public XX_ActionAgent getActionAgent(E2_NavigationList oNavList){
		return new oAgentCaptureSnapshots(oNavList);
	}
	
	
	/**
	 * gibt einen allgemeingültigen ActionAgent zurück, wenn eine ID zugrunde liegt.
	 * @param sID der Tabelle
	 * @return
	 */	
	public XX_ActionAgent getActionAgent(String sID){
		return new oAgentCaptureSnapshots(sID);
	}
	
	
	
	/**
	 * ActionAgent für das Capturing der Bilder
	 * @author manfred
	 *
	 */
	private class oAgentCaptureSnapshots extends XX_ActionAgent{

		E2_NavigationList m_navList = null;
		String    m_sID = null;
		
		/**
		 * Konstruktor für die NaviList
		 * @param oNavList
		 */
		public oAgentCaptureSnapshots(E2_NavigationList oNavList) {
			m_navList = oNavList;
		}
		
		/**
		 * Konstruktur für eine ID
		 * @param sID
		 */
		public oAgentCaptureSnapshots(String sID) {
			m_sID = sID;
		}
		
		
		
		/**
		 * Eventhhandler unterscheidet ob die NaviList oder die ID gesetzt ist.
		 * Es darf jeweils nur eine ID vorhanden sein.
		 * 
		 */
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// übernehmen der aktuell gewählten ID
			String sID = null;
			Vector<String> vSelectedIDs  = null;

			if (m_navList != null){
				vSelectedIDs = this.m_navList.get_vSelectedIDs_Unformated();
				if (vSelectedIDs.size() == 1) {
					sID = vSelectedIDs.get(0);
				}
			} else {
				if (m_sID != null){
					sID = m_sID;
				}
			}
			
			
			if (sID != null ) {
				captureSnapshots(sID);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss genau ein Datensatz ausgewählt sein"));
			} 
		}
	}
	
	
	
	/**
	 * nimmt die Bilder der Snapshot-Group auf (kann mehrere Kameras beinhalten)
	 * @param sIDKameraSnapshotGroup
	 * @throws myException 
	 */
	 public void captureSnapshots(String sTableID) throws myException {
		 
	 	 if(m_sSnapshotDaten == null || m_sSnapshotDaten.length <= 0){
			 bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Es sind keine Snapshots definiert für MODUL " + m_sSnapshotModul)));
			 return;
		 }

	 	 
		 for (int i=0; i<m_sSnapshotDaten.length; i++){
			 
			 cSnapshotData oRow = new cSnapshotData(m_sSnapshotDaten[i]);
			 
			 
			 if(oRow.get_Tablename() == null || oRow.get_Tablename().trim().length() <= 0){
				 bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Es ist kein Snapshot-Tabellenname definiert für MODUL " + m_sSnapshotModul)));
				 return;
			 }
			 
			 String sFilename = "";
			 String sPath     = "";
			 String sPathAndFilename = "";
			 String sEndung = "";
				

			 Archiver oArchiver = null;;
			
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			 
			 // Datum ist jetzt
			 Date dtReceived = new java.util.GregorianCalendar().getTime();
			 String sDateTime_for_filename = df.format(dtReceived);
				
			 oArchiver = new Archiver("IMG_CAPTURE",dtReceived,ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY);
			
			 // die Endung 
			 sEndung = "jpg";

			 // den Dateinamen zusammensetzen
			 sFilename =sDateTime_for_filename + "_" + oRow.get_SnapshotName() + "_" + oRow.get_Kameraname() + "." + sEndung;
			 
			 // den Pfad
			 sPath = oArchiver.get_cCompleteArchivePath() + File.separator;
			
			 // die Datei lesen und ablegen
			 if (readFileFromURL(oRow.get_Url(), sPath, sFilename)){
				 
				 insertImageInArchivemedien(oArchiver, 
						 oArchiver.get_cSUB_DIR_IN_Archiv(), 
						 sFilename, 
						 sEndung, 
						 "ImageCapture zu ID " + sTableID + System.getProperty("line.separator") + oRow.get_Kameraname() + ";" + oRow.get_Standort() + ";" + oRow.get_Beschreibung() , 
						 oRow.get_Tablename(), 
						 sTableID, 
						 Archiver_CONST.MEDIENKENNER.CAM_CAPTURE.get_DB_WERT());
				 bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString(String.format("Es wurden die Bilder des Snapshot-Moduls %s abgespeichert.", m_sSnapshotName )) )) ;
			 }
		 }
	}

	 
	
	 /**
	 * Capture-Funktion, welche ein Stream aus sURL liest und in Path+Filename ablegt. 
	 * @param sUrl
	 * @param sPath
	 * @param sFilename
	 * @return
	 */
	private boolean readFileFromURL(String sUrl, String sPath, String sFilename ){
			boolean 			bRet = false;
			URL 				oURL = null;
			FileOutputStream 	out  = null;
			InputStream 		in 	 = null;
			
			
			
			try {
				if (!sUrl.startsWith("http://") && !sUrl.startsWith("shttp://") ){
					sUrl = "http://" +  sUrl;
				}
				
				if (!sPath.endsWith(File.separator)){
					sPath = sPath + File.separator;
				}
				
				
				oURL = new URL(sUrl);
				
				//
				// prüfen ob die Kamera erreichbar ist...
				//
				String sHost = oURL.getHost();
				bRet = InetAddress.getByName(sHost).isReachable(1000);
				
				if (!bRet){
					bibMSG.add_MESSAGE(new MyE2_Info_Message("ACHTUNG: Kamera " + sHost + " ist nicht erreichbar!"));
					return bRet;
				}
				
			    URLConnection uc = oURL.openConnection();
			    
			    String contentType = uc.getContentType();
			    int contentLength = uc.getContentLength();

			    if (contentType.startsWith("text/") || contentLength == -1) {
			      throw new IOException("This is not a binary file.");
			    }
			    
			    InputStream raw = uc.getInputStream();
			    out = new FileOutputStream(sPath + sFilename);
			    
			    IOUtils.copy(raw, out);
			    raw.close();
			    out.close();
			    
			    bRet = true;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch( Exception e3) {
				e3.printStackTrace();
			}
			
			finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (out != null)
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		  
			return bRet;
		}

	
	
	/**
	 * Prüft den Medientyp und trägt den Anhang in die JT_ARCHIVMEDIEN ein, falls gültig.
	 * Sonst wird eine Alarmmessage geschrieben und "false" zurückgeliefert.
	 *  
	 * @author manfred
	 * @date   28.02.2013
	 * @param oArchiver
	 * @param Path
	 * @param Filename
	 * @param sEndung
	 * @param sDescription
	 * @param sTable
	 * @param sObjectID
	 * @param sMedienkenner
	 * @return
	 */
	private boolean insertImageInArchivemedien(Archiver oArchiver,
												String Path,
												String Filename, 
												String sEndung,
												String sDescription,
												String sTable,
												String sObjectID,
												String sMedienkenner){
		boolean bRet = false;
		
		// wenn die Dateiendung nicht bekannt ist, wird der Anhang nicht gespeichert!!
        if ( hsMedienTypen.contains(sEndung.toUpperCase()) ){
        	
        	try {
        		oArchiver.WriteFileInfoToArchiveTable(Path,
        				Filename,
        				Filename,
        				sDescription ,
        				null,
        				null,
        				sTable, 
        				sObjectID,
        				sEndung,
        				sMedienkenner,
        				null,
        				null,
        				null,
        				null);
        		bRet = true;
        		
        	} catch (myException e) {
        		e.printStackTrace();
        		bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Datei " + Filename + " konnte nicht archiviert werden! " ));
        	}
        	
        } else {
        	bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Datei " + Filename + " ist kein gültiges Medium zur Archivierung! " ));
        }
        
		return bRet;
	}
	
	
	
	
	/**
	 * Initialisiert eine Liste von Medientypen, die man als Anhang automatisch hochladen darf.
	 * @author manfred
	 * @date   28.02.2013
	 */
	private void initMedienTypen (){
		hsMedienTypen.clear();
        
		String[][] sIDMedienTypen = bibDB.EinzelAbfrageInArray("SELECT upper(DATEIENDUNG) FROM "+ bibE2.cTO() + ".JT_MEDIENTYP " );

        for (int i=0; i<sIDMedienTypen.length; i++){
        	   hsMedienTypen.add(sIDMedienTypen[i][0]);
        }
	}
	
	
	
	
	private class cSnapshotData {
		
		private String sUrl 			= null;
		private String sStandort 		= null;
		private String sBeschreibung 	= null;
		private String sKameraname    	= null;
		 
		private String sModulname     	= null;
		private String sTablename		= null;
		private String sSnapshotName  	= null;
		
		/**
		 * extrahiert die Daten 
		 * @param arrRow
		 */
		public cSnapshotData(String[] arrRow) {
			if(arrRow != null && arrRow.length > 0){
				extractData(arrRow);
			}
		}

		
		/**
		 * Umsetzen des Arrays 
		 * @param arrRow
		 */
		private void extractData(String[] arrRow) {
			int j = 0;
			 sUrl 			= arrRow[j++];
			 sStandort 		= arrRow[j++];
			 sBeschreibung 	= arrRow[j++];
			 sKameraname    = arrRow[j++];
			 
			 sModulname     = arrRow[j++];
			 sTablename		= arrRow[j++];
			 sSnapshotName  = arrRow[j++];
		}
		
		
		public String get_Url() {
			return sUrl;
		}

		public String get_Standort() {
			return sStandort;
		}

		public String get_Beschreibung() {
			return sBeschreibung;
		}

		public String get_Kameraname() {
			return sKameraname;
		}

		public String get_Modulname() {
			return sModulname;
		}

		public String get_Tablename() {
			return sTablename;
		}

		public String get_SnapshotName() {
			return sSnapshotName;
		}

		
	}
}
