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
public interface IF_agentSimple {
	
	public void doSomething() throws myException;
	
	public default XX_ActionAgent genActionAgent() {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				doSomething();
			}
		};
	}
	
}
