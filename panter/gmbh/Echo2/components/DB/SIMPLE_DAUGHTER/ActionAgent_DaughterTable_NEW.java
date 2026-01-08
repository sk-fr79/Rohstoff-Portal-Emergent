package panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public class ActionAgent_DaughterTable_NEW extends XX_ActionAgent
{
	private MyE2_DBC_DaughterTable 	oDaughterTable = null;
	private boolean 				bUseMapSettingAgentBeforeNew = false;
	
	
	public ActionAgent_DaughterTable_NEW(MyE2_DBC_DaughterTable odaughtertable, boolean UseMapSettingAgentBeforeNew)
	{
		super();
		oDaughterTable = odaughtertable;
		this.bUseMapSettingAgentBeforeNew = UseMapSettingAgentBeforeNew;
	}



	public void executeAgentCode(ExecINFO oExecInfo)
	{
		try
		{
			oDaughterTable.get_oNavigationList().add_Row_MAP_FOR_NEW_INPUT(true,this.bUseMapSettingAgentBeforeNew, true);
			oDaughterTable.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Anhaengen einer neuen Eingabezeile !"));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
	}

}
