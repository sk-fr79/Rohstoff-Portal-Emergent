
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO.ADI_CONST.ADI_BUTTONS;




public class ADI_LIST_bt_ListToMask extends RB_bt_List2Mask {

	private E2_NavigationList naviList = null;
	private RECORD_ADRESSE rec_adresse = null;

	public ADI_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList, RECORD_ADRESSE  p_rec_adresse) {
		super(bEdit);
		this.naviList = p_naviList;
		this.rec_adresse = p_rec_adresse;
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit ? ADI_BUTTONS.EDIT.db_val() : ADI_BUTTONS.VIEW.db_val()));

	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {

		return new ADI_MASK_MaskModulContainer(this.rec_adresse);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());

		RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
		RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit() ? RB__CONST.MASK_STATUS.EDIT : RB__CONST.MASK_STATUS.VIEW;

		for (String id : v_ids) {
			collector.put(id, new ADI_MASK_DataObjectCollector(id, aktuellerStatus));
		}
		return collector;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		String id4Title = rec_adresse.get_ID_ADRESSE_cUF();
		return new MyE2_String(this.is_UsedToEdit() ? "Bearbeiten von ID="+id4Title : "Anzeige von ID="+id4Title);
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

		return new MyE2_String(rec_adresse.get_ID_ADRESSE_cUF() + " wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> v_action = new Vector<XX_ActionAgentWhenCloseWindow>();
		v_action.add(new ownCloseActionRefreshNavilist(rb_ModulContainerMask));
		return v_action;
	}
	
	private class ownCloseActionRefreshNavilist extends XX_ActionAgentWhenCloseWindow {

		private RB_ModuleContainerMASK maskPopup = null;

		public ownCloseActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
			super(p_maskPopup);
			this.maskPopup = p_maskPopup;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			E2_NavigationList f_naviList = ADI_LIST_bt_ListToMask.this.naviList;

			String id_new = this.maskPopup.rb_ComponentMapCollector(new ADI__TableKey()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.adresse_info.n());

			f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);

			f_naviList._REBUILD_ACTUAL_SITE("");
		}
	}
}
