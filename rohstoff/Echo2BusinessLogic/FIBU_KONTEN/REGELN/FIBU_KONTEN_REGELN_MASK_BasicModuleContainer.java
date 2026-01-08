package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_KONTEN_REGELN_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public FIBU_KONTEN_REGELN_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MASK_FIBU_KONTEN_REGELN_NEU);
		
		this.set_bVisible_Row_For_Messages(true);
		
		FIBU_KONTEN_REGELN_MASK_ComponentMAP oFKR_MASK_ComponentMAP = new FIBU_KONTEN_REGELN_MASK_ComponentMAP();
		
		this.INIT(oFKR_MASK_ComponentMAP, new FIBU_KONTEN_REGELN_MASK(oFKR_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
