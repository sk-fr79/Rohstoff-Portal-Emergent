package panter.gmbh.basics4project.SANKTION;

import java.util.LinkedHashMap;
import java.util.Map;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Ergebnisse extends LinkedHashMap <String, VEK<SANKTION_Treffer>> {

	private RecList21 		adresses_recList 		= null;

	private Rec21_adresse 	hauptadresse_record 	= null;

	private MyE2_MessageVector mv = new MyE2_MessageVector();


	public SANKTION_Ergebnisse() {
		super();
	}

	public SANKTION_Ergebnisse _init_with_mainadress(Rec21_adresse adressMainRecord) throws myException{
		this.hauptadresse_record = new Rec21_adresse(adressMainRecord)._getMainAdresse();

//		DEBUG._print("Eingangsadresse: "+adressMainRecord.getId());
		
		//2018-10-10: fehlerkorrektur fuer Sebastien
		// alt:  this.adresses_recList = new Rec21_adresse(adressMainRecord).getMainEmployeeAndStoreAdresses();
		this.adresses_recList = new Rec21_adresse(this.hauptadresse_record).getMainEmployeeAndStoreAdresses();

		this.mv.clear();

		SANKTION_Check_Record_In_SanktionDB record_checker = new SANKTION_Check_Record_In_SanktionDB();

		for(Rec21 adresseRecord : adresses_recList) {
//			DEBUG._print("Teil von Adress-Sammlung: "+adresseRecord.getId());

			this.putAll(record_checker.check(new Rec21_adresse(adresseRecord)));
		}


		for(String id_erg : record_checker.getErgebnisMap().keySet()) {
			if(record_checker.getErgebnisMap().get(id_erg).size()>0) {
				this.putAll(record_checker.getErgebnisMap());
				//				this.put(id_erg, record_checker.get_sanktion_ergebnis_map().toString());
			}
		}
		return this;

	}

	public MyE2_MessageVector getMv() {
		return mv;
	}

	public boolean containsSanktion() throws myException{
		
		//2018-10-10: fehlerkorrektur fuer Sebastien: der ausgangswert muss hier false, da sonst bei einer leeren trefferliste (z.B. aufgrund zu weniger daten) immer ein hasSanktion=true
		//                                            zurueckommt
		//Falsch:  boolean has_sanktion = true;
		boolean has_sanktion = false;

		for(Map.Entry<String,VEK<SANKTION_Treffer>> ergebnis_entry: this.entrySet()) {
			if (ergebnis_entry.getValue().size()==1) {
				if(! ergebnis_entry.getValue().get(0).isOk()) {
					return true;
				}else {
					has_sanktion = false;
				}
			}else {
				return true;
			}
		}
		return has_sanktion;
	}

	public Rec21_adresse get_main_adresse() throws myException{
		return this.hauptadresse_record;
	}

	/**
	 * @return true wenn die Adresse hat die Freigabe
	 * @throws myException
	 */
	public boolean writeSanktionToDatabase() throws myException{

		SEL query = new SEL()
				.FROM(_TAB.sanktion_pruefung)
				.WHERE(new vgl(SANKTION_PRUEFUNG.id_adresse,hauptadresse_record.get_key_value()))
				.AND(new vgl_YN(SANKTION_PRUEFUNG.aktiv,true))
				.AND(new vgl(SANKTION_PRUEFUNG.id_mandant,bibALL.get_ID_MANDANT()));

		Rec21 rec_sanktion_pruefung = new Rec21(_TAB.sanktion_pruefung)
				._fill_sql(query.s());

		if(rec_sanktion_pruefung.is_ExistingRecord()) {
			if(containsSanktion()) {
				//1)get hashes

				VEK<SANKTION_Treffer> ergebnisse = this.get(hauptadresse_record.get_key_value());

				StringBuffer sanktion_hashwert = new StringBuffer(); 
				for(SANKTION_Treffer treff : ergebnisse) {
					if(!treff.isOk()) {
						sanktion_hashwert.append(treff.get_sdb_erg_4_hashwert());
					}
				}

				String adresse_old_hash 	= rec_sanktion_pruefung.get_ufs_dbVal(SANKTION_PRUEFUNG.hashwert_adresse,"");
				String adresse_actual_hash 	= hauptadresse_record.generate_sha256_hashwert();

				String sanktion_old_hash	= rec_sanktion_pruefung.get_ufs_dbVal(SANKTION_PRUEFUNG.hashwert_sanktion,"");
				String sanktion_actual_hash = new SANKTION_Hashwert_SHA256_generator().generate_sha256_hashcode(sanktion_hashwert.toString());

				//2) compare hashes: 
				//if equals, then return actual freigabe status 
				boolean adresse_hash_changed = !(adresse_old_hash.equals(adresse_actual_hash));
				boolean sanktion_hash_changed = !(sanktion_old_hash.equals(sanktion_actual_hash));

				if((adresse_hash_changed==false) && (sanktion_hash_changed==false)) {
					return rec_sanktion_pruefung.is_yes_db_val(SANKTION_PRUEFUNG.freigabe);
				}

				//else, actual entry on inactiv, freigabe NO and save the modification as a new entry
				else {
					rec_sanktion_pruefung	._nv(SANKTION_PRUEFUNG.aktiv, 		"N",	mv)
					._nv(SANKTION_PRUEFUNG.freigabe, 	"N",	mv);

					rec_sanktion_pruefung	._SAVE(true, mv);

					mv._add(write_new_entry());

					return false;
				}
			}else {
				//else sanktion auf inaktiv schalten und die adresse ist ok (korrektur von der fehleraft eintrag)
				rec_sanktion_pruefung._nv(SANKTION_PRUEFUNG.aktiv, "N",	mv)._SAVE(true, mv);
				return true;
			}
		}else {
			if(containsSanktion()) {

				mv._add(write_new_entry());

				return false;	
			}
			return true;
		}
	}

	private MyE2_MessageVector write_new_entry() throws myException{

		MyE2_MessageVector omv_ = new MyE2_MessageVector();

		Rec21 neu_sanktion_record = new Rec21(_TAB.sanktion_pruefung);

		for(Map.Entry<String,VEK<SANKTION_Treffer>> ergebnis_entry: this.entrySet()) {
			VEK<Rec21> neuen_sanktion_pos_list = new VEK<Rec21>();
			int adresse_typ = new Rec21(_TAB.adresse)._fill_id(ergebnis_entry.getKey()).get_myLong_dbVal(ADRESSE.adresstyp).get_iValue();
			if(adresse_typ == myCONST.ADRESSTYP_FIRMENINFO) {

				VEK<SANKTION_Treffer> vTreffer = this.get(hauptadresse_record.get_key_value());
				StringBuffer sanktion_hashwert = new StringBuffer(); 
				for(SANKTION_Treffer treff :  vTreffer) {
					if(!treff.isOk()) {
						sanktion_hashwert.append(treff.get_sdb_erg_4_hashwert());
						break;
					}
				}

				String sanktion_new_hash = new SANKTION_Hashwert_SHA256_generator().generate_sha256_hashcode(sanktion_hashwert.toString());
				String adresse_new_hash = this.hauptadresse_record.generate_sha256_hashwert();

				neu_sanktion_record
				._nv(SANKTION_PRUEFUNG.aktiv,				"Y", 									omv_)
				._nv(SANKTION_PRUEFUNG.freigabe,			"N",									omv_)
				._nv(SANKTION_PRUEFUNG.id_adresse, 			ergebnis_entry.getKey(), 				omv_)
				._nv(SANKTION_PRUEFUNG.hashwert_adresse, 	adresse_new_hash, 						omv_)
				._nv(SANKTION_PRUEFUNG.hashwert_sanktion, 	sanktion_new_hash, 						omv_)
				._nv(SANKTION_PRUEFUNG.geprueft_am, 		bibALL.get_cDateNOW(), 					omv_)
				;

				neu_sanktion_record._SAVE(true, mv);

			}

			for(SANKTION_Treffer erg_weg:ergebnis_entry.getValue()) {
				if(! erg_weg.isOk()) {
					Rec21 neu_sanktion_pos_record = new Rec21(_TAB.sanktion_pruefung_pos)
							._nv(SANKTION_PRUEFUNG_POS.id_adresse, ergebnis_entry.getKey(), 							omv_)
							._nv(SANKTION_PRUEFUNG_POS.sanktion_weg, erg_weg.getTreffer_typ().db_val(), 				omv_)
							._nv(SANKTION_PRUEFUNG_POS.sanktion_schluesselwort, erg_weg.getSanktion_treffer_worter(), 	omv_)
							._nv(SANKTION_PRUEFUNG_POS.adresse_schluesselwort, erg_weg.getTreffer_worter(), 			omv_)
							;
					neu_sanktion_pos_record._setNewValueInDatabaseTerminus(SANKTION_PRUEFUNG_POS.id_sanktion_pruefung, _TAB.sanktion_pruefung.seq_currval());
					neuen_sanktion_pos_list._a(neu_sanktion_pos_record);
				}
			}
			if(omv_.get_bIsOK()) {
				if(neuen_sanktion_pos_list.size()>0) {
					for(Rec21 rec_2_save: neuen_sanktion_pos_list) {
						rec_2_save._SAVE(false, omv_);
					}
				}
			}
			if(omv_.get_bIsOK()) {
				bibDB.Commit();
			}
		}
		return omv_;
	}

	public SANKTION_Ergebnisse _add_dummy_eintraege(VEK<String> vIdAdresse) throws myException{
		for(String id_adresse: vIdAdresse) {
			this.put(id_adresse, new VEK<SANKTION_Treffer>()._a(new VEK<SANKTION_Treffer>()._a(new SANKTION_Treffer()._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.OK))));
		}
		return this;
	}
}