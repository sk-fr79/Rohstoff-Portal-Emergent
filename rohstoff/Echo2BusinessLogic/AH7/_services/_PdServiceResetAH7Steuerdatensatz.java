/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

/**
 * @author martin
 *
 */
public class _PdServiceResetAH7Steuerdatensatz {
	
	public MyE2_MessageVector resetSteuerDatei(Rec21 ah7_steuerdatei) throws myException {
		MyE2_MessageVector mv = new  MyE2_MessageVector();
		ah7_steuerdatei
		._nv(AH7_STEUERDATEI.id_ah7_profil, "", mv)
		._nv(AH7_STEUERDATEI.drucke_blatt2, "N", mv)
		._nv(AH7_STEUERDATEI.drucke_blatt3, "N", mv)
		._nv(AH7_STEUERDATEI.id_1_abfallerzeuger, "", mv)
		._nv(AH7_STEUERDATEI.id_1_import_empfaenger, "", mv)
		._nv(AH7_STEUERDATEI.id_1_verbr_veranlasser, "", mv)
		._nv(AH7_STEUERDATEI.id_1_verwertungsanlage, "", mv)
		._nv(AH7_STEUERDATEI.id_2_abfallerzeuger, "", mv)
		._nv(AH7_STEUERDATEI.id_2_import_empfaenger, "", mv)
		._nv(AH7_STEUERDATEI.id_2_verbr_veranlasser, "", mv)
		._nv(AH7_STEUERDATEI.id_2_verwertungsanlage, "", mv)
		._nv(AH7_STEUERDATEI.id_3_abfallerzeuger, "", mv)
		._nv(AH7_STEUERDATEI.id_3_import_empfaenger, "", mv)
		._nv(AH7_STEUERDATEI.id_3_verbr_veranlasser, "", mv)
		._nv(AH7_STEUERDATEI.id_3_verwertungsanlage, "", mv)
		._nv(AH7_STEUERDATEI.locked, "N", mv)
		._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv)
		._SAVE(true, mv)
		;
		return mv;
	}
	
}
