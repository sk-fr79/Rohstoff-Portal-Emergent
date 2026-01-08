package panter.gmbh.indep.filter;

/**
 * Result to hold the evaluation of a visit a visitor did.
 * 
 * @author nils
 */
public class ValidateVisitResult implements IVisitResult {
	/** The value the result corresponds to */
	public boolean validatedTo;

	public ValidateVisitResult(boolean b) {
		this.validatedTo = b;
	}

	@Override
	public boolean doVisitMore() {
		return true;
	}

	public boolean getValidatedTo() {
		return validatedTo;
	}

	@Override
	public void mergeWith(IVisitResult r) {
	}
}
