package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public class RB_ResultButton_action extends XX_ActionAgent {

	private MyRECORD_IF_RECORDS result_record  = 		null;
	private RB_SearchField      calling_searchField = 	null;

	
	public RB_ResultButton_action(MyRECORD_IF_RECORDS p_result_record, RB_SearchField p_calling_searchField) {
		super();
		this.result_record = p_result_record;
		this.calling_searchField = p_calling_searchField;
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//		String result_value = this.calling_searchField.get_result_string_from_record(this.result_record);
		
		
		String ret_val = this.calling_searchField.get_result_string_from_record(result_record);
		
		//show_from_inside fuehert auch die action_after_found - methode aus 
		MyE2_MessageVector mv = this.calling_searchField.rb_set_db_value_manual(ret_val,true,true);

		this.calling_searchField.get_container_4_popupWindow().CLOSE_AND_DESTROY_POPUPWINDOW(true);

		bibMSG.add_MESSAGE(mv);
	}

}
