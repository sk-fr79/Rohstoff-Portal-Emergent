 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import java.util.HashMap;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class HELP2_CONST {
 	
 	
    public enum HELP2_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))

        ;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private HELP2_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private HELP2_NAMES(MyE2_String p_userText, String dbVal) {
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
    	return _TAB.hilfetext;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.hilfetext.rb_km();
    }
    
    
    
    
    
    public enum HELP2_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_HILFETEXT")
        ,EDIT("BEARBEITE_HILFETEXT")
        ,VIEW("ANZEIGE_HILFETEXT")
        ,NEW("NEUEINGABE_HILFETEXT")
        ,
        
        ;
        
        private String KEY = null;
        
        private HELP2_BUTTONS() {
            this.KEY=this.name();
        }
        private HELP2_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(HELP2_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner HILFETEXT_LIST  und HILFETEXT_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,HILFETEXT_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,HILFETEXT_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.HILFE_V2_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.HILFE_V2_MASK)
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
    public static enum HELP2_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(1200))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,LISTPOPUP_WIDTH(new Integer(1400))
        ,LISTPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(200))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(950))
        ,WIDTH_LONGTEXT(new Integer(500))
        ,WIDTH_COL_WITH_TEXT_AND_IMAGES(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private HELP2_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
    
    
	public enum TICKET_STATUS implements IF_enum_4_db{
		 NEW(			"Neues Ticket", Color.RED)	
		,IN_PROGRESS(	"In Arbeit", Color.YELLOW)
		,CLOSED(		"Abgeschlossen", Color.GREEN)
		;
		
		private String 	db_Val = null;
		private String 	userText = null;
		private Color   backCol = null;

		private TICKET_STATUS(String p_userText, Color p_backCol) {
			
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
			this.backCol = p_backCol;
		}

		@Override
		public String db_val() {
			return db_Val;
		}


		@Override
		public String user_text() {
			return this.userText;
		}

		@Override
		public String user_text_lang() {
			return this.userText;
		}
		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(TICKET_STATUS.values(), emptyPairInFront);
		}

		public Color getBackCol() {
			return backCol;
		}

		

		
	}

	
	

	public enum TICKET_TYP  implements IF_enum_4_db {
		DOKUMENT(			"Handbuch",true,false)
		,FEATURE(			"Neue Funktion",false,true)
		,BUGFIX(			"Fehlerkorrektur",false,true)
		;
		
		private String 		db_Val = null;
		private String 		userText = null;
		private boolean    	is_doku = false;
		private boolean    	is_develop = false;

		private TICKET_TYP(String p_userText, boolean p_doku, boolean p_develop) {
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
			this.is_develop = p_develop;
			this.is_doku = p_doku;
		}
		
		
		public String db_val() {
			return db_Val;
		}

		@Override
		public String user_text() {
			return this.userText;
		}
		
		@Override
		public String user_text_lang() {
			return this.userText;
		}

		public boolean is_doku() {
			return is_doku;
		}

		public boolean is_develop() {
			return is_develop;
		}


		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(TICKET_TYP.values(), emptyPairInFront);
		}
		
	}

	
	/**
	 * looks for usertext to a given getTicketTyp
	 * @param modulKey
	 * @return
	 */
	public static MyE2_String getTicketTypUserText(String modulKey) {
		for (TICKET_TYP m: TICKET_TYP.values()) {
			if (m.db_Val.equals(modulKey)) {
				return S.ms(m.user_text());
			}
		}
		return new MyE2_String(modulKey,false);
	}
	
	/**
	 * looks for TICKET_STATUS to a given statusKey
	 * @param statusKey
	 * @return
	 */
	public static TICKET_STATUS getTicketStatus(String statusKey) {
		for (TICKET_STATUS m: TICKET_STATUS.values()) {
			if (m.db_Val.equals(statusKey)) {
				return m;
			}
		}
		return null;
	}

	
	
	
	public static String 	ersatz4id_mandant4Export = "$$ID_MANDANT$$";	

	
	
	public enum PRINT_KENNERS  implements IF_enum_4_db {
		 BETREFF_HANDBUCHDRUCK(		"Handbuchdruck-Betreff-Klemmbrett",true,false)
		,MAILTEXT_HANDBUCHDRUCK(	"Handbuchdruck-Mailtext-Klemmbrett",false,true)
		,ARCHIV_HANDBUCHDRUCK(		"handbook","Handbuch-Archiv",false,true)
		;
		
		private String 		db_Val = null;
		private String 		userText = null;
		private boolean    	is_doku = false;
		private boolean    	is_develop = false;

		private PRINT_KENNERS(String p_userText, boolean p_doku, boolean p_develop) {
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
			this.is_develop = p_develop;
			this.is_doku = p_doku;
		}
		
		private PRINT_KENNERS(String p_userText, String val, boolean p_doku, boolean p_develop) {
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
			this.is_develop = p_develop;
			this.is_doku = p_doku;
		}
		
		
		public String db_val() {
			return db_Val;
		}

		@Override
		public String user_text() {
			return this.userText;
		}
		
		@Override
		public String user_text_lang() {
			return this.userText;
		}

		public boolean is_doku() {
			return is_doku;
		}

		public boolean is_develop() {
			return is_develop;
		}


		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(TICKET_TYP.values(), emptyPairInFront);
		}
		
	}
	
	public enum parameter_handbuchdruck {
		 WHEREBLOCK
		,ORDERBLOCK 
		,HASH_STATUS
		,HASH_TYP
		,HASH_MODULE
	}

    
	public static HashMap<String, String> get_hm_status() {
		HashMap<String, String> hm_rueck = new HashMap<String, String>();
		for (TICKET_STATUS s: TICKET_STATUS.values()) {
			hm_rueck.put(s.db_val(), s.user_text_lang());
		}
		return hm_rueck;
	}

	
	public static HashMap<String, String> get_hm_ticketTyp() {
		HashMap<String, String> hm_rueck = new HashMap<String, String>();
		for (TICKET_TYP s: TICKET_TYP.values()) {
			hm_rueck.put(s.db_val(), s.user_text_lang());
		}
		return hm_rueck;
	}
	

	
	/**
	 * dropdown-addon fuer die module, sowohl in der Maske als auch im Selektor
	 */
	public static PairS pairGlobalRange = new PairS("@GLOBALE_INFO@", "Für alle Module gültig");
	
}
 
 
