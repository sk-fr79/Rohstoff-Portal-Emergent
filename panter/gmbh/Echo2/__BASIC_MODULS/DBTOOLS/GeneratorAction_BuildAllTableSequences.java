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
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class GeneratorAction_BuildAllTableSequences extends XX_ActionAgent implements GeneratorAction__IF {

	private Vector<String>  v_meldungen_ok = new Vector<String>();
	private Vector<String>  v_meldungen_fehler = new Vector<String>();
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true);
		
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable,false);

		if (cTabellen==null || cTabellen.length==0)	{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
		} else {
			for (int k=0;k<cTabellen.length;k++) {
				Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(cTabellen[k][0], null);
				
				MyE2_MessageVector mv = oSeq.Build_New_SequenceBased_on_DatabaseQuery();
				
				for (MyE2_Message  m: mv) {
					if (m.get_iType()!=MyE2_Message.TYP_ALARM) {
						this.v_meldungen_ok.add(m.get_cMessage().CTrans());
					} else {
						this.v_meldungen_fehler.add(m.get_cMessage().CTrans());
					}
				}
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
		return new MyE2_String("Tabellen-Sequencer ...");
	}

	
}
