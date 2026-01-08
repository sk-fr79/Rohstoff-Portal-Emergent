package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;

public class RB_actionStandardClosePopup extends XX_ActionAgent {

	private RB_ModuleContainerMASK  PopupContainer = null;
	
	
	public RB_actionStandardClosePopup(RB_ModuleContainerMASK popupContainer) {
		super();
		PopupContainer = popupContainer;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		if (PopupContainer==null) {
			throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
		} else {
			this.PopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

	}

}
