package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public interface IF_seek_and_find_first<T> {

	public T get_found(VEK<T> v) throws myException;
	
}
