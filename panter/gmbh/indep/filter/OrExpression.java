package panter.gmbh.indep.filter;

/** The concrete implementation for <tt>OR</tt>-Expressions */
public class OrExpression extends AExpressionWithChildren implements IExpression, INodeElement {
	public OrExpression(INodeElement... e) {
		super(e);
	}

	public IVisitResult accept(INodeVisitor visitor) {
		return visitor.visit(this, getVisitedChildren(visitor));
	}

	public String toString() {
		return (getChildrenSize() > 1 ? _FilterConstants.OP.OR : "") + super.toString();
	}
}
