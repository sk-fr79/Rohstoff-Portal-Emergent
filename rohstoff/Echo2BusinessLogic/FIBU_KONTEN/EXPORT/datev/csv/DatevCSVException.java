package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public class DatevCSVException extends Exception {

	public DatevCSVException(String message) {
		super(message);
	}

	public DatevCSVException(String message, DatevCSVException e) {
		super(message, e);
	}

}
