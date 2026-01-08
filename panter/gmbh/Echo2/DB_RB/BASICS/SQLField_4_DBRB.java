package panter.gmbh.Echo2.DB_RB.BASICS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class SQLField_4_DBRB extends SQLField {

	public SQLField_4_DBRB(String tableName, String fieldname,  MyMetaFieldDEF oMetaDef ) throws myException {
		super(tableName, fieldname, fieldname, new MyE2_String(fieldname),oMetaDef.get_bFieldNullableBasic(), bibE2.get_CurrSession());

		this.set_bWriteable(true);
		this.set_bFieldCanBeWrittenInMask(true);
		//initialisieren
		this.set_oFieldMetaDef(oMetaDef);
	}

}
