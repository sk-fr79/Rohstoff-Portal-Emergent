 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_REITER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class RR_MASK_MaskGrid extends RB_MaskGrid {
    public RR_MASK_MaskGrid(RR_MASK_ComponentMapCollector  mapColl) throws myException {
        
    	super(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
        this.setColumnWidth(0, new Extent(100));
        RR_MASK_ComponentMap  map1 = (RR_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.report_reiter));
        
        this.add(new MyE2_Label(new MyE2_String("Id:")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT_REITER.id_report_reiter)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Reihenfolge:")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT_REITER.reihenfolge)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Reitername:")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT_REITER.reitername)),2, E2_INSETS.I(2,2,2,2));
    
    }
  
    
}
 
