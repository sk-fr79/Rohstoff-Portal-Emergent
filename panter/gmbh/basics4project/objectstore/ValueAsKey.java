/**
 * 
 */
package panter.gmbh.basics4project.objectstore;

import panter.gmbh.indep.S;

/**
 * @author martin
 *
 */
public class ValueAsKey {
	
	private STORECODE 	cacheCode = 	null;
	private String   	addOnString = 	null;
	
	/**
	 * @param key
	 * @param addOn
	 */
	public ValueAsKey(STORECODE key, String addOn) {
		super();
		this.cacheCode = key;
		this.addOnString = 	addOn;
	}
	
	/**
	 * @param key
	 * @param addOn
	 */
	public ValueAsKey(STORECODE key) {
		super();
		this.cacheCode = key;
		this.addOnString = 	"";
	}


	public STORECODE getCacheCode() {
		return cacheCode;
	}

	public String getAddOnString() {
		return addOnString;
	}

	
	@Override
	public boolean equals( Object obj ) {
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof ValueAsKey)) {
			return false;
		}
		
		if (obj==this) {
			return true;
		}
		
		ValueAsKey keyobj = (ValueAsKey)obj;
		
		if (this.cacheCode==keyobj.cacheCode && this.addOnString.equals(keyobj.addOnString)) {
			return true;
		}
		
		return false;
	}

	
	public String getIdentifierString() {
		return this.cacheCode.name()+" <"+S.NN(this.addOnString)+">";
	}
	
}
