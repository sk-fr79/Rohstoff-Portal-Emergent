/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD
 * @author manfred
 * @date 07.10.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung_Batch;

/**
 * @author manfred
 * @date 07.10.2016
 *
 */
public class AA_ermittle_kosten extends XX_ActionAgent {

	/**
	 * @author manfred
	 * @date 07.10.2016
	 *
	 */
	public AA_ermittle_kosten(Basic_PluginColumn container) {
		_container = container; 
	}
	private Basic_PluginColumn _container;
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
	 */
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		BL_Kostenberechnung_Batch oKostenberechnung = new BL_Kostenberechnung_Batch();
		oKostenberechnung.runTask();
		
		Vector<String> vMessages =  oKostenberechnung.getTaskMessages();
		if (vMessages != null){
			StringBuilder sb = new StringBuilder();
			for (String s : vMessages){
				sb.append(s + System.lineSeparator());
			}
			
			_container.addText(sb.toString());
		}
	}

}
