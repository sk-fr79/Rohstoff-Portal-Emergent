package rohstoff.utils;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
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
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class AdressPopUP_FOR_DB_SEARCH_Adresse extends MyE2_Button
{

	public AdressPopUP_FOR_DB_SEARCH_Adresse() throws myException
	{
		super(E2_ResourceIcon.get_RI("edit_mini.png"),true);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EDIT_ADRESSE_AUS_FUHRE"));
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
	}
	
	public AdressPopUP_FOR_DB_SEARCH_Adresse(String cValidationKenner) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit_mini.png"),true);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(cValidationKenner));
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			try
			{
				/*
				 * der rufende button ist der button, der den event ausloest
				 */
				MyE2_Button oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
				
				// das vater-element ist ein DB_SearchAdress - komponente
				if (oButton.getParent() instanceof DB_SEARCH_Adress)
				{
					DB_SEARCH_Adress oSearchAdress = (DB_SEARCH_Adress)oButton.getParent();
				
					// adress-ID beschaffen aus dem such-ergebnis-feld
					String cID_ADRESS = bibALL.ReplaceTeilString(oSearchAdress.get_cActualMaskValue(),".","");
					
					if (bibALL.isLong(cID_ADRESS))
					{
					
						if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM " +
								bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+
								" AND ID_ADRESSE="+cID_ADRESS).equals("1"))
						{
							
							FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
					
							MyE2_MessageVector vRueck = oButton.valid_IDValidation(bibALL.get_Vector(cID_ADRESS));
							String cSTATUS = null;
							String cInfo = null;
							
							oMask.get_oRowForButtons().removeAll();
							
							if (vRueck.size() != 0)
							{
								cSTATUS = E2_ComponentMAP.STATUS_VIEW;
								cInfo = "Adresse prüfen ";
							}
							else
							{
								cSTATUS = E2_ComponentMAP.STATUS_EDIT;
								cInfo = "Adresse bearbeiten ...";
								oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
							}
							oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
							
							E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
							vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,cID_ADRESS);
							oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst eine Adresse definieren !"));
					}
				}
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FU_MASK_DB_SEARCH_AdressePOPUP_BUTTON:ownActionAgent:myException: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FU_MASK_DB_SEARCH_AdressePOPUP_BUTTON:ownActionAgent:Exception: "+ex.getLocalizedMessage()));
			}
		}
		
	}
	
	
	
	
}
