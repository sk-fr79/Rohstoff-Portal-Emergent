package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_DIVERSE extends XXX_ViewBuilder{

	@Override
	public boolean build_View_forAll_Mandants() throws myException {
		
		String cSQL_USERLAST_AUSWERTUNG = new VIEW_ResourceStringLoader("VIEW_USERLAST_AUSWERTUNG").get_cText();
		
		RECLIST_MANDANT reclistMandanten = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		for (RECORD_MANDANT recMandant : reclistMandanten.values()) {
			String cID_Mandant = recMandant.get_ID_MANDANT_cUF();
			
			String cQuery = bibALL.ReplaceTeilString(cSQL_USERLAST_AUSWERTUNG, "#MANDANT#", cID_Mandant);
			DEBUG.System_println(cQuery);
			
			if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cQuery, true))
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_USER_LAST_AUSWERTUNG ",false, " wurde erfolgreich erstellt.",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_USER_LAST_AUSWERTUNG",false, " wurde NICHT erstellt: FEHLER !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		return false;
	}

}
