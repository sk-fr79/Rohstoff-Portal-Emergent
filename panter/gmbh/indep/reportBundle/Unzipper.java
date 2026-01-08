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
import java.util.zip.ZipInputStream;

import panter.gmbh.indep.MyFileHelper;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class Unzipper {

	private static final int 	BUFFER = 2048;
	
	Vector<String> 				m_vMessages = new Vector<String>();

	// für das Unzippen
	private ZipInputStream		m_zipIn = null;
	private FileInputStream		m_fileIn = null;
	private BufferedInputStream m_bufferedInputStream = null;
	
	private String 				m_ZipSourcePath = null;

	// die Pfade des Zipfiles:
	// Dateiname mit Pfad
	private String 				m_zipFile = null;
	// Dateiname
	private String 				m_zipFileName = null;
	// Pfad
	private String 				m_zipFilePath = null;
	
	// Basispfad der zu zippenden Dateien
	private String				m_basePath = null;
	
	
	public Unzipper(String ZipFile) {
		m_zipFile = ZipFile;
		
		String[] aFileHelper = null;
		try {
			aFileHelper = MyFileHelper.Separate_Filename_From_Path(m_zipFile);
			m_zipFileName 	= aFileHelper[1];
			m_zipFilePath   = aFileHelper[0];
			setBasePathOfFiles( m_zipFilePath );
			
		} catch (myException e) {
			m_vMessages.add(new MyString("Dateiname und Pfad konnten nicht ermittelt werden").CTrans());
		}
	}
	
	
	
	/**
	 * Setzt den Pfad, in den die Dateien extrahiert werden.
	 * Wir der Pfad nicht explizit gesetzt, dann wird der Pfad des Zipfiles als BasePath angenommen
	 * @param sBasePath
	 */
	public void setBasePathOfFiles ( String sBasePath){
		m_basePath = sBasePath;
		
		if (m_basePath != null && !m_basePath.endsWith(File.separator)){
			m_basePath = m_basePath + File.separator;
		}
		
	}
	
	
	/**
	 * Extrahiert alle Dateien in den gegebebenen Zielpfad,
	 * bis auf die die in der Liste angegebenen Dateien.
	 * 
	 * @param TargetPath
	 * @param ListOfFilesNotToExtract
	 * @throws IOException 
	 */
	public boolean extractZip( Vector<String> ListOfFilesNotToExtract, boolean bOverrideExistingFiles) {
		boolean bRet = false;
		String   sTargetFile = "";
		ZipEntry entry = null;
		BufferedOutputStream out = null;

		try {
			
			while ((entry = m_zipIn.getNextEntry()) != null){
				String name = entry.getName();
				
				if (ListOfFilesNotToExtract.contains(name) ) {
					continue;
				}
				
				// Verzeichnis anlegen wenn es ein reines Verzeichnis ist
				sTargetFile = m_basePath + name;
				File file = new File(sTargetFile);
				if (name.endsWith("/")){
					file.mkdirs();
					// protokoll
					m_vMessages.add(" Verzeichnis " + file.getAbsolutePath() +" wurde angelegt...");
					continue;
				}
				
				// prüfen, ob die Datei auf der Platte exisitert
				String sInfo = "";
				if (file.exists()){
					if (bOverrideExistingFiles) {
						sInfo = "(Datei wurde überschrieben)";
					} else {
						m_vMessages.add( sTargetFile + new MyString(" existiert bereits und wurde NICHT ersetzt...").CTrans());
						continue;
					}
				}
				
								
				
				// Prüfen, ob ein Verzeichnis angelegt werden muss, wenn es eine Datei ist
				File fileTemp = new File(sTargetFile);
				File parent = fileTemp.getParentFile();
				
//				if (parent != null && !(parent.getCanonicalPath() + "/").equals(m_basePath) && !parent.exists()) {
				if (parent != null && !(parent.getAbsolutePath() + "/").equals(m_basePath) && !parent.exists()) {
					parent.mkdirs();
					// protokoll
					m_vMessages.add(" Verzeichnis " + parent.getAbsolutePath() +" wurde angelegt...");
				}
				
				out = new BufferedOutputStream(new FileOutputStream(sTargetFile),BUFFER);

				// protokoll
				m_vMessages.add( sTargetFile + new MyString(" wurde extrahiert...").CTrans() + sInfo);


				
				int length;
				byte data[] = new byte[BUFFER];
				
				while ((length = m_zipIn.read(data,0,BUFFER)) != -1) {
					out.write(data,0,length);
				}
				out.flush();
				out.close();
				
				bRet = true;
			}
		} catch (FileNotFoundException e) {
			m_vMessages.add(new MyString("Datei wurde nicht gefunden oder konnte nicht geschrieben werden. ").CTrans() + sTargetFile);
		} catch (IOException e) {
			m_vMessages.add(new MyString("Ausgabefehler (IOException). ").CTrans() + sTargetFile);
		} 
		
		return bRet;
	}

	
	
	/**
	 * Liest eine gezippte Datei ein und gibt den String der Datei zurück
	 * @param FilenameInZip
	 * @return
	 * @throws IOException
	 */
	public String getTextFromZipfile(String FilenameInZip) throws IOException{
		String sRet = null;
		
		ZipEntry entry = null;

		ByteOutputStream byteOut = new ByteOutputStream();
	
		// Zipfile neu öffnen
		this.openZip();
		
		while ((entry = m_zipIn.getNextEntry()) != null){
			String name = entry.getName();
			
			if (!name.equalsIgnoreCase(FilenameInZip) ) {
				continue;
			}
			
			int length;
			byte data[] = new byte[BUFFER];
			
			
			
			while ((length = m_zipIn.read(data,0,BUFFER)) != -1) {
				byteOut.write(data,0,length);
			}
			
			// Umwandlung in UTF-8 String
			byte[] b = byteOut.getBytes();
			sRet = new String (b,"UTF-8");
			
			break;
		}
		
		return sRet;
	}
	
	
	
	/**
	 * öffnet das Zipfile für die Verarbeitung
	 * @return
	 */
	public boolean openZip() {
		boolean bRet = false;
		
		// schliessen der Streams
		this.closeZip();
		
		// öffnen des Zipfiles
		try {
			m_fileIn = new FileInputStream(m_zipFile);
			// der BufferedInputStream
			m_bufferedInputStream = new BufferedInputStream(m_fileIn);
			
			// öffnen des Streams 
			m_zipIn = new ZipInputStream(m_bufferedInputStream);
			
			bRet = true;
		} catch (FileNotFoundException e) {
			m_vMessages.add(new MyString("Datei wurde nicht gefunden. ").CTrans() + m_zipFile);
		}

		return bRet;
	}


	
	/**
	 * Schliesst die File- und Zip-Inputstreams
	 */
	public void closeZip() {
		try {
			m_zipIn.close();
			m_zipIn = null;
		} catch (Exception e) {
			m_zipIn = null;
		}
		
		try {
			m_bufferedInputStream.close();
			m_bufferedInputStream = null;
		} catch (Exception e) {
			m_bufferedInputStream = null;
		}
		
		
		try {
			m_fileIn.close();
			m_fileIn = null;
		} catch (Exception e) {
			m_fileIn = null;
		}
		
		
	}

	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.closeZip();
	}
	
	public Vector<String> getMessages(){
		return m_vMessages;
	}

	
	
}
