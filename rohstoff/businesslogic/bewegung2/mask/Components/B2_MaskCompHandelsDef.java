/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 20.05.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_btOpenMaskOldStyle;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonCancelMask;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_BasicModuleContainer;

/**
 * @author martin
 * @date 20.05.2020
 *
 */
public class B2_MaskCompHandelsDef extends E2_Grid implements 	 IF_RB_Component_Savable {

	private MyE2EXT__Component  EXT = new MyE2EXT__Component(this) ;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private FU_MASK_BtOpenSteuerregel      	btEditHandelsdef = new FU_MASK_BtOpenSteuerregel();
	private E2_Button      					btEraseHandelsdef = new E2_Button();
	private RB_TextField   					tfHandelsdef = new RB_TextField()._w(40)._fsa(-2);
	
	/**
	 * @author martin
	 * @date 20.05.2020
	 *
	 */
	public B2_MaskCompHandelsDef() {
		this._setSize(30,30)._bo_green();
		this._a(new RB_lab()._fsa(-2)._i()._t(S.ms("Handel")), 	new RB_gld()._span(2)._center_mid())
			._a(tfHandelsdef, 									new RB_gld()._span(2)._center_mid())
			._a(btEditHandelsdef,new RB_gld()._center_mid())._a(btEraseHandelsdef,new RB_gld()._center_mid())
			;
		
		try {
			tfHandelsdef.set_bEnabled_For_Edit(false);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		btEraseHandelsdef._image(E2_ResourceIcon.get_RI("eraser.png"),true)._aaa(()-> {
			tfHandelsdef.setText("");
		});
		
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.tfHandelsdef.setText("");
		} else {
			this.tfHandelsdef.setText(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.tfHandelsdef.rb_set_db_value_manual(valueFormated);
	}



	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.tfHandelsdef.rb_readValue_4_dataobject();
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.EXT.get_RB_K();
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.EXT.set_RB_K(key);
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;	
	}

	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.EXT = oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.EXT;
	}
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		boolean  enabled_super = (!this.EXT.get_bDisabledFromBasic())&&bEnabled;
		
		this.setEnabled(enabled_super);
		this.tfHandelsdef.set_bEnabled_For_Edit(false);
		this.btEditHandelsdef.set_bEnabled_For_Edit(enabled_super);
		this.btEraseHandelsdef.set_bEnabled_For_Edit(enabled_super);
		
	}

	
	private class FU_MASK_BtOpenSteuerregel extends E2_btOpenMaskOldStyle {


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
			MyLong l= new MyLong(tfHandelsdef.getText());
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

}
