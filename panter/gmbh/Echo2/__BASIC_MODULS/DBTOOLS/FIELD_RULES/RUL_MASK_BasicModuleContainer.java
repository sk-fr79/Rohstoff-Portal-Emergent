package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class RUL_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public RUL_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FIELDRULE_LIST);
		
		this.set_bVisible_Row_For_Messages(true);
		
		RUL_MASK_ComponentMAP oRUL_MASK_ComponentMAP = new RUL_MASK_ComponentMAP();
		
		this.INIT(oRUL_MASK_ComponentMAP, new RUL_MASK(oRUL_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
