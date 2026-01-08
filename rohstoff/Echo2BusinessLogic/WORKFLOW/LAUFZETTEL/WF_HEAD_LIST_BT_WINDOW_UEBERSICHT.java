package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY_Settings;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_ROW_OBJECT;



public class WF_HEAD_LIST_BT_WINDOW_UEBERSICHT extends MyE2_Button
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290486144842118909L;
	
	private EnumPrintOptions m_PrintOptions = EnumPrintOptions.PRINT_SELECTED;
	

	public WF_HEAD_LIST_BT_WINDOW_UEBERSICHT(	E2_NavigationList onavigationList, 
									E2_BasicModuleContainer_MASK omaskContainer, 
									EnumPrintOptions printOptions
									)
	{
		super(E2_ResourceIcon.get_RI("info.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_PrintOptions = printOptions;
		
		this.add_oActionAgent(new printActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"UEBERSICHTSFENSTER_WF_HEAD"));
	
	}
	

	
	
/***
 * Eventhandler zum Drucken der Reports	
 * @author manfred
 * @date 04.12.2008
 */
private class printActionAgent extends XX_ActionAgent
{
	private E2_NavigationList oNavigationList = null;
	

	WF_LIST_EXPANDER_HIERARCHY_Settings oSetting = new WF_LIST_EXPANDER_HIERARCHY_Settings();
	
	/***
	 * Übernehmen der Parameter in den ActionAgent
	 * 
	 * @param oNaviList
	 ***/
	public printActionAgent(E2_NavigationList oNaviList)
	{
		oNavigationList = oNaviList;
	}
	
	
	
	
	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{
		Vector<String> vIDs =  new Vector<String>();
		

		
		
		switch (m_PrintOptions)
		{
			case PRINT_SELECTED:
				vIDs.addAll(oNavigationList.get_vSelectedIDs_Unformated());
				break;
			case PRINT_CURRENT_PAGE:
				vIDs.addAll(oNavigationList.get_AllVisibleIDs_Unformated());
				break;

			default:
				vIDs.addAll(oNavigationList.get_vectorSegmentation());
				break;
		}
		
				
		// prüfen, welche Einträge man drucken soll.. (SqlStatement generieren abhängig von den Schaltern)
		E2_RecursiveSearchParent_BasicModuleContainer oSearch = 
			new E2_RecursiveSearchParent_BasicModuleContainer(this.oNavigationList);
		
		E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();
		
		E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(
				oContainerList, bibALL.get_Vector(WF_CheckBoxOption.class.getName()), null);
		
		Vector<Component> vResult = oSearchComps.get_vAllComponents();
		for (Component cb : vResult)
		{
			WF_CheckBoxOption cbOptions = (WF_CheckBoxOption) cb;
			oSetting.setValue(cbOptions);
		}
		

		// suchen nach der User-Selektion WF_HEAD_LIST_Selection_User_DropDown
		oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_HEAD_LIST_Selection_User_DropDown.class.getName()), null);
		vResult = oSearchComps.get_vAllComponents();
		if (vResult.size() > 0){
					WF_HEAD_LIST_Selection_User_DropDown oSelect = (WF_HEAD_LIST_Selection_User_DropDown)vResult.get(0);
					String sID = oSelect.get_ActualWert();
					if (!bibALL.isEmpty(sID)){
						oSetting.set_idOwnUser(sID);
					}
		}
		
		
		if (vIDs.size() > 0){
			// das Fenster aufrufen
			new WF_HEAD_WINDOW_UEBERSICHT(vIDs,oSetting);
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Es muss mindestens ein Laufzettel ausgewählt sein.")));
		}
			
	}
	
	}
}
