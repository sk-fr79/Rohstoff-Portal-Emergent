/**
 * 
 */
package panter.gmbh.indep.enumtools;

import java.util.HashMap;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * 
 * erzeugt fuer jeden enum-wert einen hashMap-eintrag, der einen VEK<U> enthaelt
 * 
 */
public interface IF_enumSammlerMitSchubladen<T extends Enum<T>, U> extends IF_enumForDb<T> {
	
	/**
	 * erzeugt einmalig einen sammler mit den schubladen 
	 * @return
	 * @throws myException
	 */
	public default HashMap<T, VEK<U>> generateSchubladen() throws myException {
		HashMap<T, VEK<U>> hm = new HashMap<>();
		
		for (T t: this.getValues()) {
			hm.put(t, new VEK<U>());
		}
		return hm;
	}

	
	/**
	 * fuegt dem sammler den wert val in der schublade enumval hinzu
	 * @param sammler
	 * @param enumval
	 * @param val
	 */
	public default void add(HashMap<T, VEK<U>> sammler, T enumval, U val) {
		sammler.get(enumval)._a(val);
	}
	
	
	public default long getCount(HashMap<T, VEK<U>> sammler, T enumval) {
		return sammler.getOrDefault(enumval, new VEK<U>()).size();
	}
	
	
}
