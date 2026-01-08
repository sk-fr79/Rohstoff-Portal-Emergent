package panter.gmbh.indep.reportBundle;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;



/**
 * Klasse zum verwalten von ReportBundle Zip-Files für die Verteilung von Listen
 * incl. SQL-Statements zum einpflegen in die DB
 * @author manfred
 *
 */
public class ReportBundleUnpacker extends ReportBundleBase {

	// fürs verpacken des Reports
	Vector<String> m_SqlForReportImport = new Vector<String>();
	

//	// Der Reportname ohne .jrxml
//	private String m_ReportName 	= null;
//	
//	// Name incl. Pfad des zu extrahierenden Zipfiles 
//	private String m_FileName		= null;
//	
//	// Der Pfad in dem das Zip steht. 
//	private String m_Path	   		= null;
	
	private String 				m_ZipSourcePath = null;
		
	// neue Mandanten-ID falls der Mandant gewechselt werden soll 
	private String 				m_IDMandantNew = null;
	private String 				m_ModulKennerNew = null;
	
	// wenn true, dann kann der Report in ein anderes Modul eingepflegt werden.
	private boolean 			m_AllowModuleChange = false;
	
	
	private boolean				m_bOverrideOldReport = true;
	
	private Unzipper 			unzip	= null;
	
	

	/**
	 * Den Bundle initialisieren.
	 * Dazu muss der Pfad angepasst werden
	 * Bsp: 
	 * /daten/reports/1/ZIP/auswertung.zip
	 * soll in 
	 * /daten/reports/1/   
	 * extrahiert werden 
	 * 
	 * @param ReportNameAndPath
	 */
	public ReportBundleUnpacker(String ZipNameAndPath) {
		super(ZipNameAndPath);
		
		// jetzt die Zip-Sachen anpassen
		m_ZipSourcePath = m_Path;
		
		// den eigentlichen Pfad ermitteln
		int endIndex = m_ZipSourcePath.indexOf(REPORT_BUNDLE_ZIP_PATH);
		if (endIndex > 0){
			m_Path = m_ZipSourcePath.substring(0,endIndex );
		}
	}

	
	
	public boolean UnpackBundle( String sIDMandantTarget, boolean bOverrideOldReport){
		boolean bRet = false;
		
		m_IDMandantNew = sIDMandantTarget;
		m_bOverrideOldReport = bOverrideOldReport;
		
		// protokoll
		addMessage(m_FileName + new MyString(" wird entpackt nach ") + m_Path );

		unzip = new Unzipper(m_File);
		
		// Datenbank füllen/updaten
		try {
			
			// Zielverzeichnis angeben
			unzip.setBasePathOfFiles(m_Path);
			
			if(unzip.openZip()){
				// entpacken der Dateien, ausser der Definitionsdatei mit den SQL-Statements
				unzip.extractZip(bibALL.get_Vector(sXML_DEF), bOverrideOldReport);
				

				// schreiben der Statements
				if( createSQLStatementsForImport() ) {
					// statements ausführen
					MyE2_MessageVector vMsg = executeImportStatements();
					if(!vMsg.get_bIsOK()){
						addMessage(vMsg.get_MessagesAsText());
					} 
					bRet = true;
				}
			}
			
		} catch (myException e) {
			addMessage("SqlStatements konnten nicht generiert werden.");
		} finally{
			unzip.closeZip();
		}
		
		addMessages(unzip.getMessages());
		
		return bRet;
	}

	
	/**
	 * Setzen des neuen Ziel-Moduls
	 * @param ModulKennerNeu
	 */
	public void setModuleKennerZiel(String ModulKennerNeu){
		this.m_ModulKennerNew = ModulKennerNeu;
	}
	
	/**
	 * Erlaubt / Verhindert den wechsel eines Reports zwischen verschiedenen Moduln
	 * @param AllowChange
	 */
	public void setAllowModuleChange(boolean AllowChange){
		this.m_AllowModuleChange = AllowChange;
	}
	
	
	
	/**
	 * Ausführen der Statements
	 */
	private MyE2_MessageVector executeImportStatements(){
		 return bibDB.ExecMultiSQLVector(m_SqlForReportImport, true, null);
	}
	
	
	
	
	/**
	 * Erzeugt das XML-Dokument aus der Definitionsdatei
	 * @return
	 * @throws DocumentException 
	 */
	private Document getXMLForImport() throws DocumentException{
		String sXML = null;
		Document doc = null;
		try {
		
			sXML = unzip.getTextFromZipfile(sXML_DEF);

		} catch (IOException e) {
			addMessage(new MyString("Konnte die Datei nicht lesen." + sXML_DEF ));
			return null;
		}
		
		// dokument muss getrimmt werden, sonst gibt es einen Fehler
		sXML = sXML.trim();
		
		doc = DocumentHelper.parseText(sXML);

		return doc;
	}
	

	
	
	
	/**
	 * liest die SQL-Statements für den Import ein und ersetzt ggf. die Mandanten-ID
	 * 
	 * @param sReportID
	 * @return
	 * @throws myException
	 * @throws  
	 */
	private boolean createSQLStatementsForImport() throws myException{
		boolean bRet = false;
 
	    Vector<String> vSQLStatements = new Vector<String>();
	    
	    Document doc = null;
		try {
			// Dokument generieren
			doc = getXMLForImport();
	    	if  (doc == null) return bRet;
		
	    	
			//Dokument verarbeiten
			List<Node> listReports = doc.selectNodes("//report");
			Iterator<Node> iterReports = listReports.iterator(); 
			while(iterReports.hasNext()){
				Node nodeReport = iterReports.next();
				Node nodeVersion = nodeReport.selectSingleNode("info/VERSION");
				Node nodeMandant = nodeReport.selectSingleNode("info/MANDANT");
				Node nodeKenner = nodeReport.selectSingleNode("info/MODUL_KENNER");
				
				// schauen, ob das Modul das gleiche ist, das beim Export festgeschrieben ist.
				// der Report kann nur in das neue Modul importiert werden, wenn explizit der Modulwechsel erlaubt ist.
				String sModulKennerNeu = ( m_ModulKennerNew != null ? m_ModulKennerNew : nodeKenner.getText());
				String sModulKennerUrsrpung = nodeKenner.getText();
				if (!sModulKennerNeu.equals(sModulKennerUrsrpung) && !m_AllowModuleChange){
					addMessage(new MyString("Ein Modulwechsel des Reports ist nicht erlaubt." ));
					continue;
				}
				
				
				// alle SQL-befehle
				List<Node> list = nodeReport.selectNodes( "statements/sql" );
				
				// durch die Sql-Statements laufen und evtl. den Parameter (Mandanten-ID) ersetzen
				for (Iterator<Node> iter = list.iterator(); iter.hasNext(); ) {
					
					Node node = iter.next();
					String s =  node.getText();
					
					s = s.replaceAll(":id_mandant_neu", (m_IDMandantNew != null ? m_IDMandantNew : nodeMandant.getText()));
					
					s = s.replaceAll(":module_kenner",sModulKennerNeu);
					
					vSQLStatements.add(s);
				}
				
			}
			bRet = true;
			
		} catch (DocumentException e) {
			addMessage(new MyString("Fehler bei der Interpretation des XML" ));
		}
        
        m_SqlForReportImport = vSQLStatements;
        return bRet;
	}
	 
	
	
}
