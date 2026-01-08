package panter.gmbh.indep.dataTools.query;

/**
 * Static factory for terms used in the Query (table and column identifiers,
 * values and unquoted values).
 */
public class Term {
	/**  
	 * Returns a table identifier
	 * @param tableName the name of the table
	 * @return the table identifier 
	 */
	public static TID table(String tableName) {
		return new TID(tableName);
	}

	/**  
	 * Returns the correlated table identifier (a table name
	 * along with it's correlation name), analogously to the SQL 
	 * {@code table_name t1}.
	 * @param tableName the name of the table
	 * @param correllationName the table's correlation name
	 * @return the aliased table identifier 
	 */
	public static TID table(String tableName, String correlationName) {
		return new TID(correlationName, tableName);
	}

	/**  
	 * Returns an column/field identifier
	 * @param fieldName the name of the field/column
	 * @return the column/field identifier 
	 */
	public static ID field(String fieldName) {
		return new ID(fieldName);
	}
	
	/**  
	 * Returns an qualified column/field identifier (a column/field
	 * identifier prefixed by the table corellation name), analougusly
	 * to the SQL {@code table_correllation_name.field_name}.
	 * @param correlationName field's/column's alias 
	 * @param fieldName the name of the field/column
	 * @return the aliased column/field identifier 
	 */
	public static ID field(String correlationName, String fieldName) {
		return (ID)new ID(correlationName, fieldName);
	}

	/** Synonym for {@link #field(String)} */
	public static ID column(String fieldName) {
		return field(fieldName);
	}
	
	/** Synonym for {@link #field(String, String)} */
	public static ID column(String correlationName, String fieldName) {
		return field(correlationName, fieldName);
	}
	
	/** 
	 * Returns a value object wrapper, which means the the wrapped
	 * object will later be quoted and properly treated as value
	 * (for example a string, a number or a date)
	 * @param value The value object  
	 * @return the wrapped value
	 */
	public static V value(Object value) {
		return new V(value);
	}
	
	/** 
	 * Returns an aliased value object wrapper, which means the the wrapped
	 * object will later be quoted and properly treated as value
	 * (for example a string, a number or a date)
	 * @param value The value object  
	 * @return the wrapped value
	 */
	public static V value(Object value, String alias) {
		return new V(value).as(alias);
	}

	/** 
	 * Returns a wrapper for something that is left unquoted / as-is / native
	 * when calling toString() on the specific {@link Query}.
	 * This is useful if you need to specify some sophisticated SQL
	 * expression like 
	 * <code>
	 * CASE WHEN t1.col1 IS NULL CONCAT(t1.col2," ") ELSE t2.col3 END
	 * </code>
	 * and want to ensure it is not parsed/modified/quoted. 
	 * {@see U}
	 * @param value The string/something to be left unquoted
	 * @return the wrapped native value
	 */
	public static U unquoted(Object expression) {
		return new U(expression);
	}

	/** 
	 * Returns an aliased, unquoted wrapper. See {@see #unquoted(Object)} 
	 * for details.
	 */
	public static U unquoted(Object expression, String alias) {
		return new U(expression).as(alias);
	}
}
