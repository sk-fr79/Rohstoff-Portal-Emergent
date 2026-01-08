package panter.gmbh.Echo2;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


/**
 * 
 *  
 * @author martin
 *
 */
public abstract class XX_MessageManipulator {
		
	public abstract MyE2_MessageVector  get_Changed_MessageVector(MyE2_MessageVector oMV) throws myException; 
	
}
