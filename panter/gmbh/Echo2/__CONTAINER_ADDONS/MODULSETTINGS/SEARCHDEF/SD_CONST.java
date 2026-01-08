 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.IF.IF_generate_RB_PSEUDO_FIELDKEY;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_CONST {
    
    public enum SD_NAMES implements IF_enum_4_db, IF_generate_RB_PSEUDO_FIELDKEY {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        ,MASK_BT_HELP(S.ms("Hilfsbutton beim SQL-Feld"))
        ;
        
        private MyE2_String userText = null; 
        private SD_NAMES(MyE2_String p_userText) {
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

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.IF.IF_generate_RB_PSEUDO_FIELDKEY#getPseudoFieldKey(panter.gmbh.basics4project.DB_ENUMS._TAB)
		 */
		@Override
		public RB_KF getPseudoFieldKey(_TAB table) throws myException {
			return new RB_KF(table.key().fn(),this.name());
		}

        
    }
    
    
    
    
    public enum SD_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_SEARCHDEF")
        ,EDIT("BEARBEITE_SEARCHDEF")
        ,VIEW("ANZEIGE_SEARCHDEF")
        ,NEW("NEUEINGABE_SEARCHDEF")
        ,
        
        ;
        
        private String KEY = null;
        
        private SD_BUTTONS() {
            this.KEY=this.name();
        }
        private SD_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(SD_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner SEARCHDEF_LIST  und SEARCHDEF_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.SEARCHDEF_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.SEARCHDEF_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    
}
 
