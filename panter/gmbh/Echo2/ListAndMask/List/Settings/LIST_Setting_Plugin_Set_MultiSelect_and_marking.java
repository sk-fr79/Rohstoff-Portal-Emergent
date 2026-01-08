package panter.gmbh.Echo2.ListAndMask.List.Settings;


import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ColorSelectPopUp;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USERSETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USERSETTINGS;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class LIST_Setting_Plugin_Set_MultiSelect_and_marking extends MyE2_Grid {

	private E2_NavigationList      			oNaviList = null;
	private HashMap<String,Component>  		hmListSettings = null;
	private ownColorSelector                oColorSelector = null;
	
	//private MyE2_Grid   		  			oGridShowNewColor = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Label  					oLabel_Beispiel_darstellung = new MyE2_Label(new MyE2_String("Beispiel für markierten Text"));
	
	private E2_BasicModuleContainer     	oContainer_this_belongs_to = null;
	private MyE2_Grid  						oGridFarbe = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	
	//falls es noch keine einstellung fuer diese liste gibt, dann die letzte moegliche einstellung laden
	private MyE2_Button   					oButtonEsWurdeNochNixGespeichert = 
		new MyE2_Button(new MyE2_String("Für dieses Modul wurde noch keine Einstellung gespeichert: Die letzte benutzte Einstellung laden"));
	
	
	
	public LIST_Setting_Plugin_Set_MultiSelect_and_marking(E2_NavigationList NaviList, E2_BasicModuleContainer Container_this_belongs_to) throws myException 
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oNaviList = NaviList;
		this.oContainer_this_belongs_to = Container_this_belongs_to;

		MyE2_Grid  oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid.setColumnWidth(0,new Extent(300));
		
		//nachsehen, ob es bereits eine speicherung gibt
		RECLIST_USERSETTINGS recUserSetting = new RECLIST_USERSETTINGS("SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE "+
													RECORD_USERSETTINGS.FIELD__HASHVALUE_SESSION+"="+bibALL.MakeSql(E2_Usersetting_ListSettings.SESSIONHASH_SAVE_LISTESETTINGS)+
													" AND "+
													RECORD_USERSETTINGS.FIELD__MODUL_KENNER+"="+bibALL.MakeSql(this.oNaviList.get_AUTOMATIC_GENERATED_KENNUNG())+
													" AND ID_USER="+bibALL.get_ID_USER());
		
		if (recUserSetting.get_vKeyValues().size()==0)
		{
			//dann nachsehen, ob der benutzer ueberhaupt schon soetwas fuer eine Liste abgespeichert hat
			recUserSetting = new RECLIST_USERSETTINGS("SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE "+
													RECORD_USERSETTINGS.FIELD__HASHVALUE_SESSION+"="+bibALL.MakeSql(E2_Usersetting_ListSettings.SESSIONHASH_SAVE_LISTESETTINGS)+
													" AND ID_USER="+bibALL.get_ID_USER()+
													" ORDER BY  "+RECORD_USERSETTINGS.FIELD__LETZTE_AENDERUNG+" DESC ");
			
			//falls mindestens ein bereits eingestelltes modul vorhanden ist, das neueste auslesen und 
			//als moegliche uebernahme kennzeichnen
			if (recUserSetting.get_vKeyValues().size()>0)
			{
				oButtonEsWurdeNochNixGespeichert.setLineWrap(true);
				oButtonEsWurdeNochNixGespeichert.add_oActionAgent(new ownActionAgentLadeSettingsVonVorigerListe(recUserSetting.get(recUserSetting.get_vKeyValues().get(0))));
				oGrid.add(oButtonEsWurdeNochNixGespeichert ,2,	new Insets(2, 10, 2, 2));
			}
		}
			
		
		//erzeugen der einstellungskomponenten mit der jeweiligen aktuellen einstellung der liste
		this.hmListSettings = this.get_hmWith_ListSetting_Components(this.oNaviList);

		
		
		oGrid.add(this.hmListSettings.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE) ,2,	new Insets(2, 10, 2, 2));
		oGrid.add(this.hmListSettings.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST) ,				2,	E2_INSETS.I_2_2_2_2);
		oGrid.add(this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS) ,				2,	new Insets(2, 2, 2, 10));
		
		


		oGrid.add(new Separator(),2,new Insets(2, 10, 2, 10));

		//farbe und hilfsmethoden
		oGridFarbe.setColumnWidth(0,new Extent(300));
		
		int[] iWidth = {150,150};
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Rot")),this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED),iWidth),1, E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new ownButtonSelectColors(),1,3,E2_INSETS.I_2_2_2_2,new Alignment(Alignment.CENTER, Alignment.CENTER));
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Grün")),this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN),iWidth), E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Blau")),this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE),iWidth), new Insets(2, 2, 2, 10));

		//oGrid.add(new E2_ComponentGroupHorizontal_NG(new ownButtonSelectColors(),this.oGridShowNewColor,iWidth),new Insets(2, 10, 2, 10));
		
		oGridFarbe.add(new Separator(),2,new Insets(2, 10, 2, 10));
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Schriftgröße")),	this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE),iWidth),2, E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Schrift fett")),	this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD),iWidth),2, E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Schrift kursiv")),	this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC),iWidth),2, E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new Separator(),2,new Insets(2, 10, 2, 10));
		oGridFarbe.add(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Schrift markierte Zeilen")),this.oLabel_Beispiel_darstellung,iWidth),2, E2_INSETS.I_2_2_2_2);
		oGridFarbe.add(new Separator(),2,new Insets(2, 10, 2, 10));
		
		//dieser block ist nur sichtbar, wenn der hervorhebungschalter selectiert ist
		oGridFarbe.setVisible(((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)).isSelected());
		
//		//falls fontsize=0, dann werden die fett/kursiv-schalter inaktiv
//		boolean SchriftSettingAktiv = !((MyE2_SelectField)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE)).get_ActualWert().equals("0");
//		((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).setEnabled(SchriftSettingAktiv);
//		((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).setEnabled(SchriftSettingAktiv);
		
		//ende farbe / hervorhebungsblock
		
		oGrid.add(this.oGridFarbe,2,E2_INSETS.I_0_0_0_0);
		oGrid.add(new ownSaveButton(),2,new Insets(2, 10, 2, 10));

		
		//jetzt noch den beispiellabel richtig faerben
		HashMap<String, String> hmSave = this.get_ActualWerte_from_popup();
		if (E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave)==null)
		{
			this.oLabel_Beispiel_darstellung.setFont(new E2_FontPlain());
		}
		else
		{
			this.oLabel_Beispiel_darstellung.setFont(E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave));
		}
		this.oLabel_Beispiel_darstellung.setForeground(E2_Usersetting_ListSettings.get_oColorFromListSetting(hmSave));

		
		
		this.add(oGrid, E2_INSETS.I_5_5_5_5);
	}
	
	
	private class ownButtonSelectColors extends MyE2_Button
	{
		public ownButtonSelectColors()
		{
			super(new MyE2_String("Farbe der Markierung auswählen"));
			this.setToolTipText(new MyE2_String("Auswahl der Text-Farbe, mit der selektierte Listenzeilen markiert werden").CTrans());
			this.setLineWrap(true);
			this.add_oActionAgent(new ownActionSelectColor());
		}
	}

	
	private class ownActionSelectColor extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			HashMap<String, String> hmSave = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.get_ActualWerte_from_popup();
			Color ocol = E2_Usersetting_ListSettings.get_oColorFromListSetting(hmSave);
			
			LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.oColorSelector=new ownColorSelector(new Action4ColorSelector(),ocol);
		}
	}
	
	
	private class ownColorSelector extends E2_ColorSelectPopUp
	{
		public ownColorSelector(XX_ActionAgent AgentActionAfterOK, Color oColor) throws myException
		{
			super(AgentActionAfterOK, oColor);
		}
	}
	

	private class Action4ColorSelector extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LIST_Setting_Plugin_Set_MultiSelect_and_marking oThis = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this;
			
			//hier die textfelder beschreiben
			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED)).setText(""+oThis.oColorSelector.get_RED());
			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN)).setText(""+oThis.oColorSelector.get_GREEN());
			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE)).setText(""+oThis.oColorSelector.get_BLUE());
			
			//oThis.oGridShowNewColor.setBackground(oThis.oColorSelector.get_oColorWasSelected());
			HashMap<String, String> hmSave = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.get_ActualWerte_from_popup();
			oThis.oLabel_Beispiel_darstellung.setFont(E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave));
			oThis.oLabel_Beispiel_darstellung.setForeground(E2_Usersetting_ListSettings.get_oColorFromListSetting(hmSave));
			
		}
	}
	
	

	
	
	private class ownSaveButton extends MyE2_Button
	{

		public ownSaveButton()
		{
			super(new MyE2_String("Speichere die Einstellungen ..."));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					HashMap<String, String> hmSave = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.get_ActualWerte_from_popup();
					new E2_Usersetting_ListSettings().STORE(LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.oNaviList.get_AUTOMATIC_GENERATED_KENNUNG(), hmSave);
					LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.writeActualWerteToNaviList();
					LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.oContainer_this_belongs_to.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Einstellungen wurden übernommen und gespeichert ...")));
					
				
				}
			});
			
		}
		
	}
	

	
	
	private HashMap<String, String> get_ActualWerte_from_popup() throws myException
	{
		HashMap<String, String> hmSave = new HashMap<String, String>();
		hmSave.put(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE, 
				((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE)).isSelected()?"Y":"N");
		hmSave.put(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS, 
				((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)).isSelected()?"Y":"N");
		hmSave.put(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST, 
				((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST)).isSelected()?"Y":"N");
		
		
		String cRED = 	((MyE2_TextField)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED)).getText();
		String cGREEN = ((MyE2_TextField)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN)).getText();
		String cBLUE = 	((MyE2_TextField)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE)).getText();
		
		if (S.isEmpty(cRED)) 	{cRED="0";}
		if (S.isEmpty(cGREEN))	{cGREEN="0";}
		if (S.isEmpty(cBLUE)) 	{cBLUE="0";}
		
		MyInteger intRED = new MyInteger(cRED);
		MyInteger intGREEN = new MyInteger(cGREEN);
		MyInteger intBLUE = new MyInteger(cBLUE);
		
		int iRed=0;
		int iGreen=0;
		int iBlue=0;
		
		if (intRED.get_oInteger()!=null) {iRed=intRED.get_iValue();} 
		if (intGREEN.get_oInteger()!=null) {iGreen=intGREEN.get_iValue();} 
		if (intBLUE.get_oInteger()!=null) {iBlue=intBLUE.get_iValue();} 
		
		if (iRed>254) {iRed=254;}
		if (iGreen>254) {iGreen=254;}
		if (iBlue>254) {iBlue=254;}
		
		hmSave.put(E2_NavigationList.NAVILIST_MARK_COLOR_RED, ""+iRed);
		hmSave.put(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN, ""+iGreen);
		hmSave.put(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE, ""+iBlue);
		
		//bereich font
		hmSave.put(E2_NavigationList.NAVILIST_MARK_FONTSIZE, ((MyE2_SelectField)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE)).get_ActualWert());
		hmSave.put(E2_NavigationList.NAVILIST_MARK_FONT_BOLD, ((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).isSelected()?"Y":"N");
		hmSave.put(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC, ((MyE2_CheckBox)this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).isSelected()?"Y":"N");
		
		
		return hmSave;
	}
	
	
	
	private void writeActualWerteToNaviList() throws myException
	{
		HashMap<String, String> hmSave = this.get_ActualWerte_from_popup();
		this.oNaviList.set_bShowInfoSelectedRowCount(hmSave.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE).equals("Y"));
		this.oNaviList.set_bMarkSelectedRows(hmSave.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS).equals("Y"));
		this.oNaviList.set_bMultiSelectWithButtonsInList(hmSave.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST).equals("Y"));

		this.oNaviList.set_oColorForeMarkSelectedRows(E2_Usersetting_ListSettings.get_oColorFromListSetting(hmSave));
		
		this.oNaviList.set_oFontForeMarkSelectedRows(E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave));
	}
	
	
	
	/**
	 * liefert komponenten fuer die allgemeinen listeneinstellungen
	 * @param oNavList
	 * @return
	 * @throws myException 
	 */
	private HashMap<String,Component> get_hmWith_ListSetting_Components(E2_NavigationList oNavList) throws myException
	{
		HashMap<String,Component> hmRueck = new HashMap<String, Component>();
		
		MyE2_CheckBox  oCBMultiSelect = 				new MyE2_CheckBox(new MyE2_String("Buttons in der Liste lassen mehrfache Selektion zu"));
		MyE2_CheckBox  oCBMarkSelected = 				new MyE2_CheckBox(new MyE2_String("Selektierte Listenzeilen werden hervorgehoben"));
		MyE2_CheckBox  oCBShowInfoWithSelectCount = 	new MyE2_CheckBox(new MyE2_String("In der Infozeile die Zahl der ausgewählten Datensätze anzeigen"));
		
		MyE2_TextField oTF_ListMarkColorRED = 		new MyE2_TextField("",100,3);
		MyE2_TextField oTF_ListMarkColorGREEN = 	new MyE2_TextField("",100,3);
		MyE2_TextField oTF_ListMarkColorBLUE =	 	new MyE2_TextField("",100,3);
		
		Color  oColorListMarking = oNavList.get_oColorForeMarkSelectedRows();
		
		oTF_ListMarkColorRED.setText(""+oColorListMarking.getRed());
		oTF_ListMarkColorGREEN.setText(""+oColorListMarking.getGreen());
		oTF_ListMarkColorBLUE.setText(""+oColorListMarking.getBlue());
		
		String[][] Groessen = {{new MyE2_String("<keine Änderung>").CTrans(),"0"},
				{"8","8"},{"9","9"},{"10","10"},{"11","11"},{"12","12"},{"13","13"},{"14","14"}};
		
		MyE2_SelectField   oSelFieldFontSize = new MyE2_SelectField(Groessen,"10",false, new Extent(100));
		MyE2_CheckBox      oCB_SetFontBold = new MyE2_CheckBox();
		MyE2_CheckBox      oCB_SetFontItalic = new MyE2_CheckBox();
		
		oSelFieldFontSize.add_oActionAgent(new ActionZeigeRichtigeBeispielanzeige());
		oCB_SetFontBold.add_oActionAgent(new ActionZeigeRichtigeBeispielanzeige());
		oCB_SetFontItalic.add_oActionAgent(new ActionZeigeRichtigeBeispielanzeige());
		
		
		Font oFontMark = oNaviList.get_oFontForeMarkSelectedRows();
		
		if (oFontMark!=null)
		{
			if (oFontMark.getSize().getValue()>=8)
			{
				oSelFieldFontSize.set_ActiveValue_OR_FirstValue(""+oFontMark.getSize().getValue());
			}
			if (oFontMark.isBold())
			{
				oCB_SetFontBold.setSelected(true);
			}
			if (oFontMark.isItalic())
			{
				oCB_SetFontItalic.setSelected(true);
			}
		}
		else
		{
			oSelFieldFontSize.set_ActiveValue_OR_FirstValue(""+0);
			oCB_SetFontBold.setSelected(false);
			oCB_SetFontItalic.setSelected(false);
			
			oCB_SetFontBold.set_bEnabled_For_Edit(false);
			oCB_SetFontItalic.set_bEnabled_For_Edit(false);
		}
		
		
		
		if (oNavList.get_bMultiSelectWithButtonsInList()) 	{oCBMultiSelect.setSelected(true);}
		if (oNavList.get_bMarkSelectedRows()) 				{oCBMarkSelected.setSelected(true);}
		if (oNavList.get_bShowInfoSelectedRowCount()) 		{oCBShowInfoWithSelectCount.setSelected(true);}
		
		hmRueck.put(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST,oCBMultiSelect);
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS,oCBMarkSelected);
		hmRueck.put(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE,oCBShowInfoWithSelectCount);
		
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_COLOR_RED,oTF_ListMarkColorRED);
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN,oTF_ListMarkColorGREEN);
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE,oTF_ListMarkColorBLUE);
		
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_FONTSIZE,oSelFieldFontSize);
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_FONT_BOLD,oCB_SetFontBold);
		hmRueck.put(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC,oCB_SetFontItalic);
		
		
		//der markierungsbutton blendet die farb- und font-optionen ein und aus
		oCBMarkSelected.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.oGridFarbe.setVisible(
						((MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource()).isSelected()
						);
				
			}
		});
		
		
		
		//falls bei der fontgroesse ausgewaehlt wird, dass nichts veraendert wird, dann werden die 
		// schalter bold und italic inaktiv und unselected
		oSelFieldFontSize.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				boolean bSchriftAenderungAktiv = !((MyE2_SelectField)LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE)).get_ActualWert().trim().equals("0");
				
				((MyE2_CheckBox)LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).set_bEnabled_For_Edit(bSchriftAenderungAktiv);
				((MyE2_CheckBox)LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).set_bEnabled_For_Edit(bSchriftAenderungAktiv);
				
				if (!bSchriftAenderungAktiv)
				{
					((MyE2_CheckBox)LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).setSelected(false);
					((MyE2_CheckBox)LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).setSelected(false);
				}
				
			}
		});
		
		
		
		return hmRueck;
	}
	
	private class ActionZeigeRichtigeBeispielanzeige extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LIST_Setting_Plugin_Set_MultiSelect_and_marking oThis = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this;
			HashMap<String, String> hmSave = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this.get_ActualWerte_from_popup();
			
			if (E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave)==null)    // dann wird die groesse und die sonstige fonteinstellung nicht beachtet
			{
				oThis.oLabel_Beispiel_darstellung.setFont(new E2_FontPlain());
			}
			else
			{
				oThis.oLabel_Beispiel_darstellung.setFont(E2_Usersetting_ListSettings.get_oFontFromListSetting(hmSave));
			}
			oThis.oLabel_Beispiel_darstellung.setForeground(E2_Usersetting_ListSettings.get_oColorFromListSetting(hmSave));
		}
	}

	
	
	/*
	 * actionagent setzt die komponenten nach eine uebergebenen RECORD_USERSETTINGS
	 */
	private class ownActionAgentLadeSettingsVonVorigerListe extends XX_ActionAgent
	{
		private RECORD_USERSETTINGS  RecUserSettings = null;

		public ownActionAgentLadeSettingsVonVorigerListe(RECORD_USERSETTINGS recUserSettings)
		{
			super();
			this.RecUserSettings = recUserSettings;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LIST_Setting_Plugin_Set_MultiSelect_and_marking oThis = LIST_Setting_Plugin_Set_MultiSelect_and_marking.this;
			
			HashMap<String,String> hmSettingsAnderesModul = (HashMap<String,String>)new E2_Usersetting_ListSettings().get_Settings(this.RecUserSettings.get_MODUL_KENNER_cUF());
			
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE)).setSelected(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_INFO_ZEILE_ZEIGT_ANZAHL_SELEKTIERTE).equals("Y"));
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)).setSelected(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS).equals("Y"));
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST)).setSelected(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MULTISELECT_IN_LIST).equals("Y"));

			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED)).setText(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED));
			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN)).setText(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN));
			((MyE2_TextField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE)).setText(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE));
			
			((MyE2_SelectField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE)).set_ActiveValue_OR_FirstValue(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE));
			
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).setSelected(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD).equals("Y"));

			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).setSelected(
					hmSettingsAnderesModul.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC).equals("Y"));
			
			//jetzt die abhaengigen einstellungen setzen
			oThis.oGridFarbe.setVisible(((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_SELECTED_ROWS)).isSelected());
			
			//falls fontsize=0, dann werden die fett/kursiv-schalter inaktiv
			boolean SchriftSettingAktiv = !((MyE2_SelectField)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE)).get_ActualWert().equals("0");
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD)).setEnabled(SchriftSettingAktiv);
			((MyE2_CheckBox)oThis.hmListSettings.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC)).setEnabled(SchriftSettingAktiv);
			
		}
		
		
	}
	
}
