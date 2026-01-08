/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class _VEK_allIdAdressFields extends VEK<IF_Field> {

	/**
	 * 
	 */
	public _VEK_allIdAdressFields() {
		super();
		
		this._a(AH7_STEUERDATEI.id_1_abfallerzeuger)
			._a(AH7_STEUERDATEI.id_1_import_empfaenger)
			._a(AH7_STEUERDATEI.id_1_verbr_veranlasser)
			._a(AH7_STEUERDATEI.id_1_verwertungsanlage)
			._a(AH7_STEUERDATEI.id_2_abfallerzeuger)
			._a(AH7_STEUERDATEI.id_2_import_empfaenger)
			._a(AH7_STEUERDATEI.id_2_verbr_veranlasser)
			._a(AH7_STEUERDATEI.id_2_verwertungsanlage)
			._a(AH7_STEUERDATEI.id_3_abfallerzeuger)
			._a(AH7_STEUERDATEI.id_3_import_empfaenger)
			._a(AH7_STEUERDATEI.id_3_verbr_veranlasser)
			._a(AH7_STEUERDATEI.id_3_verwertungsanlage);

	}

	
	
}
