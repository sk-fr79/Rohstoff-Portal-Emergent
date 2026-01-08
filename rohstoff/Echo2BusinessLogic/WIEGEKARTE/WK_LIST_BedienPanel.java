package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_ImageCapture_Handler;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class WK_LIST_BedienPanel extends MyE2_Column 
{
	
	private WK_LIST_Selector  oWK_LIST_Selector = null;
	
	public WK_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oWK_LIST_Selector = new WK_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oWK_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new WK_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);		
		oRowForComponents.add(new WK_LIST_BT_NEW_COPY(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new WK_LIST_BT_NEW_EINZELVERWIEGUNG(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		
		// Image-Captureing in Liste
		IMG_ImageCapture_Handler oImageCaptureHandler = new IMG_ImageCapture_Handler(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE);
		oRowForComponents.add(oImageCaptureHandler.get_ButtonRow(oNaviList),E2_INSETS.I_2_2_2_2);
		
		

		// Delete-Button ist jetzt Storno-Button
		oRowForComponents.add(new WK_LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		
//		oRowForComponents.add(new WK_LIST_BT_PRINT(oMaskContainer,"Wiegekarte",oNaviList,false,false,false),E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new WK_LIST_BT_PRINT(oMaskContainer,"Wiegekarte",WK_LIST_BT_PRINT.ENUM_DruckTyp.ALLE, oNaviList),E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE,oNaviList), E2_INSETS.I_0_0_10_0);
		oRowForComponents.add(new WK_LIST_BT_EXPORT_TO_EXCEL(oNaviList));
		
//		oRowForComponents.add(new WK_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
//		oRowForComponents.add(new WK_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE), new Insets(20,2,2,2));
		
		oRowForComponents.add(new WK_LIST_DATASEARCH(oNaviList,E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE), new Insets(20,2,2,2));
	}

	public WK_LIST_Selector get_oWK_LIST_Selector() 
	{
		return oWK_LIST_Selector;
	}
}
