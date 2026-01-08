package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.B2_LAG_BUTTONS;

public class B2_LALI_CONST
{
	public static String HASH_BUTTON_SHOW_FUHRE = "BUTTON_SHOW_FUHRE";
	
	public static String HASH_COLUMN_MENGE_WE = "HASH_COLUMN_MENGE_WE";
	public static String HASH_COLUMN_MENGE_WA = "HASH_COLUMN_MENGE_WA";
	
	public static String HASH_COLUMN_SORTE_BEMERKUNG = "COLUMN_SORTE_BEMERKUNG";
	public static String HASH_COLUMN_FUHRE_FUHRENORT = "COLUMN_FUHRE_FUHRENORT";
	public static String HASH_COLUMN_FUHRE_KENNZEICHEN = "COLUMN_FUHRE_KENNZEICHEN";
	public static String HASH_COLUMN_ADRESS_ID = "COLUMN_ADRESS_ID";	
	public static String HASH_COLUMN_STORNO = "COLUMN_FUHRE_STORNO";
	public static String HASH_COLUMN_STATUS = "COLUMN_FUHRE_STATUS";	
	public static String HASH_COLUMN_NUMMERN = "COLUMN_NUMMERN";
	public static String HASH_COLUMN_NUMMERN_PAPIERE = "COLUMN_NUMMERN_PAPIERE";
	
	public static String HASH_FIELD_KONTRAKTNUMMER = "FIELD_KONTRAKTNUMMER";
	public static String HASH_FIELD_RECHNR = "FIELD_RECHNR";
	
	
	
	public static String HASH_LIST_BT_KOSTEN_ERFASSUNG = "HASH_LIST_BT_KOSTEN_ERFASSUNG";
	public static String HASH_COLUMN_KOSTEN_ERFASSUNG =  "HASH_COLUMN_KOSTEN_ERFASSUNG";
	
	
	

    public enum B2_LALI_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE		( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE		( S.ms("Markierung Liste"))
        ,DIRECT_DEL			( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT		( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW		( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD		( S.ms("Dateien hochladen"))
        ,TRANSPORT_TYP		( S.ms("T"))
        //zusatz feldern
        ,WE_MENGE_BRUTTO	(S.ms("WE brutto"))
    	,WE_MENGE_NETTO		(S.ms("WE netto"))
    	,WE_MENGE_ABZUG		(S.ms("WE abzug"))
    	,WE_MENGE			(S.ms("Menge WE"))
    	,WA_MENGE_BRUTTO	(S.ms("WA brutto"))
    	,WA_MENGE_NETTO		(S.ms("WA netto"))
    	,WA_MENGE_ABZUG		(S.ms("WA abzug"))
    	,WA_MENGE			(S.ms("Menge WA"))
    	,EW_FW				(S.ms("EW"))
    	,ABRECHENBAR_BESITZ	(S.ms("Abr."))
    	,SORTE_INFO			(S.ms("Sorte"))
    	,STARTLAGER_ID			(S.ms("ID Startlager"))
    	,STARTLAGER_ADRESSE		(S.ms("Startlager"))
    	,ZIELLAGER_ADRESSE		(S.ms("Ziellager"))
    	,ZIELLAGER_ID			(S.ms("ID Ziellager"))
    	,EINHEIT_KURZ		(S.ms("Einheit"))
    	,HAUPTSORTE			(S.ms("Hauptsorte"))
        
    	,ID_ADRESSE_1		(S.ms("ID Lager"))
    	,VEKTOR_ID			(S.ms("ID Vektor"))
        ;
        
    	private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private B2_LALI_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private B2_LALI_NAMES(MyE2_String p_userText, String dbVal) {
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
        
        public MyE2_String user_text_ms() {
            if (S.isEmpty(this.userText)) {
                return S.ms(this.name());
            } else {
                return S.ms(this.userText.CTrans());
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
    	return _TAB.bg_auswert;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return _TAB.bg_auswert.rb_km();
    }
    
    
    
    
    
    public enum B2_LAG_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_BG_AUSWERT")
        ,EDIT("BEARBEITE_BG_AUSWERT")
        ,VIEW("ANZEIGE_BG_ATOM")
        ,NEW("NEUEINGABE_BG_ATOM")
        ,
        
        ;
        
        private String KEY = null;
        
        private B2_LAG_BUTTONS() {
            this.KEY=this.name();
        }
        private B2_LAG_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(B2_LAG_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner BG_ATOM_LIST  und BG_ATOM_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
        /*
         * beispiele:
         ,BG_ATOM_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,BG_ATOM_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
         *
         */
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.B2_LALI_LAGER_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.B2_LALI_LAGER_MASK)
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
    public static enum B2_LAG_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(800))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
         
    	;
    	
    	private Integer   m_value = null;
    	
    	private B2_LAG_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
//    public static enum Table_alias{
//    	VEKTOR(_TAB.bg_vektor)
//    	,ATOM(_TAB.bg_atom)
//    	,ATOM2(_TAB.bg_atom)
//    	,STATION1(_TAB.bg_station)
//    	,STATION2(_TAB.bg_station)
//    	,STATION3(_TAB.bg_station)
//    	,ADRESSE1(_TAB.adresse)
//    	,ADRESSE2(_TAB.adresse)
//    	,ADRESSE3(_TAB.adresse)
//    	,ART_BEZ(_TAB.artikel_bez)
//    	,ART(_TAB.artikel)
//    	,EINHEIT(_TAB.einheit)
//    	,WAEHRUNG(_TAB.waehrung)
//    	;
//    	
//    	private _TAB table=null;
//    	
//    	private Table_alias(_TAB oTable) {
//    		this.table = oTable;
//    	}
//    	
//    	public _TAB tab() {
//    		return this.table;
//    	}
//    	
//    	public String getAlias() {
//    		return this.name();
//    	}
//    }
    
    
    public static String[][] monatliste =
    	{
    			{"-"			,""}
    			,{"Januar"		,"01"}
    			,{"Februar"		,"02"}
    			,{"März"		,"03"}
    			,{"April"		,"04"}
    			,{"Mai"			,"05"}
    			,{"Juni"		,"06"}
    			,{"Juli"		,"07"}
    			,{"August"		,"08"}
    			,{"September"	,"09"}
    			,{"Oktober"		,"10"}
    			,{"November"	,"11"}
    			,{"Dezember"	,"12"}
    	
    	};
    
    
    
    
}
