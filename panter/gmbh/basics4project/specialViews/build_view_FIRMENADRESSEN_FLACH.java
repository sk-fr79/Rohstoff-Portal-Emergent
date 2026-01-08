package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_FIRMENADRESSEN_FLACH extends XXX_ViewBuilder{

	@Override
	public boolean build_View_forAll_Mandants() throws myException {
		
		String cSQL = new VIEW_ResourceStringLoader("VIEW_FIRMENADRESSEN_FLACH").get_cText();
		
		if (bibDB.ExecSQL(cSQL, true))
		{
			MyE2_String cInfo = new MyE2_String("Mandantenunabhängige View für flache Firmen-Adress-Struktur erfolgreich erstellt !",true);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
		}
		else
		{
			MyE2_String cInfo = new MyE2_String("Mandantenunabhängige View für flache Firmen-Adress-Struktur KONNT NICHT ERSTELLT WERDEN!",true);
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
		}
		
		
		return false;
	}

}
