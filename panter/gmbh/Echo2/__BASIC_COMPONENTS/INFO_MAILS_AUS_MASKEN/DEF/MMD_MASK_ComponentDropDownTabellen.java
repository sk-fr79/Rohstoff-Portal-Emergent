package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_ComponentDropDownTabellen extends MyE2_DB_SelectField {

	public MMD_MASK_ComponentDropDownTabellen(SQLField osqlField) throws myException {
		super(osqlField, new Extent(300));
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true),false);
		this.set_ListenInhalt(bibARR.add_emtpy_db_value_inFront(cTabellen), false);
	}

}
