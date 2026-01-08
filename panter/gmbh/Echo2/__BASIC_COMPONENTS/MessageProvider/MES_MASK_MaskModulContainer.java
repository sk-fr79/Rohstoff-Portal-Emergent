 
package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider.MES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class MES_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public MES_MASK_MaskModulContainer() throws myException {
        super();
        MES_MASK_ComponentMapCollector compMapCollector = new MES_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  MES_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new MES_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
