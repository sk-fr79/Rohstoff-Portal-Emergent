package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_LIST_BedienPanel extends E2_Grid  {
    public AH7P_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException    {
        super();
        this.setSize(1);
        
        E2_Grid grid = new E2_Grid();
        
        this._a(grid,  	new RB_gld()._ins(0,0,0,5));
        
        grid._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  	new RB_gld()._ins(2, 2, 20, 2));
        grid._a(new AH7P_LIST_bt_New(oNaviList),   											new RB_gld()._ins(2, 2, 10, 2));
        grid._a(new AH7P_LIST_bt_CopyDataset(oNaviList), 									new RB_gld()._ins(2, 2, 10, 2));
        
        grid._a(new AH7P_LIST_bt_ListToMask(true,oNaviList),  								new RB_gld()._ins(2, 2, 10, 2));
        grid._a(new AH7P_LIST_bt_ListToMask(false,oNaviList),  								new RB_gld()._ins(2, 2, 10, 2));
        grid._a(new AH7P_LIST_bt_multiDelete(oNaviList),									new RB_gld()._ins(2, 2, 10, 2));
        
        grid._a(new ownExporter(oNaviList),													new RB_gld()._ins(10, 2, 10, 2));
        
        grid._a(new AH7P_LIST_bt_SQLExport(oNaviList),										new RB_gld()._ins(10, 2, 10, 2));
        
        grid._setSizeSingleLine();
    }

    
    private class ownExporter extends EXP_popup_menue_exporter {

    	/**
		 * @param p_navigationlist
		 */
		public ownExporter(E2_NavigationList p_navigationlist) {
			super(p_navigationlist);
			
		}
		
    }
    
    
}
 
