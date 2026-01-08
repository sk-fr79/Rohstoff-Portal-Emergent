package rohstoff.Echo2BusinessLogic.INTRASTAT;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class INSTAT_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public INSTAT_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_INTRASTAT_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		INSTAT_MASK_ComponentMAP oINSTAT_MASK_ComponentMAP = new INSTAT_MASK_ComponentMAP();
		
		this.INIT(oINSTAT_MASK_ComponentMAP, new INSTAT_MASK(oINSTAT_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
