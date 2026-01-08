package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_STATION_TYP implements IF_enum_4_db{
	
	START_STATION()
	,ZIEL_STATION()
	;
	
	private String key = null;
	private String user_text = null;
	
	private ENUM_STATION_TYP() {
	}

	@Override
	public String db_val() {
		return S.NN(this.key,this.name());
	}

	@Override
	public String user_text() {
		return S.NN(this.key,this.name());
	}

	@Override
	public String user_text_lang() {
		return S.NN(this.key,this.name());
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return null;
	}
	
}
