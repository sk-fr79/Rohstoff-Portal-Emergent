package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;


import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;


public class ATOM_LAG_LIST_BT_REFRESH_FROM_FUHRE extends MyE2_Button
{

	/**
	 * 
	 */
	private E2_NavigationList oNavigationList = null;
	
	private MyE2_Column  oColLoopInfo = new MyE2_Column();

	
	public ATOM_LAG_LIST_BT_REFRESH_FROM_FUHRE(E2_NavigationList onavigationList)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Bewegungsdaten aktualisieren");
		this.oNavigationList = onavigationList;
		this.setToolTipText(new MyE2_String("Bewegungsdaten aus den Fuhren generieren").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ATOM_LAG_LIST_BT_REFRESH_FROM_FUHRE"));
	
	}
	

	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
	
			ATOM_LAG_LIST_BT_REFRESH_FROM_FUHRE oThis = ATOM_LAG_LIST_BT_REFRESH_FROM_FUHRE.this;

			BL_BEWEGUNG_HANDLER oAtomConverter = new BL_BEWEGUNG_HANDLER();
			oAtomConverter.runTask();
			Vector<String> vMessages =  oAtomConverter.getTaskMessages();
			if (vMessages != null){
				for (String s : vMessages){
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString(s)));
				}
			}

		}

	}
	
	
}
