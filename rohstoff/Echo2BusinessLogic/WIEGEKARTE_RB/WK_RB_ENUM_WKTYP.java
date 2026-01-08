package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum WK_RB_ENUM_WKTYP  implements IF_enum_4_db {

	/**
	 * Konstanten aus "altem" Wiegeschein 
	public static final String TYP_WIEGEKARTE_WIEGESCHEIN = "W";
	public static final String TYP_WIEGEKARTE_STRECKE = "S";
	public static final String TYP_WIEGEKARTE_FREMDWIEGUNG = "F";
	public static final String TYP_WIEGEKARTE_WIEGESCHEIN_LIEFERSCHEIN = "WL";
	
	public static final String TYP_WIEGEKARTE_LAGER = "LG";
	public static final String TYP_WIEGEKARTE_RETOURE = "RE";
	public static final String TYP_WIEGEKARTE_LEERFUHRE = "LE";
	public static final String TYP_WIEGEKARTE_DOKUMENTARISCH = "D";
	 * 
	 */
	
	 WIEGESCHEIN("W","Wiegeschein")
	,GESAMTVERWIEGUNG("G","Gesamtverwiegung")
	,SORTIERVERWIEGUNG("SO","Sortierverwiegung")
	,DOKUMENTARISCH("D","Dokumentarische Verwiegung")
	,STRECKE("S","Streckenschein",true)
	,LG("LG", "Lagerverwiegung")
	,FREMD("F","Fremdverwiegung")
	,WL("WL", "Wiegekarte/Lieferschein",true)
	,RE("RE", "Retoure",true)
	,LE("LE", "Leerfuhre",true)
	,WIEGEKARTE_KORREKTUR("WK","Korrekturwiegekarte (nach Storno)",true)
	,WKTYP_NONE("NO","KEIN TYP AUSGEWÄHLT",true)
	;

	private String dbVal = null;
	private String userText = null;
	private Boolean bOld = null;
	
	/**
	 * @param p_userText
	 */
	private WK_RB_ENUM_WKTYP(String p_dbVal, String p_userText) {
		this.userText = p_userText;
		this.dbVal = p_dbVal;
		this.bOld = false;
	}
	
	
	private WK_RB_ENUM_WKTYP(String p_dbVal, String p_userText,Boolean p_bOld) {
		this.userText = p_userText;
		this.dbVal = p_dbVal;
		this.bOld = p_bOld;
	}
	
	
	
	@Override
	public String db_val() {
		return dbVal;
	}

	@Override
	public String user_text() {
		return userText;
	}

	@Override
	public String user_text_lang() {
		return userText;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(WK_RB_ENUM_WKTYP.values(), emptyPairInFront);
	}

	
	public static String[][] get_ddArray(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(WK_RB_ENUM_WKTYP.values(), emptyPairInFront);
	}
	
	public static String[][] get_ddArray(boolean emptyPairInFront,boolean bShadow) throws myException {
//		return bibENUM.dd_array(WK_RB_ENUM_WKTYP.values(), emptyPairInFront);
		return get_array(emptyPairInFront, bShadow);
	}
	
	
	public static WK_RB_ENUM_WKTYP getTyp(String dbVal) throws myException {
		for (WK_RB_ENUM_WKTYP t: WK_RB_ENUM_WKTYP.values()) {
			if (t.db_val().equals(dbVal)) {
				return t;
			}
		}
		throw new myException("Cannot correlate <"+dbVal+"> to member of WiegekartenTyp !");
	}

	
	
	private static String[][] get_array(boolean emptyPairInFront,boolean bShadow){
		int iShadow = 0;
		
		// count of inactive entries
		for (IF_enum_4_db stat: values()) {
			if(((WK_RB_ENUM_WKTYP)stat).bOld) {
				iShadow++;
			}
		}

		int iLive = values().length - iShadow;
		
		int iSize = (bShadow ? iShadow : iLive) + (emptyPairInFront ? 1 : 0);
		
		String[][] ddarray = new String[ iSize ][2];
		int i=0;
		if (emptyPairInFront) {
			ddarray[i][0]="-";
			ddarray[i][1]="";
			i++;
		} 

		for (IF_enum_4_db stat: values()) {
			if(	(((WK_RB_ENUM_WKTYP)stat).bOld && bShadow)  || 	(!((WK_RB_ENUM_WKTYP)stat).bOld && !bShadow)  ) {
					ddarray[i][0]=new MyE2_String(stat.user_text()).CTrans();
					ddarray[i][1]=stat.db_val();
					i++;
				}
		}
			
		return ddarray;
	}
	
	
	
	
}
