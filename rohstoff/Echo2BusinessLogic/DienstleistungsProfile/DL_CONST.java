 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMapEnumExtender;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class DL_CONST {
 	
 	
    public enum DL_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
        ,MASK_COMP_TEXT_VOR_KUNDE(S.ms("Text vor Kunde"))
        ,MASK_COMP_TEXT_VOR_LAGER(S.ms("Text vor Lager"))
        ,DATASET_NAME(S.ms("<dataset-name>"))
        ;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private DL_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private DL_NAMES(MyE2_String p_userText, String dbVal) {
            this.userText = p_userText;
            this.m_dbVal=dbVal;
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
        
        public MyE2_String getUserText() {
        	if (this.userText!=null && S.isFull(this.userText)) {
        		return S.msUt(this.name());
        	}
        	return this.userText;
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
    	return _TAB.dlp_profil;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.dlp_profil.rb_km();
    }
    
    
    
    
    
    public enum DL_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_DIENSTLEISTUNG_PROFIL")
        ,EDIT("BEARBEITE_DIENSTLEISTUNG_PROFIL")
        ,VIEW("ANZEIGE_DIENSTLEISTUNG_PROFIL")
        ,NEW("NEUEINGABE_DIENSTLEISTUNG_PROFIL")
        ,
        
        ;
        
        private String KEY = null;
        
        private DL_BUTTONS() {
            this.KEY=this.name();
        }
        private DL_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(DL_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    
    
    /*
     * enum: hier koennen numerische (long) werte zentral gesteuert werden 
     */
    public static enum DL_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(1000))
        ,MASKPOPUP_HEIGHT(new Integer(650))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(300))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private DL_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
    //addon-speicherplaetze in der transport-hashmap
    public static enum TP_MAP_ADDONS implements RB_TransportHashMapEnumExtender {
    	FIELD_SELECT_ARTIKEL
    	,FIELD_SELECT_DIENSTLEISTUNGEN
    }
    
    
    
}
 
 
