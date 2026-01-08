package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class GeneratorAction_BuildAllTableTriggers extends XX_ActionAgent implements GeneratorAction__IF {

	private Vector<String>  v_meldungen_ok = new Vector<String>();
	private Vector<String>  v_meldungen_fehler = new Vector<String>();
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {

		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true), false);

		Vector<String> vTablesWithoutTrigger = new Vector<String>();
		vTablesWithoutTrigger.add("JD_DB_LOG");
		vTablesWithoutTrigger.add("JD_LOGIN");
		vTablesWithoutTrigger.add("TT_SORT_TABLE");
		
		if (cTabellen==null || cTabellen.length==0) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
			return;
		}
		
		for (int k=0;k<cTabellen.length;k++) {
			
			if (!vTablesWithoutTrigger.contains(cTabellen[k][0]))	{

				PAIR<VEK<String>,VEK<String>> resultMessages = new TableUpdater().tableUpdate(cTabellen[k][0]);
				
				this.v_meldungen_ok.addAll(resultMessages.getVal1());
				this.v_meldungen_fehler.addAll(resultMessages.getVal2());
			}
		}
		
		MyE2_MessageVector MV_Infos = bibMSG.MV().get_InfoMessages();
		MyE2_MessageVector MV_Alarm = bibMSG.MV().get_AlarmMessages();
		MyE2_MessageVector MV_Warning = bibMSG.MV().get_WarnMessages();

		for (MyE2_Message m: MV_Infos) {
			this.v_meldungen_ok.add(m.get_cMessage().CTrans());
		}
		for (MyE2_Message m: MV_Warning) {
			this.v_meldungen_ok.add(m.get_cMessage().CTrans());
		}
		for (MyE2_Message m: MV_Alarm) {
			this.v_meldungen_fehler.add(m.get_cMessage().CTrans());
		}

		bibMSG.MV().clear();

	}



	@Override
	public Vector<String> get_v_meldungen_ok() {
		return v_meldungen_ok;
	}

	@Override
	public Vector<String> get_v_meldungen_fehler() {
		return v_meldungen_fehler;
	}

	@Override
	public MyE2_String get_description() {
		return new MyE2_String("Tabellen-Trigger ...");
	}

}
