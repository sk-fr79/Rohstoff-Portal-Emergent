package panter.gmbh.indep.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingFormatArgumentException;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;

import panter.gmbh.indep.filter.Comparison;


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
public class ExpressionContainsVisitor implements INodeVisitor {
	ComparisonExpression contained;
	
	public ExpressionContainsVisitor(ComparisonExpression ce) {
		this.contained = ce;
	}
	public Object getLeft(Object key) {
	//	return data.get(((String)key).toUpperCase());
		return ((String)key).toUpperCase(); 
	}

	@Override
	public IVisitResult visit(ComparisonExpression ce) {
		Object l = getLeft(ce.getLeft());
		Object r = ce.getRight();
		ComparisonExpression eq = new ComparisonExpression(null, null);
		boolean answer = (eq.getComp().compare(contained.getLeft(), l) + eq.getComp().compare(contained.getRight(), r) == 0);
		//System.out.println(l+"="+r+ " ==== "+contained.getLeft()+"="+contained.getRight()+"   ==>"+answer);
		return new ValidateVisitResult(answer);
	}

	public IVisitResult visit(UnaryExpression e, IVisitResult child) {
		boolean answer = ((ValidateVisitResult) child).getValidatedTo();
		return new ValidateVisitResult(answer);
	}

	public IVisitResult visit(AndExpression andExp,
			ArrayList<IVisitResult> children) {
		for (IVisitResult child : children) {
			if (child != null) {
				boolean b = ((ValidateVisitResult) child).getValidatedTo();
				if (b) {
					//System.out.println("AND: "+andExp.toString() +" ===> true");
					return new ValidateVisitResult(true);
				}
			}
		}
		return new ValidateVisitResult(false);
	}

	public IVisitResult visit(OrExpression orExp,
			ArrayList<IVisitResult> children) {
		for (IVisitResult child : children) {
			if (child != null) {
				boolean b = ((ValidateVisitResult) child).getValidatedTo();
				if (b) {
//					System.out.println("OR: "+orExp.toString() +" ===> true");
					return new ValidateVisitResult(true);
				}
			}
		}
		return new ValidateVisitResult(false);
	}
	
	/** Checks whether rule is contained in the current tree */
	public boolean matches(INodeElement rule) {
		ValidateVisitResult result = (ValidateVisitResult) rule.accept(this);
		return result.getValidatedTo();
	}
}