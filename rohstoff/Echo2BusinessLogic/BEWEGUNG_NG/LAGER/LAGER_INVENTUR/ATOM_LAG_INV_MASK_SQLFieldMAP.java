package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_INVENTUR;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_INV_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public ATOM_LAG_INV_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_LAGER_INVENTUR", "", false);
	
		/*
		 * Standardwerte setzen
		 */
		this.get_("BUCHUNGSZEIT").set_cDefaultValueFormated("23:59");
		this.get_("MENGE").set_cDefaultValueFormated("0");
		this.get_("PREIS").set_cDefaultValueFormated("0");

		
		this.get_("BUCHUNGSZEIT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PREIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.initFields();
	}

}
