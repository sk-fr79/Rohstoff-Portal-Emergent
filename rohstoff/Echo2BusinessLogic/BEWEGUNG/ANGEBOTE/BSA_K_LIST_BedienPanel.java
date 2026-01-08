package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;

public class BSA_K_LIST_BedienPanel extends MyE2_Grid 
{
	private BSA_K_LIST__ModulContainer oModulContainer = null;

	public BSA_K_LIST_BedienPanel(BSA_K_LIST__ModulContainer modulContainer, BSA_K_MASK__ModulContainer modulContainerMASK) throws myException
	{
		super(11,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oModulContainer = modulContainer;
		
		
		MyE2_PopUpMenue oPopUpUnlock = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("unlock_popup.png"),E2_ResourceIcon.get_RI("unlock_popup__.png"), false);
		oPopUpUnlock.addButton(new BSA_K_LIST_BT_UNLOCK_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()),true);
		oPopUpUnlock.addButton(new BSA_K_LIST_BT_RESET_UNLOCK_COUNTER(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()),true);
		
		
		MyE2_PopUpMenue oPopUpDrucken = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("print_and_mail.png"),E2_ResourceIcon.get_RI("print_and_mail__.png"), false);
		oPopUpDrucken.addButton(new BSA_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Druck/Mail Angebot mit Mengen"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),false,true),true);    	
		oPopUpDrucken.addButton(new BSA_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Druck/Mail Angebot ohne Mengen"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),false,false),true);    	
		oPopUpDrucken.addButton(new BSA_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Vorschau Angebot mit Mengen"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),true,true),true);    
		oPopUpDrucken.addButton(new BSA_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Vorschau Angebot ohne Mengen"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),true,false),true);  
		
//		MyE2_PopUpMenue oPopUpMailen = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("emailwhite.png"),E2_ResourceIcon.get_RI("leer.png"), false);
//		oPopUpMailen.addButton(new BSA_K_LIST_BT_MAIL_VORGANG(modulContainer.get_oNavigationList(),modulContainer,false),true);    							// ohne mengen
//		oPopUpMailen.addButton(new BSA_K_LIST_BT_MAIL_VORGANG(modulContainer.get_oNavigationList(),modulContainer,true),true);    							// mit mengen
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oModulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		
		this.add(new BSA_K_LIST_BT_NEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSA_K_LIST_BT_COPY_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSA_K_LIST_BT_VIEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSA_K_LIST_BT_EDIT_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSA_K_LIST_BT_DELETE_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()), E2_INSETS.I_0_2_10_2);
		
		this.add(new E2_ButtonUpDown_NavigationList_to_Archiv(modulContainer.get_oNavigationList(),modulContainer.get_MODUL_IDENTIFIER()), E2_INSETS.I_0_2_10_2);
		
		this.add(oPopUpUnlock, E2_INSETS.I_0_2_10_2);
		this.add(oPopUpDrucken, E2_INSETS.I_0_2_10_2);
		
		this.add(new REP__POPUP_Button(modulContainer.get_MODUL_IDENTIFIER(),modulContainer.get_oNavigationList()));
		
		this.add(new BSA_K_LIST_DATASEARCH(this.oModulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER()), E2_INSETS.I_0_2_10_2);
	}
	
	
	
}
