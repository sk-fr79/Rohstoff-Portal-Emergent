package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.indep.exceptions.myException;

public class AT_MaskController extends RB_MaskControllerMap {

	public AT_MaskController(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)	throws myException {
		return null;
	}

}
