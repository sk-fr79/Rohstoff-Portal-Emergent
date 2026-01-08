/**
 * 
 */
package panter.gmbh.basics4project.objectstore;

import java.util.Vector;
import java.util.WeakHashMap;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class ObjectStoreMap extends WeakHashMap<Object, ValueAsKey> {

	public ObjectStoreMap() {
		super();
	}
	
	
	/**
	 * returns first object in Keyset where value equals valueAsKey
	 * @param valueAsKey
	 * @return
	 * @throws MyExceptionStoredValueNotSingluar 
	 */
	public Object get(ValueAsKey valueAsKey) throws myException {
		Vector<Object>  v = new Vector<>();
		
		for (Object o: this.keySet()) {
			
			if (this.get(o).equals(valueAsKey)) {
				v.add(o);
			}
			
		}
		
		if (v.size()>1) {
			throw new MyExceptionStoredValueNotSingluar(valueAsKey);
		} else if (v.size()==0) {
			throw new MyExceptionStoredValueNotFound(valueAsKey);
		} else {
			return v.get(0);
		}
	}

	
	public void removeFromStore(ValueAsKey valueAsKey) {
		for (Object o: this.keySet()) {
			if (this.get(o).equals(valueAsKey)) {
				this.remove(o);
			}
		}
	}


	/**
	 * 
	 */
	@Override
	public ValueAsKey put(Object o, ValueAsKey v) {
		this.removeFromStore(v);
		super.put(o, v);
		return v;
	}
	
	
	
	
	
}
