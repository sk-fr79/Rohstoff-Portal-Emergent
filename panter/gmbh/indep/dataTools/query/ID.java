package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.MyDBToolboxQueryUtils;
import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

/** An column identifier: A database column name.*/
public class ID extends AID implements SQLPart {
	/** 
	 * Sets the identifier. This may be unqualified (only given in the form "column_name"), 
	 * or a qualified column name with a preceeding table name ("table_name.colum_name").
	 * The <code>identifier</code> argument is therefore parsed appropriately.
	 * @param identifier
	 */
	public ID(final String identifier) {
		super(identifier);
		normalizeIdentifier();
	}

	public ID(final String alias, final String identifier) {
		super(alias, identifier);
		normalizeIdentifier();
	}
	
	private void normalizeIdentifier() {
		if (alias == null && identifier.indexOf(".") != -1) {
			alias = identifier.substring(0, identifier.indexOf("."));
			identifier = identifier.substring(identifier.indexOf(".")+1);
			normalizeAlias();
		}
		normalizeRenamedIdentifier();
		identifier = identifier.trim().toLowerCase();
	}

	@Override
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}

	@Override
	public String toString(ToStringMode mode) {
		//System.out.println("ID:"+identifier+","+alias+","+newName);
		String quote = Query.idQuote;
		if (identifier.equals("*")) {
			quote = "";
		}
		StringBuffer sb = new StringBuffer();
		if (alias != null) {
			String al = alias;
			if (mode == ToStringMode.SUBSTITE) {
				al = MyDBToolboxQueryUtils.replaceTable(alias);
			}
			if (al != null && !al.equals("")) {
				sb.append(al);
				sb.append(".");
			}
		}
		sb.append(quote);
		sb.append(identifier);
		sb.append(quote);
		if (newName != null) {
			sb.append(" AS ");
			sb.append(newName);
		}
		return sb.toString();
	}
	
	@Override
	public String toStringSubstituted() {
		return toString(ToStringMode.SUBSTITE);
	}
}
