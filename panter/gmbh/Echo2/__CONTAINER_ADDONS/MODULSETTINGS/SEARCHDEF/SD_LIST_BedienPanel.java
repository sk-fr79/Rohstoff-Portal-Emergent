 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_LIST_BedienPanel extends E2_Grid {
    
    

	public SD_LIST_BedienPanel(E2_NavigationList oNaviList, String modulKenner) throws myException     {
        super();
        this.setSize(2);
       
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a(new SD_LIST_bt_ListToMask(true,oNaviList, modulKenner),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new SD_LIST_bt_ListToMask(false,oNaviList, modulKenner),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new SD_LIST_bt_New(oNaviList, modulKenner),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new SD_LIST_bt_Copy(oNaviList, modulKenner),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new SD_LIST_bt_multiDelete(oNaviList),  new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToCreate_SQL_ExportStatements(oNaviList), new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new ownExporter(oNaviList),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
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
 
