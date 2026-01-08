package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOG;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CHANGELOG__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public CHANGELOG__LIST_SqlFieldMAP() throws myException 
	{
		super("JT_CHANGELOG", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
		this.get_("COLNAME").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_AM").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_VON").set_cDefaultValueFormated("");
		this.get_("ID_CHANGELOG").set_cDefaultValueFormated("");
		this.get_("ID_TABLE").set_cDefaultValueFormated("");
		this.get_("NEW_VALUE").set_cDefaultValueFormated("");
		this.get_("OLD_VALUE").set_cDefaultValueFormated("");
		this.get_("TABLENAME").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_CHANGELOG_WICHTIGKEIT","BESCHREIBUNG","ID_CHANGELOG__WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_CHANGELOG__WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_CHANGELOG.ID_USER="+cID_USER+" OR JT_CHANGELOG.ID_CHANGELOG_ IN (SELECT ID_CHANGELOG_ FROM "+bibE2.cTO()+".JT_CHANGELOG_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
