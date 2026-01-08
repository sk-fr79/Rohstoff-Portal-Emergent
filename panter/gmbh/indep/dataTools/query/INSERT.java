package panter.gmbh.indep.dataTools.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

//TODO: Der INSERT braucht eine HashMap Field=>value

/**
 * Class used to formulate INSERT SQL statements.
 *
 * Each public method in this class returns {@code this}, allowing 
 * it for chaining methods in the JQuery way. 
 * @author nils
 *
 */
public class INSERT extends Query implements UpdatingQuery {
	protected List<List<SQLPart>> inserts;
	protected InsertType type;
	/** Key=>Value mapping for values to be inserted. Used for {@see #set} and {@see #unset}. */
	protected Map<Object, Object> kvPairs;

	
	/** Copy constructor */
	public INSERT(INSERT copy) {
		super(copy);
		this.inserts = new ArrayList<List<SQLPart>>(copy.inserts);
		this.type = copy.type;
		this.kvPairs = new HashMap<Object, Object>(copy.kvPairs);
	}
	
	/**
	 * The INSERT statement is constructed with an optional list of
	 * {@param column}s on which the insert statement is supposed to run on. It
	 * is equivalent to write 
	 * {@code new INSERT("id", "name").into("table").values("1", "Henry")}
	 * for the SQL statement 
	 * {@code INSERT INTO table (id, name) VALUES ("1", "Henry")}
	 * @param column the columns to be inserted (note the strings are interpreted
	 * as column identifiers: {@link ID}s)
	 */
	public INSERT(Object... column) {
		insert(column);
	}

	/** Constructor alias */
	public INSERT insert(Object... column) {
		ensureFieldsNotNull();
		addSQLParts(column, fields, Clause.INSERT, false);
		return this;
	}
	
	/**
	 * INTO clause to specify the table(s) to insert data to. Usually
	 * at least and only one table is required. 
	 * @param table the table to insert to (not that strings are interpreted
	 * as table identifiers: {@link TID})
	 */
	public INSERT into(Object ... table) {
		if (table.length != 1) {
			throw new RuntimeException("Must specify exactly one table to insert into (here, "+table.length+" are given)");
		}
		addSQLParts(table, tables, Clause.INTO, false);
		return this;
	}
	
	/** Helper function to add rows to the insert statement */
	protected INSERT addRow(Clause cl, Object... values) {
		List<SQLPart> row = new ArrayList<SQLPart>();
		for (Object value : values) {
			row.add(Query.getValue(value));
		}
		if (inserts == null) {
			inserts = new ArrayList<List<SQLPart>>();
		}
		inserts.add(row);
		return this;
	}

	/**
	 * Formulates a subselect on an insert (selecting data from different 
	 * tables to be inserted, via sub-{@link SELECT})
	 * @param subselect the Subselect {@link SELECT} statement
	 */
	public INSERT select(Object... subselect) {
		type = InsertType.SUBSELECT;
		return addRow(Clause.INTO, subselect);
	}

	/**
	 * Formulates a regular insert with VALUES. Each of the provided {@param values}
	 * is supposed to be corresponding to either the column count and the indexes
	 * used on creating the {@link #INSERT} statement, or the number of columns of the
	 * specified table (see {@link #into} clause) 
	 * @param value the values to be inserted (note the strings are interpreted
	 * as values: {@link V}s)
	 */
	public INSERT values(Object... value) {
		type = InsertType.VALUES;
		return addRow(Clause.VALUES, value);
	}
	
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}
	
	/**
	 * Strings the statement into an SQL String.
	 */
	public String toString(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT");
		fixKeyValuePairs();
		
		if (inserts.size() > 1) {
			sb.append(" ALL\n");
		}
		
		for (List<SQLPart> row : inserts) {
			sb.append(" INTO ");
			sb.append(printArrayList(tables, mode));
			if (inserts.size() == 1) {
				sb.append("\n");
			}
			if (fields != null && fields.size() > 0) {
				sb.append(" (");
				sb.append(printArrayList(fields, mode));
				sb.append(")");
				if (inserts.size() == 1) {
					sb.append("\n");
				}
			}
			if (type == InsertType.SUBSELECT) {
				sb.append(" ");
				sb.append(printArrayList(inserts.get(0), mode));
			}
			if (type == InsertType.VALUES) {
				sb.append(" VALUES (");
				sb.append(printArrayList(row, mode));
				sb.append(")");
			}
		}
		if (inserts.size() > 1) {
			sb.append("\nSELECT 1 FROM DUAL\n");
		}
		return sb.toString(); 
	}

	/**
	 * Adds the key=>value pairs to the insert set.
	 */
	private void fixKeyValuePairs() {
		if (kvPairs != null) {
			// We will put that right with a VALUES clause
			type = InsertType.VALUES;
			
			Object[] values = new Object[kvPairs.size()];
			Iterator<Entry<Object, Object>> it = kvPairs.entrySet().iterator();
			int i = 0;
			ensureFieldsNotNull();
			while (it.hasNext()) {
				Entry<Object, Object> key = it.next();
				addSQLParts(new Object[]{key.getKey()}, fields, Clause.INSERT, false);
				values[i] = key.getValue();
				i++;
			}

			// If no row is present, create one
			ArrayList<SQLPart> row;
			if (inserts == null) {
				inserts = new ArrayList<List<SQLPart>>();
				row = new ArrayList<SQLPart>();
				inserts.add(row);
			} else {
				// Otherwise take the last row
				row = (ArrayList<SQLPart>) inserts.get(0);
			}

			// Add all values
			for (Object value : values) {
				row.add(Query.getValue(value));
			}
			
			// Set this to NULL since the pairs already were added;
			// if we did not to this and called toString() several times,
			// we would end up in duplicate field names in the query!
			kvPairs = null;
		}
	}
	
	/** 
	 * Sets a key=>value pair to be inserted.
	 * @param column the column name (note that strings are interpreted
	 * as column identifiers: {@link ID}s)
	 * @param value the value (note that values will be wrapped into 
	 * value objects: {@link V}s)
	 */
	public INSERT set(Object column, Object value) {
		if (kvPairs == null) {
			kvPairs = new HashMap<Object, Object>();
		}
		kvPairs.put(column, value);
		return this;
	}

	public INSERT set(String column, String value) {
		return set((Object)column, (Object)value);
	}
	/**
	 * Unsets a previously set key=>value pair. The specified key
	 * will not be part of the insert statement unless re-defined
	 * and overwitten later)
	 * @param key the column name (note that strings are interpreted
	 * as column identifiers: {@link ID}s) to be unset
	 */
	public INSERT unset(Object key) {
		if (kvPairs == null) {
			kvPairs = new HashMap<Object, Object>();
		}
		kvPairs.remove(key);
		return this;
	}

	@Deprecated
	public String toStringMySQL(ToStringMode mode) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(printArrayList(tables, mode));
		sb.append("\n");
		if (fields != null && fields.size() > 0) {
			sb.append(" (");
			sb.append(printArrayList(fields, mode));
			sb.append(")");
			sb.append("\n");
		}
		if (type == InsertType.SUBSELECT) {
			sb.append(" ");
			sb.append(printArrayList(inserts.get(0), mode));
		}
		
		if (type == InsertType.VALUES) {
			sb.append(" VALUES (");
			int rows = 0;
			for (List<SQLPart> row : inserts) {
				rows++;
				if (rows > 1) {
					sb.append("), (");
				}
				sb.append(printArrayList(inserts.get(0), mode));
			}
			sb.append(")");
		}
		sb.append("\n");
		return sb.toString(); 
	}}
