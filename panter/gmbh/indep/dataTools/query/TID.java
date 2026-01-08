package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.MyDBToolboxQueryUtils;
import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

/** A table identifier/name */
public class TID extends AID implements SQLPart {
	/** 
	 * Sets the table identifier. This may be unqualified (only given in the form "table_name"), 
	 * or a qualified form in two parts (with optional AS in between) in an aliased form.
	 * The <code>identifier</code> argument is therefore parsed appropriately.
	 * @param identifier
	 */
	public TID(String identifier) {
		super(identifier);
		normalizeTableAlias();
	}

	public TID(final String alias, final String identifier) {
		super(alias, identifier);
		normalizeRenamedIdentifier();
		normalizeTableAlias();
	}
	private void normalizeTableAlias() {
		if (alias == null && newName != null) {
			this.alias = newName;
			newName = null;
		}
		normalizeAlias();
	}

	public void appendToString(StringBuilder sb, ToStringMode mode) {
		String schema = Query.getSchemaName();
		if (mode == ToStringMode.SUBSTITE && schema != null && !schema.equals("")) {
			sb.append(Query.getSchemaName());
			sb.append(".");
		}
		sb.append(Query.idQuote);
		if (mode == ToStringMode.REGULAR) {
			sb.append(identifier);
		} else if (mode == ToStringMode.SUBSTITE) { 
			sb.append(MyDBToolboxQueryUtils.replaceTable(identifier));
		}
		sb.append(Query.idQuote);
	}
	
	
	public String toStringWithAlias(ToStringMode mode) {
		StringBuilder sb = new StringBuilder();
		appendToString(sb, mode);
		if (alias != null) {
			sb.append(" ");
			sb.append(alias); 
		}
		return sb.toString();
	}
	
	@Override
	public String toString(ToStringMode mode) {
		StringBuilder sb = new StringBuilder();
		appendToString(sb, mode);
		return sb.toString();
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
