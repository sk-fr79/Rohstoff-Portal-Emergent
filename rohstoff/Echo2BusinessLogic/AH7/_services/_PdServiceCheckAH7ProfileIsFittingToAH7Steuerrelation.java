/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_ENUM_SONDERPROFILE;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 * pruefung, ob ein profile zu dem inhalt einer steuertabelle passt
 */
public class _PdServiceCheckAH7ProfileIsFittingToAH7Steuerrelation {
	

	/**
	 * 
	 * @param recAH7_Profil
	 * @param idAdresseStartGeo
	 * @param idAdresseZielGeo
	 * @return
	 * @throws myException
	 */
	public boolean isFitting(Rec21 recAH7_Profil, Long idAdresseStartGeo, Long idAdresseZielGeo) throws myException {

		//eingangspruefung
		if (O.isOneNull(recAH7_Profil, idAdresseStartGeo, idAdresseZielGeo) || recAH7_Profil.is_newRecordSet() || idAdresseStartGeo<=0 || idAdresseZielGeo<=0) {
			throw new myException(this,"All recs and ids MUST NOT be null or empty !!");
		}
		
		
		//6 matching-werte:
		boolean bProfilStartInland = 		recAH7_Profil.is_yes_db_val(AH7_PROFIL.start_inland);
		boolean bProfilZielInland = 		recAH7_Profil.is_yes_db_val(AH7_PROFIL.ziel_inland);
		
		boolean bProfilStartEigenesLager = 	recAH7_Profil.is_yes_db_val(AH7_PROFIL.start_eigenes_lager);
		boolean bProfilZielEigenesLager = 	recAH7_Profil.is_yes_db_val(AH7_PROFIL.ziel_eigenes_lager);
		
		boolean bProfilQuelleSicher = 		recAH7_Profil.is_yes_db_val(AH7_PROFIL.ah7_quelle_sicher);
		boolean bProfilZielSicher = 		recAH7_Profil.is_yes_db_val(AH7_PROFIL.ah7_ziel_sicher);
		
		
		//alle durchgehen und vergleichen
		Rec21_adresse recAdresseStartGeo = new Rec21_adresse()._fill_id(idAdresseStartGeo);
		Rec21_adresse recAdresseZielGeo = new Rec21_adresse()._fill_id(idAdresseZielGeo);
		
		boolean bStartInland= (recAdresseStartGeo.get_myLong_dbVal(ADRESSE.id_land,new MyLong(-1)).get_lValue()==bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(new Long(-2)));
		boolean bZielInland= (recAdresseZielGeo.get_myLong_dbVal(ADRESSE.id_land,new MyLong(-1)).get_lValue()==bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(new Long(-2)));
		
		boolean bStartEigenesLager = recAdresseStartGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
		boolean bZielEigenesLager = recAdresseZielGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
		
		boolean bQuelleSicher = recAdresseStartGeo.is_yes_db_val(ADRESSE.ah7_quelle_sicher);
		boolean bZielSicher = recAdresseZielGeo.is_yes_db_val(ADRESSE.ah7_ziel_sicher);
		
		Rec21_adresse recAdresseDrittbesitzStartGeo = recAdresseStartGeo.getRec21LagerDrittBesitzer();
		Rec21_adresse recAdresseDrittbesitzZielGeo = recAdresseZielGeo.getRec21LagerDrittBesitzer();
		
		boolean drittBesitzStartMatches = (recAdresseDrittbesitzStartGeo!=null && recAH7_Profil.is_yes_db_val(AH7_PROFIL.startlager_fremdbesitz))
											||
										 (recAdresseDrittbesitzStartGeo==null && recAH7_Profil.is_no_db_val(AH7_PROFIL.startlager_fremdbesitz));

		boolean drittBesitzZielMatches = (recAdresseDrittbesitzZielGeo!=null && recAH7_Profil.is_yes_db_val(AH7_PROFIL.ziellager_fremdbesitz))
											||
										 (recAdresseDrittbesitzZielGeo==null && recAH7_Profil.is_no_db_val(AH7_PROFIL.ziellager_fremdbesitz));

		
		//die kriterien fuer die standard-werte:
		boolean bIsWE = 			(! 	recAdresseStartGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()))
				       			&&	(	recAdresseZielGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()));
				
		boolean bIsWA =				( 	recAdresseStartGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()))
					  			&& 	(! 	recAdresseZielGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()));
		
		boolean bIsStrecke = 		(! 	recAdresseStartGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()))
								&&  (! 	recAdresseZielGeo.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()));
		
		String idLandMandant = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF();
		
		boolean bStartImInland = 		recAdresseStartGeo.get_ufs_dbVal(ADRESSE.id_land).equals(idLandMandant);
		boolean bStartImAusland = 		!recAdresseStartGeo.get_ufs_dbVal(ADRESSE.id_land).equals(idLandMandant);

		boolean bZielImInland = 		recAdresseZielGeo.get_ufs_dbVal(ADRESSE.id_land).equals(idLandMandant);
		//boolean bZielImAusland = 		!recAdresseZielGeo.get_ufs_dbVal(ADRESSE.id_land).equals(idLandMandant);
		
		boolean bRet = false;
		
		if (recAH7_Profil.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val())) {
			bRet =     (bProfilStartInland			==	bStartInland) 
					&&  (bProfilZielInland			==	bZielInland) 
					&&  (bProfilStartEigenesLager	==	bStartEigenesLager) 
					&&  (bProfilZielEigenesLager 	== 	bZielEigenesLager)
					&&  (bProfilQuelleSicher 		== 	bQuelleSicher)
					&&  (bProfilZielSicher 			== 	bZielSicher)
					&&  (drittBesitzStartMatches) 
					&&  (drittBesitzZielMatches)
					;
		} else {
			//sonderprofile durchgehen
			if 			(recAH7_Profil.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.WE_PROFILE.db_val())) {
				if (bIsWE && bZielImInland) {
					bRet=true;
				}
			} else if 	(recAH7_Profil.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.WA_PROFILE.db_val())) {
				if (bIsWA && bStartImInland) {
					bRet=true;
				}
			} else if 	(recAH7_Profil.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.STRECKE_START_IM_AUSLAND.db_val())) {
				if (bIsStrecke && bStartImAusland) {
					bRet = true;
				}
			} else if 	(recAH7_Profil.get_ufs_dbVal(AH7_PROFIL.profile4allothers).equals(AH7_ENUM_SONDERPROFILE.STRECKE_START_IM_INLAND.db_val())) {
				if (bIsStrecke && bStartImInland) {
					bRet = true;
				}
			}
		}

		return bRet;

	}
	
}
