package rohstoff.Echo2BusinessLogic._TAX.RATE;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.ExportSql.EXP_ButtonToCreateSqLExport;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class TAX__LIST_BedienPanel extends MyE2_Column 
{
	
//	private TAX__LIST_Selector  oTAX__LIST_Selector = null;
	
	public TAX__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
//		this.oTAX__LIST_Selector = new TAX__LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
//		this.add(oTAX__LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new TAX__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TAX__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TAX__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new TAX__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAME_ENUM.MODUL.MODUL_TAX_LIST.get_callKey()), new Insets(10,2,2,2));
		
		//oRowForComponents.add(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList, new E2_ButtonAUTH_ONLY_ADMIN()), new Insets(10,2,2,2));
		oRowForComponents.add(new EXP_ButtonToCreateSqLExport(oNaviList, 			new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER()), new Insets(10,2,2,2));
		
//		oRowForComponents.add(new E2_ButtonWriteListToExcel(oNaviList, new MyE2_String("Steuersätze")), new Insets(10,2,2,2));
		oRowForComponents.add(new EXP_popup_menue_exporter(oNaviList, new MyE2_String("Export der Steuersätze (aktuelle Selektion)"),null,null,null), new Insets(10,2,2,2));
		
		oRowForComponents.add(new TAX__LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
		
		//2013-10-01: neuen listselektor einbauen
		oRowForComponents.add(new TAX__LIST_Selector(oNaviList), new Insets(20,2,2,2));
		
	}

//	public TAX__LIST_Selector get_oTAX__LIST_Selector() 
//	{
//		return oTAX__LIST_Selector;
//	}
}
