package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class SCAN_LIST_BedienPanel extends MyE2_Column 
{
	
	private SCAN_LIST_Selector  scanner_LIST_Selector = null;
	
	public SCAN_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.scanner_LIST_Selector = new SCAN_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(scanner_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new SCAN_LIST_bt_New(oNaviList), 				E2_INSETS.I(2));
		oRowForComponents.add(new SCAN_LIST_bt_ListToMask(true,oNaviList),  E2_INSETS.I(2));
		oRowForComponents.add(new SCAN_LIST_bt_ListToMask(false,oNaviList), E2_INSETS.I(2));
		oRowForComponents.add(new SCAN_LIST_bt_kopie(oNaviList), 			E2_INSETS.I(2));
		
		oRowForComponents.add(new SCAN_LIST_BT_DELETE(oNaviList), 			E2_INSETS.I(20,2,2,2));
		
		oRowForComponents.add(new EXP_popup_menue_exporter(oNaviList,		new MyE2_String("Scanner-Liste"),null, "EXPORT_SCANNER_XLS", "EXPORT_SCANNER_CSV"), E2_INSETS.I(20,2,2,2));
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList, oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE()), E2_INSETS.I(2));
		
		oRowForComponents.add(new SCAN_LIST_DATASEARCH(oNaviList), 			new Insets(20,2,2,2));
	}

	public SCAN_LIST_Selector get_list_Selector() 
	{
		return scanner_LIST_Selector;
	}
}
