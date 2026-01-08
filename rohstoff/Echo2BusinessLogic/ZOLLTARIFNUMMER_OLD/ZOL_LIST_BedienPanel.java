package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer.IMPORT_Zolltarifnummer_Temp_BasicModuleContainer;


public class ZOL_LIST_BedienPanel extends MyE2_Column 
{
    
    private ZOL_LIST_Selector  zolltarifnummer_LIST_Selector = null;
    
    public ZOL_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
    {
        super(MyE2_Column.STYLE_NO_BORDER());
        
        MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
        
        this.zolltarifnummer_LIST_Selector = new ZOL_LIST_Selector(oNaviList);
        
        Insets oInsets = new Insets(0,0,0,5);
        
        this.add(zolltarifnummer_LIST_Selector, oInsets);
        this.add(oRowForComponents, oInsets);
        
        oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
        oRowForComponents.add(new ZOL_LIST_bt_ListToMask(true,oNaviList),  new Insets(2,2,20,2));
        oRowForComponents.add(new ZOL_LIST_bt_ListToMask(false,oNaviList),  new Insets(2,2,20,2));
        oRowForComponents.add(new ZOL_LIST_bt_New(oNaviList), E2_INSETS.I_2_2_2_2);
        oRowForComponents.add(new ZOL_LIST_bt_multiDelete(oNaviList), E2_INSETS.I_2_2_2_2);
        
        oRowForComponents.add(new ZOL_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
        
        
        
    }
    public ZOL_LIST_Selector get_list_Selector() 
    {
        return zolltarifnummer_LIST_Selector;
    }
}
 
