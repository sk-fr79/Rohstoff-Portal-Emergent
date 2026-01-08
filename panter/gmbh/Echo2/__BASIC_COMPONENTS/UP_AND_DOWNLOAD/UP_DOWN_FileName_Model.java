package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.file.Path;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FileName_Model {

	private RECORD_ARCHIVMEDIEN record;
	private String originalDbDateiname;

	private String fileName;
	private String correctedFileName;

	private Path path;
	private String strCorrectedPath;

	private URI filePath;

	private boolean UriProblem = false;

	private boolean dirty = false;

	private String pfad = "";
	
	public UP_DOWN_FileName_Model(RECORD_ARCHIVMEDIEN p_record) throws myException {
		this.record = p_record;

		this.fileName = record.get_DATEINAME_cUF();

		this.setOriginalDbDateiname(record.get_DATEINAME_cUF());

		this.correctedFileName = new FileNameCleaner(this.fileName).get_filename_clean();

	}

	public UP_DOWN_FileName_Model(String p_queryResult, String p_pfad) throws myException {

		this.fileName = p_queryResult;

		this.pfad = p_pfad;
		
		this.setOriginalDbDateiname(p_queryResult);

		this.correctedFileName = new FileNameCleaner(this.fileName).get_filename_clean();

		//TODO: Warum das hier???? "?" is in den erlaubten Zeichen!
		if(this.fileName.contains("?")){
			dirty = true;
		}
		
		if(! (this.fileName.equals(correctedFileName))){
			dirty=true;
		}
	}

	public UP_DOWN_FileName_Model(Path p_path) throws UnsupportedEncodingException {
		this.path = p_path;

		// TODO: Warum als URI ??
		if(p_path.getFileName().toString().startsWith("JT_ADRESSE_ID_3114_Blech")){
			p_path.toUri();
		}
		
		this.filePath = p_path.toUri();

		fileName = p_path.getFileName().toString();
		
		this.strCorrectedPath = UP_DOWN_FileName_Checker.cleanString(fileName);

		// TODO: das fehlt hier doch...
		this.strCorrectedPath = new FileNameCleaner(this.strCorrectedPath).get_filename_clean();
		
		String step1_str = strCorrectedPath.substring(strCorrectedPath.lastIndexOf(File.separator)+1);
		
		String step2_str = UP_DOWN_FileName_Checker.correctIllegalCharList(step1_str);
		
//		if (!strCorrectedPath.equals(fileName)){
//			dirty = true;
//		}
		
		if(! step2_str.equals(fileName)){
			dirty = true;
			this.correctedFileName = step2_str;
		}else{
			this.correctedFileName = step1_str;
		}
		//URI name corrected (%E4, uzw...)
		
		//ä, uzw... correction
		
	}
	
	public UP_DOWN_FileName_Model(Path p_path, boolean pb) throws UnsupportedEncodingException {
		this.path = p_path;
	
		this.filePath = p_path.toUri();

		this.fileName = p_path.getFileName().toString();
		
		this.strCorrectedPath = UP_DOWN_FileName_Checker.cleanString(fileName);

		// TODO: das fehlt hier doch...
		this.strCorrectedPath = new FileNameCleaner(this.strCorrectedPath).get_filename_clean();
		
		String step1_str = strCorrectedPath.substring(strCorrectedPath.lastIndexOf(File.separator)+1);
		
		String step2_str = UP_DOWN_FileName_Checker.correctIllegalCharList(step1_str);
		
//		if (!strCorrectedPath.equals(fileName)){
//			dirty = true;
//		}
		
		if(! step2_str.equals(fileName)){
			dirty = true;
			this.correctedFileName = step2_str;
		}else{
			this.correctedFileName = step1_str;
		}
		//URI name corrected (%E4, uzw...)
		
		//ä, uzw... correction
		
	}
	
	
	public static boolean[] checkFileRight(File filePath){
		boolean[] returnTab = {filePath.canWrite(),filePath.canRead() } ;
		return returnTab;
	}
	
	public RECORD_ARCHIVMEDIEN getRecord() {
		return record;
	}

	public String getFileName() {
		return fileName;
	}

	public String getCorrectedFileName() {
		return correctedFileName;
	}

	public Path getPath() {
		return path;
	}

	public String getStrCorrectedPath() {
		return strCorrectedPath;
	}

	public String getOriginalDbDateiname() {
		return originalDbDateiname;
	}

	public void setOriginalDbDateiname(String originalDbDateiname) {
		this.originalDbDateiname = originalDbDateiname;
	}

	public boolean hasUriProblem() {
		return UriProblem;
	}

	public URI getFilePath() {
		return filePath;
	}

	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	public boolean isDirty() {
		return dirty;
	}

	@Override
	public String toString() {
		return "UP_DOWN_FileName_Model [\n\trecord=" + record + ",\n\tfileName=" + fileName + ",\n\tcorrectedFileName="
				+ correctedFileName + ",\n\tpath=" + path + ",\n\tstrCorrectedPath=" + strCorrectedPath + "]";
	}



}
