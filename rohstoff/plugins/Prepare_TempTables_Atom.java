package rohstoff.plugins;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.plugins.XX_DB_PLUGIN_PREPARE_REPORTING;

public class Prepare_TempTables_Atom extends XX_DB_PLUGIN_PREPARE_REPORTING {

	@Override
	public boolean CHECK_IF_MUST_BE_EXECUTED() throws myException {
		
		String cZahl1= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_WA_ATOM").trim();
		String cZahl2= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_WA_MONATS_SUM").trim();
		String cZahl3= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_WE_ATOM").trim();
		String cZahl4= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_WE_MONATS_SUM").trim();
		
		if (cZahl1.startsWith("@") || cZahl2.startsWith("@") || cZahl3.startsWith("@") || cZahl4.startsWith("@") ) {
			throw new myException("Prepare_TempTables_Atom: CHECK_IF_MUST_BE_EXECUTED: Error querying ST_WA_ATOM/ST_WA_MONATS_SUM/ST_WE_ATOM/ST_WE_MONATS_SUM");
		}
		
		if (cZahl1.equals("0") || cZahl2.equals("0") || cZahl3.equals("0") || cZahl4.equals("0") ) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public MyE2_MessageVector EXECUTED_BEFORE_REPORT() throws myException {
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cSQL1="INSERT INTO ST_WE_ATOM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_WE_ATOM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL1, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL1,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_WE_ATOM> erfolgreich geschrieben ...",true)));
		}
		
		String cSQL2="INSERT INTO ST_WE_MONATS_SUM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_WE_MONATS_SUM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL2, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL2,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_WE_MONATS_SUM> erfolgreich geschrieben ...",true)));
		}
		
		String cSQL3="INSERT INTO ST_WA_ATOM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_WA_ATOM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL3, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL3,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_WA_ATOM> erfolgreich geschrieben ...",true)));
		}
		
		String cSQL4="INSERT INTO ST_WA_MONATS_SUM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_WA_MONATS_SUM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL4, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL4,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_WA_MONATS_SUM> erfolgreich geschrieben ...",true)));
		}
		
		return oMV;
	}
	
//	protected void finalize() throws Throwable {
//		super.finalize();
//		DEBUG.System_println("Prepare_TempTables_Atom wird gekilled", "");
//	}

}
