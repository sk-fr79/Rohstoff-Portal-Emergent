package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MMD_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MMD_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_MAIL_AUS_MASK", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BASISTABELLE").set_cDefaultValueFormated("");
		this.get_("BETREFF").set_cDefaultValueFormated("");
		this.get_("ERLAUBE_FREIEMAILADRESSE").set_cDefaultValueFormated("");
		this.get_("GROOVY_BEDINGUNG").set_cDefaultValueFormated("");
		this.get_("ID_MAIL_AUS_MASK").set_cDefaultValueFormated("");
		this.get_("MAILTEXT").set_cDefaultValueFormated("");
		this.get_("MODULKENNER").set_cDefaultValueFormated("");
		this.get_("SIGNATUR_ANHAENGEN").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_MAIL_AUS_MASK_WICHTIGKEIT","BESCHREIBUNG","ID_MMD_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_MMD_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_MAIL_AUS_MASK.ID_USER="+cID_USER+" OR JT_MAIL_AUS_MASK.ID_MMD IN (SELECT ID_MMD FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
