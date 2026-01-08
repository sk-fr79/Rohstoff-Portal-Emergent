package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public class SearchDialog_ResultButton_action extends XX_ActionAgent {

	private String					rowiID = null;
	private SearchDialog_Base     calling_searchDialog = 	null;

	
	public SearchDialog_ResultButton_action(
			String row_id,
			SearchDialog_Base p_calling_searchField) {
		super();
		this.rowiID = row_id;
		this.calling_searchDialog = p_calling_searchField;
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		String ret_val = this.rowiID;
		this.calling_searchDialog.setResultValue(ret_val);
		
		// delegation zum äusseren Action Agent
		if (this.calling_searchDialog.getAgentAfterFound() != null){
			this.calling_searchDialog.getAgentAfterFound().executeAgentCode(oExecInfo);
		}

		this.calling_searchDialog.CLOSE_AND_DESTROY_POPUPWINDOW(true);
	}

}
