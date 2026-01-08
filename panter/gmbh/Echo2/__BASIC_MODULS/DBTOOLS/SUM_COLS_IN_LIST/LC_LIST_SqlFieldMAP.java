package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LC_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public LC_LIST_SqlFieldMAP() throws myException 
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
		this.get_("TEXT4SUMMATION").set_cDefaultValueFormated("");
		this.get_("TEXT4TITLE_IN_WINDOW").set_cDefaultValueFormated("");
		this.get_("TEXT4WINDOWTITLE").set_cDefaultValueFormated("");
		this.get_("TOOLTIPS").set_cDefaultValueFormated("");
		this.get_("VALIDATION_TAG").set_cDefaultValueFormated("");
		
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_COLUMNS_TO_CALC_WICHTIGKEIT","BESCHREIBUNG","ID_LC_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LC_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_COLUMNS_TO_CALC.ID_USER="+cID_USER+" OR JT_COLUMNS_TO_CALC.ID_LC IN (SELECT ID_LC FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
