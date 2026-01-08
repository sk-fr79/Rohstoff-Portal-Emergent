package calledByName.triggerExecuter;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.IF_trigger_executer;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_def;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_log;
import panter.gmbh.Echo2.components.E2_BasicContainerToShowInfos;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;


public class TriggerExecuterShowMessage implements IF_trigger_executer{

	@Override
	public void execute(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException {
		
		new E2_BasicContainerToShowInfos(S.mt("Achtung! Es gibt eine Information für Sie !!"), bibVECTOR.get_Vector(trigger_def.get_execution_code()), new Extent(600), new Extent(600),false);

	}


	
	
	
}
