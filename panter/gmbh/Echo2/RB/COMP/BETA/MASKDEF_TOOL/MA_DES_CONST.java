 
package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
public class MA_DES_CONST {
    
    public enum MA_DES_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private MA_DES_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum MA_DES_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_MASK_DEF")
        ,EDIT("BEARBEITE_MASK_DEF")
        ,VIEW("ANZEIGE_MASK_DEF")
        ,NEW("NEUEINGABE_MASK_DEF")
        ,
        
        ;
        
        private String KEY = null;
        
        private MA_DES_BUTTONS() {
            this.KEY=this.name();
        }
        private MA_DES_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(MA_DES_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner MASK_DESIGN_LIST  und MASK_DESIGN_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.MASK_DESIGN_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.MASK_DESIGN_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    public enum KUSTOM_COMPONENT{
    	BT_JUMP_2_DESIGN_UI(MASK_DEF.id_mask_def)
    	,BT_JUMP_2_TEST_UI(MASK_DEF.id_mask_def)
    	;
    	
    	private IF_Field  key = null;
        
    	private KUSTOM_COMPONENT(IF_Field p_field) {
           this.key = p_field;
        }
	    
    	public RB_KF key() throws myException {
	        return new RB_KF(this.key,this.name());
        }
    }
    
}
 
