package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import panter.gmbh.basics4project.DB_ENUMS.REPORT_REITER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_REITER;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class AW2_RECLIST_REPORT_REITER extends RECLIST_REPORT_REITER {

	public AW2_RECLIST_REPORT_REITER() throws myException {
		super(new SEL(_TAB.report_reiter).FROM(_TAB.report_reiter).ORDERUP(REPORT_REITER.reihenfolge).s());
	}

}
