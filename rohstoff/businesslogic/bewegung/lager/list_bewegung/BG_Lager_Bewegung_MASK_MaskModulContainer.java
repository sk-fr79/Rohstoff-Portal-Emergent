 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
@Deprecated
public class BG_Lager_Bewegung_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public BG_Lager_Bewegung_MASK_MaskModulContainer() throws myException {
        super();
        BG_Lager_Bewegung_MASK_ComponentMapCollector compMapCollector = new BG_Lager_Bewegung_MASK_ComponentMapCollector() ; 
//        this.registerComponent(_TAB.bg_ladung.rb_km(), compMapCollector );
        
        this.rb_INIT(BG_Lager_Bewegung_CONST.TRANSLATOR.MASK.get_modul(), new BG_Lager_Bewegung_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
