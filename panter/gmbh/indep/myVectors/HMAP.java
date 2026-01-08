/**
 * panter.gmbh.indep.myVectors
 * @author martin
 * @date 17.12.2018
 * 
 */
package panter.gmbh.indep.myVectors;

import java.util.AbstractMap;
import java.util.LinkedHashMap;


/**
 * @author martin
 * @date 17.12.2018
 *
 */
public class HMAP<K, V> extends LinkedHashMap<K, V> {

	
	public static interface filter {
		public boolean isInFilter(Object key, Object value);
	}
	
	
	public HMAP<K, V> _put(K key, V value) {
		this.put(key, value);
		
		return this;
	}
	
	public HMAP<K, V> _putAll(AbstractMap<K,V> map) {
		this.putAll(map);
		
		return this;
	}
	
	
	public VEK<K> getKeys() {
		return new VEK<K>()._a(this.keySet());
	}
	
	public VEK<V> getValues() {
		return new VEK<V>()._a(this.values());
	}
	

	
	/**
	 * 
	 * @author martin
	 * @date 19.12.2018
	 *
	 * @return s array of values or null when empty
	 */
	public K[] getArrayKeys() {
		return this.getKeys().getArray();
	}
	

	/**
	 * 
	 * @author martin
	 * @date 19.12.2018
	 *
	 * @return s array of keys or null when empty
	 */
	public V[] getArrayValues() {
		return this.getValues().getArray();
	}
	
	
	public HMAP<K, V> _clear() {
		this.clear();
		return this;
	}
	
	
	public HMAP<K,V> getHMAPFiltered(filter filter) {
		HMAP<K,V> ret = new HMAP<K,V>();
		
		for (K key: this.keySet()) {
			if (filter.isInFilter(key, this.get(key))) {
				ret._put(key, this.get(key));
			}
		}
		
		return ret;
	}
	
	
	
	
	
}
