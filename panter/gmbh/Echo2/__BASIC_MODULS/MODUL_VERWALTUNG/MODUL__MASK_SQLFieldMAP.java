package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public MODUL__MASK_SQLFieldMAP() throws myException 
	{
		super("JD_SERVLETS", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
		this.get_("ID_HAUPTMENUE").set_cDefaultValueFormated("");
		this.get_("MENUEEINTRAG").set_cDefaultValueFormated("");
		this.get_("SERVLETAUFRUF").set_cDefaultValueFormated("echo2_starter:");
		this.get_("SORTNUMMER").set_cDefaultValueFormated("10");
		this.get_("TABTEXT").set_cDefaultValueFormated("");

		this.get_("ID_HAUPTMENUE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MENUEEINTRAG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("SERVLETAUFRUF").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("SORTNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
