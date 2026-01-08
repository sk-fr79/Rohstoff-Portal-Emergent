package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class MESSAGE_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public MESSAGE_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		MESSAGE_MASK_ComponentMAP oMESSAGE_MASK_ComponentMAP = new MESSAGE_MASK_ComponentMAP();
		
		this.INIT(oMESSAGE_MASK_ComponentMAP, new MESSAGE_MASK(oMESSAGE_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
