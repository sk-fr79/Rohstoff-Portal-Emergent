package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_editSingular;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;


public class IP_EditPasswortAndHelpText extends RB_bt_editSingular {

	private String id_report_uf = null;
	
	
	
	public IP_EditPasswortAndHelpText(String p_id_report_uf) {
		super();
		this.id_report_uf = p_id_report_uf;
		this.set_text4MaskTitel(new MyE2_String("Bearbeite Reportangaben ..."));
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new IP_MASK_MaskModulContainer();
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4Edit()	throws myException {
		return new IP_MASK_DataObjectCollector(this.id_report_uf, MASK_STATUS.EDIT);
	}

	@Override
	public void define_Actions_4_saveButton(	RB_bt_editSingular btEditSingular,	
												RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,
												RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownSaveAction(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownCancelAction(maskPopup, true));
		
	}

	@Override
	public void define_Actions_4_CloseButton(	RB_bt_editSingular btEditSingular,	
												RB_bt_maskClose bt_Close, 
												RB_ModuleContainerMASK maskPopup)	throws myException {
		bt_Close.add_oActionAgent(new ownCancelAction(maskPopup, false));
	
	}



	private class ownSaveAction extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup  = null;
		public ownSaveAction(RB_ModuleContainerMASK p_maskPopup) {
			super();
			this.maskPopup = p_maskPopup;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.add_MESSAGE(this.maskPopup.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true));
			if (bibMSG.get_bIsOK()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Reportdefinition wurde gespeichert ...")));
			}
		}
	}

	private class ownCancelAction extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup  = null;
		private boolean save = true;
		public ownCancelAction(RB_ModuleContainerMASK p_maskPopup, boolean p_save) {
			super();
			this.maskPopup = p_maskPopup;
			this.save = p_save;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			this.maskPopup.CLOSE_AND_DESTROY_POPUPWINDOW(this.save);
			if (!this.save) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Bearbeitung der Reportdefinition wurde abgebrochen ...")));
			}
		}
	}

}
