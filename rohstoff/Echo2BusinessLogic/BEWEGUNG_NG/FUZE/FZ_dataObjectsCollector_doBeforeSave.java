package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public interface FZ_dataObjectsCollector_doBeforeSave {
	
	public  void manipulateRecordsBeforeSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException;
	
}
