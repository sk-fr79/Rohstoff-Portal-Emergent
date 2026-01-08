 
package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_CONST {
    
    public enum ADR_FREIGABE_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private ADR_FREIGABE_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum ADR_FREIGABE_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_SANKTION_PRUEFUNG")
        ,EDIT("BEARBEITE_SANKTION_PRUEFUNG")
        ,VIEW("ANZEIGE_SANKTION_PRUEFUNG")
        ,NEW("NEUEINGABE_SANKTION_PRUEFUNG")
        ,
        
        ;
        
        private String KEY = null;
        
        private ADR_FREIGABE_BUTTONS() {
            this.KEY=this.name();
        }
        private ADR_FREIGABE_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(ADR_FREIGABE_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner SANKTION_PRUEFUNG_LIST  und SANKTION_PRUEFUNG_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.SANKTION_PRUEFUNG_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.SANKTION_PRUEFUNG_MASK)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    public enum LIST_KEY{
    	ADRESSE_DETAIL("KEY_ADRESSE_DETAIL")
    	,FREIGABE_LBL("KEY_FREIGABE");
    	
    	private String key;
    	private LIST_KEY(String p_key) {
    		this.key = p_key;
    	}
    	
    	public String k() {
    		return key;
    	}
    }
   
    public enum MASK_KEY{
    	SANKTION_DETAIL(SANKTION_PRUEFUNG.id_sanktion_pruefung)
    	,SANKTION_ADRESSE_BEARBEITEN(SANKTION_PRUEFUNG.id_adresse)
    	;
    	
    	private IF_Field field;
    	
    	private MASK_KEY(IF_Field p_field ) {
    		this.field = p_field;
    	}
    	
    	public RB_KF fk() throws myException {
    		return new RB_KF(this.field, this.name());
    	}
    }
}
 
