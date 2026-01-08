package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonWriteListToExcel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LAND__LIST_BedienPanel extends MyE2_Column 
{
	
	private LAND__LIST_Selector  oLAND__LIST_Selector = null;
	
	public LAND__LIST_BedienPanel(E2_NavigationList oNaviList,LAND__MASK_BasicModuleContainer oMaskContainer, LAND__LIST_BasicModuleContainer oListContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oLAND__LIST_Selector = new LAND__LIST_Selector(oNaviList,oListContainer.get_MODUL_IDENTIFIER());
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oLAND__LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new LAND__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LAND__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LAND__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LAND__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new EXP_popup_menue_exporter(oNaviList,new MyE2_String("Laender-Liste"),null, "EXPORT_LAENDER_XLS", "EXPORT_LAENDER_CSV"), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_LAENDER_LIST), new Insets(20,2,2,2));
		
		oRowForComponents.add(new LAND__LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public LAND__LIST_Selector get_oLAND__LIST_Selector() 
	{
		return oLAND__LIST_Selector;
	}
}
