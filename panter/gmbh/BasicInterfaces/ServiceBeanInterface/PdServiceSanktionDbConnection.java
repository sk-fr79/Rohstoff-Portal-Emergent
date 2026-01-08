package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public interface PdServiceSanktionDbConnection {
	public MyDBToolBox getConnection() throws myException;
}
