package panter.gmbh.Echo2.components.unboundDataFields;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_ValidateInput
{
	public abstract MyE2_MessageVector isValid(String cInputText, IF_UB_Fields oField) throws myException;
	
	
}
