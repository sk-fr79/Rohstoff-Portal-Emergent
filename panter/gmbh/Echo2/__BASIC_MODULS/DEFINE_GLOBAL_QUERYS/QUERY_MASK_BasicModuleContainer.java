package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class QUERY_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public QUERY_MASK_BasicModuleContainer() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_DEFINEQUERYS_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		QUERY_MASK_ComponentMAP oQUERY_MASK_ComponentMAP = new QUERY_MASK_ComponentMAP();
		
		this.INIT(oQUERY_MASK_ComponentMAP, new QUERY_MASK(oQUERY_MASK_ComponentMAP), new Extent(1050), new Extent(650));
		
	}
	
	
	
	
}
