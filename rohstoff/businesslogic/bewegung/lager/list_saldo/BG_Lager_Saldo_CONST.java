 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public class BG_Lager_Saldo_CONST {
    
	
	
	
    public enum BG_Lager_Saldo_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ,EKLagermenge(new MyE2_String("EKLagermenge"))
        ,EKRestmenge(new MyE2_String("EKRestmenge"))
        ,EKRestmengeMitPlanung(new MyE2_String("EKRestmengeMitPlanung"))
        ,VKLagermenge(new MyE2_String("VKLagermenge"))
        ,VKRestmenge(new MyE2_String("VKRestmenge"))
        ,VKRestmengeMitPlanung(new MyE2_String("VKRestmengeMitPlanung"))
        ,GesamtRestmenge(new MyE2_String("GesamtRestmenge"))
        ,GesamtRestmengeMitPlanung(new MyE2_String("GesamtRestmengeMitPlanung"))
        ,SaldoMitInventur(new MyE2_String("SaldoInventur"))
        ,InventurDatum(new MyE2_String("InventurDatum"))
        ,InventurMenge(new MyE2_String("InventurMenge"))
        
        ,SaldoDynamisch1(new MyE2_String("SaldoZumZeitpunkt1"))
        ,SaldoDynamisch2(new MyE2_String("SaldoZumZeitpunkt2"))
        ,SaldoDelta(new MyE2_String("SaldoDelta"))
      
        ,ADRESS_INFO(new MyE2_String("AdressInfo"))
        ,ART_INFO(new MyE2_String("ArtikelInfo"))
        
        ;
        
        private MyE2_String userText = null; 
        private BG_Lager_Saldo_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum BG_Lager_Saldo_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BG_LADUNG")
        ,EDIT("BEARBEITE_BG_LADUNG")
        ,VIEW("ANZEIGE_BG_LADUNG")
        ,NEW("NEUEINGABE_BG_LADUNG")
        ,
        
        ;
        
        private String KEY = null;
        
        private BG_Lager_Saldo_BUTTONS() {
            this.KEY=this.name();
        }
        private BG_Lager_Saldo_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(BG_Lager_Saldo_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BG_LAGER_SALDO_LIST  und BG_LAGER_SALDO_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.BG_LAGER_SALDO_LIST)
//        ,MASK(E2_MODULNAME_ENUM.MODUL.BG_LAGER_SALDO_MASK)
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
 
