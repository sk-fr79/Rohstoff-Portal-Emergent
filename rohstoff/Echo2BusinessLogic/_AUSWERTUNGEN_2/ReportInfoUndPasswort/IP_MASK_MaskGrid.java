package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class IP_MASK_MaskGrid extends RB_MaskGrid {
    public IP_MASK_MaskGrid(IP_MASK_ComponentMapCollector  mapColl) throws myException {
        super(3, MyE2_Grid.STYLE_GRID_NO_BORDER());
        
        this.setColumnWidth(0, new Extent(100));
        
        IP_MASK_ComponentMap  map1 = (IP_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.report));

        this.add(new MyE2_Label(new MyE2_String("ID")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.id_report)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Button Text")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.buttontext)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Titel")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.titel)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Beschreibung")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.beschreibung)),2, E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Passwort")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.password)),2, E2_INSETS.I(2,2,2,2));
   //     this.add(map1.get_Comp(IP_CONST.IP_NAMES.CHECKBOX_MASKING_PASSWORD.db_val()), 1, 1, E2_INSETS.I(2,2,2,2),new Alignment(Alignment.LEFT, Alignment.CENTER));
        
        this.add(new MyE2_Label(new MyE2_String("Berechtigungen")),1, 2, E2_INSETS.I(2,2,2,2), new Alignment(Alignment.LEFT, Alignment.TOP));
        this.add(map1.getRbComponent(new RB_KF(REPORT.geschaeftsfuehrer)),2, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(REPORT.supervisor)),2, E2_INSETS.I(2,2,2,2));
        
    }
  
    
}
 
