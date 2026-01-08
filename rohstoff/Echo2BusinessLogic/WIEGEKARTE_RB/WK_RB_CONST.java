 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMapEnumExtender;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
 
public class WK_RB_CONST {
	
	public static RB_KF key_componentMap_collector = new RB_KF()._setHASHKEY("7e292396-278c-4f18-9705-849f7bf4aa9e")._setREALNAME("HashKey_for_ComponentMapCollector");
	
	public static Border borderNeutral = new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID);

	public static String KENNER_TEXTBAUSTEINE_HANDWIEGUNG = "KENNER_TEXT_HANDWIEGUNG";
	
	/**
	 * 
	 * @author manfred
	 * @date 16.03.2020
	 *
	 */
	public static enum ENUM_ZustandWiegekarte implements IF_enum_4_db {
		
		NEU 		(S.ms("Wiegekarte ist neu")), 
		STAMMDATEN 	(S.ms("Wiegekarte-Daten sind gespeichert")), 
		WAEGUNG1 	(S.ms("Waegung 1 wurde durchgeführt")), 
		WAEGUNG2 	(S.ms("Wagung 2 wurde durchgeführt")), 
		GEDRUCKT 	(S.ms("Wiegekarte wurde gedruckt")),
		STORNO      (S.ms("Wiegekarte ist Storniert"));
		
		
		private MyE2_String _Text = null;
		
		private ENUM_ZustandWiegekarte(MyE2_String text) {
			this._Text = text;
		}

		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			return this._Text.CTrans();
		}

		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return null;
		}
	}


	/**
	 * 
	 * @author manfred
	 * @date 30.04.2020
	 *
	 */
	public static enum ENUM_Gueterkategorie implements IF_enum_4_db {
		
		SCHUETTGUT 	(S.ms("Schüttgut")), 
		STUECKGUT 	(S.ms("Stückgut"))
		; 
		
		private MyE2_String _Text = null;
		
		private ENUM_Gueterkategorie(MyE2_String text) {
			this._Text = text;
		}

		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			return this._Text.CTrans();
		}

		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return null;
		}
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 16.03.2020
	 *
	 */
	public static enum ENUM_WaegungPos implements IF_enum_4_db {
		
		WAEGUNG_1 	(S.ms("Wägung 1"),1), 
		WAEGUNG_2 	(S.ms("Wägung 2"),2)
		; 
		
		private MyE2_String _Text = null;
		private Integer 	_pos = null; 
		
		private ENUM_WaegungPos(MyE2_String text, Integer pos) {
			this._Text = text;
			this._pos = pos;
		}

		@Override
		public String db_val() {
			return this.name();
		}
		
		/**
		 * position 1 v 2
		 * @author manfred
		 * @date 30.03.2021
		 *
		 * @return
		 */
		public int getIntVal() {
			return this._pos;
		}

		@Override
		public String user_text() {
			return this._Text.CTrans();
		}

		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return null;
		}
	}

	
	
	
    public static enum WK_RB_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
        ,MARKER_LISTE( S.ms("Markierung Liste"))
        ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
        ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
        ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
        ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
        ,DATASET_NAME(S.ms("Wiegekarte"))
        ,MEHRZEILER_W1_GEWICHT  (S.ms("Gewicht Wägung1"))
        ,MEHRZEILER_W2_GEWICHT  (S.ms("Gewicht Wägung2"))
        ,MEHRZEILER_GEWICHT  (S.ms("Gewicht"))
        ,MEHRZEILER_SORTE_KUNDE  (S.ms("Sorte Kunde"))
        ,MEHRZEILER_ID_FUHREN  (S.ms("ID Fuhre"))
        ,MEHRZEILER_SPED_STRECKE  (S.ms("Spedition Strecke"))
        ,MEHRZEILER_WE_TYP  (S.ms("WE/WA"))
        ,MEHRZEILER_BEMERKUNG  (S.ms("Bemerkung"))
        ,MEHRZEILER_BEFUNDUNG  (S.ms("Befundung"))
        ,MEHRZEILER_ZUSATZ  (S.ms("Zusatz"))
        ,MEHRZEILER_FOLGEWAEGUNG  (S.ms("Folgewägung"))
        ,MEHRZEILER_IDS  (S.ms("IDs"))
        ,MEHRZEILER_STORNO (S.ms("Storno"))
        ,MEHRZEILER_GEDRUCKT  (S.ms("Gedruckt"))
        ,MEHRZEILER_CONTAINER_NR  (S.ms("ContainerNr"))
        ,AUFLISTUNG_GEBINDE (S.ms("Auflistung Gebinde"))
        ,SPRUNGBUTTON_ZU_FUHRE (S.ms("Sprungbutton zur Fuhre"))
        ,KENNER_TEXT_HANDWIEGUNG (S.ms("Text Handwiegung"))
        ,BUTTON_VERKNUEPFUNG_MIT_FUHRE (S.ms("Verknüpfung mit Fuhre"))
        ,ROW_VERKNUEPFUNG_MIT_FUHRE (S.ms("Row Verknüpfung mit Fuhre"))
        ,LAGERPLATZ_PFAD (S.ms("Pfad des Lagerplatzes"))
        ,MEHRZEILER_KENNZEICHEN(S.ms("Kennzeichen"))
       
         
        ;
    	
    	
    	;
        
        private MyE2_String userText = null; 
        private String      m_dbVal = null;
        
        private WK_RB_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        //konstuktor mit abweichenden werten
        private WK_RB_NAMES(MyE2_String p_userText, String dbVal) {
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
        
        public MyE2_String getUserText() {
            if (this.userText!=null && S.isFull(this.userText)) {
                return this.userText;
            }
            return S.msUt(this.name());
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
    	return _TAB.wiegekarte;
    }
    
    
    /**
     * 
     * @return der ueberall verwendete mask-key
     * @throws myException
     */
    public static RB_KM getLeadingMaskKey() throws myException {
    	return RecDOWiegekarte.key;
    }
    
    
    
    
    
    
    
    public static enum WK_RB_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_WIEGEKARTE")
        ,EDIT("BEARBEITE_WIEGEKARTE")
        ,VIEW("ANZEIGE_WIEGEKARTE")
        ,NEW("NEUEINGABE_WIEGEKARTE")
        ,
        
        ;
        
        private String KEY = null;
        
        private WK_RB_BUTTONS() {
            this.KEY=this.name();
        }
        private WK_RB_BUTTONS(String key) {
            this.KEY=key;
        }
        @Override
		public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(WK_RB_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    
    
    /*
     * enum: hier koennen numerische (long) werte zentral gesteuert werden 
     */
    public static enum WK_RB_NUM_CONST {
         MASKPOPUP_WIDTH(new Integer(800))
        ,MASKPOPUP_HEIGHT(new Integer(800))
        ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(200))
        ,MASKPOPUP_FIELD_COL_SIZE(new Integer(600))
        
        ,MASK_COL_1(new Integer(150))
        ,MASK_COL_2(new Integer(50))
        ,MASK_COL_3(new Integer(120))
        ,MASK_COL_4(new Integer(100))
        ,MASK_COL_5(new Integer(120))
        ,MASK_COL_6(new Integer(100))
        ,MASK_COL_7(new Integer(100))
        ,MASK_COL_8(new Integer(100))
        ,MASK_COL_9(new Integer(100))
        ,MASK_COL_10(new Integer(100))
        ,MASK_COL_11(new Integer(100))
        ,MASK_COL_12(new Integer(100))
        ,MASK_COL_13(new Integer(100))
        ,MASK_COL_14(new Integer(100))
        ,MASK_COL_15(new Integer(100))
        ;
    	
    	private Integer   m_value = null;
    	
    	private WK_RB_NUM_CONST(Integer p_value) {
    		this.m_value=p_value;
    	}
    	
    	public Integer getValue() {
    		return this.m_value;
    	}
    }
    
    
    /**
     * Keys für zusätzliche Komponenten
     * @author manfred
     * @date 20.03.2020
     *
     */
    public enum MASK_KEYS_String {
    	 COMP_WAEGUNG1			("6778456c-9152-4330-9bb2-50d92d891425", S.ms("Wägung 1").CTrans())
    	,COMP_WAEGUNG2			("85daa0c2-4227-48ad-a706-5a98b2663d59", S.ms("Wägung 2").CTrans())
    	,COMP_WAAGELISTE		("caacf6ec-31f6-4106-978b-5ddc22814d8e", S.ms("Waage-Liste").CTrans())
    	,COMP_WEWA				("e52ea1fb-3634-4b55-817c-70a2c415f02a", S.ms("WeWa").CTrans())
    	,COMP_FUHRE         	("58996376-8656-42ce-9d11-0c6ce803bccf", S.ms("Fuhre").CTrans())
    	,COMP_SEL_WAAGE_USER	("cad51ef4-4e93-4e17-8633-f0af141a95e5", S.ms("Wagebenutzer").CTrans())
        ,SEARCH_ARTIKELBEZ		("d4ecd5af-63b9-448f-889d-68838560baf4", S.ms("Artikel Hand").CTrans())
        ,SELECT_KUNDENARTIKEL	("fbf9fcd8-a687-423c-a4fe-8b3a32f60d2a", S.ms("Kundenartikel").CTrans())
        ,CB_FREMDCONTAINER		("0bdc940e-cd76-4f6e-b6ec-bba6e048eef5", S.ms("Fremdcontainer-Nr").CTrans())
        ,COMP_GUETERKATEGORIE   ("d887b8f3-7a3b-4121-97c9-aa712d2d915d", S.ms("Güterkategorie").CTrans())
        ,CHILD_ABZUG_GEBINDE    ("68aeeff2-e3fc-491c-8604-79982a0dbe55",S.ms("Abzugsliste Gebinde").CTrans())
        ,CHILD_ABZUG_MENGE    	("78429753-29c7-4cc1-af5d-3dd458752ccb",S.ms("Abzugsliste Mengen").CTrans())
        ,CHILD_BEFUNDUNG        ("a0d6f34c-7830-4cae-933e-359544c4ff47",S.ms("Befundung").CTrans())
        ,BT_FOLGEWAEGUNG		("3cf93ff1-aa27-40ba-8414-2bc60cbba06d",S.ms("Folge-Wägung").CTrans() )
        
        ,COMP_SEL_DRUCKTYP      ("c6ed6fd5-25b6-40eb-9a20-055ea7131b22",S.ms("Drucktyp").CTrans() )
    	,BT_PRINT_WK 			("8ae954f1-eec2-45bc-b02f-e9f10ed09f3e",S.ms("Drucke Wiegekarte").CTrans() )
    	,BT_PRINT_ETIKETT		("3716d5a3-82c1-44b7-a917-1aebf03ede19",S.ms("Drucke Etiketten").CTrans() )
    	,BT_PRINT_BUERO			("3770f983-94e1-4a76-93a6-f69a8af4b7a5",S.ms("Drucke Büro").CTrans() )
    	,BT_PRINT_HOFSCHEIN	    ("5f6a6ac7-2cc8-4ab4-ae74-4fcdf5e8f861",S.ms("Drucke Hofschein").CTrans() )
    	,TXT_NUM_ETIKETTEN		("b42559a2-7dc8-4a92-bb4e-74ae09e46942",S.ms("Anzahl Etiketten").CTrans() )
    	,BT_PRINT_BEFUNDUNG		("f12f8007-bedc-4d4c-8779-015e5d1ef19c",S.ms("Drucke Befundung").CTrans() )
    	
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
    	
    	/**
    	 * gibt den Realname zurück 
    	 * @author manfred
    	 * @return
    	 */
    	public String realname() {
    		return _name;
    	}
    	
    	
    }

    
    /**
     * enum für Einstellungen, die für die ganze Maske gelten
     * @author manfred
     * @date 07.04.2020
     *
     */
    public enum WK_RB_TransportExtender implements RB_TransportHashMapEnumExtender{
    	ID_LAGER,
    	ID_STANDORT,
    	GRID_4_MASK_EXTERNAL,
    	
    }
    
    
    
    
    /**
     * Übernommen von roman-ENUM
     * @author manfred
     * @date 08.03.2021
     *
     */
    public enum EnumTaetigkeit  {

	    EINGANGSWIEGUNG("EINGANGSWIEGUNG", true, false, false, false, true),
	    AUSGANGSWIEGUNG("AUSGANGSWIEGUNG", false, true, false, false, false),
	    UMSTELLEN("UMSTELLEN", false, false, true, false, false),
	    LEEREN("LEEREN", false, false, false, true, false),
	    KIPPEN("KIPPEN", false, false, false, true, false),
	    FUELLEN("FUELLEN", false, false, false, false, true),
	    CONTAINERAUSGANG_OHNE_WAAGE("CONTAINERAUSGANG_OHNE_WAAGE", false, true, false, false, false),
	    STORNO("STORNO",false,  true,   false, false,false );

	    private String id;

	    private Boolean isZyklusStart;
	    private Boolean isZyklusEnd;
	    private Boolean isMove;
	    private Boolean isLeer;
	    private Boolean isVoll;


	    EnumTaetigkeit(String value, Boolean isZyklusStart, Boolean isZyklusEnd, Boolean isMove, Boolean isLeer, Boolean isVoll) {
	        this.id = value;
	        this.isZyklusStart=isZyklusStart;
	        this.isZyklusEnd=isZyklusEnd;
	        this.isMove=isMove;
	        this.isLeer=isLeer;
	        this.isVoll=isVoll;
	    }

	    public String getId() {
	        return id;
	    }

	    public static EnumTaetigkeit fromId(String id) {
	        for (EnumTaetigkeit at : EnumTaetigkeit.values()) {
	            if (at.getId().equals(id)) {
	                return at;
	            }
	        }
	        return null;
	    }


	    public Boolean getZyklusStart() {
	        return isZyklusStart;
	    }

	    public Boolean getZyklusEnd() {
	        return isZyklusEnd;
	    }

	    public Boolean getMove() {
	        return isMove;
	    }

	    public Boolean getLeer() {
	        return isLeer;
	    }

	    public Boolean getVoll() {
	        return isVoll;
	    }

	}
    
    
    
    
}
 
 
