package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_BEWEGUNG_FLACH extends XXX_ViewBuilder
{

	@Override
	public boolean build_View_forAll_Mandants() throws myException 
	{
		
		String cSQL_FLACH = new VIEW_ResourceStringLoader("VIEW_BEWEGUNG_FLACH").get_cText();
		String cSQL_ATOM = new VIEW_ResourceStringLoader("VIEW_BEWEGUNG_ATOM").get_cText();
		
		RECLIST_MANDANT reclistMandanten = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		for (RECORD_MANDANT recMandant : reclistMandanten.values())
		{
			String cID_Mandant = recMandant.get_ID_MANDANT_cUF();
			
			if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibALL.ReplaceTeilString(cSQL_ATOM, "#MANDANT#", cID_Mandant), true))
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_BEWEGUNG_ATOM",false, " wurde erfolgreich erstellt.",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_BEWEGUNG_ATOM",false, " wurde NICHT erstellt: FEHLER !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
			
			if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibALL.ReplaceTeilString(cSQL_FLACH, "#MANDANT#", cID_Mandant), true))
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_BEWEGUNG_FLACH",false, " wurde erfolgreich erstellt.",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Der View ",true," V"+cID_Mandant+"_BEWEGUNG_FLACH",false, " wurde NICHT erstellt: FEHLER !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		return true;
	}

}
