package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public class DatevCSVText extends DatevCSVType {

	@SuppressWarnings("unused")
	private int minLength, maxLength;
	public DatevCSVText(final int minLength, final int maxLength) {
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	public DatevCSVText(final int maxLength) {
		this(1, maxLength);
	}
	
	public void setValue(final String value) {
		this.value = value;
	}
	public void setValue(final Object value) {
		this.value = value.toString();
	}
	
	public String print() {
		String v = this.value;
		if (v == null) return "\"\"";
		
		return "\""+(v.length() > maxLength ? v.substring(0, maxLength) : v)+"\"";
	}
}
