package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.S;


/**
 * 
 * @author martin
 *
 *Vector, der kein null-objecte zulaesst
 *
 * @param <T>
 */
public class VectorNN<T> extends Vector<T> {
	
	@Override
	public boolean add(T member) {
		
		if (member instanceof String) {
			if (S.isFull((String)member)) {
				return super.add(member);
			} else {
				return false;
			}
		} else {
			if (member != null) {
				return super.add(member);
			} else {
				return false;
			}
		}
	}

	

	@Override
	public boolean addAll(Collection<? extends T> coll) {
		for (T wert: coll) {
			this.add(wert);
		}
		return true;
	}
	
	
	
}
