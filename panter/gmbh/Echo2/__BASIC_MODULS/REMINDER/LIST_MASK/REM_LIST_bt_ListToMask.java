
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.REM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;




public class REM_LIST_bt_ListToMask extends RB_bt_List2Mask {

//	private E2_NavigationList naviList = null;

	private REM__IF_getTableAndID list_container = null;

	
	public REM_LIST_bt_ListToMask(REM__IF_getTableAndID p_list_container, boolean bEdit, E2_NavigationList p_naviList) {
		super(bEdit);
		this._setNaviList(p_naviList);
		this.set_bAutoRefreshList(true);
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit ? REM_BUTTONS.EDIT.db_val() : REM_BUTTONS.VIEW.db_val()));
		this.list_container = p_list_container;

	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {

		return new REM_MASK_MaskModulContainer(this.list_container);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.get_NaviList().get_vSelectedIDs_Unformated());

		RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
		RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit() ? RB__CONST.MASK_STATUS.EDIT : RB__CONST.MASK_STATUS.VIEW;

		for (String id : v_ids) {
			collector.put(id, new REM_MASK_DataObjectCollector(id, aktuellerStatus));
		}
		return collector;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

		return new MyE2_String(this.is_UsedToEdit() ? "Bearbeiten von Erinnerungsmeldungen" : "Anzeige von Erinnerungsmeldungen");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

		return new MyE2_String("Die Erinnerungsmeldung wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {

		return null;
	}
}
