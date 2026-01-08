/**
 * rohstoff.businesslogic.bewegung2.global
 * @author manfred
 * @date 12.12.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 12.12.2019
 *
 */
public enum ENUM_SELEKTOR_Lagertyp {
	
		EIGEN("Eigenwarenlager"),
		FREMD("Fremdwarenlager"),
		STRECKE("Streckenlager"),
		;	
	
	private String lesBar = null;
	
	private ENUM_SELEKTOR_Lagertyp(String lesbar) {
		this.lesBar=lesbar;
	}
	
	public String getDescription() {
		return lesBar;
	}
	
}




