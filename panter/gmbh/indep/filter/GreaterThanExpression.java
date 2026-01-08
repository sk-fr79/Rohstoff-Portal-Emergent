package panter.gmbh.indep.filter;

/**
 * Example classed for a bit of syntactic sugar. All comparisons
 * implemented in the {@see Comparison} class can be used here
 * and defined as own classes. 
 * @author nils
 *
 */
public class GreaterThanExpression extends ComparisonExpression implements IExpression {
	public GreaterThanExpression(Object l, Object r) {
		super(l, Comparison.GT, r);
	}
}
