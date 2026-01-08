package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOGTRIG__CONST;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__BT_SCHREIBE_BASISTRIGGER extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;
	
	public LOGTRIG__BT_SCHREIBE_BASISTRIGGER(E2_NavigationList NaviList)
	{
		super(new MyE2_String("Basislogger neu schreiben"));
		
		this.oNaviList = NaviList;
		
		this.setToolTipText(new MyE2_String("Das Programm braucht für die Basisfunktionen einige Log-Trigger. Deren Tabelleinträge werden hier geschrieben").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vSQL = new Vector<String>();
			
			vSQL.add("DELETE FROM "+bibE2.cTO()+".JD_TRIGGER_DEF WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL("+RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT+",'N')='Y'");
			vSQL.addAll(LOGTRIG__CONST.get_StaticLogTriggersSQL());
			

			MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSQL, true);
			
			if (oMV.get_bHasAlarms() || oMV.get_bHasWarnings())
			{
				bibMSG.add_MESSAGE(oMV);
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Anzahl der neu geschriebenen/ersetzten Basis-Logtrigger ",true,""+(vSQL.size()-1),false)));
			}
			
			LOGTRIG__BT_SCHREIBE_BASISTRIGGER.this.oNaviList._REBUILD_COMPLETE_LIST(null);
		}
	}
	
}
