package panter.gmbh.indep.filter;

import java.util.Collection;


/**
 * This is the acutal class for comparisons in the form of 
 * <code>left OP right</code>, where <tt>OP</tt> is an operator
 * operating on data given on the left (usually provided by 
 * a checking visitor) to compare it to (a) value(s) on the right. 
 * @author nils
 *
 */
public class ComparisonExpression implements INodeElement, IExpression {
	private Object left;
	private Comparison comp;
	private Object right;

	public ComparisonExpression(Object l, Comparison c, Object r) {
		this.left = l;
		this.right = r;
		this.comp = c;
	}
	
	/** Shorthand: if no Comparison is given, equaliy comparison is used */
	public ComparisonExpression(Object l, Object r) {
		this(l, Comparison.EQ, r);
	}

	public Object getLeft() {
		return this.left;
	}

	public Object getRight() {
		return this.right;
	}

	public void setComp(Comparison c) {
		this.comp = c;
	}

	public Comparison getComp() {
		return this.comp;
	}

	public IVisitResult accept(INodeVisitor visitor) {
		return visitor.visit(this);
	}

	public String toString() {
		String q = "\"";
		if (this.right instanceof Collection) {
			q = "";
		}
		return this.left.toString() + " " + this.getComp().getId() + (this.comp.getId() != "NULL" ? " "+q
				+ this.right.toString() + ""+q : "");
	}
	
	public void setLeft(Object l) {
		this.left = l;
	}

	public void setRight(Object r) {
		this.right = r;
	}
}
