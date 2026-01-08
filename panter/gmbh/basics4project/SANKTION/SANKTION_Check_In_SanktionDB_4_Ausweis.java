package panter.gmbh.basics4project.SANKTION;

import java.sql.SQLException;

import panter.gmbh.BasicInterfaces.Service.PdServiceCleanNormalizeAndSeparateStrings;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceSanktionStatusCheck;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Check_In_SanktionDB_4_Ausweis implements PdServiceSanktionStatusCheck {


	@Override
	public VEK<SANKTION_Treffer> check(MyE2_MessageVector mv, Rec21_adresse record_2_check) throws myException {

		VEK<SANKTION_Treffer> vErg = new VEK<>();

		if(record_2_check.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue()== myCONST.ADRESSTYP_FIRMENINFO) {
			//passeport
			
			PdServiceCleanNormalizeAndSeparateStrings normalizer = new PdServiceCleanNormalizeAndSeparateStrings()._addStdReplacers();
			
			VEK<String> vNormalized_ausweis_nr = normalizer
			.getCompleteCycle(new VEK<String>()._a(record_2_check.get_ufs_dbVal(ADRESSE.ausweis_nummer, "")));
			
			VEK<String> vNormalized_steuer_nr = normalizer
			.getCompleteCycle(new VEK<String>()._a(record_2_check.getFirmeninfo().get_ufs_dbVal(FIRMENINFO.steuernummer, "")));
					
			boolean ausweis_nummer_ok = (vNormalized_ausweis_nr.size()==1 && vNormalized_ausweis_nr.get(0).length()>3);
			boolean steuer_nummer_ok = (vNormalized_steuer_nr.size()==1 && vNormalized_steuer_nr.get(0).length()>3);		
			
			String aus_nr = 	ausweis_nummer_ok	?	vNormalized_ausweis_nr.get(0):"";

			String steuer_nr = 	steuer_nummer_ok	?	vNormalized_steuer_nr.get(0):"";
			
			if(S.isFull(aus_nr) || S.isFull(steuer_nr)){

				StringBuffer abfrage = new StringBuffer("SELECT "+ENUM_SANKTION_ENCODE_MAP.LISTP.user_text() + " FROM LISTP WHERE ");
				
				if(S.isFull(aus_nr)) {
					//SEL abfrage = new SEL().FROM("LISTP").WHERE("UPPER(PASSNO)", COMP.LIKE.ausdruck(), "'%"+ aus_nr.toUpperCase() + "%'");
					abfrage.append(" UPPER(REGEXP_REPLACE(LISTP.PASSNO,'^[:alnum:]','')) LIKE "+ "'%"+ aus_nr.toUpperCase() + "%'");
				}
				if(S.isAllFull(aus_nr, steuer_nr)) {
					abfrage.append(" AND ");
				}
				if(S.isFull(steuer_nr)) {
					abfrage.append(" UPPER(REGEXP_REPLACE(LISTP.PASSNO,'^[:alnum:]','')) LIKE " + "'%"+ steuer_nr.toUpperCase() + "%'");
				}


				try {
					SANKTION_PdToolbox sanktion_db_toolbox = new SANKTION_PdToolbox();

					String[][] erg = sanktion_db_toolbox.EinzelAbfrageInArray(abfrage.toString(),"",false);

					if(erg != null && erg.length>0 ) {
						mv._addAlarm("Die Adress-ID "+ record_2_check.get_key_value() +" besitzt eine Ausweis-Nummer mit einem Treffer in der Sanktionsdatenbank!");

						SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
								._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.TREFFER_AUSWEIS)
								._add_treffer_worter(new VEK<String>()._a(aus_nr)._a(steuer_nr))
								._add_sanktion_treffer_worter(new VEK<String>()._a(erg[0]));

						vErg._a(treffer_erg);
					}
					sanktion_db_toolbox.get_MyConnection().get_oConnection().close();

				} catch (SQLException e) {
					
					SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
							._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG);
					vErg._a(treffer_erg);
					
					mv._addAlarm(e.getMessage());
				}
			}
		}
		return vErg;
	}


}
