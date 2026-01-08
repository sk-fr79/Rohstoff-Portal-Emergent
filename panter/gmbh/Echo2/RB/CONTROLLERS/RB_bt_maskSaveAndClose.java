package panter.gmbh.Echo2.RB.CONTROLLERS;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;

public class RB_bt_maskSaveAndClose extends MyE2_Button {

	
	private RB_ModuleContainerMASK mask_Container = null;
	
	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer) {
		super(E2_ResourceIcon.get_RI("save.png"));
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer, ImageReference oImg, boolean bAutoDisabled, MyString cToolTips) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg, bAutoDisabled, cToolTips,null);
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, boolean bAutoDisabled) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg, bAutoDisabled);
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, ImageReference oimgDisabled, MyString cToolTips) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg, oimgDisabled, cToolTips, null);
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, ImageReference oimgDisabled) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg, oimgDisabled);
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, MyString cToolTips) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg, cToolTips, null);
		this._init(maskContainer);
	}

	public RB_bt_maskSaveAndClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg) {
		super(oImg==null?E2_ResourceIcon.get_RI("save.png"):oImg);
		this._init(maskContainer);
	}


	
	
	
	private void _init(RB_ModuleContainerMASK maskContainer) {
		this.mask_Container = maskContainer;
//		this.add_oActionAgent(new ownActionSaveMask());
	}
	
//	private class ownActionSaveMask extends XX_ActionAgent {
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			if (RB_bt_maskSaveAndClose.this.mask_Container==null || RB_bt_maskSaveAndClose.this.mask_Container.rb_Mask_Container()==null) {
//				throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
//			} else {
//				MyE2_MessageVector  oMV = RB_bt_maskSaveAndClose.this.mask_Container.rb_Mask_Container().rb_COMPLETE_SAVE_CYCLE(true);
//				bibMSG.add_MESSAGE(oMV);
//			}
//		}
//		
//	}
//
//	private class ownActionCloseMask extends XX_ActionAgent {
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			if (bibMSG.get_bIsOK()) {
//				RB_bt_maskSaveAndClose.this.mask_Container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//			}
//		}
//		
//	}
//
	
	
	
	public RB_ModuleContainerMASK get_mask_Container() {
		return mask_Container;
	}
	
	
	
}
