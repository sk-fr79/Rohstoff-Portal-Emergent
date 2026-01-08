package panter.gmbh.indep.ZIP;

public class ZIP_NG_NamePair {

	private String c_NameInZip = null;
	private String c_NameRealInFileSystem = null;
	
	
	/**
	 * 
	 * @param cNameInZip = Name der Datei im Zip-Archiv (incl. Endung)
	 * @param cNameRealInFileSystem = Name der Datei im Filesystem (incl. Pfad und Endung)
	 */
	public ZIP_NG_NamePair(String cNameInZip, String cNameRealInFileSystem) {
		super();
		this.c_NameInZip = cNameInZip;
		this.c_NameRealInFileSystem = cNameRealInFileSystem;
	}
	
	
	public String get_cNameInZip() {
		return c_NameInZip;
	}
	public String get_cNameRealInFileSystem() {
		return c_NameRealInFileSystem;
	}
	
	
	
}
