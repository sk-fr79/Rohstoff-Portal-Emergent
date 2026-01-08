package panter.gmbh.Echo2.components;

import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.bibALL;


/*
 * spezielles textfeld, bei klick auf leeres feld wird tagesdatum eingestellt
 */
public class MyE2_TextField_FOR_DATE extends MyE2_TextField_WITH_Action implements MyE2IF__Component, E2_IF_Copy
{

	public MyE2_TextField_FOR_DATE()
	{
		super();
		this.add_oActionAgent(new ownActionAgent());
	}

	public MyE2_TextField_FOR_DATE(String cText, int iwidthPixel, int imaxInputSize)
	{
		super(cText, iwidthPixel, imaxInputSize);
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
