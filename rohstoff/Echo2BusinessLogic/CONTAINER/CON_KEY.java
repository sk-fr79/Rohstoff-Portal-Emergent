 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CON_KEY extends RB_KM {
	
	public CON_KEY() throws myException {
		super(_TAB.container);
	}
}
 
