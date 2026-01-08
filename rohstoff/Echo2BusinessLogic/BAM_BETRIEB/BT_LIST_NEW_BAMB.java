/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BT_LIST_NEW_BAMB extends MyE2_Button
{
	
	private BAMB_MASK_ModulContainer 	oMask_Container = null;
	private E2_NavigationList			oNaviList = null;
	
	
	public BT_LIST_NEW_BAMB(	E2_NavigationList oList,
								BAMB_MASK_ModulContainer oMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png"), true);
		
		this.oMask_Container = oMaskContainer;
		this.oNaviList = oList;
		this.add_oActionAgent(new ActionAgentNEW(oList,oMaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_BBAM_LIST,"NEUEINGABE_BBAM"));

	}
	
	
	
	private class ActionAgentNEW extends ButtonActionAgentNEW
	{
		public ActionAgentNEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neueingabe Betriebs-BAM"), onavigationList, omaskContainer, oownButton, null, null);
		}



		public Component build_ComponentWithMaskButtons() throws myException
		{
			/*
			 * zuerst die buttons der basis-klasse einbauen
			 */
			// E2_ComponentGroupHorizontal oCompGroup= (E2_ComponentGroupHorizontal)super.build_ComponentWithMaskButtons();

			E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(null);
			
			
			MyE2_Button oButtonPrintBAM = new BT_MASK_Mail_Print_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),BT_LIST_NEW_BAMB.this.oNaviList,false);
			MyE2_Button oButtonUnlockBAM = new  BT_MASK_Unlock_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer());
			//MyE2_Button oButtonMailBAM = new BT_MASK_Mail_BAMB((BAMB_MASK_ModulContainer)this.get_oMaskContainer(),BT_LIST_NEW_BAMB.this.oNaviList);
			
			oCompGroup.add(new maskButtonCancel());
			oCompGroup.add(new maskButtonSave());
			
			oCompGroup.add(oButtonPrintBAM);
			oCompGroup.add(oButtonUnlockBAM);
			//oCompGroup.add(oButtonMailBAM);
			return oCompGroup;
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
				BT_LIST_NEW_BAMB.this.oMask_Container.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
				try
				{
					BT_LIST_NEW_BAMB.this.oMask_Container.get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancelMask:ownActionAgent:doAction:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}
	
	
	
	
	private class maskButtonSave extends MyE2_Button
	{
		
		public maskButtonSave()
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		/*
		 * action-agent fuer maskenspeicher-button
		 */
		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{
				try
				{
					BAMB_MASK_ModulContainer 	oMask = BT_LIST_NEW_BAMB.this.oMask_Container;
					E2_NavigationList			oList = BT_LIST_NEW_BAMB.this.oNaviList;
					
					Save_BAMB	oSave = new Save_BAMB(BT_LIST_NEW_BAMB.this.oMask_Container,BT_LIST_NEW_BAMB.this.oNaviList);
					
					if (oSave.get_bISOK())
					{
						
						if (! oList.Refresh_ComponentMAP(oSave.get_cActualMaskID_Unformated(),E2_ComponentMAP.STATUS_VIEW))
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neuaufbau der Listenzeile !"));

						oList.Mark_ID_IF_IN_Page(oSave.get_cActualMaskID_Unformated());
						oMask.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}

			}	
		}
	}

	
	
	

	
	
}