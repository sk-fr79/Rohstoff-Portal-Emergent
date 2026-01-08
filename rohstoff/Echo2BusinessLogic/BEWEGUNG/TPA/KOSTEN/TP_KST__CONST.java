package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS._DB;

public class TP_KST__CONST {

	public enum MAP_HASHES {
		JUMP_TO_FUHRE
		,JUMP_TO_TPA
		,NAME_OF_CHECKBOX_IN_LIST
		,NAME_OF_LISTMARKER_IN_LIST
	}
	
	
	public enum SQLFIELDMAP_FIELDS {
		
		ADR_START_ID_LAND(			"ADR_START.ID_LAND" ,				new MyE2_String("Land Start"))
		,ADR_START_PLZ(				"ADR_START.PLZ" ,					new MyE2_String("PLZ Start"))
		,ADR_START_ORT(				"ADR_START.ORT" ,					new MyE2_String("Ort Start"))
		,ADR_ZIEL_ID_LAND(			"ADR_ZIEL.ID_LAND" ,				new MyE2_String("Land Ziel"))
		,ADR_ZIEL_PLZ(				"ADR_ZIEL.PLZ" ,					new MyE2_String("PLZ Ziel"))
		,ADR_ZIEL_ORT(				"ADR_ZIEL.ORT" ,					new MyE2_String("Ort Ziel"))
		,LAND_ZIEL_LAENDERNAME(		"LAND_ZIEL.LAENDERNAME" ,			new MyE2_String("Land Ziel"))
		,LAND_START_LAENDERNAME(	"LAND_START.LAENDERNAME" ,			new MyE2_String("Land Start"))
		,SPEDITION_NAME(			"ADR_SPED.NAME1 || ' ' || ADR_SPED.NAME2 || ', ' || ADR_SPED.ORT" ,new MyE2_String("Spedition"))
		,STARTORT_NORM(             TP_KST__CONST.get_KernStringNormierterOrt("ADR_START"), new MyE2_String("Normalisierter Startort"))
		,ZIELORT_NORM(              TP_KST__CONST.get_KernStringNormierterOrt("ADR_ZIEL"),  new MyE2_String("Normalisierter Zielort"))
		,ID_FUHRE(   	            _DB.Z_VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE,  new MyE2_String("Fuhren-ID"))
		,ID_TPA(   	            	_DB.Z_VKOPF_TPA$ID_VKOPF_TPA,  		new MyE2_String("TPA-ID"))
		,FU_SORTE( 					"NVL(JT_ARTIKEL.ARTBEZ1,'-')||' ('||NVL(JT_ARTIKEL.ANR1,'-')||')'",new MyE2_String("Sorte (Fuhre)"))
		; 
		
		
		private String 			SqlQuery = null;
		private MyE2_String     Description = null;
		
		SQLFIELDMAP_FIELDS(String sqlQuery, MyE2_String description) {
			this.SqlQuery = sqlQuery;
			this.Description = description;
		}

		public String _hash() {
			return this.name();
		}

		public String _query() {
			return SqlQuery;
		}

		public MyE2_String _descript() {
			return Description;
		}
	}
	
	
	public static String get_KernStringNormierterOrt(String TableAlias) {
		String cRueck = "UPPER(REPLACE(REPLACE(REPLACE(REPLACE(UPPER( REGEXP_REPLACE(#1#.ORT, '[^0-9,a-z]','')),'Ä','AE'),'Ö','OE'),'Ü','UE'),'ß','SS'))";
		return cRueck.replace("#1#", TableAlias);
	}

}
