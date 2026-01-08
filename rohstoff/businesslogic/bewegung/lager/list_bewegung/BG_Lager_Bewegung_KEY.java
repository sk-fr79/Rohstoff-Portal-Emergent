 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class BG_Lager_Bewegung_KEY extends RB_KM {
	
	@Deprecated
	public BG_Lager_Bewegung_KEY() throws myException {
		super(_TAB.bg_atom);
	}
}
 
