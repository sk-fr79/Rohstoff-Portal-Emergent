 
package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_LIST_BedienPanel extends E2_Grid {
    
    private ADR_FREIGABE_LIST_Selector  sanktion_pruefung_LIST_Selector = null;
    
    public ADR_FREIGABE_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException     {
        super();
        this.setSize(2);
       
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this.sanktion_pruefung_LIST_Selector = new ADR_FREIGABE_LIST_Selector(oNaviList);
        
        
        this._a(sanktion_pruefung_LIST_Selector,   				new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,   								new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(new ADR_FREIGABE_LIST_DATASEARCH(oNaviList), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a(new ADR_FREIGABE_LIST_bt_ListToMask(true,oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new ADR_FREIGABE_LIST_bt_ListToMask(false,oNaviList),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new REP__POPUP_Button(oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(),oNaviList), new RB_gld()._ins(2,2,10,2)._left_mid());

        grid4Components._a(new ownExporter(oNaviList),			new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }
    public ADR_FREIGABE_LIST_Selector get_list_Selector() 
    {
        return sanktion_pruefung_LIST_Selector;
    }
    
    
    //exporter-button
    private class ownExporter extends EXP_popup_menue_exporter {
		public ownExporter(E2_NavigationList p_navigationlist) {
			super(p_navigationlist);
		}
    }
    
}
 
