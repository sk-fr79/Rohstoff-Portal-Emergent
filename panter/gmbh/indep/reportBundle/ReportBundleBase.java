package panter.gmbh.indep.reportBundle;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.indep.MyFileHelper;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class ReportBundleBase {
	protected static final int BUFFER = 2048;
	 
	/**
	 *  das unterverzeichnis, in das die Zip-Dateien abgelegt werden sollen
	 *  incl. abschliessendem Separator
	 */
	public static final String REPORT_BUNDLE_ZIP_PATH = "ZIP" + File.separator;
	
	// der name des XML Files 
	protected static final String sXML_DEF = "definition.xml";
	
	/**
	 *  Der absolute originale Dateiname 
	 *  z.B. /daten/reports/alle/ZIP/LIST_alle.jrxml
	 */
	protected String m_File			= null;
	
	/**
	 *  Name incl. Pfad des zu extrahierenden Zipfiles / des Reports
	 *  z.B. LIST_alle.jrxml 
	 *  von /daten/reports/alle/ZIP/LIST_alle.jrxml  
	 */
	protected String m_FileName		= null;

	
	/**
	 *  Der Reportname ohne POSTFIX
	 *  z.B. LIST_alle 
	 *  von /daten/reports/alle/ZIP/LIST_alle.jrxml   
	 */
	protected String m_ReportName 	= null;
	

	/**
	 *   Der Pfad in dem die Datei steht
	 *   
	 *  z.B. /daten/reports/alle/ZIP/ 
	 *  von /daten/reports/alle/ZIP/LIST_alle.jrxml   
	 */
	protected String m_Path	   		= null;
	
	
	
	
	private Vector<String> m_vMessages = new Vector<String>();
	
	
	/**
	 * Default-Konstruktur
	 */
	public ReportBundleBase(){
		
	}
	
	
	
	/**
	 * Initialisiert die Namen und den Pfad der Dateien (Zip oder Report)
	 * @param fileName
	 */
	public ReportBundleBase( String fileName ) {
		initReportPathAndName(fileName);
	}
	
	
	
	

	
	
	
	/**
	 * ermittelt aus dem gesamten Dateinamen die einzelnen Teile Pfad, Dateiname, und Reportname(==Dateiname ohne Endung)
	 * @param fileName  (incl. Endung)
	 */
	protected boolean initReportPathAndName(String fileName){
		boolean bRet = false;
		try {
			
			m_File = fileName;
			
			String [] aFileHelper = MyFileHelper.Separate_Filename_From_Path(fileName);
			m_FileName 	= aFileHelper[1];
			m_Path   	= aFileHelper[0];
			
			// der name des Reports (ohne .zip)
			m_ReportName = m_FileName.substring(0, m_FileName.indexOf("."));
			bRet = true;
		} catch (myException e) {
			addMessage (new MyString("Die Reportnamen konnte nicht initialisiert werden").CTrans() + " (" + fileName + ")");
		}
		return bRet;
	}

	
	
	
	protected void addMessages(Vector<String> vMessages){
		m_vMessages.addAll(vMessages);
	}
	
	
	/**
	 * fügt eine Meldung in die Meldungsliste ein
	 * @param message
	 */
	protected void addMessage(String message){
		m_vMessages.add(message);
	}
	
	protected void addMessage(MyString message){
		if (message != null){
			m_vMessages.add(message.CTrans());
		}
	}
	
	
	/**
	 * true, wenn Meldungen vorhanden sind
	 * @return
	 */
	public boolean bHasMessages(){
		return m_vMessages.size() > 0;
	}
	
	
	/**
	 * gibt die Meldungen als String zurück
	 * @return
	 */
	public String getMessagesAsString(){
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = m_vMessages.iterator();
		
		while(iter.hasNext()){
			sb.append(iter.next());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
}
