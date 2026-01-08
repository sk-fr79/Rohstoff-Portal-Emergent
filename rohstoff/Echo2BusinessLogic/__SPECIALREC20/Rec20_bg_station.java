/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Rec20_bg_station extends Rec20 {

	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20_bg_station() throws myException {
		super(_TAB.bg_station);
	}


	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_bg_station(Rec20 baseRec) throws myException {
		super(baseRec);
	}

}
