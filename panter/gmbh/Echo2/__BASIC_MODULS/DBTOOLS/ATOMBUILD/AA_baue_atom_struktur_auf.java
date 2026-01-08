package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;

public class AA_baue_atom_struktur_auf extends XX_ActionAgent {

	
	/**
	 * @author manfred
	 * @date 07.10.2016
	 *
	 */
	public AA_baue_atom_struktur_auf(Basic_PluginColumn container) {
		_container = container; 
	}
	private Basic_PluginColumn _container;
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		BL_BEWEGUNG_HANDLER oAtomConverter = new BL_BEWEGUNG_HANDLER();
		
		// Kostenberechnung deaktivieren
		oAtomConverter.omitKostenCalculation(true);
		oAtomConverter.runTask();
		
		Vector<String> vMessages =  oAtomConverter.getTaskMessages();
		if (vMessages != null){
			StringBuilder sb = new StringBuilder();
			for (String s : vMessages){
				sb.append(s  + System.lineSeparator() );
			}
			_container.addText(sb.toString());;
		}

	}
	
}