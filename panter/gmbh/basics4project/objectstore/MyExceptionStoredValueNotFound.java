/**
 * 
 */
package panter.gmbh.basics4project.objectstore;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MyExceptionStoredValueNotFound extends myException {

	public MyExceptionStoredValueNotFound(ValueAsKey valueAsKey) {
		super("ObjectStore: No object found: "+valueAsKey.getIdentifierString());
	}
	
}
