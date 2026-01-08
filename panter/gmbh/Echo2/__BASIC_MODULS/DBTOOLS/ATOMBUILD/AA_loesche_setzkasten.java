/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD
 * @author manfred
 * @date 05.10.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;

/**
 * @author manfred
 * @date 05.10.2016
 *
 */
public class AA_loesche_setzkasten extends XX_ActionAgent {

	private Basic_PluginColumn _container;

	/**
	 * @author manfred
	 * @date 07.10.2016
	 *
	 */
	public AA_loesche_setzkasten(Basic_PluginColumn container) {
		_container = container; 
	}
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		BL_BEWEGUNG_HANDLER blBewegungHandler = new BL_BEWEGUNG_HANDLER();
		
		MyE2_MessageVector vm = blBewegungHandler.cleanSetzkasten();
		_container.addText(vm.get_MessagesAsText());
	}

}
