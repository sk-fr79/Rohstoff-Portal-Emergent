package org.all.test;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;

public class Component_SandBox_Container extends Project_BasicModuleContainer {

	private MyE2_Grid containerGrid;
	
	public Component_SandBox_Container() {
		super(E2_MODULNAME_ENUM.MODUL.COMPONENT_SANDBOX_MODUL.get_callKey());
		
		containerGrid = new MyE2_Grid(3);
		containerGrid.add(new MyE2_Label("Component Name"));
		containerGrid.add(new MyE2_Label("Entwickler"));
		containerGrid.add(new MyE2_Label(E2_ResourceIcon.get_RI("empty.png")));
		
		__init();
	}

	private void __init() {
		
	}
	
	

}
