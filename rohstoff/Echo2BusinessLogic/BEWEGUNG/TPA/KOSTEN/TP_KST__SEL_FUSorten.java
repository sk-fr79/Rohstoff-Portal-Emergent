package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.TermStringConcatenatorSql;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST__SEL_FUSorten extends SEL {

	public TP_KST__SEL_FUSorten() throws myException {
		super();
		
		this.ADDFIELD(new TermStringConcatenatorSql("", ARTIKEL.artbez1).add_c(" (").add_f(ARTIKEL.anr1).add_c(")"), ARTIKEL.id_artikel.t())
			.ADD_Distinct()
			.FROM(_TAB.vpos_tpa)
			.INNERJOIN(_TAB.vpos_tpa_fuhre, VPOS_TPA.id_vpos_tpa, VPOS_TPA_FUHRE.id_vpos_tpa)
			.INNERJOIN(_TAB.artikel, VPOS_TPA_FUHRE.id_artikel, ARTIKEL.id_artikel)
			.ORDERUP("1");

//		DEBUG.System_println(this.s());
		
	}

}
