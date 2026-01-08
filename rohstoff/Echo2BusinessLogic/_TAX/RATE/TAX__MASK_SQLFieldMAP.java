package rohstoff.Echo2BusinessLogic._TAX.RATE;


import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class TAX__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public TAX__MASK_SQLFieldMAP() throws myException 
	{
		super("JT_TAX", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("DROPDOWN_TEXT").set_cDefaultValueFormated("");
		this.get_("ID_LAND").set_cDefaultValueFormated(bibALL.get_RECORD_MANDANT().get_ID_LAND_cF());
		this.get_("ID_TAX").set_cDefaultValueFormated("");
		this.get_("INFO_TEXT_USER").set_cDefaultValueFormated("");
		this.get_("STEUERSATZ").set_cDefaultValueFormated("");
		this.get_("STEUERSATZ_NEU").set_cDefaultValueFormated("");
		this.get_("STEUERVERMERK").set_cDefaultValueFormated("");
		this.get_("TAXTYP").set_cDefaultValueFormated(TAX_CONST.TAXTYP_UNDEFINIERT);
		this.get_("WECHSELDATUM").set_cDefaultValueFormated("");

		this.get_(_DB.TAX$AKTIV).set_cDefaultValueFormated("Y");
		this.get_("HISTORISCH").set_cDefaultValueFormated("N");
		

		this.initFields();
	}

}
