package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public interface PdServiceSanktionHashwertEncoder {
	public String generate_sha256_hashcode(MyE2_MessageVector mv, String ... terms_2_encode)throws myException;
}
