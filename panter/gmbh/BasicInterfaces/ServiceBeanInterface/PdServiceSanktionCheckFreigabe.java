package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public interface PdServiceSanktionCheckFreigabe {
	public boolean ist_record_gefahrlich(Rec21 adresse_record) throws myException;
}
