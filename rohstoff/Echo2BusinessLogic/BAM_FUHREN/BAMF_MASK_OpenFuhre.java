package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_BT_PRINT_MAIL_BELEG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;

public class BAMF_MASK_OpenFuhre extends MyE2_Button
{

//	private String cID_FBAM_uF = null;
	
	private boolean bEdit_true_view_false = true;
	
	private BAMF_MASK_ModulContainer oBAMF_MASK_ModulContainer = null;
	
	/**
	 * 
	 * @param Edit_true_view_false (wenn true, dann editieren, sonst anzeigen)
	 */
	public BAMF_MASK_OpenFuhre(BAMF_MASK_ModulContainer obamf_MASK_ModulContainer, boolean Edit_true_view_false)
	{
		super(new MyE2_String("Fuhre"), MyE2_Button.StyleTextButton());
		
		this.oBAMF_MASK_ModulContainer = obamf_MASK_ModulContainer;
		this.bEdit_true_view_false = Edit_true_view_false;
		
		this.add_GlobalAUTHValidator_AUTO(this.bEdit_true_view_false?"FUHRE_AUS_MASKE_EDIT":"FUHRE_AUS_MASKE_VIEW");
		
		this.add_oActionAgent(new ownActionOpenFuhrenMask());
		
		if (this.bEdit_true_view_false)
		{
			this.setIcon(E2_ResourceIcon.get_RI("edit_mini.png"));
		}
		else
		{
			this.setIcon(E2_ResourceIcon.get_RI("view_mini.png"));
		}
	}

	
	
//	public String get_cID_FBAM_uF()
//	{
//		return cID_FBAM_uF;
//	}

//	public void set_cID_FBAM_uF(String ID_FBAM_uF)
//	{
//		this.cID_FBAM_uF = ID_FBAM_uF;
//	}

	
	

	
	
	
	private class ownActionOpenFuhrenMask extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BAMF_MASK_OpenFuhre oThis = BAMF_MASK_OpenFuhre.this;
			
			String cID_FBAM = oThis.oBAMF_MASK_ModulContainer.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (S.isEmpty(cID_FBAM))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Öffnen der Fuhre ist nicht möglich -- FBAM-ID ist nicht bekannt !")));
				return;
			}
			
			RECORD_FBAM recFBAM = new RECORD_FBAM(cID_FBAM);
			
			String cID_VPOS_TPA_FUHRE = 	recFBAM.get_ID_VPOS_TPA_FUHRE_cUF_NN("");
			String cID_VPOS_TPA_FUHRE_ORT = recFBAM.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");
			
			if (S.isFull(cID_VPOS_TPA_FUHRE_ORT))
			{
				cID_VPOS_TPA_FUHRE = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT).get_ID_VPOS_TPA_FUHRE_cUF();
			}
			
			if (S.isEmpty(cID_VPOS_TPA_FUHRE))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Öffnen der Fuhre ist nicht möglich -- Fuhren-ID ist nicht bekannt !")));
			}
			
			FU_MASK_ModulContainer oFuhreMask = new FU_MASK_ModulContainer(null, null, false, false);
			
			if (oThis.bEdit_true_view_false)
			{
				oThis.buildEditMask(oFuhreMask, cID_VPOS_TPA_FUHRE);
			}
			else
			{
				oThis.buildViewMask(oFuhreMask, cID_VPOS_TPA_FUHRE);
			}
		}
	}
	
	
	private void buildViewMask(FU_MASK_ModulContainer oMaskContainer, String cID_VPOS_TPA_Fuhre) throws myException
	{
		oMaskContainer.get_oRowForButtons().removeAll();
		oMaskContainer.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																new maskButtonCancel(oMaskContainer),
																new FU_MASK_BT_PRINT_MAIL_BELEG(oMaskContainer,false),
																new Insets(0,2,4,2)));
		
		oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID_VPOS_TPA_Fuhre);
		
		oMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(800),new MyE2_String("Fuhre ",true,""+cID_VPOS_TPA_Fuhre,false," (Status: Ansichtsmodus)",true));
		
	}
	

	private void buildEditMask(FU_MASK_ModulContainer oMaskContainer, String cID_VPOS_TPA_Fuhre) throws myException
	{
		oMaskContainer.get_oRowForButtons().removeAll();
		oMaskContainer.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																new maskButtonSaveEdit(oMaskContainer),
																new maskButtonCancel(oMaskContainer),
																new FU_MASK_BT_PRINT_MAIL_BELEG(oMaskContainer,false),
																new Insets(0,2,4,2)));

		oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_VPOS_TPA_Fuhre);
		
		oMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(800),new MyE2_String("Fuhre ",true,""+cID_VPOS_TPA_Fuhre,false," (Status: Bearbeitungsmodus)",true));
	}

	
	
	
	/*
	 * der save-button fuer die maske
	 */
	private class maskButtonSaveEdit extends E2_MaskSaveButton
	{
		
		private E2_BasicModuleContainer_MASK oMaskContainer = null;
		
		public maskButtonSaveEdit(E2_BasicModuleContainer_MASK MaskContainer)
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			
			this.oMaskContainer = MaskContainer;
			
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{
				maskButtonSaveEdit oThis = maskButtonSaveEdit.this;
				try
				{
					//E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oModulContainerMASK,oThis.oNaviList);
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oMaskContainer,null);

					oSaveMask.doSaveMask(false);
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die aktuelle Fuhre zur BAM wurde gespeichert ")));
						
						oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
				catch (myExceptionForUser ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
	

	
	private class maskButtonCancel extends MyE2_Button
	{
		private E2_BasicModuleContainer_MASK oMaskContainer = null;
		
		public maskButtonCancel(E2_BasicModuleContainer_MASK MaskContainer)
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.oMaskContainer = MaskContainer;
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				maskButtonCancel oThis = maskButtonCancel.this;
				oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Fuhren - Maske wurde geschlossen ..."));
			}
		}
	}


	
}
