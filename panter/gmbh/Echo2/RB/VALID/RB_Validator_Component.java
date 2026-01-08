package panter.gmbh.Echo2.RB.VALID;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_Validator_Component {
	
	public abstract MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException;
	
}
