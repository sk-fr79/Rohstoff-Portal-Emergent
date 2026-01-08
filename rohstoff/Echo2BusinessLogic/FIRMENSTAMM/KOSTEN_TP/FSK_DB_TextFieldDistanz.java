package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FSK_DB_TextFieldDistanz extends MyE2_DB_TextField {

	public FSK_DB_TextFieldDistanz(SQLField osqlField) throws myException {
		super(osqlField, true, 40);
	}

}
