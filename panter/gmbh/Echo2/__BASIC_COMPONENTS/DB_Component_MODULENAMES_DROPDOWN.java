package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_MODULENAMES_DROPDOWN extends MyE2_DB_SelectFieldWithParameter {

	public DB_Component_MODULENAMES_DROPDOWN(SQLField osqlField, Extent  oWidth, Font oFont ) throws myException {
		super (osqlField);

		String cAbfrageTable = "SELECT "+_DB.FIELD_RULE_MODULEKENNER$MODULEKENNER+","+_DB.FIELD_RULE_MODULEKENNER$MODULEKENNER+" FROM "+bibE2.cTO()+"."+_DB.FIELD_RULE_MODULEKENNER +
											" ORDER BY "+_DB.Z_FIELD_RULE_MODULEKENNER$MODULEKENNER;
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);
		
		cTabellen  = bibARR.add_emtpy_db_value_inFront(cTabellen);
		
		this.set_ListenInhalt(cTabellen, false);
		
		if (oWidth != null) {
			this.setWidth(oWidth);
		}
		if (oFont!=null) {
			this.setFont(oFont);
		}
	}

}
