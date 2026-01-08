package panter.gmbh.plugins;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * abstrakte klassendefinition fuer die ausfuehrung von DB-operationen, die listen-auswertungen
 * zudefiniert werden koennen (z.B. um temporaere tabellen zu fuellen)
 * @author martin
 *
 */
public abstract class XX_DB_PLUGIN_PREPARE_REPORTING {
	
	/**
	 * methode, um vor einem Aufruf zu pruefen, ob es noetig ist, die eigentliche aufbereitungsmethode laufen zu lassen
	 * @return
	 * @throws myException
	 */
	public abstract boolean CHECK_IF_MUST_BE_EXECUTED() throws myException;

	public abstract MyE2_MessageVector EXECUTED_BEFORE_REPORT() throws myException;
	
}
