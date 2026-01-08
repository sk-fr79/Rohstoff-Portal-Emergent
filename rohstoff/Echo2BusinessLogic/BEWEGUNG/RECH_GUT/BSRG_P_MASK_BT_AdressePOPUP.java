package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class BSRG_P_MASK_BT_AdressePOPUP extends MyE2_Button
{

	//button, der aus einer kontrakt-position !. die lieferanten-Adresse anzeigt und auch oeffnet
	public BSRG_P_MASK_BT_AdressePOPUP() throws myException
	{
		super("--");
		this.add_oActionAgent(new ownActionAgent());
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
				BSRG_P_MASK_BT_AdressePOPUP oButton = (BSRG_P_MASK_BT_AdressePOPUP)bibE2.get_LAST_ACTIONEVENT().getSource();
				
				SQLResultMAP oResultMAP = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
				
				if (oResultMAP == null)           // bei neueingabe ist der button ohne sinn
					return;
				
				String cID_VPOS_RG = oResultMAP.get_UnFormatedValue("ID_VPOS_RG");
				
				//zuerst nachsehen, ob die Position eine ID_ADRESSE enthaelt
				//String cID_ADRESSE = bibALL.null2leer(oResultMAP.get_UnFormatedValue("ID_ADRESSE"));
				
				String cID_ADRESSE = bibALL.ReplaceTeilString(bibALL.null2leer(oButton.EXT().get_oComponentMAP().get_cActualDBValueFormated("ID_ADRESSE")),".","");
				if (bibALL.isEmpty(cID_ADRESSE))
				{
					//dann ist es keine freie position und die adresse muss ueber den kopf ermittelt werden
					cID_ADRESSE  = new RECORD_VPOS_RG(cID_VPOS_RG).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_ID_ADRESSE_cUF();
				}
				
				if (bibALL.isLong(cID_ADRESSE))
				{
				
					if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM " +
							bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+
							" AND ID_ADRESSE="+cID_ADRESSE).equals("1"))
					{
						
						FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
				
						MyE2_MessageVector vRueck = oButton.valid_IDValidation(bibALL.get_Vector(cID_ADRESSE));
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
						vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,cID_ADRESSE);
						oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
					}
				}
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("BSRG_P_MASK_BUTTON_AdressePOPUP_BUTTON:ownActionAgent:Exception: "+ex.getLocalizedMessage(),false),false);
			}
		}
		
	}
	
	
	
	
}
