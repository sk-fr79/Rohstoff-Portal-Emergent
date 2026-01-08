package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.exceptions.myException;

public class FZ_MASK_cb_zeige_interne_buchungen extends RB_cb {

	private FZ_MASK_MaskModulContainer  mask_modul_container = null;
	
	
	
	/**
	 * checkbox, schaltet interne aufloesung der buchungen ein/aus
	 */
	public FZ_MASK_cb_zeige_interne_buchungen(FZ_MASK_MaskModulContainer  p_mask_modul_container) {
		super();
		
		this.mask_modul_container=p_mask_modul_container;
		
		this._txt(new MyE2_String("Zeige interne Buchungen"))
		    ._tooltxt(new MyE2_String("eigt automatische Buchungen, die vom System erzeugt werden, an "))
			._aaa(new actionToggleCheckBoxShowHidden());
		
	}

	
	
	private class actionToggleCheckBoxShowHidden extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_MASK_cb_zeige_interne_buchungen.this.mask_modul_container.rebuild_container_grid();
		}
	}

}
