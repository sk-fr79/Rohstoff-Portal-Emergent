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
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class GeneratorAction_BuildAllTableViews extends XX_ActionAgent implements GeneratorAction__IF {

	private Vector<String>  v_meldungen_ok = new Vector<String>();
	private Vector<String>  v_meldungen_fehler = new Vector<String>();
	
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
		String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);

		String 		cAbfrageTable =  DB_META.get_TablesQuery(bibALL.get_DBKENNUNG(), bibE2.cTO(),true,true);
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);

		if (cTabellen==null || cTabellen.length==0) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
			return;
		}
		if (cMandanten == null || cMandanten.length==0)	{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
			return;
		}
		
		
		for (int i=0;i<cMandanten.length;i++) {
			for (int k=0;k<cTabellen.length;k++) {
				String cNamenView = "V" + cMandanten[i][0].trim() + "_" + cTabellen[k][0].substring(3);

				/*
				 * 2013-10-07: nur tabellen mit jt_ am anfang verarbeiten, JD_ und TT_ Tabellen auslassen
				 */
				if (!cTabellen[k][0].toUpperCase().startsWith("JT_")) 	{
					v_meldungen_ok.add("Mandant: "+cMandanten[i][0].trim()+" --> Keinen View erzeugt --> " + cTabellen[k][0] + " ist eine Definitionstabelle !");
				} else {
					
					String cSqlBaueNeuView = "CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM " + cTabellen[k][0] + " WHERE ID_MANDANT=" + cMandanten[i][0].trim();

					if (bibDB.ExecSQL(cSqlBaueNeuView,true))
						v_meldungen_ok.add("OK!     Neuen View " + cNamenView + " erfolgreich erstellt !");
					else
						v_meldungen_fehler.add("ERROR!  Neuen View " + cNamenView + " nicht erstellt !");
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
		return new MyE2_String("Tabellen-Views ...");
	}

}
