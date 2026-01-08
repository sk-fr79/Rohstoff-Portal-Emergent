package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_bt_New_Generic;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT.WF_HEAD_LIST__Selectioncontainer;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_CONST;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_BT_NEW_EXTERNAL;

public class WF_HEAD_LIST_BedienPanel extends MyE2_Column 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2430585634547617324L;
	
	// TODO: Selektor austauschen
//	private WF_HEAD_LIST_Selector  oWF_HEAD_LIST_Selector = null;
	private WF_HEAD_LIST__Selectioncontainer oWF_HEAD_LIST_SelectorMain = null;
	
	private WF_HEAD_LIST_Selector_for_entries oWF_HEAD_LIST_Selector_for_Entries = null;
	
	
	public WF_HEAD_LIST_BedienPanel(WF_HEAD__NaviList oNaviList, E2_BasicModuleContainer_MASK oMaskContainer, String cMODULKENNER_LIST_CONTAINER, E2_BasicModuleContainer oListContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		// Haupt-Selektor
//		this.oWF_HEAD_LIST_Selector = new WF_HEAD_LIST_Selector(oNaviList, cMODULKENNER_LIST_CONTAINER);
		this.oWF_HEAD_LIST_SelectorMain = new WF_HEAD_LIST__Selectioncontainer(oNaviList, cMODULKENNER_LIST_CONTAINER);
		
		
		// zusätzliches Selektor-Panel welches nicht verschwindet für die Workflow-Aufgaben
		this.oWF_HEAD_LIST_Selector_for_Entries = new WF_HEAD_LIST_Selector_for_entries(oNaviList, cMODULKENNER_LIST_CONTAINER/*,oWF_HEAD_LIST_Selector.get_oSelVector()*/);
		
		
		Insets oInsets = new Insets(0,0,0,5);
		
//		this.add(oWF_HEAD_LIST_Selector, oInsets);
		this.add(oWF_HEAD_LIST_SelectorMain, oInsets);
		
		this.add(oWF_HEAD_LIST_Selector_for_Entries,oInsets);
		
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		WF_LIST_BT_NEW_EXTERNAL oBtn = new WF_LIST_BT_NEW_EXTERNAL(oListContainer,null,null,null,null);
		oRowForComponents.add(oBtn, E2_INSETS.I_2_2_2_2);
		// Debug
		oBtn.setParameterForInitialisation(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG, "<Neuer Laufzettel - Laufzettelbeschreibung hier eintragen>");
		oBtn.setParameterForInitialisation("AUFGABE", "");
		
		oRowForComponents.add(new WF_HEAD_LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new WF_HEAD_LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new WF_HEAD_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new WF_HEAD_LIST_BT_CopyWF(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new WF_HEAD_LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);

		// PopUp für die Druck-Buttons
		MyE2_PopUpMenue oPopUP_Print = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("listen.png"), E2_ResourceIcon.get_RI("listen__.png"), false);
		oPopUP_Print.addButton(new WF_HEAD_LIST_BT_PRINT(oNaviList,oMaskContainer,new MyE2_String("Nur selektierte Laufzettel"),EnumPrintOptions.PRINT_SELECTED),true);
		oPopUP_Print.addButton(new WF_HEAD_LIST_BT_PRINT(oNaviList,oMaskContainer,new MyE2_String("Alle Laufzettel auf der Seite"),EnumPrintOptions.PRINT_CURRENT_PAGE),true);
		oPopUP_Print.addButton(new WF_HEAD_LIST_BT_PRINT(oNaviList,oMaskContainer,new MyE2_String("Alle Laufzettel"),EnumPrintOptions.PRINT_ALL),true);
		oRowForComponents.add(oPopUP_Print,E2_INSETS.I_2_2_2_2);

//		MyE2_PopUpMenue oPopUP_Info = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("info.png"), E2_ResourceIcon.get_RI("info__.png"), false);
//		oPopUP_Info.addButton(new WF_HEAD_LIST_BT_WINDOW_UEBERSICHT(oNaviList,oMaskContainer,new MyE2_String("Nur selektierte Laufzettel"),EnumPrintOptions.PRINT_SELECTED),true);
//		oPopUP_Info.addButton(new WF_HEAD_LIST_BT_WINDOW_UEBERSICHT(oNaviList,oMaskContainer,new MyE2_String("Alle Laufzettel auf der Seite"),EnumPrintOptions.PRINT_CURRENT_PAGE),true);
//		oPopUP_Info.addButton(new WF_HEAD_LIST_BT_WINDOW_UEBERSICHT(oNaviList,oMaskContainer,new MyE2_String("Alle Laufzettel"),EnumPrintOptions.PRINT_ALL),true);
//		oRowForComponents.add(oPopUP_Info,E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new WF_HEAD_LIST_BT_WINDOW_UEBERSICHT(oNaviList,oMaskContainer,EnumPrintOptions.PRINT_SELECTED), E2_INSETS.I_2_2_2_2);

		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,oNaviList), E2_INSETS.I_0_0_10_0);

		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE), new Insets(20,2,2,2));
		
		
   		oRowForComponents.add(new REM_bt_New_Generic(oNaviList, MODUL.NAME_WORKFLOW_LAUFZETTEL_LISTE),E2_INSETS.I_20_2_2_2);

		
		
		oRowForComponents.add(new WF_HEAD_LIST_DATASEARCH(oNaviList, E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE), new Insets(20,2,2,2));
	}

//	public WF_HEAD_LIST_Selector get_oWF_HEAD_LIST_Selector() 
//	{
//		return oWF_HEAD_LIST_Selector;
//	}
	
	public WF_HEAD_LIST__Selectioncontainer get_oWF_HEAD_LIST_Selector() 
	{
		return oWF_HEAD_LIST_SelectorMain;
	}
	
}
