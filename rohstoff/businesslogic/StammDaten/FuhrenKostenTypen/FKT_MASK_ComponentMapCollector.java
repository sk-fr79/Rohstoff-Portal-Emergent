 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public FKT_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(_TAB.fuhren_kosten_typ.rb_km(), new FKT_MASK_ComponentMap());
    }
}
 
