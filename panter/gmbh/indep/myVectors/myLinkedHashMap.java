package panter.gmbh.indep.myVectors;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class myLinkedHashMap<T1,T2> extends LinkedHashMap<T1,T2> {

	/**
	 * 
	 */
	public myLinkedHashMap() {
		super();
	}

	
	
	public myLinkedHashMap<T1,T2> _putInFront(T1 key, T2 val) {
		
		myLinkedHashMap<T1,T2> temp = new myLinkedHashMap<T1,T2>();
		temp.putAll(this);
		
		this.clear();
		this.put(key, val);
		this.putAll(temp);
		
		return this;
	}
	
	
	public myLinkedHashMap<T1,T2> _put(T1 key, T2 val) {
		this.put(key, val);
		
		return this;
	}
	
	public myLinkedHashMap<T1,T2> _putAll(LinkedHashMap<T1,T2> hm) {
		this.putAll(hm);
		return this;
	}
	

	public T1  get_first_key() {
		for (T1 t: this.keySet()) {
			return t;
		}
		return null;
	}
	

	public T2  get_first_val() {
		for (T1 t: this.keySet()) {
			return this.get(t);
		}
		return null;
	}
	
	
	
	public boolean contains_key(T1 key) {
		return this.keySet().contains(key);
	}
	
	public boolean contains_val(T2 val) {
		return this.values().contains(val);
	}
	
	
	public myLinkedHashMap<T1,T2> _sortKeys(Comparator<T1> c) {
		Vector<T1> v = new Vector<>();
		v.addAll(this.keySet());
		v.sort(c);
		
		myLinkedHashMap<T1, T2> puffer = new myLinkedHashMap<>();
		puffer.putAll(this);
		
		this.clear();
		
		for (T1 key: v) {
			this.put(key, puffer.get(key));
		}
		
		return this;
	}
	
	
	/**
	 * @return key that fits to value, when nothing found then null
	 */
	public T1 getKeyFromValue(T2 value) {
		
		for (Map.Entry<T1,T2> e: this.entrySet()) {
			if (e.getValue().equals(value)) {
				return e.getKey();
			}
			
		}
		return null;
	}
	
	
	
	
	public T1 getKeyAt(int i) {
		
		T1 ret = null;
		int iter=0;
		
		for (T1 t: this.keySet()) {
			if (i==iter) {
				ret=t;
				break;
			}
			iter++;
		}
		return ret;
	}

	
	public T2 getValAt(int i) {
		
		T2 ret = null;
		int iter=0;
		
		for (T1 t: this.keySet()) {
			if (i==iter) {
				ret=this.get(t);
				break;
			}
			iter++;
		}
		return ret;
	}

	
}
