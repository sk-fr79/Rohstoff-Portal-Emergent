 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
  
public class TAXG_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_MASK_ComponentMapCollector(PARAMHASH  p_params) throws myException {
        super();
        
        this.params = p_params;     
		if (this.params != null) {
            this.params.put(TAXG_PARAMS.TAXG_MASK_COMPONENT_MAP_COLLECTOR,this);
        }
        
        this.registerComponent(_TAB.tax_group.rb_km(), new TAXG_MASK_ComponentMap(this.params));
    }
}
 
