/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7._HashMapCreateAllAdressRecs;
import rohstoff.Echo2BusinessLogic.AH7._VEK_allIdAdressFields;
import rohstoff.Echo2BusinessLogic.AH7._HashMapCreateAllAdressRecs.ADRESSKEY;

/**
 * @author martin
 *
 */
public class _PdServiceCheckAH7SteuerrelationIsConsistent {
	
	/**
	 * prueft, ob die adressen in der Steuerdatei bei der momentanen Situation, die sich aus den realen Situation (id_adresse_start_geo und id_adresse_ziel_geo)
	 * korrelieren, z.B. ob eine fremdbesitzer-id vorhanden war, die momentan aus dem lager entfernt wurde
	 * Ebenfalls wird hier eine pruefung gemacht, ob der steuersatz und das profil aufeinander passen
	 * @param recAH7_Steuerdatei
	 * @return
	 * @throws myException
	 */
	public boolean isAH7_steuersatzIsConsistent(Rec21 recAH7_Steuerdatei, MyE2_MessageVector mv) throws myException {
		boolean bRet = true;

		_HashMapCreateAllAdressRecs hmAllRecs = new _HashMapCreateAllAdressRecs(recAH7_Steuerdatei.getUfs(AH7_STEUERDATEI.id_adresse_geo_start), recAH7_Steuerdatei.getUfs(AH7_STEUERDATEI.id_adresse_geo_ziel));

		//alle moeglichen werte fuer adressen innerhalb der relation suchen
		VEK<Long>  vPossibleIds = new VEK<Long>();
		for (ADRESSKEY key: ADRESSKEY.values()) {
			if (hmAllRecs.get(key)!=null) {
				vPossibleIds._a(hmAllRecs.get(key).get_myLong_dbVal(ADRESSE.id_adresse).get_oLong());
			}
		}
		vPossibleIds._a(new Long(bibALL.get_ID_ADRESS_MANDANT()));   //mandantenadresse ist natuerlich auch in der liste der moeglichen
		
		
		for (IF_Field f: new _VEK_allIdAdressFields()) {
			
			if (recAH7_Steuerdatei.get_myLong_dbVal(f, new MyLong("")).isOK()) {
				if (!vPossibleIds.contains(recAH7_Steuerdatei.get_myLong_dbVal(f).get_oLong())) {
					bRet = false;
					mv._addAlarm(S.ms("In der AH7-Steuerungstabelle liegt eine für diese Relation nicht zugelassene Adresse vor !").ut("<"+f.fn()+">"));
				}
			}
		}
		
		
		//weiterhin koennte noch ein profil vergeben sein und dieses wiederum nicht mehr passen (z.B. zugefuegte lager-fremdbesitzer-id)
		if (bRet) {
			Rec21 recAH7_profil = recAH7_Steuerdatei.get_up_Rec21(AH7_PROFIL.id_ah7_profil);
			if (recAH7_profil!=null) {
				_PdServiceCompareAH7ProfileWithAdressesInMask comparer = new _PdServiceCompareAH7ProfileWithAdressesInMask();
				MyE2_MessageVector mv1 = new MyE2_MessageVector();
				boolean comp_match =comparer.isMatching(recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_adresse_geo_start) 
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_adresse_geo_ziel) 
										,recAH7_profil.get_raw_resultValue_Long(AH7_PROFIL.id_ah7_profil) 
										,mv1 
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_verbr_veranlasser)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_import_empfaenger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_abfallerzeuger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_1_verwertungsanlage)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_verbr_veranlasser)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_import_empfaenger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_abfallerzeuger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_2_verwertungsanlage)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_verbr_veranlasser)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_import_empfaenger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_abfallerzeuger)
										,recAH7_Steuerdatei.get_raw_resultValue_Long(AH7_STEUERDATEI.id_3_verwertungsanlage)
										);
					
				if (!(comp_match && mv1.isOK())) {
					bRet = false;
					mv._add(mv1);
				}
				

				
//				bRet = new _PdServiceCheckIfProfileFitsToRelation().isStillFitting(recAH7_profil, recAH7_Steuerdatei, mv);
				if (!bRet) {
					mv._addAlarm(S.ms("Das in der AH7-Steuerungsrelation angegebene Profil stimmt nicht mehr mit dem Inhalt des restlichen Datensatzes überein !"));
				}
			}
		}
		
		
		return bRet;
		
	}
}
