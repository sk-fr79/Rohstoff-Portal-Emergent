package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public class DatevCSVReserved extends DatevCSVType {

	@Override
	public void setValue(String s) {
		setValue((Object)s);
	}

	@Override
	public void setValue(Object o) {
		if (o != null) {
			throw new IllegalArgumentException("A reserved field MUST be set to null");
		}
	}
	public String print() {
		return "";
	}

}
