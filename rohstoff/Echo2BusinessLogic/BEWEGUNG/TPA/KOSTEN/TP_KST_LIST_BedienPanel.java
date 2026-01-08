package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class TP_KST_LIST_BedienPanel extends MyE2_Column 
{
	
	private TP_KST_LIST_Selector  oTP_KST_LIST_Selector = null;
	
	public TP_KST_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oTP_KST_LIST_Selector = new TP_KST_LIST_Selector(oNaviList, E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oTP_KST_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		
		//oRowForComponents.add(new TP_KST_LIST_BT_ExportExcel(oNaviList), new Insets(3,2,15,2));
		oRowForComponents.add(new TP_KST_LIST_popup_export(oNaviList), new Insets(3,2,15,2));
		
		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE, oNaviList), new Insets(3,2,15,2));

		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE), new Insets(20,2,2,2));
		
		oRowForComponents.add(new TP_KST_LIST_DATASEARCH(oNaviList,E2_MODULNAMES.NAME_MODUL_TPA_KOSTEN_LISTE), new Insets(20,2,2,2));
	}

	public TP_KST_LIST_Selector get_oTP_KST_LIST_Selector() 
	{
		return oTP_KST_LIST_Selector;
	}
}
