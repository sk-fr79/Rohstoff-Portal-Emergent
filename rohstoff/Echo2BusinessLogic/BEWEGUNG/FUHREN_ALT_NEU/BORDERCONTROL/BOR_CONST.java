 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
public class BOR_CONST {
    
    public enum BOR_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        ,ARTIKEL_LISTE(new MyE2_String("Artikel"))
        ;
        
        private MyE2_String userText = null; 
        private BOR_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum BOR_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BORDERCROSSING")
        ,EDIT("BEARBEITE_BORDERCROSSING")
        ,VIEW("ANZEIGE_BORDERCROSSING")
        ,NEW("NEUEINGABE_BORDERCROSSING")
        ,
        
        ;
        
        private String KEY = null;
        
        private BOR_BUTTONS() {
            this.KEY=this.name();
        }
        private BOR_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(BOR_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BORDERCROSSING_LIST  und BORDERCROSSING_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.BORDERCROSSING_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.BORDERCROSSING_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    
    
    public enum MASK_KEYS {
    	USER_CROSSTABLE(BORDERCROSSING.id_bordercrossing)
    	,BORDERCROSSING_ARTIKEL(BORDERCROSSING.id_bordercrossing)
    	;
    	
    	private IF_Field  field = null;
    	
    	private MASK_KEYS(IF_Field  _field) {
    		this.field=_field;
    	}
 	
    	public RB_KF key() throws myException {
   			return new RB_KF(this.field,this.name());
    	}
    }
    
    
}
 
