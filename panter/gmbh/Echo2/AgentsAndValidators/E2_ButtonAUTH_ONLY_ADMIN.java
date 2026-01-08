/*
 * Created on 27.01.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.AgentsAndValidators;


import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;


// allgemeine validator-klasse, die jedem button zugeordnet wird,
// der sowohl den modul als auch den button-kenn-string erhält
// und der als globaler validator auftritt
public class E2_ButtonAUTH_ONLY_ADMIN extends XX_ActionValidator
{

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (!bibALL.get_bIST_SUPERVISOR())
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Nur ein Benutzer mit ADMINISTRATOR-Rechten darf diese Funktion ausführen !")));
		}
		
		return oMV;
	}


	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		return new MyE2_MessageVector();
	}

}
