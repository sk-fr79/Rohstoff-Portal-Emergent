package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRANSLATOR;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;


public class AT_LIST_BedienPanel extends MyE2_Column {
    
    private AT_LIST_Selector  trigger_action_def_LIST_Selector = null;
    
    public AT_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException  {
        super(MyE2_Column.STYLE_NO_BORDER());
        
        MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
        
        this.trigger_action_def_LIST_Selector = new AT_LIST_Selector(oNaviList);
        
        Insets oInsets = new Insets(0,0,0,5);
        
        this.add(trigger_action_def_LIST_Selector, oInsets);
        this.add(oRowForComponents, oInsets);
        
        oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), E2_INSETS.I(2,2,20,2));
        oRowForComponents.add(new AT_LIST_bt_ListToMask(true,oNaviList),  E2_INSETS.I(2,2,8,2));
        oRowForComponents.add(new AT_LIST_bt_ListToMask(false,oNaviList),  E2_INSETS.I(2,2,8,2));
        oRowForComponents.add(new AT_LIST_bt_New(oNaviList), E2_INSETS.I(2,2,8,2));
        oRowForComponents.add(new AT_LIST_bt_CopyDataset(oNaviList), E2_INSETS.I(2,2,8,2));
        oRowForComponents.add(new AT_LIST_bt_Delete(oNaviList), E2_INSETS.I(2,2,8,2));
		oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,TRANSLATOR.LIST.get_modul().get_callKey()), E2_INSETS.I(2,2,8,2));
		oRowForComponents.add(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList), E2_INSETS.I(2,2,8,2));

        oRowForComponents.add(new AT_LIST_DATASEARCH(oNaviList), E2_INSETS.I(12,2,8,2));
    }
    
    
    public AT_LIST_Selector get_list_Selector()   {
        return trigger_action_def_LIST_Selector;
    }
}
 
