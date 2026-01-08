package panter.gmbh.indep.exceptions;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.indep.S;

public class myExceptionDataValueNotFittingToField extends myException
{
	
	public static final int VALUE_CAN_NOT_BE_A_NUMBER = 324323223;
	public static final int VALUE_CAN_NOT_BE_A_DATE = 324323224;
	public static final int VALUE_IS_TOO_LONG = 324323225;
	public static final int VALUE_CAN_NOT_BE_NULL = 324323226;
	
	private int 	iErrorType = -1;
	
	public myExceptionDataValueNotFittingToField()
	{
		super();
	}

	public myExceptionDataValueNotFittingToField(int ierrortype, String errorMessage)
	{
		super(errorMessage);
		this.iErrorType = ierrortype;
	}

	public int get_iErrorType()
	{
		return iErrorType;
	}

	/**
	 * 
	 * @param fieldName
	 * @param newValueFormated
	 * @return
	 */
	public MyE2_Alarm_Message  get_Message(String fieldName, String newValueFormated) {
		if (this.iErrorType	== myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG)
		{
			return new MyE2_Alarm_Message(new MyE2_String( "<"+fieldName+"> ",false,"Fehler: Eingabe ",true,  "<"+S.NN(newValueFormated)+">",false, " ist zu lang !",true));
		}
		else if (this.iErrorType == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_DATE)
		{
			return  new MyE2_Alarm_Message(new MyE2_String("<"+fieldName+"> ",false,"Fehler: Eingabe ",true,  "<"+S.NN(newValueFormated)+">",false," ist kein Datumswert !",true));
		}
		else if (this.iErrorType == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_NUMBER)
		{
			return  new MyE2_Alarm_Message(new MyE2_String("<"+fieldName+"> ",false,"Fehler: Eingabe ",false, " <"+S.NN(newValueFormated)+">",false,  " ist keine Zahl !",true));
		}
		else if (this.iErrorType == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_NULL)
		{
			return  new MyE2_Alarm_Message(new MyE2_String("<"+fieldName+"> ",false,"Fehler: Eingabe darf nicht leer sein !",true));
		}
		return new MyE2_Alarm_Message(new MyE2_String("<"+fieldName+">   <"+S.NN(newValueFormated)+">  ",false,"undefinierter Fehler !",true));

	}
	
	
}
