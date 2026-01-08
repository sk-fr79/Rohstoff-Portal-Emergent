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

public class build_table_JD_DATUM extends XXX_ViewBuilder{

	@Override
	public boolean build_View_forAll_Mandants() throws myException {
		String sSQL1 = "DROP TABLE JD_DATUM";
		String sSQL2 = "DROP SEQUENCE SEQ_DATUM";
		
		String sSQL3 = new VIEW_ResourceStringLoader("CREATE_TABLE_JD_DATUM").get_cText();
		String sSQL4 = "CREATE UNIQUE INDEX idx_datum_1 on JD_DATUM(date_ori)";
		String sSQL5 = "CREATE SEQUENCE SEQ_DATUM  START WITH  1000  INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999 NOCACHE";
		String sSQL6 = new VIEW_ResourceStringLoader("FILL_TABLE_JD_DATUM").get_cText();
		
		
		boolean bOK = true;
		DEBUG.System_println(sSQL1,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL1, false);
		DEBUG.System_println(sSQL2,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL2, false);
		
		DEBUG.System_println(sSQL3,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bOK &= bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL3, false);
		DEBUG.System_println(sSQL4,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bOK &= bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL4, false);
		DEBUG.System_println(sSQL5,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bOK &= bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL5, false);
		DEBUG.System_println(sSQL6,DEBUG.DEBUG_FLAG_SQL_EXEC);
		bOK &= bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sSQL6, false);

		if (bOK){
			bOK &= bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
		
		
		if (bOK)
		{
			MyE2_String cInfo = new MyE2_String("Die Tabelle JD_DATUM wurde angelegt und gefüllt");
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
		}
		else
		{
			MyE2_String cInfo = new MyE2_String("FEHLER: Die Tabelle JD_DATUM konnte nicht angelegt und gefüllt werden");
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
		}
	
		
		return bOK;
	}

}
