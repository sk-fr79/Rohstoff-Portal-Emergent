package panter.gmbh.indep.filter;

import java.util.ArrayList;

/**
 * An abstract expression containing <tt>n</tt> children. This is
 * used for the {@see AndExpression} and {@see OrExpression}, which
 * both subclass this class.
 * @author nils
 * 
 */
abstract class AExpressionWithChildren implements INodeElement {
	ArrayList<INodeElement> children = new ArrayList<INodeElement>();

	public AExpressionWithChildren(INodeElement... e) {
		// create new Array of elements
		for (int i = 0, j = e.length; i < j; i++) {
			this.children.add(e[i]);
		}
	}

	public INodeElement addChild(INodeElement e) {
		this.children.add(e);
		return e;
	}

	public INodeElement getLastChild() {
		if (children.size() == 0) {
			return null;
		}
		return children.get(children.size()-1);
	}	

	public INodeElement removeLastChild() {
		if (children.size() == 0) {
			return null;
		}
		return children.remove(children.size()-1);
	}
	
	public boolean isEmpty() {
		return children.size() == 0;
	}

	public int getChildrenSize() {
		return children.size();
	}
	
	public ArrayList<INodeElement> getChildren() {
		return children;
	}
	
	protected ArrayList<IVisitResult> getVisitedChildren(
			INodeVisitor visitor) {
		ArrayList<IVisitResult> visitedChildren = new ArrayList<IVisitResult>();
		IVisitResult answer;
		visit: for (INodeElement child : children) {
			answer = child.accept(visitor);
			if (answer != null && !answer.doVisitMore()) {
				break visit;
			} else {
				visitedChildren.add(answer);
			}
		}
		return visitedChildren;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (children.size() > 1) {
			sb.append("(");
		}
		for (INodeElement child : children) {
			if (sb.length() > 1) {
				sb.append(", ");
			}
			sb.append(child.toString());
		}
		if (children.size() > 1) {
			sb.append(")");
		}
		return sb.toString();
	}
}