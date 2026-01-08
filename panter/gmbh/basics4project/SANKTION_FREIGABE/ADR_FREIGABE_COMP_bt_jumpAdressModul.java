package panter.gmbh.basics4project.SANKTION_FREIGABE;

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
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class ADR_FREIGABE_COMP_bt_jumpAdressModul extends E2_Button implements IF_RB_Component {


	public ADR_FREIGABE_COMP_bt_jumpAdressModul() throws myException {
		super();
		this._t("Adresse öffnen")._standard_text_button()._al_center();
		this._ttt(S.ms("Adresse öffnen").CTrans());
		this._aaa(new ownActionAgent());
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			try
			{
				ADR_FREIGABE_COMP_bt_jumpAdressModul oButton = (ADR_FREIGABE_COMP_bt_jumpAdressModul)bibE2.get_LAST_ACTIONEVENT().getSource();

				String id_main_adresse = oButton.EXT().rb_get_belongs_to().getRbDataObjectActual().rec20().getUfs(SANKTION_PRUEFUNG.id_adresse);

				if (S.isFull(id_main_adresse))
				{
					FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();

					MyE2_MessageVector vRueck = oButton.valid_IDValidation(new VEK<String>()._a(id_main_adresse));
					String cSTATUS = null;
					String cInfo = null;

					oMask.get_oRowForButtons().removeAll();

					if (vRueck.size() != 0)
					{
						cSTATUS = E2_ComponentMAP.STATUS_VIEW;
						cInfo = "Adresse prüfen";
					}
					else
					{
						cSTATUS = E2_ComponentMAP.STATUS_EDIT;
						cInfo = "Adresse bearbeiten";
						oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
					}
					oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
					
					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
					
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,id_main_adresse);
					oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));

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
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(ex.getLocalizedMessage(),false),false);
			}
		}
	}
}