package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_REPORT_WEG implements IF_enum_4_db {
	PREVIEW("Vorschau"),
	PRINT("Druck"),
	MAIL("EMail"),
	UND("Undefiniert")
	;

	String txt ="";
	private ENUM_REPORT_WEG(String txt) {
		this.txt = txt;
	}
	
	@Override
	public String db_val() {
		// TODO Auto-generated method stub
		return this.name();
	}

	@Override
	public String user_text() {
		// TODO Auto-generated method stub
		return txt;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_REPORT_WEG.values(), emptyPairInFront);
	}

}
