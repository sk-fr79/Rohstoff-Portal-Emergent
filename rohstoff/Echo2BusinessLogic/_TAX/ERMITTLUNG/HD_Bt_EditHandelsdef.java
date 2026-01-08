package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonCancelMask;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_BasicModuleContainer;

public class HD_Bt_EditHandelsdef extends HD_BT_BASIC
{


	private RECORD_HANDELSDEF  RecHandelsDef = null;


	
	public HD_Bt_EditHandelsdef(RECORD_HANDELSDEF  recHandelsDef) 
	{
		//2018-07-12: neue validierung: super(E2_ResourceIcon.get_RI("edit.png"), TAX_CONST.VALID_KEY_BEARBEITE_HANDELSDEF);
		super(E2_ResourceIcon.get_RI("edit.png"), ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT);
		this.RecHandelsDef = recHandelsDef;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Bearbeiten einer Handelsdefinition").CTrans());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			HD_Bt_EditHandelsdef oThis = HD_Bt_EditHandelsdef.this;

			TR__MASK_BasicModuleContainer oMaskContainer = new TR__MASK_BasicModuleContainer(null);
			
			ownMaskSaver oMaskSaver=new ownMaskSaver(oMaskContainer);
			E2_ComponentGroupHorizontal oButtonGroup = new E2_ComponentGroupHorizontal(0,
										new maskButtonSaveMask(oMaskContainer,oMaskSaver,null, null), 
										new ownCancelButton(oMaskContainer), E2_INSETS.I_0_2_10_2);
			oMaskContainer.get_oRowForButtons().add(oButtonGroup);
			
			E2_vCombinedComponentMAPs vComponentMAPs = oMaskContainer.get_vCombinedComponentMAPs();
			vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oThis.RecHandelsDef.get_ID_HANDELSDEF_cUF());
			
			oMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Bearbeite die gefundene Handelsdefinition ..."));
		}
	}
	
	
	
	
	private class ownMaskSaver extends E2_SaveMASK
	{
		public ownMaskSaver(E2_BasicModuleContainer_MASK maskContainer) 
		{
			super(maskContainer, null);
		}

		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus) 
		{
			return true;
		}

		public void actionAfterSaveMask() throws myException
		{
		}
	}
	
	
	private class ownCancelButton extends maskButtonCancelMask
	{
		public ownCancelButton(E2_BasicModuleContainer_MASK maskContainer) 
		{
			super(maskContainer);
		}
		public boolean doActionAfterCancelMask() 
		{
			return true;
		}
	}

}
