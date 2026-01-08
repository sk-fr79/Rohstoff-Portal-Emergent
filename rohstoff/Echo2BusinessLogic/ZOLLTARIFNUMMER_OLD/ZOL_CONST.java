package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


public class ZOL_CONST {
    
    public enum ZOL_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        ;
        
        private MyE2_String userText = null; 
        private ZOL_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum ZOL_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_ZOLLTARIFNUMMER")
        ,EDIT("BEARBEITE_ZOLLTARIFNUMMER")
        ,VIEW("ANZEIGE_ZOLLTARIFNUMMER")
        ,NEW("NEUEINGABE_ZOLLTARIFNUMMER")
        ,
        
        ;
        
        private String KEY = null;
        
        private ZOL_BUTTONS() {
            this.KEY=this.name();
        }
        private ZOL_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(ZOL_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner ZOLLTARIFNUMMER_LIST  und ZOLLTARIFNUMMER_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.MODUL_ZOLLTARIFNUMMER_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.MODUL_ZOLLTARIFNUMMER_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }



    
	public static final String COLUMN_AKTIV = "LIST_COLUMN_AKTIV";
	public static final String COLUMN_TEXT 	= "LIST_COLUMN_TEXT";
	public static final String COLUMN_EINHEIT = "LIST_COLUMN_EINHEIT";
	public static final String COLUMN_IMPORT  = "LIST_COLUMN_IMPORT";
    
    
}
 
