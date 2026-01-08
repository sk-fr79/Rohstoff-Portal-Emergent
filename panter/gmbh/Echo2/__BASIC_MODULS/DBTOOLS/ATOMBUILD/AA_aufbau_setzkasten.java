package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler_KALKULATORISCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG.ATOM_LAG_BEW_COL_StatusLager;

public class AA_aufbau_setzkasten extends XX_ActionAgent {

	private Basic_PluginColumn _container;

	/**
	 * @author manfred
	 * @date 07.10.2016
	 *
	 */
	public AA_aufbau_setzkasten(Basic_PluginColumn container) {
		_container = container; 
	}
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		ATOM_SetzkastenHandler_KALKULATORISCH	oHandlerKalk 	= new ATOM_SetzkastenHandler_KALKULATORISCH(null);
		ATOM_SetzkastenHandler					oHandler 		= new ATOM_SetzkastenHandler(null);

		
		_container.addText("Starte Setzkasten - normal");
		oHandler.ReorganiseLagerEntries(false);
		
		_container.addText("Starte Setzkasten - kalkulatorisch");
		oHandlerKalk.ReorganiseLagerEntries(false);
		
		
	}
	
}