package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class EMS_bt_NewListToMask extends RB_bt_New {

	private E2_NavigationList  NaviList = null;
	
	public EMS_bt_NewListToMask(E2_NavigationList  naviList) {
		super();
		this.NaviList = naviList;
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new EMS__PopUpContainer(this.NaviList);
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
		EMS_DataObjectContainer do_Container = new EMS_DataObjectContainer(null, false);
		
		return do_Container;
	}


	
	private class ownActionAddNewIdToList extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RB_bt_maskSaveAndClose bt_save = (RB_bt_maskSaveAndClose)oExecInfo.get_MyActionEvent().getSource();
			
			if (bt_save.get_mask_Container().rb_hm_component_map_collector().size()!=1) {
				throw new myException(this,"Only allowed on MaskContainers which have exact one ComponentMapCollector!");
			}
			RB_ComponentMapCollector collector = bt_save.get_mask_Container().rb_FirstComponentMapCollector();
			
			RB_DataobjectsCollector actualDB_Container = collector.rb_Actual_DataobjectCollector();
			
			if (S.isFull(actualDB_Container.get_LastWrittenNewID(_DB.EMAIL_SEND_SCHABLONE))) {
				EMS_bt_NewListToMask.this.NaviList.get_vActualID_Segment().add(actualDB_Container.get_LastWrittenNewID(_DB.EMAIL_SEND_SCHABLONE));
			}
		}
	}



	@Override
	public void define_Actions_4_saveButton(RB_bt_New btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionAddNewIdToList());
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_New btNewInList,	RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)	throws myException {
		bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	}

}
