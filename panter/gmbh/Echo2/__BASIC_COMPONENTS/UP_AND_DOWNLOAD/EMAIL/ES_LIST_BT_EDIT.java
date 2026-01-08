package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__VALIDATOR_4_ATTACHMENT_POPUP;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;


public class ES_LIST_BT_EDIT extends RB_bt_List2Mask {

	private ES_LIST_BasicModuleContainer listContainer = null;
	private ES_MASK_BT_Add_Attachments   bt_add_attach = null;     //button um neue anlagen anzuhaenge
	private E2_Button                    bt_save_edit = null;      //button, um die maske zu speichern und offen zu halten
	
	public ES_LIST_BT_EDIT(ES_LIST_BasicModuleContainer oIST_BasicModuleContainer) throws myException
	{
		super(true);
		this.listContainer = oIST_BasicModuleContainer;
		
//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EMAIL_SENDUNGEN_BEARBEITEN"));
		this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.EDIT_MAILMASK));

		
		this._setNaviList(listContainer.get_naviList());
		this.set_ToolTip4Button(new MyE2_String("Bearbeiten von eMail-Sendungen"));
		
		this.get_grid4MaskExternal().add(new bt_sendEmail(), E2_INSETS.I(2,2,2,2));

		//neuer button um neue attachments zuzufuegen. wird der anlagen-komponente zugefuegt
		this.bt_add_attach  = new ES_MASK_BT_Add_Attachments(this, listContainer);
		
//		if (bibALL.get_bDebugMode()) {
		this.get_grid4MaskExternal()._setSize(50,130);
		this.bt_save_edit  = new E2_Button()._image(E2_ResourceIcon.get_RI("save_edit.png"), E2_ResourceIcon.get_RI("save_edit__.png"))._ttt(new MyE2_String("Die Maske speichern und offenhalten"));
		this.get_grid4MaskExternal().add(this.bt_save_edit, E2_INSETS.I(2,2,2,2));
		this.bt_save_edit.add_oActionAgent(new ownSaveEdit());
//		}
		
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new ES_ModuleContainerMask(this.bt_add_attach
											,listContainer!=null?listContainer.get_basename_TABLE():null
											,listContainer!=null?listContainer.get_id_TABLE():null
											);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler)  throws myException {
		if (this.get_NaviList()==null) {
			throw new myException(this,"Navilist not present !!");
		}
		RB_hm_multi_DataobjectsCollector  vDataObjects = new RB_hm_multi_DataobjectsCollector();
		
		for (String id: this.get_NaviList().get_vSelectedIDs_Unformated()) {
			vDataObjects.put(id,new ES_RB_DataobjectsCollector(id, RB__CONST.MASK_STATUS.EDIT));
		}
		if (vDataObjects.size()==0) {
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens eine eMail-Versendung zum Bearbeiten aus!")));
		}
		

		return vDataObjects;
	}
	

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		RECORD_EMAIL_SEND recSend = (RECORD_EMAIL_SEND)rb_ModulContainerMask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.email_send)).getRbDataObjectActual().get_RecORD();
		
		MyE2_String cRueck = new MyE2_String(S.t("Bearbeiten der eMail-Sendung mit der ID: "),S.ut(recSend.get_ID_EMAIL_SEND_cF()));
		
		return cRueck;
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		RECORD_EMAIL_SEND recSend = (RECORD_EMAIL_SEND)rb_ModulContainerMask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.email_send)).getRbDataObjectActual().get_RecORD();
		
		MyE2_String cRueck = new MyE2_String(S.t("Die eMail-Sendung mit der ID: "),S.ut(recSend.get_ID_EMAIL_SEND_cF()),S.t(" wurde gespeichert !"));
		
		return cRueck;	
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,  E2_NavigationList  navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> vRueck = new Vector<XX_ActionAgentWhenCloseWindow>();
		vRueck.add(new ownCloseAction(rb_ModulContainerMask));
		return vRueck;
	}
	

	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow {
		public ownCloseAction(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_ModuleContainerMASK mask = (RB_ModuleContainerMASK)this.get_oContainer();
			RECORD_EMAIL_SEND recSend = (RECORD_EMAIL_SEND)mask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.email_send)).getRbDataObjectActual().get_RecORD();
			ES_LIST_BT_EDIT.this.listContainer.get_naviList().Refresh_ComponentMAP(recSend.get_ID_EMAIL_SEND_cUF(), E2_ComponentMAP.STATUS_VIEW);
		}
		
	}
	
	
	
	
	private class bt_sendEmail extends MyE2_Button {

		public bt_sendEmail() {
			super(E2_ResourceIcon.get_RI("email.png"));
			this.setToolTipText(new MyE2_String("eMail versenden ...").CTrans());
			this.add_oActionAgent(new actionSaveAndSend());
			this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.SENDMAIL));
			//this.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(ES_LIST_BT_EDIT.this.listContainer.get_basename_TABLE()));
		}

		
		private class actionSaveAndSend extends XX_ActionAgent {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				ES_LIST_BT_EDIT oThis = ES_LIST_BT_EDIT.this;
				//actualkey holen und dataset neu bauen
				String cActualKey = oThis.get_hm_RB_Dataobjects_Container().key_of(oThis.rb_modulContainerMASK().rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector());

				//aktuelle werte speichern
				MyE2_MessageVector  oMV = oThis.rb_modulContainerMASK().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
				
				if (oMV.get_bIsOK()) {
					
					//record erzeugen 
					RECORD_EMAIL_SEND_ext recSend = new RECORD_EMAIL_SEND_ext(new RECORD_EMAIL_SEND(cActualKey));
					recSend.check_and_send_email_if_possible(oMV,recSend.get_MUST_BE_ATTACHMENT_LIST(oMV));
				}

				oMV.add_MESSAGE(oThis.rb_modulContainerMASK().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_MASK_RELOAD());
				bibMSG.add_MESSAGE(oMV);
			}
			
		}
		
		
	}
	
	
	
	private class ownSaveEdit extends RB_actionStandardSaveAndReopen {
		
		public ownSaveEdit() throws myException {
			super(new RB_KM(_TAB.email_send));
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record) throws myException {
			return new ES_RB_DataobjectsCollector(id_record, RB__CONST.MASK_STATUS.EDIT);		
		}

		@Override
		public RB_ModuleContainerMASK get_RB_ModuleContainerMASK() throws myException {
			return ES_LIST_BT_EDIT.this.get_MaskContainer();
		}
		
	}
	
	
}