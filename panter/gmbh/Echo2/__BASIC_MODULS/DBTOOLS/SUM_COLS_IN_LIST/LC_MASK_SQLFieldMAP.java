package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LC_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public LC_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_COLUMNS_TO_CALC", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("COLUMN_LABEL").set_cDefaultValueFormated("");
		this.get_("ID_COLUMNS_TO_CALC").set_cDefaultValueFormated("");
		this.get_("MODULNAME_LISTE").set_cDefaultValueFormated("");
		this.get_("NUMBER_DECIMALS").set_cDefaultValueFormated("0");
		this.get_("SUMMATION_VIA_QUERY").set_cDefaultValueFormated("");

		this.get_(_DB.COLUMNS_TO_CALC$ACTIVE).set_cDefaultValueFormated("Y");
		this.get_(_DB.COLUMNS_TO_CALC$SHOW_LINE_IN_LISTHEADER).set_cDefaultValueFormated("Y");
		
		this.get_("TEXT4SUMMATION").set_cDefaultValueFormated("");
		this.get_("TEXT4TITLE_IN_WINDOW").set_cDefaultValueFormated("");
		this.get_("TEXT4WINDOWTITLE").set_cDefaultValueFormated("");
		this.get_("TOOLTIPS").set_cDefaultValueFormated("");
		this.get_("VALIDATION_TAG").set_cDefaultValueFormated("");
		


		this.initFields();
	}

}
