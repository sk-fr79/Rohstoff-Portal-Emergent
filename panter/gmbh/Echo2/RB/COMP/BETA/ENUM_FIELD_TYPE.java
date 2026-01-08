package panter.gmbh.Echo2.RB.COMP.BETA;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF_CELL;

public enum ENUM_FIELD_TYPE  {
	STRING_VALUE("#str|@#", "str")
	,DATUM_VALUE("#dat|@#", "dat")
	,NUMBER_VALUE("#num|@#", "num")
	,BOOLEAN_VALUE("#boo|@#", "boo")
	;

	private String val, typ ="";

	private ENUM_FIELD_TYPE(String p_val, String p_typ) {
		this.val = p_val;
		this.typ = p_typ;
	}

	public String get_value() {
		return val;
	}


	public String get_typ() {
		return typ;
	}

	public HashMap<String,String> get_enum_map(){
		HashMap<String, String> hm = new HashMap<String,String>();
		for(ENUM_FIELD_TYPE val: ENUM_FIELD_TYPE.values()) {
			hm.put(val.get_typ(), val.get_value());
		}
		return hm;
	}

	private static String get_value(Rec20 record_datei, String... field_list_with_type) throws myException{
		String ret_str= "";

		String typ = field_list_with_type[0];
		String[] field_list = new String[field_list_with_type.length-1];

		for(int i = 1; i<field_list_with_type.length;i++) {
			field_list[i-1] = field_list_with_type[i];
		}
		for(int i = 0; i<field_list.length;i++) {
			if(typ.equals(STRING_VALUE.get_typ())){
				ret_str = ret_str + record_datei.get_ufs_dbVal(record_datei.get_field(field_list[i]).get_field(), "");
			}else if(typ.equals(BOOLEAN_VALUE.get_typ())){
				ret_str = ret_str + (record_datei.is_yes_db_val(record_datei.get_field(field_list[i]).get_field())?"Ja": "Nein");
			}else if(typ.equals(DATUM_VALUE.get_typ())) {
				ret_str = ret_str + record_datei.get_myDate_dbVal(record_datei.get_field(field_list[i]).get_field()).get_cDateStandardFormat();
			}else if(typ.equals(NUMBER_VALUE.get_typ())) {
				ret_str = ret_str + record_datei.get_myLong_dbVal(record_datei.get_field(field_list[i]).get_field()).get_cUF_LongString();
			}else {
				ret_str = ret_str + record_datei.get_ufs_dbVal(record_datei.get_field(field_list[i]).get_field(), "");
			}
			
			if( i == (field_list[i].length()-1)) {
				ret_str = ret_str +"";
			}else {
				ret_str = ret_str +" ";
			}
		}
		return ret_str;
	}

	public static String process_vartext(Rec20 record_definition, Rec20 record_datei) throws myException{
		Rec20_MASK_DEF_CELL rec_def 		= new Rec20_MASK_DEF_CELL(record_definition) ;
		String usr_text 					= rec_def.get_usertext();
		String translated_usr_text 			= rec_def.get_usertext();
		VEK<String> list_of_str_2_replace 	= new VEK<>();
		VEK<String> list_of_str_translated 	= new VEK<>();

		Pattern p = Pattern.compile("\\#(.*?)\\#");
		Matcher m = p.matcher(usr_text);
		while(m.find())
		{
			list_of_str_2_replace._a(m.group(1));
		}

		for(String str_2_update: list_of_str_2_replace) {
			String[] feld_list = str_2_update.split("\\|");
			String str_updated = "";

			for(int i=1;i<feld_list.length;i++) {
				
				str_updated = get_value(record_datei, feld_list);

				
			}

			list_of_str_translated._a(str_updated);
		}

		for(int i = 0; i<list_of_str_2_replace.size(); i++) {
			translated_usr_text = translated_usr_text.replace("#"+list_of_str_2_replace.get(i)+"#", list_of_str_translated.get(i));
		}


		return translated_usr_text;
	}



}
