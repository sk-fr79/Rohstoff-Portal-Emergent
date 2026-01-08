/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 01.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 01.04.2019
 *
 */
public enum EnTpVerantwortung  implements IF_enumForDb<EnTpVerantwortung> {

	QUELLE("Lieferant")
	,ZIEL("Abnehmer")
	,MANDANT("<mandant>")
	
	;

	private String lesBar = null;
	
	private EnTpVerantwortung(String lesbar) {
		this.lesBar=lesbar;
	}

	@Override
	public String userText() {
		String nameMandant = "<mandant>";
		try {
			nameMandant = bibALL.get_RECORD_MANDANT().get___KETTE(panter.gmbh.basics4project.DB_ENUMS.MANDANT.name1,panter.gmbh.basics4project.DB_ENUMS.MANDANT.name2);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		if (this==MANDANT) {
			return nameMandant;
		} else {
			return lesBar;
		}
	}
	
	@Override
	public EnTpVerantwortung[] getValues() {
		return EnTpVerantwortung.values();
	}

}
