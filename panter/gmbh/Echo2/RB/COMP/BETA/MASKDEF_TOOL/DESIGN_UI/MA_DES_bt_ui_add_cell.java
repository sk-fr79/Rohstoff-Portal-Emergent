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

public class MA_DES_bt_ui_add_cell extends RB_HL_bt_to_open_simple_mask {
	
	private int column_coordinate 	= 0;
	private int row_coordinate 		= 0;
	
	private MA_DES_design_ui design_ui;
	
	public MA_DES_bt_ui_add_cell(MA_DES_design_ui p_parent, int i_coor_x, int i_coor_y) throws myException{
		super();

		this.column_coordinate 	= i_coor_x;
		this.row_coordinate 	= i_coor_y;
		this.design_ui 			= p_parent;
		
		this._gld_align_cm()._fsa(-2)._t("+");
		this._ttt("Neue Zelle einfuegen");
		this._set_mask_status_and_id(MASK_STATUS.NEW, "");
		this.__setImages(null, null);
	}
	
	public int get_column_coordinate() {
		return column_coordinate;
	}

	public int get_row_coordinate() {
		return row_coordinate;
	}

	@Override
	public RB_ModuleContainerMASK generate_RB_ModuleContainerMASK() throws myException {
		return new own_container_mask();
	}

	@Override
	public RB_DataobjectsCollector_V2 generate_RB_DataobjectsCollector_V2(MASK_STATUS actual_mask_status,
			String id_main_table) throws myException {
		return new MC_DES_MASK_DataObjectCollector();
	}

	@Override
	public RB_ComponentMapCollector generate_RB_ComponentMapCollector() throws myException {
		return new MC_DES_MASK_ComponentMapCollector(this.design_ui.get_record_mask_definition() , this.column_coordinate,this.row_coordinate);
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
	
	private class own_container_mask extends RB_ModuleContainerMASK{/**/}
	
	private class ownActionRefreshList extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MA_DES_bt_ui_add_cell.this.design_ui.fill_list();
			
			MA_DES_bt_ui_add_cell.this.design_ui.render_grid();
		}
	}
	
}
