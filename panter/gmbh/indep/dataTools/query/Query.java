package panter.gmbh.indep.dataTools.query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.filter.AndExpression;
import panter.gmbh.indep.filter.Comparison;
import panter.gmbh.indep.filter.ComparisonExpression;
import panter.gmbh.indep.filter.ExpressionPrintSQLVisitor;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.INodeVisitor;
import panter.gmbh.indep.filter.IVisitResult;
import panter.gmbh.indep.filter.NotExpression;
import panter.gmbh.indep.filter.OrExpression;
import panter.gmbh.indep.filter.PrintVisitResult;
import panter.gmbh.indep.filter.SetString;
import panter.gmbh.indep.filter._FilterConstants;
import panter.gmbh.indep.filter._FilterConstants.COMP;
import panter.gmbh.indep.filter._FilterConstants.OP;

/**
 * Parent class for creating SQL statements on a basis of trying to resemble the
 * way one writes SQL statements as a string in a JQuery-like way, by always
 * returning <code>this</code> on public methods. This allows for writing
 * statements like <tt>
 * {@link SELECT} s = new SELECT("*").from("a_table").where("a", "=", 1)
 * {@link INSERT} i = new INSERT("*").from("a_table").where("a", "=", 1)
 * </tt>
 * 
 * @author nils
 * 
 */
public abstract class Query implements SQLPart, INodeElement {
	
	public static final String idQuote = ""; // "\"";
	public static final String valueQuote = "'";

	protected List<SQLPart> fields = null;
	protected boolean isSelectStar = false;
	protected List<SQLPart> tables = new ArrayList<SQLPart>();
	protected List<JoinCondition> joins;
	protected AndExpression whereClause;
	protected boolean negatedWhere = false;
	protected List<SQLPart> orderBy;
	protected List<SQLPart> groupBy;
	protected AndExpression havingClause = new AndExpression();

	protected List<Query> chainedQueries = null;
	protected String innerChainedKeyword = "";

	protected static String schemaName;
	protected static String mandantId;

	/**
	 * The context is the variable which holds the current context to which
	 * information provided in (joins) ON and the WHERE clause; because of the
	 * nature of always returing <code>this</code> on public methods, it is
	 * required for the class to "know" on which context conditions are
	 * currently added.
	 */
	protected AndExpression context;

	/** Alias for this Query (used in subquery context) */
	protected String alias;

	protected List<Modifier> modifiers = new ArrayList<Modifier>();
	
	static {
		try {
			schemaName = bibE2.cTO();
		} catch (NullPointerException npe) {
			//schemaName = "testdb";
		}

		try {
			mandantId = bibALL.get_ID_MANDANT();
		} catch (NullPointerException npe) {
			//mandantId = "1";
		}
	}


	/** SELECT clause via constructor */
	public Query(Object... parm) {
	}
	
	/** Copy constructor instead of clone() */
	//TODO: This is to be completed; the deep-copying is not yet completed
	public Query(Query copy) {
		this.isSelectStar = copy.isSelectStar;

		this.fields = new ArrayList<SQLPart>(copy.fields);
		this.tables = copy.tables;
		this.joins = copy.joins;
		this.whereClause = copy.whereClause;
		this.negatedWhere = copy.negatedWhere;

		this.orderBy = copy.orderBy;
		this.groupBy = copy.groupBy;
		this.havingClause = copy.havingClause;
		this.chainedQueries = copy.chainedQueries;
		this.innerChainedKeyword = copy.innerChainedKeyword;

		this.schemaName = copy.schemaName;
		this.mandantId = copy.mandantId;
		
		this.context = copy.context;
		this.alias = copy.alias;
		this.modifiers = copy.modifiers;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/** Ensures that this.fields is not null */
	protected void ensureFieldsNotNull() {
		if (this.fields == null) {
			this.fields = new ArrayList<SQLPart>();
		}
	}

	/**
	 * Helper function to take an array of Objects and to add them to an
	 * ArrayList in a structure normalizing
	 * 
	 * @param parm
	 *            The given parameters
	 * @param target
	 *            the target list these parameters be added to
	 * @param clauseName
	 *            name of the clause we are in, e.g. SELECT
	 * @param allowNumerics
	 *            if numerics are allowed to stand alone
	 * @return the target list
	 */
	// TODO: Here, also Date objects and everything atomic must be handled
	protected List<SQLPart> addSQLParts(Object[] parm, List<SQLPart> target,
			Clause clause, boolean allowNumerics) {
		SQLPart answer = null;
		for (int i = 0, j = parm.length; i < j; i++) {
			Object org = parm[i];
//			if (org instanceof Column) {
//				//TODO: This must be made more proper and cleaner
//				org = org.toString();
//			}
			
			Object o = getTransformed(org, allowNumerics, false);
			if (o instanceof String || o instanceof Number) {
				if (!allowNumerics && o instanceof Number) {
					throw new RuntimeException(
							"Numbers are not allowed in " + clause + ".");
				} else {
					if (o instanceof Number) {
						o = o.toString();
					}
				}
				if (clause == Clause.VALUES) {
					answer = new V((String) o);
				} else if (clause == Clause.SELECT || clause == Clause.INSERT
						|| clause == Clause.GROUP_BY) {
					answer = (new ID(null, (String) o));
					
				} else if (clause == Clause.FROM || clause == Clause.INTO
						|| clause == Clause.UPDATE || clause == Clause.DELETE) {
					answer = (new TID(null, (String) o));
				} else if (clause == Clause.ORDER_BY) {
					answer = (new OrderByCondition(null, (String) o));
				}
			} else if (o == null) {
				if (!allowNumerics) {
					throw new RuntimeException(
							"Stupid parameter in " + clause + ": "+o);
				}
			} else {
				answer = (SQLPart)o;
			} 
			
			// Do not add a double star (SELECT *, *...)
			if (clause == Clause.SELECT && org instanceof String && org.equals("*")) {
				if (!isSelectStar) {
					isSelectStar = true;
				} else {
					// Do not instert another star!
					return target;
				}
			}

			target.add(answer);
		}

		return target;
	}
	
	/**
	 * Transforms an Object into it's an SQLPart or a String.
	 * @param o the input object
	 * @param allowNumerics if true, numeric values are converted to string, or return null otherwise
	 * @return the SQLPart or String
	 */
	//TODO: Das AllowNumerics ist gefährlich
	public static Object getTransformed(Object o, boolean allowNumerics, boolean wrapIntoValue) {
		Object answer = null;
		if (o instanceof String || o instanceof Number) {
			if (o instanceof Number && allowNumerics) {
				answer = ((Number) o).toString();
			} else {
				answer = o;
			}
			if (wrapIntoValue) {
				if (answer == null) {
					answer = o;
				}
				return new V(answer);
			}
			return answer;
		} else if (o instanceof ID) {
			return ((ID) o);
		} else if (o instanceof TID) {
			return ((TID) o);
		} else if (o instanceof U) {
			return ((U) o);
		} else if (o instanceof V) {
			return ((V) o);
		} else if (o instanceof Query) {
			return ((Query) o);
		} else {
			try {
				double d = Double.parseDouble((String) o);
				answer = Double.valueOf(d).toString();
			} catch (NumberFormatException nfe) {
			} catch (ClassCastException cce) {
			} catch (NullPointerException npe) {
			}
		}
		if (wrapIntoValue) {
			if (answer == null) {
				answer = o;
			}
			return new V(answer);
		}
		return answer;
	}
	
	/** 
	 * Takes an Object and wraps it into a value object {@link V}, if 
	 * it is not a {@link Query}, an {@link ID}, {@link TID}, {@link V} 
	 * or {@link U} already. 
	 * */
	public static SQLPart getValue(Object o) {
		return (SQLPart)getTransformed(o, false, true);
	}

	/** SELECT modifiers */
	protected void addModifier(Modifier m) {
		if (modifiers == null) {
			modifiers = new ArrayList<Modifier>();
		}
		modifiers.add(m);
	}

	/** Helper method */
	protected INodeElement comparisonFromInput(Object l, Object op, Object r) {
		return getComparisonFromInput(l, op, r, false);
	}

	/**
	 * Yields a {@link ComparisonExpression} from the input strings.
	 * 
	 * @param l
	 *            the left handside of the comparison
	 * @param op
	 *            the comparison operation, e.g. '=' or '>'
	 * @param r
	 *            the right handside of the comparison
	 * @param idRightSide
	 *            whether the right handside is to be taken as an identifier or
	 *            not
	 * @return the {@link ComparisonExpression} object
	 */
	protected INodeElement getComparisonFromInput(Object l, Object op,
			Object r, boolean idRightSide) {
		boolean isNegated = false;

		String opString;
		if (!(op instanceof String)) {
			opString = ((COMP)op).getSqlId();
		} else {
			// Normalize operator
			opString = ((String)op).trim().toUpperCase().replaceAll("[\\s_\\-]", "");
		}

		// Find out if it is a negation
		String tempOp = negation(opString);
		if (!tempOp.equals(opString)) {
			opString = tempOp;
			isNegated = true;
		}
		COMP c = _FilterConstants.COMP.fromSqlId(opString);
		Comparison c2 =  Comparison.valueOf(c.getID());
		
		INodeElement answer;
		

		if (r == null) {
			r = new V(null);
		}
		
		Object modifiedRightHandside = r;
		if (idRightSide) {
			// Make the right handside an Identifier rather than take it as a
			// value
			modifiedRightHandside = new ID((String) r);
		}
		Object leftHandSide = l;
		if (l instanceof String) {
			leftHandSide = new ID((String)l);
		}
		
		answer = new ComparisonExpression(leftHandSide, c2, modifiedRightHandside);
		if (isNegated) {
			answer = new NotExpression(answer);
		}
		return answer;
	}

	/** Checks if an operator is negated and returns it's positive form */
	protected String negation(String op) {
		if (op.equals("!=") || op.equals("<>") || op.equals("^=")) {
			op = "=";
		} else if (op.equals("NOTIN")) {
			op = "IN";
		} else if (op.equals("NOTEXISTS")) {
			op = "EXISTS";
		} else if (op.equals("NOTNULL")) {
			op = "NULL";
		} else if (op.equals("ISNOT")) {
			op = "IS";
		} else if (op.equals("NOTLIKE")) {
			op = "LIKE";
		}
		return op;
	}

	/**
	 * Adds a condition <code>e</code> to the given {@link AndExpression}.
	 * 
	 * @param e
	 *            the Expression to add to
	 * @param type
	 *            of the context in which the expression is added to, which is
	 *            determined by the calling method (<code>.and()</code>,
	 *            <code>.or()</code>)
	 * @param addTo
	 *            where the Expression <code>e</code> is to be added to
	 */
	protected void addConditional(INodeElement e, OP type, AndExpression addTo) {
		
		if (addTo == null) {
			// Usually, the WHERE clause is NULL upon object creation
			if (whereClause == null) {
				whereClause = new AndExpression();
			}
			addTo = whereClause;
		}
		if (type.equals(OP.OR)) {
			INodeElement tmp = addTo.getLastChild();
			if (tmp == null) {
				// Nothing in the WHERE clause so far!
				OrExpression o = new OrExpression();
				o.addChild(e);
				addTo.addChild(o);
			} else if (tmp instanceof OrExpression) {
				// Last child in the WHERE clause is already an OR => add to
				// this
				((OrExpression) tmp).addChild(e);
			} else {
				// Last child in the WHERE is something else => do an
				// "OR"ing
				// with this last child
				OrExpression o = new OrExpression();
				o.addChild(addTo.removeLastChild());
				o.addChild(e);
				addTo.addChild(o);
				return;
			}
		} else {
			// It's part of the AND clause
			addTo.addChild(e);
		}
	}

	/** Helper method to comma-separate an Array of SQLParts */
	protected String printArrayList(List<? extends SQLPart> input, ToStringMode mode, boolean alias) {
		if (input == null || input.size() == 0)
			return "";
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (SQLPart child : input) {
			i++;
			if (i > 1) {
				sb.append(", ");
			}
			String text;
			
			if (alias && child instanceof TID) {
				text = ((TID)child).toStringWithAlias(mode);
			} else {
				text = child.toString(mode);
			}
			// Important part: Subselects need to be in brackets, and may have
			// an alias
			if (child instanceof Query) {
				Query subQuery = (Query) child;
				if (subQuery.getAlias() == null) {
					// If it had an ALIAS, the brackets would already 
					// be in place by calling Query.toString()
					sb.append("(");
				}
				sb.append(text);
				if (subQuery.getAlias() == null) {
					sb.append(")");
				}
			} else {
				sb.append(text);
			}
		}
		return sb.toString();
	}

	protected String printArrayList(List<? extends SQLPart> input, ToStringMode mode) {
		return printArrayList(input, mode, false);
	}


	/** Appends WHERE-part (including WHERE keyword) into a {@code StringBuffer} */
	protected void appendWherePart(StringBuffer sb, ToStringMode mode, boolean addWhereKeyword) {
		if (whereClause != null && !whereClause.isEmpty()) {
			// Calculate WHERE clause
			INodeElement evaluate = whereClause;
			if (negatedWhere) {
				evaluate = new NotExpression(whereClause);
			}

			PrintVisitResult r = (PrintVisitResult) evaluate
					.accept(new ExpressionPrintSQLVisitor(mode));
			if (addWhereKeyword == true) {
				sb.append(" WHERE ");
			}
			sb.append(r.getText());
			if (addWhereKeyword == true) {
				sb.append("\n");
			}

		}
	}

	protected void appendWherePart(StringBuffer sb, ToStringMode mode) {
		appendWherePart(sb, mode, true);
	}
	
	@Override
	public IVisitResult accept(INodeVisitor visitor) {
		return new PrintVisitResult(this.toString());
	}

	public static String quoteLiteral(Object e) {
		return Query.quoteLiteral(e, true);
	}

	/** Quotes the literal according to it's type. Please note that this is oracle-specific. */
	public static String quoteLiteral(Object e,
			boolean quoteRightHandsidesAsLiterals) {
		// Arrays
		if (e instanceof Object[]) {
			e = new SetString((Object[])e);
		}
		if (e instanceof List) {
			e = new SetString((List)e);
		}
		
		if (!(e instanceof ID) && !(e instanceof U) && !(e instanceof V)
				&& !(e instanceof Query) && !(e instanceof SetString)) {
			if (e == null) return "NULL";
			if (e.equals(true)) return "TRUE";
			if (e.equals(false)) return "FALSE";
			String s = e.toString();
			
			String q = valueQuote;
			
			if ((e instanceof Number) || (e instanceof BigDecimal) || (e instanceof BigInteger)) {
				q = "";
			}
			//ORACLE: '' => ', not \' => '
			s = q + (s.replace(valueQuote, valueQuote+valueQuote)) + q;
			if (e instanceof java.sql.Timestamp) {
				s = "TO_TIMESTAMP("+s+", 'YYYY-MM-DD HH24:MI:SS.FF4')";
			}
			if (e instanceof java.util.Date) {
				s = "TO_DATE('"+new java.sql.Date(((java.util.Date)e).getTime())+"', 'YYYY-MM-DD')";
			}
			return s;
		}

		if (e instanceof Query) {
			return "(" + e + ")";
		}
		return "" + e;
	}
	
	
	
	/** Returns the current MandantId */
	public static String getMandantId() {
		return mandantId;
	}

	/** Sets the current MandantId */
	public static void setMandantId(String mandantId) {
		Query.mandantId = mandantId;
	}

	/** Sets the Schema/Database prefix for Table identifiers */
	public static String getSchemaName() {
		return schemaName;
	}

	/** Returns the Schema/Database prefix for Table identifiers */
	public static void setSchemaName(String name) {
		schemaName = name;
	}
	
	
	
	
	public List<SQLPart> getFields() {
		return fields;
	}
	public List<SQLPart> getTables() {
		return tables;
	}
	public List<JoinCondition> getJoinConditions() {
		return joins;
	}
	public AndExpression getWhereClause() {
		return whereClause; 
	}
	public List<SQLPart> getOrderByClause() {
		return orderBy;
	}
	public List<SQLPart> getGroupBy() {
		return groupBy;
	}
	public AndExpression getHavingClause() {
		return havingClause;
	}
	
	/** Convenience methods to extract parts of the final Stringed SQL */
	
	/** Get WHERE-block (without WHERE keyword */
	public String getWhere() {
		return getWhere(ToStringMode.REGULAR);
	}
	public String getWhere(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		appendWherePart(sb, mode, false);
		return sb.toString();
	}

	/** Get SELECT ... -block (fields, without SELECT keyword */
	public String getSelect() {
		return getSelect(ToStringMode.REGULAR);
	}
	public String getSelect(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		sb.append(printArrayList(fields, mode));
		return sb.toString();
	}

	/** Get FROM ... -block (tables, without FROM keyword */
	public String getFrom() {
		return getFrom(ToStringMode.REGULAR);
	}
	public String getFrom(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		sb.append(printArrayList(tables, mode));
		return sb.toString();
	}
	
	/** Get ORDER BY ... -block (without ORDER_BY keyword */
	public String getOrderBy() {
		return getOrderBy(ToStringMode.REGULAR);
	}
	public String getOrderBy(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		sb.append(printArrayList(orderBy, mode));
		return sb.toString();
	}

	

	public enum ToStringMode {
		REGULAR, SUBSTITE
	}
	
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}
	public String toStringSubstituted() {
		return toString(ToStringMode.SUBSTITE);
	}
	public abstract String toString(ToStringMode mode);
}