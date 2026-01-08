 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;
  
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class BGL_PROFIL_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public BGL_PROFIL_MASK_MaskModulContainer() throws myException {
        super();
        BGL_PROFIL_MASK_ComponentMapCollector compMapCollector = new BGL_PROFIL_MASK_ComponentMapCollector() ; 
        this.registerComponent(_TAB.profil_grenzubertritt.rb_km(), compMapCollector );
        
        this.rb_INIT(BGL_PROFIL_CONST.TRANSLATOR.MASK.get_modul(), new BGL_PROFIL_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
