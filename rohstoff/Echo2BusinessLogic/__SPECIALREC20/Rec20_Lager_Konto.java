/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 21.02.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;




/**
 * Überschrieben, zum Typisieren
 * @author manfred
 * @date 21.02.2018
 *
 */

public class Rec20_Lager_Konto extends Rec20 {

	/**
	 * 
	 * @author manfred
	 * @date 21.02.2018
	 *
	 * @throws myException
	 */
	public Rec20_Lager_Konto() throws myException{
		super(_TAB.lager_konto);
	}

	 /**
	 * @author manfred
	 * @date 21.02.2018
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_Lager_Konto(Rec20 baseRec) throws myException {
		super(baseRec);
	}


}
