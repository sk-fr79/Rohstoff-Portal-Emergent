package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum WK_RB_ENUM_DRUCKTYP  implements IF_enum_4_db {

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
	
	 WIEGESCHEIN("WS","Wiegeschein")
	 ,WIEGESCHEIN_EINGANGSSCHEIN("WE","Wiegeschein/Eingangsschein")
	,EINGANGSCHEIN("ES","Eingangsschein/Lieferschein")
	;

	private String dbVal = null;
	private String userText = null;
	
	/**
	 * @param p_userText
	 */
	private WK_RB_ENUM_DRUCKTYP(String p_dbVal, String p_userText) {
		this.userText = p_userText;
		this.dbVal = p_dbVal;
	}
	
	
	private WK_RB_ENUM_DRUCKTYP(String p_dbVal, String p_userText,Boolean p_bOld) {
		this.userText = p_userText;
		this.dbVal = p_dbVal;
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
		return bibENUM.dd_array(WK_RB_ENUM_DRUCKTYP.values(), emptyPairInFront);
	}

	
	public static String[][] get_ddArray(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(WK_RB_ENUM_DRUCKTYP.values(), emptyPairInFront);
	}
	
	
	
	public static WK_RB_ENUM_DRUCKTYP getTyp(String dbVal) throws myException {
		for (WK_RB_ENUM_DRUCKTYP t: WK_RB_ENUM_DRUCKTYP.values()) {
			if (t.db_val().equals(dbVal)) {
				return t;
			}
		}
		throw new myException("Cannot correlate <"+dbVal+"> to member of WiegekartenTyp !");
	}

	
	
	private static String[][] get_array(boolean emptyPairInFront,boolean bShadow){
		int iLive = values().length ;
		
		int iSize = iLive + (emptyPairInFront ? 1 : 0);
		
		String[][] ddarray = new String[ iSize ][2];
		int i=0;
		if (emptyPairInFront) {
			ddarray[i][0]="-";
			ddarray[i][1]="";
			i++;
		} 

		for (IF_enum_4_db stat: values()) {
			ddarray[i][0]=new MyE2_String(stat.user_text()).CTrans();
			ddarray[i][1]=stat.db_val();
			i++;
		}
			
		return ddarray;
	}
	
	
	
	
}
