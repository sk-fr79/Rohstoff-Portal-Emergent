 
package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
 
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class REP_VER_CONST {
	
	public static final String UUID_ORDER_BY = ""+ REPORT_LOG.report_uuid.fn()+","+ REPORT_LOG.id_report_log.fn()  +" ASC";
	
public enum REP_VER_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        
        ;
        
        private MyE2_String userText = null; 
        private REP_VER_NAMES(MyE2_String p_userText) {
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
    	return _TAB.report_log;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.report_log.rb_km();
    }
    
    
    
    
    
    public enum REP_VER_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_REPORT_LOG")
        ,EDIT("BEARBEITE_REPORT_LOG")
        ,VIEW("ANZEIGE_REPORT_LOG")
        ,NEW("NEUEINGABE_REPORT_LOG")
        ,
        
        ;
        
        private String KEY = null;
        
        private REP_VER_BUTTONS() {
            this.KEY=this.name();
        }
        private REP_VER_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(REP_VER_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner REPORT_LOG_LIST  und REPORT_LOG_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,REPORT_LOG_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,REPORT_LOG_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.REPORT_VERLAUF_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.REPORT_VERLAUF_MASK)
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
    public static enum REP_VER_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(800))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private REP_VER_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    public static enum REP_VER_MASK_COMPONENT{
    	PARAMETER_DETAIL(REPORT_LOG.id_report_log)
    	,USER_TRL(REPORT_LOG.report_druck_von)
    	;
    	private IF_Field field;
    	
    	private  REP_VER_MASK_COMPONENT(IF_Field field) {
    		this.field = field;
    	}
    	
    	public RB_KF fk() throws myException {
    		return new RB_KF(this.field, this.name());
    	}
    }
    
}
 
