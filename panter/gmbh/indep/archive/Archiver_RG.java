package panter.gmbh.indep.archive;

import java.util.Date;

import panter.gmbh.indep.exceptions.myException;

public class Archiver_RG extends Archiver {

	public Archiver_RG() throws myException {
		super("RG_ORIGINALE", new Date(), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK);
	}

}
