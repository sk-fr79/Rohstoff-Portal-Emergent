package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.indep.exceptions.myException;

public class ADK_LIST_bt_New extends RB_bt_New {

	private E2_NavigationList  		naviList = null;
	private ADK_M_ModuleContainer 	ownModuleContainer = null;
	
	public ADK_LIST_bt_New(E2_NavigationList  p_naviList) {
		super();
		this.naviList=p_naviList;
	}


	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		this.ownModuleContainer = new ADK_M_ModuleContainer();
		return this.ownModuleContainer;
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4New()	throws myException {
		return new ADK_M_Dataobjects_Container(null, RB__CONST.MASK_STATUS.NEW);
	}

	@Override
	public void define_Actions_4_saveButton(RB_bt_New btNewInList,RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new saveAction(maskPopup,this.naviList));
	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_New btNewInList,	RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)	throws myException {
		bt_Close.add_oActionAgent(new closeAction(maskPopup));
	}

	
	private class saveAction extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup = null;
		private E2_NavigationList  naviList = null;
		public saveAction(RB_ModuleContainerMASK p_maskPopup, E2_NavigationList  p_naviList) {
			super();
			this.maskPopup = p_maskPopup;
			this.naviList = p_naviList;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			//gilt nur fuer masken, die genau einen rb_ComponentMapCollector haben
			if (this.maskPopup.rb_hm_component_map_collector().size()!=1) {
				throw new myException(this,"Only for Container4Visualisation with exact one ComponentMapCollector!");
			}
			
			bibMSG.add_MESSAGE(this.maskPopup.rb_FirstComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true));
			if (bibMSG.get_bIsOK()) {
				String id_new = this.maskPopup.rb_FirstComponentMapCollector().rb_Actual_DataobjectCollector().get_LastWrittenNewID(ADRESSKLASSE_DEF.fullTabName());
				this.naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
				this.naviList._REBUILD_ACTUAL_SITE("");
				this.maskPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde die neue Adressklasse mit der ID: ",true,""+id_new,false," angelegt!",true)));
			}
		}
		
	}
	
	
	private class closeAction extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup = null;
		public closeAction(RB_ModuleContainerMASK p_maskPopup) {
			super();
			this.maskPopup = p_maskPopup;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			this.maskPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Neueingabe wurde unterbrochen ")));
			
		}
		
	}
	
	
}
