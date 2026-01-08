package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class FUS_BT_OpenAdressMask extends MyE2_Button
{
	private FUS_SearchAdresse   oSearchAdresseIBelongTo = null;
	
	public FUS_BT_OpenAdressMask(FUS_SearchAdresse   SearchAdresseIBelongTo) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit_mini.png"),true);
		this.add_oActionAgent(new ownActionAgent());
		
		this.oSearchAdresseIBelongTo = SearchAdresseIBelongTo;
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EDIT_ADRESSE_AUS_SCHNELLERFASSUNG"));
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			try
			{
				
				RECORD_ADRESSE  recAdresse = FUS_BT_OpenAdressMask.this.oSearchAdresseIBelongTo.get_ActualRecHauptAdresse();
				
				if (recAdresse == null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss eine korrekt erfasste Firma vorhanden sein !!"));
					return;
				}
						
				FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
					
				oMask.get_oRowForButtons().removeAll();
							
				String cSTATUS = E2_ComponentMAP.STATUS_EDIT;
				String cInfo = "Adresse bearbeiten ...";
				oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
				oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
							
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,recAdresse.get_ID_ADRESSE_cUF());
				oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FUS_BT_OpenAdressMask:ownActionAgent:myException: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FUS_BT_OpenAdressMask:ownActionAgent:Exception: "+ex.getLocalizedMessage()));
			}
		}
		
	}

}
