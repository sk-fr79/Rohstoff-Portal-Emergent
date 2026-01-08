package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;

public class INSTAT_LIST_BT_NEW extends MyE2_Button
{
	
	private INSTAT_LIST_Selector 	m_oSelector = null;
	private E2_NavigationList	 	m_oNaviList = null;
	private INSTAT_LIST_BedienPanel m_oPanel = null;
	private ENUM_IntrastatType 		m_oType = null;
	
	
	public INSTAT_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer,INSTAT_LIST_Selector oSelector,INSTAT_LIST_BedienPanel oPanel, ENUM_IntrastatType type)
	{
		super(new MyE2_String("Werte ermitteln"), E2_ResourceIcon.get_RI("reload.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_oSelector = oSelector;
		m_oNaviList = onavigationList;
		m_oPanel = oPanel;
		m_oType = type;
		if (m_oType == ENUM_IntrastatType.ENUM_Import){
			this.set_Text("Einfuhr-Werte ermitteln");
		}
		if (m_oType == ENUM_IntrastatType.ENUM_Export){
			this.set_Text("Ausfuhr-Werte ermitteln");
		}
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_INSTAT"));

	}
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		private INSTAT_LIST_BT_NEW oThis = INSTAT_LIST_BT_NEW.this;
		
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			String sMonat = oThis.m_oSelector.get_oSelMonat().get_ActualWert();
			String sJahr = oThis.m_oSelector.get_oSelJahr().get_ActualWert();
			INSTAT_Handler o =  new INSTAT_Handler();
			int jahr = 0;
			int monat = 0;
			
			try {
				jahr = Integer.parseInt(sJahr);
			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein gültiges Jahr ausgewählt werden!")));
			}
			
			try { 
				monat = Integer.parseInt(sMonat);
			} catch (Exception e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein gültiger Monat ausgewählt werden!")));
			}
			
						
			if (!bibMSG.get_bHasAlarms()){
			
				if (!oThis.m_oPanel.canOverrideExistingEntries()){
				
					if (o.ListExists(jahr, monat, oThis.m_oType))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es sind schon Einträge in dem Monat vorhanden.")));
					}
				}
			}
			
			if (!bibMSG.get_bHasAlarms()){
				o.cleanIntrastatRecords(jahr, monat, oThis.m_oType, false);
				int newRecs = o.createIntrastatRecords(jahr, monat, oThis.m_oType);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Es wurden " + String.valueOf(newRecs) + " neue Einträge ermittelt")));

				oThis.m_oNaviList.RefreshList();
				
			}
		}
	}

	
}
