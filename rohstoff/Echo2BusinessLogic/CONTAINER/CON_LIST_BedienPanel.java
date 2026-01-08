 
package rohstoff.Echo2BusinessLogic.CONTAINER;
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
public class CON_LIST_BedienPanel extends E2_Grid {
    
    private CON_LIST_Selector  container_LIST_Selector = null;
    
    public CON_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException     {
        super();
        this.setSize(2);
       
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this.container_LIST_Selector = new CON_LIST_Selector(oNaviList);
        this._a(container_LIST_Selector,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(new CON_LIST_DATASEARCH(oNaviList), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a(new CON_LIST_bt_ListToMask(true,oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new CON_LIST_bt_ListToMask(false,oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new CON_LIST_bt_New(oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new CON_LIST_bt_Copy(oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new CON_LIST_bt_multiDelete(oNaviList),  new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new ownExporter(oNaviList),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }
    public CON_LIST_Selector get_list_Selector() 
    {
        return container_LIST_Selector;
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
 
