package rohstoff.plugins;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.plugins.XX_DB_PLUGIN_PREPARE_REPORTING;

public class Prepare_StandardTempTable extends XX_DB_PLUGIN_PREPARE_REPORTING {

	private String cNAMEBASE_OF_TABLE_AND_VIEW = null;
	
	/**
	 * 
	 * @param nameBASE_OF_TABLE_AND_VIEW = basisname, den view und Temptable gleich haben (z.B. WA_ATOM, dann heist der name des view S#ID_MANDANT#_<basisname>, die Tabelle ST_<basisname>
	 */
	public Prepare_StandardTempTable(String nameBASE_OF_TABLE_AND_VIEW) {
		super();
		this.cNAMEBASE_OF_TABLE_AND_VIEW = nameBASE_OF_TABLE_AND_VIEW;
	}

	@Override
	public boolean CHECK_IF_MUST_BE_EXECUTED() throws myException {
		
		String cZahl1= bibDB.EinzelAbfrage("SELECT COUNT(*) FROM ST_"+cNAMEBASE_OF_TABLE_AND_VIEW).trim();
		
		if (cZahl1.startsWith("@") || S.isEmpty(cZahl1)) {
			throw new myException("Prepare_TempTables_Atom: CHECK_IF_MUST_BE_EXECUTED: Error querying ST_"+this.cNAMEBASE_OF_TABLE_AND_VIEW);
		}
		
		if (cZahl1.equals("0")) {
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public MyE2_MessageVector EXECUTED_BEFORE_REPORT() throws myException {
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		Vector<Integer> vCounter = new Vector<Integer>();
		vCounter.add(new Integer(0));
		
		
		String cSQL1="INSERT INTO ST_"+this.cNAMEBASE_OF_TABLE_AND_VIEW+" (SELECT * FROM S"+bibALL.get_ID_MANDANT()+"_"+this.cNAMEBASE_OF_TABLE_AND_VIEW+")";
		if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL1, true,vCounter)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen von: ",true,cSQL1,false)));
		} else {
			oMV.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Temporäre Tabelle <ST_"+this.cNAMEBASE_OF_TABLE_AND_VIEW+"> erfolgreich geschrieben ... Anzahl Sätze: ",true, 
									""+vCounter.get(0).intValue(),false)));
		}
		
		return oMV;
	}
	

}
