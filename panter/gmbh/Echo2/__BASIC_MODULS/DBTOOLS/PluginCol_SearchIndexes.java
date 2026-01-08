package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_SPEED_INDEX;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class PluginCol_SearchIndexes extends Basic_PluginColumn
{
	
	public PluginCol_SearchIndexes(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		MyE2_Button oButtonBuildTableTriggers = new MyE2_Button(new MyE2_String("NEUAUFBAU Such-Indizes"));
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
			
			String cQueryAllIndexes = "SELECT INDEX_NAME FROM ALL_INDEXES WHERE INDEX_NAME LIKE 'SPEED_IND_%'";
			
			String[][] cIndexes = bibDB.EinzelAbfrageInArray(cQueryAllIndexes);
			
			if (cIndexes == null)
			{
				throw new myException("Err:Cannot read existing indexes ...");
			}
			
			
			for (int i=0;i<cIndexes.length;i++)
			{
				String cMessage = "Index "+cIndexes[i][0]+" deleting .... ";
				
				if (bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP INDEX "+cIndexes[i][0], true))
				{
					cMessage += "ok\n";
				}
				else
				{
					cMessage += "ERROR !!!\n";
				}
				cProtokoll.append(cMessage);
			}
			
			
			//zuerst alle indices abfragen
			RECLIST_SPEED_INDEX recListIndex = new RECLIST_SPEED_INDEX("SELECT * FROM "+bibE2.cTO()+".JD_SPEED_INDEX ORDER BY TABLENAME");
			
			for (int i=0;i<recListIndex.get_vKeyValues().size();i++)
			{
			//	"CREATE UNIQUE INDEX JD_USERSETTINGS_IDX1 ON JD_USERSETTINGS (ID_USER  ASC,HASHVALUE_SESSION ASC, MODUL_KENNER ASC);";
				
				String cSQL = "CREATE INDEX "+
							" SPEED_IND_"+recListIndex.get(i).get_ID_SPEED_INDEX_cUF()+" ON "+recListIndex.get(i).get_TABLENAME_cUF()+" ("+
							recListIndex.get(i).get_FIELDLIST_cUF_NN("")+(recListIndex.get(i).is_DESC_OR_ASC_YES()?" DESC ":" ASC ")+") ";
				
				if (bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQL, true))
				{
					cProtokoll.append("Index "+recListIndex.get(i).get_TABLENAME_cUF()+"  .... "+recListIndex.get(i).get_FIELDLIST_cUF_NN("")+"  done !\n");
				}
				else
				{
					cProtokoll.append("Index "+recListIndex.get(i).get_TABLENAME_cUF()+"  .... "+recListIndex.get(i).get_FIELDLIST_cUF_NN("")+"  failed\n");
					cProtokoll.append("      .........SQL: "+cSQL+"\n");
				}
			}

			PluginCol_SearchIndexes.this.get_TextArea4Output().setText(cProtokoll.toString());
			
		}
	}
}
		
