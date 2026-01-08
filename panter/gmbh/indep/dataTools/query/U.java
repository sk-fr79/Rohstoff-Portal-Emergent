package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

/** Something unquoted, which should be not treated in any way (escaping, quoting...) */ 
public class U implements SQLPart {
	private Object encapsulated;
	private String newName;
	
	/** 
	 * Constructs the unquoted argument. Usually, this is a string, to be passed
	 * "as-is" into the SQL statement, without any quoting, parsing or other
	 * modification. This is useful when internal SQL functions are combined with
	 * values. Precautions of proper quoting must be taken manually.
	 **/ 
	public U(Object expression) {
		this.encapsulated = expression;
	}

	public U(Object s, String newName) {
		this(s);
		this.newName = newName;
	}
	
	@Override
	public String toString(ToStringMode mode) {
		if (newName == null) {
			return encapsulated.toString();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(encapsulated.toString());
			sb.append(" AS ");
			sb.append(newName);
			return sb.toString();
		}
	}

	@Override
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}
	
	@Override
	public String toStringSubstituted() {
		return toString(ToStringMode.SUBSTITE);
	}
	
	public U as(String newName) {
		this.newName = newName;
		return this;
	}
}
