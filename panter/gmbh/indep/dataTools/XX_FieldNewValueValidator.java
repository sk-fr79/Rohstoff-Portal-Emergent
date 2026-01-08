package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.MyString;


public abstract class XX_FieldNewValueValidator
{
	private boolean 	bIsValid = false;
	private SQLField 	oSQLField = null;
	private MyString 	oErrorStringForUser = null;
	
	public XX_FieldNewValueValidator(SQLField field)
	{
		super();
		oSQLField = field;
	}
	
	
	public abstract boolean doValidate(String cNewValue);


	public boolean get_bIsValid()
	{
		return bIsValid;
	}


	public SQLField get_oSQLField()
	{
		return oSQLField;
	}


	public MyString get_oErrorStringForUser()
	{
		return oErrorStringForUser;
	}


	public void set_oErrorStringForUser(MyString errorStringForUser)
	{
		oErrorStringForUser = errorStringForUser;
	}
	
	
	
	
}
