 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_READABLE_FIELD_NAME;
 
public class TAXG_MASK_MaskGrid extends E2_Grid {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_MASK_MaskGrid(TAXG_MASK_ComponentMapCollector  mapColl, PARAMHASH  p_params) throws myException {
        super();
        this._setSize(160,600)._bo_no();
        
        this.params = p_params;
        
        if (this.params != null) {
            this.params.put(TAXG_PARAMS.TAXG_MASK_GRID,this);
        }     
        
        TAXG_MASK_ComponentMap  map1 = (TAXG_MASK_ComponentMap) mapColl.get(_TAB.tax_group.rb_km());
        
        this._a(new RB_lab(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.aktiv)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_GROUP.aktiv), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.id_tax_group)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_GROUP.id_tax_group), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.kurztext)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_GROUP.kurztext), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.langtext)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))._a(map1.getComp(TAX_GROUP.langtext), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
    
    }
  
    
}
 
