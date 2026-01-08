package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__VALIDATOR_4_ATTACHMENT_POPUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class ES_LIST_BT_VIEW extends RB_bt_List2Mask {
	
	private ES_LIST_BasicModuleContainer list_Container = null;


	public ES_LIST_BT_VIEW(ES_LIST_BasicModuleContainer listContainer) {
		super(false);
		//this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EMAIL_SENDUNGEN_ANZEIGEN"));
		this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.VIEW_MAILMASK));
		this.list_Container = listContainer;

		
		this._setNaviList(listContainer.get_naviList());
		this.set_ToolTip4Button(new MyE2_String("Anzeigen von eMail-Sendungen"));

	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new ES_ModuleContainerMask(null
				,list_Container!=null?list_Container.get_basename_TABLE():null
				,list_Container!=null?list_Container.get_id_TABLE():null
				);
	}



	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		if (this.get_NaviList()==null) {
			throw new myException(this,"Navilist not present !!");
		}
		RB_hm_multi_DataobjectsCollector  vDataObjects = new RB_hm_multi_DataobjectsCollector();
		
		for (String id: this.get_NaviList().get_vSelectedIDs_Unformated()) {
			vDataObjects.put(id,new ES_RB_DataobjectsCollector(id, RB__CONST.MASK_STATUS.VIEW));
		}
		
		if (vDataObjects.size()==0) {
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens eine eMail-Versendung zum Anzeigen aus!")));
		}
		
		return vDataObjects;
	}
	
	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		RECORD_EMAIL_SEND recSend = (RECORD_EMAIL_SEND)rb_ModulContainerMask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.email_send)).getRbDataObjectActual().get_RecORD();
		
		MyE2_String cRueck = new MyE2_String(S.t("Anzeige der eMail-Sendung mit der ID: "),S.ut(recSend.get_ID_EMAIL_SEND_cF()));
		
		return cRueck;
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return null;
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList  navilist) {
		return null;
	}

	
}