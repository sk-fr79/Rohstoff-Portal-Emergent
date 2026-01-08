 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M__ComponentMapCollector extends RB_ComponentMapCollector {
   
	private boolean 		b_fixierungsKontrakt = false;
	private VORGANGSART	 	vorgangtyp = null; 
	
	
	public KFIX_K_M__ComponentMapCollector(boolean bFixierungsKontrakt, VORGANGSART belegtyp) throws myException {
        super();
        
        this.b_fixierungsKontrakt = bFixierungsKontrakt;
       
        this.registerComponent(new RB_KM(_TAB.vkopf_kon), new KFIX_K_M_ComponentMap(this.b_fixierungsKontrakt, belegtyp));
        this.vorgangtyp = belegtyp;
        
    }
	
	public KFIX_K_M__ComponentMapCollector setFixierungKontrakte(boolean bFixierungsKontrakt){
		this.b_fixierungsKontrakt = bFixierungsKontrakt;
		return this;
	}
	
	public boolean isFixierungsKontrakte(){
		return this.b_fixierungsKontrakt;
	}

	public VORGANGSART get_vorgangtyp_ek_oder_vk() {
		return vorgangtyp;
	}
}
 
