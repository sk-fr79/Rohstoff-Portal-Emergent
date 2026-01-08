package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.DESIGN_UI.MA_DES_design_ui;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_COMP_bt_call_building_interface extends MyE2_ButtonInLIST {

	public MA_DES_COMP_bt_call_building_interface() throws myException {
		super(E2_ResourceIcon.get_RI("einstellungen.png"));
		this._aaa(new own_jump_2_design_ui());
		this._ttt("Masken bearbeiten");
	}

	
	public class own_jump_2_design_ui extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_ButtonInLIST bt = (MyE2_ButtonInLIST) oExecInfo.get_MyActionEvent().getSource();
			
			E2_ComponentMAP oMap = bt.EXT().get_oComponentMAP();
			String uf_id = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			Rec20 record_mask_def = new Rec20(_TAB.mask_def)._fill_id(uf_id);
			
			MA_DES_design_ui ui = new MA_DES_design_ui(uf_id, false);
			ui.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), 
					new MyE2_String("Maske " + record_mask_def.get_fs_dbVal(MASK_DEF.maskname) + " : Definition"));
		}
	}

}
