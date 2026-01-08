package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public ADR_FREIGABE_MASK_MaskModulContainer() throws myException {
        super();
        ADR_FREIGABE_MASK_ComponentMapCollector compMapCollector = new ADR_FREIGABE_MASK_ComponentMapCollector() ; 
        this.registerComponent(_TAB.sanktion_pruefung.rb_km(), compMapCollector );
        this._setHeight(670)._setWidth(1250);
        this.rb_INIT(ADR_FREIGABE_CONST.TRANSLATOR.MASK.get_modul(), new ADR_FREIGABE_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
