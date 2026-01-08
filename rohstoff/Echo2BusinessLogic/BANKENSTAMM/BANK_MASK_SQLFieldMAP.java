package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BANK_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public BANK_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_ADRESSE", "", false);
	
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/
		this.get_("ADRESSTYP").set_cDefaultValueFormated(""+myCONST.ADRESSTYP_BANK);
		this.initFields();
	}

}
