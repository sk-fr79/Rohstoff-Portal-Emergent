package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;


/**
 * selectfield, um den detailstatus der listenansicht waehlen zu koennen
 * @author martin
 *
 */
public class FZ_NavigationList_SelectFieldDetailStatus extends MyE2_SelectField {

	private FZ_NavigationList naviList = null;
	
	public FZ_NavigationList_SelectFieldDetailStatus( FZ_NavigationList p_naviList) throws myException {
		super();
		this.naviList = p_naviList;
		this.set_oDataToView(new dataToView(FZ__CONST.DETAIL_SWITCH.get_array4SelectField(), false, bibE2.get_CurrSession()));

		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_NavigationList_SelectFieldDetailStatus.this.naviList._REBUILD_ACTUAL_SITE("");
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Neuaufbau der Liste in der neuen Ansicht komplett")));
		}
		
	}
	
}
