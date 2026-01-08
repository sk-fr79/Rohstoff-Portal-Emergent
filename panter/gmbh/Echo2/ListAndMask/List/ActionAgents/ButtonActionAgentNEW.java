package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF__BasicModuleContainer_PopUp_BeforeExecute_Container;
import panter.gmbh.Echo2.ActionEventTools.V_Single_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class ButtonActionAgentNEW extends XX_ButtonActionAgent_FromListToMask
{
	private MyString cMessageStartingNew = 		new MyE2_String("Erfassen eines NEUEN Datensatzes ...");
	private MyString cMessageNewRowIsSaved = 	new MyE2_String("Datensatz hinzugefügt: ");
	private MyString cMessageEditRowIsSaved = 	new MyE2_String("Datensatz gespeichert: ");
	
	
	
	/*
	 * hier eine moeglichkeit vorsehen, einen popup vor der eigentlichen speicherung einzufuegen
	 */
	private E2_BasicModuleContainer_PopUp_BeforeExecute  oPopupBeforExecute = null;

	
	
	/*
	 * die 2 buttons zum speichern und zum abbruch werden veroeffentlicht, damit evtl weitere ActionAgents uebergeben werden koennen
	 */
	private MyE2_Button 		 oButtonMaskSave = null;
	private MyE2_Button 		 oButtonMaskCancel = null;
	

	/*
	 * 2014-03-12: weitere moeglichkeit: einblenden eines buttons zum Speichern und offenhalten einer maske
	 */
	private boolean   				bShowButtonSaveAndKeepOpen = false;
	private MyE2_Button		  		oButtonMaskeSaveAndKeepOpen = null;
	private boolean  				bMaskWasSaveAndReloaded = false;
	
	
	
	private Vector<Component>   vZusatzComponentsInMaskTitleButtonBar = new Vector<Component>();
	
	/**
	 * @param actionName
	 * @param onavigationList
	 * @param omaskContainer
	 * @param oownButton
	 * @param ZusatzComponentsInMaskTitleButtonBar
	 * @param POPUP__Before_Execute
	 */
	public ButtonActionAgentNEW(		MyString 						actionName,
										E2_NavigationList 				onavigationList,
										E2_BasicModuleContainer_MASK 	omaskContainer,
										MyE2_Button						oownButton, 
										Vector<Component> 				ZusatzComponentsInMaskTitleButtonBar, 
										E2_BasicModuleContainer_PopUp_BeforeExecute POPUP__Before_Execute)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,false, true);
		if (ZusatzComponentsInMaskTitleButtonBar!=null) {
			this.vZusatzComponentsInMaskTitleButtonBar.addAll(ZusatzComponentsInMaskTitleButtonBar);
		}
		
		this.oPopupBeforExecute = POPUP__Before_Execute;
		
		this.oButtonMaskSave = new maskButtonSaveNew(this.oPopupBeforExecute);
		this.oButtonMaskCancel = new maskButtonCancel();

	}

	
	/**
	 * @param actionName
	 * @param onavigationList
	 * @param omaskContainer
	 * @param oownButton
	 * @param ZusatzComponentsInMaskTitleButtonBar
	 * @param POPUP__Before_Execute
	 * @param ShowSaveButtonAndKeepMaskOpen
	 */
	public ButtonActionAgentNEW(		MyString 										actionName,
										E2_NavigationList 								onavigationList,
										E2_BasicModuleContainer_MASK 					omaskContainer,
										MyE2_Button										oownButton, 
										Vector<Component> 								ZusatzComponentsInMaskTitleButtonBar, 
										E2_BasicModuleContainer_PopUp_BeforeExecute 	POPUP__Before_Execute,
										boolean  										ShowSaveButtonAndKeepMaskOpen)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,false, true);
		if (ZusatzComponentsInMaskTitleButtonBar!=null) {
			this.vZusatzComponentsInMaskTitleButtonBar.addAll(ZusatzComponentsInMaskTitleButtonBar);
		}
		
		this.oPopupBeforExecute = POPUP__Before_Execute;
		
		this.oButtonMaskSave = new maskButtonSaveNew(this.oPopupBeforExecute);
		this.oButtonMaskCancel = new maskButtonCancel();

		this.bShowButtonSaveAndKeepOpen = ShowSaveButtonAndKeepMaskOpen;
		
		if (this.bShowButtonSaveAndKeepOpen) {
			this.oButtonMaskeSaveAndKeepOpen = new maskButtonSaveNewAndKeepOpen(this.oPopupBeforExecute);
		}

	}


	
	

	public void do_innerAction() throws myException
	{
		/*
		 * Maske einstellen
		 */
		E2_vCombinedComponentMAPs vComponentMAPs = this.get_oMaskContainer().get_vCombinedComponentMAPs();
		vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(this.cMessageStartingNew.CTrans()));
	}

	public boolean do_prepareMaskForActualID()
	{
		return true;
	}

	public Component build_ComponentWithMaskButtons() throws myException
	{
		E2_ComponentGroupHorizontal oCompGroup= new E2_ComponentGroupHorizontal(null);
		oCompGroup.add(this.oButtonMaskSave);
		
		//2014-03-12: neuer button zum speichern und offen halten einer maske
		if (this.bShowButtonSaveAndKeepOpen) {
			oCompGroup.add(this.oButtonMaskeSaveAndKeepOpen);
		}
		
		oCompGroup.add(this.oButtonMaskCancel);
		
		
		if (this.vZusatzComponentsInMaskTitleButtonBar != null && this.vZusatzComponentsInMaskTitleButtonBar.size()>0)
		{
			for (int i=0;i<this.vZusatzComponentsInMaskTitleButtonBar.size();i++)
			{
				oCompGroup.add((Component)this.vZusatzComponentsInMaskTitleButtonBar.get(i));
			}
		}
		
		return oCompGroup;
	}

	
	public void showPopupWindow() throws myException
	{
		this.get_oMaskContainer().CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Maskenansicht"));
	}

	
	
	/*
	 * savebutton mit action-agent
	 * 2014-08-25: private geaendert in protected
	 */
	protected class maskButtonSaveNew extends MyE2_Button implements
														IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveNew(E2_BasicModuleContainer_PopUp_BeforeExecute  PopupBeforExecute)
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.setToolTipText(new MyE2_String("Maske speichern und schließen").CTrans());
			this.add_oActionAgent(new ownActionAgent(PopupBeforExecute));
		}

		private class ownActionAgent extends XX_ActionAgent
		{
			/*
			 * popup vor der eigentlichen speicherung einfuegen
			 */
			private E2_BasicModuleContainer_PopUp_BeforeExecute  o_PopupBeforExecute = null;

			public ownActionAgent(E2_BasicModuleContainer_PopUp_BeforeExecute popupBeforExecute) 
			{
				super();
				o_PopupBeforExecute = popupBeforExecute;
				if (this.o_PopupBeforExecute != null)
				{
					this.set_oPopup_ContainerBeforeExecute(this.o_PopupBeforExecute);
				}
			}

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ButtonActionAgentNEW oThis = ButtonActionAgentNEW.this;
			
				String cCreatedNewKey = null;
				
				
				/*
				 * das speichern wird hier mit dem E2_SaveMask - objekt gemacht, da folgendes passieren kann:
				 * Die Maske wird leer aufgerufen und mit einem maskenknopf gespeichert und neu geladen (z.B. fuer das editieren von Tochtertabellen),
				 * dann muss beim speichern aus der maske heraus natuerlicht nicht mehr NEU sondern EDIT-Status gespeichert werden.
				 * Das E2_SaveMask - object beruecksichtigt das
				 */
				
				try
				{
					
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentNEW.this.get_oMaskContainer(),
																			ButtonActionAgentNEW.this.get_oNavigationList());
					
					oSaveMask.doSaveMask(false);

					if (bibMSG.get_bIsOK())
						cCreatedNewKey = oSaveMask.get_cActualMaskID_Unformated();
					
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}

				if (bibMSG.get_bIsOK())
				{
					if (oThis.bMaskWasSaveAndReloaded) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageEditRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
					} else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageNewRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
					}
					ButtonActionAgentNEW.this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			}
		}



		@Override
		public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() 	throws myException
		{
			V_Single_BasicModuleContainer_PopUp_BeforeExecute  vRueck = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
			
			
			//nachschauen, auf welcher maske der button liegt
			E2_RecursiveSearchParent_BasicModuleContainer oRecSearch = new E2_RecursiveSearchParent_BasicModuleContainer(this);
			
			if (oRecSearch.get_First_FoundContainer() instanceof E2_BasicModuleContainer_MASK)
			{
				E2_vCombinedComponentMAPs vMaps = ((E2_BasicModuleContainer_MASK) oRecSearch.get_First_FoundContainer()).get_vCombinedComponentMAPs();
				
				for (int i=0;i<vMaps.size();i++)
				{
					if (vMaps.get(i) instanceof IF__BasicModuleContainer_PopUp_BeforeExecute_Container)
					{
						vRueck.addAll(((IF__BasicModuleContainer_PopUp_BeforeExecute_Container)vMaps.get(i)).get_vE2_BasicModuleContainer_PopUp_BeforeExecute());
					}
				}
			}
			return vRueck;
		}
	}
	
	
	
	
	
	/*
	 * 2014-03-12: weiteres savebutton der moeglichkeit, weiterzueditieren
 	 * 2014-08-25: private geaendert in protected
	 */
	protected class maskButtonSaveNewAndKeepOpen extends MyE2_Button implements
														IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveNewAndKeepOpen(E2_BasicModuleContainer_PopUp_BeforeExecute  PopupBeforExecute)
		{
			super(E2_ResourceIcon.get_RI("save_edit.png"), true);
			this.setToolTipText(new MyE2_String("Maske speichern, anschließend weiterbearbeiten").CTrans());
			this.add_oActionAgent(new ownActionAgent2(PopupBeforExecute));
		}

		private class ownActionAgent2 extends XX_ActionAgent
		{
			/*
			 * popup vor der eigentlichen speicherung einfuegen
			 */
			private E2_BasicModuleContainer_PopUp_BeforeExecute  o_PopupBeforExecute = null;

			public ownActionAgent2(E2_BasicModuleContainer_PopUp_BeforeExecute popupBeforExecute) 
			{
				super();
				o_PopupBeforExecute = popupBeforExecute;
				if (this.o_PopupBeforExecute != null)
				{
					this.set_oPopup_ContainerBeforeExecute(this.o_PopupBeforExecute);
				}
			}

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ButtonActionAgentNEW oThis = ButtonActionAgentNEW.this;
			
				String cCreatedNewKey = null;
				
				
				try
				{
					
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentNEW.this.get_oMaskContainer(),
																			ButtonActionAgentNEW.this.get_oNavigationList());
					
					oSaveMask.doSaveMask(true);

					if (bibMSG.get_bIsOK()) {
						cCreatedNewKey = oSaveMask.get_cActualMaskID_Unformated();
					}
					
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}

				if (bibMSG.get_bIsOK())
				{
					if (oThis.bMaskWasSaveAndReloaded) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageEditRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
					} else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageNewRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
					}
					oThis.bMaskWasSaveAndReloaded = true;
				}
			}
		}



		@Override
		public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() 	throws myException
		{
			V_Single_BasicModuleContainer_PopUp_BeforeExecute  vRueck = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
			
			
			//nachschauen, auf welcher maske der button liegt
			E2_RecursiveSearchParent_BasicModuleContainer oRecSearch = new E2_RecursiveSearchParent_BasicModuleContainer(this);
			
			if (oRecSearch.get_First_FoundContainer() instanceof E2_BasicModuleContainer_MASK)
			{
				E2_vCombinedComponentMAPs vMaps = ((E2_BasicModuleContainer_MASK) oRecSearch.get_First_FoundContainer()).get_vCombinedComponentMAPs();
				
				for (int i=0;i<vMaps.size();i++)
				{
					if (vMaps.get(i) instanceof IF__BasicModuleContainer_PopUp_BeforeExecute_Container)
					{
						vRueck.addAll(((IF__BasicModuleContainer_PopUp_BeforeExecute_Container)vMaps.get(i)).get_vE2_BasicModuleContainer_PopUp_BeforeExecute());
					}
				}
			}
			return vRueck;
		}
	}

	
	
	
	
	
	
	/*
	 * 2014-08-25: private geaendert in protected
	 */
	protected class maskButtonCancel extends MyE2_Button
	{
		
		public maskButtonCancel()
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.setToolTipText(new MyE2_String("Maske schließen, Bearbeitung wird abgebrochen").CTrans());
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentNEW.this.get_oMaskContainer(),
																		ButtonActionAgentNEW.this.get_oNavigationList());
				oSaveMask.doCancelMask_AND_RefreshNaviList();
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
				try
				{
					ButtonActionAgentNEW.this.get_oMaskContainer().get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancelMask:ownActionAgent:doAction:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}



	public void set_cMessageNewRowIsSaved(MyString messageNewRowIsSaved) 
	{
		cMessageNewRowIsSaved = messageNewRowIsSaved;
	}

	public void set_cMessageStartingNew(MyString messageStartingNew) 
	{
		cMessageStartingNew = messageStartingNew;
	}
	
	
	public MyE2_Button get_oButtonMaskCancel()
	{
		return oButtonMaskCancel;
	}

	public MyE2_Button get_oButtonMaskSave()
	{
		return oButtonMaskSave;
	}

	public MyE2_Button get_oButtonMaskSaveAndKeepOpen()
	{
		return oButtonMaskeSaveAndKeepOpen;
	}


	public Vector<Component> get_vZusatzComponentsInMaskTitleButtonBar() {
		return vZusatzComponentsInMaskTitleButtonBar;
	}

}
