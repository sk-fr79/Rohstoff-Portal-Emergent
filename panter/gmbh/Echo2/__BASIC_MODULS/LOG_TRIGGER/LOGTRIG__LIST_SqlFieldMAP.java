package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public LOGTRIG__LIST_SqlFieldMAP() throws myException 
	{
		super("JD_TRIGGER_DEF", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_AM").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_VON").set_cDefaultValueFormated("");
		this.get_("ID_TRIGGER_DEF").set_cDefaultValueFormated("");
		this.get_("SPALTE").set_cDefaultValueFormated("");
		this.get_("TABELLE").set_cDefaultValueFormated("");
		this.get_("TRIGG_NAME").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TRIGGER_DEF_WICHTIGKEIT","BESCHREIBUNG","ID_LOGTRIG__WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LOGTRIG__WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_TRIGGER_DEF.ID_USER="+cID_USER+" OR JT_TRIGGER_DEF.ID_LOGTRIG_ IN (SELECT ID_LOGTRIG_ FROM "+bibE2.cTO()+".JT_TRIGGER_DEF_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
