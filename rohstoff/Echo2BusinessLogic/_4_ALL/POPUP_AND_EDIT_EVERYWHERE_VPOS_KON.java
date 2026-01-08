package rohstoff.Echo2BusinessLogic._4_ALL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContaier_Refreshable;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK__ModulContainer;

public class POPUP_AND_EDIT_EVERYWHERE_VPOS_KON extends MyE2_Button 
{
	private String   			ID_VPOS_UNFORMATED = null;
	private XX_ActionAgent  	ActionAfterSaveMask = null;
	private boolean         	bShowInViewModeWhenEditForbidden = false;
	private RECORD_VPOS_KON  	oRecord_VPosKon = null;
	
	/*
	 * der start-container wird beim klicken gesucht und, falls gefunden, hier gespeichert,
	 * falls der schalter: autoRefreshContainer = true ist, wird geprueft, ob der Container
	 * das interface IF_BasicModuleContaier_Refreshable erfuellt. Wenn beide true,
	 * new POPUP_AND_EDIT_EVERYWHERE_VPOS_KON(this.get_ID_VPOS_KON_cUF(),
																	null,
																	new MyE2_String("ID_VPOS_KON: ",false,this.get_ID_VPOS_KON_cUF(),false),
																	null,
																	true,true)dann wird nach dem speichern Refresh() ausgefuehrt
	 * 
	 */
		
		
	private E2_BasicModuleContainer   oStartContainer = null;
	private boolean 	 			  bAutoRefreshContainer = false;

	
	

	/**
	 * 
	 * @param cID_VPOS_UNFORMATED
	 * @param cBeschriftung
	 * @throws myException
	 */
	public POPUP_AND_EDIT_EVERYWHERE_VPOS_KON(String 			cID_VPOS_UNFORMATED, 
											  MyString          cBeschriftung
											  ) throws myException
	{
		super();
		
		MyString cButtonText = cBeschriftung==null?new MyE2_String(cID_VPOS_UNFORMATED,false):cBeschriftung;
		this.set_Text(cButtonText);
		this.setStyle(MyE2_Button.StyleTextButton());
		
		this.bAutoRefreshContainer = true;
		
		this.oRecord_VPosKon = new RECORD_VPOS_KON(cID_VPOS_UNFORMATED);
		this.ID_VPOS_UNFORMATED = cID_VPOS_UNFORMATED;
		this.bShowInViewModeWhenEditForbidden = true;
		
		this.add_oActionAgent(new ownActionAgentEditPosition());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITEN_KONTRAKT_POSITION"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Kontraktposition wurde bereits gelöscht !")));
		this.setToolTipText(new MyE2_String("Kontraktposition bearbeiten ").CTrans());
		
	}

	
	
	/**
	 * 
	 * @param cID_VPOS_UNFORMATED
	 * @param oIconAmStart
	 * @param cBeschriftung
	 * @param oActionAgentAfterSaveMask
	 * @param ShowInViewModeWhenEditForbidden
	 * @param autoRefreshContainer
	 * @throws myException
	 */
	public POPUP_AND_EDIT_EVERYWHERE_VPOS_KON(String 			cID_VPOS_UNFORMATED, 
											  E2_ResourceIcon   oIconAmStart, 
											  MyString          cBeschriftung,
											  XX_ActionAgent    oActionAgentAfterSaveMask,
											  boolean 			ShowInViewModeWhenEditForbidden, 
											  boolean 			autoRefreshContainer
											  ) throws myException
	{
		super();
		
		if (oIconAmStart != null)
		{
			this.__setImages(oIconAmStart, E2_ResourceIcon.get_RI("leer.png"));
			this.setStyle(MyE2_Button.StyleImageButton());
		}
		else
		{
			MyString cButtonText = cBeschriftung==null?new MyE2_String(cID_VPOS_UNFORMATED,false):cBeschriftung;
			this.set_Text(cButtonText);
			this.setStyle(MyE2_Button.StyleTextButton());
		}
		
		this.bAutoRefreshContainer = autoRefreshContainer;
		
		this.oRecord_VPosKon = new RECORD_VPOS_KON(cID_VPOS_UNFORMATED);
		
		this.ID_VPOS_UNFORMATED = cID_VPOS_UNFORMATED;
		this.ActionAfterSaveMask = oActionAgentAfterSaveMask;
		this.bShowInViewModeWhenEditForbidden = ShowInViewModeWhenEditForbidden;
		
		this.add_oActionAgent(new ownActionAgentEditPosition());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITEN_KONTRAKT_POSITION"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Kontraktposition wurde bereits gelöscht !")));
		this.setToolTipText(new MyE2_String("Kontraktposition bearbeiten ").CTrans());
		
	}
	
	

	
	private class ownActionAgentEditPosition extends XX_ActionAgent 
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			POPUP_AND_EDIT_EVERYWHERE_VPOS_KON oThis = POPUP_AND_EDIT_EVERYWHERE_VPOS_KON.this;
			
			oThis.oStartContainer = new E2_RecursiveSearchParent_BasicModuleContainer(oThis).get_First_FoundContainer(); 
			
			
			// integritaet pruefen und fuellen und uebergeben an container
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(oThis.ID_VPOS_UNFORMATED));
			
			if (bibMSG.get_bHasAlarms() &&  ! oThis.bShowInViewModeWhenEditForbidden)
			{
				return;
			}
			
			BSK_P_MASK__ModulContainer 	oMaskPositions = new BSK_P_MASK__ModulContainer(new BS__SETTING(oThis.oRecord_VPosKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF()));

			oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.oRecord_VPosKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_VKOPF_KON_cUF());
			
			E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
			
			MyE2_String 				cMask_Title = new MyE2_String("EK-Kontrakt-Position bearbeiten");

			if (oThis.oRecord_VPosKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_VK_KONTRAKT))
				cMask_Title =  new MyE2_String("VK-Kontrakt-Position bearbeiten");

			if (bibMSG.get_bHasAlarms() || (oThis.bAutoRefreshContainer && !(oThis.oStartContainer instanceof IF_BasicModuleContaier_Refreshable)  && oThis.oStartContainer.get_bMustBeRefreshed()))
			{
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
				
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,oThis.ID_VPOS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,cMask_Title);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzeige Kontrakt-Position (Bearbeiten ist nicht möglich): "+oThis.ID_VPOS_UNFORMATED,true),true);
			}
			else
			{
				oMaskPositions.get_oRowForButtons().removeAll();
				maskButtonSaveMask oMaskButtonSaveMask = new maskButtonSaveMask(oMaskPositions,
																				new ownSaveMASK(oMaskPositions),
																				null, 
																				new BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise(oMaskPositions));
				
				oMaskButtonSaveMask.set_oZusatzActionAfterSave(new ownRefreshAction());
				
				//die (nicht immer ausgefuehrte) refreshaction ausfuehren
				//oMaskButtonSaveMask.add_oActionAgent(new ownRefreshAction());
				
				oMaskPositions.get_oRowForButtons().add(oMaskButtonSaveMask);
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
				
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oThis.ID_VPOS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,cMask_Title);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Bearbeite Kontrakt-Position:  "+oThis.ID_VPOS_UNFORMATED,true),true);
			}
		}
	}

	
	private class ownRefreshAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			POPUP_AND_EDIT_EVERYWHERE_VPOS_KON oThis = POPUP_AND_EDIT_EVERYWHERE_VPOS_KON.this;
			
			if (oThis.bAutoRefreshContainer && oThis.oStartContainer != null && oThis.oStartContainer instanceof IF_BasicModuleContaier_Refreshable)
			{
				((IF_BasicModuleContaier_Refreshable)oThis.oStartContainer).Refresh(null);
			}
			
		}
	}
	
	
	
	/*
	 * eigener maskSaver, damit die tochterkomponente gleich refresh werden kann 
	 */
	private class ownSaveMASK extends E2_SaveMASK
	{

		public ownSaveMASK(E2_BasicModuleContainer_MASK maskContainer)
		{
			super(maskContainer, null);
		}

		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus)
		{
			return true;
		}

		public void actionAfterSaveMask() throws myException
		{
			POPUP_AND_EDIT_EVERYWHERE_VPOS_KON oThis = POPUP_AND_EDIT_EVERYWHERE_VPOS_KON.this;
			if (oThis.ActionAfterSaveMask != null)
				oThis.ActionAfterSaveMask.ExecuteAgentCode(new ExecINFO_OnlyCode());
			
		}
		
	}

	
}
