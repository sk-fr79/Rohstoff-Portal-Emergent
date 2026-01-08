package panter.gmbh.indep.dataTools.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.UnaryExpression;
import panter.gmbh.indep.filter._FilterConstants.COMP;
import panter.gmbh.indep.filter._FilterConstants.OP;

/**
 * Class used to formulate UPDATE SQL statements.
 *
 * Each public method in this class returns {@code this}, allowing 
 * it for chaining methods in the JQuery way. 
 * @author nils
 *
 */
public class UPDATE extends Query implements UpdatingQuery {
	protected Map<ID, Object> updates = new HashMap<ID, Object>();
	protected InsertType type;

	/** Copy constructor */
	public UPDATE(UPDATE copy) {
		super(copy);
		this.updates = new HashMap<ID, Object>(copy.updates);
		this.type = copy.type;
	}

	/**
	 * Constructs the statement by specifying which tables
	 * are to be updated.
	 * @param table The table(s) to be updated to
	 */
	public UPDATE(Object... table) {
		update(table);
	}
	
	/**
	 * Alias for the parameters in the constructor. Specifies
	 * the tables for update.
	 * @param table The table(s) to be updated to.
	 * @return the UPDATE object itself
	 */
	public UPDATE update(Object ... table) {
		addSQLParts(table, tables, Clause.UPDATE, true);
		context = whereClause;
		return this;
	}

	/**
	 * Sets {@param column} to the String value {@param value}.
	 * Corresponds to the SET clause in SQL.
	 */
	public UPDATE set(String column, String value) {
		updates.put(new ID(column), new V(value));
		return this;
	}
	
	/**
	 * Sets {@param column} to the Object value {@param value},
	 * hence treating the value object not as something to be
	 * quoted. Corresponds to the SET clause in SQL.
	 */
	public UPDATE set(Object column, Object value) {
		updates.put(new ID(column.toString()), Query.getValue(value));
		return this;
	}
	
	/**
	 * Bulk-sets a Map of String:Object pairs as the SET clause,
	 * iterating over each key and and setting it as if manually
	 * calling {@link #set(String column, Object value)}.  
	 */
	public UPDATE set(Map<String, ?> hm) {
		Iterator<String> it = hm.keySet().iterator();
		while (it.hasNext()) {
			String column = it.next();
			Object val = hm.get(column);
			set(column, val);
		}
		return this;
	}
	
	/** 
	 * Specifies the WHERE clause for this update statement, 
	 * or, which conditions have to hold for the rows to be updated.
	 * Syntax and semantics are analogeous for the WHERE calls
	 * in the {@link SELECT} class (see 
	 * {@link SELECT#where(Object leftColumn, Object op, Object rightValue)}.
	 */
	public UPDATE where(Object leftColumn, Object op, Object rightValue) {
		return and(leftColumn, op, rightValue);
	}
	
	/** Shorthand for equivalence-where. See {@link #where(Object, Object, Object)} */
	public UPDATE where(Object leftColumn, Object rightValue) {
		return and(leftColumn, COMP.EQ, rightValue);
	}
	
	/**
	 * Alias function perform {@code where().exists(...)}.
	 * See {@link SELECT#where()}.
	 */
	public UPDATE where() {
		return this;
	}
	
	/**
	 * EXISTS subquery. See {@link SELECT#exists}.
	 */
	public UPDATE exists(SELECT select) {
		INodeElement c = new UnaryExpression("EXISTS", select);
		addConditional(c, OP.AND, context);
		return this;
	}
	
	/** 
	 * Conditional AND for the {@link #where} clause. Analogous
	 * behaviour like in {@link SELECT#and(Object leftColumn, Object op, Object rightValue)}
	 */
	public UPDATE and(Object leftColumn, Object op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.AND, context);
		return this;
	}
	
	/**
	 * Equivalence AND (see {@link SELECT#and(Object leftColumn, Object rightValue)})
	 */
	public UPDATE and(Object leftColumn, Object rightValue) {
		return and(leftColumn, COMP.EQ, rightValue);
	}

	/** 
	 * Conditional OR for the {@link #where} clause. Analogous
	 * behaviour like in {@link SELECT#or(Object leftColumn, Object op, Object rightValue)}
	 */
	public UPDATE or(Object leftColumn, Object op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.OR, context);
		return this;
	}

	/**
	 * Equivalence OR (see {@link SELECT#or(Object leftColumn, Object rightValue)})
	 */
	public UPDATE or(Object leftColumn, Object rightValue) {
		return or(leftColumn, COMP.EQ, rightValue);
	}
	
	/** 
	 * Conditional NOT for the {@link #where} clause. Negates the whole WHERE
	 * clause. Analogous behaviour like in 
	 * {@link SELECT#and(Object leftColumn, Object op, Object rightValue)}
	 */

	public UPDATE not(Object leftColumn, Object op, Object rightValue) {
		INodeElement c = comparisonFromInput(leftColumn, op, rightValue);
		addConditional(c, OP.AND, context);
		negatedWhere = true;
		return this;
	}
	public UPDATE not(Object leftColumn, Object rightValue) {
		return not(leftColumn, COMP.EQ, rightValue);
	}
	public UPDATE not() {
		negatedWhere = true;
		return this;
	}
	
	/**
	 * Yields this statement's SQL string
	 */
	@Override
	public String toString(ToStringMode mode) {
		if (tables == null || tables.size() != 1) {
			throw new RuntimeException("Must specify exactly one table for update (here, "+tables.size()+" are given)");
		}
		if (updates == null || updates.size() == 0) {
			throw new RuntimeException("Must specify at least one colum with a SET clause for update");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(printArrayList(tables, mode, true));
		sb.append("\n");
		sb.append(" SET \n");
		// Here are the key = value pairs
		Iterator<ID> it = updates.keySet().iterator();
		while (it.hasNext()) {
			ID key = it.next(); 
			sb.append("  ");
			sb.append(key.toString());
			sb.append(" = ");
			Object value = updates.get(key);
			if (value instanceof Query) {
				//Qubqueries in brackets
				sb.append("(");
			}
			if (value instanceof SQLPart) {
				sb.append(((SQLPart)value).toString(mode));
			} else {
				sb.append(value.toString());
			}
			if (value instanceof Query) {
				//Qubqueries in brackets
				sb.append(")");
			}
			if (it.hasNext()) {
				sb.append(", ");
				sb.append("\n");
			}
		}
		sb.append("\n");
		appendWherePart(sb, mode);
		sb.append("\n");
		return sb.toString(); 
	}
}