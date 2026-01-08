/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 27.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_btOpenMaskOldStyle;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonCancelMask;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_BasicModuleContainer;

/**
 * @author martin
 * @date 27.11.2018
 *
 */
public class FUO_MASK_BtOpenSteuerregel extends E2_btOpenMaskOldStyle {


	@Override
	public E2_BasicModuleContainer_MASK createMask(Long id,boolean edit) throws myException {
		TR__MASK_BasicModuleContainer mask = new TR__MASK_BasicModuleContainer(null); 			
		
		ownMaskSaver oMaskSaver=new ownMaskSaver(mask);
		E2_ComponentGroupHorizontal oButtonGroup = new E2_ComponentGroupHorizontal(0,
									new maskButtonSaveMask(mask,oMaskSaver,null, null), 
									new ownCancelButton(mask), E2_INSETS.I_0_2_10_2);
		mask.get_oRowForButtons().add(oButtonGroup);
		
		E2_vCombinedComponentMAPs vComponentMAPs = mask.get_vCombinedComponentMAPs();
		vComponentMAPs.MAKRO_Fill_Build_Set_MASK(this.getIsEditAllowed()?E2_ComponentMAP.STATUS_EDIT:E2_ComponentMAP.STATUS_VIEW,this.getIdToOpen().toString());
		return mask;
	}

	@Override
	public Long getIdToOpen() throws myException {
		MyE2IF__DB_Component c = this.EXT().get_oComponentMAP().get__DBComp(VPOS_TPA_FUHRE_ORT.id_handelsdef.fn());
		MyLong l= new MyLong(c.get_cActualDBValueFormated());
		if (l.isOK()) {
			return l.get_oLong();
		} else {
			return null;
		}
	}

	private class ownMaskSaver extends E2_SaveMASK {
		public ownMaskSaver(E2_BasicModuleContainer_MASK maskContainer) {
			super(maskContainer, null);
		}
		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus)	{
			return true;
		}
		public void actionAfterSaveMask() throws myException {}
	}
	
	
	private class ownCancelButton extends maskButtonCancelMask {
		public ownCancelButton(E2_BasicModuleContainer_MASK maskContainer)	{
			super(maskContainer);
		}
		public boolean doActionAfterCancelMask() { return true;	}
	}


	
	@Override
	public MyE2_String getMaskTitel() {
		String id= "?";
		
		try {
			id = this.getIdToOpen().toString();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return S.ms("Handelsdefinition "+id);
	}

	@Override
	public boolean getIsEditAllowed() {
		return ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT.getValidator().isValid(FUO_MASK_BtOpenSteuerregel.this).isOK();
	}

	@Override
	public String getNameOfDataset() {
		return "Steuer-Regel / Handelsdefinition";
	}

}
