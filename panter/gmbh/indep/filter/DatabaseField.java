package panter.gmbh.indep.filter;

public class DatabaseField {
	private String fieldName;
	public DatabaseField (String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldName() {
		return this.fieldName;
	}
	public String toString() {
		return this.fieldName;
	}
}
