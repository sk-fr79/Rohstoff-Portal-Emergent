package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__ButtonResetPrintCounter;

public class BSRG_K_LIST_BedienPanel extends MyE2_Grid 
{
	private BSRG_K_LIST__ModulContainer oModulContainer = null;

	public BSRG_K_LIST_BedienPanel(BSRG_K_LIST__ModulContainer modulContainer, BSRG_K_MASK__ModulContainer modulContainerMASK) throws myException
	{
		super(14,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oModulContainer = modulContainer;
		
		//MyE2_PopUpMenue oPopUpDrucken = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("print_and_mail.png"),E2_ResourceIcon.get_RI("print_and_mail__.png"), false);
		
//		oPopUpDrucken.addButton(new BSRG_K_LIST_BT_Mail_and_Print(
//				new MyE2_String("Druck/Mail Rechnung/Gutschrift"),modulContainer.get_oNavigationList(),null, modulContainer.get_SETTING(),modulContainer.get_MODUL_IDENTIFIER(), false),true);
//		
//		oPopUpDrucken.addButton(new BSRG_K_LIST_BT_Mail_and_Print(
//				new MyE2_String("Vorschau Rechnung/Gutschrift"),modulContainer.get_oNavigationList(),null, modulContainer.get_SETTING(),modulContainer.get_MODUL_IDENTIFIER(), true),true);    	
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oModulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		
		this.add(new BSRG_K_LIST_BT_NEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSRG_K_LIST_BT_COPY_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSRG_K_LIST_BT_VIEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSRG_K_LIST_BT_EDIT_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_10_2);
		this.add(new BSRG_K_LIST_BT_DELETE_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()), E2_INSETS.I_0_2_10_2);
		
		//2012-12-12: upload-button zu rechnungen und gutschriften
		E2_ButtonUpDown_NavigationList_to_Archiv bt_UP_DOWN = 
				new E2_ButtonUpDown_NavigationList_to_Archiv(	modulContainer.get_oNavigationList(),
																modulContainer.get_MODUL_IDENTIFIER());
		
//		//2015-03-27: fuer email-versand ein attachment-seeker-objekt uebergeben
//		//spezialaufgabe bei editieren zum versenden von originalemails sucht er alle moeglich anlagen raus
//		bt_UP_DOWN.set_es_AttachementSeeker(new ES__AttachementSeeker_RG());
		
		this.add(bt_UP_DOWN,E2_INSETS.I_0_2_10_2);
		 
		
		//2015-03-20: dafuer sorgen, dass das schliessen der popup-maske der archivdateien die RG-Navilist refreshed
		bt_UP_DOWN.get_vActionAgentWhenCloseWindow().add(new ownActionRefresh_RG_List_onCloseArchiv(null));
		
		this.add(new BSRG_K_LIST_BT_STORNO(modulContainer), E2_INSETS.I_0_2_10_2);

		if (modulContainer.get_SETTING().get_cBELEGTYP().equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			this.add(new BSRG_K_LIST_BT_SCHECK(modulContainer.get_oNavigationList()), E2_INSETS.I_0_2_10_2);
		}

		
		this.add(new BSRG_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Druck/Mail Rechnung/Gutschrift"),modulContainer.get_oNavigationList(), modulContainer.get_SETTING(),modulContainer.get_MODUL_IDENTIFIER(), true,null), E2_INSETS.I_0_2_10_2);
		
		this.add(new BSRG_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Druck/Mail Rechnung/Gutschrift"),modulContainer.get_oNavigationList(), modulContainer.get_SETTING(),modulContainer.get_MODUL_IDENTIFIER(), false,null), E2_INSETS.I_0_2_10_2);
		
		
		//geandert: 2010-12-06: Allgemeiner Befehlspopup
		MyE2_PopUpMenue oPopUp =new MyE2_PopUpMenue(null,null, false);
		
		oPopUp.addButton(new BS__ButtonResetPrintCounter(modulContainer.get_oNavigationList(), 
														"JT_VKOPF_RG", 
														new MyE2_String(modulContainer.get_SETTING().get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG)?"Rechnung":"Gutschrift")),
														true);
		
		oPopUp.addButton(new BSRG_K_LISTE_BT_Edit_RG_START_TEXT(modulContainer.get_oNavigationList()),true);
		
		this.add(oPopUp,E2_INSETS.I_0_2_10_2);
		
		this.add(new REP__POPUP_Button(modulContainer.get_MODUL_IDENTIFIER(),modulContainer.get_oNavigationList()));
		
		this.add(new BSRG_K_LIST_DATASEARCH(this.oModulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER()), E2_INSETS.I_0_2_10_2);
	}
	
	
	
	//2015-03-20: beim schliessen des popup-windows zum upload die liste aktualisieren
	private class ownActionRefresh_RG_List_onCloseArchiv extends XX_ActionAgentWhenCloseWindow {
		public ownActionRefresh_RG_List_onCloseArchiv(E2_BasicModuleContainer container)		{
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			BSRG_K_LIST_BedienPanel.this.oModulContainer.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
		}
		
	}
}
