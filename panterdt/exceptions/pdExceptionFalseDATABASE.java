package panterdt.exceptions;

import panter.gmbh.indep.dataTools.DB_META;

public class pdExceptionFalseDATABASE extends pdException 
{

	public pdExceptionFalseDATABASE(String FalseDBType) 
	{
		super("Only DBTypes "+DB_META.DB_ORA+" and "+DB_META.DB_SAP+" are allowed (not "+FalseDBType+" )!!");
	}

}
