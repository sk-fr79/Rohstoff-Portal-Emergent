package rohstoff.Echo2BusinessLogic._TAX.RATE;


import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class TAX__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public TAX__LIST_SqlFieldMAP() throws myException 
	{
		super("JT_TAX", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("DROPDOWN_TEXT").set_cDefaultValueFormated("");
		this.get_("ID_LAND").set_cDefaultValueFormated("");
		this.get_("ID_TAX").set_cDefaultValueFormated("");
		this.get_("INFO_TEXT_USER").set_cDefaultValueFormated("");
		this.get_("STEUERSATZ").set_cDefaultValueFormated("");
		this.get_("STEUERSATZ_NEU").set_cDefaultValueFormated("");
		this.get_("STEUERVERMERK").set_cDefaultValueFormated("");
		this.get_("HISTORISCH").set_cDefaultValueFormated("N");
		this.get_("TAXTYP").set_cDefaultValueFormated(TAX_CONST.TAXTYP_UNDEFINIERT);
		this.get_("WECHSELDATUM").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TAX_WICHTIGKEIT","BESCHREIBUNG","ID_TAX__WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_TAX__WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_TAX.ID_USER="+cID_USER+" OR JT_TAX.ID_TAX_ IN (SELECT ID_TAX_ FROM "+bibE2.cTO()+".JT_TAX_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
