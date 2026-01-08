package rohstoff.Echo2BusinessLogic.SPIELWIESE.list;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class SPIELWIESE_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public SPIELWIESE_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_FIBU_KONTENREGEL_NEU", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AKTIV").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTENREGEL_NEU").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTO").set_cDefaultValueFormated("");
		this.get_("ID_FILTER").set_cDefaultValueFormated("");
		this.get_("KOMMENTAR").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_FIBU_KONTENREGEL_NEU_WICHTIGKEIT","BESCHREIBUNG","ID_SPIELWIESE_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_SPIELWIESE_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_FIBU_KONTENREGEL_NEU.ID_USER="+cID_USER+" OR JT_FIBU_KONTENREGEL_NEU.ID_SPIELWIESE IN (SELECT ID_SPIELWIESE FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_NEU_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
