package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_KOSTEN_SucheKombinationenQuelleZiel;

public class FS_BT_Ermittle_Kosten_Relationen_In_AdressListModul extends MyE2_Button {

	
	public FS_BT_Ermittle_Kosten_Relationen_In_AdressListModul() {
		super(new MyE2_String("Kostenrelationen: aufbauen"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.LIST_VALID_ERMITTLE_KOSTENRELATIONEN);
	}
 
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new TP_KOSTEN_SucheKombinationenQuelleZiel(null);
		}			
		
	}


}
