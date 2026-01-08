/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.IF_Field;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

/**
 * @author martin
 *
 */
public class AH7__hmFieldListEmptyValues extends HashMap<IF_Field, String> {

	/**
	 * 
	 */
	public AH7__hmFieldListEmptyValues() {
		super();
		this.put(AH7_STEUERDATEI.id_1_verbr_veranlasser,"");
		this.put(AH7_STEUERDATEI.id_1_import_empfaenger,"");
		this.put(AH7_STEUERDATEI.id_1_abfallerzeuger,"");
		this.put(AH7_STEUERDATEI.id_1_verwertungsanlage,"");
		this.put(AH7_STEUERDATEI.drucke_blatt2,"N");
		this.put(AH7_STEUERDATEI.id_2_abfallerzeuger,"");
		this.put(AH7_STEUERDATEI.id_2_import_empfaenger,"");
		this.put(AH7_STEUERDATEI.id_2_verbr_veranlasser,"");
		this.put(AH7_STEUERDATEI.id_2_verwertungsanlage,"");
		this.put(AH7_STEUERDATEI.drucke_blatt3,"N");
		this.put(AH7_STEUERDATEI.id_3_abfallerzeuger,"");
		this.put(AH7_STEUERDATEI.id_3_import_empfaenger,"");
		this.put(AH7_STEUERDATEI.id_3_verbr_veranlasser,"");
		this.put(AH7_STEUERDATEI.id_3_verwertungsanlage,"");
		this.put(AH7_STEUERDATEI.status_relation,AH7__ENUM_STATUSRELATION.UNDEF.db_val());
		this.put(AH7_STEUERDATEI.id_ah7_profil,"");

	}

}
