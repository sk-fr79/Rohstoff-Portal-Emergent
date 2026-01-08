package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK;

public class BST_P_LIST_BT_VIEW_VORGANGSPOS extends MyE2_Button
{

	public BST_P_LIST_BT_VIEW_VORGANGSPOS(E2_NavigationList onavigationList,BST_P_MASK__ModulContainer oPositionModulContainerMASK, BST_K_MASK__ModulContainer oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("view.png") , true);
		
		Vector<Component> vZusatzKomponent =new Vector<Component>();
		vZusatzKomponent.add(new ___BUTTON_LIST_BT_Mail_and_Print(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK(oPositionModulContainerMASK,true),"TRANSPORTAUFTRAGSMODUL"));
		
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,oPositionModulContainerMASK,this,vZusatzKomponent));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"ANZEIGE_TPA_POS"));
		this.setToolTipText(new MyE2_String("Maskenansicht eines (oder mehrerer) Positionen").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentVIEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, BST_P_MASK__ModulContainer omaskContainer, MyE2_Button oownButton,Vector<Component> vZusatzKomponent)
		{
			super(new MyE2_String("Anzeige einer TPA-Position"), onavigationList, omaskContainer, oownButton, vZusatzKomponent);
			this.set_cMessageStartEdit(new MyE2_String("Anzeige einer TPA-Position ..."));
		}
	}
	
}
