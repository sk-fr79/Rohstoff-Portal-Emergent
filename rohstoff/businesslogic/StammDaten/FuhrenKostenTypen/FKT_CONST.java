 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_CONST {
    
    public enum FKT_NAMES implements IF_enumForDb<FKT_NAMES> {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private FKT_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        @Override
        public String dbVal() {
            return this.name();
        }
        @Override
        public String userText() {
            if (S.isEmpty(this.userText)) {
                return this.name();
            } else {
                return this.userText.CTrans();
            }
        }

		@Override
		public FKT_NAMES[] getValues() {
			return FKT_NAMES.values();
		}
        
    }
    
    
    
    
    public enum FKT_BUTTONS implements IF_enumForDb<FKT_BUTTONS>  {
        DELETE("LOESCHE_FUHREN_KOSTEN_TYP")
        ,EDIT("BEARBEITE_FUHREN_KOSTEN_TYP")
        ,VIEW("ANZEIGE_FUHREN_KOSTEN_TYP")
        ,NEW("NEUEINGABE_FUHREN_KOSTEN_TYP")
        ,POPUP_TYPEN(null)
        
        ;
        
        private String KEY = null;
        
        private FKT_BUTTONS() {
            this.KEY=this.name();
        }
        private FKT_BUTTONS(String key) {
            this.KEY=key;
        }

        @Override
        public String     dbVal() {    
        	return S.isEmpty(this.KEY)?this.name():this.KEY;
        }
        
		@Override
		public FKT_BUTTONS[] getValues() {
			return FKT_BUTTONS.values();
		}
  
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner FUHREN_KOSTEN_TYP_LIST  und FUHREN_KOSTEN_TYP_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.FUHREN_KOSTEN_TYP_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.FUHREN_KOSTEN_TYP_MASK)
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
 
