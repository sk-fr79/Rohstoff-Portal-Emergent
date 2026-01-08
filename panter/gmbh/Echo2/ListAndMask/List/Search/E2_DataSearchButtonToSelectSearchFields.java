package panter.gmbh.Echo2.ListAndMask.List.Search;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneContent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SearchFields;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * button zum anzeigen eines auswahlfensters fuer suchfelder,
 * damit wird ein popup angezeigt, das die auswahl der aktiven felder
 * definiert 
 *
 */
public class E2_DataSearchButtonToSelectSearchFields extends MyE2_Button
{
	private MyE2_Button		buttonSave = 		new MyE2_Button("OK");
	private MyE2_Button		buttonSavePERM = 	new MyE2_Button("OK-Speichern");
	private MyE2_Button 	buttonCancel = 		new MyE2_Button("Abbruch");
	private MyE2_Button		buttonAll = 		new MyE2_Button("Alle AN");
	private MyE2_Button		buttonNone = 		new MyE2_Button("Alle AUS");

	/*
	 * uebernimmt die suchdefinitionen von MyE2_DataSearch
	 */
	private Vector<E2_SearchDefinition> 	vSearchDefinitions = null;
		
	
	private String    						cMODULE_KENNER = null;
	
	
	private E2_DataSearch    				oDataSearch = null;
	
	
	/*
	 * popup-fenster
	 */
	private E2_BasicModuleContainer 	oPopUpContainer = null;
	
	public E2_DataSearchButtonToSelectSearchFields(Vector<E2_SearchDefinition> vsearchDefintions, E2_DataSearch Datasearch, String MODULE_KENNER)
	{
		super(new MyE2_String("Wo Suchen?"));
		this.cMODULE_KENNER = MODULE_KENNER;
		this.oDataSearch = Datasearch;
		this.vSearchDefinitions = vsearchDefintions;
		this.add_oActionAgent(new ButtonActionAgentForSelectSearchFields());
		this.buttonSave.add_oActionAgent(new ButtonActionAgentForSave());
		this.buttonSavePERM.add_oActionAgent(new ButtonActionAgentForSavePerm());
		this.buttonCancel.add_oActionAgent(new ButtonActionAgentForCancel());
		this.buttonAll.add_oActionAgent(new ButtonActionAgentAll());
		this.buttonNone.add_oActionAgent(new ButtonActionAgentNone());
	}
	
	
	
	/**
	 * ActionAgent zum anzeigen des selektionsfensters
	 */
	private class ButtonActionAgentForSelectSearchFields extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_DataSearchButtonToSelectSearchFields oThis = E2_DataSearchButtonToSelectSearchFields.this;
			
			oThis.oPopUpContainer = new E2_BasicModuleContainer();

			oThis.oDataSearch.restore_old_settings();
			
			// column fuer die searchdef - checkboxen
			MyE2_Column oColumn = new MyE2_Column(new Style_Column_Normal(0,new Insets(8,10,8,10)));
			
			//alten status sichern
			for (E2_SearchDefinition oSearch:oThis.vSearchDefinitions)
			{
				oSearch.set_bOldStatusActiv(oSearch.get_oCheckIsEnabled().isSelected());
			}


			/*
			 * nun die checkboxen mit der column-beschriftung anzeigen
			 */
			for (int i=0;i<E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions.size();i++)
			{
				E2_SearchDefinition oSearchDef = (E2_SearchDefinition)E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions.get(i);
				MyE2_Row oRow = new MyE2_Row(new Style_Row_Normal(0,new Insets(0,0,0,1)));
				oRow.setBackground(new E2_ColorWindowPaneContent());
				oRow.setCellSpacing(new Extent(2));
				oRow.add(oSearchDef.get_oCheckIsEnabled());
				oRow.add(new MyE2_Label(oSearchDef.get_cTextForList()));
				oColumn.add(oRow);
			}
			
			
			
			// row fuer die buttons
			MyE2_Grid oGrid = new MyE2_Grid(3,0);
			
			oGrid.add(E2_DataSearchButtonToSelectSearchFields.this.buttonAll,new Insets(10,5,0,0));
			oGrid.add(E2_DataSearchButtonToSelectSearchFields.this.buttonNone,new Insets(10,5,0,0));
			oGrid.add(new MyE2_Label(""),new Insets(10,5,0,0));

			oGrid.add(E2_DataSearchButtonToSelectSearchFields.this.buttonSave,new Insets(10,2,0,0));
			oGrid.add(E2_DataSearchButtonToSelectSearchFields.this.buttonSavePERM,new Insets(10,2,0,0));
			oGrid.add(E2_DataSearchButtonToSelectSearchFields.this.buttonCancel,new Insets(10,2,0,0));
			
			int iHeight = MyE2_WindowPane.CalculateWindowHight(E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions.size()+6,7);
			
			oPopUpContainer.add_CloseActions(new Action4WindowClose(oPopUpContainer));
			oPopUpContainer.set_oExtSplitPosition(new Extent(iHeight-100));
			oPopUpContainer.add(oColumn);
			oPopUpContainer.set_Component_To_ButtonPane(oGrid);
			oPopUpContainer.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
			oPopUpContainer.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(
									new Extent(400), 
									new Extent(iHeight), 
									new MyE2_String("Auswahl Suchfelder"));
				
		}
	}


	/**
	 * Button-agent, speichert die check-box-werte
	 */
	private class ButtonActionAgentForSave extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<E2_SearchDefinition> vSearchdefs = E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions;
			int iCountSelected = 0;
			for (int i=0;i<vSearchdefs.size();i++)
			{
				E2_SearchDefinition oSearchDef = (E2_SearchDefinition)vSearchdefs.get(i);
				if (oSearchDef.get_oCheckIsEnabled().isSelected())
				{
					iCountSelected++;
				}
			}
			if (iCountSelected==0)
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens 1 Feld angeben!!")));
			else
			{
				E2_DataSearchButtonToSelectSearchFields.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Suchfeld-Auswahl wurde registriert !!")));
			}
		}
	}
	

	/**
	 * Button-agent, speichert die check-box-werte
	 */
	private class ButtonActionAgentForSavePerm extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			if (S.isEmpty(E2_DataSearchButtonToSelectSearchFields.this.cMODULE_KENNER))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("In diesem Modul ist Speichern der Suchfelder nicht möglich !!")));
				return;
			}
			
			Vector<E2_SearchDefinition> vSearchdefs = E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions;
			int iCountSelected = 0;
			for (int i=0;i<vSearchdefs.size();i++)
			{
				E2_SearchDefinition oSearchDef = (E2_SearchDefinition)vSearchdefs.get(i);
				if (oSearchDef.get_oCheckIsEnabled().isSelected())
				{
					iCountSelected++;
				}
			}
			if (iCountSelected==0)
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens 1 Feld angeben!!")));
			else
			{
				new E2_UserSetting_SearchFields().STORE(E2_DataSearchButtonToSelectSearchFields.this.cMODULE_KENNER, E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions);
				E2_DataSearchButtonToSelectSearchFields.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Suchfeld-Auswahl wurde registriert !!")));
			}
		}
	}

	
	
	/**
	 * Button-agent, speichert die check-box-werte
	 */
	private class ButtonActionAgentForCancel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_DataSearchButtonToSelectSearchFields.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	
	private class ButtonActionAgentAll extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Vector<E2_SearchDefinition> vSearchdefs = E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions;
			for (int i=0;i<vSearchdefs.size();i++)
			{
				E2_SearchDefinition oSearchDef = (E2_SearchDefinition)vSearchdefs.get(i);
				if (!oSearchDef.get_bOnlySingleSearch())
				{
					oSearchDef.get_oCheckIsEnabled().setSelected(true);
				}
			}
		}
	}
	
	private class ButtonActionAgentNone extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Vector<E2_SearchDefinition> vSearchdefs = E2_DataSearchButtonToSelectSearchFields.this.vSearchDefinitions;
			for (int i=0;i<vSearchdefs.size();i++)
			{
				E2_SearchDefinition oSearchDef = (E2_SearchDefinition)vSearchdefs.get(i);
				oSearchDef.get_oCheckIsEnabled().setSelected(false);
			}
		}
	}
	
	
	private class Action4WindowClose extends XX_ActionAgentWhenCloseWindow
	{

		public Action4WindowClose(E2_BasicModuleContainer container) 
		{
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			if (this.get_oContainer().get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL))
			{
				E2_DataSearchButtonToSelectSearchFields oThis = E2_DataSearchButtonToSelectSearchFields.this;
				//alten status wiederherstellen
				for (E2_SearchDefinition oSearch:oThis.vSearchDefinitions)
				{
					//alten status si
					oSearch.get_oCheckIsEnabled().setSelected(oSearch.get_bOldStatusActiv());
				}
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Suchfeld-Auswahl wurde nicht verändert !"));

			}
		}
		
	}
	
}
