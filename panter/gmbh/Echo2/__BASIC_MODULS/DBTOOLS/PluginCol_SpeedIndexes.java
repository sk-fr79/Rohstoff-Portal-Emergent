package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class PluginCol_SpeedIndexes extends Basic_PluginColumn
{
	
	public PluginCol_SpeedIndexes(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		MyE2_Button oButtonBuildTableTriggers = new MyE2_Button(new MyE2_String("NEUAUFBAU aller INDIZES"));
		oButtonBuildTableTriggers.add_oActionAgent(new actionAgentBuildIndizes());
		
		// column fuer das tab-pane aufbauen
		this.add(new E2_ComponentGroupHorizontal(0,oButtonBuildTableTriggers,new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);

	}

	

	
	private class actionAgentBuildIndizes extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			StringBuffer cProtokoll = new StringBuffer();
			
			//zuerst alle indices abfragen
			String[][] cIndizes = bibDB.EinzelAbfrageInArray("select index_name from all_indexes where owner = "+bibALL.MakeSql(bibE2.cTO()));
			
			if (cIndizes==null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Abfragen der Indizes !!"));
				return;
			}
			
			for (int i=0;i<cIndizes.length;i++)
			{
				if (bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"ALTER INDEX "+cIndizes[i][0]+" REBUILD", true))
				{
					cProtokoll.append(cIndizes[i][0]+" rebuild is done !!\n");
				}
				else
				{
					cProtokoll.append(cIndizes[i][0]+" rebuild ERROR !!\n");
				}
			}

			PluginCol_SpeedIndexes.this.get_TextArea4Output().setText(cProtokoll.toString());
			
		}
	}
}
		
