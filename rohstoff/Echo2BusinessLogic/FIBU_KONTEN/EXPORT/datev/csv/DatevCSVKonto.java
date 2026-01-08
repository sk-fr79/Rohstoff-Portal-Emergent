package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public class DatevCSVKonto extends DatevCSVType {

	@SuppressWarnings("unused")
	private int minLength, maxLength;
	public DatevCSVKonto(final int minLength, final int maxLength) {
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	public DatevCSVKonto() {
		this(8, 10);
	}
	
	public void setValue(final String value) {
		this.value = value;
	}

	public void setValue(final Object value) {
		//TODO: This should be okay for ints; Konto's are xxxx, where x is in {0..10}
		this.value = value.toString();
	}
	
	public String print() {
		return value;
	}
}
