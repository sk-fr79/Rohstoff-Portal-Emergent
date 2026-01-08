package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public MANDANT_MASK_SQLFieldMAP() throws myException 
	{
		super("JD_MANDANT", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ANREDE").set_cDefaultValueFormated("");
		this.get_("COLOR_DIFF").set_cDefaultValueFormated("10");

		this.get_("COLOR_BLUE").set_cDefaultValueFormated("200");
		this.get_("COLOR_GREEN").set_cDefaultValueFormated("200");
		this.get_("COLOR_RED").set_cDefaultValueFormated("200");

		this.get_("COLOR_POPUP_TITEL_BLUE").set_cDefaultValueFormated("200");
		this.get_("COLOR_POPUP_TITEL_GREEN").set_cDefaultValueFormated("200");
		this.get_("COLOR_POPUP_TITEL_RED").set_cDefaultValueFormated("200");

		this.get_("COLOR_MASK_HIGHLIGHT_BLUE").set_cDefaultValueFormated("200");
		this.get_("COLOR_MASK_HIGHLIGHT_GREEN").set_cDefaultValueFormated("200");
		this.get_("COLOR_MASK_HIGHLIGHT_RED").set_cDefaultValueFormated("200");

		this.get_("EIGENE_ADRESS_ID").set_cDefaultValueFormated("");
		
		
		this.get_("COLOR_DIFF").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("COLOR_BLUE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_GREEN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_RED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("COLOR_POPUP_TITEL_BLUE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_POPUP_TITEL_GREEN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_POPUP_TITEL_RED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("COLOR_MASK_HIGHLIGHT_BLUE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_MASK_HIGHLIGHT_GREEN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("COLOR_MASK_HIGHLIGHT_RED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_(MANDANT.color_backtext_red).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(MANDANT.color_backtext_green).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(MANDANT.color_backtext_blue).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("EIGENE_ADRESS_ID").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("MAILACCOUNT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MAILPASSWORD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MAILSERVER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MAILUSERNAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		this.initFields();
	}

}
