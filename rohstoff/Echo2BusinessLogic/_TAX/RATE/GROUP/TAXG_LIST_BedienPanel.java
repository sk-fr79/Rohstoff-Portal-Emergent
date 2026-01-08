 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
 
 
public class TAXG_LIST_BedienPanel extends E2_Grid {
    
//    private TAXG_LIST_Selector  tax_group_LIST_Selector = null;
    
    
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    public TAXG_LIST_BedienPanel(E2_NavigationList oNaviList, PARAMHASH  p_params) throws myException     {
        super();
        this.setSize(2);
        
		if (this.params != null) {
            this.params.put(TAXG_PARAMS.TAXG_MODULCONTAINER_LIST_BEDIENPANEL,this);
        }
               
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
//        this.tax_group_LIST_Selector = new TAXG_LIST_Selector(oNaviList,params);
//        this._a(tax_group_LIST_Selector,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(new TAXG_LIST_DATASEARCH(oNaviList,params), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a(new TAXG_LIST_bt_ListToMask(true,oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new TAXG_LIST_bt_ListToMask(false,oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new TAXG_LIST_bt_New(oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new TAXG_LIST_bt_Copy(oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new TAXG_LIST_bt_multiDelete(oNaviList,params),  new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new ownExporter(oNaviList),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }
//    public TAXG_LIST_Selector get_list_Selector() 
//    {
//        return tax_group_LIST_Selector;
//    }
    
    
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
 
