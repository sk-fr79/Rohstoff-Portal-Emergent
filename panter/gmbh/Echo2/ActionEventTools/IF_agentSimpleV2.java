/**
 * 
 */
package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * funktionales interface, um simple actions an  
 * @author martin
 * 
 */
public interface IF_agentSimpleV2 {
	
	public void doSomething(Object source) throws myException;
	
	public default XX_ActionAgent genActionAgent() {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				doSomething(oExecInfo.get_MyActionEvent().getSource());
			}
		};
	}
	
}
