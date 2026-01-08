package panter.gmbh.indep.reportBundle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import panter.gmbh.indep.MyFileHelper;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class Zipper {
	private static final int 	BUFFER = 2048;
	
	private Vector<String> 				m_vMessages = new Vector<String>();
	
	// für das Zippen
	private FileOutputStream    m_fileOut = null;
	private ZipOutputStream     m_zipOut = null;
	
	// die Pfade des Zipfiles:
	// Dateiname mit Pfad
	private String 				m_zipFile = null;
	// Dateiname
	private String 				m_zipFileName = null;
	// Pfad
	private String 				m_zipFilePath = null;
	
	// Basispfad der zu zippenden Dateien
	private String				m_basePath = null;
	
	
	
	
	/**
	 * Initialisiert den Zipper mit den künftigen Zipfile-Namen incl. dem (absoluten) Pfad
	 * @param ZipFile
	 */
	public Zipper(String ZipFile) {
		m_zipFile = ZipFile;
		
		String[] aFileHelper = null;
		try {
			aFileHelper = MyFileHelper.Separate_Filename_From_Path(m_zipFile);
			m_zipFileName 	= aFileHelper[1];
			m_zipFilePath   = aFileHelper[0];
			m_basePath = m_zipFilePath;
			
		} catch (myException e) {
			m_vMessages.add(new MyString("Dateiname und Pfad konnten nicht ermittelt werden").CTrans());
		}
	}
	
	
	
	/**
	 * erzeugt das Zipfile
	 * @return
	 */
	public boolean createZip(){
		boolean bRet = false;
		
		File f = new File(m_zipFilePath);
		if (!f.exists()){
			f.mkdirs();
		}
		
		try {
			m_fileOut = new FileOutputStream(m_zipFile);
			m_zipOut = new ZipOutputStream(new BufferedOutputStream(m_fileOut));
			bRet = true;
		} catch (FileNotFoundException e) {
			m_vMessages.add(new MyString("Zipdatei konnte nicht erzeugt werden.").CTrans() + m_zipFile);
		}
		return bRet;
	}
	

	/**
	 * Schliesst das Zipfile
	 * @throws IOException
	 */
	public void close() throws IOException{
		m_zipOut.close();
		m_fileOut.close();
	}

	
	/**
	 * Setzt den Pfad, zu dem alle anderen Dateien relativ gesehen dazugefügt werden.
	 * Wir der Pfad nicht explizit gesetzt, dann wird der Pfad des Zipfiles als BasePath angenommen
	 * @param sBasePath
	 */
	public void setBasePathOfFiles ( String sBasePath){
		m_basePath = sBasePath;
	}
	
	
	
	
	
	/**
	 * fügt die Datei zum Zip dazu.
	 * Der Pfad muss vollständig absolut angegeben werden. Dann wird im Zip der relative Pfad ermittelt.
	 * @param fileName
	 */
	public void addFileToZip(String fileName) {
		FileInputStream fi  = null;
		BufferedInputStream origin = null;
		byte data[] = new byte[BUFFER];
		
		if (fileName.indexOf(m_basePath, 0) < 0){
			m_vMessages.add(new MyString("Datei liegt nicht im Verzeichnis oder in einem Unterverzeichnis. ").CTrans() + fileName + ":" + m_basePath);
			return;
		}
		
		
		try {
			File file = new File(fileName);
			fi = new FileInputStream(file);
			
			// Zipfile-Pfad relativ zum Report
// ACHTUNG: getCanonicalPath() löst Symbolische Links auf!!
//			String zipFilePath = file.getCanonicalPath().substring(m_basePath.length() ,
//					file.getCanonicalPath().length());
			String zipFilePath = file.getAbsolutePath().substring(m_basePath.length() ,
					file.getAbsolutePath().length());

			
			origin = new BufferedInputStream(fi, BUFFER);
			ZipEntry entry = new ZipEntry(zipFilePath);
			m_zipOut.putNextEntry(entry);

			int count;
			while((count = origin.read(data, 0, BUFFER)) != -1) {
				m_zipOut.write(data, 0, count);
			}
			origin.close();
			
			m_vMessages.add(fileName 	+ new MyString(" wurde ins Zip-Archiv eingefügt.").CTrans());
          
		} catch (FileNotFoundException e) {
			m_vMessages.add(fileName + new MyString(" konnte nicht ins Zip-Archiv eingefügt werden (FileNotFound).").CTrans());
			return;
		} catch (IOException e) {
			m_vMessages.add( fileName + new MyString(" konnte nicht ins Zip-Archiv eingefügt werden (IOException).").CTrans());
		}
	}

	
	
	/**
	 * fügt ein Unterverzeichnis komplett zum Zipfile dazu.
	 * 
	 * @param sDirName  : nur der Unterverzeichnis-Name ohne Trennzeichen (z.B. "firmenreport" )
	 */
	public void addSubdirectoryToZip(String sSubDirName) {
		String sDir = m_basePath + sSubDirName;

		File d = new File(sDir);
		
		// Verzeichnis exisiert nicht...
		if (!d.exists()) {
			m_vMessages.add(new MyString("Verzeichnis konnte nicht gefunden werden. ").CTrans() + sDir );
			return;
		}
		
		// Verzeichnis ist kein Verzeichnis ??? kann eigentlich nicht sein, aber trotzdem
		if (!d.isDirectory()){
			m_vMessages.add(new MyString("Das Unterverzeichnis ist kein Unterverzeichnis! ").CTrans() + sDir + new MyString(" wurde nicht ins Zip-Archiv eingefügt.").CTrans() );
			return;
		}

		String[] entries = d.list();

		// Loop through all entries in the directory
		for (int i = 0; i < entries.length; i++) {
			File f = new File(d, entries[i]);
			if (f.isDirectory()) {
				m_vMessages.add(new MyString("Das Unterverzeichnis ").CTrans() + f.getName() + new MyString(" wurde NICHT ins Zip-Archiv eingefügt.").CTrans());
				// rekursiv die Unterverzeichnisse zippen
				//addSubdirectoryToZip(f.getName());
				continue;
			}
			
			this.addFileToZip(sDir + File.separator + f.getName());
//			m_vMessages.add(sDir + File.separator + f.getName() 	+ new MyString(" wurde ins Zip-Archiv eingefügt.").CTrans());
		}

	}
	
	
	
	/**
	 * fügt einen String in Form einer Datei in das Zip-Archiv ein
	 * @param fileName (Absolut zum Basispfad)
	 * @param Text
	 */
	public void addTextAsFileToZip(String fileName, String Text) {
		FileInputStream fi  = null;
		BufferedInputStream origin = null;
		
		try {
			
			
			// Zipfile-Pfad relativ zum Report
			String zipFilePath = fileName.substring(m_basePath.length() ,
					fileName.length());
			
			origin = new BufferedInputStream(fi, BUFFER);
			ZipEntry entry = new ZipEntry(zipFilePath);
			m_zipOut.putNextEntry(entry);
				
			m_zipOut.write(Text.getBytes("UTF-8"));
			
			origin.close();
          
		} catch (FileNotFoundException e) {
			m_vMessages.add("Die Date " + fileName + " konnte nicht zum Zipfile hinzugefügt werden (FileNotFound).");
			return;
		} catch (IOException e) {
			m_vMessages.add("Die Date " + fileName + " konnte nicht zum Zipfile hinzugefügt werden (IOException).");
		}
	}


	public Vector<String> getMessages(){
		return m_vMessages;
	}

}
