package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;

public class FZ_CO_Mengegrid extends E2_Grid {

	public FZ_CO_Mengegrid(Component quelle_component, Component ziel_component) {
		super();
		this._setSize(50,100);
		
		this._a(new RB_lab("Quelle")._i(), 	new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(0,0,0,2))
		._a(quelle_component, 				new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,0,0,0))
		._a(new RB_lab("Ziel")._i(), 		new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(0,4,0,2))
		._a(ziel_component, 				new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,4,0,0));
	}

}
