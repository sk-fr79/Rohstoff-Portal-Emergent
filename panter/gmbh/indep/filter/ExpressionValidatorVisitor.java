package panter.gmbh.indep.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingFormatArgumentException;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;

/**
 * Visitor to validate the expression tree against a set of data.
 * The data is stored in the {@link data} field. When the corresponding
 * {@see visit}-methods are called (given the concrete instances
 * of the nodes), the nodes can then be validated. The semantics
 * is such that iff a {@link ComparisonExpression} validates to true, 
 * the usual boolean semantics of NOT, AND and OR are applied, and
 * thus the 
 * 
 * @author nils
 * 
 */
public class ExpressionValidatorVisitor implements INodeVisitor {
	protected HashMap<String, Object> data;
	protected boolean debug = false;
	private StringBuffer debugBuffer = new StringBuffer();
	public void debug(String s) {
		debugBuffer.append(s);
		debugBuffer.append('\n');
	}
	public void flushDebug() {
		if (debugBuffer != null) {
			debugBuffer = null;
			debugBuffer = new StringBuffer();
		}
	}
	
	public String getDebugBuffer() {
		String answer = debugBuffer.toString();
		flushDebug();
		return answer;
	}
	public void setDebug(boolean d) {
		this.debug = d;
	}
	
	public boolean getDebug() {
		return this.debug;
	}
	
	public void debugOutput(String s, String s2, boolean b, Object... o) {
		if (debug) {
			String outString = "";
			try {
				outString = String.format(s2, o);
			} catch (MissingFormatArgumentException e) {
				
			}
			int l = (int) (8 + 8 * Math.floor(s.length() / 8) - s.length());
			String pr = "["+(b  ? "T" : "F")+"] "; // Prefix containing [T] (true) or [F] (false)
			debug(pr + s + (" " + (new String(new char[l]).replace("\0",	" ")) + outString));
		}
	}

	public ExpressionValidatorVisitor() {
	}
	
	public ExpressionValidatorVisitor(HashMap<String, Object> data) {
		this.data = data;
	}

	public Object getLeft(Object key) {
		return data.get(((String)key).toUpperCase());
	}

	@Override
	public IVisitResult visit(ComparisonExpression ce) {
		Object l = getLeft(ce.getLeft());
		Object r = ce.getRight();
		boolean answer = (ce.getComp().compare(l, r) == 0);
		debugOutput(ce.toString(), " (is %s)", answer, l);
		return new ValidateVisitResult(answer);
	}

	public IVisitResult visit(UnaryExpression e, IVisitResult child) {
		boolean answer = !((ValidateVisitResult) child).getValidatedTo();
		debugOutput(e.toString(), " ", answer);
		return new ValidateVisitResult(answer);
	}

	public IVisitResult visit(AndExpression andExp,
			ArrayList<IVisitResult> children) {
		for (IVisitResult child : children) {
			if (child != null) {
				boolean b = ((ValidateVisitResult) child).getValidatedTo();
				if (!b) {
					if (andExp.getChildrenSize() > 1) {
						debugOutput(andExp.toString(), " |= %s", false);
					}
					return new ValidateVisitResult(false);
				}
			}
		}
		if (andExp.getChildrenSize() > 1) {
			debugOutput(andExp.toString(), " |= %s", true);
		}
		return new ValidateVisitResult(true);
	}

	public IVisitResult visit(OrExpression orExp,
			ArrayList<IVisitResult> children) {
		for (IVisitResult child : children) {
			if (child != null) {
				boolean b = ((ValidateVisitResult) child).getValidatedTo();
				if (b) {
					if (orExp.getChildrenSize() > 1) {
						debugOutput(orExp.toString(), " |= %s", true);
					}
					return new ValidateVisitResult(true);
				}
			}
		}
		if (orExp.getChildrenSize() > 1) {
			debugOutput(orExp.toString(), " |= %s", false);
		}
		return new ValidateVisitResult(false);
	}
}