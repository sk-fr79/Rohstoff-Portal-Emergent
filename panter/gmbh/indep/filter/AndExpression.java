package panter.gmbh.indep.filter;

/** The concrete implementation for <tt>AND</tt>-Expressions */
public class AndExpression extends AExpressionWithChildren implements INodeElement, IExpression {
	public AndExpression(INodeElement... e) {
		super(e);
	}

	public IVisitResult accept(INodeVisitor visitor) {
		return visitor.visit(this, getVisitedChildren(visitor));
	}

	public String toString() {
		return (getChildrenSize() > 1 ? _FilterConstants.OP.AND : "") + super.toString();
	}
}
