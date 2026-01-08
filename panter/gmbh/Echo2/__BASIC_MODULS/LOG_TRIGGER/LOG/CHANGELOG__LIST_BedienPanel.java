package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOG;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class CHANGELOG__LIST_BedienPanel extends MyE2_Column 
{
	
	private CHANGELOG__LIST_Selector  oCHANGELOG__LIST_Selector = null;
	
	public CHANGELOG__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oCHANGELOG__LIST_Selector = new CHANGELOG__LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oCHANGELOG__LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new CHANGELOG__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new CHANGELOG__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new CHANGELOG__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new CHANGELOG__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_ENTRIES_LISTE), new Insets(20,2,2,2));
		
		oRowForComponents.add(new CHANGELOG__LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public CHANGELOG__LIST_Selector get_oCHANGELOG__LIST_Selector() 
	{
		return oCHANGELOG__LIST_Selector;
	}
}
