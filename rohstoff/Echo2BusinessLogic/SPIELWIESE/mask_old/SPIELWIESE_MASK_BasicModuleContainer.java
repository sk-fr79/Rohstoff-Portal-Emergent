package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask_old;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class SPIELWIESE_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public SPIELWIESE_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.SPIELWIESE_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		//SPIELWIESE_MASK_ComponentMAP oSPIELWIESE_MASK_ComponentMAP = new SPIELWIESE_MASK_ComponentMAP();
		
		//this.INIT(oSPIELWIESE_MASK_ComponentMAP, new SPIELWIESE_MASK(oSPIELWIESE_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
