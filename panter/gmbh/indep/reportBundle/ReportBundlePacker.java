package panter.gmbh.indep.reportBundle;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PARAMETER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum verwalten von ReportBundle Zip-Files für die Verteilung von Listen
 * incl. SQL-Statements zum einpflegen in die DB
 * @author manfred
 *
 */
public class ReportBundlePacker extends ReportBundleBase {

	
	
	// fürs verpacken des Reports
	Vector<String> m_SqlForReportImport = new Vector<String>();


	private String 				m_zipFileNameCreated = null;
	private String 				m_zipFileNameCreatedNoPath = null;
	
	// Informationen fürs XML
	private String 				 m_IdMandantOrigin = null;
	private String 				 m_ModulKenner = null;
	

	// Der ID des Reports, wenn das PackBundle über die Report-ID initailisiert wird.
	private String 				m_sIDReport = null;
	
//	private RECORD_REPORT 		m_recReport = null;
	private RECLIST_REPORT      m_reclistReport = null;
	

	
	/**
	 * Den Bundle initialisiern anhand der ReportID
	 * @param IDReport
	 */
	public ReportBundlePacker(){
		super();
		
		
	}

	
	
	
	/**
	 * über die ReportID wird der Reportname gesucht. Dann wird geprüft, ob der Report im Mandanten-Ordner oder im Alle-Ordner liegt.
	 */
	public boolean InitReportPackerFromID(String sIDReport){
		boolean bRet = false;
		
		
		m_sIDReport = sIDReport;
		RECORD_REPORT recReport = null;
		
		if (m_sIDReport == null){
			addMessage(new MyString("Es ist keine ReportID vorgegeben"));
			return bRet;
		}
		

		try {
			// lesen des einzelnen Reports in die Liste...
			m_reclistReport = new RECLIST_REPORT("SELECT * FROM " + bibE2.cTO() + ".JT_REPORT WHERE ID_REPORT = " + m_sIDReport );
			recReport = m_reclistReport.get(0);
		} catch (myException e) {
			addMessage(new MyString("ACHTUNG: Der Report konnte nicht geladen werden. ID: ") + m_sIDReport);
			return bRet;
		}
		

		String sPathALLE = bibALL.get_REPORTPATH_LISTEN_ALLE();
		String sPathMANDANT = bibALL.get_REPORTPATH_LISTEN_MANDANT();
		
		String sFilename = "";
		String sReportFileName = "";
		try {
			sReportFileName = recReport.get_NAME_OF_REPORTFILE_cUF();
		} catch (myException e) {
			addMessage(new MyString("ACHTUNG: Der Reportname konnte nicht ermittelt werden. ID: ") + m_sIDReport);
		}
		
		
		// liegt der Report im Mandanten-Ordner?
		sFilename = locateFileInDirectory(sPathMANDANT, sReportFileName);
		if (sFilename == null) {
			// Wenn nein muss er im ALLE-Ordner liegen
			sFilename = locateFileInDirectory(sPathALLE, sReportFileName);
			if (sFilename != null) {
				addMessage(new MyString("Der Report ist ein Allgemeiner Report") );
			}
		} else {
			addMessage(new MyString("Der Report ist ein Mandanten-Report") );
		}
		
		// sonst ist es ein Fehler
		if (sFilename == null) {
			addMessage(new MyString("ACHTUNG: Der Report konnte nicht gefunden werden. Name: ") + sReportFileName);
		} else {
			// sezten der Report-Namen und Pfade
			initReportPathAndName(sFilename);
			bRet = true;
		}
		
		return bRet;
	}

	
	
	
	
	/**
	 * liefert den Dateinamen der .jrxml-Datei inclusive des gesamten Pfades zurück
	 * 
	 * @param Dir
	 * @param Filename  (mit oder ohne Endung)
	 * @return
	 */
	private String locateFileInDirectory(String Dir, String Filename){
		String sRet = null;

		// es interessiert nur der Teil vor dem Punkt
		
		String sReport = Filename;
		if (sReport.indexOf(".")>=0)
		{
		   sReport =  Filename.substring(0, Filename.indexOf("."));
		}
		
		
		// Prüfen, ob der Report im Mandanten-Ordner liegt
		File d = new File(Dir);
		
		// Verzeichnis exisiert nicht...
		if (!d.exists() || !d.isDirectory()) {
			addMessage(new MyString("Das Verzeichnis exisitert nicht: ") + Dir );
			return null;
		}

		String[] entries = d.list();

		// Loop through all entries in the directory
		for (int i = 0; i < entries.length; i++) {
			File f = new File(d, entries[i]);
			if (f.isDirectory()) {
				continue;
			}
			
			// es interessiert nur der Teil vor dem Punkt
			String sFile = f.getName();
			if (sFile.indexOf(".")> 0){
				sFile = sFile.substring(0, f.getName().indexOf("."));
			}
			
			if (sFile.equals(sReport)){
				// Report gefunden
				sRet = Dir + sFile + ".jrxml";
				break;
			}
		}
		
		
		return sRet;
	}
	
	
	
	
	
	
	/**
	 * Den Bundle initialisieren anhand des Pfades
	 * Interessant sind nur der Pfad und der Name ohne Endung.
	 * 
	 * Bsp: /daten/reports/1/auswertung.jrxml
	 * -> Pfad = /daten/reports/1/
	 * -> Name = auswertung
	 * 
	 * @param ReportNameAndPath
	 */
	public boolean InitReportPackerFromReportFile(String ReportNameAndPath) {
		boolean bRet = false;
		
		try {
			bRet = initReportPathAndName(ReportNameAndPath);

			m_reclistReport= new RECLIST_REPORT("SELECT * FROM " + bibE2.cTO() + ".JT_REPORT WHERE lower(NAME_OF_REPORTFILE) = '" + m_ReportName.toLowerCase().trim()  + "' OR lower(NAME_OF_REPORTFILE) = '" + m_ReportName.toLowerCase().trim()   + ".jasper' ");
		} catch (myException e) {
			addMessage(new MyString("Der Report konnte nicht geladen werden. Name: ") + m_ReportName);
		}

		
		return bRet;
	}
	
	
	
	
	
	public boolean PackBundle() throws IOException{
		boolean bRet = true;
		

		
		m_zipFileNameCreated = m_Path + REPORT_BUNDLE_ZIP_PATH + m_ReportName + ".zip";
		m_zipFileNameCreatedNoPath = m_ReportName + ".zip";
		
		
		Zipper zip = new Zipper(m_zipFileNameCreated);
		zip.setBasePathOfFiles(m_Path);
		
		// Datei öffnen
		bRet &= zip.createZip();
		
		
		// Dateien zum Zip dazufügen
		zip.addFileToZip(m_Path + m_ReportName + ".jrxml");
		zip.addFileToZip(m_Path + m_ReportName + ".jasper");
		
		zip.addSubdirectoryToZip(m_ReportName);
		
		String sXML = null;
		try {
			 sXML = getXMLForExport();
		
			 // SQL-STATEMENTS  
			 zip.addTextAsFileToZip(m_Path + sXML_DEF , sXML);  

		} catch (myException e) {
			addMessage(new MyString("XML wurde nicht erzeugt."));
			bRet = false;
		}
		
		zip.close();

		addMessages(zip.getMessages());
		
		return bRet;
	}
	
	
	
	
	/**
	 * gibt den kompletten Pfad des erzeugten Zipfiles zurück
	 * @return
	 */
	public String getFilePath(){
		return m_zipFileNameCreated;
	}
	
	/**
	 * gibt den Namen der Datei zurück
	 * @return
	 */
	public String getFileName(){
		return m_zipFileNameCreatedNoPath;
	}
	
	
	/**
	 * Ermittelt die SQL-Statements, die in die xml-Datei eingetragen werden
	 * und trägt sie in das XML ein, das zurückgegeben wird
	 * 
	 * @param sReportID
	 * @return
	 * @throws myException
	 */
	private String getXMLForExport() throws myException {
		String sRet = "";
		Document doc = null;
		RECORD_REPORT  recREPORT = null;

		
		//
		// SQL für Report-Zeilen generieren
		//
		m_SqlForReportImport.clear();

		
		// XML-Root
		doc = DocumentHelper.createDocument();
		
		Element root = doc.addElement("reportdef");
		
		
		for (int j=0; j < m_reclistReport.get_vKeyValues().size(); j++){
			recREPORT = m_reclistReport.get(j);
			addReportInXML(root, recREPORT);
		}
		
		
		// lesbarer XML-Ausdruck
		OutputFormat outformat = OutputFormat.createPrettyPrint();
		StringWriter sw = new StringWriter();
		
		XMLWriter writer = new XMLWriter(sw,outformat);
		try {
			writer.write(doc);
		} catch (IOException e) {
			addMessage(new MyString("IOException bei der Erstellung des XML"));
		}
		
		sRet = sw.toString();

		return sRet;
	}
	
	
	
	
	/**
	 * ermittelt die Daten für das XML und baut sie in das xml am Einhängepunkt node ein
	 * @param node
	 * @param recREPORT
	 * @throws myException
	 */
	private void addReportInXML(Element node,RECORD_REPORT recREPORT) throws myException{
		
		
		// XML NODES einfügen
		Element report = node.addElement("report");
		Element info = report.addElement("info");
		info.addElement("VERSION").addText("1");
		info.addElement("MODUL_KENNER").addText(recREPORT.get_MODULE_KENNER_cUF());
		info.addElement("MANDANT").addText(recREPORT.get_ID_MANDANT_cUF());
		
		Element stmts = report.addElement("statements");

		
		// RECORDS lesen und SQL-Statements generieren
		RECLIST_REPORT_PARAMETER recParms = recREPORT.get_DOWN_RECORD_LIST_REPORT_PARAMETER_id_report();

		MySqlStatementBuilder oSTB_Report_WithSpecialfields = recREPORT.get_StatementBuilderFilledWithActualValues(false);
		oSTB_Report_WithSpecialfields.addSQL_Paar("ID_REPORT", "SEQ_REPORT.NEXTVAL");
		oSTB_Report_WithSpecialfields.addSQL_Paar("ID_MANDANT", ":id_mandant_neu");
		oSTB_Report_WithSpecialfields.addSQL_Paar("MODULE_KENNER", "':module_kenner'");
		
		
		// Statement ins xml eintragen
		stmts.addElement("sql").addCDATA(oSTB_Report_WithSpecialfields.get_CompleteInsertString("JT_REPORT"));
		
		// Reportparameter-Zeilen
		for (int i=0;i<recParms.get_vKeyValues().size();i++)
		{
			MySqlStatementBuilder oSTB_ReportParm_WithSpecialfields = recParms.get(i).get_StatementBuilderFilledWithActualValues(false);
			oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_REPORT_PARAMETER", "SEQ_REPORT_PARAMETER.NEXTVAL");
			oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_MANDANT", ":id_mandant_neu");
			
			oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_REPORT", "SEQ_REPORT.CURRVAL");
			oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_REPORT", "SEQ_REPORT.CURRVAL");
			
			// Statement ins xml eintragen
			stmts.addElement("sql").addCDATA(oSTB_ReportParm_WithSpecialfields.get_CompleteInsertString("JT_REPORT_PARAMETER"));
		}

	}
	
	

}
