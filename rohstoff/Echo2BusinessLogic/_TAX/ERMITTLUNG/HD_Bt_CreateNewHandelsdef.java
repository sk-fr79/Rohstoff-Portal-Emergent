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
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_BasicModuleContainer;

public class HD_Bt_CreateNewHandelsdef extends HD_BT_BASIC
{


	private HD_Station 		StationEK = null;
	private HD_Station 		StationVK = null;

	
	public HD_Bt_CreateNewHandelsdef(HD_Station stationEK, HD_Station stationVK) 
	{
		//2018-07-12: neue validierung:  super(E2_ResourceIcon.get_RI("new.png"),TAX_CONST.VALID_KEY_ERZEUGE_NEUE_HANDELSDEF);
		super(E2_ResourceIcon.get_RI("new.png"),ENUM_VALIDATION.HANDELSDEFINITIONEN_NEW);
		StationEK = stationEK;
		StationVK = stationVK;

		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Neuingabe einer Handelsdefinition").CTrans());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			TR__MASK_BasicModuleContainer oMaskContainer = new TR__MASK_BasicModuleContainer(null);
			
			ownMaskSaver oMaskSaver=new ownMaskSaver(oMaskContainer);
			E2_ComponentGroupHorizontal oButtonGroup = new E2_ComponentGroupHorizontal(0,
										new maskButtonSaveMask(oMaskContainer,oMaskSaver,null, null), 
										new ownCancelButton(oMaskContainer), E2_INSETS.I_0_2_10_2);
			oMaskContainer.get_oRowForButtons().add(oButtonGroup);
			
			E2_vCombinedComponentMAPs vComponentMAPs = oMaskContainer.get_vCombinedComponentMAPs();
			vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
			
			HD_Bt_CreateNewHandelsdef oThis = HD_Bt_CreateNewHandelsdef.this;
			
			oThis.FillFHandelsDef_ToMask(vComponentMAPs.get(0), oThis.StationEK, oThis.StationVK);
			
			oMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Neue Handelsdefinition erfassen ...."));
		}
	}
	
	
	
	/**
	 * Methode um felder einer eingabemaske zu fuellen mit den passenden gegebenheiten
	 * @author martin
	 *
	 */
	private void FillFHandelsDef_ToMask(E2_ComponentMAP  oMAP_Mask, HD_Station stationEK, HD_Station stationVK) throws myException
	{
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$QUELLE_IST_MANDANT).set_cActualMaskValue(stationEK.get_HDMASK_IST_MANDANT());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR).set_cActualMaskValue(stationEK.get_HDMASK_ID_LAND_JUR());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO).set_cActualMaskValue(stationEK.get_HDMASK_ID_LAND_GEO());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_RC_QUELLE).set_cActualMaskValue(stationEK.get_HDMASK_IST_SORTE_RC());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE).set_cActualMaskValue(stationEK.get_HDMASK_IST_PRODUKT());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_EOW_QUELLE).set_cActualMaskValue(stationEK.get_HDMASK_IST_EOW());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE).set_cActualMaskValue(stationEK.get_HDMASK_IST_DIENSTLEISTUNG());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE).set_cActualMaskValue(stationEK.get_HDMASK_IST_UST_TEILNEHMER());
		
		//tp-verantwortung wird der ek-seite zugeschlagen
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$TP_VERANTWORTUNG).set_cActualMaskValue(stationEK.get_HDMASK_TP_VERANTWORTUNG());

		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$ZIEL_IST_MANDANT).set_cActualMaskValue(stationVK.get_HDMASK_IST_MANDANT());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).set_cActualMaskValue(stationVK.get_HDMASK_ID_LAND_JUR());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$ID_LAND_ZIEL_GEO).set_cActualMaskValue(stationVK.get_HDMASK_ID_LAND_GEO());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_RC_ZIEL).set_cActualMaskValue(stationVK.get_HDMASK_IST_SORTE_RC());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL).set_cActualMaskValue(stationVK.get_HDMASK_IST_PRODUKT());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_EOW_ZIEL).set_cActualMaskValue(stationVK.get_HDMASK_IST_EOW());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL).set_cActualMaskValue(stationVK.get_HDMASK_IST_DIENSTLEISTUNG());
		oMAP_Mask.get__DBComp(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL).set_cActualMaskValue(stationVK.get_HDMASK_IST_UST_TEILNEHMER());
		
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
