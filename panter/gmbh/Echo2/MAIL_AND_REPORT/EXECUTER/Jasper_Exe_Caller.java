package panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER;

import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public class Jasper_Exe_Caller {

	public Jasper_Exe_Caller() {
		super();
	}
	
	/**
	 * 2014-09-25: ausfuehrungsmethode einer bestimmten klassensorte, es ein Jumppoint uebergeben werden
	 * @param oJasperHash == die betreffende JasperHash
	 * @param objZusatz == in der von Jasper_Exe_ROOT ausgewertete Objekte, kann auch null sein 
	 * @param JumpPoint == eNaum-einsprungpunkt
	 * @throws myException
	 */
	public void ExecuterStart(E2_JasperHASH oJasperHash, Object  objZusatz,  EXECUTER_JUMPPOINTS JumpPoint, MyE2_MessageVector oMV_Rueck, Object oRueckgabeObject) throws myException {

		//es darf nur entweder die klasse als kenner oder der Jumppoint uebergeben werden, der komplementaer muss null sein
		if (JumpPoint == null) {
			throw new myException("Execute-Start MUST have one JumpPoint");
		}
		//die Jumppoints werden priorisiert
		for (Jasper_Exe_ROOT  oExecuter: oJasperHash.get_vExecuters()) {
			if (oExecuter.get_JUMPMarker()!=null && oExecuter.get_JUMPMarker().equals(JumpPoint)) {
				oExecuter.EXECUTE(oJasperHash, objZusatz, oMV_Rueck, oRueckgabeObject);
			}
		}
	}
	

}
