package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.JASPER_MODUL_LISTEN;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.exceptions.myException;



public class Auswertungen_standardReports_FremdeListen extends Auswertungen_standardReports
{

	public Auswertungen_standardReports_FremdeListen(RECORD_REPORT RecReport, String cHauptMenue) throws myException
	{
		super(RecReport);
		this.set_cAuswerteGruppe(cHauptMenue);
		
	}

}
