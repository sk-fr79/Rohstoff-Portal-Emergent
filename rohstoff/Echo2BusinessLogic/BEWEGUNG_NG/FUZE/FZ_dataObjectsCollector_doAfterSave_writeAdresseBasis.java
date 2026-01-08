package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;


public class FZ_dataObjectsCollector_doAfterSave_writeAdresseBasis implements FZ_dataObjectsCollector_doAfterSave {

	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {
		
		for (RB_Dataobject dob: do_collector) {
		
			if (dob instanceof RB_Dataobject_V2) {
				
				RB_Dataobject_V2 dob2 = (RB_Dataobject_V2)dob;

				//jetzt die bewegung_stationen suchen
				Rec20 r2 = dob2.get_rec20();
				
				if (r2.get_tab()==_TAB.bewegung_station) {
					
					Rec20 recStation = null;
					if (r2.is_newRecordSet()) {
						recStation = r2.get_rec_after_save_new();
					} else {
						recStation = r2;
					}
					
					Rec20_adresse recAdresse = new Rec20_adresse(recStation.get_up_Rec20(BEWEGUNG_STATION.id_adresse,ADRESSE.id_adresse,true));
					
					recStation._nv(BEWEGUNG_STATION.id_adresse_basis, recAdresse.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse),mv);
					
					recStation._SaveAndClean(false, mv);
				}
			}
		}
	}
	

}
