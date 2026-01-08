package panter.gmbh.basics4project.SANKTION;

import java.sql.SQLException;

import panter.gmbh.BasicInterfaces.Service.PdServiceCleanNormalizeAndSeparateStrings;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceSanktionStatusCheck;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Check_In_SanktionDB_4_Adresse implements PdServiceSanktionStatusCheck {


	@Override
	public VEK<SANKTION_Treffer> check(MyE2_MessageVector mv, Rec21_adresse record_2_check) throws myException {
		VEK<SANKTION_Treffer> vErg = new VEK<>();

		MyE2_MessageVector omv = new MyE2_MessageVector();

		Rec21 recLand = record_2_check.get_up_Rec20(ADRESSE.id_land, LAND.id_land, false);

		PdServiceCleanNormalizeAndSeparateStrings string_cleaner = new PdServiceCleanNormalizeAndSeparateStrings()._addStdReplacers();

		int adresstyp = record_2_check.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue();

		String Strasse ="";
		if(adresstyp == myCONST.ADRESSTYP_MITARBEITER) {
			Strasse = record_2_check._getMainAdresse().get_ufs_dbVal(ADRESSE.strasse, "");
		}else {
			Strasse = record_2_check.get_ufs_dbVal(ADRESSE.strasse, "");
		}

		VEK<String> vStrasse = new VEK<String>();
		vStrasse._a(string_cleaner.getCompleteCycleLongestN(new VEK<String>()._a(Strasse),SANKTION_Const.max_word_length(),SANKTION_Const.minimal_length()));

		VEK<String> vOrt = new VEK<String>();
		vOrt._a(string_cleaner.getCompleteCycleLongestN(new VEK<String>()._a(record_2_check.get_ufs_dbVal(ADRESSE.ort, "")),4,3));

		String laender_iso_code = "";
		if(recLand != null) {	
			laender_iso_code = recLand.get_ufs_dbVal(LAND.iso_country_code, "").toUpperCase();
		}

		String abfrage = build_abfrage(vStrasse, vOrt, laender_iso_code);

		try {


			SANKTION_PdToolbox sanktion_toolbox = new SANKTION_PdToolbox();
			if(S.isFull(abfrage)) {
				String[][] erg = sanktion_toolbox.EinzelAbfrageInArray(abfrage,"",false);

				if(erg != null && erg.length>0) {
					omv._addAlarm("Kdnr "+ record_2_check.get_fs_dbVal(ADRESSE.id_adresse)+": Adresse ist im Sanktion Datenbank");
					
					VEK<String> vTreffer_4_hashwert = string_cleaner.getCompleteCycle(vStrasse._a(vOrt)._a(laender_iso_code));
					
					SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
							._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.TREFFER_ADRESSE)
							._add_treffer_worter(vTreffer_4_hashwert)
							._add_sanktion_treffer_worter(new VEK<String>()._a(erg[0]));

					vErg._a(treffer_erg);
				}

				sanktion_toolbox.get_MyConnection().get_oConnection().close();
			}
		} catch (SQLException e) {
			
			SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
					._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG);
			vErg._a(treffer_erg);
			
			mv._addAlarm(e.getMessage());
		}
		return vErg;
	}

	public String build_abfrage(VEK<String> vStrasse, VEK<String>vOrt, String land_code) throws myException {
		String abfrage="";

		if(vStrasse.size()>0 && vOrt.size()>0) {

			String s1 = vStrasse.get(0);
			String s2 = vStrasse.get(1);
			String s3 = vStrasse.get(2);
			String s4 = vStrasse.get(3);

			VEK<VEK<String>> where_combination = new VEK<>();

			if(S.isFull(s2)) {
				if(!s1.equals(s2)) {
					where_combination._a(new VEK<String>()._a(s1)._a(s2));

					if(!s2.equals(s3)) {
						if(S.isFull(s3)) {
							where_combination._a(new VEK<String>()._a(s2)._a(s3))._a(new VEK<String>()._a(s1)._a(s3));

							if(!s3.equals(s4)) {

								if(S.isFull(s4) && !s3.equals(s4)) {
									where_combination._a(new VEK<String>()._a(s1)._a(s4))._a(new VEK<String>()._a(s2)._a(s4))._a(new VEK<String>()._a(s3)._a(s4));	
								}
							}
						}
					}
				}else {
					where_combination._a(new VEK<String>()._a(s1)._a(""));
				}
			}else {
				where_combination._a(new VEK<String>()._a(s1)._a(""));
			}


			String normalized_street_sdb 	= "UPPER(REGEXP_REPLACE(LISTA.STREET,'[^[:alnum:][:space:]+]','')) LIKE ";
			String normalized_city_sdb 		= "UPPER(REGEXP_REPLACE(LISTA.CITY,'[^[:alnum:][:space:]+]',' ')) LIKE ";
			//			String normalized_country_sdb 	= "UPPER(REGEXP_REPLACE(LISTA.COUNTRY,'[^[:alnum:][:space:]+]','')) LIKE ";

			StringBuffer query = new StringBuffer("SELECT ");
			query.append(ENUM_SANKTION_ENCODE_MAP.LISTA.user_text());
			query.append(" FROM LISTA INNER JOIN LISTC ON LISTA.ID = LISTC.ID ");

			StringBuffer where_strasse = new StringBuffer("WHERE ((");
			StringBuffer where_city = new StringBuffer("");
			StringBuffer where_country = new StringBuffer("");

			int i = 0;
			do {
				VEK<String> wc = where_combination.get(i);
				where_strasse.append(normalized_street_sdb + "'%"+wc.get(0)+"%'");
				if(S.isFull(wc.get(1))){
					where_strasse.append(" AND ").append(normalized_street_sdb + "'%"+wc.get(1)+"%'");
				}
				if((i+1) < where_combination.size()) {
					where_strasse.append(") OR (");
				}
				i++;
			}while(i<where_combination.size());
			where_strasse.append(")").append(")");


			if(vOrt.size()>0 && S.isFull(vOrt.get(0))){
				String ort = "";
				String old_or = "";
				for(String or: vOrt) {
					if(! or.equals(old_or)) {
						ort = ort + " " + or;
					}
					old_or = or;
				}
				where_city.append(" AND (").append(normalized_city_sdb).append("'%"+ort.trim()+"%'").append(")");
			}

			where_country
			.append(" AND (LISTA.COUNTRY = (select COUNTRY.ISO3 from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select COUNTRY.ISO2 from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_ECC) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_EN) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_FR) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_SP) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_IT) from COUNTRY where UPPER(country.iso2)='"+land_code+"')")
			.append(" OR UPPER(LISTA.COUNTRY)=(select UPPER(COUNTRY.LAND_SW) from COUNTRY where UPPER(country.iso2)='"+land_code+"'))")
			;

			abfrage = query.append(where_strasse).append(where_city).append(where_country).toString();
		}
		return abfrage;
	}
}



