package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class BSRG_K_LIST_BT_VIEW_VORGANG extends MyE2_ButtonWithKey
{
	private E2_NavigationList 				oNaviList = null;
	private BSRG_K_MASK__ModulContainer 	oMaskContainer = null;

	public BSRG_K_LIST_BT_VIEW_VORGANG(E2_NavigationList onavigationList, BSRG_K_MASK__ModulContainer omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("view.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_F4);
		
		this.oNaviList  = onavigationList;
		this.oMaskContainer  = omaskContainer;

		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"ANZEIGE_BELEG"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Vorgänge").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BSRG_K_MASK__ModulContainer omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Anzeige eines Vorgangs"), onavigationList, omaskContainer, oownButton, null);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige eines Vorgangs ..."));
			
			
			BSRG_K_LIST_BT_VIEW_VORGANG oThis = BSRG_K_LIST_BT_VIEW_VORGANG.this;
			
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
