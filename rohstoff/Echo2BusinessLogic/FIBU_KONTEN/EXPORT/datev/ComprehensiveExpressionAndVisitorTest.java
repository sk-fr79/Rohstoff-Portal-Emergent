package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;

public class ComprehensiveExpressionAndVisitorTest {
	public INodeElement fromDB(int id) {
		return FilterFactory.fromDB(id);
	}

	public class SetString {
		protected Collection<Object> set = new ArrayList<Object>();

		public SetString(String s) {
			construct(s, ",");
		}
		public SetString() {
			
		}

		private void construct(String s, String split) {
			if (s == null) {
				return;
			}
			String[] parts = s.split(split);
			for (int i = 0, j = parts.length; i < j; i++) {
				set.add(parts[i].trim());
			}
		}

		public Collection<Object> getSet() {
			return set;
		}
	}
	
	public class SQLQueryString extends SetString {
		public SQLQueryString(String query) {
			super();
			constructFromSQLQuery(query);
		}
		public void constructFromSQLQuery(String query) {
			ArrayList<HashMap<String, Object>> r;
			try {
				System.out.println(query);
				r = DBUtil.select(query);
				for (HashMap<String, Object> hm : r) {
					for (Object o : hm.values()) {
						set.add((String)o);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class FilterFactory {
		private FilterFactory() {

		}

		public static INodeElement fromDB(int id) {
			// This is how the conditions are created; first, query the database
			ArrayList<HashMap<String, Object>> result = null;
			try {
				result = DBUtil
						.select("SELECT o.id_filter_and, condition_left, condition_op, condition_right, "
								+ "condition_left_type, condition_right_type, condition_positive "
								+ "FROM jt_filter_or o JOIN jt_filter_and a ON (a.id_filter_and = o.id_filter_and) "
								+ "JOIN jt_filter f ON (f.id_filter = a.id_filter) "
								+ (id > 0 ? "WHERE f.id_filter = " + id + " "
										: "") + "ORDER BY o.id_filter_and ASC");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ComprehensiveExpressionAndVisitorTest eavt = new ComprehensiveExpressionAndVisitorTest();

			AndExpression a = null; // Pointer to current AND to add
									// OR-Expressions to
			OrExpression o = null; // Pointer to current OR to add Comparison
									// expressions to
			int andId = 0;
			for (HashMap<String, Object> r : result) {
				// System.out.println(r);

				if (andId != ((BigDecimal) r.get("ID_FILTER_AND")).intValue()) {
					// Create new "outer" and
					a = eavt.new AndExpression();
					andId = ((BigDecimal) r.get("ID_FILTER_AND")).intValue();
					if (o == null) {
						o = eavt.new OrExpression();
					} else {
						a.addChild(o);
						o = eavt.new OrExpression();
					}
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

				if (!leftType.equals("LITERAL")) {
					throw new RuntimeException(
							"Only a condition with a left-handside of type LITERAL is supported (this type is "
									+ leftType + ")");
				}

				if (!(rightType.equals("CONST") || rightType.equals("SQL") || rightType
						.equals("SET"))) {
					throw new RuntimeException(
							"Only a condition with a right-handside of type CONST, SET or SQL is supported (this type is "
									+ rightType + ")");
				}

				/*
				 * 
				 * if (op.equals("EQ")) { e = eavt.new EqualsExpression(left,
				 * right); } else if (op.equals("GT")) { e = eavt.new
				 * GreaterThanExpression(left, right); } else if
				 * (op.equals("GEQ")) { e = eavt.new OrExpression(eavt.new
				 * GreaterThanExpression(left, right), eavt.new
				 * EqualsExpression(left, right)); } else if (op.equals("LT")) {
				 * e = eavt.new LessThanExpression(left, right); } else if
				 * (op.equals("LEQ")) { e = eavt.new OrExpression(eavt.new
				 * LessThanExpression(left, right), eavt.new
				 * EqualsExpression(left, right)); } else if (op.equals("NULL"))
				 * { e = eavt.new IsNullExpression(left); } else if
				 * (op.equals("IN")) { e = eavt.new InExpression(left, eavt.new
				 * SetString(right).getSet()); } else if (op.equals("COMP")) {
				 * System.out.println("Building COMPEXP"); e = eavt.new
				 * CompExpression(left, right);
				 * ((CompExpression)e).setComp(Comparison.EQ);
				 * //((CompExpression)e).setSQL("1");
				 * 
				 * } else { throw new
				 * RuntimeException("Unknown comparison operator " + op +
				 * " while creating filter from database (id = " + id + ")"); }
				 */
				if (rightType.equals("SET")) {
					right = eavt.new SetString((String)right).getSet();
				}
				if (rightType.equals("SQL")) {
					right = eavt.new SQLQueryString((String)right).getSet();
				}

				Comparison c = Comparison.valueOf(op);

				// for (Comparison s : Comparison.values() )

				e = eavt.new CompExpression(left, right);
				((CompExpression) e).setComp(c);
				// ((CompExpression)e).setSQL("1");

				/*
				 * throw new RuntimeException("Unknown comparison operator " +
				 * op + " while creating filter from database (id = " + id +
				 * ")");
				 */

				if (!positive.equals("Y")) {
					// This is a negative condition, so encapsulate her into a
					// NOT-Expression
					e = eavt.new NotExpression(e);
				}

				o.addChild(e);
			}
			if (o != null) {
				a.addChild(o);
			}
			return a;
		}

		protected EqualsExpression getEqualsExpression(
				HashMap<String, Object> hm) {
			return null; // new EqualsExpression("x", "1");
		}

	}

	public class SQLQuery {
		private String query;

		public SQLQuery(String query) {
			this.query = query;
		}
	}

	public enum LeftType {
		LITERAL("LITERAL");

		private final String id;

		LeftType(String id) {
			this.id = id;
		}
	}

	public enum RightType {
		CONST("CONST", null), 
		SQL("SQL", SQLQuery.class), 
		SET("SQL", SetString.class);

		private final String id;
		private final Class klass;

		RightType(String id, Class klass) {
			this.id = id;
			this.klass = klass;
		}
	}

	/**
	 * This enumeration names and implements the comparison possibilities
	 * for data given on the left with the right handside. It's string IDs
	 * correspond to the possible values in the CONDITION_TYPE column of the
	 * JT_FILTER_OR table. The implemented {@code compare} methods are passed
	 * that data in the {@link CompExpression} class upon being visited by
	 * an appropriate visitor.
	 *  
     * It is used for comparing for equality of <tt>left</tt> and 
     * <tt>right</tt> (they are equal if the method yields <tt>0</tt>), for
     * comparing order (lesser or greater, by yielding <tt>-1</tt> or 
     * <tt>1</tt> or for a containment of the left handside in the right 
     * argument (<tt>-1</tt> meaning no containment, anything else otherwise).
	 *
	 */
	public enum Comparison implements Comparator<Object> {
		EQ("EQ", "=") {
			public int compare(Object left, Object right) {
				if (left == null || right == null)
					return 1;
				return (left.equals(right) ? 0 : 1);
			}
		},
		GT("GT", ">") {
			public int compare(Object left, Object right) {
				if (left == right || left == null || left.equals(right)) {
					return 1;
				}
				right = numberHelper(left, right);
				return (((Comparable) left).compareTo(right) > 0 ? 0 : 1);
			}
		},
		LT("LT", "<") {
			public int compare(Object left, Object right) {
				if (left == right || left == null || left.equals(right)) {
					return 1;
				}
				right = numberHelper(left, right);
				return (((Comparable) left).compareTo(right) < 0 ? 0 : 1);
			}
		},
		GEQ("GEQ", ">=") {
			public int compare(Object left, Object right) {
				if (left == right || left == null || left.equals(right)) {
					return 1;
				}
				right = numberHelper(left, right);
				return (((Comparable) left).compareTo(right) >= 0 ? 0 : 1);
			}
		},
		LEQ("LEQ", "<=") {
			public int compare(Object left, Object right) {
				if (left == right || left == null || left.equals(right)) {
					return 1;
				}
				right = numberHelper(left, right);
				return (((Comparable) left).compareTo(right) <= 0 ? 0 : 1);
			}
		},		
		CONTAINS("CONTAINS") {
			public int compare(Object left, Object right) {
				if (left instanceof String && right instanceof String) {
					String s = (String) left;
					return (s.contains((String) right) ? 0 : 1);
				}
				return 1;
			}
		},
		STARTSWITH("STARTSWITH") {
			public int compare(Object left, Object right) {
				if (left instanceof String && right instanceof String) {
					String l = (String) left;
					String r = (String) right;
					return (l.startsWith(r) ? 0 : 1);
				}
				return 1;
			}
		},
		ENDSWITH("ENDSWITH") {
			public int compare(Object left, Object right) {
				if (left instanceof String && right instanceof String) {
					String l = (String) left;
					String r = (String) right;
					return (l.endsWith(r) ? 0 : 1);
				}
				return 1;
			}
		},
		NULL("NULL") {
			public int compare(Object left, Object right) {
				return (left == null ? 0 : 1);
			}
		},
		IN("IN") {
			public int compare(Object left, Object right) {
				if (!(right instanceof Collection) || right == null
						|| left == null)
					return 1;
				return ((Collection) right).contains(left) ? 0 : 1;
			}
		},
		;

		private final String id;
		private String alternateId;

		Comparison(String id) {
			this.id = id;
		}

		Comparison(String id, String altId) {
			this.id = id;
			this.alternateId = altId;
		}

		private String getId() {
			return (alternateId != null ? alternateId : id);
		}

		public abstract int compare(Object left, Object right);
		
		private static Object numberHelper(Object left, Object right) {
			if (left instanceof Number && right instanceof String) {
				if (left instanceof BigDecimal) {
					right = BigDecimal.valueOf(Float.parseFloat((String)right));
				} else if (left instanceof BigInteger) {
					right = BigInteger.valueOf(Long.parseLong((String)right));
				} else {
					right = Float.parseFloat((String)right);
				}
			}
			return right;
		}

	}

	/**
	 * Interface for visitors visiting expression trees
	 * 
	 * @author nils
	 * 
	 */
	public interface INodeVisitor {
		IVisitResult visit(CompExpression eqExp); // Temporary

		IVisitResult visit(LessThanExpression eqExp);

		IVisitResult visit(GreaterThanExpression eqExp);

		IVisitResult visit(ContainsExpression eqExp);

		IVisitResult visit(EqualsExpression eqExp);

		IVisitResult visit(IsNullExpression eqExp);

		IVisitResult visit(InExpression inExp);

		IVisitResult visit(NotExpression notExp, IVisitResult child);

		IVisitResult visit(AndExpression andExp,
				ArrayList<IVisitResult> children);

		IVisitResult visit(OrExpression orExp, ArrayList<IVisitResult> children);
	}

	/**
	 * The result of a visit() and accept() calls.
	 * 
	 * @author nils
	 * 
	 */
	public interface IVisitResult {
		abstract boolean doVisitMore();

		abstract void mergeWith(IVisitResult r);
	}

	/**
	 * The basic node element
	 * 
	 * @author nils
	 * 
	 */
	public interface INodeElement {
		IVisitResult accept(INodeVisitor visitor); // elements have to provide
													// accept().
	}

	/**
	 * Interface to "mark" expressions
	 * 
	 * @author nils
	 * 
	 */
	public interface Expression {

	}

	// ... Now all concrete expressions follow ...

	/**
	 * 
	 * @author nils
	 * 
	 */
	class EqualsExpression implements Expression, INodeElement {
		private String left;
		private String right;

		public EqualsExpression(String l, String r) {
			this.left = l;
			this.right = r;
		}

		public String getLeft() {
			return this.left;
		}

		public String getRight() {
			return this.right;
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	class InExpression implements Expression, INodeElement {
		private String left;
		private ArrayList<String> right;

		public InExpression(String l, ArrayList<String> r) {
			this.left = l;
			this.right = r;
		}

		public String getLeft() {
			return this.left;
		}

		public ArrayList<String> getRight() {
			return this.right;
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	class NotExpression implements Expression, INodeElement {
		INodeElement child;

		public NotExpression(INodeElement e) {
			this.child = e;
		}

		public INodeElement getChild() {
			return this.child;
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this, this.child.accept(visitor));
		}

		public String toString() {
			return "NOT(" + this.child.toString() + ")";
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	class IsNullExpression implements INodeElement {
		private String left;

		public IsNullExpression(String l) {
			this.left = l;
		}

		public String getLeft() {
			return this.left;
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	abstract class ComparisonExpression implements INodeElement {
		private String left;
		private Comparable right;

		public ComparisonExpression(String l, Comparable r) {
			this.left = l;
			this.right = r;
		}

		public String getLeft() {
			return this.left;
		}

		public Comparable getRight() {
			return this.right;
		}
	}

	/**
	 * This is the acutal class for comparisons in the form of 
	 * <code>left OP right</code>, where <tt>OP</tt> is an operator
	 * operating on data given on the left (usually provided by 
	 * a checking visitor) to compare it to (a) value(s) on the right. 
	 * @author nils
	 *
	 */
	public/* abstract */class CompExpression implements INodeElement {
		private Object left;
		private Comparison comp;
		private Object right;
		private String sqlRight;

		public CompExpression(Object l, Object r) {
			this.left = l;
			this.right = r;
		}

		public Object getLeft() {
			return this.left;
		}

		public Object getRight() {
			return this.right;
		}

		public void setSQL(String sql) {
			this.sqlRight = sql;
		}

		public void setComp(Comparison c) {
			this.comp = c;
		}

		public String getSQL() {
			return this.sqlRight;
		}

		public Comparison getComp() {
			return this.comp;
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}

		public String toString() {
			return this.left.toString() + " " + this.getComp().getId() + (this.comp.getId() != "NULL" ? " \""
					+ this.right.toString() + "\"" : "");
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	class ContainsExpression extends ComparisonExpression implements
			INodeElement {
		public ContainsExpression(String l, String r) {
			super(l, r);
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	class LessThanExpression extends ComparisonExpression implements
			INodeElement {
		public LessThanExpression(String l, String r) {
			super(l, r);
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	/**
	 * 
	 * @author nils
	 * 
	 */
	class GreaterThanExpression extends ComparisonExpression implements
			INodeElement {
		public GreaterThanExpression(String l, Comparable r) {
			super(l, r);
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this);
		}
	}

	/**
	 * 
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

		public void addChild(INodeElement e) {
			this.children.add(e);
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
			sb.append("(");
			for (INodeElement child : children) {
				if (sb.length() > 1) {
					sb.append(", ");
				}
				sb.append(child.toString());
			}
			sb.append(")");
			return sb.toString();
		}
	}

	class OrExpression extends AExpressionWithChildren implements INodeElement {
		public OrExpression(INodeElement... e) {
			super(e);
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this, getVisitedChildren(visitor));
		}

		public String toString() {
			return "OR" + super.toString();
		}
	}

	class AndExpression extends AExpressionWithChildren implements INodeElement {
		public AndExpression(INodeElement... e) {
			super(e);
		}

		public IVisitResult accept(INodeVisitor visitor) {
			return visitor.visit(this, getVisitedChildren(visitor));
		}

		public String toString() {
			return "AND" + super.toString();
		}
	}

	/**
	 * Visitor to pretty-print the expression tree
	 * 
	 * @author nils
	 * 
	 */
	public class ExpressionPrintVisitor implements INodeVisitor {

		/* Temp */
		public IVisitResult visit(CompExpression inExp) {
			return new PrintVisitResult("[" + inExp.getLeft() + " "
					+ inExp.getComp().getId() + "-COMP " + inExp.getRight()
					+ "]");
		}

		public IVisitResult visit(InExpression inExp) {
			return new PrintVisitResult("(" + inExp.getLeft() + " IN ("
					+ StringUtils.join(inExp.getRight(), ", ") + "))");
		}

		public IVisitResult visit(IsNullExpression inExp) {
			return new PrintVisitResult("(" + inExp.getLeft() + " IS NULL)");
		}

		@Override
		public IVisitResult visit(LessThanExpression eqExp) {
			return new PrintVisitResult("(" + eqExp.getLeft() + " < "
					+ eqExp.getRight() + ")");
		}

		@Override
		public IVisitResult visit(GreaterThanExpression eqExp) {
			return new PrintVisitResult("(" + eqExp.getLeft() + " > "
					+ eqExp.getRight() + ")");
		}

		@Override
		public IVisitResult visit(ContainsExpression eqExp) {
			return new PrintVisitResult("(" + eqExp.getLeft() + " CONTAINS "
					+ eqExp.getRight() + ")");
		}

		public IVisitResult visit(EqualsExpression eq) {
			return new PrintVisitResult("(" + eq.getLeft() + " = "
					+ eq.getRight() + ")");
		}

		public IVisitResult visit(NotExpression ne, IVisitResult child) {
			String s = ((PrintVisitResult) child).getText();
			return new PrintVisitResult("!" + s);
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
			if (sb.length() > 0) {
				sb.insert(0, "(");
				sb.append(")");
			}
			return new PrintVisitResult(sb.toString());
		}

		public IVisitResult visit(OrExpression orExp,
				ArrayList<IVisitResult> children) {
			return visitHelper(orExp, children, " || ");
		}

		public IVisitResult visit(AndExpression andExp,
				ArrayList<IVisitResult> children) {
			return visitHelper(andExp, children, " && ");
		}
	}

	/**
	 * String the expression tree
	 * 
	 * @author nils
	 */
	public class PrintVisitResult implements IVisitResult {
		public String text;

		public PrintVisitResult(String t) {
			this.text = t;
		}

		@Override
		public boolean doVisitMore() {
			return true;
		}

		public String getText() {
			return text;
		}

		@Override
		public void mergeWith(IVisitResult r) {
			if (r != null) {
				this.text += ((PrintVisitResult) r).getText();
			}
		}
	}

	/**
	 * Visitor to validate the expression tree against a set of data
	 * 
	 * @author nils
	 * 
	 */
	public class ExpressionValidatorVisitor implements INodeVisitor {

		private HashMap<String, Object> data;
		private boolean debug = false;

		public void setDebug(boolean d) {
			this.debug = d;
		}

		public ExpressionValidatorVisitor(HashMap<String, Object> data) {
			this.data = data;
		}

		public Object getLeft(Object key) {
			return data.get(((String)key).toUpperCase());
		}

		@Override
		public IVisitResult visit(CompExpression ltExp) {
			Object l = getLeft(ltExp.getLeft());
			Object r = ltExp.getRight();
			boolean answer = (ltExp.getComp().compare(l, r) == 0);
			/*System.out.println("COMP-SQL: " + ltExp.getSQL());
			System.out.println("COM-IDP: " + ltExp.getComp().getId());
			System.out.println("COMP-RESULT: " + ltExp.getComp().compare(l, r));
			System.out
					.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
*/
			/*
			 * if (o != null && o.toString().compareTo((String)right) < 0) {
			 * return new ValidateVisitResult(true); }
			 */

			if (debug) {
				System.out.println(ltExp.toString() + " |= "+answer+ " (is "+l+")");
			}
			return new ValidateVisitResult(answer);
		}

		@Override
		public IVisitResult visit(LessThanExpression ltExp) {
			Comparable o = (Comparable) getLeft(ltExp.getLeft());
			Comparable right = ltExp.getRight();
			if (right instanceof Number) {
				right = String.valueOf(right);
			}

			if (o != null && o.toString().compareTo((String) right) < 0) {
				return new ValidateVisitResult(true);
			}
			return new ValidateVisitResult(false);
		}

		@Override
		public IVisitResult visit(GreaterThanExpression gtExp) {
			Comparable o = (Comparable) getLeft(gtExp.getLeft());
			Comparable right = gtExp.getRight();
			if (right instanceof Number) {
				right = String.valueOf(right);
			}

			if (o != null && o.toString().compareTo((String) right) > 0) {
				return new ValidateVisitResult(true);
			}
			return new ValidateVisitResult(false);
		}

		public ValidateVisitResult visit(ContainsExpression cExp) {
			Object o = getLeft(cExp.getLeft());
			if (o != null && o.toString().contains((String) cExp.getRight())) {
				return new ValidateVisitResult(true);
			}
			return new ValidateVisitResult(false);
		}

		public ValidateVisitResult visit(IsNullExpression inExp) {
			Object o = getLeft(inExp.getLeft());
			if (o == null) {
				return new ValidateVisitResult(true);
			}
			return new ValidateVisitResult(false);
		}

		public ValidateVisitResult visit(EqualsExpression eqExp) {
			Object o = getLeft(eqExp.getLeft());
			if (o != null && o.equals(eqExp.getRight())) {
				return new ValidateVisitResult(true);
			}
			return new ValidateVisitResult(false);
		}

		public ValidateVisitResult visit(InExpression inExp) {
			Object o = getLeft(inExp.getLeft());
			for (String s : inExp.getRight()) {
				if (o != null && o.equals(s)) {
					return new ValidateVisitResult(true);
				}
			}
			return new ValidateVisitResult(false);
		}

		public IVisitResult visit(NotExpression ne, IVisitResult child) {
			boolean answer = !((ValidateVisitResult) child).getValidatedTo();
			if (debug) {
				System.out.println(ne.toString() + " |= " + answer);
			}
			return new ValidateVisitResult(answer);
		}

		public IVisitResult visit(AndExpression andExp,
				ArrayList<IVisitResult> children) {
			for (IVisitResult child : children) {
				if (child != null) {
					boolean b = ((ValidateVisitResult) child).getValidatedTo();
					if (!b) {
						if (debug) {
							System.out.println(andExp.toString() + " |= false");
						}
						return new ValidateVisitResult(false);
					}
				}
			}
			if (debug) {
				System.out.println(andExp.toString() + " |= true");
			}
			return new ValidateVisitResult(true);
		}

		public IVisitResult visit(OrExpression orExp,
				ArrayList<IVisitResult> children) {
			for (IVisitResult child : children) {
				if (child != null) {
					boolean b = ((ValidateVisitResult) child).getValidatedTo();
					if (b) {
						if (debug) {
							System.out.println(orExp.toString() + " |= true");
						}
						return new ValidateVisitResult(true);
					}
				}
			}
			if (debug) {
				System.out.println(orExp.toString() + " |= false");
			}
			return new ValidateVisitResult(false);
		}

		public boolean evaluate(INodeElement filter) {
			// TODO Auto-generated method stub
			ValidateVisitResult res = (ValidateVisitResult) filter.accept(this);
			return res.getValidatedTo();
		}
	}

	/**
	 * String the expression tree
	 * 
	 * @author nils
	 */
	public class ValidateVisitResult implements IVisitResult {
		public boolean validatedTo;

		public ValidateVisitResult(boolean b) {
			this.validatedTo = b;
		}

		@Override
		public boolean doVisitMore() {
			return true;
		}

		public boolean getValidatedTo() {
			return validatedTo;
		}

		@Override
		public void mergeWith(IVisitResult r) {
		}
	}

	/* @Test */
	public void visitorTest() {

		INodeElement filter = new OrExpression(new EqualsExpression("a", "1"),
				new EqualsExpression("b", "2"), new AndExpression(
						new EqualsExpression("c", "3"), new EqualsExpression(
								"d", "4")));

		PrintVisitResult r = (PrintVisitResult) filter
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out.println("-------------------------");

	}

	@Test
	public void expressionFromDBTest() throws SQLException, myException {
		INodeElement filter = FilterFactory.fromDB(1);
		PrintVisitResult r = (PrintVisitResult) filter
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		ArrayList<HashMap<String, Object>> dataset = DBUtil.returnEntriesRaw(
				null, 0);
		for (HashMap<String, Object> d : dataset) {
			System.out.println(d);
			//d.put("LAENDERCODE", null);
			ExpressionValidatorVisitor e = new ExpressionValidatorVisitor(d);
			e.setDebug(true);
			System.out.println(e.evaluate(filter));

			return;
		}
	}

	/* @Test */
	public void visitorTest2() throws SQLException, myException {
		ArrayList<String> l = new ArrayList<String>();
		l.add("DE");
		l.add("EN");
		l.add("D");

		BigDecimal x = BigDecimal.valueOf(1010921);
		System.out.println("BIGDEC=" + x.toString());
		// System.out.println("STR="+("1".compareTo("1")));

		INodeElement filter = new AndExpression(new OrExpression(
				new EqualsExpression("DIENSTLEISTUNG", "Y"), new InExpression(
						"LAENDERCODE", l)), new GreaterThanExpression(
				"EINZELPREIS_RESULT", "100"));

		INodeElement filter2 = new AndExpression(new EqualsExpression(
				"DIENSTLEISTUNG", "N"), new OrExpression(new NotExpression(
				new EqualsExpression("LAENDERCODE", "DE")),
				new EqualsExpression("LAENDERCODE", "D")));

		PrintVisitResult r = (PrintVisitResult) filter2
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out.println("================================");

		ArrayList<HashMap<String, Object>> dataset = DBUtil.returnEntriesRaw(
				null, 0);
		for (HashMap<String, Object> d : dataset) {
			System.out.println(d);
			ExpressionValidatorVisitor e = new ExpressionValidatorVisitor(d);
			e.setDebug(true);
			System.out.println(e.evaluate(filter));

			return;
		}

	}
}
