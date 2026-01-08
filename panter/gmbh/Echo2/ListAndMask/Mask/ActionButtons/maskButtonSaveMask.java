package panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons;

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
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;


public class maskButtonSaveMask extends E2_MaskSaveButton implements 
								IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
	
	/*
	 * map enthaelt die komponenten der maske
	 */
	private E2_SaveMASK 					oMaskSaver = null;
	private E2_NavigationList 				oNavList = null;
	private MyDBToolBox						oDB = null;
	private E2_BasicModuleContainer_MASK	oMaskContainer = null;

	
	private XX_ActionAgent                  oZusatzActionAfterSave = null;
	
	public maskButtonSaveMask(	E2_BasicModuleContainer_MASK 				MaskContainer,
								E2_SaveMASK 								maskSaver, 
								E2_NavigationList 							onavList, 
								E2_BasicModuleContainer_PopUp_BeforeExecute oPOPUP_BeforeExecute)
	{
		super(E2_ResourceIcon.get_RI("save.png"), true);
		this.oMaskSaver = maskSaver;
		this.oNavList = onavList;
		this.oMaskContainer = MaskContainer;
		this.add_oActionAgent(new ownActionAgent(oPOPUP_BeforeExecute));
		this.oDB = bibALL.get_myDBToolBox();
	}

	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		public ownActionAgent(E2_BasicModuleContainer_PopUp_BeforeExecute beforeExecute)
		{
			super();
			
			this.set_oPopup_ContainerBeforeExecute(beforeExecute);
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			maskButtonSaveMask oThis = maskButtonSaveMask.this;
			
			try
			{
				oThis.oMaskSaver.doSaveMask(false);
				if (bibMSG.get_bIsOK())
				{
					if (oThis.oMaskSaver.get_cMASK_STATUS().equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
					{
						if (oThis.oNavList!=null)
							oThis.oNavList.ADD_NEW_ID_TO_ALL_VECTORS(oThis.oMaskSaver.get_cActualMaskID_Unformated());
					}
					
					if (oThis.oNavList!=null)
						oThis.oNavList._REBUILD_ACTUAL_SITE(oThis.oMaskSaver.get_cActualMaskID_Unformated());
		
					if (oThis.oZusatzActionAfterSave != null)
					{
						//vom zusatz-agent wird nur der innere code mit der original EXECInfo gestartet
						oThis.oZusatzActionAfterSave.executeAgentCode(oExecInfo);
					}
					
					if (bibMSG.get_bIsOK())
					{
						oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Datensatz wurde gespeichert: "+oThis.oMaskSaver.get_cActualMaskID_Unformated())), false);
					}
				}
				else
				{
					if (bibMSG.get_bIsOK())
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")),true);
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


	public void set_oZusatzActionAfterSave(XX_ActionAgent zusatzActionAfterSave)
	{
		oZusatzActionAfterSave = zusatzActionAfterSave;
	}
	
}
