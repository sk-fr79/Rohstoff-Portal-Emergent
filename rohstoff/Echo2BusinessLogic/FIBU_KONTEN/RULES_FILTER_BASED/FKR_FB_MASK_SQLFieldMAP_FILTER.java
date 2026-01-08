package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_MASK_SQLFieldMAP_FILTER extends Project_SQLFieldMAP 
{

	public FKR_FB_MASK_SQLFieldMAP_FILTER() throws myException 
	{
		super(_DB.FILTER, _DB.FILTER$ERZEUGT_AM+":"+_DB.FILTER$ERZEUGT_VON, false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_(_DB.FILTER$ID_FILTER).set_cDefaultValueFormated("");
		//this.get_(_DB.FILTER$BESCHREIBUNG).set_cDefaultValueFormated("");
		
		//this.get_(_DB.FILTER$BESCHREIBUNG).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
