package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.DESIGN_UI;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_MASK_ComponentMapCollector;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_MASK_DataObjectCollector;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_MASK_MaskGrid;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_bt_to_open_simple_mask;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_bt_ui_edit_cell extends RB_HL_bt_to_open_simple_mask {

	private MA_DES_design_ui design_ui;

	public MA_DES_bt_ui_edit_cell(MA_DES_design_ui p_parent, String id_zelle) throws myException {
		super();

		this.design_ui = p_parent;
		this._ttt("Zelle bearbeiten");
		this._set_mask_status_and_id(MASK_STATUS.EDIT, id_zelle);
		this.__setImages(null, null);
	}


	@Override
	public RB_ModuleContainerMASK generate_RB_ModuleContainerMASK() throws myException {
		return new ownE2ModulContainerMask();
	}

	@Override
	public RB_DataobjectsCollector_V2 generate_RB_DataobjectsCollector_V2(MASK_STATUS actual_mask_status,
			String id_main_table) throws myException {
		return new MC_DES_MASK_DataObjectCollector(id_main_table, actual_mask_status);
	}

	@Override
	public RB_ComponentMapCollector generate_RB_ComponentMapCollector() throws myException {
		return new MC_DES_MASK_ComponentMapCollector(this.design_ui.get_record_mask_definition());
	}

	@Override
	public RB_grid4masks generate_RB_grid4masks(RB_ComponentMapCollector componentMapCollector) throws myException {
		return new MC_DES_MASK_MaskGrid(componentMapCollector);
	}

	@Override
	public RB_HL_bt_to_open_simple_mask define_actionsagents_for_mask_save_button(
			E2_IF_Handles_ActionAgents button_savemask, RB_ModuleContainerMASK mask) throws myException {
		button_savemask.add_oActionAgent(new RB_actionStandardSave(mask));
		button_savemask.add_oActionAgent(new RB_actionStandardClosePopup(mask));
		button_savemask.add_oActionAgent(new ownActionRefreshList());
		return this;
	}

	@Override
	public RB_HL_bt_to_open_simple_mask define_actionsagents_for_mask_cancel_button(
			E2_IF_Handles_ActionAgents button_cancelmask, RB_ModuleContainerMASK mask) throws myException {
		button_cancelmask.add_oActionAgent(new RB_actionStandardClosePopup(mask));
		button_cancelmask.add_oActionAgent(new ownActionRefreshList());
		return this;
	}

	private class ownActionRefreshList extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MA_DES_bt_ui_edit_cell.this.design_ui.fill_list();

			MA_DES_bt_ui_edit_cell.this.design_ui.render_grid();
		}
	}

	private class ownE2ModulContainerMask extends RB_ModuleContainerMASK {/**/}
}
