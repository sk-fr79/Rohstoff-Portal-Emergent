package panter.gmbh.Echo2.components.unboundDataFields;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class VALIDATE_INPUT_Float_Nullable extends XX_ValidateInput{
	
	@Override
	public MyE2_MessageVector isValid(String inputText, IF_UB_Fields field)	throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		boolean bDoCheck = true;
		
		if (field.get_bEmptyAllowd() && (inputText == null || inputText.equals(""))){
			// wenn leer erlaubt und das feld ist auch leer (auch keine Leerzeichen)
			bDoCheck = false;
		}

		if (bDoCheck)
		{
			// dann formatieren oder abweisen
			DotFormatter oDotF = new DotFormatterGermanFixed(inputText);
			
			if (! oDotF.doFormat())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_StringForMSG("Das Feld benötigt eine Zahl als Eingabe! ",field)));
			}
		} 
		
		return oMV;
	}
}
