 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
  
public class BOR_ART_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_MASK_MaskModulContainer(PARAMHASH  p_params) throws myException {
        super();
        
        this.params = p_params;
        
        if (this.params != null) {
            this.params.put(BOR_ART_PARAMS.BOR_ART_MODULCONTAINER_MASK,this);
        }     
        
        BOR_ART_MASK_ComponentMapCollector compMapCollector = new BOR_ART_MASK_ComponentMapCollector(this.params) ; 
        this.registerComponent(_TAB.bordercrossing_artikel.rb_km(), compMapCollector );
        
        this.rb_INIT(BOR_ART_CONST.TRANSLATOR.MASK.get_modul(), new BOR_ART_MASK_MaskGrid(compMapCollector,this.params), true);
        
    }
}
 
