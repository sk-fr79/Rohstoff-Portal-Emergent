package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	public SCAN_MASK_ComponentMapCollector() throws myException {
		super();
		
		this.registerComponent(new RB_KM(_TAB.scanner_settings), new SCAN_MASK_ComponentMap());
	}

}
