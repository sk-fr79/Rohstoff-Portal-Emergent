/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.indep.exceptions.myException;

public class BT_LIST_Edit_BAMF extends MyE2_Button
{
	
	private BAMF_MASK_ModulContainer 	oBAMF_MaskContainer = null;
	
	public BT_LIST_Edit_BAMF(	E2_NavigationList 			oList,
								BAMF_MASK_ModulContainer 	oMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png"), true);
		
		this.oBAMF_MaskContainer = oMaskContainer;
		
		this.add_oActionAgent(new ActionAgentEDIT(oList,oMaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FBAM_LIST,"EDIT_FBAM"));
	}
	
	private class ActionAgentEDIT extends ButtonActionAgentEDIT
	{
		
		private MyE2_Button oButtonPrintBAM = 		null;
		private MyE2_Button oButtonPrintWEIGER = 	null;
		private MyE2_Button oButtonUnlockBAM = 		null;
		private MyE2_Button oButtonUnlockWEIGER = 	null;
		
		//2012-10-16: neuer button zum direkten Sprung in die Fuhre aus der BAM-Maske
		private BAMF_MASK_OpenFuhre   oButtonOpenFuhreEdit = null;
		private BAMF_MASK_OpenFuhre   oButtonOpenFuhreView = null;

		
		
		
		public ActionAgentEDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten Transport-Positions-BAM"), onavigationList, omaskContainer, oownButton, null, null);
			
			//2012-10-16: neue buttons
			this.oButtonOpenFuhreEdit = new BAMF_MASK_OpenFuhre((BAMF_MASK_ModulContainer)this.get_oMaskContainer(), true);
			this.oButtonOpenFuhreView = new BAMF_MASK_OpenFuhre((BAMF_MASK_ModulContainer)this.get_oMaskContainer(), false);

		}

		/*
		 * ueberschreiben der relevanten methoden
		 * !!!BUG: wird nur beim ersten aufruf der maske aktualisiert aktualisiert
		 */
		public boolean do_prepareMaskForActualID() throws myException
		{
			
			BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW 	recFuhreAusView = new BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW( new RECORD_FBAM(this.get_cActualID_Unformated()));

			//den entsperr-button disablen, wenn die zugrundliegende fuhre abrechnungspositionen besitzt
			this.oButtonUnlockBAM.set_bEnabled_For_Edit(recFuhreAusView.get_b_FBAM_CAN_BE_SAVED());
			this.oButtonUnlockWEIGER.set_bEnabled_For_Edit(recFuhreAusView.get_b_FBAM_CAN_BE_SAVED());

//			//dem fuhren-button die ID_FBAM uebergeben
//			this.oButtonOpenFuhreEdit.set_cID_FBAM_uF(this.get_cActualID_Unformated());
//			this.oButtonOpenFuhreView.set_cID_FBAM_uF(this.get_cActualID_Unformated());
			
			return true;
		}


		public Component build_ComponentWithMaskButtons() throws myException
		{
			/*
			 * zuerst die buttons der basis-klasse einbauen
			 */
			E2_ComponentGroupHorizontal oCompGroup= (E2_ComponentGroupHorizontal)super.build_ComponentWithMaskButtons();
			
			this.oButtonPrintBAM = new BT_MASK_Mail_Print_BAMF((BAMF_MASK_ModulContainer)this.get_oMaskContainer(),this.get_oNavigationList(),false);
			this.oButtonPrintWEIGER = new BT_MASK_Mail_Print_WEIGER((BAMF_MASK_ModulContainer)this.get_oMaskContainer(),this.get_oNavigationList(),false);
			this.oButtonUnlockBAM = new  BT_MASK_Unlock_BAMF((BAMF_MASK_ModulContainer)this.get_oMaskContainer());
			this.oButtonUnlockWEIGER = new  BT_MASK_Unlock_WEIGER((BAMF_MASK_ModulContainer)this.get_oMaskContainer());
			
			
			oCompGroup.add(oButtonPrintBAM, E2_INSETS.I_10_2_10_2);
			oCompGroup.add(oButtonPrintWEIGER, E2_INSETS.I_10_2_10_2);
			oCompGroup.add(oButtonUnlockBAM, E2_INSETS.I_10_2_10_2);
			oCompGroup.add(oButtonUnlockWEIGER, E2_INSETS.I_10_2_10_2);
			
			//2012-10-16: button zum oeffnen der fuhre einbinden
			oCompGroup.add(this.oButtonOpenFuhreEdit, E2_INSETS.I_10_2_10_2);
			oCompGroup.add(this.oButtonOpenFuhreView, E2_INSETS.I_0_2_10_2);
			
			//nachsehen, ob die fuhre vorgangspositionen hat, wenn ja, dann den save-/Unlock-button sperren
			this.oButtonUnlockBAM.add_GlobalValidator(new ownGlobalValidatorForButtons());
			this.get_oButtonMaskSave().get_vGlobalValidators().removeAllElements();
			this.get_oButtonMaskSave().add_GlobalValidator(new ownGlobalValidatorForButtons());
			
			return oCompGroup;
		}
		
		
		/**
		 * 
		 * @author martin
		 * globaler validierer, der prueft, ob eine fuhre bereits abrechnungspositionen hat, und danach die buttons speichern und entsperren deaktiviert
		 */
		private class ownGlobalValidatorForButtons extends XX_ActionValidator
		{

			@Override
			public MyE2_MessageVector isValid(Component componentWhichIsValidated) 	throws myException
			{
				
				ActionAgentEDIT oThis = ActionAgentEDIT.this; 
				
				BAMF_MASK_ModulContainer 	oBAMF_MaskContainer = 	BT_LIST_Edit_BAMF.this.oBAMF_MaskContainer;
				
				if (oBAMF_MaskContainer.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP()==null)
				{
					throw new myException(this,"Button only allowed in EditStatus !!");
				}
				
				BAMF_MASK_ComponentMAP oBAMF_MASK_ComponentMAP = (BAMF_MASK_ComponentMAP)oThis.get_oMaskContainer().get_ComponentMAP_FROM_TABLE("JT_FBAM");
				
				BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW 	recFuhreAusView = oBAMF_MASK_ComponentMAP.get_recFuhreAusView();

				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				if (!recFuhreAusView.get_b_FBAM_CAN_BE_SAVED())
				{
					oMV.add(new MyE2_Alarm_Message("Die zugrundeliegende Fuhre hat bereits Abrechnungspositionen (oder ist gelöscht/storniert)!"));
				}
				
				return oMV; 
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated) 	throws myException
			{
				return null;
			}
			
		}

		
	}
	
	
	
	
	
}