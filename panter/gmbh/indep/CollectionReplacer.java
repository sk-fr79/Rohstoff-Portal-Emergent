/**
 * 
 */
package panter.gmbh.indep;

import java.util.LinkedHashMap;
import java.util.Map;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class CollectionReplacer<T,H> {

	/**
	 * class replaces in map the entryOld with entryNew
	 *  
	 */
	public CollectionReplacer(LinkedHashMap<T, H> map, Map.Entry<T, H> entryOld, Map.Entry<T, H> entryNew) throws myException {
		super();
		
	
		boolean bOldWasFound = false;
		LinkedHashMap<T, H> mapTemp = new LinkedHashMap<T, H>();
		
		mapTemp.putAll(map);
		map.clear();
		
		for (Map.Entry<T, H> e: mapTemp.entrySet()) {
			if (e.getKey().equals(entryOld.getKey())) {
				map.put(entryNew.getKey(), entryNew.getValue());
				bOldWasFound = true;
			} else 	{
				map.put(e.getKey(), e.getValue());
			}
		}
		
		if (!bOldWasFound) {
			throw new myException(this, "Error: old entry was not found in collection !!");
		}
	}
	
		
	
}
