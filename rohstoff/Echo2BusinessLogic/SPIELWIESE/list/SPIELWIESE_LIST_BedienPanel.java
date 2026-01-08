package rohstoff.Echo2BusinessLogic.SPIELWIESE.list;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class SPIELWIESE_LIST_BedienPanel extends MyE2_Column 
{
	
	private SPIELWIESE_LIST_Selector  oSPIELWIESE_LIST_Selector = null;
	
	// Changed class type of oMaskContainer
	public SPIELWIESE_LIST_BedienPanel(E2_NavigationList oNaviList, RB_ModuleContainerMASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oSPIELWIESE_LIST_Selector = new SPIELWIESE_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oSPIELWIESE_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new SPIELWIESE_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new SPIELWIESE_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new SPIELWIESE_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		//TODO: This class has cha
		oRowForComponents.add(new SPIELWIESE_LIST_BT_EDIT_NEW(oNaviList, oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new SPIELWIESE_LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.SPIELWIESE_LIST), new Insets(20,2,2,2));
		
		oRowForComponents.add(new SPIELWIESE_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
		

		//IDEA: Das ist Schwachsinn so
		// Das ist für die Mask
		MyE2_Row r2 = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		Insets i2 = new Insets(0,0,0,5);
		oMaskContainer.add(r2, i2);
		//r2.add(new RB_BT_SaveMask(oMaskContainer));
		r2.add(new SPIELWIESE_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public SPIELWIESE_LIST_Selector get_oSPIELWIESE_LIST_Selector() 
	{
		return oSPIELWIESE_LIST_Selector;
	}
}
