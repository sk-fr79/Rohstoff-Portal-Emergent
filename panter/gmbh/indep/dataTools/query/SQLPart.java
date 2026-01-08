package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.query.Query.ToStringMode;

public interface SQLPart {

	/**
	 * Should offer an alternative way of stringing itself, if something
	 * like that was wanted; usually should call toString() 
	 */
	String toString();
	String toString(ToStringMode mode);
	String toStringSubstituted();

}