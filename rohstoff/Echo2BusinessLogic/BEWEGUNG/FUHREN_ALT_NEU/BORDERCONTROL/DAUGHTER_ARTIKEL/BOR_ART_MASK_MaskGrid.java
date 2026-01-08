 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
 
public class BOR_ART_MASK_MaskGrid extends E2_Grid4Mask {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_MASK_MaskGrid(BOR_ART_MASK_ComponentMapCollector  mapColl, PARAMHASH  p_params) throws myException {
        super();
        this._setSize(160,600)._bo_no();
        
        this.params = p_params;
        
        if (this.params != null) {
            this.params.put(BOR_ART_PARAMS.BOR_ART_MASK_GRID,this);
        }     
        
        BOR_ART_MASK_ComponentMap  map1 = (BOR_ART_MASK_ComponentMap) mapColl.get(_TAB.bordercrossing_artikel.rb_km());
        
//        this._add(new RB_lab("id_bordercrossing") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BORDERCROSSING_ARTIKEL.id_bordercrossing), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("ID") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._add(new RB_lab("Artikel") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._add(map1.getComp(BORDERCROSSING_ARTIKEL.id_artikel), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
     
//        this._add(new RB_lab("menge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BORDERCROSSING_ARTIKEL.menge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
    
    }
  
    
}
 
