package panter.gmbh.indep.filter;

import java.util.ArrayList;

/**
 * Interface for visitors visiting expression trees
 * 
 * @author nils
 * 
 */
public interface INodeVisitor {
	IVisitResult visit(ComparisonExpression eqExp); // Temporary

	IVisitResult visit(UnaryExpression notExp, IVisitResult child);

	IVisitResult visit(AndExpression andExp,
			ArrayList<IVisitResult> children);

	IVisitResult visit(OrExpression orExp, ArrayList<IVisitResult> children);
}