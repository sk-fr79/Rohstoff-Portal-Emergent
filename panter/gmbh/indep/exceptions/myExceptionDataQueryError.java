package panter.gmbh.indep.exceptions;

public class myExceptionDataQueryError extends myException
{
	
	public static final int ERROR_ID_NOT_FOUND = 674323223;
	
	private int 	iErrorType = -1;
	
	public myExceptionDataQueryError()
	{
		super();
	}

	public myExceptionDataQueryError(int ierrortype, String errorMessage)
	{
		super(errorMessage);
		this.iErrorType = ierrortype;
	}

	public int get_iErrorType()
	{
		return iErrorType;
	}

}
