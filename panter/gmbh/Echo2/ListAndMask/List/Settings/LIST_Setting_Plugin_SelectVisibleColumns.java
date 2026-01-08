package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingRowsInNavigationList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class LIST_Setting_Plugin_SelectVisibleColumns extends MyE2_Grid
{

	private Vector<MyE2_CheckBox> 	 	vCheckBoxes = 			new Vector<MyE2_CheckBox>();
	private MyE2_Button 				buttonSave = 			new MyE2_Button(new MyE2_String("OK"));
	private MyE2_Button 				buttonSaveAndStore = 	new MyE2_Button(new MyE2_String("OK-Speichern"));
	private MyE2_Button 				buttonCancel = 			new MyE2_Button(new MyE2_String("Abbruch"));
	
	private E2_NavigationList      		oNaviList = null;
	private String   					cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER = null;

	private E2_BasicModuleContainer     oContainer_this_belongs_to = null;
	
	public LIST_Setting_Plugin_SelectVisibleColumns(E2_NavigationList NaviList, E2_BasicModuleContainer Container_this_belongs_to)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oNaviList = NaviList;
		this.oContainer_this_belongs_to = Container_this_belongs_to;
		
		this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER = this.oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
		
		this.buttonCancel.add_oActionAgent(new ButtonActionAgentForCancel());
		this.buttonSave.add_oActionAgent(new ButtonActionAgentForSave());
		this.buttonSaveAndStore.add_oActionAgent(new ButtonActionAgentForSaveAndStore());
		
		this.buttonCancel.setToolTipText(new MyE2_String("Abbruch, die spalten bleiben wie sie sind").CTrans());
		this.buttonSave.setToolTipText(new MyE2_String("Spalteneinstellungen für diese Sitzung ändern").CTrans());
		this.buttonSaveAndStore.setToolTipText(new MyE2_String("Spalteneinstellungen für diese Sitzung ändern und permanent festlegen").CTrans());

		
		E2_ComponentMAP 	oMap = 					this.oNaviList.get_oComponentMAP__REF();
		Vector<String> 		vComponentHashKeys = 	oMap.get_vComponentHashKeys();

		for (int i=0;i<vComponentHashKeys.size();i++)
		{
			MyE2IF__Component oComponent = (MyE2IF__Component)oMap.get(vComponentHashKeys.get(i)) ;
			
			// alle komponenten ausser den steuerungskomponenten selektieren
			if (!(oComponent instanceof E2_ButtonListMarker || oComponent instanceof E2_CheckBoxForList))
			{
				MyE2_CheckBox oCheckBox = new MyE2_CheckBox();
				oCheckBox.setSelected(oComponent.EXT().get_bIsVisibleInList());
				oCheckBox.EXT().set_O_PLACE_FOR_EVERYTHING(oComponent);
				this.vCheckBoxes.add(oCheckBox);
			}
		}

		
		MyE2_Grid  oGrid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid.setSize(20);
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		/*
		 * nun die checkboxen mit der column-beschriftung anzeigen
		 */
		this.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie die gewünschten Spalten aus:")),1,new Insets(8,8,2,8));
		this.add(oGrid);
		
		Insets oInsets = new Insets(8,2,12,2);
		
		for (MyE2_CheckBox oCB:this.vCheckBoxes)
		{
			MyE2IF__Component oComponent = (MyE2IF__Component)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			//2018-08-02: verlaengerte Bescheibung der Spalten
			MyString s = oComponent.EXT().getLongString4ColumnSelection();
			if (S.isEmpty(s)) {
				s = oComponent.EXT().get_cList_or_Mask_Titel();
			}
			
			E2_ComponentGroupHorizontal_NG  oGroup = new E2_ComponentGroupHorizontal_NG(oCB, new MyE2_Label(s), oInsets);
			oGrid.add(oGroup,E2_INSETS.I_0_0_0_0);
		}
		// fertig

		//jetzt noch die speicher-buttons
		GridLayoutData  oLO = LayoutDataFactory.get_GridLayoutGridLeft(new Insets(0,0,20,0), null, new Alignment(Alignment.LEFT, Alignment.TOP));
		E2_ComponentGroupHorizontal_NG oButtons = new E2_ComponentGroupHorizontal_NG(
									this.buttonSave, 
									this.buttonCancel,
									S.isEmpty(this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER)?new MyE2_Label(""):this.buttonSaveAndStore,
									oLO);
		
		this.add(oButtons,1,new Insets(8,20,2,0));
		
	}

	
	/**
	 * ActionAgent zum anzeigen des selektionsfensters
	 * @author martin
	 *
	 */
	private class ButtonActionAgentForCancel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LIST_Setting_Plugin_SelectVisibleColumns.this.oContainer_this_belongs_to.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	private class ButtonActionAgentForSave extends XX_ActionAgent
	{
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			Vector<MyE2_CheckBox> vvCheckBoxes = LIST_Setting_Plugin_SelectVisibleColumns.this.vCheckBoxes;
			
			for (int i=0;i<vvCheckBoxes.size();i++)
			{
				MyE2_CheckBox oCheck = (MyE2_CheckBox)vvCheckBoxes.get(i);
				MyE2IF__Component oComp = (MyE2IF__Component)oCheck.EXT().get_O_PLACE_FOR_EVERYTHING();
				oComp.EXT().set_bIsVisibleInList(oCheck.isSelected());
			}
			
			/*
			 * neuaufbau erzwingen
			 */
			try
			{
				//2011-06-01: bei speichern der neuen anordnung wird icht nur die liste neu gebaut, sondern auch vorher neu gefuelle
				//            damit kann in einzelnen berechnungsspalten geprueft werden, ob die spalte eingeblendet ist, und wenn nein,
				//            kann sie leer gelassen werden
				LIST_Setting_Plugin_SelectVisibleColumns.this.oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();
				//  --ende 

				LIST_Setting_Plugin_SelectVisibleColumns.this.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			LIST_Setting_Plugin_SelectVisibleColumns.this.oContainer_this_belongs_to.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	
	private class ButtonActionAgentForSaveAndStore extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			Vector<MyE2_CheckBox> vvCheckBoxes = LIST_Setting_Plugin_SelectVisibleColumns.this.vCheckBoxes;
			
			for (int i=0;i<vvCheckBoxes.size();i++)
			{
				MyE2_CheckBox oCheck = (MyE2_CheckBox)vvCheckBoxes.get(i);
				MyE2IF__Component oComp = (MyE2IF__Component)oCheck.EXT().get_O_PLACE_FOR_EVERYTHING();
				oComp.EXT().set_bIsVisibleInList(oCheck.isSelected());
			}
			
			/*
			 * neuaufbau erzwingen
			 */
			try
			{
				//2011-06-01: bei speichern der neuen anordnung wird icht nur die liste neu gebaut, sondern auch vorher neu gefuelle
				//            damit kann in einzelnen berechnungsspalten geprueft werden, ob die spalte eingeblendet ist, und wenn nein,
				//            kann sie leer gelassen werden
				LIST_Setting_Plugin_SelectVisibleColumns.this.oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();
				//  --ende 
				
				LIST_Setting_Plugin_SelectVisibleColumns.this.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
				
				//und jetzt speichern
				new E2_UserSettingRowsInNavigationList().store_ColumnList(LIST_Setting_Plugin_SelectVisibleColumns.this.oNaviList);
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
			LIST_Setting_Plugin_SelectVisibleColumns.this.oContainer_this_belongs_to.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	//berechnen der breite anhande der aufgelisteten Spaltenanzahl
	public int get_iWidthOfWindow()
	{
		int iWidth=500;
		if (this.vCheckBoxes.size()>20){iWidth=500;}
		if (this.vCheckBoxes.size()>40){iWidth=800;}
		if (this.vCheckBoxes.size()>60){iWidth=950;}

		return iWidth;
	}

}
