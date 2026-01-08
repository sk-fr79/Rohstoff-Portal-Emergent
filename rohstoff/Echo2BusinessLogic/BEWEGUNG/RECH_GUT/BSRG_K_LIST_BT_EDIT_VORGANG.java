package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

//done

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSRG_K_LIST_BT_EDIT_VORGANG extends MyE2_ButtonWithKey
{
	private E2_NavigationList 				oNaviList = null;
	private BSRG_K_MASK__ModulContainer 	oMaskContainer = null;
	
	public BSRG_K_LIST_BT_EDIT_VORGANG(		E2_NavigationList 			onavigationList, 
											BSRG_K_MASK__ModulContainer omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F4);

		this.oNaviList  = onavigationList;
		this.oMaskContainer  = omaskContainer;
		
		
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_RG",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Nur erlaubt bei Vorggängen, die NICHT abgeschlossen und NICHT geloescht sind !")));
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"BEARBEITEN_VORGANG"));
		this.setToolTipText(new MyE2_String("Bearbeiten eines (oder mehrerer) Vorgänge").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Bearbeiten eines (oder mehrerer) Vorgänge"), onavigationList, omaskContainer, oownButton, null, null);
			this.set_cMessageStartEdit(new MyE2_String("Bearbeiten eines (oder mehrerer) Vorgänge ..."));
			
			BSRG_K_LIST_BT_EDIT_VORGANG oThis = BSRG_K_LIST_BT_EDIT_VORGANG.this;
			
			BSRG_K_LIST_BT_Mail_and_Print oPrintAusMaske = new BSRG_K_LIST_BT_Mail_and_Print(
																			new MyE2_String("Druck/Mail Rechnung/Gutschrift"), 
																			oThis.oNaviList, 
																			oThis.oMaskContainer.get_SETTING(), 
																			oThis.oMaskContainer.get_MODUL_IDENTIFIER(), 
																			true, 
																			oThis.oMaskContainer);
			
			this.get_vZusatzkomponenten().add(oPrintAusMaske);

		}
	}
}
