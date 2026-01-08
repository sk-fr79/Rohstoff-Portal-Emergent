package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK.SCAN_MASK_DataObjectCollector;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK.SCAN_MASK_MaskModulContainer;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_bt_ListToMask extends RB_bt_List2Mask {

	private E2_NavigationList  naviList = null;

	public SCAN_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
		super(bEdit);
		this.naviList = p_naviList;
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new SCAN_MASK_MaskModulContainer();
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());

		if(v_ids.size()>0){
			RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
			RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;

			for (String id: v_ids) {
				collector.put(id, new SCAN_MASK_DataObjectCollector(id,aktuellerStatus));
			}
			return collector;
		}else{
			mv_sammler._addAlarm("Bitte wählen Sie einen Scanner Definition.");
			return null;
		}
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Scanner definitionen":"Anzeige von Scanner definitionen");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(	RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Scanner definition wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> closeActionVector = new Vector<>();
		closeActionVector.add(new ownCloseAction(rb_ModulContainerMask));
		return closeActionVector;
	}

	
	 private class ownCloseAction extends XX_ActionAgentWhenCloseWindow{


			public ownCloseAction(RB_ModuleContainerMASK oMask) {
				super(oMask);
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				E2_NavigationList f_naviList = SCAN_LIST_bt_ListToMask.this.naviList;

				f_naviList._REBUILD_COMPLETE_LIST("");

			}
	    	
	    }
}
