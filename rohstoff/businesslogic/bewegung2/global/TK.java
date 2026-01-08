/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 05.06.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.dataTools.IF_Field;

/**
 * @author martin
 * @date 05.06.2020
 * Fieldkey fuer die text-komponenten
 */
public class TK extends RB_KF {


	private boolean sonderLabel = false;
	
	/**
	 * @author martin
	 * @date 05.06.2020
	 *
	 */
	private TK() {
	}

	public static TK gen(EnTabKeyInMask table, IF_Field field) {
		TK ret = new TK();
		ret	._setHASHKEY(table.dbVal()+"@"+field.fieldName().toUpperCase())
			._setREALNAME(table.dbVal()+"@"+field.fieldName().toUpperCase())
		;
		return ret;
	}
	
	public static TK gen(EnumMaskSonderLabel field) {
		TK ret = new TK();
		ret	._setHASHKEY("ADDON@"+field.name().toUpperCase())
			._setREALNAME("ADDON@"+field.name().toUpperCase())
		;
		ret.sonderLabel=true;
		return ret;
	}

	public boolean isSonderLabel() {
		return sonderLabel;
	}
	
	
}
