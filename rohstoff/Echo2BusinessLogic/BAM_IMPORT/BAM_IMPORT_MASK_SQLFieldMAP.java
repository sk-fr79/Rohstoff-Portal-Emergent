package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public BAM_IMPORT_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_BAM_IMPORT", "", false);
	
//		this.get_("BELEGNUMMER").set_cDefaultValueFormated("");
//		this.get_("ID_BAM_IMPORT").set_cDefaultValueFormated("");
//		this.get_("BAM_ANGELEGT_AM").set_cDefaultValueFormated("");
//		this.get_("BAM_GESENDET_AM").set_cDefaultValueFormated("");
		
		this.initFields();
	}

}
