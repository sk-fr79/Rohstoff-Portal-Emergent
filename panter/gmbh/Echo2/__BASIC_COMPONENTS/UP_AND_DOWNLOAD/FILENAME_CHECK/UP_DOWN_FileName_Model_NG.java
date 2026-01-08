package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.file.Path;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FileNameCleaner;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FileName_Model_NG {

//	private RECORD_ARCHIVMEDIEN record;


	private String correctedFileName 	= "";
	private String dateiname 			= "";
	private String downloadName			= "";

//	private Path path;
//	private String strCorrectedPath;

//	private URI filePath;

//	private boolean UriProblem = false;

	private boolean dirty = false;

	private String pfad = "";
	
//	private String id_archivmedien;
	
	
	/*public UP_DOWN_FileName_Model_NG(String p_queryResult, String p_pfad) throws myException {


		this.pfad = p_pfad;
		
		this.originalDbDateiname = p_queryResult;

		this.downloadName = p_queryResult.substring(0, p_queryResult.indexOf("."));

		this.correctedFileName = new FileNameCleaner(this.downloadName).get_filename_clean();

		//TODO: Warum das hier???? "?" is in den erlaubten Zeichen!
		if(this.downloadName.contains("?")){
			dirty = true;
		}
		
		if(! (this.downloadName.equals(correctedFileName))){
			dirty=true;
		}
	}*/

	public UP_DOWN_FileName_Model_NG(String p_downloadname, String p_dateiname, String p_pfad) throws myException {

		this.downloadName = p_downloadname;

		this.dateiname = p_dateiname;

		this.pfad = p_pfad;

		this.correctedFileName = new FileNameCleaner_NG(this.dateiname).get_filename_clean();
		
		if(! (this.downloadName.equals(correctedFileName))){
			this.dirty=true;
		}
	}
	
	/*public UP_DOWN_FileName_Model_NG(Path p_path) throws UnsupportedEncodingException {
		this.path = p_path;

		// TODO: Warum als URI ??
		
		if(p_path.getFileName().toString().startsWith("JT_ADRESSE_ID_3114_Blech")){
			p_path.toUri();
		}
		
		this.filePath = p_path.toUri();

		this.downloadName = p_path.getFileName().toString();
		
		this.strCorrectedPath = UP_DOWN_FileName_Checker_NG.cleanString(downloadName);

		// TODO: das fehlt hier doch...
		this.strCorrectedPath = new FileNameCleaner(this.strCorrectedPath).get_filename_clean();
		
		String step1_str = strCorrectedPath.substring(strCorrectedPath.lastIndexOf(File.separator)+1);
		
		String step2_str = UP_DOWN_FileName_Checker_NG.correctIllegalCharList(step1_str);
		
//		if (!strCorrectedPath.equals(fileName)){
//			dirty = true;
//		}
		
		if(! step2_str.equals(downloadName)){
			dirty = true;
			this.correctedFileName = step2_str;
		}else{
			this.correctedFileName = step1_str;
		}
		//URI name corrected (%E4, uzw...)
		
		//ä, uzw... correction
		
	}*/
	


	public static boolean[] checkFileRight(File filePath){
		boolean[] returnTab = {filePath.canWrite(),filePath.canRead() } ;
		return returnTab;
	}
	

	public String getDownloadname() {
		return downloadName;
	}

	public String getCorrectedFileName() {
		return correctedFileName;
	}

	public String getDateiname() {
		return dateiname;
	}

	public String getPfad() {
		return pfad;
	}

	public boolean isDirty() {
		return dirty;
	}

	@Override
	public String toString() {
		return "UP_DOWN_FileName_Model [\n\tdownloadName=" + downloadName + ",\n\tcorrectedFileName="
				+ correctedFileName + "]";
	}



}
