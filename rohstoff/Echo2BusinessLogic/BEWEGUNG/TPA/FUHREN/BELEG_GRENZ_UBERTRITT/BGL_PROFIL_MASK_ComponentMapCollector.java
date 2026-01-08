 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class BGL_PROFIL_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public BGL_PROFIL_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(_TAB.profil_grenzubertritt.rb_km(), new BGL_PROFIL_MASK_ComponentMap());
    }
}
 
