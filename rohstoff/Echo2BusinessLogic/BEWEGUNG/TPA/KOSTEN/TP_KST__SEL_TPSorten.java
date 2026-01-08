package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST__SEL_TPSorten extends SEL {

	public TP_KST__SEL_TPSorten() throws myException {
		super();
		
		this.ADDFIELD(ARTIKEL.artbez1.t(), ARTIKEL.id_artikel.t())
			.ADD_Distinct()
			.FROM(_TAB.vpos_tpa)
			.INNERJOIN(_TAB.artikel, VPOS_TPA.id_artikel, ARTIKEL.id_artikel)
			.ORDERUP(ARTIKEL.artbez1);
		
		//DEBUG.System_println(this.s());

	}

}
