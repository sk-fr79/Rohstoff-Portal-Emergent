package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;

public class SCAN_POPUP_MultiScanner extends MyE2_PopUpMenue {

	public SCAN_POPUP_MultiScanner() {
		super(E2_ResourceIcon.get_RI("scanner.png"),E2_ResourceIcon.get_RI("scanner__.png"),false);
	}

	public SCAN_POPUP_MultiScanner(boolean bMini) {
		super(bMini?E2_ResourceIcon.get_RI("scanner_mini.png"):E2_ResourceIcon.get_RI("scanner.png"),
			  bMini?E2_ResourceIcon.get_RI("scanner_mini__.png"):E2_ResourceIcon.get_RI("scanner__.png"),false);
	}
	
}
