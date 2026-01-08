package panter.gmbh.indep.filter;

/**
 * Holding the results of a call to <tt>visit()</tt> in an
 * {@see ExpressionPrintVisitor}. Used to pretty-print 
 * the expression tree (as a String).
 * 
 * @author nils
 */
public class PrintVisitResult implements IVisitResult {
	public String text;

	public PrintVisitResult(String t) {
		this.text = t;
	}

	@Override
	public boolean doVisitMore() {
		return true;
	}

	public String getText() {
		return text;
	}

	@Override
	public void mergeWith(IVisitResult r) {
		if (r != null) {
			this.text += ((PrintVisitResult) r).getText();
		}
	}
}

