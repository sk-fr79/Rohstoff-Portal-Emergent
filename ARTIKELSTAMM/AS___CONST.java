package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.indep.dataTools.IF_FieldDefs_4_SQLFieldMAP;

public class AS___CONST
{
	public static String LABEL_EINHEIT_PREIS = 							"LABEL_EINHEIT_PREIS";
	public static String HASHKEY_FULL_DAUGHTER_ARTBEZ = 				"FULL_DAUGHTER_ARTBEZ";
	public static String HASHKEY_FULL_DAUGHTER_ARTBEZ_MWST_FIELD = 		"HASHKEY_FULL_DAUGHTER_ARTBEZ_MWST_FIELD";
	
	//public static String HASH_KEY_MASK_SEARCH_ZOLLTARIFNUMMER = 		"HASH_KEY_MASK_SEARCH_ZOLLTARIFNUMMER";
	public static String HASH_KEY_MASK_SEARCH_OECD_CODE = 				"HASH_KEY_MASK_SEARCH_OECD_CODE";
	public static String HASH_KEY_MASK_SEARCH_BASEL_CODE = 				"HASH_KEY_MASK_SEARCH_BASEL_CODE";
	

	public static String HASH_KEY_MASK_INFO_FIELD = 					"HASH_KEY_MASK_INFO_FIELD";
	
	public static String HASH_KEY_MASK_DATENBLATT_TOCHTER = 			"HASH_KEY_MASK_DATENBLATT_TOCHTER";
	
	public static String HASH_KEY_LIST_BUTTON_JUMP_TO_FUHRE = 			"HASH_KEY_LIST_BUTTON_JUMP_TO_FUHRE";
	

	
	//2016-08-08
	public enum SQL_FIELD_NAMES implements IF_FieldDefs_4_SQLFieldMAP {
		
		ID2(ARTIKEL.id_artikel.tnfn(),new MyE2_String("ID2"))
		;
		
		private String 		f_fieldDef = null;
		private MyE2_String f_userText = null;
		
		private SQL_FIELD_NAMES(String fieldDef, MyE2_String userText){
			this.f_fieldDef = fieldDef;
			this.f_userText = userText;
		}

		@Override
		public String fieldDef() {
			return this.f_fieldDef;
		}

		@Override
		public String fieldAlias() {
			return this.name();
		}

		@Override
		public MyE2_String userText() {
			return this.f_userText;
		}
		
	}

	
}
