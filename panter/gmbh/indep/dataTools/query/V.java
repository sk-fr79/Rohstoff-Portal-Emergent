package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;
import panter.gmbh.indep.filter.SetString;

/** 
 * A wrapper class for values (which will later be candidates for quoting),  
 * @see U for a class which will wrap things that are not being quoted
 */
public class V implements SQLPart {
	private Object encapsulated;
	private String alias;

	public V(Object s) {
		this.encapsulated = s;
	}

	public V(Object s, String alias) {
		this(s); 
		this.as(alias);
	}

	public V as(String alias) {
		this.alias = alias.trim().toLowerCase();
		return this;
	}

	@Override
	public String toString(ToStringMode mode) {
		return Query.quoteLiteral(encapsulated)+(alias != null ? " AS "+alias : "");
	}
	
	@Override
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}
	
	@Override
	public String toStringSubstituted() {
		return toString(ToStringMode.SUBSTITE);
	}
}