package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_TABLE_DROPDOWN extends MyE2_DB_SelectField {

	public DB_Component_TABLE_DROPDOWN(SQLField osqlField,  String[][] arrInFront) throws myException {
		super(osqlField);
		
		String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z(bibE2.cTO(),true,true,true);
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);
		
		if (arrInFront!=null) {
			cTabellen = bibARR.add_array_inFront(cTabellen, arrInFront);
		}
		
		this.set_ListenInhalt(cTabellen, false);

	}

	/**
	 * 2014-03-05: neuer konstruktor
	 * @param osqlField
	 * @param bValuesOhnePrefix = 2.spalte ohne jd_ / jt_
	 * @throws myException
	 */
	public DB_Component_TABLE_DROPDOWN(SQLField osqlField, boolean bValuesOhnePrefix, String[][] arrInFront) throws myException {
		super(osqlField);
		
		String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z(bibE2.cTO(),true,true,bValuesOhnePrefix);
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);
		
		if (arrInFront!=null) {
			cTabellen = bibARR.add_array_inFront(cTabellen, arrInFront);
		}
	
		this.set_ListenInhalt(cTabellen, false);

	}

	
}
