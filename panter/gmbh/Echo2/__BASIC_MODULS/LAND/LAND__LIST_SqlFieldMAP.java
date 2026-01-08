package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAND__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public LAND__LIST_SqlFieldMAP() throws myException 
	{
		super("JD_LAND", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ANZEIGEREIHENFOLGE").set_cDefaultValueFormated("");
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
		this.get_("GENERELLE_EINFUHR_NOTI").set_cDefaultValueFormated("");
		this.get_("HAT_POSTCODE").set_cDefaultValueFormated("");
		this.get_("ID_LAND").set_cDefaultValueFormated("");
		this.get_("INTRASTAT_JN").set_cDefaultValueFormated("");
		this.get_("ISTSTANDARD").set_cDefaultValueFormated("");
		this.get_("LAENDERCODE").set_cDefaultValueFormated("");
		this.get_("LAENDERNAME").set_cDefaultValueFormated("");
		this.get_("LAENDERVORWAHL").set_cDefaultValueFormated("");
		this.get_("POST_CODE").set_cDefaultValueFormated("");
		this.get_("UST_PRAEFIX").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JD_LAND_WICHTIGKEIT","BESCHREIBUNG","ID_LAND__WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LAND__WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JD_LAND.ID_USER="+cID_USER+" OR JD_LAND.ID_LAND_ IN (SELECT ID_LAND_ FROM "+bibE2.cTO()+".JD_LAND_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
