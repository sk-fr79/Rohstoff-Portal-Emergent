package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class HADM_ComponentMapCollector extends RB_ComponentMapCollector {

	public HADM_ComponentMapCollector() throws myException {
		super();
		
		this.registerComponent(new RB_KM(_TAB.hilfetext), new HADM_ComponentMap());
	}

}
