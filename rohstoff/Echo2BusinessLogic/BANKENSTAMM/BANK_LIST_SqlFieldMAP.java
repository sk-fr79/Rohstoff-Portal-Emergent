package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BANK_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public BANK_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_ADRESSE", "", false);
		
		
		this.add_ConnectedLookUpTable("JT_BANKENSTAMM", "BANKLEITZAHL:SWIFTCODE:IBAN_NR", "B_", "JT_BANKENSTAMM.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE");
		
		/*
		 * definition von standard-werten
		 */
		this.get_("ADRESSTYP").set_cDefaultValueFormated(""+myCONST.ADRESSTYP_BANK);
		
		
		this.add_BEDINGUNG_STATIC("JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK);

		this.initFields();
	}
	
}
