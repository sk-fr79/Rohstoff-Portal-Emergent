 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public SD_MASK_MaskModulContainer(String modulKenner) throws myException {
        super();
        SD_MASK_ComponentMapCollector compMapCollector = new SD_MASK_ComponentMapCollector(modulKenner) ; 
        this.registerComponent(_TAB.searchdef.rb_km(), compMapCollector );
        
        this._setWidth(650)._setHeight(450);
        
        this.rb_INIT(SD_CONST.TRANSLATOR.MASK.get_modul(), new SD_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
