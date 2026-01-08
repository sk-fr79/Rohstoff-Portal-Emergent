package panter.gmbh.indep.filter;

/**
 * The result of a visit() and accept() calls.
 * 
 * @author nils
 * 
 */
public interface IVisitResult {
	abstract boolean doVisitMore();

	abstract void mergeWith(IVisitResult r);
}
