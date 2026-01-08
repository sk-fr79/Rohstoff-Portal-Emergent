package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask_old;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class SPIELWIESE_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public SPIELWIESE_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_FIBU_KONTENREGEL_NEU", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AKTIV").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTENREGEL_NEU").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTO").set_cDefaultValueFormated("");
		this.get_("ID_FILTER").set_cDefaultValueFormated("");
		this.get_("KOMMENTAR").set_cDefaultValueFormated("");
		this.get_("AKTIV").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_FIBU_KONTENREGEL_NEU").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_FIBU_KONTO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_FILTER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KOMMENTAR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
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
