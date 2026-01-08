package rohstoff.Echo2BusinessLogic.INTRASTAT;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;

public class INSTAT_LIST_BT_DELETE extends MyE2_Button
{

	private INSTAT_LIST_Selector 	m_oSelector = null;
	private E2_NavigationList	 	m_oNaviList = null;
	private INSTAT_LIST_BedienPanel m_oPanel = null;
	private ENUM_IntrastatType 		m_oType = null;
	
	public INSTAT_LIST_BT_DELETE(E2_NavigationList onavigationList, 
			E2_BasicModuleContainer_MASK omaskContainer,
			INSTAT_LIST_Selector oSelector,
			INSTAT_LIST_BedienPanel oPanel, ENUM_IntrastatType type) throws myException
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
	
		m_oSelector = oSelector;
		m_oNaviList = onavigationList;
		m_oPanel = oPanel;
		m_oType = type;
		
		this.add_oActionAgent(new actionAgentAskForDelete(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_INSTAT"));
	}
	

	/**
	 * Bestätigungsdialogbox speichern ja/nein
	 * @author manfred
	 *
	 */
	private class actionAgentAskForDelete extends XX_ActionAgent{
		
		private E2_NavigationList m_oNav = null;
		private MyE2_Button 	  m_oBut = null;
		
		public actionAgentAskForDelete(E2_NavigationList onavigationList,  MyE2_Button oownButton) {
			super();
			m_oNav = onavigationList;
			m_oBut = oownButton;

		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
					new MyE2_String("Sicherheitsabfrage"),
					new MyE2_String("Löschen der Intrastat-Einträge?"),
					new MyE2_String(""),
					new MyE2_String("JA"),
					new MyE2_String("NEIN"),
					new Extent(300),
					new Extent(200)
					);
				oConfirm.set_oExtMINIMALWidth(new Extent(400));
				oConfirm.set_oExtMINIMALHeight(new Extent(200));
				
				oConfirm.set_ActionAgentForOK(new actionAgentDelete(this.m_oNav, this.m_oBut));
				oConfirm.show_POPUP_BOX();
			
		}
		
	}
	
	
	
	/**
	 * löscht die Daten aus der DB
	 * @author manfred
	 *
	 */
	private class actionAgentDelete extends XX_ActionAgent
	{
		private INSTAT_LIST_BT_DELETE oThis = INSTAT_LIST_BT_DELETE.this;
		private E2_NavigationList m_oNav = null;
		private MyE2_Button 	  m_oBut = null;

		public actionAgentDelete(E2_NavigationList onavigationList,  MyE2_Button oownButton) throws myException
		{
			super();
			this.m_oNav = onavigationList;
			this.m_oBut = oownButton;
		}
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
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
			
			
			INSTAT_Handler oHandler = new INSTAT_Handler();
			// löschen der Einträge
			oHandler.deleteIntrastatRecords(jahr, monat, m_oNav.get_vSelectedIDs_Unformated() );
			
			m_oNav.RefreshList();

		}

	}
	


	
	
}
