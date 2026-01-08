 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
  
public class BOR_ART_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_MASK_ComponentMapCollector(PARAMHASH  p_params) throws myException {
        super();
        
        this.params = p_params;     
		if (this.params != null) {
            this.params.put(BOR_ART_PARAMS.BOR_ART_MASK_COMPONENT_MAP_COLLECTOR,this);
        }
        
        this.registerComponent(_TAB.bordercrossing_artikel.rb_km(), new BOR_ART_MASK_ComponentMap(this.params));
    }
}
 
