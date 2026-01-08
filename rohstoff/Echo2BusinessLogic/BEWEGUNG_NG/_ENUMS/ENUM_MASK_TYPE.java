package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_NewLG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_NewLL;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_NewST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_NewWA;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_NewWE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW.FZ_AA_newTS;

/**
 * fuer benutzer auswählbare maskentypen
 * @author martin
 *
 */
public enum ENUM_MASK_TYPE  implements IF_enum_4_db {

	WARENEINGANG_EINFACH(			ENUM_VEKTOR_TYP.WE,		"Wareneingang (einfach)"	,"WE")
	,WARENAUSGANG(					ENUM_VEKTOR_TYP.WA,		"Warenausgang"				,"WA")
	,STRECKE(						ENUM_VEKTOR_TYP.ST,		"Strecke",					"S")
	,LAGER_ZU_LAGER(				ENUM_VEKTOR_TYP.LL,		"Lager-Lager",				"LL")
	,UMBUCHUNG_SORTENWECHSEL(		ENUM_VEKTOR_TYP.UMB,		"Umbuchung Sortenwechsel",	"UMB")
	,KORREKTUR_INS_OFF(				ENUM_VEKTOR_TYP.KORR,	"Schwund vom Lager",		"KORR")
	,KORREKTUR_VOM_OFF(				ENUM_VEKTOR_TYP.KORR,	"Zugang zum Lager",			"KORR")
	,LEERGUT( 						ENUM_VEKTOR_TYP.LG,     	"Leergutfuhre",				"LG")
	,PROBE(							ENUM_VEKTOR_TYP.TS, 		"Probestellung",			"TS")						
	
	;
	
	private ENUM_VEKTOR_TYP  	vector_type = 	null;
	private String  		user_text = 	null; 
	private String    		dbConst = null;
	
	private ENUM_MASK_TYPE(ENUM_VEKTOR_TYP p_v_type, String p_user_text, String p_db_const) {
		this.vector_type =	p_v_type;
		this.user_text	=	p_user_text;
		this.dbConst = p_db_const;
	}

	public String db_val() {
		return this.name();
	}

	public String user_text() {
		return user_text;
	}

	@Override
	public String user_text_lang() {
		return user_text;
	}
	
	public String DB_CONST() {
		return this.dbConst;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_MASK_TYPE.values(), emptyPairInFront);
	}
	
	public ENUM_VEKTOR_TYP getVectorType() {
		return vector_type;
	}

	public static ENUM_MASK_TYPE find_typ(String dbVal) {
		for (ENUM_MASK_TYPE typ: ENUM_MASK_TYPE.values()) {
			if (typ.db_val().equals(dbVal)) {
				return typ;
			}
		}
		return null;
	}

	
	
	public static String[][] get_ddArray(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_MASK_TYPE.values(), emptyPairInFront);
	}
	
	
	
	public static XX_ActionAgent generate_NewAgent(ENUM_MASK_TYPE typ, E2_NavigationList naviList) {
		XX_ActionAgent  agent = null;
		
		switch (typ) {
		case WARENEINGANG_EINFACH:
			agent = new FZ_AA_NewWE(naviList);
			break;
	
		case WARENAUSGANG:
			agent = new FZ_AA_NewWA(naviList);
			break;
			
		case STRECKE:
			agent = new FZ_AA_NewST(naviList);
			break;
			
		case LAGER_ZU_LAGER:
			agent = new FZ_AA_NewLL(naviList);
			break;
		
		case LEERGUT:
			agent = new FZ_AA_NewLG(naviList);
			break;

		case PROBE:
			agent = new FZ_AA_newTS(naviList);
			break;
			
		default:
			break;
		}

		return agent;
	}

	
	
	
}
