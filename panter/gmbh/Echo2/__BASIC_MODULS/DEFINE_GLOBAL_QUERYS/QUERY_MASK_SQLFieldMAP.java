package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public QUERY_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_QUERY", "", false);
	
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
