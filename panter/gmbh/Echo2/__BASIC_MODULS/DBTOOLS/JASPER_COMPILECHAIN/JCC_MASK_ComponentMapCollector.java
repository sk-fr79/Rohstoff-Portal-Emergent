 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public JCC_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(_TAB.jasper_compile_chain.rb_km(), new JCC_MASK_ComponentMap());
    }
}
 
