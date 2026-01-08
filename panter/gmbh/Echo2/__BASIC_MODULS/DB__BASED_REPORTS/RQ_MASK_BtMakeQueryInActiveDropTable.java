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
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 31.10.2018
 *
 */
public class RQ_MASK_BtMakeQueryInActiveDropTable extends E2_Button {

	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("94b542ec-e1ba-11e8-9f32-f2801f1b9fd1");
	
	private RB_TransportHashMap hm_trans = null;
	
	public RQ_MASK_BtMakeQueryInActiveDropTable(RB_TransportHashMap trans) {
		super();
		
		this.hm_trans = trans;
		
		this._tr("Tabelle löschen, Abfrage wieder öffnen")._s_BorderTextCentered()._fo_bold()
				._ttt(S.ms("Abfrage deaktivieren und Maske entsperren"));
		
		
		this._aaa(new OwnAction());
	}

	
	private class OwnAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			//zuerst speichern, damit der tabellenname hinterlegt ist
			RB_MaskController c = new RB_MaskController(RQ_MASK_BtMakeQueryInActiveDropTable.this);
			
			Rec21 r = hm_trans.getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey()).getRbDataObjectActual().rec21();
				
			bibMSG.MV()._add(new RQ__serviceBuildQuery().dropTableIfExisting(r));
			if (bibMSG.get_bIsOK()) {
				c.set_maskVal(REPORTING_QUERY.aktiv, "N", bibMSG.MV());
				if (bibMSG.MV().isOK()) {
					((E2_Button)hm_trans.getButtonMaskSaveAndReload()).do_OnlyCode_from_OtherActionAgent(null);
				}
			}
		}

	}
	
}
