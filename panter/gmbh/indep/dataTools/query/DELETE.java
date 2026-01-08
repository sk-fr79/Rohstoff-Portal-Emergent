package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.UnaryExpression;
import panter.gmbh.indep.filter._FilterConstants.OP;

/**
 * Class used to formulate DELETE SQL statements.
 *
 * Each public method in this class returns {@code this}, allowing 
 * it for chaining methods in the JQuery way. 
 * @author nils
 *
 */
public class DELETE extends Query implements WritingQuery {

	/** Copy constructors */
	public DELETE(DELETE copy) {
		super(copy);
	}
	public DELETE(SELECT copy) {
		super(copy);
	}
	
	/**
	 * Specifies the table to delete rows from.
	 * @param table the table to delete rows from
	 */
	public DELETE(Object... table) {
		addSQLParts(table, tables, Clause.DELETE, true);
		context = whereClause;
	}

	/**
	 * Specifies the table to delete rows from.
	 * @param table the table to delete rows from
	 */
	public DELETE from(Object... table) {
		if (table.length != 1) {
			throw new RuntimeException("Must specify exactly one table to delete from.");
		}
		addSQLParts(table, tables, Clause.DELETE, true);
		return this;
	}
	
	/** Constructor alias */
	public DELETE delete(Object ... table) {
		return from(table);
	}

	/** 
	 * Specifies the WHERE clause for this delete statement, 
	 * or, which conditions have to hold for the rows to be deleted.
	 * Syntax and semantics are analogeous for the WHERE calls
	 * in the {@link SELECT} class (see 
	 * {@link SELECT#where(Object leftColumn, String op, Object rightValue)}.
	 */
	public DELETE where(Object leftColumn, String op, Object rightValue) {
		return and(leftColumn, op, rightValue);
	}
	
	/** Shorthand for equivalence-where. See {@link #where(Object, String, Object)} */
	public DELETE where(Object leftColumn, Object rightValue) {
		return and(leftColumn, "=", rightValue);
	}
	
	/**
	 * Alias function perform {@code where().exists(...)}.
	 * See {@link SELECT#where()}.
	 */
	public DELETE where() {
		return this;
	}
	
	/**
	 * EXISTS subquery. See {@link SELECT#exists}.
	 */
	public DELETE exists(Query q) {
		INodeElement c = new UnaryExpression("EXISTS", q);
		addConditional(c, OP.AND, context);
		return this;
	}
	
	/** 
	 * Conditional AND for the {@link #where} clause. Analogous
	 * behaviour like in {@link SELECT#and(Object leftColumn, String op, Object rightValue)}
	 */
	public DELETE and(Object leftColumn, String op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.AND, context);
		return this;
	}

	/**
	 * Equivalence AND (see {@link SELECT#and(Object leftColumn, Object rightValue)})
	 */
	public DELETE and(Object leftColumn, Object rightValue) {
		return and(leftColumn, "=", rightValue);
	}

	/** 
	 * Conditional OR for the {@link #where} clause. Analogous
	 * behaviour like in {@link SELECT#or(Object leftColumn, String op, Object rightValue)}
	 */
	public DELETE or(Object leftColumn, String op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.OR, context);
		return this;
	}

	/**
	 * Equivalence OR (see {@link SELECT#or(Object leftColumn, Object rightValue)})
	 */
	public DELETE or(Object l, Object r) {
		return or(l, "=", r);
	}
	
	/** 
	 * Conditional NOT for the {@link #where} clause. Negates the whole WHERE
	 * clause. Analogous behaviour like in 
	 * {@link SELECT#and(Object leftColumn, String op, Object rightValue)}
	 */

	public DELETE not(Object leftColumn, String op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.AND, context);
		negatedWhere = true;
		return this;
	}
	public DELETE not(Object leftColumn, String rightValue) {
		return not(leftColumn, "=", rightValue);
	}
	public DELETE not() {
		negatedWhere = true;
		return this;
	}
	
	public String toString(ToStringMode mode) {
		if (tables.size() != 1) {
			throw new RuntimeException("Must specify exactly one table to delete from, either as an argument to"
					+ "the constructor or as argument in the .from(String table) method call"
					+ " (here "+tables.size()+" are given)");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM ");
		sb.append(printArrayList(tables, mode));
		sb.append("\n");
		appendWherePart(sb, mode);
		sb.append("\n");
		return sb.toString(); 
	}
}