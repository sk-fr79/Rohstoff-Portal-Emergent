package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;

public class RB_actionStandardSave extends XX_ActionAgent {

	private RB_ModuleContainerMASK  PopupContainer = null;
	
	
	public RB_actionStandardSave(RB_ModuleContainerMASK popupContainer) {
		super();
		PopupContainer = popupContainer;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		if (PopupContainer==null) {
			throw new myException(this,"MaskContainer/RB_MASK_VECTOR: NOT SET !!!");
		} else {
			MyE2_MessageVector  oMV =PopupContainer.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
			bibMSG.add_MESSAGE(oMV);
		}

	}

}
