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
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class ButtonActionAgentNEW_FROM_COPY extends XX_ButtonActionAgent_FromListToMask
{
	private MyString cMessageStartingNew = 		new MyE2_String("Erfassen eines KOPIERTEN Datensatzes ...");
	private MyString cMessageNewRowIsSaved = 	new MyE2_String("Datensatz hinzugefügt: ");
	
	
	
	/*
	 * hier eine moeglichkeit vorsehen, einen popup vor der eigentlichen speicherung einzufuegen
	 */
	private E2_BasicModuleContainer_PopUp_BeforeExecute  oPopupBeforExecute = null;

	
	
	/*
	 * die 2 buttons zum speichern und zum abbruch werden veroeffentlicht, damit evtl weitere ActionAgents uebergeben werden koennen
	 */
	private MyE2_Button 		 oButtonMaskSave = null;
	private MyE2_Button 		 oButtonMaskCancel = null;
	

	
	private Vector<Component>   vZusatzComponentsInMaskTitleButtonBar = null;
	
	/**
	 * @param actionName
	 * @param onavigationList
	 * @param omaskContainer
	 * @param oownButton
	 * @param ZusatzComponentsInMaskTitleButtonBar
	 * @param POPUP__Before_Execute
	 */
	public ButtonActionAgentNEW_FROM_COPY(		MyString 						actionName,
										E2_NavigationList 				onavigationList,
										E2_BasicModuleContainer_MASK 	omaskContainer,
										MyE2_Button						oownButton, 
										Vector<Component> 				ZusatzComponentsInMaskTitleButtonBar, 
										E2_BasicModuleContainer_PopUp_BeforeExecute POPUP__Before_Execute)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,false, true);
		this.vZusatzComponentsInMaskTitleButtonBar = ZusatzComponentsInMaskTitleButtonBar;
		
		this.oPopupBeforExecute = POPUP__Before_Execute;
		
		this.oButtonMaskSave = new maskButtonSaveNew(this.oPopupBeforExecute);
		this.oButtonMaskCancel = new maskButtonCancel();

	}

	public void do_innerAction() throws myException
	{
		/*
		 * Maske einstellen
		 */
		Vector<String> vIDs_Selected = this.get_oNavigationList().get_vSelectedIDs_Unformated();
		if (vIDs_Selected.size()!=1)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bei einer Kopie muss exakt EIN Datensatz ausgewählt werden !"));
			return;
		}
		
		E2_vCombinedComponentMAPs vComponentMAPs = this.get_oMaskContainer().get_vCombinedComponentMAPs();
		vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_COPY,vIDs_Selected.get(0));
		
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
	 */
	private class maskButtonSaveNew extends MyE2_Button implements
														IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveNew(E2_BasicModuleContainer_PopUp_BeforeExecute  PopupBeforExecute)
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
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
				ButtonActionAgentNEW_FROM_COPY oThis = ButtonActionAgentNEW_FROM_COPY.this;
			
				String cCreatedNewKey = null;
				
				
				/*
				 * das speichern wird hier mit dem E2_SaveMask - objekt gemacht, da folgendes passieren kann:
				 * Die Maske wird leer aufgerufen und mit einem maskenknopf gespeichert und neu geladen (z.B. fuer das editieren von Tochtertabellen),
				 * dann muss beim speichern aus der maske heraus natuerlicht nicht mehr NEU sondern EDIT-Status gespeichert werden.
				 * Das E2_SaveMask - object beruecksichtigt das
				 */
				
				try
				{
					
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentNEW_FROM_COPY.this.get_oMaskContainer(),
																			ButtonActionAgentNEW_FROM_COPY.this.get_oNavigationList());
					
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
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageNewRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
					ButtonActionAgentNEW_FROM_COPY.this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
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
	
	
	
	private class maskButtonCancel extends MyE2_Button
	{
		
		public maskButtonCancel()
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ButtonActionAgentNEW_FROM_COPY.this.get_oMaskContainer(),
																		ButtonActionAgentNEW_FROM_COPY.this.get_oNavigationList());
				oSaveMask.doCancelMask_AND_RefreshNaviList();
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
				try
				{
					ButtonActionAgentNEW_FROM_COPY.this.get_oMaskContainer().get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
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

	
}
