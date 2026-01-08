package panter.gmbh.indep.filter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;

import panter.gmbh.indep.exceptions.myException;

public class Filter extends ExpressionValidatorVisitor {
	protected JexlEngine jexl;
	protected UnifiedJEXL ujexl;

	public Filter(HashMap<String, Object> inputData) {
		setData(inputData);
	}

	public Filter() {
	}

	/** 
	 * Shorthand method for using the Visitor like a Filter
	 * Returns true iff the data contained in this filter
	 * matches a rule given.
	 **/
	public boolean matches(INodeElement rule) {
		ValidateVisitResult result = (ValidateVisitResult) rule.accept(this);
		return result.getValidatedTo();
	}

	/** Sets the current data to be validated */
	public void setData(HashMap<String, Object> inputData) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		Iterator<String> it = inputData.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			this.data.put(key.trim().toLowerCase(), inputData.get(key));
		}
	}

	/** Returns the data for the left handside key */
	@Override
	public Object getLeft(Object key) {
		return data.get(((String) key).toLowerCase());
	}

	@Override
	public IVisitResult visit(ComparisonExpression ltExp) {
		Object l = getLeft(ltExp.getLeft());
		Object r = ltExp.getRight();
		String originalR = null;
		String result = null;

		String source = null;
		if (r instanceof String) {
			source = (String) r;
		} else if (r instanceof SetString) {
			source = ((SetString)r).getOrig();
		} else if (r instanceof SQLSetString) {
			source = ((SQLSetString)r).getQuery();
		} else if (r instanceof DatabaseField) {
			// Unwrap it
			r = getLeft(((DatabaseField)r).getFieldName());
			source = null;
		}

		if (source != null) {
			result = substituteJexlExpressions(source);
			if (result != null) {
				originalR = r.toString();
				ltExp.setRight(result);
				if (r instanceof SQLSetString) {
					try {
						// Rebuild result set from substituted query
						((SQLSetString) r).constructFromSQLQuery(result);
					} catch (Exception e) {
						// Signatur of method is not compatible to throw any
						// exceptions; hence, we rethrow a runtime exception
						throw new RuntimeException(
								"Error while executing SQLSetString's query after variable substitution",
								e);
	
					}
				}
				if (r instanceof SetString) {
					// Rebuild SetString after variable substitution
					((SetString) r).rebuild(result);
				}
			} else {
				// No substution has taken place; the result is cached automatically (in SQLSetString)
			}
		}

		// System.out.println(r.getClass());

		boolean answer = (ltExp.getComp().compare(l, r) == 0);
		debugOutput(ltExp.toString(), " [= \"%s\"%s]", answer, l,
				(originalR != null ? ", orig. rExpr: \"" + originalR
						+ "\" => \"" + result + "\"" : ""));
		return new ValidateVisitResult(answer);
	}

	/** 
	 * Substitutes the JEXL expressions in the given string. Returns NULL if 
	 * no substitutions have been made (no variables contained)
	 * @param input
	 * @return
	 */
	public String substituteJexlExpressions(String input) {
		if (jexl == null || ujexl == null) {
			jexl = new JexlEngine();
			ujexl = new UnifiedJEXL(jexl);
		}
		UnifiedJEXL.Expression expr = ujexl.parse(input);
		if (expr.getVariables().size() > 0) {
			// Map variables
			JexlContext context = new MapContext(this.data);
			return expr.evaluate(context).toString();
			// Set the substituted expression back
		}
		return null;
	}
}
