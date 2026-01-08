package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_CheckBox_WithUserSetting;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__ButtonResetPrintCounter;

public class BSK_K_LIST_BedienPanel extends MyE2_Grid 
{
	private BSK_K_LIST__ModulContainer  		oModulContainer = null;
	private MyE2_CheckBox_WithUserSetting  		oCB_SchalteArtbez2Ein = 		null;
	private MyE2_CheckBox_WithUserSetting  		oCB_SchalteRechnungsListeEin = 	null;
	private MyE2_CheckBox_WithUserSetting  		oCB_SchalteFuhrenListeEin = 	null;



	private MyE2_CheckBox_WithUserSetting  		oCB_SortierungANR1ANR2 = 	null;
	private MyE2_CheckBox_WithUserSetting  		oCB_SortierungPOS_NUMMER = 	null;
	

	public BSK_K_LIST_BedienPanel(BSK_K_LIST__ModulContainer modulContainer, BSK_K_MASK__ModulContainer modulContainerMASK) throws myException
	{
		super(12,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oModulContainer = modulContainer;

		this.oCB_SchalteArtbez2Ein = 		new MyE2_CheckBox_WithUserSetting(new MyE2_String("ArtBez2"), 
														new MyE2_String("Wenn eingeschaltet, dann wird die Artikelbezeichnung 2 in den Ausklapp-Bereichen angezeigt," +
																"ausserdem wird die Positions-Liste permanent ausgeklappt."),
														"SESSION_HASH_MERKMAL_ARTBEZ2_ANGEZEIGT",oModulContainer.get_MODUL_IDENTIFIER());
		this.oCB_SchalteRechnungsListeEin = new MyE2_CheckBox_WithUserSetting(new MyE2_String("RE/GS-Liste"), new MyE2_String("Wenn eingeschaltet, dann werden im Ausklapp-Bereich die Abrechnungspositionen sichtbar"),
														"SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_ZEIGE_RECHPOS_MIT_MENGEN",oModulContainer.get_MODUL_IDENTIFIER());
		this.oCB_SchalteFuhrenListeEin = 	new MyE2_CheckBox_WithUserSetting(new MyE2_String("Fuhren"), new MyE2_String("Wenn eingeschaltet, dann werden im Ausklapp-Bereich die Fuhren sichtbar"),
														"SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_ZEIGE_FUHREN",oModulContainer.get_MODUL_IDENTIFIER());
		
		this.oCB_SortierungANR1ANR2 = 		new MyE2_CheckBox_WithUserSetting(new MyE2_String("Sort.ANR1/2"), new MyE2_String("Sortierung der Ausklappliste nach ANR1-ANR2"),
											  			"SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_SORT_ANR1_2",oModulContainer.get_MODUL_IDENTIFIER());

		this.oCB_SortierungPOS_NUMMER = 	new MyE2_CheckBox_WithUserSetting(new MyE2_String("Sort.POS-NR"), new MyE2_String("Sortierung der Ausklappliste nach Positionsnummer"),
														"SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_POSNUMMER",oModulContainer.get_MODUL_IDENTIFIER());

		
		MyE2_PopUpMenue oPopUpUnlock = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("unlock_popup.png"),E2_ResourceIcon.get_RI("unlock_popup__.png"), false);
		oPopUpUnlock.addButton(new BSK_K_LIST_BT_UNLOCK_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()),true);
		oPopUpUnlock.addButton(new BSK_K_LIST_BT_RESET_UNLOCK_COUNTER(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()),true);

		//2010-12-20: Reset-Druckzaehler eingefuegt
		oPopUpUnlock.addButton(new BS__ButtonResetPrintCounter(
				modulContainer.get_oNavigationList(),  "JT_VKOPF_KON", 
				new MyE2_String(modulContainer.get_SETTING().get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT)?"EK-Kontrakt":"VK-Kontrakt")),
				true);	

		MyE2_PopUpMenue oPopUpDrucken = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("print_and_mail.png"),E2_ResourceIcon.get_RI("print_and_mail__.png"), false);
		oPopUpDrucken.addButton(new BSK_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Druck/Mail Kontrakt"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),false),true);    	
		oPopUpDrucken.addButton(new BSK_K_LIST_BT_Mail_and_Print(
				new MyE2_String("Vorschau Kontrakt"),modulContainer.get_oNavigationList(),modulContainer.get_SETTING(), modulContainer.get_MODUL_IDENTIFIER(),true),true);    	
		
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oModulContainer.get_oNavigationList()), E2_INSETS.I_0_2_5_2);
		
		this.add(new BSK_K_LIST_BT_NEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_5_2);
		this.add(new BSK_K_LIST_BT_COPY_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_5_2);
		this.add(new BSK_K_LIST_BT_VIEW_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_5_2);
		this.add(new BSK_K_LIST_BT_EDIT_VORGANG(modulContainer.get_oNavigationList(),modulContainerMASK), E2_INSETS.I_0_2_5_2);
		this.add(new BSK_K_LIST_BT_DELETE_VORGANG(modulContainer.get_oNavigationList(),modulContainer.get_SETTING()), E2_INSETS.I_0_2_5_2);
		
		this.add(oPopUpUnlock, E2_INSETS.I_0_2_5_2);
		this.add(oPopUpDrucken, E2_INSETS.I_0_2_5_2);
		
		this.add(new REP__POPUP_Button(modulContainer.get_MODUL_IDENTIFIER(),modulContainer.get_oNavigationList()));

		//2015-05-23: neuer archiv-upload-knopf
		//2012-12-12: upload-button zu rechnungen und gutschriften
		E2_ButtonUpDown_NavigationList_to_Archiv bt_UP_DOWN = 
				new E2_ButtonUpDown_NavigationList_to_Archiv(	modulContainer.get_oNavigationList(),
																modulContainer.get_MODUL_IDENTIFIER());							
		this.add(bt_UP_DOWN, E2_INSETS.I_0_2_5_2);
		
		
		MyE2_Grid oGridSchalter = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridSchalter.setOrientation(Grid.ORIENTATION_VERTICAL);
		//2010-12-21: zusatzschalter fuer artbez 2
		this.oCB_SchalteArtbez2Ein.setStyle(MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER());
		this.oCB_SchalteRechnungsListeEin.setStyle(MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER());
		this.oCB_SchalteFuhrenListeEin.setStyle(MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER());
		this.oCB_SortierungANR1ANR2.setStyle(MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER());
		this.oCB_SortierungPOS_NUMMER.setStyle(MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER());
		
		oGridSchalter.add(oCB_SchalteArtbez2Ein, E2_INSETS.I_0_0_2_0);
		oGridSchalter.add(oCB_SchalteRechnungsListeEin, E2_INSETS.I_0_0_2_0);
		oGridSchalter.add(oCB_SchalteFuhrenListeEin, E2_INSETS.I_0_0_2_0);
		oGridSchalter.add(oCB_SortierungANR1ANR2, E2_INSETS.I_0_0_2_0);
		oGridSchalter.add(oCB_SortierungPOS_NUMMER, E2_INSETS.I_0_0_2_0);

		//ueber action-agents sicherstellen, dass die fuhrenliste nur zusammen mit der rechnungsliste aktiv sein kann
		oCB_SchalteRechnungsListeEin.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				if (!oCB_SchalteRechnungsListeEin.isSelected())
				{
					oCB_SchalteFuhrenListeEin.setSelected(false);
				}
			}
		});
			
		oCB_SchalteFuhrenListeEin.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				if (oCB_SchalteFuhrenListeEin.isSelected())
				{
					oCB_SchalteRechnungsListeEin.setSelected(true);
				}
			}
		});
		
		
		this.add(oGridSchalter, E2_INSETS.I_0_2_5_2);
		

		this.add(new BSK_K_LIST_DATASEARCH(this.oModulContainer.get_oNavigationList(),modulContainer.get_SETTING(),modulContainer.get_MODUL_IDENTIFIER()), E2_INSETS.I_0_2_10_2);
		
		//radio-button-agent fuer sortiervarianten
		ActionAgent_RadioFunction_CheckBoxList  oRadioAction = new ActionAgent_RadioFunction_CheckBoxList(false);
		oRadioAction.add_CheckBox(this.oCB_SortierungANR1ANR2);
		oRadioAction.add_CheckBox(this.oCB_SortierungPOS_NUMMER);
		
		//einstellungs-speicher der beiden abhaengigen checkboxen eintragen
		this.oCB_SchalteRechnungsListeEin.add_oActionAgent(new ownActionSaveStatus());
		this.oCB_SchalteFuhrenListeEin.add_oActionAgent(new ownActionSaveStatus());
		
		//den schalter fuer artbez2 mit action versehen und die user-einstellungen laden
		this.oCB_SchalteArtbez2Ein.add_oActionAgent(new ownActionAgentArtBez2());

		//die agenten, die die liste behandeln am ende
		this.oCB_SchalteRechnungsListeEin.add_oActionAgent(new ownActionAgentNeubauListe());
		this.oCB_SchalteFuhrenListeEin.add_oActionAgent(new ownActionAgentFuhrenListeEin());
		
		this.oCB_SortierungANR1ANR2.add_oActionAgent(new ownActionAgentNeubauListe());
		this.oCB_SortierungPOS_NUMMER.add_oActionAgent(new ownActionAgentNeubauListe());

		this.oCB_SortierungANR1ANR2.add_oActionAgent(new ownActionSaveStatus());
		this.oCB_SortierungPOS_NUMMER.add_oActionAgent(new ownActionSaveStatus());
		
		this.oModulContainer.get_oComponentMAPList().set_bExtendSublistsAutomatic(this.oCB_SchalteArtbez2Ein.isSelected());
	}

	
	
	public MyE2_CheckBox get_oCB_SchalteArtbez2Ein() 
	{
		return oCB_SchalteArtbez2Ein;
	}

	public MyE2_CheckBox get_oCB_SchalteRechPosEin() 
	{
		return oCB_SchalteRechnungsListeEin;
	}
	
	
	public MyE2_CheckBox get_oCB_SchalteFuhrenListeEin() 
	{
		return oCB_SchalteFuhrenListeEin;
	}

	
	public MyE2_CheckBox_WithUserSetting get_oCB_SortierungANR1ANR2() 
	{
		return oCB_SortierungANR1ANR2;
	}



	public MyE2_CheckBox_WithUserSetting get_oCB_SortierungPOS_NUMMER() 
	{
		return oCB_SortierungPOS_NUMMER;
	}

	
	
	private class ownActionAgentArtBez2 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			boolean Artbez2Anzeigen = BSK_K_LIST_BedienPanel.this.oCB_SchalteArtbez2Ein.isSelected();
			
			//der schalter arbez2anzeigen sorgt gleichzeitig fuer aufgeklappte unterlisten (immer)
			BSK_K_LIST_BedienPanel.this.oModulContainer.get_oComponentMAPList().set_bExtendSublistsAutomatic(Artbez2Anzeigen);
			BSK_K_LIST_BedienPanel.this.oModulContainer.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
			
			BSK_K_LIST_BedienPanel.this.oCB_SchalteArtbez2Ein.save_SelectedStatus();
		}
	}
	
	
	private class ownActionSaveStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSK_K_LIST_BedienPanel.this.oCB_SchalteRechnungsListeEin.save_SelectedStatus();
			BSK_K_LIST_BedienPanel.this.oCB_SchalteFuhrenListeEin.save_SelectedStatus();
			
			BSK_K_LIST_BedienPanel.this.oCB_SortierungANR1ANR2.save_SelectedStatus();
			BSK_K_LIST_BedienPanel.this.oCB_SortierungPOS_NUMMER.save_SelectedStatus();
		}
	}
	
	
	private class ownActionAgentNeubauListe extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSK_K_LIST_BedienPanel.this.oModulContainer.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
		}
	}

	
	private class ownActionAgentFuhrenListeEin extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSK_K_LIST_BedienPanel.this.oModulContainer.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
		}
	}

}
