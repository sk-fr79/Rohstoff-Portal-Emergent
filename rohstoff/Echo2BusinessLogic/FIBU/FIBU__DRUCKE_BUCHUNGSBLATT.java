package rohstoff.Echo2BusinessLogic.FIBU;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class FIBU__DRUCKE_BUCHUNGSBLATT extends MyE2_Button 
{
	private FIBU__BUCHUNGS_CONTAINER oFIBU_BUCHUNGS_CONTAINER = null;
	
	
	
	public FIBU__DRUCKE_BUCHUNGSBLATT(ImageReference oImg, FIBU__BUCHUNGS_CONTAINER oFIBU_Container) 
	{
		super(oImg, true);
		this.oFIBU_BUCHUNGS_CONTAINER = oFIBU_Container;
		
		this.add_oActionAgent(new ownActionAgentDruck());
	}

	public FIBU__DRUCKE_BUCHUNGSBLATT(String cText, FIBU__BUCHUNGS_CONTAINER oFIBU_Container)
	{
		super(cText);
		this.oFIBU_BUCHUNGS_CONTAINER = oFIBU_Container;
		
		this.add_oActionAgent(new ownActionAgentDruck());
	}


	private class ownActionAgentDruck extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			FIBU__DRUCKE_BUCHUNGSBLATT.this.oFIBU_BUCHUNGS_CONTAINER.Drucke_Buchungsblatt();
		}
	}
	
	
}
