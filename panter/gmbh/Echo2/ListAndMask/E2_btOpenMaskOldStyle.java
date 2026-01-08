/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 27.11.2018
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.11.2018
 * button, um allegemeine alte maskentypen (nicht RB_XXX) zu oeffnen
 */
public abstract class E2_btOpenMaskOldStyle extends E2_Button {

	/*
	 * maskenobject erzeugen und fuellen 
	 */
	public abstract E2_BasicModuleContainer_MASK 	createMask(Long id, boolean edit /*when false, dann view*/) throws myException;
	public abstract Long 							getIdToOpen() throws myException;
	public abstract boolean  						getIsEditAllowed();
	public abstract MyE2_String 					getMaskTitel();
	public abstract String  						getNameOfDataset();         //bsp: "Adresse"
	
	public int getMaskWidth() {
		return 1000;
	}
	
	public int getMaskHeight() {
		return 800;
	}
	
	
	public E2_btOpenMaskOldStyle() {
		super();
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)._aaa(new ownActionAgent());
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Long id = getIdToOpen();
			if (id==null) {
				bibMSG.MV()._addAlarm(S.ms("Die notwendige ID der Tabelle "+getNameOfDataset()+" konnte nicht ermittelt werden"));
			} else {
				E2_BasicModuleContainer_MASK mask = createMask(id, getIsEditAllowed());
				mask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(getMaskWidth()),new Extent(getMaskHeight()), getMaskTitel());
			}
		}
		
	}

	
	//komplettes beispiel:
	/*
	  
	 public class FU_MASK_BtOpenSteuerregel extends E2_btOpenMaskOldStyle {


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
			MyE2IF__DB_Component c = this.EXT().get_oComponentMAP().get__DBComp(VPOS_TPA_FUHRE.id_handelsdef.fn());
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
			return ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT.getValidator().isValid(FU_MASK_BtOpenSteuerregel.this).isOK();
		}
	
		@Override
		public String getNameOfDataset() {
			return "Steuer-Regel / Handelsdefinition";
		}
	
	}
 
	 
	 */
	
	
	
	
}
