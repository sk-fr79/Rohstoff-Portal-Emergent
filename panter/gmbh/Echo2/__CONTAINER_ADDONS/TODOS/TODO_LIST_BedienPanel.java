package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class TODO_LIST_BedienPanel extends MyE2_Column 
{
	
	private TODO_LIST_Selector  oTODO_LIST_Selector = null;
	
	public TODO_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer, E2_BasicModuleContainer TODO_LIST_BasicModuleContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oTODO_LIST_Selector = new TODO_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oTODO_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new TODO_LIST_BT_NEW_TODO(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TODO_LIST_BT_VIEW_TODO(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TODO_LIST_BT_EDIT_TODO(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TODO_LIST_BT_DELETE_TODO(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TODO_LIST_BT_MAIL_TODO(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new REP__POPUP_Button(TODO_LIST_BasicModuleContainer.get_MODUL_IDENTIFIER(),oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_LIST), new Insets(20,2,2,2));
		
		oRowForComponents.add(new TODO_LIST_DATASEARCH(oNaviList,E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_LIST), new Insets(20,2,2,2));
	}

	public TODO_LIST_Selector get_oTODO_LIST_Selector() 
	{
		return oTODO_LIST_Selector;
	}
}
