package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.TCKind;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibFILE;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver.ENUM_ARCHIV_AUFTEILUNG;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.archive.ArchiverFileChecker;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.query.ID;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class BAMImporter implements ICallableTask{
	
	
	// aktuelle Tabellen, an die man die Bilder der importierten BAM anhängen kann
	public enum ENUM_TABLES_TO_CONNECT_TO {
		WIEGEKARTE,
		VPOS_TPA_FUHRE,
		VPOS_TPA_FUHRE_ORT};
	
	
	/** The directory to move "processed" import files to. 
	 * This is being appended to the BAM_IMPORT_SOURCE_DIRECTORY parameter*/
	private static final String PROCESSED_SUBDIR = "PROCESSED";
	
	/** The folder for the Archiver */
	private static final String ARCHIVE_MAIN_FOLDER_BAM = "BAM";
	
	/** Prefixes for the Belegnummer */
	private static final String PREFIX_WIEGEKARTE 	= "WK";
	private static final String PREFIX_FUHRE 		= "FU";
	
	
	/** The XML file Element names */
	private static final String ELEM_SCANNED_ID 	= "scanned_id";
	private static final String ELEM_ANGELEGT_AM 	= "angelegt_am";
	private static final String ELEM_GESENDET_AM 	= "gesendet_am";
	private static final String ELEM_ID_MANDANT 	= "id_mandant";
	private static final String ELEM_DEVICE_ID		= "device_identifier";

	private static final String ELEM_BAM 			= "bam";
	private static final String ELEM_BAM_ID 		= "id";
	private static final String ELEM_BAM_ABZUG 		= "abzug";
	private static final String ELEM_BAM_ABZUG_ZUSATZ = "abzug_zusatz";
	private static final String ELEM_BAM_GEWICHT 	= "gewicht";
	private static final String ELEM_BAM_ANHAFTEND 	= "anhaftend";

	private static final String ELEM_BILDER 		= "bilder";
	private static final String ELEM_BILD 			= "bild";
	
	
	/**
	 * Default Constructor
	 */
	public BAMImporter() {
	}
	
	
	
	/**
	 * Importiere noch nicht importierte BAM
	 * @return
	 * @throws myException
	 */
	public ArrayList<String> doImport() throws myException {
		// Der Importpath liegt immer direkt unterhalb des Archivpfades in der Anwendung
		String sBasePath = File.separator + bibALL.get_WEBROOTPATH() +  File.separator + bibALL.get_ARCHIVPATH() + File.separator;
		String sImportDir = __RECORD_MANDANT_ZUSATZ.get___Value(	"BAM_IMPORT_SOURCE_DIRECTORY", "");
		if (sImportDir.startsWith(File.separator)){
			sImportDir = sImportDir.substring(1);
		}
			
		String directory = sBasePath + sImportDir;
		
		
		if (directory == null || directory.equals("") || (!new File(directory).exists())) {
			throw new myException("Bitte setzen Sie den Parameter BAM_IMPORT_SOURCE_DIRECTORY in der Mandantenvewaltung (relativ zum Archivpfad ohne führenden Pfadtrenner).");
		}

		ArrayList<String> answer = new ArrayList<String>();

		String files;
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				if (files.toLowerCase().endsWith(".xml")) {
					String importId = processImportXMLAndReturnImportId(directory, files);
					answer.add(importId);
				}
			}
		}
		return answer;
	}
	
	


	/**
	 * liest die von der APP hochgeladene Import-Datei und fügt die Einträge in die Datenbank ein.
	 * Gleichzeitig werden die hochgeladenen Bilder in das Archiv angehängt und zum Import-Eintrag verknüpft
	 * @param dirName
	 * @param fileName
	 * @return
	 */
	public  String processImportXMLAndReturnImportId(String dirName, String fileName) {
		String bamImportId = null;
		try {

			DEBUG.System_print(addFileseparator(dirName) + fileName + " : ==========================", DEBUG.DEBUG_FLAG_DIVERS1);

			
			File stocks = new File(addFileseparator(dirName) + fileName);
		
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			Element elemLagerplatz = doc.getDocumentElement(); // named
																// "lagerplatz"

			String scannedId 		= getNodeValue(ELEM_SCANNED_ID, elemLagerplatz);
			String created 			= getNodeValue(ELEM_ANGELEGT_AM, elemLagerplatz);
			String sent 			= getNodeValue(ELEM_GESENDET_AM, elemLagerplatz);
			String deviceId			= getNodeValue(ELEM_DEVICE_ID,elemLagerplatz);
			
			// prüfen, ob die
			String id_mandant		= getNodeValue(ELEM_ID_MANDANT,elemLagerplatz);
			// falls noch kein Mandant gesetzt war, gewinnt der Mandant, der als erster aufruft.
			if (bibALL.isEmpty(id_mandant)){
				id_mandant = bibALL.get_ID_MANDANT();
			}
			
			if (!bibALL.get_ID_MANDANT().equals(id_mandant)){
				
				return "";
			}
			
			
			// Strip prefix and actual number
			String scannedIdPrefix = getPrefix(scannedId);
			Vector<String> normalizedScannedIds = getNormalizedIds(scannedId);

			// Look for referenced record (Wiegekarten/Fuhren)
			String idWiegekarte = null;
			Vector<String> vidFuhre = null;
			String idFuhre = null;
			String idFuhrenort = null;
			DEBUG.System_print("Looking for other: "+scannedIdPrefix+", norm: "+normalizedScannedIds.toString(), DEBUG.DEBUG_FLAG_DIVERS1);
			
			// hashmap welche alle möglichen IDs aus dem gescannten Wert entnimmt und den Tables zuordent 
			HashMap<ENUM_TABLES_TO_CONNECT_TO, String> hmIDs_aus_gescannter_ID = new HashMap<BAMImporter.ENUM_TABLES_TO_CONNECT_TO, String>();
			hmIDs_aus_gescannter_ID.putAll( findWiegekartenIds(scannedIdPrefix, normalizedScannedIds) );
			hmIDs_aus_gescannter_ID.putAll( findFuhrenIds(scannedIdPrefix, normalizedScannedIds));

			
			// Hashmap für den Eintrag des BAM-Import initialisieren
			// mp: Warum Object? Typsicherheit?
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put(_DB.BAM_IMPORT$ERZEUGT_VON, "IMPORT");
			hm.put(_DB.BAM_IMPORT$BAM_ANGELEGT_AM, created);
			hm.put(_DB.BAM_IMPORT$BELEGNUMMER, scannedId);
			hm.put(_DB.BAM_IMPORT$BAM_GESENDET_AM, sent);
			hm.put(_DB.BAM_IMPORT$DEVICE_ID, deviceId);

			for (Map.Entry<ENUM_TABLES_TO_CONNECT_TO, String> o: hmIDs_aus_gescannter_ID.entrySet()){
				ENUM_TABLES_TO_CONNECT_TO 	e 	= o.getKey();
				String 					  	id 	= o.getValue();
				
				if (e.equals(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE)){
					idWiegekarte = id;
					hm.put(_DB.BAM_IMPORT$ID_WIEGEKARTE, idWiegekarte);
				} else if (e.equals(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE)){
					idFuhre = id;
					hm.put(_DB.BAM_IMPORT$ID_VPOS_TPA_FUHRE, idFuhre);
				}else if (e.equals(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT)){
					idFuhrenort = id;
					hm.put(_DB.BAM_IMPORT$ID_VPOS_TPA_FUHRE_ORT, idFuhrenort);
				}
			}
			

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date importDate = formatter.parse(sent);

			RECORDNEW_BAM_IMPORT nbi = new RECORDNEW_BAM_IMPORT();
			nbi.setValues(hm);
			
			MyE2_MessageVector me2mv = new MyE2_MessageVector();
			RECORD_BAM_IMPORT recBAM = nbi.do_WRITE_NEW_BAM_IMPORT(me2mv);
			
			
			for (MyE2_Message m : me2mv) {
				DEBUG.System_print(m.get_MessageLabel().get_oTextObject().toString(), DEBUG.DEBUG_FLAG_DIVERS1);
			}
			
			bamImportId = nbi.get_cLastSEQ_NUMBER();

			NodeList nodesBam = elemLagerplatz.getElementsByTagName(ELEM_BAM);

			for (int i = 0; i < nodesBam.getLength(); i++) {
				Node node = nodesBam.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String bamId = getNodeValue(ELEM_BAM_ID, node);
					String bamName = getNodeValue(ELEM_BAM_ABZUG, node);
					String bamZusatz = getNodeValue(ELEM_BAM_ABZUG_ZUSATZ, node);
					String bamWeight = getNodeValue(ELEM_BAM_GEWICHT, node);
					String bamAnhaftend = getNodeValue(ELEM_BAM_ANHAFTEND, node);

					HashMap<String, Object> hmBam = new HashMap<String, Object>();
					hmBam.put(_DB.BAM_IMPORT_INFO$ID_BAM_IMPORT, bamImportId);
					hmBam.put(_DB.BAM_IMPORT_INFO$ID_ABZUGSGRUND, bamId);
					hmBam.put(_DB.BAM_IMPORT_INFO$TEXT, bamName);
					hmBam.put(_DB.BAM_IMPORT_INFO$ZUSATZINFOS, bamZusatz);
					hmBam.put(_DB.BAM_IMPORT_INFO$GEWICHT, bamWeight);
					hmBam.put(_DB.BAM_IMPORT_INFO$ANHAFTEND, bamAnhaftend);

					MyE2_MessageVector me2mvBam = new MyE2_MessageVector();
					RECORDNEW_BAM_IMPORT_INFO nbii = new RECORDNEW_BAM_IMPORT_INFO();
					
					nbii.setValues(hmBam);
					nbii.do_WRITE_NEW_BAM_IMPORT_INFO(me2mvBam);

					for (MyE2_Message m : me2mvBam) {
						DEBUG.System_print(m.get_MessageLabel().get_oTextObject().toString(), DEBUG.DEBUG_FLAG_DIVERS1);
					}
				}
			}
			
			
			NodeList nodesPic = elemLagerplatz.getElementsByTagName(ELEM_BILD);
			if (nodesPic.getLength() > 0) {
				for (int i = 0; i < nodesPic.getLength(); i++) {
					Node node = nodesPic.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						String bamPicture = node.getTextContent();
						DEBUG.System_println("Processing: " + bamPicture, DEBUG.DEBUG_FLAG_DIVERS1);
						
						// Process picture; if it has been moved successfully to the archive, the 
						// archived name is returned
						String lastFileWritten = moveToArchive(dirName, bamPicture, bamImportId, importDate);
						
						// Move Picture to "PROCESSED" subdir
						moveToProcessedSubdir(dirName, bamPicture);
					}
				}
			}
			// Move XML file to "PROCESSED" subdir
			moveToProcessedSubdir(dirName, fileName);

			
			// alle Anhänge weiter verknüpfen, wenn nötig 
			if (idFuhrenort != null){
				doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT, idFuhrenort);
			} else if (idFuhre != null){
				doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, idFuhre);
			}
	
			if (idWiegekarte != null){
				doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, idWiegekarte);
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bamImportId;
	}

	




	/**
	 * Strips the prefix from a [A-Za-z]+(_|-| ){1}{0-9}+ string
	 * 
	 * mp: schade, geht nicht so, da Fuhren/Fuhrenort die ID FU-12345-7893 also (<FU>-<ID-Fuhre>-<ID-FUhrenort>)
	 * 
	 * @param number
	 * @return
	 */
	public static Vector<String> getNormalizedIds(String number) {
//		 Pattern p = Pattern.compile("[0-9]+");
		 Pattern p = Pattern.compile("([0-9]+)");
		 Matcher m = p.matcher(number);
		 Vector<String> vRet = new Vector<String>();
		 while (m.find()) {
			 vRet.add(m.group());
	    }		
		return vRet;
	}

	
	
	/**
	 * returns the prefix from a [A-Za-z]+(_|-| ){1}{0-9}+ string
	 * @param number
	 * @return
	 */
	public static String getPrefix(String number) {
		String[] separators = new String[]{"-", "_", " "};
		for (int i = 0; i < separators.length; i++) {
			if (number.contains(separators[i])) {
				return number.substring(0, number.indexOf(separators[i])).toUpperCase().trim();
			}
		}
		return null;
	}

	
	
	/**
	 * liest die ID der Fuhre - Fuhrenort 
	 * @param scannedIdPrefix
	 * @param normalizedScannedId
	 * @return
	 */
	protected HashMap<ENUM_TABLES_TO_CONNECT_TO, String> findFuhrenIds( String scannedIdPrefix, Vector<String> normalizedScannedId) {
		HashMap<ENUM_TABLES_TO_CONNECT_TO, String> hmRet = new HashMap<BAMImporter.ENUM_TABLES_TO_CONNECT_TO, String>();

		// beide parameter müssen vorhanden sein.
		if(scannedIdPrefix != null && normalizedScannedId != null ){
			
			if (scannedIdPrefix.equals(PREFIX_FUHRE)){
				if (normalizedScannedId.size()>1){
					// fuhrenort-ID ist vorhanden, also den Fuhrenort lesen
					try {
						RECORD_VPOS_TPA_FUHRE_ORT fo = new RECORD_VPOS_TPA_FUHRE_ORT(normalizedScannedId.elementAt(1));
						hmRet.put(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, fo.get_ID_VPOS_TPA_FUHRE_cUF());
						hmRet.put(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT, fo.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
					} catch (myException e) {
						e.printStackTrace();
					}
				} else if (normalizedScannedId.size() == 1){
					// nur die Fuhre ist vorhanden, also nur die Fuhre lesen
					try {
						RECORD_VPOS_TPA_FUHRE fu = new RECORD_VPOS_TPA_FUHRE(normalizedScannedId.elementAt(0));
						hmRet.put(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, fu.get_ID_VPOS_TPA_FUHRE_cUF());
					} catch (myException e) {
						e.printStackTrace();
					}
				}
			} 
		}
		return hmRet;
	}
	
	
	/**
	 * liest die IDs der Wiegekarte und ggf. noch die IDs der Fuhre, falls eine Wiegekarte schon eine Fuhre zugeordnet hatte
	 * @param scannedIdPrefix
	 * @param normalizedScannedId
	 * @return
	 */
	protected HashMap<ENUM_TABLES_TO_CONNECT_TO, String>  findWiegekartenIds(String scannedIdPrefix, Vector<String> normalizedScannedId) {
		HashMap<ENUM_TABLES_TO_CONNECT_TO, String> hmRet = new HashMap<BAMImporter.ENUM_TABLES_TO_CONNECT_TO, String>();
		String idWiegekarte = null;
		
		// beide parameter müssen vorhanden sein.
		if(scannedIdPrefix != null && normalizedScannedId != null ){
			if (scannedIdPrefix.equals(PREFIX_WIEGEKARTE) && normalizedScannedId.size() == 1 ){
				try {
					RECORD_WIEGEKARTE wk = new RECORD_WIEGEKARTE(normalizedScannedId.elementAt(0));
					idWiegekarte = wk.get_ID_WIEGEKARTE_cUF();
					hmRet.put(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, idWiegekarte);
					if ( wk.get_ID_VPOS_TPA_FUHRE_cUF() != null){
						hmRet.put(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, wk.get_ID_VPOS_TPA_FUHRE_cUF());	
					}
					if (wk.get_ID_VPOS_TPA_FUHRE_ORT_cUF() != null){
						hmRet.put(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT, wk.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
					}
				} catch (myException e) {
					
				}
			}
		}
		
		return hmRet;
   }
	
	

	/**
	 * Builds the archiver and archives the file accordingly
	 * 
	 * @param dirName
	 * @param fileName
	 * @param id
	 * @param date
	 * @return
	 * @throws myException
	 */
	public  String moveToArchive(String dirName, String fileName, String id,	Date date) throws myException  {
		Archiver arch = new Archiver.Builder(
								ARCHIVE_MAIN_FOLDER_BAM,
								Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG)
								.setAufteilung(date, ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY)
								.setTable("JT_BAM_IMPORT")
								.build();
		
		String sRet = null;
		
		try {
			sRet = arch.writeTempFileToArchive(dirName, fileName, id, "Bild zu Abzug aus Import. ID: " + id + " vom " + date.toString());
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Bild " + fileName + " konnte nicht archiviert werden."));
		}
		
		return  sRet ;
	}

	

	
	/**
	 * Verschiebt die verarbeitete Datei in einen separaten Unterordner
	 * @param dirName
	 * @param fileName
	 * @return
	 */
	protected boolean moveToProcessedSubdir(String dirName, String fileName) {
		//TODO: Replace with correct function name
		boolean bRet = false;

		
		
		// prüfen, ob die Datei schon mal verschoben wurde...
		String sNewFilename = addFileseparator(dirName) + PROCESSED_SUBDIR + File.separator + fileName;
		File fTest = new File (sNewFilename);
		if (fTest.exists()){
			File fMoveTo = new File(sNewFilename + "." + new Date().toString() + ".old" );
			// bestehende Archivdatei umbenennen 
			try {
				FileUtils.moveFile(fTest, fMoveTo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Datei " + sNewFilename + " konnte nicht umbenannt werden." ));
			}
		}

		// Datei verschieben...
		try {
			FileUtils.moveFileToDirectory(new File(addFileseparator(dirName) + fileName), new File(addFileseparator(dirName) + PROCESSED_SUBDIR), true);
			bRet = true;
		} catch (IOException e) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Datei " + addFileseparator(dirName)   + fileName + " konnte nicht verschoben werden." ));
		}
		return bRet;
	}

	
	
	
	
	/** fügt einen Filetrenner an, falls nötig
	 * 
	 * @param sPath
	 * @return 
	 */
	private String addFileseparator(String sPath){
		String sRet = sPath;
		
		if(!sRet.endsWith(File.separator)){
			sRet += File.separator;
		}
		
		return sRet;
	}
	
	
	
	/**
	 * Helper function to get the contents of a tag
	 * 
	 * @param tag
	 *            The name of the child tag who'se content one wants
	 * @param element
	 *            The element containing this Node
	 * @return The contents of the tag, as a String
	 */
	private static String getNodeValue(String tag, Node element) {
		String nodeValue = null;
		try{
			NodeList nodes = ((Element) element).getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodes.item(0);
			
			if (node == null){
				return "";
			}
			nodeValue = node.getNodeValue();
		} catch(Exception e){
			nodeValue = "";
		}
		
		return nodeValue;
	}
	


	/**
	 * Methode zur Anbindung der BAM-Anhänge an andere Tabellen, gleichzeitig wird in der BAM der Foreignkey gesetzt.
	 * @param recBAM
	 * @param TableToConnectTo
	 * @param IDToConnectTo
	 * @throws myException
	 */
	private void doConnectBAMImportToOtherTable(RECORD_BAM_IMPORT recBAM, ENUM_TABLES_TO_CONNECT_TO TableToConnectTo , String IDToConnectTo) throws myException{
		
		Vector<String> vSQL = new Vector<String>();
		
		if (bibALL.isEmpty(IDToConnectTo) || recBAM == null){
			return ;
		}
		
		
		// Update des aktuellen BAMImport-Records
		String sIDBAMImport = recBAM.get_ID_BAM_IMPORT_cUF_NN("-1");
		
		switch (TableToConnectTo) {
		case WIEGEKARTE:
			recBAM.set_NEW_VALUE_ID_WIEGEKARTE_(Long.parseLong(IDToConnectTo));
			break;
		case VPOS_TPA_FUHRE:
			recBAM.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_(Long.parseLong(IDToConnectTo));
			break;
		case VPOS_TPA_FUHRE_ORT:
			recBAM.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT_(Long.parseLong(IDToConnectTo));
			
			try { // den Ort nochmal lesen, um die Fuhrenid zu bekommen....
				RECORD_VPOS_TPA_FUHRE_ORT recOrt = new RECORD_VPOS_TPA_FUHRE_ORT(IDToConnectTo);
				recBAM.set_NEW_VALUE_ID_VPOS_TPA_FUHRE(recOrt.get_ID_VPOS_TPA_FUHRE_cUF());
			} catch (myException e) {
				// dann halt keine Fuhre setzen...		
			}
			
			break;
		default:
			break;
		}

		String sSql = recBAM.get_SQL_UPDATE_STATEMENT(null, true);
		vSQL.add(sSql);
		
		
		// jeden Anhang den wir zu diesem Eintrag finden können auch an die Adresse hängen
		RECLIST_ARCHIVMEDIEN rlArchivmedien = new RECLIST_ARCHIVMEDIEN ("TABLENAME = 'BAM_IMPORT' AND ID_TABLE = " + sIDBAMImport ,"");
		for(RECORD_ARCHIVMEDIEN rec :rlArchivmedien.values()){
			RECORD_ARCHIVMEDIEN_ext recExt =  new RECORD_ARCHIVMEDIEN_ext(rec);
			vSQL.add(recExt.connectToAdditionalEntry(TableToConnectTo.toString(), IDToConnectTo));
		}
		
		// die Einträge in der DB eintragen
		bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSQL, true) );
		
		if (bibMSG.get_bIsOK()){
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Abzugs-Import wurde mit der Tabelle " + TableToConnectTo.toString() + " gekoppelt." ));
		}

	}

	
	
	
	/**
	 * Versucht über die gescannte ID die BAM automatisch an die Wiegekarte und/oder Fuhre zu verbinden
	 * und die Anhänge an den jeweiligen Datensatz anzubinden.
	 * 
	 * @param idBAM
	 * @throws myException
	 */
	public void doConnect_BAM_Automatically(String idBAM) throws myException {
		RECORD_BAM_IMPORT recBAM = new RECORD_BAM_IMPORT(idBAM);
		doConnect_BAM_Automatically(recBAM);
	}
		
	
	
	/**
	 * Versucht über die gescannte ID die BAM automatisch an die Wiegekarte und/oder Fuhre zu verbinden
	 * und die Anhänge an den jeweiligen Datensatz anzubinden.
	 * 
	 * @param ID_BAM_Import
	 */
	public void doConnect_BAM_Automatically(RECORD_BAM_IMPORT recBAM) throws myException {
		if (recBAM == null) throw new myException("doConnect_BAM_Automatically::Der Record ist fehlerhaft");
		
		String scannedId = recBAM.get_BELEGNUMMER_cUF();
		
		if (bibALL.isEmpty(scannedId)){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Belegnummer ist leer!"));
			return;
		}
		
		
		// Strip prefix and actual number
		String scannedIdPrefix = getPrefix(scannedId);
		Vector<String> normalizedScannedIds = getNormalizedIds(scannedId);

		// Look for referenced record (Wiegekarten/Fuhren)
		String idWiegekarte = null;
		
		Vector<String> vidFuhre = null;
		String idFuhre = null;
		String idFuhrenort = null;
		
		DEBUG.System_print("Looking for other: "+scannedIdPrefix+", norm: "+normalizedScannedIds.toString(), DEBUG.DEBUG_FLAG_DIVERS1);
		
		// hashmap welche alle möglichen IDs aus dem gescannten Wert entnimmt und den Tables zuordent 
		HashMap<ENUM_TABLES_TO_CONNECT_TO, String> hmIDs_aus_gescannter_ID = new HashMap<BAMImporter.ENUM_TABLES_TO_CONNECT_TO, String>();
		hmIDs_aus_gescannter_ID.putAll( findWiegekartenIds(scannedIdPrefix, normalizedScannedIds) );
		hmIDs_aus_gescannter_ID.putAll( findFuhrenIds(scannedIdPrefix, normalizedScannedIds));

		for (Map.Entry<ENUM_TABLES_TO_CONNECT_TO, String> o: hmIDs_aus_gescannter_ID.entrySet()){
			ENUM_TABLES_TO_CONNECT_TO 	e 	= o.getKey();
			String 					  	id 	= o.getValue();
			
			if (e.equals(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE)){
				idWiegekarte = id;
			} else if (e.equals(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE)){
				idFuhre = id;
			}else if (e.equals(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT)){
				idFuhrenort = id;
			}
		}

		// zuest alles disconnecten
		disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, recBAM.get_ID_BAM_IMPORT_cUF_NN("-1"));
		disconnectFromBAMImport(ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, recBAM.get_ID_BAM_IMPORT_cUF_NN("-1"));
		
		// alle Anhänge weiter verknüpfen, wenn nötig 
		if (idFuhrenort != null){

			doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT, idFuhrenort);
		} else if (idFuhre != null){
			doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE, idFuhre);
		}

		if (idWiegekarte != null){
			doConnectBAMImportToOtherTable(recBAM, ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE, idWiegekarte);
		}
		
		
	}
	
	
	
	/**
	 * Verbindet die BAM mit einer gefundenen Wiegekarte
	 * @param recBAM
	 * @return
	 * @throws myException 
	 */
	public void connect_BAM_to_Wiegekarte(RECORD_BAM_IMPORT recBAM, String idWiegekarte) throws myException{
		if (recBAM.get_ID_WIEGEKARTE_cUF() != null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es ist schon eine Wiegekarte zugeordnet worden!")));
		}
		doConnectBAMImportToOtherTable(recBAM,ENUM_TABLES_TO_CONNECT_TO.WIEGEKARTE,idWiegekarte);
	}

	
	/**
	 * Verbindet die BAM mit einer gefundenen Fuhre/Fuhrenort
	 * @param recBAM
	 * @param idVposTpaFuhre
	 * @return
	 * @throws myException 
	 */
	public void connect_BAM_to_Fuhre(RECORD_BAM_IMPORT recBAM, String idVposTpaFuhre) throws myException{
		if (recBAM.get_ID_VPOS_TPA_FUHRE_cUF() != null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es ist schon eine Fuhre zugeordnet worden!")));
		}
		doConnectBAMImportToOtherTable(recBAM,ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE,idVposTpaFuhre);
	}

	/**
	 * Verbindet die BAM mit einer gefundenen Fuhre/Fuhrenort
	 * @param recBAM
	 * @param idVposTpaFuhre
	 * @return
	 * @throws myException 
	 */
	public void connect_BAM_to_FuhreOrt(RECORD_BAM_IMPORT recBAM, String idVposTpaFuhreOrt) throws myException{
		if (recBAM.get_ID_VPOS_TPA_FUHRE_cUF() != null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es ist schon ein Fuhrenort zugeordnet worden!")));
		}
		doConnectBAMImportToOtherTable(recBAM,ENUM_TABLES_TO_CONNECT_TO.VPOS_TPA_FUHRE_ORT,idVposTpaFuhreOrt);
	}

	
	/**
	 * verknüpft die BAM-Anhänge mit der Fuhre
	 * @param idBAM
	 * @param idVposTpaFuhre
	 * @param idVposTpaFuhreOrt
	 * @throws myException
	 */
	public void connect_BAM_to_Fuhre( String idBAM, String idVposTpaFuhre, String idVposTpaFuhreOrt) throws myException{
		RECORD_BAM_IMPORT recBAM = null;
		if (!bibALL.isEmpty(idBAM) ){
			recBAM = new RECORD_BAM_IMPORT(idBAM);
			
			if (!bibALL.isEmpty(idVposTpaFuhreOrt ) ){
				connect_BAM_to_FuhreOrt(recBAM, idVposTpaFuhreOrt);
			} else if (!bibALL.isEmpty(idVposTpaFuhre)){
				connect_BAM_to_Fuhre(recBAM, idVposTpaFuhre);
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param TableToDisconnectFrom
	 * @param sIDBAM
	 */
	public void disconnectFromBAMImport( ENUM_TABLES_TO_CONNECT_TO TableToDisconnectFrom ,String sIDBAM){
		String sSql = "";
		String sIDConnected = null;
		String sTableConnected = null;
		
		Vector<String> vSQL = new Vector<String>();
		
		if (bibALL.isEmpty(sIDBAM) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es ist keine Id angegeben, die entkoppelt werden soll."));
			return;
		}
				
		
		RECORD_BAM_IMPORT recBAM;
		MyE2_MessageVector vMsg = new MyE2_MessageVector();

		try {
			// den BAM-Eintrag lesen
			recBAM = new RECORD_BAM_IMPORT(sIDBAM);
			
			// die zugeordnete Table-ID lesen
			switch (TableToDisconnectFrom) {
			case WIEGEKARTE:
				sIDConnected = recBAM.get_ID_WIEGEKARTE_cUF();
				// jetzt noch den Email-Eintrag ändern
				recBAM.set_NEW_VALUE_ID_WIEGEKARTE(null);
				vSQL.add(recBAM.get_SQL_UPDATE_STATEMENT(null, true) );
				break;
				
			case VPOS_TPA_FUHRE:
			case VPOS_TPA_FUHRE_ORT:	
				sIDConnected = recBAM.get_ID_VPOS_TPA_FUHRE_cUF();
				recBAM.set_NEW_VALUE_ID_VPOS_TPA_FUHRE(null);
				recBAM.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(null);
				vSQL.add(recBAM.get_SQL_UPDATE_STATEMENT(null, true) );
				break;
			default:
				break;
			}
			
			if (sIDConnected != null){
				
				
				
				//
				// Alle Archivmedien suchen, die an dieser BAM hängen von der angehängten Adresse löschen
				//
				RECLIST_ARCHIVMEDIEN rlArchivmedienBAM = new RECLIST_ARCHIVMEDIEN ("TABLENAME = 'BAM_IMPORT' AND ID_TABLE = " + sIDBAM ,"");
				for(RECORD_ARCHIVMEDIEN rec :rlArchivmedienBAM.values()){
					
					String sDateiname = rec.get_DATEINAME_VALUE_FOR_SQLSTATEMENT();
					String sPfad      = rec.get_PFAD_VALUE_FOR_SQLSTATEMENT();
					String sWhere     = "TABLENAME = '" + TableToDisconnectFrom.toString() + "' AND ID_TABLE = " + sIDConnected + " AND DATEINAME = " + sDateiname + " AND PFAD = " + sPfad;
					
					RECLIST_ARCHIVMEDIEN rlArchivmedienLinked = new RECLIST_ARCHIVMEDIEN (sWhere ,"");
					
					// alle zuordnungen löschen 
					// NB: geht momentan nur für JT_-Tabellen
					for (RECORD_ARCHIVMEDIEN rec_ARCHIV : rlArchivmedienLinked.values()){
						sSql = "DELETE FROM " + bibE2.cTO() + ".JT_ARCHIVMEDIEN WHERE " + rec_ARCHIV.FIELD__ID_ARCHIVMEDIEN + " = " + rec_ARCHIV.get_ID_ARCHIVMEDIEN_cUF_NN("-1");
						vSQL.add(sSql);
					}
				}
				
				// die Einträge in der DB eintragen
				bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSQL, true));
				
				if (bibMSG.get_bIsOK()){
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Abzugs-Import wurde von der Tabelle " + TableToDisconnectFrom.toString() + " entkoppelt." ));
				}
			}
			
		} catch (myException e) {
			bibMSG.add_MESSAGE(vMsg);
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Beim Lösen der Verknüpfung von Abzugs-Import und " + TableToDisconnectFrom.toString() + " ist ein Fehler aufgetreten."));
			e.printStackTrace();
		}
		
	}


	
	/***********
	 * 
	 *
	 * 
	 * Bereich für die Automatisierte Import der BAM-Meldungen
	 * 
	 * 
	 * 
	 *********/

	// Automator-
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#runTask()
	 */
	@Override
	public boolean runTask() {
		boolean bRet = true;
		try {
			this.doImport();
			m_TaskMessages.add("Die Abzugslisten der APP wurden importiert.");
		} catch (myException e) {

			m_TaskMessages.add("Fehler beim Import der Abzugslisten der APP.");
			e.printStackTrace();
			bRet = false;
		}
		return bRet;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#setTaskParameters(java.util.HashMap)
	 */
	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters  = hmParameters;		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.indep.batch.ICallableTask#getTaskMessages()
	 */
	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}
	

	
	
	
	
	


}


