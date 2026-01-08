package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER.LOGTRIG__BT_CREATE_TRIGGERS;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER.LOGTRIG__BT_SCHREIBE_BASISTRIGGER;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LOGTRIG__LIST_BedienPanel extends MyE2_Column 
{
	
	private LOGTRIG__LIST_Selector  oLOGTRIG__LIST_Selector = null;
	
	public LOGTRIG__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
//		this.oLOGTRIG__LIST_Selector = new LOGTRIG__LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
//		this.add(oLOGTRIG__LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);
		
		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new LOGTRIG__LIST_BT_NEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LOGTRIG__LIST_BT_NEW_COPY(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LOGTRIG__LIST_BT_VIEW(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LOGTRIG__LIST_BT_EDIT(oNaviList,oMaskContainer), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LOGTRIG__LIST_BT_DELETE(oNaviList), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new LOGTRIG__BT_SCHREIBE_BASISTRIGGER(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new LOGTRIG__BT_CREATE_TRIGGERS(), E2_INSETS.I_2_2_2_2);
		
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_DEF_LISTE), new Insets(20,2,2,2));
		
		oRowForComponents.add(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList), new Insets(20,2,2,2));
		
		oRowForComponents.add(new LOGTRIG__LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
	}

	public LOGTRIG__LIST_Selector get_oLOGTRIG__LIST_Selector() 
	{
		return oLOGTRIG__LIST_Selector;
	}
}
