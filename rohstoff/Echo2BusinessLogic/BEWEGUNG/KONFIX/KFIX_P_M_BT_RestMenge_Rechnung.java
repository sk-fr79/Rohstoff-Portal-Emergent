package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_BT_RestMenge_Rechnung extends E2_Button {

	/**
	 * 
	 */
	public KFIX_P_M_BT_RestMenge_Rechnung() {
		super();
		
		this._tr("Neuberechnung Restmenge")._backDDark()._lw(true)._i(E2_INSETS.I(3))._style(E2_Button.StyleTextButtonCentered());
		this.setWidth(new Extent(100));
		this.setHeight(new Extent(40));
		
		this._aaa(new ownActionAgent());
		
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			KFIX_P_M_BT_RestMenge_Rechnung oThis = KFIX_P_M_BT_RestMenge_Rechnung.this;
			new KFIX_P___calculate_rests(oThis._find_componentMap_i_belong_to(),true);

		}
		
	}
	
	@Override
	public void mark_Disabled() throws myException {
		this.set_bEnabled_For_Edit(false);
	}
}
