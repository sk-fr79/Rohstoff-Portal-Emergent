/**
 * 
 */
package panter.gmbh.BasicTools.NotNull;

/**
 * @author martin
 *
 */
public interface IF_NN<T> {
	
	public default T notNull(T me, T whenNull) {
		if (me==null) {
			return whenNull;
		} 
		return me;
	}

}
