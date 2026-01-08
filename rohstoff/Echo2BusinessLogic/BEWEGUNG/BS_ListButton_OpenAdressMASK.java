package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class BS_ListButton_OpenAdressMASK extends MyE2_DB_Button
{

	private FS_ModulContainer_MASK	   	oFS_MaskContainer = null;
	
	
	public BS_ListButton_OpenAdressMASK(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		this.add_oActionAgent(new ownActionAgent());
		
		// den validator fuer das editieren nicht im global-sector sondern im id-sektor einbauen,
		// damit zwischen anschauen und editieren unterschieden werden kann -- global-validators werden automatisch ausgewertet
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			/*
			 * der rufende button ist der button, der den event ausloest
			 */
			MyE2_DB_Button oButton = (MyE2_DB_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			BS_ListButton_OpenAdressMASK	oThis = BS_ListButton_OpenAdressMASK.this;
			
			// zuerst die adress-id beschaffen
			SQLResultMAP oResult = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			if (oResult != null)
			{
				String cFieldName = oThis.EXT_DB().get_oSQLField().get_cFieldLabel();
				String cID_ADRESSE = oResult.get_UnFormatedValue(cFieldName);
				
				if (oThis.oFS_MaskContainer == null)
					oThis.oFS_MaskContainer = new FS_ModulContainer_MASK();
				
				MyE2_MessageVector vRueck = oButton.valid_IDValidation(bibALL.get_Vector(cID_ADRESSE));
				String cSTATUS = null;
				String cInfo = null;
				
				oThis.oFS_MaskContainer.get_oRowForButtons().removeAll();
				
				if (vRueck.size() != 0)
				{
					cSTATUS = E2_ComponentMAP.STATUS_VIEW;
					cInfo = "Adresse prüfen ";
				}
				else
				{
					cSTATUS = E2_ComponentMAP.STATUS_EDIT;
					cInfo = "Adresse bearbeiten ...";
					oThis.oFS_MaskContainer.get_oRowForButtons().add(new maskButtonSaveMask(oThis.oFS_MaskContainer,new E2_SaveMaskStandard(oThis.oFS_MaskContainer, null),null, null));
				}
				
				oThis.oFS_MaskContainer.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oThis.oFS_MaskContainer));
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oThis.oFS_MaskContainer.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,cID_ADRESSE);
				oThis.oFS_MaskContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
			}
			
		}
		
	}
	
	
	
	
}
