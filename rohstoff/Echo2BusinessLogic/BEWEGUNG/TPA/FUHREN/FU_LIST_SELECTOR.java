package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

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
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN.FU_SelectionComponentVector_Version1;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN.FU_SelectionComponentVector_Version2;

public class FU_LIST_SELECTOR extends E2_ListSelectorContainer
{
	 
	private  E2_SelectionComponentsVector 	oSelVector_old = null;
	
	
	//2011-08-16: neuer selektionsvector
	private  E2_SelectionComponentsVector 	oSelVector = null;


	//ein umschalten zwischen alter und neuer selektion ist mit einem umschaltbutton moeglich, der zur jeweils anderen seite wechselt 
	private MyE2_Button                     oButtonSwitchToNew = new MyE2_Button(new MyE2_String("Selektor 2"), new MyE2_String("Schaltet auf die neue Selektoren-Darstellung um"), new actionToggleToNew());
	private MyE2_Button                     oButtonSwitchToOld = new MyE2_Button(new MyE2_String("Selektor 1"), new MyE2_String("Schaltet auf die klassische Selektoren-Darstellung um"), new actionToggleToOld());
	
	private String                          cSelectedSelektor = "1";    //standard - Version 1 
	
	public FU_LIST_SELECTOR(E2_NavigationList oNavList, String cMODULE_KENNER) throws myException
	{
		super();
		
		this.oSelVector_old = 	new FU_SelectionComponentVector_Version1(oNavList,oButtonSwitchToNew, cMODULE_KENNER);
		this.oSelVector  	=	new FU_SelectionComponentVector_Version2(oNavList,oButtonSwitchToOld, cMODULE_KENNER);

		//vorgabe
		this.set_TO_InnerComponent(this.oSelVector_old.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
		
		
		//alten status wieder einlesen
		try
		{
			String cOldStatus = (String)(new E2_UserSetting_SIMPLE("SESSION_HASH_FU_LIST_SELEKTOR_USED_SELECTOR_VERSION")).get_Settings(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER);
			
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
			FU_LIST_SELECTOR.this.set_TO_InnerComponent(FU_LIST_SELECTOR.this.oSelVector.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
			FU_LIST_SELECTOR.this.cSelectedSelektor="2";
			FU_LIST_SELECTOR.this.oSelVector.doActionPassiv();
			
			//status abspeichern
			new E2_UserSetting_SIMPLE("SESSION_HASH_FU_LIST_SELEKTOR_USED_SELECTOR_VERSION").STORE(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "2");
			
		}
	}


	private class actionToggleToOld extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_SELECTOR.this.set_TO_InnerComponent(FU_LIST_SELECTOR.this.oSelVector_old.get_oSelComponent(), E2_INSETS.I_2_2_2_2);
			FU_LIST_SELECTOR.this.cSelectedSelektor="1";
			FU_LIST_SELECTOR.this.oSelVector_old.doActionPassiv();
			
			//status abspeichern
			new E2_UserSetting_SIMPLE("SESSION_HASH_FU_LIST_SELEKTOR_USED_SELECTOR_VERSION").STORE(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "1");

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
