package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgentOpenURL;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX__BT_Waehrungs_kurs extends E2_Button {

	
	public KFIX__BT_Waehrungs_kurs() throws myException {
		super();
		
		this._image(E2_ResourceIcon.get_RI("world.png"), E2_ResourceIcon.get_RI("world__.png"));
		this._ttt(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WEBSEITE_WAEHRUNGSKURSE.get_infoText());
		this._aaa( new ownAssistActionAgent());
	}

	private class ownAssistActionAgent extends XX_ActionAgent{


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String str_url = ENUM_MANDANT_ZUSATZ_FELDNAMEN.WEBSEITE_WAEHRUNGSKURSE.getTextVal(bibALL.get_ID_MANDANT());
			
			new ActionAgentOpenURL("", str_url, true).executeAgentCode(null);
		}
	}
}


