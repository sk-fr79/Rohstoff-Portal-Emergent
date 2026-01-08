package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class TODO_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public TODO_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_TODO", "", false);
		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TODO_WICHTIGKEIT","BESCHREIBUNG","ID_TODO_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_TODO_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());

		
		/*
		 * jetzt nachsehen, was fuer einen todo-supervisor-status der benutzer hat und was er sieht
		 */
		String cID_USER = bibALL.get_ID_USER();
		String cTODO_SUPERVISOR = new BASIC_RECORD_USER(bibALL.get_ID_USER()).get_IST_SUPERVISOR_cUF_NN("0");
		
		// TODO_SUPERVISOR = 0 heiﬂt keine sonderrechte
		// TODO_SUPERVISOR = 1 heiﬂt, der benutzer darf den to-do-besitzer ‰ndern
		// TODO_SUPERVISOR = 2 heiﬂt, der benutzer sieht alle anderen to-do-eintr‰ge und
		
		String cSonderQuery = "";
		if (!bibALL.get_bIST_SUPERVISOR() && (cTODO_SUPERVISOR.equals("0") || cTODO_SUPERVISOR.equals("1")))
		{
			// sieht nur seine eigenen und die, wo er beteiligt ist
			cSonderQuery =  "(JT_TODO.ID_USER="+cID_USER+" OR JT_TODO.ID_TODO IN (SELECT ID_TODO FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		}
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		
		this.initFields();
	}
	
}
