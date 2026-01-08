/**
 * 
 */
package panter.gmbh.basics4project.objectstore;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_keys4save;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */

@SuppressWarnings("rawtypes")
public enum STORECODE {
	
	
	NAVILIST(E2_NavigationList.class) 
	
	;
	private Class allowedClass = null;
	
	private STORECODE(Class cl) {
		this.allowedClass = cl;
	}

	public Class getAllowedClass() {
		return allowedClass;
	}
	


	
	public void store(Object ob, String addonKey) {
		ObjectStoreMap map = ((ObjectStoreMap) bibSES.getSessionValue(bibSES_keys4save.OBJECT_CACHE));
		
		if (map!=null) {
			//dann die map in die session schreiben
			map = new ObjectStoreMap();
			bibSES.setSessionValue(bibSES_keys4save.OBJECT_CACHE, map);
		}

		map.put(ob, new ValueAsKey(this, addonKey));
	}
	
	
	public Object get(String addonKey) throws myException {
		ObjectStoreMap map = ((ObjectStoreMap) bibSES.getSessionValue(bibSES_keys4save.OBJECT_CACHE));
		if (map!=null) {
			map = new ObjectStoreMap();
			bibSES.setSessionValue(bibSES_keys4save.OBJECT_CACHE, map);
		}
		return map.get(new ValueAsKey(this, addonKey));
	}
	
	
	public void store(Object ob) {
		ObjectStoreMap map = ((ObjectStoreMap) bibSES.getSessionValue(bibSES_keys4save.OBJECT_CACHE));
		
		if (map!=null) {
			//dann die map in die session schreiben
			map = new ObjectStoreMap();
			bibSES.setSessionValue(bibSES_keys4save.OBJECT_CACHE, map);
		}

		map.put(ob, new ValueAsKey(this));
	}
	
	
	public Object get() throws myException {
		ObjectStoreMap map = ((ObjectStoreMap) bibSES.getSessionValue(bibSES_keys4save.OBJECT_CACHE));
		if (map!=null) {
			map = new ObjectStoreMap();
			bibSES.setSessionValue(bibSES_keys4save.OBJECT_CACHE, map);
		}
		return map.get(new ValueAsKey(this));
	}
	
	

	
	
}
