 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public JCC_MASK_MaskModulContainer() throws myException {
        super();
        JCC_MASK_ComponentMapCollector compMapCollector = new JCC_MASK_ComponentMapCollector() ; 
        this.registerComponent(_TAB.jasper_compile_chain.rb_km(), compMapCollector );
        
        this.rb_INIT(JCC_CONST.TRANSLATOR.MASK.get_modul(), new JCC_MASK_MaskGrid(compMapCollector), true);
        this.set_oExtWidth(new Extent(600));
        this.set_oExtHeight(new Extent(350));
        
    }
}
 
