package rohstoff.plugins;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.plugins.XX_DB_PLUGIN_PREPARE_REPORTING;

public class Prepare_TempTables_RechnungsPos extends XX_DB_PLUGIN_PREPARE_REPORTING {

	@Override
	public boolean CHECK_IF_MUST_BE_EXECUTED() throws myException {
		
		String cZahl1= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_EK_RECHPOS_MONAT_SUM").trim();
		String cZahl2= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_VK_RECHPOS_MONAT_SUM").trim();
		
		if (cZahl1.startsWith("@") || cZahl2.startsWith("@") ) {
			throw new myException("Prepare_TempTables_Atom: CHECK_IF_MUST_BE_EXECUTED: Error querying ST_EK_RECHPOS_MONAT_SUM/ST_VK_RECHPOS_MONAT_SUM");
		}
		
		if (cZahl1.equals("0") || cZahl2.equals("0")) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public MyE2_MessageVector EXECUTED_BEFORE_REPORT() throws myException {
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cSQL1="INSERT INTO ST_EK_RECHPOS_MONAT_SUM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_EK_RECHPOS_MONAT_SUM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL1, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL1,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_EK_RECHPOS_MONAT_SUM> erfolgreich geschrieben ...",true)));
		}
		
		String cSQL2="INSERT INTO ST_VK_RECHPOS_MONAT_SUM (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_VK_RECHPOS_MONAT_SUM)";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL2, true)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL2,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Temporäre Tabelle <ST_VK_RECHPOS_MONAT_SUM> erfolgreich geschrieben ...",true)));
		}
		
		return oMV;
	}
	

}
