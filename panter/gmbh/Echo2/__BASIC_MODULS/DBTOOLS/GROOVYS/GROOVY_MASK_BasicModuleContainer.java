package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class GROOVY_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public GROOVY_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.MODUL_GROOVYDEF_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		GROOVY_MASK_ComponentMAP oSEL_MASK_ComponentMAP = new GROOVY_MASK_ComponentMAP();
		
		this.INIT(oSEL_MASK_ComponentMAP, new GROOVY_MASK(oSEL_MASK_ComponentMAP), new Extent(1000), new Extent(500));
	}
	
	
}
