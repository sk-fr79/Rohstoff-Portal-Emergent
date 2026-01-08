/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 21.02.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;




/**
 * Überschrieben, zum Typisieren
 * @author manfred
 * @date 06.11.2019
 *
 */

public class Rec21_Lager_Konto extends Rec21 {

	/**
	 * 
	 * @author manfred
	 * @date 06.11.2019
	 *
	 * @throws myException
	 */
	public Rec21_Lager_Konto() throws myException{
		super(_TAB.lager_konto);
	}

	 /**
	 * @author manfred
	 * @date 06.11.2019
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_Lager_Konto(Rec21 baseRec) throws myException {
		super(baseRec);
	}


}
