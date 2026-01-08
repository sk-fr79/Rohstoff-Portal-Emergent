package panter.gmbh.indep.filter;

/**
 * Expression to implement boolean NOT(.), having one child
 * expression to which it applies.
 * @author nils
 * 
 */
public class UnaryExpression implements IExpression, INodeElement {
	INodeElement child;
	String id; 

	public String getId() {
		return id;
	}
	public UnaryExpression() {
		
	}
	public UnaryExpression(String id, INodeElement e) {
		this.id = id;
		this.child = e;
	}

	public INodeElement getChild() {
		return this.child;
	}

	public IVisitResult accept(INodeVisitor visitor) {
		return visitor.visit(this, this.child.accept(visitor));
	}

	public String toString() {
		return id+"(" + this.child.toString() + ")";
	}
}
