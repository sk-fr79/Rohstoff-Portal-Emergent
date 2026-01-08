package panter.gmbh.indep.filter;

/**
 * Enumeration for the possible types on the left handside of a filter.
 * Currently, this is only a literal (an "identifier" for a column)
 * @author nils
 *
 */
public enum LeftType {
	LITERAL(_FilterConstants.LEFT.LITERAL);

	private final String id;

	LeftType(Enum id) {
		this.id = id.toString();
	}
}
