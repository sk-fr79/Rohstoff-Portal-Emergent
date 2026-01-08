package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_TPA;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.TermStringConcatenatorSql;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST__SEL_Spedition extends SEL {

	public TP_KST__SEL_Spedition() throws myException {
		super();
		this.ADDFIELD(new TermStringConcatenatorSql(" ",ADRESSE.name1,ADRESSE.name2)
				.add_c(", ")
				.add_f(ADRESSE.ort),
				ADRESSE.id_adresse.t()
				)
			.ADD_Distinct()
			.FROM(_TAB.vkopf_tpa)
			.INNERJOIN(_TAB.adresse, VKOPF_TPA.id_adresse, ADRESSE.id_adresse)
			.ORDERUP("1");
		
		DEBUG.System_println(this.s());

	}

}
