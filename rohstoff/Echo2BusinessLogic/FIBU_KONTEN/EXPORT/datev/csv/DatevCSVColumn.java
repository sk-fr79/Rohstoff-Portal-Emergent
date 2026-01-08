package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;


/**
 * This class represents one line in a CSV file; the <code>description</code>
 * field is optional
 * @author charlie
 *
 */
public class DatevCSVColumn {
	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getMust() {
		return must;
	}

	public void setMust(Boolean must) {
		this.must = must;
	}

	public DatevCSVType getType() {
		return type;
	}

	public void setType(DatevCSVType type) {
		this.type = type;
	}

	private Boolean must;
	private DatevCSVType type;
	
	public DatevCSVColumn(String description, Boolean must, DatevCSVType type) {
		this.description = description;
		this.must = must;
		this.type = type;
	}
	
	public DatevCSVColumn reset() {
		type.reset();
		return this;
	}
	
	public DatevCSVColumn setValue(String s) {
		type.setValue(s);
		return this;
	}

	public DatevCSVColumn setValue(Object s) {
		type.setValue(s);
		return this;
	}


	public String toCSVString() throws DatevCSVException {
		String answer = type.print();
		if (this.must.equals(Boolean.TRUE) && answer == null) {
			//TODO: Exception schmeissen oder nicht?
		//	throw new DatevCSVException("Fehler im Feld "+description+", es darf nicht leer sein.");
		}
		if (answer == null) return "";
		return answer;
	}


}
