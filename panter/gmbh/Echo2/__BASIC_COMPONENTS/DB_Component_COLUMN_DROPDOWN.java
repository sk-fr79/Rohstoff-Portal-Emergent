package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_COLUMN_DROPDOWN extends MyE2_DB_SelectFieldWithParameter {

	public DB_Component_COLUMN_DROPDOWN(SQLField osqlField) throws myException {
		super (osqlField,	
			   " SELECT COLUMN_NAME ,COLUMN_NAME AS TAB2 FROM SYS.USER_TAB_COLUMNS WHERE " +
			   " (TABLE_NAME = 'JT_#TABLE_NAME#' OR TABLE_NAME = 'JD_#TABLE_NAME#') ORDER BY COLUMN_NAME ASC"
			   ,false
			   ,false);
		this.AddParameter("#TABLE_NAME#");
		this.RefreshComboboxFromSQL();

	}

	/**
	 * 2014-03-05: variante mit exakten tabellen-namen
	 * @param osqlField
	 * @param bExactTableName  wenn die tabellennamen komplett geschrieben werden
	 * @throws myException
	 */
	public DB_Component_COLUMN_DROPDOWN(SQLField osqlField, boolean bExactTableName) throws myException {
		super (osqlField,	
			   " SELECT COLUMN_NAME ,COLUMN_NAME AS TAB2 FROM SYS.USER_TAB_COLUMNS WHERE " +
			   (bExactTableName?" (TABLE_NAME = '#TABLE_NAME#')":" (TABLE_NAME = 'JT_#TABLE_NAME#' OR TABLE_NAME = 'JD_#TABLE_NAME#')") +
			   " ORDER BY COLUMN_NAME ASC"
			   ,false
			   ,false);
		this.AddParameter("#TABLE_NAME#");
		this.RefreshComboboxFromSQL();

	}

}
