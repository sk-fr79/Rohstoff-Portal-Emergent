package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.SANKTION.SANKTION_Treffer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public interface PdServiceSanktionStatusCheck {
	public VEK<SANKTION_Treffer> check(MyE2_MessageVector mv, Rec21_adresse record_2_check) throws myException;
	
}
