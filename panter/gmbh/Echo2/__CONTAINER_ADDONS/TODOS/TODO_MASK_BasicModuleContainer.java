package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class TODO_MASK_BasicModuleContainer extends E2_BasicModuleContainer_MASK 
{
	public static String HASHVALUE_TODO_TEILNEHMER = "HASHVALUE_TODO_TEILNEHMER";
	

	public TODO_MASK_BasicModuleContainer() throws myException
	{
		super();
		this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_MASK);
		
		TODO_MASK_ComponentMAP oTODO_MASK_ComponentMAP = new TODO_MASK_ComponentMAP();
		
		this.INIT(oTODO_MASK_ComponentMAP, new TODO_MASK(oTODO_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
	}
	
	
	
	
}
