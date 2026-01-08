 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
 
import java.util.HashMap;
 
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
 
public class BOR_ART_CONST {
    public enum BOR_ART_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private BOR_ART_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum BOR_ART_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BORDERCROSSING_ARTIKEL")
        ,EDIT("BEARBEITE_BORDERCROSSING_ARTIKEL")
        ,VIEW("ANZEIGE_BORDERCROSSING_ARTIKEL")
        ,NEW("NEUEINGABE_BORDERCROSSING_ARTIKEL")
        ,
        
        ;
        
        private String KEY = null;
        
        private BOR_ART_BUTTONS() {
            this.KEY=this.name();
        }
        private BOR_ART_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(BOR_ART_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BORDERCROSSING_ARTIKEL_LIST  und BORDERCROSSING_ARTIKEL_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,BORDERCROSSING_ARTIKEL_LIST(MODUL_TYP.LIST		, S.ms("Verwalten der Bordercrossing-Artikel (Liste)"))
	     ,BORDERCROSSING_ARTIKEL_MASK(MODUL_TYP.MASK_RB	, S.ms("Verwalten der Bordercrossing-Artikel (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.BORDERCROSSING_ARTIKEL_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.BORDERCROSSING_ARTIKEL_MASK)
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
    public static class PARAMHASH extends HashMap<BOR_ART_PARAMS,Object> {
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
    public static enum BOR_ART_PARAMS {
    	
        //parameter fuer die zentrale hashmap, die in allen calls uebergeben wird
       	
    	
        BOR_ART_NAVILIST()
        ,BOR_ART_MODULCONTAINER_MASK()
        ,BOR_ART_MODULCONTAINER_LIST()
        ,BOR_ART_MODULCONTAINER_LIST_SQL_FIELDMAP()
        ,BOR_ART_MODULCONTAINER_LIST_SELECTOR()
        ,BOR_ART_MODULCONTAINER_LIST_BEDIENPANEL()
        ,BOR_ART_MASK_COMPONENT_MAP_COLLECTOR()
        ,BOR_ART_MASK_DATAOBJECTS_COLLECTOR()
        ,BOR_ART_MASK_GRID()
        ,BOR_ART_ID_BORDERCROSSING()
        ;
    	
         private BOR_ART_PARAMS() {
        }
        
        public String getParamName() {
            return this.name();
        }
    }
    
    
}
 
