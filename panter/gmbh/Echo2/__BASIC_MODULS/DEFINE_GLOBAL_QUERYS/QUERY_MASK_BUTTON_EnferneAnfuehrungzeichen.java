package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_BUTTON_EnferneAnfuehrungzeichen extends MyE2_Button 
{

	public QUERY_MASK_BUTTON_EnferneAnfuehrungzeichen() 
	{
		super(new MyE2_String("Entferne Anführungszeichen"));
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_RecursiveSearch_MaskInfo oMaskSearch = new E2_RecursiveSearch_MaskInfo(bibE2.get_LAST_ACTIONEVENT());
			
			MyE2_TextArea oFieldSQLFields = (MyE2_TextArea)oMaskSearch.get_Component("SQLFELDLISTE");
			MyE2_TextArea oFieldSQLFrom   = (MyE2_TextArea)oMaskSearch.get_Component("SQLFROMBLOCK");
			MyE2_TextArea oFieldSQLWhere = (MyE2_TextArea)oMaskSearch.get_Component("SQLWHEREBLOCK");
			MyE2_TextArea oFieldSQLOrder = (MyE2_TextArea)oMaskSearch.get_Component("SQLORDERBLOCK");
			
			oFieldSQLFields.setText(bibALL.ReplaceTeilString(bibALL.null2leer(oFieldSQLFields.getText()), "\"", ""));
			oFieldSQLFrom.setText(bibALL.ReplaceTeilString(bibALL.null2leer(oFieldSQLFrom.getText()), "\"", ""));
			oFieldSQLWhere.setText(bibALL.ReplaceTeilString(bibALL.null2leer(oFieldSQLWhere.getText()), "\"", ""));
			oFieldSQLOrder.setText(bibALL.ReplaceTeilString(bibALL.null2leer(oFieldSQLOrder.getText()), "\"", ""));
		}
		
	}
	
	
	
}
