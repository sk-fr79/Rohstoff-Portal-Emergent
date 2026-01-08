package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public FIBU_EXPORT_PROFILES_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_FIBU_EXPORT_PROFILES_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		FIBU_EXPORT_PROFILES_MASK_ComponentMAP oFIBU_EXPORT_PROFILES_MASK_ComponentMAP = new FIBU_EXPORT_PROFILES_MASK_ComponentMAP();
		
		this.INIT(oFIBU_EXPORT_PROFILES_MASK_ComponentMAP, new FIBU_EXPORT_PROFILES_MASK(oFIBU_EXPORT_PROFILES_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
