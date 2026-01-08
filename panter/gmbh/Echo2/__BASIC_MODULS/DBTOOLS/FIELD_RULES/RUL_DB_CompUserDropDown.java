package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RUL_DB_CompUserDropDown extends DB_Component_USER_DROPDOWN_NEW
{

	public RUL_DB_CompUserDropDown(SQLField osqlField) throws myException
	{
		super(osqlField, true);
	}

}
