package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_DB_TextField_FOR_DATE extends MyE2_DB_TextField_WITH_Action  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{

	public MyE2_DB_TextField_FOR_DATE(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			TextField oTF = (TextField)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			if (bibALL.isEmpty(oTF.getText()))
				oTF.setText(bibALL.get_cDateNOW());
		}
		
	}

	
}
