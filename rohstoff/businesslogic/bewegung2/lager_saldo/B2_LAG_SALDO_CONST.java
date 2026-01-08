package rohstoff.businesslogic.bewegung2.lager_saldo;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class B2_LAG_SALDO_CONST {
 	
 	
    public enum B2_LAG_SALDO_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
        ,ID_BASE(S.ms("ID Basis"))
        ,SUBADRESSE(S.ms("ID Adresse"))
        ,ADRESSE_INFO(S.ms("Adresse"))
        ,SUBARTIKEL(S.ms("ID Artikel"))
        ,ART_INFO(S.ms("Sorte"))
        ,EINHEITKURZ(S.ms("Einheit"))
        ,INVENTURDATUM(S.ms("Inventurdatum"))
        ,INVENTURMENGE(S.ms("Inventurmenge"))
        ,SALDO_KONTRAKT(S.ms("Mögl. Saldo mit Kontrakten"))
        ,SUMME_EK_KONTRAKT(S.ms("Summe EK-Kontr."))
        ,RESTMGE_EK_KONTRAKT(S.ms("Restmengen EK-Kontr."))
        ,SUMME_VK_KONTRAKT(S.ms("Summe VK-Kontr."))
        ,RESTMGE_VK_KONTRAKT(S.ms("Restmengen VK-Kontr."))
        
        ,SALDO_DYN_1(S.ms("Lagerbestand1 zusätzlich"))
        ,SALDO_DYN_2(S.ms("Lagerbestand2 zusätzlich"))
        ,SALDO_DELTA(S.ms("Lagerbestandsänderung"))
        ,SALDO_MIT_INVENTUR(S.ms("Lagerbestand"))
        
        ;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private B2_LAG_SALDO_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private B2_LAG_SALDO_NAMES(MyE2_String p_userText, String dbVal) {
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
    	return _TAB.bg_station;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.bg_station.rb_km();
    }
    
    
    
    
    
    public enum B2_LAG_SALDO_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BG_STATION")
        ,EDIT("BEARBEITE_BG_STATION")
        ,VIEW("ANZEIGE_BG_STATION")
        ,NEW("NEUEINGABE_BG_STATION")
        ,
        
        ;
        
        private String KEY = null;
        
        private B2_LAG_SALDO_BUTTONS() {
            this.KEY=this.name();
        }
        private B2_LAG_SALDO_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(B2_LAG_SALDO_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BG_STATION_LIST  und BG_STATION_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,BG_STATION_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,BG_STATION_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.B2_LAG_SALDO_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.B2_LAG_SALDO_MASK)
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
    public static enum B2_LAG_SALDO_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(800))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private B2_LAG_SALDO_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
}
 
 
