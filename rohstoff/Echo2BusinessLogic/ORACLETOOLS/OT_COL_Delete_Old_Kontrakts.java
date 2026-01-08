package rohstoff.Echo2BusinessLogic.ORACLETOOLS;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class OT_COL_Delete_Old_Kontrakts extends MyE2_Column
{
	private 	MyE2_Button 			oButtonDel = new MyE2_Button(new MyE2_String("LÖSCHEN ...."));
	private 	OT_BasicContainer 		oBasicContainer = null;
	private 	MyDBToolBox 			oDBORA = null; 
	
	
	public OT_COL_Delete_Old_Kontrakts(OT_BasicContainer BasicContainer)
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		this.oBasicContainer = 	BasicContainer;
		this.oDBORA = 			this.oBasicContainer.get_oDBORA();
		
		this.add(
				new MyE2_Label(new MyE2_String("Löscht alle Einträge aus der Oracle-Tabelle JAVA_KONTRAKT mit <ERLEDIGT=2> !!!"),
							   MyE2_Label.STYLE_TITEL_BIG()),
								E2_INSETS.I_10_10_10_10);
		
		this.oButtonDel.add_oActionAgent(new actionAgentDelete());
		this.oButtonDel.add_GlobalValidator(new E2_ButtonAUTHValidator(this.oBasicContainer.get_MODUL_IDENTIFIER(),"DELETE_UNUSED_KONTRAKTS"));
		
		this.add(new E2_ComponentGroupHorizontal(0,this.oButtonDel,E2_INSETS.I_0_2_5_2),E2_INSETS.I_10_10_10_10);
	}

	
	private class actionAgentDelete extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			OT_COL_Delete_Old_Kontrakts oThis = OT_COL_Delete_Old_Kontrakts.this;
			
			String cSQL = "SELECT COUNT(*) FROM "+bibALL.get_oracle_tableowner()+".JAVA_KONTRAKT "+
			" WHERE ERLEDIGT='2'";
			
			String cAnzahl = oThis.oDBORA.EinzelAbfrage(cSQL);
			
			
			if (bibALL.isInteger(cAnzahl))
			{
				
				E2_ConfirmBasicModuleContainer oConf = new E2_ConfirmBasicModuleContainer(
															new MyE2_String("Sicherheitsabfrage :"), 
															new MyE2_String(cAnzahl+ " Kontrakteinträge löschen ?"),
															new MyE2_String(""),
															new MyE2_String("JA, Löschen !"),
															new MyE2_String("NEIN - Abbruch !"),
															new Extent(350),new Extent(350));
				
				// oConf.set_SplitPixelsFromBottom(100);
				oConf.set_ActionAgentForOK(new actionAgentDeleteOK());
				oConf.show_POPUP_BOX();
				
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der zu löschenden Kontrakte !"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cSQL,false)));
			}
			
			
			
		}
	}
	
	private class actionAgentDeleteOK extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			OT_COL_Delete_Old_Kontrakts oThis = OT_COL_Delete_Old_Kontrakts.this;

			String cSQL = "DELETE FROM "+bibALL.get_oracle_tableowner()+".JAVA_KONTRAKT "+
							" WHERE ERLEDIGT='2'";
			
			if (oThis.oDBORA.ExecSQL(cSQL,true))
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Erledigt."));
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Löschen der Kontrakte !"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cSQL,false)));
			}
		}
	}
	

	
	
	
	
}
