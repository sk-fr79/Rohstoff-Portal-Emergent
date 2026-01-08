 
package rohstoff.Echo2BusinessLogic.AH7.RELATION;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class AH7_KEY extends RB_KM {
	
	public AH7_KEY() throws myException {
		super(_TAB.ah7_steuerdatei);
	}
}
 
