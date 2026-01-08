package panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


/**
 * basisklasse fuer ein allgemeines verfahren, um an jeder beliebigen stelle innerhalb von E2_JasperHASH-objekten und 
 * nachfolgenden klassen im zusammenhang damit, aktionen ausfuehren zu koennen,
 * Ebenfalls wird ein Object zurueckgegeben
 * @author martin
 *
 */
public abstract class Jasper_Exe_ROOT {
	
	public abstract void EXECUTE(E2_JasperHASH oJasperHash, Object  objZusatz, MyE2_MessageVector oMV, Object oSammlerRueckgaben) throws myException;
	
	public abstract Jasper_Exe_CONST.EXECUTER_JUMPPOINTS get_JUMPMarker();

}
