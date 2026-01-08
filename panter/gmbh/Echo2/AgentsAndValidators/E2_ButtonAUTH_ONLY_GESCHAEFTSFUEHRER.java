package panter.gmbh.Echo2.AgentsAndValidators;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER extends XX_ActionValidator
{

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)  throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (!bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Nur ein Benutzer mit GESCHAEFTSFÜHRER-Rechten darf diese Funktion ausführen !")));
		}
		
		return oMV;
	}


	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) 	throws myException
	{
		return null;
	}

}
