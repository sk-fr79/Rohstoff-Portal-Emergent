package panter.gmbh.indep.filter;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.ExpressionAndVisitorTest;

/**
 * Class to create Filters from the database.
 * @author nils
 *
 */
public class RuleFactory {
	private RuleFactory() {

	}

	public static INodeElement fromDB(int id) throws SQLException, myException {
		// This is how the conditions are created; first, query the database, then build the filter
		return fromResult( DBUtil.getFilter(id) );
	}
	
	public static INodeElement fromResult(ArrayList<HashMap<String, Object>> result) throws SQLException, myException {
		// Create one outer and-Expression
		AndExpression a = new AndExpression(); 

		OrExpression o = null; // Pointer to current OR to add Comparison
								// expressions to
		int andId = 0;
		for (HashMap<String, Object> r : result) {
			// Anytime andId changes, the current OR-Set is ended and an new one is
			// opened up
			if (andId != ((BigDecimal) r.get("ID_FILTER_AND")).intValue()) {
				if (o != null) {
					a.addChild(o);
				}
				o = new OrExpression();
				andId = ((BigDecimal) r.get("ID_FILTER_AND")).intValue();
			}
			
			// Create current condition
			String op = (String) r.get("CONDITION_OP");
			String positive = (String) r.get("CONDITION_POSITIVE");
			String left = (String) r.get("CONDITION_LEFT");
			Object right = (String) r.get("CONDITION_RIGHT");
			String leftType = (String) r.get("CONDITION_LEFT_TYPE");
			String rightType = (String) r.get("CONDITION_RIGHT_TYPE");
			INodeElement e = null;
			// System.out.println("Creating (lt="+leftType+", rt="+rightType);

			if (!(leftType.equals(_FilterConstants.LEFT.LITERAL.name()) || leftType.equals(_FilterConstants.LEFT.FIELD.name())
					|| leftType.equals(_FilterConstants.LEFT.CONST.name()))) {
				throw new RuntimeException(
						"Only a condition with a left-handside of type CONST, LITERAL and FIELD are supported (this type is "
								+ leftType + ")");
			}
			//TODO: This is a quick fix
			if (leftType.equals(_FilterConstants.LEFT.FIELD.name())) {
				leftType = _FilterConstants.LEFT.LITERAL.name();
			}

			if (!(rightType.equals(_FilterConstants.RIGHT.CONST.name()) || rightType.equals(_FilterConstants.RIGHT.SQL.name()) || rightType
					.equals(_FilterConstants.RIGHT.SET.name()) || rightType.equals(_FilterConstants.RIGHT.FIELD.name()))) {
				throw new RuntimeException(
						"Only a condition with a right-handside of type CONST, FIELD, SET or SQL is supported (this type is "
								+ rightType + ")");
			}

			if (rightType.equals(_FilterConstants.RIGHT.SET.name())) {
				right = new SetString((String)right); //.getSet();
			}
			if (rightType.equals(_FilterConstants.RIGHT.SQL.name())) {
				right = new SQLSetString((String)right); //.getSet();
			}
			
			//TODO: This is a quick fix
			if (leftType.equals(_FilterConstants.LEFT.FIELD.name())) {
				leftType = _FilterConstants.LEFT.LITERAL.name();
			}

			if (rightType.equals(_FilterConstants.RIGHT.LITERAL.name())) {
				right = new DatabaseField((String)right); //.getSet();
			}

			
			Comparison c = Comparison.valueOf(op);

			e = new ComparisonExpression(left, right);
			((ComparisonExpression) e).setComp(c);

			if (!positive.equals("Y")) {
				// This is a negative condition, so encapsulate her into a
				// NOT-Expression
				e = new NotExpression(e);
			}

			o.addChild(e);
		}
		if (o != null) {
			a.addChild(o);
		}
		return a;
	}
	
	public static INodeElement getRule(int id) throws SQLException, myException {
		return fromDB(id);
	}
}
