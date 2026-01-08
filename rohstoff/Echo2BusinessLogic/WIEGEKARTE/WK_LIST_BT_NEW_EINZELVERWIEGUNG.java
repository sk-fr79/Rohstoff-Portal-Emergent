package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WK_LIST_BT_NEW_EINZELVERWIEGUNG extends MyE2_Button
{

	private E2_NavigationList m_navigationList = null;
	
	public WK_LIST_BT_NEW_EINZELVERWIEGUNG(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(new MyE2_String("Einzelverwiegung"), E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_WK_EINZEL"));
		this.setToolTipText(new MyE2_String("Erzeugt eine Einzelverwiegung zu einer Gesamtverwiegung. Es muss der Wiegeschein der Gesamtverwiegung ausgewählt sein.").CTrans());
		
		m_navigationList = onavigationList;
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// TODO Auto-generated method stub
			//
			// Zustände der drüberliegenden Checkboxen prüfen, d.h. abfragen:
			// 
			// recursive nach dem E2_BasicModuleContainer suchen
			E2_RecursiveSearchParent_BasicModuleContainer oSearch = new E2_RecursiveSearchParent_BasicModuleContainer(m_navigationList);
			E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();

			String sIDLager = null;
			E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WK_SelectField_Lager.class.getName()), null);
			Vector<Component> vResult = oSearchComps.get_vAllComponents();
			if (vResult.size()== 1)
			{
				Component o = vResult.get(0);
				WK_SelectField_Lager selLager = (WK_SelectField_Lager) o;
				sIDLager = selLager.get_ActualWert();
			}
			
			String sIDStandort = null;
			E2_RecursiveSearch_Component oSearchCompStandort = new E2_RecursiveSearch_Component( oContainerList, bibALL.get_Vector(WK_SelectField_Standort.class.getName()), null);
			Vector<Component> vResultStandort = oSearchCompStandort.get_vAllComponents();
			if (vResultStandort.size()== 1)
			{
				Component o = vResultStandort.get(0);
				WK_SelectField_Standort selStandort = (WK_SelectField_Standort) o;
				sIDStandort = selStandort.get_ActualWert();
			}
			
			
			if (bibALL.isEmpty(sIDLager) || bibALL.isEmpty(sIDStandort)){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Für die Erfassung einer Wiegekarte muss ein Lager und Standort ausgewählt sein!")));
			} else {
				Vector<String> vIDs = m_navigationList.get_vSelectedIDs_Unformated();
				
				if (vIDs.size() <= 0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss eine Wiegekarte ausgewählt sein!")));
					return;
				}
				String gesVerwiegung = m_navigationList.get_ComponentMAP(vIDs.firstElement()).get_oInternalSQLResultMAP().get_UnFormatedValue("IST_GESAMTVERWIEGUNG");
				
				if (bibALL.isEmpty(gesVerwiegung) || !gesVerwiegung.equals("Y")){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Einzelverwiegungen sind nur für Gesamtverwiegungen erlaubt.")));
					return;
				}
				
				if(vIDs.size() == 1){
//					WK_WiegekartenHandler oWKHandler = new WK_WiegekartenHandler(vIDs.firstElement());
//					oWKHandler.saveWiegekarteAsCopy(false, true);
					
//					new WK_Erfassung_Waegung(m_navigationList, oWKHandler.get_ID_Wiegekarte(),sIDLager,true,true);
					new WK_Erfassung_Waegung(m_navigationList, vIDs.firstElement(),sIDLager,sIDStandort,true,true);
					m_navigationList.RefreshList();
				}
			}
		}
	}
	
}
