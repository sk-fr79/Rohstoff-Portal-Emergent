 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
 
import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class TAXG_CONST {
    public enum TAXG_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private TAXG_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum TAXG_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_TAX_GROUP")
        ,EDIT("BEARBEITE_TAX_GROUP")
        ,VIEW("ANZEIGE_TAX_GROUP")
        ,NEW("NEUEINGABE_TAX_GROUP")
        ,
        
        ;
        
        private String KEY = null;
        
        private TAXG_BUTTONS() {
            this.KEY=this.name();
        }
        private TAXG_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(TAXG_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner TAX_GROUP_LIST  und TAX_GROUP_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,TAX_GROUP_LIST(MODUL_TYP.LIST		, S.ms("MWSt.-Gruppierung (Liste)"))
	     ,TAX_GROUP_MASK(MODUL_TYP.MASK_RB	, S.ms("MWSt.-Gruppierung (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.TAX_GROUP_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.TAX_GROUP_MASK)
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
     *  hashmap zum transport von parametern in die einzelnen klassen
     */    
    public static class PARAMHASH extends HashMap<TAXG_PARAMS,Object> {
        /**
         * 
        */
        public PARAMHASH() {
           super();
        }
    }
    
    
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public static enum TAXG_PARAMS {
    	
        //parameter fuer die zentrale hashmap, die in allen calls uebergeben wird
       	
    	
        TAXG_NAVILIST()
        ,TAXG_MODULCONTAINER_MASK()
        ,TAXG_MODULCONTAINER_LIST()
        ,TAXG_MODULCONTAINER_LIST_SQL_FIELDMAP()
        ,TAXG_MODULCONTAINER_LIST_SELECTOR()
        ,TAXG_MODULCONTAINER_LIST_BEDIENPANEL()
        ,TAXG_MASK_COMPONENT_MAP_COLLECTOR()
        ,TAXG_MASK_DATAOBJECTS_COLLECTOR()
        ,TAXG_MASK_GRID()
        ;
    	
         private TAXG_PARAMS() {
        }
        
        public String getParamName() {
            return this.name();
        }
    }
    
    
    
    
    /*
     * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
     */
    public static enum TAXG_READABLE_FIELD_NAME {
        AKTIV(TAX_GROUP.aktiv,"Aktive Gruppe"),
        ID_TAX_GROUP(TAX_GROUP.id_tax_group,"ID"),
        KURZTEXT(TAX_GROUP.kurztext,"Kurztext"),
        LANGTEXT(TAX_GROUP.langtext,"Beschreibung"),
    	;
    	
    	private IF_Field m_field = null;
    	private String   m_readAble = null;
    	
    	private TAXG_READABLE_FIELD_NAME(IF_Field f, String readAble) {
    		this.m_field=f;
    		this.m_readAble = readAble;
    	}
    	
    	public static String getReadable(IF_Field f) {
    		String ret = "";
    		
    		for (TAXG_READABLE_FIELD_NAME  rf: TAXG_READABLE_FIELD_NAME.values() ) {
    			if (rf.m_field == f) {
    				return rf.m_readAble;
    			}
    		}
    		return ret;
    	}
    }
    
    
    
    /*
     * enum: hier koennen numerische (long) werte zentral gesteuert werden 
     */
    public static enum TAXG_NUM_CONST {
         MASKPOPUP_WIDTH(new Long(700))
        ,MASKPOPUP_HEIGHT(new Long(350))
         
    	;
    	
    	private Long   m_value = null;
    	
    	private TAXG_NUM_CONST(Long p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Long getValue() {
    		return this.m_value;
    	}
    }
    
}
 
