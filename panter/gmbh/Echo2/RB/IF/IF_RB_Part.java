package panter.gmbh.Echo2.RB.IF;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.indep.exceptions.myException;

public interface IF_RB_Part<T> {
	
	public void rb_set_belongs_to(T obj) throws myException;
	public T rb_get_belongs_to() throws myException;
	
	/**
	 * 
	 * @return key, under which this component is registered
	 * @throws myException
	 */
	public RB_K   getMyKeyInCollection() throws myException;
	public RB_K   setMyKeyInCollection(RB_K key) throws myException;
	
}
