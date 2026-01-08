 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class RQ_CONST {
 	
 	
	public static String getSESCode() {
		return bibALL.MakeSql(bibE2.get_CurrSession().getId());
	}
	
	public static String getUserCode() {
		return bibALL.MakeSql(bibALL.get_ID_USER()+"-"+bibALL.get_KUERZEL());
	}
	
	public static String getTimeStamp() {
		return "SYSDATE";
	}
	
	
	public static String getModuKenner(Rec21 recQuery) throws myException {
		return RQ_CONST.RQ_NAMES.TAG_FOR_MODULEKENNER.db_val()+"_"+recQuery.getUfs(REPORTING_QUERY.realname_temptable);
	}
	
    public enum RQ_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,BUTTON_ANALYSE_QUERY( S.ms("Analyse der Query"))
        ,PREFIX_OF_TABLES(S.ms("Praefix fuer die Temp-Tables"),"TR")
        ,INDEX_PLACEHOLDER(S.ms("Praefix fuer die Temp-Tables"),"#PRIMAERKEY#")
        ,TAG_FOR_FIELDS(S.ms("Praefix fuer die Temp-Tables"),"$$")
        ,TAG_FOR_FIELDS_REGEX(S.ms("Praefix fuer die Temp-Tables fuer regulaeren such-ausdruck"),"\\$\\$")
        ,TAG_FOR_MODULEKENNER(S.ms("Praefix fuer die Kennung des Moduls mit der erzeugten NaviList"),"SQLBASED_LIVE_REPORT")
        ,SESSIONFIELDNAME(S.ms("Name für das Sessionfeld"),"SF72884300E0FB11E89F32")
        ,USERIDFIELDNAME(S.ms("Name für das Benutzerfeld"),"UF5D4538D4E19D11E8")
        ,TIMESTAMPFIELDNAME(S.ms("Name für das Tiemstamp-Feld"),"TSF69BDDA80E19D")
        ,LISTBUTTON_START_REPORT(S.ms("Report starten ..."),"START_REPORT")
        
        ;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private RQ_NAMES(MyE2_String p_userText, String dbVal) {
            this.userText = p_userText;
            this.m_dbVal=dbVal;
        }

        private RQ_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        @Override
        public String db_val() {
        	if (S.isFull(m_dbVal)) {
        		return m_dbVal;
        	}
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
    	return _TAB.reporting_query;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.reporting_query.rb_km();
    }
    
    
    
    
    
    public enum RQ_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_REPORTING_QUERY")
        ,EDIT("BEARBEITE_REPORTING_QUERY")
        ,VIEW("ANZEIGE_REPORTING_QUERY")
        ,NEW("NEUEINGABE_REPORTING_QUERY")
        ,
        
        ;
        
        private String KEY = null;
        
        private RQ_BUTTONS() {
            this.KEY=this.name();
        }
        private RQ_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(RQ_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner REPORTING_QUERY_LIST  und REPORTING_QUERY_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,REPORTING_QUERY_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,REPORTING_QUERY_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.REPORTING_QUERY_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.REPORTING_QUERY_MASK)
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
    public static enum RQ_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(1100))
        ,MASKPOPUP_HEIGHT(new Integer(850))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(110))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(900))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private RQ_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
}
 
 
