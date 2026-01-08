 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public SD_MASK_ComponentMapCollector(String modulKenner) throws myException {
        super();
        
        this.registerComponent(_TAB.searchdef.rb_km(), new SD_MASK_ComponentMap(modulKenner));
    }
}
 
