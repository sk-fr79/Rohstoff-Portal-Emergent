package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public LOGTRIG__MASK_SQLFieldMAP() throws myException 
	{
		super("JD_TRIGGER_DEF", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
//		this.get_("ERZEUGT_AM").set_cDefaultValueFormated("");
//		this.get_("ERZEUGT_VON").set_cDefaultValueFormated("");
		this.get_("ID_TRIGGER_DEF").set_cDefaultValueFormated("");
		this.get_("SPALTE").set_cDefaultValueFormated("");
		this.get_("TABELLE").set_cDefaultValueFormated("");
		this.get_("TRIGG_NAME").set_cDefaultValueFormated("");
		this.get_(RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT).set_cDefaultValueFormated("N");
		
		this.get_("BESCHREIBUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ERZEUGT_AM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ERZEUGT_VON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_TRIGGER_DEF").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("SPALTE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("TABELLE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("TRIGG_NAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
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
