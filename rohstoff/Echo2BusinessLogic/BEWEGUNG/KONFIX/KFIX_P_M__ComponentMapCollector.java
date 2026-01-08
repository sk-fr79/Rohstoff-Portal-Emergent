 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


public class KFIX_P_M__ComponentMapCollector extends RB_ComponentMapCollector {

    
	public KFIX_P_M__ComponentMapCollector(String oBelegTyp, Rec20 rec20_vkopf) throws myException {
        super();
        
        this.registerComponent(_TAB.vpos_kon.rb_km(), 		new KFIX_P_M__ComponentMap(oBelegTyp, rec20_vkopf));
        this.registerComponent(_TAB.vpos_kon_trakt.rb_km(),	new KFIX_P_M__ComponentMapAddon(oBelegTyp, rec20_vkopf));
    }

}
 
