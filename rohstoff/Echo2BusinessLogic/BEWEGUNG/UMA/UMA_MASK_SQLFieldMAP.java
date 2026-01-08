package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class UMA_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public UMA_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_UMA_KONTRAKT", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BEMERKUNGEN").set_cDefaultValueFormated("");
		this.get_("DATUM_VERTRAG").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ID_USER_BETREUER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		
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
