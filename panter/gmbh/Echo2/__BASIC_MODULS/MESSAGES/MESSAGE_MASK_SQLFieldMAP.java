package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public MESSAGE_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_NACHRICHT", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BESTAETIGT").set_cDefaultValueFormated("");
		this.get_("ID_NACHRICHT").set_cDefaultValueFormated("");
		this.get_("ID_NACHRICHT_PARENT").set_cDefaultValueFormated("");
		this.get_("ID_USER").set_cDefaultValueFormated("");
		this.get_("ID_USER_SENDER").set_cDefaultValueFormated("");
		this.get_("NACHRICHT").set_cDefaultValueFormated("");
		this.get_("TITEL").set_cDefaultValueFormated("");
		this.get_("BESTAETIGT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_NACHRICHT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_NACHRICHT_PARENT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_USER_SENDER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NACHRICHT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("TITEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
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
