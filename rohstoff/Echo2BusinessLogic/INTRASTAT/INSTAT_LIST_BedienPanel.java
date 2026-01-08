package rohstoff.Echo2BusinessLogic.INTRASTAT;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;


public class INSTAT_LIST_BedienPanel extends MyE2_Column 
{
	
	private INSTAT_LIST_Selector  oINSTAT_LIST_Selector = null;
	private MyE2_CheckBox 	      oCheckBoxOverride = null;
	
	public INSTAT_LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oINSTAT_LIST_Selector = new INSTAT_LIST_Selector(oNaviList, E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE);
		
		Insets oInsets = new Insets(0,0,0,5);

		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oCheckBoxOverride =  new MyE2_CheckBox(new MyE2_String("Alte Einträge überschreiben"),oStyle);
		oCheckBoxOverride.setToolTipText(new MyE2_String("Falls schon Einträge in dem Monat vorhanden sind, werden diese gelöscht und neu angelegt!").toString());
		
		
		this.add(oINSTAT_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		MyE2_Button btn = null;
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE,oNaviList), E2_INSETS.I_0_0_10_0);
		
		oRowForComponents.add(new INSTAT_LIST_BT_NEW(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector,this,ENUM_IntrastatType.ENUM_Import), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new INSTAT_LIST_BT_NEW(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector,this,ENUM_IntrastatType.ENUM_Export), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(oCheckBoxOverride ,E2_INSETS.I_2_2_20_2);
		
		oRowForComponents.add(new INSTAT_BT_LIST_EXCEL(oNaviList));
		oRowForComponents.add(new INSTAT_LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		btn = new INSTAT_LIST_BT_DELETE(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector,this,ENUM_IntrastatType.ENUM_Import);
		btn.setToolTipText("Löscht den Eintrag aus der Intrastat-Liste. Der gelöschte Eintrag wird bei einem neuen Ermittlungslauf wieder berücksichtigt.");
		oRowForComponents.add(btn, E2_INSETS.I_2_2_2_2);
		
		btn = new INSTAT_LIST_BT_NICHT_MELDEN(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector,this);
		btn.setToolTipText("Schaltet den Nicht-Melden Zustand der gewählten Eiträge um (nicht melden -> melden / melden -> nicht melden) ");
		oRowForComponents.add(btn, E2_INSETS.I_2_2_20_2);
		
		oRowForComponents.add(new INSTAT_LIST_BT_DOWNLOAD_EINFUHR(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new INSTAT_LIST_BT_DOWNLOAD_AUSFUHR(oNaviList,oMaskContainer,this.oINSTAT_LIST_Selector), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE), new Insets(20,2,2,2));
		
		oRowForComponents.add(new INSTAT_LIST_DATASEARCH(oNaviList,E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE), new Insets(20,2,2,2));
	}

	public INSTAT_LIST_Selector get_oINSTAT_LIST_Selector() 
	{
		return oINSTAT_LIST_Selector;
	}
	
	public boolean canOverrideExistingEntries(){
		return oCheckBoxOverride.isSelected();	
	}
}
