package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public USER__LIST_SqlFieldMAP() throws myException 
	{
		super("JD_USER", "", false);
		
		HashMap<String, String> hmZusatzfelder = new HashMap<String, String>();
		hmZusatzfelder.put("NAME_INFO", "NVL(JD_MANDANT.NAME1,'')||' '||NVL(JD_MANDANT.NAME2,'')");
		
		this.add_JOIN_Table("JD_MANDANT", "JD_MANDANT", SQLFieldMAP.LEFT_OUTER, "NAME1", true, "JD_USER.ID_MANDANT=JD_MANDANT.ID_MANDANT", "MAN_", hmZusatzfelder);
		
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AKTIV").set_cDefaultValueFormated("");
		this.get_("AUTCODE").set_cDefaultValueFormated("");
		this.get_("EIGENDEF_BREITEAENDERBAR").set_cDefaultValueFormated("");
		this.get_("EIGENDEF_MENUEBREITE").set_cDefaultValueFormated("");
		this.get_("EIGENDEF_SCHRIFTGROESSE").set_cDefaultValueFormated("");
		this.get_("EMAIL").set_cDefaultValueFormated("");
		this.get_("HAT_FAHRPLAN_BUTTON").set_cDefaultValueFormated("");
		this.get_("ID_SPRACHE").set_cDefaultValueFormated("");
		this.get_("ID_USER").set_cDefaultValueFormated("");
		this.get_("ID_USERGROUP").set_cDefaultValueFormated("");
		this.get_("IST_FAHRER").set_cDefaultValueFormated("");
		this.get_("IST_SUPERVISOR").set_cDefaultValueFormated("");
		this.get_("KUERZEL").set_cDefaultValueFormated("");
		this.get_("LAUFZEIT_SESSION").set_cDefaultValueFormated("");
		this.get_("LISTEGESAMTLAENGE").set_cDefaultValueFormated("");
		this.get_("LISTESEITELAENGE").set_cDefaultValueFormated("");
		this.get_("MAIL_SIGNATUR").set_cDefaultValueFormated("");
		this.get_("NAME").set_cDefaultValueFormated("");
		this.get_("NAME1").set_cDefaultValueFormated("");
		this.get_("NAME2").set_cDefaultValueFormated("");
		this.get_("NAME3").set_cDefaultValueFormated("");
		this.get_("PASSWORT").set_cDefaultValueFormated("");
		this.get_("TELEFAX").set_cDefaultValueFormated("");
		this.get_("TELEFON").set_cDefaultValueFormated("");
		this.get_("TEXTCOLLECTOR").set_cDefaultValueFormated("");
		this.get_("TODO_SUPERVISOR").set_cDefaultValueFormated("");
		this.get_("VORNAME").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JD_USER_WICHTIGKEIT","BESCHREIBUNG","ID_USER__WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_USER__WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JD_USER.ID_USER="+cID_USER+" OR JD_USER.ID_USER_ IN (SELECT ID_USER_ FROM "+bibE2.cTO()+".JD_USER_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		
		if (! (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() )) {
			
			this.add_BEDINGUNG_STATIC(_DB.USER+"."+_DB.USER$ID_USER+"="+bibALL.get_RECORD_USER().get_ID_USER_cUF());
		}
		
		
		this.initFields();
	}
	
}
