package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public ADR_FREIGABE_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(_TAB.sanktion_pruefung.rb_km(), new ADR_FREIGABE_MASK_ComponentMap());
    }
}
 
