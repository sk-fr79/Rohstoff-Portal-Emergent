package panter.gmbh.Echo2.RB.IF;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.indep.exceptions.myException;

public interface IF_RB_Collector<T> {
	
	public T registerComponent(RB_K key, T obj) throws myException;

}
