package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LOGTRIG__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public LOGTRIG__MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_DEF_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LOGTRIG__MASK_ComponentMAP oLOGTRIG__MASK_ComponentMAP = new LOGTRIG__MASK_ComponentMAP();
		
		this.INIT(oLOGTRIG__MASK_ComponentMAP, new LOGTRIG__MASK(oLOGTRIG__MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
