package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class PluginCol_TriggerBuilder extends Basic_PluginColumn
{
	
//	private String 					cTriggerStatement = 	"";
//	private WindowForServerPush 	oWindowMSG = 			new WindowForServerPush(100);

	public PluginCol_TriggerBuilder(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		MyE2_Button oButtonBuildTableTriggers = new MyE2_Button(new MyE2_String("Baue Tabellen-Trigger neu"));
		oButtonBuildTableTriggers.add_oActionAgent(new actionAgentBuildTableTriggers());
		
		//einzelaufbau
		PopDown_AlleTabellen  oPop = new PopDown_AlleTabellen(new actionAgentBuildTableSingleTrigger()) ;
		
		// column fuer das tab-pane aufbauen
		this.add(new E2_ComponentGroupHorizontal(0,oButtonBuildTableTriggers,oPop,E2_INSETS.I_0_0_10_0),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);
	}

	

	
	private class actionAgentBuildTableTriggers extends XX_ActionAgent {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Aufbau der Trigger läuft ..."),true,true,false,1000)	{
				
				@Override
				public void Run_Loop() throws myException			{
					
					String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true);
					
					String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);

					Vector<String> vTablesWithoutTrigger = new Vector<String>();
					vTablesWithoutTrigger.add("JD_DB_LOG");
					vTablesWithoutTrigger.add("JD_LOGIN");
					vTablesWithoutTrigger.add("TT_SORT_TABLE");
					vTablesWithoutTrigger.add("JT_TRIGGER_LOG");
					
					if (cTabellen==null || cTabellen.length==0) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
						return;
					}
					
					VEK<String> messages = new VEK<>();
					
					for (int k=0;k<cTabellen.length;k++) {
						
						if (!vTablesWithoutTrigger.contains(cTabellen[k][0]))	{

							
							messages._a("");
							messages._a("-------------------------------");
							messages._a("Table: "+cTabellen[k][0]);
							messages._a("-------------------------------");
							PAIR<VEK<String>,VEK<String>> results = new TableUpdater().tableUpdate(cTabellen[k][0]);
							messages._a(results.getVal1())._a(results.getVal2());

							//fortschrittsmeldungen
							this.get_oGridBaseForMessages().removeAll();
							this.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(cTabellen[k][0]+"   ("+k+"/"+cTabellen.length+")",false)));
						}
					}
					
					PluginCol_TriggerBuilder oThis = PluginCol_TriggerBuilder.this;
					
					StringBuffer textBlock =  new StringBuffer(oThis.get_TextArea4Output().getText()+"\n");
					messages.stream().forEach(e->{textBlock.append(e+"\n");});
					
					oThis.get_TextArea4Output().setText(textBlock.toString());
					
				}  //run_loop
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}
			};
		}
	}
	
	
	
	
	
	private class actionAgentBuildTableSingleTrigger extends XX_ActionAgent {
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
			MyE2_Button oButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

			String cTabelle = oButton.EXT().get_C_MERKMAL();

			Vector<String> vTablesWithoutTrigger = new Vector<String>();
			vTablesWithoutTrigger.add("JD_DB_LOG");
			vTablesWithoutTrigger.add("JD_LOGIN");
			vTablesWithoutTrigger.add("TT_SORT_TABLE");
					
			if (!vTablesWithoutTrigger.contains(cTabelle)) {
				PAIR<VEK<String>,VEK<String>> results = new TableUpdater().tableUpdate(cTabelle);
				results.getVal1().stream().forEach(e->{bibMSG.MV()._addInfo(e);});
				results.getVal2().stream().forEach(e->{bibMSG.MV()._addInfo(e);});
			}
				
		} 
	}
}
	
	

		
