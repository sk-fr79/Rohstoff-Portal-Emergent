package panter.gmbh.indep.filter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;

/**
 * Creates a set of Objects by executing a Query, looping over
 * the results and taking the first element of each result set entry.
 * @author nils
 *
 */
public class SQLSetString extends SetString {
	String query;
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public SQLSetString(String query) throws SQLException, myException {
		super();
		this.query = query;
		constructFromSQLQuery(this.query);
	}
	
	public void constructFromSQLQuery(String query) throws SQLException, myException {
		this.clear();
		ArrayList<HashMap<String, Object>> r;
		r = DBUtil.select(this.query);
		for (HashMap<String, Object> hm : r) {
			for (Object o : hm.values()) {
				try {
					this.add((String)o);
				} catch (ClassCastException e) {
					this.add(o.toString());
				}
			}
		}
	}
}

