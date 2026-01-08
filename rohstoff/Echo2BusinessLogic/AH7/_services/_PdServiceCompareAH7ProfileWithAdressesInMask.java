/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7__hmTranslateProfileToRealAdressIds;

/**
 * @author martin
 *
 */
public class _PdServiceCompareAH7ProfileWithAdressesInMask {

	/**
	 * 
	 * @param id_adresse_start_geo
	 * @param id_adresse_ziel_geo
	 * @param id_ah7_profile
	 * @param mv
	 * @param id_1_verbringsveranlasser
	 * @param id_1_importeur
	 * @param id_1_abfallerzeuger
	 * @param id_1_verwertungsanlage
	 * @param id_2_verbringsveranlasser
	 * @param id_2_importeur
	 * @param id_2_abfallerzeuger
	 * @param id_2_verwertungsanlage
	 * @param id_3_verbringsveranlasser
	 * @param id_3_importeur
	 * @param id_3_abfallerzeuger
	 * @param id_3_verwertungsanlage
	 * @return
	 * @throws myException
	 */
	public boolean isMatching(	 Long id_adresse_start_geo
								,Long id_adresse_ziel_geo
								,Long id_ah7_profile
								,MyE2_MessageVector mv_top
								,Long id_1_verbringsveranlasser
								,Long id_1_importeur
								,Long id_1_abfallerzeuger
								,Long id_1_verwertungsanlage
								,Long id_2_verbringsveranlasser
								,Long id_2_importeur
								,Long id_2_abfallerzeuger
								,Long id_2_verwertungsanlage
								,Long id_3_verbringsveranlasser
								,Long id_3_importeur
								,Long id_3_abfallerzeuger
								,Long id_3_verwertungsanlage
								) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		boolean ret = true;
		
		if (O.isOneNull(id_adresse_start_geo, id_adresse_ziel_geo, id_ah7_profile) || id_adresse_start_geo<=0 || id_adresse_ziel_geo <=0 || id_ah7_profile<=0)  {
			throw new myException(this,"Error: all IDs MUST NOT BE NULL  or 0 !!");
		}
		
		AH7__hmTranslateProfileToRealAdressIds translater = new AH7__hmTranslateProfileToRealAdressIds(id_adresse_start_geo.toString(), id_adresse_ziel_geo.toString(), new Rec21(_TAB.ah7_profil)._fill_id(id_ah7_profile), mv);
		
		if (mv.isOK()) {
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_1_verbr_veranlasser), id_1_verbringsveranlasser, 	mv, "Block 1 Verbringsveranlasser");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_1_import_empfaenger), id_1_importeur, 				mv, "Block 1 Importuer/Empfänger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_1_abfallerzeuger),    id_1_abfallerzeuger, 			mv, "Block 1 Abfallerzeuger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_1_verwertungsanlage), id_1_verwertungsanlage, 		mv, "Block 1 Verwertungsanlage");
			
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_2_verbr_veranlasser), id_2_verbringsveranlasser, 	mv, "Block 2 Verbringsveranlasser");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_2_import_empfaenger), id_2_importeur, 				mv, "Block 2 Importuer/Empfänger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_2_abfallerzeuger),    id_2_abfallerzeuger, 			mv, "Block 2 Abfallerzeuger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_2_verwertungsanlage), id_2_verwertungsanlage, 		mv, "Block 2 Verwertungsanlage");
		
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_3_verbr_veranlasser), id_3_verbringsveranlasser, 	mv, "Block 3 Verbringsveranlasser");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_3_import_empfaenger), id_3_importeur, 				mv, "Block 3 Importuer/Empfänger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_3_abfallerzeuger),    id_3_abfallerzeuger, 			mv, "Block 3 Abfallerzeuger");
			ret = ret && this.compare(translater.get(AH7_STEUERDATEI.id_3_verwertungsanlage), id_3_verwertungsanlage, 		mv, "Block 3 Verwertungsanlage");
		
		}
		mv_top._add(mv);
		
		return ret;
	}
	
	
	private boolean compare(String id_adresse_formated, Long id_adresse, MyE2_MessageVector mv, String fieldnameOfComparedField) {
		if (S.isEmpty(id_adresse_formated) && id_adresse==null) {
			return true;
		} else if ( (S.isEmpty(id_adresse_formated) && id_adresse!=null) || S.isFull(id_adresse_formated) && id_adresse==null )  {
			mv._addAlarm(S.ms("Fehlende Übereinstimmung zwischen Profil und Maske im Feld: "+fieldnameOfComparedField));
			return false;
		} else {
			MyLong lVal = new MyLong(id_adresse_formated);
			if (lVal.isOK()) {
				if (lVal.get_lValue()==id_adresse) {
					return true;
				} else {
					mv._addAlarm(S.ms("Fehlende Übereinstimmung zwischen Profil und Maske im Feld: "+fieldnameOfComparedField));
					return false;
				}
			} else {
				mv._addAlarm(S.ms("Fehler bei der Prüfung zwischen Profil und Maske im Feld: "+fieldnameOfComparedField));
				return false;
			}
		}
	}
	
}
