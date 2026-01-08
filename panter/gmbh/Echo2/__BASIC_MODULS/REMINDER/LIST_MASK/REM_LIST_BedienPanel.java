
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;




public class REM_LIST_BedienPanel extends MyE2_Column {

	private REM_LIST_Selector reminder_def_LIST_Selector = null;

	private E2_ButtonToSelectVisibleListColumns_and_other_settings 	oButtonToSelectVisibleListColumns_and_other_settings = null;
	private REM_LIST_bt_ListToMask									oButtonListToMaskEdit = null;
	private REM_LIST_bt_ListToMask									oButtonListToMaskView = null;
	private REM_LIST_bt_New											oButtonNew = null;			
	private REM_LIST_DATASEARCH										oButtonDatasearch = null;
	private E2_ButtonUpDown_NavigationList_to_Archiv				oButtonNavigationList_to_Archiv = null;
	
	public REM_LIST_BedienPanel(REM__IF_getTableAndID list_container, E2_NavigationList oNaviList) throws myException {
		super(MyE2_Column.STYLE_NO_BORDER());

		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());

		this.reminder_def_LIST_Selector = new REM_LIST_Selector(oNaviList);

		oButtonToSelectVisibleListColumns_and_other_settings =  new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList);
		oButtonListToMaskEdit = new REM_LIST_bt_ListToMask(list_container, true, oNaviList);
		oButtonListToMaskView = new REM_LIST_bt_ListToMask(list_container, false, oNaviList);
		oButtonNew = new REM_LIST_bt_New(list_container, oNaviList);
		oButtonDatasearch = new REM_LIST_DATASEARCH(oNaviList);
		oButtonNavigationList_to_Archiv = new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList, E2_MODULNAME_ENUM.MODUL.REMINDER_DEF_LIST.toString());
		
		
		Insets oInsets = new Insets(0, 0, 0, 5);

		this.add(reminder_def_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);

		oRowForComponents.add(oButtonToSelectVisibleListColumns_and_other_settings, new Insets(2, 2, 20, 2));
		oRowForComponents.add(oButtonListToMaskEdit, new Insets(2, 2, 20, 2));
		oRowForComponents.add(oButtonListToMaskView, new Insets(2, 2, 20, 2));
		oRowForComponents.add(oButtonNew, E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(oButtonNavigationList_to_Archiv, new Insets(20, 2, 2, 2));
		
//		oRowForComponents.add(new REM_LIST_bt_multiDelete(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(oButtonDatasearch, new Insets(20, 2, 2, 2));

		// im Debug-Mode den Button zum manuellen verschicken der Reminder
		// anzeigen
		if (bibALL.get_bDebugMode()) {
			oRowForComponents.add(new REM_ButtonTest(), E2_INSETS.I_10_2_2_2);
		}

	}

	public REM_LIST_Selector get_list_Selector() {

		return reminder_def_LIST_Selector;
	}

	
	public REM_LIST_Selector getReminder_def_LIST_Selector() {
		return reminder_def_LIST_Selector;
	}

	
	
	public E2_ButtonToSelectVisibleListColumns_and_other_settings getoButtonToSelectVisibleListColumns_and_other_settings() {
		return oButtonToSelectVisibleListColumns_and_other_settings;
	}

	public REM_LIST_bt_ListToMask getoButtonListToMaskEdit() {
		return oButtonListToMaskEdit;
	}

	public REM_LIST_bt_ListToMask getoButtonListToMaskView() {
		return oButtonListToMaskView;
	}

	public REM_LIST_bt_New getoButtonNew() {
		return oButtonNew;
	}

	public REM_LIST_DATASEARCH getoButtonDatasearch() {
		return oButtonDatasearch;
	}

	public E2_ButtonUpDown_NavigationList_to_Archiv getoButtonNavigationList_to_Archiv() {
		return oButtonNavigationList_to_Archiv;
	}
	
	
	
	
	
	
	
}
