package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;


public class CAL_TerminButton_EDIT extends MyE2_Button 
{
	private CAL_TerminDataRecordHashMap   	hmTerminQuery = null;
	private CAL_GridWithDayButtons 					oColumnDay = null;
	
	public CAL_TerminButton_EDIT(CAL_TerminDataRecordHashMap   drhmTerminQuery, CAL_GridWithDayButtons ColumnDay) throws myException
	{
		super("");
		this.hmTerminQuery = drhmTerminQuery;
		this.oColumnDay = ColumnDay;
		
		this.set_Text(this.hmTerminQuery.get_UnFormatedValue("KURZTEXT")+"  ("+this.hmTerminQuery.get_UnFormatedValue("ZEIT_VON")+" - "+this.hmTerminQuery.get_UnFormatedValue("ZEIT_BIS")+")");
		
		this.add_oActionAgent(new actionEditTermin());
	}

	public CAL_TerminDataRecordHashMap get_hmTerminQuery() 
	{
		return hmTerminQuery;
	}

	
	
	 private class actionEditTermin extends XX_ActionAgent
	 {
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			CAL_TerminButton_EDIT oThis = CAL_TerminButton_EDIT.this;
			CAL_ModuleContainer_MASK oContainer = null;
			try
			{
				oContainer = 
					new CAL_ModuleContainer_MASK(E2_ComponentMAP.STATUS_EDIT,oThis.oColumnDay.get_oCalendarBasicRow());
		
				E2_vCombinedComponentMAPs vCombinedComponentMAPs = oContainer.get_vCombinedComponentMAPs();

				// id_user festlegen
				((SQLFieldForRestrictTableRange)((E2_ComponentMAP)vCombinedComponentMAPs.get(1)).get_oSQLFieldMAP().get_("ID_USER")).set_cRestrictionValue_IN_DB_FORMAT(bibALL.get_ID_USER());
				
				oContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oThis.hmTerminQuery.get_cID_TERMIN());

				oContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyColumnDay:actionNewTermin:Error: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				if (oContainer != null)
					oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
		 
	 }

	
	
	
	
}
