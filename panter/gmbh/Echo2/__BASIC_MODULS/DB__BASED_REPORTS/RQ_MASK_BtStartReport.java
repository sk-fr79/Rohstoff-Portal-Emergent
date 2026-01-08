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
public class RQ_MASK_BtStartReport extends E2_Button {

	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("8f59abe8-e10c-11e8-9f32-f2801f1b9fd1");
	
	private RB_TransportHashMap hm_trans = null;
	
	public RQ_MASK_BtStartReport(RB_TransportHashMap trans) {
		super();
		
		this.hm_trans = trans;
		
		this._tr("Report starten")._s_BorderTextCentered()._fo_bold()
				._ttt(S.ms("Report ausführen und Listenfenster mit Ergebnissen öffnen"));
		
		
		this._aaa(new OwnAction());
	}

	
	private class OwnAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RB_MaskController c = new RB_MaskController(RQ_MASK_BtStartReport.this);

			if (c.isYes_LiveVal(REPORTING_QUERY.aktiv)) {
			
			if (bibMSG.MV().isOK()) {
				String id=((RB_Dataobject_V2)hm_trans.getMaskDataObjectsCollector().get(RQ_CONST.getLeadingMaskKey())).get_rec20().get_key_value();
				Rec21 r = new Rec21(_TAB.reporting_query)._fill_id(id);
				RB__EXECUTION_Container popup = new RB__EXECUTION_Container(r,false,null);
				popup.CREATE_AND_SHOW_POPUPWINDOW(S.msUt(r.getUfs(REPORTING_QUERY.titel_4_user)));
			}
			} else {
				bibMSG.MV()._addAlarm("Start nur bei aktiven Reports möglich !");
			}
		}

	}
	
}
