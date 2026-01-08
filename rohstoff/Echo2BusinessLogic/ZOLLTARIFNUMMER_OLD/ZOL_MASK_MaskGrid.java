package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;



public class ZOL_MASK_MaskGrid extends RB_MaskGrid {
    public ZOL_MASK_MaskGrid(ZOL_MASK_ComponentMapCollector  mapColl) throws myException {
        super(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
        
        ZOL_MASK_ComponentMap  map1 = (ZOL_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.zolltarifnummer));
        
        this.add(new MyE2_Label(new MyE2_String("Aktiv")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.aktiv)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("bm_nummer")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.bm_nummer)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("bm_text")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.bm_text)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("id_zolltarifnummer")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.id_zolltarifnummer)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("letzter_import")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.letzter_import)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("nummer")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.nummer)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("reverse_charge")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.reverse_charge)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("text1")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.text1)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("text2")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.text2)),2, E2_INSETS.I(2,2,2,2));
        this.add(new MyE2_Label(new MyE2_String("text3")),1, E2_INSETS.I(2,2,2,2));
        this.add(map1.getRbComponent(new RB_KF(ZOLLTARIFNUMMER.text3)),2, E2_INSETS.I(2,2,2,2));
        
        this.add((IF_RB_Component)new RB_TextField(100),2, E2_INSETS.I(2,2,2,2));
    
    }
  
    
}
 
