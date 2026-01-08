package rohstoff.Echo2BusinessLogic.LAGER;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LAG_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4712875328604359619L;

	public LAG_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGERMASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LAG_MASK_ComponentMAP oLAG_MASK_ComponentMAP = new LAG_MASK_ComponentMAP();
		
		this.INIT(oLAG_MASK_ComponentMAP, new LAG_MASK(oLAG_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
