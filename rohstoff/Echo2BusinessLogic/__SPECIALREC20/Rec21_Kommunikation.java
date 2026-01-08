/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 09.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 09.03.2020
 *
 */
public class Rec21_Kommunikation extends Rec21 {

	public Rec21_Kommunikation() throws myException {
		super(_TAB.kommunikation);
	}

	/**
	 * @author martin
	 * @date 09.03.2020
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_Kommunikation(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.kommunikation) {
			throw new myException(this,"Only Record from type KOMMUNIKATION are allowed !");
		}
	}

	
	public String getTelefonNummerGanz() {
		String tel = null;
		try {
			tel = this.getFs(KOMMUNIKATION.wert_laendervorwahl)
						+" "+this.getFs(KOMMUNIKATION.wert_vorwahl)
						+" "+this.getFs(KOMMUNIKATION.wert_rufnummer);
			tel = tel.trim();
			tel = tel.replace("  ", " ");
		} catch (myException e) {}
		
		return tel;
			
	}
	
}
