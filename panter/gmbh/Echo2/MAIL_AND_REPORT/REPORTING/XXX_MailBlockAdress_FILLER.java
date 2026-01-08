package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.indep.exceptions.myException;


/** 
 * abstrakte vorlage fuer MailAdressCollectors, die in den <jaspername>.maildef - steuerdateien stehen und benutzt werden, 
 * um eMailAdressen fuer die eMail-Versendung der report-ergebniss-pdfs zu laden
 * Ein MailBlockGenerator  liefert einen kompletten eMail-Block mit MailAdress4MailBlock - vector zurueck.
 * Als parameter wird der komplette jasperHash uebergeben, sodass das jeweilige modul anhand der hasheintraege die
 * Mailadressen zusammensuchen kann
 */
public abstract class XXX_MailBlockAdress_FILLER
{
	public abstract void fill_MAILAdresses_to_MailBLOCK(MailBlock oMB, E2_JasperHASH oJasperHash, boolean bAddEmptyMailAdressWhen_no_MailAdressFound) throws myException;
}
