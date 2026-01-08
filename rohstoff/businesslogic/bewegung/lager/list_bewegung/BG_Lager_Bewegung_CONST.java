 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public class BG_Lager_Bewegung_CONST {
    
    public enum BG_Lager_Bewegung_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private BG_Lager_Bewegung_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum BG_Lager_Bewegung_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BG_LADUNG")
        ,EDIT("BEARBEITE_BG_LADUNG")
        ,VIEW("ANZEIGE_BG_LADUNG")
        ,NEW("NEUEINGABE_BG_LADUNG")
        ,
        
        ;
        
        private String KEY = null;
        
        private BG_Lager_Bewegung_BUTTONS() {
            this.KEY=this.name();
        }
        private BG_Lager_Bewegung_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(BG_Lager_Bewegung_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BG_LADUNG_LIST  und BG_LADUNG_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.BG_LAGER_BEWEGUNG_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.BG_LAGER_BEWEGUNG_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }



	
    // Konstanten für Spalten in der Liste	
	public static String HASH_BUTTON_SHOW_FUHRE = "BUTTON_SHOW_FUHRE";
	
//	public static String HASH_COLUMN_MENGE_WE = "HASH_COLUMN_MENGE_WE";
//	public static String HASH_COLUMN_MENGE_WA = "HASH_COLUMN_MENGE_WA";
	
	public static String MENGE_NETTO_VZ 	= "MENGE_VZ_WE";
	public static String MENGE_BRUTTO_VZ 	= "MENGE_VZ_WA";
	public static String MENGE_NETTO_WE 	= "MENGE_NETTO_WE";
	public static String MENGE_NETTO_WA 	= "MENGE_NETTO_WA";
	public static String MENGE_BRUTTO_WE 	= "MENGE_BRUTTO_WE";
	public static String MENGE_BRUTTO_WA 	= "MENGE_BRUTTO_WA";
	
	public static String HASH_COLUMN_SORTE_BEMERKUNG = "COLUMN_SORTE_BEMERKUNG";
	public static String LADUNG_STORNO 		= "LADUNG_STORNO";
	public static String LADUNG_DELETED		= "LADUNG_DELETED";
	
	public static String HASH_COLUMN_FUHRE_FUHRENORT = "COLUMN_FUHRE_FUHRENORT";
//	public static String HASH_COLUMN_FUHRE_KENNZEICHEN = "COLUMN_FUHRE_KENNZEICHEN";
//	public static String HASH_COLUMN_ADRESS_ID = "COLUMN_ADRESS_ID";	
//	public static String HASH_COLUMN_STATUS = "COLUMN_FUHRE_STATUS";	
//	public static String HASH_COLUMN_NUMMERN = "COLUMN_NUMMERN";
//	public static String HASH_COLUMN_NUMMERN_PAPIERE = "COLUMN_NUMMERN_PAPIERE";
//	
//	public static String HASH_FIELD_KONTRAKTNUMMER = "FIELD_KONTRAKTNUMMER";
//	public static String HASH_FIELD_RECHNR = "FIELD_RECHNR";
//	
	public static String HASH_LIST_BT_KOSTEN_ERFASSUNG = "HASH_LIST_BT_KOSTEN_ERFASSUNG";
	public static String HASH_COLUMN_KOSTEN_ERFASSUNG =  "HASH_COLUMN_KOSTEN_ERFASSUNG";
    
}
 
