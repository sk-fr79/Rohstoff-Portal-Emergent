package rohstoff.businesslogic.bewegung2.lager_liste;


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
import rohstoff.businesslogic.bewegung.convert_from_fuhre.BG_BEWEGUNG_HANDLER;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.Bewegung_Conversion_handler;


public class B2_LALI_LIST_BT_REFRESH_FROM_FUHRE extends MyE2_Button
{

	/**
	 * 
	 */
	private E2_NavigationList oNavigationList = null;
	
	private MyE2_Column  oColLoopInfo = new MyE2_Column();

	
	public B2_LALI_LIST_BT_REFRESH_FROM_FUHRE(E2_NavigationList onavigationList)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Bewegungsdaten aktualisieren");
		this.oNavigationList = onavigationList;
		this.setToolTipText(new MyE2_String("Bewegungsdaten aus den Fuhren generieren").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BG_LALI_BT_REFRESH_FROM_FUHRE"));
	
	}
	

	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
	
			B2_LALI_LIST_BT_REFRESH_FROM_FUHRE oThis = B2_LALI_LIST_BT_REFRESH_FROM_FUHRE.this;

			Bewegung_Conversion_handler oBGConverter = new Bewegung_Conversion_handler();
			oBGConverter.runTask();
			Vector<String> vMessages =  oBGConverter.getTaskMessages();
			if (vMessages != null){
				for (String s : vMessages){
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString(s)));
				}
			}

		}

	}
	
	
}
