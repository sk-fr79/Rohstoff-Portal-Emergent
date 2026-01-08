package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BST_K_LIST_BT_EDIT_VORGANG extends MyE2_ButtonWithKey
{

	public BST_K_LIST_BT_EDIT_VORGANG(	E2_NavigationList onavigationList, 
										BST_K_MASK__ModulContainer omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F4);
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_TPA",
								"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
								bibALL.get_Array("N","N"),
								true, new MyE2_String("Nur erlaubt bei Transportaufträgen, die NICHT abgeschlossen und NICHT geloescht sind !")));
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_TPA_LIST,"BEARBEITEN_TPA"));
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) Transportaufträg(e)").CTrans());
		
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten Transportaufträg(e)"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartEdit(new MyE2_String("Bearbeiten eines (oder mehrerer) Transportaufträg(e) ..."));
		}
	}
}
