package rohstoff.Echo2BusinessLogic._TAX.RATE;

import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.basics4project.DB_ENUMS.FIBU_KONTO;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class TAX_DD_FibuKonto extends E2_DropDownSettingsNew {

	public TAX_DD_FibuKonto()	throws myException {
		super( 	new SEL(new TermSimple(FIBU_KONTO.beschreibung.fn()+"||' ('||"+FIBU_KONTO.konto+"||')'").s(),FIBU_KONTO.id_fibu_konto.fn()).FROM(FIBU_KONTO.T()).ORDERUP(FIBU_KONTO.beschreibung).s(),
				true, true);
	}

}
