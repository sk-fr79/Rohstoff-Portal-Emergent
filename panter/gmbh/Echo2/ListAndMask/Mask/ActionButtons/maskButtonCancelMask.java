package panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public abstract class maskButtonCancelMask extends MyE2_ButtonWithKey
{
	
	/*
	 * map enthaelt die komponenten der maske
	 */
	private E2_BasicModuleContainer_MASK 		oMaskContainer = null;

	
	public maskButtonCancelMask(E2_BasicModuleContainer_MASK maskContainer)
	{
		super(E2_ResourceIcon.get_RI("cancel.png"), true,KeyStrokeListener.VK_ESCAPE);
		this.oMaskContainer = maskContainer;
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_BasicModuleContainer_MASK oMask_Container = maskButtonCancelMask.this.oMaskContainer;
			
			if (oMask_Container.get_bPopUpWasShown())
				oMask_Container.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
			 
			try
			{
				E2_ComponentMAP 		oLeadingMaskMAP = 	maskButtonCancelMask.this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();

				//status der maske feststellen (es wird nur zwischen edit und new unterschieden (copy=new)
				String cActualMaskStatus = E2_ComponentMAP.STATUS_NEW_EMPTY;
				if (oLeadingMaskMAP.get_oInternalSQLResultMAP()!=null)
					cActualMaskStatus = E2_ComponentMAP.STATUS_EDIT;

				oMask_Container.get_vCombinedComponentMAPs().do_MapSettings_AFTER(cActualMaskStatus);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancelMask:ownActionAgent:doAction:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	public abstract boolean doActionAfterCancelMask();
	
}
