 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMapEnumExtender;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_TEILNEHMER;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
 
public class WF_SIMPLE_CONST {
	
	 static String prefix_general = "WORKFLOW_SIMPLE_";
	
	 static String prefix_besitzer = "BESITZER_" ;
	 static String prefix_abgeschlossen_von = "ABGESCHLOSSEN_" ;
	 static String prefix_bearbeiter = "BEARBEITER_" ;
	 static String prefix_status = "STATUS_";
	 
	 
	 static String alias_besitzer = prefix_besitzer + USER.fullTabName();
	 static String alias_abgeschlossen_von = prefix_abgeschlossen_von + USER.fullTabName();
	 static String alias_bearbeiter = prefix_bearbeiter + USER.fullTabName();
	 static String alias_status = prefix_status + LAUFZETTEL_STATUS.fullTabName();
	 

	static String colname_bearbeiter_name = prefix_bearbeiter + USER.name.fn();
	static String colname_besitzer_name = prefix_besitzer + USER.name.fn();
	static String colname_abgeschlossen_von_name = prefix_abgeschlossen_von + USER.name.fn();
	static String colname_status = prefix_status + LAUFZETTEL_STATUS.status.fn();
	
	static String colname_aufgabe_bericht = prefix_general + LAUFZETTEL_EINTRAG.aufgabe.fn() + "_" + LAUFZETTEL_EINTRAG.bericht.fn();
	public static String colname_aufgabe = prefix_general + LAUFZETTEL_EINTRAG.aufgabe.fn() ;
	public static String colname_bericht = prefix_general + LAUFZETTEL_EINTRAG.bericht.fn() ;
	
	

 	
    public enum WF_SIMPLE_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
        ,DIRECT_UPLOAD2(S.ms("Anlagen hochladen"))
        ,CLOSE_ALL(S.ms("Alle Laufzetteleinträge bei Abschluss eines Eintrags abschließen"))
        ;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private WF_SIMPLE_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private WF_SIMPLE_NAMES(MyE2_String p_userText, String dbVal) {
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
    	return _TAB.laufzettel_eintrag;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.laufzettel_eintrag.rb_km();
    }
    
    /**
     * 
     * @return der mask-key für den Laufzettel-Eintrag
     * @throws myException
     */
    public static RB_KM getMaskKeyLaufzettelEintrag() throws myException {
    	return _TAB.laufzettel_eintrag.rb_km();
    }
    
    /**
     * 
     * @return der mask-key für den Laufzettel
     * @throws myException
     */
    public static RB_KM getMaskKeyLaufzettel() throws myException {
    	return _TAB.laufzettel.rb_km();
    }
    
    
    
    public enum WF_SIMPLE_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_LAUFZETTEL_EINTRAG")
        ,EDIT("BEARBEITE_LAUFZETTEL_EINTRAG")
        ,VIEW("ANZEIGE_LAUFZETTEL_EINTRAG")
        ,NEW("NEUEINGABE_LAUFZETTEL_EINTRAG")
        ,
        
        ;
        
        private String KEY = null;
        
        private WF_SIMPLE_BUTTONS() {
            this.KEY=this.name();
        }
        private WF_SIMPLE_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(WF_SIMPLE_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner LAUFZETTEL_EINTRAG_LIST  und LAUFZETTEL_EINTRAG_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,LAUFZETTEL_EINTRAG_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,LAUFZETTEL_EINTRAG_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.WF_SIMPLE_MASK)
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
    public static enum WF_SIMPLE_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(800))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private WF_SIMPLE_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
    public enum WF_SIMPLE_TransportExtender implements RB_TransportHashMapEnumExtender{
    	DEFAULT_STATUS,
    	DEFAULT_PRIO,
    	ID_STATUS_ABGESCHLOSSEN
    	
    }
    

    public enum MASK_KEYS {
    	USER_CROSSTABLE(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag)
    	,LAUFZETTEL_TEXT_RO(LAUFZETTEL.text)
    	
    	;
    	private IF_Field  field = null;
    	
    	private MASK_KEYS(IF_Field  _field) {
    		this.field=_field;
    	}
    	
    	public RB_KF key() throws myException {
    		return new RB_KF(this.field,this.name());
    	}
    }
    
    
    public enum MASK_KEYS_String {
    	MODUL_CONNECTOR("1a73500e-f74d-45a1-97a4-aba405e0d3fd","Sprung")
    	,LAUFZETTEL_GRID_TASKS("9e174428-8f35-4f0a-9e4b-4eba361cde1d","Aufgaben-Liste")
    	,LAUFZETTEL_EINTRAG_ATTACHMENT("d686edaf-50a5-46aa-94fb-f1389b2bd71e","Laufzettel-Eintrag Anhang")
    	
    	;
    	
    	private String  _field	= null;
    	private String  _name 	= null;
    	
    	private MASK_KEYS_String(String  field, String name ) {
    		this._field	=field;
    		this._name 	= name;
    	}
    	
    	public RB_KF key() throws myException {
    		return new RB_KF()._setHASHKEY(_field)._setREALNAME(_name);
    	}
    	
    	
    }

    
    /**
     * Farbliche Markierung zeitlicher Verzug 
     * @author manfred
     * @date 02.05.2019
     *
     */
    public enum Color_Date {
    	OK(0, new E2_ColorBase() )
    	,LATE(1,Color.YELLOW)
    	,VERY_LATE(2,Color.RED)
    	;
    	
    	private Color  	_color	= null;
    	private int  	_days 	= 0;
    	
    	private Color_Date(int late,Color col ) {
    		this._color	= col;
    		this._days 	= late;
    		
    	}
    	
    	public Color getCol() throws myException {
    		return _color;
    	}
    	public int getDays() throws myException {
    		return _days;
    	}
    	
    	
    }
    
}


 
 
