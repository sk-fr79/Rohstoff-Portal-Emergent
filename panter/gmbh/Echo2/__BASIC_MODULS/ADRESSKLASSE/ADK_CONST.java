package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.indep.dataTools.IF_FieldDefs_4_SQLFieldMAP;

public class ADK_CONST {

	public enum SQL_FIELD_NAMES implements IF_FieldDefs_4_SQLFieldMAP {
		
		ID2(ADRESSKLASSE_DEF.id_adressklasse_def.tnfn(),new MyE2_String("ID(2)"))
		
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
	
	
	public enum COMP_NAMES {
		
		COLORSELEKTOR
		
		;
		
		
	}

	
	
}
