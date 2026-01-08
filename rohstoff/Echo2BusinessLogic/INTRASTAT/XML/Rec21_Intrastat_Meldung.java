package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_Intrastat_Meldung extends Rec21 {

	public Rec21_Intrastat_Meldung() throws myException {
		super(_TAB.intrastat_meldung);
		
	}

	public Rec21_Intrastat_Meldung(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.intrastat_meldung) {
			throw new myException(this,"Only Record from type " +  this.get_TABLENAME() + " is allowed !");
		}
	}

}
