package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_ModuleContainer extends RB_ModuleContainerMASK {

	public ADK_M_ModuleContainer() throws myException {
		super();
		
		ADK_M_ComponentMapCollector maskContainer = new ADK_M_ComponentMapCollector();
		
		this.registerComponent(new RB_KM("DUMMY"), maskContainer);
		
		this.rb_INIT(E2_MODULNAME_ENUM.MODUL.ADRESSKLASSE_DEF_MASK,maskContainer.get_ContainerWithElements(),true);
		
	}

}
