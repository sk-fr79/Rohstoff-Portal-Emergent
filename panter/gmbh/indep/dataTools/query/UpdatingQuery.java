package panter.gmbh.indep.dataTools.query;

/**
 * Interface for queries that perform writing updates based on
 * column values to be set on the database (these are: {@link INSERT}s and
 * {@link UPDATE}s)
 * 
 * @author nils
 *
 */
public interface UpdatingQuery extends WritingQuery {
	/** 
	 * Sets a column value.
	 * 
	 * @param column The affected column
	 * @param value The value to be set
	 * @return the Query itself.
	 */
	public UpdatingQuery set(String column, String value);

	/** 
	 * Sets a column value.
	 * 
	 * @param column The affected column
	 * @param value The value to be set
	 * @return the Query itself.
	 */
	public UpdatingQuery set(Object column, Object value);
}
