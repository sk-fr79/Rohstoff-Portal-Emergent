package panter.gmbh.Echo2.AgentsAndValidators;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_FieldSetter_AND_Validator
{
	
	/**
	 * @param cSTATUS_MAP
	 * @param EXT_own 
	 * @returns vector of MyString
	 * @throws myException
	 */
	public abstract MyE2_MessageVector isValid( String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException; 
}
