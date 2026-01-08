package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_SESSION_SIMPLE_SAVER;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;
import rohstoff.Echo2BusinessLogic.INTRASTAT.XML.Instat_XML_Handler;


public class INSTAT_LIST_BT_DOWNLOAD_AUSFUHR extends MyE2_Button
{

	private INSTAT_LIST_Selector m_oSelector = null;
	private E2_NavigationList m_oNav = null;

	public INSTAT_LIST_BT_DOWNLOAD_AUSFUHR(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer,INSTAT_LIST_Selector oSelector)
	{
		super(new MyE2_String("Ausfuhr-Liste"), E2_ResourceIcon.get_RI("save.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_oSelector = oSelector;
		this.m_oNav = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ANZEIGE_INSTAT"));
	}
	
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		private INSTAT_LIST_BT_DOWNLOAD_AUSFUHR oThis = INSTAT_LIST_BT_DOWNLOAD_AUSFUHR.this;
		
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
			String sZaehler = oThis.m_oSelector.get_oSelZaehler().get_ActualWert();
			
			
			int jahr = 0;
			int monat = 0;
			int zaehler = 0;
			
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
			
			try {
				zaehler = Integer.parseInt(sZaehler);
			} catch (NumberFormatException e) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist kein gültiger Meldungszähler angegeben worden")));
			}
			
			if (!bibMSG.get_bHasAlarms()){
				
				if (ENUM_MANDANT_DECISION.INTRASTAT_XML_FORMAT.is_YES()) {
					Instat_XML_Handler o =  new Instat_XML_Handler();
					o.exportIntrastatRecordsToXML(jahr, monat,zaehler, ENUM_IntrastatType.ENUM_Export);
					
				} else {
					INSTAT_Handler o =  new INSTAT_Handler();
					o.exportIntrastatRecords(jahr, monat,zaehler, ENUM_IntrastatType.ENUM_Export);
				}
			}
			
			oThis.m_oNav.RefreshList();
			oThis.m_oSelector.refresh_CMBZaehler();
			
		}

		
		
	}

	
}
