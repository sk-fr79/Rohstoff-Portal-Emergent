package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class BAM_IMPORT_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public BAM_IMPORT_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.BAM_IMPORT_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		BAM_IMPORT_MASK_ComponentMAP oBAM_IMPORT_MASK_ComponentMAP = new BAM_IMPORT_MASK_ComponentMAP(this);
		
		this.INIT(oBAM_IMPORT_MASK_ComponentMAP, new BAM_IMPORT_MASK(oBAM_IMPORT_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
