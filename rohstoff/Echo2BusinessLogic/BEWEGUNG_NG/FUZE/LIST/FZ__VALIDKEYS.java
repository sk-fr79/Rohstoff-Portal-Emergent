package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public  enum FZ__VALIDKEYS  implements IF_enum_4_db {
	__ALL__("@@@ALL@@@");   //pseudoschluessel

	
	private String KEY = null;
	private FZ__VALIDKEYS(String key) {
		this.KEY=key;
	}

	public String     db_val() {	return S.isEmpty(this.KEY)?this.name():this.KEY;}

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
		return bibENUM.dd_array(FZ__VALIDKEYS.values(), emptyPairInFront);
	}


	
}
