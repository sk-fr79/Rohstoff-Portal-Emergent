package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


public class FZ_dataObjectsCollector_doAfterSave_writeStatistikDaten implements FZ_dataObjectsCollector_doAfterSave {

	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {
		
		
		for (RB_Dataobject dob: do_collector) {
		
			if (dob instanceof RB_Dataobject_V2) {
				
				RB_Dataobject_V2 dob2 = (RB_Dataobject_V2)dob;

				//jetzt die bewegung_vektor(en) suchen
				Rec20 r2 = dob2.get_rec20();
				
				if (r2.get_tab()==_TAB.bewegung_vektor) {
					
					Rec20 recWrittenAndLoaded = null;
					if (r2.is_newRecordSet()) {
						recWrittenAndLoaded = r2.get_rec_after_save_new();
					} else {
						recWrittenAndLoaded = r2;
					}
					
					MyLong l_start = new MyLong(bibDB.EinzelAbfrage("SELECT START_ATOM("+recWrittenAndLoaded.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
					MyLong l_ziel = new MyLong(bibDB.EinzelAbfrage("SELECT ZIEL_ATOM("+recWrittenAndLoaded.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
					MyLong l_zahl_rechpos = new MyLong(bibDB.EinzelAbfrage("SELECT RECHPOS("+recWrittenAndLoaded.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
					MyLong l_zahl_gutpos  = new MyLong(bibDB.EinzelAbfrage("SELECT GUTPOS("+recWrittenAndLoaded.get_ufs_dbVal(BEWEGUNG_VEKTOR.id_bewegung_vektor)+") FROM DUAL"));
			
					recWrittenAndLoaded._nv(BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart, l_start.get_cUF_LongString(), mv);
					recWrittenAndLoaded._nv(BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel,  l_ziel.get_cUF_LongString(), mv);
					recWrittenAndLoaded._nv(BEWEGUNG_VEKTOR.zahl_rechpos, l_zahl_rechpos.get_cUF_LongString(), mv);
					recWrittenAndLoaded._nv(BEWEGUNG_VEKTOR.zahl_gutpos, l_zahl_gutpos.get_cUF_LongString(), mv);
					recWrittenAndLoaded._add_field_val_pair(BEWEGUNG_VEKTOR.statistik_timestamp, "SYSDATE+NUMTODSINTERVAL(2,'SECOND')");
					
					recWrittenAndLoaded._SaveAndClean(false, mv);
				}
			}
		}
	}
	

}
