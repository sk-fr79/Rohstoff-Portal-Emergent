package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

/**
 * Exception thrown by the {@link AccountFinder} during processing a vpos.
 * Thrown in two cases,
 * (1) when the vpos cannot be assigned an account number 
 * (2) when more than one rule applies, so that the vpos would have an account number ambiguity
 * @author nils
 *
 */
public class DatevExportException extends Exception {
	public enum TYPE {
		NO_RULE_FOUND, MORE_THAN_ONE_RULE_FOUND, VARIABLE_RULE_EXCEPTION
	}
	private TYPE type;
	
	public DatevExportException(String message) {
		super(message);
	}

	public DatevExportException(String message, TYPE type) {
		super(message);
		this.type = type;
	}

	public DatevExportException(String message, Exception e) {
		super(message, e);
	}

	public DatevExportException(String message, Exception e, TYPE type) {
		super(message, e);
		this.type = type;
	}
	
	public TYPE getType() {
		return type;
	}
}
