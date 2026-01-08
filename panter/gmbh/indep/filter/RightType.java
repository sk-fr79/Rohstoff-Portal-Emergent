package panter.gmbh.indep.filter;

/**
 * Enumeration of types possible for the right handside of a filter.
 * These are currently CONST (constant values, like numbers and strings),
 * SQL (the result set of an SQL query) or SET (a collection of objects).
 * They are the types the {@see LeftType}s are compared with. 
 * @author nils
 *
 */
public enum RightType {
	CONST(_FilterConstants.RIGHT.CONST), 
	SQL(_FilterConstants.RIGHT.SQL), 
	SET(_FilterConstants.RIGHT.SET),
	LITERAL(_FilterConstants.RIGHT.LITERAL),
	FIELD(_FilterConstants.RIGHT.FIELD);

	private final String id;

	RightType(Enum id) {
		this.id = id.toString();
	}
}