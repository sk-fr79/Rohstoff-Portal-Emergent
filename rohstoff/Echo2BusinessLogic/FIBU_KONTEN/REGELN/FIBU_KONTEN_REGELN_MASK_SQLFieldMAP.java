package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_KONTEN_REGELN_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_KONTEN_REGELN_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_FIBU_KONTENREGEL_NEU", "", false);
	
		this.get_("ID_FIBU_KONTENREGEL_NEU").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTO").set_cDefaultValueFormated("");
		
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
