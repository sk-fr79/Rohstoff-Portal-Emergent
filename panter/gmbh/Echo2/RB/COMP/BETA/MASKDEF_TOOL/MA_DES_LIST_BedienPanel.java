package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_LIST_BedienPanel extends E2_Grid {
    
    private MA_DES_LIST_Selector  mask_def_LIST_Selector = null;
    
    public MA_DES_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException     {
        super();
        this.setSize(2);
       
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this.mask_def_LIST_Selector = new MA_DES_LIST_Selector(oNaviList);
        
        this._a(mask_def_LIST_Selector,   	new RB_gld()._ins(0,0,0,5)._span(2));
       
        this._a(grid4Components,   											new RB_gld()._left_mid()._ins(0,0,0,5));
        this._a(new MA_DES_LIST_DATASEARCH(oNaviList), 						new RB_gld()._left_mid()._ins(2,2,10,2));
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._left_mid()._ins(2,2,20,2));
        grid4Components._a(new MA_DES_LIST_bt_New(oNaviList),   			new RB_gld()._left_mid()._ins(0,2,5,2));
        grid4Components._a(new MA_DES_LIST_bt_ListToMask(true,oNaviList),   new RB_gld()._left_mid()._ins(0,2,5,2));
        grid4Components._a(new MA_DES_LIST_bt_ListToMask(false,oNaviList),  new RB_gld()._left_mid()._ins(0,2,5,2));
        grid4Components._a(new MA_DES_LIST_bt_Copy(oNaviList),   			new RB_gld()._left_mid()._ins(0,2,5,2));
        
        grid4Components._a(new MA_DES_LIST_bt_multiDelete(oNaviList),  		new RB_gld()._left_mid()._ins(20,2,2,2));
        
        grid4Components._a(new ownExporter(oNaviList),						new RB_gld()._left_mid()._ins(20,2,2,2));
        
    }
    public MA_DES_LIST_Selector get_list_Selector() 
    {
        return mask_def_LIST_Selector;
    }
    
    
    //exporter-button
    private class ownExporter extends EXP_popup_menue_exporter {
    	/**
		 * @param p_navigationlist
		 */
		public ownExporter(E2_NavigationList p_navigationlist) {
			super(p_navigationlist);
		}
		
    }
    
    
}
 
