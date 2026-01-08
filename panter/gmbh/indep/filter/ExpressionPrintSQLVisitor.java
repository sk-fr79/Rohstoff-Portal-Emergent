package panter.gmbh.indep.filter;

import java.util.ArrayList;

import panter.gmbh.indep.dataTools.query.ID;
import panter.gmbh.indep.dataTools.query.Query;
import panter.gmbh.indep.dataTools.query.Query.ToStringMode;
import panter.gmbh.indep.dataTools.query.SQLPart;
import panter.gmbh.indep.dataTools.query.V;

/**
 * Visitor to pretty-print the expression tree; this is more some example to
 * demonstrate the use of the visitor pattern. The return value of the Visitors
 * used here is always an {@see IVisitResult}, concretely used below in a {@see
 * PrintVisitResult} and a {@see ValidateVisitResult} to make all visitor
 * results compatible with the above expression structure.
 * 
 * @author nils
 * 
 */
public class ExpressionPrintSQLVisitor extends ExpressionPrintVisitor {
	public ExpressionPrintSQLVisitor() {
		this(ToStringMode.REGULAR);
	}
	
	public ExpressionPrintSQLVisitor(ToStringMode mode) {
		super();
		this.mode = mode;
	}
	private boolean quoteRightHandsidesAsLiterals = true;
	private ToStringMode mode;

	/**
	 * Strings the {@see ComparisonExpression} e such as that the left
	 * hadside is consideded to be an identifier and the right hand-side
	 * is treated as an identifier or a literal, if 
	 * {@value #quoteRightHandsidesAsLiterals} is true.
	 */
	public IVisitResult visit(ComparisonExpression e) {
		StringBuffer sb = new StringBuffer();
		Object left = e.getLeft();
		String leftStringed; 
		if (left instanceof SQLPart) {
			leftStringed = ((SQLPart)left).toString(mode);
		} else {
			leftStringed = left.toString();
		}
		
		sb.append(leftStringed);
		sb.append(" ");
		sb.append(e.getComp().getId());
		if (e.getComp() == Comparison.IS) {
			String op = e.getRight().toString().toLowerCase().replaceAll("[\\s_\\-]+", "");
			//TODO: Another IS... operator (see wikipedia: SQL)
			if (op.equals("notnull")) {
				sb.append(" NOT NULL");
			} else {
				sb.append(" NULL");
			}
			return new PrintVisitResult(sb.toString());
		}
		sb.append(" ");
		
		Object right = e.getRight();
		String rightString;
		if (quoteRightHandsidesAsLiterals) {
			rightString = Query.quoteLiteral(right, quoteRightHandsidesAsLiterals);
		} else {
			right = Query.getTransformed(right, true, false);
			if (right instanceof String) {
				right = new ID((String)right);
			} else if (right instanceof Number) {
				right = new V(right.toString());
			} 
			
			if (right instanceof SQLPart) {
				rightString = ((SQLPart)right).toString(mode);
			} else {
				rightString = right.toString();
			}
		}
		sb.append(rightString);
		return new PrintVisitResult(sb.toString());
	}

	public IVisitResult visit(NotExpression e, IVisitResult child) {
		String s = ((PrintVisitResult) child).getText();
		return new PrintVisitResult(_FilterConstants.OP.NOT + "(" + s + ")");
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
						// Concat is the "operator", e.g. AND, OR
						sb.append(concat);
						sb.append(" ");
					}
				}
			}
		}
		if (sb.length() > 0) {
			sb.insert(0, "(");
			sb.append(")");
		}
		return new PrintVisitResult(sb.toString());
	}

	/** Concatenates the children with the usual || */
	public IVisitResult visit(OrExpression orExp,
			ArrayList<IVisitResult> children) {
		return visitHelper(orExp, children, " " + _FilterConstants.OP.OR + " ");
	}

	/** Concatenates the children with the usual && */
	public IVisitResult visit(AndExpression andExp,
			ArrayList<IVisitResult> children) {
		return visitHelper(andExp, children, " " + _FilterConstants.OP.AND
				+ " ");
	}

	public boolean isQuoteRightHandsidesAsLiterals() {
		return quoteRightHandsidesAsLiterals;
	}

	public void setQuoteRightHandsidesAsLiterals(boolean b) {
		this.quoteRightHandsidesAsLiterals = b;
	}
}
