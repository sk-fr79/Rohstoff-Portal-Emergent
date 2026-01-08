/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BT_MASK_Unlock_WEIGER extends MyE2_Button
{
	private BAMF_MASK_ModulContainer BAMF_ContainerMASK = null;

	public BT_MASK_Unlock_WEIGER(BAMF_MASK_ModulContainer oContainerMASK)
	{
		super(E2_ResourceIcon.get_RI("unlock_WM.png"));
		this.BAMF_ContainerMASK = oContainerMASK;
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oContainerMASK.get_MODUL_IDENTIFIER(),"UNLOCK_BAM"));
		this.add_oActionAgent(new ownActionUnlock(oContainerMASK));
		
		this.add_GlobalValidator(new ownValidator());

		this.setToolTipText(new MyE2_String("Weigerungsmeldungsrelevanter Maskenbereich entsperren ").CTrans());

	}
	
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			BT_MASK_Unlock_WEIGER oThis = BT_MASK_Unlock_WEIGER.this;
			BAMF_MASK_ComponentMAP     oMap = (BAMF_MASK_ComponentMAP)oThis.BAMF_ContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			
			if (oMap.get_oInternalSQLResultMAP()==null)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Entsperren bei Neueingabe ist nicht sinnvoll !"));
				return oMV;
			}

			BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW recFuhre = oMap.get_recFuhreAusView();

			//RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(""+oMap.get_oInternalSQLResultMAP().get_LActualDBValue("ID_VPOS_TPA_FUHRE", true));
			if (recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add(new MyE2_Alarm_Message("Die zugrundeliegende Fuhre wurde bereits storniert oder gelöscht !"));
				return oMV;
			}
			if (recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT) ||
				recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
			{
				oMV.add(new MyE2_Alarm_Message("Die zugrundeliegende Fuhre hat bereits Abrechnungspositionen !"));
				return oMV;
			}

			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)  	throws myException
		{
			return null;
		}
		
	}

	
	private class ownActionUnlock extends XX_ActionAgent
	{
		private BAMF_MASK_ModulContainer oMaskContainer = null;
		
		
		public ownActionUnlock(BAMF_MASK_ModulContainer maskcontainer)
		{
			super();
			this.oMaskContainer = maskcontainer;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				
				E2_ComponentMAP oMap = this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
				MyE2_DB_TextField otfWM_ZAEHLER_ENTSPERRUNG = (MyE2_DB_TextField)oMap.get("WM_ZAEHLER_ENTSPERRUNG");
				MyE2_DB_TextField otfWM_DATUM = (MyE2_DB_TextField)oMap.get("WM_DATUM");
				MyE2_DB_TextField otfWM_UHRZEIT = (MyE2_DB_TextField)oMap.get("WM_UHRZEIT");
				MyE2_DB_TextField otfWM_LETZTERDRUCK_DATUM = (MyE2_DB_TextField)oMap.get("WM_LETZTERDRUCK_DATUM");
				MyE2_DB_TextField otfWM_LETZTERDRUCK_UHRZEIT = (MyE2_DB_TextField)oMap.get("WM_LETZTERDRUCK_UHRZEIT");
				MyE2_DB_CheckBox otfWM_GESPERRT = (MyE2_DB_CheckBox)oMap.get("WM_GESPERRT");
				
				if (!otfWM_GESPERRT.isSelected())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Weigermeldung ist nicht gesperrt !"));
					return;
				}

				
				otfWM_GESPERRT.setSelected(false);
				if (S.isFull(otfWM_DATUM.get_cActualMaskValue())) {otfWM_LETZTERDRUCK_DATUM.set_cActualMaskValue(otfWM_DATUM.get_cActualMaskValue());}
				if (S.isFull(otfWM_UHRZEIT.get_cActualMaskValue())) {otfWM_LETZTERDRUCK_UHRZEIT.set_cActualMaskValue(otfWM_UHRZEIT.get_cActualMaskValue());}
				otfWM_DATUM.set_cActualMaskValue("");
				otfWM_UHRZEIT.set_cActualMaskValue("");

				int iCount=1;
				if (bibALL.isInteger(otfWM_ZAEHLER_ENTSPERRUNG.getText()))
				{
					iCount = (new Integer(otfWM_ZAEHLER_ENTSPERRUNG.getText()).intValue()+1);
				}
				otfWM_ZAEHLER_ENTSPERRUNG.set_cActualMaskValue(""+iCount);

				Save_BAMF oSave = new Save_BAMF(this.oMaskContainer,null);   // schreibt eigene warnungen
				if (oSave.get_bISOK())
				{
					// dann neu laden in den zustand edit (wie speichern und schliessen und wieder oeffnen
					this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oSave.get_cActualMaskID_Unformated());
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Weigermeldung ist wieder frei ..."));
				}

				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentUnlockWeigermeldung:Error at unlock: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentUnlockWeigermeldung:Unknown Error at unlock: "+ex.getLocalizedMessage()));
			}
			
			
		}
	}
}