/**
 * 
 */
package panter.gmbh.basics4project.objectstore;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MyExceptionStoredValueNotSingluar extends myException {

	public MyExceptionStoredValueNotSingluar(ValueAsKey valueAsKey) {
		super("ObjectStore: StoredValue is not singular: "+valueAsKey.getIdentifierString());
	}
	
}
