 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
 
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
 
 
public class BOR_ART_LIST_BedienPanel extends E2_Grid {
    
    private BOR_ART_LIST_Selector  bordercrossing_artikel_LIST_Selector = null;
    
    
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    public BOR_ART_LIST_BedienPanel(E2_NavigationList oNaviList, PARAMHASH  p_params) throws myException     {
        super();
        this.setSize(2);
        
        this.params = p_params;
        
		if (this.params != null) {
            this.params.put(BOR_ART_PARAMS.BOR_ART_MODULCONTAINER_LIST_BEDIENPANEL,this);
        }
               
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this.bordercrossing_artikel_LIST_Selector = new BOR_ART_LIST_Selector(oNaviList,params);
//        this._a(bordercrossing_artikel_LIST_Selector,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
//        this._a(new BOR_ART_LIST_DATASEARCH(oNaviList,params), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a(new BOR_ART_LIST_bt_ListToMask(true,oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new BOR_ART_LIST_bt_ListToMask(false,oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new BOR_ART_LIST_bt_New(oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new BOR_ART_LIST_bt_Copy(oNaviList,params),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new BOR_ART_LIST_bt_multiDelete(oNaviList,params),  new RB_gld()._ins(2,2,10,2)._left_mid());
        
//        grid4Components._a(new ownExporter(oNaviList),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }

    
    public BOR_ART_LIST_Selector get_list_Selector() 
    {
        return bordercrossing_artikel_LIST_Selector;
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
 
