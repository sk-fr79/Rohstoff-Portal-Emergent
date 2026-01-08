package panter.gmbh.indep.archive;

public class Archiver_Normalized_Tablename {

	private String TableName = "";
	private String TableBaseName = "";
	
	public Archiver_Normalized_Tablename(String cName) {
		super();
		
		if (cName.toUpperCase().startsWith("JD_") ||
			cName.toUpperCase().startsWith("JT_")) {
			
			this.TableName = cName;
			this.TableBaseName = cName.substring(3);
		} else {
			this.TableName = "JT_"+cName;
			this.TableBaseName = cName;
		}
		
	}

	/**
	 * 
	 * @return name starting with JT_ oder JD_
	 */
	public String get_TableName() {
		return TableName;
	}

	/**
	 * 
	 * @return name without JT_ oder JD_ prefix
	 */
	public String get_TableBaseName() {
		return TableBaseName;
	}
	
	
	
	
}
