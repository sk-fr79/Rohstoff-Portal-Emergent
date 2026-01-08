package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

public class Assignment {
	String tableName;
	String id;
	public Assignment(String tableName, String id) {
		this.tableName = tableName;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public String getTableName() {
		return tableName;
	}
}