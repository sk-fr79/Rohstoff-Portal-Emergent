package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ActionSetInfoButtonsInListe_Status extends XX_ActionAgent {

	private E2_NavigationList  naviList = null;
	private boolean            b_allow_jumps_in_infopopup = false;

	public BSAAL_ActionSetInfoButtonsInListe_Status(E2_NavigationList p_naviList,  boolean p_allow_jumps_in_infopopup) {
		super();
		this.naviList = p_naviList;
		this.b_allow_jumps_in_infopopup = p_allow_jumps_in_infopopup;
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
		Vector<E2_ComponentMAP> vComponentMaps = this.naviList.get_vComponentMAPS();
			
		for (E2_ComponentMAP map: vComponentMaps) {
			for (MyE2IF__Component com: map.values()) {
				if (com instanceof BSAAL_List_BUTTON_INFO) {
					((BSAAL_List_BUTTON_INFO)com).set_allow_jumps_in_infopopup(this.b_allow_jumps_in_infopopup);
				}
			}
		}
		

	}

}
