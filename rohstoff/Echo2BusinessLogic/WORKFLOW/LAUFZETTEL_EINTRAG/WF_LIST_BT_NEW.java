package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class WF_LIST_BT_NEW extends MyE2_ButtonWithKey
{
 
	private E2_BasicModuleContainer_MASK oModulContainer = null;
	
	public WF_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F8);
		this.add_oActionAgent(new ownButtonActionAgentNEW(	new MyE2_String("Neueingabe eines Laufzettel-Eintrages... "),
															onavigationList,
															omaskContainer,
															this.get_oButton(),
															null));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_WF"));
		
		oModulContainer = omaskContainer;
	}
	
//	private class ownActionAgent extends ButtonActionAgentNEW
//	{
//		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
//		{
//			super(new MyE2_String("Neueingabe eines Laufzettel-Eintrages... "), onavigationList, omaskContainer, oownButton, null);
//		}
//	}
	
	private class ownButtonActionAgentNEW extends XX_ButtonActionAgent_FromListToMask
	{
		private MyString cMessageStartingNew = 		new MyE2_String("Erfassen eines NEUEN Datensatzes ...");
		private MyString cMessageNewRowIsSaved = 	new MyE2_String("Datensatz hinzugefügt: ");
		
		private Vector<Component>   vZusatzComponentsInMaskTitleButtonBar = null;
		
		/**
		 * @param actionName
		 * @param onavigationList
		 * @param omaskContainer
		 * @param oownButton
		 * @param ZusatzComponentsInMaskTitleButtonBar
		 */
		public ownButtonActionAgentNEW(		MyString 						actionName,
											E2_NavigationList 				onavigationList,
											E2_BasicModuleContainer_MASK 	omaskContainer,
											MyE2_Button						oownButton, 
											Vector<Component> 				ZusatzComponentsInMaskTitleButtonBar)
		{
			super(actionName,onavigationList,omaskContainer,oownButton,true,false, true);
			this.vZusatzComponentsInMaskTitleButtonBar = ZusatzComponentsInMaskTitleButtonBar;
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

		public Component build_ComponentWithMaskButtons()
		{
			E2_ComponentGroupHorizontal oCompGroup= new E2_ComponentGroupHorizontal(null);
			oCompGroup.add(new maskButtonSaveNew());
			oCompGroup.add(new maskButtonCancel());
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
		private class maskButtonSaveNew extends MyE2_Button
		{
			
			public maskButtonSaveNew()
			{
				super(E2_ResourceIcon.get_RI("save.png"), true);
				this.add_oActionAgent(new ownActionAgent());
			}

			private class ownActionAgent extends XX_ActionAgent
			{

				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					ownButtonActionAgentNEW oThis = ownButtonActionAgentNEW.this;
				
					String cCreatedNewKey = null;
					
					
					/*
					 * das speichern wird hier mit dem E2_SaveMask - objekt gemacht, da folgendes passieren kann:
					 * Die Maske wird leer aufgerufen und mit einem maskenknopf gespeichert und neu geladen (z.B. fuer das editieren von Tochtertabellen),
					 * dann muss beim speichern aus der maske heraus natuerlicht nicht mehr NEU sondern EDIT-Status gespeichert werden.
					 * Das E2_SaveMask - object beruecksichtigt das
					 */
					
					Vector<String> vIDs_Formated = new Vector<String>();
					E2_ComponentMAP oMap = WF_LIST_BT_NEW.this.oModulContainer.get_vCombinedComponentMAPs().get(0);
					
					MyE2_DB_SelectField oSelFieldBearbeiter = (MyE2_DB_SelectField)oMap.get__Comp_From_RealComponents("ID_USER_BEARBEITER");
					
					for (int i=0;i<vIDs_Formated.size();i++)
					{
					
						try
						{
							oSelFieldBearbeiter.set_ActiveValue(vIDs_Formated.get(i));
							
							E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ownButtonActionAgentNEW.this.get_oMaskContainer(),
																					ownButtonActionAgentNEW.this.get_oNavigationList());
							
							oSaveMask.doSaveMask(false);
	
							if (bibMSG.get_bIsOK())
								cCreatedNewKey = oSaveMask.get_cActualMaskID_Unformated();
							
						}
						catch (myException ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
						}
						if (bibMSG.get_bHasAlarms())
						{
							break;
						}
					}
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(oThis.cMessageNewRowIsSaved.CTrans()+" ID:"+cCreatedNewKey,false)), false);
						ownButtonActionAgentNEW.this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
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
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(ownButtonActionAgentNEW.this.get_oMaskContainer(),
																			ownButtonActionAgentNEW.this.get_oNavigationList());
					oSaveMask.doCancelMask_AND_RefreshNaviList();
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
					try
					{
						ownButtonActionAgentNEW.this.get_oMaskContainer().get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
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
		
		
	}

	
}
