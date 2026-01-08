package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RB_SQLField extends SQLField {

	public RB_SQLField(String tableName, String fieldname) throws myException {
		super(tableName, fieldname, fieldname, new MyE2_String(fieldname),true, bibE2.get_CurrSession());

		this.set_bWriteable(true);
		this.set_bFieldCanBeWrittenInMask(true);
		//initialisieren
		MyMetaFieldDEF mf = bibSES.get_MetaFieldDEF(tableName, fieldname);
		this.set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(mf.get_bFieldNullableBasic());
		this.set_oFieldMetaDef(mf);
	}

	
	public RB_SQLField(IF_Field field) throws myException {
		super(field.fullTableName(), field.fieldName(), field.fieldName(), new MyE2_String(field.fieldName()),true, bibE2.get_CurrSession());

		this.set_bWriteable(true);
		this.set_bFieldCanBeWrittenInMask(true);
		//initialisieren
		MyMetaFieldDEF mf = field.generate_MetaFieldDef();
		this.set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(mf.get_bFieldNullableBasic());
		this.set_oFieldMetaDef(mf);
	}

	
	
}
