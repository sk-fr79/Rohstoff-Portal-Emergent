package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_Loesche_ID_HANDELSDEF extends MyE2_Button {

	public FU_MASK_BT_Loesche_ID_HANDELSDEF() {
		super(E2_ResourceIcon.get_RI("eraser.png"),true);
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			FU_MASK_BT_Loesche_ID_HANDELSDEF.this.EXT().get_oComponentMAP().get__DBComp(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF).prepare_ContentForNew(false);
		}
		
	}
	
	
	
}
