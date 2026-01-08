package panter.gmbh.indep.filter;

import java.util.ArrayList;

/**
 * Visitor to pretty-print the expression tree; this is more
 * some example to demonstrate the use of the visitor pattern.
 * The return value of the Visitors used here is always an
 * {@see IVisitResult}, concretely used below in a 
 * {@see PrintVisitResult} and a {@see ValidateVisitResult} 
 * to make all visitor results compatible with the above
 * expression structure.  
 * 
 * @author nils
 * 
 */
public class ExpressionPrintVisitor implements INodeVisitor {
	public IVisitResult visit(ComparisonExpression inExp) {
		return new PrintVisitResult("" + inExp.getLeft() + " "
				+ (inExp.getComp() == Comparison.NULL ? "IS_NULL" : inExp.getComp().getId() + " " + inExp.getRight()
				+ ""));
	}

	public IVisitResult visit(UnaryExpression ne, IVisitResult child) {
		String s = ((PrintVisitResult) child).getText();
		return new PrintVisitResult(ne.getId()+"(" + s +")");
	}

	public IVisitResult visitHelper(AExpressionWithChildren cExp,
			ArrayList<IVisitResult> children, String concat) {
		int i = 0, j = children.size();
		StringBuilder sb = new StringBuilder();
		for (IVisitResult child : children) {
			i++;
			if (child != null) {
				String text = ((PrintVisitResult) child).getText();
				if (text != null) {
					sb.append(text);
					if (i != j) {
						sb.append(" ");
						sb.append(concat);
						sb.append(" ");
					}
				}
			}
		}
		if (children.size() > 1 && sb.length() > 0) {
			sb.insert(0, "(");
			sb.append(")");
		}
		return new PrintVisitResult(sb.toString());
	}

	/** Concatenates the children with the usual || */
	public IVisitResult visit(OrExpression orExp,
			ArrayList<IVisitResult> children) {
		return visitHelper(orExp, children, " "+_FilterConstants.OP.OR+" ");
	}

	public IVisitResult visit(AndExpression andExp,
			ArrayList<IVisitResult> children) {
		return visitHelper(andExp, children, " "+_FilterConstants.OP.AND+" ");
	}
}

