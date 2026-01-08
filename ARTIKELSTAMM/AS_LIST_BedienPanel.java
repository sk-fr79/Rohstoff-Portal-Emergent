package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_Generic;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.GROUP_COLLECTOR.POPUP_GROUP_COLLECTORS;

public class AS_LIST_BedienPanel extends MyE2_Grid
{

	public AS_LIST_BedienPanel(		AS_BasicModulContainerLIST 	oModulContainerList, 
									AS_BasicModuleContainerMASK	oModulContainerMASK, 
									E2_NavigationList 			oNaviList) throws myException
	{
		super(11,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),E2_INSETS.I_0_0_10_0);
		
		// jetzt die buttons fuer die maske
		this.add(new AS_BT_LIST_VIEW_ARTIKEL(oNaviList,oModulContainerMASK),E2_INSETS.I_0_0_5_0);
		this.add(new AS_BT_LIST_NEW_ARTIKEL(oNaviList,oModulContainerMASK),E2_INSETS.I_0_0_5_0);
		this.add(new AS_BT_LIST_EDIT_ARTIKEL(oNaviList,oModulContainerMASK),E2_INSETS.I_0_0_5_0);
		this.add(new AS_BT_LIST_DELETE_ARTIKEL(oNaviList),E2_INSETS.I_0_0_10_0);
		
//		//TEST
//		this.add(new Test_CalcButton(oNaviList),E2_INSETS.I_0_0_10_0);
//		//TEST
		
		//NEU_09
		this.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oModulContainerList.get_oNaviList(),oModulContainerList.get_MODUL_IDENTIFIER()),E2_INSETS.I_0_0_10_0);
		this.add(new REM_bt_New_Generic(oModulContainerList.get_oNaviList(), MODUL.NAME_MODUL_ARTIKELSTAMM_LISTE));
		
		//this.add( new AS_BT_LIST_EXCEL_LIST(oNaviList),E2_INSETS.I_0_0_10_0);
		
		this.add( new AS_LIST_bt_popup_export(oNaviList),E2_INSETS.I_0_0_10_0);
		
		this.add(new REP__POPUP_Button(oModulContainerList.get_MODUL_IDENTIFIER(),oNaviList), E2_INSETS.I_0_0_10_0);

		this.add(new POPUP_GROUP_COLLECTORS(oModulContainerList.get_MODUL_IDENTIFIER(),oNaviList));
		
		this.add(new AS_LIST_DATASEARCH(oNaviList,oModulContainerList.get_MODUL_IDENTIFIER()));
			
	}

	
	
	
	
}
