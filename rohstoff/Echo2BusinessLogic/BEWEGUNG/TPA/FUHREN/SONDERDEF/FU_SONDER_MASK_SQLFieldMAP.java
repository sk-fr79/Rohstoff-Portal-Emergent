package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_SONDER_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public FU_SONDER_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_SONDER", "", false);
	
		this.initFields();
	}

}
