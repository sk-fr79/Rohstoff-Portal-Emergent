package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;

public class ADK_LIST_bt_view_edit extends RB_bt_List2Mask {

	private Vector<String>  vLastUsedIDs =new Vector<String>();
 	
	public ADK_LIST_bt_view_edit(boolean bEdit, E2_NavigationList naviList) {
		super(bEdit);
		this._setNaviList(naviList);
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new ADK_M_ModuleContainer();
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		E2_NavigationList  naviList = this.get_NaviList();
		this.vLastUsedIDs.clear();
		this.vLastUsedIDs.addAll(naviList.get_vSelectedIDs_Unformated());
		
		if (this.vLastUsedIDs.size()==0) {
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Adressklasse auswählen!")));
			return null;
		} else {
			
			RB_hm_multi_DataobjectsCollector hmDataContainer = new RB_hm_multi_DataobjectsCollector();
			
			for (String id: this.vLastUsedIDs) {
				hmDataContainer.put(id, new ADK_M_Dataobjects_Container(id,this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW));
			}
			return hmDataContainer;
		}
		
		
		
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Bearbeite Adressklassen");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Adressklasse wurde gespeichert !");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> v_action = new Vector<XX_ActionAgentWhenCloseWindow>();
		v_action.add(new ownActionClose(rb_ModulContainerMask));
		
		return v_action;
	}

	private class ownActionClose extends XX_ActionAgentWhenCloseWindow {
		public ownActionClose(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ADK_LIST_bt_view_edit.this.get_NaviList().Refresh_ComponentMAP(ADK_LIST_bt_view_edit.this.vLastUsedIDs, E2_ComponentMAP.STATUS_VIEW);
		}
		
	}
	
	
}
