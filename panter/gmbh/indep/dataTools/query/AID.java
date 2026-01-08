package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

/** An abstract Identifier */
public abstract class AID implements SQLPart {
	protected String alias;
	protected String identifier;
	protected String newName; 
	
	public AID(final String identifier) {
		this(null, identifier);
	}

	public AID(final String alias, final String identifier) {
		this.alias = alias;
		normalizeAlias();
		this.identifier = identifier.trim().toLowerCase();
	}

	protected void normalizeAlias() {
		if (alias == null) return;
		alias = alias.trim().toLowerCase();
	}

	protected void normalizeNewName() {
		if (newName == null) return;
		newName = newName.trim().toLowerCase();
	}
	
	protected void normalizeRenamedIdentifier() {
		if (identifier == null) {
			throw new RuntimeException("An identifier cannot be NULL!");
		}
		if (identifier.indexOf(" ") != -1) {
			
			newName = identifier.substring(identifier.indexOf(" ")+1);
			if (newName.indexOf(" ") != -1) {
				newName = newName.substring(newName.indexOf(" ")+1);
			}
			normalizeNewName();
			identifier = identifier.substring(0, identifier.indexOf(" "));
		}
	}

	public abstract String toString(ToStringMode mode);
	
	public AID as(String newName) {
		this.newName = newName;
		return this;
	}
}


