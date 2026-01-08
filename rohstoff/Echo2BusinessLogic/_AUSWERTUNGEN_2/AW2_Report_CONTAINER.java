package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import panter.gmbh.Echo2.Auswertungen.REPORT_MODULE_CONTAINER;
import panter.gmbh.indep.exceptions.myException;

/**
 * WICHTIG: container nur fuer die datenbank-basierten abfragen
 * @author martin
 *
 */
public class AW2_Report_CONTAINER extends REPORT_MODULE_CONTAINER { 
	/*
	 * dreigeteiler container fuer die datenbankabfragen
	 * 1. Selector mit erlaubten abfragen des benutzers
	 * 2. Bereich mit abfrage-eingaben
	 * 3. liste mit ergebnissen
	 */
	public AW2_Report_CONTAINER(String cID_REPORT)  throws myException
	{
		super(cID_REPORT);
		this.set_bVisible_Row_For_Messages(false);
	}		
}