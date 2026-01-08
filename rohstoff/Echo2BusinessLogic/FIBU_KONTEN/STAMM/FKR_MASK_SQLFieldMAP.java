package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FKR_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public FKR_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_FIBU_KONTENREGEL", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("DEF_EK_VK").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_GRUPPE_FIBU").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTENREGEL").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTO").set_cDefaultValueFormated("");
		this.get_("ID_TAX").set_cDefaultValueFormated("");
		this.get_("DIENSTLEISTUNG").set_cDefaultValueFormated("");
		this.get_("PRIVAT").set_cDefaultValueFormated("");
//		this.get_("DEF_EK_VK").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ADRESSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ARTIKEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ARTIKEL_GRUPPE_FIBU").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_FIBU_KONTENREGEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_FIBU_KONTO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_TAX").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(true);
		
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
