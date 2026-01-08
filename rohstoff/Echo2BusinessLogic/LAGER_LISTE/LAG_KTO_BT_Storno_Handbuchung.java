package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

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
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerHandler;


public class LAG_KTO_BT_Storno_Handbuchung extends MyE2_Button
{

	/**
	 * @author manfred
	 * @date   05.10.2011
	 */
	private static final long serialVersionUID = -6305134016037954078L;

	public LAG_KTO_BT_Storno_Handbuchung(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("storno.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.add_oActionAgent(new actionAgentAskForDelete(onavigationList,this));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_KTO_STORNO_HANDBUCHUNG"));
		this.setToolTipText(new MyString("Stornieren einer Korrekturbuchung").CTrans());
	}
	
	
	/**
	 * Eventhandler Storno
	 * @author manfred
	 * @date   08.02.2012
	 */
	private class actionAgentStornoHandbuchung extends XX_ActionAgent{

		E2_NavigationList m_navList = null;
		public actionAgentStornoHandbuchung(E2_NavigationList onavigationList) {
			m_navList = onavigationList;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (m_navList == null || m_navList.get_vSelectedIDs_Unformated().size() != 1){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur genau eine Korrekturbuchung storniert werden.")));
				return;
			}
			
			String idLagerKonto = m_navList.get_vSelectedIDs_Unformated().firstElement();
			RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO( idLagerKonto );	
			if(rec.get_BUCHUNG_HAND_cUF_NN("-").equals("-")){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können nur Korrektur- oder Umbuchungen storniert werden.")));
				return;
			}
			
			
			// TODO Auto-generated method stub
			LAG_LagerHandler oHandler = new LAG_LagerHandler();
			oHandler.storniereLagerbuchung(idLagerKonto, true);
			m_navList.RefreshList();
			
		}
		
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
			
			if (m_oNav == null || m_oNav.get_vSelectedIDs_Unformated().size() != 1){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur genau eine Korrekturbuchung storniert werden.")));
				return;
			}
			
			String idLagerKonto = m_oNav.get_vSelectedIDs_Unformated().firstElement();
			RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO( idLagerKonto );	
			if(rec.get_BUCHUNG_HAND_cUF_NN("-").equals("-")){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können nur Korrektur- oder Umbuchungen storniert werden.")));
				return;
			}
			
			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
					new MyE2_String("Sicherheitsabfrage"),
					new MyE2_String("Stornieren der Handbuchung?"),
					new MyE2_String(""),
					new MyE2_String("JA"),
					new MyE2_String("NEIN"),
					new Extent(300),
					new Extent(200)
					);
				oConfirm.set_oExtMINIMALWidth(new Extent(400));
				oConfirm.set_oExtMINIMALHeight(new Extent(200));
				
				oConfirm.set_ActionAgentForOK(new actionAgentStornoHandbuchung(this.m_oNav));
				oConfirm.show_POPUP_BOX();
			
		}
		
	}
	
	
	
	
	
}
