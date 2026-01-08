package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_CONST.DRUCK_BUTTONS;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_CONST {
	
	public enum SCAN_NAMES implements IF_enum_4_db {
		
		CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
		,MARKER_LISTE(new MyE2_String("Markierung Liste"))
		
		;
		
		private MyE2_String userText = null; 
		private SCAN_NAMES(MyE2_String p_userText) {
			this.userText = p_userText;
		}
		

		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			if (S.isEmpty(this.userText)) {
				return this.name();
			} else {
				return this.userText.CTrans();
			}
		}

		@Override
		public String user_text_lang() {
			return this.user_text();
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return null;
		}
		
	}
	
	
	
	
	public enum SCAN_BUTTONS implements IF_enum_4_db  {
		 DELETE("LOESCHE_SCANNER")
	     ,EDIT("BEARBEITE_SCANNER")
	     ,VIEW("ANZEIGE_SCANNER")
	     ,NEW("NEUEINGABE_SCANNER")
	     ,      
	     ;

		private String KEY = null;
		
		private SCAN_BUTTONS() {
			this.KEY=this.name();
		}

        private SCAN_BUTTONS(String key) {
            this.KEY=key;
        }
        
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(DRUCK_BUTTONS.values(), emptyPairInFront);
        }

		  
	}
	
	
	public enum SCAN_FILETYPE implements IF_enum_4_db {
		PDF("pdf")
		,PNG("png")
		,JPG("jpg")
		
		;

		private String userText = null;
		private SCAN_FILETYPE(String p_userText) {
			this.userText = S.NN(p_userText, this.name());
		}
		
		@Override
		public String db_val() {			return this.name();		}
		@Override
		public String user_text() {			return "*."+ this.userText;		}
		@Override
		public String user_text_lang() {	return "*."+ this.userText;		}
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(values(), emptyPairInFront);
		}
		
	}
	
	
	
	public enum SCAN_DPI implements IF_enum_4_db {
		DPI150("150")
		,DPI300("300")
		,DPI600("600")
		
		;

		private String userText = null;
		private SCAN_DPI(String p_userText) {
			this.userText = S.NN(p_userText, this.name());
		}
		
		@Override
		public String db_val() {			return this.userText;		}
		@Override
		public String user_text() {			return this.userText + " dpi";		}
		@Override
		public String user_text_lang() {	return this.userText + " dpi";		}
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(SCAN_DPI.values(), emptyPairInFront);
		}
		
	}
	
	
	public enum SCAN_PROGRAMMKENNER implements IF_enum_4_db {
		RECHNUNG_ANHANG("Rechnungsversand")
		,GUTSCHRIFT_ANHANG("Gutschriftsversand")
		,RECH_GUT_ANHANG("Rechnungs- und Gutschriftsversand")
		
		;

		private String userText = null;
		private SCAN_PROGRAMMKENNER(String p_userText) {
			this.userText = S.NN(p_userText, this.name());
		}
		
		@Override
		public String db_val() {			return this.name();		}
		@Override
		public String user_text() {			return this.userText;		}
		@Override
		public String user_text_lang() {	return this.userText;		}
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(values(), emptyPairInFront);
		}
	}
	
	
	public enum ADDITIONNAL_COMPONENTS{
    	BT_TEST_SCANNER(DRUCKER.id_drucker);
    	
    	private IF_Field  field = null;
    	
    	private ADDITIONNAL_COMPONENTS(IF_Field  _field) {
    		this.field=_field;
    	}
 	
    	public RB_KF key() throws myException {
   			return new RB_KF(this.field,this.name());
    	}
    }
}
