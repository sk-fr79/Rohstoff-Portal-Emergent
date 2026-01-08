package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_PrimaNotaRaster;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;

public class FZ__bt_primaNota extends E2_Button {

	private IF_mapCollector_4_FZ_MaskModulContainer own_ll_cm_collector = null;

	
	public FZ__bt_primaNota(IF_mapCollector_4_FZ_MaskModulContainer p_own_ll_cm_collector) throws myException {
		super();
		this.own_ll_cm_collector = p_own_ll_cm_collector;
		this._image(E2_ResourceIcon.get_RI("info_primanota.png"), true)
			._aaa(new ownActionShowPrima())
			._ttt(new MyE2_String("Anzeige eines Mengen/Preisverlaufs innerhalb dieser Warenbewegung"))
			;
	}

	
	private class ownActionShowPrima extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ__bt_primaNota oThis = FZ__bt_primaNota.this;
			String id_vektor = oThis.own_ll_cm_collector.get_id_bewegung_vektor();
			
			if (S.isFull(id_vektor)) {
				
				new FZ_PrimaNotaRaster(id_vektor)._showInPopupWindow(1200, 600);
				
			}
			
			
		}
	}
	
	
	
	
}
