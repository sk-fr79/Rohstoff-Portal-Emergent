 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public FKT_MASK_MaskModulContainer() throws myException {
        super();
        FKT_MASK_ComponentMapCollector compMapCollector = new FKT_MASK_ComponentMapCollector() ; 
        this.registerComponent(_TAB.fuhren_kosten_typ.rb_km(), compMapCollector );
        
        this.rb_INIT(FKT_CONST.TRANSLATOR.MASK.get_modul(), new FKT_MASK_MaskGrid(compMapCollector), true);
     
        this._setLeftPos(80)._setTopPos(80)._setWidth(700)._setHeight(300);
        
    }
}
 
