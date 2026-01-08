package panter.gmbh.basics4project.SANKTION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import panter.gmbh.BasicInterfaces.Service.PdServiceCleanNormalizeAndSeparateStrings;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceSanktionStatusCheck;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Check_In_SanktionDB_4_Name implements PdServiceSanktionStatusCheck {

	@Override
	public VEK<SANKTION_Treffer> check(MyE2_MessageVector mv, Rec21_adresse p_record_2_check) throws myException {

		VEK<SANKTION_Treffer> vErg = new VEK<>();

		vErg.clear();

		VEK<String> komplettname_from_record = new VEK<String>()
				._a(p_record_2_check.get_ufs_kette(" ", true, ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2, ADRESSE.name3))
				;

		PdServiceCleanNormalizeAndSeparateStrings normalize_service = new PdServiceCleanNormalizeAndSeparateStrings()._addStdReplacers();
		
		VEK<String> normalized_string = 
				normalize_service.getCompleteCycleLongestN(komplettname_from_record,SANKTION_Const.max_word_length(),SANKTION_Const.minimal_length());

		boolean has_enough_data = false;
		has_enough_data = normalized_string.size()>0;
		if(has_enough_data) {
			has_enough_data = ! normalized_string.get(0).equals(normalized_string.get(1));
		}
		
		if(has_enough_data) {
			if(normalized_string.size()>0) {

				//			DEBUG.System_println(p_record_2_check.get_key_value());
				String abfrage = build_abfrage_v2(normalized_string);

				if(S.isFull(abfrage)) {
					try {
						SANKTION_PdToolbox sanktion_db_toolbox = new SANKTION_PdToolbox();
						String[][] erg = sanktion_db_toolbox.EinzelAbfrageInArray(abfrage,"",false);
						if(erg != null && erg.length>0) {
							
							VEK<String> vSanktion_erg = new VEK<String>()._a(erg[0]);
							
							SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
									._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.TREFFER_NAME)
									._add_treffer_worter(normalized_string)
									._add_sanktion_treffer_worter(vSanktion_erg);

							vErg._a(treffer_erg);
						}
						sanktion_db_toolbox.get_MyConnection().get_oConnection().close();
					}catch(Exception e) {
						SANKTION_Treffer treffer_erg = new SANKTION_Treffer()
								._add_treffer_typ(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG);
						vErg._a(treffer_erg);
						
						mv._addAlarm(e.getMessage());
					}
				} 
			}
		}

		return vErg;
	}


	public String build_abfrage(VEK<String> oNameToCheck) throws myException {

		StringBuffer query_v2 = new StringBuffer("SELECT "+ ENUM_SANKTION_ENCODE_MAP.LISTN.user_text() +" FROM LISTN WHERE (");

//		String like_clause =  "(N1||' '||N2||' '||N3||' '||N4||' '||N5||' '||N6||' '||N7||' '||N8||' '||N9||' '||N10||' '||N11||' '||N12) LIKE";

		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";

		if(oNameToCheck.size()>0) {
			StringBuffer x = new StringBuffer("(");
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+oNameToCheck.get(0)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			x.append(")");
			s1 = (S.isFull(oNameToCheck.get(0)))?x.toString():"";
//			s1 = (S.isFull(oNameToCheck.get(0)))?like_clause + " '%"+ oNameToCheck.get(0) + "%' ":"";
		
		}
		if(!oNameToCheck.get(0).equals(oNameToCheck.get(1))) {
			StringBuffer x = new StringBuffer("(");
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+oNameToCheck.get(1)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			x.append(")");
			s2 = (S.isFull(oNameToCheck.get(1)))?x.toString():"";

//			s2 = (S.isFull(oNameToCheck.get(1)))?like_clause + " '%"+ oNameToCheck.get(1) + "%' ":"";
		}
		if(!oNameToCheck.get(1).equals(oNameToCheck.get(2))) {
			StringBuffer x = new StringBuffer("(");
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+oNameToCheck.get(2)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			x.append(")");
			s3 = (S.isFull(oNameToCheck.get(2)))?x.toString():"";

//			s3 = (S.isFull(oNameToCheck.get(2)))?like_clause + " '%"+ oNameToCheck.get(2) + "%' ":"";
		}
		if(!oNameToCheck.get(2).equals(oNameToCheck.get(3))) {
			StringBuffer x = new StringBuffer("(");
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+oNameToCheck.get(3)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			x.append(")");
			s4 = (S.isFull(oNameToCheck.get(3)))?x.toString():"";

//			s4 = (S.isFull(oNameToCheck.get(3)))?like_clause + " '%"+ oNameToCheck.get(3) + "%' ":"";
		}

		String s1s2 = 	"";
		String s1s3 =	"";
		String s1s4 = 	"";
		String s2s3 = 	"";
		String s2s4 = 	"";
		String s3s4 =	"";

		if(S.isAllFull(s1,s2,s3,s4)) {
			if(!s1.equals(s2)) {
				s1s2 =  s1 + " AND " +  s2  ;
			}
			if(! s1.equals(s3)) {
				s1s3 =  s2 + " AND " +  s3  ;		
			}
			if(! s1.equals(s4)) {
				s1s4 =  s1 + " AND " + s4 ;				
			}	
			if(!s2.equals(s3)) {
				s2s3 =  s2 + " AND " + s3 ;				
			}
			if(!s3.equals(s4)) {
				s2s3 =  s3 + " AND " + s4 ;				
			}
		}else if( S.isAllFull(s1,s2,s3)) {
			if(!s1.equals(s2)) {
				s1s2 =  s1 + " AND " +  s2 ;
			}
			if(! s1.equals(s3)) {
				s1s3 =  s2 + " AND " +  s3  ;		
			}
			if(!s2.equals(s3)) {
				s2s3 = s2 + " AND " + s3 ;				
			}
		}else if(S.isAllFull(s1,s2)) {
			if(!s1.equals(s2)) {
				s1s2 = s1 + " AND " +  s2 ;
			}
		}else {
			s1s2 = s1;
		}

		if(S.isFull(s1s2)) {
			query_v2.append(s1s2);
		}
		if(S.isFull(s1s3)) {
			query_v2.append(") OR ( ").append(s1s3);
		}
		if(S.isFull(s1s4)) {
			query_v2.append(") OR ( ").append(s1s4);
		}
		if(S.isFull(s2s3)) {
			query_v2.append(") OR ( ").append(s2s3);
		}
		if(S.isFull(s2s4)) {
			query_v2.append(") OR ( ").append(s2s4);
		}
		if(S.isFull(s3s4)) {
			query_v2.append(") OR ( ").append(s3s4);
		}



		//		s1s3 = like_clause + " '%"+ oNameToCheck.get(0) + "%' "  ;
		//		if(S.isFull(oNameToCheck.get(2))) {
		//			s1s3 = s1s3 + " AND " +  like_clause + " '%"+ oNameToCheck.get(2) + "%' ";
		//			if(! oNameToCheck.get(3).equals(oNameToCheck.get(2))) {
		//				s3s4 = like_clause + " '%"+ oNameToCheck.get(2) + "%' " ;
		//				if(S.isFull(oNameToCheck.get(3))){
		//					s3s4 = s3s4 + " AND " +  like_clause + " '%"+ oNameToCheck.get(3) + "%' " ;
		//				}
		//			}
		//		}
		//
		//		s1s4 = like_clause + " '%"+ oNameToCheck.get(0) + "%' ";
		//
		//		if(S.isFull(oNameToCheck.get(3))) {
		//			s1s4 = s1s4 + " AND " +  like_clause + " '%"+ oNameToCheck.get(3) + "%'";
		//		}
		//
		//		query_v2.append("WHERE ");
		//
		//		if(S.isAllFull(oNameToCheck.get(0), oNameToCheck.get(1), oNameToCheck.get(2), oNameToCheck.get(3))) {
		//			query_v2
		//			.append(s1s2).append(") OR ( ")
		//			.append(s1s3).append(") OR ( ")
		//			.append(s2s3).append(") OR ( ")
		//			.append(s2s4).append(") OR ( ")
		//			.append(s1s4);
		//			if(! oNameToCheck.get(3).equals(oNameToCheck.get(2))) {
		//				query_v2.append(") OR ( ").append(s3s4);	
		//			}
		//			
		//		}else if(S.isAllFull(oNameToCheck.get(0), oNameToCheck.get(1), oNameToCheck.get(2))) {
		//			query_v2.append(s1s2).append(") OR ( ").append(s1s3).append(") OR ( ").append(s2s3);
		//		}else if(S.isAllFull(oNameToCheck.get(0), oNameToCheck.get(1))){
		//			query_v2.append(s1s2);
		//		}else if(S.isFull(oNameToCheck.get(0))) {
		//			query_v2.append(s1s2);
		//		}else {
		//			return "";
		//		}
		query_v2.append(")");

		DEBUG.System_println("   " + query_v2.toString());

		return query_v2.toString();
	}

	/*
	 * old check, only in case of;
	 		//		SEL abfrage = new SEL().FROM("LISTN");
		//		for(int i=0; i<ENUM_SANKTION_ENCODE_MAP.LISTN.get_fieldList().length;i++) {
		//			abfrage.ADDFIELD("LISTN."+ENUM_SANKTION_ENCODE_MAP.LISTN.get_fieldList()[i]);
		//		}
		//		String where_clause = "Upper(N1||N2||N3||N4||N5||N6||N7||N8||N9||N10||N11||N12)";
		//
		////		String laender_iso_code="";
		////		Rec21 recLand = record_2_check.get_up_Rec20(ADRESSE.id_land, LAND.id_land, false);
		////		if(recLand != null) {
		////			laender_iso_code = recLand.get_ufs_dbVal(LAND.iso_country_code, "").toUpperCase();
		////		}
		//
		////		String is_person = mitarbeiter_oder_private_kunde(record_2_check); 
		//
		////		String[] NamenKette = 	PdServiceNormalizeString.normalisierung(record_2_check.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.vorname)).split("[\\s-_(){}]");
		//		
		////		.INNERJOIN("LISTC", "LISTN.ID", "LISTC.ID")
		////		.INNERJOIN("LIST", "LISTN.ID", "LIST.ID")
		////		.WHERE("LISTC.COUNTRY", COMP.EQ.ausdruck(), "(select COUNTRY.ISO3 from COUNTRY where UPPER(country.iso2)='"+laender_iso_code+"')")
		////		.AND("LIST.TYPE", COMP.EQ.ausdruck(), (is_person))
		//		;
		//		
		//		VEK<IF_Field> field_2_extract = new VEK<IF_Field>()._a(ADRESSE.name1)._a(ADRESSE.name2)._a(ADRESSE.name3)._a(ADRESSE.vorname);
		//		for(int i=0; i<field_2_extract.size(); i++) {
		//			String normalised_name = PdServiceNormalizeString.normalisierung(record_2_check.get_ufs_dbVal(field_2_extract.get(i)));
		//			if(S.isFull(normalised_name)) {
		//				normalised_name = "%" + normalised_name.replaceAll(" ", "%' AND " + where_clause +"LIKE '%") + "%";
		//				abfrage.OR("("+where_clause, COMP.LIKE.ausdruck() ,"'%" + normalised_name + "%')");
		//			}
		//		}
		//		
		//		try {
		//			String[][] erg = sanktion_db_toolbox.EinzelAbfrageInArray(abfrage.s(),"",false);
		//			if(erg != null && erg.length>0) {
		//				mv._addAlarm("Der Kdnr "+ record_2_check.get_key_value() + " Name ist im Sanktion Datenbank");
		//				vErg._a(erg[0]);
		//			}
		//		}catch(Exception e) {
		//			mv._addAlarm(e.getMessage());
		//		}
	 */
	
	public String build_abfrage_v2(VEK<String> vInput) throws myException {

		VEK<VEK<String>> vRueck = new VEK<VEK<String>>();
		
		VEK<String> vIntermediate = new VEK<String>();
		//get all possible pair
		for(int i = 0; i<vInput.size(); i++) {
			for(int j = i; j<vInput.size(); j++) {
				if(vInput.get(i).equals(vInput.get(j))) {
					continue;
				}else {
					vIntermediate._a(new VEK<String>()._a(vInput.get(i)+";"+vInput.get(j)));
				}
			}
		}
		//eliminate duplicate (since java8)
		List<Object> tmp = Arrays.stream(vIntermediate.toArray()).distinct().collect(Collectors.toList());
		
		//convert VEK<String> in VEK<VEK<String>>
		for(int i = 0; i<tmp.size(); i++) {
			vRueck._a(new VEK<String>()._a( ((String)tmp.get(i)).split(";")));
		}
			
		//build request 
		StringBuffer buffRueck = new StringBuffer("SELECT "+ ENUM_SANKTION_ENCODE_MAP.LISTN.user_text() +" FROM LISTN WHERE (");
		
		//for each word pair
		for(int vIndex = 0; vIndex<vRueck.size(); vIndex++) {
			
			//get word pair
			VEK<String> vterm = vRueck.get(vIndex) ;
			
			
			// (N1= word1 OR N2=word1 ... N12=word1) 
			StringBuffer x = new StringBuffer("(");
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+vterm.get(0)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			
			x.append(") AND (");
			
			// (N1= word2 OR N2=word2 ... N12=word2) 
			for(int i=1;i<13;i++) {
				x.append("N"+i+"=").append("'"+vterm.get(1)+"'");
				if(i<12) {
					x.append(" OR ");
				}
			}
			x.append(")");
			
			//if last pair
			if(vIndex == (vRueck.size()-1)) {
				x.append(")");
			}
			//if not, OR with the next pair
			else {
				x.append(") OR ( ");
			}
			
			buffRueck.append(x);
		}
		
		DEBUG.System_println("** " + buffRueck.toString());
		
		return buffRueck.toString();
	}
} 
