package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


public class KFIX___CONST {
	
	public static final String HASH_KEY_ANZEIGE_LOCKED = 							"HASH_KEY_ANZEIGE_LOCKED";
	public static final String HASH_KEY_ANZEIGE_LOCKED_POS =						"HASH_KEY_ANZEIGE_LOCKED_POS";
	public static final String HASH_KEY_ANZEIGE_POSITIONS = 						"HASH_KEY_ANZEIGE_POSITIONS";
	public static final String HASH_KEY_ANZEIGE_FIX =								"HASH_KEY_ANZEIGE_FIX";
	public static final String HASH_KEY_ANZEIGE_FIX_GESAMT_MENGE =					"HASH_KEY_ANZEIGE_FIX_GESAMT_MENGE";
	public static final String HASH_KEY_ANZEIGE_FIX_AKTUELL_MENGE =					"HASH_KEY_ANZEIGE_FIX_AKTUELL_MENGE";
	
	public static final String HASH_KEY_BUTTON_ANLAGE =								"HASH_KEY_BUTTON_ANLAGE";
	
	public static E2_ResourceIcon LABEL_EMPTY = 									E2_ResourceIcon.get_RI("empty20.png")	;
	public static E2_ResourceIcon LABEL_POSITION_LOCKED  = 							E2_ResourceIcon.get_RI("locked.png");
	public static E2_ResourceIcon LABEL_POSITION_UNLOCKED  = 						E2_ResourceIcon.get_RI("unlocked.png");
	public static E2_ResourceIcon LABEL_KOPF_FIXIERT =								E2_ResourceIcon.get_RI("fix.png");
	public static E2_ResourceIcon LABEL_KOPF_FIXIERT_TOTAL =						E2_ResourceIcon.get_RI("fix_total.png");
	public static E2_ResourceIcon LABEL_KOPF_FIXIERT_PROBLEM =						E2_ResourceIcon.get_RI("fix_prob.png");
	public static E2_ResourceIcon LABEL_KOPF_NORMAL = 								E2_ResourceIcon.get_RI("fix_norm.png");
	

	public static final String HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG = 				"HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG";
	public static final String HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE = 				"HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE";
	
//	public static int  KONTRAKTPOS_OK = 					1000000;    // alles im Rahmen
//	public static int  KONTRAKTPOS_UEBERLIEFERT_ABER_OK = 	1000001;    // ueberliefert, aber erlaubt
//	public static int  KONTRAKTPOS_UEBERLIEFERT  = 			1000002;    // ueberliefert, aber verboten
	

//	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_KEINEN_FUHREN_ZUGEORDNET =
//							new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
//					                "   NVL(DELETED,'N')='N' AND ( ID_VPOS_KON_EK=#WERT# OR  ID_VPOS_KON_VK=#WERT#) ",
//									bibALL.get_Array("0"),
//									true, new MyE2_String("Aktion ist verboten, die Kontraktposition habt bereits zugeordnete Fuhren !"));
//
	
	
	
//	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_ARTIKELPOS_MENGE_PREIS_NOT_LEER = 
//									new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
//												" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
//												" ANZAHL IS NOT NULL AND EINZELPREIS IS NOT NULL AND "+
//												" ID_VPOS_KON=#WERT#",
//												bibALL.get_Array("1"),
//												true, new MyE2_String("Nur bei komplett ausgefüllten Mengenpositionen erlaubt !"));

	
	//2011-12-05: Sprungbutton von kontraktlist in die fuhre
	public static String 			BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE = "BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE";
	

	
	
//	public enum BSK_P_BUTTONS implements IF_enum_4_db  {
//        DELETE("LOESCHE_VPOS_KON")
//        ,EDIT("BEARBEITE_VPOS_KON")
//        ,VIEW("ANZEIGE_VPOS_KON")
//        ,NEW("NEUEINGABE_VPOS_KON")
//        ,
//        
//        ;
//        
//        private String KEY = null;
//        
//        private BSK_P_BUTTONS() {
//            this.KEY=this.name();
//        }
//        private BSK_P_BUTTONS(String key) {
//            this.KEY=key;
//        }
//        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
//        @Override
//        public String user_text_lang() {return this.name();    }
//        @Override
//        public String user_text() {        return this.name();        }
//        @Override
//        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
//            return bibENUM.dd_array(BSK_P_BUTTONS.values(), emptyPairInFront);
//        }
//	        
//	}
//
	
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR_POS {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner VPOS_KON_LIST  und VPOS_KON_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
    	MASK_VK(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_MASK_NG),
        MASK_EK(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_MASK_NG)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR_POS(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
   
    
    public enum ADDITIONNAL_COMP_POS{
    	HASHKEY_INFO_BUCHUNGNR					(VPOS_KON.id_vpos_kon)
    	,HASHKEY_INFO_BUCHUNGPOSNR				(VPOS_KON.positionsnummer)
    	,HASHKEY_INFO_MENGE						(VPOS_KON.anzahl)
    	,HASHKEY_INFO_FIRMA						(VPOS_KON.id_vpos_kon)
    	,HASHKEY_INFO_SORTE						(VPOS_KON.id_artikel)
    	,HASHKEY_INFO_PREIS						(VPOS_KON.einzelpreis)

    	,HASHKEY_BT_KURS_HOLEN					(VPOS_KON.id_vpos_kon)
    	,HASHKEY_BT_BERECHNUNG					(VPOS_KON.id_vpos_kon)
    	,HASHKEY_BT_RECHNEN						(VPOS_KON.id_vpos_kon)
    	,HASHKEY_BT_LIEFERORT					(VPOS_KON_TRAKT.lieferort)
    	,HASHKEY_GRID_INFO						(VPOS_KON.id_vkopf_kon)
//    	,HASHKEY_INFO_FIX_FIX_MGE(VPOS_KON.id_vkopf_kon)
    	,HASHKEY_LIEFERUNG_LIST					(VPOS_KON.id_vpos_kon)
    	,HASHKEY_LIEFERTERMINE					(VPOS_KON.id_vpos_kon)
    	,HASHKEY_AENDERUNG						(VPOS_KON.id_vpos_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH			(VPOS_KON.id_vpos_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE	(VPOS_KON.id_vpos_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO	(VPOS_KON.id_vpos_kon)
    	,HASH_KEY_BT_SET_FIXIERUNGS_ZEITRAUM	(VPOS_KON.id_vpos_kon)
    	,HASHKEY_BT_LIEFERANT					(VPOS_KON_TRAKT.id_vpos_kon)
    	
    	,HASHKEY_KOPF_FIX_VON					(VPOS_KON.id_vkopf_kon)
    	,HASHKEY_KOPF_FIX_BIS					(VPOS_KON.id_vkopf_kon)
    	,HASHKEY_KOPF_FIX_GESAMTMENGE			(VPOS_KON.id_vkopf_kon)
    	,CALC_RESTMENGE							(VPOS_KON.id_vkopf_kon)
    	,TA_GELIEFERT							(VPOS_KON.id_vkopf_kon)
    	,TA_GELIEFERT_RESTMENGE					(VPOS_KON.id_vkopf_kon)
    	,TA_FIXIERT								(VPOS_KON.id_vkopf_kon)
    	,TA_FIXIERT_RESTMENGE					(VPOS_KON.id_vkopf_kon)
    	,HASHKEY_AUTOFILL_ANZAHL_BT				(VPOS_KON.id_vkopf_kon)
    	,LBL_EINHEIT							(VPOS_KON.id_vkopf_kon)
    	,LBL_EINHEIT_2							(VPOS_KON.id_vkopf_kon)
    	,LBL_WAEHRUNG							(VPOS_KON.id_waehrung_fremd)
    	,LBL_WAEHRUNG_2							(VPOS_KON.id_waehrung_fremd)
    	,HASHKEY_AVV_CODE_LIST					(VPOS_KON.id_vpos_kon)
    	;
    	
    	private IF_Field  field = null;
    	
    	
    	private ADDITIONNAL_COMP_POS(IF_Field  _field) {
    		this.field=_field;
    	}
 	
    	public RB_KF key() throws myException {
   			return new RB_KF(this.field,this.name());
    	}
    }

	
    
    
    
    
    
    public enum BSK_M_VK_BUTTONS implements IF_enum_4_db  {

    	DELETE("LOESCHE_VKOPF_KON")
        ,EDIT("BEARBEITE_VKOPF_KON")
        ,VIEW("ANZEIGE_VKOPF_KON")
        ,NEW("NEUEINGABE_VKOPF_KON");
        
        private String KEY = null;
        
        private BSK_M_VK_BUTTONS() {
            this.KEY=this.name();
        }
        
        private BSK_M_VK_BUTTONS(String key) {
            this.KEY=key;
        }
        
        public String     db_val() {    
        	return S.isEmpty(this.KEY)?this.name():this.KEY;
        }
        @Override
        
        public String user_text_lang() {
        	return this.name();    
        }
        
        @Override
        public String user_text() {        
        	return this.name();        
        }
       
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(BSK_M_VK_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR_KOPF {
    	
        MASK_VK(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_MASK_NG),
        MASK_EK(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_MASK_NG)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR_KOPF(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }
    
    
    
    
	public enum ADDITIONNAL_COMP_KOPF {
		
    	MASK_ANSPRECHPARTNER_BT(VKOPF_KON.id_vkopf_kon)
    	,DAUGHTERTABLE_PRINTPROTOKOLL(VKOPF_KON.id_vkopf_kon)
    	,TA_ANR1(VKOPF_KON.id_vkopf_kon)
    	,TA_ANR2(VKOPF_KON.id_vkopf_kon)
    	,TA_FIXIERT(VKOPF_KON.id_vkopf_kon)
    	,TA_FIXIERT_RESTMENGE(VKOPF_KON.id_vkopf_kon)
    	,TA_GELIEFERT(VKOPF_KON.id_vkopf_kon)
    	,TA_GELIEFERT_RESTMENGE(VKOPF_KON.id_vkopf_kon)
    	,CALC_RESTMENGE(VKOPF_KON.id_vkopf_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH(VKOPF_KON.id_vkopf_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE(VKOPF_KON.id_vkopf_kon)
    	,HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO(VKOPF_KON.id_vkopf_kon)
    	,HASH_KEY_LAB_EINHEIT(VKOPF_KON.fix_id_artikel)
    	,DAUGHTERTABLE_POSITION(VKOPF_KON.id_vkopf_kon)
   
    	;
    	
    	private IF_Field  field = null;
    	
    	private ADDITIONNAL_COMP_KOPF(IF_Field  _field) {
    		this.field=_field;
    	}
 	
    	public RB_KF key() throws myException {
   			return new RB_KF(this.field,this.name());
    	}
    }
    
    public enum IKON {
    	
    	EMPTY("empty.png");
    	
    	private String ikon_file_name ="";
    	
    	private IKON(String oName) {
    		ikon_file_name = oName;
    	}
    	
    	public E2_ResourceIcon getIkon(){
    		return E2_ResourceIcon.get_RI(ikon_file_name);
    	}
    }

	
}

