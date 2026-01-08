package panter.gmbh.Echo2.RB.CONTROLLERS;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class RB_bt_maskClose extends MyE2_Button {

	private RB_ModuleContainerMASK mask_Container = null;
	
	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer) {
		super(E2_ResourceIcon.get_RI("cancel.png"));
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer, ImageReference oImg, boolean bAutoDisabled, MyString cToolTips, XX_ActionAgent oAgent) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg, bAutoDisabled, cToolTips, oAgent);
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, boolean bAutoDisabled) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg, bAutoDisabled);
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, ImageReference oimgDisabled, MyString cToolTips, XX_ActionAgent oAgent) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg, oimgDisabled, cToolTips, oAgent);
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, ImageReference oimgDisabled) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg, oimgDisabled);
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg, MyString cToolTips, XX_ActionAgent oAgent) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg, cToolTips, oAgent);
		this._init(maskContainer);
	}

	public RB_bt_maskClose(RB_ModuleContainerMASK maskContainer,ImageReference oImg) {
		super(oImg==null?E2_ResourceIcon.get_RI("cancel.png"):oImg);
		this._init(maskContainer);
	}

	private void _init(RB_ModuleContainerMASK maskContainer) {
		this.mask_Container = maskContainer;
		this.add_oActionAgent(new ownActionSaveMask());
	}
	
	private class ownActionSaveMask extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (RB_bt_maskClose.this.mask_Container==null || RB_bt_maskClose.this.mask_Container.rb_FirstAndOnlyComponentMapCollector()==null) {
				throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
			} else {
				RB_bt_maskClose.this.mask_Container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
	}
	
	
	
}
