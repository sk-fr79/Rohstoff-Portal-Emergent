package panter.gmbh.indep.dataTools.query;

import java.util.ArrayList;
import java.util.List;

import panter.gmbh.indep.filter.AndExpression;
import panter.gmbh.indep.filter.ExpressionPrintSQLVisitor;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.PrintVisitResult;
import panter.gmbh.indep.filter.UnaryExpression;
import panter.gmbh.indep.filter._FilterConstants.COMP;
import panter.gmbh.indep.filter._FilterConstants.OP;

/**
 * A SELECT statement as chained method-call Java object. 
 * @author nils
 *
 */
public class SELECT extends Query {
	/** Copy-Constructor */
	public SELECT(SELECT copy) {
		super(copy);
	}
	
	/** 
	 * The SELECT constructor takes >= 1 parameters which represent the
	 * columns to be selected and which may be aliased. Each
	 * parameter represents one column (or other values). The semantics for
	 * String parameters in a constructor like  
	 *   {@code new SELECT("t1.col1", "t1.col2", "t2.col_a")}
	 * means that {@code col1} and {@code col2} are being selected from 
	 * table {@code t1}, and {@code col_a} from table {@code t2} (where
	 * the two tables must be provided in the {@link #from} clause as aliased 
	 * tables.
	 * You can write {@code new SELECT("*")} or {@code new SELECT("t.*")}.
	 * <p>
	 * Note that the columns selected are treated as field/column identifiers,
	 * which means they are internally converted to {@link ID} objects, meaning
	 * they are candidates for normalizing and quoting. If you want to 
	 * specify some arbitrary expression, condsider wrapping it into an 
	 * {@link U} expresion, like {@code new U("expression")}, or use the
	 * {@link Term} factory class, like {@link Term#field(String)}. 
	 * 
	 * @param the columns/values to be selected. Not that if strings are provided,
	 * they will be interpreted as column identifiers ({@link ID}s).
	 * @return the SELECT statement itself
	 */
	public SELECT(Object... column) {
		select(column);
	}
	/** Alias for the constructor. */
	public SELECT select(Object... column) {
		ensureFieldsNotNull();
		addSQLParts(column, fields, Clause.SELECT, true);
		return this;
	}
	
	/** Adds DISTINCT modifier and behaves analogously to {@link #SELECT} */
	public SELECT distinct(Object... column) {
		addModifier(Modifier.DISTINCT);
		return select(column);
	}
	
	/** Adds UNIQUE modifier and behaves analogously to {@link #SELECT} */
	public SELECT unique(Object... column) {
		addModifier(Modifier.UNIQUE);
		return select(column);
	}
	
	/** Adds ALL modifier and behaves analogously to {@link #SELECT} */
	public SELECT all(Object... column) {
		ensureFieldsNotNull();
		addModifier(Modifier.ALL);
		addSQLParts(column, fields, Clause.SELECT, true);
		return this;
	}
	
	/** Static getter to obtain an instance of the class. */
	public static SELECT instance(Object... o) {
		if (o.length == 0) {
			o = new Object[]{"*"};
		}
		return new SELECT(o);
	}
	
	/** 
	 * Adds a FROM clause to the query. The FROM clause consists of a
	 * list of String parameters, which represent tables and their 
	 * aliases. Suppose {@code s} to be a {@link #SELECT} object, then
	 * {@code s.from("table t1", "table t2")} defines the tables 
	 * {@code t1} and {@code t2} as aliases of the tables {@code table1}
	 * and {@code table2} to be selected from (resulting in a CROSS join
	 * in old syntax: {@code SELECT * FROM table t1, table t2}). Usually,
	 * and for the sake of readability, one should use only one argument
	 * and do any other joins with the {@link #join}, {@link leftJoin}, or
	 * {@link #crossJoin} to do cross Joins and the like.
	 * @param table the tables specified in the FROM clause. Note that 
	 * string arguments taken here are converted to table identifiers, 
	 * represented as {@link TID} objects.
	 * @return the SELECT itself
 
	 */
	public SELECT from(Object ... table) {
		addSQLParts(table, tables, Clause.FROM, false);
		return this;
	}

	/**
	 * Shorthand notation for {@code .from(table+" "+correlationName)} 
	 * @param table the table to be selected from
	 * @param correlationName the table's correlation name
	 */
	public SELECT from(String table, String correlationName) {
		tables.add(new TID(correlationName, table));
		return this;
	}

	/**
	 * Notation for a correllated subselect in the FROM clause.
	 * The provided {@param select} is the subselect query, the
	 * {@param alias} it's alias 
	 */
	public SELECT from(SELECT select, String alias) {
		select.setAlias(alias);
		tables.add(select);
		return this;
	}

	/** Allows to provide an {@param alias} for this SELECT statement */
	public SELECT as(String alias) {
		super.setAlias(alias);
		return this;
	}
	
	/**
	 * Adds a JOIN condition to this query.
	 * 
	 * @param table the table to be joined
	 * @param correlationName the correlation name of the table 
	 * @param type
	 * @return
	 */
	protected SELECT abstractJoin(Object table, String correlationName, Join type) {
		checkAndFinishJoin();
		JoinCondition jc = new JoinCondition();
		if (table instanceof String) {
			jc.setTable((String)table);
		}
		if (correlationName != null) {
			jc.setCorrelationName(correlationName);
		}
		if (table instanceof TID) {
			jc.setTable(((TID)table).identifier);
			jc.setCorrelationName(((TID)table).alias);
		}
		jc.setType(type);
		context = jc;
		return this;
	}
	
	/**
	 * (INNER) JOIN clause. The conditions of the join may
	 * be provided by an {@link #on} clause afterwards.
	 * Please not that the arguments are converted to 
	 * {@link JoinCondition}s.
	 * @param table The table to be joined. 
	 */
	public SELECT join(Object table) {
		return abstractJoin(table, null, Join.INNER);
	}
	/**
	 * (INNER) JOIN on {@param table} with alias. See 
	 * {@link #join(String)} for details.
	 */
	public SELECT join(String table, String correlationName) {
		return abstractJoin(table, correlationName, Join.INNER);
	}

	/** 
	 * Adds ON-clause for a {@link #join}, {@link #leftJoin}, {@link #rightJoin}
	 * and {@link #fullJoin} clause call. 
	 * <p>
	 * Behaves analogously to the {@link #where} method by adding conditions 
	 * which the corresponding join has to meet, with the condition that
	 * <b>left and right</b> handsides of the join conditions are treated
	 * as table identifiers, if strings are provided.
	 * <p>
	 * That implies that if one wants the right handside to be treated as values, 
	 * one must implicitly wrap them in the value class {@link V}. 
	 * <p>
	 * Suppose <code>s</code> to  be a {@link SELECT}, then
	 * <code>
	 *		s.join("join_table jt").on("table.id", "=", "jt.id")
	 * </code>
	 * works on the identifiers of {@code table} and {@code join_table},  
	 * resulting in some SQL like {@code JOIN ... ON (table.id = jt.id)}, where 
	 * <code>
	 * 		s.join("join_table jt").on("table.name", "=", new V("aValue"))
	 * </code>
	 * takes the right handside as a value, and properly quotes it in SQL
	 * <code>
	 * 		JOIN ... ON (table.name = "aValue")
	 * </code>
	 * @param leftColumn the left handside of the join, treated as column identifier
	 * {@link ID}, if a string is given.
	 * @param op the "theta" join condition, usually an equals "=" sign
	 * @param rightColumn the right handside of the join, treated as column identifier
	 * {@link ID}, if a string is given.
	 * @return the SELECT
	 */
	public SELECT on(Object leftColumn, String op, Object rightColumn) {
		if (!(context instanceof JoinCondition)) {
			throw new IllegalStateException("Attempt to add an ON clause without prior JOIN clause.");
		}
		// context has already been set on the abstractJoin method
		return and(leftColumn, op, rightColumn);
	}
	
	/**
	 * Adds an equi-join condition, analogously to {@link #on(Object, String, Object)}
	 * @param left The left handside of the join, treated as column identifier
	 * {@link ID}, if a string is given.
	 * @param left The right handside of the join, treated as column identifier
	 * {@link ID}, if a string is given.
	 * @return the SELECT
	 */
	public SELECT on(Object leftColumn, Object rightColumn) {
		if (!(context instanceof JoinCondition)) {
			throw new IllegalStateException("Attempt to add an ON clause without prior JOIN clause.");
		}
		// context has already been set on the abstractJoin method
		return and(leftColumn, "=", rightColumn);
	}

	/** LEFT JOIN on {@param table} */
	public SELECT leftJoin(Object table) {
		return abstractJoin(table, null, Join.LEFT);
	}

	/** LEFT JOIN on {@param table} with it's correlated name {@param correlationName} */
	public SELECT leftJoin(String table, String correlationName) {
		return abstractJoin(table, correlationName, Join.LEFT);
	}

	/** RIGHT JOIN on {@param table} */
	public SELECT rightJoin(Object table) {
		return abstractJoin(table, null, Join.RIGHT);
	}

	/** RIGHT JOIN on {@param table} with it's correlated name {@param correlationName} */
	public SELECT rightJoin(String table, String correlationName) {
		return abstractJoin(table, correlationName, Join.RIGHT);
	}

	/** FULL JOIN on {@param table} */
	public SELECT fullJoin(Object table) {
		return abstractJoin(table, null, Join.FULL);
	}
	
	/** FULL JOIN on {@param table} with it's correlated name {@param correlationName} */
	public SELECT fullJoin(String table, String correlationName) {
		return abstractJoin(table, correlationName, Join.FULL);
	}

	/** CROSS JOIN on {@param table} */
	public SELECT crossJoin(Object table) {
		return abstractJoin(table, null, Join.CROSS);
	}
	
	/** CROSS JOINon {@param table} with it's correlated name {@param correlationName} */
	public SELECT crossJoin(String table, String correlationName) {
		return abstractJoin(table, correlationName, Join.CROSS);
	}	
	/** 
	 * Checks if the current context is still a JOIN condition context that has not
	 * been collected yet. This is called before every new JOIN condition
	 * is added and before the WHERE conditions.
	 */
	private void checkAndFinishJoin() {
		if (context instanceof JoinCondition) {
			addJoinCondition((JoinCondition)context);
		}
	}
	
	/** Adds the {@link JoinCondition} to the current statement */
	protected void addJoinCondition(JoinCondition jc) {
		if (joins == null) {
			 joins = new ArrayList<JoinCondition>();
		}
		joins.add((JoinCondition)context);
	}
	
	/** 
	 * Adds the WHERE clause for the current statement. 
	 * <p>
	 * A WHERE clause is a chained set of conditions which are constructed 
	 * syntactically by calling {@code where("left", OP, "right")}, where 
	 * {@code left} is the left handside (in the WHERE-context interpreted as 
	 * a column identifier), {@code OP} is a comparison operator 
	 * ("=", "!=", ">" or the like) and {@code right} is the right handside 
	 * (which is in the WHERE-context always expected to be a scalar value, 
	 * and hence wrappedm into a {@link V} value object).
	 * <p> 
	 * The result is internally added to an array of conditions (in SQL 
	 * semantics, they are combined by AND), and which can be refined 
	 * by further providing {@link #and} and {@link #or} calls to a conjugated
	 * normal form of conditions.
	 * <p>
	 * For every call to {@link #and} and {@link or} after the {@link #where} 
	 * call, the right handsides are treated as values by default. Suppose
	 * <code>s</code> to be a {@link SELECT}, then
	 *    s.where("col1", ">", "1").and("col2", "<", "100")
	 * properly quotes the right handsides by default. If one wants to use
	 * an identifier on the right handside, one must explicitly wrap it into
	 * an {@link ID} object, as follows: 
	 *    s.where("col1", "=", new ID("col2")).and("col3", ">", new ID("col4"))
	 * resulting in some SQL like
	 *    {@code SELECT ... WHERE (col1 = col2 AND col3 > col4)} 
	 * @param leftColumn the left handside, assumed to be <b>a column identifier</b> 
	 * (a column or tablealias.column, wrapped into a {@link ID} object)
	 * @param op the operator as String, e.g. {@code "="}, {@code "<"} or {@code "IN"}.
	 * @param rightValue the right handside of the comparison, deemed <b>a value</b> 
	 * and therefore wrapped into a {@link V} object, unlike in the join 
	 * context.
	 */
	public SELECT where(Object leftColumn, Object op, Object rightValue) {
		checkAndFinishJoin(); // A context may still point to a previous join 
		context = whereClause;
		return and(leftColumn, op, rightValue);
	}
	
	/** Shorthand notation for {@code where(left, "=", right)}, meaning 
	/* equality comparison on {@param left} and {@param right}. */
	public SELECT where(Object leftColumn, Object rightValue) {
		checkAndFinishJoin(); // A context may still point to a previous join 
		context = whereClause;
		return and(leftColumn, "=", rightValue);
	}
	
	/** Alias function to provide a syntactically nice way of something
	 * like {@code select("a").from("table").where().exists(...)}.  
	 */
	public SELECT where() {
		checkAndFinishJoin();
		context = whereClause;
		return this;
	}
	
	/** 
	 * Used to provide a subselect in an EXISTS clause.
	 * @param select the subselect
	 */
	public SELECT exists(SELECT select) {
		checkAndFinishJoin(); // A context may still point to a previous join 
		context = whereClause;
		INodeElement c = new UnaryExpression("EXISTS", select);
		addConditional(c, OP.AND, context);
		return this;
	}

	/** 
	 * Adds a condition to a WHERE/ON/HAVING clause which is added with an AND
	 * conjugation.  
	 * @param left the left handside, assumed to be <b>a column identifier</b> 
	 * (a column or tablealias.column, wrapped into a {@link ID} object)
	 * @param op the operator, e.g. {@code "="}, {@code "<"} or {@code "IN"}.
	 * @param right the right handside of the comparison, deemed <b>a value 
	 * in a WHERE or HAVING clause context</b> (to be wrapped into a {@link V} 
	 * object, <b>or a column identifier in a JOIN context</b>, to be wrapped
	 * into an {@link ID} object.
	 * @param left the left handside of the condition, always deemed an identifier
	 * @param op the comparison operation
	 * @param right the right handside of the condition, deemed an identifier
	 * or a value depending on the clause context.
	 */
	public SELECT and(Object left, Object op, Object right) {
		INodeElement c = comparisonFromInput(left, op, right);
		addConditional(c, OP.AND, context);
		return this;
	}

	/** Shorthand for equality-comparison AND {@link #and(String left, String op, Object right)} */
	public SELECT and(Object left, Object right) {
		return and(left, COMP.EQ, right);
	}

	/** 
	 * Adds a condition to the WHERE clause which is added with an OR
	 * conjugation to the last added condition (see {@link #and}). Suppose {@code s}
	 * to be a {@link SELECT}, then
	 * {@code s.where("a", "=", 1).and("b", "=", 2).or("b", "=", 3)}
	 * will result in the following SQL
	 * {@code SELECT ... WHERE (a = 1 AND (b = 2 OR b = 3)).
	 * Adding another AND condition by calling {@link #and} will then result in the
	 * condition to be added to the "outer" AND block, so 
	 * {@code s.where("a", "=", 1).and("b", "=", 2).or("b", "=", 3).and("c", "=", 4)}
	 * will result in 
	 * {@code SELECT ... WHERE (a = 1 AND (b = 2 OR b = 3) AND c = 4).
	 * @param left the left handside, assumed to be <b>an identifier</b> (a column or tablealias.column)
	 * @param op the operator as String, e.g. {@code "="}, {@code "<"} or {@code "IN"}.
	 * @param right the right handside of the condition, deemed an identifier
	 * or a value depending on the clause context.
	 */
	public SELECT or(Object left, Object op, Object right) {
		INodeElement c = comparisonFromInput(left, op, right);
		addConditional(c, OP.OR, context);
		return this;
	}

	/** Shorthand for equality-comparison OR {@link #or(String left, String op, Object right)} */
	public SELECT or(Object left, Object right) {
		return or(left, "=", right);
	}
	
	/** 
	 * Conditional NOT for the {@link #where} clause. Negates the whole WHERE
	 * clause. Analogous behaviour like in 
	 * {@link SELECT#and(String left, Object op, Object right)}
	 */
	public SELECT not(Object left, Object op, Object right) {
		INodeElement c = comparisonFromInput(left, op, right);
		addConditional(c, OP.AND, context);
		negatedWhere = true;
		return this;
	}
	public SELECT not(Object left, Object right) {
		return not(left, "=", right);
	}
	public SELECT not() {
		negatedWhere = true;
		return this;
	}

	/** 
	 * GROUP-BY clause for the statement. The provided {@param expression} are the 
	 * column names (including aliases) on which the {@link #SELECT) is grouped on.
	 * Suppose {@code s} to be a {@link #SELECT}, then 
	 * {@code s.groupBy("t1.column1", "t2.column_b")} would produce something like
	 * {@code ... GROUP BY t1.column1, t2.column_b}.
	 */
	public SELECT groupBy(Object... expression) {
		if (this.groupBy == null) {
			this.groupBy = new ArrayList<SQLPart>();
		}
		addSQLParts(expression, groupBy, Clause.GROUP_BY, true);
		return this;
	}
	
	/** 
	 * Adds a HAVING clause for statements using grouped expressions with {@link #groupBy},
	 * used to further filter the grouped sets based on conditions. These conditions
	 * are constructed in the same way as those provided by {@link #where} calls.
	 * Suppose {@code s} to be a {@link SELECT}, then 
	 * {@code s.groupBy(...).having("a", "=", 1).and("b", "=", "2)}
	 * will subsequently build the SQL HAVING clause, resulting in 
	 * {@code SELECT ... GROUP BY ... HAVING (a = '1' AND b = '2')}.
	 * @param leftColumn the left handside of the condition, deemed a column identifier if a
	 * String is given (to be wrapped into an {@link ID} object
	 * @param op the comparison operation
	 * @param rightValue the right handside, deemed a value to be wrapped into a
	 * {@link V} object. 
	 */
	public SELECT having(Object leftColumn, Object op, Object rightValue) {
		if (this.groupBy == null || this.groupBy.size() == 0) {
			throw new IllegalStateException("Attempt to add a HAVING clause without prior GROUP-BY clause.");
		}
		checkAndFinishJoin(); // A context may still point to a previous join 
		context = havingClause;
		return and(leftColumn, op, rightValue);
	}
	
	/** Shorthand for equality-comparison for the HAVING clause {@link #having(String left, String op, Object right)} */
	public SELECT having(Object leftColumn, Object rightValue) {
		checkAndFinishJoin(); // A context may still point to a previous join 
		context = havingClause;
		return and(leftColumn, COMP.EQ, rightValue);
	}
	
	/** Helper method to build chained queries with {@link #union}, {@link #unionAll}, 
	 * {@link #intersect} and {@link minus} */
	protected SELECT chain(Query unionQuery) {
		if (chainedQueries == null) {
			chainedQueries = new ArrayList<Query>();
		}
		chainedQueries.add(unionQuery);
		return this;
	}
	
	/** 
	 * Chained UNION (concatenates this query with the provided {@param select}
	 * @param select the SELECT statement to be unioned with this statement
	 */
	public SELECT union(SELECT select) {
		select.innerChainedKeyword = "UNION";
		return chain(select);
	}

	/** 
	 * Chained UNION ALL (concatenates this query with the provided {@param select}
	 * @param select the SELECT statement to be all-unioned with this statement
	 */
	public SELECT unionAll(SELECT select) {
		select.innerChainedKeyword = "UNION ALL";
		return chain(select);
	}

	/** 
	 * Chained INTERSECT (intersects this query with the provided {@param select}
	 * @param select the SELECT statement to be intersected with this statement
	 */
	public SELECT intersect(SELECT select) {
		select.innerChainedKeyword = "INTERSECT";
		return chain(select);
	}

	/** 
	 * Chained MINUS (subtracts this query with the provided {@param select}
	 * @param select the SELECT statement to be subtracted from this statement
	 */
	public SELECT minus(SELECT select) {
		select.innerChainedKeyword = "MINUS";
		return chain(select);
	}
	

	/** 
	 * ORDER-BY clause for this statement. The provided {@param expression} are the 
	 * column names (including aliases) on which the {@link #SELECT) is to be ordered
	 * on. The last call to {@code orderBy} can be modified by calling {@link #asc} or
	 * {@link #desc} to set ascending or descending order. 
	 * 
	 * Suppose {@code s} to be a {@link #SELECT}, then 
	 * {@code s.orderBy("t1.column1").asc().orderBy("t2.column_b").desc()} 
	 * would produce something like
	 * {@code ... ORDER BY t1.column1 ASC, t2.column_b DESC}.
	 */
	public SELECT orderBy(Object... expression) {
		if (this.orderBy == null) {
			this.orderBy = new ArrayList<SQLPart>();
		}
		addSQLParts(expression, (ArrayList<SQLPart>)orderBy, Clause.ORDER_BY, true);
		return this;
	}

	/** Sets the last expression provided by {@link #orderBy} to ascending order */
	public SELECT asc() {
		if (orderBy != null && orderBy.size() > 0) {
			OrderByCondition ob = (OrderByCondition) orderBy.get(orderBy.size()-1);
			ob.setOrder(Order.ASC);
		} else {
			throw new RuntimeException("ASC clause without an ORDER-BY clause given.");
		}
		return this;
	}

	/** Sets the last expression provided by {@link #orderBy} to descending order */
	public SELECT desc() {
		if (orderBy != null && orderBy.size() > 0) {
			OrderByCondition ob = (OrderByCondition) orderBy.get(orderBy.size()-1);
			ob.setOrder(Order.DESC);
		} else {
			throw new RuntimeException("ASC clause without an ORDER-BY clause given.");
		}
		return this;
	}
	
	/**
	 * Prints out the whole query as String
	 */
	public String toString(ToStringMode mode) {
		checkAndFinishJoin();
		StringBuffer sb = new StringBuffer();
		if (this.getAlias() != null) {
			sb.append("(");
		}
		sb.append("SELECT ");
		if (modifiers != null && modifiers.size() > 0) {
			if (modifiers.contains(Modifier.UNIQUE)) {
				sb.append("UNIQUE ");
			} else if (modifiers.contains(Modifier.DISTINCT)) {
				sb.append("DISTINCT ");
			} else if (modifiers.contains(Modifier.ALL)) {
				sb.append("ALL ");
			}
		}
		if (fields == null || fields.size() == 0) {
			// Nothing explicitly stated for selection: Assume SELECT * ...!
			sb.append("*");
		} else {
			sb.append(printArrayList(fields, mode));
		}
		sb.append("\n");
		if (tables.size() > 0) {
			// This is used for providing SELECT VERSION() without FROM clause
			sb.append(" FROM ");
			// Print FROM table alias ...
			sb.append(printArrayList(tables, mode, true));
			sb.append("\n");
		}
		
		if (joins != null) {
			for (JoinCondition jc: joins) {
				sb.append(jc.toString(mode));
				sb.append("\n");
			}
		}

		appendWherePart(sb, mode);
		
		if (groupBy != null && groupBy.size() > 0) {
			sb.append(" GROUP BY ");
			sb.append(printArrayList(groupBy, mode));
			sb.append("\n");
		}

		if (havingClause != null && !havingClause.isEmpty()) {
			// Calculate HAVING clause analogously to WHERE
			PrintVisitResult r = (PrintVisitResult) havingClause
				.accept(new ExpressionPrintSQLVisitor(mode));
			sb.append(" HAVING ");
			sb.append(r.getText());
			sb.append("\n");
		}
		
		// Chained queries: UNION, UNION ALL, MINUS, INTERSECT
		if (chainedQueries != null && chainedQueries.size() > 0) {
			for (Query cq : chainedQueries) {
				sb.append(cq.innerChainedKeyword);
				sb.append("\n");
				sb.append(" ");
				sb.append("(");
				sb.append(cq.toString(mode));
				sb.append(")");
				sb.append("\n");
			}
		}

		if (orderBy != null && orderBy.size() > 0) {
			sb.append(" ORDER BY ");
			sb.append(printArrayList(orderBy, mode));
			sb.append("\n");
		}
		
		if (this.getAlias() != null) {
			sb.append(") ");
			sb.append(getAlias());
			sb.append("\n");
		}
		
		return sb.toString(); 
	}

}
