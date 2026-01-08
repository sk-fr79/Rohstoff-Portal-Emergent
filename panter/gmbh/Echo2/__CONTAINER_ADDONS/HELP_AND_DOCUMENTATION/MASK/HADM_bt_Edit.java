package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_editSingular;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Searcher;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.indep.exceptions.myException;

public class HADM_bt_Edit extends RB_bt_editSingular implements  IF_AR_Component {

	private String id_hilfeText = null;
	private GridLayoutData     gl = MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(0, 0, 0, 0));
	
	public HADM_bt_Edit(String p_id_hilfeText) {
		super();
		this.id_hilfeText=p_id_hilfeText;
		this.gl.setColumnSpan(1);
		
		this.setLayoutData(this.gl);
		
		this.setToolTipText(new MyE2_String("Den Eintrag in dieser Zeil bearbeiten").CTrans());

		
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new HADM_MaskModulContainer();
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4Edit() throws myException {
		return new HADM_DataObjectCollector(this.id_hilfeText);
	}

	@Override
	public void define_Actions_4_saveButton(RB_bt_editSingular btEditSingular,	RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,	RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag wurde gespeichert.")));
			}
		});
		bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new HAD_Searcher()._rebuild();
			}
		});

	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_editSingular btEditSingular,RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)	throws myException {
		bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
		bt_Close.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Maske wurde abgebrochen ...")));
			}
		});
	}

	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}
	
	
}
