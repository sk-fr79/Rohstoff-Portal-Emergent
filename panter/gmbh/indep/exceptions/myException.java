/*
 * Created on 24.07.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.indep.exceptions;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.indep.S;

public class myException extends Exception
{
    public static int TYPE_ERROR_ON_QUERY = 1000;
    public static int TYPE_ERROR_ON_DELETE = 1001;
    public static int TYPE_ERROR_ON_QUERYSEQUENCE = 1002;
    public static int TYPE_ERROR_ON_DELETE_RELATION_MASTER = 1003;
    public static int TYPE_ERROR_ON_POSITIONING_MASKQUERY = 1004;
    public static int TYPE_ERROR_ON_FILLING_QUERY_TO_TABLEFIELD = 1005;
    public static int TYPE_ERROR_ON_SEARCHING_KEYFIELD = 1006;
    public static int TYPE_ERROR_ON_FILLING_MASK_TO_TABLEFIELD = 1007;
    public static int TYPE_ERROR_ON_GENERATINGSQL_FORSPECIALFIELD = 1008;
    public static int TYPE_ERROR_REQUIRED_FIELD_IS_NULL = 1009;
    public static int TYPE_ERROR_SEARCHED_FIELD_IS_NOT_UNIQUE = 1010;
	public static int TYPE_ERROR_JASPER = 1020;
	public static int TYPE_ERROR_ITEXT = 1030;
	public static int TYPE_ERROR_CREATE_DBDEFINED_MASK = 1050;
	public static int TYPE_ERROR_ECHOLAUNCHER = 1060;
    
	public static int TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT = 11060;

	// codes fuer MyDataRecordHashMap
	public static int TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE = 11061;
	public static int TYPE_ERROR_SQL_QUERY_IS_EMPTY = 11062;
	

	
    public int ErrorCode;
    public String ErrorMessage;
    public String ZusatzInfo = null;

    private StackTraceElement[]  StackFromMotherException = null;
    
    public myException()
    {
    }

    public myException(int Errorcode)
    {
        super("ErrorCode (myException)"+Errorcode);
        this.ErrorCode = Errorcode;
        ErrorMessage = "";
    }

    public myException(int errorCode, String errorMessage)
    {
        super("ErrorCode (myException)"+errorCode+"\n"+errorMessage);
        this.ErrorCode = errorCode;
        this.ErrorMessage = errorMessage;
    }

    public myException(String errorMessage)
    {
        super(errorMessage);
        this.ErrorCode = 0;
        this.ErrorMessage = errorMessage;
    }

    public myException(myException ex)
    {
        super(ex.getOriginalMessage());
        this.ErrorCode = ex.get_ErrorCode();
        this.ErrorMessage = ex.getOriginalMessage();
    }

    
    
    public myException(String errorMessage, Exception oFatherException)
    {
        super(errorMessage);
        this.ErrorCode = 0;
        this.ErrorMessage = errorMessage;
        this.StackFromMotherException = oFatherException.getStackTrace();
    }

    
    public myException(Object objWhere, String errorMessage)
    {
        super(objWhere.getClass().getName()+":"+errorMessage);
        this.ErrorCode = 0;
        this.ErrorMessage = objWhere.getClass().getName()+":"+errorMessage;
    }
    
    public myException(Object objWhere, String errorMessage, String cZusatzInfo)
    {
        super(objWhere.getClass().getName()+":"+errorMessage);
        this.ErrorCode = 0;
        this.ErrorMessage = objWhere.getClass().getName()+":"+errorMessage;
        this.ZusatzInfo = cZusatzInfo;
    }
  
    
    public String getLocalizedMessage()
    {
    	return this.ErrorMessage;
    }
    
    public String getOriginalMessage()
    {
    	return this.ErrorMessage;
    }

    public int get_ErrorCode()
    {
        return ErrorCode;
    }

    public MyE2_Message get_ErrorMessage()
    {
        return new MyE2_Message(new MyE2_String(this.ErrorMessage,false),MyE2_Message.TYP_ALARM,false);
    }

    public MyE2_Message get_ErrorMessage(String addOn)
    {
        return new MyE2_Message(new MyE2_String(this.ErrorMessage,false).ut(" <"+S.NN(addOn)+">"),MyE2_Message.TYP_ALARM,false);
    }

    
    
	public StackTraceElement[] get_StackFromMotherException()
	{
		return StackFromMotherException;
	}

	public void set_StackFromMotherException(StackTraceElement[] stackFromMotherException)
	{
		StackFromMotherException = stackFromMotherException;
	}
}
