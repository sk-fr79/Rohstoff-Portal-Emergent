package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class MS_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public MS_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		MS_MASK_ComponentMAP oMS_MASK_ComponentMAP = new MS_MASK_ComponentMAP(this);
		
		this.INIT(oMS_MASK_ComponentMAP, new MS_MASK(oMS_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
