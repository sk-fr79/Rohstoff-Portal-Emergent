package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M__MaskController extends RB_MaskControllerMap {
	
	public KFIX_P_M__MaskController( RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
	}

	public KFIX_P_M__MaskController _refresh() throws myException {
		super._refresh();
		return this;
	}
	
	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}
}
