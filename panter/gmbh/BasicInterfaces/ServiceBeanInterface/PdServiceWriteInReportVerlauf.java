package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public interface PdServiceWriteInReportVerlauf {
	public void save(E2_JasperHASH ojHash, MyE2_MessageVector omv) throws myException;
	
}
