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
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_K_LIST_BT_DELETE_VORGANG extends MyE2_Button
{

	public BSA_K_LIST_BT_DELETE_VORGANG(E2_NavigationList onavigationList,BS__SETTING SETTING)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(SETTING.get_cMODULCONTAINER_LIST_IDENTIFIER(),"LOESCHEN_ANGEBOT"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_STD",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Nur erlaubt bei Angeboten, die NICHT abgeschlossen und NICHT geloescht sind !")));

		
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Angebote").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,  "JT_VKOPF_STD", false);
		}

		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
}
