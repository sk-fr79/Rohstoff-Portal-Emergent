package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_DATEV_PROFILE", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("DATEV_BERATERNUMMER").set_cDefaultValueFormated("");
		this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_cDefaultValueFormated("");
		this.get_("DATEV_MANDANTNUMMER").set_cDefaultValueFormated("");
		this.get_("ID_DATEV_PROFILE").set_cDefaultValueFormated("");
		this.get_("ID_DRUCKER").set_cDefaultValueFormated("");
//		this.get_("ID_USER").set_cDefaultValueFormated("");

		this.get_("DATEV_BERATERNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DATEV_GESCHAEFTSJAHRESBEGINN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DATEV_MANDANTNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_DATEV_PROFILE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_DRUCKER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(true);
		this.get_("ID_DRUCKER_PROTOKOLLE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(true);
//		this.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("EXPORT_DIR").set_cDefaultValueFormated("");
		this.get_("EXPORTS_STARTING_DATE").set_cDefaultValueFormated("");
		this.get_("EXPORTS_STARTING_ID").set_cDefaultValueFormated("");
		this.get_("FLUSH_TABLES").set_cDefaultValueFormated("");
		this.get_("PRINT_PROTOCOLS").set_cDefaultValueFormated("");
		this.get_("CORRECT_DATES").set_cDefaultValueFormated("");
		this.get_("STAMP_IMPORTED_WITH_DEBUGFLAGS").set_cDefaultValueFormated("");
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
