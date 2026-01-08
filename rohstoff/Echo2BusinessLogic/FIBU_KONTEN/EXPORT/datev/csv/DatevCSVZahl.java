package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public class DatevCSVZahl extends DatevCSVType {
	@SuppressWarnings("unused")
	private int vorkomma, nachkomma, maxLength; 
	
	public DatevCSVZahl(int vorkomma, int nachkomma, int maxLength) {
		this.vorkomma = vorkomma;
		this.nachkomma = nachkomma;
		this.maxLength = maxLength;
	}

	public DatevCSVZahl(int vorkomma) {
		this(vorkomma, 2, 16);
	}

	public DatevCSVZahl() {
		this(10, 2, 13);
	}
	
	public void setValue(final String value) {
		this.value = value;
	}

	public void setValue(final Object value) {
		//TODO: This is only valid vor int, BigDecimal and so forth.
		this.value = value.toString();
	}
	
	public String print() {
		return (this.value == null ? "" : this.value);
	}
}
