package panter.gmbh.Echo2.components.unboundDataFields;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class VALIDATE_INPUT_DATE extends XX_ValidateInput
{

	@Override
	public MyE2_MessageVector isValid(String inputText, IF_UB_Fields field)	throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		// dann formatieren oder abweisen
		TestingDate oTestDate = new TestingDate(inputText);
		
		if (! oTestDate.testing())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt ein Datum als Eingabe !",field)));
		}

		return oMV;
	}


}
