package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;

public class BST_K_LIST_BedienPanel extends MyE2_Grid 
{
	private BST_K_LIST__ModulContainer oModulContainer = null;

	public BST_K_LIST_BedienPanel(BST_K_LIST__ModulContainer modulContainer, BST_K_MASK__ModulContainer modulContainerMASK) throws myException
	{
		super(11,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oModulContainer = modulContainer;
		
		MyE2_PopUpMenue oPopUpUnlock = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("unlock_popup.png"),E2_ResourceIcon.get_RI("unlock_popup__.png"), false);
		oPopUpUnlock.addButton(new BST_K_LIST_BT_UNLOCK_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_oSetting()),true);
		oPopUpUnlock.addButton(new BST_K_LIST_BT_RESET_UNLOCK_COUNTER(modulContainer.get_oNavigationList(),modulContainer.get_oSetting()),true);

		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oModulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		
		this.add(new BST_K_LIST_BT_NEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BST_K_LIST_BT_COPY_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BST_K_LIST_BT_VIEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BST_K_LIST_BT_EDIT_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BST_K_LIST_BT_DELETE_VORGANG(modulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		
		
		//TEST TEST DRUCK
		this.add(new BST_K_LIST_BT_Mail_and_Print(modulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		
		this.add(oPopUpUnlock, E2_INSETS.I_0_2_10_2);
		//this.add(oPopUpDrucken, E2_INSETS.I_0_2_10_2);
		//this.add(oPopUpMailen, E2_INSETS.I_0_2_10_2);
		this.add(new REP__POPUP_Button(modulContainer.get_MODUL_IDENTIFIER(),modulContainer.get_oNavigationList()));

		this.add(new BST_K_LIST_DATASEARCH(this.oModulContainer.get_oNavigationList(),modulContainer.get_oSetting(), modulContainer.get_MODUL_IDENTIFIER()), E2_INSETS.I_0_2_10_2);
	}
	
}
