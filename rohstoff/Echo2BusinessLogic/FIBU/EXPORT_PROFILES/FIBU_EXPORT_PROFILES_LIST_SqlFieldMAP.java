package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_DATEV_PROFILE", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
//		this.get_("GEAENDERT_VON").set_cDefaultValueFormated("");

		this.get_("DATEV_BERATERNUMMER").set_cDefaultValueFormated("");
		this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_cDefaultValueFormated("");
		this.get_("DATEV_MANDANTNUMMER").set_cDefaultValueFormated("");
		this.get_("ID_DATEV_PROFILE").set_cDefaultValueFormated("");
		this.get_("ID_DRUCKER").set_cDefaultValueFormated("");
		this.get_("DESCRIPTION").set_cDefaultValueFormated("");
		
		
		// Join Table "jt_vkopf_export_rg"
		HashMap<String, String>  hmDrucker = new HashMap<String, String>();
		hmDrucker.put("DRUCKER_LESBAR", "DRUCKER.ID_DRUCKER || ' - ' || DRUCKER.NAME");
		this.add_JOIN_Table(_DB.DRUCKER, "DRUCKER", SQLFieldMAP.LEFT_OUTER, "", true, "JT_DATEV_PROFILE.ID_DRUCKER = DRUCKER.ID_DRUCKER", "DR_", hmDrucker);

		
		
		this.get_("EXPORT_DIR").set_cDefaultValueFormated("");
		this.get_("EXPORTS_STARTING_DATE").set_cDefaultValueFormated("");
		this.get_("EXPORTS_STARTING_ID").set_cDefaultValueFormated("");
		this.get_("FLUSH_TABLES").set_cDefaultValueFormated("");
		this.get_("PRINT_PROTOCOLS").set_cDefaultValueFormated("");
		this.get_("CORRECT_DATES").set_cDefaultValueFormated("");
		this.get_("STAMP_IMPORTED_WITH_DEBUGFLAGS").set_cDefaultValueFormated("");

		
		
//		this.get_("ID_USER").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_DATEV_PROFILE_WICHTIGKEIT","BESCHREIBUNG","ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_DATEV_PROFILE.ID_USER="+cID_USER+" OR JT_DATEV_PROFILE.ID_FIBU_EXPORT_PROFILES IN (SELECT ID_FIBU_EXPORT_PROFILES FROM "+bibE2.cTO()+".JT_DATEV_PROFILE_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
