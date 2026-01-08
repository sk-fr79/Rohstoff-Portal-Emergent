package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;

public class AW2_SORT_bt_OrderList extends MyE2_Button {

	private RECORD_REPORT_REITER  					recReiter=null;
	private AW2__Tab_Reiter 	callingTabGrid =null;
	
	public AW2_SORT_bt_OrderList(RECORD_REPORT_REITER  p_recReiter, AW2__Tab_Reiter p_callingTabGrid ) {
		super(E2_ResourceIcon.get_RI("sort_up_down.png"));
		this.recReiter = p_recReiter;
		
		this.callingTabGrid = p_callingTabGrid;
		
		this.add_oActionAgent(new ownActionAgent());
		
		this.setToolTipText(new MyE2_String("Sortierung der Report-Gruppe vornehmen ...").CTrans());
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AW2_RECLIST_M2N rl_m2n = new AW2_RECLIST_M2N();
			
			Vector<AW2_RECORD_REPORT>  v_reports = rl_m2n.get_v_reports_from_reiter(AW2_SORT_bt_OrderList.this.recReiter);
			
			AW2_SORT_Container  sort_popup = new AW2_SORT_Container(AW2_SORT_bt_OrderList.this.recReiter,AW2_SORT_bt_OrderList.this.callingTabGrid);
			
			for (AW2_RECORD_REPORT rr: v_reports) {
				sort_popup.add_(new AW2_SORT_object(rr));
			}
			
			sort_popup.start_sort_dialog();
		}
	}
}
