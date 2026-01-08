package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentSingleDelete_Basic;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;

public class FSK_BT_LIST_DELETE_KONTO extends MyE2_Button
{

	public FSK_BT_LIST_DELETE_KONTO(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"LOESCHE_KONTO"));

	}
	
	private class ownActionAgent extends ButtonActionAgentSingleDelete_Basic
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Löschen eines Kontos"), onavigationList);
		}
	}
	
	
	
	
}
