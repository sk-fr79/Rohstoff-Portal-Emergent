package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_LIST_Selector_Main__orig extends E2_ListSelectorContainer
{
	 
	private static final String SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN = "SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN";


	private  E2_SelectionComponentsVector 	oSelVector_old = null;
	//2015-04-27: neuer selektionsvector
	private  E2_SelectionComponentsVector 	oSelVector = null;

	
	//ein umschalten zwischen alter und neuer selektion ist mit einem umschaltbutton moeglich, der zur jeweils anderen seite wechselt 
	private MyE2_Button                     oButtonSwitchToNew = new MyE2_Button(new MyE2_String("Selektor 2"), new MyE2_String("Schaltet auf die neue Selektoren-Darstellung um"), new actionToggleToNew());
	private MyE2_Button                     oButtonSwitchToOld = new MyE2_Button(new MyE2_String("Selektor 1"), new MyE2_String("Schaltet auf die klassische Selektoren-Darstellung um"), new actionToggleToOld());
	private String                          cSelectedSelektor = "1";    //standard - Version 1 
	
	
	public WF_HEAD_LIST_Selector_Main__orig(E2_NavigationList oNavList, String cMODULE_KENNER) throws myException
	{
		super();
		
		this.oSelVector_old = 	new WF_HEAD_LIST_SelectionComponentVector_Version1(oNavList,oButtonSwitchToNew, cMODULE_KENNER);
		this.oSelVector  	=	new WF_HEAD_LIST_SelectionComponentVector_Version2(oNavList,oButtonSwitchToOld, cMODULE_KENNER);

		//vorgabe
		this.set_TO_InnerComponent(this.oSelVector_old.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
		
		
		//alten status wieder einlesen
		try
		{
			String cOldStatus = (String)(new E2_UserSetting_SIMPLE(SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN)).get_Settings(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE);
			
			if (cOldStatus.equals("1"))
			{
				this.set_TO_InnerComponent(this.oSelVector_old.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
				this.cSelectedSelektor="1";
			} 
			else if (cOldStatus.equals("2"))
			{
				this.set_TO_InnerComponent(this.oSelVector.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
				this.cSelectedSelektor="2";
			}
			else
			{
				this.set_TO_InnerComponent(new MyE2_Column(), E2_INSETS.I_2_2_2_2);
			}
		}
		catch (Exception ex)
		{
			
		}
	}

	
	private class actionToggleToNew extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WF_HEAD_LIST_Selector_Main__orig.this.set_TO_InnerComponent(WF_HEAD_LIST_Selector_Main__orig.this.oSelVector.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
			WF_HEAD_LIST_Selector_Main__orig.this.cSelectedSelektor="2";
			WF_HEAD_LIST_Selector_Main__orig.this.oSelVector.doActionPassiv();
			
			//status abspeichern
			new E2_UserSetting_SIMPLE(SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN).STORE(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, "2");
			
		}
	}


	private class actionToggleToOld extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WF_HEAD_LIST_Selector_Main__orig.this.set_TO_InnerComponent(WF_HEAD_LIST_Selector_Main__orig.this.oSelVector_old.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
			WF_HEAD_LIST_Selector_Main__orig.this.cSelectedSelektor="1";
			WF_HEAD_LIST_Selector_Main__orig.this.oSelVector_old.doActionPassiv();
			
			//status abspeichern
			new E2_UserSetting_SIMPLE(SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN).STORE(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, "1");

		}
	}
	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		if (this.cSelectedSelektor.equals("1"))
		{
			return oSelVector_old;			
		}
		else
		{
			return oSelVector;			
		}
	}

	
}
