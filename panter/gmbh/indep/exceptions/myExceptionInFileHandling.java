package panter.gmbh.indep.exceptions;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;

public class myExceptionInFileHandling extends myException
{
	public static MyE2_String  ERROR_FILETYPE_NOT_ALLOWED = 	new MyE2_String("Dateityp ist nicht erlaubt !");
	public static MyE2_String  ERROR_FILE_EXISTS = 				new MyE2_String("Datei existiert bereits !");
	public static MyE2_String  ERROR_FILECANNOT_BE_WRITTEN = 	new MyE2_String("Datei kann nicht geschrieben werden !");
	
	private MyString 	oMessage = null; 
	private String 		cAdditionalInfo = null;
	
	public myExceptionInFileHandling(MyE2_String errorMessage,String cadditionalInfo)
	{
		super(errorMessage.COrig());
		this.oMessage = errorMessage;
		this.cAdditionalInfo = cadditionalInfo;
	}

	public MyString get_oMessage()
	{
		return oMessage;
	}

	public String get_cAdditionalInfo()
	{
		return cAdditionalInfo;
	}

}
