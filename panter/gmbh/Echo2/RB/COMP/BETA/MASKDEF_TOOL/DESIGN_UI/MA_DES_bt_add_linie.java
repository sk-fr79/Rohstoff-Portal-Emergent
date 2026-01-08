package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.DESIGN_UI;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.BETA.E2_PopUpMenue;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_bt_add_linie extends E2_PopUpMenue{
	
	private MA_DES_design_ui 	dui 		= null;
	private int 				linie_ix 	= 0 ;
	
	public MA_DES_bt_add_linie(MA_DES_design_ui p_dui, int i_linie_idx) throws myException{
		super(
				E2_ResourceIcon.get_RI("multi_select_add_new.png"), 
				E2_ResourceIcon.get_RI("multi_select_add_new.png"),
				false,
				new Extent(150), new Extent(50),
				0, 32
			);
		this._ttt("Neue Linie einfuegen.");
		this.dui 		= p_dui;
		this.linie_ix 	= i_linie_idx;
		
		this.addButton(new E2_Button()._aaa(new own_add_oben())	._t("Linie oberhalb")	, true);
		this.addButton(new E2_Button()							._t("-")				, true);
		this.addButton(new E2_Button()._aaa(new own_add_unten())._t("Linie unterhalb")	, true);
	}
	
	private class own_add_oben extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MA_DES_bt_add_linie.this.dui.add_new_linie_before(linie_ix);
		}
		
	}
	
	private class own_add_unten extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MA_DES_bt_add_linie.this.dui.add_new_linie_after(linie_ix);
		}
		
	}
}
