package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


/**
 * hilfsklasse, die in die E2_JasperHASH eingreift, wenn die erzeugung der temp-files abgeschlossen ist.
 * Benutzt im konkreten fall um an die erzeugte pdf-datei noch etwas anzuhaengen
 * @author martin
 *
 */
public abstract class XX_ActionAfterCreateTempFiles
{
	public abstract MyE2_MessageVector doActionAfterCreatingTempFiles(E2_JasperHASH  oJasperHash) throws myException;
}
