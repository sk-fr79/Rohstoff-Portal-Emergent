package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;



public class FPSE_BT_START_ZIEL extends MyE2_Button
{
	private boolean bStartNachZiel = true;
	
	public FPSE_BT_START_ZIEL()
	{
		super(E2_ResourceIcon.get_RI("fahrt_start_ziel.png"));
		this.setToolTipText(new MyE2_String("Fuhre geht von A nach B").CTrans());
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				FPSE_BT_START_ZIEL oThis = FPSE_BT_START_ZIEL.this;
				if (oThis.bStartNachZiel)
				{
					oThis.setIcon(E2_ResourceIcon.get_RI("fahrt_ziel_start.png"));
					oThis.setToolTipText(new MyE2_String("Fuhre geht von B nach A").CTrans());
					oThis.bStartNachZiel = false;
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Fuhre wird von B nach A definiert !"));
				}
				else
				{
					oThis.setIcon(E2_ResourceIcon.get_RI("fahrt_start_ziel.png"));
					oThis.setToolTipText(new MyE2_String("Fuhre geht von A nach B").CTrans());
					oThis.bStartNachZiel = true;
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Fuhre wird von A nach B definiert !"));
				}
			}
		});
		
	}

	public boolean is_bStartNachZiel()
	{
		return bStartNachZiel;
	}

	
	
}
