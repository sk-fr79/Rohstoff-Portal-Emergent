package panter.gmbh.Echo2.Container;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingTabReihenfolge;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_HelpButton;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TabbedPane.Button_TabComponent_ActionAgent;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_BasicModuleContainer_Button_To_Change_TabPaneOrder extends MyE2_Button
{
	private E2_BasicModuleContainer oMotherContainer = null;
	private MyE2_TabbedPane  		oTabbedPaneToSort = null;

	private MyE2_Grid				oGridToShowPositions = new MyE2_Grid(3, 1);
	
	private String   				cLastKlicked = null;   //dunkler hintergrund beim neuaufbau
	
	private Vector<String> 			vSortVector = new Vector<String>();
	
	
	public E2_BasicModuleContainer_Button_To_Change_TabPaneOrder(E2_BasicModuleContainer oMother)
	{
		
		//super(E2_ResourceIcon.get_RI("tabpanesetup.png"));
		super(new MyE2_String("Tabreihenfolge festlegen"));
		this.oMotherContainer = oMother;
		this.setToolTipText(new MyE2_String("Im der Maske koennen die Tab-Reiter verschoben / ausgeblendet werden ").CTrans());
		this.add_oActionAgent(new ownActionAgentShowSortPopup());
	}
	
	
	private void baue_Grid()
	{
		this.oGridToShowPositions.removeAll();
		
		HashMap<String,Button_TabComponent_ActionAgent> hmTabs = this.oTabbedPaneToSort.get_hmAgentsAndButtons();
		
		for (int i=0;i<E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this.vSortVector.size();i++)
		{
			ButtonMoveUp  oButton =	new ButtonMoveUp(hmTabs.get(this.oTabbedPaneToSort.get_SortVector().get(i)).oString,
													  this.oTabbedPaneToSort.get_SortVector().get(i),
													  this.oTabbedPaneToSort.get_SortVector());
			oGridToShowPositions.add(oButton);
			
			if (this.cLastKlicked != null && this.cLastKlicked.equals(this.oTabbedPaneToSort.get_SortVector().get(i)))
			{
				oButton.setFont(new E2_FontBold(2));
			}
			oGridToShowPositions.add(new ButtonMoveUp(this.oTabbedPaneToSort.get_SortVector().get(i),this.oTabbedPaneToSort.get_SortVector()));
			oGridToShowPositions.add(new ButtonMoveDown(this.oTabbedPaneToSort.get_SortVector().get(i),this.oTabbedPaneToSort.get_SortVector()));
		}
	
	}
	
	
	private class ownActionAgentShowSortPopup  extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainer_Button_To_Change_TabPaneOrder oThis = E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this; 

			if (oThis.oMotherContainer != null)
			{
				E2_RecursiveSearch_Component oSearchComp = new E2_RecursiveSearch_Component(
						oThis.oMotherContainer,
						bibALL.get_Vector(MyE2_TabbedPane.class.getName()),
						null);

				
				if (oSearchComp.get_vAllComponents().size()==1)
				{
					oThis.oTabbedPaneToSort = 	(MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0);
					oThis.vSortVector = 		((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).get_SortVector();
					
					ownBasicContainer oPopUpSort = new ownBasicContainer();
					oThis.baue_Grid();
					
					Vector<String> vInfos = bibALL.get_Vector("Anklicken der Buttons", "der einzelnen Karteireiter", "bewegt diese um eine Position nach oben.", "Damit kann die Reihenfolge der Tabs", "frei definiert werden.", null, null, null);
					
					oPopUpSort.add(new  E2_ComponentGroupHorizontal(0,new E2_HelpButton(new MyE2_String("Bedienhilfe"),vInfos,true),null), E2_INSETS.I_10_10_10_10);
					oPopUpSort.add(oGridToShowPositions, E2_INSETS.I_10_10_10_10);
					oPopUpSort.add(new  E2_ComponentGroupHorizontal(0,new ButtonSaveNewOrder((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0),oPopUpSort),null), E2_INSETS.I_10_10_10_10);
					
					oPopUpSort.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(500), new MyE2_String("Tabs einstellen"));
				
				}
			}
		}
	}

	
	
	private class ButtonMoveUp extends MyE2_Button
	{
		public String 				cHashKey = null;
		private Vector<String>		vHashKeys = null;

		
		public ButtonMoveUp(String hashKey, Vector<String> hashKeys)
		{
			super(E2_ResourceIcon.get_RI("sortup.png"),E2_ResourceIcon.get_RI("sortup.png"));
			cHashKey = hashKey;
			vHashKeys = hashKeys;
			this.add_oActionAgent(new ownActionAgent());
		}

		public ButtonMoveUp(MyString ButtonText, String hashKey, Vector<String> hashKeys)
		{
			super(ButtonText);
			cHashKey = hashKey;
			vHashKeys = hashKeys;
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
			this.add_oActionAgent(new ownActionAgent());
		}


		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ButtonMoveUp oThis = ButtonMoveUp.this;
				
				
				
				for (int i=0;i<oThis.vHashKeys.size();i++)
				{
					if (oThis.vHashKeys.get(i).equals(oThis.cHashKey))
					{
						String cRaus = oThis.vHashKeys.remove(i);
						int iPosToInsert = i==0?0:i-1;
						oThis.vHashKeys.add(iPosToInsert,cRaus);
						break;
					}
				}
				
				E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this.cLastKlicked=oThis.cHashKey;
				E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this.baue_Grid();
			}
		}
		
	}
	

	
	private class ButtonMoveDown extends MyE2_Button
	{
		public String 				cHashKey = null;
		private Vector<String>		vHashKeys = null;

		
		public ButtonMoveDown(String hashKey, Vector<String> hashKeys)
		{
			super(E2_ResourceIcon.get_RI("sortdown.png"),E2_ResourceIcon.get_RI("sortdown.png"));
			cHashKey = hashKey;
			vHashKeys = hashKeys;
			this.add_oActionAgent(new ownActionAgent());
		}


		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ButtonMoveDown oThis = ButtonMoveDown.this;
				
				for (int i=oThis.vHashKeys.size()-1;i>=0;i--)
				{
					if (oThis.vHashKeys.get(i).equals(oThis.cHashKey))
					{
						int iSize = oThis.vHashKeys.size();
						String cRaus = oThis.vHashKeys.remove(i);
						int iPosToInsert = i==iSize-1?iSize-1:i+1;
						oThis.vHashKeys.add(iPosToInsert,cRaus);
						break;
					}
				}
				
				E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this.cLastKlicked=oThis.cHashKey;
				E2_BasicModuleContainer_Button_To_Change_TabPaneOrder.this.baue_Grid();
			}
		}
		
	}

	
	
	
	
	private class ButtonSaveNewOrder extends MyE2_Button
	{
		private MyE2_TabbedPane 			oTabbedPane = null;
		private E2_BasicModuleContainer 	oPopUpContainer = null;
		
		public ButtonSaveNewOrder(MyE2_TabbedPane TabbedPane, E2_BasicModuleContainer PopUpContainer)
		{
			super(new MyE2_String("Speichern"));
			this.oTabbedPane = TabbedPane;
			this.oPopUpContainer = PopUpContainer;
			
			this.add_oActionAgent(new actionSave());
		}

		
		private class actionSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ButtonSaveNewOrder oThis = ButtonSaveNewOrder.this;
				
				new E2_UserSettingTabReihenfolge().storeTabbedPane(oThis.oTabbedPane);
				new E2_UserSettingTabReihenfolge().prepareTabbedPane(oThis.oTabbedPane);
				
				ButtonSaveNewOrder.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
		
	}
	
	
	/*
	 * damit die groesse abgespeichert werden kann
	 */
	private class ownBasicContainer extends E2_BasicModuleContainer
	{
		public ownBasicContainer()
		{
			super();
		}
	}



}
