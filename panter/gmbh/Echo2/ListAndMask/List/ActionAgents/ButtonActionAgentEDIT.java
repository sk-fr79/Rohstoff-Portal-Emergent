package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF__BasicModuleContainer_PopUp_BeforeExecute_Container;
import panter.gmbh.Echo2.ActionEventTools.V_Single_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class ButtonActionAgentEDIT extends XX_ButtonActionAgent_FromListToMask
{

	private MyString					cMessageStartEdit = new MyE2_String("Editieren von Datensatz : ");
	private Vector<Component>  			vZusatzComponentsInMaskTitleButtonBar = new Vector<Component>();

	
	
	/*
	 * hier eine moeglichkeit vorsehen, einen popup vor der eigentlichen speicherung einzufuegen
	 */
	private E2_BasicModuleContainer_PopUp_BeforeExecute  oPopupBeforExecute = null;
	
	
	/*
	 * die 2 buttons zum speichern und zum abbruch werden veroeffentlicht, damit evtl weitere ActionAgents uebergeben werden koennen
	 */
	private E2_MaskSaveButton 		 oButtonMaskSave = null;
	private MyE2_Button 			 oButtonMaskCancel = null;
	
	
	/*
	 * 2014-03-12: weitere moeglichkeit: einblenden eines buttons zum Speichern und offenhalten einer maske
	 */
	private boolean   				bShowButtonSaveAndKeepOpen = false;
	private MyE2_Button		  		oButtonMaskeSaveAndKeepOpen = null;
	
	
	/**
	 * @param actionName
	 * @param onavigationList
	 * @param omaskContainer
	 * @param oownButton
	 * @param ZusatzComponentsInMaskTitleButtonBar
	 * @param POPUP__Before_Execute
	 */
	public ButtonActionAgentEDIT(	MyString 									actionName,
									E2_NavigationList 							onavigationList,
									E2_BasicModuleContainer_MASK 				omaskContainer,
									MyE2_Button									oownButton, 
									Vector<Component> 							ZusatzComponentsInMaskTitleButtonBar, 
									E2_BasicModuleContainer_PopUp_BeforeExecute POPUP__Before_Execute)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,true, false);
		this.set_cTextJumpToNext(new MyE2_String("Überspringe Datensatz OHNE zu speichern !!!"));
		
		if (ZusatzComponentsInMaskTitleButtonBar != null)
		{
			this.vZusatzComponentsInMaskTitleButtonBar.addAll(ZusatzComponentsInMaskTitleButtonBar);
		}
		
		this.oPopupBeforExecute = POPUP__Before_Execute;
		
		this.oButtonMaskSave = new maskButtonSaveEdit(this.oPopupBeforExecute);
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
	public ButtonActionAgentEDIT(	MyString 									actionName,
									E2_NavigationList 							onavigationList,
									E2_BasicModuleContainer_MASK 				omaskContainer,
									MyE2_Button									oownButton, 
									Vector<Component> 							ZusatzComponentsInMaskTitleButtonBar, 
									E2_BasicModuleContainer_PopUp_BeforeExecute POPUP__Before_Execute,
									boolean  									ShowSaveButtonAndKeepMaskOpen)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,true, false);
		this.set_cTextJumpToNext(new MyE2_String("Überspringe Datensatz OHNE zu speichern !!!"));
		
		if (ZusatzComponentsInMaskTitleButtonBar != null)
		{
			this.vZusatzComponentsInMaskTitleButtonBar.addAll(ZusatzComponentsInMaskTitleButtonBar);
		}
		
		this.oPopupBeforExecute = POPUP__Before_Execute;
		
		this.oButtonMaskSave = new maskButtonSaveEdit(this.oPopupBeforExecute);
		this.oButtonMaskCancel = new maskButtonCancel();
		
		this.bShowButtonSaveAndKeepOpen = ShowSaveButtonAndKeepMaskOpen;
		
		if (this.bShowButtonSaveAndKeepOpen) {
			this.oButtonMaskeSaveAndKeepOpen = new maskButtonSaveEditAndKeepOpen(this.oPopupBeforExecute);
		}
		
	}


	
	
	
	
	public void do_innerAction()  throws myException
	{

		E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = this.get_oMaskContainer().get_vCombinedComponentMAPs();
		
		String cID = this.get_cActualID_Unformated();

		bibMSG.add_MESSAGE(this.get_oOwnButton().valid_IDValidation(bibALL.get_Vector(cID)));
		
		if (bibMSG.get_bIsOK())
		{
			vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
			if (this.get_oMaskContainer().get_bPopUpWasShown())
			{
				this.get_oMaskContainer().get_oWindowPane().set_oTitle(new MyE2_String("Maskenansicht zum Bearbeiten:  ID="+cID));
			}
			bibMSG.add_MESSAGE(new MyE2_Info_Message(this.cMessageStartEdit.CTrans()+"  ID:"+cID),true);
			
		}
		else
		{
			/*
			 * beenden
			 */
			this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
			vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
		}
		
	}

	public boolean do_prepareMaskForActualID() throws myException
	{
		return true;
	}


	public Component build_ComponentWithMaskButtons() throws myException
	{
		E2_ComponentGroupHorizontal oCompGroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_10_2);
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

	
	
	public void set_cMessageStartEdit(MyString messageStartEdit) 
	{
		cMessageStartEdit = messageStartEdit;
	}
	
	

	
	
	
	/*
	 * der save-button fuer die maske
	 * 2014-08-25: private geaendert in protected
	 */
	protected class maskButtonSaveEdit extends E2_MaskSaveButton  implements 
															IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveEdit(E2_BasicModuleContainer_PopUp_BeforeExecute  PopupBeforExecute)
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.setToolTipText(new MyE2_String("Maske speichern, anschließend schließen oder zum nächsten Datensatz wechseln").CTrans());
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


			public void executeAgentCode(ExecINFO oExecInfo)
			{
				ButtonActionAgentEDIT oThis = ButtonActionAgentEDIT.this;

				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.get_oMaskContainer().get_vCombinedComponentMAPs();

				try
				{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.get_oMaskContainer(),	oThis.get_oNavigationList());
					try
					{
						oSaveMask.doSaveMask(false);
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()), false);
						}
					}
					catch (myExceptionForUser exx)
					{
						exx.printStackTrace();
						bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
					}

					
					if (bibMSG.get_bIsOK())
					{
						/*
						 * weiter, den naechsten aktivieren oder schliessen
						 */
						if (ButtonActionAgentEDIT.this.activate_Next_ID())
						{
							ButtonActionAgentEDIT.this.do_innerAction();
						}
						else
						{
							ButtonActionAgentEDIT.this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
							vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
						}
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
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
	 * 2014-03-12: der save-button fuer die maske mit der moeglichkeit, die maske offen zu halten
	 * 2014-08-25: private geaendert in protected
	 */
	protected class maskButtonSaveEditAndKeepOpen extends E2_MaskSaveButton  implements 
															IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveEditAndKeepOpen(E2_BasicModuleContainer_PopUp_BeforeExecute  PopupBeforExecute)
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


			public void executeAgentCode(ExecINFO oExecInfo)
			{
				ButtonActionAgentEDIT oThis = ButtonActionAgentEDIT.this;

				try
				{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.get_oMaskContainer(),	oThis.get_oNavigationList());
					try
					{
						oSaveMask.doSaveMask(true);
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()), false);
						}
					}
					catch (myExceptionForUser exx)
					{
						exx.printStackTrace();
						bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
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
				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentEDIT.this.get_oMaskContainer(),
																		ButtonActionAgentEDIT.this.get_oNavigationList());
				oSaveMask.doCancelMask_AND_RefreshNaviList();

				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen ..."));
			}
		}
	}



	public void showPopupWindow() throws myException
	{
		this.get_oMaskContainer().CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Maskenansicht zum Bearbeiten:  ID="+this.get_cActualID_Unformated()));
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

	
	public Vector<Component> get_vZusatzkomponenten()
	{
		return vZusatzComponentsInMaskTitleButtonBar;
	}



}
