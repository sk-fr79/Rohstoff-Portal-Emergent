package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class SEL_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public SEL_MASK_BasicModuleContainer(E2_BasicModuleContainer CallingModuleContainer) throws myException
	{
		super(E2_MODULNAMES.MODUL_SELEKTORDEF_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		SEL_MASK_ComponentMAP oSEL_MASK_ComponentMAP = new SEL_MASK_ComponentMAP(CallingModuleContainer);
		
		this.INIT(oSEL_MASK_ComponentMAP, new SEL_MASK(oSEL_MASK_ComponentMAP), new Extent(1000), new Extent(500));
	}
	
	
}
