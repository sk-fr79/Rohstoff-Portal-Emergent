package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAND__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public LAND__MASK_SQLFieldMAP() throws myException 
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
		
		//this.get_("ANZEIGEREIHENFOLGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		//this.get_("BESCHREIBUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		//this.get_("GENERELLE_EINFUHR_NOTI").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("HAT_POSTCODE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("INTRASTAT_JN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ISTSTANDARD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("LAENDERCODE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("LAENDERNAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		//this.get_("LAENDERVORWAHL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		//this.get_("POST_CODE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		//this.get_("UST_PRAEFIX").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/

		this.initFields();
	}

}
