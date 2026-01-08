/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_TEILNEHMER;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Profil;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 */
public class AH7__hmTranslateProfileToRealAdressIds extends HashMap<IF_Field, String> {

	
	
	
	/**
	 * @throws myException 
	 * generats hashMap with key=IF_Field and value=formated adress-id or ""
	 */
	public AH7__hmTranslateProfileToRealAdressIds(String idStartGeoUF, String idZielGeoUF, Rec21 recProfile, MyE2_MessageVector mv) throws myException {
		super();
		
		//alle durchgehen und vergleichen
		Rec21_adresse 	recAdresseStartGeo = (Rec21_adresse)new Rec21_adresse()._fill_id(idStartGeoUF);
		Rec21_adresse 	recAdresseStartJur = recAdresseStartGeo.__get_main_adresse();
		Rec21_adresse	recAdresseStartBesitzGeo = recAdresseStartGeo.getRec21LagerDrittBesitzer();
		
		Rec21_adresse 	recAdresseZielGeo =  (Rec21_adresse)new Rec21_adresse()._fill_id(idZielGeoUF);
		Rec21_adresse 	recAdresseZielJur =  recAdresseZielGeo.__get_main_adresse();
		Rec21_adresse	recAdresseZielBesitzGeo = recAdresseZielGeo.getRec21LagerDrittBesitzer();
		

		String idSGeo = recAdresseStartGeo.get_fs_dbVal(ADRESSE.id_adresse);
		String idSJur = recAdresseStartJur.get_fs_dbVal(ADRESSE.id_adresse);
		String idSGeoBesitz = null;
		if (recAdresseStartBesitzGeo!=null) {
			idSGeoBesitz=recAdresseStartBesitzGeo.get_fs_dbVal(ADRESSE.id_adresse);
		}
		
		String idZGeo = recAdresseZielGeo.get_fs_dbVal(ADRESSE.id_adresse);
		String idZJur = recAdresseZielJur.get_fs_dbVal(ADRESSE.id_adresse);
		String idZGeoBesitz = null;
		if (recAdresseZielBesitzGeo!=null) {
			idZGeoBesitz=recAdresseZielBesitzGeo.get_fs_dbVal(ADRESSE.id_adresse);
		}
		
		
		
		finder find = new finder(idSGeo,idSJur,idSGeoBesitz, idZGeo,idZJur,idZGeoBesitz);
		
		Rec21_AH7_Profil rp = new Rec21_AH7_Profil(recProfile);
			
		AH7__hmFieldListEmptyValues emptyMap = new AH7__hmFieldListEmptyValues();
		
		//zuerst alles leermachen
		for (IF_Field f: emptyMap.keySet() ) {
			this.put(f,emptyMap.get(f));
		}
		
		
		if (rp.isBlock_1_vollstaendig()) {
			this.put(AH7_STEUERDATEI.id_1_verbr_veranlasser, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verbr_veranlasser_1))));
			this.put(AH7_STEUERDATEI.id_1_import_empfaenger, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.import_empfaenger_1))));
			this.put(AH7_STEUERDATEI.id_1_abfallerzeuger, 		find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.abfallerzeuger_1))));
			this.put(AH7_STEUERDATEI.id_1_verwertungsanlage, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verwertungsanlage_1))));
			if (rp.isBlock_2_vollstaendig()) {
				this.put(AH7_STEUERDATEI.drucke_blatt2, 			"Y");
				this.put(AH7_STEUERDATEI.id_2_abfallerzeuger, 		find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.abfallerzeuger_2))));
				this.put(AH7_STEUERDATEI.id_2_import_empfaenger, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.import_empfaenger_2))));
				this.put(AH7_STEUERDATEI.id_2_verbr_veranlasser, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verbr_veranlasser_2))));
				this.put(AH7_STEUERDATEI.id_2_verwertungsanlage, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verwertungsanlage_2))));
			}  
			if (rp.isBlock_3_vollstaendig()) {
				this.put(AH7_STEUERDATEI.drucke_blatt3, 			"Y");
				this.put(AH7_STEUERDATEI.id_3_abfallerzeuger, 		find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.abfallerzeuger_3))));
				this.put(AH7_STEUERDATEI.id_3_import_empfaenger, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.import_empfaenger_3))));
				this.put(AH7_STEUERDATEI.id_3_verbr_veranlasser, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verbr_veranlasser_3))));
				this.put(AH7_STEUERDATEI.id_3_verwertungsanlage, 	find.ID(AH7__ENUM_TEILNEHMER.getTeilnehmer(rp.get_fs_dbVal(AH7_PROFIL.verwertungsanlage_3))));
			}
			this.put(AH7_STEUERDATEI.status_relation, 	rp.get_fs_dbVal(AH7_PROFIL.status_relation));
			this.put(AH7_STEUERDATEI.id_ah7_profil, 	rp.get_fs_dbVal(AH7_PROFIL.id_ah7_profil));
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Unvollständiges Profile, Abbruch")));
		}
	}

	
	/**
	 * prueft, ob evtl nur ein teil der vom profil geforderten adressen aufgeloest werden konnte 
	 * @return
	 */
	public boolean isBlock1GanzVoll() {
		if (O.isNoOneNull(	this.get(AH7_STEUERDATEI.id_1_verbr_veranlasser)
							,this.get(AH7_STEUERDATEI.id_1_import_empfaenger)
							,this.get(AH7_STEUERDATEI.id_1_abfallerzeuger)
							,this.get(AH7_STEUERDATEI.id_1_verwertungsanlage))) {
			return true;
		}
		return false;
	}
	
	
	public boolean isBlock2GanzVollOderLeer() {
		if (O.isNoOneNull(	this.get(AH7_STEUERDATEI.id_2_verbr_veranlasser)
							,this.get(AH7_STEUERDATEI.id_2_import_empfaenger)
							,this.get(AH7_STEUERDATEI.id_2_abfallerzeuger)
							,this.get(AH7_STEUERDATEI.id_2_verwertungsanlage)) 
			|| 
			O.isAllNull(this.get(AH7_STEUERDATEI.id_2_verbr_veranlasser)
						,this.get(AH7_STEUERDATEI.id_2_import_empfaenger)
						,this.get(AH7_STEUERDATEI.id_2_abfallerzeuger)
						,this.get(AH7_STEUERDATEI.id_2_verwertungsanlage))) {
			return true;
		}
		return false;
	}
	

	
	public boolean isBlock3GanzVollOderLeer() {
		if (O.isNoOneNull(	this.get(AH7_STEUERDATEI.id_3_verbr_veranlasser)
							,this.get(AH7_STEUERDATEI.id_3_import_empfaenger)
							,this.get(AH7_STEUERDATEI.id_3_abfallerzeuger)
							,this.get(AH7_STEUERDATEI.id_3_verwertungsanlage)) 
			|| 
			O.isAllNull(this.get(AH7_STEUERDATEI.id_3_verbr_veranlasser)
						,this.get(AH7_STEUERDATEI.id_3_import_empfaenger)
						,this.get(AH7_STEUERDATEI.id_3_abfallerzeuger)
						,this.get(AH7_STEUERDATEI.id_3_verwertungsanlage))) {
			return true;
		}
		return false;
	}
	

	
	
	private class finder {
		private String idSGeo = null;
		private String idSJur = null;
		private String pidSGeoBesitz_cf = null;
		private String idZGeo = null;
		private String idZJur = null;
		private String pidZGeoBesitz_cf = null;
		
		/**
		 * @param pidSGeo_cf
		 * @param pidSJur_cf
		 * @param pidSGeoBesitz_cf   //falls vorhanden, dann id_adresse_lager_besitz, sonst null
		 * @param pidZGeo_Cf
		 * @param pidZJur_cf
		 * @param pidZGeoBesitz_cf   //falls vorhanden, dann id_adresse_lager_besitz, sonst null
		 */
		public finder(String pidSGeo_cf, String pidSJur_cf, String pidSGeoBesitz_cf, String pidZGeo_Cf, String pidZJur_cf, String pidZGeoBesitz_cf) {
			super();
			this.idSGeo = pidSGeo_cf;
			this.idSJur = pidSJur_cf;
			this.pidSGeoBesitz_cf = pidSGeoBesitz_cf;
			this.idZGeo = pidZGeo_Cf;
			this.idZJur = pidZJur_cf;
			this.pidZGeoBesitz_cf = pidZGeoBesitz_cf;
		
		}

		public String ID(AH7__ENUM_TEILNEHMER t) throws myException {
			String id = null;
			
			if (t==null) {
				return "";
			}
			
			switch (t) {
			case ADRESSE_MANDANT:
				id = new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_cF_LongString();
				break;
			case ADRESSE_START_GEO:
				id = this.idSGeo;
				break;
			case ADRESSE_START_GEO_DRITTBESITZ:
				id = this.pidSGeoBesitz_cf;
				break;
			
			case ADRESSE_START_JUR:
				id = this.idSJur;
				break;
			case ADRESSE_ZIEL_GEO:
				id = this.idZGeo;
				break;
			case ADRESSE_ZIEL_JUR:
				id = this.idZJur;
				break;
			case ADRESSE_ZIEL_GEO_DRITTBESITZ:
				id = this.pidZGeoBesitz_cf;
				break;
				
				
			default:
				throw new myException("AH7__ENUM_TEILNEHMER:finder: unidentified Member!");
			}
			return id;
			
		}
	}
	
}
