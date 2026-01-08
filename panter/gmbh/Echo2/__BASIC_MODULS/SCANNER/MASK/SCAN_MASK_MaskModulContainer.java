package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	public SCAN_MASK_MaskModulContainer() throws myException {
		super();
		SCAN_MASK_ComponentMapCollector compMapCollector = new SCAN_MASK_ComponentMapCollector() ; 
		this.registerComponent(new RB_KM(_TAB.scanner_settings), compMapCollector );
		
		this.rb_INIT(MODUL.SCANNER_MASK, new SCAN_MASK_MaskGrid(compMapCollector), true);
		
	}

}
