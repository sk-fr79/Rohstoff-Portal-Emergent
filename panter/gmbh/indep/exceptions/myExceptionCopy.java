package panter.gmbh.indep.exceptions;

public class myExceptionCopy extends myException
{
	public static final String ERROR_COPYING = 					"ERROR_COPYING";
	public static final String ERROR_COPY_NOT_IMPLEMENTED = 	"ERROR_COPY_NOT_IMPLEMENTED";
	
	public myExceptionCopy(String ErrorMessage)
	{
		super(ErrorMessage);
	}

}
