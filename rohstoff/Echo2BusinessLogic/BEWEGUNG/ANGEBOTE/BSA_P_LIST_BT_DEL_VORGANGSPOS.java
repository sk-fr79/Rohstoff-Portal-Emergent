package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class BSA_P_LIST_BT_DEL_VORGANGSPOS extends MyE2_Button
{

	public BSA_P_LIST_BT_DEL_VORGANGSPOS(E2_NavigationList onavigationList,BSA_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"LOESCHEN_POSITION_IN_MASKE"));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));
		
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Positionen").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,  "JT_VPOS_STD", true);
		}

		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
}
