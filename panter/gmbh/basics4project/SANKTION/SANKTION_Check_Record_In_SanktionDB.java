package panter.gmbh.basics4project.SANKTION;

import java.util.LinkedHashMap;

import panter.gmbh.BasicInterfaces.Service.PdServiceNormalizeString;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Check_Record_In_SanktionDB {

	private LinkedHashMap<String, VEK<SANKTION_Treffer>> 	ergebnisMap 			= new LinkedHashMap<>();
	private VEK<String> 									sanktion_ergebnis_map 	= new VEK<String>();
	
	private MyE2_MessageVector mv = new MyE2_MessageVector();
	
	private long adresse_id = 0;

	public LinkedHashMap<String, VEK<SANKTION_Treffer>> check(Rec21_adresse rec_adresse)
			throws myException {

		adresse_id = Long.parseLong(rec_adresse.get_key_value());

		MyE2_MessageVector lmv = new MyE2_MessageVector();

		boolean enough_data_2_check = true;

		String Strasse = PdServiceNormalizeString.normalisierung(rec_adresse.get_ufs_dbVal(ADRESSE.strasse, ""));

		boolean is_lager = 			(rec_adresse.getLongDbValue(ADRESSE.adresstyp) == myCONST.ADRESSTYP_LIEFERADRESSE);

		if(is_lager) {
			if(S.isEmpty(Strasse) || Strasse.length()<3) {
				enough_data_2_check = false;
			}
		}else {
			boolean hast_genug_info_fur_name = rec_adresse.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.vorname).toUpperCase().split("[\\s-_(){}]").length>1;
			if(!hast_genug_info_fur_name) {
				if(S.isEmpty(Strasse) || Strasse.length()<3) {
					enough_data_2_check = false;
				}
			}
		}

		//2018-10-10: fehlerkorrektur fuer Sebastien  (hier kommt fuer inaktive records immer ein sanktionsinfo, da eine leere rueckgabemap eine unsinnige meldung hervorruft
		if( /*rec_adresse.is_yes_db_val(ADRESSE.aktiv) &&*/   
				enough_data_2_check) {

			VEK<SANKTION_Treffer> problem_vector = new VEK<SANKTION_Treffer>();

			VEK<SANKTION_Treffer> vGefahrlichAusweis =  new SANKTION_Check_In_SanktionDB_4_Ausweis().check( lmv, rec_adresse);
			if(!vGefahrlichAusweis.isEmpty()) {
				problem_vector._a(vGefahrlichAusweis);
				lmv._addAlarm("Firma " + rec_adresse._getMainAdresse().__get_name1_ort() + "(" + adresse_id + "): Ausweis ist im Sanktion Datenbank");
			}

			VEK<SANKTION_Treffer> vGefahrlichName = new SANKTION_Check_In_SanktionDB_4_Name().check( lmv, rec_adresse);
			if(! vGefahrlichName.isEmpty()) {
				problem_vector._a(vGefahrlichName);
				lmv._addAlarm("Firma " + rec_adresse._getMainAdresse().__get_name1_ort() + "(" + adresse_id + "): Name ist im Sanktion Datenbank");
			}

			VEK<SANKTION_Treffer> vGefahrlichAdresse = new SANKTION_Check_In_SanktionDB_4_Adresse().check( lmv, rec_adresse);
			if(! vGefahrlichAdresse.isEmpty()) {
				problem_vector._a(vGefahrlichAdresse);
				lmv._addAlarm("Firma " + rec_adresse._getMainAdresse().__get_name1_ort() + "(" + adresse_id + "): Adresse ist im Sanktion Datenbank");
			}

			if(problem_vector.size()==0) {
				this.ergebnisMap.put(""+adresse_id, new VEK<SANKTION_Treffer>()._a(new VEK<SANKTION_Treffer>()._a(new SANKTION_Treffer()._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.OK))));
			}else {
				this.ergebnisMap.put(""+adresse_id, problem_vector);
			}
		}

		this.mv._add(lmv);
		
		return ergebnisMap;
	}

	public LinkedHashMap<String, VEK<SANKTION_Treffer>> getErgebnisMap() {
		return ergebnisMap;
	}

	public MyE2_MessageVector get_mv() throws myException{
		return mv;
	}
	
	public VEK<String> get_sanktion_ergebnis_map() {
		return sanktion_ergebnis_map;
	}
}
