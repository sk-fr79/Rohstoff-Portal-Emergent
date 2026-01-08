/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS
 * @author martin
 * @date 31.10.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 31.10.2018
 *
 */
public class RQ_MASK_BtSimulateInputDialog extends E2_Button {

	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("7f2f08a0-dd13-11e8-9f8b-f2801f1b9fd1");
	
	private RB_TransportHashMap hm_trans = null;
	
	public RQ_MASK_BtSimulateInputDialog(RB_TransportHashMap trans) {
		super();
		
		this.hm_trans = trans;
		
		this._tr("Eingabe/Parameter-Dialog aufrufen")._s_BorderTextCentered()._fo_bold()
				._ttt(S.ms("Eingabe/Parameter-Dialog aufrufen und Liste Fixieren"));
		
		
		this._aaa(new OwnAction());
	}

	
	private class OwnAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			//zuerst speichern, damit der tabellenname hinterlegt ist
			((E2_Button)hm_trans.getButtonMaskSaveAndReload()).do_OnlyCode_from_OtherActionAgent(null);
			
			RB_MaskController c = new RB_MaskController(RQ_MASK_BtSimulateInputDialog.this);
			RQ_MASK_BtMakeQueryActive bt = (RQ_MASK_BtMakeQueryActive)c.get_comp(RQ_CONST.getLeadingMaskKey(), RQ_MASK_BtMakeQueryActive.KEY, bibMSG.MV());
			
			if (bibMSG.MV().isOK()) {
				String id=((RB_Dataobject_V2)hm_trans.getMaskDataObjectsCollector().get(RQ_CONST.getLeadingMaskKey())).get_rec20().get_key_value();
				Rec21 r = new Rec21(_TAB.reporting_query)._fill_id(id);
				RB__EXECUTION_Container popup = new RB__EXECUTION_Container(r,true,bt);
				popup.CREATE_AND_SHOW_POPUPWINDOW(S.msUt(r.getUfs(REPORTING_QUERY.titel_4_user)));
			}
		}

	}
	
}
