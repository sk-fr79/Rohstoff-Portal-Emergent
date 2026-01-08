package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

public class UP_DOWN_FILENAME_Row_Model{
	
	private String correctedFileName;
	
//	private Path 	fileSystemPath;
	private String 	pfad;
	private String 	dateiname;
	private String 	downloadname;
	private RENAME_STATUS status;
	private String fs_dateiname;
	
	public UP_DOWN_FILENAME_Row_Model(
			String p_correctedFileName,
			String p_dateiname, 
			String p_downloadname,
			String p_pfad) {
		
		this.correctedFileName 	= p_correctedFileName;
		this.pfad 				= p_pfad;
		this.dateiname 			= p_dateiname;
		this.setStatus(RENAME_STATUS.UNCORRECT);

		this.fs_dateiname = new FileNameCleaner_NG(p_dateiname).clean_fs(p_dateiname);
		
		this.downloadname = p_downloadname;
	}

	public String getCorrectedFileName() {
		return correctedFileName;
	}

	public String get_fs_dateiname() {
		return fs_dateiname;
	}

	public String getDownloadname() {
		return downloadname;
	}

//	public String getPfad() {
//		return pfad;
//	}

	public String getDateiname() {
		return dateiname;
	}

	public void setCorrectedFileName(String correctedFileName) {
		this.correctedFileName = correctedFileName;
	}

//	public void setFileSystemPath(Path fileSystemPath) {
//		this.downloadname = fileSystemPath;
//	}

	public RENAME_STATUS getStatus() {
		return status;
	}

	public void setStatus(RENAME_STATUS status) {
		this.status = status;
	}

	public String getPfad() {
		return pfad;
	}
}