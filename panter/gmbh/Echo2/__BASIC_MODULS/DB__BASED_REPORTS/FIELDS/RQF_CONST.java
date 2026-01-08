 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class RQF_CONST {
 	
 	
    public enum RQF_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        
        ;
        
        private MyE2_String userText = null; 
        private RQF_NAMES(MyE2_String p_userText) {
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
    
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static _TAB getLeadingTable() throws myException {
    	return _TAB.reporting_query_field;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.reporting_query_field.rb_km();
    }
    
    
    
    
    
    public enum RQF_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_REPORTING_QUERY_FIELD")
        ,EDIT("BEARBEITE_REPORTING_QUERY_FIELD")
        ,VIEW("ANZEIGE_REPORTING_QUERY_FIELD")
        ,NEW("NEUEINGABE_REPORTING_QUERY_FIELD")
        ,
        
        ;
        
        private String KEY = null;
        
        private RQF_BUTTONS() {
            this.KEY=this.name();
        }
        private RQF_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(RQF_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner REPORTING_QUERY_FIELD_LIST  und REPORTING_QUERY_FIELD_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,REPORTING_QUERY_FIELD_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,REPORTING_QUERY_FIELD_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.REPORTING_QUERY_FIELD_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.REPORTING_QUERY_FIELD_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    
    
    
    /*
     * enum: hier koennen numerische (long) werte zentral gesteuert werden 
     */
    public static enum RQF_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(850))
        ,MASKPOPUP_HEIGHT(new Integer(600))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(150))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(680))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private RQF_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
}
 
 
