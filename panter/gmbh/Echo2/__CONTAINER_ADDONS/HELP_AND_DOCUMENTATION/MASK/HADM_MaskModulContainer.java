package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class HADM_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	public HADM_MaskModulContainer() throws myException {
		super();
		HADM_ComponentMapCollector compMapCollector = new HADM_ComponentMapCollector() ; 
		this.registerComponent(new RB_KM(_TAB.hilfetext), compMapCollector );
		
		this.rb_INIT(MODUL.HILFETEXT_MASKE, new HADM_MaskGrid(compMapCollector), true);
		
	}

}
